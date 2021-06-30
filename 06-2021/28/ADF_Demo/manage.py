#!/usr/bin/env python
"""Django's command-line utility for administrative tasks."""
import os
import sys


def main():
    """Run administrative tasks."""
    os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'ADF_Demo.settings')
    try:
        from django.core.management import execute_from_command_line
    except ImportError as exc: #pragma: no cover
        raise ImportError( #pragma: no cover
            "Couldn't import Django. Are you sure it's installed and " #pragma: no cover
            "available on your PYTHONPATH environment variable? Did you " #pragma: no cover
            "forget to activate a virtual environment?" #pragma: no cover
        ) from exc #pragma: no cover
    execute_from_command_line(sys.argv) #pragma: no cover


if __name__ == '__main__':
    main()
