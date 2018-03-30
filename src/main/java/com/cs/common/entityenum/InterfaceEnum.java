package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;

/**
 * 接口枚举
 * @author llz
 */
public enum InterfaceEnum implements Identifiable<Integer> {
	JK03(3, "bookInfo","获取日期"),
	JK04 (4, "bookInfo","获取时间"),
	JK05 (5, "bookInfo","获取写入数据"),
	JK06 (6, "bookInfo","查询预约信息"),
	JK07 (7, null,"取消预约"),
	JK08 (8, "bookInfo","批量查询预约信息"),
	JK09 (9, "bookInfo","获取备案企业");

	/** 顺序 */
	private Integer index;

	/** fatherNode */
	private String fatherNode;
	
	private String description;
	
	private InterfaceEnum(Integer index, String fatherNode, String description) {
		this.index = index;
		this.fatherNode = fatherNode;
		this.description = description;
	}


	public Integer getIndex() {
		return this.index;
	}
	
	public String getFatherNode() {
		return this.fatherNode;
	}
	
	public String getDescription() {
		return this.description;
	}

	public static List<InterfaceEnum> getAll() {
		return Arrays.asList(InterfaceEnum.class.getEnumConstants());
	}

	public static InterfaceEnum findByIndex(Integer index) {
		return EnumUtils.getEnum(InterfaceEnum.class, index);
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
