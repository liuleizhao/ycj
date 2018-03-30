<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}</title>
<%@ include file="/WEB-INF/common/common.jsp"%>

</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
    	<div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>全局参数添加/编辑</h5>
                    </div>
                    <div class="ibox-content">
                    	<c:set var="url" value="${ctx }/backend/system/globalConfig/add"></c:set>
                        <c:if test="${null != globalConfig.id}">
                        	<c:set var="url" value="${ctx }/backend/system/globalConfig/edit"></c:set>
                        </c:if>
                        <form class="form-horizontal m-t" action="${url }" method="post" name="form" id="form">
                           	<input type="hidden" name="id" value="${globalConfig.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-8">
                                	<input name="name" id="name" type="text" class="form-control" value="${globalConfig.name }" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">key：</label>
                                <div class="col-sm-8">
                                    <input id="dataKey" name="dataKey" type="text" class="form-control"  value="${globalConfig.dataKey }" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">value：</label>
                               <div class="col-sm-8">
                                    <input id="dataValue" name="dataValue" type="text" class="form-control"  value="${globalConfig.dataValue }" >
                                </div>
                            </div>
							
							 <div class="form-group">
                                <label class="col-sm-3 control-label">描述：</label>
                               <div class="col-sm-8">
                                    <input id="description" name="description" type="text" class="form-control"  value="${globalConfig.description }" >
                                	<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 请输入对变量的描述</span>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">保存</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
      </div>
</body>
</html>
