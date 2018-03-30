package com.cs.system.entity;

import java.util.Date;

import com.cs.common.entityenum.MethodTypeEnum;
import com.cs.common.entityenum.ResourceTypeEnum;
import com.cs.mvc.dao.BaseEntity;

public class Resource extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date createdDate;

    private String creator;

    private String description;

    private String name;

    private Short orderNumber;

    private String path;

    private ResourceTypeEnum resourceType;

    private String parentId;

    private String icon;

    private MethodTypeEnum methodType;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Short orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ResourceTypeEnum getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceTypeEnum resourceType) {
		this.resourceType = resourceType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public MethodTypeEnum getMethodType() {
		return methodType;
	}

	public void setMethodType(MethodTypeEnum methodType) {
		this.methodType = methodType;
	}

}