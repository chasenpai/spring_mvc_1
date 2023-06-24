package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    /**
     * HTTP message body 에 데이터 직접 담기
     * - HTTP API 에서 주로 사용, JSON, XML, TEXT
     * - 데이터 형식은 주로 JSON
     * - POST, PUT, PATCH
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletInputStream inputStream = request.getInputStream(); //byte 코드를 반환
        String msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //Charset 지정
        System.out.println("msgBody = " + msgBody);

        response.getWriter().write("ok");
    }

}
