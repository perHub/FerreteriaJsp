/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Imprimen;

import Controladora.CCompra;
import Modelo.Detalle;
import Modelo.Usuario;
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
@WebServlet(name = "DetallesDeCompra", urlPatterns = {"/DetallesDeCompra"})
public class DetallesDeCompra extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            
            if(request.getParameterMap().isEmpty()){
                throw new NoException();
            }
            
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            
            if(usuario == null){
                session.setAttribute("error", "No se encuentra logueado.");
                response.sendRedirect("error");
            }
            
            PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<style type=\"text/css\">\n"
                    + ".botonR {\n"
                    + "margin-left: 63px;\n"
                    + "margin-top: 5px;"
                    + "}\n"
                    + ".carr {\n"
                    + "	margin-left: 367px;\n"
                    + "	margin-top: 1px;\n"
                    + "	left: -118px;\n"
                    + " position: relative;"
                    + "}\n"
                    + ".botonConf {\n"
                    + "	margin-left: 800px;\n"
                    + "}"
                    + "</style>");
            out.println("</head>");
            out.println("<body>");
            RequestDispatcher rd = request.getRequestDispatcher("menu");
            rd.include(request, response);

            int compId = Integer.parseInt(request.getParameter("compId"));
            CCompra cComp = new CCompra();
            List<Detalle> detalles = cComp.getDetalles(compId);

            if (detalles.size() > 0) {
                for (Detalle d : detalles) {
                    out.println("<form action=\"eliminarCarrito\" method=\"post\">\n"
                            + "	<div id=\"carrito\" align=\"center\" class=\"carr\" style=\"border-width: 1px; border-style: solid; width: 448px; right: auto; padding-bottom: 2; height: 157px;\">\n"
                            + "		<table style=\"width: 100%; height: 120px;\" border= \"1px\">"
                            + "			<tr>\n"
                            + "				<td style=\"width: 319px\">Prducto:</td>\n"
                            + "				<td>" + d.getProducto().getNombre() + "</td>\n"
                            + "			</tr>\n"
                            + "			<tr>\n"
                            + "				<td style=\"width: 319px\">Cantidad:</td>\n"
                            + "				<td>" + d.getCantidad() + "</td>\n"
                            + "			</tr>\n"
                            + "			<tr>\n"
                            + "				<td style=\"width: 319px\">Precio unitario:</td>\n"
                            + "				<td>" + d.getProducto().getPrecio() + "</td>\n"
                            + "			</tr>\n"
                            + "			<tr>\n"
                            + "				<td style=\"width: 319px\">Precio total:</td>\n"
                            + "				<td>" + d.getPrecio() + "</td>\n"
                            + "			</tr>\n"
                            + "		</table>\n"
                            + "	</div>\n"
                            + "</form>"
                            + "<br>");
                }
                out.println("</body>");
                out.println("</html>");
            }
        } catch (NoException ex) {
            Logger.getLogger(DetallesDeCompra.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", "Debe seleccionar un detalle para acceder.");
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
