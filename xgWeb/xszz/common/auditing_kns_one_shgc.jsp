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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
		function yzt(){
			var isXX = document.getElementById('isXX').value;
			var xx = document.getElementById('xx').value;
			if(("ͨ��" == xxsh) && (isXX != "1")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
			
			refreshForm('/xgxt/auditing_kns_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ��������� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" id="xxsh" name="xxsh" value="<bean:write name="xxsh" />" />
			<input type="hidden" id="xx" name="xx" value="<bean:write name="xx" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="7" align="center">	
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" colspan="2">
						ѧ�ţ�
					</td>
					<td align="left" colspan="3">
						<bean:write name="XH" />
					</td>
					<td align="right">
						��ȣ�
					</td>
					<td align="left">
						<bean:write name="ND" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						������
					</td>
					<td align="left" colspan="3">
						<bean:write name="XM" />
					</td>
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="XN" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						�Ա�
					</td>
					<td align="left" colspan="3">
						<bean:write name="XB" />
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
						<bean:write name="XQ" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						�꼶��
					</td>
					<td align="left" colspan="3">
						<bean:write name="NJ" />
					</td>
					<td align="right">
						��ϵ�绰��
					</td>
					<td align="left">
						<bean:write name='LXDH' />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left" colspan="3">
						<bean:write name="XYMC" />
					</td>
					<td align="right">
						����ʱ�䣺
					</td>
					<td align="left">
						<bean:write name="SQSJ" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						רҵ��
					</td>
					<td align="left" colspan="3">
						<bean:write name="ZYMC" />
					</td>
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<bean:write name="BJMC" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						���ţ�
					</td>
					<td align="left" colspan="3">
						<bean:write name="kh" />
					</td>
					<td align="right">
					</td>
					<td align="left">
					</td>
				</tr>
				<tr>
								<td colspan="2">
									<div align="right">
										��ͥ���ڣ�
									</div>
								</td>
								<td colspan="3" align="left">
									<bean:write name="jtfk"/>
								</td>
								<td>
									<div align="right">
										��ͥ����������
									</div>
								</td>
								<td>
									<bean:write name="jtfkrs"/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="right">
										��ͥ�������룺
									</div>
								</td>
								<td colspan="3">
									<bean:write name="jtyzsr"/>
								</td>
								<td>
									<div align="right">
										��ͥ�˾����룺
									</div>
								</td>
								<td>
									<bean:write name="jtrjsr"/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="right">
										��ͥ������Դ��
									</div>
								</td>
								<td colspan="3">
									<bean:write name="jtsrly"/>
								</td>
								<td>
									<div align="right">
										�������룺
									</div>
								</td>
								<td>
									<bean:write name="yzbm"/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="right">
										��ͥסַ��
									</div>
								</td>
								<td colspan="5">
									<bean:write name="jtzz"/>
								</td>
							</tr>
							<tr>
								<td width="4%" rowspan="6">
									<div align="center">
										��
										<br>
										ͥ
										<br>
										��
										<br>
										Ա
										<br>
										��
										<br>
										��
									</div>
								</td>
								<td width="12%">
									<div align="center">
										����
									</div>
								</td>
								<td width="9%">
									<div align="center">
										����
									</div>
								</td>
								<td width="15%">
									<div align="center">
										�뱾�˹�ϵ
									</div>
								</td>
								<td width="10%">
									<div align="center">
										������
									</div>
								</td>
								<td colspan="2">
									<div align="center">
										������ѧϰ��λ
									</div>
								</td>
							</tr>
							<tr>
								<td width="12%" align="center">
									<bean:write name="jtcy1_xm"/>
								</td>
								<td align="center">
									<bean:write name="jtcy1_nl" />
								</td>
								<td align="center">
									<bean:write name="jtcy1_gx" />
								</td>
								<td align="center">
									<bean:write name="jtcy1_ysr" />
								</td>
								<td colspan="2" align="center">
									<bean:write name="jtcy1_gzdw" />
								</td>
							</tr>
							<tr>
								<td width="12%" align="center">
									<bean:write name="jtcy2_xm"/>
								</td>
								<td align="center">
									<bean:write name="jtcy2_nl" />
								</td>
								<td align="center">
									<bean:write name="jtcy2_gx" />
								</td>
								<td align="center">
									<bean:write name="jtcy2_ysr" />
								</td>
								<td colspan="2" align="center">
									<bean:write name="jtcy2_gzdw" />
								</td>
							</tr>
							<tr>
								<td width="12%" align="center">
									<bean:write name="jtcy3_xm"/>
								</td>
								<td align="center">
									<bean:write name="jtcy3_nl" />
								</td>
								<td align="center">
									<bean:write name="jtcy3_gx" />
								</td>
								<td align="center">
									<bean:write name="jtcy3_ysr" />
								</td>
								<td colspan="2" align="center">
									<bean:write name="jtcy3_gzdw" />
								</td>
							</tr>
							<tr>
								<td width="12%" align="center">
									<bean:write name="jtcy4_xm"/>
								</td>
								<td align="center">
									<bean:write name="jtcy4_nl" />
								</td>
								<td align="center">
									<bean:write name="jtcy4_gx" />
								</td>
								<td align="center">
									<bean:write name="jtcy4_ysr" />
								</td>
								<td colspan="2" align="center">
									<bean:write name="jtcy4_gzdw" />
								</td>
							</tr>
							<tr>
								<td width="12%" align="center">
									<bean:write name="jtcy5_xm"/>
								</td>
								<td align="center">
									<bean:write name="jtcy5_nl" />
								</td>
								<td align="center">
									<bean:write name="jtcy5_gx" />
								</td>
								<td align="center">
									<bean:write name="jtcy5_ysr" />
								</td>
								<td colspan="2" align="center">
									<bean:write name="jtcy5_gzdw" />
								</td>
							</tr>
				<tr style="height:22px">
				<logic:notEmpty name="xx">	
					<td align="right" colspan="2">
						<bean:message key="lable.xsgzyxpzxy" />��˽����
					</td>
					<td align="left" colspan="3">
						<bean:write name="XYSH" />
					</td>
				</logic:notEmpty>
				<logic:empty name="xx">	
					<td align="left" colspan="2">
					</td>
				</logic:empty>
					<td align="right">
						��˽����
					</td>
					<td align="left">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						�������ɣ�
					</td>
					<td align="left" colspan="5">
						<bean:write name="SQYY" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						��ע��
					</td>
					<td align="left" colspan="5">
						<bean:write name="BZ" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="yzt();"
					style="width:80px" id="buttonSave">
					�� ��
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
