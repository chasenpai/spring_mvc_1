package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    /**
     * Front Controller
     * - 프론트 컨트롤러 서블릿 하나로 클라이언트의 요청을 받음
     * - 프론트 컨트롤러가 요청에 맞는 컨트롤러를 찾아서 호출
     * - 공통 처리 가능
     * - 프론트 컨트롤러를 제외한 나머지 컨트롤러는 서블릿을 사용하지 않아도 됨
     */
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    //front-controller/v1/* 의 하위 요청은 모두 이 서블릿에서 받아들인다
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV1 controller = controllerMap.get(requestURI); //uri 를 조회해서 실제 호출할 컨트롤러를 map 에서 찾는다

       if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //만약 없다면 404
            return;
        }

        controller.process(request, response); //요청 컨트롤러를 실행
    }

}
