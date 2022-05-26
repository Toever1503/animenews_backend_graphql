package animenews.graphql.publishers;

import animenews.entity.Post;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import org.springframework.stereotype.Component;

@Component
public class PostUpdatePublisher {

    private final Flowable<Post> publisher;

    private ObservableEmitter<Post> emitter;

    public PostUpdatePublisher() {
        Observable<Post> commentUpdateObservable = Observable.create(emitter -> {
            this.emitter = emitter;
        });
        ConnectableObservable<Post> connectableObservable = commentUpdateObservable.share().publish();
        connectableObservable.connect();
        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    public void publish(Long id, final Post post) {
        emitter.onNext(post);
    }


    public Flowable<Post> getPublisher() {
        return publisher;
    }
}
