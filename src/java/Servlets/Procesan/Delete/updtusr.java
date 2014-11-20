/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Procesan;

import Exceptions.NoException;
import Exceptions.AdminClienteException;
import Controladora.CUsuario;
import Modelo.*;
import java.io.IOException;
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
@WebServlet(name = "updtusr", urlPatterns = {"/updtusr"})
public class updtusr extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");
        try {
            if (request.getParameterMap().isEmpty()) {
                throw new NoException();
            }

            if (usuario.getClass() != Administrador.class) {
                throw new AdminClienteException(true);
            }

            CUsuario cu = new CUsuario();
            int id = Integer.parseInt(request.getParameter("usrId"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String sDni = request.getParameter("dni");
            String estado = request.getParameter("estado");
            Boolean activo = false;
            if(estado.equals("Activo")){
                activo = true;
            }

            Usuario usr = cu.getById(id);

            usr.setUsername(username);
            usr.setPassword(password);
            usr.setNombre(nombre);
            usr.setApellido(apellido);

            if (estado.equals("Desactivado")) {
                usr.setActivo(activo);
            }
            
            if(usr.getClass() == Cliente.class){
                int dni = Integer.parseInt(sDni);
                ((Cliente)usr).setDni(dni);
            }

            cu.actualizar(usr);

            response.sendRedirect("AdminUsuarios");

        } catch (NullPointerException e) {
            if (usuario == null) {
                session.setAttribute("error", "No se encuentra logueado.");
                response.sendRedirect("error");
            }
        } catch (NoException ex) {
            response.sendRedirect("No.html");
        } catch (AdminClienteException ex) {
            Logger.getLogger(updtusr.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", "No tiene permisos para realizar esta operación.");
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
