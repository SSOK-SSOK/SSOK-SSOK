# ================================================================
#
#   File name   : detection_demo.py
#   Author      : PyLessons
#   Created date: 2020-09-27
#   Website     : https://pylessons.com/
#   GitHub      : https://github.com/pythonlessons/TensorFlow-2.x-YOLOv3
#   Description : object detection image and video example
#
# ================================================================
from yolov3.configs import *
from yolov3.utils import detect_image, detect_realtime, detect_video, Load_Yolo_model, detect_video_realtime_mp
import tensorflow as tf
import numpy as np
import cv2
import os
os.environ['CUDA_VISIBLE_DEVICES'] = '0'

image_path = "../AI/images/test_image.jpg"
detected_image_path = "../AI/images/detected_image.jpg"
video_path = ""

yolo = Load_Yolo_model()
detect_image(yolo, image_path, detected_image_path, input_size=YOLO_INPUT_SIZE, show=True, rectangle_colors=(255, 0, 0))


# for filename in filenames:
#     full_filename = os.path.join(image_path, filename)
#     ext = os.path.splitext(full_filename)[-1]
#     if ext == '.jpg' or ext == '.JPG':
#         full_predict_filename = os.path.join(image_path, 'out/' + filename)
#         # print(full_filename, full_predict_filename)

# detect_video(yolo, video_path, "", input_size=YOLO_INPUT_SIZE, show=False, rectangle_colors=(255,0,0))
# detect_realtime(yolo, '', input_size=YOLO_INPUT_SIZE, show=True, rectangle_colors=(255, 0, 0))

#detect_video_realtime_mp(video_path, "Output.mp4", input_size=YOLO_INPUT_SIZE, show=False, rectangle_colors=(255,0,0), realtime=False)
