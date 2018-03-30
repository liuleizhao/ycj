package com.cs.system.entity;

import java.util.Date;

import com.cs.mvc.dao.BaseEntity;

public class UserRoleRelation extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer authorizeType;

    private String roleId;

    private String userId;

    private Date createdDate;


    public Integer getAuthorizeType() {
        return authorizeType;
    }

    public void setAuthorizeType(Integer authorizeType) {
        this.authorizeType = authorizeType;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}