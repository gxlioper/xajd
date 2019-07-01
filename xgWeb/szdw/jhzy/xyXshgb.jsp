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
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/show_xyXshgb_list.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�˼������-�༶���-<bean:message key="lable.xsgzyxpzxy" />ѧ����ɲ�
				</div>
			</div>
			<div class="rightcontent">
				<fieldset>
					<legend>
						ѧ����ɲ�����
					</legend>
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
							value="sxjy_xyxshgbxxb" />
					<input type="hidden" id="tableName" name="tableName"
						value="view_xyxshgbxx" />
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									�꼶��
									<input type="hidden" name="njV" value=""/>
									<html:select property="nj" 
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" styleId="xy"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									ְ��
									<html:select property="zwdm"  styleId="zwdm">
									<html:option value=""></html:option>
									<html:options collection="bjzwList" property="xshgbdm" labelProperty="xshgbmc" />
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="szdwBjMust('/xgxt/show_xyXshgb_list.do?act=xyXshzw')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									<input type="hidden" name="zyV" value="<bean:write name="zydm" scope="request"/>"/>
									רҵ��
									<html:select property="zydm" styleId="zy"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>			
									&nbsp;&nbsp;�༶��
									<input type="hidden" name="bjV" value="<bean:write name="bjdm" scope="request"/>"/>
									<html:select property="bjdm"  styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									&nbsp;&nbsp;ѧ�ţ�
									<html:text property="xh" />
									&nbsp;&nbsp;������
									<html:text property="xm" />
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
					<legend>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
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
							<tr onclick="rowOnClick(this)" ondblclick="sxjyReportupdata('xyXshzw')" style="cursor:hand">
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
				<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
					onclick="showTopWin('xyXshgb_updata.do?act=xyXshzw',560,350)"
					style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" 
					onclick="sxjyReportupdata('xyXshzw')"
					style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="sxjyReportDel('xyXshzw')"
						style="width:80px">
						ɾ ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="impAndChkData()"
												style="width: 80px">
												��������
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataExport()"
										style="width: 80px">
										��������
									</button>
				</div>
			</div>
		</html:form>
		<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
		</script>
	</body>
</html>

