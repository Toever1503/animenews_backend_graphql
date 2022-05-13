package animenews.graphql.queryMutation.fieldResolver;

import animenews.entity.*;
import animenews.entity.PostMeta_;
import animenews.service.*;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostFieldsResolver implements GraphQLResolver<Post> {
    private final ITagService tagService;
    private final ItermService termService;
    private final IPostMetaService postMetaService;
    private final IPostService postService;
    private final IUserService userService;

    public PostFieldsResolver(ITagService tagService, ItermService termService, IPostMetaService postMetaService, IPostService postService, IUserService userService) {
        this.tagService = tagService;
        this.termService = termService;
        this.postMetaService = postMetaService;
        this.postService = postService;
        this.userService = userService;
    }

    List<PostMeta> postMetas(Post post) {
        return this.postMetaService.findAll(Specification.where((root, query, cb) -> cb.equal(root.get(PostMeta_.postId.getName()), post.getId())));
    }

    List<Tag> postTags(Post post) {
        return this.tagService.tagsByObjectIdAndBy(post.getId(), "post");
    }

    List<Term> postTerms(Post post) {
        return this.termService.termsByObjectIdAndBy(post.getId(), "post");
    }

    List<Post> nextPrevPost(Post post, Long termSlug) {
        return this.postService.findNextPrevPosts(post.getId(), termSlug);
    }
}
