### 2. Program to read a CSV (CSV with n number of columns) and store it in DICT of list.


![2 png](https://github.com/ADF-RM/Training/blob/main/06-2021/18/2/2.png)


#### Solution:

i)   Create a simple [csv](https://github.com/ADF-RM/Training/blob/main/06-2021/18/2/address.csv) file containing n number of columns and the filename is given as input.

ii)  Open the text file using python's ```open()``` function.

iii) Read the csv file using python's ```csv.reader``` function from ```csv``` module.

iv) Create an empty dictionary with ```keys``` as column names and ```values``` as empty list.

v)  Use ```try``` ```except``` block to catch errors like if the file is not found.

vi) Iterate through each and every column in the csv file and store the corresponding values in the map key named same as that of column.

vii) Print the dictionary.
