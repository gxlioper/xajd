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
			var chhzjl = document.getElementById('chhzjl').value;
			var xxcj = document.getElementById('xxcj').value;
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if(chhzjl != null){
	         	if(chhzjl.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("曾获何种奖励不能大于500个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxcj != null){
	         	if(xxcj.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("学习成绩不能大于1000个字符");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("申请理由不能大于1000个字符");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=gjlzjxjsqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=gjlzjxjsqb";
			document.forms[0].submit();
		}
		function je(){
			var jtrkzs = document.getElementById('jtrkzs').value;
			var rjysr = document.getElementById('rjysr').value;
			var jtyzsr = "0";
			
			if((jtrkzs == null) || (jtrkzs == "")){
				jtrkzs = "0";
			}
			if((rjysr == null) || (rjysr == "")){
				rjysr = "0";
			}
			jtyzsr = Math.round(jtrkzs) * Math.round(rjysr);
			
			document.getElementById('jtrkzs').value = jtrkzs;
			document.getElementById('rjysr').value = rjysr;
			document.getElementById('jtyzsr').value = jtyzsr;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 国家励志奖学金
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内，不能申请！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="zjjdzyjsxy_xszz.do?method=gjlzjxjsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zjjdzyjsxy_xszz.do?method=gjlzjxjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">

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
						<td align="center" width="20%">
							<font color="red">*</font>学号：
						</td>
						<td align="left" width="30%">
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
						<td align="center" width="20%">
							<font color="red">*</font>学号：
						</td>
						<td align="left" width="">
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
						<input type="text" id="sfzh" readonly="readonly" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							民族
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
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
							出生日期
						</div>
					</td>
					<td>
						<input type="text" id="csrq" readonly="readonly" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
					<td>
						<div align="center">
							入学年月
						</div>
					</td>
					<td>
						<input type="text" id="rxny" name="rxny" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxny"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							年度
						</div>
					</td>
					<td>
						<input type="text" id="nd" readonly="readonly" name="nd"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nd"/>">
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" name="lxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="4" style="padding:0;">
						<%@ include file="yhkh.jsp"%>
					</td>
				</tr>	
				<tr>
					<td>
						<div align="center">
							曾获何种奖励
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="chhzjl" rows='3'
							style="width:100%" onblur="yzdx(this,'chhzjl')" />
						<input type="hidden" id="chhzjl" name="chhzjl" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭户口
						</div>
					</td>
					<td>
						<div align="center">
						<logic:present name="rs" property="jthk">
							<logic:match value="城镇" name="rs" property="jthk">
								<input type="radio" name="jthk" value="城镇" checked>&nbsp;&nbsp;城镇
							    <input type="radio" name="jthk" value="农村">&nbsp;&nbsp;农村
							</logic:match>
							<logic:match value="农村" name="rs" property="jthk">
								<input type="radio" name="jthk" value="城镇">&nbsp;&nbsp;城镇
							    <input type="radio" name="jthk" value="农村" checked>&nbsp;&nbsp;农村
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jthk">
							<input type="radio" name="jthk" value="城镇" checked>&nbsp;&nbsp;城镇
							<input type="radio" name="jthk" value="农村">&nbsp;&nbsp;农村
						</logic:notPresent>
						</div>
					</td>
					<td>
						<div align="center">
							家庭人口总数
						</div>
					</td>
					<td>
						<input type="text" id="jtrkzs" name="jtrkzs" maxlength="3"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="jtrkzs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							人均月收入
						</div>
					</td>
					<td>
						<input type="text" id="rjysr" maxlength="5" name="rjysr"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="rjysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							家庭月总收入
						</div>
					</td>
					<td>
						<input type="text" id="jtyzsr" name="jtyzsr" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyzsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							收入来源
						</div>
					</td>
					<td>
						<input type="text" id="srly" maxlength="200" name="srly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="srly"/>">
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
							家庭住址
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtzz" maxlength="200" name="jtzz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzz"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							学习成绩
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xxcj" rows='10'
							style="width:100%" onblur="yzdx(this,'xxcj')" />
						<input type="hidden" id="xxcj" name="xxcj" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sqly" rows='10'
							style="width:100%" onblur="yzdx(this,'sqly')" />
						<input type="hidden" id="sqly" name="sqly" value="">
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
