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
    li {list-style-type:none;}
</style>
<body class="gray-bg">
<div class="wrapper wrapper-content fadeInRight">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-12">
					<div class="context">
						<div class="table_box">
						        	<c:forEach items="${pageView.list }" var="item">
							                <li id="${item.id}">${item.title }</li>
						        	</c:forEach>
						</div>
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
		$("li").on("click",function(){
				var id = $(this).attr("id");
				window.location.href="${ctx }/wx/news?id="+id;
			})
		})
	</script>

</html>