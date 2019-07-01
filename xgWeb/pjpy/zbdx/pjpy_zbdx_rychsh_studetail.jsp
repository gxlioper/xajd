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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
		<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="pkVal" scope="request"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - ��� - �����ƺ���� - ���������ƺ����
				</div>
			</div>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							���������ƺ����
						</td>
					</tr>
				</thead>
				<nested:root name="zbdxrychsh_rs" >
					<nested:nest property="stu">
						
						<tr style="height:22px">
							<td align="right">
								ѧ�ţ�
							</td>
							<td align="left"  id="selxh">
								<nested:text property="XH" readonly="true"/>
							</td>
							<td align="right">
								������
							</td>
							<td align="left">
								<nested:text property="XM" readonly="true"/>
							</td>
						</tr>
				
						<tr style="height:22px">
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<nested:text property="XB" readonly="true"/>
							</td>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<nested:text property="NJ" readonly="true"/>
							</td>
						</tr>
						
						
						<tr style="height:22px">
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<nested:text property="XYMC" readonly="true"/>
							</td>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<nested:text property="ZYMC" readonly="true"/>
							</td>
						</tr>
						
						<tr style="height:22px">
							<td align="right">
								�༶:
							</td>
							<td align="left">
								<nested:text property="BJMC" readonly="true"/>
							</td>
							<td align="right">
								
							</td>
							<td align="left">
								
							</td>
						</tr>
						
					</nested:nest>
					<nested:nest property="rychsh">
					
						<tr style="height:22px">
							<td align="right">
								���:
							</td>
							<td align="left">
								<nested:text property="nd" readonly="true"/>
							</td>
							<td align="right">
								ѧ�꣺
							</td>
							<td align="left">
								<nested:text property="xn" readonly="true"/>
							</td>
						</tr>
					
						<tr style="height:22px">
							<td align="right">
								�����ƺţ�
							</td>
							<td align="left">
								<nested:text property="rychmc" readonly="true"/>
							</td>
							<td align="right">
								�³ɼ���
							</td>
							<td align="left">
								<nested:text property="sjddszcp_1" readonly="true"/>
							</td>
						</tr>
						
						<tr style="height:22px">
							<td align="right">
								��ɼ���
							</td>
							<td align="left">
								<nested:text property="stszcp_1" readonly="true"/>
							</td>
							<td align="right">
								רҵ�������ʷ֣�
							</td>
							<td align="left">
								<nested:text property="zylrszf" readonly="true"/>
							</td>
						</tr>
						
						<tr style="height:22px">
							<td align="right">
								һѧ�����Ƿ����пγ�ͨ����
							</td>
							<td align="left">
								<nested:text property="no_fail" readonly="true"/>
							</td>
							<td align="right">
								�Ƿ�ͨ��CET4(Ҫ����꼶���ϱ���ͨ��)��
							</td>
							<td align="left">
								<nested:text property="pass_cet_4" readonly="true"/>
							</td>
						</tr>
						
						<tr style="height:22px">
							<td align="right">
								�ۺ����ʲ����ܷ�����(ͬ�꼶ͬרҵ)��
							</td>
							<td align="left">
								<nested:text property="zhszcpzfpm" readonly="true"/>
							</td>
							<td align="right">
								�������ʷ֣�
							</td>
							<td align="left">
								<nested:text property="rwszcp_1" readonly="true"/>
							</td>
						</tr>

						<tr style="height:22px">
							<td align="right">
								��ˣ�
							</td>
							<td align="left">
								<html:select property="yesNo">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<td align="right">
								
							</td>
							<td align="left">
								
							</td>
						</tr>
						
					</nested:nest>
				</nested:root>
			</table>
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="refreshForm('/xgxt/creditChkOne.do?act=save');Close();window.dialogArguments.document.getElementById('search_go').click();"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="showTopWin('/xgxt/stu_info_details.do?xh=' +document.getElementById('selxh').getElementsByTagName('input')[0].value,800,600)" style="width:90px"
					id="buttonQuery">
					�鿴ѧ����Ϣ
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
