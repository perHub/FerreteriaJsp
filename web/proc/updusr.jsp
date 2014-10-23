<%-- 
    Document   : updusr
    Created on : 14/10/2014, 21:30:10
    Author     : Peri
--%>

<%@page import="Controladora.CUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="lg" uri="/WEB-INF/tlds/logincheck.tld"%>
<lg:logchk admin="true"/>
<jsp:useBean id="usr" class="Modelo.Cliente" scope="request"/>
<jsp:setProperty name="usr" property="*"/>
<%@ page errorPage="proc/error.jsp" %>
<jsp:useBean id="nuevo" class="oracle.jsp.jml.JmlBoolean" scope="request"/>
<jsp:setProperty name="nuevo" property="value"/>

<%
    CUsuario cUsr = new CUsuario();
    if (nuevo.getValue()){
        cUsr.agregar(usr);
    }
    else{
        cUsr.actualizar(usr);
    }
    
    response.sendRedirect("../admin_usuarios.jsp");
%>
