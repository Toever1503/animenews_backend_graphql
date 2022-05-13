package animenews.graphql.connection;

import graphql.com.google.common.collect.ImmutableList;
import graphql.relay.Edge;

import java.util.List;

import static graphql.Assert.assertNotNull;


public class DefaultCustomConnection<T> implements CustomConnection {
    private final ImmutableList<Edge<T>> edges;
    private final CustomPageInfo pageInfo;

    public DefaultCustomConnection(List<Edge<T>> edges, DefaultCustomPageInfo pageInfo) {
        this.edges = ImmutableList.copyOf(assertNotNull(edges, () -> "edges cannot be null"));
        this.pageInfo = assertNotNull(pageInfo, () -> "page info cannot be null");
    }

    public DefaultCustomConnection<T> get() {
        return this;
    }

    @Override
    public CustomPageInfo getPageInfo() {
        return this.pageInfo;
    }

    @Override
    public List<Edge<T>> getEdges() {
        return this.edges;
    }
}
