<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
		
	<script Language="javascript">
function a(x,y,color)
{
document.write("<img border='0' style='position: absolute; left: "+(x+parseInt((document.body.clientWidth*0.01)*4.1))+"; top: "+(y+parseInt((document.body.clientHeight*0.01)*19.4))+";background-color: "+color+"' src='px.gif' width=1 height=1>")}
</script>	
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>

		<html:form action="/stu_info_add" method="post">
		<table width="85%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/>
					<b>
						<span style='font-size:16.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xxmc }<logic:equal value="����ѧԺ" property="xymc" name="rs">${rs.xymc }</logic:equal>��ͥ��������ѧ�����������
						</span>
						</b>
						<br/><br/>
					<div align="right">
						<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							���ʱ�䣺&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<td width="6%"></td>
				<td width="6%"></td>
				<td width="2%"></td>
				<td width="14%"></td>
				<td width="3%"></td>
				<td width="2%"></td>
				<td width="2%"></td>
				<td width="2%"></td>
				<td width="3%"></td>
				<td width="7%"></td>
				<td width="3%"></td>
				<td width="3%"></td>
				<td width="3%"></td>
				<td width="4%"></td>
				<td width="2%"></td>
				<td width="4%"></td>
				<td width="3%"></td>
				<td width="3%"></td>
				<td width="7%"></td>
				<td width="7"></td>
				<td width="7%"></td>
				<td width="6%"></td>
			</tr>
			<!-- ��һ�� -->
			<tr style="height:30px">
				<td align="center"  colspan="3">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						����������
					</span>
				</td>
				<td  align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xm }
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						�Ա�
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xb }
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						����
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.mzmc }
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��Դ��
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.syd }
					</span>
				</td>
			</tr>
			<!-- �ڶ��� -->
			<tr style="height:30px">
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
					<logic:equal value="����ѧԺ" property="xymc" name="rs">ϵ������</logic:equal>
					<logic:notEqual value="����ѧԺ" property="xymc" name="rs"><bean:message key="lable.xb" /></logic:notEqual>
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xymc }
					</span>
				</td>
				<td align="center"colspan="3">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						רҵ
					</span>
				</td>
				<td align="center"colspan="5">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.zymc }
					</span>
				</td>
				<td align="center"colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						ѧ��
					</span>
				</td>
				<td align="center"colspan="4">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xh }
					</span>
				</td>
				<td align="center"colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��ϵ�绰
					</span>
				</td>
				<td align="center"colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.sjhm }
					</span>
				</td>
			</tr>
			<!-- ��ѧ����������� -->
			<tr style="height:30px">
				<td rowspan="${xszzNum+2 }" align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��ѧ����<br/>�������
					</span>
				</td>
				<td align="center" colspan="5">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��������Ŀ����
					</span>
				</td>
				<td align="center" colspan="6">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						�������
					</span>
				</td>
				<td align="center" colspan="9">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						ʹ���������;��
					</span>
				</td>
			</tr>
			<logic:iterate name="xszzList" id="xszz">
			<tr style="height:30px">
				<td align="center" colspan="5">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${xszz.xmmc }&nbsp;&nbsp;
					</span>
				</td>
				<td align="center" colspan="6">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${xszz.xmzzje }&nbsp;&nbsp;
					</span>
				</td>
				<td align="center" colspan="9">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;&nbsp;
					</span>
				</td>
			</tr>
			</logic:iterate>
			<tr style="height:30px">
				<td align="left" colspan="20">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						ע����������Ŀ��������ѧ���������ѧ�𡢹�����־��ѧ��ѧУ��ѧ����ᰮ����ѧ�����Ѳ����ȡ�
					</span>
				</td>
			</tr>
			<!-- ��ѧ��μӹ������ -->
			<tr style="height:30px">
				<td align="center" colspan="4">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��ѧ��μӹ������
					</span>
				</td>
				<td align="left" colspan="18">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						�����Ͷ���&nbsp;&nbsp;&nbsp;Сʱ��������&nbsp;&nbsp;&nbsp;�Σ��޳���Ѫ��&nbsp;&nbsp;&nbsp;ml
					</span>
				</td>
			</tr>
			<!-- �༶����С�� -->
			<!-- �༶����С���1�� -->
			<tr style="height:20px">
				<td id="xmxx" align="center" >
					<%-- background="<%=request.getContextPath()%>/xszz/comm/print/hzsfxy/bjxx.png" style="width:6%;height:20" --%>
					��Ŀ<%-- <img src="<%=request.getContextPath()%>/xszz/comm/print/hzsfxy/bjxx.png" border="0" align="absmiddle" style="width:100%;height:20" /> --%>
				</td>
				<td align="center" colspan="8">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��&nbsp;&nbsp;��&nbsp;&nbsp;��
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						�Ʒ�
					</span>
				</td>
				<td align="center" colspan="11">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						�ο��Ʒֵ�
					</span>
				</td>
			</tr>
			<!-- �༶����С���2�� -->
			<tr style="height:30px">
				<td align="center" rowspan="7">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��<br/>
						��<br/>
						��<br/>
						��<br/>
						С<br/>
						��
					</span>
				</td>
				<td align="left" colspan="8">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						�����ѽϷǼ�ͥ��������ѧ��
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;
					</span>
				</td>
				<td align="left" colspan="11">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						0����ࡴ10�����ѡ�20��������30
					</span>
				</td>
			</tr>
			<!-- �༶����С���3�� -->
			<tr style="height:30px">
				<td align="left" colspan="8">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						����ѧϰ̬��
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;
					</span>
				</td>
				<td align="left" colspan="11">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						0��һ�㡴5�����á�10���ܺá�15
					</span>
				</td>
			</tr>
			<!-- �༶����С���4�� -->
			<tr style="height:30px">
				<td align="left" colspan="8">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						���빫�����
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;
					</span>
				</td>
				<td align="left" colspan="11">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						0�����á�5��������10���ܺá� 20
					</span>
				</td>
			</tr>
			<!-- �༶����С���5�� -->
			<tr style="height:30px">
				<td align="left" colspan="8">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						����У��У�����
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;
					</span>
				</td>
				<td align="left" colspan="11">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						0��һ�㡴5�����á�10���ܺá�15
					</span>
				</td>
			</tr>
			<!-- �༶����С���6�� -->
			<tr style="height:30px">
				<td align="left" colspan="8">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��ʵ���ŵĳ̶�
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;
					</span>
				</td>
				<td align="left" colspan="11">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						-10�������š�0��һ�㡴5�����š�10
					</span>
				</td>
			</tr>
			<!-- �༶����С���7�� -->
			<tr style="height:30px">
				<td align="left" colspan="8">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						Ϊ�˴��·���
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;
					</span>
				</td>
				<td align="left" colspan="11">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						0&lt;�о��е㲻��Ⱥ&nbsp;5&lt;������ͬѧһ��&nbsp;10&lt;��������ǿ
					</span>
				</td>
			</tr>
			<!-- �༶����С���8�� -->
			<tr style="height:30px">
				<td align="left" colspan="8">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						�Ʒ�С��
					</span>
				</td>
				<td align="center" colspan="13">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;
					</span>
				</td>
			</tr>
			<!-- ���ѵȼ� -->
			<tr style="height:30px">
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						����<br/>�ȼ�
					</span>
				</td>
				<td align="center" colspan="4">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��ͥ�����ر����ѣ�A��
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<%--  
						<logic:equal value="��������" property="knjb" name="rs">
							��
						</logic:equal>
						<logic:notEqual value="��������" property="knjb" name="rs">
						&nbsp;
						</logic:notEqual>
						 --%>
					</span>
				</td>
				<td align="center" colspan="7">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��ͥ����һ�����ѣ�B��
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<%--  
						<logic:equal value="һ������" property="knjb" name="rs">
							��
						</logic:equal>
						<logic:notEqual value="һ������" property="knjb" name="rs">
						&nbsp;
						</logic:notEqual>
						 --%>
					</span>
				</td>
				<td align="center" colspan="4">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��ͥ���ò����ѣ�C��
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<%--  
						<logic:present name="rs" property="knjb">
							&nbsp;
						</logic:present>
						<logic:notPresent name="rs" property="knjb">
							��
						</logic:notPresent>
						 --%>
					</span>
				</td>
			</tr>
			<!--  -->
			<tr style="height:80px">
				<td align="center" colspan="22">
					<p style="height:60px" align="left">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						����������<br/>
					</span>
					</p>
					<div align="center">
						<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							��֧��ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ϵ��ʽ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ϵ��ʽ��
						</span>
					</div>
					<br/>
				</td>
			</tr>
			<tr style="height:80px">
				<td align="center">
					<logic:equal value="����ѧԺ" property="xymc" name="rs">ϵ<br/>��<br/>��<br/>��<br/></logic:equal>
					<logic:notEqual value="����ѧԺ" property="xymc" name="rs">ѧ<br/>Ժ<br/></logic:notEqual>
					��<br/>
					��
				</td>
				<td align="center" colspan="21">
					<p style="height:60px" align="left">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					</span>
					</p>
					<div align="right">
						<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:equal value="����ѧԺ" property="xymc" name="rs">ϵ������</logic:equal>
							<logic:notEqual value="����ѧԺ" property="xymc" name="rs"><bean:message key="lable.xb" /></logic:notEqual>���£�
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</div>
					<br/>
					<div align="right">
						<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							��&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</div>
					<br/>
				</td>
			</tr>
		</table>
		</td>
		</tr>
		</table>
		<br>
		<div align="center" class='noPrin'>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				ֱ�Ӵ�ӡ
			</button>
		</div>
<script>
function line(x1,y1,x2,y2,color)
{
var tmp
if(x1>=x2)
{
tmp=x1;
x1=x2;
x2=tmp;
tmp=y1;
y1=y2;
y2=tmp;
}
for(var i=x1;i<=x2;i++)
{
x = i;
y = (y2 - y1) / (x2 - x1) * (x - x1) + y1;
a(x,y,color);
}
}
//left:0,top:331,width:71,heigth:23
//x1:1,y1:331,x2:71,y2:354
//ҳ��ߣ�1412��ҳ���737
//line(0,331,37,354,"#000000");//��ȷ��
//alert("��ʽ��"+parseInt(((document.body.clientWidth*0.85)*0.06)/2));
//alert("x��ʽ��"+parseInt((document.body.clientWidth*0.01)*4.1));
//alert("�ߣ�"+document.body.clientHeight);
//alert("left��"+xmxx.offsetLeft+"top��"+xmxx.offsetTop+"��"+xmxx.offsetWidth+"�ߣ�"+xmxx.offsetHeight);
//line(xmxx.offsetLeft,xmxx.offsetTop,xmxx.offsetLeft+xmxx.offsetWidth,xmxx.offsetTop+xmxx.offsetHeight,'#000000')//ԭ
//���
//line(xmxx.offsetLeft,xmxx.offsetTop,parseInt(((document.body.clientWidth*0.85)*0.06)/2),xmxx.offsetTop+xmxx.offsetHeight,'#000000')
</script>
		</html:form>
	</body>
</html>
