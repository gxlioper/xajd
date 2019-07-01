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
<html:html locale="true">
<head>
	<title><bean:message key="lable.title" />
	</title>
	<%
			response.setHeader("Pragma","No-cache");
			response.setHeader("Cache-Control","no-cache");
			response.setDateHeader("Expires", 0);
		%>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="Copyright" content="正方软件 zfsoft" />
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>

</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			<bean:message bundle="shgc" key="hzjy_bgsq" />
		</div>
	</div>
	<html:form action="/hzjy_bggzsq">
		<p align="center">
			<strong style="font-size:15px">变更产学合作教育雇主单位申请表</strong>
		<table class="tbstyle" style="width:100%;" align="center" id="tab">
			<tr align="center">
				<td width="20%" height="20%">
					姓 名
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="xm" />
				</td>
				<logic:notEqual value="teacher" name="userOnLine" scope="session">
					<td width="156" height="20%">
						<bean:write name="rs" property="xh" />
				</logic:notEqual>
				<td width="20%" height="20%">
					学 院
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="xymc"  />
				</td>
			</tr>
			<tr align="center">
				<td width="20%" height="20%">
					班 级
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="bjmc"  />
				</td>
				<td width="20%" height="20%">
					学 号
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="xh"  />
				</td>
			</tr>
			<tr align="center">
				<td width="20%" height="20%">
					原雇主单位名称
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="ygzdwmc"  />
				</td>
				<td width="20%" height="20%">
					原单位联系人
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="ygzdwlxr"  />
				</td>
			</tr>
			<tr align="center">
				<td width="20%" height="20%">
					原单位联系电话
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="ygzdwlxdh"  />
				</td>
			</tr>
			<tr align="center">
				<td width="20%" height="20%">
					新雇主单位名称
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="xgzdwmc"  />
				</td>
				<td width="20%" height="20%">
					新雇主单位地址
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="xgzdwdz"  />
				</td>
			</tr>
			<tr align="center">
				<td width="20%" height="20%">
					区域
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="qy"  />
				</td>
				<td width="96" height="20%">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮编
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="yzbm"  />
				</td>
			</tr>
			<tr align="center">
				<td width="20%" height="20%">
					联系人
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="xgzdwlxr"  />
				</td>
				<td width="96" height="20%">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部门
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="xgzdwbm"  />
				</td>
			</tr>
			<tr align="center">
				<td width="20%" height="20%">
					联系电话
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="xgzdwlxdh"  />
				</td>
				<td width="96" height="20%" align=center>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机号码
				</td>
				<td width="156" height="20%">
					<bean:write name="rs" property="xgzdwsjh"  />
				</td>
			</tr>
			<tr>
				<td width="540" height="50" colspan="5" valign="top">
					申请理由：
					<br>
					<bean:write name="rs" property="sqly" />
					<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申	 请 人：
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年	月   日
					</p>
				</td>
			</tr>
		</table>
		<div class="buttontool" align=center>
			<button class="button2" onclick="expTab('tab','变更产学合作教育雇主单位申请表')" style="width:10%">
				打印
			</button>
		</div>
	</html:form>
</body>
</html:html>
