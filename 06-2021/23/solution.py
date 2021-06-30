'''String Manipulation Program'''
import os
import sys
import logging
from collections import Counter
import re
import getopt


class CustomLogger:
    '''CustomLogger class'''
    def __init__(self):
        logging.basicConfig(
            filename='logger.txt',
            filemode='w+',
            format='%(asctime)s %(levelname)s - %(message)s',
            datefmt='%Y-%m-%d %H:%M:%S'
        )
    def __str__(self): #pragma: no cover
        return self.__class__.__name__ #pragma: no cover
    def __del__(self): #pragma: no cover
        return '' #pragma: no cover


class FileReadWrite:
    '''class to read and write file'''

    def __init__(self):
        self.__input_file = None
        self.__output_file = None
        self.__config = os.path.join(os.getcwd(), 'config')
        self.set_input_output_file()

    def get_config(self):
        '''returns config file'''
        return self.__config

    def set_input_output_file(self):
        '''read input output from config and store it to input_file and output_file'''
        try:
            with open(self.__config, 'r') as file:
                try:
                    lines = str(file.read()).split('\n')
                    assert len(lines) > 1
                    print('lines', str(lines))
                    self.__input_file = (lines[0].split('='))[1]
                    self.__output_file = (lines[1].split('='))[1]
                    print('input_file')
                    print(self.__input_file)
                    logging.warning('File read success..')
                except AssertionError: #pragma: no cover
                    print('Config file is empty') #pragma: no cover
                    logging.warning('File seems to be empty..') #pragma: no cover
        except FileNotFoundError: #pragma: no cover
            logging.warning('File read failed.. File might have been deleted') #pragma: no cover


    def write_file(self, contents):
        '''writes output to the output file'''
        with open(self.__output_file, 'w+') as file:
            file.write(contents)

    def get_input_file(self):
        '''returns a read input file'''
        print('input file')
        print(self.__input_file)
        try:
            with open(str(self.__input_file), 'r') as file:
                return str(file.read())
        except FileNotFoundError as err: #pragma: no cover
            print('Input File not found') #pragma: no cover
            return err #pragma: no cover

class WriteConfig(FileReadWrite):
    '''Class to write config file'''
    def __init__(self):
        super().__init__()
        self.config = super().get_config()

    def write(self):
        '''Writing Config file the command line arguments'''
        argv = sys.argv[1:]
        try:
            opts, args = getopt.getopt(argv, "i:o:")
            print(f'opt {opts} : args {args}')
        except getopt.GetoptError: #pragma: no cover
            print('error reading command line arguments') #pragma: no cover

        for opt, arg in opts:
            if opt in ['-i']:
                input_file = arg
            elif opt in ['-o']:
                output_file = arg
        print(
            f'\n\n Input file : {input_file} ; Output file : {output_file}\n\n')

        with open(self.config, 'w+') as file:
            file.write(f'INPUT_FILE={input_file}')
            file.write('\n')
            file.write(f'OUTPUT_FILE={output_file}')
            file.write('\n')
        super().set_input_output_file()


class StringManipulation(FileReadWrite):
    '''Class for manipulating string'''

    def __init__(self):
        super().__init__()
        self.input_file = super().get_input_file()
        self.find_prefix()
        self.find_suffix()
        self.count_max()
        self.palindrome_check()
        self.get_unique()
        self.word_dict()
        self.file_write()

    def find_prefix(self):
        '''Words starting with \'to\''''
        print('\n Words starting with \'to\'')
        count = 0
        for i in self.input_file.strip('.').split(' '):
            if i.lower().startswith('to') and i != 'to':
                count += 1
                # print(i)
        print(count)

    def find_suffix(self):
        '''Words ending with \'ing\''''
        print('\n Words ending with \'ing\'')
        count = 0
        for i in self.input_file.strip('.').split(' '):
            if i.lower().endswith('ing') and i != 'ing':
                # print(i)
                count += 1
        print(count)

    def count_max(self):
        '''Maximum occuring word'''
        counter = Counter(self.input_file.strip('.').split(' '))
        print(
            f'\nThe maximum occuring word is : {counter.most_common(1)[0][0]}')

    def palindrome_check(self):
        '''Palindrome words'''
        print('\n Palindrome words are :')
        temp = set()
        for i in self.input_file.strip('.').split(' '):
            if i == i[::-1]:
                temp.add(i)
        print(*temp)

    def get_unique(self):
        '''Unique set of words'''
        print('\n Unique set of words are :')
        counter = Counter(self.input_file.strip('.').split(' '))
        least_common = []
        for i in counter.most_common():
            if i[1] == 1:
                least_common.append(i[0])
        print(*least_common)

    def word_dict(self):
        '''Word dict with key as counter index and value as words in the file'''
        print('\nWord dict with key as counter index and value as words in the file : ')
        temp_map = dict()
        counter = Counter(self.input_file.strip('.').split(' '))
        for i in range(len(counter.most_common())):
            temp_map[i] = counter.most_common()[i][0]
        print(map)

    def file_write(self):
        '''Writin manipulated string into file'''
        vowel_map = []

        capitalized_words_list = []

        capitalized_fifth_words_list = []

        hiphen_semi_colonized_words_list = []

        def capitalize_third():
            print('\n Capitalizing third letter of every word :')
            for i in self.input_file.strip('.').split(' '):
                if len(i) > 2:
                    capitalized_words_list.append(
                        "".join([x.upper() if index == 2 else x for index, x in enumerate(i)]))
            print(capitalized_words_list)

        def vowel_split():
            print('\n Vowel split of words :')
            temp = self.input_file.strip('.')
            temp = re.split('a|e|i|o|u|\n', temp)
            vowel_map.extend(temp)
            print(vowel_map)

        def capitalize_fifth_word():
            print('\n Capitalizing Fifth Word')
            for index, value in enumerate(self.input_file.strip('.').split(' ')):
                if index % 5 == 0:
                    temp = value.upper()
                else:
                    temp = value
                capitalized_fifth_words_list.append(temp)
            print(' '.join(capitalized_fifth_words_list))

        def hiphen_semi_colon():
            print('\n Hiphen split of words')
            file = self.input_file.strip('.')
            file = file.replace(' ', '-')
            file = file.replace('\n', ';')
            hiphen_semi_colonized_words_list.append(file)
            print(' '.join(hiphen_semi_colonized_words_list))

        def write():
            pass

        vowel_split()
        capitalize_third()
        capitalize_fifth_word()
        hiphen_semi_colon()
        write()
        super().write_file('\nWord Split using vowels : \n')
        super().write_file(str(vowel_map))
        super().write_file('\n\nCapitalized third letter of every words : \n')
        super().write_file(str(capitalized_words_list))
        super().write_file('\n\nCapitalized fifth words : \n')
        super().write_file(' '.join(capitalized_fifth_words_list))
        super().write_file('\n\nHipen for spaces and semicolon for newline : \n')
        super().write_file(' '.join(hiphen_semi_colonized_words_list))


if __name__ == '__main__':
    log = CustomLogger()
    logging.warning('Working..')
    write_config_file = WriteConfig()
    write_config_file.write()
    s = StringManipulation()
    logging.warning('End of program...')
