package animenews.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Authority.class)
public abstract class Authority_ {

	public static volatile SingularAttribute<Authority, String> authorityName;
	public static volatile SingularAttribute<Authority, Integer> authorityLevel;
	public static volatile SingularAttribute<Authority, Integer> id;
	public static volatile SetAttribute<Authority, User> users;

	public static final String AUTHORITY_NAME = "authorityName";
	public static final String AUTHORITY_LEVEL = "authorityLevel";
	public static final String ID = "id";
	public static final String USERS = "users";

}

