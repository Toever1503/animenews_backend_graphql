package animenews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "post_title", nullable = false)
    private String postTitle;

    @Column(name = "post_excerpt")
    private String postExcerpt;

    @Lob
    @Column(name = "post_content", nullable = false)
    private String postContent;

    @Column(name = "post_status", nullable = false)
    private String postStatus;

    @Column(name = "post_name", nullable = false)
    private String postName;

    @Column(name = "is_pinged")
    private Boolean isPinged;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_date")
    private Date postDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_modified")
    private Date postModified;

    @Column(name = "comment_count")
    private Long commentCount;

    @Column(name = "post_featured_image")
    private String postFeaturedImage;

    @ManyToOne
    @JoinColumn(name = "post_author", nullable = false)
    private User author;

//    @Transient
//    private List<PostMeta> postMetas;
//
//    @Transient
//    private List<Term> postTerms;
//
//    @Transient
//    private List<Tag> postTags;

//    @Transient
//    private Post next;
//    @Transient
//    private Post prev;
}