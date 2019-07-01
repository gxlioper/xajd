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

		<html:form action="/stu_info_add" method="post">
		<div align="center" style="font-size:28px;font:'����' "><b>������Ϣְҵ����<bean:message key="lable.xsgzyxpzxy" /></b></div>
		<div align="center" style="font-size:18px;"><b>ѧ������ǼǱ�</b></div>
		<br>
		<div align="left" style="font-size:10px;">
		<b>
		<logic:equal name="xs" value="yes">
		<bean:write name='rs' property="xymc" />&nbsp;ϵ&nbsp;<bean:write name='rs' property="bjmc" />��
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                    
		ѧ��  <bean:write name='rs' property="xh" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		��д���ڣ�<bean:write name='rs' property="year" />��<bean:write name='rs' property="month" />��<bean:write name='rs' property="day" />��
		</logic:equal>
		<logic:equal name="xs" value="no">
		_________________ϵ&nbsp;_________________��
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;               
		ѧ��____________
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		��д���ڣ�&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
		</logic:equal>
		</b>
		</div>
		<br>
		<table width="100%" id="rsT" class="printstyle">
			<tr>
			<td>
			<table width="99%" class="printstyle">
				<tr style="height:22px">
					<th width="10%">
						<div align="center">
							����
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="xm" />
						</div>
					</th>
					<th width="10%">
						<div align="center">
							������
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="cym" />
						</div>
					</th>
					<th width="5%">
						<div align="center">
							�Ա�
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="xb" />
						</div>
					</th>
					<th width="10%">
						<div align="center">
							��������
						</div>
					</th>
					<th width="20%" colspan="2">
						<div align="center">
							<bean:write name='rs' property="csrq" />
						</div>
					</th>
					<th  rowspan="5">
						<div align="center">
							����Ƭ��
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							���<br>֤��
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="sfzh" />
						</div>
					</th>
					<th>
						<div align="center">
							׼��֤��
						</div>
					</th>
					<th colspan="3">
						<div align="center">
							<bean:write name='rs' property="zkzh" />
						</div>
					</th>
					<th width="10%">
						<div align="center">
							��ѧ<br>�ɼ�
						</div>
					</th>
					<th width="10%" >
						<div align="center">
							<bean:write name='rs' property="rxcj" />
						</div>
					</th>
				</tr>
				<tr>
					<th colspan="3">
						<div align="center">
							��ʱ�εغ��˽������ţ�����
						</div>
					</th>
					<th colspan="4">
						<div align="center">
							<bean:write name='rs' property="rdsj" />
							<bean:write name='rs' property="rddd" />
							<bean:write name='rs' property="rdjsr" />
						</div>
					</th>
					<th>
						<div align="center">
							����
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="mzmc" />
						</div>
					</th>
				</tr>
				<tr>
					<th colspan="3">
						<div align="center">
							��ͥ��ϸ��ַ
						</div>
					</th>
					<th colspan="6">
						<div align="center">
							<bean:write name='rs' property="jtdz" />	
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							�ʱ�
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="yzbm" />	
						</div>
					</th>
					<th>
						<div align="center">
							լ��
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="jtdh" />	
						</div>
					</th>
					<th>
						<div align="center">
							���˵绰
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="lxdh" />	
						</div>
					</th>
				</tr>
				<tr>
					<th colspan="3">
						<div align="center">
							���˰����س�
						</div>
					</th>
					<th colspan="7">
						<div align="center">
							<bean:write name='rs' property="grahtc" />	
						</div>
					</th>
				</tr>
			</table>
			</td>
			</tr>
			<tr>
			<td>
			<table  width="99%" class="printstyle">
				<tr style="height:22px">
					<th width="5%" rowspan="4">
						<div align="center">
							��<br>��<br>��<br>Ҫ<br>��<br>��
						</div>
					</th>
					<th width="15%" colspan="2">
						<div align="center">
							��ʱ��
						</div>
					</th>
					<th width="15%" colspan="2">
						<div align="center">
							��ʱֹ
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							�ںεأ���ѧУ��ε�λѧϰ������	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							�κ�ְ
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjlkssj1" />	
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjljssj1" />	
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjlnr1" />	
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="grjlzw1" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjlkssj2" />	
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjljssj2" />	
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjlnr2" />	
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="grjlzw2" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjlkssj3" />	
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjljssj3" />	
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjlnr3" />	
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="grjlzw3" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%" rowspan="5">
						<div align="center">
							��<br>ͥ<br>��<br>Ա<br>��<br>��
						</div>
					</th>
					<th width="5%">
						<div align="center">
							��ν
						</div>
					</th>
					<th width="10%">
						<div align="center">
							����
						</div>
					</th>
					<th width="7%">
						<div align="center">
							����
						</div>
					</th>
					<th width="8%">
						<div align="center">
							������ò
						</div>
					</th>
					<th width="35%">
						<div align="center">
							�ε�λ�����Ź���
						</div>
					</th>
					<th width="10%">
						<div align="center">
							��������
						</div>
					</th>
					<th width="20%">
						<div align="center">
							��ϵ�绰
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="jtcych1" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcyxm1" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="jtcynl1" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="jtcyzzmm1" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="jtcydw1" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcysr1" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="jtcydh1" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="jtcych2" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcyxm2" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="jtcynl2" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="jtcyzzmm2" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="jtcydw2" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcysr2" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="jtcydh2" />	
						</div>
					</th>
				</tr>
								<tr style="height:22px">
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="jtcych3" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcyxm3" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="jtcynl3" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="jtcyzzmm3" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="jtcydw3" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcysr3" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="jtcydh3" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="jtcych4" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcyxm4" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="jtcynl4" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="jtcyzzmm4" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="jtcydw4" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcysr4" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="jtcydh4" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%" rowspan="4">
						<div align="center">
							��<br>Ҫ<br>��<br>��<br>��<br>ϵ<br>��<br>��
						</div>
					</th>
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="shgxch1" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxxm1" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="shgxnl1" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="shgxzzmm1" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="shgxdw1" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxsr1" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="shgxdh1" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="shgxch2" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxxm2" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="shgxnl2" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="shgxzzmm2" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="shgxdw2" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxsr2" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="shgxdh2" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="shgxch3" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxxm3" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="shgxnl3" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="shgxzzmm3" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="shgxdw3" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxsr3" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="shgxdh3" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="shgxch4" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxxm4" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="shgxnl4" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="shgxzzmm4" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="shgxdw4" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxsr4" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="shgxdh4" />	
						</div>
					</th>
				</tr>
				<tr style="height:200px">
					<th>
						<div align="center">
							��<br>ע
						</div>
					</th>
					<th colspan="7" height="100">
						<div align="center">
							<bean:write name='rs' property="bz" />	
						</div>
					</th>
				</tr>
			</table>
			</td>
			</tr>
		</table>
			
		<br><br>
		<div align="center" style="font-size:18px;"><b>ѧ���ۺ������������</b></div>	<br><br>
		<table width="100%" id="rsT" class="printstyle">
			<tr>
			<td>
			<table class="printstyle" width="100%">
				<tr>
					<td width="2%" align="center">
						�꼶
					</td>
					<td width="10%" align="center">
						ѧ��
					</td>
					<td width="10%" align="center">
						����
					</td>
					<td width="10%" align="center">
						����
					</td>
					<td width="10%" align="center">
						����
					</td>
					<td width="10%" align="center">
						�ۺϲ���
					</td>
					<td width="10%" align="center">
						����
					</td>
					<td width="" align="center">
						ѧ�꽱���������ְ���
					</td>
					<td width="10%" align="center">
						��¼��
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						һ�꼶
					</td>
					<td>
						��ѧ��
					</td>
					<td>
						<bean:write name='stuInfo' property="yydcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yyzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yytcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yykpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yybjpm" />
					</td>
					<td rowspan="2" valign="top" height="100">
						<bean:write name='stuInfo' property="yqk" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						��ѧ��
					</td>
					<td>
						<bean:write name='stuInfo' property="yrdcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yrzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yrtcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yrkpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yrbjpm" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="2">
						���꼶
					</td>
					<td>
						��ѧ��
					</td>
					<td>
						<bean:write name='stuInfo' property="rydcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="ryzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="rytcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="rykpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="rybjpm" />
					</td>
					<td rowspan="2" valign="top" height="100">
						<bean:write name='stuInfo' property="rqk" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						��ѧ��
					</td>
					<td>
						<bean:write name='stuInfo' property="rrdcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="rrzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="rrtcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="rrkpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="rrbjpm" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="2">
						���꼶
					</td>
					<td>
						��ѧ��
					</td>
					<td>
						<bean:write name='stuInfo' property="sydcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="syzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="sytcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="sykpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="sybjpm" />
					</td>
					<td rowspan="2" valign="top" height="100">
						<bean:write name='stuInfo' property="sqk" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						��ѧ��
					</td>
					<td>
						<bean:write name='stuInfo' property="srdcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="srzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="srtcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="srkpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="srbjpm" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="2">
						���꼶
					</td>
					<td>
						��ѧ��
					</td>
					<td>
						<bean:write name='stuInfo' property="xydcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xyzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xytcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xykpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xybjpm" />
					</td>
					<td rowspan="2" valign="top" height="100">
						<bean:write name='stuInfo' property="xqk" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						��ѧ��
					</td>
					<td>
						<bean:write name='stuInfo' property="xrdcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xrzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xrtcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xrkpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xrbjpm" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="2">
						���꼶
					</td>
					<td>
						��ѧ��
					</td>
					<td>
						<bean:write name='stuInfo' property="fydcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="fyzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="fytcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="fykpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="fybjpm" />
					</td>
					<td rowspan="2" valign="top" height="100">
						<bean:write name='stuInfo' property="fqk" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						��ѧ��
					</td>
					<td>
						<bean:write name='stuInfo' property="frdcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="frzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="frtcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="frkpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="frbjpm" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						��ҵ����
					</td>
					<td colspan="8" height="200" align="right" valign="bottom">
					�����Σ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;
					��
					</td>
				</tr>
			</table>
			</td>
			</tr>
		</table>
		<br>
		<div align="left" style="font-size:15px;">˵����1���ۺϲ����ɼ����������ɼ��������ɼ��������ɼ������е����ɼ�ռ30%�������ɼ�ռ60%�������ɼ�ռ10%���ۺϲ����ɼ�Ϊ����֮�͡�</div>
		<br>
		<div align="left" style="font-size:15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2����������̼�ظֱʻ�����ˮ����д��һʽ���ݣ�һ��<bean:message key="lable.xsgzyxpzxy" />�浵��һ��<bean:message key="lable.xb" />���桢һ�ݹ���ѧ��������</div>
		
		
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
