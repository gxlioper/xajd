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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body >	
		<html:form action="/detailData" method="post">
		<input type="hidden" id="pk" name="pk" value="xh||xn||xq||sj" />
		<input type="hidden" id="pkValueB" name="pkValueB" value="<bean:write name="tabName" scope="request"/>" />
		<input type="hidden" id="pkValueA" name="pkValueA" value="<bean:write name="pkValueA" scope="request"/>" />		
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 公寓德育考评 - 详细信息查看 - 学生奖励信息
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
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								学生奖励
							</td>
						</tr>
					</thead>					
					<tr>
						<td align="right">
							学号：
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="true"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />						
						</td>
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true" />
						</td>						
					</tr>					
					<tr>
						<td align="right">
							性别：
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true" />
						</td>
						<td align="right">
							学年：
						</td>
						<td align="left">						
						<html:text name="rs" property="xn" value="${rs.xn}" readonly="true"></html:text>							
						</td>											
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true" />
						</td>
						<td align="right">
							学期：
						</td>
						<td align="left">						
						<html:text name="rs" property="xq" value="${rs.xq}" readonly="true"></html:text>							
						</td>				
					</tr>					
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xymc" readonly="true" />
						</td>
						<td align="right">
							楼栋名称：
						</td>
						<td align="left">
							<input type="text" value="${rs.ldmc}" readonly="true"/>
							<html:hidden name="rs" property="lddm"></html:hidden>
						</td>				
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" />
						</td>
						<td align="right">
							寝室号：
						</td>
						<td align="left">						
						<html:text name="rs" property="qsh" readonly="true" style="width: 120px"></html:text>							
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true" />
						</td>
						<td align="right">
							奖励时间：									
						</td>
						<td align="left">							
						<html:text name="rs" property="sj" style="width: 120px" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							奖励内容：
						</td>
						<td align="left">
							<logic:present name="showhzy">
								<html:select  name ="rs" property="jlnr" style="width: 120px" onchange="getGyJcXf_Modi('jl')">
								    <html:option value=""></html:option>
								    <html:options collection="xsjlList" property="jldm" 
								          labelProperty="jlmc"/>
								</html:select>
							</logic:present>
							<logic:notPresent name="showhzy">
								<html:text name='rs' property="jlnr" styleId="jlnr" />
							</logic:notPresent>
						</td>
						<td align="right">
							德育加分：
						</td>
						<td align="left">							
							<html:text name="rs" property="ryjf" style="width: 120px" styleId="ryjf" onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)"/>
						</td>			
					</tr>
					<tr>
						<td align="right">
							备注：									
						</td>
						<td align="left" colspan="3">
							<textarea rows="5" cols="65" name="bz" id="bz" type="_moz">${rs.bz}</textarea>			
						</td>					
					</tr>
				</table>
				<div class="buttontool" align="center">	
				<logic:notEqual  value="view" name="doType">			
					<button class="button2" onclick="hh_zrssave();return false;" style="width:80px" id="buttonSave">
						保 存
					</button>
					</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>	
			<logic:equal value="ok" name="done">
				<script type="text/javascript">
	            alert("保存成功！");
		        Close();
		        window.dialogArguments.document.getElementById('search_go').click();
	    </script>
			</logic:equal>
			<logic:equal value="no" name="done">
				<script type="text/javascript">
	           alert("保存失败！");
		       Close();
		       window.dialogArguments.document.getElementById('search_go').click();
	    </script>
	    </logic:equal>	
		</html:form>
  </body>
 <script type="text/javascript">
   function saveData(){
	var xh = document.forms[0].xh.value;
	var pk = document.forms[0].pk.value;
	var pkValueA = document.forms[0].pkValueA.value;
	var pkValueB = document.forms[0].pkValueB.value;
	var url = "/xgxt/detailData.do?act=";
	url += "save";
	url += "&xh=";
	url += xh;
	url += "&pk=";
	url += pk;
	url += "&pkValueA=";
	url += pkValueA;
	url += "&pkValueB=";
	url += pkValueB;
	refreshForm(url);
	$("buttonSave").disabled=true;
  }
</script>
</html>
