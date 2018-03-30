package com.cs.system.entity;


import java.util.Date;

import com.cs.common.entityenum.BookChannelEunm;
import com.cs.common.entityenum.BookStateEnum;
import com.cs.common.entityenum.CarTypeEnum;
import com.cs.common.entityenum.CheckStateEnum;
import com.cs.common.entityenum.DriverTypeEnum;
import com.cs.common.entityenum.FuelTypeEnum;
import com.cs.common.entityenum.IdTypeEnum;
import com.cs.common.entityenum.UseCharaterEnum;
import com.cs.common.entityenum.VehicleCharacterEnum;
import com.cs.common.entityenum.VehicleTypeEnum;
import com.cs.mvc.dao.BaseEntity;
public class BookInfo extends BaseEntity{

	private static final long serialVersionUID = 973989842549733116L;
	
	//车辆类型
	private CarTypeEnum carTypeId;
	/** 号牌号码*/
    private String platNumber;
    /** 车架号*/
    private String frameNumber;
    /** 预约手机号*/
    private String mobile;
    /** 预约天*/
    private String bookDate;
    /** 预约时间*/
    private String bookTime;
    /** 检测站ID*/
    private String stationId;
    /** 预约状态*/
    private BookStateEnum bookState;
    /** 预约号*/
    private String bookNumber;
    /** 创建时间*/
    private Date createDate;
    /** 预约渠道*/
    private BookChannelEunm bookChannel;
    /** 预约人名称*/
    private String bookerName;
    /** 证件类型ID*/
    private IdTypeEnum idTypeId;
    /** 身份证号*/
    private String idNumber;
    /** 使用性质*/
    private UseCharaterEnum useCharater;
    /** 车辆类型（自定义）*/
    private VehicleTypeEnum vehicleType;

    /** 发动机号*/
    private String engineNumber;
    /** 驱动类型*/
    private DriverTypeEnum driverType;
    /** 燃油类型*/
    private FuelTypeEnum fuelType;
    /** 车辆类型名称*/
    private String carTypeName;
    /** 检测站名称*/
    private String stationName;
    /** 证件名称*/
    private String idTypeName;
    /** 预约人ID，后台预约*/
    private String userId;
    /** 预约姓名，后台预约*/
    private String userName;
    /** 车牌性质*/
    private VehicleCharacterEnum vehicleCharacter;
    /** 请求IP*/
    private String requestIp;

    /** 检测时间*/
    private Date checkTime;
    /** 实际检测站名称*/
    private String checkStation;
    /** 检测序列号*/
    private String checkSerialNumber;
    /** 检测标记*/
    private Integer checkOperationMark;
    
    /** 检测状态*/
    private CheckStateEnum checkState;
    
    /** 是否新车 1，旧车 0*/
	private String newflag = "0";

	/** 验证码*/
	private String verifyCode;
	
	private String otherIdNumber;
	
    private String compApplyFormId;
    
    /** 兼容开始时间*/
    private String compatibleStartDate;
    
    /** 兼容结束时间*/
    private String compatibleEndDate;
    
    /** 客运企业ID，营转非大型载客汽车必填 */
    private String busCompanyId;
    
    //预约日期区间查询
    private String sDate;
    private String eDate;

	public CarTypeEnum getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(CarTypeEnum carTypeId) {
		this.carTypeId = carTypeId;
	}

	public String getPlatNumber() {
		return platNumber;
	}

	public void setPlatNumber(String platNumber) {
		this.platNumber = platNumber;
	}

	public String getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBookDate() {
		return bookDate;
	}

	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	public String getBookTime() {
		return bookTime;
	}

	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public BookStateEnum getBookState() {
		return bookState;
	}

	public void setBookState(BookStateEnum bookState) {
		this.bookState = bookState;
	}

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BookChannelEunm getBookChannel() {
		return bookChannel;
	}

	public void setBookChannel(BookChannelEunm bookChannel) {
		this.bookChannel = bookChannel;
	}

	public String getBookerName() {
		return bookerName;
	}

	public void setBookerName(String bookerName) {
		this.bookerName = bookerName;
	}

	public IdTypeEnum getIdTypeId() {
		return idTypeId;
	}

	public void setIdTypeId(IdTypeEnum idTypeId) {
		this.idTypeId = idTypeId;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public UseCharaterEnum getUseCharater() {
		return useCharater;
	}

	public void setUseCharater(UseCharaterEnum useCharater) {
		this.useCharater = useCharater;
	}

	public VehicleTypeEnum getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleTypeEnum vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public DriverTypeEnum getDriverType() {
		return driverType;
	}

	public void setDriverType(DriverTypeEnum driverType) {
		this.driverType = driverType;
	}

	public FuelTypeEnum getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelTypeEnum fuelType) {
		this.fuelType = fuelType;
	}

	public String getCarTypeName() {
		return carTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getIdTypeName() {
		return idTypeName;
	}

	public void setIdTypeName(String idTypeName) {
		this.idTypeName = idTypeName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public VehicleCharacterEnum getVehicleCharacter() {
		return vehicleCharacter;
	}

	public void setVehicleCharacter(VehicleCharacterEnum vehicleCharacter) {
		this.vehicleCharacter = vehicleCharacter;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckStation() {
		return checkStation;
	}

	public void setCheckStation(String checkStation) {
		this.checkStation = checkStation;
	}

	public String getCheckSerialNumber() {
		return checkSerialNumber;
	}

	public void setCheckSerialNumber(String checkSerialNumber) {
		this.checkSerialNumber = checkSerialNumber;
	}

	public Integer getCheckOperationMark() {
		return checkOperationMark;
	}

	public void setCheckOperationMark(Integer checkOperationMark) {
		this.checkOperationMark = checkOperationMark;
	}

	public CheckStateEnum getCheckState() {
		return checkState;
	}

	public void setCheckState(CheckStateEnum checkState) {
		this.checkState = checkState;
	}

	public String getNewflag() {
		return newflag;
	}

	public void setNewflag(String newflag) {
		this.newflag = newflag;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getOtherIdNumber() {
		return otherIdNumber;
	}

	public void setOtherIdNumber(String otherIdNumber) {
		this.otherIdNumber = otherIdNumber;
	}

	public String getCompApplyFormId() {
		return compApplyFormId;
	}

	public void setCompApplyFormId(String compApplyFormId) {
		this.compApplyFormId = compApplyFormId;
	}

	public String getCompatibleStartDate() {
		return compatibleStartDate;
	}

	public void setCompatibleStartDate(String compatibleStartDate) {
		this.compatibleStartDate = compatibleStartDate;
	}

	public String getCompatibleEndDate() {
		return compatibleEndDate;
	}

	public void setCompatibleEndDate(String compatibleEndDate) {
		this.compatibleEndDate = compatibleEndDate;
	}

	public String getBusCompanyId() {
		return busCompanyId;
	}

	public void setBusCompanyId(String busCompanyId) {
		this.busCompanyId = busCompanyId;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
    
    
  
}