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
		<base target = "_self" />
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
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
		<html:form action="/addDromFugle" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 宿舍分配 - 楼层长信息
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
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="url" name="url" value="/gygl/lczxxb.jsp" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								添加楼层长信息
							</td>
						</tr>
					</thead>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>学年：
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="xn" onchange="refreshForm('/xgxt/addDromFugle.do')" style="width:120px" styleId="xn" >
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<td height="22" align="right">
							楼栋名称：
						</td>
						<td height="22" align="left">
							<bean:write name="rs" property="ldmc" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>学期：
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="xq" onchange="refreshForm('/xgxt/addDromFugle.do')" style="width:120px" styleId="xq">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						<td height="22" align="right">
							宿舍号：
						</td>
						<td height="22" align="left">
							<bean:write name="rs" property="qsh" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							年度：
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="nd"  style="width:120px" styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
						<td height="22" align="right">
							<font color="red">*</font>宿舍编号：
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="ssbh" style="width: 120px" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							楼长：
						</td>						
						<td height="22" align="left">
							<logic:present name="lzxxList">
							 <html:select name="rs" property="lz" style="width:120px"
									styleId="lz">
									<html:option value=""></html:option>
									<html:options collection="lzxxList" property="yhm"
										labelProperty="xm" />
							</html:select>
							</logic:present>
							<logic:notPresent name="lzxxList">
								<html:select name="rs" property="lz" style="width:120px"
									styleId="lz">
									<html:option value=""></html:option>
									<html:options collection="lzList" property="xh"
										labelProperty="xm" />
								</html:select>
							</logic:notPresent>
						</td>
						<td height="22" align="right">
							层长：
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="cz"  style="width:120px" styleId="cz">
								<html:option value=""></html:option>
								<html:options collection="lzList" property="xh"
									labelProperty="xm" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							寝室长：
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="qsz"  style="width:120px" styleId="qsz">
								<html:option value=""></html:option>
								<html:options collection="qszList" property="xh"
									labelProperty="xm" />
							</html:select>
						</td>
						<td height="22" align="right">
						</td>
						<td height="22" align="left">
						</td>
					</tr>
				</table>
				<br />
				<div class="buttontool" id="button" align="center">
					<button class="button2"
						onclick="Savedata('xn-xq-ssbh','SaveLczxx.do')"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					<button class="button2"
						onclick="Close()"
						style="width:80px" id="buttonSave">
						关 闭
					</button>
				</div>		
			</logic:notEmpty>
		</html:form>
		<logic:equal value="ok" name="result">
			<script type="text/javascript">
				alert("操作成功！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script type="text/javascript">
				alert("操作失败！");
			</script>
		</logic:equal>
	</body>
</html>
