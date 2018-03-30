package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;

/**
 * 
 * 深圳地区枚举
 */
public enum DistrictEnum implements Identifiable<String> {
	
	LUOHU("SZ01","罗湖区"),
	FUTIAN("SZ02","福田区"),
	NANSHAN("SZ03","南山区"),
	BAOAN("SZ04","宝安区"),
	LONGGANG("SZ05","龙岗区"),
	YANTIAN("SZ06","盐田区"),
	LONGHUA("SZ07","龙华区"),
	PINGSHAN("SZ08","坪山区"),
	GUANGMING("SZ09","光明新区"),
	DAPENG("SZ10","大鹏新区");
	
	private String index;
	private String description;

	private DistrictEnum(String index, String description) {
		this.index = index;
		this.description = description;
	}

	@Override
	public String getId() {
		return this.index;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	public static List<DistrictEnum> getAll() {
		return Arrays.asList(DistrictEnum.class.getEnumConstants());
	}

	public static DistrictEnum findByIndex(String index) {
		return EnumUtils.getEnum(DistrictEnum.class, index);
	}

	public String getValue() {
		return this.name();
	}

	public String toString() {
		return this.name();
	}

}
