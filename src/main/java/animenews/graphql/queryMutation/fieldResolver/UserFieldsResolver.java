package animenews.graphql.queryMutation.fieldResolver;

import animenews.entity.Authority;
import animenews.entity.Authority_;
import animenews.entity.User;
import animenews.entity.User_;
import animenews.repository.AuthorityRepository;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFieldsResolver implements GraphQLResolver<User> {
    private final AuthorityRepository authorityRepository;

    public UserFieldsResolver(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    List<Authority> userRoles(User user) {
        return authorityRepository.findAll(Specification.where(
                (root, query, cb) ->
                        cb.equal(root.get(Authority_.USERS).get(User_.ID), user.getId())
        ));
    }

}
