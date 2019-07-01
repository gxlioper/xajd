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
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>	
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body >		
		<html:form action="/jgsdx_gygl" method="post">
		<input type="hidden" name="qshV" id="qshV" />	
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 公寓管理 - 申请 - 文明寝室申请
				</div>
			</div>
			<logic:equal value="false" name="canDo" >			
			   <div align="center"><font color="red">只有辅导员用户可以申请!</font> </div>
			</logic:equal>
			<logic:empty name="rs">
				<br/>
				<br/>
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
			
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							文明寝室申请
						</td>
					</tr>
				</thead>
				<tr>
				    <td align="right">
						<font color="red">*</font>楼栋名称：
					</td>
					<td align="left">
							<html:select property="lddm" style="width:80px" styleId="lddm"
								onchange="GetQshList()">
								<html:option value=""></html:option>
								<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
							</html:select>
							<%--					   <input type="hidden" id="ssbh" name="ssbh" value="${rs.ssbh}" />								--%>
<%--					<html:text name="rs" property="ldmc" readonly="true"></html:text>--%>
					</td>		
					<td align="right" >
						<font color="red">*</font>学年：
					  </td>
					<td align="left">
						<html:select name="rs" property="xn" style="width:90px" styleId="xn">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>	
				</tr>					
				<tr>
				<td align="right">
						<font color="red">*</font>寝室号：
					</td>					
					<td align="left">
					<html:select property="qsh" style="width:80px" styleId="qsh">
						<html:option value=""></html:option>
						<html:options collection="qshList" property="dm"
							  labelProperty="mc" />
					</html:select>
					<%--					
					<html:text name="rs" property="qsh" readonly="true"></html:text>--%>
<%--			        <input type="hidden" id="ssbh" name="ssbh"--%>
<%--						value="${rs.ssbh}" />--%>
					</td>	
					<td align="right">
						<font color="red">*</font>学期：
					</td>
					<td align="left">
						<html:select name="rs" property="xq" style="width:90px" styleId="xq">
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>					
				</tr>
				<tr>	
					<td align="right">
						<font color="red">*</font>评优时间：
					</td>
					<td align="left">
						<html:text name="rs" property="pysj" readonly="true"
						onblur="dateFormatChg(this)"
						onclick="return showCalendar('pysj','y-mm-dd');"
						style="cursor:hand " />
					</td>							
					<td align="right">
					    申请人：
					</td>
					<td align="left">
						<html:text name="rs" property="xm" readonly="true"></html:text>		
					</td>
				</tr>
				<tr>
					<td align="right" >
						备注：
					</td>
					<td align="left" colspan="3">
					<textarea rows="5" cols="80" name="bz" id="bz" type="_moz"> ${rs.bz}</textarea>
					</td>
				</tr>
			</table>
		</logic:notEmpty>	
			<div class="buttontool" align="center">
			<logic:equal value="true" name="canDo" >	
				<button class="button2" onclick="if(IsNoEmpty('xn-xq-lddm-qsh-pysj')){refreshForm('/xgxt/jgsdx_gygl.do?method=wmqsSbSave');}"
					style="width:80px" id="buttonSave">
					提  交
				</button>
			</logic:equal>
			</div>	
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
	     alert("申请成功！");
	    </script>
		</logic:equal>	
		<logic:equal value="false" name="done">
			<script type="text/javascript">
	     alert("申请失败！");
	    </script>
		</logic:equal>
	</body>
  
</html>
