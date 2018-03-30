<%@ page language="java" pageEncoding="UTF-8"%>
<link href="${ctx }/static/backend/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/static/backend/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/static/backend/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="${ctx }/static/backend/css/animate.min.css" rel="stylesheet">

<script src="${ctx }/static/backend/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/static/backend/js/bootstrap.min.js?v=3.3.6"></script>
<!--artDialog-->
<script type="text/javascript" src="${ctx}/static/plugins/artDialog/jquery.artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}/static/plugins/artDialog/plugins/iframeTools.source.js"></script>

<input type="hidden" id="message" value="${message }" />

<script type="text/javascript">
	var ctx = "${ctx}";
	if ($("#message").val()!=""){
		$.dialog( {time : 3,content : "${message }"});
	}
</script>