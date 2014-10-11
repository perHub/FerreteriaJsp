/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Imprimen;

import Controladora.CUsuario;
import Modelo.*;
import Modelo.Cliente;
import Exceptions.AdminClienteException;
import Exceptions.NoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "AdminUsuarios", urlPatterns = {"/AdminUsuarios"})
public class AdminUsuarios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    CUsuario cUsr = new CUsuario();
    List<Usuario> lstUsr;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        try {
            PrintWriter out = response.getWriter();

            if (usuario.getClass() != Administrador.class) {
                throw new AdminClienteException(true);
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administrar usuarios</title>");
            out.println("<style type=\"text/css\">\n"
                    + ".botones {\n"
                    + "	margin-left: 343px;\n"
                    + "}\n"
                    + ".datosUsr {\n"
                    + "	margin-left: 367px;\n"
                    + "	margin-top: 1px;\n"
                    + " position: relative;"
                    + "left: -118px;"
                    + "top: -90px;"
                    + "}\n"
                    + "</style>");
            out.println("</head>");
            out.println("<body>");

            RequestDispatcher rd = request.getRequestDispatcher("menu");
            rd.include(request, response);
            lstUsr = cUsr.getAll();

            //TODO: agregar tipo
            if (lstUsr.isEmpty()) {
                throw new NoException();
            }
            for (Usuario usr : lstUsr) {
                String estado;
                if (usr.isActivo()) {
                    estado = "Activo";
                } else {
                    estado = "Desactivado";
                }

                out.println("<form method=\"post\">\n"
                        + "	<div id=\"lstUsr\" align=\"center\" class=\"datosUsr\" style=\"border-width: 1px; border-style: solid; width: 448px; right: auto; padding-bottom: 2; height: 264px;\">\n"
                        + "		<table style=\"width: 100%; height: 220px;\" border=\"1px\">\n"
                        + "			<tr>\n"
                        + "				<td style=\"width: 319px\">Nombre de usuario:</td>\n"
                        + "				<td>" + usr.getUsername() + "<input type=\"hidden\" name=\"username\" value=\"" + usr.getUsername() + "\"/> </td>\n"
                        + "			</tr>\n"
                        + "			<tr>\n"
                        + "				<td style=\"width: 319px\">Nombre:</td>\n"
                        + "				<td>" + usr.getNombre() + " <input type=\"hidden\" name=\"nombre\" value=\"" + usr.getNombre() + "\"/> </td>\n"
                        + "			</tr>\n"
                        + "			<tr>\n"
                        + "				<td style=\"width: 319px\">Apellido:</td>\n"
                        + "				<td>" + usr.getApellido() + " <input type=\"hidden\" name=\"apellido\" value=\"" + usr.getApellido() + "\"/> </td>\n"
                        + "			</tr>\n"
                        + showProperOptions(usr)
                        + "                      <tr>\n"
                        + "				<td style=\"width: 319px; height: 7px;\">Estado:</td>\n"
                        + "				<td style=\"height: 7px\">" + estado + "</td>\n" + "<input type=\"hidden\" name=\"dni\" value=\"" + showDni(usr) + "\"/>"
                        + "			</tr>\n"
                        + "		</table>\n"
                        + "		<div class=\"botones\" style=\"width: 91px; height: 27px\">\n"
                        + "			<button type=\"submit\" formmethod=\"post\" formaction=\"EditarUsuario\">Editar</button>"
                        + "		</div>\n"
                        + "	</div> <input type=\"hidden\" name=\"usrId\" value=\"" + usr.getId() + "\"/> <input type=\"hidden\" name=\"password\" value=\"" + usr.getPassword() + "\"/> <input type=\"hidden\" name=\"estado\" value=\"" + estado + "\"/>"
                        + "</form>"
                        + "<br />\n"
                        + "<br />");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (NullPointerException ex) {
            if (usuario == null) {
                session.setAttribute("error", "No se encuentra logueado.");
                response.sendRedirect("error");
            }
        } catch (AdminClienteException ex) {
            Logger.getLogger(AdminUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", "No tiene permisos para realizar esta operación.");
            response.sendRedirect("error");
        } catch (NoException ex) {
            Logger.getLogger(AdminUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", "No hay usuarios.");
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

    public String showProperOptions(Usuario usr) {
        String str = "";
        if (usr.getClass() == Cliente.class) {
            String dni = ((Cliente) usr).getDni().toString();
            str = "<tr>\n"
                    + "<td style=\"width: 319px; height: 7px;\">DNI:</td>\n"
                    + "<td style=\"height: 7px\">" + dni + "</td>\n" + "<input type=\"hidden\" name=\"dni\" value=\"" + dni + "\"/>"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td style=\"width: 319px; height: 7px;\">Compras:</td>\n"
                    + "<td style=\"height: 7px\"><button type=\"submit\" formmethod=\"post\" formaction=\"Compras\">Ver</button></td>\n"
                    + "</tr>\n ";
        }

        return str;
    }

    public String showDni(Usuario usr) {
        String str = "";
        if (usr.getClass() == Cliente.class) {
            String dni = ((Cliente) usr).getDni().toString();
        }

        return str;

    }

}
