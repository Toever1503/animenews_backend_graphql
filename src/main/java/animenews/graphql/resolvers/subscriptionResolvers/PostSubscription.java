package animenews.graphql.resolvers.subscriptionResolvers;

import animenews.entity.Post;
import animenews.graphql.publishers.PostUpdatePublisher;
import animenews.service.IPostService;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PostSubscription implements GraphQLSubscriptionResolver {
    private final IPostService postService;
    private final PostUpdatePublisher postUpdatePublisher;

    public PostSubscription(IPostService postService, PostUpdatePublisher postUpdatePublisher) {
        this.postService = postService;
        this.postUpdatePublisher = postUpdatePublisher;
    }

    public Publisher<Post> postUpdate() {
        return postUpdatePublisher.getPublisher();
    }

}
