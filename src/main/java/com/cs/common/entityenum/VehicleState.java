package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;

/**
 * 是否逻辑删除
 * 如果被用户删除为0
 * 还出现在用户的车辆列表为1
 * @author huang
 *
 */
public enum VehicleState implements Identifiable<Integer>{
	USING(1,"在用"),
	ABANDON(0,"已被用户删除废弃");

	/** 顺序 */
	private int index;

	/** 描述 */
	private String description;

	private VehicleState(int index, String description) {
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

	public static List<VehicleState> getAll() {
		return Arrays.asList(VehicleState.class.getEnumConstants());
	}

	public static VehicleState findByIndex(Integer index) {
		return EnumUtils.getEnum(VehicleState.class, index);
	}


}
