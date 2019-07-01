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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">
		
		function save(url){
		if($("rsNum")){
			if($("rsNum").value == "0"){
				alert("请确定欲设置（取消）的学生！");
				return false;
			}else{
				if (confirm("确定进行该操作吗?")) {
					saveUpdate(url,'');
				}
			}
		}
		}
		
	</script>
	<body>
		<html:form action="zjcmCprs" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="realTable" id="realTable" value="zjcm_pjpy_cprssz" />
			<input type="hidden" name="rsNum" ud="rsNum" value="${rsNum}" styleId="rsNum"/>
			<input type="hidden" name="lx" ud="lx" value="${lx}" styleId="lx"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置:${title}
				</div>
			</div>			
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
								&nbsp;年级:
								<html:select property="queryequals_nj" style="width:100px"
										styleId="nj" >
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>
							</td>
							<td width="10" align="center" valign="middle" rowspan="2">
								<button type="button" class="button2" id="search_go"
											onclick="allNotEmpThenGo('zjcmCprs.do?method=cprsWh&doType=qry')" style="height: 25px">
											查 询
										</button>
										<br>
										<button type="button" class="button2" style="height:25px" id="cz"
											onclick="czSearchCond('xn-nj-xy-zy-bj');">
											重 置
								</button>
							</td>
						</tr>
						<tr>
							<td>
							&nbsp;院系:
								<logic:equal name="userType" value="xy">
								<html:select property="xydm" disabled="true" style="width:180px"
										styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
								</html:select>
								<html:hidden property="queryequals_xydm" value="${userDep}"/>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
								<html:select property="queryequals_xydm" style="width:180px"
										styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
								</html:select>
								</logic:notEqual>
								&nbsp;专业:
								<html:select property="queryequals_zydm" style="width:180px" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
								</html:select>
								&nbsp;班级:
								<html:select property="queryequals_bjdm" style="width:180px" styleId="bj"
									>
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
								</html:select>
								</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rsNum">
				<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
				</logic:empty>
			</logic:empty>
			<div id="tmpdiv1">
				
				</div>
			<logic:notEmpty name="rsNum">
				<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1" >
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								 style="cursor:hand">
										<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" style="display:none" checked id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>>
											   <input type="hidden" name="checkVal" id="checkVal" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
									</logic:iterate>
								
								<logic:iterate id="v" name="s" offset="1" length="4">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="5" length="1">
									<td nowrap>
										<input type="text" name="rs" value="${v}"  style="width:50px" onkeypress="return onlyNum(this,20)" 
											style="ime-mode:disabled"/>
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
								page="/sjcz/turnpage.jsp?form=zjcmCprsForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
				</logic:notEmpty>
			</logic:notEmpty>
			<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">	
				<logic:equal name="writeAble" value="yes">
					<button type="button" class="button2" onclick="save('zjcmCprs.do?method=cprsWh&doType=save')" style="width:80px">
						保   存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="wjcfDataExport('zjcmCprs.do?method=expDate')" style="width:80px">
						导出数据
					</button>
				<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:notEqual>
				</logic:present>
		</html:form>
	</body>
</html>
