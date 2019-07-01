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
			var xmdm = document.getElementById('xmdm').value;
			var xh = document.getElementById('xh').value;
			var sqly = document.getElementById('sqly').value;
			var zdyzdXxxx = document.getElementById('zdyzdXxxx').value;
			
			var str = new Array();
			var strT = new Array();
			var i = 1;
			var j = 1;
			if(xmdm == null || xmdm == ""){
				alert("请选择要申请的资助项目!");
				return false;
			}
			if(xh == null){
				alert("学号不能为空!");
				return false;
			}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("申请理由不能大于2000个字符！");
	          		 return false;
	       		 }
	       	}
			str = zdyzdXxxx.split("!!TwoSpile!!");
	       	for(i = 1; i < str.length; i++) {
	       		strT = str[i].split("!!OneSpile!!");
	       		for(j = 1; j < strT.length; j++){
	       			if(strT[4] == "文本框"){
	       				var temp = document.getElementById('zdy'+strT[2]).value;
	       				if(temp != null){
	         				if(temp.replace(/[^\u0000-\u00ff]/g, "**").length > Math.round(strT[5])){	         
	          					 alert(str[3]+"不能大于"+strT[5]+"个字符！");
	          					 return false;
	       		 			}
	       				}
	       			}
	       		}
	       	}
			document.forms[0].action = "/xgxt/whlg_xszz.do?method=xszzsq&doType=add";
			document.forms[0].submit();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function toPrintOut(){//输出相应的打印页面
			//document.forms[0].action = "/xgxt/new_common_xszz.do?method=xszzsqb";
			//document.forms[0].submit();
		}
		
		function chang(){
			document.forms[0].action = "/xgxt/whlg_xszz.do?method=xszzsq";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 数据维护 - 资助申请
		</div>
	</div><%--
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内或不符合申请条件！！！
			</p>
		</center>
	</logic:equal>
	--%><%--<logic:equal name="sfksq" value="1">
		--%>
		<html:form action="/whlg_xszz.do?method=xszzsq" method="post">
		
		<input type="hidden" id="url" name="url"
			value="/whlg_xszz.do?method=xszzsq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc-nj-zzmmmc-sfzh-zymc" />
		<input type="hidden" id="userOnLine" name="userOnLine"
			value="<bean:write name="userOnLine" scope="session"/>" />
		<%--<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">
			--%>
			<%--<input type="hidden" id="url" name="url"
				value="/new_common_xszz.do?method=xszzsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" />">
			<input type="hidden" id="xmmc" name="xmmc"
				value="<bean:write name="rs" property="xmmc" />">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xyshsj" name="xyshsj"
				value="<bean:write name="rs" property="xyshsj" />">
			<input type="hidden" id="xypzje" name="xypzje"
				value="<bean:write name="rs" property="xypzje" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">
			<input type="hidden" id="xxshsj" name="xxshsj"
				value="<bean:write name="rs" property="xxshsj" />">
			<input type="hidden" id="xxpzje" name="xxpzje"
				value="<bean:write name="rs" property="xxpzje" />">--%>
			<input type="hidden" id="zdyzdXxxx" name="zdyzdXxxx"
				value="<bean:write name="zdyzdXxxx" scope="request"/>">
			<logic:present name="have">
				<logic:match value="have" name="have">
					<script language="javascript">
	         			alert("已通过审核，不能申请！");
	         			document.location = "/about:blank";
	         		</script>
				</logic:match>
			</logic:present>
			<table class="tbstyle" width="99%">
				<tr>
					<td align="center" colspan="2">
						<font color="red">*</font>资助项目
					</td>
					<td colspan="7">
						<html:select name="rs" property="xmdm" style="width:180px"
							onchange="chang();">
							<html:option value=""></html:option>
							<html:options collection="zzxmList" property="xmdm"
								labelProperty="xmmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="3">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
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
						<td align="left" colspan="3">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="11%" scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="3" scope="col">
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xm" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xb" name="xb" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xb" />" readonly="true">
					</td>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="nj" name="nj" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="nj" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sfzh" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="sfzh" />" readonly="true">
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="lxdh" name="lxdh"
							style="width:100%;heigh:100%" maxlength="15"
							value="<bean:write name='rs' property="lxdh" />" 
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xymc" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xymc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zymc" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zymc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="bjmc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							生源地
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="syd" name="syd"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="syd" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							宿舍编号
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="ssbh" name="ssbh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="ssbh" />" readonly="true">
					</td>
					<td>
						<div align="center">
							寝室电话
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="qsdh" name="qsdh"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="qsdh" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zzmmmc" name="zzmmmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zzmmmc" />"
							readOnly="true">
					</td>
					<td>
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="mzmc" name="mzmc"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="mzmc" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							入学年月
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="rxny" name="rxny"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="rxny" />" readonly="true">
					</td>
					<td>
						<div align="center">
							出生日期
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="csrq" name="csrq"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="csrq" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							卡号
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="kh" name="kh" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="kh" />" readonly="true">
					</td>
					<td>
						<div align="center">
							入学前户口
						</div>
					</td>
					<td colspan="3" align="center">
						<logic:notEmpty name="rs" property="rxqhk">
							<logic:match value="城镇" name="rs" property="rxqhk">
								<input type="radio" name="rxqhk" value="城镇" checked>&nbsp;&nbsp;城镇
							    <input type="radio" name="rxqhk" value="农村">&nbsp;&nbsp;农村
							</logic:match>
							<logic:match value="农村" name="rs" property="rxqhk">
								<input type="radio" name="rxqhk" value="城镇">&nbsp;&nbsp;城镇
							    <input type="radio" name="rxqhk" value="农村" checked>&nbsp;&nbsp;农村
							</logic:match>
						</logic:notEmpty>
						<logic:empty name="rs" property="rxqhk">
							<input type="radio" name="rxqhk" value="城镇" checked>&nbsp;&nbsp;城镇
							<input type="radio" name="rxqhk" value="农村">&nbsp;&nbsp;农村
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td colspan="4" scope="row">
						<div align="left">
							是否愿意参加慈善或志愿活动
						</div>
					</td>
					<td width="12%" align="center">
						<logic:notEmpty name="rs" property="sfyycjcshzyhd">
							<logic:match value="是" name="rs" property="sfyycjcshzyhd">
								<input type="radio" name="sfyycjcshzyhd" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfyycjcshzyhd" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfyycjcshzyhd">
								<input type="radio" name="sfyycjcshzyhd" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfyycjcshzyhd" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:notEmpty>
						<logic:empty name="rs" property="sfyycjcshzyhd">
							<input type="radio" name="sfyycjcshzyhd" value="是" checked>&nbsp;&nbsp;是
							<input type="radio" name="sfyycjcshzyhd" value="否">&nbsp;&nbsp;否
						</logic:empty>
					</td>
					<td colspan="3">
						<div align="left">
							是否愿意申请国家助学贷款或勤工助学
						</div>
					</td>
					<td width="12%" align="center">
						<logic:notEmpty name="rs" property="sfyysqgjzxdkhqgzx">
							<logic:match value="是" name="rs" property="sfyysqgjzxdkhqgzx">
								<input type="radio" name="sfyysqgjzxdkhqgzx" value="是" checked/>&nbsp;&nbsp;是
							    <input type="radio" name="sfyysqgjzxdkhqgzx" value="否"/>&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfyysqgjzxdkhqgzx">
								<input type="radio" name="sfyysqgjzxdkhqgzx" value="是"/>&nbsp;&nbsp;是
							    <input type="radio" name="sfyysqgjzxdkhqgzx" value="否" checked/>&nbsp;&nbsp;否
							</logic:match>
						</logic:notEmpty>
						<logic:empty name="rs" property="sfyysqgjzxdkhqgzx">
							<input type="radio" name="sfyysqgjzxdkhqgzx" value="是" checked>&nbsp;&nbsp;是
							<input type="radio" name="sfyysqgjzxdkhqgzx" value="否">&nbsp;&nbsp;否
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td colspan="9" scope="row">
						<div align="center">
							家庭信息
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭类型
						</div>
					</td>
					<td colspan="7">
						&nbsp;&nbsp;&nbsp;是否健全：
						<logic:notEmpty name="rs" property="sfjq">
							<logic:match value="是" name="rs" property="sfjq">
								<input type="radio" name="sfjq" value="是" checked/>是
							    <input type="radio" name="sfjq" value="否"/>否
							</logic:match>
							<logic:match value="否" name="rs" property="sfjq">
								<input type="radio" name="sfjq" value="是"/>&nbsp;是
							    <input type="radio" name="sfjq" value="否" checked/>否
							</logic:match>
						</logic:notEmpty>
						<logic:empty name="rs" property="sfjq">
							<input type="radio" name="sfjq" value="是" checked/>是
							<input type="radio" name="sfjq" value="否"/>否
						</logic:empty>
						&nbsp;&nbsp; 是否孤儿：
						<logic:notEmpty name="rs" property="sfge">
							<logic:match value="是" name="rs" property="sfge">
								<input type="radio" name="sfge" value="是" checked/>是
							    <input type="radio" name="sfge" value="否"/>否
							</logic:match>
							<logic:match value="否" name="rs" property="sfge">
								<input type="radio" name="sfge" value="是"/>是
							    <input type="radio" name="sfge" value="否" checked/>否
							</logic:match>
						</logic:notEmpty>
						<logic:empty name="rs" property="sfge">
							<input type="radio" name="sfge" value="是"/>是
							<input type="radio" name="sfge" value="否" checked/>否
						</logic:empty>
						&nbsp;&nbsp; 是否单亲：
						<logic:notEmpty name="rs" property="sfdq">
							<logic:match value="是" name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="是" checked>是
							    <input type="radio" name="sfdq" value="否">否
							</logic:match>
							<logic:match value="否" name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="是">是
							    <input type="radio" name="sfdq" value="否" checked>否
							</logic:match>
						</logic:notEmpty>
						<logic:empty name="rs" property="sfdq">
							<input type="radio" name="sfdq" value="是">是
							<input type="radio" name="sfdq" value="否" checked>否
						</logic:empty>
						&nbsp;&nbsp; 是否残疾：
						<logic:notEmpty name="rs" property="sfcj">
							<logic:match value="是" name="rs" property="sfcj">
								<input type="radio" name="sfcj" value="是" checked>是
							    <input type="radio" name="sfcj" value="否">否
							</logic:match>
							<logic:match value="否" name="rs" property="sfcj">
								<input type="radio" name="sfcj" value="是">是
							    <input type="radio" name="sfcj" value="否" checked>否
							</logic:match>
						</logic:notEmpty>
						<logic:empty name="rs" property="sfcj">
							<input type="radio" name="sfcj" value="是">是
							<input type="radio" name="sfcj" value="否" checked>否
						</logic:empty>
						<br />是否军烈属：
						<logic:notEmpty name="rs" property="sfjls">
							<logic:match value="是" name="rs" property="sfjls">
								<input type="radio" name="sfjls" value="是" checked>是
							    <input type="radio" name="sfjls" value="否">否
							</logic:match>
							<logic:match value="否" name="rs" property="sfjls">
								<input type="radio" name="sfjls" value="是">是
							    <input type="radio" name="sfjls" value="否" checked>否
							</logic:match>
						</logic:notEmpty>
						<logic:empty name="rs" property="sfjls">
							<input type="radio" name="sfjls" value="是">是
							<input type="radio" name="sfjls" value="否" checked>否
						</logic:empty>
						&nbsp;&nbsp; 是否离异：
						<logic:notEmpty name="rs" property="sfly">
							<logic:match value="是" name="rs" property="sfly">
								<input type="radio" name="sfly" value="是" checked>是
							    <input type="radio" name="sfly" value="否">否
							</logic:match>
							<logic:match value="否" name="rs" property="sfly">
								<input type="radio" name="sfly" value="是">是
							    <input type="radio" name="sfly" value="否" checked>否
							</logic:match>
						</logic:notEmpty>
						<logic:empty name="rs" property="sfly">
							<input type="radio" name="sfly" value="是">是
							<input type="radio" name="sfly" value="否" checked>否
						</logic:empty>
						&nbsp;&nbsp; 是否重病：
						<logic:notEmpty name="rs" property="sfzb">
							<logic:match value="是" name="rs" property="sfzb">
								<input type="radio" name="sfzb" value="是" checked>是
							    <input type="radio" name="sfzb" value="否">否
							</logic:match>
							<logic:match value="否" name="rs" property="sfzb">
								<input type="radio" name="sfzb" value="是">是
							    <input type="radio" name="sfzb" value="否" checked>否
							</logic:match>
						</logic:notEmpty>
						<logic:empty name="rs" property="sfzb">
							<input type="radio" name="sfzb" value="是">是
							<input type="radio" name="sfzb" value="否" checked>否
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭住址
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtzz" maxlength="100" name="jtzz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzz"/>">
					</td>
					<td>
						<div align="center">
							邮政编码
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="yzbm" maxlength="6" name="yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭联系电话
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtlxdh" maxlength="15" name="jtlxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtlxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							家庭人均<br />年收入
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtrjnsr" maxlength="6" name="jtrjnsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrjnsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6" scope="row">
						<div align="center">
							家
							<br />
							庭
							<br />
							成
							<br />
							员
							<br />
							信
							<br />
							息
						</div>
					</td>
					<td width="12%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="12%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="10%">
						<div align="center">
							与本人
							<br />
							关系
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作(学习)单位
						</div>
					</td>
					<td width="12%">
						<div align="center">
							职业
						</div>
					</td>
					<td width="10%">
						<div align="center">
							年收入
							<br />
							(元)
						</div>
					</td>
					<td width="12%">
						<div align="center">
							健康状况
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_xm" maxlength="40" name="jtcy1_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_nl" maxlength="3" name="jtcy1_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_gx" maxlength="20" name="jtcy1_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy1_gzdw" maxlength="200" name="jtcy1_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_zy" maxlength="20" name="jtcy1_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_nsr" maxlength="8" name="jtcy1_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_jkzk" maxlength="40" name="jtcy1_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_xm" maxlength="40" name="jtcy2_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_nl" maxlength="3" name="jtcy2_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_gx" maxlength="20" name="jtcy2_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy2_gzdw" maxlength="200" name="jtcy2_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_zy" maxlength="20" name="jtcy2_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_nsr" maxlength="8" name="jtcy2_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_jkzk" maxlength="40" name="jtcy2_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_xm" maxlength="40" name="jtcy3_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_nl" maxlength="3" name="jtcy3_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_gx" maxlength="20" name="jtcy3_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy3_gzdw" maxlength="200" name="jtcy3_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_zy" maxlength="20" name="jtcy3_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_nsr" maxlength="8" name="jtcy3_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_jkzk" maxlength="40" name="jtcy3_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_xm" maxlength="40" name="jtcy4_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_nl" maxlength="3" name="jtcy4_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_gx" maxlength="20" name="jtcy4_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy4_gzdw" maxlength="200" name="jtcy4_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_zy" maxlength="20" name="jtcy4_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_nsr" maxlength="8" name="jtcy4_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_jkzk" maxlength="40" name="jtcy4_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_xm" maxlength="40" name="jtcy5_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_nl" maxlength="3" name="jtcy5_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_gx" maxlength="20" name="jtcy5_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy5_gzdw" maxlength="200" name="jtcy5_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_zy" maxlength="20" name="jtcy5_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_nsr" maxlength="8" name="jtcy5_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_jkzk" maxlength="40" name="jtcy5_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							学生在本地<br />受助情况
						</div>
					</td>
					<td colspan="7">
						<input type="text" id="xszbdszqk" maxlength="250" name="xszbdszqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xszbdszqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭遭受<br />自然灾害情况
						</div>
					</td>
					<td colspan="7">
						<input type="text" id="jtzszrzhqk" maxlength=250" name="jtzszrzhqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzszrzhqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭遭受<br />突发意外事件
						</div>
					</td>
					<td colspan="7">
						<input type="text" id="jtzstfywsj" maxlength="250" name="jtzstfywsj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzstfywsj"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							其他情况
						</div>
					</td>
					<td colspan="7">
						<input type="text" id="qtqk" maxlength="250" name="qtqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qtqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							民政部门通讯地址
						</div>
					</td>
					<td colspan="7">
						<input type="text" id="mzbm_txdz" maxlength="150" name="mzbm_txdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_txdz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							民政部门邮政编码
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="mzbm_yzbm" maxlength="6" name="mzbm_yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							民政部门<br />联系电话
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="mzbm_lxdh" maxlength="15" name="mzbm_lxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="9">
						<div align="center">
							申请信息
						</div>
					</td>
				</tr>
				<logic:equal name="isNULL" value="no">
					<%
								String zdyzdXxxx = String.valueOf(request
								.getAttribute("zdyzdXxxx"));
								String zddm = "";
								String zdmc = "";
								String zddx = "";
								String zdlx = "";
								String bxwsz = "";

								String[] temp = zdyzdXxxx.split("!!TwoSpile!!");
								for (int i = 0; i < temp.length; i++) {
									String[] str = temp[i].split("!!OneSpile!!");
									zddm = "zdy" + str[2];
									zdmc = str[3];
									zdlx = str[4];
									zddx = str[5];
									bxwsz = str[6];
					%>
					<tr>
						<td colspan="2">
							<div align="center">
								<%=zdmc%>
							</div>
						</td>
						<td colspan="7">
							<%
							if ("文本框".equalsIgnoreCase(zdlx)) {
							%>
							<html:textarea name="rs" property="<%=zddm%>" rows='5' style="width:100%" onblur="yzdx(this,'<%=zddm%>')"/>
							<input type="hidden" id="<%=zddm%>" name="<%=zddm%>" value="">
							<%
							} else {
							%>
							<%if("是".equalsIgnoreCase(bxwsz)){ %>
							<input type="text" id="<%=zddm%>" name="<%=zddm%>"
								style="width:100%;heigh:100%" maxlength="<%=zddx%>"
								value="<bean:write name='rs' property="<%=zddm%>" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
								>
							<%} else {%>
							<input type="text" id="<%=zddm%>" name="<%=zddm%>"
								style="width:100%;heigh:100%" maxlength="<%=zddx%>"
								value="<bean:write name='rs' property="<%=zddm%>" />">
							<%} %>
							<%
							}
							%>
						</td>
					</tr>
					<%
					}
					%>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="7">
						<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="yz();">
					提交申请
				</button>
				<button type="button" class="button2" onClick="toPrintOut();">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>

		</html:form>
	<%--</logic:equal>
--%></body>
<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         			alert("保存成功！");
	         			if(window.dialogArguments){
			   				dialogArgumentsQueryChick();
			   				Close();
			   			}
	         		</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
</html:html>
