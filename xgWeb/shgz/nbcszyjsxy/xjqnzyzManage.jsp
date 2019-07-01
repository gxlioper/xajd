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
	<base target="_self">
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script language="javascript">
</script>
	<body onload="check_user()">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/stcygl" method="post">
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="zyV" value=""/>
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã���Ṥ�� - ��Ϣά�� - �Ǽ�����־Ը��ͳ��
					</div>
				</div>
				<fieldset>
					<legend>
						�� ��
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
							    <td>
<%--							  ־Ը���Ǽ���--%>
<%--								<html:select property="xjzyzlb" >--%>
<%--										<html:option value="yxjzyz">һ�Ǽ�����־Ը��</html:option>--%>
<%--										<html:option value="exjzyz">���Ǽ�����־Ը��</html:option>--%>
<%--										<html:option value="sxjzyz">���Ǽ�����־Ը��</html:option>--%>
<%--								</html:select>--%>
								�꼶��
								<html:select property="nj" 
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;ѧ�ţ�
								<html:text property="xh" />
								&nbsp;&nbsp;������
								<html:text property="xm" />
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="refreshForm('/xgxt/stcygl.do?method=xjqnzyzManage&go=go')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" styleId="xy" 
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm" styleId="zy"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;�༶��
									<html:select property="bjdm" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
					<br />
					<br />
					<p align="center">
						δ�ҵ��κμ�¼��
						<br>
						<br>
						<br>
						<br>
						<br>
					</p>
					<div align="left" style="color: red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ע��
					<ul style="color: blue" >
						<li>
						��һ�Ǽ�����־Ը�ߡ�Ӧ��ʽ�μ�����־Ը�߷���һ�����ϣ�־Ը����ʱ���ۻ�40Сʱ����
						</li>
						<li>
						�����Ǽ�����־Ը�ߡ�Ӧ��ʽ�μ�����־Ը�߷���һ�����ϣ�־Ը����ʱ���ۻ�100Сʱ����
						</li>
						<li>
						�����Ǽ�����־Ը�ߡ�Ӧ��ʽ�μ�����־Ը�߷����������ϣ�־Ը����ʱ���ۻ�150Сʱ����
						</li>
					</ul>
					</div>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0" >
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand">	
									<logic:iterate id="v" name="s" offset="0">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<logic:notEmpty name="result">
			</logic:notEmpty>
<%--			<logic:equal value="yes" name="writeAble">--%>
<%--						<div class="buttontool" id="btn"--%>
<%--							style="position: absolute;left:1%;top:100px" width="100%">--%>
<%--							<button class="button2" onclick="dataExport()" style="width:80px">--%>
<%--								��������--%>
<%--							</button>--%>
<%--						</div>--%>
<%--				</logic:equal>				--%>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
