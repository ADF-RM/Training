import csv
import os


class ReadCSV:
    def __init__(self, filename):
        self.filename = filename

    def read_csv(self):
        mydict = {
            'first_name': [],
            'last_name': [],
            'street_name': [],
            'town_name': [],
            'country_name': [],
            'pin_code': [],
        }

        try:
            with open(file, 'r') as f:
                csv_file = csv.reader(f)
                try:
                    for i in csv_file:
                        mydict['first_name'].append(i[0])
                        mydict['last_name'].append(i[1])
                        mydict['street_name'].append(i[2])
                        mydict['town_name'].append(i[3])
                        mydict['country_name'].append(i[4])
                        mydict['pin_code'].append(i[5])
                    return mydict
                except IndexError as error:
                    print('Data Not found in a given column : ' + str(error))
        except FileNotFoundError as error:
            print('Exception occured : ' + str(error))


filename = 'address.csv'
file = os.path.join(os.getcwd(), filename)

r = ReadCSV(filename)
print(r.read_csv())
