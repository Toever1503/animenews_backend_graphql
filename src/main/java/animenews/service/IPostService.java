package animenews.service;

import animenews.entity.Post;
import animenews.entity.model.PostFilter;
import animenews.entity.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService extends BaseService<PostModel, Post, Long> {
    Page<Post> filterPosts(PostFilter filter, Pageable page);
    Post findPostUrl(String postName, String postDate);
    Page<Post> findAllByTermSlug(String term, Pageable page);
    Page<Post> findAllByTagSlug(String tag, Pageable page);
    List<Post> findNextPrevPosts(Long postId, Long termId);
}
