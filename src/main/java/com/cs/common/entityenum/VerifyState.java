package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;

/**
 * 车辆信息是否已验证，验证为1
 * 未验证为0
 * @author huang
 *
 */
public enum VerifyState implements Identifiable<Integer>{
	
	NO_VERIFY(0,"未验证"),
	IS_VERIFY(1,"已验证"),;

	/** 顺序 */
	private int index;

	/** 描述 */
	private String description;

	private VerifyState(int index, String description) {
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

	public static List<VerifyState> getAll() {
		return Arrays.asList(VerifyState.class.getEnumConstants());
	}

	public static VerifyState findByIndex(Integer index) {
		return EnumUtils.getEnum(VerifyState.class, index);
	}


}
