package animenews.entity.relationship.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_authority")
public class UserAuthority {
    @EmbeddedId
    private UserAuthorityId id;

    public UserAuthorityId getId() {
        return id;
    }

    public void setId(UserAuthorityId id) {
        this.id = id;
    }

//TODO [JPA Buddy] generate columns from DB
}