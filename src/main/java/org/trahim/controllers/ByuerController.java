package org.trahim.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.trahim.hibernate.HibernateUtil;
import org.trahim.hibernate.entity.Byuer;
import org.trahim.hibernate.entity.Byuer_;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(eager = true)
@SessionScoped
public class ByuerController implements Serializable, Converter {

    private SessionFactory sessionFactory;
    private List<Byuer> byuerList;
    private List<SelectItem> selectItems = new ArrayList<>();
    private Map<Long, Byuer> map;
    private Byuer byuer;
    private long selectedByuerId;
    private boolean editMode;
    private Byuer addByuer;

    public void switchAddMode() {
        addByuer = new Byuer();
    }

    public void addByuer() {
        sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(addByuer);
        session.getTransaction().commit();
        session.close();
        map.put(addByuer.getId(), addByuer);

        selectItems.add(new SelectItem(addByuer, addByuer.getName()));
        addByuer = null;

    }




    public ByuerController() {
        map = new HashMap<>();
        fillAllByuers();
        for (Byuer byuer :
                byuerList) {
            map.put(byuer.getId(), byuer);
            selectItems.add(new SelectItem(byuer, byuer.getName()));
        }

    }

    public void switchEditMode() {
        editMode = !editMode;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public String updateByuer() {
        Session session = sessionFactory.openSession();
        Byuer byuer1 = session.get(Byuer.class, selectedByuerId);
        byuer1.setAddress(byuer.getAddress());
        byuer1.setName(byuer.getName());
        byuer1.setUnp(byuer.getUnp());

        session.beginTransaction();
        session.update(byuer1);
        session.getTransaction().commit();
        session.close();
        switchEditMode();
        return "back";
    }

    public List<Byuer> fillAllByuers() {

        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Byuer> cq = cb.createQuery(Byuer.class);
        Root<Byuer> root = cq.from(Byuer.class);// первостепенный, корневой entity (в sql запросе - from)

        Selection[] selection = {root.get(Byuer_.id), root.get(Byuer_.name)}; // выборка полей
        cq.select(cb.construct(Byuer.class, selection));


        // этап выполнения запроса
        Query query = session.createQuery(cq);

        byuerList = query.getResultList();

        session.close();

        return byuerList;

    }

    public String fillCurrentByuer() {
        sessionFactory = HibernateUtil.getSessionFactory();

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        selectedByuerId = Long.valueOf(params.get("byuer_id"));

        Session session = sessionFactory.openSession();
        byuer = session.get(Byuer.class, selectedByuerId);
        session.close();
        return "byuer";
    }

    public String deleteByuer() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Byuer byuer = session.get(Byuer.class, selectedByuerId);
        session.delete(byuer);
        session.getTransaction().commit();
        session.close();
        return "back";

    }


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String s = value;

        return map.get(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Byuer byuer = (Byuer) value;
        return String.valueOf(((Byuer) value).getId());
    }

    public List<Byuer> getByuerList() {
        return byuerList;
    }

    public void setByuerList(List<Byuer> byuerList) {
        this.byuerList = byuerList;
    }

    public Byuer getByuer() {
        return byuer;
    }

    public void setByuer(Byuer byuer) {
        this.byuer = byuer;
    }

    public long getSelectedByuerId() {
        return selectedByuerId;
    }

    public void setSelectedByuerId(long selectedByuerId) {
        this.selectedByuerId = selectedByuerId;

    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SelectItem> selectItems) {
        this.selectItems = selectItems;
    }

    public Byuer getAddByuer() {
        return addByuer;
    }

    public void setAddByuer(Byuer addByuer) {
        this.addByuer = addByuer;
    }
}
