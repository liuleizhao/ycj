<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}-预约信息列表</title>
<%@ include file="/WEB-INF/common/common.jsp"%>
    

<link href="${ctx }/static/backend/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<script src="${ctx }/static/backend/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx }/static/backend/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${ctx }/static/backend/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctx }/static/backend/js/plugins/My97DatePicker/WdatePicker.js"></script>
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
			 <form role="form" class="form-inline" action="${ctx }/backend/system/bookInfo/list" method="get" name="form" id="form">
			 	<input type="hidden" id="currentPage" name="currentPage" value="${currentPage }" />
                  <div class="form-group">
                  	  <label class="control-label">预约号</label>
                      <input type="text" name="bookNumber" value="${bookInfo.bookNumber }" class="form-control">
                  </div>&nbsp;
                  <div class="form-group">
                  	  <label class="control-label">预约姓名</label>
                      <input type="text" name="bookerName" value="${bookInfo.bookerName }" class="form-control">
                  </div>&nbsp;
                  <div class="form-group">
                  	  <label class="control-label">预约日期</label>
                      <input class="form-control" type="text" name="sDate" value="${bookInfo.sDate }" onclick="WdatePicker({startDate:'%y-%M-%d',minDate:'{%y-2}-%M-%d',maxDate:'%y-%M-%d'});">&nbsp;~&nbsp;
                      <input class="form-control" type="text" name="eDate" value="${bookInfo.eDate }" onclick="WdatePicker({startDate:'%y-%M-%d',minDate:'{%y-2}-%M-%d',maxDate:'%y-%M-%d'});">
                  </div>&nbsp;
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
										<th>预约号</th>
										<th>姓名</th>
										<th>检测站</th>
										<th>联系方式</th>
										<th>预约日期</th>
										<th>预约时间</th>
										<th>预约状态</th>
										<th>创建时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageView.list }" var="bookInfo" varStatus="status">
										<tr>
											<td>${bookInfo.bookNumber }</td>
											<td>${bookInfo.bookerName }</td>
											<td>${bookInfo.stationName }</td>
											<td>${bookInfo.mobile }</td>
											<td>${bookInfo.bookDate }</td>
											<td>${bookInfo.bookTime }</td>
											<td>${bookInfo.bookState.description }</td>
											<td><fmt:formatDate value="${bookInfo.createDate }" pattern="yyyy-MM-dd HH:mm" /></td>
											<td>
												<a href="${ctx }/backend/system/bookInfo/details?id=${bookInfo.id}">详情</a>
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
