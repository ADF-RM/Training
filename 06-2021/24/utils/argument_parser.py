"""
Argument parser class with argparse in python
"""
import argparse

class Parser:
    def __init__(self,program:str,description:str):
        self.parser = argparse.ArgumentParser(
            prog=program,
            description = description,
            )
    def add_positional_arguments(self,*iterables):
        """
        [add arguments to parser]
        """
        for iterable in iterables:
            self.parser.add_argument(
                iterable[0],
                metavar=iterable[1],
                help=iterable[2],
                default=iterable[3]
            )
    def add_optional_arguments(self,*iterables):
        """
        [add optional arguments to parser]
        """
        for iterable in iterables:
            self.parser.add_argument(
                iterable[0],
                metavar=iterable[1],
                help=iterable[2],
                dest=iterable[3],
                default=iterable[4],
            )
    def get_args(self):
        """
        Returns:
            [args]: [all the args passed]
        """
        return self.parser.parse_args()