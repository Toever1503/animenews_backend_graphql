package animenews.service.impl;

import animenews.entity.Authority;
import animenews.model.AuthorityModel;
import animenews.service.IAuthorityService;
import animenews.repository.AuthorityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements IAuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    private Authority modelToEntity(AuthorityModel authorityModel) {
        if (authorityModel == null) return null;
        return Authority.builder()
                .id(authorityModel.getId())
                .authorityName(authorityModel.getAuthorityName())
                .authorityLevel(authorityModel.getAuthorityLevel())
                .build();
    }

    @Override
    public Authority findById(Integer id) {
        return this.authorityRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Authority> findAll(Pageable page) {
        return this.authorityRepository.findAll(page);
    }

    @Override
    public List<Authority> findAll() {
        return null;
    }

    @Override
    public Page<Authority> findAll(Specification specs, Pageable page) {
        return null;
    }

    @Override
    public List<Authority> findAll(Specification specs) {
        return this.authorityRepository.findAll(specs);
    }

    @Override
    public Page<Authority> search(String q, Pageable page) {
        return this.authorityRepository.findAllByAuthorityNameLike("%".concat(q).concat("%"), page);
    }

    @Override
    public Authority add(AuthorityModel model) {
        Authority entity = modelToEntity(model);
        if (entity == null) return null;
        return this.authorityRepository.save(entity);
    }

    @Override
    public Authority update(AuthorityModel model) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        this.authorityRepository.deleteById(id);
        return false;
    }

    @Override
    public boolean deleteByIdS(List<Integer> ids) {
        return false;
    }

    @Override
    public List<Authority> findAllByUserId(Long userId) {
        return this.authorityRepository.findAllAuthoritesByUser(userId);
    }
}
