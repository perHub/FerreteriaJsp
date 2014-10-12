/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tags;

import Exceptions.AdminClienteException;
import Modelo.*;
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
public class UsuarioCheck extends SimpleTagSupport {

    private boolean admin;
    private boolean isPresent;
    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        

        try {
            Usuario usr = (Usuario) getJspContext().getAttribute("usuario", PageContext.SESSION_SCOPE);
            if ( admin && usr.getClass() == Cliente.class) {
                throw new AdminClienteException();
            }
            else if(!admin && usr.getClass() == Administrador.class)
                throw new AdminClienteException();
            
            
        } catch (AdminClienteException ex) {
            throw new JspException(ex.getMessage());
        }
    }

    public void setAdmin(boolean admin) {
        isPresent = true;
        this.admin = admin;
    }

}

