# How to use YOLO & COCO Dataset

>  COCO데이터셋과 YOLO 모델로 Object Detection을 구현하는 방법을 설명합니다.

<br/>

### 01. 가상환경 생성

> 라이브러리, 패키지의 충돌을 방지하기 위해 가상환경을 `salee_AI` 를 생성합니다.

```bash
$ conda create -n salee_AI python=3.7.7
```

> 설치한 라이브러리, 패키지, 모듈은 아래와 같습니다.

```
Package              Version
-------------------- -------------------
absl-py              0.12.0
astor                0.8.1
cached-property      1.5.2
certifi              2020.12.5
Cython               0.29.22
gast                 0.2.2
google-pasta         0.2.0
grpcio               1.36.1
h5py                 3.2.1
importlib-metadata   3.7.3
Keras-Applications   1.0.8
Keras-Preprocessing  1.1.2
Markdown             3.3.4
numpy                1.20.1
opencv-python        4.5.1.48
opt-einsum           3.3.0
pandas               1.2.3
pip                  21.0.1
protobuf             3.15.6
python-dateutil      2.8.1
pytz                 2021.1
setuptools           52.0.0.post20210125
six                  1.15.0
tensorboard          1.15.0
tensorflow           1.15.0
tensorflow-estimator 1.15.1
termcolor            1.1.0
typing-extensions    3.7.4.3
Werkzeug             1.0.1
wheel                0.36.2
wrapt                1.12.1
zipp                 3.4.1
```

<br/>

### 02. 모델, 데이터셋 다운로드 

> 모델은 YOLO의 Tensorflow 버전인 [`Darkflow`](https://github.com/thtrieu/darkflow) , 데이터셋은 [`COCO Dataset 2017`](https://cocodataset.org/#download) 버전을 사용합니다.

```bash
$ mkdir coco
$ cd coco

// COCO 2017 dataset 다운로드
$ wget http://images.cocodataset.org/zips/train2017.zip
$ unzip train2017.zip
$ rm train2017.zip

// COCO 2017 annotation 다운로드
$ wget http://images.cocodataset.org/annotations/annotations_trainval2017.zip
$ unzip annotations_trainval2017.zip
$ rm annotations_trainval2017.zip
```

```
초기 파일 트리
-team1
	-darkflow
	-data
		-COCO
			-annotations
				instances_train2017.json
			-val2017
				0000000xxxx.jpg
				...
			coco.names
```

<br/>

### 03. COCO Dataset annotation 전처리

> COCO annotation은 `.json` 파일이지만, YOLO에서 인식하려면 `.xml` 형식으로 변환해주어야 합니다. 
>
> [coco-annotations-to-xml](https://github.com/mhiyer/coco-annotations-to-xml) 파일을 커스터마이징하여 annotation파일 전처리를 진행합니다. 

```xml
# .xml 파일 예시
<annotation>
	<folder>n02814533</folder>
	<filename>n02814533_62180</filename>
	<source>
		<database>ILSVRC_2012</database>
	</source>
	<size>
		<width>1280</width>
		<height>960</height>
		<depth>3</depth>
	</size>
	<segmented>0</segmented>

</annotation>
```

```
포맷 변경 후 파일 구조
-team1
	-darkflow
	-data
		-COCO
			-annotations
				-Annotations
					0000000xxxx.xml
			-val2017
				0000000xxxx.jpg
				...
			coco.names
```

```python
# coco_get_annotations_xml_format.py

...

if __name__=='__main__':
    
    # read annotations file
    annotations_path = [.json 주석파일의 경로를 입력합니다]
    
    ...
    
    # specify image locations
    image_folder = [이미지 데이터셋의 경로를 입력합니다.]
    
    ...
    
    # loop through the annotations in the subset
    for anno in annotations:
        # 주석파일에서 이미지 id를 추출하고 데이터셋에 있는 이미지 이름 형식에 맞게 수정합니다.
        image_id = anno['image_id']
        image_name = '{0:012d}.jpg'.format(image_id)    
        
	...
    
    # generate .xml files
    for image_name in image_dict.keys():
        write_to_xml(image_name, image_dict, image_folder, savepath)
        print('generated for: ', image_name)
```

❗ opencv에서 `AttributeError: 'NoneType' object has no attribute 'shape'`  발생 이유

: **주석파일의 image id에 해당하는 실제 이미지 데이터셋이 없어서 발생합니다.** 데이터셋에 있는 이미지의 주석파일이 모두 들어와있는지 확인해주거나,  매치가 안되는 이미지와 주석파일은 삭제해주면 해결됩니다.

<br/>

### 04. YOLO Object detection 

> [darkflow](https://github.com/thtrieu/darkflow)를 이용하면 직접 딥러닝 구조를 구현하지 않아도 모델을 학습할 수 있는 장점이 있습니다. 

```bash
# darkflow를 설치하고 build 합니다.
$ git clone https://github.com/thtrieu/darkflow.git
$ cd darkflow/
$ python setup.py build_ext --inplace
$ pip install .
```

```bash
# 학습
$ python flow 
--model ./cfg/tiny-yolo-salee.cfg # 기본 cfg에서 따로 복사하여 사용합니다.
--labels ./labels.txt 
--trainer adam 
--dataset ../data/COCO/train2017/ 
--annotation ../data/COCO/annotations/Annotations/ 
--train 
--summary ./logs 
--batch 5 
--epoch 100 
--save 1000 
--keep 5 
--lr 1e-04 
--gpu 0.5 
--load -1 # --load 를 없애면 처음부터 학습시작, --load -1는 이어서 학습합니다. 
```

```bash
# 테스트
python flow 
--imgdir ../data/testset/ 
--model ./cfg/tiny-yolo-salee.cfg 
--load -1 
--batch 1 
--threshold 0.03 # threshold를 조절하여 객체 탐지의 민감도를 조절할 수 있습니다. 
```

<br/>

### 05. 학습 진행 상황

- 160,000 : 정확도 매우 50%...? 너무 낮음...
- learning rate를 줄여서 계속 학습을 진행할 예정