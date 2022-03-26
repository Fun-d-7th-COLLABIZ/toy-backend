package collabiz.toy.entity;

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


}
