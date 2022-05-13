package animenews.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Term.class)
public abstract class Term_ {

	public static volatile SingularAttribute<Term, String> name;
	public static volatile SingularAttribute<Term, String> description;
	public static volatile SingularAttribute<Term, Long> id;
	public static volatile SingularAttribute<Term, String> slug;

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String SLUG = "slug";

}

