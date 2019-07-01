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
	<script language="javascript">
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
		function yz(titName){
			var xh = document.getElementById('xh').value;
			var sfzh = document.getElementById('sfzh').value;
			var jtrks = document.getElementById('jtrks').value;
			var jtyzbm = document.getElementById('jtyzbm').value;
			var jtlxdh = document.getElementById('jtlxdh').value;
			var jtcy1_nl = document.getElementById('jtcy1_nl').value;
			var jtcy1_nsr = document.getElementById('jtcy1_nsr').value;
			var jtcy2_nl = document.getElementById('jtcy2_nl').value;
			var jtcy2_nsr = document.getElementById('jtcy2_nsr').value;
			var jtcy3_nl = document.getElementById('jtcy3_nl').value;
			var jtcy3_nsr = document.getElementById('jtcy3_nsr').value;
			var jtcy4_nl = document.getElementById('jtcy4_nl').value;
			var jtcy4_nsr = document.getElementById('jtcy4_nsr').value;
			var jtcy5_nl = document.getElementById('jtcy5_nl').value;
			var jtcy5_nsr = document.getElementById('jtcy5_nsr').value;
			var jtrjnsr = document.getElementById('jtrjnsr').value;
			var mzbmyzbm = document.getElementById('mzbmyzbm').value;
			var mzbmlxdh = document.getElementById('mzbmlxdh').value;
			
			if(xh == null){
				alert("学号不能为空!");
				return false;
			}
			if(!checkSfzh(sfzh)){
				return false;
			}
			if((jtrks != "") && (!isNumber(jtrks))){
				alert("家庭人口数必须为整数!");
				return false;
			}
			if((jtyzbm != "") && (!isNumber(jtyzbm))){
				alert("家庭邮政编码必须为整数!");
				return false;
			}
			if((jtlxdh != "") && (!isNumber(jtlxdh))){
				alert("家庭联系电话必须为整数!");
				return false;
			}
			if((jtcy1_nl != "") && (!isNumber(jtcy1_nl))){
				alert("家庭成员1年龄必须为整数!");
				return false;
			}
			if((jtcy1_nsr != "") && (!isNumber(jtcy1_nsr))){
				alert("家庭成员1年收入必须为整数!");
				return false;
			}
			if((jtcy2_nl != "") && (!isNumber(jtcy2_nl))){
				alert("家庭成员2年龄必须为整数!");
				return false;
			}
			if((jtcy2_nsr != "") && (!isNumber(jtcy2_nsr))){
				alert("家庭成员2年收入必须为整数!");
				return false;
			}
			if((jtcy3_nl != "") && (!isNumber(jtcy3_nl))){
				alert("家庭成员3年龄必须为整数!");
				return false;
			}
			if((jtcy3_nsr != "") && (!isNumber(jtcy3_nsr))){
				alert("家庭成员3年收入必须为整数!");
				return false;
			}
			if((jtcy4_nl != "") && (!isNumber(jtcy4_nl))){
				alert("家庭成员4年龄必须为整数!");
				return false;
			}
			if((jtcy4_nsr != "") && (!isNumber(jtcy4_nsr))){
				alert("家庭成员4年收入必须为整数!");
				return false;
			}
			if((jtcy5_nl != "") && (!isNumber(jtcy5_nl))){
				alert("家庭成员5年龄必须为整数!");
				return false;
			}
			if((jtcy5_nsr != "") && (!isNumber(jtcy5_nsr))){
				alert("家庭成员5年收入必须为整数!");
				return false;
			}
			if((jtrjnsr != "") && (!isNumber(jtrjnsr))){
				alert("家庭人均年收入必须为整数!");
				return false;
			}
			if((mzbmyzbm != "") && (!isNumber(mzbmyzbm))){
				alert("民政部门邮政编码必须为整数!");
				return false;
			}
			if((mzbmlxdh != "") && (!isNumber(mzbmlxdh))){
				alert("民政部门联系电话必须为整数!");
				return false;
			}
			document.forms[0].action = "/xgxt/xnmz_knssq.do?doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		
		function checkSfzh(sfzh) {
   			sfzh=sfzh.toLowerCase()
			var OldID;
			if(sfzh.length == 15){
				OldID = sfzh;
				return true;
			}else if(sfzh.length == 18){
				OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
			}else{
				alert("请输入正确的身份证号码！","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
			var A = new Array("1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2");
			var i, j, S;
			var NewID, ID, strNF;
			NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
			S = 0;
			for( i = 0; i <= 16; i++){
				j = NewID.substring(i, i+1) * W[i];
				S = S + j;
			}
			S = S % 11;
			if(sfzh != NewID + A[S]){
				alert("请输入正确的身份证号码！","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			return true;
		}
		
	</script>
</head>

<body onload="loadPage()">
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 困难生申请
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="xnmz_knssq.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url"
				value="/xnmz_knssq.do?jxjlbType=lstdsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
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
			<div class="xxk">
				<logic:notEmpty name="pages">
					<logic:iterate id="card" name="pages" scope="request">
						<ul>
							<li id="<bean:write name='card' property='en'/>l"
								class="xxk_off_l"></li>
							<li id="<bean:write name='card' property='en'/>m" onclick=""
								class="xxk_off_m">
								&nbsp;
								<bean:write name='card' property='cn' />
								&nbsp;
							</li>
							<li id="<bean:write name='card' property='en'/>r"
								class="xxk_off_r"></li>
						</ul>
					</logic:iterate>
				</logic:notEmpty>
			</div>

				<table class="tbstyle">
					<tr>
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right" colspan="3">
								<font color="red">*</font>学号：
							</td>
							<td align="left" colspan="2">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true"/>
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right" colspan="3">
								<font color="red">*</font>学号：
							</td>
							<td align="left" colspan="2">
								<input type="text" id="xh" name="xh"
									style="width:100%;heigh:100%"
									value="<bean:write name='rs' property="xh" />" readonly="true">
							</td>
						</logic:equal>
						<td width="16%">
							<div align="right">
								姓名：
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="xm" name="xm"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xm"/>"
								readonly="readonly">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								学校名称：
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="xxmc" name="xxmc" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xxmc"/>">
						</td>
						<td>
							<div align="right">
								<bean:message key="lable.xsgzyxpzxy" />名称：
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="xymc" readonly="readonly" name="xymc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xymc"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								专业名称：
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="xmc" name="xmc" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xmc"/>">
						</td>
						<td>
							<div align="right">
								年级：
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="nj" readonly="readonly" name="nj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="nj"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								性别：
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="xb" name="xb" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xb"/>">
						</td>
						<td>
							<div align="right">
								出生年月：
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('csny','y-mm');"
								value="<bean:write name='rs' property="csny" />" name="csny"
								id="csny" />
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								民族名称：
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="mzmc" name="mzmc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name="rs" property="mzmc"/>">
						</td>
						<td>
							<div align="right">
								身份证号：
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="sfzh" maxlength="18" name="sfzh"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="sfzh"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								政治面貌：
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="zzmm" name="zzmm" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zzmm"/>">
						</td>
						<td>
							<div align="right">
								入学前户口：
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="rxqhk" name="rxqhk" maxlength="40"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="rxqhk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								毕业学校：
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="byxx" name="byxx" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="byxx"/>">
						</td>
						<td>
							<div align="right">
								个人特长：
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="grtc" name="grtc" maxlength="60"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="grtc"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								是否孤残：
							</div>
						</td>
						<td colspan="2" align="left">
							<logic:present name="rs" property="sfgc">
								<logic:match value="是" name="rs" property="sfgc">
									<input type="radio" name="sfgc" value="是" checked>&nbsp;&nbsp;是
							         	<input type="radio" name="sfgc" value="否">&nbsp;&nbsp;否
							         </logic:match>
								<logic:match value="否" name="rs" property="sfgc">
									<input type="radio" name="sfgc" value="是">&nbsp;&nbsp;是
							         	<input type="radio" name="sfgc" value="否" checked>&nbsp;&nbsp;否
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="sfgc">
								<input type="radio" name="sfgc" value="是">&nbsp;&nbsp;是
							         <input type="radio" name="sfgc" value="否" checked>&nbsp;&nbsp;否
						         </logic:notPresent>
						</td>
						<td>
							<div align="right">
								是否单亲：
							</div>
						</td>
						<td colspan="3" align="left">
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
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								是否烈士子女：
							</div>
						</td>
						<td colspan="2" align="left">
							<logic:present name="rs" property="sflszn">
								<logic:match value="是" name="rs" property="sflszn">
									<input type="radio" name="sflszn" value="是" checked>&nbsp;&nbsp;是
							         	<input type="radio" name="sflszn" value="否">&nbsp;&nbsp;否
							         </logic:match>
								<logic:match value="否" name="rs" property="sflszn">
									<input type="radio" name="sflszn" value="是">&nbsp;&nbsp;是
							         	<input type="radio" name="sflszn" value="否" checked>&nbsp;&nbsp;否
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="sflszn">
								<input type="radio" name="sflszn" value="是">&nbsp;&nbsp;是
							         <input type="radio" name="sflszn" value="否" checked>&nbsp;&nbsp;否
						         </logic:notPresent>
						</td>
						<td>
							<div align="right">
								家庭人口数：
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="jtrks" name="jtrks" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtrks"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								家庭邮政编码：
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="jtyzbm" name="jtyzbm" maxlength="6"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtyzbm"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td>
							<div align="right">
								家庭联系电话：
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="jtlxdh" name="jtlxdh" maxlength="15"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtlxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								家庭详细通讯地址：
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="jtxxtxdz" name="jtxxtxdz" maxlength="100"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtxxtxdz"/>">
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
						<td width="8%">
							<div align="center">
								姓名
							</div>
						</td>
						<td width="8%">
							<div align="center">
								年龄
							</div>
						</td>
						<td width="9%">
							<div align="center">
								与学生关系
							</div>
						</td>
						<td colspan="2">
							<div align="center">
								工作或学习单位
							</div>
						</td>
						<td width="12%">
							<div align="center">
								职业
							</div>
						</td>
						<td width="11%">
							<div align="center">
								年收入(元)
							</div>
						</td>
						<td width="11%">
							<div align="center">
								健康状况
							</div>
						</td>
					</tr>
					<tr>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy1_xm" name="jtcy1_xm" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_xm"/>">
							</div>
						</td>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="5"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_nl"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy1_gx" name="jtcy1_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_gx"/>">
							</div>
						</td>
						<td colspan="2">
							<div align="center">
								<input type="text" id="jtcy1_gzdw" name="jtcy1_gzdw"
									maxlength="100" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_gzdw"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy1_zy" name="jtcy1_zy" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_zy"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy1_nsr" name="jtcy1_nsr"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_nsr"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy1_jkzk" name="jtcy1_jkzk"
									maxlength="20" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_jkzk"/>">
							</div>
						</td>
					</tr>
					<tr>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy2_xm" name="jtcy2_xm" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_xm"/>">
							</div>
						</td>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="5"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_nl"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy2_gx" name="jtcy2_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_gx"/>">
							</div>
						</td>
						<td colspan="2">
							<div align="center">
								<input type="text" id="jtcy2_gzdw" name="jtcy2_gzdw"
									maxlength="100" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_gzdw"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy2_zy" name="jtcy2_zy" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_zy"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy2_nsr" name="jtcy2_nsr"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_nsr"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy2_jkzk" name="jtcy2_jkzk"
									maxlength="20" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_jkzk"/>">
							</div>
						</td>
					</tr>
					<tr>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy3_xm" name="jtcy3_xm" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_xm"/>">
							</div>
						</td>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="5"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_nl"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy3_gx" name="jtcy3_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_gx"/>">
							</div>
						</td>
						<td colspan="2">
							<div align="center">
								<input type="text" id="jtcy3_gzdw" name="jtcy3_gzdw"
									maxlength="100" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_gzdw"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy3_zy" name="jtcy3_zy" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_zy"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy3_nsr" name="jtcy3_nsr"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_nsr"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy3_jkzk" name="jtcy3_jkzk"
									maxlength="20" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_jkzk"/>">
							</div>
						</td>
					</tr>
					<tr>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy4_xm" name="jtcy4_xm" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_xm"/>">
							</div>
						</td>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="5"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_nl"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy4_gx" name="jtcy4_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_gx"/>">
							</div>
						</td>
						<td colspan="2">
							<div align="center">
								<input type="text" id="jtcy4_gzdw" name="jtcy4_gzdw"
									maxlength="100" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_gzdw"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy4_zy" name="jtcy4_zy" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_zy"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy4_nsr" name="jtcy4_nsr"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_nsr"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy4_jkzk" name="jtcy4_jkzk"
									maxlength="20" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_jkzk"/>">
							</div>
						</td>
					</tr>
					<tr>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy5_xm" name="jtcy5_xm" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_xm"/>">
							</div>
						</td>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="5"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_nl"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy5_gx" name="jtcy5_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_gx"/>">
							</div>
						</td>
						<td colspan="2">
							<div align="center">
								<input type="text" id="jtcy5_gzdw" name="jtcy5_gzdw"
									maxlength="100" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_gzdw"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy5_zy" name="jtcy5_zy" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_zy"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy5_nsr" name="jtcy5_nsr"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_nsr"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy5_jkzk" name="jtcy5_jkzk"
									maxlength="20" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_jkzk"/>">
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								家庭人均年收入：
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="jtrjnsr" name="jtrjnsr" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtrjnsr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td>
							<div align="right">
								家庭欠债情况：
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="jtqzqk" name="jtqzqk" maxlength="100"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtqzqk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								学生已获资助情况：
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="xsyhhjqk" name="xsyhhjqk" maxlength="200"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xsyhhjqk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								家庭遭受自然灾害情况：
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="jtzszrzhqk" name="jtzszrzhqk"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtzszrzhqk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								家庭遭受突发以外事件：
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="jtzstfywsj" name="jtzstfywsj"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtzstfywsj"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								家庭成员因残疾、年迈而劳动力弱情况：
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="jtcyndlrqk" name="jtcyndlrqk"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcyndlrqk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								家庭成员失业情况：
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="jtcysyqk" name="jtcysyqk" maxlength="200"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcysyqk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								其他情况：
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="qtqk" name="qtqk" maxlength="200"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="qtqk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								民政部门邮政编码：
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="mzbmyzbm" name="mzbmyzbm" maxlength="6"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="mzbmyzbm"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td>
							<div align="right">
								民政部门联系电话：
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="mzbmlxdh" name="mzbmlxdh" maxlength="15"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="mzbmlxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								民政部门详细通讯地址：
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="mzbmxxtxdz" name="mzbmxxtxdz"
								maxlength="100" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="mzbmxxtxdz"/>">
						</td>
					</tr>
				</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
					<button class="button2"
						onClick="yz(document.getElementById('titName').value);">
						提交申请
					</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
