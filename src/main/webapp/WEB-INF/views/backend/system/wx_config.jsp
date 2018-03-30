<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}</title>
<%@ include file="/WEB-INF/common/common.jsp"%>

<script type="text/javascript"src="${ctx }/static/backend/js/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"src="${ctx }/static/backend/js/plugins/ckeditor/ckeditor.js"></script>

<!-- iCheck插件 -->
<link href="${ctx }/static/backend/css/plugins/iCheck/custom.css"rel="stylesheet">
<script src="${ctx }/static/backend/js/plugins/iCheck/icheck.min.js"></script>
<!-- 表单验证 -->
<script src="${ctx }/static/backend/js/plugins/validate/jquery.validate.min.js"></script>
</head>
<body class="gray-bg">
		<div class="wrapper wrapper-content animated fadeInRight" style="padding: 0px;">
    	<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins" style="margin-bottom: 0px;">
				<div class="ibox-content">
					<form class="form-horizontal m-t" action="${ctx }/backend/system/station/wxConfig" method="post" name="form" id="form">
						<input type="hidden" name="id" value="${wxConfig.id }">
						<input type="hidden" name="stationId" value="${wxConfig.stationId }">
						
						<div class="form-group">
							<label class="col-sm-3 control-label">*appId：</label>
							<div class="col-sm-8">
								<input name="appId" id="appId" type="text" class="form-control"
									value="${wxConfig.appId }" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">*appSecret：</label>
							<div class="col-sm-8">
								<input name="appSecret" id="appSecret" type="text"
									class="form-control" value="${wxConfig.appSecret }" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">token：</label>
							<div class="col-sm-8">
								<input name="token" id="token" type="text" class="form-control"
									value="${wxConfig.token }" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">aesKey：</label>
							<div class="col-sm-8">
								<input name="aesKey" id="aesKey" type="text"
									class="form-control" value="${wxConfig.aesKey }" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">mchId：</label>
							<div class="col-sm-8">
								<input name="mchId" id="mchId" type="text" class="form-control"
									value="${wxConfig.mchId }" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">apiKey：</label>
							<div class="col-sm-8">
								<input name="apiKey" id="apiKey" type="text"
									class="form-control" value="${wxConfig.apiKey }" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
$(function(){

		api = art.dialog.open.api, // 			art.dialog.open扩展方法
		form = $('#form');
		if (!api){
			return;
		}

		// 操作对话框
		api.button({
			name : '保存',
			callback : function() {
			var succeed = false;
				$.ajax({url: form.attr("action"),  
				    type: "post",  
				    dataType: "json",  
				    async: false,  
				    data: form.serialize(),  
				    success: function(data){  
				       if(data.state == "00"){
								succeed = true;
							}else{
								$.dialog( {time : 3,content : data.message});
								succeed = false;
							}
				    }  
				}); 
				return succeed;
			},
			focus : true
		}, {
			name : '取消'
		});
})
</script>
</html>
