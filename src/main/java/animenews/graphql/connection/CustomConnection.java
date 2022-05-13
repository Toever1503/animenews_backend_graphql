package animenews.graphql.connection;

import graphql.relay.Connection;
import graphql.relay.Edge;

import java.util.List;

public interface CustomConnection<T> extends Connection {
    @Override
    CustomPageInfo getPageInfo();

    @Override
    List<Edge<T>> getEdges();
}
