<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}-微信用户车辆信息</title>
<%@ include file="/WEB-INF/common/common.jsp"%>
<link href="${ctx }/static/backend/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<script src="${ctx }/static/backend/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx }/static/backend/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${ctx }/static/backend/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
</head>
<style>
	.float-e-margins .btn {
     margin-bottom: 0px; 
}
</style>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight" style="padding: 0px;">
                <div class="ibox float-e-margins" style="margin-bottom: 0px;">
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-12">
					<div class="context">
						<div class="table_box">
							<table data-toggle="table">
								<thead>
									<tr>
										<th>号牌种类</th>
										<th>号牌号码</th>
										<th>车架号（后四位）</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${carInfos }" var="carInfo" varStatus="status">
										<tr>
											<td>${carInfo.carTypeId.description }</td>
											<td>${carInfo.platNumber }</td>
											<td>${carInfo.frameNumber }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<%-- <%@ include file="/WEB-INF/common/pagination.jsp"%> --%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
