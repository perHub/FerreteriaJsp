<%-- 
    Document   : admin_productos
    Created on : 10/10/2014, 19:11:24
    Author     : Peri
--%>

<%@page import="java.util.List"%>
<%@page import="Controladora.CProducto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<lg:logchk admin="true"/>
<%
    List<Producto> lstProd;
    CProducto cProd = new CProducto();
    lstProd = cProd.getAll();

    pageContext.setAttribute("lstProd", lstProd, PageContext.PAGE_SCOPE);

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos cargados</title>
        <%@ include file="menu.jsp"%> 
    </head>
    <body>
        <h1 align="center">Productos</h1>

        <c:forEach items="${lstProd}" var="prod">
            <form method="post" action="agregarCarrito">
                <div id="lProd" class="panel panel-default" style="width: 400px; margin-left: auto; margin-right: auto;">
                    <table class="table table-bordered">
                        <tr>
                            <td style="width: 319px">Prducto:</td>
                            <td><c:out value="${prod.nombre}"/></td>
                        </tr>
                        <tr>
                            <td style="width: 319px">Precio:</td>
                            <td>$<c:out value="${prod.precio}"/></td>
                        </tr>
                        <tr>
                            <td style="width: 319px">Stock:</td>
                            <td><c:out value="${prod.stock}"/></td>
                        </tr>
                    </table>
                    <input type="hidden" name="id" value="<c:out value="${prod.id}"/>"/>
                    <div style="margin: auto; position:relative; width: 10%;">
                        <input name="Button1" type="submit" value="Editar" />&nbsp;
                    </div>
                </div>
            </form>
        </c:forEach>
    </body>
</html>
