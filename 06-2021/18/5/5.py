class Convert(object):
    def __init__(self, decimal):
        self.decimal = decimal

    def binaryConv(self):
        return f"Binary Conversion : \n{self.decimal} - "+bin(self.decimal)[2:]

    def octalConv(self):
        return f"Octal conversion : \n{self.decimal} - "+oct(self.decimal)[2:]

    def hexDecimalCon(self):
        return f"HexaDecimal conversion : \n{self.decimal} - "+hex(self.decimal)[2:]


s = Convert(decimal=int(input('Enter a number : \n')))
print(s.binaryConv())
print(s.octalConv())
print(s.hexDecimalCon())
