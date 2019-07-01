<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />

	<meta name="Copyright" content="正方软件 zfsoft" />

<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<base target="_self">
<link id="csssDate" rel="stylesheet" rev="stylesheet"
	href="js/calendar.css" type="text/css" media="all" />

<script language="javascript" src="js/function.js"></script>
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>

<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
</style>
	<script type="text/javascript">
	function toSearch(){
	   if($("nj")){
	      if($("nj").value==""){
	         alert("请选择年级！");
	         return false;
	      }
	   }
	   if($("xydm")){
	      if($("xydm").value==""){
	         alert("请选择<bean:message key="lable.xsgzyxpzxy" />！");
	         return false;
	      }
	   }
	   document.forms[0].go.value='go';
	   refreshForm('/xgxt/sztzXfTjManage.do');
	   $("search_go").disabled=true;
	}
	</script>
	<body>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
        %>
		<center>			
			<html:form action="/sztzXfTjManage" method="post">	
				<div align="center" style="font-size: 20px;font:bold">大学生素质培养拓展学分统计表</div>
				<br>				
				<div align="left" style="font-size: 12px;font:bold">
				系部:<u>&nbsp;&nbsp;<bean:write name="xymc"/>&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				年级:<u>&nbsp;&nbsp;<bean:write name="nj" />&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<logic:notEqual value="" name="zymc">
				专业:<u>&nbsp;&nbsp;<bean:write name="zymc" />&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notEqual>
				<logic:notEqual value="" name="bjmc">
				班级:<u>&nbsp;&nbsp;<bean:write name="bjmc" />&nbsp;&nbsp;</u>
				</logic:notEqual>
				</div>
				
				<logic:notEmpty name="rs">
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand">
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="4" length="1">
											 <img src="fdygl/fdygzdc/total.jpg" width="<bean:write name="v" />" height="10px"/>
											<bean:write name="v" />
										</logic:iterate>
									</td>
								</tr>
							</logic:iterate>
						</table>									
				</logic:notEmpty>

			</html:form>
		</center>			
	</body>
<div class='noPrin' align="center">
	<input type='button' class='button2' value='页面设置'
		onclick="WebBrowser.ExecWB(8,1);return false;">
	<input type='button' class='button2' value='打印预览'
		onclick="WebBrowser.ExecWB(7,1);return false;">
	<input type='button' class='button2' value='直接打印'
		onclick="WebBrowser.ExecWB(6,6);return false;">
</div>	
</html:html>

