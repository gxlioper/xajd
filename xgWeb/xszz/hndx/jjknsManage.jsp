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
		<meta name="Copyright" content="������� zfsoft" />
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
	<script language="javascript" src="js/xszz/xszzFunction.js"></script>
	<script language="javascript">	
	//�������
	function plshSqInfo(shzt){
		var checkBoxArr = document.getElementsByName("primarykey_checkVal");
		var yzchk=true;
		for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked){
					yzchk =false;
					break;
				}
		}
		if(yzchk==true){
			alert("�빴ѡ��������˵�ѧ��");
			return;
		}
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
		dd_html += "----------------��ѡ����˼���---------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "����������:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='knsjb' onchange='chJb(this.value)'>" 
		dd_html += $('knsjb').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		if(shzt == "tg"){
			dd_html += "<button class='button2' onclick='shTg()';>ȷ��</button>";
		}else{
			dd_html += "<button class='button2' onclick='shBtg()';>ȷ��</button>";
		}
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button class='button2' onclick='closeAdd1()'>ȡ��</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
	}
	
	function chJb(knsjb){
		$("knsjb").value = knsjb;
	}

	function shTg() {
		if($("knsjb")){
			if($("knsjb").value == ""){
				alert("��ȷ������������");
				return false;
			}
		}
		sumitInfo('/xgxt/hndxXszz.do?method=jjknssh&shzt=tg','sh');
	}

	function shBtg() {
		if($("knsjb")){
			if($("knsjb").value == ""){
				alert("��ȷ������������");
				return false;
			}
		}
		sumitInfo('/xgxt/hndxXszz.do?method=jjknssh&shzt=btg','sh');
	}
	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/hndxXszz" method="post">
		<%@ include file="/xszz/hiddenValue.jsp"%>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
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
							�� ѯ
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										<!-- ѧ�� -->
										<logic:equal name="userType" value="stu">
											&nbsp;&nbsp;�꼶��
											<html:hidden property="queryequals_nj"/>
											<html:select property="queryequals_nj" style="" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj" labelProperty="nj" />
											</html:select>
										</logic:equal>
										<!-- ѧ�� end-->
										<!-- ��ѧ�� -->
										<logic:notEqual name="userType" value="stu">
											&nbsp;&nbsp;�꼶��
											<html:select property="queryequals_nj" style="" onchange="initZyList();initBjList()">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj" labelProperty="nj" />
											</html:select>
										</logic:notEqual>
										<!-- ��ѧ�� end-->
										&nbsp;&nbsp;ѧ�꣺
										<html:select property="queryequals_xn" style="" onchange="">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;ѧ�ţ�
										<html:text property="querylike_xh" styleId="xh" style="" maxlength="20"/>
										&nbsp;&nbsp;������
										<html:text property="querylike_xm" styleId="xm" style="" maxlength="20"/>
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<!-- ��� -->
										<logic:equal name="mklx" value="sh">
											<button class="button2" style="height:40px" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/hndxXszz.do?method=jjknssh');">
												��ѯ
											</button>
										</logic:equal>
										<!-- ��� end-->
										<!-- ��� -->
										<logic:equal name="mklx" value="jg">
											<button class="button2" style="height:40px" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/hndxXszz.do?method=jjknsjg');">
												��ѯ
											</button>
										</logic:equal>
										<!-- ��� end-->
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<!-- ѧ�� -->
										<logic:equal name="userType" value="stu">
											&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
											<html:hidden property="queryequals_xydm"/>
											<html:select property="queryequals_xydm" style="" styleId="xy" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm" labelProperty="xymc" />
											</html:select>
											&nbsp;&nbsp;רҵ��
											<html:hidden property="queryequals_zydm"/>
											<html:select property="queryequals_zydm" style="" styleId="zy" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm" labelProperty="zymc" />
											</html:select>
											&nbsp;&nbsp;�༶��
											<html:hidden property="queryequals_bjdm"/>
											<html:select property="queryequals_bjdm" style="" styleId="bj" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
											</html:select>
										</logic:equal>
										<!-- ѧ�� end-->
										<!-- ��ѧ�� -->
										<logic:notEqual name="userType" value="stu">
											&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
											<html:select property="queryequals_xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm" labelProperty="xymc" />
											</html:select>
											&nbsp;&nbsp;רҵ��
											<html:select property="queryequals_zydm" style="" styleId="zy" onchange="initBjList()">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm" labelProperty="zymc" />
											</html:select>
											&nbsp;&nbsp;�༶��
											<html:select property="queryequals_bjdm" style="" styleId="bj">
												<html:option value=""></html:option>
												<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
											</html:select>
										</logic:notEqual>
										<!-- ��ѧ�� end-->
									</td>
								</tr>
								<!-- ��� -->
								<logic:equal name="mklx" value="sh">
								<tr>
									<td>
										&nbsp;&nbsp;����������
										<html:select property="queryequals_knsjb" styleId="knsjb" style="">
											<html:option value=""></html:option>
											<html:option value="δָ��">δָ��</html:option>
											<html:options collection="knsjbList" property="dm" labelProperty="mc" />
										</html:select>
										<!-- �༶��� -->
										<logic:equal name="isBjsh" value="true">
											&nbsp;&nbsp;�༶��ˣ�
											<html:select property="queryequals_bjsh" style="" styleId="bjsh" onchange="">
												<html:option value=""></html:option>
												<html:options collection="shList" property="en" labelProperty="cn" />
											</html:select>
											&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��ˣ�
											<html:select property="queryequals_xysh" style="" styleId="xysh" onchange="">
												<html:option value=""></html:option>
												<html:options collection="shList" property="en" labelProperty="cn" />
											</html:select>
										</logic:equal>
										<!-- �༶��� end-->
										<!-- �ǰ༶��� -->
										<logic:equal name="isBjsh" value="false">
											<!-- <bean:message key="lable.xsgzyxpzxy" />��� -->
											<logic:equal name="userType" value="xy">
												&nbsp;&nbsp;�༶��ˣ�
												<html:hidden property="queryequals_bjsh"/>
												<html:select property="queryequals_bjsh" style="" styleId="bjsh" onchange="" disabled="true">
													<html:option value=""></html:option>
													<html:options collection="shList" property="en" labelProperty="cn" />
												</html:select>
												&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��ˣ�
												<html:select property="queryequals_xysh" style="" styleId="xysh" onchange="">
													<html:option value=""></html:option>
													<html:options collection="shList" property="en" labelProperty="cn" />
												</html:select>
											</logic:equal>
											<!-- <bean:message key="lable.xsgzyxpzxy" />��� end-->
											<!-- ѧУ��� -->
											<logic:notEqual name="userType" value="xy">
												&nbsp;&nbsp;�༶��ˣ�
												<html:hidden property="queryequals_bjsh"/>
												<html:select property="queryequals_bjsh" style="" styleId="bjsh" onchange="" disabled="true">
													<html:option value=""></html:option>
													<html:options collection="shList" property="en" labelProperty="cn" />
												</html:select>
												&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��ˣ�
												<html:hidden property="queryequals_xysh"/>
												<html:select property="queryequals_xysh" style="" styleId="xysh" onchange="" disabled="true">
													<html:option value=""></html:option>
													<html:options collection="shList" property="en" labelProperty="cn" />
												</html:select>
											</logic:notEqual>
											<!-- ѧУ��� end-->
										</logic:equal>
										<!-- �ǰ༶��� end-->
										&nbsp;&nbsp;ѧУ��ˣ�
										<html:select property="queryequals_xxsh" style="" styleId="xxsh" onchange="">
											<html:option value=""></html:option>
											<html:options collection="shList" property="en" labelProperty="cn" />
										</html:select>
									</td>
								</tr>
								</logic:equal>
								<!-- ��� end-->
								<!-- ��� -->
								<logic:equal name="mklx" value="jg">
									<tr>
										<td>
											&nbsp;&nbsp;����������
											<html:select property="queryequals_knsjb" styleId="knsjb" style="">
												<html:option value=""></html:option>
												<html:option value="δָ��">δָ��</html:option>
												<html:options collection="knsjbList" property="dm" labelProperty="mc" />
											</html:select>
											&nbsp;&nbsp;�༶��ˣ�
											<html:select property="queryequals_bjsh" style="" styleId="bjsh" onchange="">
												<html:option value=""></html:option>
												<html:options collection="shList" property="en" labelProperty="cn" />
											</html:select>
											&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��ˣ�
											<html:select property="queryequals_xysh" style="" styleId="xysh" onchange="">
												<html:option value=""></html:option>
												<html:options collection="shList" property="en" labelProperty="cn" />
											</html:select>
											&nbsp;&nbsp;ѧУ��ˣ�
											<html:select property="queryequals_xxsh" style="" styleId="xxsh" onchange="">
												<html:option value=""></html:option>
												<html:options collection="shList" property="en" labelProperty="cn" />
											</html:select>
										</td>
									</tr>
								</logic:equal>
								<!-- ��� end-->
							</thead>
						</table>
					</fieldset>
					<logic:empty name="rs">
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								��¼����
								<bean:write name="rsNum" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ.</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>	
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" id="selall" name="selall" onclick="selAll()">
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
									<tr onclick="rowOnClick(this);" style="cursor:hand" 
										ondblclick="showOpenInfo('/xgxt/hndxXszz.do?method=jjknsView','view',$('mklx').value,'1000','600')">
										<td align="center">
											<input type="checkbox" id="checkVal" 
												   name="primarykey_checkVal"  
												   value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
										</td>
										<logic:iterate id="v" name="s" offset="1" length="1">
										<td align="center">
											<bean:write name="v" />
										</td>
										</logic:iterate>
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
											page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
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
							<!-- ��� -->
							<logic:equal name="mklx" value="sh">
								<button class="button2" 
									onclick="showOpenInfo('/xgxt/hndxXszz.do?method=jjknsView','update',$('mklx').value,'1000','600')">
									�������
								</button>
								&nbsp;&nbsp;
								<button class="button2" 
									onclick="plshSqInfo('tg')"
									style="width:80px">
									���ͨ��
								</button>
								&nbsp;&nbsp;
								<button class="button2" 
									onclick="plshSqInfo('btg')"
									style="width:80px">
									��˲�ͨ��
								</button>
							</logic:equal>
							<!-- ��� end-->
							<!-- ��� -->
							<logic:equal name="mklx" value="jg">
								&nbsp;&nbsp;
								<button class="button2" 
									onclick="showOpenInfo('/xgxt/hndxXszz.do?method=jjknsView','add',$('mklx').value,'1000','600')"
									style="width:80px">
									��	��
								</button>
								&nbsp;&nbsp;
								<button class="button2" 
									onclick="showOpenInfo('/xgxt/hndxXszz.do?method=jjknsView','update',$('mklx').value,'1000','600')"
									style="width:80px">
									��	��
								</button>
								&nbsp;&nbsp;
								<button class="button2" 
									onclick="sumitInfo('/xgxt/hndxXszz.do?method=jjknsjg','del')"
									style="width:80px">
									ɾ	��
								</button>
								&nbsp;&nbsp;
								<button class="button2" onclick="impAndChkData()"
									style="width:80px">
									��������
								</button>
								&nbsp;&nbsp;
								<button class="button2" onclick="wjcfDataExport('hndxXszz.do?method=jjknsjg&doType=exp')"
									style="width:80px">
									��������
								</button>
							</logic:equal>
							<!-- ��� end-->
						</div>
					</center>
					</logic:equal>
				</div>
			</logic:empty>
			<div id="tmpdiv1"></div>
		</html:form>
		<logic:notEmpty name="result">
			<script>
				if($("message") && $("message").value != ""){
					alert($("message").value);
					$("message").value = "";
					$("doType").value = "";
				}
			</script>
		</logic:notEmpty>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
