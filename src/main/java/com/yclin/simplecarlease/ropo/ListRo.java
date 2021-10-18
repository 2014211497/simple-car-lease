package com.yclin.simplecarlease.ropo;

import lombok.Data;

import java.util.List;

/**
 * @author LinYuchang
 */
@Data
public class ListRo<T> {

    protected List<T> data;

    protected Long total;

    protected Boolean hasMore;

    public ListRo() {
    }

    public ListRo(List<T> data, Long total, Boolean hasMore) {
        this.data = data;
        this.total = total;
        this.hasMore = hasMore;
    }
}
