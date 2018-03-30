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
    <div class="wrapper wrapper-content animated fadeIn">
    	<div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>检测站信息</h5>
                         <a class="config" style="float: right;">公众号配置</a>
                         <a class="menu" style="float: right; margin-right: 10px;">首页菜单配置</a>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" action="${ctx }/backend/system/station/details" method="post" name="form" id="form">
	                        <input type="hidden" name="id" value="${station.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-8">
                                	<input type="text" class="form-control" id="name" name="name" <c:if test="${station != null }"> disabled="disabled" </c:if> value="${station.name }"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">编号：</label>
                                <div class="col-sm-8">
                                	<input type="text" class="form-control" id="code" name="code" <c:if test="${station != null }"> disabled="disabled" </c:if> value="${station.code }"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">简称：</label>
                                <div class="col-sm-8">
                                	<input type="text" class="form-control" id="viewName" name="viewName" value="${station.viewName }"/>
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">地址：</label>
                                <div class="col-sm-8">
                                	<input type="text" class="form-control" id="address" name="address" value="${station.address }"/>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系方式：</label>
                                <div class="col-sm-8">
                                	<input type="text" class="form-control" id="phone" name="phone" value="${station.phone }"/>
                                	<span class="help-block m-b-none"><i class="fa fa-info-circle"></i>请填写一个联系方式</span>
                                </div>
                            </div>
                           <%--  <div class="form-group">
                                <label class="col-sm-3 control-label">邮箱：</label>
                                <div class="col-sm-8">
                                	<input type="text" class="form-control" id="email" name="email" value="${station.email }" />
                                </div>
                            </div> --%>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">经度：</label>
                                <div class="col-sm-8">
                                	<input type="text" class="form-control" id="pointX" name="pointX" value="${station.pointX }" />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">纬度：</label>
                                <div class="col-sm-8">
                                	<input type="text" class="form-control" id="pointY" name="pointY" value="${station.pointY }" />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">所在区：</label>
                                <div class="col-sm-8"> 
                                    <select class="form-control m-b" name="districtId" id="districtId">
                                    	<option value="" >请选择...</option>
                                    	<c:forEach items="${districtList }" var="district">
											<option value="${district }" <c:if test="${station.districtId == district }">selected="selected"</c:if>>
												${district.description }
											</option>
										</c:forEach>
                                    </select>
                                </div>
                            </div>
                            
                            
                           <%-- <div class="form-group">
                                <label class="col-sm-3 control-label">状态：</label>
                                <div class="col-sm-8">
                                	 <select class="form-control m-b" name="stationState" id="stationState">
                                    	<option value="" >请选择...</option>
                                    	<c:forEach items="${stateList }" var="state">
											<option value="${state }" <c:if test="${station.stationState == state }">selected="selected"</c:if>>
												${state.description }
											</option>
										</c:forEach>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">排序：</label>
                                <div class="col-sm-8">
                                	<input type="text" class="form-control" id="orderNum" name="orderNum" value="${station.orderNum }" />
                                </div>
                            </div> --%>
                            
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
	
	
	
	$(".config").on("click",function(){
				var id = $(this).parent().attr("id");
				var url = "${ctx }/backend/system/station/wxConfig?id="+id;
				var title = "公众号参数配置";
				openDialog(url,title);
			})
			$(".menu").on("click",function(){
				var id = $(this).parent().attr("id");
				var url = "${ctx }/backend/system/station/indexMenu?id="+id;
				var title = "公众号主页菜单设置";
				openDialog(url,title);
			})
			
			
		function openDialog(url,title){
				var dialog = $.dialog.open(url,{title:title,drag : false,resize : false,lock : true,});
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
				name:{
					required:true,
					remote:"${ctx}/backend/system/station/checkName"
					},
				address: "required",
				code:{
					required:true,
					remote:"${ctx}/backend/system/station/checkCode"
					},
				phone:{
					required:true,
					isPhone:true
					},
				longitude:{
					required:true,
					isLongitude:true
					},
				latitude:{
					required:true,
					isLatitude:true
					},
				email:{
			        email: true
			      },
			     state:"required",
			     district:"required",
			     orderNum:"required",
			     viewName:"required"
			},
			messages: {
				name: {
			        required: e + "请输入名称",
			        remote: e + "名称已存在，请重新输入"
			      },
			      viewName: e + "请输入简称",
			      code: {
			        required: e + "请输入编码",
			        remote: e + "编码已存在，请重新输入"
			      },
					address: e + "请输入地址",
					phone: {required:e + "请输入联系方式"},
				longitude: {required:e + "请输入经度"},
				latitude: {required:e + "请输入纬度"},
				state: e + "请选择状态",
				district: e + "请选择地区",
				email:{
			        email: e + "邮箱格式错误，请重新输入"
			      },
			      
			      orderNum:e + "请输入序号",
			}
		})
		$.validator.addMethod("isPhone", function(value, element) {   
		    var tel = /^(1[0-9])\d{9}$/;
		    var phone = /^\d{8}|\d{4}-\{7,8}$/;
		    return this.optional(element) || (tel.test(value)||phone.test(value));
		}, e +"手机号格式错误");
		
		$.validator.addMethod("isLongitude", function(value, element) {   
		    var tel = /^-?((0|1?[0-7]?[0-9]?)(([.][0-9]{1,8})?)|180(([.][0]{1,8})?))$/;
		    return this.optional(element) || tel.test(value);
		}, e +"经度格式错误");
		$.validator.addMethod("isLatitude", function(value, element) {   
		    var tel = /^-?((0|[1-8]?[0-9]?)(([.][0-9]{1,8})?)|90(([.][0]{1,8})?))$/;
		    return this.optional(element) || tel.test(value);
		}, e +"纬度格式错误");
		
		});
	});
</script>
</html>