package com.lh.dao.impl;

import com.lh.bean.Student;
import com.lh.dao.DBUtils;
import com.lh.dao.StudentDao;
import com.lh.util.StudentEnum;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDaoImpl extends DBUtils implements StudentDao{

    @Override
    public List<Student> getStudentList(String stuname, String stuno, Integer sex, int pageIndex, int pageSize) {
        List list = new ArrayList<Student>();
        List params = new ArrayList();
        try {
            StringBuffer sql = new StringBuffer(" select * from student where 1 = 1 and state != 4");

            if (stuname != null && stuname.length() > 0){
                sql.append(" and stuname like ? ");
                params.add("%" + stuname + "%");
            }
            if (stuno != null && stuno.length() > 0) {
                sql.append(" and stuno like ? ");
                params.add(stuno);
            }
            if (sex != null && sex != -1) {
                sql.append(" and sex = ? ");
                params.add(sex);
            }

            sql.append(" limit ?,?"); // 1 5, limit 0, 5
            // limit ()
            params.add((pageIndex - 1) * pageSize);
            params.add(pageSize);

            resultSet = query(sql.toString(), params);
            while (resultSet.next()) {
                Student student = new Student();
                student.setStuId(resultSet.getInt("stuid"));
                student.setStuName(resultSet.getString("stuname"));
                student.setStuNo(resultSet.getString("stuno"));
                student.setSex(resultSet.getInt("sex"));
                student.setPhone(resultSet.getString("phone"));
                student.setProfession(resultSet.getString("profession"));
                student.setRegDate(resultSet.getDate("regdate"));
                student.setAddress(resultSet.getString("address"));
                student.setIdNumber(resultSet.getString("idnumber"));
                student.setPolitics(resultSet.getString("politics"));
                student.setRegistered(resultSet.getString("registered"));
                student.setState(resultSet.getInt("state"));
                student.setIntroduction(resultSet.getString("introduction"));
                student.setGid(resultSet.getInt("gid"));
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int total(String stuname, String stuno, Integer sex) {
        int total = 0;
        try {
            List params = new ArrayList();
            StringBuffer sql = new StringBuffer(" select count(*) from student where 1 = 1 and state != 4");

            if (stuname != null && stuname.length() > 0){
                sql.append(" and stuname like ? ");
                params.add("%" + stuname + "%");
            }
            if (stuno != null && stuno.length() > 0) {
                sql.append(" and stuno=? ");
                params.add(stuno);
            }
            if (sex != null && sex != -1) {
                sql.append(" and sex = ? ");
                params.add(sex);
            }

            resultSet = query(sql.toString(), params);
            while (resultSet.next()) {
                total = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return total;
    }

    @Override
    public int insertStu(Student student) {
        int i = 0;
        try {
            String sql = "insert into student values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            List params = new ArrayList();
            params.add(student.getStuName());
            params.add(student.getStuNo());
            params.add(student.getSex());
            params.add(student.getPhone());
            params.add(student.getEmail());
            params.add(student.getRegistered());
            params.add(student.getAddress());
            params.add(student.getProfession());
            params.add(student.getIdNumber());
            params.add(student.getPolitics());
            params.add(new Date());
            params.add(StudentEnum.READING.type);
            params.add(student.getIntroduction());
            params.add(student.getGid());
            i = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return i;
    }

    @Override
    public Student findById(int sid) {
        Student student = new Student();
        try {
            String sql = "select * from student where stuid = ?";
            List params = new ArrayList();
            params.add(sid);
            resultSet = query(sql, params);
            while (resultSet.next()) {
                student.setStuId(resultSet.getInt("stuid"));
                student.setStuNo(resultSet.getString("stuno"));
                student.setStuName(resultSet.getString("stuname"));
                student.setGid(resultSet.getInt("gid"));
                student.setSex(resultSet.getInt("sex"));
                student.setEmail(resultSet.getString("email"));
                student.setPhone(resultSet.getString("phone"));
                student.setRegistered(resultSet.getString("registered"));
                student.setAddress(resultSet.getString("address"));
                student.setPolitics(resultSet.getString("politics"));
                student.setIdNumber(resultSet.getString("idnumber"));
                student.setProfession(resultSet.getString("profession"));
                student.setIntroduction(resultSet.getString("introduction"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return student;
    }

    @Override
    public int updateStu(Student student) {
        int update = 0;
        try {
            String sql = "update student set stuname=?, address=?, sex=? where stuid=?";
            List params = new ArrayList();
            params.add(student.getStuName());
            params.add(student.getAddress());
            params.add(student.getSex());
            params.add(student.getStuId());

            update = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return update;
    }

    @Override
    public int deleteStu(String sId) {
        int update = 0;
        try {
            String sql = "update student set state = ? where stuid = ?";
            List params = new ArrayList();
            params.add(StudentEnum.DELETE.type);
            params.add(sId);
            update = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return update;
    }
}




















