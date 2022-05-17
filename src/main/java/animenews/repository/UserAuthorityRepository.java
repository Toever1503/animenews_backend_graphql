package animenews.repository;

import animenews.entity.relationship.user.UserAuthority;
import animenews.entity.relationship.user.UserAuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, UserAuthorityId> {
    void deleteAllByIdUserId(Long userId);

    void deleteAllByIdAuthorityId(Long authorityId);

    void deleteAllByIdUserIdAndIdAuthorityIdNotIn(Long userId, Iterable<Long> authorityIds);
}