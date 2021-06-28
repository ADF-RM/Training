"""
This file let user to connect the MySQL database.
"""
import json
import logging
import datetime
from utils.argument_parser import Parser
from utils.my_db_config import My_SQL
from utils.data_validation import Validate

def request_time():
    """Gives current time"""
    current_time = datetime.datetime.now()
    return current_time

class SqlConnector:
    """
    This class is used to open the database and connect the database
    """

    def __init__(self):
        # self.__log = Logging()
        self.__parser = Parser(
            'SqlConnector',
            description='SqlConnector for Python')
        self.__parser.add_positional_arguments(
            ('FIRST_NAME', '-f', 'first name of the user', 'Null'),
            ('LAST_NAME', '-l', 'last name of the user', 'Null'),
            ('DOB', '-dob', 'Date of Birth', 'Null'),
            ('GENDER', '-g', 'gender of the user', 'Null'),
            ('NATIONALITY', '-n', 'nationality of the user', 'Null'),
            ('CURRENT_CITY', '-c', 'Current city of the user', 'Null'),
            ('STATE', '-s', 'State of the user', 'Null'),
            ('PIN_CODE', '-p', 'pincode of the user', 'Null'),
            ('QUALIFICATION', '-q', 'Qualification of the user', 'Null'),
            ('SALARY', '-s', 'Salary of the user', 'Null'),
            ('PAN', '-pan', 'PAN Number of the user', 'Null'),
        )
        self.__parser.add_optional_arguments(
            ('-m', '-m', 'Middle Name of the user', 'MIDDLE_NAME', 'Null'),
        )
        self.args = self.__parser.get_args()

    def get_values(self):
        """"Gives all the fields collected from command line"""
        args = self.args
        values = []
        values.extend([
            args.FIRST_NAME,
            args.MIDDLE_NAME,
            args.LAST_NAME,
            args.DOB,
            args.GENDER,
            args.NATIONALITY,
            args.CURRENT_CITY,
            args.STATE,
            args.PIN_CODE,
            args.QUALIFICATION,
            args.SALARY,
            args.PAN,
        ])
        return values


    def is_user_repeated(self):
        """Method to check if user transactions are repeated"""
        required_query = f"""
        select REQUEST_TIME from request_info where PAN = '{self.args.PAN}' ORDER BY REQUEST_TIME;
        """
        sql = My_SQL()
        sql.connect_db('adf_user')
        row_count = sql.get_row_count('request_info')
        print(row_count[0])
        if row_count[0] > 0:
            cursor = my_sql.get_cursor()
            cursor.execute(required_query)
            today = datetime.date.today()
            try:
                records = cursor.fetchall()
                if len(records) == 0:
                    return True
                day = int(str(*records[-1]).split(' ')[0].split('-')[2])
                assert (today.day - day ) > 5
                return True
            except AssertionError as err:
                logging.error(err)
                return False
        else:
            return True

    def validate(self):
        """Method to validate the data to be stored"""
        validation = Validate()

        def check_date_format():
            try:
                assert validation.check_date_time_format(self.args.DOB) is True
                logging.warning('Age format is correct')
                return True
            except AssertionError as err:
                logging.warning(err)
                print(err)
                return 'Age should be in the format of YYYY-MM-DD'

        def check_age_with_gender():
            try:
                assert validation.check_age_with_gender(
                    self.args.DOB, self.args.GENDER) is True
                logging.warning('age and gender are perfect')
                return True
            except AssertionError as err:
                logging.warning(
                    'Age should be greater than 21 for male and greater than 18 for female')
                print(err)
                return 'Age should be greater than 21 for male and greater than 18 for female'

        def check_last_five_transaction():
            try:
                assert self.is_user_repeated() is True
                logging.warning('No last transactions for last 5 days')
                return True
            except AssertionError as err:
                message = 'There should not be any last 5 day transactions from the same user'
                logging.warning(message)
                print(err)
                return 'There should not be any last 5 day transactions from the same user'

        def check_nationality():
            print('m111...\n\n')
            try:
                assert self.args.NATIONALITY.upper() in ['INDIAN', 'AMERICAN']
                message = 'Proud of {}'.format(self.args.NATIONALITY.upper())
                logging.warning(message)
                return True
            except AssertionError as err:
                logging.warning('Nationality should be Indian or American')
                print(err)
                return 'Nationality should be Indian or American'

        def check_state():
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
            try:
                assert self.args.STATE.upper() in states_list
                message = 'poud of {}'.format(self.args.STATE.upper())
                logging.warning(message)
                return True
            except AssertionError as err:
                print(err)
                message = 'Invalid state.. States should be in {}'.format(states_list)
                logging.warning(message)
                return f'Invalid state.. States should be in {states_list}'

        def check_salary():
            try:
                assert int(self.args.SALARY) >= 10000 and int(self.args.SALARY) <= 90000
                logging.warning('Salary validated')
                return True
            except AssertionError as err:
                print(err)
                logging.warning(err)
                return 'Salary should be less than 90000 and greater than 10000'

        try:
            test_case = check_date_format()
            assert test_case is True
            test_case = check_age_with_gender()
            assert test_case is True
            test_case = check_last_five_transaction()
            assert test_case is True
            test_case = check_nationality()
            assert test_case is True
            test_case = check_state()
            assert test_case is True
            test_case = check_salary()
            assert test_case is True
            print('Success validating')
            logging.warning('Success validating')
            return True
        except AssertionError:
            print(test_case)
            return test_case

if __name__ == '__main__':
    logging.basicConfig(
        filename='logger.txt',
        filemode='w+',
        format='%(asctime)s %(levelname)s - %(message)s',
        datefmt='%Y-%m-%d %H:%M:%S'
    )

    DATABASE_NAME = 'adf_user'

    REQUEST_TABLE_NAME = 'Request_Info'
    RESPONSE_TABLE_NAME = 'Response_Info'

    DB_PARAMS = "ID int PRIMARY KEY AUTO_INCREMENT,FIRST_NAME CHAR(20) NOT NULL,MIDDLE_NAME CHAR(20),LAST_NAME CHAR(20),DOB DATE,GENDER CHAR(1),NATIONALITY CHAR(20),CURRENT_CITY CHAR(30),STATE CHAR(20),PIN_CODE TEXT,QUALIFICATION TEXT,SALARY INT,PAN TEXT, REQUEST_TIME TEXT"
    REQUEST_INFO_TABLE_FIELDS = "FIRST_NAME, MIDDLE_NAME, LAST_NAME, DOB, GENDER, NATIONALITY, CURRENT_CITY, STATE, PIN_CODE, QUALIFICATION, SALARY, PAN, REQUEST_TIME"
    DB_PARAMS1 = f"ID int PRIMARY KEY AUTO_INCREMENT, REQUEST_ID INT, RESPONSE_STATUS TEXT NOT NULL, REASON TEXT, RESPONSE_TIME TEXT, FOREIGN KEY(REQUEST_ID) REFERENCES {REQUEST_TABLE_NAME}(ID)"
    RESPONSE_INFO_TABLE_FIELDS = 'REQUEST_ID, RESPONSE_STATUS, REASON, RESPONSE_TIME'

    sql_connect = SqlConnector()
    REQUEST_TABLE_VALUES = f"{str(sql_connect.get_values())[1:-1]}, '{request_time()}'"

    my_sql = My_SQL()
    my_sql.connect_db(DATABASE_NAME)
    print(my_sql.show_db())

    my_sql.create_table(REQUEST_TABLE_NAME, DB_PARAMS)
    my_sql.create_table(RESPONSE_TABLE_NAME, DB_PARAMS1)

    VALIDATION_STATUS = sql_connect.validate()
    if VALIDATION_STATUS is True:
        RESPONSE_TABLE_VALUES = ''
        my_sql.insert_into_table(
            REQUEST_TABLE_NAME, REQUEST_INFO_TABLE_FIELDS, REQUEST_TABLE_VALUES)
        RECORDS = my_sql.get_recent_transactions(REQUEST_TABLE_NAME, 'ID', 1, 'ID')
        REQUEST_TIME = my_sql.get_recent_transactions(REQUEST_TABLE_NAME, 'REQUEST_TIME', 1, 'REQUEST_TIME')
        RESPONSE_TABLE_VALUES = f"{RECORDS[0][0]}, 'Success', '','{REQUEST_TIME[0][0]}'"
        my_sql.insert_into_table(
            RESPONSE_TABLE_NAME, RESPONSE_INFO_TABLE_FIELDS, RESPONSE_TABLE_VALUES)
    else:
        count = my_sql.get_row_count(REQUEST_TABLE_NAME)
        if count[0] == 0:
            RECORDS = 0
        else:
            RECORDS = my_sql.get_recent_transactions(REQUEST_TABLE_NAME, 'ID', 1, 'ID')
            RECORDS = RECORDS[0][0]
        RESPONSE_TABLE_VALUES = f"{RECORDS}, 'Failed', '{VALIDATION_STATUS}','{request_time()}'"
        my_sql.insert_into_table(
            RESPONSE_TABLE_NAME, RESPONSE_INFO_TABLE_FIELDS, RESPONSE_TABLE_VALUES)

    print(my_sql.display_table(REQUEST_TABLE_NAME))

    RESPONSE = my_sql.display_table(RESPONSE_TABLE_NAME)

    JSON_DICT = dict()

    print(RESPONSE)

    for rows in RESPONSE:
        JSON_DICT[rows[0]] = {
            'REQUEST_ID' : rows[1],
            'REQUEST_STATUS' : rows[2],
            'REASON' : rows[3],
            'REQUEST_TIME' : rows[4]
        }

    JSON_RESPONSE = json.dumps(JSON_DICT, indent=4, sort_keys=True)

    print('\n\n JSON Response')
    print(f'\n\n {JSON_RESPONSE}')

    my_sql.disconnect()
