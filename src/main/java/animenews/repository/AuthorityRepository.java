package animenews.repository;

import animenews.entity.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer>, JpaSpecificationExecutor<Authority> {
    Set<Authority> findAllByAuthorityLevelGreaterThanEqual(Integer authorityLevel);

    Optional<Authority> findAuthorityByAndAuthorityName(String name);

    @Query("select a from Authority a join UserAuthority ua on a.id = ua.id.authorityId where ua.id.userId = ?1")
    List<Authority> findAllAuthoritesByUser(Long userId);

    Page<Authority> findAllByAuthorityNameLike(String authorityName, Pageable pageable);
}