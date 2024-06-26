package animenews.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Post.class)
public abstract class Post_ {

	public static volatile SingularAttribute<Post, String> postExcerpt;
	public static volatile SingularAttribute<Post, String> postContent;
	public static volatile SingularAttribute<Post, Date> postModified;
	public static volatile SingularAttribute<Post, User> author;
	public static volatile SingularAttribute<Post, String> postName;
	public static volatile SingularAttribute<Post, String> postStatus;
	public static volatile SingularAttribute<Post, Date> postDate;
	public static volatile SingularAttribute<Post, String> postTitle;
	public static volatile SingularAttribute<Post, String> postFeaturedImage;
	public static volatile SingularAttribute<Post, Long> id;
	public static volatile SingularAttribute<Post, Boolean> isPinged;
	public static volatile SingularAttribute<Post, Long> commentCount;

	public static final String POST_EXCERPT = "postExcerpt";
	public static final String POST_CONTENT = "postContent";
	public static final String POST_MODIFIED = "postModified";
	public static final String AUTHOR = "author";
	public static final String POST_NAME = "postName";
	public static final String POST_STATUS = "postStatus";
	public static final String POST_DATE = "postDate";
	public static final String POST_TITLE = "postTitle";
	public static final String POST_FEATURED_IMAGE = "postFeaturedImage";
	public static final String ID = "id";
	public static final String IS_PINGED = "isPinged";
	public static final String COMMENT_COUNT = "commentCount";

}

