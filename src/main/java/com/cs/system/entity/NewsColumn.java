package com.cs.system.entity;

import java.util.Date;

import com.cs.common.entityenum.CommonStateEnum;
import com.cs.mvc.dao.BaseEntity;

public class NewsColumn extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String stationId;

    private String name;

    private CommonStateEnum state;

    private String description;

    private String creator;

    private Integer orderNumber;

    private Date createDate;

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommonStateEnum getState() {
		return state;
	}

	public void setState(CommonStateEnum state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
    
}