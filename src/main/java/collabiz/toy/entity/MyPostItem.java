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
    private MyPost mypost; //주문
    //시간 등의 정보 추가 되면 추가

}
