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
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdxq.js"></script>
		
	</head>

	<body>
		<div class="secondclass_details container" style="width:100%;">
				<ul class="nav-tabs nav panel-heading notice-tabs col-sm-12 p-0">
					<li class="active boder-right"><a href="javascript:void(0);" data-toggle="tab" id="zxxq">最新详情</a></li>
					<logic:equal name="model" property="bmsf" value="1">
					<li><a href="javascript:void(0);" data-toggle="tab" id="bmgl">报名审核</a></li>
					</logic:equal>
					<li class="boder-right"><a href="javascript:void(0);" data-toggle="tab" id="plgl">评论管理</a></li>
					
<%--					<li class="active boder-right"><a href="javascript:void(0);" data-toggle="tab" onclick="getHdInfo('${data.hdid}');return false;" id="zxxq">最新详情</a></li>--%>
<%--					<li class="boder-right"><a href="javascript:void(0);" data-toggle="tab" onclick="plgl('${data.hdid}');return false;" id="plgl">评论管理</a></li>--%>
<%--					<li><a href="javascript:void(0);" data-toggle="tab" onclick="bmgl('${data.hdid}')" id="bmgl">报名管理</a></li>--%>
					
				</ul>
		</div>
		<input type="hidden" id="hdid" value="${model.hdid}" />
		<input type="hidden" id="bmlx" value="${model.bmlx}" />
		<div id="content_hdxq"></div>
		<div id="content_plgl"></div>
		<div id="content_bmgl"></div>
	</body>
	
	<script type="text/javascript">
		
			jQuery(document).ready(function(){
				//最新详情点击事件
				jQuery("#zxxq").on("click",function(){
					getHdInfo();
					jQuery("#zxxq").parent().attr('class','boder-right active');
					jQuery("#plgl").parent().attr('class','boder-right');
					jQuery("#bmgl").parent().attr('class','boder-right');
				});

				//评论管理点击事件
				jQuery("#plgl").on("click",function(){
					plgl();
					jQuery("#zxxq").parent().attr('class','boder-right');
					jQuery("#plgl").parent().attr('class','boder-right active');
					jQuery("#bmgl").parent().attr('class','boder-right');
				});

				//报名审核点击事件
				jQuery("#bmgl").on("click",function(){
					bmgl();
					jQuery("#zxxq").parent().attr('class','boder-right');
					jQuery("#plgl").parent().attr('class','boder-right');
					jQuery("#bmgl").parent().attr('class','boder-right active');
				});

				jQuery("#zxxq").trigger("click");
			})
	</script>
</html>
