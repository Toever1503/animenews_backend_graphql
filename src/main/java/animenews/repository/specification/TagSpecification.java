package animenews.repository.specification;

import animenews.entity.Tag;
import animenews.entity.Tag_;
import animenews.model.filter.TagFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TagSpecification {
    public static Specification<Tag> like(String q) {
        return (root, query, cb) -> cb.like(root.get(Tag_.NAME), q);
    }

    public static Specification<Tag> bySlug(String slug) {
        return (root, query, cb) -> cb.equal(root.get(Tag_.slug), slug);
    }

    public static Specification<Tag> byId_In(List<Long> ids) {
        return (root, query, cb) -> root.get(Tag_.id).in(ids);
    }

    public static Specification<Tag> byId_Not_In(List<Long> ids) {
        return (root, query, cb) -> root.get(Tag_.id).in(ids).not();
    }

    public static Specification<Tag> filter(TagFilter filter) {
        List<Specification<Tag>> specs = new ArrayList<>();
        if (filter.getQ() != null)
            specs.add(like("%".concat(filter.getQ().concat("%"))));
        if (filter.getId_in() != null)
            specs.add(byId_In(filter.getId_in()));
        else if (filter.getId_not_in() != null)
            specs.add(byId_Not_In(filter.getId_not_in()));
        if (filter.getSlug() != null)
            specs.add(bySlug(filter.getSlug()));

        Specification<Tag> finalSpec = null;
        for (Specification<Tag> spec : specs) {
            if (finalSpec == null)
                finalSpec = Specification.where(spec);
            else
                finalSpec = finalSpec.and(spec);
        }
        return finalSpec;
    }

}
