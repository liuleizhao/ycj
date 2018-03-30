define(['jquery',"mui"],function($,mui){
	
	//变量声明区
	var a_model = {}; //推荐方式
	var name = "ajs 文件";
	var version = "1.0.0";
	
	//声明方法区
	var alert1 = function (mes){
      mui.toast(mes)
    }
	
	//提供外部调用区
	a_model.alert1 = alert1;
	
	
	//返回供调用的对象
	return a_model;
	
})
