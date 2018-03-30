<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}-模板信息列表</title>
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
<div class="wrapper wrapper-content animated fadeIn">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			
		</div>
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-12">
					<div class="context">
						<div class="table_box">
							<table data-toggle="table">
								<thead>
									<tr>
										<th>模板ID</th>
										<th>模板标题</th>
										<th>主行业</th>
										<th>副行业</th>
										<th>模板内容</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${TemplateListResult.template_list }" var="template_list" varStatus="status">
										<tr>
											<td>${template_list.template_id }</td>
											<td>${template_list.title }</td>
											<td>${template_list.primary_industry }</td>
											<td>${template_list.deputy_industry }</td>
											<td>${template_list.content }</td>
											<td>
												<a href="${ctx }/templateDelete?template_id=${template_list.template_id}">删除</a>
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

</html>
