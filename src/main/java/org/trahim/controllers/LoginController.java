package org.trahim.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class LoginController implements Serializable {
    public String login() {
        return "burse";
    }

    public String back() {
        return "back";
    }

    public String exit() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "exit";
    }
}
