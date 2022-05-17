package animenews.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TermModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String slug;

    private String description;
}