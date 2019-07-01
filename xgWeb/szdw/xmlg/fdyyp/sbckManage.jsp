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
	<script language="javascript">	
	function delFdyyp(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var selall = document.getElementById('selall');
		var flag = false;
		
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if(flag){
			if (confirm("确定要删除所勾选的数据?")) {
				showTips('处理数据中，请等待......');
				var url = "/xgxt/xmlgfdyyp.do?method=sbckManage&doType=del";
				refreshForm(url);
			}
		}else{
			alert("请勾选要处理的数据");
			return false;
		}
	}
	
	function hzFdyyp(){
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
		dd_html += "<td colspan='2'>";
		dd_html += "----------------汇总时间---------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "学年:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='hzxn' id ='hzxn' onchange='chValue(this.id,this.value)'>" 
		dd_html += $('hzxn').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "开始月:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='kssj' id ='kssj' onchange='chValue(this.id,this.value)'>" 
		dd_html += $('kssj').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "结束月:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='jssj' id ='jssj' onchange='chValue(this.id,this.value)'>" 
		dd_html += $('jssj').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='hzbb()';>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd()'>关闭</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv.innerHTML = dd_html;
	}
	
	function time(id){
		return showCalendar(id,'y-mm-dd');
	}
	
	function hzbb(){
		if($("hzxn").value == ""){
			alert("请确定汇总年度");
			return false;
		}
		if($("kssj").value == ""){
			alert("请确定汇总开始月");
			return false;
		}
		if($("jssj").value == ""){
			alert("请确定汇总结束月");
			return false;
		}
		if(parseInt($("kssj").value)>parseInt($("jssj").value)){
			alert("开始月大于结束月，请确认");
			return false;
		}
		var url = "/xgxt/xmlgfdyyp.do?method=fdyypHz";
		
		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";

	}
	
	function chValue(id,value){
		$(id).value=value;
	}
	</script>
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/xmlgfdyyp" method="post">
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="isFdy" name="isFdy"
				value="<bean:write name="fdyQx" scope="session"/>" />
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<html:select property="hzxn" style="display:none">
				<html:options collection="ndList" property="nd" labelProperty="nd" />
			</html:select>
			<html:select property="kssj" style="display:none">
				<html:option value=""></html:option>
				<html:options collection="yfList" property="yf" labelProperty="yf" />
			</html:select>
			<html:select property="jssj" style="display:none">
				<html:option value=""></html:option>
				<html:options collection="yfList" property="yf" labelProperty="yf" />
			</html:select>	
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="title" />
				</div>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red"><bean:write name="msg" />
					</FONT>
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
										学年：				
										<html:select property="xn" style="">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>	
										&nbsp;&nbsp;学期：
										<html:select property="xq" style="width:80px">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
										</html:select>	
										&nbsp;&nbsp;职工号：
										<html:text property="zgh" style="" maxlength="20"></html:text>
										&nbsp;&nbsp;姓名：
										<html:text property="xm" style="" maxlength="20"></html:text>
										&nbsp;&nbsp;申报时间:
										<html:text  property="sbsj" styleId="sbsj" indexed="0"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('sbsj','y-mm-dd');"/>
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xmlgfdyyp.do?method=sbckManage');">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										所在部门：
										<html:select property="bmdm" style="width:300px" styleId="bmdm">
											<html:option value=""></html:option>
											<html:options collection="bmList" property="bmdm"
												labelProperty="bmmc" />
										</html:select>
										&nbsp;&nbsp;评分月份：
										<html:select property="yf" style="" onchange="onShow()">
											<html:option value=""></html:option>
											<html:options collection="yfList" property="yf" labelProperty="yf" />
										</html:select>月	
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
								<font color="blue">提示：单击表头可以排序;双击记录可查看详细.</font>
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
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);"
										ondblclick="showTopWin('/xgxt/xmlgfdyyp.do?method=fdyypCk&doType=view&pk='+curr_row.getElementsByTagName('input')[0].value,600,500);"
										style="cursor:hand">
										<td align="center">
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
											page="/sjcz/turnpage.jsp?form=xmlgSzdwForm"></jsp:include>
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
							<button type="button" class="button2"
								onclick="if(curr_row == null){alert('请选择要查看的申报记录！');return false;}showTopWin('/xgxt/xmlgfdyyp.do?method=fdyypCk&pk='+curr_row.getElementsByTagName('input')[0].value,600,500);"
								style="width:80px">
								修	改
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="delFdyyp()" style="width:80px">
								删	除
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="hzFdyyp()" style="width:80px">
								汇	总
							</button>
						</div>
					</center>
					</logic:equal>
				</div>
				<div id="tmpdiv"></div>
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
