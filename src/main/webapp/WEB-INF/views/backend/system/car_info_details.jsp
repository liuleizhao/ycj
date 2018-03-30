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
					<h5>车辆信息详情</h5>
				</div>
				<div class="ibox-content">
					<div class="row form-body form-horizontal m-t">
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">新旧车标志：</label>
								<div class="col-sm-9">
									<p class="form-control-static">
										<c:if test="${carInfo.newflag == 1}">新车</c:if>
										<c:if test="${carInfo.newflag == 0}">旧车</c:if>
									</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">车牌号：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${carInfo.platNumber }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">车架号：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${carInfo.frameNumber }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">发动机号：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${carInfo.engineNumber }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">车辆类型：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${carInfo.vehicleType.description }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">车辆性质：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${carInfo.vehicleCharacter.description }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">号牌种类：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${carInfo.carTypeId.description }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">驱动类型：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${carInfo.driverType.description }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">燃料类型：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${carInfo.fuelType.description }</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">使用性质：</label>
								<div class="col-sm-9">
									<p class="form-control-static">${carInfo.useCharater.description}</p>
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