<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
<head>
	<base target="_self">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link rel="stylesheet" type="text/css" media="all" href="style/calendar.css" title="win2k-cold-1" />
    <script type="text/javascript" src="style/calendar.js"></script>
    <script type="text/javascript" src="style/calendar-zh.js"></script>
    <script type="text/javascript" src="style/calendar-setup.js"></script>
	<script language="JavaScript" src="style/dmwh.js"></script>
	<script language="JavaScript" src="style/xjgl.js"></script>
	<script language="JavaScript" src="style/jhgl.js"></script>
	<script language="javascript">
	function checkForm(){
			var xh = document.forms(0).xh.value;
			if(xh==""){
				alert("请输入学号！");
				return false;
			}
			changTab('/yjsjwgl/jybdzzg.do?dotype=add');
		}
	function checkModify(){
			var xh = document.forms(0).xh.value;
			if(xh==""){
				alert("请输入学号！");
				return false;
			}
			changTab('/yjsjwgl/jybdzzg.do?dotype=modify');
		}
	</script>
</head>
 <%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<body>
	<html:form method="POST" action="jytjgl"
		enctype="multipart/form-data">		
		<logic:equal name="page" value="add">
		<div id="title">
			<div class="titiel_img"></div>
			当前所在位置：就业报到证管理-->增加页面
		</div>
		<div>
			<table width="99%">
				<tr>
					<td align="center" width="45%">
						<font color="red">*</font>学号
					</td>
					<td><input type="text" name="xh" /></td>
				</tr>
				<tr>
					<td align="center" width="45%">
						办理时间
					</td>
					<td><input type="text" name="blsj" 
							onclick="return showCalendar('blsj','y-mm-dd');"/></td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						领取时间
					</td>
					<td><input type="text" name="lqsj" 
							onclick="return showCalendar('lqsj','y-mm-dd');"/></td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						领取人
					</td>
					<td><input type="text" name="lqr"/></td>	
				</tr>
			</table>
			<div id="button">
				<button class="button2" onclick="checkForm()">
					增 加
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="window.close();return false;">
					关 闭
				</button>
			</div>
		</div>
		</logic:equal>
	
	<!-- 以上为增加页面内容，以下为修改页面内容 -->
		<logic:equal name="page" value="modify">
		<div>
			<div id="title">
				<div class="titiel_img"></div>
				当前所在位置：就业报到证管理-->修改页面
			</div>
			<input type="hidden" name="dm" 
				value="<bean:write name="dm" scope="request"/>"/>
			<table width="99%">
				<tr>
					<td align="center" width="45%">
						<font color="red">*</font>学号
					</td>
					<td><html:text property="xh" name="map"></html:text>  </td>
				</tr>
				<tr>
					<td align="center" width="45%">
						办理时间
					</td>
					<td>
						<html:text property="blsj" name="map"
								onclick="return showCalendar('blsj','y-mm-dd');">
						</html:text>
					</td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						领取时间
					</td>
					<td><html:text property="lqsj" name="map"
							onclick="return showCalendar('lqsj','y-mm-dd');"></html:text> </td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						领取人
					</td>
					<td><html:text property="lqr" name="map"></html:text> </td>	
				</tr>
			</table>
			<div id="button">
				<logic:notPresent name="ck">
				<button class="button2" onclick="checkModify();">
					修 改
				</button>
				</logic:notPresent>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="window.close();return false;">
					关 闭
				</button>
			</div>
		</div>
			</logic:equal>
			<logic:equal name="result" value="view">
				<script>
			    alert("<bean:write name='jyglForm' property='message'/>");
			    window.dialogArguments.document.forms[0].action='/yjsjwgl/jybdzgl.do?act=find';
			   	window.dialogArguments.document.forms[0].submit();
			   	window.close();
			  	</script>
			</logic:equal>
	</html:form>
</body>

</html:html>

