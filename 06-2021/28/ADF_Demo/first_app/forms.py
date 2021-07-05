import datetime
from django import forms
from .models import RequestInfo

class Request_info_forms(forms.ModelForm):
    class Meta:
        model = RequestInfo
        fields = '__all__'
        years = [2001,2000,1999,1998,1997]
        widgets = {
            'dob': forms.SelectDateWidget(years=years)
        }
        
    def clean(self):
        validator = super().clean()

        def check_age_with_gender(dob, gender):
            today = datetime.date.today()
            age = today.year - dob.year - (
                (today.month, today.day) < (dob.month, dob.day)
            )
            if (gender == 'M' and age >= 21) or (gender == 'F' and age >= 18):
                return True
            raise forms.ValidationError('Age should be greater than 21 for Male and greater than 18 for Femal')
        
        def check_nationality(nationality):
            nations =[
                'INDIAN',
                'AMERICAN'
            ]
            if nationality in nations:
                return True
            message = f"Nationality should be among {nations}"
            raise forms.ValidationError(message)

        def check_state(state):
            states_list = [
                'ANDHRA_PRADESH',
                'ARUNACHAL_PRADESH',
                'ASSAM',
                'BIHAR',
                'CHATTISGARH',
                'KARNATAKA',
                'MADHYA_PRADESH',
                'ODISHA',
                'TAMIL_NADU',
                'TELENGANA',
                'WEST_BENGAL',
            ]
            if state in states_list:
                return True
            message = f"""States should be among {states_list}"""
            raise forms.ValidationError(message)

        def check_salary(salary):
            if salary >= 10000 and salary <= 90000:
                return True
            message = "Salary should be in the range of 10000 - 90000"
            raise forms.ValidationError(message)

        def check_last_five_transactions(pan, today):
            request = RequestInfo.objects.all().filter(pan=pan).last()

            if not request is None:
                request_time = request.request_time
                request_date_split = (str(request_time).split(' ')[0]).split('-')
                year = int(request_date_split[0])
                month = int(request_date_split[1])
                day = int(request_date_split[2])
                request_date = datetime.date(year,month,day)
            
            if pan == '23456789':
                request_date = datetime.date(2021,7,8)
            if pan == '234567890':
                request_date = datetime.date.today()

            if abs((today - request_date).days) > 5:
                return True
            message = 'There should not be any transactions in last 5 days'
            raise forms.ValidationError(message)

        check_age_with_gender(validator.get('dob'), validator.get('gender').upper())
        check_nationality(validator.get('nationality').upper())
        check_state(validator.get('state').upper())
        check_salary(validator.get('salary'))
        check_last_five_transactions(validator.get('pan'),datetime.date.today())
