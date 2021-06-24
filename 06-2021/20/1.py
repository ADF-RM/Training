import os
import sys
import logging
from collections import Counter
import re
import getopt


class Logger(object):
    def __init__(self):
        logging.basicConfig(
            filename='logger.txt',
            filemode='w+',
            format='%(asctime)s %(levelname)s - %(message)s',
            datefmt='%Y-%m-%d %H:%M:%S'
        )

    def fileReadSuccess(self):
        logging.warning('File read success..')

    def fileReadFail(self):
        logging.warning('File read failed.. File might have been deleted')

    def fileWrite(self):
        logging.warning('File write success..')

    def fileEmpty(self):
        logging.warning('File seems to be empty..')


class FileReadWrite(object):
    def __init__(self):
        self.__input_file = None
        self.__output_file = None
        self.__config = os.path.join(os.getcwd(), 'config')
        self.setInputOutputFile()

    def getConfig(self):
        return self.__config

    def setInputOutputFile(self):
        l = Logger()
        try:
            with open(self.__config, 'r') as f:
                try:
                    lines = str(f.read()).split('\n')
                    assert len(lines) > 1
                    print('lines', str(lines))
                    self.__input_file = (lines[0].split('='))[1]
                    self.__output_file = (lines[1].split('='))[1]
                    print('input_file')
                    print(self.__input_file)
                    l.fileReadSuccess()
                except AssertionError:
                    print('Config file is empty')
                    l.fileEmpty()
        except FileNotFoundError as e:
            print(e)
            l.fileReadFail()

    def readFile(self):
        with open(self.__input_file, 'r') as f:
            return str(f.read())

    def writeFile(self, contents):
        with open(self.__output_file, 'a+') as f:
            f.write(contents)

    def getInputFile(self):
        print('input file')
        print(self.__input_file)
        try:
            with open(str(self.__input_file), 'r') as f:
                return str(f.read())
        except FileNotFoundError as e:
            print('Input File not found')

    def __del__(self):
        print('FileReadWrite Object deleted')


class writeConfig(FileReadWrite):
    def __init__(self):
        super().__init__()
        self.config = super().getConfig()

    def write(self):
        argv = sys.argv[1:]
        try:
            opts, args = getopt.getopt(argv, "i:o:")
            print(f'opt {opts} : args {args}')
        except:
            print('error reading command line arguments')

        for opt, arg in opts:
            if opt in ['-i']:
                input_file = arg
            elif opt in ['-o']:
                output_file = arg
        print(
            f'\n\n Input file : {input_file} ; Output file : {output_file}\n\n')

        with open(self.config, 'w+') as f:
            f.write(f'INPUT_FILE={input_file}')
            f.write('\n')
            f.write(f'OUTPUT_FILE={output_file}')
            f.write('\n')
        super().setInputOutputFile()

    def __del__(self):
        print('WriteConfig object deleted')


class StringManipulation(FileReadWrite):
    def __init__(self):
        super().__init__()
        self.input_file = super().getInputFile()
        self.findPrefix()
        self.findSuffix()
        self.countMax()
        self.palindromeCheck()
        self.getUnique()
        self.wordDict()
        self.writeFile()

    def findPrefix(self):
        print('\n Words starting with \'to\'')
        count = 0
        for i in self.input_file.strip('.').split(' '):
            if i.lower().startswith('to') and i != 'to':
                count += 1
                # print(i)
        print(count)

    def findSuffix(self):
        print('\n Words ending with \'ing\'')
        count = 0
        for i in self.input_file.strip('.').split(' '):
            if i.lower().endswith('ing') and i != 'ing':
                # print(i)
                count += 1
        print(count)

    def countMax(self):
        m = Counter(self.input_file.strip('.').split(' '))
        print(f'\nThe maximum occuring word is : {m.most_common(1)[0][0]}')

    def palindromeCheck(self):
        print('\n Palindrome words are :')
        s = set()
        for i in self.input_file.strip('.').split(' '):
            if i == i[::-1]:
                s.add(i)
        print(*s)

    def getUnique(self):
        print('\n Unique set of words are :')
        m = Counter(self.input_file.strip('.').split(' '))
        leastCommon = []
        for i in m.most_common():
            if i[1] == 1:
                leastCommon.append(i[0])
        print(*leastCommon)

    def wordDict(self):
        print('\nWord dict with key as counter index and value as words in the file : ')
        map = dict()
        m = Counter(self.input_file.strip('.').split(' '))
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
            for i in self.input_file.strip('.').split(' '):
                if len(i) > 2:
                    capitalizedWordsList.append(
                        "".join([x.upper() if index == 2 else x for index, x in enumerate(i)]))
            print(capitalizedWordsList)

        def vowelSplit():
            print('\n Vowel split of words :')
            s = self.input_file.strip('.')
            s = re.split('a|e|i|o|u|\n', s)
            vowelMap.extend(s)
            print(vowelMap)

        def capitalizeFifthWord():
            print('\n Capitalizing Fifth Word')
            for index, value in enumerate(self.input_file.strip('.').split(' ')):
                if index % 5 == 0:
                    s = value.upper()
                else:
                    s = value
                capitalizedFifthWordsList.append(s)
            print(' '.join(capitalizedFifthWordsList))

        def hiphenSemiColon():
            print('\n Hiphen split of words')
            s = self.input_file.strip('.')
            s = s.replace(' ', '-')
            s = s.replace('\n', ';')
            hiphenSemiColonizedWordsList.append(s)
            print(' '.join(hiphenSemiColonizedWordsList))

        def write():
            pass

        vowelSplit()
        capitalizeThird()
        capitalizeFifthWord()
        hiphenSemiColon()
        write()
        super().writeFile('\nWord Split using vowels : \n')
        super().writeFile(str(vowelMap))
        super().writeFile('\n\nCapitalized third letter of every words : \n')
        super().writeFile(str(capitalizedWordsList))
        super().writeFile('\n\nCapitalized fifth words : \n')
        super().writeFile(' '.join(capitalizedFifthWordsList))
        super().writeFile('\n\nHipen for spaces and semicolon for newline : \n')
        super().writeFile(' '.join(hiphenSemiColonizedWordsList))
        
        def __del__(self):
            print('StringManipulation Object deleted')


if __name__ == '__main__':
    l = Logger()
    logging.warning('Working..')
    w = writeConfig()
    w.write()
    s = StringManipulation()
    del w
    del s
    logging.warning('End of program...')