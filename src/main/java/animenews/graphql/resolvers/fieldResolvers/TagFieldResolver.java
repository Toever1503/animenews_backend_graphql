package animenews.graphql.resolvers.fieldResolvers;

import animenews.entity.Post;
import animenews.entity.Tag;
import animenews.graphql.connection.CustomConnection;
import animenews.graphql.connection.CustomPageable;
import animenews.graphql.resolvers.objectResolvers.ConnectionQuery;
import animenews.service.IPostService;
import animenews.service.ITagService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class TagFieldResolver implements GraphQLResolver<Tag>  {
    private final ITagService tagService;
    private final IPostService postService;

    public TagFieldResolver(ITagService tagService, IPostService postService) {
        this.tagService = tagService;
        this.postService = postService;
    }

    public CustomConnection<Post> posts(Tag tag, CustomPageable page) {
        return ConnectionQuery.createConnection(this.postService.findAllByTagSlug(tag.getSlug(), page.toPageable()));
    }
}
