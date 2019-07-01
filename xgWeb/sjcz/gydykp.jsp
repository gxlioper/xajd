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
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
	</head>
	<base target="_self">
	<body  onload="bjLimit('bj');">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<script language="javascript" src="js/function.js"></script>
			<script language="javascript" src="js/xsgyglFunction.js"></script>
			<script type='text/javascript'src='/xgxt/dwr/interface/GetListData.js'></script>
			<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
			<script type='text/javascript' src='dwr/util.js'></script>
			<script language="javascript" src="js/AjaxFunction.js"></script>
	        <script language="javascript" src="js/String.js"></script>
			<html:form action="/gydykp.do" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置：当前所在位置：公寓管理 - 公寓德育考评
					</div>
				</div>
				<fieldset>
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />
					<input type="hidden" name="qshV" id="qshV" />
					<input type="hidden" name="lcV" id="lcV" value=""/>
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="xxdm" name="xxdm"
						value="<bean:write name="xxdm" scope="request"/>" />
                    <input type="hidden" name="isFdy" id="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />					
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									年级：
									<html:select property="nj" 
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：

									<html:select property="xydm" 
										onchange="initZyList();initBjList()" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>

									&nbsp;&nbsp;专业：
									<html:select property="zydm"  styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" id="go" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="document.forms[0].go.value='go';refreshForm('/xgxt/gydykp.do');this.disabled=true">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" >
									班级：
									<html:select property="bjdm"  styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
									&nbsp;&nbsp;学号：
									<html:text property="xh"
										onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';refreshForm('/xgxt/gydykp.do');}" />
									&nbsp;&nbsp;姓名：
									<html:text property="xm"
										onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';refreshForm('/xgxt/gydykp.do');}" />

								</td>
							</tr>
							<tr>
								<td>									
<%--									<logic:notPresent name="showhzy">--%>
<%--								    当前入住楼栋：--%>
<%--								        <html:select property="lddm" style="width:120px"--%>
<%--											onchange="GetQshList()" styleId="lddm">--%>
<%--											--%>
<%--											<html:options collection="ldList" property="lddm"--%>
<%--												labelProperty="ldmc" />--%>
<%--										</html:select>								--%>
<%--								    &nbsp;&nbsp;当前入住寝室：--%>
<%--								       <html:select property="qsh" style="width:110px" styleId="qsh">--%>
<%--											<html:option value=""></html:option>--%>
<%--											<html:options collection="qshList" property="dm"--%>
<%--												labelProperty="mc" />--%>
<%--										</html:select>--%>
<%--									</logic:notPresent>--%>
<%--									<logic:present name="showhzy">--%>
										&nbsp;&nbsp;当前入住楼栋:
									<html:select property="lddm"  styleId="lddm"
										onchange="getLcList()">
										
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>
										&nbsp;&nbsp;当前入住楼层:
										<html:select property="lc"  styleId="lc"
										onchange="getQshList2()">
										
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select>
										&nbsp;&nbsp;当前入住寝室:
										<html:select property="qsh"  styleId="qsh">
										
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
<%--									</logic:present>--%>

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
							<font color="blue">提示：双击一行可以查看、增加、修改和删除数据；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td onclick="tableSort(this)" nowrap>
										学号
									</td>
  									<td onclick="tableSort(this)" nowrap>
										姓名
									</td>
  									<td onclick="tableSort(this)" nowrap>
										性别
									</td>
  									<td onclick="tableSort(this)" nowrap>
										班级名称
									</td>
  									<td onclick="tableSort(this)" nowrap>
										当前所住楼栋名称
									</td>
  									<td onclick="tableSort(this)" nowrap>
										当前所住寝室号
									</td>
  									<td onclick="tableSort(this)" nowrap>
										寝室电话
									</td>
  									<td onclick="tableSort(this)" nowrap>
										当前学年(期)总分
									</td>
                                   
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="showTopWin('stu_gydykp_info.do?Pkxh='+curr_row.cells[0].innerText.trim(),'900','800');">	
									<logic:iterate id="v" name="s" offset="0">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>
