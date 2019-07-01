<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList"/>
<jsp:directive.page import="java.util.Iterator"/>

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
		<title><bean:message key="lable.title" />
		</title>
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
			if((("一般困难" == xxsh) || ("特别困难" == xxsh)) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("学校审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
			 refreshForm('/xgxt/auditing_gdnz_kns_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 贫困生审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" id="xxsh" name="xxsh" value="<bean:write name="rs" property="xxsh" />" />
			<input type="hidden" id="isXX" name="isXX" value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>
				<td align="center" width="16%">
					学号
				</td>
				<td align="left" width="34%">
					<bean:write name="rs" property="xh" />
				</td>
				<td width="16%" scope="col">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="34%" scope="col">
					<bean:write name="rs" property="xm" />
				</td>
			</tr>
			<tr>
				<td width="16%" scope="row">
					<div align="center">
						性别
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="xb" />
				</td>
				<td>
					<div align="center">
						年级
					</div>
				</td>
				<td>
					<bean:write name="rs" property="nj" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						身份证号
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfzh" />
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xymc" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						专业
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zymc" />
				</td>
				<td>
					<div align="center">
						班级
					</div>
				</td>
				<td>
					<bean:write name="rs" property="bjmc" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						生源地
					</div>
				</td>
				<td>
					<bean:write name="rs" property="syd" />
				</td>
				<td>
					<div align="center">
						户口性质
					</div>
				</td>
				<td>
					<bean:write name="rs" property="hkxz" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭居住地址
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="jtjzdz" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						邮政编码
					</div>
				</td>
				<td>
					<bean:write name="rs" property="yzbm" />
				</td>
				<td>
					<div align="center">
						联系电话
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxdh" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭人口数
					</div>
				</td>
				<td>
					<bean:write name="rs" property="jtrks" />
				</td>
				<td>
					<div align="center">
						家庭人均月收入
					</div>
				</td>
				<td>
					<bean:write name="rs" property="jtrjysr" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭年总收入
					</div>
				</td>
				<td>
					<bean:write name="rs" property="jtnzsr" />
				</td>
				<td>
					<div align="center">
						学生本人月实际消费额度
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xsbrysjxfed" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭收入来源
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="srly" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭情况
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="jtqk" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭经济情况说明
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="jtjjqksm" />
				</td>
			</tr>
			<tr>
				<td scope="row" colspan="4">
					在校期间历年获得奖、助学金及困难补助和贷款记录：
					<div align="left">
						<%
									ArrayList list = (ArrayList) request
									.getAttribute("zzjl");
									for (Iterator it = list.iterator(); it.hasNext();) {
								String temp = (String)it.next();
						%>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<%=temp%>
						<br />
						<%
						}
						%>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						是否已参加勤工助学
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfycjqgzx" />
				</td>
				<td>
					<div align="center">
						欠学费金额
					</div>
				</td>
				<td>
					<bean:write name="rs" property="qxfje" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						申请时间
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sqsj" />
				</td>
				<td>
					<div align="center">
						备注
					</div>
				</td>
				<td>
					<bean:write name="bz" />
				</td>
			</tr>
				<tr>
					<logic:equal name="isXX" value="is">
					<td>
						<div align="center">
							困难程度排名
						</div>
					</td>
					<td align="left">
						<bean:write name="rs" property="kncdpm" />
					</td>
					</logic:equal>
					<logic:equal name="isXX" value="no">
					<td>
						<div align="center">
							困难程度排名
						</div>
					</td>
					<td align="left">
						<input type="text" id="kncdpm" maxlength="5" name="kncdpm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="kncdpm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					</logic:equal>
					<td>
						<div align="center">
							审核结果
						</div>
					</td>
					<td>
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr>
				<td scope="row">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />审核意见
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="xyshyj" rows='3' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
					<input type="hidden" id="xyshyj" name="xyshyj" value="">
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						学校审核意见
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="xxshyj" rows='3' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
					<input type="hidden" id="xxshyj" name="xxshyj" value="">
				</td>
			</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
