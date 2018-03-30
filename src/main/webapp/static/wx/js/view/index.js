define(['jquery',"mui","wx"],function($,mui,wxjs){
	
	//变量声明区
	var obj = {}; //推荐方式
	var name = "首页js";
	
	wxjs.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: $("#appid").val(), // 必填，公众号的唯一标识
	    timestamp:$("#timestamp").val(), // 必填，生成签名的时间戳
	    nonceStr: $("#noncestr").val(), // 必填，生成签名的随机串
	    signature: $("#signature").val(),// 必填，签名
	    jsApiList: $("#jsApiList").val().replace("[","").replace("]","").split(",") // 必填，需要使用的JS接口列表
	});
	
	wxjs.ready(function(){
		 mui.toast("欢迎您")
		$("#navigation").on("click",function(){
			 var y = parseFloat($(this).attr("y"));
			 var x = parseFloat($(this).attr("x"));
			 var _name = $(this).attr("_name");
			 var address = $(this).attr("address");
			 wxjs.openLocation({
				 latitude: y, // 纬度，浮点数，范围为90 ~ -90
				 longitude: x, // 经度，浮点数，范围为180 ~ -180。
				 name: _name, // 位置名
				 address: address, // 地址详情说明
				 scale: 14, // 地图缩放级别,整形值,范围从1~28。默认为最大
				 infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
				 });
		 })
	});
	 
	
	
	//声明方法区
	
	//提供外部调用区
	
	//返回供调用的对象
	return obj;
	
})
