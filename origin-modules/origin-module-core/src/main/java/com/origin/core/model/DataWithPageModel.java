package com.origin.core.model;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by lc on 2017/6/22.
 */
public class DataWithPageModel<T> {

    private PageInfo<T> pageInfo;

    private List<T> data;

    public PageInfo<T> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
