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
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdxx.js"></script>
		<script type="text/javascript">
			//初始化
			jQuery(document).ready(function(){ 
				var pxfs = '${pxfs}';
				if(!!pxfs){
					searchRs('1',pxfs)
				}else{
					searchRs('1','zxsx');
				}
			})
		</script>
		
	</head>

	<body>
		<html:form action="/hdgl_hdxx" method="post" styleId="hdxxForm" onsubmit="return false;">
			 <div class="secondclass_details container" style="width:100%;">
				<ul class="nav-tabs nav panel-heading notice-tabs col-sm-12 p-0">
					<li class="active boder-right"><a href="javascript:void(0);" data-toggle="tab" onclick="searchRs('1','zxsx');return false;" id="zxsx">最新上线活动</a></li>
					<li class="boder-right"><a href="javascript:void(0);" data-toggle="tab" onclick="searchRs('1','zjjb');return false;" id="zjjb">最近举办活动</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" onclick="searchRs('1','wcj')" id="wcj">我参加的活动</a></li>
				</ul>
			</div>
			
			<input type="hidden" id="sy" value="${sy}" />
			<input type="hidden" id="xy" value="${xy}" />
			<input type="hidden" id="ymmc" name="ymmc" value="zxsx" />
			<div class="container" id="activity-list" style="width:100%;">
				

			</div>
			<div class="text-center">
				<jsp:include flush="true" page="/xsgzgl/hdgl/hdxx/turnpage.jsp?form=hdxxForm"></jsp:include>
			</div>
		</html:form>
</html>
