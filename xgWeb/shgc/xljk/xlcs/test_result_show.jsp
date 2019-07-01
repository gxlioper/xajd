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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<title>在线普测</title>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
</head>
<body>
<form action="">
	<logic:present name="doPaper" scope="request">
		<script>	
			alert("该试卷您已经有作答，点确定查看您的作答结果!");
		</script>
	</logic:present>
	<logic:present name="py_xljk" scope="request">
		<bean:write name="py_xljk"/>
	</logic:present>
	<logic:notPresent name="py_xljk" scope="request">
	<p align="center"><strong style="font-size:15px">测试结果如下：</strong></p>
	<table align="center"  width="100%" id="rsTable" class="tbstyle">
		<thead>
			<tr align="center">
				<logic:equal value="yes" name="view">
				<td align="center">
					因子
				</td>
				</logic:equal>
				<td align="center">
					因子名字
				</td>
				<logic:equal value="yes" name="view">
				<td align="center">
					标准得分
				</td>
				</logic:equal>
				<td align="center">
					实际得分
				</td>
				<td align="center">
					评语
				</td>
			</tr>	
		</thead>
		<logic:equal value="yes" name="view">
			<logic:iterate id="rs" name="py">
			<tr align="center">
				<td><bean:write name="rs" property="yxz"/></td>
				<td><bean:write name="rs" property="pyArray"/></td>
				<td><bean:write name="rs" property="bzfsArray"/></td>
				<td><bean:write name="rs" property="sjdf"/></td>
				<td><bean:write name="rs" property="py"/></td>
			</tr>
			</logic:iterate>
		</logic:equal>
		<logic:equal value="no" name="view">
			<logic:iterate id="rs" name="py">
			<tr align="center">
				<td><bean:write name="rs" property="yxmc"/></td>
				<td><bean:write name="rs" property="df"/></td>
				<td><bean:write name="rs" property="bzpy"/></td>
			</tr>
			</logic:iterate>
		</logic:equal>
		
	</table>
	<p align="center" >人的性格是发展变化的，也是可以塑造的，本结果仅供参考，不得作为其它评定的依据!</p>
	<p align="center"><input type="button" class="button2" onclick="window.close();return false;"  value="关闭窗口"></p>
	</logic:notPresent>	
</form>
</body>
</html>