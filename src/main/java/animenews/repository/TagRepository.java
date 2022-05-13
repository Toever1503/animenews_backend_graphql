package animenews.repository;

import animenews.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {

    Optional<Tag> findBySlug(String slug);

    Page<Tag> findTop5ByNameLike(String tagName, Pageable page);

    @Query(value="SELECT t from Tag t join TagRelationship tr on t.id = tr.tagId WHERE tr.objectId = ?1 and tr.tagBy = ?2")
    List<Tag> tagsByObjectIdAndBy(Long objectId, String by);
}