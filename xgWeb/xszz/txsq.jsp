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
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>

</head>

<body onload="loadPage()">
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 贴息申请
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="txsq.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url"
				value="/txsq.do?jxjlbType=txsq" />
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
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("已通过审核，不能申请！");
	         		</script>
				</logic:match>
			</logic:present>
			<div class="xxk">
				<logic:notEmpty name="pages">
					<logic:iterate id="card" name="pages" scope="request">
						<ul>
							<li id="<bean:write name='card' property='en'/>l"
								class="xxk_off_l"></li>
							<li id="<bean:write name='card' property='en'/>m" onclick=""
								class="xxk_off_m">
								&nbsp;
								<bean:write name='card' property='cn' />
								&nbsp;
							</li>
							<li id="<bean:write name='card' property='en'/>r"
								class="xxk_off_r"></li>
						</ul>
					</logic:iterate>
				</logic:notEmpty>
			</div>

			<table class="tbstyle">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="right" colspan="2">
							<font color="red">*</font>学号：
						</td>
						<td align="left">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)"  readonly="true"/>
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>学号：
						</td>
						<td align="left">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="16%">
						<div align="center">
							<font color="red">*</font>姓名
						</div>
					</td>
					<td width="34%">
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<td>
						<div align="center">
							民族
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" maxlength="18" name="sfzh" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
					<td>
						<div align="center">
							所在院校
						</div>
					</td>
					<td>
						<input type="text" id="xxmc" name="xxmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xxmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							贷款银行(支行)
						</div>
					</td>
					<td>
						<input type="text" id="dkyh" name="dkyh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkyh"/>">
					</td>
					<td>
						<div align="center">
							贷款总金额
						</div>
					</td>
					<td>
						<input type="text" id="dkzje" name="dkzje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkzje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							贷款期限
						</div>
					</td>
					<td>
						<input type="text" id="dkqx" name="dkqx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkqx"/>">
					</td>
					<td>
						<div align="center">
							应付利息
						</div>
					</td>
					<td>
						<input type="text" id="yflx" name="yflx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yflx"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							贷款银行签章
						</div>
					</td>
					<td width="60" colspan="3">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="3">
						<div align="center">
							申
							<br>
							请
							<br>
							理
							<br>
							由
						</div>
					</td>
					<td width="12%">
						<div align="center">
							帮困贴息
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<textarea id="bktx" name="bktx"
							style="width:100%;heigh:100%;heigh:100%" value="" rows="5" type="_moz">
							<bean:write name="rs" property="bktx" />
						</textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td width="12%">
						<div align="center">
							奖优贴息
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<textarea id="jytx" name="jytx"
							style="width:100%;heigh:100%;heigh:100%" value="" rows="5" type="_moz">
							<bean:write name="rs" property="jytx" />
						</textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td width="12%">
						<div align="center">
							政策贴息
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<textarea id="zctx" name="zctx"
							style="width:100%;heigh:100%;heigh:100%" value="" rows="5" type="_moz">
							<bean:write name="rs" property="zctx" />
						</textarea>
						</div>
					</td>
				</tr>
			</table>

	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2"
					onClick="xssqgjjxjzxj(document.getElementById('titName').value);">
					提交申请
				</button>
				<button class="button2"
					onClick="toPrintOut(document.getElementById('titName').value);">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
