package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;

/**
 * 预约渠道枚举
 */

public enum BookChannelEunm  implements Identifiable<Integer> {

	WEB(1, "web客户端"), 
	WAP(2, "wap客户端"), 
	APP(3, "手机客户端"), 
	BACKEND(4,"大客户"),//绿色通道
	STATION(5,"绿色通道"),//大客户
	TP_APP(6, "第三方手机客户端"), 
	TP_WECHAT(7, "第三方微信端"), 
	TP_ALIPAY(8, "第三方支付宝端"), 
	QT(9, "其他");

	/** 顺序 */
	private int index;

	/** 描述 */
	private String description;

	private BookChannelEunm(int index, String description) {
		this.index = index;
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public int getIndex() {
		return this.index;
	}

	@Override
	public Integer getId() {
		return this.index;
	}

	public static List<BookChannelEunm> getAll() {
		return Arrays.asList(BookChannelEunm.class.getEnumConstants());
	}

	public static BookChannelEunm findByIndex(Integer index) {
		return EnumUtils.getEnum(BookChannelEunm.class, index);
	}

	/**
	 * 用于struts2标签。如s:select标签的自动回显
	 * 
	 * @return
	 */
	public String getValue() {
		return this.name();
	}

	public String toString() {
		return this.name();
	}

}
