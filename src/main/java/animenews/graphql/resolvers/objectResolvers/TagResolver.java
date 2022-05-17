package animenews.graphql.resolvers.objectResolvers;

import animenews.entity.Tag;
import animenews.model.TagModel;
import animenews.model.filter.TagFilter;
import animenews.graphql.connection.CustomConnection;
import animenews.graphql.connection.CustomPageable;
import animenews.repository.specification.TagSpecification;
import animenews.service.ITagService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class TagResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    private final ITagService tagService;

    public TagResolver(ITagService tagService) {
        this.tagService = tagService;
    }

    public Tag tag(Long id) {
        return tagService.findById(id);
    }

    public Tag tag(String slug) {
        return tagService.findBySlug(slug);
    }

    public CustomConnection<Tag> tags(CustomPageable page) {
        return ConnectionQuery.createConnection(this.tagService.findAll(page.toPageable()));
    }

    public CustomConnection<Tag> tagsFilter(TagFilter filter, CustomPageable page) {
        return ConnectionQuery.createConnection(this.tagService.findAll(TagSpecification.filter(filter), page.toPageable()));
    }

    public Tag createTag(TagModel tagModel) {
        tagModel.setId(null);
        return tagService.add(tagModel);
    }

    public Tag updateTag(Long id, TagModel tagModel) {
        tagModel.setId(id);
        return tagService.update(tagModel);
    }

    public boolean deleteTag(Long id) {
        return tagService.deleteById(id);
    }
}
