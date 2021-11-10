package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username"); // url에 /hello?username=kim 입력
        System.out.println("username = " + username);

        response.setContentType("text/plain"); // response시 내용 타입을 단순 문자로 지정
        response.setCharacterEncoding("utf-8"); // 글자를 utf-8로 지정
        response.getWriter().write("hello " + username); // html 바디에 입력
    }
}
