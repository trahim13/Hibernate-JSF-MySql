package org.trahim.hibernate.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Seller.class)
public abstract class Seller_ {

	public static volatile SingularAttribute<Seller, String> address;
	public static volatile SingularAttribute<Seller, Long> unp;
	public static volatile SingularAttribute<Seller, String> name;
	public static volatile ListAttribute<Seller, ExchangeTransaction> exchangeTransactions;
	public static volatile SingularAttribute<Seller, Long> id;

}

