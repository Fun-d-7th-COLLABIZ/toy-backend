package collabiz.toy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

/**
 * by.dahae
 */

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class MyPostItem {
    @Id
    @GeneratedValue
    @Column(name = "post_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private MyPost mypost; //내가 작성한 post
    private String content; //쓴 글의 내용

    //==생성 메서드==//
    public static MyPostItem createMyPostItem(MyPost myPost, String content) {
        MyPostItem myPostItem = new MyPostItem();
        myPostItem.setMypost(myPost);
        myPostItem.setContent(content);
        myPost.addQuantity(1);//쓴 글의 수 하나 추가
        return myPostItem;
    }
}
