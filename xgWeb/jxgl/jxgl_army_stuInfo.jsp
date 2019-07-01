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
	<script language="javascript">	
	</script>
	<body onload="xyDisabled('xy');initPage();initBjList();getLdjzList();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getJxLdjzList.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/ArmyStuInfo" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ѵ���� - ��Ϣά�� - ��ѵ����ά��
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<input type="hidden" name="njV" id="njV" />
					<input type="hidden" name="ndV" id="ndV" />
					<input type="hidden" name="xyV" id="xyV" />
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />
					<input type="hidden" name="ldbhV" id="ldbhV" />
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									�꼶��
									<html:select property="nj" style="width:80px"
										onchange="initZyList();initBjList()">
									</html:select>
									&nbsp;&nbsp;&nbsp;��ȣ�
									<html:select property="nd" style="width:100px" styleId="nd" 
										onchange="getLdjzList();">
									</html:select>
									&nbsp;&nbsp;&nbsp;ѧ�ţ�
									<html:text property="xh" style="width:85px"></html:text>
									&nbsp;&nbsp;&nbsp;������
									<html:text property="xm" style="width:85px"></html:text>
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/ArmyStuInfo.do')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
									</html:select>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList()">
									</html:select>
									&nbsp;&nbsp;�༶��
									<html:select property="bjdm" style="width:180px" styleId="bj">
										<html:option value=""></html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									�������ӣ�
									<html:select property="ldbh" style="width:180px" styleId="ldbh">
										<logic:equal name="xxdm" value="11407">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ldList" property="dm" labelProperty="mc" />
										</logic:equal>
									</html:select>
									&nbsp;&nbsp;�Ա�
									<html:select property="xb" style="width:90px"
										styleId="xb" onchange="getLdjzList();">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="Ů">Ů</html:option>
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
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="showDataFrame('viewArmyStu.do','modi',600,550)">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<logic:equal value="yes" name="writeAble" scope="request">
								<button type="button" class="button2" onclick="showDataFrame('viewArmyStu.do','add',600,550)"
									style="width:80px">
									�� ��
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2" onclick="showDataFrame('viewArmyStu.do','modi',600,550)"
									style="width:80px">
									�� ��
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2" onclick="showDataFrame('viewArmyStu.do','del',550,350)"
									style="width:80px">
									ɾ ��
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:equal>
							<button type="button" class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
								��������
							</button>
						</div>
					</center>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("�����ɹ�!");
				window.document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��!");
				window.document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
