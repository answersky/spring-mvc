package com.java.domain;

import java.util.List;
import java.util.Map;

/**
 * Created by liuf on 2016/7/30.
 */
public class JsonpData {
    /**
     * 总的记录数
     */
    private int records;
    /**
     * 当前页
     */
    private int page;
    /**
     * 总页数
     */
    private int total;
    /**
     * 每一行的内容
     */
    private List<Object> rows;

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Object> getRows() {
        return rows;
    }

    public void setRows(List<Object> rows) {
        this.rows = rows;
    }
}
