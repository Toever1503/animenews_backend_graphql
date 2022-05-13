package animenews.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Boolean> userStatus;
	public static volatile SingularAttribute<User, String> userDescription;
	public static volatile SingularAttribute<User, String> displayName;
	public static volatile SingularAttribute<User, Integer> loginFailed;
	public static volatile SingularAttribute<User, String> userPass;
	public static volatile SingularAttribute<User, String> userAvatar;
	public static volatile ListAttribute<User, Authority> userRoles_;
	public static volatile SingularAttribute<User, Date> userRegistered;
	public static volatile SingularAttribute<User, String> userNicename;
	public static volatile SingularAttribute<User, String> userLogin;
	public static volatile SingularAttribute<User, String> userEmail;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, Long> userActivationKey;

	public static final String USER_STATUS = "userStatus";
	public static final String USER_DESCRIPTION = "userDescription";
	public static final String DISPLAY_NAME = "displayName";
	public static final String LOGIN_FAILED = "loginFailed";
	public static final String USER_PASS = "userPass";
	public static final String USER_AVATAR = "userAvatar";
	public static final String USER_ROLES_ = "userRoles_";
	public static final String USER_REGISTERED = "userRegistered";
	public static final String USER_NICENAME = "userNicename";
	public static final String USER_LOGIN = "userLogin";
	public static final String USER_EMAIL = "userEmail";
	public static final String ID = "id";
	public static final String USER_ACTIVATION_KEY = "userActivationKey";

}

