package animenews.repository;

import animenews.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    @Procedure(procedureName = "getAllPostDate")
    List<String> getDatesOfPosts();

    @Query("select p from Post p join TermRelationship tr on p.id = tr.objectId join Term as t on t.id = tr.termId where t.slug=?1 and tr.termBy='post' order by p.postDate desc")
    Page<Post> findAllByTermSlug(String termSlug, Pageable page);

    @Query("select p from Post p join TagRelationship tr on p.id = tr.objectId join Tag as t on t.id = tr.tagId where t.slug=?1 and tr.tagBy='post' order by p.postDate desc")
    Page<Post> findAllByTagSlug(String tagSlug, Pageable page);

    @Query(value = "select * from posts where post_name =:postName and CAST(post_date AS date)  =:d", nativeQuery = true)
    Optional<Post> findPostUrl(@Param("postName") String guid, @Param("d") String date);

    @Query("select p from Post p where p.postTitle like %?1% or p.postContent like %?1%")
    Page<Post> search(String q, Pageable page);

    @Query("select p from Post p join TermRelationship tr on p.id = tr.objectId where tr.termBy='post' and tr.objectId > ?1 and tr.termId=?2")
    List<Post> nextPost(Long postId, Long termId, Pageable page);

    @Query("select p from Post p join TermRelationship tr on p.id = tr.objectId where tr.termBy='post' and tr.objectId < ?1 and tr.termId=?2")
    List<Post> prevPost(Long postId, Long termId, Pageable page);
}