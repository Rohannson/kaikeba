package com.lh.util;

import java.util.List;

public class PageUtil {

    // 页码值 显示条数 查询结果列表 总条数 总页数
    private Integer pageIndex;
    private Integer pageSize = 5;
    private List dataList;
    private Integer total;
    private Integer totalPages;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    // 获取总页数 = 总条数 % 每页现实的条数 > 0 ? 总条数 / 每页显示的条数 + 1 : 总条数 / 每页显示的条数
    public Integer getTotalPages() {
        return  total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
