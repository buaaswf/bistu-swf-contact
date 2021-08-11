package cn.bistu.servlet;

import cn.bistu.service.ContactService;
import cn.bistu.service.impl.ContactServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteContactServlet",urlPatterns = {"/DeleteContactServlet"})
public class DeleteContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //在火狐浏览器中以GET方式提交带参数的数据，会重复提交两次
        System.out.println("删除联系人");

        //1.接受ID
        String id = request.getParameter("id");

        //2.调用service删除联系人方法
        ContactService service = new ContactServiceImpl();
        service.deleteContact(id);

        //3.跳转到查询联系人的页面
        response.sendRedirect(request.getContextPath()+"/ListContactServlet");
    }
}
