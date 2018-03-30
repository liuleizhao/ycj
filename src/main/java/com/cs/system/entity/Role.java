package com.cs.system.entity;

import java.util.Date;

import com.cs.mvc.dao.BaseEntity;

public class Role extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    
	/** 角色名称*/
    private String name;
    
    /** 角色描述 */
    private String description;
    
    /** 创建时间 */
    private Date createdDate;
    
    /** 创建者 */
    private String creator;
    
    /** 排序Num */
    private Long orderNumber;
 
    /** 父类Id */
    private String parentId;
    
    /** 授权数 */
    private Integer count;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

    
}