package com.lh.web;


import com.lh.bean.Student;
import com.lh.service.StudentService;
import com.lh.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/Educational/student/getStudentList")
public class GetStudentServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stuname = req.getParameter("stuname");
        String stuno = req.getParameter("stuno");
        String sex = req.getParameter("sex");

        String pageIndex = req.getParameter("pageIndex");
        int index = pageIndex == null ? 1 : Integer.parseInt(pageIndex);

        StudentService service = new StudentServiceImpl();
        int uSex = (sex == null || sex.length() == 0 ? -1 : Integer.parseInt(sex));
        System.out.println("pageIndex: " + pageIndex + " sex: " + sex + " stuno: " + stuno + " stuname: " + stuname);
        List<Student> students =
                service.getStudents(stuname, stuno, uSex, index, 5);
        // 获取总页数 = 总条数 % 每页现实的条数 > 0 ? 总条数 / 每页显示的条数 + 1 : 总条数 / 每页显示的条数
        int total = service.total(stuname, stuno, uSex);
        int totalPages = total % 5 > 0 ? total / 5 + 1 : total / 5;

        req.setAttribute("stulist", students);

        // 存储模糊查条件
        req.setAttribute("stuname", stuname);
        req.setAttribute("stuno", stuno);
        req.setAttribute("sex", sex);
        req.setAttribute("index", index);
        req.setAttribute("total", total);
        req.setAttribute("size", 5);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }

}
