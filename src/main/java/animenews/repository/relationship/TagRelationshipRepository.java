package animenews.repository.relationship;

import animenews.entity.relationship.user.post.TagRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TagRelationshipRepository extends JpaRepository<TagRelationship, Long> {

//    @Transactional
    @Modifying
    void deleteAllByObjectIdAndTagBy(Long tagId, String tagBy);

    @Transactional
    @Modifying
    void deleteAllByTagId(Long tagId);

    @Query("select tr.tagId from TagRelationship tr where tr.tagId in ?1 and tr.objectId = ?2 and tr.tagBy = ?3")
    List<Long> findAllTagIds(List<Long> tagIds, Long objectId, String tagBy);

    @Transactional
//    @Modifying
//    @Query("delete from TagRelationship tr where tr.tagId not in ?1 and tr.objectId = ?2 and tr.tagBy = ?3")
    void deleteAllByTagIdNotInAndObjectIdAndTagBy(List<Long> termIds, Long objectId, String tagBy);
}