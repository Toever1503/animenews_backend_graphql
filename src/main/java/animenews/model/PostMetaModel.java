package animenews.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostMetaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long MetaId;

    private String metaKey;

    private String metaValue;

}