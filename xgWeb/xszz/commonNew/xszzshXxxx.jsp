<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.Iterator" />
<jsp:directive.page import="java.util.ArrayList" />

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
		
			refreshForm('/xgxt/new_common_xszz.do?method=xszzshXxxx&actDo=save');
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ������� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" name="xxsh" value="<bean:write name='rs' property='xxsh' />" />
			<input type="hidden" name="isXX" value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="9" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							������Ŀ
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xmmc" />
					</td>
					<td>
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						ѧ��
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="xh" />
					</td>
					<td width="11%" scope="col">
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
							��ϵ�绰
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="lxdh" />
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
							��Դ��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="syd" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="ssbh" />
					</td>
					<td>
						<div align="center">
							���ҵ绰
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="qsdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							������ò
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zzmmmc" />
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mzmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="rxny" />
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="csrq" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							רҵ�ɼ�����
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="zycjpm" />
					</td>
					<td>
						<div align="center">
							רҵ����
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="zyrs" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							�������Ŀ��
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="bjgmc" />
					</td>
					<td scope="row">
						<div align="center">
							&nbsp;
						</div>
					</td>
					<td colspan="3">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="kh" />
					</td>
					<td>
						<div align="center">
							��ѧǰ����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="rxqhk" />
					</td>
				</tr>
				<tr>
					<td colspan="4" scope="row">
						<div align="left">
							�Ƿ�Ը��μӴ��ƻ�־Ը�
						</div>
					</td>
					<td width="12%">
						<bean:write name="rs" property="sfyycjcshzyhd" />
					</td>
					<td colspan="3">
						<div align="left">
							�Ƿ�Ը�����������ѧ������ڹ���ѧ
						</div>
					</td>
					<td width="12%">
						<bean:write name="rs" property="sfyysqgjzxdkhqgzx" />
					</td>
				</tr>
				<tr>
					<td colspan="9" scope="row">
						<div align="center">
							��ͥ��Ϣ
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ͥ����
						</div>
					</td>
					<td colspan="7">
						&nbsp;&nbsp;&nbsp;�Ƿ�ȫ��
						<bean:write name="rs" property="sfjq" />
						&nbsp;&nbsp;&nbsp;&nbsp; �Ƿ�¶���
						<bean:write name="rs" property="sfge" />
						&nbsp;&nbsp;&nbsp;&nbsp; �Ƿ��ף�
						<bean:write name="rs" property="sfdq" />
						&nbsp;&nbsp;&nbsp;&nbsp; �Ƿ�м���
						<bean:write name="rs" property="sfcj" />
						<br />
						�Ƿ��������
						<bean:write name="rs" property="sfjls" />
						&nbsp;&nbsp;&nbsp;&nbsp; �Ƿ����죺
						<bean:write name="rs" property="sfly" />
						&nbsp;&nbsp;&nbsp;&nbsp; �Ƿ��ز���
						<bean:write name="rs" property="sfzb" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							�Ƿ�������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfkns" />
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="jtzz" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ͥ��ϵ�绰
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtlxdh" />
					</td>
					<td>
						<div align="center">
							��ͥ�˾�
							<br />
							������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtrjnsr" />
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6" scope="row">
						<div align="center">
							��
							<br />
							ͥ
							<br />
							��
							<br />
							Ա
							<br />
							��
							<br />
							Ϣ
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="10%">
						<div align="center">
							�뱾��
							<br />
							��ϵ
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							����(ѧϰ)��λ
						</div>
					</td>
					<td width="12%">
						<div align="center">
							ְҵ
						</div>
					</td>
					<td width="10%">
						<div align="center">
							������
							<br />
							(Ԫ)
						</div>
					</td>
					<td width="12%">
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
							&nbsp;
							<bean:write name="rs" property="jtcy1_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nsr" />
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
							&nbsp;
							<bean:write name="rs" property="jtcy2_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nsr" />
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
							&nbsp;
							<bean:write name="rs" property="jtcy3_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nsr" />
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
							&nbsp;
							<bean:write name="rs" property="jtcy4_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nsr" />
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
							&nbsp;
							<bean:write name="rs" property="jtcy5_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							ѧ���ڱ���
							<br />
							�������
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="xszbdszqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ͥ����
							<br />
							��Ȼ�ֺ����
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="jtzszrzhqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ͥ����
							<br />
							ͻ�������¼�
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="jtzstfywsj" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							�������
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="qtqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��������ͨѶ��ַ
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="mzbm_txdz" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							����������������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mzbm_yzbm" />
					</td>
					<td>
						<div align="center">
							��������
							<br />
							��ϵ�绰
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mzbm_lxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="9">
						<div align="center">
							������Ϣ
						</div>
					</td>
				</tr>
				<logic:equal name="isNULL" value="no">
					<%
								ArrayList zdyzdList = (ArrayList) request
								.getAttribute("zdyzdList");

								for (Iterator it = zdyzdList.iterator(); it
								.hasNext();) {
							String[] temp = (String[])it.next();
							String zdydm = "zdy" + temp[0];
					%>
					<tr>
						<td colspan="2">
							<div align="center">
								<%=temp[1]%>
							</div>
						</td>
						<td colspan="7">
							<bean:write name="rs" property="<%=zdydm%>" />
						</td>
					</tr>
					<%
					}
					%>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="sqly" />
					</td>
				</tr>
				<%String[] je = (String[])request.getAttribute("zjeList"); %>
				<tr>
					<td colspan="9">
						<div align="center">
							���˼�¼
						</div>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							��<br />ѧ<br />��<br />��<br />¼
						</div>
					</td>
					<td>
						<div align="center">
							��ý��
						</div>
					</td>
					<td colspan="7">
						<%=je[0]%>&nbsp;Ԫ
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<%
							ArrayList jxjList = (ArrayList)request.getAttribute("xsJxjjlList");
							if(jxjList.size() == 0){
						%>
						&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼
						<%	
							} else {
						%>
						<br />
						<%
								for(Iterator it = jxjList.iterator(); it.hasNext();){
						%>
						 	&nbsp;<%=it.next() %><br />
						 <%} %>
						 <br />
						 <%} %>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							��<br />��<br />��<br />ѧ<br />��<br />¼
						</div>
					</td>
					<td>
						<div align="center">
							��ý��
						</div>
					</td>
					<td colspan="7">
						<%=je[1]%>&nbsp;Ԫ
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<%
							ArrayList qgzuList = (ArrayList)request.getAttribute("xsQgzuCjjlList");
							if(qgzuList.size() == 0){
						%>
						&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼
						<%	
							} else {
						%>
						<br />
						<%
								for(Iterator it = qgzuList.iterator(); it.hasNext();){
						%>
						 	&nbsp;<%=it.next() %><br />
						 <%} %>
						 <br />
						 <%} %>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							��<br />��<br />��<br />¼
						</div>
					</td>
					<td>
						<div align="center">
							��ý��
						</div>
					</td>
					<td colspan="7">
						<%=je[2]%>&nbsp;Ԫ
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<%
							ArrayList zzList = (ArrayList)request.getAttribute("xszzHdjeList");
							if(zzList.size() == 0){
						%>
						&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼
						<%	
							} else {
						%>
						<br />
						<%
								for(Iterator it = zzList.iterator(); it.hasNext();){
						%>
						 	&nbsp;<%=it.next() %><br />
						 <%} %>
						 <br />
						 <%} %>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ѧ������
						</div>
					</td>
					<td colspan="3">
						<%=je[3] %>&nbsp;Ԫ
					</td>
					<td>
						<div align="center">
							����ܽ��
						</div>
					</td>
					<td colspan="3">
						<%=je[4] %>&nbsp;Ԫ
					</td>
				</tr>
				<logic:equal name="isXX" value="is">
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="xysh" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />��׼���
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="xypzje" />
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td colspan="2">
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
					<td>
						<div align="center">
							��׼���
						</div>
					</td>
					<td colspan="3">
						<html:select name="rs" property="zzje">
							<html:option value="0"></html:option>
							<html:options collection="zzjeList" property="zzje"
								labelProperty="zzje" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />������
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="left">
							ѧУ������
						</div>
					</td>
					<td colspan="6">
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
				<button class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
