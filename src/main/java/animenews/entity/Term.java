package animenews.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "terms")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Term implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "slug", nullable = false, length = 200)
    private String slug;

    @Column(name = "description", length = 160)
    private String description;

}