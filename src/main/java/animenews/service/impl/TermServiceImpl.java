package animenews.service.impl;

import animenews.entity.Term;
import animenews.entity.model.TermModel;
import animenews.service.ItermService;
import animenews.repository.TermRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermServiceImpl implements ItermService {
    private final TermRepository termRepository;

    public TermServiceImpl(TermRepository termRepository) {
        this.termRepository = termRepository;
    }

    public Term modelToEntity(TermModel model) {
        if (model == null) return null;
        return Term.builder()
                .id(model.getId())
                .name(model.getName())
                .slug(model.getSlug())
                .description(model.getDescription())
                .build();
    }

    @Override
    public Term findById(Long id) {
        return this.termRepository.findById(id).orElseThrow(() -> new RuntimeException("Term not found"));
    }

    @Override
    public Page<Term> findAll(Pageable page) {
        return this.termRepository.findAll(page);
    }

    @Override
    public List<Term> findAll() {
        return null;
    }

    @Override
    public Page<Term> findAll(Specification specs, Pageable page) {
        return null;
    }

    @Override
    public List<Term> findAll(Specification specs) {
        return null;
    }

    @Override
    public Page<Term> search(String q, Pageable page) {
        return this.termRepository.findBySlugLike("%".concat(q).concat("%"), page);
    }

    @Override
    public Term add(TermModel model) {
        Term term = modelToEntity(model);
        if (term == null) return null;
        return this.termRepository.save(term);
    }

    @Override
    public Term update(TermModel model) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            this.termRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Term> termsByObjectIdAndBy(Long objectId, String by) {
        return this.termRepository.termsByObjectIdAndBy(objectId, by);
    }
}
