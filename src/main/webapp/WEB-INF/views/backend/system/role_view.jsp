<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}-查看角色</title>
<%@ include file="/WEB-INF/common/common.jsp"%>
<link href="${ctx }/static/backend/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<script src="${ctx }/static/backend/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx }/static/backend/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
</head>
<style>
	.float-e-margins .btn {
     margin-bottom: 0px; 
}
</style>
<body class="gray-bg">
<div class="wrapper wrapper-content fadeInRight">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			 <form role="form" class="form-inline" action="${ctx }/backend/system/role/views" method="get" name="form" id="form">
                  <input type="hidden" name="roleId" value="${role.id }" >
                  <div class="form-group">
                  		<label class="control-label">角色名称</label>
			                  <input type="text" value="${role.name }" class="form-control">
			        </div>
			         <div class="form-group">
                  		<label class="control-label">排列顺序</label>
			                  <input type="text" value="${role.orderNumber }" class="form-control">
			        </div>
			         <div class="form-group">
                  		<label class="control-label">角色描述</label>
			                  <input type="text" value="${role.description }" class="form-control">
			        </div>
              </form>
		</div>
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-12">
					<div class="context">
						<div class="table_box">
							<table data-toggle="table">
								<thead>
									<tr>
										<th>账户名称</th>
										<th>姓名</th>
										<th>所属检测站</th>
										<th>用户类型</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageView.list }" var="user" varStatus="status">
										<tr>
											<td>${user.account }</td>
											<td>${user.name }</td>
											<td>${user.stationName }</td>
											<td>${user.userType.description }</td>
											<td>无</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<%@ include file="/WEB-INF/common/pagination.jsp"%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>