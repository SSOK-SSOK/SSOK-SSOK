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

# base64코드를 변환하여 jpg로 저장합니다.
def convert_base64_to_img(data):
    prefix = 'data:image/jpeg;base64,'
    cut = data[len(prefix):]
    im = Image.open(BytesIO(base64.b64decode(cut)))
    im.save('..\\AI\\images\\test_image.jpg')

# Create your views here.

@api_view(['POST'])
@method_decorator(csrf_exempt, name='dispatch')
def detection(request):
    convert_base64_to_img(request.data['image'])
    # image = base64.b64decode(image_code).decode('utf-8')
    # print(image)
    return Response({
        'message' : '사진 잘 저장됨!!',
        'items' : ['파이썬', '사진', '정확도', 'Azure'],
    })
