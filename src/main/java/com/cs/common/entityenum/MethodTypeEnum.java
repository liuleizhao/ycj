package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;
import com.cs.mvc.mybatis.enumhandler.Identifiable;
import com.cs.common.utils.EnumUtils;

/**
 * 提交方式
 * 
 */
public enum MethodTypeEnum implements Identifiable<Integer> {

	GET(1, "GET"), POST(2, "POST"),

	PUT(3, "PUT"), DELETE(4, "DELETE")

	;

	private int type;
	private String description;

	private MethodTypeEnum(int type, String description) {
		this.type = type;
		this.description = description;
	}

	@Override
	public Integer getId() {
		return this.type;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	public static List<MethodTypeEnum> getAll() {
		return Arrays.asList(MethodTypeEnum.class.getEnumConstants());
	}

	public static MethodTypeEnum findByIndex(Integer index) {
		return EnumUtils.getEnum(MethodTypeEnum.class, index);
	}

	public String getValue() {
		return this.name();
	}

	public String toString() {
		return this.name();
	}

}
