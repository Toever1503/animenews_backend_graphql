package animenews.graphql.connection;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
public class CustomPageable {
    private int page;
    private int size;
    private CustomSort sort;

    public CustomPageable(int page, int size, CustomSort sort) {
        this.page = page;
        this.size = size > 50 ? 50 : size;
        this.sort = sort;
    }

    public Pageable toPageable() {
        if (sort == null) return PageRequest.of(page, size);
        return PageRequest.of(page, size).withSort(sort.toSort());
    }
}
