package animenews.graphql.resolvers.fieldResolvers;

import animenews.entity.*;
import animenews.service.*;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostFieldsResolver implements GraphQLResolver<Post> {
    private final ITagService tagService;
    private final ITermService termService;
    private final IPostMetaService postMetaService;
    private final IPostService postService;
    private final IUserService userService;

    public PostFieldsResolver(ITagService tagService, ITermService termService, IPostMetaService postMetaService, IPostService postService, IUserService userService) {
        this.tagService = tagService;
        this.termService = termService;
        this.postMetaService = postMetaService;
        this.postService = postService;
        this.userService = userService;
    }

    List<PostMeta> postMetas(Post post, DataFetchingEnvironment env) {
        System.out.println("post metas thread id: " + Thread.currentThread().getId());
        return env.getOperationDefinition().getOperation().name().equalsIgnoreCase("mutation")
                ? post.getPostMeta_save()
                : this.postMetaService.findAllByPostId(post.getId());
    }

    List<Tag> postTags(Post post) {
        System.out.println("posts tag thread id: " + Thread.currentThread().getId());
        return this.tagService.tagsByObjectIdAndBy(post.getId(), "post");
    }

    List<Term> postTerms(Post post) {
        System.out.println("posts term thread id: " + Thread.currentThread().getId());
        return this.termService.termsByObjectIdAndBy(post.getId(), "post");
    }

    List<Post> nextPrevPost(Post post, Long termSlug) {
        System.out.println("posts next prev thread id: " + Thread.currentThread().getId());
        return this.postService.findNextPrevPosts(post.getId(), termSlug);
    }
}
