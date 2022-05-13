package animenews.entity.model.relationship.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TagRelationshipModel {
    private Long id;

    private Long objectId;

    private Long tagId;

    private String tagBy;

}