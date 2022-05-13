package animenews.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String slug;

}