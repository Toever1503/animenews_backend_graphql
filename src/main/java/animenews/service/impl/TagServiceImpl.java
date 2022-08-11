package animenews.service.impl;

import animenews.Utils.ASCIIConverter;
import animenews.entity.Tag;
import animenews.graphql.exception.CustomGraphqlException;
import animenews.model.TagModel;
import animenews.repository.relationship.TagRelationshipRepository;
import animenews.service.ITagService;
import animenews.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements ITagService {
    private final TagRepository tagRepository;
    private final TagRelationshipRepository tagRelationshipRepository;

    public TagServiceImpl(TagRepository tagRepository, TagRelationshipRepository tagRelationshipRepository) {
        this.tagRepository = tagRepository;
        this.tagRelationshipRepository = tagRelationshipRepository;
    }

    public Tag modelToEntity(TagModel model) {
        if (model == null) throw new RuntimeException("Tag model is null");
        return Tag.builder()
                .id(model.getId())
                .name(model.getName())
                .slug(model.getSlug()==null ? ASCIIConverter.utf8ToAscii(model.getName()) : model.getSlug())
                .build();
    }

    @Transactional
    @Override
    public Tag findById(Long id) {
        return this.tagRepository.findById(id).orElseThrow(() -> new CustomGraphqlException("Tag not found"));
    }
    @Transactional
    @Override
    public Page<Tag> findAll(Pageable page) {
        return this.tagRepository.findAll(page);
    }
    @Transactional
    @Override
    public List<Tag> findAll() {
        return null;
    }
    @Transactional
    @Override
    public Page<Tag> findAll(Specification specs, Pageable page) {
        return this.tagRepository.findAll(specs, page);
    }
    @Transactional
    @Override
    public List<Tag> findAll(Specification specs) {
        return null;
    }
    @Transactional
    @Override
    public Page<Tag> search(String q, Pageable page) {
        return this.tagRepository.findTop5ByNameLike("%".concat(q).concat("%"), page);
    }
    @Transactional
    @Override
    public Tag add(TagModel model) {
        Tag tag = modelToEntity(model);
        if (tag == null) return null;
        return this.tagRepository.save(tag);
    }
    @Transactional
    @Override
    public Tag update(TagModel model) {
        return add(model);
    }
    @Transactional
    @Override
    public boolean deleteById(Long id) {
        this.tagRepository.deleteById(id);
        this.tagRelationshipRepository.deleteAllByTagId(id);
        return true;
    }
    @Transactional
    @Override
    public boolean deleteByIdS(List<Long> ids) {
        for(Long id : ids) {
            this.deleteById(id);
        }
        return true;
    }
    @Transactional
    @Override
    public List<Tag> tagsByObjectIdAndBy(Long objectId, String by) {
        return this.tagRepository.tagsByObjectIdAndBy(objectId, by);
    }
    @Transactional
    @Override
    public Tag findBySlug(String slug) {
        return this.tagRepository.findBySlug(slug).orElseThrow(() -> new CustomGraphqlException("Tag not found"));
    }
}
