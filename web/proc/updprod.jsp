<%-- 
    Document   : updprod
    Created on : Oct 14, 2014, 12:20:57 AM
    Author     : Dev
--%>

<%@page import="Controladora.CProducto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="lg" uri="/WEB-INF/tlds/logincheck.tld"%>
<lg:logchk admin="true"/>
<jsp:useBean id="producto" class="Modelo.Producto" scope="request"/>
<jsp:setProperty name="producto" property="*"/>
<jsp:useBean id="nuevo" class="oracle.jsp.jml.JmlBoolean" scope="request"/>
<jsp:setProperty name="nuevo" property="value"/>
<%@ page errorPage="proc/error.jsp" %>

<%
    CProducto cProd = new CProducto();
    if (nuevo.getValue()){
        cProd.create(producto);
    }
    else{
        cProd.update(producto);
    }
    
    response.sendRedirect("../admin_productos.jsp");
%>
