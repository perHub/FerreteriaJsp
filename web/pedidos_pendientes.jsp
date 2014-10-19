<%-- 
    Document   : admin_pedidos
    Created on : 16/10/2014, 20:14:10
    Author     : Peri
--%>

<%@page import="java.util.List"%>
<%@page import="Controladora.CCompra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<lg:logchk admin="true"/>
<%@ page errorPage="proc/error.jsp" %>
<!DOCTYPE html>
<%
    CCompra cComp = new CCompra();

    List<Compra> pendientes = cComp.getPendientes();

    List<Compra> pend = cComp.getAll();

    pageContext.setAttribute("pendientes", pendientes, PageContext.PAGE_SCOPE);
    pageContext.setAttribute("pend", pend, PageContext.PAGE_SCOPE);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compras pendientes</title>
        <%@ include file="menu.jsp"%> 
    </head>
    <body>

        <div class="panel panel-default center-block" style="width: 700px;">
            <!-- Default panel contents -->
            <div class="panel-heading">Pedidos pendientes</div>

            <!-- Table -->
            <form method="post">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Procesar</th>
                            <th>Fecha</th>
                            <th>Total</th>
                            <th>Usuario</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${pendientes}" var="p">
                            <tr>
                                <td>
                                    <input type="checkbox" name="procesar">
                                </td>
                                <td>${p.fecha}</td>
                                <td>$${p.total}</td>
                                <td>${p.cliente.getNombre()}</td>
                            </tr>
                        </c:forEach>                    
                    </tbody>
                </table>
                <div style="margin: auto; position:relative; top:25%; width: 20%;"><button class="btn btn-primary btn-lg" role="button">Procesar pedidos</button></div>
            </form>
        </div>

    </body>
</html>
