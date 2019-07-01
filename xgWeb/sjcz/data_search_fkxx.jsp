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
	<body onload="xyDisabled('xy');removeXnXq()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript">
		/**
		 * ��ѯ��Ч�麯��
		 */
		function validate() {
			return true;
		}
		
		/**
		 * ɾ����¼
		 */
		function del() {
			if (curr_row == null) {
				alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
			} else {
				if (confirm("ȷ��Ҫɾ������������")) {
					showTopWin('shgc_xszz.do?method=fkxxDel&pkVal='+curr_row.getElementsByTagName('input')[0].value+"&xh="+curr_row.xh, 600, 350);
				}
			}
		}
		/**
		 * �޸ļ�¼
		 */
		function edit() {
			if (curr_row == null) {
				alert("��ѡ��ҪҪ�޸ĵ����ݣ�\n��������Ӧ���У�");
			} else {				
				showTopWin('shgc_xszz.do?method=fkxxEdit&pkVal='+curr_row.getElementsByTagName('input')[0].value+"&xh="+curr_row.xh, 600, 350);
			}
		}
		
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<div class="rightcontent">
				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<input type="hidden" id="pk" name="pk"
						value="<bean:write name="pk" scope="request"/>" />
						
						
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									ѧ�ţ�
									<html:text property="xh" style="width:120px"></html:text>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���֤�ţ�
									<html:text property="sfzh" style="width:120px"></html:text>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ͬ�ţ�
									<html:text property="hth" style="width:120px"></html:text>
								</td>
								<td width="50" rowspan="1" align="center" valign="middle">
								<input type="hidden" name="go" value="a"/>
									<button type="button" class="button2" id="search_go"
										onclick="if(validate()) {document.forms[0].go.value = 'go'; refreshForm('shgc_xszz.do?method=fkxxList&go=go');}">
										��ѯ
									</button>
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
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="edit()" xh="<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div></div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
									<button type="button" class="button2" onclick="showTopWin('shgc_nullForm.do?method=zxdk_fkxxAdd', 600, 350, 1);"
										style="width:80px">
										�� ��
									</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="button2" onclick="edit()"
											style="width:80px">
											�� ��
										</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="button2" onclick="del();"
										style="width:80px">
										ɾ ��
									</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
										onclick="impAndChkData();"
										style="width:80px">
										��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
								��������
							</button>
						</div>
					</center>
				</logic:equal>
				
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

