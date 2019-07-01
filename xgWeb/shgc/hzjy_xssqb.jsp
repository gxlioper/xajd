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
	<base target="_self" />
	<title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="Copyright" content="正方软件 zfsoft" />
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>

</head>

<body>
	<logic:present name="bcjg">
		<logic:equal value="true" name="bcjg">
			<script>
				alert("保存成功！");
				window.dialogArguments.document.getElementById('search_go').click();
				Close();
			</script>
		</logic:equal>
		<logic:equal value="false" name="bcjg">
			<script>
				alert("保存失败！");
			</script>
		</logic:equal>	
	</logic:present>
	<html:form action="hzjy_xssq.do">
		<logic:notPresent name="sh">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message bundle="shgc" key="hzjy_xssq" />
				</div>
			</div>
			<p align="center" style="font-size:15px">
				非合作教育学生参加合作教育申请表
			</p>
			<table align="center" class="tbstyle" style="width:100%;height: 100%"
				id="mytab">
				<tr>

					<td width="100" height="31" align="center">
						学 号
					</td>
					<td width="170" height="31">
						<bean:write name="rs" property="xh" />
					</td>
					<td width="132" height="31" align="center">
						学 院
					</td>
					<td width="144" height="31">
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td width="100" height="31" align="center">
						班 级
					</td>
					<td width="170" height="31">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td width="132" height="31" align="center">
						姓 名
					</td>
					<td width="144" height="31">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td width="100" height="31" align="center">
						班 长
					</td>
					<td width="170" height="31">
						<bean:write name="rs" property="bzxm" />
					</td>
					<td width="132" height="31" align="center">
						班 主 任
					</td>
					<td width="144" height="31">
						<bean:write name="rs" property="bzrxm" />
					</td>
				</tr>
				<tr>
					<td width="100" height="31" align="center">
						家庭电话
					</td>
					<td width="170" height="31">
						<bean:write name="rs" property="jtdh" />
					</td>
					<td width="132" height="31" align="center">
						手机号码
					</td>
					<td width="144" height="31">
						<bean:write name="rs" property="sjh" />
					</td>
				</tr>
				<!-- <tr><td align=center>是否补考</td><td><html:radio property="sfbk" value="yes">&nbsp;&nbsp;&nbsp;是</html:radio>&nbsp;&nbsp;&nbsp;<html:radio property="sfbk" value="no">&nbsp;&nbsp;&nbsp;否</html:radio></td> 
		  <td></td><td></td>
		  </tr>-->
				<tr>
					<td width="540" height="264" colspan="4" valign="top">
						<p>
							申请理由：
						</p>
						<bean:write name="rs" property="sqly" />
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<p align="center">
							申请人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="center">
							&nbsp;年&nbsp; &nbsp;月 &nbsp;&nbsp;日
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>

					</td>
				</tr>
			</table>
			<div align="center">
				<input type="button" value="打印"
					onclick="expTab('mytab','非合作教育学生参加合作教育申请表')" />
			</div>
		</logic:notPresent>


		<logic:present name="sh">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message bundle="shgc" key="hzjy_dgsh" />
				</div>
			</div>
			<input type="hidden" name="pk" value="<bean:write name="pk"/>"/>
			<input type="hidden" name="pkValue" value="<bean:write name="pkValue"/>"/>
			<table class="tbstyle">
				<tr>
					<td width="100" height="31" align="center">
						学 号
					</td>
					<td width="170" height="31">
						<bean:write name="rs" property="xh" />
					</td>
					<td width="132" height="31" align="center">
						学 院
					</td>
					<td width="144" height="31">
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td width="100" height="31" align="center">
						班 级
					</td>
					<td width="170" height="31">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td width="132" height="31" align="center">
						姓 名
					</td>
					<td width="144" height="31">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td width="100" height="31" align="center">
						班 长
					</td>
					<td width="170" height="31">
						<bean:write name="rs" property="bzxm" />
					</td>
					<td width="132" height="31" align="center">
						班 主 任
					</td>
					<td width="144" height="31">
						<bean:write name="rs" property="bzrxm" />
					</td>
				</tr>
				<tr>
					<td align='center'>
						审核结果
					</td>
					<td>
						<html:select property="shjg">
							<html:option value="" />
							<html:option value="未审核" />
							<html:option value="通过" />
							<html:option value="不通过" />
						</html:select>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td width="540" height="50%" colspan="4" valign="top">
						<p>
							申请理由：
						</p>
						<bean:write name="rs" property="sqly" />
					</td>
				</tr>
			</table>
			<center>
				<div class="buttontool" id="btn" 
					width="100%">
					<button class="button2" onclick="document.forms[0].action='hzjy_sh.do?tableName=hzjysqb&sh=sh&doType=save';document.forms[0].submit();">
						保存
					</button>
					<button class="button2" onclick="window.close();return false;">
						关闭
					</button>
				</div>
			</center>
		</logic:present>
	</html:form>
</body>
</html:html>
