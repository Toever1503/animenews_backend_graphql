package animenews.entity.relationship.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tag_relationships")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "object_id", nullable = false)
    private Long objectId;

    @Column(name = "tag_id", nullable = false)
    private Long tagId;

    @Column(name = "tag_by", nullable = false)
    private String tagBy;

}