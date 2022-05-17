package animenews.service;

import animenews.entity.Tag;
import animenews.model.TagModel;

import java.util.List;

public interface ITagService extends IBaseService<TagModel, Tag, Long> {
    List<Tag> tagsByObjectIdAndBy(Long objectId, String by);

    Tag findBySlug(String slug);

}
