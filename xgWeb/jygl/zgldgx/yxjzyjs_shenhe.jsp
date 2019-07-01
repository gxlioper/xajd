<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
<title>就业管理信息系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<base target="_self">

<script language="javascript" src="js/function.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<script language="javascript">
function gnxxsh(){
	var url ="/xgxt/yxjzyjs.do?act=Auditing&shenhe=auditing&doType=kssh&pkValue=";
	var pkValue ="";
	 pkValue = document.getElementById("pkValue").value;
	 url += pkValue;
	 document.forms[0].action = url;
	 document.forms[0].submit();
}

</script>

<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<body>
<html:form action="/yxjzyjs.do" method="post">
	<table width="100%" class="tbstyle">
		<thead>
			<tr>
				<td align="center" colspan="4" height=""><b>系（院）及专业介绍录入</b></td>
			</tr>
		</thead>
		<tr>
			<td align="right">系(院)名称：</td>
			<td colspan="3"><input id="pkValue" name="pkValue" type="hidden"
				value="${rs1.id }" /> <html:text name="rs1" property="xymc"
				readonly="true" /></td>
		</tr>
		<tr>
			<td align="right">系(院)基本情况：</td>
			<td align="right" colspan="3"></td>
		</tr>
		<tr>
			<td colspan="1"></td>
			<td colspan="3"><html:textarea name="rs1" property="xyjbqk"
				style="width: 100%" rows="8" readonly="true"></html:textarea></td>
		</tr>
		<tr>
			<td align="right">专业名称：</td>
			<td><html:text name="rs1" property="zymc" style="width: 70%"
				readonly="true" /></td>
			<td align="right">人数：</td>
			<td><html:text name="rs1" property="rs1" readonly="true" />人</td>
		</tr>
		<tr>
			<td align="right"><font color="red">*</font>培养层次：</td>
			<td><html:text name="rs1" property="pycc" style="width: 70%"
				readonly="true" /></td>
			<td align="right">学制：</td>
			<td><html:text name="rs1" property="xz" readonly="true" />年</td>
		</tr>
		<tr>
			<td align="right">学位：</td>
			<td colspan="3"><html:text name="rs1" property="xw"
				style="width: 80%" readonly="true" /></td>

		</tr>
		<tr>
			<td align="right">培养目标及特色：</td>
			<td align="right" colspan="3"></td>
		</tr>
		<tr>
			<td colspan="1"></td>
			<td colspan="3"><html:textarea name="rs1" property="pymb"
				style="width: 100%" rows="8" readonly="true"></html:textarea></td>
		</tr>
		<tr>
			<td align="right">主要课程：</td>
			<td align="right" colspan="3"></td>
		</tr>
		<tr>
			<td colspan="1"></td>
			<td colspan="3"><html:textarea name="rs1" property="zykc"
				style="width: 100%" rows="8" readonly="true"></html:textarea></td>
		</tr>
		<tr>
			<td align="right">就业前景及方向：</td>
			<td align="right" colspan="3"></td>
		</tr>
		<tr>
			<td colspan="1"></td>
			<td colspan="3"><html:textarea name="rs1" property="jyqj"
				style="width: 100%" rows="8" readonly="true">
			</html:textarea></td>
		</tr>
		<tr>
			<td align="right"><font color="red">*</font>学校审核：</td>
			<td><html:select name="rs1" property="xxsh">
				<html:option value=""></html:option>
				<html:option value="未审核">未审核</html:option>
				<html:option value="已通过√">已通过√</html:option>
				<html:option value="未通过X">未通过X</html:option>
			</html:select></td>
			<td align="right">审核人：</td>
			<td><html:text name="rs1" property="shr" readonly="true" /></td>
		</tr>
	</table>
	<div align="center">
	<button class="button2" onclick="gnxxsh();BatAlert.showTips('正在提交，请等待...');" style="width: 80px">
	提 交</button>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button class="button2"
		onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
		style="width: 80px">关 闭</button>
	</div>
</html:form>

<logic:notEmpty name="ifsh">
	<logic:equal name="ifsh" value="yes">
		<script>
                      alert("审核成功！");
                    </script>
	</logic:equal>
	<logic:equal name="ifsh" value="no">
		<script>
                      alert("审核失败!");
                    </script>
	</logic:equal>
</logic:notEmpty>
</body>
</html>

