<%-- 
    Document   : menu_admin
    Created on : 10/10/2014, 19:15:29
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
            <a class="navbar-brand" href="admin_usuarios.jsp">Ferretería</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Administrar usuarios <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="admin_usuarios.jsp">Ver usuarios</a></li>
                        <li><a href="alta_usuario.jsp">Alta de usuario</a></li>
                    </ul>
                </li>
                
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Administrar productos <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="admin_productos.jsp">Ver productos</a></li>
                        <li><a href="info_producto.jsp">Nuevo producto</a></li>
                    </ul>
                </li>
                
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Administrar pedidos<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="pedidos_pendientes.jsp">Ver compras pendientes</a></li>
                        <li><a href="pedidos_procesados.jsp">Ver pedidos procesados</a></li>
                    </ul>
                </li>
                
            </ul>
            <ul class="nav navbar-nav navbar-right">
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