package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.mvc.mybatis.enumhandler.Identifiable;
import com.cs.common.utils.EnumUtils;

/**
 * 性别枚举
 * @author llz
 */
public enum SexEnum implements Identifiable<Integer> {
	/** 男 */
	MAN   (1, "男"),
	/** 女 */
	WOMEN (2, "女"),
	/*未知*/
	UNKNOWN (0, "未知");

	/** 顺序 */
	private int index;

	/** 描述 */
	private String description;
	
	private SexEnum(int index, String description) {
		this.index = index;
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public int getIndex() {
		return this.index;
	}

	public static List<SexEnum> getAll() {
		return Arrays.asList(SexEnum.class.getEnumConstants());
	}

	public static SexEnum findByIndex(Integer index) {
		return EnumUtils.getEnum(SexEnum.class, index);
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

	@Override
	public Integer getId() {
		return this.index;
	}

}
