package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * /WEB-INF
         * - 해당 경로안에 JSP 가 있으면 외부에서 직접 JSP 를 호출할 수 없다
         * - 컨트롤러를 통해서 JSP 를 호출하는 것
         */
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response); //다른 서블릿이나 JSP 로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출 발생

        /**
         * 리다이렉트 VS 포워드
         * - 리다이렉트는 실제 클라이언트에 응답이 나갔다가 클라이언트가 redirect 경로로 다시 요청한다
         * - 따라서 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경된다
         * - 포워드는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못한다
         */
    }

}
