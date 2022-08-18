package animenews.model;

import animenews.entity.Comment;
import animenews.entity.Post;
import animenews.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentModel {
    private Long id;

    private String content;

    private Long commentParent;

    private Long post;

}
