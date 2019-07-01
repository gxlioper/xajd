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
			var xxqk = document.getElementById('xxqk').value;
			var chhzjlhzzqk = document.getElementById('chhzjlhzzqk').value;
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if(xxqk != null){
	         	if(xxqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("学习情况不能超过200个字符！");
	          		 return false;
	       		 }
	       	}
			if(chhzjlhzzqk != null){
	         	if(chhzjlhzzqk.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("曾获何种奖励和资助情况不能超过1000个字符！");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("学生陈述申请理由不能超过2000个字符！");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=gjzxjsqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=gjzxjsqb";
			document.forms[0].submit();
		}
		function rks(){
			var jtrkzs = document.getElementById('jtrkzs').value;
			var jtrjysr = document.getElementById('jtrjysr').value;
			
			if((jtrkzs == null) || (jtrkzs == "")){
				jtrkzs = "0";
			}
			if((jtrjysr == null) || (jtrjysr == "")){
				jtrjysr = "0";
			}
			
			document.getElementById('jtyzsr').value = Math.round(jtrkzs)*Math.round(jtrjysr);
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 申请 - 国家助学金
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="nblg_xszz.do?method=gjzxjsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/nblg_xszz.do?method=gjzxjsq" />
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
			<input type="hidden" id="bdsyj" name="bdsyj"
				value="<bean:write name="rs" property="bdsyj" />">

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
							<font color="red">*</font>学号
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
							<font color="red">*</font>学号
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
							政治面貌
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zzmmmc" readonly="readonly" name="zzmmmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
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
							出生年月
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="csny" readonly="readonly" name="csny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csny"/>">
					</td>
					<td>
						<div align="center">
							上学年有无违纪处分
						</div>
					</td>
					<td>
						<input type="text" id="sxnywwjcf" name="sxnywwjcf" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sxnywwjcf"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							学习情况<br />(学习态度<br />、成绩等)
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="xxqk" rows='3'
							style='width:100%' onblur="yzdx(this,'xxqk')" />
						<input type="hidden" id="xxqk" name="xxqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							曾获何种奖励和资助情况
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="chhzjlhzzqk" rows='5'
							style='width:100%' onblur="yzdx(this,'chhzjlhzzqk')" />
						<input type="hidden" id="chhzjlhzzqk" name="chhzjlhzzqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							困难程度
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="kndj"/>
					</td>
					<td>
						<div align="center">
							申请国家助学金类别
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="sqdj">
							<logic:match value="一档" name="rs" property="sqdj">
								<input type="radio" name="sqdj" value="一档" checked>一档(家庭经济特别困难)&nbsp;
							    <input type="radio" name="sqdj" value="二档">二档(家庭经济困难)
							</logic:match>
							<logic:match value="二档" name="rs" property="sqdj">
								<input type="radio" name="sqdj" value="一档">一档(家庭经济特别困难)&nbsp;
							    <input type="radio" name="sqdj" value="二档" checked>二档(家庭经济困难)
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sqdj">
							<input type="radio" name="sqdj" value="一档" checked>一档(家庭经济特别困难)&nbsp;
							<input type="radio" name="sqdj" value="二档">二档(家庭经济困难)
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭户口
						</div>
					</td>
					<td align="center" colspan="2">
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
					</td>
					<td>
						<div align="center">
							家庭类型
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="jtlx">
							<logic:match value="双亲" name="rs" property="jtlx">
								<input type="radio" name="jtlx" value="双亲" checked>&nbsp;&nbsp;双亲
							    <input type="radio" name="jtlx" value="单亲">&nbsp;&nbsp;单亲
							    <input type="radio" name="jtlx" value="孤儿">&nbsp;&nbsp;孤儿
							</logic:match>
							<logic:match value="单亲" name="rs" property="jtlx">
								<input type="radio" name="jtlx" value="双亲">&nbsp;&nbsp;双亲
							    <input type="radio" name="jtlx" value="单亲" checked>&nbsp;&nbsp;单亲
							    <input type="radio" name="jtlx" value="孤儿">&nbsp;&nbsp;孤儿
							</logic:match>
							<logic:match value="孤儿" name="rs" property="jtlx">
								<input type="radio" name="jtlx" value="双亲">&nbsp;&nbsp;双亲
							    <input type="radio" name="jtlx" value="单亲">&nbsp;&nbsp;单亲
							    <input type="radio" name="jtlx" value="孤儿" checked>&nbsp;&nbsp;孤儿
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jtlx">
							<input type="radio" name="jtlx" value="双亲" checked>&nbsp;&nbsp;双亲
							<input type="radio" name="jtlx" value="单亲">&nbsp;&nbsp;单亲
							<input type="radio" name="jtlx" value="孤儿">&nbsp;&nbsp;孤儿
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							是否离异
						</div>
					</td>
					<td align="center" colspan="2">
						<logic:present name="rs" property="sfly">
							<logic:match value="是" name="rs" property="sfly">
								<input type="radio" name="sfly" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfly" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfly">
								<input type="radio" name="sfly" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfly" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfly">
							<input type="radio" name="sfly" value="是">&nbsp;&nbsp;是
							<input type="radio" name="sfly" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
					<td>
						<div align="center">
							是否烈属
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="sfls">
							<logic:match value="是" name="rs" property="sfls">
								<input type="radio" name="sfls" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfls" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfls">
								<input type="radio" name="sfls" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfls" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfls">
							<input type="radio" name="sfls" value="是">&nbsp;&nbsp;是
							<input type="radio" name="sfls" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭人口总数
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtrkzs" name="jtrkzs" maxlength="3"
							style="width:100%;heigh:100%" onblur="rks();"
							value="<bean:write name="rs" property="jtrkzs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							家庭人均月收入
						</div>
					</td>
					<td>
						<input type="text" id="jtrjysr" name="jtrjysr" maxlength="5"
							style="width:100%;heigh:100%" onblur="rks();"
							value="<bean:write name="rs" property="jtrjysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭月总收入
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtyzsr" name="jtyzsr" maxlength="10"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name="rs" property="jtyzsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							收入来源
						</div>
					</td>
					<td>
						<input type="text" id="srly" name="srly" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="srly"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭住址
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtzz" name="jtzz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzz"/>">
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
					<td width="4%" rowspan="6">
						<div align="center">
							家
							<br />
							庭
							<br />
							成
							<br />
							员
							<br />
							情
							<br />
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
							称谓
						</div>
					</td>
					<td width="17%">
						<div align="center">
							年收入
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							职业和工作(学习)单位
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy1_xm" name="jtcy1_xm"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy1_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy1_cw" name="jtcy1_cw"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy1_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy1_sr" name="jtcy1_sr"
							style="width:100%;heigh:100%" maxlength="7"
							value="<bean:write name='rs' property="jtcy1_sr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy1_gz" name="jtcy1_gz"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy1_gz" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy2_xm" name="jtcy2_xm"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy2_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy2_cw" name="jtcy2_cw"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy2_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy2_sr" name="jtcy2_sr"
							style="width:100%;heigh:100%" maxlength="7"
							value="<bean:write name='rs' property="jtcy2_sr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy2_gz" name="jtcy2_gz"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy2_gz" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy3_xm" name="jtcy3_xm"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy3_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy3_cw" name="jtcy3_cw"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy3_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy3_sr" name="jtcy3_sr"
							style="width:100%;heigh:100%" maxlength="7"
							value="<bean:write name='rs' property="jtcy3_sr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy3_gz" name="jtcy3_gz"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy3_gz" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy4_xm" name="jtcy4_xm"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy4_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy4_cw" name="jtcy4_cw"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy4_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy4_sr" name="jtcy4_sr"
							style="width:100%;heigh:100%" maxlength="7"
							value="<bean:write name='rs' property="jtcy4_sr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy4_gz" name="jtcy4_gz"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy4_gz" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy5_xm" name="jtcy5_xm"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy5_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy5_cw" name="jtcy5_cw"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy5_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy5_sr" name="jtcy5_sr"
							style="width:100%;heigh:100%" maxlength="7"
							value="<bean:write name='rs' property="jtcy5_sr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy5_gz" name="jtcy5_gz"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy5_gz" />">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="sqly" rows='12'
							style='width:100%' onblur="yzdx(this,'sqly')" />
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
