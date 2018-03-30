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
<!-- 表单验证 -->
<script src="${ctx }/static/backend/js/plugins/validate/jquery.validate.min.js"></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
    	<div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>用户<c:choose><c:when test="${user == null }">添加</c:when><c:otherwise>编辑</c:otherwise></c:choose></h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" action="${ctx }/backend/system/user/<c:choose><c:when test="${user.id == null }">add</c:when><c:otherwise>edit</c:otherwise></c:choose>" method="post" name="form" id="form">
                            <c:if test="${user != null }">
	                            <input type="hidden" name="id" value="${user.id }">
                            </c:if>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">账号名称：</label>
                                <div class="col-sm-8">
                                	<input type="text" <c:choose><c:when test="${user == null }">id="account" name="account"</c:when><c:otherwise>disabled="disabled"</c:otherwise></c:choose> class="form-control"  value="${user.account }"/>
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i>由至少6位字母，数字，下划线组成</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓名：</label>
                                <div class="col-sm-8">
                                	<input name="name" id="name" type="text" value="${user.name }" class="form-control"/>
                                </div>
                            </div>
                            <c:if test="${user == null }">
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">密码：</label>
	                                <div class="col-sm-8">
	                                    <input id="password" name="password" class="form-control" type="password">
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">确认密码：</label>
	                                <div class="col-sm-8">
	                                    <input id="confirm_pwd" name="confirm_pwd" class="form-control" type="password">
	                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 请再次输入您的密码</span>
	                                </div>
	                            </div>
                            </c:if>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手机号：</label>
                                <div class="col-sm-8">
		                            <input name="phone" id="phone" type="text" value="${user.phone }" class="form-control"/>
                                </div>
                            </div>
                            <%-- <div class="form-group">
                                <label class="col-sm-3 control-label">身份证号：</label>
                                <div class="col-sm-8">
                                	<input name="idNumber" id="idNumber" type="text" value="${user.idNumber }" class="form-control"/>
                                </div>
                            </div> --%>
                            <%-- <div class="form-group">
								<label class="col-sm-3 control-label">性别：</label>
								<div class="col-sm-8">
									<c:forEach items="${sexList }" var="sex">
										<label class="checkbox-inline i-checks">
											<input type="radio" name="sex" value="${sex }" <c:if test="${user.sex ==sex }">checked="checked"</c:if> />${sex.description}
										</label>
									</c:forEach>
								</div>
							</div> --%>
                            <%-- <div class="form-group">
                                <label class="col-sm-3 control-label">职务：</label>
                                <div class="col-sm-8">
                                	<input name="post" id="post" type="text" value="${user.post }" class="form-control"/>
                                </div>
                            </div> --%>
                            <%-- <div class="form-group">
                                <label class="col-sm-3 control-label">邮箱：</label>
                                <div class="col-sm-8">
                                	<input name="email" id="email" type="email" value="${user.email }"class="form-control"/>
                                </div>
                            </div> --%>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">用户类型</label>
                                <div class="col-sm-8"> 
                                    <select class="form-control m-b" name="userType" id="userType">
                                    	<option value="" >请选择...</option>
                                    	<c:forEach items="${userTypeList }" var="entity">
												<option value="${entity }" <c:if test="${user.userType == entity }">selected="selected"</c:if>>
													${entity.description}
												</option>
										</c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div id="station_form" class="form-group">
                                <label class="col-sm-3 control-label">所属检测站</label>
                                <div class="col-sm-8"> 
                                    <select class="form-control m-b" name="stationId" id="stationId">
                                    	<option value="" >请选择...</option>
                                    	<c:forEach items="${stationInfoList }" var="entity">
											<option value="${entity.id }" <c:if test="${user.stationId == entity.id }">selected="selected"</c:if>>
												${entity.orderNum }:${ entity.name}
											</option>
										</c:forEach>
                                    </select>
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
	show_station();
	$("#userType").on("change",function(){
		show_station();
	})
	function show_station(){
		if($("#userType").val() == "STATION"){
			$("#station_form").show()
		}else{
			$("#station_form").hide()
		}
	}
	
	$.validator.setDefaults({	
		highlight: function(e) {
			$(e).closest(".form-group").removeClass("has-success").addClass("has-error")
		},
		success: function(e) {
			e.closest(".form-group").removeClass("has-error").addClass("has-success")
		},
		errorElement: "span",
		errorPlacement: function(e, r) {
			e.appendTo(r.is(":radio") || r.is(":checkbox") ? r.parent().parent().parent() : r.parent())
		},
		errorClass: "help-block m-b-none",
		validClass: "help-block m-b-none"
	}), $().ready(function() {
		var e = "<i class='fa fa-times-circle'></i> ";
		$("#form").validate({
			rules: {
				account:{
					required:true,
					isAccount:true,
					remote:"${ctx}/backend/system/user/check"
					},
				name: "required",
				password:{
					required:true,
					isPassword:true
					},
				confirm_pwd:{
					required:true,
			        equalTo: "#password"
			      },
				phone:{
					required:true,
					isPhone:true
					},
				idNumber:{
					required:true,
					isIdNumber:true
					},
				sex:"required",
				post:"required",
				email:{
			        required: true,
			        email: true
			      }
			},
			messages: {
				account: {
			        required: e + "请输入账号",
			        remote: e + "账号已存在，请重新输入"
			      },
				name: e + "请输入您的名字",
				password: {required:e + "请输入密码"},
				confirm_pwd:e+"两次密码输入不一致",
				phone: {required:e + "请输入联系方式"},
				idNumber: {required:e + "请输入身份证号码"},
				sex:e + "请选择选择您的性别",
				post:e + "请输入您的职务",
				email:{
			        required: e + "请输入您的邮箱",
			        email: e + "邮箱格式错误，请重新输入"
			      }
			}
		})
		$.validator.addMethod("isAccount", function(value, element) {   
		    var tel = /^[a-zA-Z0-9_]{6,16}$/;
		    return this.optional(element) || (tel.test(value));
		}, e +"请输入正确的账号格式");
		$.validator.addMethod("isPassword", function(value, element) {   
		    var tel = /^.*(?=.{6,})(?=.*\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^&*? ]).*$/;
		    return this.optional(element) || (tel.test(value));
		}, e +"密码最少6位，包括至少1个字母，1个数字，1个特殊字符");
		$.validator.addMethod("isPhone", function(value, element) {   
		    var tel = /^(1[0-9])\d{9}$/;
		    return this.optional(element) || (tel.test(value));
		}, e +"手机号格式错误");
		$.validator.addMethod("isIdNumber", function(value, element) {   
		    var tel = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
		    return this.optional(element) || (tel.test(value));
		}, e +"身份证格式错误");
	});
	});
</script>
</html>