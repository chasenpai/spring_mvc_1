package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    /**
     * JSON 결과를 파싱해서 사용할 수 있는 자바 객체로 변환하려면 Jackson, Gson 같은 라이브러리를 사용해야 한다
     * 스프링 부트는 기본적으로 Jackson 라이브러리(objectMapper)를 제공
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletInputStream inputStream = request.getInputStream();
        String msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("msgBody = " + msgBody);

        HelloData helloData = objectMapper.readValue(msgBody, HelloData.class);
        System.out.println("helloData = " + helloData);

        response.getWriter().write("ok");
    }

}
