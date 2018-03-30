<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}-资源列表</title>
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
<div class="wrapper wrapper-content fadeInRight">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			 <form role="form" class="form-inline" action="${ctx }/backend/system/user/list" method="get" name="form" id="form">
			 	<input type="hidden" id="currentPage" name="currentPage" value="${currentPage }" />
                  <div class="form-group">
                  	  <label class="control-label">账号名称</label>
                      <input type="text" name="account" value="${user.account }" class="form-control">
                  </div>
                  <div class="form-group">
                  	  <label class="control-label">姓名</label>
                      <input type="text" name="name" value="${user.name }" class="form-control">
                  </div>
                   <div class="form-group">
						<label class="control-label">用户类型</label>
                        <select class="form-control" name="userType">
                        		<option value="">请选择..</option>
                            <c:forEach items="${userTypeList }" var="entity">
								<option value="${entity.value }" <c:if test="${user.userType eq entity.value }">selected="selected"</c:if>>${entity.description}</option>
							</c:forEach>
                        </select>
                    </div>
                  <button class="btn btn-white" type="submit">查询</button>
                  <button class="btn btn-white" type="button" onclick="javascript:window.location.href='${ctx }/backend/system/user/add'">新增</button>
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
										<th>性别</th>
										<th>移动电话</th>
										<th>所属检测站</th>
										<th>用户类型</th>
										<th>email</th>
										<th>职位</th>
										<th>登陆次数</th>
										<th>用户状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageView.list }" var="user" varStatus="status">
										<tr>
											<td>${user.account }</td>
											<td>${user.name }</td>
											<td>${user.sex.description }</td>
											<td>${user.phone }</td>
											<td>${user.stationName }</td>
											<td>${user.userType.description }</td>
											<td>${user.email }</td>
											<td>${user.post }</td>
											<td>${user.loginCount }</td>
											<td>${user.status.description }</td>
											<td>
												<a href="${ctx }/backend/system/user/edit?userId=${user.id}">编辑</a>
												&nbsp; &nbsp;
												<a href="${ctx }/backend/system/user/authorize?userId=${user.id}">授权</a>
												&nbsp; &nbsp;
												<a href="javascript:deleteUser('${user.id}','${user.name}');">删除</a>
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
	<!-- 将common.js放置在最后 -->
	<script type="text/javascript">
		function deleteUser(id,name){
			art.dialog.confirm("您确定要删除用户【"+name+"】吗？", function() {
					location.href = "${ctx}/backend/system/user/delete?userId="+id;
				})
		}
	</script>

</html>
