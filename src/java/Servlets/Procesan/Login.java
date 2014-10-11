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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
       PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        CUsuario cUsr = new CUsuario();
        @SuppressWarnings("UnusedAssignment")
        Usuario usr = null;
        
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
        
        if((usr = cUsr.login(username, password)) != null)
        {
            if(!usr.isActivo()){
                throw new NoException();
            }
            session.setAttribute("usuario", usr);
            
            if(usr.getClass() == Administrador.class)
                response.sendRedirect("admin_usuarios.jsp");
            else{
                session.setAttribute("compUsrId", usr.getId());
                response.sendRedirect("main.jsp");
            }
        }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login no v{alido</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Credenciales inválidas.</h1>");
            

            out.println("</body>");
            out.println("</html>");
        } catch (NoException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", "Usuario desactivado.");
            response.sendRedirect("error");
        } finally {
            out.close();
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
