import mysql.connector

class My_SQL:
    def __init__(self):
        self.database = ''
        self.fields = ''
        self.my_db = mysql.connector.connect(
            host='localhost', user='root', password='ADFRM551$', autocommit=True)
    
    def get_cursor(self):
        return self.my_db.cursor()

    def connect_db(self, database):
        self.find_db(database.lower())
        self.my_db = mysql.connector.connect(
            host='localhost', user='root', password='ADFRM551$', autocommit=True, database=database)
        self.database = database

    def find_db(self, database):
        dbs_list = self.show_db()
        db_status = False
        for dbs in dbs_list:
            if str(dbs) == database:
                db_status = True
                break
        if not db_status:
            self.create_db(database)

    def create_db(self, database_name):
        cursor = self.my_db.cursor()
        cursor.execute(f'CREATE DATABASE {database_name}')

    def show_db(self):
        cursor = self.my_db.cursor()
        cursor.execute('show databases')
        dbs_list = []
        for dbs in cursor:
            dbs_list.append(dbs[0])
        return dbs_list

    def delete_db(self, database_name):
        cursor = self.my_db.cursor()
        cursor.execute(f'DROP DATABASE {database_name}')

    def create_table(self, table_name, db_params):
        cursor = self.my_db.cursor()
        print(f'db params : {db_params}')
        cursor.execute(
            f'CREATE TABLE IF NOT EXISTS {table_name}({db_params});'
        )

    def display_table(self, table_name):
        cursor = self.my_db.cursor()
        cursor.execute(f'SELECT * FROM {table_name}')
        records = cursor.fetchall()
        print(f'{table_name} records')
        return records

    def insert_into_table(self, table_name, table_fields, table_values):
        cursor = self.my_db.cursor()
        cursor.execute(
            f"""INSERT INTO {table_name} ({table_fields}) VALUES ({table_values});"""
        )
        return cursor

    def get_recent_transactions(self, table_name, field, limit, required_field):
        cursor = self.my_db.cursor()
        try:
            cursor.execute(
                f"""SELECT {required_field} FROM {table_name} ORDER BY {field} DESC LIMIT {limit}"""
            )
            return cursor.fetchall()
        except:
            return ((0,),)

    def get_row_count(self, table_name):
        cursor = self.my_db.cursor()
        cursor.execute(
            f"""SELECT COUNT(*) FROM {table_name}"""
        )
        return cursor.fetchone()

    def drop_table(self, table_name):
        cursor = self.my_db.cursor()
        cursor.execute(f'DROP TABLE {table_name};')

    def disconnect(self):
        cursor = self.my_db.cursor()
        cursor.close()


# database_name = 'adf_user'
# table_name = 'UserDetails'
# db_params = "ID int PRIMARY KEY AUTO_INCREMENT,FIRST_NAME CHAR(20) NOT NULL,MIDDLE_NAME CHAR(20),LAST_NAME CHAR(20),DOB DATE,GENDER CHAR(1),NATIONALITY CHAR(20),CURRENT_CITY CHAR(30),STATE CHAR(20),PIN_CODE TEXT,QUALIFICATION TEXT,SALARY TEXT,PAN_NO TEXT"

# table_values = "1,'NAVEEN','R','M','2001-05-05','M','INDIAN','COIMBATORE','TAMILNADU','641026','B.Tech','6','123'"
# table_values1 = "2,'NAVEEN','R','M','2001-05-05','M','INDIAN','COIMBATORE','TAMILNADU','641026','B.Tech','6','123'"

# my_sql = My_SQL()
# my_sql.connect_db(database_name)
# print(my_sql.show_db())
# my_sql.create_table(table_name, db_params)
# my_sql.insert_into_table(table_name, table_values)
# my_sql.insert_into_table(table_name, table_values1)
# print(my_sql.display_table(table_name))
# my_sql.disconnect()
