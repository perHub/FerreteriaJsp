<%-- 
    Document   : compras
    Created on : Oct 11, 2014, 10:52:25 PM
    Author     : Dev
--%>

<%@page import="java.util.List"%>
<%@page import="Controladora.CCompra"%>
<%@page import="Modelo.*"%>
<%@page import="Exceptions.loginException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="proc/error.jsp" %>
<%@taglib prefix="lg" uri="/WEB-INF/tlds/logincheck.tld"%>
<lg:logchk admin="false"/>
<jsp:useBean id="usuario" class="Modelo.Usuario" scope="session"/>
<jsp:useBean id="usrCompras" class="Modelo.Cliente" scope="page"/>
<jsp:setProperty name="usrCompras" property="id"/>

<%
    CCompra cc = new CCompra();
    List<Compra> compras;
    
    
    if (usrCompras.getId() == 0) {
        int compUsrid;
        compUsrid = Integer.parseInt(session.getAttribute("compUsrId").toString());
        compras = cc.getAllByUserId(compUsrid);
    } else {
        compras = cc.getAllByUserId(usrCompras.getId());
    }
    if (compras.isEmpty()) {
        throw new ServletException("No hay compras que mostrar!");
    }

    pageContext.setAttribute("compras", compras, PageContext.PAGE_SCOPE);

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compras</title>
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
        <%@ include file="menu.jsp"%> 
    </head>
    <body>
        <div style="width: 321px; margin-left: auto; margin-right: auto;">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Fecha</th>

                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${compras}" var="c">
                    <form id="comp" action="detalles_de_compra.jsp" method="post">
                        <tr>
                            <td>${c.fecha}</td>
                            <td><button type="submit" class="button">$${c.total}</button></td>
                        </tr>
                        <input name="id" type="hidden" value="${c.id}" />
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
