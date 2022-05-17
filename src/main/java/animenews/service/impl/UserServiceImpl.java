package animenews.service.impl;

import animenews.Utils.SecurityUtils;
import animenews.config.jwt.JwtLoginResponse;
import animenews.config.jwt.JwtProvider;
import animenews.config.jwt.JwtUserLoginModel;
import animenews.entity.User;
import animenews.model.UserModel;
import animenews.model.UserRegisterModel;
import animenews.repository.AuthorityRepository;
import animenews.service.CustomUserDetail;
import animenews.service.IMailService;
import animenews.service.IUserService;
import animenews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final String resetPasswordHost;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final IMailService mailService;

    public UserServiceImpl(UserRepository userRepository,
                           AuthorityRepository authorityRepository,
                           PasswordEncoder passwordEncoder, @Value("${host.frontend.account.resetPassword}") String resetPasswordHost, JwtProvider jwtProvider, AuthenticationManager authenticationManager, IMailService mailService) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.resetPasswordHost = resetPasswordHost;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.mailService = mailService;
        SecurityUtils.setUser(this.userRepository.findById(1l).orElse(null));
    }

    public User modelToEntity(UserModel userModel) {
        if (userModel == null) throw new RuntimeException("UserModel is null");
        return User.builder()
                .id(userModel.getId())
                .userLogin(userModel.getUserLogin())
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

    @Transactional
    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    @Override
    public Page<User> findAll(Pageable page) {
        return this.userRepository.findAll(page);
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return null;
    }

    @Transactional
    @Override
    public Page<User> findAll(Specification specs, Pageable page) {
        return this.userRepository.findAll(specs, page);
    }

    @Transactional
    @Override
    public List<User> findAll(Specification specs) {
        return null;
    }

    @Transactional
    @Override
    public Page<User> search(String q, Pageable page) {
        return this.userRepository.findAllByUserLoginOrUserEmailLike("%".concat(q).concat("%"), "%".concat(q).concat("%"), page);
    }

    @Transactional
    @Override
    public User add(UserModel model) {
        User user = modelToEntity(model);
        user.setUserPass(passwordEncoder.encode(model.getUserPass()));
        user.setUserRegistered(Calendar.getInstance().getTime());
        setAuthority(user, model.getAuthorityLevel());
        return this.userRepository.save(user);
    }

    void setAuthority(User user, Integer authorityLevel) {
        user.setAuthorityFilter(this.authorityRepository.findAllByAuthorityLevelGreaterThanEqual(authorityLevel));
        if (user.getAuthorityFilter().isEmpty())
            user.setAuthorityFilter(Collections
                    .singleton(this.authorityRepository.findAuthorityByAndAuthorityName(SecurityUtils.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("User role not found"))));
        System.out.println("user auths: " + user.getAuthorityFilter().size());
    }

    @Transactional
    @Override
    public User update(UserModel model) {
        User original = this.findById(model.getId());
        if (model.getUserPass() != null)
            original.setUserPass(passwordEncoder.encode(model.getUserPass()));
        original.setUserEmail(model.getUserEmail());
        original.setUserNicename(model.getUserNicename());
        original.setDisplayName(model.getDisplayName());
        original.setUserAvatar(model.getUserAvatar());
        original.setUserDescription(model.getUserDescription());
        original.setUserStatus(model.getUserStatus());
        setAuthority(original, model.getAuthorityLevel());
        return this.userRepository.save(original);
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        this.userRepository.deleteById(id);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteByIdS(List<Long> ids) {
        ids.forEach(this::deleteById);
        return true;
    }

    @Transactional
    @Override
    public User findByPostId(Long postId) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findFirstByUserLoginOrUserEmail(username, username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    @Override
    public User registerAccount(UserRegisterModel userRegisterModel) {
        User user = User.builder()
                .userLogin(userRegisterModel.getUserLogin())
                .userEmail(userRegisterModel.getUserEmail())
                .userPass(passwordEncoder.encode(userRegisterModel.getUserPass()))
                .userNicename(userRegisterModel.getUserNicename())
                .userStatus(false)
                .authorityFilter(Collections.emptySet())
                .build();
        return this.userRepository.save(user);
    }

    @Transactional
    @Override
    public boolean forgotPassword(String username) {
        User original = this.findByUsername(username);
        original.setUserActivationKey(Math.round(100000 + Math.random() * 999999));
        this.userRepository.save(original);
        String resetToken = jwtProvider.generateToken(username.concat("-").concat(original.getUserActivationKey().toString()), JwtProvider.JWT_TOKEN_VALIDITY / 2);
        new Thread(() -> {
            try {
                Map<String, Object> model = new HashMap<>();
                model.put("username", original.getUserLogin());
                model.put("resetUrl", this.resetPasswordHost.concat(resetToken));
                this.mailService.sendMail("reset_password.html", original.getUserEmail(), "Reset Password", model);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }).start();
        return true;
    }

    @Transactional
    @Override
    public boolean resetPassword(String resetToken, String newPassword) {
        jwtProvider.isTokenExpired(resetToken);
        String[] subject = jwtProvider.getUsernameFromToken(resetToken).split("-");
        if (subject.length < 2) throw new RuntimeException("Invalid reset token");
        User original = this.findByUsername(subject[0]);
        if (!original.getUserActivationKey().toString().equalsIgnoreCase(subject[1]))
            throw new RuntimeException("Invalid reset token");
        original.setUserPass(passwordEncoder.encode(newPassword));
        original.setUserActivationKey(0l);
        this.userRepository.save(original);
        return true;
    }

    @Override
    public JwtLoginResponse login(JwtUserLoginModel userLogin) {
        UserDetails userDetail = new CustomUserDetail(this.findByUsername(userLogin.getUsername()));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetail, userLogin.getPassword(), userDetail.getAuthorities()));
        return JwtLoginResponse.builder()
                .token(jwtProvider.generateToken(userLogin.getUsername(), userLogin.isRemember() ? 86400 * 7 : 0l))
                .authorities(userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .build();
    }


}
