<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}</title>
<%@ include file="/WEB-INF/common/common.jsp"%>
<link href="${ctx }/static/backend/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<script src="${ctx }/static/backend/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx }/static/backend/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${ctx }/static/backend/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
</head>
<style>
	.float-e-margins .btn {
     margin-bottom: 0px; 
}
</style>
<body class="gray-bg">
<div class="wrapper wrapper-content fadeInRight">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			 <form role="form" class="form-inline" action="${ctx }/backend/system/newsColumn/list" method="get">
			 	  <input type="hidden" name="currentPage" value="${currentPage }" />
			 	   <div class="form-group">
                  	  <label class="control-label">栏目名称</label>
                      <input type="text" name="name" value="${newsColumn.name }" class="form-control">
                  </div>
                  <button class="btn btn-white" type="submit">查询</button>
                  <button class="add btn btn-white" type="button">新增</button>
              </form>
		</div>
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-12">
					<div class="context">
						<div class="table_box">
							<table  data-toggle="table">
						    	<thead>
									<tr>
										<th>序号</th>
										<th>名称</th>
										<th>状态</th>
										<th>描述</th>
										<th>创建者</th>
										<th>创建时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageView.list }" var="item">
										<tr>
											<td>${item.orderNumber }</td>
							                <td>${item.name }</td>
							                <td>${item.state.description }</td>
							                <td title="${item.description }">${item.description }</td>
							                <td>${item.creator }</td>
							                <td><fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd HH:mm"/></td>
							                <td id="${item.id }">
							                	<a class="edit">查看</a>
							                	&nbsp;&nbsp;
							                	<a class="del">删除</a>
							                </td>
										</tr>
									</c:forEach>
								</tbody>
						    </table>
						</div>
						<%@ include file="/WEB-INF/common/pagination.jsp"%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
	<!-- 将common.js放置在最后 -->
	<script type="text/javascript">
		$(function(){
			$(".add").on("click",function(){
				window.location.href="${ctx }/backend/system/newsColumn/add";
			})
			$(".edit").on("click",function(){
				var id = $(this).parent().attr("id");
				window.location.href="${ctx }/backend/system/newsColumn/edit?id="+id;
			})
			$(".del").on("click",function(){
				var id = $(this).parent().attr("id");
				art.dialog.confirm("您确定要删除吗？", function() {
					location.href = "${ctx}/backend/system/newsColumn/delete?id="+id;
				})
			})
		})
	</script>

</html>