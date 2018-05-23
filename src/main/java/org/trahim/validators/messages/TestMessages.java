package org.trahim.validators.messages;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class TestMessages {


    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage("Успешно!", "Действие выполно успешно.");
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(null, facesMessage);

    }

    private void headerMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage("Внимание!", "В целях демонстрции реализован поиск только по полям: Дата биржевой сделки " +
                "и наименование торговой сессии. Поиск не строгий, типа like.");
        facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
        context.addMessage(null, facesMessage);

    }


}

