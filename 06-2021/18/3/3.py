from threading import Timer
from time import sleep


class Repeat:
    def __init__(self, i):
        self.i = i
        self.timer = None
        self.dp = self.prime()
        self.start()

    def start(self):
        self.timer = Timer(1.0, self.start)
        self.timer.start()
        print(self.dp[self.i])
        self.i += 1

    def prime(self):
        l = [1]
        for num in range(1, 100):
            if num > 1:
                for i in range(2, num):
                    if (num % i) == 0:
                        break
                else:
                    l.append(num)
        return l

    def stop(self):
        self.timer.cancel()


r = Repeat(0)
try:
    sleep(10)
finally:
    r.stop()
