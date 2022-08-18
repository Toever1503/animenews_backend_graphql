package animenews.repository;

import animenews.entity.Comment;
import animenews.entity.UserLikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserLikeCommentRepository extends JpaRepository<UserLikeComment, Long>, JpaSpecificationExecutor<UserLikeComment> {
}
