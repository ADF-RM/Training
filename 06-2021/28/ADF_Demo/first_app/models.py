from django.db import models

class RequestInfo(models.Model):
     
    first_name = models.CharField(max_length=20)
     
    middle_name = models.CharField(max_length=20, blank=True) 
    
    last_name = models.CharField(max_length=20, blank=True)
     
    dob = models.DateField(blank=True)
     
    gender = models.CharField(max_length=1, blank=True)
     
    nationality = models.CharField(max_length=20, blank=True)
     
    current_city = models.CharField(max_length=30, blank=True)
     
    state = models.CharField(max_length=20, blank=True)
     
    pin_code = models.TextField(blank=True)
     
    qualification = models.TextField(blank=True)
     
    salary = models.IntegerField(blank=True)
     
    pan = models.TextField(blank=True)
     
    request_time = models.DateTimeField(auto_now=True)

    class Meta:
        managed = True
        db_table = 'request_info'


class ResponseInfo(models.Model):
    
    request = models.ForeignKey(RequestInfo, models.DO_NOTHING)

    response_status = models.TextField(max_length=7)

    reason = models.TextField()
    
    response_time = models.DateTimeField(auto_now=True)

    class Meta:
        managed = True
        db_table = 'response_info'