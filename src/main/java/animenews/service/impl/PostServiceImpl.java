package animenews.service.impl;

import animenews.Utils.ASCIIConverter;
import animenews.Utils.SecurityUtils;
import animenews.entity.Post;
import animenews.graphql.publishers.PostUpdatePublisher;
import animenews.model.filter.PostFilter;
import animenews.model.PostModel;
import animenews.entity.relationship.post.TagRelationship;
import animenews.entity.relationship.post.TermRelationship;
import animenews.repository.specification.PostSpecification;
import animenews.service.IPostService;
import animenews.repository.PostMetaRepository;
import animenews.repository.PostRepository;
import animenews.repository.TagRepository;
import animenews.repository.TermRepository;
import animenews.repository.relationship.TagRelationshipRepository;
import animenews.repository.relationship.TermRelationshipRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {
    private final PostRepository postRepository;
    private final PostMetaRepository postmetaRepository;
    private TermRelationshipRepository termRelationshipRepository;
    private final TermRepository termRepository;
    private final TagRelationshipRepository tagRelationshipRepository;
    private final TagRepository tagRepository;
    private final PostUpdatePublisher postUpdatePublisher;

    public PostServiceImpl(PostRepository postRepository, PostMetaRepository postmetaRepository, TermRelationshipRepository termRelationshipRepository, TermRepository termRepository, TagRelationshipRepository tagRelationshipRepository, TagRepository tagRepository, PostUpdatePublisher postUpdatePublisher) {
        this.postRepository = postRepository;
        this.postmetaRepository = postmetaRepository;
        this.termRelationshipRepository = termRelationshipRepository;
        this.termRepository = termRepository;
        this.tagRelationshipRepository = tagRelationshipRepository;
        this.tagRepository = tagRepository;
        this.postUpdatePublisher = postUpdatePublisher;
    }

    public Post modelToEntity(PostModel model) {
        if (model == null) return null;
        return Post.builder()
                .id(model.getId())
                .postDate(model.getPostDate())
                .postContent(model.getPostContent())
                .postTitle(model.getPostTitle())
                .postExcerpt(model.getPostExcerpt())
                .postStatus(model.getPostStatus())
                .postName((model.getPostName() == null || model.getPostName().isEmpty()) ? ASCIIConverter.utf8ToAscii(model.getPostTitle()) : model.getPostName())
                .isPinged(model.getIsPinged())
                .postModified(model.getPostModified())
                .commentCount(model.getCommentCount())
                .postMeta_save(new ArrayList<>())
                .build();
    }


    @Transactional
    @Override
    public Post findById(Long id) {
        return this.postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Transactional
    @Override
    public Page<Post> findAll(Pageable page) {
        return this.postRepository.findAll(page);
    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Page<Post> findAll(Specification specs, Pageable page) {
        return null;
    }

    @Override
    public List<Post> findAll(Specification specs) {
        return null;
    }


    @Transactional
    @Override
    public Page<Post> search(String q, Pageable page) {
        return this.postRepository.search(q, page);
    }


    @Transactional
    @Override
    public Post add(PostModel model) {
        Post post = modelToEntity(model);
        Calendar calendar = Calendar.getInstance();
        post.setPostDate(calendar.getTime());
        post.setPostModified(calendar.getTime());
        post.setAuthor(SecurityUtils.getUser());

        post.setPostMeta_save(model.getPostMetas());

        post = this.postRepository.save(post); // save post and get postId
        insertRelationPost(model, post.getId()); // for add relation post meta, tag, term
        return post;
    }

    void insertRelationPost(PostModel model, Long post) {
        // insert new tag if has
        if (model.getTagIds() != null && !model.getTagIds().isEmpty()) {
            model.setTagIds(this.tagRepository.findAllIdsIn(model.getTagIds()));
            this.tagRelationshipRepository.saveAll(
                    model.getTagIds().stream()
                            .map(tagId -> new TagRelationship(null, post, tagId, "post"))
                            .collect(Collectors.toList())
            );
        }
        //insert new term if has
        if (model.getTermIds() != null && !model.getTermIds().isEmpty()) {
            model.setTermIds(this.termRepository.findAllIdsIn(model.getTermIds()));
            this.termRelationshipRepository.saveAll(
                    model.getTermIds().stream()
                            .map(termId -> new TermRelationship(null, post, termId, "post"))
                            .collect(Collectors.toList())
            );
        }
    }


    @Transactional
    @Override
    public Post update(PostModel model) {
        Post post = modelToEntity(model);
        Calendar calendar = Calendar.getInstance();
        post.setPostModified(calendar.getTime());
        post.setAuthor(SecurityUtils.getUser());

        if (model.getPostMetas() != null && !model.getPostMetas().isEmpty())
            post.setPostMeta_save(model.getPostMetas()
                    .stream()
                    .map(meta -> meta.postId(post.getId())).collect(Collectors.toList()));

        // check previous post tag and delete if not exist in tagIds
        if (model.getTagIds() == null || model.getTagIds().isEmpty())
            this.tagRelationshipRepository.deleteAllByObjectIdAndTagBy(post.getId(), "post");
        else {
            List<Long> originalTagIds = this.tagRelationshipRepository.findAllTagIds(model.getTagIds(), post.getId(), "post");
            System.out.println("originalTagIds: " + originalTagIds);
            if (originalTagIds != null && !originalTagIds.isEmpty()) {
                this.tagRelationshipRepository.deleteAllByTagIdNotInAndObjectIdAndTagBy(originalTagIds, post.getId(), "post");
                model.getTagIds().removeAll(originalTagIds);
                System.out.println("new tagids: " + model.getTagIds());
            }
        }
        // check previous term and delete if not exist in termIds
        if (model.getTermIds() == null || model.getTermIds().isEmpty())
            this.termRelationshipRepository.deleteAllByObjectIdAndTermBy(post.getId(), "post");
        else {
            List<Long> originalTermIds = this.termRelationshipRepository.findAllTagIds(model.getTermIds(), post.getId(), "post");
            System.out.println("originalTermIds: " + originalTermIds);
            if (originalTermIds != null && !originalTermIds.isEmpty())
                this.termRelationshipRepository.deleteAllByTermIdNotInAndObjectIdAndTermBy(originalTermIds, post.getId(), "post");
            model.getTermIds().removeAll(originalTermIds);
            System.out.println("new termids: " + model.getTermIds());
        }
        insertRelationPost(model, post.getId()); // for add relation post meta, tag, term
        this.postRepository.save(post);
        postUpdatePublisher.publish(model.getId(), post);
        return post;
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        this.postRepository.deleteById(id);
        this.termRelationshipRepository.deleteAllByObjectIdAndTermBy(id, "post");
        this.tagRelationshipRepository.deleteAllByObjectIdAndTagBy(id, "post");
        return true;
    }

    @Transactional
    @Override
    public boolean deleteByIdS(List<Long> ids) {
        for (Long id : ids) {
            this.deleteById(id);
        }
        return true;
    }

    @Override
    public Page<Post> filterPosts(PostFilter filter, Pageable page) {
        return this.postRepository.findAll(PostSpecification.filter(filter), page);
    }

    @Override
    public Post findPostUrl(String postName, String postDate) {
        return this.postRepository.findPostUrl(postName, postDate).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Override
    public Page<Post> findAllByTermSlug(String term, Pageable page) {
        return this.postRepository.findAllByTagSlug(term, page);
    }

    @Override
    public Page<Post> findAllByTagSlug(String tag, Pageable page) {
        return this.postRepository.findAllByTagSlug(tag, page);
    }

    @Override
    public List<Post> findNextPrevPosts(Long postId, Long termId) {
        Pageable page = PageRequest.of(0, 1); // get first result
        List<Post> data = new ArrayList<Post>(); // empty result

        // get prev post and add if has data
        List<Post> nextPrevPost = this.postRepository.prevPost(postId, termId, page);
        data.add(nextPrevPost.isEmpty() ? null : nextPrevPost.get(0));
        // get next post and add if has data
        nextPrevPost = this.postRepository.nextPost(postId, termId, page);
        data.add(nextPrevPost.isEmpty() ? null : nextPrevPost.get(0));

        return data;
    }
}
