package animenews.graphql.resolvers.objectResolvers;

import animenews.entity.Post;
import animenews.model.filter.PostFilter;
import animenews.model.PostModel;
import animenews.graphql.connection.CustomConnection;
import animenews.graphql.connection.CustomPageable;
import animenews.service.IPostService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class PostResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    private final IPostService postService;

    public PostResolver(IPostService postService) {
        this.postService = postService;
    }

    @Transactional
    public Post post(Long id) {
        return postService.findById(id);
    }

    @Transactional
    public CustomConnection<Post> posts(CustomPageable page) {
        System.out.println("posts thread id: " + Thread.currentThread().getId());
        return ConnectionQuery.createConnection(this.postService.findAll(page.toPageable()));
    }

    @Transactional
    public CompletableFuture<Post> postUrl(String postName, String postDate, DataFetchingEnvironment env) {
        System.out.println("posts thread id: " + Thread.currentThread().getId());
        String[] key = {postName, postDate};
        return ((DataLoader) env.getDataLoader("postUrl")).load(key);
    }

    // not cache
    @Transactional
    public List<Post> postsByTerm(String term, CustomPageable page) {
        return this.postService.findAllByTermSlug(term, page.toPageable()).getContent();
    }

    // not cache
    @Transactional
    public List<Post> postsByTag(String tag, CustomPageable page) {
        return this.postService.findAllByTagSlug(tag, page.toPageable()).getContent();
    }

    // not cache
    @Transactional
    public CustomConnection<Post> postsFilter(PostFilter filter, CustomPageable page) {
        return ConnectionQuery.createConnection(this.postService.filterPosts(filter, page.toPageable()));
    }

    @Transactional
    public Post createPost(PostModel post) {
        post.setId(null);
        return this.postService.add(post);
    }

    @Transactional
    public Post updatePost(Long id, PostModel post) {
        post.setId(id);
        return this.postService.update(post);
    }

    @Transactional
    public boolean deletePost(Long id) {
        return this.postService.deleteById(id);
    }
}
