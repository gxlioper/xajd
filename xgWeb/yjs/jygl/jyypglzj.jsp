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
			changTab('/yjsjwgl/jyypzg.do?dotype=add');
		}
	function checkModify(){
			var xh = document.forms(0).xh.value;
			if(xh==""){
				alert("请输入学号！");
				return false;
			}
			changTab('/yjsjwgl/jyypzg.do?dotype=modify');
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
			当前所在位置：就业应聘管理-->增加页面
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
						参加招聘会时间
					</td>
					<td><input type="text" name="cjzphsj" 
					   onclick="return showCalendar('cjzphsj','y-mm-dd');"/></td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						参加招聘会次数
					</td>
					<td><input type="text" name="cjzphcs" /></td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						获得事业单位意向份数
					</td>
					<td><input type="text" name="hdsydwyxfs" /></td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						获得科研单位意向份数
					</td>
					<td><input type="text" name="hdkydwyxfs" /></td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						获得企业意向份数
					</td>
					<td><input type="text" name="hdqyyxfs" /></td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						参加面试次数
					</td>
					<td><input type="text" name="cjmscs" /></td>	
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
				当前所在位置：就业应聘管理-->修改页面
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
						参加招聘会时间
					</td>
					<td>
						<html:text property="cjzphsj" name="map"
							onclick="return showCalendar('cjzphsj','y-mm-dd');">
						</html:text>
					</td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						参加招聘会次数
					</td>
					<td><html:text property="cjzphcs" name="map"></html:text> </td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						获得事业单位意向份数
					</td>
					<td><html:text property="hdsydwyxfs" name="map"></html:text> </td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						获得科研单位意向份数
					</td>
					<td><html:text property="hdkydwyxfs" name="map"></html:text> </td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						获得企业意向份数
					</td>
					<td><html:text property="hdqyyxfs" name="map"></html:text> </td>	
				</tr>  
				<tr>
					<td align="center" width="45%">
						参加面试次数
					</td>
					<td><html:text property="cjmscs" name="map"></html:text> </td>	
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
			    window.dialogArguments.document.forms[0].action='/yjsjwgl/jyypgl.do?act=find';
			   	window.dialogArguments.document.forms[0].submit();
			   	window.close();
			  	</script>
			</logic:equal>
	</html:form>
</body>

</html:html>

