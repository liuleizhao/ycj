package com.cs.system.entity;

import java.util.Date;

import com.cs.mvc.dao.BaseEntity;

public class RoleResourceRelation extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private Integer authorizeType;

    private String resourceId;

    private String roleId;

    private Date createDate;


    public Integer getAuthorizeType() {
        return authorizeType;
    }

    public void setAuthorizeType(Integer authorizeType) {
        this.authorizeType = authorizeType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}