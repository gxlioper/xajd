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
	
	<script language="javascript" src="/xgxt/js/function.js"></script>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript">
	function expDate_lx(){
		document.forms[0].action = "/xgxt/leaveExpDate.do";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}	
	</script>
	<body onload="check_user();">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/listingQuery.do" method="post">
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="session"/>" />	
				<input type="hidden" name="xyV" value=""/>
				<input type="hidden" name="zyV" value=""/>
				<input type="hidden" name="bjV" value=""/>		
				<input type="hidden" name="tableName" value="view_xslxxx"/>		
				<input type="hidden" name="realTable" value="xslxxxb"/>
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰλ��: ѧ����У-��У�嵥��ѯ						
					</div>
				</div>
				<logic:notEqual value="student" name="user">
					<fieldset>
						<legend>
							�� ѯ
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">										
										�꼶��
										<logic:notPresent name="njList">
											<html:select property="nj" style="width:90px"
											onchange="initZyList();initBjList();" disabled="disabled">
											<html:option value=""></html:option>
											</html:select>
										</logic:notPresent>
										
										<logic:present name="njList">
										<html:select property="nj" style="width:90px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>		
										</logic:present>								
										&nbsp;&nbsp;ѧ�ţ�
										<html:text property="xh" />
										&nbsp;&nbsp;������
										<html:text property="xm" />
										
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/listingQuery.do')">
											��ѯ
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>										
										<bean:message key="lable.xsgzyxpzxy" />��
										<logic:notPresent name="xyList">
											<html:select property="xydm" style="width:180px"
											styleId="xy" disabled="disabled">
											<html:option value=""></html:option>
										</html:select>
										</logic:notPresent>
										
										<logic:present name="xyList">
										<html:select property="xydm" style="width:180px"
											onchange="refreshForm('/xgxt/listingQuery.do')" styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										</logic:present>
										
										&nbsp;&nbsp;רҵ��
										<logic:notPresent name="zyList">
											<html:select property="zydm" style="width:180px" styleId="zy" disabled="disabled">
											<html:option value=""></html:option>
										</html:select>
										</logic:notPresent>
										<logic:present name="zyList">
											<html:select property="zydm" style="width:180px" styleId="zy"
											onchange="refreshForm('/xgxt/listingQuery.do')">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
										</logic:present>									
										
										&nbsp;&nbsp;�༶��
										<logic:notPresent name="bjList">
											<html:select property="bjdm" style="width:120px" styleId="bj" disabled="disabled">
												<html:option value=""></html:option>												
											</html:select>
										</logic:notPresent>
										<logic:present name="bjList">
											<html:select property="bjdm" style="width:120px" styleId="bj">
												<html:option value=""></html:option>
												<html:options collection="bjList" property="bjdm"
													labelProperty="bjmc" />
											</html:select>
										</logic:present>
										</td>
								</tr>
								<tr>
									<td>
										��˵�λ��
										<html:select property="shdw">
											<html:option value="">--���е�λ--</html:option>
											<html:option value="xdzsyj">ϵ����֧���</html:option>
											<html:option value="bzryj">������</html:option>
											<html:option value="tsgyj">ͼ���</html:option>
											<html:option value="ywsyj">ҽ����</html:option>
											<html:option value="cwcyj">����</html:option>
											<html:option value="jwcyj">����</html:option>
											<html:option value="xscyj">ѧ��������</html:option>
											<html:option value="sqglbyj">�������ڹ�˾</html:option>
										</html:select>
										&nbsp;&nbsp;
										��˽����
										<html:select property="shjg">
											<html:option value=""></html:option>
											<html:option value="ͨ��">ͨ��</html:option>
											<html:option value="��ͨ��">��ͨ��</html:option>
											<html:option value="δ���">δ���</html:option>
										</html:select>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
				</logic:notEqual>
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
											<td id="<bean:write name="tit" property="cn"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     ">
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
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
					</fieldset>
				</logic:notEmpty>	
				
				<logic:present name="writeAble">
				<logic:equal value="yes" name="writeAble">
				<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="button2"
								onclick="expDate_lx()"
								style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="expTab('rsTable','ѧ����У�����Ϣ','')">
								��ӡ�б�
							</button>
				</div>
				</logic:equal>
				</logic:present>
				<script type="text/javascript" src="js/bottomButton.js"></script>				
			</html:form>
		</center>
	</body>
</html>
