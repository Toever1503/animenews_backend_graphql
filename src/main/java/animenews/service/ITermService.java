package animenews.service;

import animenews.entity.Term;
import animenews.model.TermModel;

import java.util.List;

public interface ITermService extends IBaseService<TermModel, Term, Long> {
    List<Term> termsByObjectIdAndBy(Long objectId, String by);

    Term findBySlug(String slug);
}
