package org.trahim.hibernate.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Byuer.class)
public abstract class Byuer_ {

	public static volatile SingularAttribute<Byuer, String> address;
	public static volatile SingularAttribute<Byuer, Long> unp;
	public static volatile SingularAttribute<Byuer, String> name;
	public static volatile ListAttribute<Byuer, ExchangeTransaction> exchangeTransactions;
	public static volatile SingularAttribute<Byuer, Long> id;

}

