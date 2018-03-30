require(['config'],function(){
	require(["common"], function(common) {
		
		var targetModule = $("#currentPage").attr("target-module");
		require([targetModule], function(page) {
			
		})
	})
});