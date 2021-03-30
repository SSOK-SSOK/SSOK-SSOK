from django.urls import path
from . import views

urlpatterns = [
    path('stt/', views.stt),
]