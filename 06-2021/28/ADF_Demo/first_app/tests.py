import datetime
from first_app import urls
from first_app.forms import Request_info_forms
from django.test import TestCase, Client
from .models import RequestInfo, ResponseInfo
from django.urls import reverse

class TestTable(TestCase):

    def setUp(self):
        self.client = Client()
        self.request_table = RequestInfo.objects.create(
            first_name='Naveen',
            middle_name='R',
            last_name='M',
            dob=datetime.date(2000, 5, 5),
            gender='M',
            nationality='INDIAN',
            current_city='COIMBATORE',
            state='TAMIL_NADU',
            pin_code='641026',
            qualification='B.Tech',
            salary=18000,
            pan='23456789',
        )
        self.response_table = ResponseInfo.objects.create(
            request=self.request_table,
            response_status="Success",
            reason='',
        )

    def test_model(self):
        self.assertEqual(
            ResponseInfo.objects.get(
                request=self.request_table
            ).response_status,
            "Success"
        )

    def test_forms_correct(self):
        form = Request_info_forms(
            data={
                'first_name': 'Naveen',
                'middle_name': 'R',
                'last_name': 'M',
                'dob': datetime.date(2000, 5, 5),
                'gender': 'M',
                'nationality': 'INDIAN',
                'current_city': 'COIMBATORE',
                'state': 'TAMIL_NADU',
                'pin_code': '641026',
                'qualification': 'B.Tech',
                'salary': 18000,
                'pan': '23456789',
            }
        )
        self.assertTrue(form.is_valid())

    def test_age_with_gender(self):
        form = Request_info_forms(
            data={
                'first_name': 'Naveen',
                'middle_name': 'R',
                'last_name': 'M',
                'dob': datetime.date(2001, 5, 5),
                'gender': 'M',
                'nationality': 'INDIAN',
                'current_city': 'COIMBATORE',
                'state': 'TAMIL_NADU',
                'pin_code': '641026',
                'qualification': 'B.Tech',
                'salary': 18000,
                'pan': '23456789',
            }
        )
        self.assertFalse(form.is_valid())

    def test_nationality(self):
        form = Request_info_forms(
            data={
                'first_name': 'Naveen',
                'middle_name': 'R',
                'last_name': 'M',
                'dob': datetime.date(2000, 5, 5),
                'gender': 'M',
                'nationality': 'CANADIAN',
                'current_city': 'COIMBATORE',
                'state': 'TAMIL_NADU',
                'pin_code': '641026',
                'qualification': 'B.Tech',
                'salary': 18000,
                'pan': '23456789',
            }
        )

        self.assertFalse(form.is_valid())

    def test_state(self):
        form = Request_info_forms(
            data={
                'first_name': 'Naveen',
                'middle_name': 'R',
                'last_name': 'M',
                'dob': datetime.date(2000, 5, 5),
                'gender': 'M',
                'nationality': 'INDIAN',
                'current_city': 'COIMBATORE',
                'state': 'KERALA',
                'pin_code': '641026',
                'qualification': 'B.Tech',
                'salary': 18000,
                'pan': '23456789',
            }
        )
        self.assertFalse(form.is_valid())

    def test_salary(self):
        form = Request_info_forms(
            data={
                'first_name': 'Naveen',
                'middle_name': 'R',
                'last_name': 'M',
                'dob': datetime.date(2000, 5, 5),
                'gender': 'M',
                'nationality': 'INDIAN',
                'current_city': 'COIMBATORE',
                'state': 'TAMIL_NADU',
                'pin_code': '641026',
                'qualification': 'B.Tech',
                'salary': 180000,
                'pan': '23456789',
            }
        )
        self.assertFalse(form.is_valid())

    def test_recent_transactions_true(self):
        form = Request_info_forms(
            data={
                'first_name': 'Naveen',
                'middle_name': 'R',
                'last_name': 'M',
                'dob': datetime.date(2000, 5, 5),
                'gender': 'M',
                'nationality': 'INDIAN',
                'current_city': 'COIMBATORE',
                'state': 'TAMIL_NADU',
                'pin_code': '641026',
                'qualification': 'B.Tech',
                'salary': 18000,
                'pan': '23456789',
            }
        )

        self.assertTrue(form.is_valid())

    def test_recent_transactions_false(self):
        form = Request_info_forms(
            data={
                'first_name': 'Naveen',
                'middle_name': 'R',
                'last_name': 'M',
                'dob': datetime.date(2000, 5, 5),
                'gender': 'M',
                'nationality': 'INDIAN',
                'current_city': 'COIMBATORE',
                'state': 'TAMIL_NADU',
                'pin_code': '641026',
                'qualification': 'B.Tech',
                'salary': 18000,
                'pan': '234567890',
            }
        )
        self.assertFalse(form.is_valid())

    def test_first_page_get(self):
        response = self.client.get(reverse('home'))
        self.assertEqual(response.status_code, 200)
        self.assertTemplateUsed('templates/index.html')

    def test_first_page_post(self):
        post_url = reverse('responses')
        form = Request_info_forms(
            data = {
            'first_name': 'Naveen',
            'middle_name': 'R',
            'last_name': 'M',
            'dob': datetime.date(2000, 5, 5),
            'gender': 'M',
            'nationality': 'INDIAN',
            'current_city': 'COIMBATORE',
            'state': 'TAMIL_NADU',
            'pin_code': '641026',
            'qualification': 'B.Tech',
            'salary': 18000,
            'pan': '23456789',
            }
        
        )
        response = self.client.post(post_url,form.data)

        # print(f'\n\n response : {response}')

        print(f'\n\n status_code : {response.status_code}\n\n')
        self.assertEqual(response.status_code, 200)
        