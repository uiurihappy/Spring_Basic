package hello.core.member;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component  //memoryMemberRepository
public class MemoryMemberRepository implements MemberRepository{

    //Memory저장소의 Map 선언
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        //Memory저장소에 id와 회원 정보를 put
        store.put(member.getId(), member);
    }

    //아이디를 찾아온다.
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
