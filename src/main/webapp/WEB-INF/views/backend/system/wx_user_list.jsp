<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}-微信用户管理</title>
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
			 <form role="form" class="form-inline" action="${ctx }/backend/system/wxUser/list" method="get" name="form" id="form">
			 	<input type="hidden" id="currentPage" name="currentPage" value="${currentPage }" />
                  <div class="form-group">
                  	  <label class="control-label">昵称</label>
                      <input type="text" name="nickName" value="${wxuser.nickName }" class="form-control">
                  </div>
                  	<c:if test="${stationList != null}">
	                   <div class="form-group">
	                  	  <label class="control-label">检测站</label>
	                  	  <select name="stationId" class="form-control">
	                  	  	<c:forEach items="${stationList }" var="station">
	                  	  		<option value="${station.id }"<c:if test="${wxuser.stationId eq station.id }">selected</c:if>>${station.viewName }</option>
	                  	  	</c:forEach>
	                  	  </select>
	                  </div>
                  	</c:if>
                  <button class="btn btn-white" type="submit">查询</button>
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
										<th>昵称</th>
										<th>性别</th>
										<th>登陆次数</th>
										<th>创建时间</th>
										<th>车辆信息</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageView.list }" var="wxuser" varStatus="status">
										<tr>
											<td>${wxuser.nickName }</td>
											<td>${wxuser.sex.description }</td>
											<td>${wxuser.loginCount }</td>
											<td><fmt:formatDate value="${wxuser.createDate }" pattern="yyyy-MM-dd HH:mm" /></td>
											<td>
												<a href="javascript:opencarDialog('${wxuser.id }')">查看</a>
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
		function opencarDialog(id){
				var url = "${ctx }/backend/system/wxUser/carInfo?wxuserId="+id;
				var dialog = $.dialog.open(url, {
				title: "车辆信息",
				drag: false,
    			resize: false,
				lock: true,
				width:600
			    });
		}
		
	</script>

</html>
