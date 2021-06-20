### 1. Program to read a file and store the unique words in a list sorted based on the length of word in a new file along with each word length appended to it.


![1 png](https://user-images.githubusercontent.com/86099882/122677123-93699280-d1fe-11eb-90be-a589344270e1.png)


Solution:

i)   Create a simple [text](https://github.com/ADF-RM/Training/blob/main/06-2021/18/1/sample_text.txt) file containing repeated words and the filename is given as input in the command line.

ii)  Open the text file using python's ```open()``` function.

iii) Use ```try``` ```except``` block to catch errors like if the file is not found.

iv)  Find all the unique words in the text file and sort them based on their length of character. Use ```lambda``` function to find the length of a word simultaneously while sorting.

v)   Write the sorted unique words along with its length into a new [text](https://github.com/ADF-RM/Training/blob/main/06-2021/18/1/final_text.txt) file.
