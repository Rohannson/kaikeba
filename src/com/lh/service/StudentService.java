package com.lh.service;

import com.lh.bean.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getStudents(String stuname, String stuno, Integer sex, int pageIndex, int pageSize);

    //获得总数据条数
    public int total(String stuname, String stuno, Integer sex);

    public int insertStu(Student student);

    public Student findById(int sid);

    // 修改学生
    public int updateStu(Student student);

    // 删除学生
    public int deleteStu(String sId);
}
