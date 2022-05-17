package animenews.service.impl;

import animenews.Utils.ASCIIConverter;
import animenews.entity.Term;
import animenews.model.TermModel;
import animenews.repository.relationship.TermRelationshipRepository;
import animenews.service.ITermService;
import animenews.repository.TermRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TermServiceImpl implements ITermService {
    private final TermRepository termRepository;
    private final TermRelationshipRepository termRelationshipRepository;

    public TermServiceImpl(TermRepository termRepository, TermRelationshipRepository termRelationshipRepository) {
        this.termRepository = termRepository;
        this.termRelationshipRepository = termRelationshipRepository;
    }

    public Term modelToEntity(TermModel model) {
        if (model == null) return null;
        return Term.builder()
                .id(model.getId())
                .name(model.getName())
                .slug(model.getSlug() == null ? ASCIIConverter.utf8ToAscii(model.getName()) : model.getSlug())
                .description(model.getDescription())
                .build();
    }

    @Transactional
    @Override
    public Term findById(Long id) {
        return this.termRepository.findById(id).orElseThrow(() -> new RuntimeException("Term not found"));
    }

    @Transactional
    @Override
    public Page<Term> findAll(Pageable page) {
        return this.termRepository.findAll(page);
    }

    @Transactional
    @Override
    public List<Term> findAll() {
        return null;
    }

    @Transactional
    @Override
    public Page<Term> findAll(Specification specs, Pageable page) {
        return this.termRepository.findAll(specs, page);
    }

    @Transactional
    @Override
    public List<Term> findAll(Specification specs) {
        return null;
    }

    @Transactional
    @Override
    public Page<Term> search(String q, Pageable page) {
        return this.termRepository.findBySlugLike("%".concat(q).concat("%"), page);
    }

    @Transactional
    @Override
    public Term add(TermModel model) {
        Term term = modelToEntity(model);
        if (term == null) return null;
        return this.termRepository.save(term);
    }

    @Transactional
    @Override
    public Term update(TermModel model) {
        return add(model);
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        this.termRepository.deleteById(id);
        this.termRelationshipRepository.deleteAllByTermId(id);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteByIdS(List<Long> ids) {
        ids.forEach(this::deleteById);
        return true;
    }

    @Transactional
    @Override
    public List<Term> termsByObjectIdAndBy(Long objectId, String by) {
        return this.termRepository.termsByObjectIdAndBy(objectId, by);
    }

    @Transactional
    @Override
    public Term findBySlug(String slug) {
        return this.termRepository.findBySlug(slug).orElseThrow(() -> new RuntimeException("Term not found"));
    }
}
