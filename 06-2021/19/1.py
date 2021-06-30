import sys
from collections import Counter
import re


class Solution:
    def __init__(self, filename) -> None:
        self.filename = filename
        self.file = self.openFile()

    def openFile(self):
        try:
            with open(self.filename, 'r') as f:
                try:
                    assert len(str(f.read)) > 1
                    return str(f.read())
                except AssertionError:
                    print('File does not contains any words')
        except FileNotFoundError as e:
            print(e.message)

    def findPrefix(self):
        print('\n Words starting with \'to\'')
        count = 0
        for i in self.file.strip('.').split(' '):
            if i.lower().startswith('to') and i != 'to':
                count += 1
                # print(i)
        print(count)

    def findSuffix(self):
        print('\n Words ending with \'ing\'')
        count = 0
        for i in self.file.strip('.').split(' '):
            if i.lower().endswith('ing') and i != 'ing':
                # print(i)
                count += 1
        print(count)

    def countMax(self):
        m = Counter(self.file.strip('.').split(' '))
        print(f'\nThe maximum occuring word is : {m.most_common(1)[0][0]}')

    def palindromeCheck(self):
        print('\n Palindrome words are :')
        s = set()
        for i in self.file.strip('.').split(' '):
            if i == i[::-1]:
                s.add(i)
        print(*s)

    def getUnique(self):
        print('\n Unique set of words are :')
        m = Counter(self.file.strip('.').split(' '))
        leastCommon = []
        for i in m.most_common():
            if i[1] == 1:
                leastCommon.append(i[0])
        print(*leastCommon)

    def wordDict(self):
        print('\nWord dict with key as counter index and value as words in the file : ')
        map = dict()
        m = Counter(self.file.strip('.').split(' '))
        for i in range(len(m.most_common())):
            map[i] = m.most_common()[i][0]
        print(map)

    def writeFile(self):
        vowelMap = []

        capitalizedWordsList = []

        capitalizedFifthWordsList = []

        hiphenSemiColonizedWordsList = []

        def capitalizeThird():
            print('\n Capitalizing third letter of every word :')
            for i in self.file.strip('.').split(' '):
                if len(i) > 2:
                    capitalizedWordsList.append(
                        "".join([x.upper() if index == 2 else x for index, x in enumerate(i)]))
            print(capitalizedWordsList)

        def vowelSplit():
            print('\n Vowel split of words :')
            s = self.file.strip('.')
            s = re.split('a|e|i|o|u|\n', s)
            vowelMap.extend(s)
            print(vowelMap)

        def capitalizeFifthWord():
            print('\n Capitalizing Fifth Word')
            for index, value in enumerate(self.file.strip('.').split(' ')):
                if index % 5 == 0:
                    s = value.upper()
                else:
                    s = value
                capitalizedFifthWordsList.append(s)
            print(' '.join(capitalizedFifthWordsList))

        def hiphenSemiColon():
            print('\n Hiphen split of words')
            s = self.file.strip('.')
            s = s.replace(' ', '-')
            s = s.replace('\n', ';')
            hiphenSemiColonizedWordsList.append(s)
            print(' '.join(hiphenSemiColonizedWordsList))

        def writeFile():
            print(vowelMap)
            with open('newFile.txt', 'w+') as f:
                f.write('\nWord Split using vowels : \n')
                f.write(str(vowelMap))
                f.write('\n\nCapitalized third letter of every words : \n')
                f.write(str(capitalizedWordsList))
                f.write('\n\nCapitalized fifth words : \n')
                f.write(' '.join(capitalizedFifthWordsList))
                f.write('\n\nHipen for spaces and semicolon for newline : \n')
                f.write(' '.join(hiphenSemiColonizedWordsList))

        vowelSplit()
        capitalizeThird()
        capitalizeFifthWord()
        hiphenSemiColon()
        writeFile()


filename = sys.argv[1]
s = Solution(filename)
s.findPrefix()
s.findSuffix()
s.countMax()
s.palindromeCheck()
s.getUnique()
s.wordDict()
s.writeFile()
