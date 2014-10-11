/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Imprimen;

import Controladora.CCompra;
import Modelo.*;
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
 * Para el admin llamo a esta página filtrando por id
 */
@WebServlet(name = "Compras", urlPatterns = {"/Compras"})
public class Compras extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            Usuario usr;

            if ((usr = (Usuario) session.getAttribute("usuario")) == null) {
                throw new NoException();
            }
            int compUsrid;
            String sUsId;

            if ((sUsId = request.getParameter("usrId")) == null) {
                compUsrid = Integer.parseInt(session.getAttribute("compUsrId").toString());
            } else {
                compUsrid = Integer.parseInt(sUsId);
            }

            CCompra cc = new CCompra();
            List<Compra> lstComp = cc.getAllByUserId(compUsrid);

            if (lstComp == null) {
                session.setAttribute("error", "Error no especificado.");
                response.sendRedirect("error");
            } else if (lstComp.isEmpty()) {
                session.setAttribute("error", "No hay compras.");
                response.sendRedirect("error");
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<style type=\"text/css\">\n"
                    + ".det {\n"
                    + "	margin-left: 367px;\n"
                    + "	margin-top: 1px;\n"
                    + "	left: -118px;\n"
                    + "top: -100px;"
                    + " position: relative;"
                    + "}"
                    + "</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div style=\"margin-left: 367px;\"><h1>Historial de compras</h1></div>");
            RequestDispatcher rd = request.getRequestDispatcher("menu");
            rd.include(request, response);
            int cont = 0;

            for (Compra c : lstComp) {
                out.println("<form id=\"comp" + cont + "\" action=\"DetallesDeCompra\" method=\"post\">\n"
                        + "	<div class=\"det\" style=\"width: 321px\">\n"
                        + "		<table border=\"1px\" style=\"width: 54%; height: 64px;\">\n"
                        + "			<tr>\n"
                        + "				<td style=\"width: 198px\">Fecha:</td>\n"
                        + "				<td style=\"width: 334px\">" + c.getFecha().toString() + "</td>\n"
                        + "			</tr>\n"
                        + "			<tr>\n"
                        + "				<td style=\"width: 198px\">Total:</td>\n"
                        + "				<td style=\"width: 334px\"><a href=\"javascript:comp" + cont + ".submit()\">$" + c.getTotal() + "</a></td>\n"
                        + "			</tr>\n"
                        + "		</table>\n"
                        + "		<input name=\"compId\" type=\"hidden\" value=\"" + c.getId() + "\" /></div>\n"
                        + "</form>"
                        + "</br>");
                cont++;
            }
            out.println("</body>");
            out.println("</html>");
        } catch (NullPointerException ex) {

        } catch (NoException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", "No se encuentra logueado.");
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
