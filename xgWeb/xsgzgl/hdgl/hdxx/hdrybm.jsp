<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>
<%@ include file="/syscommon/v4_url.ini"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript">
			var stylePath = "<%=stylePath%>";
		</script>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="assets/css/style.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="bjwh/bootstrap-datepicker/bootstrap-datepicker.css"/>

		<script language="javascript" src="comm/editor/kindeditor.js"></script>
		<script language="javascript" src="comm/editor/zh_CN.js"></script>
		<script language="javascript" src="comm/editor/editor.js"></script>
		<script type="text/javascript" src="js/function.js"></script>
		<%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/comm/ymPrompt.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript' src="js/comm/watermark.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<link rel="stylesheet" href="comm/skin/zfstyle/ymPrompt.css" type="text/css" media="all" />
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="bjwh/bootstrap-datepicker/bootstrap-datepicker.js"/>
		<script type="text/javascript" src="bjwh/bootstrap-datepicker/bootstrap-datepicker.zh-CN.js"/>
		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdxx.js"></script>
		<script type="text/javascript">
			
		</script>
		
	</head>

	<body>
		<html:form action="/hdgl_hdxx" method="post" styleId="hdxxForm" onsubmit="return false;">
			<input type="hidden" id="hdid" name="hdid" value="${data.hdid}" />
			<input type="hidden" id="hdmc" value="${data.hdmc}" />
			 <div class="secondclass_details container" style="width:100%;">
				<div class="panel panel-default margin_t15 p-10 index_list">
					<div class="details-con">
						<div class="img">
							<img src="${data.hb}">
						</div>
					   <div class="panel panel-default margin_t15 p-10 index_list">
							<div class="details_title">
								<h4>
									${data.hdmc}
								</h4>
								<div class="form-group">
									<label>时间：</label>
									<span class="time">${data.hdkssj} - ${data.hdjssj}</span>
								</div>
								<div class="form-group">
									<label>地点：</label>
									<span class="address">${data.hddd}</span>
								</div>
								<div class="form-group">
									<label>类型：</label>
									<span class="address">${data.hdlxmc}</span>
								</div>
								<div class="form-group">
									<label>发布方：</label>
									<span class="address">${data.fbf}</span>
								</div>
								<logic:equal value="0" name="data" property="bmlx">
									<div class="form-group">
										<label>队伍人数：</label>
										<span class="address">${data.dwrs}</span>
									</div>
								</logic:equal>

								<h4>活动介绍</h4>
								<span>
									${data.nryq}
								</span>
							</div>
							<div class="button-groups p-10">
								<button class="btn btn-primary" onclick="fh()">
									返回
								</button>
								
								<logic:equal value="0" name="data" property="bmlx">
									<button class="btn btn-primary" onclick="zdbmview();return false;">
										创建团队报名
									</button>
									<button class="btn btn-primary" onclick="bmForGroup();return false;">
										加入团队报名
									</button>
								</logic:equal>

								<logic:equal value="1" name="data" property="bmlx">
									<button class="btn btn-primary" onclick="bmview();return false;">
										我要报名
									</button>
								</logic:equal>
							</div>
						</div>
	                </div>
	            </div>
        	</div>
		<!--提示框弹框-->
		<div class="modal fade" id="msgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<a type="button" class="close" data-dismiss="modal"
						   aria-hidden="true">×
						</a>
						<h4 class="modal-title" id="msgTitle">
							提示
						</h4>
					</div>
					<div class="modal-body" id="msgContent">
						按下 ESC 按钮退出。
					</div>
					<div class="modal-footer">
						<a type="button" class="btn btn-default"
						   data-dismiss="modal">关闭
						</a>
					</div>
				</div>
			</div>
		</div>
	</html:form>
</html>
