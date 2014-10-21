<%-- 
    Document   : pedidos_procesados
    Created on : 16/10/2014, 20:42:22
    Author     : Peri
--%>

<%@page import="Controladora.CPedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="lg" uri="/WEB-INF/tlds/logincheck.tld"%>
<lg:logchk admin="true"/>
<%@ page errorPage="proc/error.jsp" %>
<!DOCTYPE html>
<%
    if (request.getParameter("pedido") == null) {
        throw new NoException("No.");
    }
    int pedido = Integer.parseInt(request.getParameter("pedido"));
    CPedido cPedido = new CPedido();
    Pedido p = cPedido.read(pedido);

    pageContext.setAttribute("idPedido", pedido, PageContext.REQUEST_SCOPE);
    pageContext.setAttribute("prodsPick", p.obtenerProductos(), PageContext.REQUEST_SCOPE);
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
        <div class="container">
            <!-- Simple Invoice - START -->
            <div class="container">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="text-center">
                            <i class="fa fa-search-plus pull-left icon"></i>
                            <h2>Órden número #000${idPedido}</h2>
                        </div>
                        <hr>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="text-center"><strong>Órden de piqueo</strong></h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <table class="table table-condensed">
                                                <thead>
                                                    <tr>
                                                        <td><strong>Producto</strong></td>
                                                        <td class="text-center">
                                                            <strong>Cantidad</strong></td>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${prodsPick.entrySet()}" var="p">
                                                        <tr>
                                                            <td>${p.key}</td>
                                                            <td class="text-center">${p.getValue()}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="margin: auto; position:relative; top:25%; width: 10%;"><button onclick="window.print();" class="btn btn-primary btn-lg" role="button">Imprimir</button></div>

                    <style>
                        .height {
                            min-height: 200px;
                        }

                        .icon {
                            font-size: 47px;
                            color: #5CB85C;
                        }

                        .iconbig {
                            font-size: 77px;
                            color: #5CB85C;
                        }

                        .table > tbody > tr > .emptyrow {
                            border-top: none;
                        }

                        .table > thead > tr > .emptyrow {
                            border-bottom: none;
                        }

                        .table > tbody > tr > .highrow {
                            border-top: 3px solid;
                        }
                    </style> <!-- Simple Invoice - END -->
                </div>
                </body>
                </html>