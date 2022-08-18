package animenews.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comment.class)
public abstract class Comment_ {

	public static volatile SingularAttribute<Comment, Long> commentParent;
	public static volatile SingularAttribute<Comment, Date> createdDate;
	public static volatile SingularAttribute<Comment, User> userComment;
	public static volatile SingularAttribute<Comment, Post> post;
	public static volatile SingularAttribute<Comment, Integer> totalLike;
	public static volatile SingularAttribute<Comment, Integer> totalDislike;
	public static volatile SingularAttribute<Comment, Long> id;
	public static volatile SingularAttribute<Comment, Date> updatedDate;
	public static volatile ListAttribute<Comment, Comment> commentChilds;
	public static volatile SingularAttribute<Comment, String> content;

	public static final String COMMENT_PARENT = "commentParent";
	public static final String CREATED_DATE = "createdDate";
	public static final String USER_COMMENT = "userComment";
	public static final String POST = "post";
	public static final String TOTAL_LIKE = "totalLike";
	public static final String TOTAL_DISLIKE = "totalDislike";
	public static final String ID = "id";
	public static final String UPDATED_DATE = "updatedDate";
	public static final String COMMENT_CHILDS = "commentChilds";
	public static final String CONTENT = "content";

}

