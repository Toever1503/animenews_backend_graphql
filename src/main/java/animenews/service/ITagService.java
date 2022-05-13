package animenews.service;

import animenews.entity.Tag;
import animenews.entity.model.TagModel;

import java.util.List;

public interface ITagService extends BaseService<TagModel, Tag, Long> {
    List<Tag> tagsByObjectIdAndBy(Long objectId, String by);
}
