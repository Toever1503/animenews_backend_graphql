package animenews.repository;

import animenews.entity.PostMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostMetaRepository extends JpaRepository<PostMeta, Long>, JpaSpecificationExecutor<PostMeta>  {
//    List<PostMeta> findAllByIdIn(List<Long> ids);
    @Transactional
    void deleteAllByPostId(Long postId);

//    @Transactional
//    void deleteAllByIdNotInAndPostId(List<Long> ids, Long postId);

    Page<PostMeta> findAllByMetaKeyLike(String metaKey, Pageable pageable);

    List<PostMeta> findAllByPostId(Long id);
}