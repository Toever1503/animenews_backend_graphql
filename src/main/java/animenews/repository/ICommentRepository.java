package animenews.repository;

import animenews.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICommentRepository extends JpaRepository<Comment, Long> , JpaSpecificationExecutor<Comment> {
}
