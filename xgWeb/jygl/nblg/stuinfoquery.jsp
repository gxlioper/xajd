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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getTowdays.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getScoreinfo.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript">
	       function querygo(){
		 	       document.forms[0].action = "stuinfoquery.do?act=query";
		 	       document.forms[0].submit();
		 	       document.getElementById("search_go").disabled=true;
           }
   
   	       function stu_send(type)
		    {
			var pkValue=curr_row.getElementsByTagName("input")[0].value;;
			if(type=="jyxx"){
			window.dialogArguments.refreshForm('stuxsjyxxinput.do?act=go&pkValue='+pkValue);
			window.close();
			}
		}
	</script>

		<html:form action="/stuinfoquery" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />

			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 学生信息 - 学生信息查询
				</div>
			</div>
			<fieldset>
				<legend>
					查 询
				</legend>

				<table width="100%" class="tbstyle">
					<thead>
						<tr style="cursor:hand">
							<td>
								学号：
								<html:text property="xh" style="width:120px" />
								&nbsp;&nbsp;&nbsp;&nbsp;姓名：
								<html:text name="rs1" property="xm" style="width:70px" />
								&nbsp;&nbsp;&nbsp;&nbsp;性别：
								<html:select property="xb" style="width:50px">
									<html:option value=""></html:option>
									<html:option value="男">
										男
									</html:option>
									<html:option value="女">
										女
									</html:option>
								</html:select>
<%--								学年：--%>
<%--								<html:select name="rs1" property="xn">--%>
<%--									<html:options collection="xnList" property="xn"--%>
<%--										labelProperty="xn" />--%>
<%--								</html:select>--%>
<%--								学期：--%>
<%--								<html:select name="rs1" property="xq">--%>
<%--									<html:option value=""></html:option>--%>
<%--									<html:options collection="xqList" property="xqmc"--%>
<%--										labelProperty="xqmc" />--%>
<%--								</html:select>--%>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height:40px" onclick="querygo()" id="search_go">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />：
								<logic:equal name="who" value="xx">
									<html:select property="xydm" style="width:180px"
										styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:equal name="who" value="xy">
									<html:select name="rs1" property="xydm" style="width:150px"
										styleId="xy" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								&nbsp;&nbsp;专业：
								<html:select property="zydm" style="width:150px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" style="width:150px"
									styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
							<logic:equal value="10863" name="xxdm" scope="session">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</logic:equal>
							<logic:notEqual value="10863" name="xxdm" scope="session">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</logic:notEqual>
							</tr>
						</thead>
						<logic:equal value="10863" name="xxdm" scope="session">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="stu_send('jyxx')">
									<td align="center">
									<input type="hidden" name="pk" value="<bean:write name="s" property="xh"/>" />
										<bean:write name="s" property="xh" />
									</td>
									<td align="center">
										<bean:write name="s" property="xm" />
									</td>
									<td align="center">
										<bean:write name="s" property="xn" />
									</td>
									<td align="center">
										<bean:write name="s" property="xq" />
									</td>
									<td align="center">
										<bean:write name="s" property="sfdlh" />
									</td>
							</tr>
						</logic:iterate>
						</logic:equal>
						<logic:notEqual value="10863" name="xxdm" scope="session">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="stu_send('jyxx')">
									<td align="center">
									<input type="hidden" name="pk" value="<bean:write name="s" property="xh"/>" />
										<bean:write name="s" property="xh" />
									</td>
									<td align="center">
										<bean:write name="s" property="xm" />
									</td>
									<td align="center">
										<bean:write name="s" property="xb" />
									</td>
									<td align="center">
										<bean:write name="s" property="nj" />
									</td>
									<td align="center">
										<bean:write name="s" property="xydm" />
									</td>
									<td align="center">
										<bean:write name="s" property="zydm" />
									</td>
									<td align="center">
										<bean:write name="s" property="bjdm" />
									</td>
							</tr>
						</logic:iterate>
						</logic:notEqual>
					</table>
					<table width="100%" id="Table" class="tbstyle">
						<TR>
							<TD>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
							</TD>
						</TR>
					</table>

				</fieldset>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
