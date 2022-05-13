package animenews.service.impl;

import animenews.entity.Tag;
import animenews.entity.model.TagModel;
import animenews.service.ITagService;
import animenews.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements ITagService {
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag modelToEntity(TagModel model) {
        if (model == null) return null;
        return Tag.builder()
                .id(model.getId())
                .name(model.getName())
                .slug(model.getSlug())
                .build();
    }

    @Override
    public Tag findById(Long id) {
        return this.tagRepository.findById(id).orElseThrow(() -> new RuntimeException("Tag not found"));
    }

    @Override
    public Page<Tag> findAll(Pageable page) {
        return this.tagRepository.findAll(page);
    }

    @Override
    public List<Tag> findAll() {
        return null;
    }

    @Override
    public Page<Tag> findAll(Specification specs, Pageable page) {
        return null;
    }

    @Override
    public List<Tag> findAll(Specification specs) {
        return null;
    }

    @Override
    public Page<Tag> search(String q, Pageable page) {
        return this.tagRepository.findTop5ByNameLike("%".concat(q).concat("%"), page);
    }

    @Override
    public Tag add(TagModel model) {
        Tag tag = modelToEntity(model);
        if (tag == null) return null;
        return this.tagRepository.save(tag);
    }

    @Override
    public Tag update(TagModel model) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            this.tagRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Tag> tagsByObjectIdAndBy(Long objectId, String by) {
        return this.tagRepository.tagsByObjectIdAndBy(objectId, by);
    }
}
