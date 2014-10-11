/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Imprimen;

import Modelo.*;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "menu", urlPatterns = {"/menu"})
public class menu extends HttpServlet {

    String username;

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
        try {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession(true);
            Usuario usr = (Usuario) session.getAttribute("usuario");
            username = usr.getUsername();
            out.println("<script>\n"
                    + "function openPopUp(u) {\n"
                    + "    var myWindow = window.open(u, \"\", \"width=1100, height=500, scrollbars=1\");\n"
                    + "}\n"
                    + "</script>");

            out.println("<table border=1 bgcolor=#D3D3D3>\n"
                    + "<tr><td><a href='main'>Productos</a></td></tr>\n"
                    + showProperItem(usr)
                    + "<tr><td><a href='Logout'>Logout[" + username + "]</a></td></tr>"
                    + " </table>");
        } catch (NullPointerException ex) {
            Logger.getLogger(EditarUsuario.class.getName()).log(Level.SEVERE, null, ex);
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

    public String showProperItem(Usuario usr) {
        String str;
        if (usr.getClass() == Administrador.class) {
            str = "<tr><td><a href='AltaUsuario'>Agregar usuario</a></td></tr>\n"
                    + "<tr><td><a href='AdminUsuarios'>Administrar usuarios</a></td></tr>\n";
        } else {
            str = "<tr><td><a href='Compras'>Mis compras</a></td></tr>\n"
                    + "<tr><td><a href=\"javascript:openPopUp('Carrito');\">Carrito de compras</a></td></tr>\n";
        }

        return str;
    }
}
