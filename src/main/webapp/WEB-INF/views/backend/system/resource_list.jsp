<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}-资源列表</title>
<link href="${ctx }/static/backend/css/bootstrap.min14ed.css?v=3.3.6"rel="stylesheet">
<link href="${ctx }/static/backend/css/font-awesome.min93e3.css?v=4.4.0"rel="stylesheet">
<link href="${ctx }/static/backend/css/style.min862f.css?v=4.1.0"rel="stylesheet">

<script src="${ctx }/static/backend/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/static/backend/js/bootstrap.min.js?v=3.3.6"></script>

<link href="${ctx}/static/plugins/zTree/zTreeStyle/style_reset.css"rel="stylesheet" type="text/css" />
<link href="${ctx}/static/plugins/zTree/zTreeStyle/zTreeStyle.css"rel="stylesheet" type="text/css">

<script type="text/javascript"src="${ctx}/static/plugins/zTree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"src="${ctx}/static/plugins/zTree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"src="${ctx}/static/plugins/zTree/jquery.ztree.exedit-3.5.js"></script>

<!--artDialog-->
<script type="text/javascript"src="${ctx}/static/plugins/artDialog/jquery.artDialog.js?skin=default"></script>
<script type="text/javascript"src="${ctx}/static/plugins/artDialog/plugins/iframeTools.source.js"></script>

<style type="text/css">
.ztree li span.button.add {text-align: center;margin-left: 6px;margin-right: 6px;background-position: -144px 0;vertical-align: top;*vertical-align: middle}
.ztree li span.button.edit, .ztree li span.button.remove {text-align: center;margin-left: 6px;margin-right: 6px;}
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							资源树 <small>（权限）</small>
						</h5>
					</div>
					<div class="ibox-content">
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
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
		var setting = {	
						view: {
								addHoverDom: addHoverDom,
								removeHoverDom: removeHoverDom,
								selectedMulti: false //设置是否允许同时选中多个节点。
							  },
						edit: {
							enable: true,
							showRemoveBtn: false,
							showRenameBtn: true,
							showRemoveBtn: true,
							renameTitle: "修改",
							removeTitle:"删除"
						},
						callback: {
							beforeEditName: beforeEditName,
							beforeRemove: beforeRemove
							
						}
			          };
   
		//var zNodes;
		var zNodes="";
		$(document).ready(function(){
			$.post("${ctx }/backend/system/resource/getResource", { "treeType": 2 },
			   function(data){
			  zNodes =  eval(data.resources);
			  $.fn.zTree.init($("#resource_list"), setting,zNodes);
			  }, "json");
		});
		
		//var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) {
				//控制添加一个增加按钮
				return;
			};
			
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='新增' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) {
				btn.bind("click", function(){
					var zTree = $.fn.zTree.getZTreeObj("resource_list");
					//zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
					//打开一个弹窗做增加页面
					openEditDialog(treeNode.id,"1",zTree,treeNode);
					return false;
				});
			}
		};
		
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};
		function selectAll() {
			var zTree = $.fn.zTree.getZTreeObj("resource_list");
			zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
		}
		
		//编辑节点
		function beforeEditName(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("resource_list");//获取 zTree 对象
			zTree.selectNode(treeNode);//选中指定节点
			openEditDialog(treeNode.id,"0",zTree,treeNode);
			return false;
		}
		
		function beforeRemove(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("resource_list");
			zTree.selectNode(treeNode);
			$.dialog.confirm("警告：您确认要删除【"+treeNode.name+"】吗？",
				function(){
					var r = removeResource(zTree,treeNode);
					}
			);
			return false;
		}
	
		function removeResource(zTree,treeNode){
			$.post("${ctx}/backend/system/resource/delete", { "resourceId": treeNode.id },
						   function(data){
						    if(data==true){
						    zTree.removeNode(treeNode);
						    	$.dialog( {
									time : 3,
									icon : 'succeed',
									content : "删除【"+treeNode.name+"】成功"
								});
						    }else {
						    	$.dialog( {
									time : 3,
									icon : 'error',
									content : "删除【"+treeNode.name+"】失败，请重新操作"
								});
							}
						   }, "json");
		}
		
		/*---------------------------------------------------*/
		//mark 用于表示是针对该节点做修改还是增加子节点  0 修改  1增加
		function openEditDialog(id,mark,zTree,treeNode){
				if("0"==mark){
					title = "修改资源";
				}else{
					title = "新增资源";
				}
				var dialog = $.dialog.open("${ctx}/backend/system/resource/edit?resourceId="+id+"&mark="+mark, {
				title: title,
				drag: false,
    			resize: false,
				lock: true,
				width:800,
				height:418,
			    ok: function () {
			    	var iframe = this.iframe.contentWindow;
			    	if (!iframe.document.body) {
			        	alert('iframe还没加载完毕呢')
			        	return false;
			        };
			    	//父资源ID
					var parentId = iframe.document.getElementById('parent_id').value;
					//资源路径
					var path = iframe.document.getElementById('path').value;
					//资源名称
					var name = iframe.document.getElementById('name').value;
					//资源类型
					var resourceType = iframe.document.getElementById('resource_type').value;
					//排列顺序                                                      													
					var orderNumber = iframe.document.getElementById('orderNumber').value;
					//资源描述
					var description = iframe.document.getElementById('description').value;
					//资源ID
					var id = iframe.document.getElementById('id').value;
					
					var icon = iframe.document.getElementById('icon').value;
					
					var methodType = iframe.document.getElementById('method_type').value;
			        
			        try{
						if(""==name){throw ("资源名称不能为空");}
						if("" == resourceType){throw ("资源类型不能为空");}
						if("" == methodType){throw ("提交方式不能为空");}
						var reg = /^[\d]{1,}$/;
						if(!reg.test(orderNumber)){throw ("输入正确的排列顺序");}
					}catch (e) {
						$.dialog( {
									time : 3,
									content : e,
									icon:"error"
								});
						return false;
					}
					$.ajax({url:"${ctx}/backend/system/resource/edit",type:"post",
						    data: {id:id,parentId:parentId,path:path,name:name,resourceType:resourceType,
						    		methodType : methodType,orderNumber:orderNumber,description:description,icon:icon
						    	  },dataType:"json",
						   			success:function(data){
								   		if(data!=null){
									   		if("0"==mark){
												treeNode.name=name;
												zTree.updateNode(treeNode);
											}else{
												var newNode = {id:data.newNodeId,name:data.newNodeName};
												zTree.addNodes(treeNode, newNode);
											}
											$.dialog( {
												time : 3,
												content : data.message
											});
											dialog.close();
								   		}
						   			}
							}
						);
			       	return false;
			    },
			    cancel: true});
			}
		function expandNode(e) {
			var zTree = $.fn.zTree.getZTreeObj("resource_list"),
			type = e.data.type,
			nodes = zTree.getSelectedNodes();
			if (type.indexOf("All")<0 && nodes.length == 0) {
				$.dialog( {
							time : 3,
							content : "请选择一个父节点",
							icon : 'error',
						});
			}

			if (type == "expandAll") {
				zTree.expandAll(true);
			} else if (type == "collapseAll") {
				zTree.expandAll(false);
			} else {
				var callbackFlag = $("#callbackTrigger").attr("checked");
				for (var i=0, l=nodes.length; i<l; i++) {
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
		$(function(){
			$("#expandBtn").bind("click", {type:"expand"}, expandNode);
			$("#toggleBtn").bind("click", {type:"toggle"}, expandNode);
			$("#expandAllBtn").bind("click", {type:"expandAll"}, expandNode);
			$("#collapseAllBtn").bind("click", {type:"collapseAll"}, expandNode);
		});
	</script>
</html>