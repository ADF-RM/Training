from datetime import date
import pytest
from solution import SqlConnector
from utils.my_db_config import My_SQL
from utils.data_validation import Validate

validation = Validate()


@pytest.fixture
def get_age():
    return 18


@pytest.fixture
def get_gender():
    return 'F'


@pytest.fixture
def get_date():
    return "2001-05-05"


@pytest.fixture
def get_nationality():
    return "INDIAN"

@pytest.fixture
def get_state():
    return "TAMIL_NADU"


@pytest.fixture
def get_salary():
    return 50000


@pytest.fixture
def get_last_date():
    return date(2021,6,27)


@pytest.fixture
def check_date(get_date):
    return validation.check_date_time_format(get_date)


@pytest.fixture
def check_age_with_gender(get_age, get_gender):
    if (get_gender == 'M' and get_age >= 21) or (get_gender == 'F' and get_age >= 18):
        return True


@pytest.fixture
def check_nationality(get_nationality):
    if get_nationality in ['INDIAN', 'AMERICAN']:
        return True


@pytest.fixture
def check_state(get_state):
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
    if get_state in states_list:
        return True

@pytest.fixture
def check_salary(get_salary):
    if get_salary >= 10000 and get_salary <= 90000:
        return True

@pytest.fixture
def check_last_five_transactions(get_last_date):
    if (date.today() - get_last_date).days > 5:
        return True

def test_date(check_date):
    assert check_date is True

def test_age_with_gender(check_age_with_gender):
    assert check_age_with_gender is True

def test_nationality(check_nationality):
    assert check_nationality is True

def test_state(check_state):
    assert check_state is True

def test_salary(check_salary):
    assert check_salary is True

def test_last_five_transactions(check_last_five_transactions):
    assert check_last_five_transactions is True