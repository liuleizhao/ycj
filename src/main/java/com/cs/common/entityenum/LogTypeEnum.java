package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;

/**
 * 
 * 用户操作类型
 */
public enum LogTypeEnum implements Identifiable<Integer> {
	
	LOGIN(1,"用户登录"),
	USER(2,"用户管理"),
	AUTHORITY(3,"权限管理"),
	DEPARTMENT(4,"部门管理"),
	APPOINTMENT(5,"预约管理"),
	WORKDAY(6,"工作日管理"),
	ARGUMENTS(7,"参数管理"),
	PUBLICNOTICE(8,"公告管理"),
	INPUTOUTPUT(9,"导入导出管理"),
	SELECTNUM(10,"预选号码管理");
	
	private int type;
	private String description;

	private LogTypeEnum(int type, String description) {
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

	public static List<LogTypeEnum> getAll() {
		return Arrays.asList(LogTypeEnum.class.getEnumConstants());
	}

	public static LogTypeEnum findByIndex(Integer index) {
		return EnumUtils.getEnum(LogTypeEnum.class, index);
	}

	public String getValue() {
		return this.name();
	}

	public String toString() {
		return this.name();
	}

}
