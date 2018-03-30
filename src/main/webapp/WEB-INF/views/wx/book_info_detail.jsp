<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>车辆信息</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
</head>
	<body> 
		<div class="mui-content">
			<div class="i-content-padded">
				<c:choose>
					<c:when test="${carInfo == null}"><form action="${ctx }/wx/carInfo/add" method="post" id="form"></c:when>
					<c:otherwise><form action="${ctx }/app/carInfo/edit" method="post" id="form"></c:otherwise> 
				</c:choose>
					<!-- id -->
					<c:if test="${carInfo != null}"><input name="id" type="hidden" value="${carInfo.id }"></c:if>
					<ul class="mui-table-view i-table-view">
						<li class="mui-table-view-cell">
							<span></span>
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
                         	<option value="津" <c:if test="${platNumber_1 eq '鄂'}">selected</c:if>>津</option>
                         	<option value="云" <c:if test="${platNumber_1 eq '津'}">selected</c:if>>云</option>
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
						  <option value="1"<c:if test="${carInfo.vehicleType eq '1'}">selected</c:if>>小型汽车</option>
						  <option value="2"<c:if test="${carInfo.vehicleType eq '2'}">selected</c:if>>大型汽车</option>
						  <option value="3"<c:if test="${carInfo.vehicleType eq '3'}">selected</c:if>>挂车</option>
						</select>
					</div>
					<h5>号牌种类：</h5>
					<div class="mui-input-row">
						<select name="carTypeId" class="i-select">
							<option class="small" value="312AED23657445C194540C794DBDBDB9" <c:if test="${carInfo.carTypeId eq '312AED23657445C194540C794DBDBDB9'}">selected</c:if>>小型汽车（蓝底白字）</option>
							<option class="small" value="763FF1EEE4BB4C3995B402E8A7D2C550" <c:if test="${carInfo.carTypeId eq '763FF1EEE4BB4C3995B402E8A7D2C550'}">selected</c:if>>小型新能源汽车（渐变绿底黑字）</option>
							
							<option class="big" value="5AA667F13C2143F0A41C6940E74B127E" <c:if test="${carInfo.carTypeId eq '5AA667F13C2143F0A41C6940E74B127E'}">selected</c:if>>大型汽车（黄底黑字）</option>
							<option class="big" value="0D7E3ABB86774FD1927EE05CF82FDA4B"<c:if test="${carInfo.carTypeId eq '0D7E3ABB86774FD1927EE05CF82FDA4B'}">selected</c:if>>低速车（黄底黑字黑框线）</option>
							<option class="big" value="0EBEC3DB9EAA40A7B97DDD547FF58F51"<c:if test="${carInfo.carTypeId eq '0EBEC3DB9EAA40A7B97DDD547FF58F51'}">selected</c:if>>大型新能源汽车（黄绿双拼色底黑字）</option>
							
							<option class="trailer" value="0AAA03BC4AE74531BF1FE45A03C38577"<c:if test="${carInfo.carTypeId eq '0AAA03BC4AE74531BF1FE45A03C38577'}">selected</c:if>>挂车</option>
							
							<option class="small_big" value="B4394B3F2F3B4E78911713C3D54D4196"<c:if test="${carInfo.carTypeId eq 'B4394B3F2F3B4E78911713C3D54D4196'}">selected</c:if>>领馆汽车（黑底白字、红领字）</option>
							<option class="small_big" value="A4FA9722C81C408B8A5BB65F8BD9C9B1"<c:if test="${carInfo.carTypeId eq 'A4FA9722C81C408B8A5BB65F8BD9C9B1'}">selected</c:if>>外籍汽车（黑底白字）</option>
							<option class="small_big" value="D7FAFC5A68004845864C42345B58D7BC"<c:if test="${carInfo.carTypeId eq 'D7FAFC5A68004845864C42345B58D7BC'}">selected</c:if>>教练汽车（黄底黑字黑框线）</option>
							<option class="small_big" value="31CCBA351E0A4B7AA1BAFBDE2AA93161"<c:if test="${carInfo.carTypeId eq '31CCBA351E0A4B7AA1BAFBDE2AA93161'}">selected</c:if>>警用汽车</option>
						
						</select>
					</div>
					<h5>车辆性质：</h5>
					<div class="mui-input-row">
						<select name="vehicleCharacter" class="i-select">
							<option class="small" value="1"<c:if test="${carInfo.vehicleCharacter eq '1'}">selected</c:if>>小型载客汽车</option>
							<option class="small" value="2"<c:if test="${carInfo.vehicleCharacter eq '2'}">selected</c:if>>小型载货汽车(含专项作业车)</option>
							<option value="small" value="3" <c:if test="${carInfo.vehicleCharacter eq '3'}">selected</c:if>>校车（小型）</option>
							
							<option class="big" value="4"<c:if test="${carInfo.vehicleCharacter eq '4'}">selected</c:if>>中型载客汽车</option>
							<option class="big" value="5"<c:if test="${carInfo.vehicleCharacter eq '5'}">selected</c:if>>大型载客汽车</option>
							<option class="big" value="6"<c:if test="${carInfo.vehicleCharacter eq '6'}">selected</c:if>>中型载货汽车</option>
							<option class="big" value="7"<c:if test="${carInfo.vehicleCharacter eq '7'}">selected</c:if>>重型载货汽车</option>
							<option class="big" value="8"<c:if test="${carInfo.vehicleCharacter eq '8'}">selected</c:if>>专项作业车</option>
							<option value="big" value="9" <c:if test="${carInfo.vehicleCharacter eq '9'}">selected</c:if>>校车（大型）</option>
							<option class="big" value="10"<c:if test="${carInfo.vehicleCharacter eq '10'}">selected</c:if>>无轨电车</option>
							<option class="big" value="12"<c:if test="${carInfo.vehicleCharacter eq '12'}">selected</c:if>>低速载货汽车</option>
							
							<option class="trailer" value="11"<c:if test="${carInfo.vehicleCharacter eq '11'}">selected</c:if>>普通挂车</option>
							
							<option value="13"<c:if test="${carInfo.vehicleCharacter eq '13'}">selected</c:if>>危化品运输车</option>
							
						</select>
					</div>
					
					<h5>驱动类型：</h5>
					<div class="mui-input-row">
						<select name="driverType" class="i-select">
							<option class="small" value="0"<c:if test="${carInfo.driverType eq '0'}">selected</c:if>>两驱（含非全时四驱）</option>
							<option class="small" value="1"<c:if test="${carInfo.driverType eq '1'}">selected</c:if>>全时四驱</option>
							
							<option class="big" value="3"<c:if test="${carInfo.driverType eq '3'}">selected</c:if>>两轴</option>
							<option class="big" value="5"<c:if test="${carInfo.driverType eq '5'}">selected</c:if>>三轴及以上</option>
							<option class="big" value="6"<c:if test="${carInfo.driverType eq '6'}">selected</c:if>>单轴轴重超15吨</option>
							
							<option class="trailer" value="2"<c:if test="${carInfo.driverType eq '2'}">selected</c:if>>两轴及以下</option>
							<option class="trailer" value="4"<c:if test="${carInfo.driverType eq '4'}">selected</c:if>>三轴及以上</option>
							<option class="trailer" value="7"<c:if test="${carInfo.driverType eq '7'}">selected</c:if>>并装双轴及以上</option>
						</select>
					</div>
					
					<h5>燃油类型：</h5>
					<div class="mui-input-row">
						<select name="fuelType" class="i-select">
							<option class="small_big" value="1"<c:if test="${carInfo.fuelType eq '1'}">selected</c:if>>汽油、混合动力、纯电</option>
							<option class="small_big" value="2"<c:if test="${carInfo.fuelType eq '2'}">selected</c:if>>柴油、柴电混合（总质量小于等于3500KG）</option>
							<option value="3"<c:if test="${carInfo.fuelType eq '3'}">selected</c:if>>柴油、柴电混合（总质量大于3500KG）</option>
						</select>
					</div>
					
					
					<div class="i-btn-box">
						<button type="button" class="mui-btn i-btn-bule save">保存</button>
					</div>
				</form>
			</div>
		</div>
	</body>
	<script data-main="${ctx }/static/wx/js/main" id="currentPage" target-module="book_info_detail" src="${ctx }/static/wx/js/require.min.js"></script>
</html>






