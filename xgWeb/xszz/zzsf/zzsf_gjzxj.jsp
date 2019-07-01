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
			var lxdh = document.getElementById('lxdh').value;
			var jtzrks = document.getElementById('jtzrks').value;
			var rjysr = document.getElementById('rjysr').value;
			var jtyzsr = document.getElementById('jtyzsr').value;
			var srly = document.getElementById('srly').value;
			var yzbm = document.getElementById('yzbm').value;
			var xxcj = document.getElementById('xxcj').value;
			var jtzz = document.getElementById('jtzz').value;
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((chhzjl == null) || (chhzjl == "")){
				alert("曾获何种奖励不能为空!");
				return false;
			}
			if((lxdh == null) || (lxdh == "")){
				alert("联系电话不能为空!");
				return false;
			}
			if((jtzrks == null) || (jtzrks == "")){
				alert("家庭总人口数不能为空!");
				return false;
			}
			if((rjysr == null) || (rjysr == "")){
				alert("人均月收入不能为空!");
				return false;
			}
			if((jtyzsr == null) || (jtyzsr == "")){
				alert("家庭月总收入不能为空!");
				return false;
			}
			if((srly == null) || (srly == "")){
				alert("收入来源不能为空!");
				return false;
			}
			if((yzbm == null) || (yzbm == "")){
				alert("邮政编码不能为空!");
				return false;
			}
			if((xxcj == null) || (xxcj == "")){
				alert("学习成绩不能为空!");
				return false;
			}
			if(chhzjl != null){
	         	if(chhzjl.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("曾获何种奖励不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(jtzz != null || (jtzz != "")){
	         	if(jtzz.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("家庭住址不能大于200个字符");
	          		 return false;
	       		 }
	       	} else {
	       		alert("家庭住址不能为空!");
				return false;
	       	}
			if(sqly != null || (sqly != "")){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("申请理由不能大于1000个字符");
	          		 return false;
	       		 }
	       	} else {
	       		alert("申请理由不能为空!");
				return false;
	       	}
			document.forms[0].action = "/xgxt/zzsf_gjzxj.do?doType=add";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/zzsf_gjzxjb.do";
			document.forms[0].submit();
		}
		
		function je(){
			var jtzrks = document.getElementById('jtzrks').value;
			var rjysr = document.getElementById('rjysr').value;
			if((jtzrks == null) || (jtzrks == "")){
				jtzrks = "0";
			}
			if((rjysr == null) || (rjysr == "")){
				rjysr = "0";
			}
			var jtyzsr = Math.round(jtzrks) * Math.round(rjysr);
			document.getElementById('jtyzsr').value=jtyzsr;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 国家助学金申请
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间！
			</p>
		</center>
	</logic:equal>
		<html:form action="zzsf_gjzxj.do" method="post">
			<input type="hidden" id="url" name="url" value="/zzsf_gjzxj.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" scope="request" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" scope="request" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" scope="request" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" scope="request" />">
			<input type="hidden" id="xyzzfzryj" name="xyzzfzryj"
				value="<bean:write name="rs" property="xyzzfzryj" scope="request" />">
			<input type="hidden" id="xn" name="xn"
				value="<bean:write name="rs" property="xn" scope="request" />">
			<input type="hidden" id="isKNS" name="isKNS"
				value="<bean:write name="isKNS" scope="request" />">

			<logic:present name="notKns">
				<logic:match value="is" name="notKns">
					<script language="javascript">
	         			alert("必须是困难生才能申请！");
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
							value="<bean:write name="rs" property="xm"/>">
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
							出生年月
						</div>
					</td>
					<td>
						<input type="text" id="csrq" name="csrq" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
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
							身份证号
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
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
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
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
				</tr>
				<tr>
					<td>
						<div align="center">
							入学日期
						</div>
					</td>
					<td>
						<input type="text" id="rxrq" readonly="readonly" name="rxrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxrq"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>联系电话
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" maxlength="15" name="lxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>曾获何种奖励
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="chhzjl" rows='2' style="width:100%" onblur="yzdx(this,'chhzjl')"/>
						<input type="hidden" id="chhzjl" name="chhzjl" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>家庭户口
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="jthk">
									<logic:match value="城镇" name="rs" property="jthk">
										<input type="radio" name="jthk" value="城镇" checked>&nbsp;&nbsp;A  城镇
							         	<input type="radio" name="jthk" value="农村">&nbsp;&nbsp;B 农村
							         </logic:match>
									<logic:match value="农村" name="rs" property="jthk">
										<input type="radio" name="jthk" value="城镇">&nbsp;&nbsp;A  城镇
							         	<input type="radio" name="jthk" value="农村" checked>&nbsp;&nbsp;B 农村
							         </logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="jthk">
									<input type="radio" name="jthk" value="城镇" checked>&nbsp;&nbsp;A  城镇 
							         <input type="radio" name="jthk" value="农村">&nbsp;&nbsp;B 农村
						         </logic:notPresent>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>家庭人口总数
						</div>
					</td>
					<td>
						<input type="text" id="jtzrks" maxlength="3" name="jtzrks"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="jtzrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>家庭人均月收入
						</div>
					</td>
					<td>
						<input type="text" id="rjysr" maxlength="5" name="rjysr"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="rjysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>家庭月总收入
						</div>
					</td>
					<td>
						<input type="text" id="jtyzsr" maxlength="10" name="jtyzsr" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyzsr"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>收入来源
						</div>
					</td>
					<td>
						<input type="text" id="srly" maxlength="25" name="srly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="srly"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>邮政编码
						</div>
					</td>
					<td>
						<input type="text" id="yzbm" maxlength="6" name="yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>家庭地址
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="jtzz" rows='2' style="width:100%" onblur="yzdx(this,'jtzz')"/>
						<input type="hidden" id="jtzz" name="jtzz" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>学生类别
						</div>
					</td>
					<td>
						<html:select name="rs" property="xslb">
							<html:options collection="xszzXslbList" property="xslb"
								labelProperty="xslb" />
						</html:select>
					</td>
					<td colspan="2">
					<font color="#ff0000">
					注："学习成绩"栏新生填写入学成绩，老生填写上学年综合测评成绩及专业排名（含本专业年级总人数）。
					</font>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>学习成绩
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xxcj" maxlength="25" name="xxcj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xxcj"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>申请理由
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" style="position: absolute;width:90%">
				<button type="button" class="button2" id="tjbut"
					onClick="yz();">
					提交申请
				</button>
				<button type="button" class="button2"
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
