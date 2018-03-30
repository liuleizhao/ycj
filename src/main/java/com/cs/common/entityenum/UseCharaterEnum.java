package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;

/**
 * 使用性质
 */
public enum UseCharaterEnum implements Identifiable<String> {

	QT("","其它"),YZF("L","营转非");
	
	/** 顺序 */
	private String index;

	/** 描述 */
	private String description;

	private UseCharaterEnum(String index, String description) {
		this.index = index;
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public String getIndex() {
		return this.index;
	}

	@Override
	public String getId() {
		return this.index;
	}
	
	public static List<UseCharaterEnum> getAll() {
		return Arrays.asList(UseCharaterEnum.class.getEnumConstants());
	}

	public static UseCharaterEnum findByIndex(String index) {
		return EnumUtils.getEnum(UseCharaterEnum.class, index);
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
