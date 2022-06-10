package com.lh.dao.impl;

import com.lh.bean.Grade;
import com.lh.dao.DBUtils;
import com.lh.dao.GradeDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDaoImpl extends DBUtils implements GradeDao {

    @Override
    public List<Grade> getList() {
        List gs = new ArrayList();
        try {
            String sql = "select * from grade";
            resultSet = query(sql, null);
            while (resultSet.next()) {
                Grade grade = new Grade();
                grade.setGradeId(resultSet.getInt("gradeid"));
                grade.setGradeName(resultSet.getString("gradename"));
                gs.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return gs;
    }
}
