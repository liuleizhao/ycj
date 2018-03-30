package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;

/**
 * 订单状态枚举
 */
public enum OrderStateEnum implements Identifiable<Integer> {

	DZF(1, "待支付"),
	YZF(2, "已支付"),
	BLZ(3,"办理中"),
	BLWC(4, "办理完成"),
	QX(5, "已取消"),
	GQ(6, "过期"),
	FQ(7, "废弃"),
	;
	/** 顺序 */
	private int id;

	/** 描述 */
	private String description;

	private OrderStateEnum(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public Integer getId() {
		return this.id;
	}
	
	public static List<OrderStateEnum> getAll() {
		return Arrays.asList(OrderStateEnum.class.getEnumConstants());
	}

	public static OrderStateEnum findByIndex(Integer id) {
		return EnumUtils.getEnum(OrderStateEnum.class, id);
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
