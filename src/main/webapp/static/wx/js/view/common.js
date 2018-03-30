define(['jquery',"mui"],function($,mui){
	
	//变量声明区
	var obj = {}; //推荐方式
	var name = "js公共方法类";
	
	//地区和字母
	var province=["粤","鄂","豫","皖","赣","冀","鲁","浙","苏","湘","闽","蒙","京","辽","渝","沪","陕","川","黑","晋","桂","吉","宁","贵","琼","甘","青","津","云","藏","新"];
	var letters=["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
	
	//声明方法区
	/*信息弹出*/
	var toast = function (msg){
		if(msg!=""){
			mui.toast(msg)
		}
    }
	
	/*实现返回按钮页面可控*/
	var pushHistory = function (url) {
		window.onpopstate = function() {
            location.href=url;
        };
        var state = {
            title: "title",
            url: "#"    };
        window.history.pushState(state, "title", "#");
    };
    /*获取页面名称*/
    function getPageName()
    {
      var a = location.href;
      var b = a.split("/");
      var c = b.slice(b.length-1, b.length).toString(String).split(".");
      return c.slice(0, 1);
    }
    
	/*封装的post方法*/
	var post = function(url,data,fun) {
		var mask=mui.createMask();//遮罩层
		mui.ajax(url,{
		    data:data,
		        type: 'post', //HTTP请求类型
		        timeout: 10000, //超时时间设置为10秒；
		    beforeSend: function() {
		        mask.show();//显示遮罩层
		    },
		    complete: function() {
		        mask.close();//关闭遮罩层
		    },
		    success: function(data) {
		    	console.log(data);
		    	if(typeof data == 'string'){
		    		fun(eval('(' + data + ')'))
		    	}else{
		    		fun(data)
		    	}
		        //服务器返回响应； 
		    },
		    error: function(xhr, type, errorThrown) {
		        mui.alert('服务器连接超时，请稍后再试');
		    }
		})
	}
	
	//让只读的input，不能获取到焦点
	var readonly = function () {
		$('input[readonly]').on('focus', function() {
		    $(this).trigger('blur');
		});
	}
	
	//提供外部调用区
	obj.toast = toast;
	obj.pushHistory = pushHistory;
	obj.getPageName = getPageName;
	obj.post = post;
	obj.readonly = readonly;
	obj.province = province;
	obj.letters = letters;
	//返回供调用的对象
	return obj;
	
})