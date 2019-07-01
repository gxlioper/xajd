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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qgzx/qgzxFunction.js"></script>
	<script language="javascript">
	
	function setLsgw(){
	
		var checkBoxArr = document.getElementsByName("primarykey_checkVal");
		var yzchk=true;
		
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked){
				yzchk =false;
				break;
			}
		}
		if(yzchk==true){
			alert("请勾选需设置临时岗位的学生");
			return;
		}
		
		var gwsj = document.getElementsByName("gwsj");
		var gwmc = document.getElementsByName("gwmc");
		
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 250;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top)/ 2;
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<table width='300' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td>";
		dd_html += "----------------请选择岗位---------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='left'>";
		for(var i=0;i<gwsj.length;i++){
			dd_html += "<input type='checkbox' name='check_lsgwmc' value='"+gwsj[i].value+"'>"+gwmc[i].value +"&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td align='center'>";
		dd_html += "<button type='button' class='button2' onclick='saveLsgw()';>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>关闭</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
	}
	
	//保存临时岗位
	function saveLsgw(){
	
		var lsgwmc = document.getElementsByName("check_lsgwmc");
		var noChecked = true;
		
		for(var i=0;i<lsgwmc.length;i++){
			if(lsgwmc[i].checked){
			
				noChecked = false;
				
				var id = "gwmc"+i;
				var tmp1 = document.createElement("input");
				tmp1.type = "hidden";
				tmp1.name = "lsgwmc";
				tmp1.value = $(id).value;
				document.forms[0].appendChild(tmp1);
				
				id = "gwsj"+i;
				var tmp2 = document.createElement("input");
				tmp2.type = "hidden";
				tmp2.name = "lsgwsbsj";
				tmp2.value = $(id).value;
				document.forms[0].appendChild(tmp2);
			}
		}
		
		if(noChecked){
			alert("请至少选择一个临时岗位");
			return false;
		}
		
		var url = "/xgxt/gzdxQgzx.do?method=lsgwfpManage&doType=save";
		showTips('处理数据中，请等待......');
		refreshForm(url);
	}
	</script>
	<body onload="">
		<html:form action="/gzdxQgzx" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/qgzx/hiddenValue.jsp"%>
			<logic:iterate name="lsgwList" id="gw" indexId="num">
				<input type="hidden" name="gwsj" id="gwsj${num }" value="${gw.sj }"/>
				<input type="hidden" name="gwmc" id="gwmc${num }" value="${gw.dm }"/>
			</logic:iterate>
			<!-- 隐藏域 end-->
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="title" />
				</div>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
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
										<html:select property="queryequals_nj" style="" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
										&nbsp;&nbsp;年度：
										<html:hidden property="nd"/>
										<html:select property="queryequals_nd" style="" onchange="" disabled="true">
											<html:options collection="ndList" property="nd" labelProperty="nd" />
										</html:select>
										&nbsp;&nbsp;学年：
										<html:hidden property="xn"/>
										<html:select property="queryequals_xn" style="" onchange="" disabled="true">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;学期：
										<html:hidden property="xq"/>
										<html:select property="queryequals_xq" style="" onchange="" disabled="true">
											<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
										</html:select>
										&nbsp;&nbsp;学号：
										<html:text property="querylike_xh" styleId="xh" style="" maxlength="20"/>
										&nbsp;&nbsp;姓名：
										<html:text property="querylike_xm" styleId="xm" style="" maxlength="20"/>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/gzdxQgzx.do?method=lsgwfpManage')">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />：
										<!-- <bean:message key="lable.xsgzyxpzxy" />用户 -->
										<logic:equal name="userType" value="xy">
											<html:hidden property="queryequals_xydm"/>
											<html:select property="queryequals_xydm" style="" styleId="xy" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm" labelProperty="xymc" />
											</html:select>
										</logic:equal>
										<!-- <bean:message key="lable.xsgzyxpzxy" />用户 end-->
										<!-- 非<bean:message key="lable.xsgzyxpzxy" />用户 -->
										<logic:notEqual name="userType" value="xy">
											<html:select property="queryequals_xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm" labelProperty="xymc" />
											</html:select>
										</logic:notEqual>
										<!-- 非<bean:message key="lable.xsgzyxpzxy" />用户 end-->
										&nbsp;&nbsp;专业：
										<html:select property="queryequals_zydm" style="" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm" labelProperty="zymc" />
										</html:select>
										&nbsp;&nbsp;班级：
										<html:select property="queryequals_bjdm" style="" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
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
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>	
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" id="selall" name="selall" onclick="selAll()">
										</td>
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td align="center">
												<input type="checkbox" id="checkVal" 
												   name="primarykey_checkVal"  
												   value="<bean:write name="v" />">
											</td>
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1">
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
											page="/sjcz/turnpage.jsp?form=qgzxTyForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble">
					<div id="toolTipLayer"
						style="position:absolute; visibility: hidden" /></div>
					<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal name="writeAble" value="yes">
							<button type="button" class="button2"
								onclick="setLsgw()"
								style="width:80px">
								保&nbsp;&nbsp;存
							</button>
						</logic:equal>
					</div>
					</center>
					</logic:equal>
				</div>
			</logic:empty>
			<div id="tmpdiv1"></div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
				$("doType").value = "";
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
				$("doType").value = "";
			</script>
		</logic:equal>
		<logic:present name="message">
			<script>
				alert(''+document.getElementById('message').value);
			</script>
		</logic:present>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
