package animenews.web;

import animenews.repository.PostRepository;
import animenews.service.IPostService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/test")
public class TestController {

    private final PostRepository postRepository;
    private final IPostService postService;

    public TestController(PostRepository postRepository, IPostService postService) {
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @RequestMapping("{id}/{slug}")
    public Object test(@PathVariable("id") Long id, @PathVariable("slug") Long slug) {
//        return Arrays.asList(this.postRepository.nextPost(id, slug, PageRequest.of(0, 1)),
//                this.postRepository.prevPost(id, slug, PageRequest.of(0, 1)));

        return postService.findNextPrevPosts(id, slug);
    }
}
