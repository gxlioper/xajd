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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="xyDisabled('xy')">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/pjpyFunction.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_whlgdx.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript" src="js/bbld.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/webPrint.js"></script>
	
	
		<script type="text/javascript">
			function chec(){
			
			var checkBoxArr = document.getElementsByName("cbv");
	var selall = document.getElementById('rychxx');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			if (checkBoxArr[i].disabled==true) {
				checkBoxArr[i].checked = false;	
			} else {
				checkBoxArr[i].checked = true;
			}
			
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}	
  			}
  	function viewSh() {
			var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			var url = "pjpy_gzdx_viewJxjSh.do?lb=rych&pkValue=" + pkValue;
			showTopWin(url, 650, 500);
		}
		</script>
		<html:form action="/prise_conf_rs" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<logic:notEqual value="12872" name="xxdm">
				<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
				<input type="hidden" id="realTable" name="realTable" value="xsrychb" />
			</logic:notEqual>
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }"/>
			<input type="hidden" name="zyV" id="zyV"/>
			<input type="hidden" name="bjV" id="bjV"/>
			<input type="hidden" name="userName" id="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }"/>
			<fieldset>
				<legend>
					条件选择
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								学年：
								<html:select property="xn" style="width:90px" disabled="true"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;年级：
								<html:select property="nj" style="width:70px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;
								荣誉称号：
								<html:select property="xmdm" style="width:145px" styleId="jxjdm">
									<html:option value=""></html:option>
									<html:options collection="rychList" property="rychdm"
										labelProperty="rychmc" />
								</html:select>
								&nbsp;状态:
								<html:select property="zt" styleId="zt" >
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="不通过">不通过</html:option>
									<html:option value="通过">通过</html:option>
								</html:select>
								&nbsp;学号:
								<html:text property="xh" styleId="xh" style="width:100px"></html:text>
								&nbsp;姓名:
								<html:text property="xm" styleId="xm" style="width:100px"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('/xgxt/credit_check.do')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" style="width:165px"
								onchange="initZyList();initBjList()" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
									&nbsp;
								专业：
								<html:select property="zydm"  onchange="initBjList()" style="width:150px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;
								班级：
								<html:select property="bjdm" style="width:150px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								&nbsp;
								<input id="chgMode" type="checkbox"
									style="border:0px;display:none" />
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<div id="result">
				<div class="searchcontent">
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
								<font color="blue">提示：双击一行可以查看相信信息，并可以改变审核状态；单击表头可以排序； </font>
							</legend>
								<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" name="rychxx" value="all" onclick="chec()">
										</td>
										<logic:iterate id="tit" name="topTr" offset="2">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>										
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" align="center"
										style="cursor:hand;background-color:<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"
										ondblclick="viewSh()">
										<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="checkbox" name="cbv" id="cbv" value="<bean:write name="v"/>">											
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
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
						<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">
							<button class="button2" onclick="checkAll('pass')" style="width:100px">
									审核通过
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="checkAll('nopass')" style="width:100px">
									审核不通过
							</button>
							</div>
					</logic:equal>
					<div id="tmpdiv"></div>
					<logic:present name="result">
					<logic:equal value="true" name="result">
						<script>
						alert("操作成功!");
						document.getElementById("search_go").click();
						</script>
					</logic:equal>
					<logic:equal value="false" name="result">
						<script>
						 alert("操作失败!");
						</script>
					</logic:equal>
					</logic:present>
		</html:form>
 <script type="text/javascript" src="js/bottomButton.js"></script> 
</body>
</html>
