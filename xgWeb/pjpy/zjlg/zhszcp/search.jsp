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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
	<script type="text/javascript" src="js/pjpy/pjpy_dwr.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript">
function jsfs(){
	var xn = $("xn").value;
	var dqxn = $("dqxn").value;
	if (xn == ""){
		alert("计算综合素质分必须选定学年！！");
		return false;
	}
	if (confirm("将要计算"+xn+"学年的综合素质分，确认吗？\n点击\"确定\"，计算数据；\n点击\"取消\"，将放弃计算！")) {
		showTips('处理数据中，请等待......');
		var url = "/xgxt/zjlgPjpy.do?method=zhszcp&type=save";
		refreshForm(url);
	}
}

function printBb(){
	var xn = $("xn").value;
	if(xn == ""){
		alert("请选择要打印的学年");
		return false;
	}
	if (confirm("将要打印"+xn+"学年的综合素质测评情况，确认吗？\n点击\"确定\"，打印；\n点击\"取消\"，将放弃打印！")) {
		var url = "/xgxt/zjlgPjpy.do?method=zhszcpPrint&xn="+xn;
		if($("zy").value!=""){
			url += "&zydm="+$("zy").value;
		}
		if($("bj").value!=""){
			url += "&bjdm="+$("bj").value;
		}
		window.open(url);
	}
}

function setCpzList(){
	var xydm = $("xy").value;
	var xn   = $("xn").value;
	getCpzfp.getCpzList(xn,xydm,function initTjList(data){
		if (data != null && typeof data == 'object') {
		var objId = "cpz";
		if($(objId) && $(objId).tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions(objId);
			if($('tableName')){
				$(objId).options[0] = new Option('','');   
			}
			DWRUtil.addOptions(objId,data,"cpzdm","xscpz");
		}else{
			showMsgWin("有错误出现：远程数据读取失败！");
		}
	}
	});
}
	</script>
	<body onload="bjLimit('bj');onShow();">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>	
		<center>
			<html:form action="/zjlgPjpy" method="post">
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName"/>" scope="request" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable"/>" scope="request"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope = "session"/>" />
			<input type="hidden" id="writeAble" name="writeAble" value="<bean:write name="writeAble"/>" />
			<input type="hidden" name="xyV" id="xyV" value="<bean:write name="xydm" scope="request"/>"/>
			<input type="hidden" name="zyV" id="zyV" value="<bean:write name="zyV" />"/>
			<input type="hidden" name="bjV" id="bjV" value="<bean:write name="bjV" />"/>
			<input type="hidden" name="njV" id="njV" value="<bean:write name="njV" />"/>
			<input type="hidden" name="xnV" id="xnV" value="<bean:write name="xnV" />"/>
			<input type="hidden" name="xhV" id="xhV" value="<bean:write name="xhV" />"/>
			<input type="hidden" name="xmV" id="xmV" value="<bean:write name="xmV" />"/>
			<input type="hidden" id="msg" name="msg" value="${msg}"/>
			<input type="hidden" name="dqxn" id="dqxn" value="<bean:write name="dqxn" />"/>
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name = "title" />
				</div>
			</div>
			<fieldset>
				<legend>
					查 询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
								&nbsp;&nbsp;年级：
								<html:select property="nj" style="width:60px"
										onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;学年：											
								<html:select property="xn" style="width:90px" styleId="xn"
										onchange="setCpzList();">
									<html:options collection="xnList" property="xn"
												labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学号：
								<html:text property="xh" styleId="xh" style="width:100px"maxlength="20"/>
								&nbsp;&nbsp;姓名：
								<html:text property="xm" style="width:100px"/>
							</td>
							<td width="10" rowspan="3" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/zjlgPjpy.do?method=zhszcp')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<input type="hidden" name="zyV" value=""/>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:180px" styleId="xy" 
									onchange="initZyList();initBjList();setCpzList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
									&nbsp;&nbsp;专业：
								<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" style="width:200px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								<input type="hidden" name="bjV" value=""/>
							</td>
						</tr>
						<tr>
							<td>
							&nbsp;&nbsp;参评组：
								<html:select property="cpzdm" style="width:150px"  styleId="cpz">
									<html:option value=""></html:option>
									<html:options collection="cpzList" property="cpzdm"
										labelProperty="cpzmc" />
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
							<font color="blue">提示：单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="top" name="topTr" offset="0" >
									<td id="<bean:write name="top" property="en"/>"
											onclick="tableSort(this)" nowrap>
										<bean:write name="top" property="cn" />		
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr>
							<td align="center">
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
								<bean:write name="s" property="zymc" />
							</td>
							<td align="center">
								<bean:write name="s" property="bjmc" />
							</td>
							<td align="center">
								<logic:lessThan  name="s" property="dycpf" value="1">
									<logic:equal name="s" property="dycpf" value="0">
									0
									</logic:equal>
									<logic:notEqual name="s" property="dycpf" value="0">
									0<bean:write name="s" property="dycpf" />
									</logic:notEqual>
								</logic:lessThan>
								<logic:greaterEqual name="s" property="dycpf" value="1">
									<bean:write name="s" property="dycpf" />
								</logic:greaterEqual>
							</td>
							<td align="center">
								<logic:lessThan  name="s" property="zycpf" value="1">
									<logic:equal name="s" property="zycpf" value="0">
									0
									</logic:equal>
									<logic:notEqual name="s" property="zycpf" value="0">
									0<bean:write name="s" property="zycpf" />
									</logic:notEqual>
								</logic:lessThan>
								<logic:greaterEqual name="s" property="zycpf" value="1">
									<bean:write name="s" property="zycpf" />
								</logic:greaterEqual>
							</td>
							<td align="center">
								<bean:write name="s" property="tycj" />
							</td>
							<td align="center">
								<logic:lessThan  name="s" property="zhcpf" value="1">
									<logic:equal name="s" property="zhcpf" value="0">
									0
									</logic:equal>
									<logic:notEqual name="s" property="zhcpf" value="0">
									0<bean:write name="s" property="zhcpf" />
									</logic:notEqual>
								</logic:lessThan>
								<logic:greaterEqual name="s" property="zhcpf" value="1">
									<bean:write name="s" property="zhcpf" />
								</logic:greaterEqual>
							</td>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>						
			</logic:notEmpty>
			<logic:equal value="yes" name="writeAble">
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<logic:notEqual name="userType" value="teacher">
					<logic:notEqual name="userType" value="xy">
					<button type="button" class="button2" onclick="jsfs();" style="width:80px">
						计算
					</button>
					&nbsp;
					<button type="button" class="button2" onclick="printBb();" style="width:80px">
						打印报表
					</button>
					&nbsp;
					</logic:notEqual>
					</logic:notEqual>
					<button type="button" class="button2"
						onclick="showOpenWindow('/xgxt/zjlgPjpy.do?method=zhszcpSz&type=edit',400,200);" style="width:80px">
						参数设置
					</button>
					&nbsp;
					<button type="button" class="button2" onclick="impAndChkData()"
						style="width:80px;display:none">
						导入数据
					</button>
					&nbsp;
					<button type="button" class="button2" onclick="dataExport()"
						style="width:80px;display:none">
						导出数据
					</button>
				</div>
			<logic:present name="delFzdx">
				<script>
					alert(''+document.getElementById('delFzdx').value);
				</script>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert(''+document.getElementById('msg').value);
				</script>
				</logic:present>
			</logic:equal>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>