package animenews.repository.relationship;

import animenews.entity.relationship.post.TermRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TermRelationshipRepository extends JpaRepository<TermRelationship, Long> {

    @Transactional
    @Modifying
//    @Query("delete from TermRelationship tr where tr.termId not in ?1 and tr.objectId = ?2 and tr.termBy = ?3")
    void deleteAllByTermIdNotInAndObjectIdAndTermBy(List<Long> termIds, Long postId, String termBy);

    void deleteAllByObjectIdAndTermBy(Long postId, String termBy);

    @Query("select tr.termId from TermRelationship tr where tr.termId in ?1 and tr.objectId = ?2 and tr.termBy = ?3")
    List<Long> findAllTagIds(List<Long> termIds, Long postId, String termBy);

    @Transactional
    @Modifying
    void deleteAllByTermId(Long termId);

}