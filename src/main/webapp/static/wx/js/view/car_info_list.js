define(['jquery',"mui","common"],function($,mui,common){
	
	//变量声明区
	var obj = {}; //推荐方式
	var name = "车辆信息管理js文件";
	var version = "1.0.0";
	//声明方法区
	
	var init = function() {
	}
	
	//事件绑定
	$(".add").on("click",function(){
		window.location.href = ctx+"/wx/carInfo/add";
	})
	
	$(".edit").on("click",function(){
		var id = $(this).parent().attr("car_id");
		window.location.href = ctx+"/wx/carInfo/edit?carInfoId="+id;
	})
	
	$(".del").on("click",function(){
		var id = $(this).parent().attr("car_id");
		var btnArray = ['取消','删除'];
		mui.confirm('',"确认删除车辆信息", btnArray, function(e) {
			if (e.index == 1) {
				common.post( ctx+"/wx/carInfo/del",
						{"carInfoId":id},
						function(data){
					if(data.code == "00"){
						mui.toast(data);
					}else{
						mui.toast(data);
					}
				});
			}
		})
	})
	
	
    init();
	//提供外部调用区
	
	//返回供调用的对象
	return obj;
	
})
