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
			var sqje = document.getElementById('sqje').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((sqje == null) || (sqje == "")){
				sqje = "0";
			}
			if (6000 < Math.round(sqje)){
				alert("申请贷款金额不得大于6000元!");
				return false;
			}
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=gjzxdksqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function je(){
			var sqje = document.getElementById('sqje').value;
			if((sqje == null) || (sqje == "")){
				sqje = "0";
			}
			if (6000 < Math.round(sqje)){
				alert("申请贷款金额不得大于6000元!");
				document.getElementById('sqje').value = "6000";
			}
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=gjzxdksqb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 申请 - 国家助学贷款
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="nblg_xszz.do?method=gjzxdksq" method="post">
			<input type="hidden" id="url" name="url"
				value="/nblg_xszz.do?method=gjzxdksq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id=xn name="xn"
				value="<bean:write name="rs" property="xn" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xz" name="xz"
				value="<bean:write name="rs" property="xz" />">

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
			<logic:present name="rs" property="notKns">
				<logic:match value="notKns" name="rs" property="notKns">
					<script language="javascript">
	         	alert("必须是困难生才能申请！");
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
			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" width="16%">
							<font color="red">*</font>学号
						</td>
						<td align="left" width="34%">
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
						<td align="left" width="34%">
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
							出生年月
						</div>
					</td>
					<td>
						<input type="text" id="csny" readonly="readonly" name="csny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csny"/>">
					</td>
					<td>
						<div align="center">
							入学年月
						</div>
					</td>
					<td>
						<input type="text" id="rxny" readonly="readonly" name="rxny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxny"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							籍贯
						</div>
					</td>
					<td>
						<input type="text" id="jg" name="jg" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jg"/>">
					</td>
					<td>
						<div align="center">
							户籍所在地
						</div>
					</td>
					<td>
						<input type="text" id="hjszd" name="hjszd" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hjszd"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							E-Mail地址
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="email" maxlength="400" name="email"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="email"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							移动电话
						</div>
					</td>
					<td>
						<input type="text" id="yddh" name="yddh" maxlength="11"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yddh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							家庭电话
						</div>
					</td>
					<td>
						<input type="text" id="jtdh" name="jtdh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							健康状况
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="jkzk">
							<logic:match value="良好" name="rs" property="jkzk">
								<input type="radio" name="jkzk" value="良好" checked>&nbsp;&nbsp;良好&nbsp;
							    <input type="radio" name="jkzk" value="一般">&nbsp;&nbsp;一般
							</logic:match>
							<logic:match value="一般" name="rs" property="jkzk">
								<input type="radio" name="jkzk" value="良好">&nbsp;&nbsp;良好&nbsp;
							    <input type="radio" name="jkzk" value="一般" checked>&nbsp;&nbsp;一般
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jkzk">
							<input type="radio" name="jkzk" value="良好" checked>&nbsp;&nbsp;良好&nbsp;
							<input type="radio" name="jkzk" value="一般">&nbsp;&nbsp;一般
						</logic:notPresent>
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
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭通讯地址
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jttxdz" maxlength="400" name="jttxdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jttxdz"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							申请金额
						</div>
					</td>
					<td>
						<input type="text" id="sqje" name="sqje" maxlength="4"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="sqje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							困难等级
						</div>
					</td>
					<td>
						<bean:write name="rs" property="kndj"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							见证人1情况
						</div>
					</td>
					<td>
						<div align="center">
							姓名
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_xm" name="jzr1_xm" maxlength="50"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr1_xm"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="jzr1_xb">
							<logic:match value="男" name="rs" property="jzr1_xb">
								<input type="radio" name="jzr1_xb" value="男" checked>&nbsp;&nbsp;男&nbsp;
							    <input type="radio" name="jzr1_xb" value="女">&nbsp;&nbsp;女
							</logic:match>
							<logic:match value="女" name="rs" property="jzr1_xb">
								<input type="radio" name="jzr1_xb" value="男">&nbsp;&nbsp;男&nbsp;
							    <input type="radio" name="jzr1_xb" value="女" checked>&nbsp;&nbsp;女
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jzr1_xb">
							<input type="radio" name="jzr1_xb" value="男" checked>&nbsp;&nbsp;男&nbsp;
							<input type="radio" name="jzr1_xb" value="女">&nbsp;&nbsp;女
						</logic:notPresent>
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_sfzh" name="jzr1_sfzh" maxlength="18"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr1_sfzh"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							与借款人关系
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_gx" name="jzr1_gx" maxlength="50"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr1_gx"/>">
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_lxdh" name="jzr1_lxdh" maxlength="20"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr1_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							现住址
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jzr1_xzz" name="jzr1_xzz" maxlength="200"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr1_xzz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							见证人2情况
						</div>
					</td>
					<td>
						<div align="center">
							姓名
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_xm" name="jzr2_xm" maxlength="50"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr2_xm"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="jzr2_xb">
							<logic:match value="男" name="rs" property="jzr2_xb">
								<input type="radio" name="jzr2_xb" value="男" checked>&nbsp;&nbsp;男&nbsp;
							    <input type="radio" name="jzr2_xb" value="女">&nbsp;&nbsp;女
							</logic:match>
							<logic:match value="女" name="rs" property="jzr2_xb">
								<input type="radio" name="jzr2_xb" value="男">&nbsp;&nbsp;男&nbsp;
							    <input type="radio" name="jzr2_xb" value="女" checked>&nbsp;&nbsp;女
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jzr2_xb">
							<input type="radio" name="jzr2_xb" value="男" checked>&nbsp;&nbsp;男&nbsp;
							<input type="radio" name="jzr2_xb" value="女">&nbsp;&nbsp;女
						</logic:notPresent>
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_sfzh" name="jzr2_sfzh" maxlength="18"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr2_sfzh"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							与借款人关系
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_gx" name="jzr2_gx" maxlength="50"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr2_gx"/>">
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_lxdh" name="jzr2_lxdh" maxlength="20"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr2_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							现住址
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jzr2_xzz" name="jzr2_xzz" maxlength="200"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr2_xzz"/>">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">			
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="yz();">
					提交申请
				</button>
				<button type="button" class="button2" onClick="toPrintOut();">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
