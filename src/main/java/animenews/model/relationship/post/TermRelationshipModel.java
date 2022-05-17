package animenews.model.relationship.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TermRelationshipModel {
    private Long id;

    private Long objectId;

    private Long termId;

    private String termBy;
}