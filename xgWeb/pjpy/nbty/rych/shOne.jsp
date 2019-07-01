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
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript">
	
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();">		
		<html:form action="nbty_rych" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 审核 - 荣誉称号审核 
				</div>
			</div>
				<input type ="hidden" id="save_xq" name="save_xq" value=${rs.xq }>
				<input type ="hidden" id="save_xh" name="save_xh" value=${rs.xh }>
				<input type ="hidden" id="save_nd" name="save_nd" value=${rs.nd }>
				<input type ="hidden" id="save_xn" name="save_xn" value=${rs.xn }>
				<input type ="hidden" id="save_rychdm" name="save_rychdm" value=${rs.rychdm }>	
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
				<logic:equal name="isFdy" value="true">
					<input type="hidden" id="save_fdyshsj" name="save_fdyshsj" value="${nowTime}" />
				</logic:equal>
				<logic:notEqual name="isFdy" value="true">
				<logic:equal name="userType" value="xy">
					<input type="hidden" id="save_xyshsj" name="save_xyshsj" value="${nowTime}" />
				</logic:equal>
				<logic:equal name="userType" value="xx">
					<input type="hidden" id="save_xxshsj" name="save_xxshsj" value="${nowTime}" />
				</logic:equal>
				</logic:notEqual>
				<fieldset>
					<table width="99%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>填写申请表</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right" style="width: 10%">
							<font color="red">*</font>学号：
						</td>
						<td>
							<bean:write name="rs" property="xh" />
						</td>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="left">
								<logic:notEmpty name="rs">
									<html:hidden name='rs' property="xh" styleId="xh" />
									<bean:write name='rs' property="save_xh" />
								</logic:notEmpty>
							</td>
						</logic:equal>

						<td align="right" style="width: 10%">
							学年：
						</td>
						<td align="left" style="width: 40%">
							<bean:write name="rs" property="xn"/>
							<html:hidden name="rs" property="save_xn"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<logic:notEmpty name="rs">
								<bean:write name='rs' property="xm" />
							</logic:notEmpty>
						</td>
						<td align="right">
							学期：
						</td>
						<td align="left">
							<bean:write name="rs" property="xqmc"/>
							<html:hidden name="rs" property="save_xq"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<logic:notEmpty name="rs">
								<bean:write name='rs' property="xb" />
							</logic:notEmpty>
						</td>
						<td align="right">
							<font color="red">*</font>荣誉称号：
						</td>
						<td align="left">
							<bean:write name="rs" property="rychmc"/>
							<html:hidden property="save_rychdm" name="rs"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<logic:notEmpty name="rs">
								<bean:write name='rs' property="xymc" />
							</logic:notEmpty>
						</td>
						<td align="right">
							担任职务：
						</td>
						<td align="left">
							<bean:write name="rs" property="xrzw" />
							<html:hidden property="save_xrzw" name="rs"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							专业：
						</td>
						<td align="left">
							<logic:notEmpty name="rs">
								<bean:write name='rs' property="zymc" />
							</logic:notEmpty>
						</td>
						<td align="right">
							学年平均成绩
						</td>
						<td align="left">
							<bean:write name="rs" property="xnpjcj" />
							<html:hidden property="save_xnpjcj" name="rs"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							班级：
						</td>
						<td align="left">
							<logic:notEmpty name="rs">
								<bean:write name='rs' property="bjmc" />
							</logic:notEmpty>
						</td>
						<td align="right">
							任现职时间：
						</td>
						<td align="left">
							<bean:write name="rs" property="rxzsj" />
							<html:hidden property="save_rxzsj" name="rs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<logic:equal value="xy" name="userType"><bean:message key="lable.xsgzyxpzxy" /></logic:equal>
							<logic:notEqual value="xy" name="userType">学校</logic:notEqual>
							审核:
						</td>
						<td align="left" colspan="3">
						<logic:equal value="true" name="isFdy">
							<html:select property="save_fdysh" style="width:90px">
							     <html:option value="不通过" >不通过</html:option>
								 <html:option value="通过" >通过</html:option>
								 <html:option value="未审核" >未审核</html:option>
							   </html:select>
						</logic:equal>
						<logic:notEqual name="isFdy" value="true">
						<logic:equal value="xy" name="userType">
							<html:select property="save_xysh" style="width:90px" >
							     <html:option value="不通过" >不通过</html:option>
								 <html:option value="通过" >通过</html:option>
								 <html:option value="未审核" >未审核</html:option>
							   </html:select>
						</logic:equal>
						<logic:equal value="xx" name="userType">
							<html:select property="save_xxsh" style="width:90px">
							     <html:option value="不通过" >不通过</html:option>
								 <html:option value="通过" >通过</html:option>
								 <html:option value="未审核" >未审核</html:option>
							   </html:select>
						</logic:equal>
						<logic:equal value="admin" name="userType">
							<html:select property="save_xxsh" style="width:90px" >
							     <html:option value="不通过" >不通过</html:option>
								 <html:option value="通过" >通过</html:option>
								 <html:option value="未审核" >未审核</html:option>
							   </html:select>
						</logic:equal>
						</logic:notEqual>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							获奖时间及名称：
							<br />
							<span class="style1">(限400字)&nbsp;</span>
						</td>
						<td colspan="3" align="left">
							<html:textarea rows="8" style="width:98%" name="rs"
								property="save_hjsjmc" onblur="chLeng(this,400)" />
								<html:hidden name="rs" property="save_hjsjmc"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							主要事迹：
							<br />
							<span class="style1">(限400字)&nbsp;</span>
						</td>
						<td colspan="3" align="left">
							<html:textarea rows="8" style="width:98%" name="rs"
								property="zysj" onblur="chLeng(this,400)" />
								<html:hidden name="rs" property="save_zysj"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							备注：
							<br />
							<span class="style1">(限400字)&nbsp;</span>
						</td>
						<td colspan="3" align="left">
							<html:textarea rows="8" style="width:98%" name="rs" property="bz"
								onblur="chLeng(this,400)" />
								<html:hidden name="rs" property="save_bz"/>
						</td>
					</tr>
				</table>
					<div class="buttontool" align="center">
							<button type="button" class="button2" ${disabled } onclick="saveData('nbty_rych.do?method=shOne&doType=modi','shjg')"
								style="width:80px" id="buttonSave">
								保 存
							</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
					</div>
				</fieldset>
		</html:form>
		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>
