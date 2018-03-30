<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>预约信息填写</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
	
	<link href="${ctx }/static/wx/css/mui.picker.min.css" rel="stylesheet" />
	<link href="${ctx }/static/wx/css/mui.poppicker.css" rel="stylesheet" />
</head>
	<body> 
		<div class="mui-content">
			<div class="i-content-padded">
				<form action="${ctx }/wx/book/submit" method="post" id="form">
					<h5>姓名：</h5>
					<div class="mui-input-row">
						<input id="bookerName" name="bookerName" type="text" class="mui-input-group" placeholder="请输入姓名">
					</div>
					<h5>手机号：</h5>
					<div class="mui-input-row">
						<input id="mobile" name="mobile" type="text" class="mui-input-group" placeholder="请输入手机号">
					</div>
					<h5>证件类型：</h5>
					<div class="mui-input-row">
						<select id="idTypeId" name="idTypeId" class="i-select">
							<c:forEach items="${idTypeList }" var="idType">
						  		<option value="${idType }">${idType.description }</option>
							</c:forEach>
						</select>
					</div>
					<h5>证件号码：</h5>
					<div class="mui-input-row">
						<input id="idNumber" name="idNumber" type="text" class="mui-input-group" placeholder="证件号码">
					</div>
					<h5>预约日期：</h5>
					<div class="mui-input-row">
						<input id="bookDate" disabled="disabled"  name="bookDate" type="text" class="mui-input-group" placeholder="请选择..." readonly="readonly">
					</div>
					<h5>预约时间：</h5>
					<div class="mui-input-row">
						<input id="bookTime" disabled="disabled"  name="bookTime" type="text" class="mui-input-group" placeholder="请选择..." readonly="readonly">
					</div>
					<div class="i-btn-box">
						<button type="button" disabled="disabled" class="mui-btn i-btn-bule submit">立即预约</button>
					</div>
				</form>
			</div>
		</div>
	</body>
	<script data-main="${ctx }/static/wx/js/main" id="currentPage" target-module="own_book_input" src="${ctx }/static/wx/js/require.min.js"></script>
</html>