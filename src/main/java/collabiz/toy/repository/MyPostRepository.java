package collabiz.toy.repository;

import collabiz.toy.entity.Member;
import collabiz.toy.entity.MyPost;
import collabiz.toy.entity.QMember;
import collabiz.toy.entity.QMyPost;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * by.dahae
 */
@Repository
@RequiredArgsConstructor
public class MyPostRepository {

    private final EntityManager em;
    private final MemberRepository memberRepository;
    private final JPAQueryFactory queryFactory; // 빈으로 등록해야 함!

    public void save(MyPost order) {
        em.persist(order);
    }

    public MyPost findOne(Long id) {
        return em.find(MyPost.class, id);
    }

    /**
     * MemberId로 MyPost를 찾아야 하는 상황이 필요하다는 가정
     * 직접 JPQL을 사용했을 때
     */
    public List<MyPost> findByMemberId(Long memberId) {
        return em.createQuery(
                "select p from MyPost p join Member m where m.id = :memberId", MyPost.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    /**
     * 위 경우를 querydsl로 했을 때
     */
    public List<MyPost> findByMemberIdWithQuerydsl(Long memberId) {
        return queryFactory
                .select(QMyPost.myPost)
                .from(QMyPost.myPost)
                .join(QMember.member)
                .where(
                        memberIdEq(memberId)
                        /*
                        ,조건검증메소드1()
                        ,조건검증메소드2()
                        ,조건검증메소드3()
                        이런 형식으로 ,를 이용해서 다른 조건 검증 함수 추가 가능
                        조건이 맞지 않는다면 무시한다
                         */
                )
                .fetch();
    }

    /*
    조건 검증 메소드
    where에 들어가는 조건들을 메소드르 뽑아 놓은 것!
    조건이 불충족하면 알아서 무시된다
     */
    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null ? QMember.member.id.eq(memberId) : null;
    }

    /**
     * MyPost 제목 검색
     */
    public Page<MyPost> findByTitle(Long memberId, Pageable pageable, String title) {
        List<MyPost> content = queryFactory
                .select(QMyPost.myPost)
                .from(QMyPost.myPost)
                .join(QMember.member)
                .on(QMember.member.id.eq(memberId))
                .where(
                        titleEq(title)
                )
                .offset(pageable.getOffset() - pageable.getPageSize())
                .limit(pageable.getPageSize())
                .fetch();

        // 총 페이지 수 조회
        JPAQuery<Long> countQuery = queryFactory
                .select(QMyPost.myPost.count())
                .from(QMyPost.myPost)
                .join(QMember.member)
                .on(QMember.member.id.eq(memberId))
                .where(titleEq(title));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression titleEq(String title) {
        return StringUtils.hasText(title) ? QMyPost.myPost.title.eq(title) : null;
    }
}