<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>预约信息</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
</head>
	<body>
		<div class="mui-content">
			<div class="i-content-padded">
				<ul class="mui-table-view  i-ul">
					<c:forEach items="${bookInfoList }"  var="bookInfo">
						<li class="mui-table-view-cell  i-li">
							<div>
								<span class="mui-h5">预约号：${bookInfo.bookNumber }</span>
								<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">${bookInfo.bookState.description }</div>
							</div>
							<hr style="height:3px;border:none;border-top:1px double #EEE;">
							<div>
								<span class="mui-h6">预约人：${bookInfo.bookerName }</span>
							</div>
							<div>
								<span class="mui-h6">预约日期：${bookInfo.bookDate }</span>
							</div>
							<div>
								<span class="mui-h6">预约时间：${bookInfo.bookTime }</span>
							</div>
							<hr style="height:3px;border:none;border-top:1px double #EEE;">
							<div>
								<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">详情<span style="font-size: 16px;" class="mui-icon mui-icon-arrowright"></span></div>
								<c:if test="${bookInfo.bookState eq 'YYZ'}">
									<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">取消<span style="font-size: 16px;" class="mui-icon mui-icon-trash"></span></div>
								</c:if>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</body>
	<script data-main="${ctx }/static/wx/js/main" id="currentPage" target-module="book_info_list"  src="${ctx }/static/wx/js/require.min.js"></script>
</html>