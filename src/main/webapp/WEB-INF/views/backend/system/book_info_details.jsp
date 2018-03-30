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
					<h5>预约信息详情</h5>
				</div>
				<div class="ibox-content">
					<div class="row form-body form-horizontal m-t">
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">预约号：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.bookNumber }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">预约人：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.bookerName }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">预约检测站：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.stationName }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">预约时间：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.bookTime }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">预约日期：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.bookDate }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">验证码：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.verifyCode }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">联系方式：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.mobile }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">预约状态：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.bookState.description }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">预约渠道：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.bookChannel.description }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">证件类型：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.idTypeId.description}</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">证件号码：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.idNumber }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">新旧车：</label>
								<div class="col-sm-9">
									<p class="form-control-static">
										<c:if test="${bookInfo.newflag == 1}">新车</c:if>
										<c:if test="${bookInfo.newflag == 0}">旧车</c:if>
									</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">车牌号：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.platNumber }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">车架号：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.frameNumber }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">发动机号码：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.engineNumber }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">车辆类型：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.vehicleType.description }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">车辆性质：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.vehicleCharacter.description}</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">号牌种类：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.carTypeId.description }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">驱动类型：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.driverType.description }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">燃料类型：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.fuelType.description }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">使用性质：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${bookInfo.useCharater }</p>
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