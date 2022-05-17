package animenews.service;

import animenews.entity.PostMeta;
import animenews.model.PostMetaModel;

import java.util.List;

public interface IPostMetaService extends IBaseService<PostMetaModel, PostMeta, Long> {
    List<PostMeta> findAllByPostId(Long postId);
}
