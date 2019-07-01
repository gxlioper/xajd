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
			var htbh = document.getElementById('htbh').value;
			
			if((htbh == null) || (htbh == "")){
				alert("合同编号不能为空!");
				return false;
			}
			document.forms[0].action = "/xgxt/gzdx_xszz.do?method=xshkxxEditSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function autoFillStuInfoT(cod) {
			if (cod == 13) {
				var htbh = document.getElementById('htbh').value;
			
				if((htbh == null) || (htbh == "")){
					alert("合同编号不能为空!");
					return false;
				}
				document.forms[0].action = "/xgxt/gzdx_xszz.do?method=xshkxxEdit";
				document.forms[0].submit();
			}
		}
	</script>
</head>

<body>
	<html:form action="gzdx_xszz.do?method=xshkxxEdit" method="post">
		<input type="hidden" id="url" name="url"
			value="/gzdx_xszz.do?method=xshkxxEdit" />
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
		<logic:present name="isNull">
			<logic:match value="is" name="isNull">
				<script language="javascript">
	         			alert("不存在该合同信息！");
	         		</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" width="100%">
			<tr>
				<td align="center" width="16%">
					<font color="red">*</font>合同号
				</td>
				<td align="left" width="34%">
					<logic:notEqual name="type" value="modi">
						<html:text name='rs' property="htbh" styleId="htbh" 
							onkeypress="autoFillStuInfoT(event.keyCode)" />
					</logic:notEqual>
					<logic:equal name="type" value="modi">
						<input type="text" readonly="readonly" id="htbh" name="htbh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="htbh"/>" readonly="readonly">
					</logic:equal>
				</td>
				<td width="16%">
					<div align="center">
						贷款次数
					</div>
				</td>
				<td width="34%">
					<input type="text" readonly="readonly" id="dkcs" name="dkcs"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dkcs"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						贷款学年
					</div>
				</td>
				<td>
					<input type="text" id="xn" readonly="readonly" name="xn"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xn"/>">
				</td>
				<td>
					<div align="center">
						贷款银行
					</div>
				</td>
				<td>
					<input type="text" id="dkyh" name="dkyh" readonly="true"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dkyh"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学号
					</div>
				</td>
				<td>
					<input type="text" id="xh" readonly="readonly" name="xh"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xh"/>">
				</td>
				<td>
					<div align="center">
						姓名
					</div>
				</td>
				<td>
					<input type="text" id="xm" name="xm" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xm"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
					<input type="text" id="xymc" readonly="readonly" name="xymc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xymc"/>">
				</td>
				<td>
					<div align="center">
						专业
					</div>
				</td>
				<td>
					<input type="text" id="zymc" name="zymc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zymc"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						班级
					</div>
				</td>
				<td>
					<input type="text" id="bjmc" readonly="readonly" name="bjmc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="bjmc"/>">
				</td>
				<td>
					<div align="center">
						年级
					</div>
				</td>
				<td>
					<input type="text" id="nj" name="nj" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="nj"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						银行卡号
					</div>
				</td>
				<td>
					<input type="text" id="yhkh" readonly="readonly" name="yhkh"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="yhkh"/>">
				</td>
				<td>
					<div align="center">
						贷款总额
					</div>
				</td>
				<td>
					<input type="text" id="dkze" name="dkze" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dkze"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						贷款年级
					</div>
				</td>
				<td>
					<input type="text" id="dknj" name="dknj" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dknj"/>">
				</td>
				<td>
					<div align="center">
						贷款发放日
					</div>
				</td>
				<td>
					<input type="text" id="dkffr" readonly="readonly" name="dkffr"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dkffr"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						贷款到期日
					</div>
				</td>
				<td>
					<input type="text" id="dkdqr" readonly="readonly" name="dkdqr"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dkdqr"/>">
				</td>
				<td>
					<div align="center">
						贴息终止日
					</div>
				</td>
				<td>
					<input type="text" id="txzzr" name="txzzr" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="txzzr"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						展期原因
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="zqyy" maxlength="100" name="zqyy"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zqyy"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						展期到期日期
					</div>
				</td>
				<td>
					<input type="text" style="cursor:hand;width:100%" maxlength="10"
							onclick="return showCalendar('zqdqrq','y-mm-dd');"
							value="<bean:write name='rs' property="zqdqrq" />" name="zqdqrq"
							id="zqdqrq" />
				</td>
				<td>
					<div align="center">
						展期贴息终止日期
					</div>
				</td>
				<td>
					<input type="text" style="cursor:hand;width:100%" maxlength="10"
							onclick="return showCalendar('zqtxzzrq','y-mm-dd');"
							value="<bean:write name='rs' property="zqtxzzrq" />" name="zqtxzzrq"
							id="zqtxzzrq" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						提前还款时间
					</div>
				</td>
				<td>
					<input type="text" style="cursor:hand;width:100%" maxlength="10"
							onclick="return showCalendar('tqhksj','y-mm-dd');"
							value="<bean:write name='rs' property="tqhksj" />" name="tqhksj"
							id="tqhksj" />
				</td>
				<td>
					<div align="center">
						提前还款金额
					</div>
				</td>
				<td>
					<input type="text" id="tqhkje" name="tqhkje" maxlength="10"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="tqhkje"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						按合同正常还款
					</div>
				</td>
				<td>
					<div align="center">
						第1年利息归还情况
					</div>
				</td>
				<td>
					<input type="text" id="lxghqk_1" name="lxghqk_1" maxlength="10"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxghqk_1"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						第2年利息归还情况
					</div>
				</td>
				<td>
					<input type="text" id="lxghqk_2" maxlength="10" name="lxghqk_2"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxghqk_2"/>">
				</td>
				<td>
					<div align="center">
						第3年利息归还情况
					</div>
				</td>
				<td>
					<input type="text" id="lxghqk_3" name="lxghqk_3" maxlength="10"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxghqk_3"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						第4年利息归还情况
					</div>
				</td>
				<td>
					<input type="text" id="lxghqk_4" maxlength="10" name="lxghqk_4"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxghqk_4"/>">
				</td>
				<td>
					<div align="center">
						第5年利息归还情况
					</div>
				</td>
				<td>
					<input type="text" id="lxghqk_5" name="lxghqk_5" maxlength="10"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxghqk_5"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						第6年利息归还情况
					</div>
				</td>
				<td>
					<input type="text" id="lxghqk_6" maxlength="10" name="lxghqk_6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxghqk_6"/>">
				</td>
				<td>
					<div align="center">
						第7年本息归还情况
					</div>
				</td>
				<td>
					<input type="text" id="lxghqk_7" name="lxghqk_7" maxlength="10"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxghqk_7"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						违约时间
					</div>
				</td>
				<td>
					<input type="text" style="cursor:hand;width:100%" maxlength="10"
							onclick="return showCalendar('wysj','y-mm-dd');"
							value="<bean:write name='rs' property="wysj" />" name="wysj"
							id="wysj" />
				</td>
				<td>
					<div align="center">
						违约金额
					</div>
				</td>
				<td>
					<input type="text" id="wyje" name="wyje" maxlength="10"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="wyje"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						违约原因
					</div>
				</td>
				<td>
					<input type="text" id="wyyy" maxlength="100" name="wyyy"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="wyyy"/>">
				</td>
				<td>
					<div align="center">
						是否全部还清贷款
					</div>
				</td>
				<td>
					<html:select name="rs" property="sfqbhqdk">
						<html:option value=""></html:option>
						<html:option value="是">是</html:option>
						<html:option value="否">否</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						备注
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="bz" maxlength="100" name="bz"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="bz"/>">
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
