package com.cs.system.entity;

import java.util.Date;

import com.cs.common.entityenum.CarTypeEnum;
import com.cs.common.entityenum.DriverTypeEnum;
import com.cs.common.entityenum.FuelTypeEnum;
import com.cs.common.entityenum.UseCharaterEnum;
import com.cs.common.entityenum.VehicleCharacterEnum;
import com.cs.common.entityenum.VehicleTypeEnum;
import com.cs.mvc.dao.BaseEntity;

public class CarInfo extends BaseEntity{
	
	private static final long serialVersionUID = 6447077076904117126L;

	private String userId;

    private String newflag;

    private String platNumber;

    private String frameNumber;

    private String engineNumber;

    private VehicleTypeEnum vehicleType;

    private VehicleCharacterEnum vehicleCharacter;

    private CarTypeEnum carTypeId;

    private DriverTypeEnum driverType;

    private FuelTypeEnum fuelType;

    private UseCharaterEnum useCharater;

    private String busCompanyId;

    private Date createDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNewflag() {
		return newflag;
	}

	public void setNewflag(String newflag) {
		this.newflag = newflag;
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

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public VehicleTypeEnum getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleTypeEnum vehicleType) {
		this.vehicleType = vehicleType;
	}

	public VehicleCharacterEnum getVehicleCharacter() {
		return vehicleCharacter;
	}

	public void setVehicleCharacter(VehicleCharacterEnum vehicleCharacter) {
		this.vehicleCharacter = vehicleCharacter;
	}

	public CarTypeEnum getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(CarTypeEnum carTypeId) {
		this.carTypeId = carTypeId;
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

	public UseCharaterEnum getUseCharater() {
		return useCharater;
	}

	public void setUseCharater(UseCharaterEnum useCharater) {
		this.useCharater = useCharater;
	}

	public String getBusCompanyId() {
		return busCompanyId;
	}

	public void setBusCompanyId(String busCompanyId) {
		this.busCompanyId = busCompanyId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

  
}