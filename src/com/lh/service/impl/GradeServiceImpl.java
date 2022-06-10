
package com.lh.service.impl;

import com.lh.bean.Grade;
import com.lh.dao.GradeDao;
import com.lh.dao.impl.GradeDaoImpl;
import com.lh.service.GradeService;

import java.util.List;

public class GradeServiceImpl implements GradeService {
    private GradeDao dao = new GradeDaoImpl();

    @Override
    public List<Grade> getList() {
        return dao.getList();
    }
}
