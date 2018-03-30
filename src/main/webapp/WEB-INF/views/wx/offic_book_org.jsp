<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>预约机构</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
</head>
	<body>
		<div class="mui-content">
			<div class="mui-content-padded">


		<form action="${ctx}/front/welcome!jszBook.action" method="post" id="jszForm">
		<s:token></s:token>
			
			<h5>预约办理单位：</h5>
			<div class="mui-input-row">
				<select id="org_id" name="driveInfo.organization.id" onchange="select_org(this.value);" class="i-select">
					<c:forEach var="org" varStatus="varStatus"   items="${list}">
						<option name="org" value="${org.id}" textstr="${org.name}" >${org.name}</option>
					</c:forEach>
				</select>
			</div>
			
			<h5>预约日期（工作日）：</h5>
			<div class="mui-input-row">
				<select id="dates" name="driveInfo.appointmentDate" class="i-select" onchange="select_date(this.value)"></select>
			</div>
			
			<h5>预约时间：</h5>
			<div class="mui-input-row">
				<select id="times"  name="driveInfo.appointmentTime" class="i-select"></select>
			</div>
			
			<div class="i-btn-box">
				<button type="button" class="mui-btn i-btn-bule" onclick="window.location.href = '${ctx }/wx/book/book_ok'">预约</button>
			</div>	
		</form>
		</div>
		</div>
	</body>
	<script data-main="${ctx }/static/wx/js/main" id="currentPage" target-module="offic_book_org" src="${ctx }/static/wx/js/require.min.js"></script>
</html>