package com.itheima.testnewproject.bean;

import java.util.List;

/**
 * @创建者 baoxin
 * @日期 2017/6/30.
 * @描述
 */

public class MoreBorrowPage {

    private int total;
    private int offset;
    private int limit;
    private List<BorrowPage> content;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<BorrowPage> getContent() {
        return content;
    }

    public void setContent(List<BorrowPage> content) {
        this.content = content;
    }

}
