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

		<html:form action="/data_search" method="post">
		<div align="right">
			<table width="15%" border="1" bordercolor="black" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" width="50%" align="center">
						<font style="size: 20px">���</font>
					</td>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</div>
		<br><br><br><br><br><br><br>
		<div align="center" style="font-size:28px;font:'����' "><b>����ְҵ����<bean:message key="lable.xsgzyxpzxy" />�༶��ɫ��Ŀ����</b></div>
		<br><br><br><br>
		<div align="center" style="font-size:25px;"><b>�걨��</b></div>
		<br><br><br><br><br><br><br><br><br><br><br>
		<div align="center">
			<table width="50%">
				<tr>
					<td height="50" width="50%" align="center">
						<span style="font-size:14.0pt;">�� Ŀ �� �ƣ�</span>
					</td>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</td>	
				</tr>
				<tr>
					<td height="50" width="50%" align="center">
						<span style="font-size:14.0pt;">��������༶��</span>
					</td>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td height="50" width="50%" align="center">
						<span style="font-size:14.0pt;">�� �� �� Ժ��</span>
					</td>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</div>
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		<div align="center" style="font-size:20px;"><b>����ְҵ����<bean:message key="lable.xsgzyxpzxy" />ѧ������</b></div>
		<div align="center" style="font-size:20px;"><b>������   ��    ��    ��</b></div>
		<br><br><br><br><br><br><br><br><br><br><br>
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<th width="10%" rowspan="6">
					<div align="center">
						1-1<br><br>����<br><br>��Ϣ
					</div>
				</th>
				<th width="15%">
					<div align="center">
						�༶����
					</div>
				</th>
				<th colspan="2" width="35%">
					<div align="center">
						<bean:write name='rs' property="bjmc" />
					</div>
				</th>
				<th width="20%">
					<div align="center">
						������
					</div>	
				</th>
				<th width="20%">
					<div align="center">
						<bean:write name='rs' property="bzrxm" />
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div align="center">
						�೤
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						<bean:write name='rs' property="bzxm" />
					</div>
				</th>
				<th>
					<div align="center">
						��֧������
					</div>	
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="tzsxm" />
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div align="center">
						������Ժ
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						<bean:write name='rs' property="xymc" />
					</div>
				</th>
				<th>
					<div align="center">
						����
					</div>	
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="bjrs" />
					</div>
				</th>
			</tr>
			<tr>
				<th rowspan="3">
					<div align="center">
						��ϵ����Ϣ
					</div>
				</th>
				<th width="17%">
					<div align="center">
						����
					</div>
				</th>
				<th width="18%">
					<div align="center">
						<bean:write name='rs' property="lxrxm" />
					</div>
				</th>
				<th>
					<div align="center">
						ְ��
					</div>	
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="lxrzw" />
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div align="center">
						�ֻ��̺�
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="lxrsjdh" />
					</div>
				</th>
				<th>
					<div align="center">
						���Ҽ��绰
					</div>	
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="lxrqs" /> <bean:write name='rs' property="lxrqsdh" />
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div align="center">
						�ֻ�
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="lxrsj" />
					</div>
				</th>
				<th>
					<div align="center">
						E-mail
					</div>	
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="lxremail" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 780px;">
					<div align="center">
						1-2<br>�༶<br>��У<br>����<br>Ҫ��<br>����<br>��Ŀ<br>����<br>�Ѿ�<br>������
					</div>
				</th>
				<th colspan="5" valign="top">
					<div align="left">
						<bean:write name='rs' property="bjcjtj" />
					</div>
				</th>
			</tr>
			</table>
			<br><br>
			<table width="100%" id="rsT" class="printstyle">
			<tr>
				<th width="10%" rowspan="4">
					<div align="center">
						2-1<br>��Ŀ<br>����<br>����<br>������<br>��ɫ<br>������

					</div>
				</th>
				<th>
					<div align="left">
						��Ŀ���ƣ�<bean:write name='rs' property="xmmc" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 140px;" valign="top">
					<div align="left">
						��Ŀ��ɫ��<br><bean:write name='rs' property="xmts" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 200px;" valign="top">
					<div align="left">	
						��Ŀ�������������<br><bean:write name='rs' property="xmjsfa" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 200px;" valign="top">
					<div align="left">
						���ȣ�<br><bean:write name='rs' property="xmjd" />
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div align="center">
						2-2<br>ȫ��<br><br>ͬѧ<br><br>�Է�<br><br>����<br><br>��ͬ
					</div>
				</th>
				<th style="height: 390px;" valign="top">
					<div align="left">
						<bean:write name='rs' property="qbrt" />
					</div>
				</th>
			</tr>
		</table>
		<br>
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<th style="height: 150px;" valign="top" colspan="5">
					<div align="left">
						��ĿԤ��Ч��:<br>
						<bean:write name='rs' property="yqxg" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 150px;" valign="top" colspan="5">
					<div align="left">
						��Ŀ����Ҫ��:<br>
						<bean:write name='rs' property="ysyd" />
					</div>
				</th>
			</tr>
			<tr>
				<th  width="10%" rowspan="7">
					<div align="center">
						4-1<br>����<br>ѧ��<br>����<br>����<br>��<br>Ԥ��
					</div>
				</th>
				<th width="22%">
					<div align="center">
						֧����Ŀ
					</div>
				</th>
				<th width="22%">
					<div align="center">
						�ϰ����
					</div>
				</th>
				<th width="22%">
					<div align="center">
						�°����
					</div>
				</th>
				<th width="24%">
					<div align="center">
						�ϼ�
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zz_xm0" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_sbzz0" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_xbzz0" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_hj0" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zz_xm1" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_sbzz1" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_xbzz1" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_hj1" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zz_xm2" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_sbzz2" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_xbzz2" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_hj2" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zz_xm3" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_sbzz3" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_xbzz3" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_hj3" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zz_xm4" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_sbzz4" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_xbzz4" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_hj4" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						�ϼ�
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_sbhj" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_xbhj" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_zhj" />
					</div>
				</th>
			</tr>
			<tr>
				<th  width="10%" rowspan="7">
					<div align="center">
						4-2<br>�༶<br>����<br>����<br>Ԥ��
					</div>
				</th>
				<th width="22%">
					<div align="center">
						֧����Ŀ
					</div>
				</th>
				<th width="22%">
					<div align="center">
						�ϰ����
					</div>
				</th>
				<th width="22%">
					<div align="center">
						�°����
					</div>
				</th>
				<th width="24%">
					<div align="center">
						�ϼ�
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zy_xm0" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_sbzz0" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_xbzz0" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_hj0" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zy_xm1" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_sbzz1" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_xbzz1" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_hj1" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zy_xm2" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_sbzz2" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_xbzz2" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_hj2" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zy_xm3" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_sbzz3" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_xbzz3" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_hj3" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zy_xm4" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_sbzz4" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_xbzz4" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_hj4" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						�ϼ�
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_sbhj" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_xbhj" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_zhj" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 60px;">
					<div align="center">
						������<br>���
					</div>
				</th>
				<th colspan="4">
					<div align="left">
						<bean:write name='rs' property="bzryj" />
					</div>
					<br>
					<div align="right">
						�����ˣ�
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name='rs' property="bzrxm" />
					</div>
					<div align="right">
						<bean:write name='rs' property="bzrshsj" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 60px;">
					<div align="center">
						������<br>Ժ��ϵ��<br>���
					</div>
				</th>
				<th colspan="4">
					<div align="left">
						<bean:write name='rs' property="xyyj" />
					</div>
					<br>
					<div align="right">
						�����ˣ�	
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						�����£�
					</div>
					<div align="right">
						<bean:write name='rs' property="xyshsj" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 60px;">
					<div align="center">
						ѧ����<br>���
					</div>
				</th>
				<th colspan="4">
					<div align="left">
						<bean:write name='rs' property="xxyj" />
					</div>
					<br>
					<div align="right">
						�����ˣ�	
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						�����£�
					</div>
					<div align="right">
						<bean:write name='rs' property="xxshsj" />
					</div>
				</th>
			</tr>
		</table>
			<div align="center" class='noPrin'>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
					ҳ������
				</button>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
					��ӡԤ��
				</button>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
					ֱ�Ӵ�ӡ
				</button>
			</div>
		</html:form>
	</body>
</html>
