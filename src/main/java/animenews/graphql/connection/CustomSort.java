package animenews.graphql.connection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomSort {
    List<CustomOrder> orders;
    public Sort toSort() {
        return Sort.by(this.orders.stream().map(CustomOrder::toOrder).collect(Collectors.toList()));
    }

    public String getProperties() {
        StringBuilder sb = new StringBuilder();
        for (CustomOrder order : orders) {
            sb.append(order.getProperty()).append(",");
        }
        return sb.toString();
    }
}
