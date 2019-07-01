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
	
		<%@ include file="/syscommon/autocomplete.ini"%>
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
		
		
		<script type="text/javascript" src="xsgzgl/dekt/qnzyhd/js/qnzyhd.js"></script>

		
		<script type='text/javascript'>
			
		</script>
		
	</head>
	<body style="width: 100%">		
				<div class="activity-details-page m-t20">
			<div class="container">
				<input type="hidden" id="gs" value="${data.jbfwgs}" />
				<h4 class="title text-center">${data.hdmc}</h4>
				<logic:empty name="data" property="fjpath">
					<div class="text-center m-t20"><img src="default_dekt.jpg" style="width:100%;height:360px;"></div>
				</logic:empty>
				<logic:notEmpty name="data" property="fjpath">
					<div class="text-center m-t20"><img src="${data.fjpath}" style="width:100%;height:360px;"></div>
				</logic:notEmpty>
				<div class="content">
					<div class="activity-info m-t20">
						<div class="row">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12"><p>负责人：${data.fzrxm}/${data.hdfzrlxfs}</p></div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 apply-num text-right"><p>已报/限定人数：【<span class="num">${data.ybrs}</span>/${data.xdrs}人】</p></div>
						</div>
						<p>组织部门：${data.zzbm}</p>
						<p>
							活动时间：${data.hdkssj}
							<logic:notEmpty name="data" property="hdjssj">
								至 ${data.hdjssj}
							</logic:notEmpty>
						</p>
						<p>活动地点： ${data.hddd}</p>
					</div>
					<div class="article m-t20">
						${data.hdxq}
					</div>
					<div class="text-center">
						<button type="button" class="btn btn-primary green-bg-btn apply-btn" onclick="savebm('${data.hdid}');return false;">立即报名</button>
						<button type="button" class="btn btn-primary green-bg-btn apply-btn" onclick="fh();return false;">返回</button>
					</div>
				</div>
			</div>
		</div>		 
	</body>
</html>

