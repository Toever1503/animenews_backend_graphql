package animenews.service.impl;

import animenews.entity.PostMeta;
import animenews.model.PostMetaModel;
import animenews.repository.PostMetaRepository;
import animenews.service.IPostMetaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostMetaServiceImpl implements IPostMetaService {
    private final PostMetaRepository postMetaRepository;

    public PostMetaServiceImpl(PostMetaRepository postMetaRepository) {
        this.postMetaRepository = postMetaRepository;
    }

    @Override
    public PostMeta findById(Long id) {
        return null;
    }

    @Override
    public Page<PostMeta> findAll(Pageable page) {
        return null;
    }

    @Override
    public List<PostMeta> findAll() {
        return null;
    }

    @Override
    public Page<PostMeta> findAll(Specification specs, Pageable page) {
        return null;
    }

    @Override
    public List<PostMeta> findAll(Specification specs) {
        return this.findAll(specs);
    }

    @Override
    public Page<PostMeta> search(String q, Pageable page) {
        return null;
    }

    @Override
    public PostMeta add(PostMetaModel model) {
        return null;
    }

    @Override
    public PostMeta update(PostMetaModel model) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean deleteByIdS(List<Long> ids) {
        this.postMetaRepository.deleteAllById(ids);
        return true;
    }

    @Override
    public List<PostMeta> findAllByPostId(Long postId) {
        return this.postMetaRepository.findAllByPostId(postId);
    }
}
