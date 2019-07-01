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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/jsfunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
	</script>
		<html:form action="/prise_conf_rs" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 参数设置 - 自定义报表维护
				</div>
			</div>
			<input type="hidden" id="tabName" name="tabName" value="xbemy_qgzx_zdyxmdmb"/>
			<input type="hidden" id="realTable" name="realTable" value="xbemy_qgzx_zdyxmdmb"/>
			<input type="hidden" id="pkDel" name="pkDel" value=""/>
			<fieldset>
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									定义项目：
									<html:select property="xmdm" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xmList" property="xmdm"
											labelProperty="xmmc" />
									</html:select>
								</td>
								<td width="50" rowspan="1" align="center" valign="middle">
								<input type="hidden" name="go" value="a"/>
									<button type="button" class="button2" id="search_go"
										onclick="refreshForm('xbemyQgzx.do?method=reportManager&go=go');">
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
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序；</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)"
									ondblclick="javascript:if(curr_row==null){alert('请选择相应的行！')}else{showTopWin('xbemyQgzx.do?method=showReportManager&xmdm='+curr_row.cells[0].innerText + '&xmmc=' + curr_row.cells[1].innerText,800,600)}">
									
									<logic:iterate id="v" name="s">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
						<button type="button" class="button2" onclick="javascript:if(curr_row==null){alert('请选择相应的行！')}else{showTopWin('xbemyQgzx.do?method=showReportManager&xmdm='+curr_row.cells[0].innerText + '&xmmc=' + curr_row.cells[1].innerText,800,600)}"
							style="width:80px">
							修 改
						</button>
				</div>
			</center>
			<logic:present name="result">
				<logic:equal value="true" name="result">
				<script>
					alert('操作成功！');
					document.getElementById("search_go").onclick();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert('操作失败！');
					document.getElementById("search_go").onclick();
					</script>
				</logic:equal>
			</logic:present>
			<div id="tmpdiv"></div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
