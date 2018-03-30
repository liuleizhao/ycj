define(['jquery',"common","mui"],function($,common,mui){
	
	$(".own").on("click",function(){
		window.location.href = ctx+"/wx/book/car";
	})
	
	$(".offic").on("click",function(){
		//window.location.href = ctx+"/wx/book/book_agreement";
		common.toast("暂不提供该服务")
	})
	
})
