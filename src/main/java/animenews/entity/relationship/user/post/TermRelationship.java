package animenews.entity.relationship.user.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "term_relationships")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TermRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "term_id")
    private Long termId;

    @Column(name = "term_by", nullable = false, length = 50)
    private String termBy;
}