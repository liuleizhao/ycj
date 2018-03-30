package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;

/**
 * 新闻状态
 * @author Administrator
 *
 */
public enum NewsStateEnum implements Identifiable<Integer>{
	TIMING(0,"定时发布"),
	VALID(1,"发布"),
	INVALID(2,"下架");

	/** 顺序 */
	private int index;

	/** 描述 */
	private String description;

	private NewsStateEnum(int index, String description) {
		this.index = index;
		this.description = description;
	}
	@Override
	public Integer getId() {
		return this.index;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	public static List<NewsStateEnum> getAll() {
		return Arrays.asList(NewsStateEnum.class.getEnumConstants());
	}

	public static NewsStateEnum findByIndex(Integer index) {
		return EnumUtils.getEnum(NewsStateEnum.class, index);
	}

	/**
	 * 用于struts2标签。如s:select标签的自动回显
	 * @return
	 */
	public String getValue(){
		return this.name();
	}

	public String toString() {
		return this.name();
	}
}
