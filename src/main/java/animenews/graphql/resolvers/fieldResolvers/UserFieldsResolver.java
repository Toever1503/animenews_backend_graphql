package animenews.graphql.resolvers.fieldResolvers;

import animenews.entity.Authority;
import animenews.entity.User;
import animenews.service.IAuthorityService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFieldsResolver implements GraphQLResolver<User> {
    private final IAuthorityService authorityService;

    public UserFieldsResolver(IAuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    List<Authority> authorities(User user) {
        return authorityService.findAllByUserId(user.getId());
    }

}
