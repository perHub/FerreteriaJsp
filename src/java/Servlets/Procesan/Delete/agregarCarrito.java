/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Procesan;

import Exceptions.NoException;
import Exceptions.AdminClienteException;
import Modelo.CantidadInsuficienteException;
import Modelo.Cliente;
import Modelo.Compra;
import Modelo.Detalle;
import Modelo.Producto;
import Modelo.Usuario;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Peri
 */
@WebServlet(name = "agregarCarrito", urlPatterns = {"/agregarCarrito"})
public class agregarCarrito extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        try {

            if (request.getParameterMap().isEmpty()) {
                throw new NoException();
            }
            if (usuario.getClass() != Cliente.class) {
                throw new AdminClienteException(false);
            }

            List<Producto> productos = (List) session.getAttribute("productos");
            int prodId = Integer.parseInt(request.getParameter("prodId"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            Producto p = findProd(productos, prodId);

            Compra c = (Compra) session.getAttribute("carrito");

            if (c == null) {
                session.setAttribute("carrito", (c = new Compra(new Date(), new Detalle(findProd(productos, prodId), cantidad), (Cliente) usuario)));
                session.setAttribute("itemCount", c.getDetalles().size());
            } else {
                if(c.agregarDetalle(new Detalle(findProd(productos, prodId), cantidad)))
                    session.setAttribute("itemCount", c.getDetalles().size());
            }


            response.sendRedirect("main.jsp");
        } catch (NullPointerException ex) {
            if (usuario == null) {
                response.sendRedirect("No.html");
            }

        } catch (CantidadInsuficienteException ex) {
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect("error");
        } catch (AdminClienteException ex) {
            Logger.getLogger(agregarCarrito.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("AdminUsuarios");
        } catch (NoException ex) {
            Logger.getLogger(agregarCarrito.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("No.html");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public Producto findProd(List<Producto> lstprod, int id) {
        Producto p = null;
        for (Producto prod : lstprod) {
            if (prod.getId() == id) {
                p = prod;
            }
        }
        return p;

    }
    
    public void contarItems(HttpSession session){
        
            Integer itemCount;

            if ((itemCount = (Integer)session.getAttribute("itemCount")) == null) {
                session.setAttribute("itemCount", 1);
            }
            else{
                
                session.setAttribute("itemCount", itemCount + 1);
            }
    }

}
