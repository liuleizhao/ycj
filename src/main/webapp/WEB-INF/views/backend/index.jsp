<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>${appName }-后台用户登录</title>
    <%@ include file="/WEB-INF/common/common.jsp"%>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">${user.name }</strong></span>
                                <span class="text-muted text-xs block">${user.userType.description }</span>
                                </span>
                            </a>
                        </div>
                        <div class="logo-element">xx系统</div>
                    </li>
                    <c:if test="${not empty parentResources}">
			   			<c:forEach var="resource" items="${parentResources }">
			   				<li>
		   					<a href="#">
	                            <i class="fa fa-home"></i>
	                            <span class="nav-label">${resource.name}</span>
	                            <span class="fa arrow"></span>
	                        </a>
				   						
							<!-- 获取二级节点 -->
							<c:if test="${not empty childResources}"> 
								<c:set value="${resource.id }" var="rId"></c:set>
								 <ul class="nav nav-second-level">
								 	 <c:forEach var="twoResource" items="${childResources[rId]}">
									<li><a class="J_menuItem" href="${ctx }${twoResource.path }">${twoResource.name }</a></li>  
									 </c:forEach>
		                            
		                          </ul>
							</c:if>
							</li>
			   			</c:forEach>
			    	</c:if>  	
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
        	 <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 10px">
                	<a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
               </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="home">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="login.html" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${ctx }/backend/home" frameborder="0" data-id="home"></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">&copy; 2014-2015 <a href="http://www.zi-han.net/" target="_blank">zihan's blog</a>
                </div>
            </div>
        </div>
    </div>
    <script src="${ctx }/static/backend/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx }/static/backend/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx }/static/backend/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${ctx }/static/backend/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${ctx }/static/backend/js/plugins/layer/layer.min.js"></script>
    <script src="${ctx }/static/backend/js/hplus.min.js?v=4.1.0"></script>
    <script src="${ctx }/static/backend/js/contabs.min.js" type="text/javascript"></script>
    <script src="${ctx }/static/backend/js/plugins/pace/pace.min.js"></script>
</body>
</html>
