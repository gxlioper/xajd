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
	<body onload="xyDisabled('xy')">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
	function print(){		
		if( curr_row == null){
			alert("��ѡ��Ҫ��ӡ�ļ�¼(����һ�м���)");
			return false;
		}
		var xh=curr_row.cells[5].innerText;	
		document.forms[0].action="qgzx_xskh_print.do?xh="+xh;
		//alert(document.forms[0].action);
		document.forms[0].submit();			    
	}
	</script>
		<html:form action="/stu_work_checkInit.do?doType=xskh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>"/>
			<fieldset>
				<legend>
					�� ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">								
									ѧ�꣺								
								<html:select property="xn" style="width:120px"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>								
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ȣ�
								<html:select property="nd" style="width:90px" 
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
									onclick="refreshForm('/xgxt/stu_work_checkInit.do?doType=xskh&go=go')">
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
								&nbsp;&nbsp;��λ���ƣ�
								<html:select property="xmdm" style="width:230px" styleId="xmdm">
									<html:option value=""></html:option>
									<html:options collection="gwList" property="gwdm"
										labelProperty="gwdm" />
								</html:select>
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
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ�������Կ��˵ȼ���������ͷ��������</font>
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="2">
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
								ondblclick="updateOrViewOne('/xgxt/stu_work_info.do?doType=xskh&act=view&pkVal=')">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<%--�㶫Ů��ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
			<logic:equal value="12742" name="xxdm">
				<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="xlsDown/qgzx_gdnzzy_qgzxkhb.doc" target="_blank" style="height:20px">�������˱�����</a>
				</div>
			</logic:equal>
			<%--end�㶫Ů��ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
			<%--�������ϴ�ѧ--%>
			<logic:equal value="11417" name="xxdm">
				<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2" onclick="print();">
								��ӡ����
							</button>	
				</div>
			</logic:equal>
			<%--end�������ϴ�ѧ--%>
			<%--�Ϻ����̼�����ѧ--%>
			<logic:equal value="10856" name="xxdm">
				<div class="buttontool" align="center" id="btn" style="position: absolute;left:1%;top:100px">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="refreshForm('qgzxShgc.do?method=printYrdwXskh')" style="width:80px"
						id="buttonPrint">
						�� ӡ
					</button>								
				</div>
			</logic:equal>
			<%--end�Ϻ����̼�����ѧ--%>
			<div id="tmpdiv"> </div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
                                                                                                   
