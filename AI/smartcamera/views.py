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
from ../yolov3.configs import *
from ../yolov3.utils import detect_image, detect_realtime, Load_Yolo_model, detect_video_realtime_mp
import tensorflow as tf
import numpy as np
import cv2
import os


# base64코드를 변환하여 jpg로 저장합니다.
def convert_base64_to_img(data):
    prefix = 'data:image/jpeg;base64,'
    cut = data[len(prefix):]
    im = Image.open(BytesIO(base64.b64decode(cut)))
    im.save('..\\AI\\images\\test_image.jpg')


@api_view(['POST'])
@method_decorator(csrf_exempt, name='dispatch')
def detection(request):
    # base64이미지를 jpg로 저장
    convert_base64_to_img(request.data['image'])

    # YOLO사용 준비
    os.environ['CUDA_VISIBLE_DEVICES'] = '0'
    image_path = "../images/test_image.jpg"
    detected_image_path = "../images/detected_image.jpg"
    video_path = ""

    # YOLO 모델로 detecting
    yolo = Load_Yolo_model()
    detect_image(yolo, image_path, detected_image_path, input_size=YOLO_INPUT_SIZE, show=True, rectangle_colors=(255, 0, 0))

    return Response({
        'message' : '사진 잘 저장됨!!',
        'items' : ['파이썬', '사진', '정확도', 'Azure'],
    })





# detect_realtime(yolo, '', input_size=YOLO_INPUT_SIZE, show=True, rectangle_colors=(255, 0, 0))
# detect_video_realtime_mp(video_path, "Output.mp4", input_size=YOLO_INPUT_SIZE, show=False, rectangle_colors=(255,0,0), realtime=False)