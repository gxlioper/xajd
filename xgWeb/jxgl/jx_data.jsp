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
	<body onload="xyDisabled('xydm');">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<script language="javascript" src="/xgxt/js/jxgl.js"></script>
			<html:form action="/Army" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã�
					<bean:write name="tips" scope="request" />
					</div>
				</div>			   
				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="act" name="act"
						value="<bean:write name="act" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<input type="hidden" id="pk" name="pk"
						value="<bean:write name="pk" scope="request"/>" />
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									�꼶��
									<html:select property="nj" styleId="nj" style="width:90px"
										onchange="refreshPage('/xgxt/Army.do?','jx_data')">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" styleId="xydm" style="width:180px"
										onchange="refreshPage('/xgxt/Army.do?','jx_data')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm" styleId="zydm" style="width:180px"
										onchange="refreshPage('/xgxt/Army.do?','jx_data')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" id="go" name="go" value="a"/>
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/Army.do?')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									�༶��
									<html:select property="bjdm" styleId="bjdm" style="width:230px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									&nbsp;&nbsp;ѧ�ţ�
									<html:text property="xh" styleId="xh" />
									&nbsp;&nbsp;������
									<html:text property="xm" styleId="xm" />
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
							<font color="blue">��ʾ��˫��һ�п���ѡ����������ͷ��������</font>
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
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="">
									<logic:iterate id="v" name="s">
										<td align="left">
											<input type="hidden" value="<bean:write name="v"/>" />
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
						<logic:equal value="yes" name="writeAble" scope="request">
						<button type="button" class="button2" onclick="viewMore('add')" style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="viewMore('modi')" style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="viewMore('del')" style="width:80px">
							ɾ ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2"
							onclick="impAndChkData();"
							style="width:80px">
							��������
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2"
							onclick="dataExport('/xgxt/expData.do')"
							style="width:80px">
							��������
						</button>
						</logic:equal>												
					</div>
				<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
			   </script>
			    <logic:equal value="yes" name="done">
					<script language="javascript">
      						//alert("�����ɹ���");
	  				�������� document.getElementById("search_go").click();
	  				</script>
				</logic:equal>
				<logic:equal  value="no" name="done">
					<script language="javascript">
	  						//alert("����ʧ��! ");
	  						document.getElementById("search_go").click();
	  					</script>
				</logic:equal>
			</html:form>
		</center>
	</body>
</html>
