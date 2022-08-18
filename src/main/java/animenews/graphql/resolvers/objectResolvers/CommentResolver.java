package animenews.graphql.resolvers.objectResolvers;

import animenews.entity.Comment;
import animenews.entity.Comment_;
import animenews.graphql.connection.CustomConnection;
import animenews.graphql.connection.CustomPageable;
import animenews.model.CommentModel;
import animenews.service.ICommentService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import static antlr.build.ANTLR.root;

@Component
public class CommentResolver {

    private final ICommentService commentService;

    public CommentResolver(ICommentService commentService) {
        this.commentService = commentService;
    }

    public Comment addComment(CommentModel model){
        return commentService.add(model);
    }

    public Comment updateComment(Long id ,CommentModel model){
        return commentService.update(model);
    }

    public Boolean likeUnlikeComment(Long id){
        return commentService.likeUnlikeComment(id);
    }

    public Boolean dislikeUnDislikeComment(Long id){
        return commentService.dislikeUnDislikeComment(id);
    }

    public CustomConnection<Comment> commentsByPost(Long postId, CustomPageable page){
        Specification<Comment> specification =   (root, query, cb) -> cb.equal(root.get(Comment_.POST), postId);
        return ConnectionQuery.createConnection(this.commentService.findAll(Specification.where(specification), page.toPageable()));
    }
    public CustomConnection<Comment> commentsByParent(Long parentId, CustomPageable page){
        Specification<Comment> specification =   (root, query, cb) -> cb.equal(root.get(Comment_.COMMENT_PARENT), parentId);
        return ConnectionQuery.createConnection(this.commentService.findAll(Specification.where(specification), page.toPageable()));
    }
}
