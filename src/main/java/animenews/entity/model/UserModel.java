package animenews.entity.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String userLogin;

    private String userPass;

    private String userNicename;

    private String userEmail;


    private Date userRegistered;

    private Long userActivationKey;

    private Boolean userStatus;

    private String displayName;

    private String userAvatar;

    private String userDescription;

    private Integer loginFailed;

    private List<Integer> userRoles;

}