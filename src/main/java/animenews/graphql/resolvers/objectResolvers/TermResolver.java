package animenews.graphql.resolvers.objectResolvers;

import animenews.entity.Term;
import animenews.model.TermModel;
import animenews.model.filter.TagFilter;
import animenews.graphql.connection.CustomConnection;
import animenews.graphql.connection.CustomPageable;
import animenews.repository.specification.TagSpecification;
import animenews.service.ITermService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class TermResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    private final ITermService termService;

    public TermResolver(ITermService termService) {
        this.termService = termService;
    }

    public Term term(Long id) {
        return termService.findById(id);
    }

    public Term term(String slug) {
        return termService.findBySlug(slug);
    }

    public CustomConnection<Term> terms(CustomPageable page) {
        return ConnectionQuery.createConnection(this.termService.findAll(page.toPageable()));
    }

    public CustomConnection<Term> termsFilter(TagFilter filter, CustomPageable page) {
        return ConnectionQuery.createConnection(this.termService.findAll(TagSpecification.filter(filter), page.toPageable()));
    }

    public Term createTerm(TermModel termModel) {
        termModel.setId(null);
        return termService.add(termModel);
    }

    public Term updateTerm(Long id, TermModel termModel) {
        termModel.setId(id);
        return termService.update(termModel);
    }

    public boolean deleteTerm(Long id) {
        return termService.deleteById(id);
    }
}
