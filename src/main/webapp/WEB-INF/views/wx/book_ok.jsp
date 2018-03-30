<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>预约完成</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
</head>
	<body>
		<div class="mui-content">
			<div class="mui-table-view">
				<ul class="mui-table-view">
					<li class="mui-table-view-cell">
						预约号码
						<div class="i-content-text">${bookInfo.bookNumber }</div>
					</li>
					<li class="mui-table-view-cell">
						预约号码
						<div class="i-content-text">${bookInfo.bookNumber }</div>
					</li>
					<li class="mui-table-view-cell">
						预约日期
						<div class="i-content-text">${bookInfo.bookDate }</div>
					</li>
					<li class="mui-table-view-cell">
						预约时间
						<div class="i-content-text">${bookInfo.bookTime }</div>
					</li>
					<li class="mui-table-view-cell">
						受理单位
						<div class="i-content-text">${station.name }</div>
					</li>
					<li class="mui-table-view-cell">
						详细地址
						<div class="i-content-text">${station.address }</div>
					</li>
					<li class="mui-table-view-cell">
						<p style="color: red">温馨提示：请在预约时间段内前来办理,逾期将无法办理</p>
					</li>
				</ul>
				<div class="i-btn-box i-content-padded">
					<button type="button" class="mui-btn i-btn-bule" onclick="window.location.href = '${ctx }/wx/index'">主页</button>
				</div>
		</div>
	</div>
</body>
	<script data-main="${ctx }/static/wx/js/main" id="currentPage" target-module="book_ok"  src="${ctx }/static/wx/js/require.min.js"></script>
</html>
