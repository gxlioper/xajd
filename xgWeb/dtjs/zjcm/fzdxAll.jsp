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
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript">
	function searchCheck(url){
		var xh = document.getElementById("xhV").value;
		if(xh != ""){
			if(xh.match(/^\d+\.{0,1}\d{0,3}$/)==null){
				alert("学号必需为数字！");
				return false;
			}
		}
		allNotEmpThenGo(url);
	}
	function fzks(){
	var checkBoxArr = document.getElementsByName("checkVal");
	var selall = document.getElementById('selall');
	var flag = false;
	var kssj = document.getElementById('kssj').value;

	if(kssj == ""){
		alert('请确认发展对象开始的时间!');
		return false;
	}
	
	if (confirm("确认要将这些学生的发展对象开始时间设置为"+kssj+"吗?")) {
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
	
		if(selall.checked){
			showTips('处理数据中，请等待......');
			refreshForm('/xgxt/dtjs_zjcm.do?method=addFzdx&go=go');
		
		} else {
			if(flag){
				showTips('处理数据中，请等待......');
				refreshForm('/xgxt/dtjs_zjcm.do?method=addFzdx&go=go');
			}
			else{
				alert('请选择要设置为发展对象的学生!');
			}
		} 
	}
	}
	
	/*function setSj(){
	var checkBoxArr = document.getElementsByName("checkVal");
	var splitSignOne = "!!SplitSignOne!!";
	var temppk="";
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			temppk=temppk+checkBoxArr[i].value.replace("&","").replace("&","").replace("&","").replace("&","")+splitSignOne;
		}
	}
	showTopWin('/xgxt/dtjs_zjcm.do?method=setSj&pk='+temppk,300,240);
	}*/
	
	function a(){
	var checkBoxArr = document.getElementsByName("checkVal");
	var selall = document.getElementById('selall');
	var flag = false;
	var jssj = document.getElementById('jssj').value;

	if(jssj == ""){
		alert('请确认发展对象结束的时间!');
		return false;
	}
	
	if (confirm("确认要将这些学生的发展对象结束时间设置为"+jssj+"吗?")) {
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
	
		if(selall.checked){
			showTips('处理数据中，请等待......');
			refreshForm('/xgxt/dtjs_zjcm.do?method=addFzdx&go=go');
		
		} else {
			if(flag){
				showTips('处理数据中，请等待......');
				refreshForm('/xgxt/dtjs_zjcm.do?method=addFzdx&go=go');
			}
			else{
				alert('请选择要设置为发展对象的学生!');
			}
		} 
	}
	}
	
	</script>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>	
		<center>
			<html:form action="/dtjs_zjcm" method="post">
			<input type="hidden" name="fz" value="<bean:write name = "fz" />"/>
			<input type="hidden" id="numFzdx" name="numFzdx" value="${numFzdx}"/>
			<input type="hidden" id="numNoYb" name="numNoYb" value="${numNoYb}"/>
			<input type="hidden" id="numIsYb" name="numIsYb" value="${numIsYb}"/>
			<input type="hidden" id="addFzdx" name="addFzdx" value="${addFzdx}"/>
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
								<html:select property="nj" style="width:90px"
										onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;学年：											
								<html:select property="xn" style="width:90px" styleId="xn"
										onchange="">
									<html:options collection="xnList" property="xn"
												labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;年度：
								<html:select property="nd" styleId="nd">
									<html:options collection="ndList" property="nd"
											labelProperty="nd" />
								</html:select>
								&nbsp;&nbsp;学期：
								<html:select property="xq" style="width:60px" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>
							</td>
							<td width="10" rowspan="3" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="searchCheck('/xgxt/dtjs_zjcm.do?method=fzdxAll')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<input type="hidden" name="zyV" value=""/>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:180px" styleId="xy" 
									onchange="initZyList();initBjList();">
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
								<html:select property="bjdm" style="width:120px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								<input type="hidden" name="bjV" value=""/>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;&nbsp;学号：
								<html:text property="xh" styleId="xhV" maxlength="10"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名：
								<html:text property="xm" style="width:100px" maxlength="10"/>
								<logic:notEqual name="fz" value="fz">
								&nbsp;&nbsp;发展对象开始时间：
								<html:text property="kssj" onblur="dateFormatChg(this)" styleId="kssj"
										style="cursor:hand;" onclick="return showCalendar('kssj','y-mm-dd');" />
								<font color="red">*</font>
								</logic:notEqual>
								<logic:equal name="fz" value="fz">
								&nbsp;&nbsp;发展对象结束时间：
								<html:text property="jssj" onblur="dateFormatChg(this)" styleId="jssj"
										style="cursor:hand;" onclick="return showCalendar('jssj','y-mm-dd');" />
								<font color="red">*</font>
								</logic:equal>
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
							<font color="blue">提示：双击一行可以选定；单击表头可以排序；勾选欲确认的数据</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" id="selall" name="selall"
										onclick="selAll()">
								</td>
								<logic:iterate id="top" name="topTr" offset="1" length="1">
									<td id="<bean:write name="top" property="en"/>"
											onclick="tableSort(this)" nowrap>
										<bean:write name="top" property="cn" />		
									</td>
								</logic:iterate>
								<logic:iterate id="top" name="topTr" offset="2">
									<td id="<bean:write name="top" property="en"/>"
											onclick="tableSort(this)" nowrap>
										<bean:write name="top" property="cn" />	
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
								<tr>
								<td>
									<input type="checkbox" id="checkVal" name="checkVal" 
								 	value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
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
										<td>
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
											page="/sjcz/turnpage.jsp?form=dtjszjcmForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
				</fieldset>						
			</logic:notEmpty>

			<div class="buttontool">
			<logic:notEqual name="fz" value="fz">
				<button type="button" class="button2" style="width:100px"
					onclick="fzks()">
					确认
				</button>
			</logic:notEqual>
			<logic:equal name="fz" value="fz">
				<button type="button" class="button2" style="width:100px"
					onclick="a()">
					确认
				</button>
			</logic:equal>
			</div>
			<logic:present name="numFzdx">
				<script>
					alert(''+document.getElementById('numFzdx').value);
				</script>
			</logic:present>
			<logic:present name="numIsYb">
				<script>
					alert(''+document.getElementById('numIsYb').value);
				</script>
			</logic:present>
			<logic:present name="numNoYb">
				<script>
					alert(''+document.getElementById('numNoYb').value);
				</script>
			</logic:present>
			<logic:present name="addFzdx">
				<script>
					alert(''+document.getElementById('addFzdx').value);
				</script>
			</logic:present>
				<logic:present name="inserted">
					<logic:equal name="inserted" value="ok">
						<script>
    					alert("设置成功！");
    					dialogArgumentsQueryChick();
						Close();
   					 </script>
					</logic:equal>
					<logic:equal name="inserted" value="no">
						<script>
    					alert("设置失败,请重新选择！");
   					 </script>
					</logic:equal>
				</logic:present>
			</html:form>
		</center>
	</body>
</html>