package hello.login.web.session;

import hello.login.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {

        //세션 생성 -> 사용자 정보 객체를 서버에 저장하고, 그 쿠키를 response에 담기
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, response); // -> UUID(쿠키)와 member(객체)가 저장

        //요청에 응답 쿠키 저장 -> 클라이언트가 response로 부터 받은 UUID(쿠키)를 request에 담음
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //세션 조회 -> 서버 세션에 저장된 객체와 저장한 사용자 정보 객체를 비교
        Object result = sessionManager.getSession(request); // getSession: UUID를 받아 객체만 반환
        assertThat(result).isEqualTo(member);

        //세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }
}
