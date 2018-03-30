package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;
import com.cs.mvc.mybatis.enumhandler.Identifiable;
import com.cs.common.utils.EnumUtils;

/**
 * @描述：授权类型
 * @版本：1.0
 */
public enum AuthorizeTypeEnum implements Identifiable<Integer>{
	/** 正常授权 */
	NORMAL(1, "正常授权"),
	/** 临时授权 */
	TEMP(2, "临时授权");
	
	/** 顺序 */
	private int index;

	/** 描述 */
	private String description;

	private AuthorizeTypeEnum(int index, String description) {
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
 
	public static List<AuthorizeTypeEnum> getAll() {
		return Arrays.asList(AuthorizeTypeEnum.class.getEnumConstants());
	}

	public static AuthorizeTypeEnum findByIndex(Integer index) {
		return EnumUtils.getEnum(AuthorizeTypeEnum.class, index);
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
