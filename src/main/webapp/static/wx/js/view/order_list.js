define(['jquery',"mui","common"],function($,mui,common){
	
	//变量声明区
	var a_model = {}; //推荐方式
	var name = "ajs 文件";
	var currentPage = 2;
	
	//声明方法区
	var alert1 = function (mes){
      mui.toast(mes)
    }
	
	mui.init({
		swipeBack: false,
		pullRefresh: {
			container: '#pullrefresh',
			down: {
				style:'circle',
				callback: pulldownRefresh
			},
			up: {
				contentrefresh: '正在加载...',
				callback: pullupRefresh
			}
		}
	});
	/**
	 * 下拉刷新具体业务实现
	 */
	function pulldownRefresh() {
		setTimeout(function(){
			
			var table =$('.mui-table-view');
			var url = ctx+"/wx/order/ajaxList";//请求地址
			currentPage = 1;
			common.post(url, {
						currentPage:currentPage//请求参数
						},function(data){
						if(data.state == "00"){
							mui('#pullrefresh').pullRefresh().enablePullupToRefresh();  
							currentPage = 2;
							table.empty();
							var result = data.result;
							append_order(result,table);
						}else{
							mui.toast(data.message);
						}
			});
			mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
			mui.toast('下拉刷新成功');
		}, 1000);
	}
	var count = 0;
	/**
	 * 上拉加载具体业务实现
	 */
	function pullupRefresh() {
		setTimeout(function() {
			
			
			var table =$('.mui-table-view');
			var url = ctx+"/wx/order/ajaxList";
			common.post(url, {currentPage:currentPage
						},function(data){
						if(data.state == "00"){
							var result = data.result;
							mui('#pullrefresh').pullRefresh().endPullupToRefresh(result.length < 5); //参数为true代表没有更多数据了。
							currentPage = currentPage+1;
							
							append_order(result,table);
						}else{
							mui.toast(data.message);
						}
					});
					}, 1000);
	}
	
	var show_state = function(state) {
			switch (state) {
			case "DZF":
				return "待支付"
				break;
			case "YZF":
				return "已支付"
				break;
			case "BLZ":
				return "办理中"
				break;
			case "BLWC":
				return "办理完成"
				break;
			case "QX":
				return "已取消"
				break;
			case "GQ":
				return "过期"
				break;
			default:
				break;
			}
	}
	
	var show_btn =  function(state) {
		var btn_doc = "";
		switch (state) {
		case "DZF":
			btn_doc += '<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">支付<span style="font-size: 16px;" class="mui-icon mui-icon-paperplane"></span></div><div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">取消<span style="font-size: 16px;" class="mui-icon mui-icon-trash"></span></div>';
			break;
		case "YZF":
			btn_doc += '<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">取消<span style="font-size: 16px;" class="mui-icon mui-icon-trash"></span></div>';
			break;
		case "BLZ":
			return "办理中"
			break;
		case "BLWC":
			btn_doc += '<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">删除<span style="font-size: 16px;" class="mui-icon mui-icon-trash"></span></div>';
			break;
		case "QX":
			btn_doc += '<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">删除<span style="font-size: 16px;" class="mui-icon mui-icon-trash"></span></div>';
			break;
		case "GQ":
			btn_doc +='	<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">选择预约时间<span style="font-size: 16px;" class="mui-icon mui-icon-arrowright"></span></div>'
			break;
		default:
			break;
		}
		btn_doc += '<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">联系我们<span style="font-size: 16px;"class="mui-icon mui-icon-phone"></span></div><div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">一键导航<span style="font-size: 16px;"class="mui-icon mui-icon-location"></span></div>'
		return btn_doc;
	}
	
	var append_order = function(result,table) {
		for(var i = 0; i < result.length; i++) {
			
			var order = result[i];
			var platNumber = order.bookInfo.platNumber;
			var state = show_state(order.state);
			var amountPayment = order.amountPayment;
			var bookNumber = order.bookNumber;
			var bookDate  = order.bookInfo.bookDate;
			var bookTime = order.bookInfo.bookTime;
			
			var btn_doc = show_btn(order.state);
			
			var stationName = order.bookInfo.stationName
			var li = document.createElement('li');
			li.className = 'mui-table-view-cell i-li';
			
			li.innerHTML = '<div><span class="mui-h5">预约号：'+platNumber+'</span><div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">'+
			
			state+'</div></div><hr style="height:3px;border:none;border-top:1px double #EEE;"><div><span class="mui-h6">支付金额：'+amountPayment+'</span></div><div><span class="mui-h6">预约号码：'+bookNumber+'</span></div><div><span class="mui-h6">预约时间：'+bookDate +'&nbsp;'+bookTime +'</span></div><div><span class="mui-h6">预约检测站：'+stationName +'</span></div><hr style="height:3px;border:none;border-top:1px double #EEE;">'+
			'<div>'+btn_doc+'</div>';
			
			//下拉刷新，新纪录插到最前面；
			table.append(li);
		}
		/*<div>
			<span class="mui-h5">预约号：'+order.bookInfo.platNumber+' </span>
			<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">'+order.state.description+' </div>
		</div>
		<hr style="height:3px;border:none;border-top:1px double #EEE;">
		<div>
			<span class="mui-h6">支付金额：'+order.amountPayment+' </span>
		</div>
		<div>
			<span class="mui-h6">预约号码：'+order.bookNumber+' </span>
		</div>
		<div>
			<span class="mui-h6">预约时间：'+${order.bookInfo.bookDate }&nbsp;${order.bookInfo.bookTime }+' </span>
		</div>
		<div>
			<span class="mui-h6">预约检测站：'+${order.bookInfo.stationName }+' </span>
		</div>
		<hr style="height:3px;border:none;border-top:1px double #EEE;">
		<div>
			
			<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">联系我们<span style="font-size: 16px;" class="mui-icon mui-icon-phone"></span></div>
			<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">一键导航<span style="font-size: 16px;" class="mui-icon mui-icon-location"></span></div>
		</div>*/
		/*<c:if test="${order.state eq 'DZF'}">
		<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">支付<span style="font-size: 16px;" class="mui-icon mui-icon-paperplane"></span></div>
	</c:if>
	<c:if test="${order.state eq 'DZF' or order.state eq 'YZF'}">
		<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">取消<span style="font-size: 16px;" class="mui-icon mui-icon-trash"></span></div>
	</c:if>
	
	<c:if test="${order.state eq 'QX' or order.state eq 'BLWC'}">
		<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">删除<span style="font-size: 16px;" class="mui-icon mui-icon-trash"></span></div>
	</c:if>
	
	<c:if test="${order.state eq 'GQ'}">
		<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">选择预约时间<span style="font-size: 16px;" class="mui-icon mui-icon-arrowright"></span></div>
	</c:if>*/
	}
	
	
	//提供外部调用区
	a_model.alert1 = alert1;
	
	
	//返回供调用的对象
	return a_model;
	
})
