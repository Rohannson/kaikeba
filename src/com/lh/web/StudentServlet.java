package com.lh.web;

import com.lh.bean.Grade;
import com.lh.bean.Student;
import com.lh.bean.Users;
import com.lh.service.GradeService;
import com.lh.service.StudentService;
import com.lh.service.impl.GradeServiceImpl;
import com.lh.service.impl.StudentServiceImpl;
import com.lh.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Educational/student/studentServlet")
public class StudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (("insert").equals(method)) {
            addStu(req, resp);
        } else if (("delete").equals(method)) {
            deleteStu(req, resp);
        } else if (("findbyid").equals(method)) {
            findById(req, resp);
        } else if (("update").equals(method)) {
            update(req, resp);
        } else if (("getGradeList").equals(method)) {
            getGradeList(req, resp);
        } else {
            findList(req, resp);
        }
    }

    protected void getGradeList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询年纪列表方法
        GradeService gradeService = new GradeServiceImpl();
        List<Grade> list = gradeService.getList();
        req.setAttribute("glist", list);
        req.getRequestDispatcher("add.jsp").forward(req, resp);
    }
    // Delete Student
    protected void deleteStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("sid");
        StudentService service = new StudentServiceImpl();
        int i = service.deleteStu(sId);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if (i > 0) {
            writer.println("<script>alert('删除成功');location.href='/Educational/student/studentServlet'</script>");
        } else {
            writer.println("<script>alert('删除失败');location.href='/Educational/student/studentServlet'</script>");
        }
    }

    // Add Student
    protected void addStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stuNo = req.getParameter("stuNo");
        String stuName = req.getParameter("stuName");
        String gid = req.getParameter("gid");
        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String registered = req.getParameter("registered");
        String address = req.getParameter("address");
        String politics = req.getParameter("politics");
        String idNumber = req.getParameter("idNumber");
        String profession = req.getParameter("profession");
        String introduction = req.getParameter("introduction");

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();

        PageUtil pageUtil = new PageUtil();
        StudentService service = new StudentServiceImpl();
        StudentService studentService = new StudentServiceImpl();
        List<Student> stuList = studentService.getStudents("", stuNo, -1, 1, pageUtil.getPageSize());

        if (stuList.size() != 0) {
            writer.println("<script>alert('学号已存在');location.href='/Educational/student/studentServlet?method=getGradeList'</script>");
            return;
        }

        Student student = new Student();
        student.setStuNo(stuNo);
        student.setStuName(stuName);
        student.setGid(Integer.parseInt(gid));
        student.setSex(Integer.parseInt(sex));
        student.setEmail(email);
        student.setPhone(phone);
        student.setRegistered(registered);
        student.setAddress(address);
        student.setPolitics(politics);
        student.setIdNumber(idNumber);
        student.setProfession(profession);
        student.setIntroduction(introduction);
        int i = service.insertStu(student);
        if (i > 0) {
            writer.println("<script>alert('新增成功');location.href='/Educational/student/studentServlet'</script>");
        } else {
            writer.println("<script>alert('新增失败');location.href='/Educational/student/studentServlet'</script>");
        }
    }

    // Search by Main Key
    protected void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("sid");
        StudentService service = new StudentServiceImpl();
        Student student = service.findById(Integer.parseInt(sId));

        //Search Grade List
        GradeService gradeService = new GradeServiceImpl();
        List<Grade> list = gradeService.getList();

        req.setAttribute("gList", list);
        req.setAttribute("stu", student);
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }

    // Search Student List
    protected void findList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users users = (Users) req.getSession().getAttribute("u1");
        if (users == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("<script>alert('请登录');top.location.href='/login.jsp';</script>");
            return;
        }

        String stuname = req.getParameter("stuname");
        String stuno = req.getParameter("stuno");
        String sex = req.getParameter("sex");

        String pageIndex = req.getParameter("pageIndex");
        int index = pageIndex == null ? 1 : Integer.parseInt(pageIndex);

        StudentService service = new StudentServiceImpl();
        PageUtil pageUtil = new PageUtil();
        int uSex = (sex == null || sex.length() == 0 ? -1 : Integer.parseInt(sex));
        System.out.println("StudentServlet/findList -> pageIndex: " + pageIndex + " sex: " + sex + " stuno: " + stuno + " stuname: " + stuname);
        List<Student> students =
                service.getStudents(stuname, stuno, uSex, index, pageUtil.getPageSize());

        int total = service.total(stuname, stuno, uSex); // 总条数

        pageUtil.setTotal(total);
        pageUtil.setDataList(students);
        pageUtil.setPageIndex(index);

        // 存储模糊查条件
        req.setAttribute("stuname", stuname);
        req.setAttribute("stuno", stuno);
        req.setAttribute("sex", sex);

        // 储存分页的数据
        req.setAttribute("p1", pageUtil);

        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }

    // Update Student
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("sId");
        String stuNo = req.getParameter("stuNo");
        String stuName = req.getParameter("stuName");
        String gid = req.getParameter("gid");
        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String registered = req.getParameter("registered");
        String address = req.getParameter("address");
        String politics = req.getParameter("politics");
        String idNumber = req.getParameter("idNumber");
        String profession = req.getParameter("profession");
        String introduction = req.getParameter("introduction");

        Student student = new Student();
        student.setStuName(stuName);
        student.setAddress(address);
        student.setSex(Integer.parseInt(sex));
        student.setStuId(Integer.parseInt(sId));

        StudentService studentService = new StudentServiceImpl();
        int i = studentService.updateStu(student);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if (i > 0) {
            writer.println("<script>alert('更新成功');location.href='/Educational/student/studentServlet'</script>");
        } else {
            writer.println("<script>alert('更新失败');location.href='/Educational/student/studentServlet?method=findById&sid=" + sId + "'</script>");
        }
    }

}


























