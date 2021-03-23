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

❗ 커스터마이징 한 부분 추가 예정

<br/>