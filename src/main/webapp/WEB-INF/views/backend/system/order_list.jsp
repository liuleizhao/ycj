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
			 <form role="form" class="form-inline" action="${ctx }/backend/system/order/list" method="get" name="form" id="form">
			 	<input type="hidden" id="currentPage" name="currentPage" value="${currentPage }" />
                  <div class="form-group">
                  	  <label class="control-label">订单号</label>
                      <input type="text" name="orderNumber" value="${order.orderNumber }" class="form-control">
                  </div>
                  <div class="form-group">
                  	  <label class="control-label">预约号</label>
                      <input type="text" name="bookNumber" value="${order.bookNumber }" class="form-control">
                  </div>
                  <div class="form-group">
                  	  <label class="control-label">快递单号</label>
                      <input type="text" name="courierNumber" value="${order.courierNumber }" class="form-control">
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
										<th>订单号</th>
										<th>预约号</th>
										<th>快递单号</th>
										<th>收件人</th>
										<th>收件地址</th>
										<th>联系电话</th>
										<th>状态</th>
										<th>创建时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageView.list }" var="order">
										<tr>
											<td class="orderNumber">${order.orderNumber }</td>
											<td>${order.bookNumber }</td>
											<td class="courierNumber">${order.courierNumber }</td>
											<td>${order.consignee }</td>
											<td>${order.address }</td>
											<td>${order.phone }</td>
											<td>${order.state.description }</td>
											<td><fmt:formatDate value="${order.createDate }" pattern="yyyy-MM-dd HH:mm" /></td>
											<td id="${order.id }">
												<a class="details">详情</a>
												<c:if test="${'DDYJFZ' eq order.state }">
													&nbsp;&nbsp;<a class="send">发货</a>
												</c:if>
												<c:if test="${'FZYJ' eq order.state || 'FZLQ' eq order.state}">
													&nbsp;&nbsp;
													<a class="finish">完成</a>
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
<script>
$(function(){

	$(".details").on("click",function(){
		var id = $(this).parent().attr("id");
		window.location.href="${ctx }/backend/system/order/details?id="+id;
	})
	
	$(".send").on("click",function(){
		var id = $(this).parent().attr("id");
		openSendDialog(id)
	})
	
	$(".finish").on("click",function(){
		var id = $(this).parent().attr("id");
		var orderNumber = $(this).parent().find(".orderNumber").text()
		art.dialog.confirm("您确定要完成"+orderNumber+"订单吗", function() {
					$.post("${ctx}/backend/system/order/finish",{
						id:id,
					},function(data){
						$.dialog( {
								time : 3,
								content : data.message
							});
					})
				})
	})

})
		function openSendDialog(id){
		
				$.dialog.prompt('请填写订单号', function(courierNumber){
				    $.post("${ctx}/backend/system/order/send",{
						id:id,
						courierNumber:courierNumber
					},function(data){
						if(data.state == "00"){
							$("#"+id).parent().find(".courierNumber").text(courierNumber);
						}
						$.dialog( {
								time : 3,
								content : data.message
							});
					})
				}, '请输入快递订单编号');
			}
</script>

</html>
