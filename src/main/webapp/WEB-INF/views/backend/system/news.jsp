<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}</title>
<%@ include file="/WEB-INF/common/common.jsp"%>

<script type="text/javascript" src="${ctx }/static/backend/js/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/static/backend/js/plugins/ckeditor/ckeditor.js"></script>

<!-- iCheck插件 -->
<link href="${ctx }/static/backend/css/plugins/iCheck/custom.css" rel="stylesheet">
<script src="${ctx }/static/backend/js/plugins/iCheck/icheck.min.js"></script>
<!-- 表单验证 -->
<script src="${ctx }/static/backend/js/plugins/validate/jquery.validate.min.js"></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
    	<div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>新闻咨询编辑</h5>
                    </div>
                    <div class="ibox-content">
                    
                    	<c:set var="url" value="${ctx }/backend/system/news/add"></c:set>
                        <c:if test="${null != news.id}">
                        	<c:set var="url" value="${ctx }/backend/system/news/edit"></c:set>
                        </c:if>
                        <form class="form-horizontal m-t" action="${url }" method="post" name="form" id="form">
	                        <input type="hidden" name="id" value="${news.id }">
                          
                            <div class="form-group">
                                <label class="col-sm-3 control-label">标题：</label>
                                <div class="col-sm-8">
                                	<input name="title" id="title" type="text" class="form-control" value="${news.title }" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">摘要：</label>
                                <div class="col-sm-8">
                                    <input id="summary" name="summary" type="text" class="form-control"  value="${news.summary }" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">作者：</label>
                               <div class="col-sm-8">
                                    <input id="creator" name="creator" type="text" class="form-control"  value="${news.creator }" >
                                	<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 请输入对栏目的描述</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">栏目：</label>
                                <div class="col-sm-8"> 
                                    <select class="form-control m-b" name="columnId" >
                                    	<option value="" >请选择...</option>
                                    	<c:forEach items="${newsColumnList  }" var="item">
											<option value="${item.id }" <c:if test="${item.id eq news.columnId }">selected="selected"</c:if>>
												${item.name }
											</option>
										</c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
								<label class="col-sm-3 control-label">状态：</label>
								<div class="col-sm-8">
									<c:forEach items="${newsStateList }" var="item">
										<label class="checkbox-inline i-checks">
											<input type="radio" name="state" value="${item }" <c:if test="${news.state == item }">checked="checked"</c:if> />${item.description}
										</label>
									</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">热点：</label>
								<div class="col-sm-8">
									<c:forEach items="${hotList }" var="item">
										<label class="checkbox-inline i-checks">
											<input type="radio" name="hot" value="${item }" <c:if test="${news.hot == item }">checked="checked"</c:if> />${item.description}
										</label>
									</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">置顶</label>
								<div class="col-sm-8">
									<c:forEach items="${topList }" var="item">
										<label class="checkbox-inline i-checks">
											<input type="radio" name="top" value="${item }" <c:if test="${news.top == item }">checked="checked"</c:if> />${item.description}
										</label>
									</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">定时时间：</label>
								<div class="col-sm-4">
									<input type="text" id="startDate" name="startDate" value="<fmt:formatDate value='${news.startDate }' pattern='yyyy-MM-dd HH:mm'/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endDate\')}'})"/>
								</div>
								<div class="col-sm-4">
							        <input type="text" id="endDate" name="endDate" value="<fmt:formatDate value='${news.endDate }' pattern='yyyy-MM-dd HH:mm'/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d\'}'})"/>
								</div>
							</div>
							
						    <div class="form-group">
						    <label class="col-sm-3 control-label">内容：</label>
								<div class="col-sm-8">
						     	<textarea name="content" id="content">${news.content }</textarea>
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
<script type="text/javascript">
	$(function() {
		CKEDITOR.replace( 'content' );
		$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})
	})
</script>
</html>
