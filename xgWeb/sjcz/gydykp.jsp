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
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
	</head>
	<base target="_self">
	<body  onload="bjLimit('bj');">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<script language="javascript" src="js/function.js"></script>
			<script language="javascript" src="js/xsgyglFunction.js"></script>
			<script type='text/javascript'src='/xgxt/dwr/interface/GetListData.js'></script>
			<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
			<script type='text/javascript' src='dwr/util.js'></script>
			<script language="javascript" src="js/AjaxFunction.js"></script>
	        <script language="javascript" src="js/String.js"></script>
			<html:form action="/gydykp.do" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰλ�ã���ǰ����λ�ã���Ԣ���� - ��Ԣ��������
					</div>
				</div>
				<fieldset>
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />
					<input type="hidden" name="qshV" id="qshV" />
					<input type="hidden" name="lcV" id="lcV" value=""/>
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="xxdm" name="xxdm"
						value="<bean:write name="xxdm" scope="request"/>" />
                    <input type="hidden" name="isFdy" id="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />					
					<legend>
						�� ѯ
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									�꼶��
									<html:select property="nj" 
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��

									<html:select property="xydm" 
										onchange="initZyList();initBjList()" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>

									&nbsp;&nbsp;רҵ��
									<html:select property="zydm"  styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" id="go" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="document.forms[0].go.value='go';refreshForm('/xgxt/gydykp.do');this.disabled=true">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" >
									�༶��
									<html:select property="bjdm"  styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
									&nbsp;&nbsp;ѧ�ţ�
									<html:text property="xh"
										onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';refreshForm('/xgxt/gydykp.do');}" />
									&nbsp;&nbsp;������
									<html:text property="xm"
										onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';refreshForm('/xgxt/gydykp.do');}" />

								</td>
							</tr>
							<tr>
								<td>									
<%--									<logic:notPresent name="showhzy">--%>
<%--								    ��ǰ��ס¥����--%>
<%--								        <html:select property="lddm" style="width:120px"--%>
<%--											onchange="GetQshList()" styleId="lddm">--%>
<%--											--%>
<%--											<html:options collection="ldList" property="lddm"--%>
<%--												labelProperty="ldmc" />--%>
<%--										</html:select>								--%>
<%--								    &nbsp;&nbsp;��ǰ��ס���ң�--%>
<%--								       <html:select property="qsh" style="width:110px" styleId="qsh">--%>
<%--											<html:option value=""></html:option>--%>
<%--											<html:options collection="qshList" property="dm"--%>
<%--												labelProperty="mc" />--%>
<%--										</html:select>--%>
<%--									</logic:notPresent>--%>
<%--									<logic:present name="showhzy">--%>
										&nbsp;&nbsp;��ǰ��ס¥��:
									<html:select property="lddm"  styleId="lddm"
										onchange="getLcList()">
										
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>
										&nbsp;&nbsp;��ǰ��ס¥��:
										<html:select property="lc"  styleId="lc"
										onchange="getQshList2()">
										
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select>
										&nbsp;&nbsp;��ǰ��ס����:
										<html:select property="qsh"  styleId="qsh">
										
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
<%--									</logic:present>--%>

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
							<font color="blue">��ʾ��˫��һ�п��Բ鿴�����ӡ��޸ĺ�ɾ�����ݣ�������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td onclick="tableSort(this)" nowrap>
										ѧ��
									</td>
  									<td onclick="tableSort(this)" nowrap>
										����
									</td>
  									<td onclick="tableSort(this)" nowrap>
										�Ա�
									</td>
  									<td onclick="tableSort(this)" nowrap>
										�༶����
									</td>
  									<td onclick="tableSort(this)" nowrap>
										��ǰ��ס¥������
									</td>
  									<td onclick="tableSort(this)" nowrap>
										��ǰ��ס���Һ�
									</td>
  									<td onclick="tableSort(this)" nowrap>
										���ҵ绰
									</td>
  									<td onclick="tableSort(this)" nowrap>
										��ǰѧ��(��)�ܷ�
									</td>
                                   
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="showTopWin('stu_gydykp_info.do?Pkxh='+curr_row.cells[0].innerText.trim(),'900','800');">	
									<logic:iterate id="v" name="s" offset="0">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>
