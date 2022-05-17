package animenews.graphql.resolvers.fieldResolvers;

import animenews.entity.Post;
import animenews.entity.Tag;
import animenews.entity.Term;
import animenews.graphql.connection.CustomConnection;
import animenews.graphql.connection.CustomPageable;
import animenews.graphql.resolvers.objectResolvers.ConnectionQuery;
import animenews.service.IPostService;
import animenews.service.ITermService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class TermFieldResolver implements GraphQLResolver<Term> {
    private final ITermService termService;
    private final IPostService postService;

    public TermFieldResolver(ITermService termService, IPostService postService) {
        this.termService = termService;
        this.postService = postService;
    }

    public CustomConnection<Post> posts(Term term, CustomPageable page) {
        return ConnectionQuery.createConnection(this.postService.findAllByTagSlug(term.getSlug(), page.toPageable()));
    }
}
