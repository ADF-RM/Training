<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="">
    </head>

    <body>

        <!-- WELCOME ${params.name} { rollNo : ${params.rollNo}, age : ${params.age} } -->
        <form action="details">
            ID : <input type="number" name="rollNo"><br><br>
            NAME : <input type="text" name="name"><br><br>
            AGE : <input type="number" name="age"><br><br><br>
            <input type="submit"><br>
        </form>
    </body>

    </html>