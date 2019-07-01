<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page language="java" import="org.owasp.encoder.Encode"  pageEncoding="GBK"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
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
		
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		function xfsq(lx){
			var url = "dekt_xfsq.do?method=xfsqAdd&lx="+encodeURI(encodeURI(lx));
			var title = "学分申请";
			showDialog(title,800,500,url);
		}
		</script>
	</head>

	<body>
		<%-- <div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div> --%>
		<div class="credit-apply-page m-t20">
			<div >
			<%@ include file="/comm/hiddenValue.jsp"%>
				<ul>
				
				<%List<HashMap<String,Object>>sqlb=(List<HashMap<String,Object>>)request.getAttribute("sqlb");
				for (HashMap<String,Object> map:sqlb){
				%>
					<li>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 padding-r0">
							<div class="activity-type">
								<img src="assets/images/<%=map.get("tb")%>"/>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 padding-l0">
							<h4 class="title"><%=map.get("lx")%></h4>
							<p><b>认定项目：</b>
							<%
							List<HashMap<String,String>> list=(List<HashMap<String,String>>)map.get("rdxm");
							for(HashMap<String,String> rdxmmap:list){
								%><%=rdxmmap.get("rdxm")%>  <%
							}
							%>
							</p>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 text-right"><a href="#"><button type="button" class="btn btn-primary green-bg-btn" onclick="xfsq('<%=map.get("lx")%>');">我要申请</button></a></div>
					</li>
					<%} %>
					
				</ul>
			</div>
		</div>
	</body>
</html>
