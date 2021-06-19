class Ascii:
    def __init__(self, char: str) -> None:
        self.char = char

    def find(self) -> int:
        return ord(self.char)


s = Ascii(input('Enter a character:'))
print(s.find())
