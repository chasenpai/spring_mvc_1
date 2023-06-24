package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "mvcMemberListServlet", urlPatterns = "/servlet-mvc/members")
public class MvcMemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * MVC 패턴의 한계
         *
         * 중복되는 코드들(포워드, viewPath)
         * - 만약 템플릿엔진을 변경하게되면 전체 코드를 변경해야 한다
         *
         * 사용하지 않는 코드
         * - 현재 코드에서 response 는 사용되지 않고, 이러한 코드는 테스트 케이스 작성도 어려움
         *
         * 어려운 공통 처리
         * - 기능이 복잡해질 수록 컨트롤러에서 공통으로 처리해야 하는 부분이 많아질 것이다. 단순히 공통 기능을
         * - 메서드로 뽑으면 될 것 같지만 결과적으로 해당 메서드를 항상 호출해야 하고, 그 자체가 중복이다
         *
         * 프론트 컨트롤러(Front Controller)
         * - 위 문제들을 해결하기 위한 수문장 역할을 할 컨트롤러가 필요하다
         * - 스프링 MVC 의 핵심도 프론트 컨트롤러에 있다
         */
        List<Member> memberList = memberRepository.findAll();
        request.setAttribute("memberList", memberList);

        String viewPath = "/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

}
