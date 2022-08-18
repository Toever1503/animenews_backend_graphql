package animenews.repository.specification;

import animenews.entity.Post;
import animenews.entity.Post_;
import animenews.entity.relationship.user.post.TagRelationship_;
import animenews.entity.relationship.user.post.TermRelationship_;
import animenews.model.filter.PostFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PostSpecification {

    public static Specification<Post> like(String q) {
        return (root, query, cb) -> cb.or(cb.like(root.get(Post_.postContent), q), cb.like(root.get(Post_.postTitle), q));
    }


    public static Specification<Post> id_in(List<Long> ids) {
        return (root, query, cb) -> root.get(Post_.id).in(ids);
    }

    public static Specification<Post> id_not_int(List<Long> ids) {
        return (root, query, cb) -> root.get(Post_.id).in(ids).not();
    }

    public static Specification<Post> term_in(List<Long> terms) {
        return (root, query, cb) -> root.join(Post_.postTermFilter.getName()).get(TermRelationship_.termId.getName()).in(terms);
    }

    public static Specification<Post> term_not_in(List<Long> terms) {
        return (root, query, cb) -> root.join(Post_.postTermFilter.getName()).get(TermRelationship_.termId.getName()).in(terms).not();
    }

    public static Specification<Post> tag_in(List<Long> tags) {
        return (root, query, cb) -> root.join(Post_.postTagFilter.getName()).get(TagRelationship_.tagId.getName()).in(tags);
    }

    public static Specification<Post> tag_not_in(List<Long> tags) {
        return (root, query, cb) -> root.join(Post_.postTagFilter.getName()).get(TagRelationship_.tagId.getName()).in(tags).not();
    }

    public static Specification<Post> author_in(List<Long> authors) {
        return (root, query, cb) -> root.get(Post_.author).in(authors);
    }

    public static Specification<Post> author_not_in(List<Long> authors) {
        return (root, query, cb) -> root.get(Post_.author).in(authors).not();
    }

    public static Specification<Post> postDateBetween(Date startDate, Date endDate) {
        return (root, query, cb) -> cb.between(root.get(Post_.postDate), startDate, endDate);
    }

    public static Specification<Post> isPing(boolean ping) {
        return (root, query, cb) -> cb.equal(root.get(Post_.isPinged), ping);
    }

    public static Specification<Post> byPostStatus(String postStatus) {
        return (root, query, cb) -> cb.equal(root.get(Post_.postStatus), postStatus);
    }


    public static Specification filter(PostFilter filter) {
        List<Specification<Post>> specs = new ArrayList<>();

        if (filter.getQ() != null)
            specs.add(like("%".concat(filter.getQ().concat("%")))); // for like post title or content

        if (filter.getPostStatus() != null)
            specs.add(byPostStatus(filter.getPostStatus()));

        if (filter.isPinged())
            specs.add(isPing(filter.isPinged()));

        if (filter.getId_in() != null) {
            specs.add(id_in(filter.getId_in()));
        } else if (filter.getId_not_in() != null)
            specs.add(id_not_int(filter.getId_not_in()));

        if (filter.getCreatedFrom() != null && filter.getCreatedTo() != null) {
            specs.add(postDateBetween(filter.getCreatedFrom(), filter.getCreatedTo()));
        }

        if (filter.getTerm_in() != null)
            specs.add(term_in(filter.getTerm_in()));
        else if (filter.getTerm_not_in() != null)
            specs.add(term_not_in(filter.getTerm_not_in()));

        if (filter.getTag_in() != null)
            specs.add(tag_in(filter.getTag_in()));
        else if (filter.getTag_not_in() != null)
            specs.add(tag_not_in(filter.getTag_not_in()));

        if (filter.getAuthor_in() != null)
            specs.add(author_in(filter.getAuthor_in()));
        else if (filter.getAuthor_not_in() != null)
            specs.add(author_not_in(filter.getAuthor_in()));


        Specification<Post> finalSpec = null;
        for (Specification<Post> spec : specs) {
            if (finalSpec == null) {
                finalSpec = Specification.where(spec);
            } else finalSpec = finalSpec.and(spec);
        }
        return finalSpec;
    }
}
