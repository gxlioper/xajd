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
	<script language="javascript">
	function batch(type){
		var RowsStr="!!";	
		var mes = "ȷ��Ҫ������ѡ��¼��";
		for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	if(document.getElementsByName("pkV")[i].checked){
	    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	}
		}
		if (RowsStr=="!!"){
			alert("��ѡ��Ҫ���������ļ�¼��");
			return false;
		}
		if(confirm(mes)){
			refreshForm('dwjl.do?method=dwjlsqsh&type=' + type+'&pkString=' + RowsStr);
		}
	}
</script>
	<body onload="xyDisabled('xy')">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<html:form action="/prise_conf_rs" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk" 	value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								ѧ�꣺
								<html:select property="xn" style="width:120px" disabled="true"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ȣ�
								<html:select property="nd" style="width:90px" disabled="true"
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�꼶��
								<html:select property="nj" style="width:90px;padding-left:80px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="listPriseConf('/xgxt/comm_check.do')">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" style="width:230px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;������Ŀ��
								<html:select property="xmdm" style="width:230px" styleId="jxjdm">
									<html:option value=""></html:option>
									<html:options collection="jlxmList" property="dwjlxmdm"
										labelProperty="dwjlxmmc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input id="chgMode" type="checkbox"
									style="border:0px;display:none" />
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
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��Ӧ��Ϣ�������Ըı����״̬��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									<input type="checkbox" id="all" name="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="3">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)"
								style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
    "
								ondblclick="chkPriseOne('/xgxt/commChkOne.do?act=view&pkVal=')">
								<td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="checkbox" name="pkV" value="<bean:write name="v"/>">
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="3" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="4">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="Table" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
			<%--			<logic:equal value="yes" name="writeAble" scope="request">--%>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<button type="button" class="button2" onclick="batch('1')">
					���ͨ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="batch('0')" style="width:80px">
					��ͨ��
				</button>
			</div>
			<%--			</logic:equal>--%>
			<div id="tmpdiv"></div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<logic:notEmpty name="message">
						<input name="mes" id="mes" value="${message}" type="hidden"/>
						<script>
							alert(document.getElementById('mes').value);
						</script>
					</logic:notEmpty>
					<logic:empty name="message">
						<script>
							alert("�����ɹ�!");
						</script>
					</logic:empty>
				</logic:equal>
				
				<logic:equal value="false" name="result">
					<logic:notEmpty name="message">
						<input name="mes" id="mes" value="${message}" type="hidden"/>
						<script>
							alert(document.getElementById('mes').value);
						</script>
					</logic:notEmpty>
					<logic:empty name="message">
						<script>
							alert("����ʧ��!");
						</script>
					</logic:empty>
				</logic:equal>
				<script>
					allNotEmpThenGo('/xgxt/comm_check.do')
				</script>				
			</logic:present>
			<script language="javascript" src="js/bottomButton.js"></script>
		</html:form>
	</body>
</html>
