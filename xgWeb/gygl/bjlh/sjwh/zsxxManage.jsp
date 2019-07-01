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
	<script type='text/javascript' src='/xgxt/dwr/interface/getBjlhGyglDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/gygl/bjlh/bjlhFunction.js"></script>
	<script language="javascript">	
	function chLx(value){
		if(value=="�о���"||value=="�ɽ���"){
			$("xslx").value=value;
		}else{
			$("xslx").value="";
		}
		setNjList($('xslx').value);
		setXyList($('xslx').value);
		setZyList($('xslx').value);
		setBjList($('xslx').value);
	}
	
	function zttf(){
		var nj = $("nj").value;
		var xydm = $("xydm").value;
		var zydm = $("zydm").value;
		var bjdm = $("bjdm").value;
		var lx = $("lx").value;
		var url = "/xgxt/bjlh_sjwh.do?method=zsxxManage&doType=zttf";
		if(nj != "" || xydm != "" || zydm != "" || bjdm != ""){
			var msg = "ȷ������ѡ��";
			if(nj !=""){
				msg+="�꼶";
			}
			if(xydm !=""){
				msg+="<bean:message key="lable.xsgzyxpzxy" />";
			}
			if(zydm !=""){
				msg+="רҵ";
			}
			if(bjdm !=""){
				msg+="�༶";
			}
			msg+="��"+lx+"ѧ��";
			msg+="�����˷�?\nȷ���밴��ȷ������ť��������ȡ��";
			if (confirm(msg)) {
				refreshForm(url);
			}
		}else{
			alert("��ȷ����Ҫ�����˷����꼶��<bean:message key="lable.xsgzyxpzxy" />��רҵ���༶��!");
		}
	}
	
	function checkErr(){
		var flag = $("sfyc").checked;
		if(flag){
			$("errlx").disabled = false;
		}else{
			$("errlx").disabled = true;
		}
	}
	
	function search(){
		var flag = $("sfyc").checked;
		if(flag){
			showTips('���ݼ���У���ȴ�......');
		}
		allNotEmpThenGo('/xgxt/bjlh_sjwh.do?method=zsxxManage');
	}
	
	function viewStuInfo(xh){
		var url = "/xgxt/bjlh_sjwh.do?method=zsxxView";
		url +="&xh="+xh;
		showTopWin(url,600,480);
	}
	</script>
	<body onload="checkErr()">
		<html:form action="/bjlh_sjwh" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" id="xslx" name="xslx" value="" />
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }" />
			<input type="hidden" id="title" name="title" value="${title }" />
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
										У����
										<html:select property="xqdm" style="" styleId="xqdm" onchange="setLdList()">
											<html:options collection="xqList" property="dm" labelProperty="mc" />
										</html:select>
										&nbsp;&nbsp;¥����
										<html:select property="lddm" style="" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
											<html:options collection="ldList" property="dm" labelProperty="mc" />
										</html:select>
										&nbsp;&nbsp;����������
										<html:select property="cs" style="" styleId="cs" onchange="setQsList();">
											<html:options collection="csList" property="dm" labelProperty="mc" />
										</html:select>
										&nbsp;&nbsp;���Һţ�
										<html:select property="qsh" style="" styleId="qsh" onchange="">
											<html:options collection="qsList" property="dm" labelProperty="mc" />
										</html:select>
										&nbsp;&nbsp;�꼶��
										<html:select property="nj" style="" onchange="setZyList($('xslx').value);setBjList($('xslx').value)">
											<html:options collection="njList" property="dm" labelProperty="mc" />
										</html:select>
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="search();">
											��ѯ
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />��
										<logic:equal name="isXy" value="yes">
										<html:select property="xydm" style="" styleId="xydm" disabled="true">
											<html:options collection="xyList" property="dm" labelProperty="mc" />
										</html:select>
										</logic:equal>
										<logic:equal name="isXy" value="no">
										<html:select property="xydm" style="" styleId="xydm" onchange="setZyList($('xslx').value);setBjList($('xslx').value)">
											<html:options collection="xyList" property="dm" labelProperty="mc" />
										</html:select>
										</logic:equal>
										&nbsp;&nbsp;רҵ��
										<html:select property="zydm" style="" styleId="zydm" onchange="setBjList($('xslx').value)">
											<html:options collection="zyList" property="dm" labelProperty="mc" />
										</html:select>
										&nbsp;&nbsp;�༶��
										<html:select property="bjdm" style="" styleId="bjdm">
											<html:options collection="bjList" property="dm" labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										ѧ�ţ�
										<html:text property="xh" style="width:85px" maxlength="20"/>
										&nbsp;&nbsp;������
										<html:text property="xm" style="width:85px" maxlength="20"/>
										&nbsp;&nbsp;ѧ�����ͣ�
										<logic:equal name="isAdmin" value="yes">
										<html:select property="lx" style="" styleId="lx" onchange="chLx(this.value)">
											<html:options collection="xsList" property="en" labelProperty="cn" />
										</html:select>
										</logic:equal>
										<logic:equal name="isAdmin" value="no">
										<html:select property="lx" style="" styleId="lx" disabled="true">
											<html:options collection="xsList" property="en" labelProperty="cn" />
										</html:select>
										</logic:equal>
										&nbsp;&nbsp;ס��״̬��
										<html:select property="zzbj" style="" styleId="zzbj">
											<html:options collection="zzList" property="en" labelProperty="cn" />
										</html:select>
										&nbsp;&nbsp;��������쳣��
										<html:checkbox property="sfyc" styleId="sfyc" onclick="checkErr()"></html:checkbox>
										<html:select property="errlx" style="" styleId="errlx" disabled="true">
											<html:options collection="errList" property="en" labelProperty="cn" />
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
										ondblclick="showInfo('/xgxt/bjlh_sjwh.do?method=zsxxUpdate','view','600','480')">
										<td align="center">
											<input type="checkbox" id="checkVal" name="checkVal" 
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<logic:equal name="errlx" value="lxbf">
													<a href="javascript:viewStuInfo(<bean:write name='v' />)"><bean:write name="v" /></a>
												</logic:equal>
												<logic:notEqual name="errlx" value="lxbf">
													<bean:write name="v" />
												</logic:notEqual>
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
											page="/sjcz/turnpage.jsp?form=bjlhGyglForm"></jsp:include>
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
							<button class="button2" onclick="showTopWin('/xgxt/bjlh_sjwh.do?method=zsxxUpdate',600,480);">
								��	��
							</button>
							&nbsp;&nbsp;
							<button class="button2" onclick="showInfo('/xgxt/bjlh_sjwh.do?method=zsxxUpdate','update','600','480')">
								��	��
							</button>
							&nbsp;&nbsp;
							<button class="button2" onclick="zttf()">
								�����˷�
							</button>
							&nbsp;&nbsp;
							<button class="button2" onclick="sumitInfo('/xgxt/bjlh_sjwh.do?method=zsxxManage','tf')">
								��	��
							</button>
							&nbsp;&nbsp;
							<button class="button2" onclick="sumitInfo('/xgxt/bjlh_sjwh.do?method=zsxxManage','del')">
								ɾ	��
							</button>
							&nbsp;&nbsp;
							<button class="button2" onclick="impAndChkData()"
								style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;
							<button class="button2" onclick="dataExport()"
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
