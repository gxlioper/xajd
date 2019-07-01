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
	<script language="javascript">
</script>
	<body onload="xyDisabled('xy')">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<html:form action="/sztz_hd_cx" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType1" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
				
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" nowrap>
								ѧ�꣺
								<html:select property="xn" style="width:100px" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;ѧ�ڣ�
								<html:select property="xq" style="width:90px" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm"
									style="width:230px;background-color:#FFFFFF" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input id="chgMode" type="checkbox"
									style="border:0px;display:none" />
							</td>
								
							<td width="10" align="center" valign="middle" rowspan="2">
								<input type="hidden" name="go" value="a" />
									<button class="button2" id="search_go" style="height: 40px"
										onclick="allNotEmpThenGo('/xgxt/sztz_hd_cx.do')">
										��ѯ
									</button>							
							</td>
						</tr>
						<tr>
						<td align="left" nowrap>
								��Ŀ���
								<html:select property="kmdm"
									style="width:230px;background-color:#FFFFFF" styleId="kmdm">
									<html:option value=""></html:option>
									<html:options collection="kmdmList" property="kmdm"
										labelProperty="kmm" />
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
			<logic:present name="check">
			
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴������Ϣ�������Ըı����״̬��������ͷ��������</font>
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
						 <tr onclick="rowOnClick(this)"  
                             style="cursor:hand;background-color:
                             <logic:iterate id="v" name="s" offset="0" length="1">
                             <bean:write name="v"/>
                             </logic:iterate>
						    "ondblclick="checkTzhd('/xgxt/sztz_hd_check.do?doType=check&xmdm=')">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				</logic:present>
				<logic:notPresent name="check">				
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴������Ϣ��������ͷ��������</font>						
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
						 <tr onclick="rowOnClick(this)" style="cursor:hand" ondblclick="checkTzhd('/xgxt/sztz_hd_view.do?doType=check&xmdm=')">
						  <td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				</logic:notPresent>
			</logic:notEmpty>
			<br/><br/><br/>
			<logic:notPresent name="check">
				<div class="buttontool" id="btn"
								style="position: absolute;left:1%;top:100px" width="100%">					
					<logic:equal value="yes" name="writeAble" scope="request">					
					<button class="button2"
						onclick="showTopWin('/xgxt/sztz_hd_sb.do','800','600')">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="modiTzhd('/xgxt/sztz_hd_sb.do?doType=modi&xmdm=');">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="modiTzhd('/xgxt/sztz_hd_sb.do?doType=del&xmdm=')">
						ɾ ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
							onclick="impAndChkData();"
						style="width:80px">
						��������
					</button>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="dataExport()" style="width:80px">
						��������
					</button>
					
				</div>	
				</logic:notPresent>		
			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal value="ok" name="isEXIST">
         		<script language="javascript">
	         		alert("�����ɹ���");
	         	</script>
	    </logic:equal>
	        <logic:equal value="no" name="isEXIST">
         		<script language="javascript">
	         		alert("����ʧ�ܣ�");
	         	</script>
	        </logic:equal>
	        <logic:equal value="ischeck" name="isEXIST">
         		<script language="javascript">
	         		alert("����Ŀ��ͨ�����,�����ٽ����޸ġ�ɾ��������");
	         	</script>
	        </logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
