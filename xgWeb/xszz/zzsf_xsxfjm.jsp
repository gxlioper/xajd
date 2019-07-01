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
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var bz = document.getElementById('bz').value;
			var jzxm = document.getElementById('jzxm').value;
			var jtdh = document.getElementById('jtdh').value;
			var jtdz = document.getElementById('jtdz').value;
			var yzbm = document.getElementById('yzbm').value;
			var ssdh = document.getElementById('ssdh').value;
			var ssdz = document.getElementById('ssdz').value;
			var jtjjqk = document.getElementById('jtjjqk').value;
			var yxknbzqk = document.getElementById('yxknbzqk').value;
			var qggw = document.getElementById('qggw').value;
			var xxcj = document.getElementById('xxcj').value;
			var sqje = document.getElementById('sqje').value;
			var psbxqk = document.getElementById('psbxqk').value;
			var gehjqk = document.getElementById('gehjqk').value;

			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((jzxm == null) || (jzxm == "")){
				alert("家长姓名不能为空!");
				return false;
			}
			if((jtdh == null) || (jtdh == "")){
				alert("家庭电话不能为空!");
				return false;
			}
			if((jtdz == null) || (jtdz == "")){
				alert("家庭地址不能为空!");
				return false;
			}
			if((yzbm == null) || (yzbm == "")){
				alert("邮政编码不能为空!");
				return false;
			}
			if((ssdh == null) || (ssdh == "")){
				alert("宿舍电话不能为空!");
				return false;
			}
			if((ssdz == null) || (ssdz == "")){
				alert("宿舍地址不能为空!");
				return false;
			}
			if((jtjjqk == null) || (jtjjqk == "")){
				alert("家庭经济情况不能为空!");
				return false;
			}
			if((yxknbzqk == null) || (yxknbzqk == "")){
				alert("院、系困难补助情况不能为空!");
				return false;
			}
			if((qggw == null) || (qggw == "")){
				alert("勤工岗位不能为空!");
				return false;
			}
			if((xxcj == null) || (xxcj == "")){
				alert("学习成绩不能为空!");
				return false;
			}
			if((sqje == null) || (sqje == "")){
				alert("申请金额不能为空!");
				return false;
			}
			if((psbxqk == null) || (psbxqk == "")){
				alert("平时表现情况不能为空!");
				return false;
			}
			if((gehjqk == null) || (gehjqk == "")){
				alert("个人获奖情况不能为空!");
				return false;
			}
			if((jtdh != null) && (jtdh != "") && (!isNumber(jtdh))){
				alert("家庭电话必须为整数!");
				return false;
			}
			if((yzbm != null) && (yzbm != "") && (!isNumber(yzbm))){
				alert("邮政编码必须为整数!");
				return false;
			}
			if((ssdh != null) && (ssdh != "") && (!isNumber(ssdh))){
				alert("宿舍电话必须为整数!");
				return false;
			}
			if(jtjjqk != null){
	         	if(jtjjqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("家庭经济情况不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(yxknbzqk != null){
	         	if(yxknbzqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />困难补助情况不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if((sqje != null) && (sqje != "") && (!isNumber(sqje))){
				alert("申请金额必须为整数!");
				return false;
	       	}
	       	if(psbxqk != null){
	         	if(psbxqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("平时表现情况不能大于200个字符");
	          		 return false;
	       		 }
			}
			if(gehjqk != null){
	         	if(gehjqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("个人获奖情况不能大于200个字符");
	          		 return false;
	       		 }
			}
			if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("备注不能大于100个字符");
	          		 return false;
	       		 }
			}
			
			document.forms[0].action = "/xgxt/zzsf_xsxfjm.do?doType=add";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/zzsf_xsxfjmsqb.do";
			document.forms[0].submit();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 学生学费减免申请
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="zzsf_xsxfjm.do" method="post">
			<input type="hidden" id="url" name="url"
				value="/zzsf_xsxfjm.do?jxjlbType=zzsf_gjzxdk" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
			<input type="hidden" id="tyjmjedx" name="tyjmjedx"
				value="<bean:write name="rs" property="tyjmjedx"/>">
			<input type="hidden" id="tyjmje" name="tyjmje"
				value="<bean:write name="rs" property="tyjmje"/>">
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd"/>">
			<input type="hidden" id="xslbmc" name="xslbmc"
				value="<bean:write name="rs" property="xslbmc"/>">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj"/>">
			<input type="hidden" id="isKNS" name="isKNS"
				value="<bean:write name="isKNS" scope="request" />">

			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         	alert("已通过学校审核，不能再次申请！");
	         	</script>
				</logic:match>
			</logic:present>
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

			<table class="tbstyle">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>学号：
						</td>
						<td align="left" colspan="3">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
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
						<td align="left" colspan="3">
							<input type="text" id="xh" name="xh" readonly="readonly"
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
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							生源地
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="syd" readonly="readonly" name="syd"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="syd"/>">
					</td>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zzmm" readonly="readonly" name="zzmm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmm"/>">
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
					<td colspan="2">
						<div align="center">
							学校
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xxmc" readonly="readonly" name="xxmc"
							style="width:100%;heigh:100%" value="<bean:write name="xxmc"/>">
					</td>
					<td>
						<div align="center">
							 系别
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
							专业
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zymc" name="zymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<td>
						<div align="center">
							班
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
							出生年月
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="csrq" readonly="readonly" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>学生类别
						</div>
					</td>
					<td>
						<html:select name="rs" property="xslb">
							<html:options collection="xslbList" property="xslb"
								labelProperty="xslbmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>家长姓名
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jzxm" name="jzxm" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzxm"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>家庭电话
						</div>
					</td>
					<td>
						<input type="text" id="jtdh" name="jtdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>家庭地址
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="jtdz" maxlength="50" name="jtdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>邮政编码
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="yzbm" maxlength="6" name="yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>宿舍电话
						</div>
					</td>
					<td>
						<input type="text" id="ssdh" name="ssdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ssdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>宿舍地址
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="ssdz" maxlength="50" name="ssdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ssdz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>家庭经济情况(人均月收入)
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="jtjjqk" rows='5' style="width:100%" onblur="yzdx(this,'jtjjqk')"/>
						<input type="hidden" id="jtjjqk" name="jtjjqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>院、系困难补助情况
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="yxknbzqk" rows='5' style="width:100%" onblur="yzdx(this,'yxknbzqk')"/>
						<input type="hidden" id="yxknbzqk" name="yxknbzqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>勤工岗位
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="qggw" maxlength="25" name="qggw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qggw"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>学习成绩(班级排名)
						</div>
					</td>
					<td>
						<input type="text" id="xxcj" name="xxcj" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xxcj"/>">
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<div align="right">
							<font color="red">注：新生学习成绩填写高考分数及班级排名。</font>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>申请种类
						</div>
					</td>
					<td colspan="3" align="center">
						<logic:present name="rs" property="sqzl">
							<logic:match value="减交" name="rs" property="sqzl">
								<input type="radio" name="sqzl" value="减交" checked>&nbsp;&nbsp;减交
							         	<input type="radio" name="sqzl" value="免交">&nbsp;&nbsp;免交
							         </logic:match>
							<logic:match value="免交" name="rs" property="sqzl">
								<input type="radio" name="sqzl" value="减交">&nbsp;&nbsp;减交
							         	<input type="radio" name="sqzl" value="免交" checked>&nbsp;&nbsp;免交
							         </logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sqzl">
							<input type="radio" name="sqzl" value="减交" checked>&nbsp;&nbsp;减交
							         <input type="radio" name="sqzl" value="免交">&nbsp;&nbsp;免交
						         </logic:notPresent>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>申请金额
						</div>
					</td>
					<td>
						<input type="text" id="sqje" name="sqje" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>平时表现情况
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="psbxqk" rows='5' style="width:100%" onblur="yzdx(this,'psbxqk')"/>
						<input type="hidden" id="psbxqk" name="psbxqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>个人获奖情况
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="gehjqk" rows='5' style="width:100%" onblur="yzdx(this,'gehjqk')"/>
						<input type="hidden" id="gehjqk" name="gehjqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							备注
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="bz" rows='3' style="width:100%" onblur="yzdx(this,'bz')"/>
						<input type="hidden" id="bz" name="bz" value="">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2" id="tjbut"
					onClick="yz();">
					提交申请
				</button>
				<button class="button2"
					onClick="toPrintOut();">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
				<logic:equal name="isKNS" value="no">
					<br /><font color="red">必须是困难生才能申请!</font>
				</logic:equal>
			</div>
	</logic:equal>

		</html:form>
</body>
<script language="javascript">
	var isKNS = document.getElementById('isKNS').value;
	if (isKNS == "is") {
		document.getElementById('tjbut').disabled = "";
	} else {
		document.getElementById('tjbut').disabled = "true";
	}
</script>
</html:html>
