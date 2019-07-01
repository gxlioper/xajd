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

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>

	<script language="javascript">
	function expStationDis(){
		document.forms[0].target = "_blank";
		refreshForm('xsqgzx.do?method=expStation');
		document.forms[0].target = "_self";
	}
	
	function modi(){
		var xxdm = document.getElementById("xxdm").value;
		if(curr_row != null){
			if(xxdm=="10497"){
				if(curr_row.cells[10].innerText.trim()=="ͨ��"){
					alert("����Ѿ�ͨ�����Ѳ����ٽ����޸ģ�");			
					return false;
				}
			}
			refreshForm('/xgxt/comm_pub.do?doType=modi&pkValue='+curr_row.getElementsByTagName('input')[0].value);
			return true;
		}else{
			alert('��ѡ��Ҫ�޸ĵ��У�');
			return false;
		}
	}
	</script>
	<body>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="writeAble" name="writeAble"
				value="<bean:write name="writeAble" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm"/>" />
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								��ȣ�
								<html:select property="nd" style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
								&nbsp;&nbsp;ѧ�꣺
								<html:select property="xn" style="width:120px">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;ѧ�ڣ�
								<html:select property="xq" style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								<logic:equal value="view_gwxx" name="tableName">
								&nbsp;&nbsp;��λ���ƣ�
								<html:select property="gwdm" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="gwList" property="gwdm"
											labelProperty="gwdm" />
									</html:select>
								</logic:equal>
							</td>
							<td width="10" align="center" valign="middle" rowspan="2">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/data_search2.do?act='+document.getElementById('act').value)">
									�� ѯ
								</button>
							</td>
						</tr>
						<logic:equal value="view_gwxx" name="tableName">
							<tr>
								<td>
									���˵�λ��
									<html:select property="yrdwdm" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="yrdwList" property="yrdwdm"
											labelProperty="yrdwmc" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
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
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:equal value="view_dwjlxx" name="tableName">
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</logic:equal>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="viewMore('modi','yes','details')" style="cursor:hand">

								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
									<logic:iterate id="v" name="s" offset="3">
										<td nowrap>
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

			<logic:equal value="yes" name="writeAble" scope="request">
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="yes" name="writeAble">
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<button type="button" class="button2"
							onclick="refreshForm('comm_pub.do?doType=add&tableName='+document.forms[0].tableName.value,800,600)"
							<%--						"window.location.href='/xgxt/comm_pub.do?doType=add&tableName='+document.forms[0].tableName.value"--%>
						style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="modi()" style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="viewMore('del')"
							style="width:80px">
							ɾ ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="impAndChkData();"
							style="width:80px">
							��������
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="dataExport()" style="width:80px">
							��������
						</button>	
						 &nbsp;&nbsp;&nbsp;&nbsp;						
						<button type="button" class="button2" onclick="dataStencilExport()"
								style="width:80px">
								����ģ��
							</button>
					</div>
					<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
		</script>
				</logic:equal>
				</logic:equal>
		</html:form>

	</body>
</html>
