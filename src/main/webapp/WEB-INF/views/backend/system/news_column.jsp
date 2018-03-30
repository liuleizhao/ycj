<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}</title>
<%@ include file="/WEB-INF/common/common.jsp"%>

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
                        <h5>用户新闻资讯栏目编辑</h5>
                    </div>
                    <div class="ibox-content">
                    	<c:set var="url" value="${ctx }/backend/system/newsColumn/add"></c:set>
                        <c:if test="${null != newsColumn.id}">
                        	<c:set var="url" value="${ctx }/backend/system/newsColumn/edit"></c:set>
                        </c:if>
                        <form class="form-horizontal m-t" action="${url }" method="post" name="form" id="form">
	                        <input type="hidden" name="id" value="${newsColumn.id }">
                          
                            <div class="form-group">
                                <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-8">
                                	<input name="name" id="name" type="text" class="form-control" value="${newsColumn.name }" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">序号：</label>
                                <div class="col-sm-8">
                                    <input id="orderNumber" name="orderNumber" type="text" class="form-control"  value="${newsColumn.orderNumber }" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">描述：</label>
                               <div class="col-sm-8">
                                    <input id="description" name="description" type="text" class="form-control"  value="${newsColumn.description }" >
                                	<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 请输入对栏目的描述</span>
                                </div>
                            </div>
                            <div class="form-group">
								<label class="col-sm-3 control-label">状态：</label>
								<div class="col-sm-8">
									<c:forEach items="${stateList }" var="item">
										<label class="checkbox-inline i-checks">
											<input type="radio" id="state" name="state" value="${item }" <c:if test="${newsColumn.state == item }">checked="checked"</c:if> />${item.description}
										</label>
									</c:forEach>
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
<script type="text/javascript">
	$(function() {
		$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})
	})
</script>
</html>
