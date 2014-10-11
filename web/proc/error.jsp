<%-- 
    Document   : error
    Created on : 02/10/2014, 20:55:26
    Author     : Peri
--%>
<%@ page isErrorPage="true" contentType="text/html" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1> <%=exception.getMessage() %></h1>
    </body>
</html>
