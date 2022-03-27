package collabiz.toy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * by.dahae
 */
@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "member")
    private List<MyPost> myPosts = new ArrayList<>();
}
