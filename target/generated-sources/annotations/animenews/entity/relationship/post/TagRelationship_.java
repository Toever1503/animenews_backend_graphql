package animenews.entity.relationship.post;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TagRelationship.class)
public abstract class TagRelationship_ {

	public static volatile SingularAttribute<TagRelationship, String> tagBy;
	public static volatile SingularAttribute<TagRelationship, Long> tagId;
	public static volatile SingularAttribute<TagRelationship, Long> id;
	public static volatile SingularAttribute<TagRelationship, Long> objectId;

	public static final String TAG_BY = "tagBy";
	public static final String TAG_ID = "tagId";
	public static final String ID = "id";
	public static final String OBJECT_ID = "objectId";

}

