<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}-角色列表</title>
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
			 <form role="form" class="form-inline" action="${ctx }/backend/system/role/list" method="get" name="form" id="form">
                  <div class="form-group">
                  	  <label class="control-label">角色名称</label>
                      <input type="text" name="name" value="${roleName }" class="form-control">
                  </div>
                 
                  <button class="btn btn-white" type="submit">查询</button>
                  <button class="btn btn-white" type="button" onclick="javascript:window.location.href='${ctx }/backend/system/role/add'">新增</button>
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
										<th>角色名称</th>
										<th>角色描述</th>
										<th>创建者</th>
										<th>创建时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageView.list }" var="role" varStatus="status">
										<c:choose>
											<c:when test="${status.index %2 == 1 }">
												<tr id="${role.id }">
											</c:when>
											<c:otherwise>
												<tr id="${role.id }">
											</c:otherwise>
										</c:choose>
										<td>${role.name }</td>
										<td>${role.description }</td>
			
						                <td>${role.creator }</td>
										<td><fmt:formatDate value="${role.createdDate }" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td>
											<a href="${ctx }/backend/system/role/edit?roleId=${role.id}">编辑</a> 
											&nbsp; &nbsp; 
											<a href="${ctx }/backend/system/role/views?roleId=${role.id}">查看</a> 
											&nbsp; &nbsp; 
											<c:if test="${role.name ne '系统管理员'}">
												<a href="javascript:deleteRole('${role.id }','${role.name }');">删除</a>
											</c:if>
										</td>
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
<script type="text/javascript">
	function deleteRole(roleId, roleName) {
		art.dialog.confirm("您确定要删除用户【"+roleName+"】吗？", function() {
					location.href = "${ctx }/backend/system/role/delete?roleId="+roleId;
				})
	}
</script>
</html>