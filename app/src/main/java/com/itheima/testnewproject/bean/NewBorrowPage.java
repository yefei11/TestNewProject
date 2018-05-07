package com.itheima.testnewproject.bean;

import java.util.List;

/**
 * @创建者 baoxin
 * @日期 2017/6/29.
 * @描述
 */

public class NewBorrowPage {

    private String moduleName;
    private String attachPath;
    private int moduleId;
    private int isMore;
    private List<BorrowPage> moduleList;

    public String getModuleName() {
        return moduleName;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getIsMore() {
        return isMore;
    }

    public void setIsMore(int isMore) {
        this.isMore = isMore;
    }

    public List<BorrowPage> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<BorrowPage> moduleList) {
        this.moduleList = moduleList;
    }
}
