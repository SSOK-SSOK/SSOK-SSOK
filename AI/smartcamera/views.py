from django.shortcuts import render

from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view

from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from django.utils.decorators import method_decorator

# For save Image
import base64, secrets, io
from PIL import Image
from django.core.files.base import ContentFile
from .models import Images

# For Object Detection (YOLO)
from yolov3.configs import *
from yolov3.utils import detect_image, detect_realtime, Load_Yolo_model, detect_video_realtime_mp
import tensorflow as tf
import numpy as np
import cv2
import os


# base64코드를 변환하여 jpeg로 저장합니다.
def get_image_from_data_url( data_url, resize=True, base_width=600 ):
    _format, _dataurl       = data_url.split(';base64,')
    _filename, _extension   = secrets.token_hex(20), _format.split('/')[-1]

    # generating the contents of the file
    file = ContentFile( base64.b64decode(_dataurl), name="test_image.jpg")

    # resizing the image, reducing quality and size
    if resize:
        image = Image.open(file)
        image_io = io.BytesIO()

        # resize
        w_percent    = (base_width/float(image.size[0]))
        h_size       = int((float(image.size[1])*float(w_percent)))
        image        = image.resize((base_width,h_size), Image.ANTIALIAS)

        # save resized image
        image.save(image_io, format=_extension)

        # generating the content of the new image
        file = ContentFile( image_io.getvalue(), name="test_image.jpg" )

    return file

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
    im = get_image_from_data_url(request.data['image'])
    
    image = Images.objects.last()
    image.images = im
    image.save()
    
    # YOLO사용 준비
    os.environ['CUDA_VISIBLE_DEVICES'] = '0'
    video_path = ""

    # YOLO 모델로 detecting
    last_image = Images.objects.last().images
    yolo = Load_Yolo_model()
    detect_image(yolo, image.images.url,  input_size=YOLO_INPUT_SIZE, show=True, rectangle_colors=(255, 0, 0))

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
