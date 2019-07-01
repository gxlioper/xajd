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
		<script language="javascript">
		function validate(){
			var userType= document.all['userType'].value;
			if("xy"==userType)
			{
				var xyshyj = document.getElementById('xyshyj').value;
				if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于100个字符");
	          		 return false;
	       		 }
				}
			}
			if("xx"==userType)
			{
				var xxshyj = document.getElementById('xxshyj').value;
				if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("学校审核意见不能大于100个字符");
	          		 return false;
	       		 }
				}
			}
			 return true;
		}
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		</script>
		<html:form action="/zbdx_xszz.do?method=dkqrsShSave" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 贷款确认书审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" name="nd"
				value="<bean:write name="map"  property="nd"/>" />
			<input type="hidden" name="xh"
				value="<bean:write name="map"  property="xh"/>" />
			<input type="hidden" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<table width="100%" id="rsTable" class="tbstyle">
				<tr>
					<td height="31">
						<div align="left">
							学号：
						</div>
					</td>
					<td colspan="2">
						<div align="left">
							&nbsp;
							<bean:write name='map' property="xh" />
						</div>
					</td>

					<td height="31" colspan="2">
						姓名
					</td>
					<td colspan="2">
						&nbsp;
						<bean:write name="map" property="xm" />
					</td>
				</tr>
				<tr>
					<td height="31">
						性别
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="xb" />
					</td>
					<td height="31" colspan="2">
						年级
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="nj" />
					</td>
				</tr>
				<tr>
					<td height="31">
						<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name='map' property="xy" />
					</td>
					<td height="31" colspan="2">
						年度
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="nd" />
					</td>
				</tr>
				<tr>
					<td height="31">
						专业
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="zymc" />
					</td>
					<td height="31" colspan="2">
						学年
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="xn" />
					</td>
				</tr>
				<tr>
					<td height="31">
						班级
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="bj" />
					</td>
					<td height="31" colspan="2">
						学期
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="xq" />
					</td>
				</tr>
				<tr>
					<td height="31">
						本地工行帐号或者卡号
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="ghzh" />
					</td>
					<td height="31" colspan="2">
						贷款期限
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="dkqx" />
					</td>
				</tr>
				<tr>
					<td height="31">
						申请贷款总额
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="dkze" />
						元
					</td>
					<td height="31" colspan="2">
						亲友与本人关系
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="qygx" />
					</td>
				</tr>
				<tr>
					<td height="31">
						申请贷款学费
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="sqdkxf" />
						元
					</td>
					<td height="31" colspan="2">
						亲友联系电话
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="qylxdh" />
					</td>
				</tr>
				<tr>
					<td height="31">
						申请贷款生活费
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="qsdkshf" />
						元
					</td>
					<td height="31" colspan="2">
						亲友身份证号码
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="qysfzh" />
					</td>
				</tr>
				<tr>
					<td height="31">
						申请贷款住宿费
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="sqdkzsf" />
						元
					</td>
					<td height="31" colspan="2">
						亲友工作单位
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="qygzdw" />
					</td>
				</tr>
				<tr>
					<td height="31">
						有效联系方式
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="yxlxfs" />
					</td>
					<td height="31" colspan="2">
						亲友住址
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="qyzz" />
					</td>
				</tr>
				<tr>
					<td height="48">
						父亲姓名
					</td>
					<td height="48" colspan="2">
						&nbsp;
						<bean:write name="map" property="fqxm" />
					</td>
					<td height="48" colspan="2">
						母亲姓名
					</td>
					<td height="48" colspan="2">
						&nbsp;
						<bean:write name="map" property="mqxm" />
					</td>
				</tr>
				<tr>
					<td height="48">
						父亲身份证号码
					</td>
					<td height="48" colspan="2">
						&nbsp;
						<bean:write name="map" property="fqsfzh" />
					</td>
					<td height="48" colspan="2">
						母亲身份证号码
					</td>
					<td height="48" colspan="2">
						&nbsp;
						<bean:write name="map" property="mqsfzh" />
					</td>
				</tr>
				<tr>
					<td height="48">
						父亲工作单位
					</td>
					<td height="48" colspan="2">
						&nbsp;
						<bean:write name="map" property="fqgzdw" />
					</td>
					<td height="48" colspan="2">
						母亲工作单位
					</td>
					<td height="48" colspan="2">
						&nbsp;
						<bean:write name="map" property="mqgzdw" />
					</td>
				</tr>
				<tr>
					<td height="48">
						父亲联系方式
					</td>
					<td height="48" colspan="2">
						&nbsp;
						<bean:write name="map" property="fqlxfs" />
					</td>
					<td height="48" colspan="2">
						母亲联系方式
					</td>
					<td height="48" colspan="2">
						&nbsp;
						<bean:write name="map" property="mqlxfs" />
					</td>
				</tr>
				<tr>
					<td height="48">
						首次毕业去向
					</td>
					<td height="48" colspan="2">
						&nbsp;
						<bean:write name="map" property="scbyqx" />
					</td>
					<td height="48" colspan="2">
						发放时间
					</td>
					<td height="48" colspan="2">
						&nbsp;
						<bean:write name="map" property="ffsj" />
					</td>
				</tr>
				<tr>
					<td height="48">
						合同号1
					</td>
					<td height="48" colspan="2">
						&nbsp;
						<bean:write name="map" property="hth1" />
					</td>
					<td height="48" colspan="2">
						合同号2
					</td>
					<td height="48" colspan="2">
						&nbsp;
						<bean:write name="map" property="hth2" />
					</td>
				</tr>
				<tr>
					<td height="31">
						合同经办金融机构1
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="htjbjg1" />
					</td>
					<td height="31" colspan="2">
						合同经办金融机构2
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="htjbjg2" />
					</td>
				</tr>
				<tr>
					<td height="31">
						分支机构名称1
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="fzjgmc1" />
					</td>
					<td height="31" colspan="2">
						分支机构名称2
					</td>
					<td height="31" colspan="2">
						&nbsp;
						<bean:write name="map" property="fzjgmc2" />
					</td>
				</tr>
				<tr>
					<td width="11%" height="31">
						贷款金额1
					</td>
					<td width="8%" colspan="2">
						&nbsp;
						<bean:write name="map" property="dkje1" />
						元
					</td>
					<td width="15%" height="31" colspan="2">
						贷款金额2
					</td>
					<td colspan="2">
						&nbsp;
						<bean:write name="map" property="dkje2" />
						元
					</td>
				</tr>
				<tr>
					<td width="11%" height="31">
						还款年份1
					</td>
					<td width="8%" colspan="2">
						&nbsp;
						<bean:write name="map" property="hknf1" />
					</td>
					<td width="15%" height="31" colspan="2">
						还款年份2
					</td>
					<td colspan="2">
						&nbsp;
						<bean:write name="map" property="hknf2" />
					</td>
				</tr>
				<tr>
					<td width="11%" height="31">
						合同号3
					</td>
					<td width="8%" colspan="2">
						&nbsp;
						<bean:write name="map" property="hth3" />
					</td>
					<td width="15%" height="31" colspan="2">
						当前工作单位
					</td>
					<td colspan="2">
						&nbsp;
						<bean:write name="map" property="dqgzdw" />
					</td>
				</tr>
				<tr>
					<td width="11%" height="31">
						合同经办金融机构3
					</td>
					<td width="8%" colspan="2">
						&nbsp;
						<bean:write name="map" property="htjbjg3" />
					</td>
					<td width="15%" height="31" colspan="2">
						当前单位地址
					</td>
					<td colspan="2">
						&nbsp;
						<bean:write name="map" property="dqdwdz" />
					</td>
				</tr>
				<tr>
					<td width="11%" height="31">
						分支机构名称3
					</td>
					<td width="8%" colspan="2">
						&nbsp;
						<bean:write name="map" property="fzjgmc3" />
					</td>
					<td width="15%" height="31" colspan="2">
						当前单位邮编
					</td>
					<td colspan="2">
						&nbsp;
						<bean:write name="map" property="dqdwyb" />
					</td>
				</tr>
				<tr>
					<td width="11%" height="31">
						贷款金额3
					</td>
					<td width="8%" colspan="2">
						&nbsp;
						<bean:write name="map" property="dkjg3" />
						元
					</td>
					<td width="15%" height="31" colspan="2">
						当前单位联系方式
					</td>
					<td colspan="2">
						&nbsp;
						<bean:write name="map" property="dqdwlxfs" />
					</td>
				</tr>
				<tr>
					<td width="11%" height="31">
						还款年份3
					</td>
					<td width="8%" colspan="2">
						&nbsp;
						<bean:write name="map" property="hknf" />
					</td>
					<td width="15%" height="31" colspan="2">
						邮件发送标记
					</td>
					<td colspan="2">
						&nbsp;
						<bean:write name="map" property="yjfsbj" />
					</td>
				</tr>
				<tr>
					<td width="11%" height="31">
						<bean:message key="lable.xsgzyxpzxy" />审核
					</td>
					<td width="8%" colspan="2">
						&nbsp;
						<bean:write name="map" property="xysh" />
					</td>
					<td width="15%" height="31" colspan="2">
						学校审核
					</td>
					<td colspan="2">
						&nbsp;
						<bean:write name="map" property="xxsh" />
					</td>
				</tr>
				<tr>
					<td width="11%" height="31">
						发放贷款总额
					</td>
					<td width="8%" colspan="2">
						&nbsp;
						<bean:write name="map" property="fsdkze" />
						元
					</td>
					<td width="15%" height="31" colspan="2">
						发放贷款学费
					</td>
					<td colspan="2">
						&nbsp;
						<bean:write name="map" property="fsdkzsf" />
						元
					</td>
				</tr>
				<tr>
					<td width="11%" height="31">
						发放贷款生活费
					</td>
					<td width="8%" colspan="2">
						&nbsp;
						<bean:write name="map" property="fsdkshf" />
						元
					</td>
					<td width="15%" height="31" colspan="2">
						发放贷款住宿费
					</td>
					<td colspan="2">
						&nbsp;
						<bean:write name="map" property="yffzxdkje" />
						元
					</td>
				</tr>
				<tr>
					<td width="11%" height="31">
						申请理由
					</td>
					<td width="8%" colspan="6">
						&nbsp;
						<bean:write name="map" property="sqly" />
					</td>
				</tr>
				<tr>
					<td width="11%" height="31">
						备注
					</td>
					<td width="8%" colspan="6">
						&nbsp;
						<bean:write name="map" property="beizhu" />
					</td>
				</tr>
				<tr>
					<td height="31">
						审核结果
					</td>
					<td height="31" colspan="6">
						&nbsp;
						<logic:equal name="userType" value="xx">
							<select id="xxsh" name="xxsh"
								value="<bean:write name="map" property="xxsh"/>">
								<option value="未审核">
									未审核
								</option>
								<option value="未通过">
									未通过
								</option>
								<option value="通过">
									通过
								</option>
							</select>
						</logic:equal>
						<logic:notEqual name="userType" value="xx">
							<select name="xysh"
								value="<bean:write name="map" property="xysh"/>">
								<option value="未审核">
									未审核
								</option>
								<option value="未通过">
									未通过
								</option>
								<option value="通过">
									通过
								</option>
							</select>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>

					<td colspan="9">
						<html:textarea rows="5" style="width:100%" name="map"
							property="xyshyj" styleId="xyshyj" />
					</td>
				</tr>
				<logic:equal name="userType" value="xx">
					<tr>
						<td>
							<div align="left">
								学校审核意见
							</div>
						</td>
						<td colspan="9">
							<html:textarea rows="5" style="width:100%" name="map"
								property="xxshyj" styleId="xxshyj" />
						</td>
					</tr>
				</logic:equal>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="if (validate()) {refreshForm('zbdx_xszz.do?method=dkqrsShSave');Close();window.dialogArguments.document.getElementById('search_go').click();}"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
<script type="text/javascript">
<logic:equal name="userType" value="xx">
	$('xxsh').value="<bean:write name="map" property="xxsh"/>";
</logic:equal>
<logic:notEqual name="userType" value="xx">
	$('xysh').value="<bean:write name="map" property="xysh"/>";
</logic:notEqual>
</script>
</html>
