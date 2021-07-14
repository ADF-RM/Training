<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <title>Display Page</title>
            <meta name="description" content="">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="">
        </head>

        <body>
            <center>
                <h2>DETAILS</h2><br><br>
                <c:forEach items="${params}" var="params">
                    Name : ${params.name}<br><br>
                    RollNo : ${params.rollNo}<br><br>
                    Age : ${params.age}<br><br><br>
                </c:forEach>
            </center>
        </body>

        </html>