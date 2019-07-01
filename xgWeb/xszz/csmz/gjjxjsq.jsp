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
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var zyjx = document.getElementById('zyjx').value;
			var sqly = document.getElementById('sqly').value;
			
			if(xh == null || xh == ""){
				alert("学号不能为空!");
				return false;
			}
			
			if(zyjx != null){
	         	if(zyjx.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("主要奖项不能大于1000个字符！");
	          		 return false;
	       		 }
	       	}
	       	
	       	if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("申请理由不能大于2000个字符！");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=gjjxjsq&doType=save";
			document.forms[0].submit();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=gjjxjsqb";
			document.forms[0].submit();
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
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="csmz_xszz.do?method=gjjxjsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/csmz_xszz.do?method=gjjxjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xn" name="xn"
				value="<bean:write name="rs" property="xn" />">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" />">

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
			<logic:present name="have">
				<logic:match value="have" name="have">
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
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
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
					<td width="16%" scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%" scope="col">
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xm" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xb" />" readonly="true">
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="sfzh" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xymc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<input type="text" id="zymc" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zymc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="bjmc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td>
						<input type="text" id="nj" name="nj" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="nj" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							入学年月
						</div>
					</td>
					<td>
						<input type="text" id="rxny" name="rxny"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="rxny" />" readonly="true">
					</td>
					<td>
						<div align="center">
							出生年月
						</div>
					</td>
					<td>
						<input type="text" id="csny" name="csny"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="csny" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							民族
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="mzmc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zzmmmc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" name="lxdh"
							style="width:100%;heigh:100%" maxlength="20"
							value="<bean:write name='rs' property="lxdh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							院级奖励(项)
						</div>
					</td>
					<td>
						<input type="text" id="yjjx" name="yjjx"
							style="width:100%;heigh:100%" maxlength="4"
							value="<bean:write name='rs' property="yjjx" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							校级奖励(项)
						</div>
					</td>
					<td>
						<input type="text" id="xjjx" name="xjjx"
							style="width:100%;heigh:100%" maxlength="4"
							value="<bean:write name='rs' property="xjjx" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							省级以上奖励(项)
						</div>
					</td>
					<td>
						<input type="text" id="sjjx" name="sjjx"
							style="width:100%;heigh:100%" maxlength="4"
							value="<bean:write name='rs' property="sjjx" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							主要奖项
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="zyjx" rows='5' style="width:100%" onblur="yzdx(this,'zyjx')"/>
						<input type="hidden" id="zyjx" name="zyjx" value="">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							本学年必修<br />课程(门)
						</div>
					</td>
					<td>
						<input type="text" id="gxnbxkcs" name="gxnbxkcs"
							style="width:100%;heigh:100%" maxlength="4"
							value="<bean:write name='rs' property="gxnbxkcs" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							优秀(门)
						</div>
					</td>
					<td>
						<input type="text" id="yxkc" name="yxkc"
							style="width:100%;heigh:100%" maxlength="4"
							value="<bean:write name='rs' property="yxkc" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							良好(门)
						</div>
					</td>
					<td>
						<input type="text" id="lhkc" name="lhkc"
							style="width:100%;heigh:100%" maxlength="4"
							value="<bean:write name='rs' property="lhkc" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							成绩排名(专业或年级)<br />(名次/总人数)
						</div>
					</td>
					<td>
						<input type="text" id="cjpm" name="cjpm"
							style="width:100%;heigh:100%" maxlength="10"
							value="<bean:write name='rs' property="cjpm" />">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							综合考评成绩(分)
						</div>
					</td>
					<td>
						<input type="text" id="zhkpcj" name="zhkpcj"
							style="width:100%;heigh:100%" maxlength="8"
							value="<bean:write name='rs' property="zhkpcj" />">
					</td>
					<td>
						<div align="center">
							综合考评排名<br />(名次/总人数)
						</div>
					</td>
					<td>
						<input type="text" id="zhkppm" name="zhkppm"
							style="width:100%;heigh:100%" maxlength="10"
							value="<bean:write name='rs' property="zhkppm" />">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							申请理由<br />
							(全面反映德智体美情况)
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sqly" rows='10' style="width:100%" onblur="yzdx(this,'sqly')"/>
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2" onClick="yz();">
					提交申请
				</button>
				<button class="button2" onClick="toPrintOut();">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
