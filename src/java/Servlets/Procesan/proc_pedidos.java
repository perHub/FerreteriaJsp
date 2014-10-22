/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Procesan;

import Controladora.CCompra;
import Controladora.CPedido;
import Exceptions.AdminClienteException;
import Exceptions.NoException;
import Modelo.Administrador;
import Modelo.CantidadInsuficienteException;
import Modelo.Compra;
import Modelo.Pedido;
import Modelo.Producto;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
 * @author Dev
 */
@WebServlet(name = "proc_pedidos", urlPatterns = {"/proc_pedidos"})
public class proc_pedidos extends HttpServlet {

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

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        try {
            Map<String, String[]> params;

            if ((params = request.getParameterMap()).isEmpty()) {
                throw new NoException();
            }

            if (usuario.getClass() != Administrador.class) {
                throw new AdminClienteException(true);
            }

            CCompra cComp = new CCompra();
            CPedido cPedido = new CPedido();
            
            Map<Integer, Compra> compras = cComp.getAllMap();

            Pedido p = new Pedido(new Date());

            for (Map.Entry<String, String[]> mapStr : params.entrySet()) { //Proceso los pedidos
                Compra c = compras.get(Integer.parseInt(mapStr.getKey()));
                c.setProcesado(Boolean.TRUE);
                c.setPedido(p); 
                p.getCompras().put(c.getId(), c);
            }
            
            Map<Producto, Integer> prodCant = p.obtenerProductos();
            
            for (Map.Entry<Producto, Integer> prod : prodCant.entrySet()) {
                prod.getKey().reduceStock(prod.getValue());
            }
            cPedido.agregar(p); //Persisto
            
            response.sendRedirect("pedidos_procesados.jsp");

        } catch (NullPointerException e) {
            if (usuario == null) {
                session.setAttribute("error", "No se encuentra logueado.");
                response.sendRedirect("error");
            }
        } catch (NoException ex) {
            session.setAttribute("error", "Debe seleccionar una compra.");
            response.sendRedirect("error");
        } catch (AdminClienteException ex) {
            Logger.getLogger(updtusr.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", "No tiene permisos para realizar esta operación.");
            response.sendRedirect("error");
        } catch (CantidadInsuficienteException ex) {
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect("error");
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

}
