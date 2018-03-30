define(['jquery',"common"],function($,common){
	
	//变量声明区
	var obj = {}; //推荐方式
	var name = "车辆信息选择js文件";
	var version = "1.0.0";
	
	//声明方法区
	
	var init = function() {
		
	}
	
	
	$(".add").on("click",function(){
		window.location.href = ctx+"/wx/carInfo/add";
	})
	
	//提供外部调用区
	init();
	
	//返回供调用的对象
	return obj;
	
})
