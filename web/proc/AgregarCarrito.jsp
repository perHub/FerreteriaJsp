<%-- 
    Document   : AgregarCarrito
    Created on : 02/10/2014, 20:00:02
    Author     : Peri
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="Modelo.*"%>
<%@page import="Exceptions.*"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="usuario" class="Modelo.Usuario" scope="session"/>

<%
    {

        if (request.getParameterMap().isEmpty()) {
            throw new NoException("Imposible realizar la operación.");
        }
        if (usuario.getClass() != Cliente.class) {
            throw new AdminClienteException(false);
        }

        HashMap<Integer, Producto> productos = (HashMap<Integer, Producto>) session.getAttribute("productos");
        int prodId = Integer.parseInt(request.getParameter("prodId"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        Producto p = productos.get(prodId);

        Compra c = (Compra) session.getAttribute("carrito");

        if (c == null) {
            session.setAttribute("carrito", new Compra(new Date(), new Detalle(p, cantidad)));
        } else {
            c.agregarDetalle(new Detalle(p, cantidad));
        }

        response.sendRedirect("../main");
    }
    

%>

