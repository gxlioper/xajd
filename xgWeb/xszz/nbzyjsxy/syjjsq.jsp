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
			var chhzjl = document.getElementById('chhzjl').value;
			var jtzz = document.getElementById('jtzz').value;
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if(chhzjl != null){
	         	if(chhzjl.replace(/[^\u0000-\u00ff]/g, "**").length > 400){	         
	          		 alert("曾获何种奖励不能大于200个字");
	          		 return false;
	       		 }
	       	}
	       	if(jtzz != null){
	         	if(jtzz.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("家庭住址不能大于100个字");
	          		 return false;
	       		 }
	       	}
	       	if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("申请理由不能大于1000个字");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/nbzyjsxy_xszz.do?method=syjjsqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/nbzyjsxy_xszz.do?method=syjjsqb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 思源基金
		</div>
	</div>
		<html:form action="nbzyjsxy_xszz.do?method=syjjsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/nbzyjsxy_xszz.do?method=syjjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name='pkVal' />">

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
			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>学号：
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
						<td align="center" colspan="2">
							<font color="red">*</font>学号：
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
					<td width="34%">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td colspan="2">
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
							民族
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" name="mzmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							出生日期
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="csny" readonly="readonly" name="csny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csny"/>">
					</td>
					<td>
						<div align="center">
							入学时间
						</div>
					</td>
					<td>
						<input type="text" id="rxny" name="rxny" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxny"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
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
					<td colspan="2">
						<div align="center">
							专业名称
						</div>
					</td>
					<td colspan="2">
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
					<td colspan="2">
						<div align="center">
							曾获何种奖励
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="chhzjl" rows='4' style="width:100%" onblur="yzdx(this,'chhzjl')"/>
						<input type="hidden" id="chhzjl" name="chhzjl" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭户口
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jthk" name="jthk" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jthk"/>">
					</td>
					<td>
						<div align="center">
							家庭月均收入
						</div>
					</td>
					<td>
						<input type="text" id="jtyjsr" name="jtyjsr" maxlength="8"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyjsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭住址
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="jtzz" rows='2' style="width:100%" onblur="yzdx(this,'jtzz')"/>
						<input type="hidden" id="jtzz" name="jtzz" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							联系电话
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="lxdh" name="lxdh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请金额
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="sqje" name="sqje" maxlength="8"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							申请时间
						</div>
					</td>
					<td>
						<input type="text" id="sqsj" name="sqsj" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqsj"/>">
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
					<td width="17%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="17%">
						<div align="center">
							与本人关系
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作或学习单位
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_xm" name="jtcy1_xm" maxlength="50"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_gx" name="jtcy1_gx" maxlength="40"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy1_gzdw" name="jtcy1_gzdw"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_xm" name="jtcy2_xm" maxlength="50"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_gx" name="jtcy2_gx" maxlength="40"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy2_gzdw" name="jtcy2_gzdw"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_xm" name="jtcy3_xm" maxlength="50"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_gx" name="jtcy3_gx" maxlength="40"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy3_gzdw" name="jtcy3_gzdw"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_xm" name="jtcy4_xm" maxlength="50"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_gx" name="jtcy4_gx" maxlength="40"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy4_gzdw" name="jtcy4_gzdw"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_xm" name="jtcy5_xm" maxlength="50"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_gx" name="jtcy5_gx" maxlength="40"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy5_gzdw" name="jtcy5_gzdw"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
			</table>
		<logic:equal name="isKns" value="yes">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="yz();">
					提交申请
				</button>
				<button type="button" class="button2" onClick="toPrintOut();">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
		</logic:equal>
		<logic:equal name="isKns" value="no">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<div align="center">
					<font color="red">必须为困难生才能申请。</font>
				</div>
				<button type="button" class="button2" disabled="disabled">
					提交申请
				</button>
				<button type="button" class="button2" onClick="toPrintOut();">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
		</logic:equal>
		</html:form>
</body>
<script language="javascript">
</script>
</html:html>
