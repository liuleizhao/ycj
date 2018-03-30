<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}</title>
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
<div class="wrapper wrapper-content animated fadeIn">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			 <form role="form" class="form-inline" action="${ctx }/backend/carInfo/list" method="get" name="form" id="form">
			 	<input type="hidden" id="currentPage" name="currentPage" value="${currentPage }" />
                  <div class="form-group">
                  	  <label class="control-label">车牌号</label>
                      <input type="text" name="platNumber" value="${carInfo.platNumber }" class="form-control">
                  </div>
                  <div class="form-group">
                  	  <label class="control-label">车架号</label>
                      <input type="text" name="frameNumber" value="${carInfo.frameNumber }" class="form-control">
                  </div>
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
										<th>车牌号</th>
										<th>车架号</th>
										<th>车辆类型</th>
										<th>车辆性质</th>
										<th>号牌种类</th>
										<th>创建时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageView.list }" var="carInfo">
										<tr>
											<td>${carInfo.platNumber }</td>
											<td>${carInfo.frameNumber }</td>
											<td>${carInfo.vehicleType.description }</td>
											<td>${carInfo.vehicleCharacter.description }</td>
											<td>${carInfo.carTypeId.description }</td>
											<td><fmt:formatDate value="${carInfo.createDate }" pattern="yyyy-MM-dd HH:mm" /></td>
											<td>
												<a href="${ctx }/backend/system/carInfo/details?id=${carInfo.id}">详情</a>
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

</html>
