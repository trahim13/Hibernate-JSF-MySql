package org.trahim.controllers;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.trahim.hibernate.HibernateUtil;
import org.trahim.hibernate.entity.Product;
import org.trahim.hibernate.entity.Product_;

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
public class ProductController implements Serializable, Converter {

    private SessionFactory sessionFactory;
    private List<Product> productList;
    private List<SelectItem> selectItems = new ArrayList<>();
    private Map<Long, Product> map;
    private boolean editMode;
    private Product product;
    private long selectedProductId;

    public ProductController() {
        map = new HashMap<>();
        fillAllProducts();

        for (Product product :
                productList) {
            map.put(product.getId(), product);
            selectItems.add(new SelectItem(product, product.getName()));
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

    public List<Product> fillAllProducts() {

        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);// первостепенный, корневой entity (в sql запросе - from)

        Selection[] selection = {root.get(Product_.id), root.get(Product_.name)}; // выборка полей, в классе Author должен быть конструктор с этими параметрами

        cq.select(cb.construct(Product.class, selection));


        // этап выполнения запроса
        Query query = session.createQuery(cq);

        productList = query.getResultList();

        session.close();

        return productList;

    }

    public String fillCurrentProduct() {
        sessionFactory = HibernateUtil.getSessionFactory();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        selectedProductId = Long.parseLong(params.get("product_id"));
        Session session = sessionFactory.openSession();
        product = session.get(Product.class, selectedProductId);
        session.close();
        return "product";
    }

    public String updateProduct() {
        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Product product1 = session.get(Product.class, selectedProductId);

        product1.setName(product.getName());
        product1.setCurrency(product.getCurrency());
        product1.setDeliveryConditions(product.getDeliveryConditions());
        product1.setPrice(product.getPrice());
        product1.setProducerGoods(product.getProducerGoods());
        product1.setQuantity(product.getQuantity());
        product1.setTermsPayment(product.getTermsPayment());
        product1.setUnit(product.getUnit());
        product1.setVatRate(product.getVatRate());


        session.beginTransaction();
        session.update(product1);
        session.getTransaction().commit();
        session.close();

        return "back";
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String s = value;
        return map.get(Long.valueOf(value.trim()));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Product product = (Product) value;
        return String.valueOf(((Product) value).getId());
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getSelectedProductId() {
        return selectedProductId;
    }

    public void setSelectedProductId(long selectedProductId) {
        this.selectedProductId = selectedProductId;
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SelectItem> selectItems) {
        this.selectItems = selectItems;
    }
}
