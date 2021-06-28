import datetime

class Validate:

    def check_date_time_format(self, date_time):
        format = "%Y-%m-%d"
        try:
            assert datetime.datetime.strptime(date_time, format)
            return True
        except AssertionError:
            return False

    def age_calculate(self,dob):
        today = datetime.date.today()
        s = list(map(int,dob.split('-')))
        print(s)
        birthDate = datetime.date(*s)
        print(birthDate)
        age = today.year - birthDate.year - ((today.month, today.day) < (birthDate.month, birthDate.day))
        print(f'age : {age}')
        return age
    
    def check_age_with_gender(self, dob, gender):
        age = self.age_calculate(dob)
        if gender is 'M' and age >= 20:
            return True
        if gender is 'F' and age >= 18:
            return True
        else:
            return False
