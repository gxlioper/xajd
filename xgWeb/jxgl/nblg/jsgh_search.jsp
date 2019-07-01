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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="xyDisabled('xy');">
		<center>
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="/xgxt/js/jxglFunction.js"></script>
			<html:form action="/jxgl" method="post">
				<input type="hidden" id="userType" name="userType"  value="<bean:write name="userType"/>" />
				<input type="hidden" id="doType" name="doType"  value="<bean:write name="doType"/>" />
				<input type="hidden" id="pkValue" name="pkValue"  value="<bean:write name="pkValue"/>" />
				<input type="hidden" id="xyV" name="xyV"  value="" />
				<input type="hidden" id="zyV" name="zyV"  value="" />
				<input type="hidden" id="bjV" name="bjV"  value="" />
				
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：军训管理 - 信息维护 - 带队老师信息 - 教师工号查询
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
									<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" style="width:180px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>									
									&nbsp;姓名：
									<html:text property="xm" />
									&nbsp;性别：
									<html:select property="xb" styleId="xb">
										<html:option value=""></html:option>
										<html:option value="男">男</html:option>
										<html:option value="女">女</html:option>
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px"
										onclick="document.forms[0].go.value='go';refreshForm('/xgxt/jxgl.do?method=infoSearch');">
										查询
									</button>
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
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="sendJsghInfo()">
										<logic:iterate id="v" name="s" offset="0">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
						</table>
					</fieldset>
					<br />
					<br />
				</logic:notEmpty>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px;display:none" width="100%">
						<logic:equal value="yes" name="writeAble">
							<button type="button" class="button2" onclick="showTopWin('pjpy_zjsyzy.do?method=addSzjf',600,480)"
								style="width:80px">
								增 加
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="modiInfo('pjpy_zjsyzy.do?method=modiSzjf',600,480)"
								style="width:80px">
								修 改
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="batch('pjpy_zjsyzy.do?method=delSzjf','del')"
								style="width:80px">
								删 除
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="showTopWin('/xgxt/data_import.do',600,480)"
								style="width:80px">
								导入数据
							</button>						
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
								导出数据
							</button>
						</logic:equal>
					</div>
				</center>
				<logic:present name="result">
					<logic:equal value="true" name="result">
					<script>
						alert('操作成功!');
						Close();						
					</script>
					</logic:equal>
					<logic:equal value="false" name="result">
						<logic:notEmpty name="mes">
						<input name="mes" id="mes" value="${mes}"/>
						<script>
							alert(document.getElementById("mes").value);
						</script>
						</logic:notEmpty>
						<logic:empty name="mes">
						<script>
							alert('操作失败！');
						</script>
						</logic:empty>
					</logic:equal>
				</logic:present>
				
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
	<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
	</script>
</html>
