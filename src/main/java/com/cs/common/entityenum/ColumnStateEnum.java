package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;

/**
 * 栏目状态
 * @author Administrator
 *
 */
public enum ColumnStateEnum implements Identifiable<Integer>{
	INVALID(0,"无效"),
	VALID(1,"有效");

	/** 顺序 */
	private int index;

	/** 描述 */
	private String description;

	private ColumnStateEnum(int index, String description) {
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

	public static List<ColumnStateEnum> getAll() {
		return Arrays.asList(ColumnStateEnum.class.getEnumConstants());
	}

	public static ColumnStateEnum findByIndex(Integer index) {
		return EnumUtils.getEnum(ColumnStateEnum.class, index);
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
