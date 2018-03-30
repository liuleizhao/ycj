define(['jquery',"common","mui","picker","poppicker"],function($,common,mui){
	
	//变量声明区
	var a_model = {}; //推荐方式
	var name = "ajs 文件";
	var version = "1.0.0";
	
	var datePicker;//日期控件对象
	var timePicker;//时间控件对象
	
	var stationId_val,vehicleType_val,vehicleingCharacter_val,carTypeId_val,
		driverType_val;
	
	var mobile_box,bookerName_box,idNumber_box,idTypeId_box,bookDate_box,bookTime_box
	
	//声明方法区
	var init = function() {
		
		stationId_val = $("#stationId").val();
		vehicleType_val = $("#vehicleType").val();
		vehicleCharacter_val = $("#vehicleCharacter").val();
		carTypeId_val = $("#carTypeId").val();
		driverType_val = $("#driverType").val();
		
		
		mobile_box = $("#mobile");
		bookerName_box = $("#bookerName");
		idNumber_box = $("#idNumber");
		idTypeId_box = $("#idTypeId");
		bookDate_box = $("#bookDate");
		bookTime_box = $("#bookTime");
		
		getDate();
		
		common.toast(message);
		common.readonly();
	}
	
	var getDate = function() {
		common.post(ctx+"/wx/book/getDate",{},function(data){
			if(data.code != "00"){
				mui.toast(data.message)
				return;
			}
			//声明方法区
			datePicker = new mui.PopPicker();
			datePicker.setData(data.result.string);
			$("#bookDate").removeAttr("disabled"); 
			$("#bookDate").on("click",function(){
				var date = $(this);
				datePicker.show(function(items) {
					date.val(items[0])
					$("#bookTime").val("");
					getTime(items[0])
				});
			});
		});
	}
	
	var getTime = function(bookDate) {
		common.post(ctx+"/wx/book/getTime",{
			"bookDate":bookDate
		},function(data){
			if(data.code != "00"){
				mui.toast(data.message)
				return;
			}
			timePicker = new mui.PopPicker();
			timePicker.setData(data.result.AppTimeHelper);
			$("#bookTime").removeAttr("disabled"); 
			$("#bookTime").on("click",function(){
				var time = $(this)
				timePicker.show(function(items) {
					if(items[0].yetnumber >= items[0].maxnumber){
						return false;
					}
					time.val(items[0].apptime)
					$(".submit").removeAttr("disabled"); 
				});
			});
		});
	}
	
	
	var submit = function() {
		common.post($('#form').attr("action"),
				$('#form').serialize(),
				function(data){
			if(data.code == "00"){
				window.location.href = ctx+"/wx/book/ok?bookNumber="+data.result;
			}else{
				mui.toast(data.result);
			}
		});
	}
	
	
	var validate = function() {
		return true;
	}
	
	$(".submit").on("click",function(){
		if(validate()){
			submit();
		}
	})
	
	
	//提供外部调用区
	init()
	
	//返回供调用的对象
	return a_model;
	
})
