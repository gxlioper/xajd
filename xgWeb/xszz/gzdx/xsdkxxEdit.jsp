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


	<title><bean:message key="lable.title" />
	</title>
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
			var xn = document.getElementById('xn').value;
			var htbh = document.getElementById('htbh').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((xn == null) || (xn == "")){
				alert("学年不能为空!");
				return false;
			}
			if((htbh == null) || (htbh == "")){
				alert("合同编号不能为空!");
				return false;
			}
			document.forms[0].action = "/xgxt/gzdx_xszz.do?method=xsdkxxEditSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
	</script>
</head>

<body>
	<html:form action="gzdx_xszz.do?method=xsdkxxEdit" method="post">
		<input type="hidden" id="url" name="url"
			value="/gzdx_xszz.do?method=xsdkxxEdit" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc" />
		<input type="hidden" id="pkVal" name="pkVal"
			value="<bean:write name="rs" property="pkVal" />">
		<input type="hidden" id="type" name="type"
			value="<bean:write name="type" />">

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
		<logic:present name="isPASS">
			<logic:match value="is" name="isPASS">
				<script language="javascript">
	         			alert("已存在信息，不能再添加！");
	         		</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" width="100%">
			<tr>
				<td align="center" width="16%">
					<font color="red">*</font>学号
				</td>
				<td align="left" width="34%">
					<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					<logic:notEqual name="type" value="modi">
						<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</logic:notEqual>
				</td>
				<td width="16%">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="34%">
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
				<td>
					<input type="text" id="xb" readonly="readonly" name="xb"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xb"/>">
				</td>
				<td>
					<div align="center">
						身份证号
					</div>
				</td>
				<td>
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
				<td>
					<input type="text" id="nj" readonly="readonly" name="nj"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="nj"/>">
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />名称
					</div>
				</td>
				<td>
					<input type="text" id="xymc" name="xymc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xymc"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						专业名称
					</div>
				</td>
				<td>
					<input type="text" id="zymc" readonly="readonly" name="zymc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zymc"/>">
				</td>
				<td>
					<div align="center">
						班级名称
					</div>
				</td>
				<td>
					<input type="text" id="bjmc" name="bjmc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="bjmc"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学制
					</div>
				</td>
				<td>
					<input type="text" id="xz" name="xz" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xz"/>">
				</td>
				<td>
					<div align="center">
						<font color="red">*</font>学年
					</div>
				</td>
				<td>
					<input type="text" id="xn" maxlength="9" name="xn"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xn"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						贷款年级
					</div>
				</td>
				<td>
					<input type="text" id="dknj" maxlength="10" name="dknj"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dknj"/>">
				</td>
				<td>
					<div align="center">
						贷款次数
					</div>
				</td>
				<td>
					<input type="text" id="dkcs" name="dkcs" maxlength="2"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dkcs"/>"
						onkeyup="value=value.replace(/[^\d]/g,'')" 
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						家庭住址
					</div>
				</td>
				<td>
					<input type="text" id="jtzz" maxlength="100" name="jtzz"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtzz"/>">
				</td>
				<td>
					<div align="center">
						邮政编码
					</div>
				</td>
				<td>
					<input type="text" id="yzbm" name="yzbm" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="yzbm"/>"
						onkeyup="value=value.replace(/[^\d]/g,'')" 
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						联系电话1
					</div>
				</td>
				<td>
					<input type="text" id="lxdh1" maxlength="20" name="lxdh1"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxdh1"/>"
						onkeyup="value=value.replace(/[^\d]/g,'')" 
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<div align="center">
						联系电话2
					</div>
				</td>
				<td>
					<input type="text" id="lxdh2" name="lxdh2" maxlength="20"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxdh2"/>"
						onkeyup="value=value.replace(/[^\d]/g,'')" 
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学费贷款额
					</div>
				</td>
				<td>
					<input type="text" id="xfdk" maxlength="6" name="xfdk"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xfdk"/>"
						onkeyup="value=value.replace(/[^\d]/g,'')" 
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<div align="center">
						住宿费贷款额
					</div>
				</td>
				<td>
					<input type="text" id="zsfdk" name="zsfdk" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zsfdk"/>"
						onkeyup="value=value.replace(/[^\d]/g,'')" 
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						贷款总额
					</div>
				</td>
				<td>
					<input type="text" id="dkze" maxlength="6" name="dkze"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dkze"/>"
						onkeyup="value=value.replace(/[^\d]/g,'')" 
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<div align="center">
						贷款银行
					</div>
				</td>
				<td>
					<input type="text" id="dkyh" name="dkyh" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dkyh"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<font color="red">*</font>合同编号
					</div>
				</td>
				<td>
					<input type="text" id="htbh" maxlength="25" name="htbh"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="htbh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'')" 
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<div align="center">
						银行卡号
					</div>
				</td>
				<td>
					<input type="text" id="yhkh" name="yhkh" maxlength="20"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="yhkh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'')" 
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						贷款年限
					</div>
				</td>
				<td>
					<input type="text" id="dknx" maxlength="10" name="dknx"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dknx"/>">
				</td>
				<td>
					<div align="center">
						期限(月)
					</div>
				</td>
				<td>
					<input type="text" id="dkqx" name="dkqx" maxlength="10"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dkqx"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						贷款利率
					</div>
				</td>
				<td>
					<input type="text" id="dkll" maxlength="10" name="dkll"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dkll"/>">
				</td>
				<td>
					<div align="center">
						贷款发放日期
					</div>
				</td>
				<td>
					<input type="text" style="cursor:hand;width:100%" maxlength="10"
							onclick="return showCalendar('dkffr','y-mm-dd');"
							value="<bean:write name='rs' property="dkffr" />" name="dkffr"
							id="dkffr" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						贷款到期日期
					</div>
				</td>
				<td>
					<input type="text" style="cursor:hand;width:100%" maxlength="10"
							onclick="return showCalendar('dkdqr','y-mm-dd');"
							value="<bean:write name='rs' property="dkdqr" />" name="dkdqr"
							id="dkffr" />
				</td>
				<td>
					<div align="center">
						贴息终止日期
					</div>
				</td>
				<td>
					<input type="text" style="cursor:hand;width:100%" maxlength="10"
							onclick="return showCalendar('txzzr','y-mm-dd');"
							value="<bean:write name='rs' property="txzzr" />" name="txzzr"
							id="txzzr" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学籍变动情况
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="xjbdqk" maxlength="100" name="xjbdqk"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xjbdqk"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						备注1
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="bz1" maxlength="100" name="bz1"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="bz1"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						备注2
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="bz2" maxlength="100" name="bz2"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="bz2"/>">
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<button class="button2" id="buttonSave" onClick="yz();">
				保&nbsp;&nbsp;&nbsp;&nbsp;存
			</button>
			<button class="button2" onClick="Close();window.dialogArguments.document.getElementById('search_go').click();" id="buttonPrint">
				关&nbsp;&nbsp;&nbsp;&nbsp;闭
			</button>
		</div>
	</html:form>
</body>
</html:html>
