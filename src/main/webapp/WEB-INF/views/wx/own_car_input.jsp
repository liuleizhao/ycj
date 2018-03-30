<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>车辆信息</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
		<link href="${ctx }/static/wx/css/mui.picker.min.css" rel="stylesheet" />
	<link href="${ctx }/static/wx/css/mui.poppicker.css" rel="stylesheet" />
</head>
	<body> 
		<div class="mui-content">
			<div class="i-content-padded">
				<c:choose>
					<c:when test="${carInfo == null}"><form action="${ctx }/wx/book/input" method="post" id="form"></c:when>
					<c:otherwise><form action="${ctx }/wx/carInfo/edit" method="post" id="form"></c:otherwise> 
				</c:choose>
					<!-- id -->
					<c:if test="${carInfo != null}"><input name="id" type="hidden" value="${carInfo.id }"></c:if>
					
					
					<ul class="mui-table-view i-table-view">
						<li class="mui-table-view-cell mui-checkbox mui-right">
							<input name="newflag" value="0" type="hidden">
							<label>新车</label>
							<input id="newflag" type="checkbox">
						</li>
					</ul>
					<h5 class="platNumber">号牌号码：<div class="choose mui-text-right mui-pull-right">选择车俩<span class="mui-icon mui-icon-arrowright"></span></div></h5>
					<div class="mui-input-row platNumber">
						<input name="platNumber" class="mui-input-group i-col-10" type="hidden" value="${carInfo.platNumber }">
						<c:set var="platNumber_1" value="${fn:substring(carInfo.platNumber,0,1)}"></c:set>
						<c:set var="platNumber_2" value="${fn:substring(carInfo.platNumber,1,2)}"></c:set>
						<c:set var="platNumber_3" value="${fn:substring(carInfo.platNumber,2,fn:length(carInfo.platNumber))}"></c:set>
						<select name="platNumber_1" class="i-select i-col-2">
							<c:if test="${platNumber_1 != '' }">
	                         	<option value="${platNumber_1 }" selected>${platNumber_1 }</option>
							</c:if>
						</select>
						<select name="platNumber_2" class="i-select i-col-2">
							<c:if test="${platNumber_2 != '' }">
								<option value="${platNumber_12 }" selected>${platNumber_2 }</option>
							</c:if>
						</select>
						<input name="platNumber_3" class="mui-input-group i-col-9" type="text" value="${platNumber_3 }">
					</div>
					<h5 class="frameNumber">车架号(车辆识别号)后4位：</h5>
					<div class="mui-input-row">
						<input name="frameNumber" type="text" class="mui-input-group" value="${carInfo.frameNumber }">
					</div>
					<h5>车辆类型：</h5>
					<div class="mui-input-row">
						<select name="vehicleType" class="i-select">
							<c:forEach items="${vehicleTypeList }" var="vehicleType">
							  <option value="${vehicleType }"<c:if test="${carInfo.vehicleType eq vehicleType}">selected</c:if>>${vehicleType.description }</option>
							</c:forEach>
						</select>
					</div>
					<h5>号牌种类：</h5>
					<div class="mui-input-row">
						<select name="carTypeId" class="i-select">
							<c:forEach items="${carTypeList }" var="carType">
							  <option class="${carType.belong }" value="${carType }"<c:if test="${carInfo.carTypeId eq carType}">selected</c:if>>${carType.description }</option>
							</c:forEach>
						</select>
					</div>
					<h5>车辆性质：</h5>
					<div class="mui-input-row">
						<select name="vehicleCharacter" class="i-select">
							<c:forEach items="${vehicleCharacterList }" var="vehicleCharacter">
							  <option class="${vehicleCharacter.belong }" value="${vehicleCharacter }"<c:if test="${carInfo.vehicleCharacter eq vehicleCharacter}">selected</c:if>>${vehicleCharacter.description }</option>
							</c:forEach>
						</select>
					</div>
					<h5>驱动类型：</h5>
					<div class="mui-input-row">
						<select name="driverType" class="i-select">
							<c:forEach items="${driverTypeList }" var="driverType">
							  <option class="${driverType.belong }" value="${driverType }"<c:if test="${carInfo.driverType eq driverType}">selected</c:if>>${driverType.description }</option>
							</c:forEach>
						</select>
					</div>
					<h5>燃油类型：</h5>
					<div class="mui-input-row">
						<select name="fuelType" class="i-select">
							<c:forEach items="${fuelTypeList }" var="fuelType">
							  <option class="${fuelType.belong }" value="${fuelType }"<c:if test="${carInfo.fuelType eq fuelType}">selected</c:if>>${fuelType.description }</option>
							</c:forEach>
						</select>
					</div>
					
					
					<div class="i-btn-box">
						<button type="button" class="mui-btn i-btn-bule next">下一步</button>
					</div>
				</form>
			</div>
		</div>
	</body>
	<script data-main="${ctx }/static/wx/js/main" id="currentPage" target-module="own_car_input"  src="${ctx }/static/wx/js/require.min.js"></script>
</html>
