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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
		function sumTime(){
			var sum=0;
			var tem=0;
			var mxsbc=document.forms[0].mxsbc.value;
			for(var i=1;i<32;i++){
				tem=document.getElementById("sj"+i).value;
				sum=sum+tem*1;
			}
			document.getElementById("ljxs").innerText=sum;
			document.forms[0].ysj.value = sum;
			document.getElementById("zq").innerText=sum*mxsbc
			document.forms[0].yje.value = sum*mxsbc;
		}
		
   		function sumpd(){
			var sum=document.forms[0].ysj.value;
			var myzgxs=document.forms[0].myzgxs.value;
			var xxdm = document.getElementById("xxdm").value;
			if(sum>myzgxs){
				alert("总工作时间超过每月最大工作时间!");
				return false;
			}
			if(xxdm=="10100"){
				//廊坊师范
				var kshour =parseInt(document.getElementById("kshour").value);
				var jshour =parseInt(document.getElementById("jshour").value);
				var jsminute =parseInt(document.getElementById("jsminute").value);
				var ksminute =parseInt(document.getElementById("ksminute").value);
				if(kshour=="" || ksminute==""){
					alert("工作开始时间为空!");
					return false;
				}
				if(jshour=="" || jsminute==""){
					alert("工作结束时间为空!");
					return false;
				}
				if(kshour<0 ||kshour>23){
					alert("工作开始小时不正确！");
					return false;
				}
				if(jshour<0 ||jshour>23){
					alert("工作结束小时不正确！");
					return false;
				}
				if(ksminute<0 || ksminute>60){
					alert("工作开始分钟不正确!");
					return false;
				}
				if(jsminute<0 || jsminute>60){
					alert("工作结束分钟不正确!");
					return false;
				}				
			}
			return true;
		}
		
		function initValue(){
			 var kssj = document.getElementById("kssj").value;
			 var jssj = document.getElementById("jssj").value;
			 if(kssj!=null && kssj!=""){
			 	 document.getElementById("kshour").value = kssj.substring(0,kssj.indexOf('点'));
			 	 document.getElementById("ksminute").value = kssj.substring(kssj.indexOf('点')+1,jssj.indexOf('分'));
			 	 document.getElementById("jshour").value = jssj.substring(0,jssj.indexOf('点'));
			 	 document.getElementById("jsminute").value = jssj.substring(jssj.indexOf('点')+1,jssj.indexOf('分'));
			 }
		}
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body
		onload="checkWinType();document.all('buttonClose').focus();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 考核 - 学生工作记录 - 单个学生工作情况记录
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="rs" property="xh||gwdm||sqsj"/>" />
			<input type="hidden" name="doType" value="<bean:write name="doType"/>" />
			<input type="hidden" name="xh" value="<bean:write name="rs" property="xh" />" />
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm" scope="request"/>" />
			<input type="hidden" id="ysj" name="ysj" value="" />
			<input type="hidden" id="yje" name="yje" value="" />
			<table width="100%" align="center" class="tbstyle" id="tb">
				<tr>
					<td>
						<table width="100%" align="center" class="tbstyle">
							<thead>
								<tr style="height:22px">
									<td colspan="4" align="center">
										单个学生工作情况记录
									</td>
								</tr>
							</thead>
							<tr style="height:22px">
								<td align="right">
									学号：
								</td>
								<td align="left">
									<input type="hidden" id="xh" name="xh"
										value="<bean:write name="rs" property="xh" />" />
									<bean:write name="rs" property="xh" />
								</td>
								<td align="right">
									年度：
								</td>
								<td align="left">
									<bean:write name="rs" property="nd" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									姓名：
								</td>
								<td align="left">
									<bean:write name="rs" property="xm" />
								</td>
								<td align="right">
									学年：
								</td>
								<td align="left">
									<bean:write name="rs" property="xn" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									性别：
								</td>
								<td align="left">
									<bean:write name="rs" property="xb" />
								</td>
								<td align="right">
									岗位名称：
								</td>
								<td align="left">
									<bean:write name="rs" property="gwdm" />
									<input type="hidden" id="gwmc" name="gwmc" value="${rs.gwdm}" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									年级：
								</td>
								<td align="left">
									<bean:write name="rs" property="nj" />
								</td>
								<td align="right">
									申请时间：
								</td>
								<td align="left">
									<bean:write name="rs" property="sqsj" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />：
								</td>
								<td align="left">
									<bean:write name="rs" property="xymc" />
								</td>
								<td align="right">
									是否困难生：
								</td>
								<td align="left">
									<bean:write name="rs" property="sfpks" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									专业：
								</td>
								<td align="left">
									<bean:write name="rs" property="zymc" />
								</td>
								<td align="right">
									联系电话：
								</td>
								<td align="left">
									<bean:write name="rs" property="lxdh" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									班级：
								</td>
								<td align="left">
									<bean:write name="rs" property="bjmc" />
								</td>
								<td align="right">
									审核：
								</td>
								<td align="left">
									<bean:write name="rs" property="yesNo" />
								</td>
							</tr>
							<logic:present name="xskh">
								<tr>
									<td align="right">
										工作表现：
									</td>
									<logic:equal value="10355" name="xxdm">
										<td align="left">
										<html:select name="rs" property="gzbx" style="width:120px"
											styleId="gzbx">
											<html:option value="优秀">优秀</html:option>
											<html:option value="合格">合格 </html:option>
										</html:select>
										</td>
									</logic:equal>
									<logic:notEqual value="10355" name="xxdm">
										<td align="left">
										<html:select name="rs" property="gzbx" style="width:120px"
											styleId="gzbx">
											<html:option value="优"> 优 </html:option>
											<html:option value="良"> 良 </html:option>
											<html:option value="差"> 差 </html:option>
										</html:select>
										</td>
									</logic:notEqual>									
									<td>
									</td>
									<td>
									</td>
								</tr>
							</logic:present>
							<logic:present name="gzjl">
								<td align="right">
									工作情况：
								</td>
								<td colspan="3">
									<html:textarea name='rs' property='xsgzqk' rows="4"
										styleId="xsgzqk" style="width:99%" />
								</td>
							</logic:present>
							<logic:present name="xskh">
								<td align="right">
									工作情况：
								</td>
								<td colspan="3">
									<html:textarea name='rs' property='xsgzqk' rows="4"
										styleId="xsgzqk" readonly="true" style="width:99%" />
								</td>
							</logic:present>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" class="tbstyle" id="rsTable">
							<tr height="25">
								<td align="center">
									27<br/>
									到岗时间：离岗时间
								</td>
								<td align="center">
									28<br/>
									到岗时间：离岗时间
								</td>
								<td align="center">
									29<br/>
									到岗时间：离岗时间
								</td>
								<td align="center">
									30<br/>
									到岗时间：离岗时间
								</td>
																
							</tr>
							<tr height="45">
								<logic:present name="day27">
									<td align="center">	
										<html:select property="day27_ksxs" name="rs" styleId="day27_ksxs" >
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day27_ksfz" name="rs" styleId="day27_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
											：
										<html:select property="day27_jsxs" name="rs" styleId="day27_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day27_jsfz" name="rs" styleId="day27_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day27">
									<td align="center">
										<html:select property="day27_ksxs" name="rs" styleId="day27_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day27_ksfz" name="rs" styleId="day27_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day27_jsxs" name="rs" styleId="day27_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day27_jsfz" name="rs" styleId="day27_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day28">
									<td align="center">
										<html:select property="day28_ksxs" name="rs" styleId="day28_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day28_ksfz" name="rs" styleId="day28_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day28_jsxs" name="rs" styleId="day28_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select><html:select property="day28_jsfz" name="rs" styleId="day28_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day28">
									<td align="center">
										<html:select property="day28_ksxs" name="rs" styleId="day28_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day28_ksfz" name="rs" styleId="day28_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day28_jsxs" name="rs" styleId="day28_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day28_jsfz" name="rs" styleId="day28_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day29">
									<td align="center">
										<html:select property="day29_ksxs" name="rs" styleId="day29_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day29_ksfz" name="rs" styleId="day29_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day29_jsxs" name="rs" styleId="day29_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day29_jsfz" name="rs" styleId="day29_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day29">
									<td align="center">
										<html:select property="day29_ksxs" name="rs" styleId="day29_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day29_ksfz" name="rs" styleId="day29_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day29_jsxs" name="rs" styleId="day29_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day29_jsfz" name="rs" styleId="day29_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day30">
									<td align="center">
										<html:select property="day30_ksxs" name="rs" styleId="day30_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day30_ksfz" name="rs" styleId="day30_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day30_jsxs" name="rs" styleId="day30_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day30_jsfz" name="rs" styleId="day30_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day30">
									<td align="center">
										<html:select property="day30_ksxs" name="rs" styleId="day30_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day30_ksfz" name="rs" styleId="day30_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day30_jsxs" name="rs" styleId="day30_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day30_jsfz" name="rs" styleId="day30_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>															
							</tr>
							<tr height="25">
								<td align="center">
									31
								</td>
								<td align="center">
									1
								</td>
								<td align="center">
									2
								</td>
								<td align="center">
									3
								</td>																
							</tr>
							<tr height="45">
							<logic:present name="day31">
									<td align="center">
										<html:select property="day31_ksxs" name="rs" styleId="day31_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day31_ksfz" name="rs" styleId="day31_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day31_jsxs" name="rs" styleId="day31_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day31_jsfz" name="rs" styleId="day31_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day31">
									<td align="center">
										<html:select property="day31_ksxs" name="rs" styleId="day31_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day31_ksfz" name="rs" styleId="day31_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day31_jsxs" name="rs" styleId="day31_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day31_jsfz" name="rs" styleId="day31_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>	
								<logic:present name="day1">
									<td align="center">
										<html:select property="day1_ksxs" name="rs" styleId="day1_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day1_ksfz" name="rs" styleId="day1_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day1_jsxs" name="rs" styleId="day1_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day1_jsfz" name="rs" styleId="day1_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day1">
									<td align="center">
										<html:select property="day1_ksxs" name="rs" styleId="day1_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day1_ksfz" name="rs" styleId="day1_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day1_jsxs" name="rs" styleId="day1_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day1_jsfz" name="rs" styleId="day1_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day2">
									<td align="center">
										<html:select property="day2_ksxs" name="rs" styleId="day2_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day2_ksfz" name="rs" styleId="day2_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day2_jsxs" name="rs" styleId="day2_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day2_jsfz" name="rs" styleId="day2_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day2">
									<td align="center">
										<html:select property="day2_ksxs" name="rs" styleId="day2_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day2_ksfz" name="rs" styleId="day2_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day2_jsxs" name="rs" styleId="day2_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day2_jsfz" name="rs" styleId="day2_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day3">
									<td align="center">
										<html:select property="day3_ksxs" name="rs" styleId="day3_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day3_ksfz" name="rs" styleId="day3_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day3_jsxs" name="rs" styleId="day3_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day3_jsfz" name="rs" styleId="day3_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day3">
									<td align="center">
										<html:select property="day3_ksxs" name="rs" styleId="day3_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day3_ksfz" name="rs" styleId="day3_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day3_jsxs" name="rs" styleId="day3_jsxs" 
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day3_jsfz" name="rs" styleId="day3_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>														
							</tr>
							<tr height="25">
								<td align="center">
									4
								</td>
								<td align="center">
									5
								</td>
								<td align="center">
									6
								</td>
								<td align="center">
									7
								</td>															
							</tr>
							<tr height="45">	
								<logic:present name="day4">
									<td align="center">
										<html:select property="day4_ksxs" name="rs" styleId="day4_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day4_ksfz" name="rs" styleId="day4_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day4_jsxs" name="rs" styleId="day4_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day4_jsfz" name="rs" styleId="day4_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day4">
									<td align="center">
										<html:select property="day4_ksxs" name="rs" styleId="day4_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day4_ksfz" name="rs" styleId="day4_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day4_jsxs" name="rs" styleId="day4_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day4_jsfz" name="rs" styleId="day4_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>	
								<logic:present name="day5">
									<td align="center">
										<html:select property="day5_ksxs" name="rs" styleId="day5_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day5_ksfz" name="rs" styleId="day5_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day5_jsxs" name="rs" styleId="day5_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day5_jsfz" name="rs" styleId="day5_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day5">
									<td align="center">
										<html:select property="day5_ksxs" name="rs" styleId="day5_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day5_ksfz" name="rs" styleId="day5_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day5_jsxs" name="rs" styleId="day5_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day5_jsfz" name="rs" styleId="day5_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>								
								<logic:present name="day6">
									<td align="center">
										<html:select property="day6_ksxs" name="rs" styleId="day6_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day6_ksfz" name="rs" styleId="day6_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day6_jsxs" name="rs" styleId="day6_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day6_jsfz" name="rs" styleId="day6_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day6">
									<td align="center">
										<html:select property="day6_ksxs" name="rs" styleId="day6_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day6_ksfz" name="rs" styleId="day6_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day6_jsxs" name="rs" styleId="day6_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day6_jsfz" name="rs" styleId="day6_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day7">
									<td align="center">
										<html:select property="day7_ksxs" name="rs" styleId="day7_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day7_ksfz" name="rs" styleId="day7_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day7_jsxs" name="rs" styleId="day7_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day7_jsfz" name="rs" styleId="day7_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day7">
									<td align="center">
										<html:select property="day7_ksxs" name="rs" styleId="day7_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day7_ksfz" name="rs" styleId="day7_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day7_jsxs" name="rs" styleId="day7_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day7_jsfz" name="rs" styleId="day7_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>																
							</tr>
							<tr height="25">
							<td align="center">
									8
								</td>
								<td align="center">
									9
								</td>
								<td align="center">
									10
								</td>	
								<td align="center">
									11
								</td>																
							</tr>
							<tr height="45">
							<logic:present name="day8">
									<td align="center">
										<html:select property="day8_ksxs" name="rs" styleId="day8_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day8_ksfz" name="rs" styleId="day8_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day8_jsxs" name="rs" styleId="day8_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day8_jsfz" name="rs" styleId="day8_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day8">
									<td align="center">
										<html:select property="day8_ksxs" name="rs" styleId="day8_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day8_ksfz" name="rs" styleId="day8_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day8_jsxs" name="rs" styleId="day8_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day8_jsfz" name="rs" styleId="day8_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day9">
									<td align="center">
										<html:select property="day9_ksxs" name="rs" styleId="day9_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day9_ksfz" name="rs" styleId="day9_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day9_jsxs" name="rs" styleId="day9_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day9_jsfz" name="rs" styleId="day9_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day9">
									<td align="center">
										<html:select property="day9_ksxs" name="rs" styleId="day9_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day9_ksfz" name="rs" styleId="day9_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day9_jsxs" name="rs" styleId="day9_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day9_jsfz" name="rs" styleId="day9_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day10">
									<td align="center">
										<html:select property="day10_ksxs" name="rs" styleId="day10_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day10_ksfz" name="rs" styleId="day10_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day10_jsxs" name="rs" styleId="day10_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day10_jsfz" name="rs" styleId="day10_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day10">
									<td align="center">
										<html:select property="day10_ksxs" name="rs" styleId="day10_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day10_ksfz" name="rs" styleId="day10_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day10_jsxs" name="rs" styleId="day10_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day10_jsfz" name="rs" styleId="day10_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day11">
									<td align="center">
										<html:select property="day11_ksxs" name="rs" styleId="day11_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day11_ksfz" name="rs" styleId="day11_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day11_jsxs" name="rs" styleId="day11_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day11_jsfz" name="rs" styleId="day11_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day11">
									<td align="center">
										<html:select property="day11_ksxs" name="rs" styleId="day11_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day11_ksfz" name="rs" styleId="day11_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day11_jsxs" name="rs" styleId="day11_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day11_jsfz" name="rs" styleId="day11_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>

															
							</tr>
							<tr height="25">
							<td align="center">
									12
								</td>
								<td align="center">
									13
								</td>
								<td align="center">
									14
								</td>
								<td align="center">
									15
								</td>															
							</tr>

							<tr height="45">
							<logic:present name="day12">
									<td align="center">
										<html:select property="day12_ksxs" name="rs" styleId="day12_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day12_ksfz" name="rs" styleId="day12_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day12_jsxs" name="rs" styleId="day12_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day12_jsfz" name="rs" styleId="day12_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day12">
									<td align="center">
										<html:select property="day12_ksxs" name="rs" styleId="day12_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day12_ksfz" name="rs" styleId="day12_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day12_jsxs" name="rs" styleId="day12_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day12_jsfz" name="rs" styleId="day12_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>

								<logic:present name="day13">
									<td align="center">
										<html:select property="day13_ksxs" name="rs" styleId="day13_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day13_ksfz" name="rs" styleId="day13_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day13_jsxs" name="rs" styleId="day13_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day13_jsfz" name="rs" styleId="day13_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day13">
									<td align="center">
										<html:select property="day13_ksxs" name="rs" styleId="day13_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day13_ksfz" name="rs" styleId="day13_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day13_jsxs" name="rs" styleId="day13_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day13_jsfz" name="rs" styleId="day13_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>

								<logic:present name="day14">
									<td align="center">
										<html:select property="day14_ksxs" name="rs" styleId="day14_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day14_ksfz" name="rs" styleId="day14_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day14_jsxs" name="rs" styleId="day14_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day14_jsfz" name="rs" styleId="day14_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>				

								<logic:notPresent name="day14">
									<td align="center">
										<html:select property="day14_ksxs" name="rs" styleId="day14_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day14_ksfz" name="rs" styleId="day14_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day14_jsxs" name="rs" styleId="day14_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day14_jsfz" name="rs" styleId="day14_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>

								<logic:present name="day15">
									<td align="center">
										<html:select property="day15_ksxs" name="rs" styleId="day15_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day15_ksfz" name="rs" styleId="day15_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day15_jsxs" name="rs" styleId="day15_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day15_jsfz" name="rs" styleId="day15_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day15">
									<td align="center">
										<html:select property="day15_ksxs" name="rs" styleId="day15_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day15_ksfz" name="rs" styleId="day15_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day15_jsxs" name="rs" styleId="day15_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day15_jsfz" name="rs" styleId="day15_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>									
								</tr>
								
								<tr>
								<td align="center">
									16
								</td>
								<td align="center">
									17
								</td>
								<td align="center">
									18
								</td>
								<td align="center">
									19
								</td>															
								</tr>
							<tr height="45">
							<logic:present name="day16">
									<td align="center">
										<html:select property="day16_ksxs" name="rs" styleId="day16_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day16_ksfz" name="rs" styleId="day16_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day16_jsxs" name="rs" styleId="day16_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day16_jsfz" name="rs" styleId="day16_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day16">
									<td align="center">
										<html:select property="day16_ksxs" name="rs" styleId="day16_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day16_ksfz" name="rs" styleId="day16_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day16_jsxs" name="rs" styleId="day16_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day16_jsfz" name="rs" styleId="day16_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day17">
									<td align="center">
										<html:select property="day17_ksxs" name="rs" styleId="day17_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day17_ksfz" name="rs" styleId="day17_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day17_jsxs" name="rs" styleId="day17_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day17_jsfz" name="rs" styleId="day17_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day17">
									<td align="center">
										<html:select property="day17_ksxs" name="rs" styleId="day17_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day17_ksfz" name="rs" styleId="day17_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day17_jsxs" name="rs" styleId="day17_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day17_jsfz" name="rs" styleId="day17_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>

								<logic:present name="day18">
									<td align="center">
										<html:select property="day18_ksxs" name="rs" styleId="day18_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day18_ksfz" name="rs" styleId="day18_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day18_jsxs" name="rs" styleId="day18_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day18_jsfz" name="rs" styleId="day18_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day18">
									<td align="center">
										<html:select property="day18_ksxs" name="rs" styleId="day18_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day18_ksfz" name="rs" styleId="day18_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day18_jsxs" name="rs" styleId="day18_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day18_jsfz" name="rs" styleId="day18_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day19">
									<td align="center">
										<html:select property="day19_ksxs" name="rs" styleId="day19_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day19_ksfz" name="rs" styleId="day19_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day19_jsxs" name="rs" styleId="day19_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day19_jsfz" name="rs" styleId="day19_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day19">
									<td align="center">
										<html:select property="day19_ksxs" name="rs" styleId="day19_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day19_ksfz" name="rs" styleId="day19_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day19_jsxs" name="rs" styleId="day19_jsxs"
										 disabled="true">										
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day19_jsfz" name="rs" styleId="day19_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>							
							</tr>														
							<tr>
								<td align="center">
									20
								</td>	
								<td align="center">
									21
								</td>
								<td align="center">
									22
								</td>
								<td align="center">
									23
								</td>								
							</tr>
							<tr>	

								<logic:present name="day20">
									<td align="center">
										<html:select property="day20_ksxs" name="rs" styleId="day20_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day20_ksfz" name="rs" styleId="day20_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day20_jsxs" name="rs" styleId="day20_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day20_jsfz" name="rs" styleId="day20_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day20">
									<td align="center">
										<html:select property="day20_ksxs" name="rs" styleId="day20_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day20_ksfz" name="rs" styleId="day20_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day20_jsxs" name="rs" styleId="day20_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day20_jsfz" name="rs" styleId="day20_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day21">
									<td align="center">
										<html:select property="day21_ksxs" name="rs" styleId="day21_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day21_ksfz" name="rs" styleId="day21_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day21_jsxs" name="rs" styleId="day21_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day21_jsfz" name="rs" styleId="day21_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day21">
									<td align="center">
										<html:select property="day21_ksxs" name="rs" styleId="day21_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day21_ksfz" name="rs" styleId="day21_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day21_jsxs" name="rs" styleId="day21_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day21_jsfz" name="rs" styleId="day21_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day22">
									<td align="center">
										<html:select property="day22_ksxs" name="rs" styleId="day22_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day22_ksfz" name="rs" styleId="day22_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day22_jsxs" name="rs" styleId="day22_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day22_jsfz" name="rs" styleId="day22_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day22">
									<td align="center">
										<html:select property="day22_ksxs" name="rs" styleId="day22_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day22_ksfz" name="rs" styleId="day22_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day22_jsxs" name="rs" styleId="day22_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day22_jsfz" name="rs" styleId="day22_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day23">
									<td align="center">
										<html:select property="day23_ksxs" name="rs" styleId="day23_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day23_ksfz" name="rs" styleId="day23_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day23_jsxs" name="rs" styleId="day23_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day23_jsfz" name="rs" styleId="day23_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day23">
									<td align="center">
										<html:select property="day23_ksxs" name="rs" styleId="day23_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day23_ksfz" name="rs" styleId="day23_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day23_jsxs" name="rs" styleId="day23_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day23_jsfz" name="rs" styleId="day23_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								
							</tr>
							<tr>
								<td align="center">
									24
								</td>
								<td align="center">
									25
								</td>	
								<td align="center">
									26
								</td>
								<td align="center" rowspan="2" colspan="4">
									月上岗时间累计
									<span id="ljxs"><bean:write name="rs" property="zsj"/></span>小时
									<br />
									总钱
									<span id="zq"><bean:write name="rs" property="zcj"/></span>元
								</td>
							</tr>
							<tr>
								<logic:present name="day24">
									<td align="center">
										<html:select property="day24_ksxs" name="rs" styleId="day24_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day24_ksfz" name="rs" styleId="day24_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day24_jsxs" name="rs" styleId="day24_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day24_jsfz" name="rs" styleId="day24_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day24">
									<td align="center">
										<html:select property="day24_ksxs" name="rs" styleId="day24_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day24_ksfz" name="rs" styleId="day24_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day24_jsxs" name="rs" styleId="day24_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day24_jsfz" name="rs" styleId="day24_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
								<logic:present name="day25">
									<td align="center">
										<html:select property="day25_ksxs" name="rs" styleId="day25_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day25_ksfz" name="rs" styleId="day25_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day25_jsxs" name="rs" styleId="day25_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day25_jsfz" name="rs" styleId="day25_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day25">
									<td align="center">
										<html:select property="day25_ksxs" name="rs" styleId="day25_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day25_ksfz" name="rs" styleId="day25_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day25_jsxs" name="rs" styleId="day25_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day25_jsfz" name="rs" styleId="day25_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>	
								<logic:present name="day26">
									<td align="center">
										<html:select property="day26_ksxs" name="rs" styleId="day26_ksxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day26_ksfz" name="rs" styleId="day26_ksfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day26_jsxs" name="rs" styleId="day26_jsxs">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day26_jsfz" name="rs" styleId="day26_jsfz">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day26">
									<td align="center">
										<html:select property="day26_ksxs" name="rs" styleId="day26_ksxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day26_ksfz" name="rs" styleId="day26_ksfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
										：
										<html:select property="day26_jsxs" name="rs" styleId="day26_jsxs"
										 disabled="true">
										<html:options collection="xsList" property="xs" labelProperty="xs"/>
										</html:select>
										<html:select property="day26_jsfz" name="rs" styleId="day26_jsfz"
										 disabled="true">
										<html:options collection="fzList" property="fz" labelProperty="fz"/>
										</html:select>
									</td>
								</logic:notPresent>
							</tr>
							</table>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="refreshForm('/xgxt/stu_work_info.do?act=save&doType=' + document.getElementById('doType').value);Close();window.dialogArguments.document.getElementById('search_go').click();"
					style="width:80px" id="buttonSave">
					保 存
<%--					if(sumpd()) --%>
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="expTab('tb','','')"
					style="width:80px" id="buttonPrint">
					打印列表
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
