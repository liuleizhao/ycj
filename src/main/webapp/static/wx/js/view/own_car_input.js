define(['jquery',"common","mui","picker","poppicker"],function($,common,mui){
	
	var pn_box,pn1_box,pn2_box,pn3_box,//车牌号
		vt_box,//车辆类型
		nf_box,//新旧车标识
		cti_box,
		fn_box;
	
	var pn_val;//号牌号码
	var pn1_val;//号牌号码省份1
	var pn2_val;//号牌号码字母部分2
	var pn3_val;//号牌号码后部分2
	var vt_val;//车辆类型
	var cti_val;//号牌种类
	var fn_val;//车架号
	
	var init = function() {
		pn_box = $("input[name='platNumber']");
		pn1_box = $("select[name='platNumber_1']");
		pn2_box = $("select[name='platNumber_2']");
		pn3_box = $("input[name='platNumber_3']");
		vt_box = $("select[name='vehicleType']");
		nf_box = $("input[name='newflag']");
		
		_nf_box = $("#newflag");
		cti_box = $("input[name='carTypeId']");
		fn_box = $("input[name='frameNumber']");
		
		addProvince();
		addLetters();
		
		change(vt_box.val(),false);
		common.toast(message);
		common.readonly();
	}
	
	//初始化地区简称
	var addProvince = function() {
		
		var province = common.province
		var selectedVal = pn1_box.val()
		
		for(var i=0;i<province.length;i++){
			var pro = province[i];
			if(selectedVal){
				if(pro == selectedVal){
					continue;
				}
			}else{
				if("粤" == pro){
					pn1_box.append("<option value='"+pro+"' selected>"+pro+"</option>");
					continue;
				}
			}
			pn1_box.append("<option value='"+pro+"'>"+pro+"</option>");
		}
	}
	
	var addLetters = function() {
		var letters = common.letters
		var selectedVal = pn2_box.val()
		
		for(var i=0;i<letters.length;i++){
			var let = letters[i];
			if(selectedVal){
				if(let == selectedVal){
					continue;
				}
			}else{
				if("B" == let){
					pn2_box.append("<option value='"+let+"' selected>"+let+"</option>");
					continue;
				}
			}
			pn2_box.append("<option value='"+let+"'>"+let+"</option>");
		}
	}
	
	/*表单验证*/
	var validate = function(){
		newflag = nf_box.val()
		if(newflag == '0'){
			/*号牌号码*/
			pn1_val = pn1_box.val();
			pn2_val = pn2_box.val();
			pn3_val = $.trim(pn3_box.val()).toUpperCase();
			
			if(pn3_val != ""){
				var reg_platNumber = new RegExp(/^[0-9a-zA-Z]*$/);
				if (!reg_platNumber.test(pn3_val)){
					mui.toast ("输入的号牌号码数字或字母组合，汉字无需输入！");
					return false;
				}
				
				vt_val = vt_box.val();
				if(vt_val == "SMALL_CAR" || vt_val == "LARGER_CAR"){
					if( pn3_val.length > 7 || pn3_val.length < 4){
						mui.toast ("请检查号牌号码是否输入正确！");
						return false;
					}
					cti_val =  cti_box.val();
					if($.trim(cti_val) == "312AED23657445C194540C794DBDBDB9" && 
							pn3_val.length < 6 ){//小型车
						mui.toast ("小型汽车输入的号牌号码长度大于等于6位！");
						return false;
					} 
					if(($.trim(cti_val) == "763FF1EEE4BB4C3995B402E8A7D2C550" || 
							$.trim(cti_val) == "0EBEC3DB9EAA40A7B97DDD547FF58F51")&& 
							pn3_val.length != 7 ){ //新能源
						mui.toast ("新能源汽车输入的号牌号码长度为7！");
						return false;
					}
				}else if( pn3_val.length > 5){
					mui.toast ("挂车输入的号牌号码不能超过5位！");
					return false;
				}
				pn_box.val(pn1_val+pn2_val+pn3_val);
			}else{
				mui.toast ("请输入正确的号牌号码！");
				pn_box.val("");
				return false;
			}
		}
		
		/*车架号*/
		fn_val = fn_box.val();
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
			select_show("vehicleCharacter");
			select_show("driverType");
			select_show("fuelType");
		}
	}
	
	
	/*选中select显示的option的第一个*/
	var select_show = function(name) {
		$("select[name='"+name+"']").val($("select[name='"+name+"'] option:not(:disabled):first").val())
	}
	
	init();
	
	//事件绑定
	_nf_box.on('change',function() {
		if(this.checked){
			$("input[name='newflag']").val("1");
			$(".platNumber").hide()
			$("input[name='platNumber']").val("");
			$(".frameNumber").text("车架号(车辆识别号)：")
		}else{
			$("input[name='newflag']").val("0");
			$(".platNumber").show()
			$(".frameNumber").text("车架号(车辆识别号)后4位：")
		}
	});
	
	/*表单提交*/
	$(".next").on("click",function(){
		if(validate()){
			$('#form').submit();
		}
	})
	
	/*选择车辆类型改变后续选项*/
	vt_box.on("change",function(){
		change($(this).val(),true);
	})
	
})
