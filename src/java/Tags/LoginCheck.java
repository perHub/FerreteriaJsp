/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tags;

import Exceptions.AdminClienteException;
import Exceptions.loginException;
import Modelo.Administrador;
import Modelo.Cliente;
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

    private boolean admin;
    private boolean isPresent;

    @Override
    public void doTag() throws JspException {

        try {
            Usuario usr = (Usuario) getJspContext().getAttribute("usuario", PageContext.SESSION_SCOPE);
            if (usr == null) {
                throw new loginException();
            } else if (!usr.isActivo()) {

                throw new JspException("Usuario inactivo.");
            }

            if (isPresent) {
                if (admin && usr.getClass() == Cliente.class) {
                    throw new AdminClienteException();
                } else if (!admin && usr.getClass() == Administrador.class) {
                    throw new AdminClienteException();
                }
            }

        } catch (loginException ex) {
            getJspContext().setAttribute("error", ex.getMessage());
            throw new JspException(ex.getMessage());
        } catch (AdminClienteException ex) {
            throw new JspException(ex.getMessage());
        }
    }

    public void setAdmin(boolean admin) {
        isPresent = true;
        this.admin = admin;
    }

}
