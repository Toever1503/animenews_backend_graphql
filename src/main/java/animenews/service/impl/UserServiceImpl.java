package animenews.service.impl;

import animenews.entity.User;
import animenews.entity.model.UserModel;
import animenews.service.IUserService;
import animenews.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User modelToEntity(UserModel userModel) {
        if (userModel == null) return null;
        return User.builder()
                .id(userModel.getId())
                .userLogin(userModel.getUserLogin())
                .userPass(userModel.getUserPass())
                .userNicename(userModel.getUserNicename())
                .userEmail(userModel.getUserEmail())
                .userRegistered(userModel.getUserRegistered())
                .userActivationKey(userModel.getUserActivationKey())
                .userStatus(userModel.getUserStatus())
                .displayName(userModel.getDisplayName())
                .userAvatar(userModel.getUserAvatar())
                .userDescription(userModel.getUserDescription())
                .loginFailed(userModel.getLoginFailed())
                .build();

    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Page<User> findAll(Pageable page) {
        return this.userRepository.findAll(page);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Page<User> findAll(Specification specs, Pageable page) {
        return null;
    }

    @Override
    public List<User> findAll(Specification specs) {
        return null;
    }

    @Override
    public Page<User> search(String q, Pageable page) {
        return this.userRepository.findAllByUserLoginOrUserEmailLike("%".concat(q).concat("%"), "%".concat(q).concat("%"), page);
    }

    @Override
    public User add(UserModel model) {
        User user = modelToEntity(model);
        if (user == null) return null;
        return this.userRepository.save(user);
    }

    @Override
    public User update(UserModel model) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            this.userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findByPostId(Long postId) {
        return null;
    }
}
