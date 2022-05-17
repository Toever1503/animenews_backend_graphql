package animenews.repository.specification;

import animenews.entity.Authority_;
import animenews.entity.User;
import animenews.entity.User_;
import animenews.model.filter.UserFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserSpecification {
    public static Specification<User> like(String q) {
        return (root, query, cb) -> cb.or(
                cb.like(root.get(User_.USER_NICENAME), q),
                cb.like(root.get(User_.DISPLAY_NAME), q),
                cb.like(root.get(User_.USER_DESCRIPTION), q)
        );
    }

    public static Specification<User> byUserLogin(String login) {
        return (root, query, cb) -> cb.equal(root.get(User_.USER_LOGIN), login);
    }

    public static Specification<User> byUserEmail(String email) {
        return (root, query, cb) -> cb.equal(root.get(User_.USER_EMAIL), email);
    }

    public static Specification<User> byUserStatus(boolean status) {
        return (root, query, cb) -> cb.equal(root.get(User_.USER_STATUS), status);
    }

    public static Specification<User> id_in(List<Long> ids) {
        return (root, query, cb) -> root.get(User_.id).in(ids);
    }

    public static Specification<User> id_not_in(List<Long> ids) {
        return (root, query, cb) -> root.get(User_.id).in(ids).not();
    }

    public static Specification<User> byAuthority(String authority) {
        return (root, query, cb) -> root.join(User_.authorityFilter.getName()).get(Authority_.authorityName.getName()).in(Collections.singleton(authority));
    }

    public static Specification<User> filter(UserFilter filter) {
        List<Specification<User>> specs = new ArrayList<>();

        if (filter.getQ() != null)
            specs.add(like("%" + filter.getQ() + "%"));
        if (filter.getId_in() != null)
            specs.add(id_in(filter.getId_in()));
        else if (filter.getId_not_in() != null)
            specs.add(id_not_in(filter.getId_not_in()));
        if (filter.getUserEmail() != null)
            specs.add(byUserEmail(filter.getUserEmail()));
        if (filter.getUserLogin() != null)
            specs.add(byUserLogin(filter.getUserLogin()));
        if (filter.getAuthority() != null)
            specs.add(byAuthority(filter.getAuthority()));
        if (filter.getUserStatus() != null)
            specs.add(byUserStatus(filter.getUserStatus()));

        Specification<User> finalSpec = null;
        for (Specification<User> spec : specs) {
            if (finalSpec == null)
                finalSpec = spec;
            else
                finalSpec = finalSpec.and(spec);
        }
        return finalSpec;
    }
}
