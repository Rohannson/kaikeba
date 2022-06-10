package com.lh.web;

import com.lh.bean.Users;
import com.lh.service.UsersService;
import com.lh.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UsersService usersService = new UsersServiceImpl();
        Users users = usersService.login(username, password);

        if (users == null) {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script> location.href='login.jsp'; alert('用户名密码不正确');</script>");
        } else {
            req.getSession().setAttribute("u1", users);
            resp.sendRedirect("index.jsp");
        }
    }
}
