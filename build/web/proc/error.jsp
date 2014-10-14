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
