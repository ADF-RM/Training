import os
import sys


class GetFile(object):
    def __init__(self) -> None:
        self.l = []

    def open(self, dir=None):
        try:
            with open(dir, "r") as f:
                for i in str(f.read()).strip('.').split(' '):
                    if len(i) >= 1:
                        self.l.append(i.lower())
            return self.l
        except FileNotFoundError as e:
            print("Exception " + str(e))
            return

    def findUnique(self):
        temp = set(self.l)
        for i in temp:
            if self.l.count(i) > 1:
                temp.remove(i)
        temp = list(temp)
        temp = sorted(temp, key=lambda x: len(x))
        return temp

    def writeFile(self, temp):
        with open("final_text.txt", 'w') as f:
            for i in temp:
                f.write(i + ' - ' + str(len(i)) + '\n')


file = sys.argv[1]
dir = f'{os.getcwd()}\{file}'

getFile = GetFile()
getFile.open(file)
list = getFile.findUnique()
getFile.writeFile(list)
