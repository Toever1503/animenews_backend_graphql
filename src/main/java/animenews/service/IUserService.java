package animenews.service;

import animenews.entity.User;
import animenews.entity.model.UserModel;

public interface IUserService extends BaseService<UserModel, User, Long> {
    User findByPostId(Long postId);
}
