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
					<c:when test="${carInfo == null}"><form action="${ctx }/wx/carInfo/add" method="post" id="form"></c:when>
					<c:otherwise><form action="${ctx }/wx/carInfo/edit" method="post" id="form"></c:otherwise> 
				</c:choose>
					<!-- id -->
					<c:if test="${carInfo != null}"><input name="id" type="hidden" value="${carInfo.id }"></c:if>
					
					<ul class="mui-table-view i-table-view">
						<li class="mui-table-view-cell">
							<span>新车</span>
							<input name="newflag" type="hidden" value="">
							<div class="mui-switch <c:if test="${'1' eq carInfo.newflag }">mui-active</c:if>">
								<div class="mui-switch-handle"></div>
							</div>
						</li>
					</ul>
					<h5 class="platNumber">号牌号码：</h5>
					<div class="mui-input-row platNumber">
						<input name="platNumber" class="mui-input-group i-col-10" type="hidden" value="${carInfo.platNumber }">
						<c:set var="platNumber_1" value="${fn:substring(carInfo.platNumber,0,1)}"></c:set>
						<c:set var="platNumber_2" value="${fn:substring(carInfo.platNumber,1,fn:length(carInfo.platNumber))}"></c:set>
						<select name="platNumber_1" class="i-select i-col-2">
							<option value="粤" <c:if test="${platNumber_1 eq '粤'}">selected</c:if>>粤</option>
							<option value="鄂" <c:if test="${platNumber_1 eq '鄂'}">selected</c:if>>鄂</option>
                         	<option value="豫" <c:if test="${platNumber_1 eq '豫'}">selected</c:if>>豫</option>
                         	<option value="皖" <c:if test="${platNumber_1 eq '皖'}">selected</c:if>>皖</option>
                         	<option value="赣" <c:if test="${platNumber_1 eq '赣'}">selected</c:if>>赣</option>
                         	<option value="冀" <c:if test="${platNumber_1 eq '冀'}">selected</c:if>>冀</option>
                         	<option value="鲁" <c:if test="${platNumber_1 eq '鲁'}">selected</c:if>>鲁</option>
                         	<option value="浙" <c:if test="${platNumber_1 eq '浙'}">selected</c:if>>浙</option>
                         	<option value="苏" <c:if test="${platNumber_1 eq '苏'}">selected</c:if>>苏</option>
                         	<option value="湘" <c:if test="${platNumber_1 eq '湘'}">selected</c:if>>湘</option>
                         	<option value="闽" <c:if test="${platNumber_1 eq '闽'}">selected</c:if>>闽</option>
                         	<option value="蒙" <c:if test="${platNumber_1 eq '蒙'}">selected</c:if>>蒙</option>
                         	<option value="京" <c:if test="${platNumber_1 eq '京'}">selected</c:if>>京</option>
                         	<option value="辽" <c:if test="${platNumber_1 eq '辽'}">selected</c:if>>辽</option>
                         	<option value="渝" <c:if test="${platNumber_1 eq '渝'}">selected</c:if>>渝</option>
                         	<option value="沪" <c:if test="${platNumber_1 eq '沪'}">selected</c:if>>沪</option>
                         	<option value="陕" <c:if test="${platNumber_1 eq '陕'}">selected</c:if>>陕</option>
                         	<option value="川" <c:if test="${platNumber_1 eq '川'}">selected</c:if>>川</option>
                         	<option value="黑" <c:if test="${platNumber_1 eq '黑'}">selected</c:if>>黑</option>
                         	<option value="晋" <c:if test="${platNumber_1 eq '晋'}">selected</c:if>>晋</option>
                         	<option value="桂" <c:if test="${platNumber_1 eq '桂'}">selected</c:if>>桂</option>
                         	<option value="吉" <c:if test="${platNumber_1 eq '吉'}">selected</c:if>>吉</option>
                         	<option value="宁" <c:if test="${platNumber_1 eq '宁'}">selected</c:if>>宁</option>
                         	<option value="贵" <c:if test="${platNumber_1 eq '贵'}">selected</c:if>>贵</option>
                         	<option value="琼" <c:if test="${platNumber_1 eq '琼'}">selected</c:if>>琼</option>
                         	<option value="甘" <c:if test="${platNumber_1 eq '甘'}">selected</c:if>>甘</option>
                         	<option value="青" <c:if test="${platNumber_1 eq '青'}">selected</c:if>>青</option>
                         	<option value="津" <c:if test="${platNumber_1 eq '津'}">selected</c:if>>津</option>
                         	<option value="云" <c:if test="${platNumber_1 eq '云'}">selected</c:if>>云</option>
                         	<option value="藏" <c:if test="${platNumber_1 eq '藏'}">selected</c:if>>藏</option>
                         	<option value="新" <c:if test="${platNumber_1 eq '新'}">selected</c:if>>新</option>
						</select>
						<input name="platNumber_2" class="mui-input-group i-col-10" type="text" value="${platNumber_2 }">
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
					
					<h5>使用性质：</h5>
					<div class="mui-input-row">
						<select name="useCharater" class="i-select">
							<c:forEach items="${useCharaterList }" var="useCharater">
							  <option value="${useCharater }"<c:if test="${carInfo.useCharater eq useCharater}">selected</c:if>>${useCharater.description }</option>
							</c:forEach>
						</select>
					</div>
					
					<h5 class="busCompanyId">所属公司（营转非大型载客汽车必须在车管所备案）：</h5>
					<div class="mui-input-row busCompanyId">
						<input name="busCompanyId" type="hidden">
						<input id="busCompanyId" disabled="disabled"  type="text" class="mui-input-group" placeholder="请选择..." readonly="readonly">
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
						<button type="button" class="mui-btn i-btn-bule save">保存</button>
					</div>
				</form>
			</div>
		</div>
	</body>
	<script data-main="${ctx }/static/wx/js/main" id="currentPage" target-module="car_info_detail"  src="${ctx }/static/wx/js/require.min.js"></script>
</html>






