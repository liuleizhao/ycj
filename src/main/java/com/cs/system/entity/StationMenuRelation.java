package com.cs.system.entity;

import java.util.Date;

import com.cs.mvc.dao.BaseEntity;

public class StationMenuRelation extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer authorizeType;

    private String stationId;

    private String menuId;

    private Date createdDate;

    public Integer getAuthorizeType() {
        return authorizeType;
    }

    public void setAuthorizeType(Integer authorizeType) {
        this.authorizeType = authorizeType;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}