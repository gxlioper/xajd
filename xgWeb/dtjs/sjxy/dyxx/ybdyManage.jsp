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
	<script type='text/javascript' src='/xgxt/dwr/interface/getSjxyDtjsDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">	
	function dyzz(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var yzchk=true;
		for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked){
					yzchk =false;
					break;
				}
		}
		if(yzchk==true){
			alert("�빴ѡ��ת����ѧ��");
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
		dd_html += "----------------��Աת��ʱ��---------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "ʱ��:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='gzkssj' id='gzkssj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='saveYbdy()';>ȷ��</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>�ر�</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
	}
	
	function time(id){
		return showCalendar(id,'y-mm-dd');
	}
	
	function saveYbdy() {
		if($("gzkssj")){
			if($("gzkssj").value == ""){
				alert("��ȷ��ת��ʱ��");
				return false;
			}
		}
		var url = "/xgxt/sjxyDtjsDyxx.do?method=ybdyManage&doType=zz";
		showTips('���������У���ȴ�......');
		refreshForm(url);
	}
	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/sjxyDtjsDyxx" method="post">
			<%@ include file="/dtjs/hiddenValue.jsp"%>
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
										�꼶��
										<html:select property="nj" style="" onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
										&nbsp;&nbsp;ѧ�꣺
										<html:select property="nj" style=""
											onchange="initZyList();initBjList()">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;��ȣ�
										<html:select property="nj" style=""
											onchange="initZyList();initBjList()">
											<html:options collection="ndList" property="nd" labelProperty="nd" />
										</html:select>
										&nbsp;&nbsp;ѧ�ڣ�
										<html:select property="nj" style="" onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
										</html:select>
										&nbsp;&nbsp;&nbsp;��ְ״̬��
										<html:select property="zzzt" styleClass="select" style="" styleId="zzzt">
											<html:option value="all">&nbsp;</html:option>
											<html:option value="yes">��</html:option>
											<html:option value="no">��</html:option>
										</html:select>
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/sjxyDtjsDyxx.do?method=ybdyManage');">
											��ѯ
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />��
										<html:select property="xydm" style="" styleId="xy" onchange="getSszb();initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										&nbsp;&nbsp;רҵ��
										<html:select property="zydm" style="" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
										&nbsp;&nbsp;�༶��
										<html:select property="bjdm" style="" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										ѧ�ţ�
										<html:text property="xh" style="" maxlength="20"/>
										&nbsp;&nbsp;������
										<html:text property="xm" style="" maxlength="20"/>
										&nbsp;&nbsp;������֧����
										<html:select property="zbmc" style="" styleId="zbmc">
											<html:options collection="zbmcList" property="dm" labelProperty="mc" />
										</html:select>
									</td>
								</tr>
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
										ondblclick="showInfo('/xgxt/sjxyDtjsDyxx.do?method=ybdyUpdate','view','600','480')">
										<td align="center">
											<input type="checkbox" id="checkVal" name="checkVal" 
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
										</td>
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
											page="/sjcz/turnpage.jsp?form=sjxyDtjsForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<div id="tmpdiv1"></div>
					<logic:equal value="yes" name="writeAble">
					<div id="toolTipLayer"
						style="position:absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2" onclick="showTopWin('/xgxt/sjxyDtjsDyxx.do?method=ybdyUpdate',600,480);">
								��&nbsp;&nbsp;��
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="showInfo('/xgxt/sjxyDtjsDyxx.do?method=ybdyUpdate','update','600','480')">
								��&nbsp;&nbsp;��
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="sumitInfo('/xgxt/sjxyDtjsDyxx.do?method=ybdyManage','del')">
								ɾ&nbsp;&nbsp;��
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dyzz()" style="">
								����ת��
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="impAndChkData()"
								style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()"
								style="width:80px">
								��������
							</button>
						</div>
					</center>
					</logic:equal>
				</div>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
