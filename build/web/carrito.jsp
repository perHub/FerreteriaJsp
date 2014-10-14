<%-- 
    Document   : carrito
    Created on : 10/10/2014, 18:44:43
    Author     : Peri
--%>

<%@page import="java.util.Set"%>
<%@page import="Exceptions.*"%>
<%@page import="Modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="lg" uri="/WEB-INF/tlds/logincheck.tld"%>
<lg:logchk/>
<jsp:useBean id="usuario" class="Modelo.Usuario" scope="session"/>
<jsp:useBean id="carrito" class="Modelo.Compra" scope="session"/>

<%
    if (usuario.getClass() == Administrador.class) {
        throw new AdminClienteException(false);
    } else if (usuario.getClass() != Cliente.class) {
        throw new loginException();
    }

    if (carrito.getDetalles().isEmpty()) {
        throw new NoException("Carrito vacío!");
    }

    Set<Detalle> detalles = carrito.getDetalles();//Preguntar
    pageContext.setAttribute("detalles", detalles, PageContext.PAGE_SCOPE);
%>
<html>
    <head>
        <title>Carrito</title>
        <%@ include file="menu.jsp"%> 
    </head>
    <body>
        <c:forEach items="${detalles}" var="d">
            <form action="eliminarCarrito" method="post">
                <div id="carrito" align="center">
                    <table class="table-bordered"
                           <tr>
                            <td style="width: 319px">Prducto:</td>
                            <td>${d.getProducto().getNombre()}</td>
                        </tr>
                        <tr>
                            <td style="width: 319px">Cantidad:</td>
                            <td>${d.getCantidad()}</td>
                        </tr>
                        <tr>
                            <td style="width: 319px">Precio unitario:</td>
                            <td>$ ${d.getProducto().getPrecio()}</td>
                        </tr>
                        <tr>
                            <td style="width: 319px">Precio total:</td>
                            <td>$ ${d.getPrecio()}</td>
                        </tr>
                    </table>
                    <input type="hidden" name="detId" value="${d.getId()}" />
                    <div class="botonR" style="width: 148px">
                        <input name="Button1" type="submit" value="Quitar del carrito" />
                    </div>
                </div>
            </form>
            <br>
        </c:forEach>

        <div align="center">
            <br>
            <form name="confirmar" method="post" action="confirmarCompra">
                <div class="botonConf">
                    Total de compras: ${carrito.total}
                    <input name="Button2" type="submit" value="Confirmar compras" />
                </div>
            </form>
        </div>
    </body>
</html>
