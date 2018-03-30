package com.cs.common.entityenum;
import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;
/**
 * 
 * 接口返回code枚举类型
 */
public enum InterfaceCodeEnum implements Identifiable<String> {
	
	SUCCEED("00","请求成功"),
	PARAM_NULL("01","存在非空参数为空"),
	PARAM_NOT_PATTERN("02","未找到参数值对应的实体"),
	LOGICAL_ERROR("03","业务逻辑存在错误"),
	ID_NULL("04","接口ID为空"),
	USER_NULL("05","用户名或密码为空"),
	USER_ERROR("06","用户名或密码错误"),
	SYSTEM_ERROR("90","系统错误，请重试"),
	SYSTEM_UPGRADE("99","系统升级中，暂停预约服务"),
	FAILED ("80","请求失败");

	
	private String index;
	
	private String description;
	
	@Override
	public String getId() {
		return this.index;
	}

	@Override
	public String getDescription() {
		return this.description;
	}
	

	private InterfaceCodeEnum(String index, String description) {
		this.index = index;
		this.description = description;
	}

	public static List<InterfaceCodeEnum> getAll() {
		return Arrays.asList(InterfaceCodeEnum.class.getEnumConstants());
	}

	public static InterfaceCodeEnum findByIndex(String index) {
		return EnumUtils.getEnum(InterfaceCodeEnum.class, index);
	}

	public String getValue() {
		return this.name();
	}

	public String toString() {
		return this.name();
	}
	
}
