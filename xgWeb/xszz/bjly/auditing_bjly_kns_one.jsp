<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.Iterator" />

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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("ͨ��" == xxsh) && (isXX != "is")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("ѧУ���������ܴ���200���ַ�");
	          		 return false;
	       		 }
			 }
			 refreshForm('/xgxt/auditing_bjly_kns_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		</script>
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
			<input type="hidden" id="xxsh" name="xxsh" value="<bean:write name="rs" property="xxsh" />" />
			<input type="hidden" id="isXX" name="isXX" value="<bean:write name="isXX" />" />
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="9" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td colspan="2" scope="col">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="3" scope="col">
						<bean:write name="rs" property="xh" />
					</td>
					<td width="14%" scope="col">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="3" scope="col">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xb" />
					</td>
					<td>
						<div align="center">
							�꼶
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="nj" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							���֤��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfzh" />
					</td>
					<td>
						<div align="center">
							���
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="nd" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xymc" />
					</td>
					<td>
						<div align="center">
							רҵ
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6" scope="row">
						<div align="center">
							��ͥ��Ա��Ϣ
						</div>
					</td>
					<td width="10%">
						<div align="center">
							����
						</div>
					</td>
					<td width="10%">
						<div align="center">
							��ν
						</div>
					</td>
					<td width="10%">
						<div align="center">
							����
						</div>
					</td>
					<td width="16%">
						<div align="center">
							���֤��
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							������λ
						</div>
					</td>
					<td width="10%">
						<div align="center">
							������
						</div>
					</td>
					<td width="10%">
						<div align="center">
							����״��
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_sfzh" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_ysr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_sfzh" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_ysr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_sfzh" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_ysr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_sfzh" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_ysr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_sfzh" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_ysr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_jkzk" />
						</div>
					</td>
				</tr>
				<logic:equal name="isNULL" value="is">
					<tr>
						<td rowspan="<bean:write name='con' />">
							<div align="center">
								��
								<br>
								��
								<br>
								��
								<br>
								Դ
								<br>
								��
								<br>
								��
							</div>
						</td>
						<td colspan="8">
							<div align="center">
								��������Դ��Ŀ!
							</div>
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="isNULL" value="no">
					<tr>
						<td rowspan="<bean:write name='con' />">
							<div align="center">
								��
								<br>
								��
								<br>
								��
								<br>
								Դ
								<br>
								��
								<br>
								��
							</div>
						</td>
						<td colspan="4">
							<div align="center">
								��Ŀ
							</div>
						</td>
						<td colspan="4">
							<div align="center">
								����(Ԫ)
							</div>
						</td>
					</tr>
					<%
						ArrayList srlyList = (ArrayList) request
							.getAttribute("srlyList");
						String srlyName = "";

						for (Iterator it = srlyList.iterator(); it.hasNext();) {
							String[] temp = (String[]) it.next();
							srlyName = "srly" + temp[0];
					%>
					<tr>
						<td colspan="4">
							<div align="center">
								<%=temp[1]%>
							</div>
						</td>
						<td colspan="4">
							<div align="center">
								<bean:write name="rs" property="<%=srlyName%>" />
							</div>
						</td>
					</tr>
					<%
						}
					%>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="center">
							����ԭ��
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="sqyy" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ע
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="bz" />
					</td>
				</tr>
				<tr>
					<logic:notEmpty name="xx">
						<td align="center" colspan="2">
							<bean:message key="lable.xsgzyxpzxy" />��˽����
						</td>
						<td align="center" colspan="3">
							<bean:write name="rs" property="xysh" />
						</td>
					</logic:notEmpty>
					<logic:empty name="xx">
						<td align="left" colspan="5">
						</td>
					</logic:empty>
					<td>
						<div align="center">
							��˽��
						</div>
					</td>
					<td colspan="3">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />������
						</div>
					</td>
					<td colspan="7">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧУ������
						</div>
					</td>
					<td colspan="7">
						<html:textarea name="rs" property="xxshyj" rows='5' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
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
