package com.cs.system.entity;

import java.util.Date;

import com.cs.common.entityenum.CommonStateEnum;
import com.cs.common.entityenum.HotEnum;
import com.cs.common.entityenum.NewsStateEnum;
import com.cs.common.entityenum.TopEnum;
import com.cs.mvc.dao.BaseEntity;

public class News extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private Integer code;

    private String columnId;

    private String title;

    private String image;

    private String summary;

    private NewsStateEnum state;

    private TopEnum top;

    private HotEnum hot;

    private String creator;

    private Date startDate;

    private Date endDate;

    private Integer clickRate;

    private Date createDate;

    private String content;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public NewsStateEnum getState() {
		return state;
	}

	public void setState(NewsStateEnum state) {
		this.state = state;
	}

	public TopEnum getTop() {
		return top;
	}

	public void setTop(TopEnum top) {
		this.top = top;
	}

	public HotEnum getHot() {
		return hot;
	}

	public void setHot(HotEnum hot) {
		this.hot = hot;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getClickRate() {
		return clickRate;
	}

	public void setClickRate(Integer clickRate) {
		this.clickRate = clickRate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
   
}