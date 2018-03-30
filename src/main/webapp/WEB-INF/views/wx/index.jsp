<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>${station.viewName }</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
</head>
	<body> 
		<header class="i-index-header"></header>
		<div class="mui-content">
			<input id="appid" type="hidden" value="${wxJsapiConfig.appid }">
			<input id="noncestr" type="hidden" value="${wxJsapiConfig.noncestr }">
			<input id="timestamp" type="hidden" value="${wxJsapiConfig.timestamp }">
			<input id="signature" type="hidden" value="${wxJsapiConfig.signature }">
			<input id="jsApiList" type="hidden" value="${wxJsapiConfig.jsApiList }">
			
			<ul class="mui-table-view mui-grid-view i-business-list">
				<c:forEach items="${wxMenuList }" var="wxMenu">
					<li class="${wxMenu.icon } mui-table-view-cell mui-media mui-col-xs-6">
						<a href="${wxMenu.href }">${wxMenu.name }</a>
					</li>
				</c:forEach>
				<c:if test="${wxMenuList == null }">
					<li class="ic1 mui-table-view-cell mui-media mui-col-xs-6">
						<a href="#">公司简介</a>
					</li>
					<li class="ic2 mui-table-view-cell mui-media mui-col-xs-6">
						<a href="${ctx }/wx/book/chancel">年审预约</a>
					</li>
					<li class="ic4 mui-table-view-cell mui-media mui-col-xs-6">
						<a href="${ctx }/wx/bookInfo/list">预约记录</a>
					</li>
					<li class="ic3 mui-table-view-cell mui-media mui-col-xs-6">
						<a href="${ctx }/wx/carInfo/list">车辆管理</a>
					</li>
					<li class="ic6 mui-table-view-cell mui-media mui-col-xs-6">
						<a href="tel://88888888">联系我们</a>
					</li>
				</c:if>
				<c:if test="${wxMenuList != null }">
					<li class="ic5 mui-table-view-cell mui-media mui-col-xs-6">
						<a id="navigation" href="javascript:;" x="${station.pointX }" y="${station.pointY }" _name="${station.name }" address="${station.address }">一键导航</a>
					</li><!-- href 调用函数 -->
					<li class="ic6 mui-table-view-cell mui-media mui-col-xs-6">
						<a href="tel://${station.phone }">联系我们</a>
					</li>
				</c:if>
				
			</ul>
		</div>
	</body>
	<script data-main="${ctx }/static/wx/js/main" id="currentPage" target-module="index" src="${ctx }/static/wx/js/require.min.js"></script>
</html>