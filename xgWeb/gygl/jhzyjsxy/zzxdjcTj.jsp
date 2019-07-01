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
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript">
function tz(){
	$("tjlx").value = "";
	$("tjdw").value = "";
	$("xn").value = "";
	$("xq").value = "";
	$("yf").value = "";
	$("jcrq").value = "";
	refreshForm('/xgxt/jhzy_gygl.do?method=zzxdjcInfoManage');
}
function cx(){
	$("tjlx").value = "";
	$("tjdw").value = "";
	$("xn").value = "";
	$("xq").value = "";
	$("yf").value = "";
	$("jcrq").value = "";
	refreshForm('/xgxt/jhzy_gygl.do?method=zzxdjcQueryDate');
}
function getTjlxList(){
	var tjlx = $("tjlx").value;
	DWRUtil.removeAllOptions("tjdw");
	
	if (tjlx=="3") {
		var option0 = new Option("��ѧ��", "2");
		var option1 = new Option("���·�", "3");
		var option2 = new Option("������", "4");
		for (var i=0; i < 3; i++) {
	    	eval("document.forms[0].tjdw.options[i]=option" + i)
	    }
	} else {
		var option0 = new Option("��ѧ��", "1");
		var option1 = new Option("��ѧ��", "2");
		var option2 = new Option("���·�", "3");
		var option3 = new Option("������", "4");
		for (var i=0; i < 4; i++) {
	    	eval("document.forms[0].tjdw.options[i]=option" + i)
	    }
	}
	var tjdwT = $("tjdwT").value;
	if (tjdwT == null || tjdwT == "") {
		$("tjdw").selectedIndex = 0;
	} else {
		$("tjdw").value = tjdwT;
	}
	getTjdwList();
}
function getTjdwList(){
	var tjdw = $("tjdw").value;
	
	if (tjdw=="1") {
		$("xn").disabled = false;
		$("xq").disabled = true;
		$("yf").disabled = true;
		$("jcrq").disabled = true;
	} else if (tjdw=="2") {
		$("xn").disabled = false;
		$("xq").disabled = false;
		$("yf").disabled = true;
		$("jcrq").disabled = true;
	} else if (tjdw=="3") {
		$("xn").disabled = false;
		$("xq").disabled = true;
		$("yf").disabled = false;
		$("jcrq").disabled = true;
	} else if (tjdw=="4") {
		$("xn").disabled = true;
		$("xq").disabled = true;
		$("yf").disabled = true;
		$("jcrq").disabled = false;
	} else {
		$("xn").disabled = true;
		$("xq").disabled = true;
		$("yf").disabled = true;
		$("jcrq").disabled = true;
	}
}
function tjyz(){
	var tjdw = $("tjdw").value;
	var xn = $("xn").value;
	var xq = $("xq").value;
	var yf = $("yf").value;
	var jcrq = $("jcrq").value;
	
	if (tjdw=="1") {
		if (xn == null || xn == "") {
			alert("��ѡ����Ҫͳ�Ƶ�ѧ��!");
			return false;
		}
	} else if (tjdw=="2") {
		if (xn == null || xn == "") {
			alert("��ѡ����Ҫͳ�Ƶ�ѧ��!");
			return false;
		}
		if (xq == null || xq == "") {
			alert("��ѡ����Ҫͳ�Ƶ�ѧ��!");
			return false;
		}
	} else if (tjdw=="3") {
		if (xn == null || xn == "") {
			alert("��ѡ����Ҫͳ�Ƶ�ѧ��!");
			return false;
		}
		if (yf == null || yf == "") {
			alert("��ѡ����Ҫͳ�Ƶ��·�!");
			return false;
		}
	} else if (tjdw=="4") {
		if (jcrq == null || jcrq == "") {
			alert("��ѡ����Ҫͳ�Ƶļ������!");
			return false;
		}
	} else {
		alert("��ѡ��ͳ�Ƶ�λ!");
		return false;
	}
	refreshForm('/xgxt/jhzy_gygl.do?method=zzxdjcTj&go=go');
}
function dy(){
	if ($("rsTable") != null) {
		var tit = "";
		var xn = $("xn").value;
		var xq = document.getElementById('xq')[document.getElementById('xq').selectedIndex].text;
		var yf = $("yf").value;
		var jcrq = $("jcrq").value;
		var tjdw = $("tjdw").value;
		
		if (tjdw=="1") {
			tit += xn + "ѧ������Ϩ�Ƽ��ͳ�Ʊ�";
		} else if (tjdw=="2") {
			tit += xn + "ѧ�� "+xq+" ����Ϩ�Ƽ��ͳ�Ʊ�";
		} else if (tjdw=="3") {
			tit += xn + "ѧ��"+yf+"�·�����Ϩ�Ƽ��ͳ�Ʊ�";
		} else if (tjdw=="4") {
			tit += jcrq + "  ����Ϩ�Ƽ��ͳ�Ʊ�";
		}
		expTab('rsTable',tit,'');
	}
}
</script>
	<body>
		<center>
			<script language="javascript" src="js/commanFunction.js"></script>
			<script language="javascript" src="js/function.js"></script>
			<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="js/AjaxFunction.js"></script>
			<script language="javascript" src="js/prototype-1.6.0.3.js"></script>
			<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
			<script language="javascript" src="js/calendar.js"></script>
			<script language="javascript" src="js/calendar-zh.js"></script>
			<script language="javascript" src="js/calendar-setup.js"></script>
			
			<html:form action="/jhzy_gygl" method="post">
				<input type="hidden" id="showSelect" name="showSelect" value="yes"/>
				<input type="hidden" id="tjlxT" name="tjlxT" value="<bean:write name='rs1' property="tjlx" />" />
				<input type="hidden" id="tjdwT" name="tjdwT" value="<bean:write name='rs1' property="tjdw" />" />
				<input type="hidden" id="pkVStr" name="pkVStr"  value="" />
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã���Ԣ���� - ��Ϣά�� - ����Ϩ�Ƽ��ͳ�� 
					</div>
				</div>
				<fieldset>
					<legend>
						ͳ��ѡ��
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									ͳ������
									<html:select property="tjlx" styleId="tjlx"
										onchange="getTjlxList()" >
										<html:option value="1">��<bean:message key="lable.xsgzyxpzxy" /></html:option>
										<html:option value="2">��¥��</html:option>
										<html:option value="3">����Ԣ����Ա</html:option>
									</html:select>
									ͳ�Ƶ�λ
									<html:select property="tjdw" styleId="tjdw"
										onchange="getTjdwList()" >
										<html:option value="1">��ѧ��</html:option>
										<html:option value="2">��ѧ��</html:option>
										<html:option value="3">���·�</html:option>
										<html:option value="4">������</html:option>
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="tjyz();">
										ͳ��
									</button>
								</td>
							</tr>
							<tr>
							<td align="left" nowrap>
								ѧ��
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								ѧ��
								<html:select property="xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								�·�
								<html:select property="yf" styleId="yf">
									<html:option value=""></html:option>
									<html:options collection="yfList" property="yf"
										labelProperty="yf" />
								</html:select>
								�������
								<input type="text" style="cursor:hand;width:80px" readonly="readonly"
									onclick="return showCalendar('jcrq','y-mm-dd');" onblur="dateFormatChg(this);"
									value="<bean:write name='rs1' property="jcrq" />" name="jcrq"
									id="jcrq" />
							</td>
						</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
					<br />
					<br />
					<p align="center">
						δ�ҵ��κμ�¼��
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
									<tr style="cursor:hand">
										<logic:iterate id="v" name="s" offset="0">
											<td align="center">
												<bean:write name='v'/>
											</td>
										</logic:iterate>
									</tr>
							</logic:iterate>
						</table>
					</fieldset>
					<br />
					<br />
				</logic:notEmpty>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<button class="button2" onclick="dy();" style="width:80px">
							��ӡ�б�
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="tz();" style="width:80px">
							���¼��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="cx();" style="width:80px">
							�����ѯ
						</button>
					</div>
				</center>				
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
	<script language="javascript">
		getTjlxList();
		document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
		document.getElementById("btn").style.width = "96%";
		window.setInterval("initBTNTool('btn')",1);
	</script>
</html>
