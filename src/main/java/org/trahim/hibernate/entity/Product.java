package org.trahim.hibernate.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DynamicInsert
@DynamicUpdate
public class Product {
    private long id;
    private String deliveryConditions;
    private String name;
    private String termsPayment;
    private String producerGoods;
    private String unit;
    private long quantity;
    private String currency;
    private long vatRate;
    private long price;
    private List<ExchangeTransaction> exchangeTransactions = new ArrayList<>();

    public Product() {
    }

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(fetch = FetchType.EAGER, targetEntity = ExchangeTransaction.class, mappedBy = "product")
    public List<ExchangeTransaction> getExchangeTransactions() {
        return exchangeTransactions;
    }

    public void setExchangeTransactions(List<ExchangeTransaction> exchangeTransactions) {
        this.exchangeTransactions = exchangeTransactions;
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
    @Column(name = "delivery_conditions", nullable = false, length = 200)
    public String getDeliveryConditions() {
        return deliveryConditions;
    }

    public void setDeliveryConditions(String deliveryConditions) {
        this.deliveryConditions = deliveryConditions;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "terms_payment", nullable = false, length = 200)
    public String getTermsPayment() {
        return termsPayment;
    }

    public void setTermsPayment(String termsPayment) {
        this.termsPayment = termsPayment;
    }

    @Basic
    @Column(name = "producer_goods", nullable = false, length = 200)
    public String getProducerGoods() {
        return producerGoods;
    }

    public void setProducerGoods(String producerGoods) {
        this.producerGoods = producerGoods;
    }

    @Basic
    @Column(name = "unit", nullable = false, length = 45)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "currency", nullable = false, length = 100)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "VAT_rate", nullable = false)
    public long getVatRate() {
        return vatRate;
    }

    public void setVatRate(long vatRate) {
        this.vatRate = vatRate;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
