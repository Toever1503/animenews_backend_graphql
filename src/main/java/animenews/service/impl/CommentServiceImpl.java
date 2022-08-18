package animenews.service.impl;

import animenews.entity.Comment;
import animenews.model.CommentModel;
import animenews.repository.ICommentRepository;
import animenews.repository.IUserLikeCommentRepository;
import animenews.service.ICommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    private final ICommentRepository commentRepository;
    private final IUserLikeCommentRepository userLikeCommentRepository;

    public CommentServiceImpl(ICommentRepository commentRepository, IUserLikeCommentRepository userLikeCommentRepository) {
        this.commentRepository = commentRepository;
        this.userLikeCommentRepository = userLikeCommentRepository;
    }

    @Override
    public Comment findById(Long id) {
        return null;
    }

    @Override
    public Page<Comment> findAll(Pageable page) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Page<Comment> findAll(Specification specs, Pageable page) {
        return null;
    }

    @Override
    public List<Comment> findAll(Specification specs) {
        return null;
    }

    @Override
    public Page<Comment> search(String q, Pageable page) {
        return null;
    }

    @Override
    public Comment add(CommentModel model) {
        return null;
    }

    @Override
    public Comment update(CommentModel model) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean deleteByIdS(List<Long> ids) {
        return false;
    }

    @Override
    public Boolean likeUnlikeComment(Long commentId) {
        return null;
    }

    @Override
    public Boolean dislikeUnDislikeComment(Long commentId) {
        return null;
    }
}
