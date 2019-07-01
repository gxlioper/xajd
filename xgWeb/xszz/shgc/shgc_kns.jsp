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
		function yz(titName){
			var xh = document.getElementById('xh').value;
			var mzbm_txdz = document.getElementById('mzbm_txdz').value;
			var mzbm_yzbm = document.getElementById('mzbm_yzbm').value;
			var mzbm_lxdh1 = document.getElementById('mzbm_lxdh1').value;
			var mzbm_lxdh2 = document.getElementById('mzbm_lxdh2').value;
			var syd=document.getElementById('syd').value;
			var lxdh=document.getElementById('lxdh').value;
			var rxqhk=document.getElementById('rxqhk').value;
			var jtzz=document.getElementById('jtzz').value;
			var yzbm=document.getElementById('yzbm').value;
			var jtlxdh1=document.getElementById('jtlxdh1').value;
			var jtlxdh2=document.getElementById('jtlxdh2').value;
			var sfyycjcshzyhd=document.getElementById('sfyycjcshzyhd').value;
			var sfyysqgjzxdkhqgzx=document.getElementById('sfyysqgjzxdkhqgzx').value;
			var sfjq=document.getElementById('sfjq').value;
			var sfge=document.getElementById('sfge').value;
			var sfdq=document.getElementById('sfdq').value;
			var sfcj=document.getElementById('sfcj').value;
			var sfjls=document.getElementById('sfjls').value;
			var sfly=document.getElementById('sfly').value;
			var sfzb=document.getElementById('sfzb').value;
			var jtrjnsr=document.getElementById('jtrjnsr').value;
			var xszbdszqk=document.getElementById('xszbdszqk').value;
			var jtzszrzhqk=document.getElementById('jtzszrzhqk').value;
			var jtzstfywsj=document.getElementById('jtzstfywsj').value;
			var qtqk=document.getElementById('qtqk').value;
			
			if(xh == null || xh == ""){
				alert("学号不能为空!");
				return false;
			}
			if(syd == null || syd== ""){
				alert("生源地不能为空!");
				return false;
			}
			if(lxdh == null || lxdh== ""){
				alert("联系电话不能为空!");
				return false;
			}
			if(rxqhk == null || rxqhk== ""){
				alert("入学前户口不能为空!");
				return false;
			}
			if(jtzz == null || jtzz== ""){
				alert("家庭住址不能为空!");
				return false;
			}
			if(yzbm == null || yzbm== ""){
				alert("邮政编码不能为空!");
				return false;
			}
			if(jtlxdh1 == null || jtlxdh1== ""){
				alert("家庭电话区号不能为空!");
				return false;
			}
			if(jtlxdh2 == null || jtlxdh2== ""){
				alert("家庭电话不能为空!");
				return false;
			}
			if(sfyycjcshzyhd == null || sfyycjcshzyhd== ""){
				alert("是否愿意参加慈善或志愿活动不能为空!");
				return false;
			}
			if(sfyysqgjzxdkhqgzx == null || sfyysqgjzxdkhqgzx== ""){
				alert("是否愿意申请国家助学贷款或勤工助学不能为空!");
				return false;
			}
			if(sfjq == null || sfjq== ""){
				alert("是否健全不能为空!");
				return false;
			}
			if(sfge == null || sfge== ""){
				alert("是否孤儿不能为空!");
				return false;
			}
			if(sfdq == null || sfdq== ""){
				alert("是否单亲不能为空!");
				return false;
			}
			if(sfcj == null || sfcj== ""){
				alert("是否残疾不能为空!");
				return false;
			}
			if(sfjls == null || sfjls== ""){
				alert("是否军烈属不能为空!");
				return false;
			}
			if(sfly == null || sfly== ""){
				alert("是否离异不能为空!");
				return false;
			}
			if(sfzb == null || sfzb== ""){
				alert("是否重病不能为空!");
				return false;
			}
			if(jtrjnsr == null || jtrjnsr== ""){
				alert("家庭人均年收入不能为空!");
				return false;
			}
			if(xszbdszqk == null || xszbdszqk== ""){
				alert("学生在本地受助情况不能为空!");
				return false;
			}
			if(jtzszrzhqk == null || jtzszrzhqk== ""){
				alert("家庭遭受自然灾害情况不能为空!");
				return false;
			}
			if(jtzstfywsj == null || jtzstfywsj== ""){
				alert("家庭遭受突发意外事件不能为空!");
				return false;
			}
			if(qtqk == null || qtqk== ""){
				alert("其他情况不能为空!");
				return false;
			}
			if(mzbm_txdz == null || mzbm_txdz == ""){
				alert("民政部门通讯地址不能为空!");
				return false;
			}
			if(mzbm_yzbm == null || mzbm_yzbm == ""){
				alert("民政部门邮政编码不能为空!");
				return false;
			}
			if(mzbm_lxdh1 == null || mzbm_lxdh1 == ""){
				alert("民政部门联系电话区号不能为空!");
				return false;
			}
			if(mzbm_lxdh2 == null || mzbm_lxdh2 == ""){
				alert("民政部门联系电话不能为空!");
				return false;
			}
			document.forms[0].action = "/xgxt/shgc_kns.do?doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		
		function toPrintOut(titName){//输出相应的打印页面
			document.forms[0].action = "/xgxt/shgc_knsb.do";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 家庭经济困难学生认定申请表
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间！
			</p>
		</center>
	</logic:equal>
	<html:form action="shgc_kns.do" method="post">
		<input type="hidden" id="titName" name="titName"
			value="<bean:write name="titName" scope="request" />">
		<input type="hidden" id="url" name="url" value="/shgc_kns.do" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc" />
		<input type="hidden" id="xysh" name="xysh"
			value="<bean:write name="rs" property="xysh"/>">
		<input type="hidden" id="xxsh" name="xxsh"
			value="<bean:write name="rs" property="xxsh"/>">
		<input type="hidden" id="xxshyj" name="xxshyj"
			value="<bean:write name="rs" property="xxshyj"/>">
		<input type="hidden" id="xyshyj" name="xyshyj"
			value="<bean:write name="rs" property="xyshyj"/>">

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
					<td align="left" colspan="3">
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
					<td align="left" colspan="3">
						<input type="text" id="xh" name="xh" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xh" />" readonly="true">
					</td>
				</logic:equal>
				<td colspan="2">
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
				<td colspan="3">
					<input type="text" id="xb" readonly="readonly" name="xb"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xb"/>">
				</td>
				<td colspan="2">
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
						民族
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="mzmc" readonly="readonly" name="mzmc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="mzmc"/>">
				</td>
				<td colspan="2">
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
				<td colspan="2">
					<div align="center">
						系别
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="xymc" name="xymc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xymc"/>">
				</td>
				<td colspan="2">
					<div align="center">
						专业
					</div>
				</td>
				<td>
					<input type="text" id="zymc" readonly="readonly" name="zymc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zymc"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						班级
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="bjmc" readonly="readonly" name="bjmc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="bjmc"/>">
				</td>
				<td colspan="2">
					<div align="center">
						年级
					</div>
				</td>
				<td>
					<input type="text" id="nj" readonly="readonly" name="nj"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="nj"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>生源地
					</div>
				</td>
				<td colspan="3">
					<html:select name="rs" property="syd">
						<html:options collection="sydList" property="syd"
							labelProperty="syd" />
					</html:select>
				</td>
				<td colspan="3">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>联系电话
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="lxdh" maxlength="15" name="lxdh"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxdh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>入学前户口
					</div>
				</td>
				<td align="center">
					<logic:present name="rs" property="rxqhk">
						<logic:match value="城镇" name="rs" property="rxqhk">
							<input type="radio" name="rxqhk" value="城镇" checked>&nbsp;&nbsp;城镇
							    <input type="radio" name="rxqhk" value="农村">&nbsp;&nbsp;农村
							</logic:match>
						<logic:match value="农村" name="rs" property="rxqhk">
							<input type="radio" name="rxqhk" value="城镇">&nbsp;&nbsp;城镇
							    <input type="radio" name="rxqhk" value="农村" checked>&nbsp;&nbsp;农村
							</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="rxqhk">
						<input type="radio" name="rxqhk" value="城镇" checked>&nbsp;&nbsp;城镇
							<input type="radio" name="rxqhk" value="农村">&nbsp;&nbsp;农村
						</logic:notPresent>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>家庭住址
					</div>
				</td>
				<td colspan="6">
					<input type="text" id="jtzz" maxlength="100" name="jtzz"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtzz"/>">
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
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>联系电话
					</div>
				</td>
				<td>
					<input type="text" id="jtlxdh1" maxlength="4" name="jtlxdh1"
						style="width:20%;heigh:100%"
						value="<bean:write name="rs" property="jtlxdh1"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					(区号)-
					<input type="text" id="jtlxdh2" maxlength="11" name="jtlxdh2"
						style="width:60%;heigh:100%"
						value="<bean:write name="rs" property="jtlxdh2"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>是否愿意参加
						<br />
						慈善或志愿活动
					</div>
				</td>
				<td colspan="3" align="center">
					<logic:present name="rs" property="sfyycjcshzyhd">
						<logic:match value="是" name="rs" property="sfyycjcshzyhd">
							<input type="radio" name="sfyycjcshzyhd" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfyycjcshzyhd" value="否">&nbsp;&nbsp;否
							</logic:match>
						<logic:match value="否" name="rs" property="sfyycjcshzyhd">
							<input type="radio" name="sfyycjcshzyhd" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfyycjcshzyhd" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="sfyycjcshzyhd">
						<input type="radio" name="sfyycjcshzyhd" value="是" checked>&nbsp;&nbsp;是
							<input type="radio" name="sfyycjcshzyhd" value="否">&nbsp;&nbsp;否
						</logic:notPresent>
				</td>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>是否愿意申请国家
						<br />
						助学贷款或勤工助学
					</div>
				</td>
				<td align="center">
					<logic:present name="rs" property="sfyysqgjzxdkhqgzx">
						<logic:match value="是" name="rs" property="sfyysqgjzxdkhqgzx">
							<input type="radio" name="sfyysqgjzxdkhqgzx" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfyysqgjzxdkhqgzx" value="否">&nbsp;&nbsp;否
							</logic:match>
						<logic:match value="否" name="rs" property="sfyysqgjzxdkhqgzx">
							<input type="radio" name="sfyysqgjzxdkhqgzx" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfyysqgjzxdkhqgzx" value="否"
								checked>&nbsp;&nbsp;否
							</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="sfyysqgjzxdkhqgzx">
						<input type="radio" name="sfyysqgjzxdkhqgzx" value="是" checked>&nbsp;&nbsp;是
							<input type="radio" name="sfyysqgjzxdkhqgzx" value="否">&nbsp;&nbsp;否
						</logic:notPresent>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="center">
						家庭类型
					</div>
					<div align="left">
						<font color="#ff0000">
							注：1.单亲指一方去世；2.离异家庭注明对方抚养情况；3.孤儿写明监护人的情况及收入和民政补贴； <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.军烈属及优抚家庭需提供相应证明；5.残疾及重病家庭需提供县级以上医院证明
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>是否健全
					</div>
				</td>
				<td align="center" colspan="3">
					<logic:present name="rs" property="sfjq">
						<logic:match value="是" name="rs" property="sfjq">
							<input type="radio" name="sfjq" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfjq" value="否">&nbsp;&nbsp;否
							</logic:match>
						<logic:match value="否" name="rs" property="sfjq">
							<input type="radio" name="sfjq" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfjq" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="sfjq">
						<input type="radio" name="sfjq" value="是" checked>&nbsp;&nbsp;是
							<input type="radio" name="sfjq" value="否">&nbsp;&nbsp;否
						</logic:notPresent>
				</td>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>是否孤儿
					</div>
				</td>
				<td align="center">
					<logic:present name="rs" property="sfge">
						<logic:match value="是" name="rs" property="sfge">
							<input type="radio" name="sfge" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfge" value="否">&nbsp;&nbsp;否
							</logic:match>
						<logic:match value="否" name="rs" property="sfge">
							<input type="radio" name="sfge" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfge" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="sfge">
						<input type="radio" name="sfge" value="是">&nbsp;&nbsp;是
							<input type="radio" name="sfge" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>是否单亲
					</div>
				</td>
				<td align="center" colspan="3">
					<logic:present name="rs" property="sfdq">
						<logic:match value="是" name="rs" property="sfdq">
							<input type="radio" name="sfdq" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfdq" value="否">&nbsp;&nbsp;否
							</logic:match>
						<logic:match value="否" name="rs" property="sfdq">
							<input type="radio" name="sfdq" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfdq" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="sfdq">
						<input type="radio" name="sfdq" value="是">&nbsp;&nbsp;是
							<input type="radio" name="sfdq" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
				</td>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>是否残疾
					</div>
				</td>
				<td align="center">
					<logic:present name="rs" property="sfcj">
						<logic:match value="是" name="rs" property="sfcj">
							<input type="radio" name="sfcj" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfcj" value="否">&nbsp;&nbsp;否
							</logic:match>
						<logic:match value="否" name="rs" property="sfcj">
							<input type="radio" name="sfcj" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfcj" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="sfcj">
						<input type="radio" name="sfcj" value="是">&nbsp;&nbsp;是
							<input type="radio" name="sfcj" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>是否军烈属
					</div>
				</td>
				<td align="center" colspan="3">
					<logic:present name="rs" property="sfjls">
						<logic:match value="是" name="rs" property="sfjls">
							<input type="radio" name="sfjls" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfjls" value="否">&nbsp;&nbsp;否
							</logic:match>
						<logic:match value="否" name="rs" property="sfjls">
							<input type="radio" name="sfjls" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfjls" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="sfjls">
						<input type="radio" name="sfjls" value="是">&nbsp;&nbsp;是
							<input type="radio" name="sfjls" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
				</td>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>是否离异
					</div>
				</td>
				<td align="center">
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
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>是否重病
					</div>
				</td>
				<td align="center" colspan="3">
					<logic:present name="rs" property="sfzb">
						<logic:match value="是" name="rs" property="sfzb">
							<input type="radio" name="sfzb" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfzb" value="否">&nbsp;&nbsp;否
							</logic:match>
						<logic:match value="否" name="rs" property="sfzb">
							<input type="radio" name="sfzb" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfzb" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="sfzb">
						<input type="radio" name="sfzb" value="是">&nbsp;&nbsp;是
							<input type="radio" name="sfzb" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
				</td>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>家庭人均年收入(元)
					</div>
				</td>
				<td>
					<input type="text" id="jtrjnsr" maxlength="6" name="jtrjnsr"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtrjnsr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td rowspan="6" width="4%">
					<div align="center">
						家庭成员情况
					</div>
				</td>
				<td width="12%" align="center">
					姓名
				</td>
				<td width="10%" align="center">
					年龄
				</td>
				<td width="12%" align="center">
					与学生
					<br />
					关系
				</td>
				<td width="12%" align="center">
					职业
				</td>
				<td width="8%" align="center">
					年收入
					<br />
					(元)
				</td>
				<td width="8%" align="center">
					健康状况
				</td>
				<td align="center">
					工作(学习)单位
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="text" id="jtcy1_xm" maxlength="40" name="jtcy1_xm"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy1_xm"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy1_nl" maxlength="3" name="jtcy1_nl"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy1_nl"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td align="center">
					<input type="text" id="jtcy1_gx" maxlength="20" name="jtcy1_gx"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy1_gx"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy1_zy" maxlength="20" name="jtcy1_zy"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy1_zy"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy1_nsr" maxlength="8" name="jtcy1_nsr"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy1_nsr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td align="center">
					<input type="text" id="jtcy1_jkzk" maxlength="40" name="jtcy1_jkzk"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy1_jkzk"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy1_gzdw" maxlength="200"
						name="jtcy1_gzdw" style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy1_gzdw"/>">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="text" id="jtcy2_xm" maxlength="40" name="jtcy2_xm"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy2_xm"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy2_nl" maxlength="3" name="jtcy2_nl"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy2_nl"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td align="center">
					<input type="text" id="jtcy2_gx" maxlength="20" name="jtcy2_gx"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy2_gx"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy2_zy" maxlength="20" name="jtcy2_zy"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy2_zy"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy2_nsr" maxlength="8" name="jtcy2_nsr"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy2_nsr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td align="center">
					<input type="text" id="jtcy2_jkzk" maxlength="40" name="jtcy2_jkzk"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy2_jkzk"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy2_gzdw" maxlength="200"
						name="jtcy2_gzdw" style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy2_gzdw"/>">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="text" id="jtcy3_xm" maxlength="40" name="jtcy3_xm"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy3_xm"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy3_nl" maxlength="3" name="jtcy3_nl"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy3_nl"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td align="center">
					<input type="text" id="jtcy3_gx" maxlength="20" name="jtcy3_gx"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy3_gx"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy3_zy" maxlength="20" name="jtcy3_zy"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy3_zy"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy3_nsr" maxlength="8" name="jtcy3_nsr"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy3_nsr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td align="center">
					<input type="text" id="jtcy3_jkzk" maxlength="40" name="jtcy3_jkzk"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy3_jkzk"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy3_gzdw" maxlength="200"
						name="jtcy3_gzdw" style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy3_gzdw"/>">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="text" id="jtcy4_xm" maxlength="40" name="jtcy4_xm"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy4_xm"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy4_nl" maxlength="3" name="jtcy4_nl"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy4_nl"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td align="center">
					<input type="text" id="jtcy4_gx" maxlength="20" name="jtcy4_gx"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy4_gx"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy4_zy" maxlength="20" name="jtcy4_zy"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy4_zy"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy4_nsr" maxlength="8" name="jtcy4_nsr"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy4_nsr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td align="center">
					<input type="text" id="jtcy4_jkzk" maxlength="40" name="jtcy4_jkzk"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy4_jkzk"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy4_gzdw" maxlength="200"
						name="jtcy4_gzdw" style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy4_gzdw"/>">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="text" id="jtcy5_xm" maxlength="40" name="jtcy5_xm"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy5_xm"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy5_nl" maxlength="3" name="jtcy5_nl"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy5_nl"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td align="center">
					<input type="text" id="jtcy5_gx" maxlength="20" name="jtcy5_gx"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy5_gx"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy5_zy" maxlength="20" name="jtcy5_zy"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy5_zy"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy5_nsr" maxlength="8" name="jtcy5_nsr"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy5_nsr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td align="center">
					<input type="text" id="jtcy5_jkzk" maxlength="40" name="jtcy5_jkzk"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy5_jkzk"/>">
				</td>
				<td align="center">
					<input type="text" id="jtcy5_gzdw" maxlength="200"
						name="jtcy5_gzdw" style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy5_gzdw"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>学生在本地
						<br />
						受助情况
					</div>
				</td>
				<td colspan="6">
					<input type="text" id="xszbdszqk" maxlength="150" name="xszbdszqk"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xszbdszqk"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>家庭遭受
						<br />
						自然灾害情况
					</div>
				</td>
				<td colspan="6">
					<input type="text" id="jtzszrzhqk" maxlength="150"
						name="jtzszrzhqk" style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtzszrzhqk"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>家庭遭受
						<br />
						突发意外事件
					</div>
				</td>
				<td colspan="6">
					<input type="text" id="jtzstfywsj" maxlength="150"
						name="jtzstfywsj" style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtzstfywsj"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>其他情况
					</div>
				</td>
				<td colspan="6">
					<input type="text" id="qtqk" maxlength="150" name="qtqk"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="qtqk"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>民政部门
						<br />
						详细通讯地址
					</div>
				</td>
				<td colspan="6">
					<input type="text" id="mzbm_txdz" maxlength="150" name="mzbm_txdz"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="mzbm_txdz"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>民政部门邮政编码
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="mzbm_yzbm" maxlength="6" name="mzbm_yzbm"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="mzbm_yzbm"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="2">
					<div align="center">
						<font color="red">*</font>民政部门联系电话
					</div>
				</td>
				<td>
					<input type="text" id="mzbm_lxdh1" maxlength="4" name="mzbm_lxdh1"
						style="width:20%;heigh:100%"
						value="<bean:write name="rs" property="mzbm_lxdh1"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					(区号)-
					<input type="text" id="mzbm_lxdh2" maxlength="11" name="mzbm_lxdh2"
						style="width:60%;heigh:100%"
						value="<bean:write name="rs" property="mzbm_lxdh2"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
		</table>
		<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2"
					onClick="yz(document.getElementById('titName').value);">
					提交申请
				</button>
				<button type="button" class="button2"
					onClick="toPrintOut(document.getElementById('titName').value);">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
		</logic:equal>
	</html:form>
</body>
</html:html>
