package org.trahim.hibernate.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, String> termsPayment;
	public static volatile SingularAttribute<Product, String> unit;
	public static volatile SingularAttribute<Product, String> deliveryConditions;
	public static volatile SingularAttribute<Product, Long> quantity;
	public static volatile SingularAttribute<Product, Long> price;
	public static volatile SingularAttribute<Product, Long> vatRate;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, String> producerGoods;
	public static volatile SingularAttribute<Product, String> currency;
	public static volatile ListAttribute<Product, ExchangeTransaction> exchangeTransactions;
	public static volatile SingularAttribute<Product, Long> id;

}

