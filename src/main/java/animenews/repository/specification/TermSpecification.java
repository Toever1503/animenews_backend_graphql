package animenews.repository.specification;

import animenews.entity.Term;
import animenews.entity.Term_;
import animenews.model.filter.TermFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TermSpecification {
    public static Specification<Term> like(String q) {
        return (root, query, cb) -> cb.or(cb.like(root.get(Term_.NAME), q), cb.like(root.get(Term_.description), q));
    }

    public static Specification<Term> bySlug(String slug) {
        return (root, query, cb) -> cb.equal(root.get(Term_.slug), slug);
    }

    public static Specification<Term> byId_In(List<Long> ids) {
        return (root, query, cb) -> root.get(Term_.id).in(ids);
    }

    public static Specification<Term> byId_Not_In(List<Long> ids) {
        return (root, query, cb) -> root.get(Term_.id).in(ids).not();
    }

    public static Specification<Term> filter(TermFilter filter) {
        List<Specification<Term>> specs = new ArrayList<>();
        if (filter.getQ() != null)
            specs.add(like("%".concat(filter.getQ().concat("%"))));
        if (filter.getId_in() != null)
            specs.add(byId_In(filter.getId_in()));
        else if (filter.getId_not_in() != null)
            specs.add(byId_Not_In(filter.getId_not_in()));
        if (filter.getSlug() != null)
            specs.add(bySlug(filter.getSlug()));

        Specification<Term> finalSpec = null;
        for (Specification<Term> spec : specs) {
            if (finalSpec == null)
                finalSpec = Specification.where(spec);
            else
                finalSpec = finalSpec.and(spec);
        }
        return finalSpec;
    }

}
