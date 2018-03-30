<%@ page language="java" pageEncoding="UTF-8"%>
<style>
	.ue-clear:after {
	content: "";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
}

.ue-clear {
	display: inline-block;
}

* html .ue-clear {
	height: 1%;
}

.ue-clear {
	display: block;
	overflow: hidden;
}

.placeholder {
	color: #ddd;
}

.pagination a {
	text-decoration: none;
	border: solid 1px;
}

.pagination .pxofy {
	float: left;
	margin-left: 5px;
	height: 25px;
	line-height: 25px;
}

.pagination a,.pagination span {
	display: block;
	float: left;
	line-height: 24px;
	padding: 0 9px;
	border-radius: 2px;
	margin-right: 5px;
	font-family: Arial, Helvetica, sans-serif !important;
}

.pagination .current {
	cursor: default;
	border: solid 1px;
}

.pagination .prev,.pagination .next {
	*line-height: 22px;
}

.pagination a {
	color: #000000;
	border-color: #8EB2D2;
	background: #eaf4fa;
}

.pagination a:hover {
	color: #023054;
	border-color: #8EB2D2;
	background: #B8DFFB;
}

.pagination .current {
	color: #fff;
	border-color: #4ea052;
	background: #5ebc62;
}

.pagination .current.prev,.pagination .current.next {
	color: #B9B9B9;
	border-color: #D3D3D3;
	background: #fff;
}

.pagination .pxofy {
	color: #023054;
}
</style>
<div class="pagination ue-clear"></div>
<script type="text/javascript" src="${ctx }/static/backend/js/jquery.pagination.js"></script>
<script type="text/javascript">
	//到指定的分页页面
	function topage(currentPage){
		var form = document.forms[0];
		form.currentPage.value=currentPage;
		form.submit();
	}
	$('.pagination').pagination( ${pageView.total }, {
			callback: function(page) {
				topage(page+1);
			},
			current_page : ${pageView.pageNum}-1,
			items_per_page : ${pageView.pageSize },
			display_msg: true,
			setPageNo: false
		});
</script>