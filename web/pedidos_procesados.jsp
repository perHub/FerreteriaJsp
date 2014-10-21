<%-- 
    Document   : pedidos_procesados
    Created on : 16/10/2014, 20:42:22
    Author     : Peri
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="Controladora.CCompra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="lg" uri="/WEB-INF/tlds/logincheck.tld"%>
<lg:logchk admin="true"/>
<%@ page errorPage="proc/error.jsp" %>
<!DOCTYPE html>
<%
    CCompra cComp = new CCompra();

    List<Compra> procesados = cComp.getProcesados();

    if (procesados.isEmpty()) {
        throw new JspException("No hay compras procesadas");
    }

    pageContext.setAttribute("procesados", procesados, PageContext.REQUEST_SCOPE);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compras procesadas</title>
        <%@ include file="menu.jsp"%> 
        <style>
            .button {
                background:none!important;
                border:none; 
                padding:0!important;
                /*border is optional*/
                border-bottom:1px solid #444; 
                color: blue;
                border-bottom-color: blue;
            }
        </style>
    </head>
    <body>

        <div class="panel panel-default center-block" style="width: 700px;">
            <!-- Default panel contents -->
            <div class="panel-heading">Compras procesadas</div>

            <!-- Table -->
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Fecha</th>
                        <th>Número de orden</th>
                        <th>Total</th>
                        <th>Usuario</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${procesados}" var="p">
                    <form method="post" action="orden_piqueo.jsp">
                        <input name="pedido" type="hidden" value="${p.pedido.getId()}"/>
                        <tr>
                            <td>${p.fecha}</td>
                            <td><button type="submit" class="button">00${p.pedido.getId()}</button></td>
                            <td>$${p.total}</td>
                            <td>${p.cliente.getNombre()}</td>
                        </tr>
                    </form>
                </c:forEach>                    
                </tbody>
            </table>
        </div>
    </body>
</html>