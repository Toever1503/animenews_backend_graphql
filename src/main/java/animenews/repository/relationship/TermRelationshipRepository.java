package animenews.repository.relationship;

import animenews.entity.relationship.post.TermRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TermRelationshipRepository extends JpaRepository<TermRelationship, Long> {

    @Transactional
    @Modifying
    void deleteAllByIdNotInAndObjectIdAndTermBy(List<Long> ids, Long postId, String termBy);

    void deleteAllByObjectIdAndTermBy(Long postId, String termBy);

    @Transactional
    @Modifying
    void deleteAllByTermId(Long termId);

}