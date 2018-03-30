/*配置模块信息*/
define(function(){
   require.config({
	   	urlArgs: "r=1.3",
		baseUrl: ctx + "/static/wx/js",
		paths: {
			"jquery": "lib/jquery/jquery-3.3.1.min",
			"mui":"lib/mui/mui.min",
			"picker":"lib/mui/picker",
			"poppicker":"lib/mui/poppicker",
			"wx":"lib/wx/jweixin-1.2.0",
			
			"index":"view/index",
			"common":"view/common",
			
			"offic_book_agreement":"view/offic_book_agreement",
			"offic_book_input":"view/offic_book_input",
			"offic_book_login":"view/offic_book_login",
			"offic_book_org":"view/offic_book_org",
			
			"own_book_way":"view/own_book_way",
			"own_book_car":"view/own_book_car",
			"own_car_input":"view/own_car_input",
			"own_book_input":"view/own_book_input",
			"book_ok":"view/book_ok",
			
			"car_info_list":"view/car_info_list",
			"car_info_detail":"view/car_info_detail",
			
			"book_info_list":"view/book_info_list",
			"book_info_detail":"view/book_info_detail",
				
			"order_list":"view/order_list"
			
		},shim:{
			'picker': {
	            deps: ['mui'],
	            exports: 'picker'
	        },
	        'poppicker': {
	            deps: ['mui'],
	            exports: 'poppicker'
	        }
		}
	})
});
