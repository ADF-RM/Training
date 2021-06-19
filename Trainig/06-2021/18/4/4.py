class GCD(object):
    def __init__(self, list) -> None:
        self.list = list

    def find_gcd(self, x, y):
        while y:
            x, y = y, x % y
        return x

    def gcd(self):
        gcd = self.list[0]
        for i in range(1, len(self.list)):
            gcd = self.find_gcd(gcd, self.list[i])
        return gcd


l = [12, 2, 10, 14, 20]

gcd = GCD(l)
print(gcd.gcd())
