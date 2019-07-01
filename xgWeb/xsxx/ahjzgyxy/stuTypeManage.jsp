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
	<body onload="xyDisabled('xy');bjLimit('bj')">
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/xsxxAhjzgyxy" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生信息 - 学生信息 - 学生分类管理
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						查 询
					</legend>
					<input type="hidden" id="checkedValue" name="checkedValue" value="<bean:write name = "checkedValue" />" />
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="isFdy" name="isFdy"
						value="<bean:write name="isFdy" scope="session"/>" />
					<input type="hidden" id="userName" name="userName"
						value="<bean:write name="userName" scope="session"/>"/>
						<input type="hidden" name="zyV" id="zyV" />
						<input type="hidden" name="bjV" id="bjV" />
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr >
									<td align="left">
										年级：
										<html:select property="nj" 
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>	
										<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm" styleId="xy"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										&nbsp;&nbsp;专业：
										<html:select property="zydm" styleId="zy"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>																			
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2" style="height:60px" id="search_go"
											onclick="to_search()">
											查询
										</button>
									</td>
								</tr>								
								<tr>
									<td align="left">
										班级：
										<html:select property="bjdm" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
										&nbsp;&nbsp;学号：
										<html:text property="xh" style="width:80px" styleId="xh"></html:text>
										&nbsp;&nbsp;姓名：
										<html:text property="xm" styleId="xm" style="width:80px"></html:text>																				
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
									<logic:iterate name="topTr" id="tit">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" >
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr  style="cursor:hand">									
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td  width="10%">
											<logic:iterate id="n" name="v" offset="0" length="1">
											</logic:iterate>
											<bean:write name = "n"/>			
										</td>
										<td nowrap width="5%">
											<logic:iterate id="n" name="v" offset="1" length="1">
											</logic:iterate>
											<bean:write name = "n"/>											
										</td>
										<td nowrap  width="3%">
											<logic:iterate id="n" name="v" offset="2" length="1">
											</logic:iterate>
											<bean:write name="n" />
										</td>
										<td nowrap  width="5%">
											<logic:iterate id="n" name="v" offset="3" length="1">
											</logic:iterate>
											<bean:write name="n" />
										</td>
										<td nowrap  width="10%">
											<logic:iterate id="n" name="v" offset="4" length="1">
											</logic:iterate>
											<bean:write name="n" />
										</td>
										<td nowrap  width="10%">
											<logic:iterate id="n" name="v" offset="5" length="1">
											</logic:iterate>
											<bean:write name="n" />
										</td>
									</logic:iterate>
									<td >									
										<logic:iterate id="v" name="s" offset="0">
											<logic:iterate id="v1" name="v" offset="6" length="1">
											</logic:iterate>
											<logic:iterate id="v2" name="v" offset="7" length="1">
											</logic:iterate>											
												&nbsp;&nbsp;
												<input type="checkbox" name="lxids"
												id="<bean:write name = "v1"/>"
												value="<bean:write name = "v1"/>" />
											<bean:write name="v2" />
										</logic:iterate>										
									</td>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
                    <br>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
<%--					<logic:equal value="yes" name="writeAble" scope="request">--%>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2" onclick="refreshForm('/xgxt/xsxxAhjzgyxy.do?method=stuTypeSave&go=go');this.disabled=true"	>
								       保存结果
							 </button>
						</div>
<%--					</logic:equal>--%>
				</center>
			</div>					
				</logic:notEmpty>				
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<script>
		var lxids = document.getElementById("checkedValue").value.split('!!');	
		    for(i = 0; i < lxids.length; i++){
			   if($(lxids[i])){
				  document.getElementById(lxids[i]).checked=true;
			   }
		    }
		function to_search(){
		   var bjdm="";
		   var xh="";
		   var xm="";
		   if($("bjdm")){
		     bjdm = $("bjdm").value;
		   }
		   if($("xh")){
		     xh = $("xh").value;
		   }
		   if($("xm")){
		     xm = $("xm").value;
		   }
		   if(xh==""&&xm==""){
		     if(bjdm==""){
		        alert("请选择班级！");
		        return false;
		     }
		   }
		   refreshForm('/xgxt/xsxxAhjzgyxy.do?method=stuTypeManage&go=go');
		   $("search_go").disabled=true
		}
		</script>
	</body>
</html>
