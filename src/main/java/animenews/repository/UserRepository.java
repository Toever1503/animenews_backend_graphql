package animenews.repository;

import animenews.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUserLogin(String username);

    Optional<User> findByUserEmail(String email);

    Optional<User> findFirstByUserLoginOrUserEmail(String username, String username1);

    Page<User> findAllByUserLoginOrUserEmailLike(String username, String username1, Pageable pageable);

}