package animenews.service;

import animenews.config.jwt.JwtLoginResponse;
import animenews.config.jwt.JwtUserLoginModel;
import animenews.entity.User;
import animenews.model.UserModel;
import animenews.model.UserRegisterModel;

public interface IUserService extends IBaseService<UserModel, User, Long> {
    User findByPostId(Long postId);

    User findByUsername(String username);

    User registerAccount(UserRegisterModel userRegisterModel);

    boolean forgotPassword(String username);
    boolean resetPassword(String resetToken, String newPassword);

    JwtLoginResponse login(JwtUserLoginModel userLogin);
}
