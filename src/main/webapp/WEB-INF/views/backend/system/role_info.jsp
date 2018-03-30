<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}-角色列表</title>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!-- ztree -->
<link href="${ctx}/static/plugins/zTree/zTreeStyle/style_reset.css"rel="stylesheet" type="text/css" />
<link href="${ctx}/static/plugins/zTree/zTreeStyle/zTreeStyle.css"rel="stylesheet" type="text/css">
<script type="text/javascript"src="${ctx}/static/plugins/zTree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"src="${ctx}/static/plugins/zTree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"src="${ctx}/static/plugins/zTree/jquery.ztree.exedit-3.5.js"></script>

<!-- 表单验证 -->
<script src="${ctx }/static/backend/js/plugins/validate/jquery.validate.min.js"></script>
</head>
<style>
	.float-e-margins .btn {
     margin-bottom: 0px; 
}
</style>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							角色 <small><c:choose><c:when test="${role == null }">添加</c:when><c:otherwise>编辑</c:otherwise></c:choose></small>
						</h5>
					</div>
					<div class="ibox-content">
						<div class="row">
                            <div class="col-sm-6">
                                <form role="form" class="form-horizontal" action="${ctx }/backend/system/role/<c:choose><c:when test="${role.id == null }">add</c:when><c:otherwise>edit</c:otherwise></c:choose>" method="post" name="form" id="form">
						            <input type="hidden" name="ids" id="ids" value="${resourceIds}"> 
						            <c:if test="${role != null }">
										<input type="hidden" name="id" id="id" value="${role.id}">
						            </c:if>
						            <div class="form-group">
						                <label class="col-sm-3 control-label">角色名称</label>
						                <div class="col-sm-8">
							            	<input type="text" <c:choose><c:when test="${role == null }"> id="name" name="name" </c:when><c:otherwise> disabled="disabled" </c:otherwise></c:choose> value="${role.name }" class="form-control">
							            </div>
							        </div>
							         <div class="form-group">
						                <label class="col-sm-3 control-label">排列顺序</label>
						                <div class="col-sm-8">
							             	<input type="text" id="orderNumber" name="orderNumber" value="${role.orderNumber }" class="form-control">
							            </div>
							        </div>
							         <div class="form-group">
						                <label class="col-sm-3 control-label">角色描述</label>
						                <div class="col-sm-8">
							                 <input type="text" id="description" name="description" value="${role.description }" class="form-control">
							             </div>
							        </div>
							         <div class="form-group">
			                               <div class="col-sm-8 col-sm-offset-3">
			                                   <button class="btn btn-primary" type="button" id="save_btn">保存</button>
			                               </div>
			                           </div>
					            </form>
                            </div>
                            <div class="col-sm-6 text-warning">
                            	<blockquote class="text-warning">
	                            	<button class="btn btn-white"  id="expandAllBtn" type="button">展开全部节点</button>
									<button class="btn btn-white"  id="collapseAllBtn" type="button">折叠全部节点</button>
									<button class="btn btn-white"  id="expandBtn" type="button">展开选中节点</button>
									<button class="btn btn-white"  id="toggleBtn" type="button">折叠选中节点</button>
									<div class="zTreeBackground">
										<ul id="resource_list" class="ztree">
											<li>
												<img src="${ctx}/static/plugins/zTree/zTreeStyle/img/loading.gif" />
												正在加载资源树，请稍候...
											</li>
										</ul>
									</div>
								</blockquote>
                            </div>
                        </div>
					</div>
				</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var setting = {
		view : {
			selectedMulti : false//设置是否允许同时选中多个节点。
		},
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}
		},
		callback : {}
	};
	var zNodes = "";
	function expandNode(e) {
		var zTree = $.fn.zTree.getZTreeObj("resource_list"), type = e.data.type, nodes = zTree
				.getSelectedNodes();
		if (type.indexOf("All") < 0 && nodes.length == 0) {
			$.dialog({time : 3,content : "请选择一个父节点",icon : 'error',});
		}
		if (type == "expandAll") {
			zTree.expandAll(true);
		} else if (type == "collapseAll") {
			zTree.expandAll(false);
		} else {
			var callbackFlag = $("#callbackTrigger").attr("checked");
			for ( var i = 0, l = nodes.length; i < l; i++) {
				zTree.setting.view.fontCss = {};
				if (type == "expand") {
					zTree.expandNode(nodes[i], true, null, null, callbackFlag);
				} else if (type == "collapse") {
					zTree.expandNode(nodes[i], false, null, null, callbackFlag);
				} else if (type == "toggle") {
					zTree.expandNode(nodes[i], null, null, null, callbackFlag);
				} else if (type == "expandSon") {
					zTree.expandNode(nodes[i], true, true, null, callbackFlag);
				} else if (type == "collapseSon") {
					zTree.expandNode(nodes[i], false, true, null, callbackFlag);
				}
			}
		}
	}
	$(function() {
		var roleId = $("#id").val();
		
		$.post("${ctx }/backend/system/resource/getResource", {
			"treeType" : 2,
			"roleId" : roleId
		}, function(data) {
			zNodes = eval(data.resources);
			$.fn.zTree.init($("#resource_list"), setting, zNodes);
		}, "json");
		
		$("#expandBtn").bind("click", {type : "expand"}, expandNode);
		$("#toggleBtn").bind("click", {type : "toggle"}, expandNode);
		$("#expandAllBtn").bind("click", {type : "expandAll"}, expandNode);
		$("#collapseAllBtn").bind("click", {type : "collapseAll"}, expandNode);
		
		$("#save_btn").click(function() {
			var zTree = $.fn.zTree.getZTreeObj("resource_list");
			var nodes = zTree.getCheckedNodes(true);
			var ids = '';//选中节点ids
			for ( var i = 0; i < nodes.length; i++) {
				if (ids != '') {
					ids += ',';
				}
				ids += nodes[i].id;
			}
			$("#ids").attr("value", ids);
			$("#form").submit();
		});
		
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
						remote:"${ctx}/backend/system/role/check"
						},
					orderNum: {
						digits:true,
						min:1
					},
					description:"required",
				},
				messages: {
					name: {
				        required: e + "请输入角色名称",
				        remote: e + "角色名称已存在，请重新输入"
				      },
					orderNum:{
						digits: e + "请输入正确的排列序号",
						min:e + "最小排列顺序为1"
					} ,
					description:e + "请输入角色描述信息"
				}
			})
		})
	}) 
	
</script>
</html>