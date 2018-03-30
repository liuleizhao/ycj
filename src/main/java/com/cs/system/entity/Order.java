package com.cs.system.entity;

import java.util.Date;

import com.cs.common.entityenum.OrderStateEnum;
import com.cs.mvc.dao.BaseEntity;

public class Order extends BaseEntity{

	//微信用户ID
    private String wxUserId;
    //订单号
    private String orderNumber;
    //预约号
    private String bookNumber;
    //订单状态
    private OrderStateEnum state;
    //收货人
    private String consignee;
    //收货地址
    private String consigneeAddress;
    //收货电话
    private String consigneePhone;
    //创建时间
    private Date createDate;
    //完成时间
    private Date finishDate;
    //物流号
    private String courierNumber;
    //支付时间
    private Date paymentData;
    //办理时间
    private Date handlingDate;
    //取消时间
    private Date cancelDate;
    //支付金额
    private Double amountPayment;
    //联系方式
    private String phone;
    
    private BookInfo bookInfo;
    
    private String VEHICLE_INFO_ID;

    public String getVEHICLE_INFO_ID() {
		return VEHICLE_INFO_ID;
	}

	public void setVEHICLE_INFO_ID(String vEHICLE_INFO_ID) {
		VEHICLE_INFO_ID = vEHICLE_INFO_ID;
	}

	public BookInfo getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}

	public String getWxUserId() {
        return wxUserId;
    }

    public void setWxUserId(String wxUserId) {
        this.wxUserId = wxUserId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public OrderStateEnum getState() {
        return state;
    }

    public void setState(OrderStateEnum state) {
        this.state = state;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }

    public Date getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(Date paymentData) {
        this.paymentData = paymentData;
    }

    public Date getHandlingDate() {
        return handlingDate;
    }

    public void setHandlingDate(Date handlingDate) {
        this.handlingDate = handlingDate;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Double getAmountPayment() {
        return amountPayment;
    }

    public void setAmountPayment(Double amountPayment) {
        this.amountPayment = amountPayment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}