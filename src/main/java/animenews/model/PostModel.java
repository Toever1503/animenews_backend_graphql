package animenews.model;

import animenews.entity.PostMeta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String postTitle;
    private String postExcerpt;
    private String postContent;
    private String postStatus;
    private String postName;

    private Boolean isPinged;
    private Date postDate;
    private Date postModified;
    private Long commentCount;
    private String postFeaturedImage;
    private Long author;


    private List<PostMeta> postMetas;
    private List<Long> tagIds;
    private List<Long> termIds;

}