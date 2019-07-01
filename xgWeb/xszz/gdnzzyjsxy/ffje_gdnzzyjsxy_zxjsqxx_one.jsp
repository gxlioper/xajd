<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.Iterator" />

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
			 refreshForm('/xgxt/ffje_gdnzzyjsxy_zxjsqxx_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function jehj(){
			var ffje = document.getElementById('ffje').value;
			var cjje = document.getElementById('cjje').value;
			if((ffje == null) || (ffje == "")){
				ffje = "0";
			}
			if((cjje == null) || (cjje == "")){
				cjje = "0";
			}
			var sfje = Math.round(ffje) - Math.round(cjje);
			document.getElementById('sfje').value=sfje;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 助学金发放 - 单个发放
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" width="16%">
						助学金名称
					</td>
					<td align="left" width="34%">
						<bean:write name="zzmc" />
					</td>
					<td width="16%" scope="col">
						<div align="center">
							资助总额
						</div>
					</td>
					<td width="34%" scope="col">
						<bean:write name="zzze" />
					</td>
				</tr>
				<tr>
					<td align="center" width="16%">
						资助个人金额
					</td>
					<td align="left" width="34%">
						<bean:write name="zzgrje" />
					</td>
					<td width="16%" scope="col">
						<div align="center">
							年度
						</div>
					</td>
					<td width="34%" scope="col">
						<bean:write name="nd" />
					</td>
				</tr>
				<tr>
					<td align="center">
						资助单位
					</td>
					<td align="left" colspan="3">
						<bean:write name="zzdw" />
					</td>
				</tr>
				<tr>
					<td align="center" width="16%">
						学号
					</td>
					<td align="left" width="34%">
						<bean:write name="xh" />
					</td>
					<td width="16%" scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%" scope="col">
						<bean:write name="xm" />
					</td>
				</tr>
				<tr>
					<td width="16%" scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td width="34%">
						<bean:write name="xb" />
					</td>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td>
						<bean:write name="nj" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name="sfzh" />
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<bean:write name="xymc" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<bean:write name="zymc" />
					</td>
					<td>
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<bean:write name="bjmc" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							生源地
						</div>
					</td>
					<td>
						<bean:write name="syd" />
					</td>
					<td>
						<div align="center">
							户口性质
						</div>
					</td>
					<td>
						<bean:write name="hkxz" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭居住地址
						</div>
					</td>
					<td colspan="3">
						<bean:write name="jtjzdz" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							邮政编码
						</div>
					</td>
					<td>
						<bean:write name="yzbm" />
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<bean:write name="lxdh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭人口数
						</div>
					</td>
					<td>
						<bean:write name="jtrks" />
					</td>
					<td>
						<div align="center">
							家庭人均月收入
						</div>
					</td>
					<td>
						<bean:write name="jtrjysr" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭年总收入
						</div>
					</td>
					<td>
						<bean:write name="jtnzsr" />
					</td>
					<td>
						<div align="center">
							学生本人月实际消费额度
						</div>
					</td>
					<td>
						<bean:write name="xsbrysjxfed" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭收入来源
						</div>
					</td>
					<td colspan="3">
						<bean:write name="srly" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭情况
						</div>
					</td>
					<td colspan="3">
						<bean:write name="jtqk" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭经济情况说明
						</div>
					</td>
					<td colspan="3">
						<bean:write name="jtjjqksm" />
					</td>
				</tr>
				<tr>
					<td align="center" width="16%">
						上学期成绩排名
					</td>
					<td align="left" width="34%">
						<bean:write name="sxqcjpm" />
					</td>
					<td width="16%" scope="col">
						<div align="center">
							综合测评排名
						</div>
					</td>
					<td width="34%" scope="col">
						<bean:write name="zhcppm" />
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
						<bean:write name="sfycjqgzx" />
					</td>
					<td>
						<div align="center">
							欠学费金额
						</div>
					</td>
					<td>
						<bean:write name="qxfje" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							困难程度排名
						</div>
					</td>
					<td>
						<bean:write name="kncdpm" />
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
					<td>
						<div align="center">
							申请时间
						</div>
					</td>
					<td align="left">
						<bean:write name="sqsj" />
					</td>
					<td>
						<div align="center">
							发放金额
						</div>
					</td>
					<td>
						<input type="text" id="ffje" name="ffje" maxlength="10"
							style="width:100%;heigh:100%" onblur="jehj();"
							value="<bean:write name="ffje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							冲减金额
						</div>
					</td>
					<td align="left">
						<input type="text" id="cjje" name="cjje" maxlength="10"
							style="width:100%;heigh:100%" onblur="jehj();"
							value="<bean:write name="cjje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							实发金额
						</div>
					</td>
					<td>
						<input type="text" id="sfje" name="sfje" maxlength="10"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name="sfje"/>">
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
