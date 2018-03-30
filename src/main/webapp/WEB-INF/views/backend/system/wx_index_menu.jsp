<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}-用户添加/编辑</title>
<%@ include file="/WEB-INF/common/common.jsp"%>

<!-- iCheck插件 -->
<link href="${ctx }/static/backend/css/plugins/iCheck/custom.css" rel="stylesheet">
<script src="${ctx }/static/backend/js/plugins/iCheck/icheck.min.js"></script>

</head>
		
		<body class="gray-bg">
		<div class="wrapper wrapper-content animated fadeInRight" style="padding: 0px;">
    	<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins" style="margin-bottom: 0px;">
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" action="${ctx }/backend/system/station/indexMenu" method="post" name="form" id="form">
							<input type="hidden" name="stationId" value="${station.id }"> 
                            <div class="form-group">
                                <label class="col-sm-3 control-label">检测站名称：</label>
                                <div class="col-sm-8">
                                	<input type="text" disabled="disabled" class="form-control"  value="${station.name }"/>
                                </div>
                            </div>
                            
                            <div class="form-group">
								<label class="col-sm-3 control-label">菜单</label>
								<div class="col-sm-8">
									<c:forEach items="${allMenuList}" var="menu" varStatus="statu">
										<label class="checkbox-inline i-checks">
										<input type="checkbox" name="menuIds" value="${menu.id}" 
										<c:forEach items="${stationMenuList }" var="smenu">
												<c:if test="${smenu.id==menu.id}">checked</c:if></c:forEach> />
										${menu.name}
										</label>
									</c:forEach>
								</div>
							</div>
                        </form>
                    </div>
                </div>
            </div>
      </div>
 </body>
 <script type="text/javascript">
$(function() {
	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})
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
	
})
</script>
</html>

