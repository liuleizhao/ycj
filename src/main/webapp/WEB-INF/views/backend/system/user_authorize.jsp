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
    <div class="wrapper wrapper-content animated fadeInRight">
    <div class="col-sm-12">
    <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>用户授权</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" action="${ctx }/backend/system/user/authorize" method="post" name="form" id="form">
							<input type="hidden" name="userId" value="${user.id }"> 
                            <div class="form-group">
                                <label class="col-sm-3 control-label">账号名称：</label>
                                <div class="col-sm-8">
                                	<input type="text" disabled="disabled" class="form-control"  value="${user.account }"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓名：</label>
                                <div class="col-sm-8">
                                	<input type="text" value="${user.name }" class="form-control"/>
                                </div>
                            </div>
                            
                            <div class="form-group">
								<label class="col-sm-3 control-label">角色</label>
								<div class="col-sm-8">
									<c:forEach items="${roleList}" var="role" varStatus="statu">
										<label class="checkbox-inline i-checks">
										<input type="checkbox" name="roleIds" value="${role.id}" 
										<c:forEach items="${userRoleList }" var="uRole">
												<c:if test="${uRole.id==role.id}">checked</c:if></c:forEach> />
										${role.name}
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

