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
public class Byuer  {
    private long id;
    private String name;
    private long unp;
    private String address;


    public Byuer() {
    }

    public Byuer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private List<ExchangeTransaction> exchangeTransactions = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, targetEntity = ExchangeTransaction.class, mappedBy = "byuer")
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
    @Column(name = "name", nullable = false, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "UNP", nullable = false)
    public long getUnp() {
        return unp;
    }

    public void setUnp(long unp) {
        this.unp = unp;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byuer byuer = (Byuer) o;
        return id == byuer.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
