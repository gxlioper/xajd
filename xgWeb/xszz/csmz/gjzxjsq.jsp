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
			var sqly = document.getElementById('sqly').value;
			
			if(xh == null || xh == ""){
				alert("学号不能为空!");
				return false;
			}
			
	       	if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("申请理由不能大于1000个字符！");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=gjzxjsq&doType=save";
			document.forms[0].submit();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function rs(){
			var jtzrks = document.getElementById('jtzrks').value;
			var rjysr = document.getElementById('rjysr').value;
			
			if (jtzrks == null || jtzrks == ""){
				jtzrks = "0";
			}
			if (rjysr == null || rjysr == ""){
				rjysr = "0";
			}
			
			var jtyzsr = Math.round(jtzrks)*Math.round(rjysr);
			
			if (jtyzsr == 0){
				jtyzsr = "";
			}
			
			document.getElementById('jtyzsr').value = jtyzsr;
		}
		
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=gjzxjsqb";
			document.forms[0].submit();
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
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="csmz_xszz.do?method=gjzxjsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/csmz_xszz.do?method=gjzxjsq" />
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
			<logic:present name="kns">
				<logic:match value="no" name="kns">
					<script language="javascript">
	         			alert("必须是困难生才能申请！");
	         		</script>
				</logic:match>
			</logic:present>

			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="2">
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
					<td scope="row" colspan="2">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="2">
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
					<td scope="row" colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="2">
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
					<td scope="row" colspan="2">
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="2">
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
					<td scope="row" colspan="2">
						<div align="center">
							入学年月
						</div>
					</td>
					<td colspan="2">
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
					<td scope="row" colspan="2">
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="mzmc" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="mzmc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" name="lxdh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="lxdh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							家庭户口
						</div>
					</td>
					<td colspan="2">
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
						<input type="text" id="jtzrks" name="jtzrks" onblur="rs();"
							style="width:100%;heigh:100%" maxlength="4"
							value="<bean:write name='rs' property="jtzrks" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							人均月收入
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="rjysr" name="rjysr" onblur="rs();"
							style="width:100%;heigh:100%" maxlength="4"
							value="<bean:write name='rs' property="rjysr" />"
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
							style="width:100%;heigh:100%" maxlength="8"
							value="<bean:write name='rs' property="jtyzsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							收入来源
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="srly" name="srly"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="srly" />">
					</td>
					<td>
						<div align="center">
							邮政编码
						</div>
					</td>
					<td>
						<input type="text" id="yzbm" name="yzbm"
							style="width:100%;heigh:100%" maxlength="6"
							value="<bean:write name='rs' property="yzbm" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							家庭住址
						</div>
					</td>
					<td colspan="4">
						<input type="text" id="jtzz" name="jtzz"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtzz" />">
					</td>
				</tr>
				<tr>
					<td rowspan="6" width="4%">
						<div align="center">
							家<br />庭<br />成<br />员<br />情<br />况
						</div>
					</td>
					<td width="14%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="16%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="16%">
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
						<input type="text" id="jtcy1_xm" name="jtcy1_xm" align="middle"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy1_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy1_nl" name="jtcy1_nl" align="middle"
							style="width:100%;heigh:100%" maxlength="3"
							value="<bean:write name='rs' property="jtcy1_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy1_gx" name="jtcy1_gx" align="middle"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy1_gx" />">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy1_gzdw" name="jtcy1_gzdw"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy1_gzdw" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy2_xm" name="jtcy2_xm" align="middle"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy2_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy2_nl" name="jtcy2_nl" align="middle"
							style="width:100%;heigh:100%" maxlength="3"
							value="<bean:write name='rs' property="jtcy2_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy2_gx" name="jtcy2_gx" align="middle"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy2_gx" />">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy2_gzdw" name="jtcy2_gzdw"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy2_gzdw" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy3_xm" name="jtcy3_xm" align="middle"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy3_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy3_nl" name="jtcy3_nl" align="middle"
							style="width:100%;heigh:100%" maxlength="3"
							value="<bean:write name='rs' property="jtcy3_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy3_gx" name="jtcy3_gx" align="middle"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy3_gx" />">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy3_gzdw" name="jtcy3_gzdw"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy3_gzdw" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy4_xm" name="jtcy4_xm" align="middle"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy4_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy4_nl" name="jtcy4_nl" align="middle"
							style="width:100%;heigh:100%" maxlength="3"
							value="<bean:write name='rs' property="jtcy4_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy4_gx" name="jtcy4_gx" align="middle"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy4_gx" />">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy4_gzdw" name="jtcy4_gzdw"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy4_gzdw" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy5_xm" name="jtcy5_xm" align="middle"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy5_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy5_nl" name="jtcy5_nl" align="middle"
							style="width:100%;heigh:100%" maxlength="3"
							value="<bean:write name='rs' property="jtcy5_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy5_gx" name="jtcy5_gx" align="middle"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy5_gx" />">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy5_gzdw" name="jtcy5_gzdw"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy5_gzdw" />">
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="sqly" rows='8' style="width:650px" onblur="yzdx(this,'sqly')"/>
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
