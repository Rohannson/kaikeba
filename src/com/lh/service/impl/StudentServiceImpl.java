package com.lh.service.impl;

import com.lh.bean.Student;
import com.lh.dao.StudentDao;
import com.lh.dao.impl.StudentDaoImpl;
import com.lh.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> getStudents(String stuname, String stuno, Integer sex, int pageIndex, int pageSize) {
        return studentDao.getStudentList(stuname, stuno, sex, pageIndex, pageSize);
    }

    @Override
    public int total(String stuname, String stuno, Integer sex) {
        return studentDao.total(stuname, stuno, sex);
    }

    @Override
    public int insertStu(Student student) {
        return studentDao.insertStu(student);
    }

    @Override
    public Student findById(int sid) {
        return studentDao.findById(sid);
    }

    @Override
    public int updateStu(Student student) {
        return studentDao.updateStu(student);
    }

    @Override
    public int deleteStu(String sId) {
        return studentDao.deleteStu(sId);
    }
}
