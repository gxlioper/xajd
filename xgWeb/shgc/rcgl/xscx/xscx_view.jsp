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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
	</script>
	<body >
	
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsfunction.js"></script>
		<html:form action="/rcgl_xscx" method="post">
		<input type="hidden" id="add_flag" name="add_flag" value="no" />		
				<table  class="tbstyle" align="center">
					<thead>
						<tr style="height:22px">
							<td colspan="6" align="center">
								<b>诚信信息</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
							<td align="right" colspan="2">
								<font color="red">*</font>学 号：
							</td>
							<td align="left">
								<html:text  property="xh" styleId="xh"
									onblur="" onkeypress=""  readonly="true"/>
							</td>
							
						<td align="right">
							姓 名：
						</td>
						<td align="left">
							<html:text  property="xm" styleId="xm" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2" readonly="true" >
							性 别：
						</td>
						<td align="left">
							<html:text  property="xb" styleId="xb" readonly="true"/>
						</td>
						<td align="right">
							班 级：
						</td>
						<td align="left">
							<html:text  property="bjmc" styleId="bjmc" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							学 院：
						</td>
						<td align="left">
							<html:text  property="xymc" styleId="xymc"  readonly="true"/>
						</td>
						<td align="right" >
							日 期：
						</td>
						<td align="left">
							<html:text style="cursor:hand; width:75px;" styleId="rq" property="rq"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							学年：
						</td>
						<td align="left">							
								<html:text  property="xn" styleId="xn"  readonly="true"/>
						</td>
						<td align="right" >
							学期：
						</td>
						<td align="left">							
								<html:select property="xq" style="width:50px;background-color:#FFFFFF" styleId="xq" disabled="true" >
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							行为模块：
						</td>
						<td align="left">
							<html:text property="mkmc" style="width:100px" styleId="mkmc" readonly="true"/>		
						</td>
						<td align="right" >
							诚信模块：
						</td>
						<td align="left">
							<html:text  property="cxmkmc" style="width:100px" styleId="cxmkmc" readonly="true"/>
						</td>
					</tr>	
					<tr style="height:22px">
						<td align="right" >
							记录人：
						</td>
						<td align="left"colspan="3">
							<html:text  property="jlr" readonly="true"/>		
						</td>						
					</tr>				
				   <tr>
				  		<td align="right" colspan="2"> 具体内容： </td>
                    	<td colspan="4" align="left"><html:textarea rows="5"  style="width:98%" property="jtnr" styleId="a" readonly="true"/></td>
				  </tr>
				</table>
				<div class="buttontool" align="center">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"  onclick="Close();return false;">
						关 闭
					</button>
				</div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
