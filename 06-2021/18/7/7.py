import sys


class Application:
    def __init__(self, *details):
        self.details = details
        self.map = {}

    def display(self):
        self.map['name'] = self.details[0]
        self.map['age'] = self.details[1]
        self.map['gender'] = self.details[2]
        self.map['salary'] = self.details[3]
        self.map['state'] = self.details[4]
        self.map['city'] = self.details[5]

        return self.map


details = sys.argv[1][1:-1].split(',')
a = Application(*details)
print(a.display())