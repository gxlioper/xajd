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
		function modi(url){
			if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		function save(url){
		if($("rsNum")){
			if($("rsNum").value == "0"){
				alert("请确定欲设置（取消）的班级！");
				return false;
			}else{
				if (confirm("确定进行该操作吗?")) {
					saveUpdate(url,'');
				}
			}
		}
		}
		function chkData(){
			var pc="";
			if($("stpc")){
				pc=$("stpc").value;
			}
			if(pc!=""){
				save('ghxyBjryf.do?method=bjryfWh&doType=modi&pc='+pc);
			}else{
				alert("请先选择批次！");
				return false;
			}
		}
		function djzh(){
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
		dd_html += "----------------请选择批次---------------";
		dd_html += "</td>";
			dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
	
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "批次:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='stpc' id ='stpc'>" 
		dd_html += $('pc').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
	
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='chkData()'>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>关闭</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		document.getElementById('tmpdiv1').innerHTML = dd_html;
	}
	</script>
	<body>
		<html:form action="ghxyBjryf" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="realTable" id="realTable" value="nbty_xszz_jtjjkns" />
			<input type="hidden" name="rsNum" id="rsNum" value="${rsNum}" styleId="rsNum"/>
			<input type="hidden" name="lx" id="lx" value="${lx}" styleId="lx"/>
			<div class="title">
				<div class="title_img" id="title_m">
					所在位置：${title }		
				</div>
			</div>
			<div class="xxk">
			    			
			    		<logic:equal name="lx" value="wh">
							<ul>
								<li id="001l"
									class="xxk_on_l"></li>
								<li id="001m"
									onclick="refreshForm('ghxyBjryf.do?method=bjryfWh')" class="xxk_on_m">
									&nbsp;
									班级荣誉分维护
									&nbsp;
								</li>
								<li id="001r"
									class="xxk_on_r"></li>
							</ul>
							<ul>
								<li id="002l"
									class="xxk_off_l"></li>
								<li id="002m"
									onclick="refreshForm('ghxyBjryf.do?method=bjryfCx')" class="xxk_off_m">
									&nbsp;
									班级荣誉分查询
								</li> 
								<li id="002r"
									class="xxk_off_r"></li>
							</ul>
							</logic:equal>
							<logic:equal name="lx" value="cx">
							<ul>
								<li id="001l"
									class="xxk_off_l"></li>
								<li id="001m"
									onclick="refreshForm('ghxyBjryf.do?method=bjryfWh')" class="xxk_off_m">
									&nbsp;
									班级荣誉分维护
									&nbsp;
								</li>
								<li id="001r"
									class="xxk_off_r"></li>
							</ul>
							<ul>
								<li id="002l"
									class="xxk_on_l"></li>
								<li id="002m"
									onclick="refreshForm('ghxyBjryf.do?method=bjryfCx')" class="xxk_on_m">
									&nbsp;
									班级荣誉分查询
								</li> 
								<li id="002r"
									class="xxk_on_r"></li>
							</ul>
							</logic:equal>
			  			</div>				
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								<!-- 班级荣誉等级隐藏域 -->
									<html:select property="pc" style="display : none" styleId='pc' onchange="">
									<html:option value=""></html:option>
									<html:options collection="ryfPcList" property="pcdm"
										labelProperty="pcmc" />
								</html:select>
								<logic:equal name="lx" value="wh">
									&nbsp;&nbsp;学年：
								<html:select property="dqxn" styleId="dqxn" value="${dqxn}" disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								<html:hidden property="queryequals_xn" value="${dqxn}"/>
								&nbsp;&nbsp;学期：
								<html:select property="dqxq" styleId="dqxq" disabled="true" value="${dqxq}">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								<html:hidden property="queryequals_xq" value="${dqxq}"/>
								</logic:equal>
								
								<logic:equal name="lx" value="cx">
								&nbsp;&nbsp;学年：
								<html:select property="queryequals_xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学期：
								<html:select property="queryequals_xq" styleId="xq">
									<html:option value="" ></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								</logic:equal>
								&nbsp;&nbsp;年级：
								<html:select property="queryequals_nj"  styleId="nj"
									onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								<logic:equal name="lx" value="cx">
								&nbsp;&nbsp;批次：
								<html:select property="queryequals_pc"  styleId="pc">
									<html:options collection="ryfPcList" property="pcdm"
										labelProperty="pcmc" />
								</html:select>
								</logic:equal>
								
							</td>
							<td width="10" align="center" valign="middle" rowspan="2">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go"
									onclick="allNotEmpThenGo('ghxyBjryf.do?method=bjryfWh&doType=qry')" style="height: 25px">
									查 询
								</button>
								<br>
								<button type="button" class="button2" style="height:25px" id="cz"
											onclick="czSearchCond('xn-xq-pc-xy-zy-bj-nj');">
											重 置
								</button>
							</td>
						</tr>
						<tr>
						<td>
							&nbsp;&nbsp;<bean:message key="lable.xb" />：
							<logic:equal name="isFdy" value="true">
							  <html:select  property="queryequals_xydm" style="width:180px"
							   styleId="xy" onchange="initZyList();initBjList();">
							     <html:option value=""></html:option>
							     <html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
								</html:select>
							</logic:equal>
							<logic:notEqual name="isFdy" value="true">
								<logic:equal name="userType" value="xy">
									<html:select  property="xydm" style="width:180px"
							  		 styleId="xy" onchange="initZyList();initBjList();">
							   		  <html:option value=""></html:option>
							    		 <html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
									<html:hidden property="queryequals_xydm" value="${userDep}"/>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
									 <html:select  property="queryequals_xydm" style="width:180px"
							  		 styleId="xy" onchange="initZyList();initBjList();">
							     		<html:option value=""></html:option>
							     		<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
							</logic:notEqual>
							&nbsp;&nbsp;专业：
							<html:select property="zydm" style="width:180px" styleId="zy"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp;班级：
							<html:select property="bjdm" style="width:180px" styleId="bj">
								<logic:notEqual value="yes" name="isBzr">
									<html:option value=""></html:option>
								</logic:notEqual>
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
								<logic:iterate id="tit" name="topTr" offset="0" indexId="index">
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap style="width:60px">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								 style="cursor:hand">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="plbjdm" id="plbjdm" checked="checked" style="display : none"
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>>
											   <input type="hidden" name="checkVal" id="checkVal" value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
									</logic:iterate>
								<td style="width:150px">
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:equal name="lx" value="wh">
								<logic:iterate id="v" name="s" offset="2" >
										<td>
											<input type="text" name="xmfz" style="width:30px" onkeypress="return onlyNum(this,20)"/>
										</td>
								</logic:iterate>
								</logic:equal>
								<logic:equal name="lx" value="cx">
								<logic:iterate id="v" name="s" offset="2" >
										<td>
											${v}
										</td>
								</logic:iterate>
								</logic:equal>
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
								page="/sjcz/turnpage.jsp?form=ghxyBjryfForm"></jsp:include>
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
					<logic:equal name="lx" value="wh">
					<button type="button" class="button2"
						onclick="djzh()"
						style="width:80px">
						保 存
					</button>
					</logic:equal>
					</logic:equal>
					</div>
				
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
