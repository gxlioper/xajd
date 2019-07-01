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
<html>
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="正方软件 zfsoft">
	</head>
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
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<base target="_self">
	<body onload="">
		<html:form action="/xbemyArchive">
			<input type="hidden" name="url" id="url" value="/xbemyArchive.do?method=showEnrollmentArchive">
<%--			<input type="hidden" name="variable" id="variable" value="false">--%>
			<input type="hidden" name="getStuInfo" id="getStuInfo" value="xm-xb">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：学生信息 - 档案管理 - 入学档案信息
				</div>
			</div>
			<table width="100%" class="tbstyle">
			<thead align="center">
				<tr><td align="center" colspan="4">入学档案信息</td></tr>
			</thead>	
					<tr>
						<td>
							<div align="right">
								<font color="red">*</font>学号：
							</div>
						</td>
						<td width="24%">
								<html:text name="rs" property="xh" styleId="xh" onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
								<button align="left" class="button2"
									onclick="showTopWin('/xgxt/stu_info.do?',750,550);"
									style="width:20px" id="buttonFindStu">
									选择
								</button>
						</td>
						<td rowspan="5" align="center">
							<img border="1" style="height:133px;width:100px;" src="/<bean:write name="rs" property="xh"/>.jpg" id="pic">
						</td>
						</tr>
						<tr>
						<td width="30%">
							<div align="right">
								姓名：
							</div>
						</td>
						<td width="30%"">
							<bean:write name="rs" property="xm" />
							<input type="hidden" name="xm" id="xm"
								value="<bean:write name="rs" property="xm"/>" />
						</td>					
					</tr>
					<tr>
						<td width="22%">
							<div align="right">
								性别：
							</div>
						</td>
						<td width="33%" >
							<html:radio name="rs" property="xb" value="1">男</html:radio>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="xb" value="2">女</html:radio>
						</td>						
					</tr>
					<tr>
						<td>
							<div align="right">
								年级：
							</div>
						</td>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
					</tr>
					<tr>
						<td width="22%">
							<div align="right">
								民族：
							</div>
						</td>
						<td width="33%">
							<bean:write name="rs" property="mzmc" />
							<input type="hidden" name="mzmc" id="mzmc"
								value="<bean:write name="rs" property="mzmc"/>" />
						</td>
					</tr>
					<tr height="25px">
						<td width="22%">
							<div align="right">生源地：
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="syd" />
							<input type="hidden" name="syd" id="syd"
								value="<bean:write name="rs" property="syd"/>" />
						</td>
					</tr>
					<tr height="45px">
						<td>
							<div align="right">到档情况： 
							</div>
						</td>						
						<td colspan="2">
							<html:select property="daqk" name="rs">
								<html:option value=""></html:option>
								<html:options collection="ddqkList" property="dddm" labelProperty="ddmc"/>
							</html:select>
						</td>
					</tr>	
					<tr>
						<td>
							<div align="right">备注： 
							</div>
						</td>
						<td colspan="2">
							<html:textarea property="bz" name="rs" style="width:100%"/>
						</td>
					</tr>
			</table>

			<center>
				<div class="buttontool" id="btn" width="100%">		
						<button class="button2"
							onclick="refreshForm('xbemyArchive.do?method=showEnrollmentArchive&doType=save')"
							style="width:80px">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="Close();return false;"
							style="width:80px">
							关 闭
						</button>		
				</div>
			</center>
			<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("操作成功！");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert("操作失败！");
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
