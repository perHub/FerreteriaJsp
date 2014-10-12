/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Procesan;

import Exceptions.NoException;
import Controladora.CUsuario;
import Modelo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
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
@WebServlet(name = "confirmarCompra", urlPatterns = {"/confirmarCompra"})
public class confirmarCompra extends HttpServlet {

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
        Cliente cl = (Cliente) session.getAttribute("usuario");
        Compra c = (Compra) session.getAttribute("carrito");

        try {
            if (request.getParameterMap().isEmpty()) {
                throw new NoException();
            }

            PrintWriter out = response.getWriter();
            CUsuario cUsr = new CUsuario();
            Set<Detalle> detalles = c.getDetalles();

            if (detalles.isEmpty()) {
                session.setAttribute("error", "No es posible realizar la operación.");
                response.sendRedirect("error");
            }

            for (Detalle d : detalles) { //Limpio los IDs para poder persistirlos.
                d.setId(0);
            }
            cl.agregarCompra(c);

            cUsr.actualizar(cl);
            
            session.setAttribute("carrito", null);
            
            response.sendRedirect("main.jsp");

        } catch (NullPointerException ex) {
            if (cl == null) {
                response.sendRedirect("No.html");
            }
            if (c == null) {
                session.setAttribute("error", "No hay compras.");
                response.sendRedirect("error");
            }

        } catch (ClassCastException ex) {
            response.sendRedirect("AdminUsuarios");
        } catch (NoException ex) {
            Logger.getLogger(confirmarCompra.class.getName()).log(Level.SEVERE, null, ex);
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

}
