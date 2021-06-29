package com.lzh.jdh.qa.commons.core.web.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lzh.jdh.qa.commons.core.domain.AbstractRequest;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity基类
 *
 */
public class BaseEntity extends AbstractRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 逻辑删除 */
    private String deleted;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Override
    public void requestCheck() {

    }
}
