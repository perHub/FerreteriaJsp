/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tags;

import Exceptions.AdminClienteException;
import Modelo.*;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

/**
 *
 * @author Peri
 */
public class LoginCheck extends TagHandler {

    public LoginCheck(TagConfig config) {
        super(config);
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
        ctx.getAttribute("admin");
        this.tag.getAttributes();

        UIOutput child = new HtmlOutputText();
        child.setValue("Hello World");
        parent.getChildren().add(child);
        
        nextHandler.apply(ctx, parent);
    }

}
