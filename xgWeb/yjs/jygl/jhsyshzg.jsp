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
	function checkModify(){
			changTab('/yjsjwgl/jhsysh.do?act=dosh');
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
		
		<div id="title">
			<div class="titiel_img"></div>
			当前所在位置：计划生育管理-->审核页面
		</div>
		<div>
			<fieldset>
				<legend>
					<font color="blue">计划生育申请审核</font>
				</legend>
				<input type="hidden" name="dm" value="${dm}">
				<input type="hidden" name="page" value="${page}">
				<table width="99%">
					<tr>
						<td align="center">
							学号
						</td>
						<td><input type="text" value="${map.xh}"/></td>
						<td align="center">
							姓名
						</td>
						<td><input type="text" value="${map.xm}"/></td>
					</tr>
					<tr>
						<td align="center">
							年级
						</td>
						<td><input type="text" value="${map.nk}"/> </td>
						<td align="center">
							出生日期
						</td>
						<td><input type="text" value="${map.csrq}"/></td>	
					</tr>
					<tr>
						<td align="center">
							身份证号
						</td>
						<td><input type="text" value="${map.sfzh}"/></td>
						<td align="center">
							培养类型
						</td>
						<td><input type="text" value="${map.pyccmc}"/></td>	
					</tr>
					<tr>
						<td align="center">
							婚姻状况
						</td>
						<td><input type="text" value="${map.hyzk}"/></td>
						<td align="center">
							籍贯
						</td>
						<td><input type="text" value="${map.jgmc}"/></td>	
					</tr>
					<tr>
						<td align="center">
							申请时间
						</td>
						<td><input type="text" value="${map.sqsj}"/></td>
						<td align="center">
							申请状态
						</td>
						<td>
							<logic:equal value="bm" name="page">
								<html:select property="bmshzt" style="width:150px">
								<html:option value="${map.bmshztdm}">--${map.bmshzt}--</html:option>
								<html:option value="0">未审核</html:option>
								<html:option value="1">通过</html:option>
								<html:option value="2">未通过</html:option>
								</html:select>
							</logic:equal>
							<logic:equal value="xy" name="page">
								<html:select property="xyshzt" style="width:150px">
								<html:option value="${map.xyshztdm}">--${map.xyshzt}--</html:option>
								<html:option value="0">未审核</html:option>
								<html:option value="1">通过</html:option>
								<html:option value="2">未通过</html:option>
								</html:select>
							</logic:equal>
							<logic:equal value="ds" name="page">
								<html:select property="dsshzt" style="width:150px">
								<html:option value="${map.dsshztdm}">--${map.dsshzt}--</html:option>
								<html:option value="0">未审核</html:option>
								<html:option value="1">通过</html:option>
								<html:option value="2">未通过</html:option>
								</html:select>
							</logic:equal>
							<logic:equal value="yws" name="page">
								<html:select property="ywsshzt" style="width:150px">
								<html:option value="${map.ywsshztdm}">--${map.ywsshzt}--</html:option>
								<html:option value="0">未审核</html:option>
								<html:option value="1">通过</html:option>
								<html:option value="2">未通过</html:option>
								</html:select>
							</logic:equal>
						</td>
					</tr>
					<tr height="100px">
						<td align="center">
							申请信息
						</td>
						<td colspan="3">
							<html:textarea property="" value="${map.sqxx}" style="width:350px;height:95px"></html:textarea> 
						</td>
					</tr>
				</table>
			</fieldset>
			<div id="button">
				<logic:notPresent name="ck">
				<button class="button2" onclick="checkModify();">
					审 核
				</button>
				</logic:notPresent>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="window.close();return false;">
					关 闭
				</button>
			</div>
	   </div>
			<logic:equal name="result" value="view">
				<script>
			    alert("<bean:write name='jyglForm' property='message'/>");
			    var page = window.dialogArguments.document.forms[0].page.value;
			    window.dialogArguments.document.forms[0].action='/yjsjwgl/jhsysh.do?act=find&page'+page;
			   	window.dialogArguments.document.forms[0].submit();
			   	window.close();
			  	</script>
			</logic:equal>
	</html:form>
</body>

</html:html>

