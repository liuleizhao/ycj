<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}-预约信息详情</title>
<%@ include file="/WEB-INF/common/common.jsp"%>

</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>订单信息详情</h5>
				</div>
				<div class="ibox-content">
					<div class="row form-body form-horizontal m-t">
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">订单编号：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${order.orderNumber }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">预约号：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${order.bookNumber }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">订单状态：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${order.state.description }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">收件人：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${order.consignee }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">收件地址：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${order.address }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">联系方式：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${order.phone }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">创建时间：</label>
								<div class="col-sm-9">
									<p class="form-control-static"><fmt:formatDate value="${order.createDate }" pattern="yyyy-MM-dd HH:mm" /></p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">预约验证时间：</label>
								<div class="col-sm-9">
									<p class="form-control-static"><fmt:formatDate value="${order.verifiedDate }" pattern="yyyy-MM-dd HH:mm" /></p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">检测时间：</label>
								<div class="col-sm-9">
									<p class="form-control-static"><fmt:formatDate value="${order.testDate }" pattern="yyyy-MM-dd HH:mm" /></p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">发送副证时间：</label>
								<div class="col-sm-9">
									<p class="form-control-static"><fmt:formatDate value="${order.deliveryDate }" pattern="yyyy-MM-dd HH:mm" /></p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">快递单号：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${order.courierNumber }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">订单完成时间：</label>
								<div class="col-sm-9">
									<p class="form-control-static"><fmt:formatDate value="${order.finishDate }" pattern="yyyy-MM-dd HH:mm" /></p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>