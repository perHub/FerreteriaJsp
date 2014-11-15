<%-- 
    Document   : info_producto
    Created on : Oct 12, 2014, 8:19:35 PM
    Author     : Dev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="proc/error.jsp" %>
<%@taglib prefix="lg" uri="/WEB-INF/tlds/logincheck.tld"%>
<lg:logchk admin="true"/>
<jsp:useBean id="producto" class="Modelo.Producto" scope="request"/>
<jsp:setProperty name="producto" property="*"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edite o de de alta un producto</title>
        <%@ include file="menu.jsp"%> 
        <style>
            .span3 {
                width: 170px;
            }
            .span4 {
                width: 50px;
            }

        </style>
        <script>
            function check() {
                return document.getElementById("myCheck").checked;
            }
        </script>
    </head>
    <body>
        <div class="span3 center-block">
            <h2>Agregar/Modificar producto</h2>
            <form action="proc/updprod.jsp" method="post">
                <label>Nombre</label>
                <input type="text" name="nombre" value="<c:if test="${producto.nombre != null}">${producto.nombre}</c:if>" class="span3" required>
                    <label>Precio</label>
                    <input type="number" name="precio" value="<c:if test="${producto.precio != 0}">${producto.precio}</c:if>" min="0.05" step="0.05" class="span3" required>
                    <label>Stock</label>
                    <input type="number" name="stock" value="<c:if test="${producto.stock != 0}">${producto.stock}</c:if>" min="1" class="span3" required>
                    <br>
                    <label><input type="checkbox" name="activo" <c:if test="${producto.id == 0 || (producto.activo && producto.id != 0)}">checked</c:if>> Disponible</label>
                <input type="hidden" name="id" value="${producto.id}"/>
                <input type='hidden' name='nuevo' value='${producto.id == 0 ? "true" : "false"}'/>
                <br>
                <input type="submit" value="Actualizar" class="btn btn-primary pull-right">
            </form>
        </div>
    </body>
</html>
