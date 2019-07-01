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
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript">
	
	function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
	}
	
	function checkDisabled(user,ele){
		var userType = $('userType').value;
		if(user == userType){
			var temp = ele.split('-');
			for(var i=0;i<temp.length;i++){
				if(document.getElementById(temp[i])){
					document.getElementById(temp[i]).disabled = 'disabled';
				}
			}
		}
	}
</script>
	<body
		onload="checkDisabled('xy','xy'); checkDisabled('stu','nj-xh-xm-xy-zy-bj');">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/rcsw_xwzswh.do?method=getInfo" method="post">
				<input type="hidden" name="xyV" value="" />
				<input type="hidden" name="zyV" value="" />
				<input type="hidden" name="bjV" value="" />
				<input type="hidden" id="tableName" name="tableName"
					value="<bean:write name="tableName"/>" />
				<input type="hidden" id="realTable" name="realTable"
					value="<bean:write name="realTable"/>" />
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope = "session"/>" />
				<input type="hidden" id="title" name="title"
					value="<bean:write name="title"/>" />
				<input type="hidden" id="writeAble" name="writeAble"
					value="<bean:write name="writeAble"/>" />
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：
						<bean:write name="title" />
					</div>
				</div>
				<fieldset>
					<legend>
						查询条件
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									年级：
									<html:select styleId="nj" property="queryequals_nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;学号：
									<logic:equal value="stu" name="userType">
										<input type="hidden" name="queryequals_xh" value="${xh }" } />
									</logic:equal>
									<html:text styleId="xh" property="querylike_xh"></html:text>
									&nbsp;&nbsp;姓名：
									<html:text styleId="xm" property="querylike_xm"></html:text>
								</td>
								<td width="10" align="center" valign="middle" rowspan="3">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" id="search_go" style="height:40px"
										onclick="allNotEmpThenGo('/xgxt/rcsw_xwzswh.do?method=xwzswh')">
										查 询
									</button>
								</td>
							</tr>
							<tr>
								<td>
									<bean:message key="lable.xsgzyxpzxy" />：
									<logic:equal name="userType" value="xy">
										<input type="hidden" name="queryequals_xydm"
											value="${userDep}" />
									</logic:equal>
									<html:select property="queryequals_xydm" style="width:180px"
										styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;专业：
									<html:select property="queryequals_zydm" style="width:180px"
										styleId="zy" onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;班级：
									<html:select property="queryequals_bjdm" style="width:180px"
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
							<font color="blue">提示：双击一行可以选定；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="all" value="all" onclick="chec()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="modi('/xgxt/rcsw_xwzswh.do?method=xwzsModi&type=modi&act=query')">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="primarykey_xh" id="pkV"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
					<TABLE width="99%" id="Table" class="tbstyle">
						<TR>
							<TD height=3></TD>
						</TR>
						<TR>
							<TD>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=xwzswhForm"></jsp:include>
							</TD>
						</TR>
						<TR>
							<TD height=3></TD>
						</TR>
					</TABLE>

				</logic:notEmpty>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<logic:equal value="yes" name="writeAble">
					<button type="button" class="button2"
						onclick="showTopWin('/xgxt/rcsw_xwzswh.do?method=xwzsAdd',700,500)"
						style="width:80px">
						增加
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="if(curr_row == null){alert('请选择要修改的行!');return false;}else{modi('/xgxt/rcsw_xwzswh.do?method=xwzsModi&type=modi')}"
						style="width:80px">
						修 改
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="if(curr_row == null){alert('请选择要删除的行!');return false;}else{dataBatch('/xgxt/rcsw_xwzswh.do?method=xwzswh&type=del')}"
						style="width:80px">
						删除
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
					<button type="button" class="button2"
						onclick="wjcfDataExport('rcsw_xwzswh.do?method=xwzsExp')"
						style="width:80px">
						导出数据
					</button>
					<logic:present name="result">
						<input type="hidden" id="message" value="${message }" />
						<script type="text/javascript">
								alert(document.getElementById('message').value);
							</script>
					</logic:present>

		</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
