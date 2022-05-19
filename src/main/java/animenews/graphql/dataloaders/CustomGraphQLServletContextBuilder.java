package animenews.graphql.dataloaders;

import animenews.entity.Post;
import animenews.graphql.connection.CustomConnection;
import animenews.graphql.resolvers.objectResolvers.ConnectionQuery;
import animenews.repository.PostRepository;
import animenews.service.IPostService;
import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class CustomGraphQLServletContextBuilder implements GraphQLServletContextBuilder {
    //    private  Map<String, DataLoader<?, ?>> dataLoaders;
    private final IPostService postService;
    private final DataLoaderRegistry dataLoaderRegistry;

    public CustomGraphQLServletContextBuilder(IPostService postService) {
        this.postService = postService;
        dataLoaderRegistry = new DataLoaderRegistry();
        dataLoaderRegistry.register("postUrl", new DataLoader<Object, Post>(
                key -> CompletableFuture.supplyAsync(() -> {
                            String[] keyOf = (String[]) key.get(0);
                            return Arrays.asList(postService.findPostUrl(keyOf[0], keyOf[1]));
                        }
                )));
    }

    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return DefaultGraphQLServletContext.createServletContext()
                .with(dataLoaderRegistry)
                .with(httpServletRequest)
                .with(httpServletResponse)
                .build();
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        return DefaultGraphQLWebSocketContext.createWebSocketContext(dataLoaderRegistry, null)
                .with(session)
                .with(handshakeRequest)
                .build();
    }

    @Override
    public GraphQLContext build() {
        return new DefaultGraphQLContext(dataLoaderRegistry, null);
    }

}
