<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.Iterator" />
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.Vector" />

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
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
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
			if((("ͨ��" == xxsh)) && (isXX != "is")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("ѧУ���������ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
		
			refreshForm('/xgxt/shgc_pjpy.do?method=jxjshXxxx&actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function shcheng(){
			var yesNo = document.getElementById("yesNo").value;
			var jxjjeT = document.getElementById('jxjjeT').value;
			
			if ("ͨ��" == yesNo){
				document.getElementById("jxjje").disabled=false;
				if (jxjjeT != null && jxjjeT != "" && jxjjeT != "0"){
					document.getElementById("jxjje").value=jxjjeT;
				} else {
					document.getElementById("jxjje").value="";
				}
			} else {
				document.getElementById("jxjje").value="";
				document.getElementById("jxjje").disabled=true;
			}
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - ��� - ��ѧ����� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" name="xxsh"
				value="<bean:write name='rs' property='xxsh' />" />
			<input type="hidden" name="jxjjeT" id="jxjjeT"
				value="<bean:write name='rs' property='jxjje' />" />
			<input type="hidden" name="isXX" value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td width="16%" scope="row">
						<div align="center">
							��ѧ����Ŀ
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="bbmc" />
					</td>
					<td width="16%">
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							���
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nd" />
					</td>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xqmc" />
					</td>
				</tr>
				<tr>
					<td align="center">
						ѧ��
					</td>
					<td align="left">
						<bean:write name="rs" property="xh" />
					</td>
					<td scope="col">
						<div align="center">
							����
						</div>
					</td>
					<td scope="col">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
					<td>
						<div align="center">
							�꼶
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nj" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh" />
					</td>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
					<td>
						<div align="center">
							רҵ
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�༶
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc" />
					</td>
					<td>
						<div align="center">
							��Դ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="syd" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="ssbh" />
					</td>
					<td>
						<div align="center">
							���ҵ绰
						</div>
					</td>
					<td>
						<bean:write name="rs" property="qsdh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							������ò
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc" />
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mzmc" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rxny" />
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="csrq" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="kh" />
					</td>
					<td>
						<div align="center">
							��ѧǰ����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rxqhk" />
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div align="center">
							������Ϣ
						</div>
					</td>
				</tr>
				<logic:equal name="isNULL" value="no">
					<%
					@SuppressWarnings("unchecked")
								ArrayList<Vector<String>> zdyzdList = (ArrayList<Vector<String>>) request
								.getAttribute("zdyzdList");

								for (Iterator<Vector<String>> it = zdyzdList.iterator(); it
								.hasNext();) {
							Vector<String> temp = it.next();
							String zdydm = "zdy" + temp.get(0);
					%>
					<tr>
						<td>
							<div align="center">
								<%=temp.get(1)%>
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="<%=zdydm%>" />
						</td>
					</tr>
					<%
					}
					%>
				</logic:equal>
				<tr>
					<td>
						<div align="center">
							�����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="hjqk" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ѧ�����ķ���
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xslwfb" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ר������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zljs" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly" />
					</td>
				</tr>
				<%
				String[] je = (String[]) request.getAttribute("zjeList");
				%>
				<tr>
					<td colspan="4">
						<div align="center">
							���˼�¼
						</div>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							��
							<br />
							ѧ
							<br />
							��
							<br />
							��
							<br />
							¼
						</div>
					</td>
					<td colspan="3">
						��ý��:&nbsp;&nbsp;&nbsp;&nbsp;
						<%=je[0]%>
						&nbsp;Ԫ
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<logic:equal name="xxdm" value="10856">
						<table width="100%" align="center" class="tbstyle">
							<tr>
								<td width="30%" align="center">
									����ѧ����ѧ��
								</td>
								<td width="70%">
									<%
									@SuppressWarnings("unchecked")
											ArrayList<String> xsYxJxjjlList = (ArrayList<String>) request
											.getAttribute("xsYxJxjjlList");
											if (xsYxJxjjlList.size() == 0) {
									%>
									&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼
									<%
									} else {
									%>
									<br />
									<%
									for (Iterator it = xsYxJxjjlList.iterator(); it.hasNext();) {
									%>
									&nbsp;
									<%=it.next()%>
									<br />
									<%
									}
									%>
									<br />
									<%
									}
									%>
								</td>
							</tr>
							<tr>
								<td width="30%" align="center">
									��ǿѧ����ѧ��
								</td>
								<td width="70%">
									<%
									@SuppressWarnings("unchecked")
											ArrayList<String> xsZqJxjjlList = (ArrayList<String>) request
											.getAttribute("xsZqJxjjlList");
											if (xsZqJxjjlList.size() == 0) {
									%>
									&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼
									<%
									} else {
									%>
									<br />
									<%
									for (Iterator it = xsZqJxjjlList.iterator(); it.hasNext();) {
									%>
									&nbsp;
									<%=it.next()%>
									<br />
									<%
									}
									%>
									<br />
									<%
									}
									%>
								</td>
							</tr>
							<tr>
								<td width="30%" align="center">
									����ѧ����ѧ��
								</td>
								<td width="70%">
									<%
									@SuppressWarnings("unchecked")
											ArrayList<String> xsQtJxjjlList = (ArrayList<String>) request
											.getAttribute("xsQtJxjjlList");
											if (xsQtJxjjlList.size() == 0) {
									%>
									&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼
									<%
									} else {
									%>
									<br />
									<%
									for (Iterator it = xsQtJxjjlList.iterator(); it.hasNext();) {
									%>
									&nbsp;
									<%=it.next()%>
									<br />
									<%
									}
									%>
									<br />
									<%
									}
									%>
								</td>
							</tr>
						</table>
						</logic:equal>
						<logic:notEqual name="xxdm" value="10856">
						<%
						@SuppressWarnings("unchecked")
								ArrayList<String> xsJxjjlList = (ArrayList<String>) request
								.getAttribute("xsJxjjlList");
								if (xsJxjjlList.size() == 0) {
						%>
						&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼
						<%
						} else {
						%>
						<br />
						<%
						for (Iterator it = xsJxjjlList.iterator(); it.hasNext();) {
						%>
						&nbsp;
						<%=it.next()%>
						<br />
						<%
						}
						%>
						<br />
						<%
						}
						%>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							��
							<br />
							��
							<br />
							��
							<br />
							ѧ
							<br />
							��
							<br />
							¼
						</div>
					</td>
					<td colspan="3">
						��ý��:&nbsp;&nbsp;&nbsp;&nbsp;
						<%=je[1]%>
						&nbsp;Ԫ
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<%
						@SuppressWarnings("unchecked")
								ArrayList<String> qgzuList = (ArrayList<String>) request
								.getAttribute("xsQgzuCjjlList");
								if (qgzuList.size() == 0) {
						%>
						&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼
						<%
						} else {
						%>
						<br />
						<%
						for (Iterator it = qgzuList.iterator(); it.hasNext();) {
						%>
						&nbsp;
						<%=it.next()%>
						<br />
						<%
						}
						%>
						<br />
						<%
						}
						%>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							��
							<br />
							��
							<br />
							��
							<br />
							¼
						</div>
					</td>
					<td colspan="3">
						��ý��:&nbsp;&nbsp;&nbsp;&nbsp;
						<%=je[2]%>
						&nbsp;Ԫ
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<%
						@SuppressWarnings("unchecked")
								ArrayList<String> zzList = (ArrayList<String>) request
								.getAttribute("xszzHdjeList");
								if (zzList.size() == 0) {
						%>
						&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼
						<%
						} else {
						%>
						<br />
						<%
						for (Iterator it = zzList.iterator(); it.hasNext();) {
						%>
						&nbsp;
						<%=it.next()%>
						<br />
						<%
						}
						%>
						<br />
						<%
						}
						%>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ѧ������
						</div>
					</td>
					<td colspan="">
						<%=je[3]%>
						&nbsp;Ԫ
					</td>
					<td>
						<div align="center">
							����ܽ��
						</div>
					</td>
					<td>
						<%=je[4]%>
						&nbsp;Ԫ
					</td>
				</tr>
				<logic:equal name="isXX" value="is">
					<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xysh" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />��׼���
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xypzje" />
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td>
						<div align="center">
							��˽��
						</div>
					</td>
					<td>
						<html:select name="rs" property="yesNo" onchange="shcheng();" styleId="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<td>
						<div align="center">
							��׼���
						</div>
					</td>
					<td>
						<html:select name="rs" property="jxjje" styleId="jxjje">
							<html:option value="0"></html:option>
							<html:options collection="jxjjeList" property="jxjje"
								labelProperty="jxjje" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />������
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xyshyj" rows='5'
							style="width:100%" onblur="yzdx(this,'xyshyj')" />
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							ѧУ������
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xxshyj" rows='5'
							style="width:100%" onblur="yzdx(this,'xxshyj')" />
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
