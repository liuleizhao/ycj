<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>预约渠道</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
</head>
	<body class="i-chancel-body"> 
		<div class="mui-content">
			<div class="i-content-padded">
			<header class="i-chancel-header"></header>
				<div class="i-btn-box">
					<button type="button" class="own mui-btn i-big_btn-bule">委托预约办理<br/>(更便捷)</button>
					<button type="button" class="offic mui-btn i-big_btn-bule">车管所网站预约</button>
				</div>
			</div>
		</div>
	</body>
	<script data-main="${ctx }/static/wx/js/main" id="currentPage" target-module="own_book_way" src="${ctx }/static/wx/js/require.min.js"></script>
</html>