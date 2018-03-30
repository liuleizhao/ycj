define(['jquery',"common","mui","picker","poppicker"],function($,common,mui){
	
	//变量声明区
	var obj = {}; //推荐方式
	var name = "车辆信息js文件";
	var version = "1.0.0";
	var pn_val;//号牌号码
	var pn1_val;//号牌号码省份1
	var pn2_val;//号牌号码后部分2
	var vt_val;//车辆类型
	var cti_val;//号牌种类
	var fn_val;//车架号
	//声明方法区
	
	var init = function() {
		change($("select[name='vehicleType']").val(),false);
		
		common.toast(message);
		common.readonly();
	}
	
	/*表单验证*/
	var validate = function(){
		newflag = $("input[name='newflag']").val()
		if(newflag == 0){
			/*号牌号码*/
			pn1_val = $("select[name='platNumber_1']").val();
			pn2_val = $.trim($("input[name='platNumber_2']").val()).toUpperCase();
			if(pn2_val != ""){
				var reg_platNumber = new RegExp(/^[A-Z]{1}[0-9a-zA-Z]*$/);
				if (!reg_platNumber.test(pn2_val)){
					mui.toast ("输入的号牌号码第一位必须为字母，后面为数字或字母组合，汉字无需输入！");
					return false;
				}
				
				vt_val = $("select[name='vehicleType']").val();
				if(vt_val == "SMALL_CAR" || vt_val == "LARGER_CAR"){
					if( pn2_val.length > 7 || pn2_val.length < 4){
						mui.toast ("请检查号牌号码是否输入正确！");
						return false;
					}
					cti_val =  $("#carTypeId").val();
					if($.trim(cti_val) == "312AED23657445C194540C794DBDBDB9" && 
							pn2_val.length < 6 ){//小型车
						mui.toast ("小型汽车输入的号牌号码长度大于等于6位！");
						return false;
					} 
					if(($.trim(cti_val) == "763FF1EEE4BB4C3995B402E8A7D2C550" || 
							$.trim(cti_val) == "0EBEC3DB9EAA40A7B97DDD547FF58F51")&& 
							pn2_val.length != 7 ){ //新能源
						mui.toast ("新能源汽车输入的号牌号码长度为7！");
						return false;
					}
				}else if( pn2_val.length > 5){
					mui.toast ("挂车输入的号牌号码不能超过5位！");
					return false;
				}
				$("input[name='platNumber']").val(pn1_val+pn2_val);
			}else{
				$("input[name='platNumber']").val("");
			}
		}
		
		/*车架号*/
		fn_val = $("input[name='frameNumber']").val();
		var fn_val_length = fn_val.length;
		if(newflag == 1){
			if(fn_val_length<=4||fn_val_length>24){
				mui.toast("请正确填写车架号码号码！");
				return false;
			}
		}else{
			if(fn_val_length != 4){
				mui.toast("旧车请请输入车架号后4位！");
				return false;
			}
		}
		return true;
	}
	
	var change = function(v,refresh) {
		$(".small,.big,.trailer,.small_big").attr("disabled",true).hide();
		switch (v) {
		case "SMALL_CAR":
			$('.small,.small_big').removeAttr("disabled").show(); 
			break;
		case "LARGER_CAR":
			$('.big,.small_big').removeAttr("disabled").show(); 
			break;
		case "TRAILER":
			$('.trailer').removeAttr("disabled").show();
			break;
		default:
			break;
		}
		if(refresh){
			select_show("carTypeId");
			select_show("vehicleingCharacter");
			select_show("driverType");
			select_show("fuelType");
		}
		select_company();
	}
	
	
	
	/*选中select显示的option的第一个*/
	var select_show = function(name) {
		$("select[name='"+name+"']").val($("select[name='"+name+"'] option:not(:disabled):first").val())
	}
	
	var select_company = function() {
		if($("select[name='useCharater']").val() == 'YZF' && $("select[name='vehicleCharacter']").val() == 'DXZKQC'){
			$(".busCompanyId").show();
		}else{
			$(".busCompanyId").hide();
		}
	}
	//事件绑定
	
	mui('.mui-content .mui-switch').each(function() { //循环所有toggle
			//toggle.classList.contains('mui-active') 可识别该toggle的开关状态
			var state = this.classList.contains('mui-active');
			if(state){
				this.parentNode.querySelector('span').innerText = '新车';
				this.parentNode.querySelector('input').value = "1";
				$(".platNumber").hide()
				$(".frameNumber").text("车架号(车辆识别号)完整：")
			}else{
				this.parentNode.querySelector('span').innerText = '旧车';
				this.parentNode.querySelector('input').value = "0";
				$(".platNumber").show()
				$(".frameNumber").text("车架号(车辆识别号)后4位：")
			}
			/**
			 * toggle 事件监听
			 */
			this.addEventListener('toggle', function(event) {
				var state = event.detail.isActive;
				if(state){
					this.parentNode.querySelector('span').innerText = '新车';
					this.parentNode.querySelector('input').value = "1";
					$(".platNumber").hide()
					$("input[name='platNumber']").val("");
					$(".frameNumber").text("车架号(车辆识别号)：")
				}else{
					this.parentNode.querySelector('span').innerText = '旧车';
					this.parentNode.querySelector('input').value = "0";
					$(".platNumber").show()
					$(".frameNumber").text("车架号(车辆识别号)后4位：")
				}
			});
		});
	
	/*表单提交*/
	$(".save").on("click",function(){
		if(validate()){
			common.post($('#form').attr("action"),
					$('#form').serialize(),
					function(data){
				if(data.state == "00"){
					mui.back();
				}else{
					mui.toast(data.message);
				}
			});
		}
	})
	
	/*选择车辆类型改变后续选项*/
	$("select[name='vehicleType']").on("change",function(){
		change($(this).val(),true);
	})
	
	$("select[name='vehicleCharacter']").on("change",function(){
		select_company();
	})
	
	$("select[name='useCharater']").on("change",function(){
		select_company();
	})
	
	init();
	//提供外部调用区
	
	
	//返回供调用的对象
	return obj;
	
})
