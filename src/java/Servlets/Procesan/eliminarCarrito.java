/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Procesan;

import Exceptions.NoException;
import Modelo.Compra;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;
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
@WebServlet(name = "eliminarCarrito", urlPatterns = {"/eliminarCarrito"})
public class eliminarCarrito extends HttpServlet {

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
        try {

            if (request.getParameterMap().isEmpty()) {
                throw new NoException();
            }

            Compra c = (Compra) session.getAttribute("carrito");
            int detId = Integer.parseInt(request.getParameter("detId"));

            c.eliminarDetalle(detId);

//            Integer itemCount;
//            itemCount = (Integer) session.getAttribute("itemCount");
            session.setAttribute("itemCount", c.getDetalles().size());

            if (c.getDetalles().isEmpty()) {
                session.setAttribute("carrito", null);
                response.sendRedirect("main.jsp");
            } else {
                response.sendRedirect("carrito.jsp");
            }
        } catch (NullPointerException | NotFoundException ex) {
            Logger.getLogger(eliminarCarrito.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoException ex) {
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
