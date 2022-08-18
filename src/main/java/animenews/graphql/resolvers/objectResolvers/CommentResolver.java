package animenews.graphql.resolvers.objectResolvers;

import animenews.service.ICommentService;
import org.springframework.stereotype.Component;

@Component
public class CommentResolver {

    private final ICommentService commentService;

    public CommentResolver(ICommentService commentService) {
        this.commentService = commentService;
    }
}
