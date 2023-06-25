package hello.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {

    /**
     * 서블릿과 비슷한 형태의 컨트롤러 인터페이스를 도입한다.
     * 프론트 컨트롤러는 이 인터페이스를 호출해서 구현과 관계없이 로직의 일관성을 유지한다
     */
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
