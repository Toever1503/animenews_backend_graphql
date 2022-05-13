package animenews.repository;

import animenews.entity.Term;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TermRepository extends JpaRepository<Term, Long>, JpaSpecificationExecutor<Term> {
    Optional<Term> findBySlug(String slug);

    @Query(value = "SELECT t from Term t join TermRelationship tr on t.id = tr.termId WHERE tr.objectId = ?1 and tr.termBy = ?2")
    List<Term> termsByObjectIdAndBy(Long postId, String by);

    Page<Term> findBySlugLike(String slug, Pageable pageable);
}