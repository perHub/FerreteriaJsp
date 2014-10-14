<%-- 
    Document   : Login
    Created on : 30/09/2014, 20:52:07
    Author     : Peri
--%>

<%@page import="Modelo.Usuario"%>
<%@page import="Exceptions.NoException"%>
<%@page import="java.util.logging.Level"%>
<%@page import="Modelo.Administrador"%>
<%@page import="Controladora.CUsuario"%>
<%@ page errorPage="proc/error.jsp" %>
<jsp:useBean id="usuario" class="Modelo.Usuario" scope="session"/>
<jsp:setProperty name="usuario" property="username" />
<jsp:setProperty name="usuario" property="password"/>

<%! CUsuario cUsr;
    Usuario usr;
%>

<%

    if (request.getParameterMap().isEmpty()) {
        throw new NoException("No.");
    }

    cUsr = new CUsuario();
    if ((usuario = (cUsr.login(usuario.getUsername(), usuario.getPassword()))) != null) {

        session.setAttribute("usuario", usuario);

        if (!usuario.isActivo()) {
            throw new NoException("Usuario inactivo.");
        }

        if (usuario.getClass() == Administrador.class) {
            response.sendRedirect("../AdminUsuarios");
        } else {
            session.setAttribute("compUsrId", usuario.getId());
            response.sendRedirect("../main");
        }

    }


%>
