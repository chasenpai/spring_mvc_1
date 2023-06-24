package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    /**
     * GET
     * - /url?username=hello&age=20 ..
     * - 메세지 바디 없이 URL 의 쿼리 파라미터에 데이터를 포함해서 전달
     * - 주로 검색, 필터, 페이징 등에서 많이 사용하는 방식
     *
     * POST
     * - content-type: application/x-www-form-urlencoded
     * - 메세지 바디에 쿼리 파라미터 형식으로 전달
     * - 예) 회원 가입, 상품 주문, HTML Form 사용
     * - application/x-www-form-urlencoded 형식은 GET 의 쿼리 파라미터 형식과 같다
     * - GET 과 동일하게 파라미터 조회 메서드를 사용하면 된다
     * - 클라이언트 입장에서 두 방식에 차이가 있지만, 서버 입장에서는 둘은 형식이 동일하다
     * - content-type 은 HTTP 메세지 바디의 데이터 형식을 지정한다
     * - GET URL 쿼리 파라미터 형식은 HTTP 바디를 사용하지 않기 때문에 content-type 이 없다
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[전체 파라미터 조회] - start");

        request.getParameterNames().asIterator().forEachRemaining(
                param -> System.out.println(param + " = " + request.getParameter(param)));

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] names = request.getParameterValues("username"); //request.getParameter 를 사용하면 첫번째 값을 반환
        for (String n : names) {
            System.out.println("name = " + n);
        }

    }

}
