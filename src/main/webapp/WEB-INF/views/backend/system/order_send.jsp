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
		<div class="wrapper wrapper-content animated fadeInRight" style="padding: 0px;">
    	<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins" style="margin-bottom: 0px;">
                    <div class="ibox-content">
                        <form class="form-horizontal m-t">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">快递单号：</label>
                                <div class="col-sm-8">
                                	<input type="text" id="courierNumber" name="courierNumber" class="form-control" />
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i>填写对应发货快递单单号</span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                </div>
            </div>
      </div>
 </body>
</html>