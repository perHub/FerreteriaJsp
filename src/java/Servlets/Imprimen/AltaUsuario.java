/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Imprimen;

import Modelo.Administrador;
import Modelo.Usuario;
import Exceptions.NoException;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AltaUsuario", urlPatterns = {"/AltaUsuario"})
public class AltaUsuario extends HttpServlet {

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
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        try {
            if (usuario.getClass() != Administrador.class) {
                throw new NoException();
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<script src=\"scripts/check.js\"></script>");
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

            out.println("<form name=\"f1\" method=\"post\" action=\"crearusr\" id=\"f1\">\n"
                    + "	<div id=\"lstUsr\" align=\"center\" class=\"datosUsr\" style=\"border-width: 1px; border-style: solid; width: 448px; right: auto; padding-bottom: 2; height: 200px;\">\n"
                    + "        <table>\n"
                    + "            <tr>\n"
                    + "                <td class=\"f1_label\">Nombre de usuario :</td><td><input type=\"text\" name=\"username\" value=\"\" />\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td class=\"f1_label\">Contraseña  :</td><td><input type=\"password\" name=\"password\" value=\"\"  />\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "			 <tr>\n"
                    + "                <td class=\"f1_label\">Nombre  :</td><td><input type=\"text\" name=\"nombre\" value=\"\"  />\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "			 <tr>\n"
                    + "                <td class=\"f1_label\">Apellido  :</td><td><input type=\"text\" name=\"apellido\" value=\"\"  />\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "			<tr>\n"
                    + "                <td class=\"f1_label\">DNI  :</td><td><input type=\"text\" name=\"dni\" value=\"\"  />\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "		<tr>\n"
                    + "               <td>Tipo:</td> <td>"
                    + "<select name=\"tipo\" style=\"width: 116px\">\n"
                    + "	<option value=\"Cliente\">Cliente</option>\n"
                    + "	<option value=\"Administrador\">Administrador</option>\n"
                    + "	</select></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td>\n"
                    + "                    <input type=\"button\" name=\"crear\" value=\"Crear usuario\" onclick=\"check()\" style=\"font-size:18px; \" />\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "        </table>\n"
                    + "       </div>"
                    + "    </form> ");
            out.println("</body>");
            out.println("</html>");
        } catch (NullPointerException ex) {
            if (usuario == null) {
                session.setAttribute("error", "No se encuentra logueado.");
                response.sendRedirect("error");
            }
        } catch (NoException ex) {
            Logger.getLogger(AltaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", "No tiene permisos para realizar esta operación.");
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
