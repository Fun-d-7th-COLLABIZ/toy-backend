package collabiz.toy.entity;

import collabiz.toy.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

/**
 * by.dahae
 * 220326. 비지니스 로직 추가 필요
 */

@Entity
@Table(name = "myposts")
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
public class MyPost {
    @Id
    @GeneratedValue
    @Column(name = "mypost_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //회원(post의 주인)

    @OneToMany(mappedBy = "mypost", cascade = CascadeType.ALL)
    private List<MyPostItem> MyPostItems = new ArrayList<>();

    private LocalDateTime postDate; //글 쓴 시간
    private int postQuantity; // 쓴 글의 수
    
    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getMyPosts().add(this);
    }

    //==생성 메서드==//
    //글 쓴 사람과 글쓴 날짜 + 추가요소 추가 필요
    public static MyPost createMyPost(Member member) {
        MyPost order = new MyPost();
        order.setMember(member);
        order.setPostDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//
    public void addQuantity(int quantity) {
        this.postQuantity += quantity;
    }

    public void removeQuantity(int quantity) { //재고가 0 이하인데 재고를 빼는 상황이면 에러 내줘야 함
        int restStock = this.postQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.postQuantity = restStock;
    }
}
