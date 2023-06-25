package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    /**
     * 복잡하게 dispatcher.forward() 를 직접 생성할 필요 없이
     * 간단하게 MyView 객체를 생성하고 거기에 뷰 이름만 넣고 반환하면 된다
     */
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/new-form.jsp");
    }

}
