package com.yclin.simplecarlease.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yclin.simplecarlease.core.ContextHolder;
import com.yclin.simplecarlease.core.IdGenerator;
import lombok.Data;

/**
 * @author LinYuchang
 */
@JsonIgnoreProperties({"isDeleted"})
@Data
public class BaseModel {

    protected String id;

    protected String creatorId;

    protected String updaterId;

    protected String deleterId;

    protected Long createTime;

    protected Long updateTime;

    protected Long deleteTime;

    protected Long isDeleted;

    public String initId() {
        this.id = IdGenerator.uuid32();
        return this.id;
    }

    public void initCreateInfo() {
        this.creatorId = ContextHolder.getAccessContext().getUserId();
        this.createTime = System.currentTimeMillis() / 1000L;
        this.updaterId = this.creatorId;
        this.updateTime = this.createTime;
    }

    public void initUpdateInfo() {
        this.updaterId = ContextHolder.getAccessContext().getUserId();
        this.updateTime = System.currentTimeMillis() / 1000L;
    }

    public void initDeleteInfo() {
        this.deleterId = ContextHolder.getAccessContext().getUserId();
        this.deleteTime = System.currentTimeMillis() / 1000L;
    }
}
