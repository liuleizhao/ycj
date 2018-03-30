<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}-资源列表</title>
<link href="${ctx }/static/backend/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/static/backend/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${ctx }/static/backend/css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>
	<body class="gray-bg">
		<div class="wrapper wrapper-content animated fadeInRight" style="padding: 0px;">
    	<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins" style="margin-bottom: 0px;">
                    <div class="ibox-content">
                        <form method="post" class="form-horizontal" action="${ctx }/backend/system/resource/edit" name="form" id="resourceAddForm">
							<input type="hidden" id="id" value="${resource.id }" />
							<input type="hidden" id="resource_type" value="${resource.resourceType.value}" />
							<input type="hidden" id="method_type" value="${resource.methodType.value}" />
							<div class="form-group">
								<label class="col-sm-2 control-label">父资源</label>
								<div class="col-sm-10">
									<input class="form-control" value="${parentName }" disabled="disabled" type="text" />
									<input id="parent_id" type="hidden" value="${resource.parentId }" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">资源名称</label>
								<div class="col-sm-10">
									<input class="form-control" id="name" type="text" value="${resource.name }" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">排列顺序</label>
								<div class="col-sm-10">
									<input class="form-control" id="orderNumber" type="text" value="${resource.orderNumber }" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">资源路径</label>
								<div class="col-sm-10">
									<input class="form-control" id="path" type="text" value="${resource.path }" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">资源类型</label>
								<div class="col-sm-10">
									<c:forEach items="${resourceTypes }" var="resourceType" varStatus="s">
										<label class="checkbox-inline i-checks">
						                 <input name="resource_type" type="radio" value="${resourceType.value }"<c:if test="${resource.resourceType.value eq resourceType.value }">checked="checked"</c:if>>
						                 ${resourceType.description }</label>
									</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">提交方式</label>
								<div class="col-sm-10">
									<c:forEach items="${methodTypes }" var="methodType" varStatus="s">
										<label class="checkbox-inline i-checks">
						                  <input name="method_type" type="radio" value="${methodType.value }"<c:if test="${resource.methodType.value eq methodType.value }">checked="checked"</c:if>/>
						                  ${methodType.description }
						                </label>
									</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">图标</label>
								<div class="col-sm-10">
									<input class="form-control" id="icon" type="text" value="${resource.icon }" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">资源描述</label>
								<div class="col-sm-10">
									<input class="form-control" id="description" type="text" value="${resource.description }" />
								</div>
							</div>
						</form>
					</div>
                </div>
			</div>
		</div>
	</div>
</body>
<script src="${ctx }/static/backend/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/static/backend/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${ctx }/static/backend/js/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})
		
		$("input:radio[name='resource_type']").on('ifChecked', function(event){
			$(this).attr("checked",true);
			$("#resource_type").attr("value", $(this).val())
		})
		$("input:radio[name='method_type']").on('ifChecked', function(event){
			$(this).attr("checked",true);
			$("#method_type").attr("value", $(this).val())
		})
	});
</script>
</html>