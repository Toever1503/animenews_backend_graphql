package animenews.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserLikeComment.class)
public abstract class UserLikeComment_ {

	public static volatile SingularAttribute<UserLikeComment, Boolean> hasDisLike;
	public static volatile SingularAttribute<UserLikeComment, Boolean> hasLike;
	public static volatile SingularAttribute<UserLikeComment, User> userLike;
	public static volatile SingularAttribute<UserLikeComment, Comment> comment;
	public static volatile SingularAttribute<UserLikeComment, Long> id;

	public static final String HAS_DIS_LIKE = "hasDisLike";
	public static final String HAS_LIKE = "hasLike";
	public static final String USER_LIKE = "userLike";
	public static final String COMMENT = "comment";
	public static final String ID = "id";

}

