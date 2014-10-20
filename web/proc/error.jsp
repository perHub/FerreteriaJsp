<%@page import="Exceptions.NoException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" contentType="text/html" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>
            <% 
                if(exception.getMessage().equals("No.")){
                    response.sendRedirect("No.html");
                }
                String msg;
                if (exception.getMessage() == null) {
                   msg = (String)session.getAttribute("error");
                }
                else{
                    msg = exception.getMessage();
                }
            %>
            <%=msg%>
        </h1>
    </body>
</html>
