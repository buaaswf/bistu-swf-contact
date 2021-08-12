package cn.bistu.servlet;

import cn.bistu.entity.Contact;
import cn.bistu.exception.NameRepeatException;
import cn.bistu.service.ContactService;
import cn.bistu.service.impl.ContactServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddContactServlet",urlPatterns = {"/AddContactServlet"})
public class AddContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.接收参数
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String qq = request.getParameter("qq");

        //封装成Contact对象
        Contact contact = new Contact();
        contact.setName(name);
        contact.setGender(gender);
        contact.setAge(Integer.parseInt(age));
        contact.setPhone(phone);
        contact.setEmail(email);
        contact.setQq(qq);

        ContactService service = new ContactServiceImpl();
        //2.调用dao类的添加联系人方法
        try {
            service.addContact(contact);
        } catch (NameRepeatException e) {
            //处理自定义业务异常
            request.setAttribute("message",e.getMessage());
            request.getRequestDispatcher("/addContact.html").forward(request,response);
            return;
        }
        //3.跳转到查询联系人页面
        response.sendRedirect(request.getContextPath()+"/ListContactServlet");
    }
}
