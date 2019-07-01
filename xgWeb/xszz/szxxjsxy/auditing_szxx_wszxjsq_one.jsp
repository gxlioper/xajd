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
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("通过" == xxsh) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("学校审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
			
			refreshForm('/xgxt/auditing_szxx_wszxjsq_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 外设助学金审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />" />
			<input type="hidden" id="isXX" name="isXX"
				value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">
						</td>
					</tr>
				</thead>
				<tr>

					<td align="center" colspan="2">
						学号
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="xh" />
					</td>

					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%" colspan="2">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							出生年月
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="csny" />
					</td>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							籍贯
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jg" />
					</td>
					<td>
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="mzmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							学制
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xz" />
					</td>
					<td colspan="1">
						<div align="center">
							外设助学金类别
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="bzmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							助学金金额
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="wszxjje" />
					</td>
					<td colspan="1">
						<div align="center">
							证明材料
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zmclmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							寝室号
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="qsh" />
					</td>
					<td align="center">
						寝室电话
					</td>
					<td>
						<bean:write name="rs" property="qsdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							奖惩情况
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jcqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zzmm" />
					</td>
					<td>
						<div align="center">
							邮政编码
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭住址
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtdz" />
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							家
							<br>
							庭
							<br>
							成
							<br>
							员
							<br>
							情
							<br>
							况
						</div>
					</td>
					<td width="12%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="9%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="13%">
						<div align="center">
							与本人关系
						</div>
					</td>
					<td width="12%">
						<div align="center">
							月收入
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							工作或学习单位
						</div>
					</td>
				</tr>
				<tr>
					<td width="12%">
						<bean:write name="rs" property="jtcy1_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_nl" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_ysr" />
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtcy1_gzdw" />
					</td>
				</tr>
				<tr>
					<td width="12%">
						<bean:write name="rs" property="jtcy2_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_nl" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_ysr" />
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtcy2_gzdw" />
					</td>
				</tr>
				<tr>
					<td width="12%">
						<bean:write name="rs" property="jtcy3_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_nl" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_ysr" />
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtcy3_gzdw" />
					</td>
				</tr>
				<tr>
					<td width="12%">
						<bean:write name="rs" property="jtcy4_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_nl" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_ysr" />
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtcy4_gzdw" />
					</td>
				</tr>
				<tr>
					<td width="12%">
						<bean:write name="rs" property="jtcy5_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_nl" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_ysr" />
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtcy5_gzdw" />
					</td>
				</tr>
				<tr>
					<td height="108">
						担任社会工作情况
					</td>
					<td colspan="7">
						<bean:write name="rs" property="drshgzqk" />
					</td>
				</tr>
				<tr>
					<td>
						<br>
						申请
						<br>
						理由
					</td>
					<td colspan="7">
						（包括家庭经济情况，学费来源，个人品行、 学习情况 ）
						<bean:write name="rs" property="sqzzly" />
					</td>
				</tr>
				<tr>
					<td height="108">
						<br>
						本
						<br>
						人
						<br>
						承
						<br>
						诺
					</td>
					<td colspan="7">
						<bean:write name="rs" property="brcn" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							卡号
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="kh" />
					</td>
					<td>
						<div align="center">
							审核结果
						</div>
					</td>
					<td colspan="2">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />
							<br>
							审核
							<br>
							意见
						</div>
					</td>
					<td colspan="7">
						<html:textarea name="rs" property="xyshyj" rows='5'
							style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							学校
							<br>
							审核
							<br>
							意见
						</div>
					</td>
					<td colspan="7">
						<html:textarea name="rs" property="xxshyj" rows='5'
							style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="yz();" style="width:80px"
					id="buttonSave">
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
</html>
