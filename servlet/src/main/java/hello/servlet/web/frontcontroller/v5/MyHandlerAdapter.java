package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MyHandlerAdapter {

    boolean supports(Object handler); //어댑터가 해당 컨트롤러를 처리할 수 있는지 판단

    /**
     * 어댑터는 실제 컨트롤러를 호출하고 ModelView 를 반환한다
     * 실제 컨트롤러를 호출을 어댑터가 대신한다
     */
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;

}
