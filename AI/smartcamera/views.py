from django.shortcuts import render

from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view

from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from django.utils.decorators import method_decorator

import base64
from PIL import Image
from io import BytesIO

# For Object Detection (YOLO)
from yolov3.configs import *
from yolov3.utils import detect_image, detect_realtime, Load_Yolo_model, detect_video_realtime_mp
import tensorflow as tf
import numpy as np
import cv2
import os


# base64코드를 변환하여 jpg로 저장합니다.
def convert_base64_to_img(data):
    prefix = 'data:image/jpeg;base64,'
    cut = data[len(prefix):]
    im = Image.open(BytesIO(base64.b64decode(cut)))
    im.save('..\\images\\test_image.jpg')

# 정답 여부를 체크합니다.
def check_answer(answer):
    items = []

    # 파일을 불러옵니다.
    with open('./images/score.txt', 'r') as f:
        lines = f.readlines()
        for line in lines:
            score, category = line.split(",")
            score = int(score)
            category = category.strip("\n")
            items.append([score, category])
        f.close()

    # 정확도 순으로 내림차순 정렬합니다.
    items.sort(key=lambda x : -x[0])
    
    if len(items) == 1:
        score, category = items[0][0], items[0][1]
        if category == "person":
            return False, "물체가 없어요"
        else:
            if category == answer:
                return True, score
            else:
                return False, category
    else:
        # 첫번째 값이 person이면 두번째 값과, 아니면 첫번째 값과 비교합니다.
        if items[0][1] == "person":
            score, category = items[1][0], items[1][1]
            if category == answer:
                return True, score
            else:
                return False, category
        else:
            score, category = items[0][0], items[0][1]
            if category == answer:
                return True, score
            else:
                return False, category


@api_view(['POST'])
@method_decorator(csrf_exempt, name='dispatch')
def detection(request):
    # base64이미지를 jpg로 저장
    convert_base64_to_img(request.data['image'])

    # YOLO사용 준비
    os.environ['CUDA_VISIBLE_DEVICES'] = '0'
    video_path = ""

    # YOLO 모델로 detecting
    yolo = Load_Yolo_model()
    detect_image(yolo, input_size=YOLO_INPUT_SIZE, show=True, rectangle_colors=(255, 0, 0))

    # detecting된 결과와 정답 비교하기
    answer = request.data['question']
    is_correct, info = check_answer(answer)

    return Response({
        'message' : '사진 테스트 완료!!',
        'is_correct' : is_correct,
        'info' : info,
    })

# detect_realtime(yolo, '', input_size=YOLO_INPUT_SIZE, show=True, rectangle_colors=(255, 0, 0))
# detect_video_realtime_mp(video_path, "Output.mp4", input_size=YOLO_INPUT_SIZE, show=False, rectangle_colors=(255,0,0), realtime=False)