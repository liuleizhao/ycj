package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;

/**
 * 热点
 * @author Administrator
 *
 */
public enum HotEnum implements Identifiable<Integer>{
	NORMAL(0,"非热点"),
	HOT(1,"热点");

	/** 顺序 */
	private int index;

	/** 描述 */
	private String description;

	private HotEnum(int index, String description) {
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

	public static List<HotEnum> getAll() {
		return Arrays.asList(HotEnum.class.getEnumConstants());
	}

	public static HotEnum findByIndex(Integer index) {
		return EnumUtils.getEnum(HotEnum.class, index);
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
