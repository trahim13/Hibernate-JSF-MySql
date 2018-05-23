package org.trahim.hibernate.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExchangeTransaction.class)
public abstract class ExchangeTransaction_ {

	public static volatile SingularAttribute<ExchangeTransaction, Seller> seller;
	public static volatile SingularAttribute<ExchangeTransaction, Product> product;
	public static volatile SingularAttribute<ExchangeTransaction, Byuer> byuer;
	public static volatile SingularAttribute<ExchangeTransaction, String> nameTraidingSession;
	public static volatile SingularAttribute<ExchangeTransaction, Long> referenceExchangeNumber;
	public static volatile SingularAttribute<ExchangeTransaction, Long> id;
	public static volatile SingularAttribute<ExchangeTransaction, String> transactionDate;
	public static volatile SingularAttribute<ExchangeTransaction, Long> amountExchangeTransaction;

}

