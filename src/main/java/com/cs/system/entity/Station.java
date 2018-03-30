package com.cs.system.entity;

import java.util.Date;

import com.cs.common.entityenum.CommonStateEnum;
import com.cs.common.entityenum.DistrictEnum;
import com.cs.mvc.dao.BaseEntity;

public class Station extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String address;

    private String code;

    private String email;

    private String mobile;

    private String name;

    private String phone;

    private Double pointX;

    private Double pointY;

    private CommonStateEnum stationState;

    private DistrictEnum districtId;

    private String orgId;

    private Integer orderNum;

    private String serialNumber;

    private Integer greenMark;

    private String vehicleCharacters;

    private String driverTypes;

    private String fuelTypes;

    private String vehicleTypes;

    private String useCharaters;

    private String carTypes;

    private String viewName;

    private Integer lineNumber;

    private Integer maxNumber;

    private String newFlag;

    private String satutdayFlag;

    private Date createDate;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getPointX() {
		return pointX;
	}

	public void setPointX(Double pointX) {
		this.pointX = pointX;
	}

	public Double getPointY() {
		return pointY;
	}

	public void setPointY(Double pointY) {
		this.pointY = pointY;
	}

	public CommonStateEnum getStationState() {
		return stationState;
	}

	public void setStationState(CommonStateEnum stationState) {
		this.stationState = stationState;
	}

	public DistrictEnum getDistrictId() {
		return districtId;
	}

	public void setDistrictId(DistrictEnum districtId) {
		this.districtId = districtId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Integer getGreenMark() {
		return greenMark;
	}

	public void setGreenMark(Integer greenMark) {
		this.greenMark = greenMark;
	}

	public String getVehicleCharacters() {
		return vehicleCharacters;
	}

	public void setVehicleCharacters(String vehicleCharacters) {
		this.vehicleCharacters = vehicleCharacters;
	}

	public String getDriverTypes() {
		return driverTypes;
	}

	public void setDriverTypes(String driverTypes) {
		this.driverTypes = driverTypes;
	}

	public String getFuelTypes() {
		return fuelTypes;
	}

	public void setFuelTypes(String fuelTypes) {
		this.fuelTypes = fuelTypes;
	}

	public String getVehicleTypes() {
		return vehicleTypes;
	}

	public void setVehicleTypes(String vehicleTypes) {
		this.vehicleTypes = vehicleTypes;
	}

	public String getUseCharaters() {
		return useCharaters;
	}

	public void setUseCharaters(String useCharaters) {
		this.useCharaters = useCharaters;
	}

	public String getCarTypes() {
		return carTypes;
	}

	public void setCarTypes(String carTypes) {
		this.carTypes = carTypes;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Integer getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
	}

	public String getNewFlag() {
		return newFlag;
	}

	public void setNewFlag(String newFlag) {
		this.newFlag = newFlag;
	}

	public String getSatutdayFlag() {
		return satutdayFlag;
	}

	public void setSatutdayFlag(String satutdayFlag) {
		this.satutdayFlag = satutdayFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

  
}