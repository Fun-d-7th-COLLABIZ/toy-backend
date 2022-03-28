package collabiz.toy.service;


import collabiz.toy.entity.Member;
import collabiz.toy.entity.MyPost;
import collabiz.toy.entity.MyPostItem;
import collabiz.toy.repository.MemberRepository;
import collabiz.toy.repository.MyPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * by.dahae
 * mypost 생성 로직 꼬임. 다시 설계
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyPostService {
    private final MemberRepository memberRepository;
    private final MyPostRepository myPostRepository;

    /** post 생성 **/
    @Transactional
    public Long myPost(Long memberId, Long myPostId) {
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        MyPost myPost = myPostRepository.findOne(myPostId);

        //주문상품 생성
        MyPostItem myPostItem = MyPostItem.createMyPostItem(myPost, myPost);
        //주문 생성
        MyPost myPost = MyPost.createMyPost(member);

        //주문 저장
        myPostRepository.save(myPost);
        return myPost.getId();
    }
    /** 주문 취소 */
    @Transactional
    public void cancelOrder(Long myPostId) {
        //주문 엔티티 조회
        MyPost myPost = myPostRepository.findOne(myPostId);
    }
    /** 주문 검색 */
    /*
     public List<Order> findOrders(OrderSearch orderSearch) {
     return orderRepository.findAll(orderSearch);
     }
    */
}
