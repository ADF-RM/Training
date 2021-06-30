import pytest
from solution import StringManipulation, WriteConfig
import os

@pytest.fixture
def get_config():
    return os.path.join(os.getcwd(), 'config')


@pytest.fixture
def read_config(get_config):
    with open(get_config, 'r') as file:
        return str(file.read())


@pytest.fixture
def get_input_file(read_config):
    lines = read_config.split('\n')
    return lines[0].split('=')[1]


@pytest.fixture
def get_output_file(read_config):
    lines = read_config.split('\n')
    return lines[1].split('=')[1]


@pytest.fixture
def read_output_file(get_output_file):
    with open(get_output_file, 'r') as file:
        lines = str(file.read())
        return lines


def test_check_config(get_config):
    assert os.path.isfile(get_config)


def test_check_input_output_path(get_input_file, get_output_file):
    assert os.path.isfile(get_input_file)
    assert os.path.isfile(get_output_file)


def test_output_file(read_output_file):
    output_file = str(read_output_file)
    assert len(output_file) > 1
    list_of_words_in_output_file = [
        'Word Split using vowels : ',
        'Capitalized third letter of every words : ',
        'Capitalized fifth words : ',
        'Hipen for spaces and semicolon for newline : ',
    ]
    for words in list_of_words_in_output_file:
        assert words in output_file
