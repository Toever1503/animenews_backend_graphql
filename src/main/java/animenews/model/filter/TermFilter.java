package animenews.model.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TermFilter {
    private String q;
    private String slug;
    private List<Long> id_in;
    private List<Long> id_not_in;
}
