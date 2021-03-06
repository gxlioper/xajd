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
		<title><bean:message key="lable.title" /></title>
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
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于1000个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("学校审核意见不能大于1000个字符");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/nblg_xszz.do?method=zxszxjshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/nblg_xszz.do?method=zxszxjshSave" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 慈善总会高校在校生助学金审核 - 单个审核
				</div>
			</div>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="6" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="2">
						学号
					</td>
					<td align="left" colspan="2">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
						<input type="hidden" id="xn" name="xn"
							value="<bean:write name='rs' property="xn" />" />
					</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="nj"/>
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							专业名称
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="center">
							班级名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							出生年月
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="csny"/>
					</td>
					<td>
						<div align="center">
							籍贯
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jg"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭住址
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtzz"/>
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="lxdh"/>
					</td>
				</tr>
				<!--非宁波天一-->
				<logic:notEqual value="13742" name="xxdm">
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							家
							<br />
							庭
							<br />
							成
							<br />
							员
							<br />
							情
							<br />
							况
						</div>
					</td>
					<td width="12%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="17%">
						<div align="center">
							称谓
						</div>
					</td>
					<td width="17%">
						<div align="center">
							月收入
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							职业和工作(学习)单位
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy1_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy1_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy1_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy1_gz" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy2_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy2_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy2_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy2_gz" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy3_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy3_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy3_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy3_gz" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy4_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy4_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy4_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy4_gz" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy5_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy5_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy5_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy5_gz" />
					</td>
				</tr>
				</logic:notEqual>
				<!--宁波天一-->
				<logic:equal value="13742" name="xxdm">
					<tr>
						<td width="4%" rowspan="4">
							<div align="center">
								家
								<br />
								庭
								<br />
								成
								<br />
								员
								<br />
								情
								<br />
								况
							</div>
						</td>
						<td width="12%">
							<div align="center">
								姓名
							</div>
						</td>
						<td width="17%">
							<div align="center">
								称谓
							</div>
						</td>
						<td width="17%">
							<div align="center">
								月收入
							</div>
						</td>
						<td colspan="2">
							<div align="center">
								职业和工作(学习)单位
							</div>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;${rs.jtcy1_xm}
						</td>
						<td>
							&nbsp;${rs.jtcy1_gx}
						</td>
						<td>
							&nbsp;${rs.jtcy1_ysr}
						</td>
						<td colspan="2">
							&nbsp;${rs.jtcy1_gzdwmc}
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;${rs.jtcy2_xm}
						</td>
						<td>
							&nbsp;${rs.jtcy2_gx}
						</td>
						<td>
							&nbsp;${rs.jtcy2_ysr}
						</td>
						<td colspan="2">
							&nbsp;${rs.jtcy2_gzdwmc}
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;${rs.jtcy3_xm}
						</td>
						<td>
							&nbsp;${rs.jtcy3_gx}
						</td>
						<td>
							&nbsp;${rs.jtcy3_ysr}
						</td>
						<td colspan="2">
							&nbsp;${rs.jtcy3_gzdwmc}
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="center">
							助学金金额
						</div>
					</td>
					<td colspan="4">
						<bean:write name='rs' property="zxjje" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="4">
						<bean:write name='rs' property="sqly" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请时间
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sqsj" />
					</td>
					<td>
						<div align="center">
							学年
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xn" />
					</td>
				</tr>
				<logic:equal name="userType" value="xy">
					<tr>
						<td colspan="2">
							<div align="center">
								困难等级
							</div>
						</td>
						<td colspan="2">
							<bean:write name='rs' property="kndj" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核
							</div>
						</td>
						<td>
							<html:select name="rs" property="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核意见
							</div>
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="xyshyj" rows='3'
								style="width:100%" onblur="yzdx(this,'xyshyj')" />
							<input type="hidden" id="xyshyj" name="xyshyj" value="">
							<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name='rs' property="xxshyj" />" />
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
					<tr>
						<td colspan="2">
							<div align="center">
								困难等级
							</div>
						</td>
						<td colspan="2">
							<bean:write name='rs' property="kndj" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核
							</div>
						</td>
						<td>
							<bean:write name='rs' property="xysh" />
							<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name='rs' property="xysh" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								学校审核
							</div>
						</td>
						<td colspan="2">
							<html:select name="rs" property="xxsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="2">
							<div align="center">
								&nbsp;
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核意见
							</div>
						</td>
						<td colspan="4">
							<bean:write name='rs' property="xyshyj" />
							<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name='rs' property="xyshyj" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								学校审核意见
							</div>
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="xxshyj" rows='3'
								style="width:100%" onblur="yzdx(this,'xxshyj')" />
							<input type="hidden" id="xxshyj" name="xxshyj" value="">
						</td>
					</tr>
				</logic:equal>
			</table>
			<div class="buttontool" align="center">
				<logic:notEqual name="userType" value="admin">
					<logic:equal name="rs" property="xxsh" value="未审核">
						<button type="button" class="button2" onclick="yz()" style="width:80px"
							id="buttonSave">
							保 存
						</button>
					</logic:equal>
					<logic:notEqual name="rs" property="xxsh" value="未审核">
						<button type="button" class="button2" onclick="yz()" style="width:80px"
							id="buttonSave" disabled="disabled">
							保 存
						</button>
					</logic:notEqual>
				</logic:notEqual>
				<logic:equal name="userType" value="admin">
					<button type="button" class="button2" onclick="yz()" style="width:80px"
						id="buttonSave">
						保 存
					</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2"
					onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
					style="width:80px" id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
