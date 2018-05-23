package org.trahim.hibernate.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "exchange_transaction", schema = "burse", catalog = "")
public class ExchangeTransaction {


    private long id;
    private long referenceExchangeNumber;
    private String transactionDate;
    private String nameTraidingSession;
    private long amountExchangeTransaction;

    private Byuer byuer;
    private Product product;
    private Seller seller;


    @JoinColumn
    @ManyToOne
    public Byuer getByuer() {
        return byuer;
    }

    public void setByuer(Byuer byuer) {
        this.byuer = byuer;
    }

    @JoinColumn
    @ManyToOne
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @JoinColumn
    @ManyToOne
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Id
    @Column(name = "id", nullable = false)

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "reference_exchange_number", nullable = false)
    public long getReferenceExchangeNumber() {
        return referenceExchangeNumber;
    }

    public void setReferenceExchangeNumber(long referenceExchangeNumber) {
        this.referenceExchangeNumber = referenceExchangeNumber;
    }

    @Basic
    @Column(name = "transaction_date", nullable = false, length = 45)
    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Basic
    @Column(name = "name_traiding_session", nullable = false, length = 45)
    public String getNameTraidingSession() {
        return nameTraidingSession;
    }

    public void setNameTraidingSession(String nameTraidingSession) {
        this.nameTraidingSession = nameTraidingSession;
    }

    @Basic
    @Column(name = "amount_exchange_transaction", nullable = false)
    public long getAmountExchangeTransaction() {
        return amountExchangeTransaction;
    }

    public void setAmountExchangeTransaction(long amountExchangeTransaction) {
        this.amountExchangeTransaction = amountExchangeTransaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeTransaction that = (ExchangeTransaction) o;
        return id == that.id &&
                referenceExchangeNumber == that.referenceExchangeNumber &&
                amountExchangeTransaction == that.amountExchangeTransaction &&
                Objects.equals(transactionDate, that.transactionDate) &&
                Objects.equals(nameTraidingSession, that.nameTraidingSession);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, referenceExchangeNumber, transactionDate, nameTraidingSession, amountExchangeTransaction);
    }
}
