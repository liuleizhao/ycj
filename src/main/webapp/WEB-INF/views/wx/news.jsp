<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}</title>
<%@ include file="/WEB-INF/common/common.jsp"%>

<script type="text/javascript" src="${ctx }/static/backend/js/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/static/backend/js/plugins/ckeditor/ckeditor.js"></script>

<!-- iCheck插件 -->
<link href="${ctx }/static/backend/css/plugins/iCheck/custom.css" rel="stylesheet">
<script src="${ctx }/static/backend/js/plugins/iCheck/icheck.min.js"></script>
<!-- 表单验证 -->
<script src="${ctx }/static/backend/js/plugins/validate/jquery.validate.min.js"></script>
</head>
<body class="gray-bg">
    
								<div class="col-sm-8">
						     	${news.content }
						     	</div>
						    
</body>
<script type="text/javascript">
	$(function() {
		//CKEDITOR.replace( 'content' );
		$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})
	})
</script>
</html>
