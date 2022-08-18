package animenews.service;

import animenews.entity.Comment;
import animenews.model.CommentModel;

public interface ICommentService extends IBaseService<CommentModel, Comment, Long>{

    void likeUnlikeComment(Long commentId);
    void dislikeUnDislikeComment(Long commentId);

}
