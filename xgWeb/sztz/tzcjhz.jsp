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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<body>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
        %>
		<center>
			
			<html:form action="/sztzcj_hz_query" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰλ�ã�������չ - ͳ�Ʒ��� - ������չ�ɼ����� - ��ѯ
					</div>
				</div>
				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<input type="hidden" name="zyV" id="zyV" />
			        <input type="hidden" name="bjV" id="bjV" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />

					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									�꼶��
									<html:select property="nj"
										style="width:90px;background-color:#FFFFFF"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm"
										style="width:180px;background-color:#FFFFFF"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm"
										style="width:180px;background-color:#FFFFFF" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/sztzcj_hz_query.do');">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									�༶��
									<html:select property="bjdm"
										style="width:120px;background-color:#FFFFFF" styleId="bj">
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
							��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ������һ�п���ѡ����������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand">
									<logic:iterate id="v" name="s">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
					<TABLE width="99%" id="rsTable" class="tbstyle">
						<TR>
							<TD height=3></TD>
						</TR>
						<TR>
							<TD>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=sztzForm"></jsp:include>
							</TD>
						</TR>
						<TR>
							<TD height=3></TD>
						</TR>
					</TABLE>
				</logic:notEmpty>
				<br>
				<br>
				<br>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="sztzHz('dnhz')" style="width:80px">
						�������
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="sztzHz('lnhz')" style="width:80px">
						�������
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="sztzDataExport()"
						style="width:80px">
						��������
					</button>
				</div>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>				
	</body>
</html>
