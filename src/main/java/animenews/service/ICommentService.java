package animenews.service;

import animenews.entity.Comment;
import animenews.model.CommentModel;

public interface ICommentService extends IBaseService<CommentModel, Comment, Long>{

    Boolean likeUnlikeComment(Long commentId);
    Boolean dislikeUnDislikeComment(Long commentId);

}
