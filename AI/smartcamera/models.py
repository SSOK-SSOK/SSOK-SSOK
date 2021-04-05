from django.db import models

# Create your models here.
class Images(models.Model):
  title = models.CharField(max_length=255)
  image = models.FileField(upload_to='images/', null=True)

  def __str__(self):
        return self.title