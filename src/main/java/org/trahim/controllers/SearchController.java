package org.trahim.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.trahim.hibernate.HibernateUtil;
import org.trahim.hibernate.entity.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ManagedBean(eager = true)
@SessionScoped
public class SearchController implements Serializable {

    private static boolean editMode;
    private SessionFactory sessionFactory;
    private String searchString;
    private Long selectedByuerId;
    private List<ExchangeTransaction> transactionList;
    private int count;

    public SearchController() {
        fillAllTransactions();
//
    }

    public void switchEditMode() {
        editMode = !editMode;
    }

    public void fillAllTransactions() {
        editMode = false;

        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();


        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(ExchangeTransaction.class);
        Root<ExchangeTransaction> root = criteriaQuery.from(ExchangeTransaction.class);
        criteriaQuery.select(root);
        Query query = session.createQuery(criteriaQuery);

        transactionList = query.getResultList();
        session.close();

    }


    public String fillExchangeTransactionsByShearch() {
        editMode = false;
        if (searchString.trim().length() == 0) {
            fillAllTransactions();
            return "burse";
        }


        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<ExchangeTransaction> cq = cb.createQuery(ExchangeTransaction.class);

        Root<ExchangeTransaction> root = cq.from(ExchangeTransaction.class);

        ParameterExpression<String> nameParam = cb.parameter(String.class, "param");
//        ParameterExpression<Long> longParam = cb.parameter(Long.class, "param2");


        cq.select(root)
                .where(cb.or(
                        cb.like(root.get(ExchangeTransaction_.nameTraidingSession), nameParam),
                        cb.like(root.get(ExchangeTransaction_.transactionDate), nameParam)
//                        cb.ge(root.get(ExchangeTransaction_.referenceExchangeNumber), longParam)

                ));


        Query query = session.createQuery(cq);
        query.setParameter("param", "%" + searchString + "%");
//        query.setParameter("param2", longParam);


        transactionList = query.getResultList();

        session.close();


        return "burse";
    }


    public String fillByuerTransactions() {

        editMode = false;

        sessionFactory = HibernateUtil.getSessionFactory();

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();


        selectedByuerId = Long.valueOf(params.get("buyer_id"));


        Session session = sessionFactory.openSession();
        Byuer byuer = session.get(Byuer.class, selectedByuerId);

        transactionList = byuer.getExchangeTransactions();
        session.close();
        return "burse";
    }


    public String fillSellerTransactions() {
        editMode = false;

        sessionFactory = HibernateUtil.getSessionFactory();

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();


        selectedByuerId = Long.valueOf(params.get("seller_id"));


        Session session = sessionFactory.openSession();
        Seller seller = session.get(Seller.class, selectedByuerId);

        transactionList = seller.getExchangeTransactions();
        session.close();
        return "burse";
    }


    public String fillProductTransactions() {

        editMode = false;

        sessionFactory = HibernateUtil.getSessionFactory();

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();


        selectedByuerId = Long.valueOf(params.get("product_id"));


        Session session = sessionFactory.openSession();
        Product product = session.get(Product.class, selectedByuerId);

        transactionList = product.getExchangeTransactions();
        session.close();
        return "burse";
    }

    public String updateExchangeTransaction() {


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        for (ExchangeTransaction t :
                transactionList) {
            count++;
            if (count % 20 == 0) {
                session.flush();
            }
            session.update(t);

        }

        session.getTransaction().commit();
        session.close();

        switchEditMode();
        return "burse";

    }


    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public Long getSelectedByuerId() {
        return selectedByuerId;
    }


    public List<ExchangeTransaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<ExchangeTransaction> transactionList) {
        this.transactionList = transactionList;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

}
