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
			var lxdh = document.getElementById('lxdh').value;
			var gxnbxkcs = document.getElementById('gxnbxkcs').value;
			var yxkcs = document.getElementById('yxkcs').value;
			var lhkcs = document.getElementById('lhkcs').value;
			var cjpm = document.getElementById('cjpm').value;
			var zhkpcj = document.getElementById('zhkpcj').value;
			var zhkppm = document.getElementById('zhkppm').value;
			var hjqk = document.getElementById('hjqk').value;
			var hjqk_xj = document.getElementById('hjqk_xj').value;
			var hjqk_xxj = document.getElementById('hjqk_xxj').value;
			var hjqk_sj = document.getElementById('hjqk_sj').value;
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((lxdh == null) || (lxdh == "")){
				alert("联系电话不能为空!");
				return false;
			}
			if((gxnbxkcs == null) || (gxnbxkcs == "")){
				alert("该学年必修课程数不能为空!");
				return false;
			}
			if((yxkcs == null) || (yxkcs == "")){
				alert("优秀课程数不能为空!");
				return false;
			}
			if((lhkcs == null) || (lhkcs == "")){
				alert("良好课程数不能为空!");
				return false;
			}
			if((cjpm == null) || (cjpm == "")){
				alert("成绩排名不能为空!");
				return false;
			}
			if((zhkpcj == null) || (zhkpcj == "")){
				alert("综合考评成绩不能为空!");
				return false;
			}
			if((zhkppm == null) || (zhkppm == "")){
				alert("综合考评排名不能为空!");
				return false;
			}
			if((hjqk == null) || (hjqk == "")){
				alert("获奖情况不能为空!");
				return false;
			}
			if((hjqk_xj == null) || (hjqk_xj == "")){
				alert("系级奖项不能为空!");
				return false;
			}
			if((hjqk_xxj == null) || (hjqk_xxj == "")){
				alert("校级奖项不能为空!");
				return false;
			}
			if((hjqk_sj == null) || (hjqk_sj == "")){
				alert("省级奖项不能为空!");
				return false;
			}
			if((sqly == null) || (sqly == "")){
				alert("申请理由不能为空!");
				return false;
			}
			if(hjqk != null){
	         	if(hjqk.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("获奖情况不能大于200个字符");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("申请理由不能大于2000个字符");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zzsf_gjjxj.do?doType=add";
			document.forms[0].submit();
		}
		
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/zzsf_gjjxjb.do";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 国家奖学金申请
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间！
			</p>
		</center>
	</logic:equal>
		<html:form action="zzsf_gjjxj.do" method="post">
			<input type="hidden" id="url" name="url" value="/zzsf_gjjxj.do" />
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
			<input type="hidden" id="xydzzshyj" name="xydzzshyj"
				value="<bean:write name="rs" property="xydzzshyj" scope="request" />">
			<input type="hidden" id="xn" name="xn"
				value="<bean:write name="rs" property="xn" scope="request" />">

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
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>该学年必修课程数
						</div>
					</td>
					<td>
						<input type="text" id="gxnbxkcs" maxlength="3" name="gxnbxkcs"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gxnbxkcs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>优秀课程数
						</div>
					</td>
					<td>
						<input type="text" id="yxkcs" maxlength="3" name="yxkcs"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yxkcs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>良好课程数
						</div>
					</td>
					<td>
						<input type="text" id="lhkcs" maxlength="3" name="lhkcs"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lhkcs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>成绩排名<br />(专业或年级)
						</div>
					</td>
					<td>
						<input type="text" id="cjpm" maxlength="10" name="cjpm"
							style="width:60%;heigh:100%"
							value="<bean:write name="rs" property="cjpm"/>">(名次/总人数)
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>综合考评成绩
						</div>
					</td>
					<td>
						<input type="text" id="zhkpcj" maxlength="10" name="zhkpcj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zhkpcj"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>综合考评排名
						</div>
					</td>
					<td>
						<input type="text" id="zhkppm" maxlength="10" name="zhkppm"
							style="width:60%;heigh:100%"
							value="<bean:write name="rs" property="zhkppm"/>">
						(名次/总人数)
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>获奖情况
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="hjqk" rows='3' style="width:100%" onblur="yzdx(this,'hjqk')"/>
						<input type="hidden" id="hjqk" name="hjqk" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>获得系级奖项
						</div>
					</td>
					<td>
						<input type="text" id="hjqk_xj" maxlength="3" name="hjqk_xj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hjqk_xj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>获得校级奖项
						</div>
					</td>
					<td>
						<input type="text" id="hjqk_xxj" maxlength="3" name="hjqk_xxj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hjqk_xxj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>获得省级奖项
						</div>
					</td>
					<td>
						<input type="text" id="hjqk_sj" maxlength="3" name="hjqk_sj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hjqk_sj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							申请时间
						</div>
					</td>
					<td>
						<input type="text" id="sqsj" readonly="readonly" name="sqsj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqsj"/>">
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
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2"
					onClick="yz();">
					提交申请
				</button>
				<button type="button" class="button2"
					onClick="toPrintOut();">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
