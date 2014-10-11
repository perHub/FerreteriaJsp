/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Imprimen;

import Controladora.CProducto;
import Modelo.Producto;
import Modelo.Usuario;
import Exceptions.NoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "main", urlPatterns = {"/main"})
public class main extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    CProducto cProd = new CProducto();
    Map<Integer, Producto>lstProd;

    {
        if (lstProd == null) {
            lstProd = cProd.getAllMap();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        session.setAttribute("productos", lstProd);
        try {
            if (usuario == null) {
                throw new NoException();
            }
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista de productos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<style type=\"text/css\">\n"
                    + ".botones {\n"
                    + "	margin-left: 343px;\n"
                    + "}\n"
                    + ".lstProd {\n"
                    + "	margin-left: 367px;\n"
                    + "	margin-top: 1px;\n"
                    + "	left: -118px;\n"
                    + " top: -120px;\n"
                    + " position: relative;"
                    + "}\n"
                    + "</style>");
            out.println("<br>");

            RequestDispatcher rd = request.getRequestDispatcher("menu");
            rd.include(request, response);

            if (!lstProd.isEmpty()) {
//                for (Producto prod : lstProd)
                for (Map.Entry<Integer, Producto> prod : lstProd.entrySet()){
                    out.println("<form method=\"post\" action=\"proc/AgregarCarrito.jsp\">\n"
                            + "	<div id=\"lstProd\" align=\"center\" class=\"lstProd\" style=\"border-width: 1px; border-style: solid; width: 448px; right: auto; padding-bottom: 2; height: 154px;\">\n"
                            + "		<table style=\"width: 100%; height: 120px;\" border=\"1px\">\n"
                            + "			<tr>\n"
                            + "				<td style=\"width: 319px\">Prducto:</td>\n"
                            + "				<td>" + prod.getValue().getNombre() + "</td>\n"
                            + "			</tr>\n"
                            + "			<tr>\n"
                            + "				<td style=\"width: 319px\">Precio:</td>\n"
                            + "				<td>$" + prod.getValue().getPrecio() + "</td>\n"
                            + "			</tr>\n"
                            + "			<tr>\n"
                            + "				<td style=\"width: 319px\">Stock:</td>\n"
                            + "				<td>" + prod.getValue().getStock() + "</td>\n"
                            + "			</tr>\n"
                            + "			</table>\n"
                            + "		<div class=\"auto-style1\" style=\"width: 322px; height: 27px\">Cantidad:"
                            + "             <input type=\"number\" name=\"cantidad\" value=\"1\" min=\"1\" max=\"" + prod.getValue().getStock() + "\" style=\"width: 50px\">"
                            + "<input type=\"hidden\" name=\"prodId\" value=\"" + prod.getValue().getId() + "\"/>"
                            + "			<input name=\"Button1\" type=\"submit\" value=\"Agregar al carrito\" />&nbsp;\n"
                            + "			<br />\n"
                            + "			<br />\n"
                            + "		</div>\n"
                            + "	</div>\n"
                            + "</form>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        } catch (NoException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", "No se encuentra logueado.");
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
