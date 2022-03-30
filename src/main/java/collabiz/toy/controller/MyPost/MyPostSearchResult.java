package collabiz.toy.controller.MyPost;

import collabiz.toy.entity.MyPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class MyPostSearchResult {

    private List<MyPost> myPosts;
    private int totalPage;
}
