<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>车辆管理</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
</head>
	<body> 
		<div class="mui-content">
			<div class="i-content-padded">
				<ul class="mui-table-view i-ul">
					<c:forEach items="${carInfoList }" var="item" >
						<li class="mui-table-view-cell i-li">
							<div>
								<c:choose>
									<c:when test="${'' eq item.platNumber || null == item.platNumber }">
										车架号：${item.frameNumber }
									</c:when>
									<c:otherwise>
										车牌号：${item.platNumber }
									</c:otherwise>
								</c:choose>
							</div>
							<div car_id="${item.id }" class="i-li-content">
			                    <span class="mui-h6">车辆类型：${item.vehicleType.description }</span>
			                    <div class="i-btn del">删除<span class="mui-icon mui-icon-trash"></span></div>
			                   	<div class="i-btn edit">编辑<span class="mui-icon mui-icon-compose"></span></div>
			                </div>
						</li>
					</c:forEach>
				</ul>
				<div class="i-btn-box">
					<button type="button" class="mui-btn i-btn-bule add">新增车辆</button>
				</div>
			</div>
		</div>
	</body>
	<script data-main="${ctx }/static/wx/js/main" id="currentPage" target-module="car_info_list" src="${ctx }/static/wx/js/require.min.js"></script>
</html>