package animenews.service;

import animenews.entity.Term;
import animenews.entity.model.TermModel;

import java.util.List;

public interface ItermService extends BaseService<TermModel, Term, Long> {
    List<Term> termsByObjectIdAndBy(Long objectId, String by);
}
