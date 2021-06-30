from django.conf.urls import url
from . import views

urlpatterns = [
    url('',views.FirstPage.as_view(),name='home'),
    url('responses/',views.FirstPage.as_view(),name='responses'),
]