package animenews.entity.relationship.user;

import lombok.Builder;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Builder
@Embeddable
public class UserAuthorityId implements Serializable {
    private static final long serialVersionUID = -932343280333850559L;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "authority_id", nullable = false)
    private Integer authorityId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserAuthorityId entity = (UserAuthorityId) o;
        return Objects.equals(this.authorityId, entity.authorityId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorityId, userId);
    }

}