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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">	
	
	function rychck(){	
		if(curr_row == null){
			alert('请选择要修改的信息!');
			return false;
		}
		
		var pk = curr_row.getElementsByTagName("input")[0].value;
		var url = "/xgxt/zjcm_rych.do?method=rychshOne&jg=yes";
		url+="&pk="+pk;
		showOpenWindow(url,800,600);
		//showTopWin(url,800,600);
	}

	function delRychPl(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var selall = document.getElementById('selall');
		var flag = false;
		
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if(flag){
			if (confirm("确定要删除所勾选的数据？")) {
				showTips('处理数据中，请等待......');
				var url = "/xgxt/zjcm_rych.do?method=shManage&doType=del";
				refreshForm(url);
			}
		}else{
			alert("请勾选要处理的数据");
			return false;
		}
	}
	</script>
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/zjcmZhfDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/zjcm_rych" method="post">
			<input type="hidden" name="pjxn" id="pjxn" value="${pjxn}"/>
			<input type="hidden" name="pjxq" id="pjxq" value="${pjxq}"/>
			<input type="hidden" name="xyV" id="xyV" value=""/>
			<input type="hidden" name="zyV" id="zyV" value=""/>
			<input type="hidden" name="bjV" id="bjV" value=""/>
			<input type="hidden" name="njV" id="njV" value=""/>
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name="title"/>
				</div>
			</div>
			<logic:notEmpty name="msg">
				<div align="center"><FONT color="red"><bean:write name="msg"/></FONT></div>
			</logic:notEmpty>
			<logic:empty name="msg">
			<div class="rightcontent">
				<fieldset>
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									年级：
									<html:select property="nj" style=""
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									学年：
									<html:select property="xn" style="" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									学期：
									<html:select property="xqdm" style="" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;学号：
									<html:text property="xh" style="width:85px" maxlength="20"></html:text>
									&nbsp;&nbsp;&nbsp;姓名：
									<html:text property="xm" style="width:85px" maxlength="20"></html:text>								
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/zjcm_rych.do?method=sqjgManage');">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" style="" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;专业：
									<html:select property="zydm" style="" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;班级：
									<html:select property="bjdm" style="" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td>
									获得荣誉称号：
									<html:select property="rychdm" style="" onchange="">
										<html:option value=""></html:option>
										<html:options collection="rychList" property="dm" labelProperty="mc" />
									</html:select>
									<bean:message key="lable.xsgzyxpzxy" />审核：
									<html:select property="xysh" style="" onchange="">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
									学校审核:
									<html:select property="xxsh" style="" onchange="">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>			
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
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
									<td>
										<input type="checkbox" id="selall" name="selall"
											onclick="selAll()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" ondblclick = "rychck();" style="cursor:hand">
									<td>
									<input type="checkbox" id="checkVal" name="checkVal" 
								 		value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
									</td>	
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2" >
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						<TABLE width="100%" id="Table" class="tbstyle">
							<TR>
								<TD height=3></TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=pjpyZjcmActionForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal name="writeAble" value="yes">
							<button type="button" class="button2"
								onclick="rychck();"
								style="width:80px">
								查看
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="delRychPl();"
								style="width:80px">
								批量删除
							</button>
						</logic:equal>
					</div>
				</center>
				<div id="tmpdiv"></div>
			</div>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
