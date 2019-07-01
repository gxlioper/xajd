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
	<body onload="bjDisabled('nj-xy-zy-bj');myxyDisabled('xy');removeXnXq();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		
    <html:form action="/csmz_sztz.do?method=data_search" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
	        <input type="hidden" name="userType" id="userType" 
		        value="<bean:write name="userType" scope="request"/>">	
		     <input type="hidden" name="userName" id="userName" 
		        value="<bean:write name="userName" scope="session"/>">		
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<div class="rightcontent">
				<fieldset>
					<legend>
						查 询 
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
						<logic:equal value="yes" name="xmsb">
								<tr>
									<td align="left">
										学年：
										<html:select property="xn" style="width:100px" styleId="xn">
										<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;学期：
										<html:select property="xq" style="width:90px" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
										&nbsp;&nbsp;所属科目：
										<html:select property="kmdm" style="width:150px"
											styleId="kmdm">
											<html:option value=""></html:option>
											<html:options collection="kmList" property="kmdm"
												labelProperty="kmm" />
										</html:select>	
										&nbsp;&nbsp;项目名称：
										<html:text property="xmmc" styleId="xmmc"></html:text>								
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/csmz_sztz.do?method=data_search&act='+$('act').value)">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left">										
										部门：
										<html:select property="xydm" styleId="xy"
											style="background-color:#FFFFFF;">
											<option value=""></option>
											<html:options collection="bmList" property="bmdm"
												labelProperty="bmmc"></html:options>
										</html:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目级别：
										<html:select property="xmjb" styleId="xmjb"
											style="background-color:#FFFFFF;">
											<option value=""></option>
											<html:options collection="xmjbList" property="en"
												labelProperty="cn"></html:options>
										</html:select>
									</td>
								</tr>
							</logic:equal>
							<logic:equal value="no" name="xmsb">
								<tr>
									<td align="left">
										年级：
										<html:select property="nj" style="width:80px"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
										<logic:equal value="no" name="ptcx">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年：
										<html:select property="xn" style="width:100px" styleId="xn"
											onchange="genNdList(this)">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期：
										<html:select property="xq" style="width:90px" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
										</logic:equal>
										&nbsp;&nbsp;&nbsp;学号：
										<html:text property="xh" style="width:85px"></html:text>
										&nbsp;&nbsp;&nbsp;姓名：
										<html:text property="xm" style="width:85px"></html:text>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/csmz_sztz.do?method=data_search&act='+$('act').value)">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm" style="width:180px" styleId="xy"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										&nbsp;&nbsp;专业：
										<html:select property="zydm" style="width:180px" styleId="zy"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
										&nbsp;&nbsp;班级：
										<html:select property="bjdm" style="width:180px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
							</logic:equal>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
						<p align="center">
							未找到任何记录！
						</p>						
						<logic:equal value="view_csmz_cgsbxx" name="tableName">
						    <logic:equal value="true" name="isSH">
						    <ul style="color: red;list-style:none;">
						    <li>
						    1、已分配权限的班干部用户只能查看及审核本班学生申报的项目成果。
						    </li>
						    <li>
						    2、已分配权限的<bean:message key="lable.xsgzyxpzxy" />用户只能查看及审核本<bean:message key="lable.xsgzyxpzxy" />内学生申报的项目成果。
						    </li>
						    <li>
						    3、已分配权限的学校用户只能查看学生申报的<font color="blue" style="font-family: sans-serif">"班级级别"</font>项目成果，能查看及审核'班级级别以外'的项目成果。
						    </li>
						    <li>
						    4、<font color="blue" style="font-family: sans-serif">"班级级别"</font>项目成果审核流程：由已分配权限的班干部用户审核通过-&gt;已分配权限的<bean:message key="lable.xsgzyxpzxy" />用户审核通过后即可有效 
						    </li>
						    <li>
						    5、<font color="blue" style="font-family: sans-serif">"班级级别以外"</font>的项目审核流程：须由已分配权限的班干部用户审核通过-&gt;已分配权限的<bean:message key="lable.xsgzyxpzxy" />用户审核通过-&gt;已分配权限的学校用户审核通过后才有效
						    </li>
						    </ul>
						    </logic:equal>
						</logic:equal>	
						<logic:equal value="view_csmz_tzxmxx" name="tableName">
						    <logic:equal value="true" name="isSH">
						    <ul style="color: red;list-style:none;">						    
						    <li>
						    1、已分配权限的班干部用户只能查看及审核本班内<font color="blue" style="font-family: sans-serif">"班级级别"</font>的项目申报。
						    </li>
						    <li>
						    2、已分配权限的<bean:message key="lable.xsgzyxpzxy" />用户只能查看及审核本<bean:message key="lable.xsgzyxpzxy" />内，已"通过"班级审核的<font color="blue" style="font-family: sans-serif">"班级级别"</font>的项目申报，
						    查看及审核本<bean:message key="lable.xsgzyxpzxy" />内的<font color="blue" style="font-family: sans-serif">"<bean:message key="lable.xsgzyxpzxy" />级别"</font>的项目申报。
						    </li>
						    <li>
						    3、已分配权限的学校用户只能查看<font color="blue" style="font-family: sans-serif">"班级级别"</font>的项目申报，能查看及审核已"通过"<bean:message key="lable.xsgzyxpzxy" />审核的<font color="blue" style="font-family: sans-serif">"<bean:message key="lable.xsgzyxpzxy" />级别"</font>的项目申报,
						    查看及审核<font color="blue" style="font-family: sans-serif">"学校级别以上"</font>的项目申报。
						    </li>						    
						    </ul>
						    </logic:equal>
						</logic:equal>						
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:equal value="true" name="isSH">
							<font color="blue">提示：双击一行可以查看详细信息并进行审核；单击表头可以排序;</font>
							</logic:equal>
							<logic:equal value="false" name="isSH">
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序;</font>
							</logic:equal>
						</legend>
<%--						<div--%>
<%--							style="overflow-x:auto;overflow-y:auto;width:810px;height:330px;">--%>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="2">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" >
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:equal value="true" name="isSH"><!-- 审核查询时 -->
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);"
											style="cursor:hand;background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        "ondblclick="CheckDo()">
											<td>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td >
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</logic:equal>
								<logic:equal value="false" name="isSH"><!-- 非审核查询时 -->
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);"
											style="cursor:hand;"
											ondblclick="MyMoreDo('view')">
											<td>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td >
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</logic:equal>
							</table>
<%--						</div>--%>
				  </fieldset>				 
				  <logic:equal value="false" name="isSH">
					<TABLE width="99%" id="rsTable" class="tbstyle">
						<TR>
							<TD height=3></TD>
						</TR>
						<TR>
							<TD>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=sztzForm"></jsp:include>
							</TD>
						</TR>
						<TR>
							<TD height=3></TD>
						</TR>
					</TABLE>
					</logic:equal>
				</logic:notEmpty>
				 <br><br> <br><br>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				  <center>
				  <logic:equal value="false" name="isSH">
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble" scope="request">
							<button class="button2" onclick="MyMoreDo('add')"
								style="width:80px">
								增 加
							</button>								
							&nbsp;&nbsp;&nbsp;&nbsp;							
										<button class="button2" onclick="MyMoreDo('modi')"
								style="width:80px">
								修 改
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="button2" onclick="MyMoreDo('del')"
								style="width:80px">
								删 除
							</button>							
<%--							&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--									<button class="button2"--%>
<%--								onclick="showTopWin('/xgxt/data_import.do',600,480)"--%>
<%--								style="width:80px">--%>
<%--								导入数据--%>
<%--							</button>--%>
						</logic:equal>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="dataExport()" style="width:80px">
							导出数据
						</button>
						<br>
						&nbsp;&nbsp;
					</div>
					</logic:equal>
					<logic:equal value="true" name="isSH">
					<logic:equal value="csmz_tzcgb" name="realTable">
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">					
					<button class="button2" onclick="allBjSh('/xgxt/csmz_sztz.do?method=all_check&bjdm='+document.forms[0].bjdm.value)"
								style="width:80px">
								全班通过
					</button>
					<br>
						&nbsp;&nbsp;						
					</div>
					</logic:equal>
					</logic:equal>
				</center>
			</div>		
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>		
    </body>
	<logic:equal name="autoChk" value="ok" scope="request">
		<script language="javascript">
                   alert("自动审核完成！");
                   document.getElementById('search_go').click();	      
	    </script>
	</logic:equal>
	<logic:equal name="autoChk" value="no" scope="request">
		<script language="javascript">
                 alert("自动审核失败！");
                 document.getElementById('search_go').click();	      
 	    </script>
	</logic:equal>
<script type="text/javascript">

function CheckDo(){
    var	w = 700;
	var	h = 500;
	var realTable = document.getElementById("realTable").value;
	if(realTable=="csmz_tzxmb"){
		url = "/xgxt/csmz_sztz.do?method=tzxm_sh";
	}else if(realTable=="csmz_tzcgb"){
	    url = "/xgxt/csmz_sztz.do?method=tzcg_sh";
	}
	url += "&pkValue=";
	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	showTopWin(url, w, h);	
}


function allBjSh(url){
    confirmTxt = "全班通过审核将以某学年、学期项目\n及班级为单位进行批量通过审核! \n";
    if(document.forms[0].xn.value==""){
		alert(confirmTxt+"请选择学年！");
		return false;
    }
    if(document.forms[0].xq.value==""){
        alert(confirmTxt+"请选择学期！");
        return false;
    }
    if (document.forms[0].bjdm.value == "") {
		alert("请选择班级！");
		return false;
	}else{
	   confirmTxt = "全班通过审核将耗费较长的时间，确定要开始自动审核吗？";
		if (confirm(confirmTxt)) {		
		    alert("点击\"确定\"后开始审核！");		    
		    sztzShowTips('正在进行整批审核，请稍候......');
			refreshForm(url);
			return true;
		} else {
			return false;
		}
	}	
}
function myxyDisabled(ele) {
    var userT = document.getElementById("userType").value;
	if (userT == "xy"||userT =="stu") {
		var tmp = ele.split("-");
		for (i = 0; i < tmp.length; i++) {
			document.getElementById(tmp[i]).disabled = true;
		}
	}
}
</script>
</html>	
