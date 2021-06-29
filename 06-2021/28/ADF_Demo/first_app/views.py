import json
from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from .forms import Request_info_forms
from .models import RequestInfo, ResponseInfo
from django.views import generic


class FirstPage(generic.View):
    def get(self, request):
        form = Request_info_forms()
        params = {
            'form': form.as_p(),
        }
        return render(request, "templates/index.html", context=params)

    def post(self, request):
        form = Request_info_forms(request.POST)
        print('forms...')
        params = {
            'form': form.as_p(),
        }
        if form.is_valid():
            print('\n\nValidator : Form saved\n\n')
            form.save()
            fields = form.cleaned_data
            print(f'\n\nForm fields : {fields}\n\n')
            request_form = RequestInfo.objects.filter(
                pan=fields['pan']).order_by('request_time').reverse()[0]
            print(f"\n\n i : {request_form.request_time} ")

            get_request_id = RequestInfo.objects.get(id=request_form.id)

            response_data = dict()
            response_info = ResponseInfo.objects.create(
                request=get_request_id,
                response_status='Success',
                reason='',
                response_time = request_form.request_time,
            )
            response_info.save()

            response_table = ResponseInfo.objects.all()

            for i in response_table:
                response_data[i.id] = {
                    'id': i.id,
                    'request_id': i.request_id,
                    'response_status': i.response_status,
                    'reason': i.reason,
                    'request_time': str(i.response_time),
                }
            json_response = json.dumps(response_data, indent=4, sort_keys=True)
            params = {
                'responses': json_response,
            }

            return JsonResponse(response_data,safe=True,content_type='application/json')

            # return render(request, "templates/response_list.html", context=params)
        return render(request, "templates/index.html", context=params)
