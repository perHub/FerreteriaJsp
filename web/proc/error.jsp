<%@page import="org.hibernate.exception.ConstraintViolationException"%>
<%@page import="javax.persistence.PersistenceException"%>
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
                String msg = null;
                if (exception != null && exception.getMessage() != null) {
                    if (exception.getMessage().equals("No.")) {
                        response.sendRedirect("No.html");
                    }
                    if (exception.getMessage() == null) {
                        msg = (String) session.getAttribute("error");
                    } else {
                        msg = exception.getMessage();
                    }

                    if (exception.getClass() == PersistenceException.class) {
                        if (exception.getCause().getClass() == ConstraintViolationException.class) {
                            msg = "El " + ((ConstraintViolationException) exception.getCause()).getConstraintName() + " ha sido registrado por otro usuario.";
                        }
                    }
                } else {
                    msg = "Error no especificado.";
                }
            %>
            <%=msg%>
        </h1>
    </body>
</html>
