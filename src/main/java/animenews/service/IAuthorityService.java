package animenews.service;

import animenews.entity.Authority;
import animenews.model.AuthorityModel;

import java.util.List;

public interface IAuthorityService extends IBaseService<AuthorityModel, Authority, Integer> {
    List<Authority> findAllByUserId(Long userId);
}
