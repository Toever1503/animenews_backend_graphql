package animenews.graphql.connection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomOrder {
    private Sort.Direction direction;
    private String property;

    public Sort.Order toOrder() {
        return new Sort.Order(direction, property);
    }
}
