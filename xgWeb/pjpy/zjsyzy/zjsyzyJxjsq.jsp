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
		<style type="text/css">
		<!--
		.style1 {color: #FF0000}
		-->
		</style>
	</head>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
	<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/pjpyFunction.js"></script>
	<script language="javascript">
		function jxjsave() {
			if (dataDoSaveApply1('/xgxt/applySave.do','jxjdm','jxj','xh-jxjdm')) 
				BatAlert.showTips('正在操作，请等待...');
				
		}
		function loadJxjInfo(){
			var jxjdm = document.getElementById('jxjdm').value;
			pjpyZjsyzy.queryJxjInfo(jxjdm,function(data){
				document.getElementById("jxjlb").innerText = data.jxjlb;
				document.getElementById("jlje").innerText = data.jlje;
			});
		}
		function printBj() {
		showTopWin('pjpy_zjsyzy.do?method=bjzhszcpInfo&xh='+document.getElementById('xh').value+'&xn='+document.getElementById('xn').value+'&xq='+document.getElementById('xq').value,750,600);
		}
	</script>
	<input type="hidden" name="oldjxjdm" id="oldjxjdm" value="${oldjxjdm }"/>
	<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
	<input type="hidden" name="xn" id="xn" value="${rs.xn}"/>
	<input type="hidden" name="nd" id="nd" value="${rs.nd}"/>
	<input type="hidden" name="xq" id="xq" value="${rs.xq}"/>

	<body>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：评奖评优 - 奖学金申请 - 填写申请表
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
				    alert("您输入的学号无效!");
				    </script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/prise_apply.do?tab=qtjxj" />
				<input type="hidden" name="tab1" id="tab1" value="qtjxj"/>
				<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="12" align="center">
								<b>填写申请表</b>
							</td>
						</tr>
					</thead>
					<logic:notEmpty name="cfList">
						<tr style="height:22px">
						<td colspan="8" align="center">
							<span class="style1" align="center">${sflag }</span>
						</td>
						</tr>
					</logic:notEmpty>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left" colspan="2">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left" colspan="2">
								<bean:write name='rs' property="xh" /><html:hidden name='rs' property="xh" styleId="xh" />
							</td>
						</logic:equal>
						<td align="right" >
							学年：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="xn" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right" >
							学期：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="xqmc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="xb" />
						</td>
						<td align="right" >
							<font color="red">*</font>奖学金：
						</td>
						<td align="left" colspan="4">
							<html:select property="xmdm" styleId="jxjdm"
								onchange="loadJxjInfo()" name="rs">
								<option value=""></option>
								<html:options collection="jxjList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							年级：
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="nj" />
						</td>
						<td align="right" >
							奖学金类别：
						</td>
						<td align="left" colspan="4" id="jxjlb">
							<bean:write name='rs' property="jxjlb" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right" >
							奖励金额：
						</td>
						<td align="left" colspan="4" id="jlje">
							<bean:write name='rs' property='jlje' />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							专业：
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="zymc" />
						</td>
						<td align="right" >
							担任职务：
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="drzw" styleId="a" maxlength="50"/>
						</td>
					</tr>

					<tr style="height:22px">
						<td align="right">
							班级：
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right" >
							综合素质测评成绩排名：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="cpzfpm" />
						</td>
					</tr>
					<tr>
						<td align="right">
							综合素质测评参评排名：
						</td>
						<td align="left" colspan="2">
							<logic:equal value="能" name="rs" property="nfhdtdjxj">
								无须参评
							</logic:equal>
							<logic:notEqual value="能" name="rs" property="nfhdtdjxj">
								<bean:write name='rs' property="cpcppm" />
							</logic:notEqual>
						</td>
						<td align="right">
							能否获得特等奖学金：
						</td>
						<td colspan="4">
							<bean:write name='rs' property="nfhdtdjxj" />
						</td>
					</tr>
					<logic:notEmpty name="kccj">
					<tr>
						<td colspan="9" align="center" bgcolor="">
							<b>课程成绩信息</b>
						</td>
					</tr>
					<thead>
					<tr>
						<td colspan="3">课程名称</td>
						<td colspan="3">课程类型</td>
						<td colspan="2">课程成绩</td>
					</tr>
					</thead>
					<logic:iterate id="list" name="kccj">
					<tr style="cursor:hand;background-color:<logic:iterate id="v" name="list" offset="0" length="1">
						<bean:write name="v"/>
					</logic:iterate>">
						<logic:iterate id="v" name="list" offset="1">
						<td colspan="3"><bean:write name="v"/></td>
						</logic:iterate>
					</tr>
					</logic:iterate>
					</logic:notEmpty>	
					
					<logic:notEmpty name="wjcf">
					<tr>
					<td colspan="9" align="center" bgcolor="">
						<b>违纪处分信息</b>
					</td>					
					</tr>
					<thead>
					<tr>
						<td colspan="2">学年</td>
						<td colspan="2">学期</td>
						<td colspan="2">处分类别</td>
						<td colspan="2">处分原因</td>
					</tr>
					</thead>
					<logic:iterate id="list" name="wjcf">
					<tr>
						<logic:iterate id="v" name="list">
						<td colspan="2"><bean:write name="v"/></td>
						</logic:iterate>
					</tr>
					</logic:iterate>
					</logic:notEmpty>	
					
					
					<tr align="left" style="height:22px">
						<td align="right">
							学习简历：
							<br/>
							<span class="style1">(限制在400字内)</span>
						</td>
						<td colspan="7">
							<html:textarea name='rs' property='xxjl' style="width:99%"
								rows='5' styleId="xxjl"/>
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							科研项目：
							<br/>
							<span class="style1">(限制在400字内)</span>
							</td>
							<td colspan="7">
							<html:textarea name='rs' property='kycg' style="width:99%"
								rows='5' styleId="kyxm"/>
							</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							申请理由：
							<br/>
							<span class="style1">(限制在800字内)</span>
						</td>
						<td colspan="7">
							<html:textarea name='rs' property='sqly' style="width:99%"
								rows='5' styleId="sqly"/>
						</td>
					</tr>
				</table>
					<div class="buttontool" align="center">
						<button type="button" class="button2" <logic:equal value="no" name="jxjsq">disabled="disabled"</logic:equal>
							onclick="commitPrise()">
							提 交 申 请
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="window.open('jxjpsdjb.do?xh='+document.getElementById('xh').value+'&jxjdm='+document.getElementById('jxjdm').value);">
							打 印 报 表
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="printBj()">
							班级测评查询
						</button>
					</div>
			</logic:notEmpty>
			
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
					    alert("申请成功！");
					    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					    	window.dialogArguments.document.all("search_go").click();
					    	Close();
					    }
   					 </script>
				</logic:equal>
				
				<logic:equal name="inserted" value="no">
					<script>
					    alert("申请失败！");
					    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					    	window.dialogArguments.document.all("search_go").click();
					    	Close();
					    }
    				</script>
				</logic:equal>
			</logic:notEmpty>
			
		</html:form>
	</body>
</html>
