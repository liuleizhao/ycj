<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>短信验证</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
</head>
	<body>
		<div class="mui-content">
			<div class="i-content-padded">
				<form action="" id="" method="">
					<h5>电话号码</h5>
					<div class="mui-input-row">
						<input class="mui-input-group" type="text">
					</div>
					<h5>短信验证码</h5>
					<div class="mui-input-row">
						<input class="mui-input-group i-col-5" type="text">
						<button type="button" class="mui-btn mui-btn-green i-col-7" onclick="">获取短信验证码</button>
					</div>
					<div class="i-btn-box">
						<button type="button" class="mui-btn i-btn-bule" onclick="window.location.href = '${ctx }/wx/book/offic_book_input'">登录</button>
					</div>
				</form>
				<table>
		        <tr>
		          <td>
		          	<p>一、短信验证码适用于大陆的三网用户（移动、联通、电信）</p>
					<p>二、初次申请，手机号将与预约信息绑定，且不能修改，请准确录入。</p>
					<p>三、短信验证码有效时间为30分钟，请快速办理预约业务，否则需重新获取，*频繁获取短信验证码，会导致手机号被拉入黑名单。</p>
					<p>四、已在公安机关交通管理部门登记的摩托车不需预约。</p>
		          </td>
		        </tr>
		      </table>
			</div> 
		</div>
	</body>
	<script data-main="${ctx }/static/wx/js/main" id="currentPage" target-module="offic_book_login" src="${ctx }/static/wx/js/require.min.js"></script>
</html>