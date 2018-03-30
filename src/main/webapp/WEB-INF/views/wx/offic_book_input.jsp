<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>预约信息</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
</head>
	<body>
		<div class="mui-content">
			<div class="i-content-padded">
				<form action="" method="post">
					<h5>车主姓名：</h5>
					<div class="mui-input-row">
						<input type="text" class="mui-input-group" value="">
					</div>
					<h5>身份证名类型：</h5>
					<div class="mui-input-row">
						<select class="i-select">
							<option value="e4e48584399473d20139947f125e2b2c">居民身份证</option>
							<option value="40288282463ceca50146462942d3055c">组织机构代码证书</option>
							<option value="4028823f51d79d4d0151f1ebb1dc361e">统一社会信用代码</option>
							<option value="e4e48584399b293601399b60996b55e6">境外人员身份证明</option>
						</select>
					</div>

					<h5>身份证明号码/机构代码证：</h5>
					<div class="mui-input-row">
						<input type="text" maxlength="18" oninput="value=value.toUpperCase()" class="mui-input-group" value="">
					</div>
					<h5>新旧车</h5>
					<div class="mui-input-row mui-checkbox">
						<select class="i-select">
							<option value="1">旧车</option>
							<option value="2">新车</option>
						</select>
					</div>

					<h5>号牌号码：</h5>
					<div class="mui-input-row">
						<select class="i-select i-col-2">
							<option value="粤">粤</option>
							<option value="鄂">鄂</option>
							<option value="豫">豫</option>
							<option value="皖">皖</option>
							<option value="赣">赣</option>
							<option value="冀">冀</option>
							<option value="鲁">鲁</option>
							<option value="浙">浙</option>
						</select>
						<input class="mui-input-group i-col-10" type="text" value="">
					</div>

					<h5>车架号(车辆识别号)后4位：</h5>
					<div class="mui-input-row">
						<input type="text" class="mui-input-group" value="">
					</div>
					<h5>车辆类型：</h5>
					<div class="mui-input-row">
						<select class="i-select">
							<option value="">小型汽车</option>
							<option value="">大型汽车</option>
							<option value="">挂车</option>
						</select>
					</div>
					<h5>号牌种类：</h5>
					<div class="mui-input-row">
						<select class="i-select">
							<option value="5AA667F13C2143F0A41C6940E74B127E">大型汽车(黄底黑字)</option>
							<option value="B4394B3F2F3B4E78911713C3D54D4196">领馆汽车(黑底白字、红领字)</option>
							<option value="A4FA9722C81C408B8A5BB65F8BD9C9B1">外籍汽车(黑底白字)</option>
							<option value="0D7E3ABB86774FD1927EE05CF82FDA4B">低速车(黄底黑字黑框线)</option>
							<option value="D7FAFC5A68004845864C42345B58D7BC">教练汽车(黄底黑字黑框线)</option>
							<option value="31CCBA351E0A4B7AA1BAFBDE2AA93161">警用汽车(警用汽车)</option>
							<option value="0EBEC3DB9EAA40A7B97DDD547FF58F51">大型新能源汽车(黄绿双拼色底黑字)</option>
						</select>
					</div>
					<h5>车辆性质：</h5>
					<div class="mui-input-row">
						<select class="i-select">
							<option value="XXZK">小型载客汽车</option>
							<option value="ZZZH">小型载货汽车(含专项作业车)</option>
							<option value="WHPZSC">危化品运输车</option>
							<option value="XXXC">校车</option>
						</select>
					</div>
					<h5> 驱动类型：</h5>
					<div class="mui-input-row">
						<select class="i-select">
							<option value="XXZK">两驱（含非全时四驱）</option>
							<option value="ZZZH">全时四驱</option>
						</select>
					</div>
					<h5>  燃油类型：</h5>
					<div class="mui-input-row">
						<select class="i-select">
							<option value="1">汽油、混合动力、纯电</option>
							<option value="2">柴油、柴电混合（总质量小于等于3500KG）</option>
							<option value="3">柴油、柴电混合（总质量大于3500KG）</option>
						</select>
					</div>
					<div class="i-btn-box">
						<button type="button" class="mui-btn i-btn-bule" onclick="window.location.href = '${ctx }/wx/book/org'">下一步</button>
					</div>
				</form>
			</div>
		</div>
	</body>
	<script data-main="${ctx }/static/wx/js/main" id="currentPage" target-module="offic_book_input" src="${ctx }/static/wx/js/require.min.js"></script>
</html>