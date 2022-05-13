package animenews.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public interface BaseService<M, T, K> {
    T findById(K id);

    Page<T> findAll(Pageable page);
    List<T> findAll();

    Page<T> findAll(Specification specs, Pageable page);
    List<T> findAll(Specification specs);

    Page<T> search(String q, Pageable page);

    T add(M model);

    T update(M model);

    boolean deleteById(K id);
}
