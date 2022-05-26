package animenews.graphql.resolvers.objectResolvers;

import animenews.config.jwt.JwtLoginResponse;
import animenews.config.jwt.JwtProvider;
import animenews.config.jwt.JwtUserLoginModel;
import animenews.entity.User;
import animenews.model.UserModel;
import animenews.model.UserRegisterModel;
import animenews.model.filter.UserFilter;
import animenews.graphql.connection.CustomConnection;
import animenews.graphql.connection.CustomPageable;
import animenews.repository.specification.UserSpecification;
import animenews.service.IUserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class UserResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    private final IUserService userService;

    public UserResolver(IUserService userService) {
        this.userService = userService;
    }

    public User user(Long id) {
        return userService.findById(id);
    }

    public User user(String username) {
        return userService.findByUsername(username);
    }

    public CustomConnection<User> users(CustomPageable page) {
        return ConnectionQuery.createConnection(userService.findAll(page.toPageable()));
    }

    public CustomConnection<User> usersFilter(UserFilter filter, CustomPageable page) {
        return ConnectionQuery.createConnection(userService.findAll(UserSpecification.filter(filter), page.toPageable()));

    }

    public User createUser(UserModel userModel, String userPass) {
        userModel.setId(null);
        userModel.setUserPass(userPass);
        return userService.add(userModel);
    }

    public User updateUser(Long id, UserModel userModel) {
        userModel.setId(id);
        return userService.update(userModel);
    }

    public boolean deleteUser(Long id) {
        return userService.deleteById(id);
    }

    public boolean forgotPassword(String username) {
        return userService.forgotPassword(username);
    }

    public boolean resetPassword(String resetToken, String newPassword) {
        return userService.resetPassword(resetToken, newPassword);
    }

    public User registerAccount(UserRegisterModel userRegister) {
        return this.userService.registerAccount(userRegister);
    }

    public JwtLoginResponse login(JwtUserLoginModel userLogin) {
        return this.userService.login(userLogin);
    }
}
