package org.trahim.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;
import org.trahim.hibernate.HibernateUtil;
import org.trahim.hibernate.entity.ExchangeTransaction;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

@ManagedBean
@SessionScoped
public class TransactionController implements Serializable {
    SessionFactory sessionFactory;

    private ExchangeTransaction transaction;
    private long selectedTransactionId;
    private boolean editMode;
    private ExchangeTransaction addTransaction;


    public void switchEditMode() {
        editMode = !editMode;
    }

    public void switchAddMode() {
                addTransaction = new ExchangeTransaction();
            }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }


    public String fillCurrentTransaction() {
        sessionFactory = HibernateUtil.getSessionFactory();

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        selectedTransactionId = Long.valueOf(params.get("transaction_id"));

        Session session = sessionFactory.openSession();
        transaction = session.get(ExchangeTransaction.class, selectedTransactionId);
        session.close();
        return "transaction";
    }

    public String deleteTransaction() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        ExchangeTransaction transaction = session.get(ExchangeTransaction.class, selectedTransactionId);
        session.delete(transaction);
        session.getTransaction().commit();
        session.close();
        return "back";

    }

    public String updateTransaction() {
        Session session = sessionFactory.openSession();
        ExchangeTransaction transaction1 = session.get(ExchangeTransaction.class, selectedTransactionId);
        transaction1.setTransactionDate(transaction.getTransactionDate());
        transaction1.setReferenceExchangeNumber(transaction.getReferenceExchangeNumber());
        transaction1.setNameTraidingSession(transaction.getNameTraidingSession());
        transaction1.setAmountExchangeTransaction(transaction.getAmountExchangeTransaction());
        session.beginTransaction();
        session.update(transaction1);
        session.getTransaction().commit();
        session.close();
        switchEditMode();
        return "back";
    }


    public void addTransaction() {
        sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(addTransaction);
        session.getTransaction().commit();
        session.close();
        addTransaction = null;

    }

    public ExchangeTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(ExchangeTransaction transaction) {
        this.transaction = transaction;
    }

    public long getSelectedTransactionId() {
        return selectedTransactionId;
    }

    public void setSelectedTransactionId(long selectedTransactionId) {
        this.selectedTransactionId = selectedTransactionId;


    }

    public ExchangeTransaction getAddTransaction() {
        return addTransaction;
    }

    public void setAddTransaction(ExchangeTransaction addTransaction) {
        this.addTransaction = addTransaction;
    }
}
