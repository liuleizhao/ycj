package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;

/**
 * 置顶
 * @author Administrator
 *
 */
public enum TopEnum implements Identifiable<Integer>{
	NORMAL(0,"不置顶"),
	TOP(1,"置顶");

	/** 顺序 */
	private int index;

	/** 描述 */
	private String description;

	private TopEnum(int index, String description) {
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

	public static List<TopEnum> getAll() {
		return Arrays.asList(TopEnum.class.getEnumConstants());
	}

	public static TopEnum findByIndex(Integer index) {
		return EnumUtils.getEnum(TopEnum.class, index);
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
