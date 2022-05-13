package animenews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "user_login", nullable = false, length = 60)
    private String userLogin;

    @Column(name = "user_pass", nullable = false)
    private String userPass;

    @Column(name = "user_nicename", length = 255)
    private String userNicename;

    @Column(name = "user_email", nullable = false, length = 100)
    private String userEmail;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_registered", nullable = false)
    private Date userRegistered;

    @Column(name = "user_activation_key")
    private Long userActivationKey;

    @Column(name = "user_status")
    private Boolean userStatus;

    @Column(name = "display_name", length = 250)
    private String displayName;

    @Column(name = "user_avatar")
    private String userAvatar;

    @Column(name = "user_description", length = 1000)
    private String userDescription;

    @Column(name = "login_failed")
    private Integer loginFailed;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private List<Authority> userRoles_;

}