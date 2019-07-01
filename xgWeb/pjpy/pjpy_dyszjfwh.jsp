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
	<script language="javascript">
		function dyszwh(xh,xn)
		{
		
			var url ='/xgxt/dyszwh.do'+'?xh='+xh+'&xn='+xn;
			showTopWin(url,650,650);
		}
	</script>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="xyDisabled('xy');">
		
		<center>
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
			<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
			<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
			<script type='text/javascript' src='dwr/util.js'></script>
			<script language="javascript" src="js/AjaxFunction.js"></script>
			<html:form action="/dyszjfwh" method="post">
			<input type="hidden" name="zyV" id="zyV" />
						<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="realTable" name="realTable" value="zhszcp_dyszjfwh" />
			<input type="hidden" id="tableName" name="tableName" value="view_zhszcp_dyszjfwh" /> 
			<input type="hidden" id="userName" name="userName"
							value="<bean:write name="userName" scope="session"/>" />
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			
				<div class="title">
					<div class="title_img" id="title_m">
						<bean:message bundle="pjpy" key="dyszjfwh"/> 
					</div>
				</div>
				<fieldset>
				
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									
									学年：
								    <html:select property="xn" styleId="xn">
								       <html:options collection="xnList" property="xn" labelProperty="xn" />
								    </html:select>
								    学期：
								    <html:select property="xq" styleId="xq">
								       <html:option value=""></html:option>
								       <html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								    </html:select>
								    &nbsp;学号：
									<html:text property="xh" />
									&nbsp;姓名：
									<html:text property="xm" />
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="document.forms[0].go.value='go';refreshForm('/xgxt/dyszjfwh.do');">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
								    年级：
									<html:select property="nj" style="width:60px"
									onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;专业：
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									班级：
									<html:select property="bjdm" style="width:160px" styleId="bj">
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
							<font color="blue">提示：双击一行可以选定；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:present name="showjsxx">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="mark('dyszjfwh')">
									<logic:iterate id="v" name="s">
										<td align="left"><bean:write name="v" /></td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</logic:present>
							<logic:notPresent name="showjsxx">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="dyszwh(this.cells[0].innerText,this.cells[2].innerText)">
									<logic:iterate id="v" name="s">
										<td align="left"><bean:write name="v" /></td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</logic:notPresent>
						</table>
						<TABLE width="99%" id="rsTable1" class="tbstyle">
											<TR>
												<TD height=3></TD>
											</TR>
											<TR>
												<TD>
													<jsp:include flush="true"
														page="/sjcz/turnpage.jsp?form=PjpyForm"></jsp:include>
												</TD>
											</TR>
											<TR>
												<TD height=3></TD>
											</TR>
										</TABLE>
					</fieldset>
					<br /><br />
				</logic:notEmpty>
				<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<logic:notPresent name="showjsxx">
							<button class="button2" onclick="if(document.getElementById('xn').value=='' || document.getElementById('xq').value=='') {alert('请指定要计算的学年学期！！');return false;}AutoAccountCj('/xgxt/AutoAccount.do?xn='+document.getElementById('xn').value+'&xq='+document.getElementById('xq').value)"
								style="width:80px">
								自动计算
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:notPresent>
							<logic:present name="showjsxx">
							<button class="button2"
								onclick="mark('dyszjfwh')"
								style="width:80px">
								打 分
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							 </logic:present>
							<button class="button2"
								onclick="showTopWin('/xgxt/data_import.do',600,480)"
								style="width:80px">
								导入数据
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="dataExport()" style="width:80px">
								导出数据
							</button>
						</div>
					</center>
			</html:form>
			<div id="tmpdiv"></div>
				<logic:present name="autoCj">
					<logic:equal name="autoCj" value="ok">
						<script language="javascript">
      						alert("自动计算完成！");
	  					</script>
	  				</logic:equal>
	  				<logic:equal name="autoCj" value="no">
	  					<script language="javascript">
	  						alert("自动计算失败! ");
	  					</script>
	  				</logic:equal>
				</logic:present>
		</center>
	</body>
	<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
	</script>
</html>
