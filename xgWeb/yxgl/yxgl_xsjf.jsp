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
		
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/yxglFunction.js"></script>
		<script language="javascript" src="js/sharedFunction.js"></script>
		<input type="hidden" id="flag" name="flag" value="<bean:write name="onload" />">
		<html:form action="yxgl_xsjf.do">
		<input type="hidden" id="realTable" name="realTable" value="newstuskb" >
		<input type="hidden" id="tableName" name="tableName" value="newstuskb" >
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�<bean:message bundle="yxgl" key="yxgl_xsjf" />
				</div>
			</div>
				<fieldset>
					<legend>
						�� ѯ
					</legend>				
					<table width="100%" class="tbstyle">
						<thead>	
							<tr>
								<td align="left">
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
									<logic:equal name="userType" value="xy" scope="session">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="refreshForm('yxgl_xsjf.do');getfocus('search_go')" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:equal>
									<logic:notEqual name="userType" value="xy" scope="session">
										<html:select property="xydm" style="width:180px" styleId="xy"
											onchange="refreshForm('yxgl_xsjf.do');getfocus('search_go')">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="refreshForm('yxgl_xsjf.do');getfocus('search_go')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;�༶��
									<html:select property="bjdm" style="width:180px" styleId="bj"
										onchange="refreshForm('yxgl_xsjf.do');getfocus('search_go')">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>									
								</td>
								<td width="50" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" id="search_go"
										onclick="search('yxgl_xsjf.do')"
										onkeypress="keyPressDo(this)" style="height:40px;">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;�����ţ�
									<html:text property="ksh" style="width:120px"></html:text>
									&nbsp;&nbsp;ѧ�ţ�
									<html:text property="xh" style="width:120px"></html:text>
									&nbsp;&nbsp;������
									<html:text property="xm" style="width:120px"></html:text>
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
								<logic:iterate id="tit" name="topTr" offset="0" length='1'>
										<td  style="display:none" id="<bean:write name="tit" property="en"/>"
											nowrap>
											<input type="hidden" value="<bean:write name="tit" property="cn" />" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="yxgltableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>						
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">	
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="yxglBdModi('yxgl_xsjf_one.do')">
										<td style="display:none">
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>											
										</td>
										<logic:iterate id="v" name="s" offset="1">
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
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">															
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="yxglBdModi('yxgl_xsjf_one.do');"
									style="width:80px">
								�� ��
							</button>							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="dataExport()" style="width:80px">
								��������
							</button>							
						</div>
					</center>
				</logic:equal>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

