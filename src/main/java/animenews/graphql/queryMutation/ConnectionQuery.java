package animenews.graphql.queryMutation;

import animenews.graphql.connection.CustomConnection;
import animenews.graphql.connection.DefaultCustomConnection;
import animenews.graphql.connection.DefaultCustomEdge;
import animenews.graphql.connection.DefaultCustomPageInfo;
import graphql.relay.Edge;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ConnectionQuery {


    public static Pageable extractPageable(DataFetchingEnvironment env) {
        int perPage = env.getArgument("perPage");
        System.out.println("getting connection: " + perPage);
        return PageRequest.of(env.getArgument("page"), perPage > 50 ? 50 : perPage);
    }


    public static Map<String, Object> extractParentId(DataFetchingEnvironment env) {
        if (env.getExecutionStepInfo().getParent().hasParent())
            return env.getExecutionStepInfo().getParent().getArguments();
        return null;
    }

    public static DefaultCustomPageInfo extractPageInfo(Page page) {
        return new DefaultCustomPageInfo(page.hasNext(),
                page.hasPrevious(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements());
    }

    public static DefaultCustomConnection<?> createConnection(Page<?> page) {
        return new DefaultCustomConnection(new Edges<>(page.getContent()).edges, extractPageInfo(page));
    }

    // custom for dynamic edges
    public static class Edges<T> {
        private List<Edge<T>> edges;

        public Edges(List<T> data) {
            this.edges = data.stream().map(element -> new DefaultCustomEdge<T>(element)).collect(Collectors.toList());
        }

        public List<Edge<T>> getEdges() {
            return edges;
        }
    }

//    public CustomConnection<Video> getVideoConnection(DataFetchingEnvironment env) {
//        Pageable pageable = extractPageable(env);
//        Page<Video> page = this.videoRepository.findAll(pageable);
//        return createConnection(page);
//    }
//
//    public CustomConnection<Staff> getStaffConnection(DataFetchingEnvironment env) {
//        Map<String, Object> parentId = extractParentId(env);
//        Pageable pageable = extractPageable(env);
//        Page<Staff> page = null;
//        if (env.getArgument("q") == null)
//            page = this.staffRepository.findAllStaffsByVideoId(Long.valueOf(parentId.get("id").toString()), pageable);
//        else
//            page = this.staffRepository.searchByAndVideoId(env.getArgument("q"), Long.valueOf(parentId.get("id").toString()), pageable);
//        return createConnection(page);
//    }
//
//    public CustomConnection<Studio> getStudioConnection(DataFetchingEnvironment env) {
//        Map<String, Object> parentId = extractParentId(env);
//        Pageable pageable = extractPageable(env);
//        Page<Studio> page = null;
//        if (env.getArgument("q") == null)
//            page = this.studioRepository.findAllStudiosByVideoId(Long.valueOf(parentId.get("id").toString()), pageable);
//        else
//            page = this.studioRepository.searchByAndVideoId(env.getArgument("q"), Long.valueOf(parentId.get("id").toString()), pageable);
//        return createConnection(page);
//    }
//
//    public CustomConnection<Character> getCharacterConnection(DataFetchingEnvironment env) {
//        Map<String, Object> parentId = extractParentId(env);
//        Pageable pageable = extractPageable(env);
//        Page<Character> page = null;
//        if (env.getArgument("q") == null)
//            page = this.characterRepository.findAllCharactersByVideoId(Long.valueOf(parentId.get("id").toString()), pageable);
//        else
//            page = this.characterRepository.searchByAndVideoId(env.getArgument("q"), Long.valueOf(parentId.get("id").toString()), pageable);
//        return createConnection(page);
//    }
//
//    public CustomConnection<Category> getCategoryConnection(DataFetchingEnvironment env) {
//        Map<String, Object> parentId = extractParentId(env);
//        Pageable pageable = extractPageable(env);
//        Page<Category> page = null;
//        if (env.getArgument("q") == null)
//            page = this.categoryRepository.findAllCategoriesByVideoId(Long.valueOf(parentId.get("id").toString()), pageable);
//        else
//            page = this.categoryRepository.searchByAndVideoId(env.getArgument("q"), Long.valueOf(parentId.get("id").toString()), pageable);
//        return createConnection(page);
//    }
//
//    public CustomConnection<Tag> getTagConnection(DataFetchingEnvironment env) {
//        Map<String, Object> parentId = extractParentId(env);
//        Pageable pageable = extractPageable(env);
//        Page<Tag> page = null;
//        if (env.getArgument("q") == null)
//            page = this.tagRepository.findAllTagsByVideoId(Long.valueOf(parentId.get("id").toString()), pageable);
//        else
//            page = this.tagRepository.searchByAndVideoId(env.getArgument("q"), Long.valueOf(parentId.get("id").toString()), pageable);
//        return createConnection(page);
//    }


    // method reuse for get all connection
    public CustomConnection<?> getTypeConnection(JpaSpecificationExecutor repository, Specification spec, Pageable page) {
        Page pageData = repository.findAll(spec, page);
        return createConnection(pageData);
    }


}
