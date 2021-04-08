import argparse

class config:
    # parser 인스턴스 생성
    parser = argparse.ArgumentParser(description='object detection에 필요한 세팅 값입니다.')
    
    # 캡션 데이터가 있는 파일 경로
    #parser.add_argument('--caption_file_path', help='캡션 데이터의 파일 경로입니다.', type=str, default='.\\datasets\\captions.csv')

    # 이미지 파일이 저장된 경로
    #parser.add_argument('--images_path', help='이미지의 파일 경로입니다.', type=str, default='.\\datasets\\images\\images')

    # 샘플링 여부
    #parser.add_argument('--do_sampling', type=bool, default=True)