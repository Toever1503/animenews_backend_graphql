package animenews.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostFilter {
    private String q;

    private Date createdFrom;
    private Date createdTo;
    private String postStatus;
    private boolean isPinged;

    private List<Long> id_in;
    private List<Long> id_not_in;

    private List<Long> term_in;
    private List<Long> term_not_in;

    private List<Long> tag_in;
    private List<Long> tag_not_in;

    private List<Long> author_in;
    private List<Long> author_not_in;
}
