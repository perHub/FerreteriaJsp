<%-- 
    Document   : menu_cliente
    Created on : 10/10/2014, 19:49:41
    Author     : Peri
--%>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="main.jsp">Ferretería</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Compras <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="compras.jsp">Mis compras</a></li>
                    </ul>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="carrito.jsp">Ver carrito</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b>${usuario.getUsername()} </b> <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="Logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>