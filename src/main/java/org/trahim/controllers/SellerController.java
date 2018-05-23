package org.trahim.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.trahim.hibernate.HibernateUtil;
import org.trahim.hibernate.entity.Seller;
import org.trahim.hibernate.entity.Seller_;

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
public class SellerController implements Serializable, Converter {

    private SessionFactory sessionFactory;

    private List<SelectItem> selectItems = new ArrayList<>();
    private Map<Long,Seller> map;




    private List<Seller> sellerList;
    private boolean editMode;
    private Seller seller;
    private long selectedSellerId;
    private Seller addSeller;

    public void switchAddMode() {
        addSeller = new Seller();
    }

    public void addSeller() {
        sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(addSeller);
        session.getTransaction().commit();
        session.close();

        selectItems.add(new SelectItem(addSeller, addSeller.getName()));
        addSeller = null;

        addSeller = null;
    }


    public SellerController() {

        map = new HashMap<>();
        fillAllSellers();
        for (Seller seller : sellerList) {
            map.put(seller.getId(), seller);
            selectItems.add(new SelectItem(seller, seller.getName()));
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

    public List<Seller> fillAllSellers() {

        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Seller> cq = cb.createQuery(Seller.class);
        Root<Seller> root = cq.from(Seller.class);// первостепенный, корневой entity (в sql запросе - from)

        Selection[] selection = {root.get(Seller_.id), root.get(Seller_.name)}; // выборка полей, в классе Author должен быть конструктор с этими параметрами

        cq.select(cb.construct(Seller.class, selection));


        // этап выполнения запроса
        Query query = session.createQuery(cq);

        sellerList = query.getResultList();

        session.close();

        return sellerList;

    }

    public String fillCurrentSeller() {
        sessionFactory = HibernateUtil.getSessionFactory();

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        selectedSellerId = Long.parseLong(params.get("seller_id"));

        Session session = sessionFactory.openSession();

        seller = session.get(Seller.class, selectedSellerId);
        session.close();
        return "seller";
    }

    public String updateSeller() {
        sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();
        Seller seller1 = session.get(Seller.class, selectedSellerId);
        seller1.setName(seller.getName());
        seller1.setAddress(seller.getAddress());
        seller1.setUnp(seller.getUnp());

        session.beginTransaction();
        session.update(seller1);
        session.getTransaction().commit();
        session.close();

        return "back";
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public long getSelectedSellerId() {
        return selectedSellerId;
    }

    public void setSelectedSellerId(long selectedSellerId) {
        this.selectedSellerId = selectedSellerId;
    }

    public Seller getAddSeller() {
        return addSeller;
    }

    public void setAddSeller(Seller addSeller) {
        this.addSeller = addSeller;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
         return map.get(Long.valueOf(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Seller seller = (Seller) o;
        return String.valueOf(seller.getId());
    }
}
