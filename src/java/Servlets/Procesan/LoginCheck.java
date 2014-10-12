/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Procesan;

import Exceptions.loginException;
import Modelo.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Dev
 */
public class LoginCheck extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     * @throws javax.servlet.jsp.JspException
     */
    @Override
    public void doTag() throws JspException {

        try {
            Usuario usr = (Usuario) getJspContext().getAttribute("usuario", PageContext.SESSION_SCOPE);
            if (usr == null) {
                throw new loginException();

            }

        } catch (loginException ex) {
            getJspContext().setAttribute("error", ex.getMessage());
            throw new JspException(ex.getMessage());
        }
    }

}
