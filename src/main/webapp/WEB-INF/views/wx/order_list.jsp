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
		<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
			<div class="mui-scroll">
				<ul class="mui-table-view i-ul">
					<c:forEach items="${pageView.list }" var="order">
						<li class="mui-table-view-cell i-li">
							<div>
								<span class="mui-h5">预约号：${order.bookInfo.platNumber }</span>
								<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">${order.state.description }</div>
							</div>
							<hr style="height:3px;border:none;border-top:1px double #EEE;">
							<div>
								<span class="mui-h6">支付金额：${order.amountPayment }</span>
							</div>
							<div>
								<span class="mui-h6">预约号码：${order.bookNumber }</span>
							</div>
							<div>
								<span class="mui-h6">预约时间：${order.bookInfo.bookDate }&nbsp;${order.bookInfo.bookTime } </span>
							</div>
							<div>
								<span class="mui-h6">预约检测站：${order.bookInfo.stationName } </span>
							</div>
							<hr style="height:3px;border:none;border-top:1px double #EEE;">
							<div>
								<c:if test="${order.state eq 'DZF'}">
									<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">支付<span style="font-size: 16px;" class="mui-icon mui-icon-paperplane"></span></div>
								</c:if>
								<c:if test="${order.state eq 'DZF' or order.state eq 'YZF'}">
									<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">取消<span style="font-size: 16px;" class="mui-icon mui-icon-trash"></span></div>
								</c:if>
								
								<c:if test="${order.state eq 'QX' or order.state eq 'BLWC'}">
									<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">删除<span style="font-size: 16px;" class="mui-icon mui-icon-trash"></span></div>
								</c:if>
								
								<c:if test="${order.state eq 'GQ'}">
									<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">选择预约时间<span style="font-size: 16px;" class="mui-icon mui-icon-arrowright"></span></div>
								</c:if>
								<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">联系我们<span style="font-size: 16px;" class="mui-icon mui-icon-phone"></span></div>
								<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">一键导航<span style="font-size: 16px;" class="mui-icon mui-icon-location"></span></div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<script data-main="${ctx }/static/wx/js/main" id="currentPage" target-module="order_list"  src="${ctx }/static/wx/js/require.min.js"></script>
		
	</body>

</html>