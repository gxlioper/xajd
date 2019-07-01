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
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var hth = document.getElementById('hth').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((hth == null) || (hth == "")){
				alert("合同号不能为空!");
				return false;
			}
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=fkxx_save";
			document.forms[0].submit();
		}
		function je(){
			var fkje1 = document.getElementById('fkje1').value;
			var fkje2 = document.getElementById('fkje2').value;
			var fkje3 = document.getElementById('fkje3').value;
			var fkje4 = document.getElementById('fkje4').value;
			var fkze = "0";
			
			if((fkje1 == null) || (fkje1 == "")){
				fkje1 = "0";
				document.getElementById('fkje1').value = "";
			}
			if((fkje2 == null) || (fkje2 == "")){
				fkje2 = "0";
				document.getElementById('fkje2').value = "";
			}
			if((fkje3 == null) || (fkje3 == "")){
				fkje3 = "0";
				document.getElementById('fkje3').value = "";
			}
			if((fkje4 == null) || (fkje4 == "")){
				fkje4 = "0";
				document.getElementById('fkje4').value = "";
			}
			fkze = Math.round(fkje1) + Math.round(fkje2) + Math.round(fkje3) + Math.round(fkje4);
			document.getElementById('fkze').value = fkze;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 信息维护 - 国家助学贷款放款详细信息
		</div>
	</div>
		<html:form action="/zjjdzyjsxy_xszz.do?method=fkxx_queryOne" method="post">
			<input type="hidden" id="url" name="url" value="/zjjdzyjsxy_xszz.do?method=fkxx_queryOne" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />

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
			<logic:equal name="userType" value="stu">
			<table class="tbstyle" width="100%">
				<tr>
					<td align="center" width="16%">
						学号
					</td>
					<td align="left" colspan="2">
						<bean:write name='rs' property="xh" />
					</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<td>
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
					<td colspan="3">
						<bean:write name="rs" property="sfzh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="nj"/>
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							合同号
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="hth"/>
					</td>
					<td>
						<div align="center">
							合同金额
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="htje"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							贷款银行
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="dkyh"/>
					</td>
					<td>
						<div align="center">
							贷款利率
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="dkll"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							合同期限
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="htqx"/>
					</td>
					<td>
						<div align="center">
							放款总额
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="fkze"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							放款1
						</div>
					</td>
					<td width="14%">
						<div align="center">
							日期
						</div>
					</td>
					<td width="20%">
						<bean:write name='rs' property="fkrq1" />
					</td>
					<td>
						<div align="center">
							金额
						</div>
					</td>
					<td width="12%">
						<bean:write name="rs" property="fkje1"/>
					</td>
					<td width="12%">
						<div align="center">
							年份
						</div>
					</td>
					<td width="10%">
						<bean:write name='rs' property="fknf1" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							放款2
						</div>
					</td>
					<td>
						<div align="center">
							日期
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fkrq2" />
					</td>
					<td>
						<div align="center">
							金额
						</div>
					</td>
					<td>
						<bean:write name="rs" property="fkje2"/>
					</td>
					<td>
						<div align="center">
							年份
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fknf2" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							放款3
						</div>
					</td>
					<td>
						<div align="center">
							日期
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fkrq3" />
					</td>
					<td>
						<div align="center">
							金额
						</div>
					</td>
					<td>
						<bean:write name="rs" property="fkje3"/>
					</td>
					<td>
						<div align="center">
							年份
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fknf3" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							放款4
						</div>
					</td>
					<td>
						<div align="center">
							日期
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fkrq4" />
					</td>
					<td>
						<div align="center">
							金额
						</div>
					</td>
					<td>
						<bean:write name="rs" property="fkje4"/>
					</td>
					<td>
						<div align="center">
							年份
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fknf4" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							备注
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="bz"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
			</logic:equal>
			<logic:notEqual name="userType" value="stu">
			<table class="tbstyle" width="100%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" width="16%">
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" width="16%">
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="2">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="3">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<td>
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>合同号
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="hth" name="hth" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hth"/>">
					</td>
					<td>
						<div align="center">
							合同金额
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="htje" name="htje" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="htje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							贷款银行
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="dkyh" name="dkyh" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkyh"/>">
					</td>
					<td>
						<div align="center">
							贷款利率
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="dkll" name="dkll" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkll"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							合同期限
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="htqx" name="htqx" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="htqx"/>">
					</td>
					<td>
						<div align="center">
							放款总额
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="fkze" name="fkze" maxlength="10"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name="rs" property="fkze"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							放款1
						</div>
					</td>
					<td width="14%">
						<div align="center">
							日期
						</div>
					</td>
					<td width="20%">
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('fkrq1','y-mm-dd');"
								value="<bean:write name='rs' property="fkrq1" />"
								name="fkrq1" id="fkrq1" />
					</td>
					<td>
						<div align="center">
							金额
						</div>
					</td>
					<td width="12%">
						<input type="text" id="fkje1" name="fkje1" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="fkje1"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td width="12%">
						<div align="center">
							年份
						</div>
					</td>
					<td width="10%">
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('fknf1','y');"
								value="<bean:write name='rs' property="fknf1" />"
								name="fknf1" id="fknf1" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							放款2
						</div>
					</td>
					<td>
						<div align="center">
							日期
						</div>
					</td>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('fkrq2','y-mm-dd');"
								value="<bean:write name='rs' property="fkrq2" />"
								name="fkrq2" id="fkrq2" />
					</td>
					<td>
						<div align="center">
							金额
						</div>
					</td>
					<td>
						<input type="text" id="fkje2" name="fkje2" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="fkje2"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							年份
						</div>
					</td>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('fknf2','y');"
								value="<bean:write name='rs' property="fknf2" />"
								name="fknf2" id="fknf2" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							放款3
						</div>
					</td>
					<td>
						<div align="center">
							日期
						</div>
					</td>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('fkrq3','y-mm-dd');"
								value="<bean:write name='rs' property="fkrq3" />"
								name="fkrq3" id="fkrq3" />
					</td>
					<td>
						<div align="center">
							金额
						</div>
					</td>
					<td>
						<input type="text" id="fkje3" name="fkje3" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="fkje3"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							年份
						</div>
					</td>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('fknf3','y');"
								value="<bean:write name='rs' property="fknf3" />"
								name="fknf3" id="fknf3" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							放款4
						</div>
					</td>
					<td>
						<div align="center">
							日期
						</div>
					</td>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('fkrq4','y-mm-dd');"
								value="<bean:write name='rs' property="fkrq4" />"
								name="fkrq4" id="fkrq4" />
					</td>
					<td>
						<div align="center">
							金额
						</div>
					</td>
					<td>
						<input type="text" id="fkje4" name="fkje4" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="fkje4"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							年份
						</div>
					</td>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('fknf4','y');"
								value="<bean:write name='rs' property="fknf4" />"
								name="fknf4" id="fknf4" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							备注
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="bz" name="bz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bz"/>">
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
			</logic:notEqual>
		</html:form>
</body>
</html:html>
