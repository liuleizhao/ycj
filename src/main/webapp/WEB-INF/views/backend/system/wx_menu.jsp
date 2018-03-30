<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}</title>
<%@ include file="/WEB-INF/common/common.jsp"%>

<script type="text/javascript" src="${ctx }/static/backend/js/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/static/backend/js/plugins/ckeditor/ckeditor.js"></script>

<!-- iCheck插件 -->
<link href="${ctx }/static/backend/css/plugins/iCheck/custom.css" rel="stylesheet">
<script src="${ctx }/static/backend/js/plugins/iCheck/icheck.min.js"></script>
<!-- 表单验证 -->
<script src="${ctx }/static/backend/js/plugins/validate/jquery.validate.min.js"></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
    	<div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>新闻咨询编辑</h5>
                    </div>
                    <div class="ibox-content">
                    
                    	<c:set var="url" value="${ctx }/backend/system/wxMenu/add"></c:set>
                        <c:if test="${null != wxMenu.id}">
                        	<c:set var="url" value="${ctx }/backend/system/wxMenu/edit"></c:set>
                        </c:if>
                        <form class="form-horizontal m-t" action="${url }" method="post" name="form" id="form">
	                        <input type="hidden" name="id" value="${wxMenu.id }">
                            
							<div class="form-group">
                                <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-8">
                                	<input name="name" id="name" type="text" class="form-control" value="${wxMenu.name }" />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">图标：</label>
                                <div class="col-sm-8">
                                	<input name="icon" id="icon" type="text" class="form-control" value="${wxMenu.icon }" />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">序号：</label>
                                <div class="col-sm-8">
                                	<input name="orderNumber" id="orderNumber" type="text" class="form-control" value="${wxMenu.orderNumber }" />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">href：</label>
                                <div class="col-sm-8">
                                	<input name="href" id="href" type="text" class="form-control" value="${wxMenu.href }" />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">type：</label>
                                <div class="col-sm-8">
                                	<input name="type" id="type" type="text" class="form-control" value="${wxMenu.type }" />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">保存</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
      </div>
</body>
</html>
