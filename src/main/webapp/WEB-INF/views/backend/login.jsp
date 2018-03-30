<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName }-后台用户登录</title>
<%@ include file="/WEB-INF/common/common.jsp"%>
<link href="${ctx }/static/backend/css/login.min.css" rel="stylesheet">
<script>
    if(window.top!==window.self){window.top.location=window.location};
</script>
</head>
<body class="signin">
    <div class="signinpanel">
        <div class="row">
         <div class="col-sm-3"></div>
            <div class="col-sm-6">
                <form method="post" action="${ctx }/backend/login">
                    <h4 class="no-margins">登录：</h4>
                    <input type="text" name="account" class="form-control uname" placeholder="用户名" />
                    <input type="password" name="password" class="form-control pword m-b" placeholder="密码" />
                    <a href="#">忘记密码了？</a>
                    <button class="btn btn-success btn-block">登录</button>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2015 All Rights Reserved. 
            </div>
        </div>
    </div>
</body>
</html>
