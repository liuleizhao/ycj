<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>网上预约协议书</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
</head>
	<body>
		<div class="mui-content">
			<div class="i-content-padded">
		      <table>
		        <tr>
		          <td>
		          	<p>一、请如实录入个人及车辆信息，否则不予受理。</p>
					<p>二、申请人凭手机验证码认证方式进行登录。</p>
					<p>三、一个手机号每天限预约6笔。</p>
					<p>四、在选择预约日期和时段时，如系统未能显示，表示该时间段无法预约。</p>
					<p>五、预约成功后，前往检验机构办理时，未通过检测的，一个月内无需再次预约，可直接前往该检验机构进行复检。</p>
					<p>六、预约成功后，前往检验机构办理时，如果产生退办，需重新预约，且一个月内只能预约该检验机构。</p>
					<p>七、如需取消预约，须至少提前一天登录预约系统进行取消。</p>
					<p>八、非因检验机构原因，申请人未在预约时间到预约检验机构检验，视为失约。</p>
					<p>九、预约失约累计达到2次及以上，该手机将记入黑名单，半年内无法再进行预约。</p>
					<p>十、送检机动车如存在明显的漏油、漏水现象，资料不齐，或者轮胎胎面严重磨损影响上线安全的，检验机构可以拒绝该车上线检测，该车的当次预约做作废处理。</p>
		          </td>
		        </tr>
		      </table>
		      <div class="i-btn-box">
				<button type="button" class="mui-btn i-btn-bule" onclick="window.location.href = '${ctx }/wx/book/login'">接受（下一步）</button>
			</div>
			</div>
		</div>
	</body>
	<script data-main="${ctx }/static/wx/js/main" id="currentPage" target-module="offic_book_agreement" src="${ctx }/static/wx/js/require.min.js"></script>
</html>

