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
		<html:form action="/zxdk_xnmz" method="post">
		<table width="100%" id="rsT" class="printstyle">
			<tr height='50px'>
				<td  colspan=8 valign="middle" >
					<p align=center style='font-size:14.0pt;'>
						<b>
							�й����й�����ѧ��������������
						</b>
					</p>
				</td>
			</tr>
			<tr  height='30px'>
				<td  valign=bottom >
					�����������${rs.xm }
				</td>
				<td colspan=3 valign=bottom >
					�Ա�${rs.xb }
				</td>
				<td  colspan=4 valign=bottom >
					�������ڣ�${rs.csrq }
				</td>
			</tr>
			<tr  height='30px'>
				<td  valign=bottom >
					�Ͷ�ѧУ��${xxmc }
				</td>
				<td  colspan=6 valign=bottom >
					���֤���룺${rs.sfzh }
				</td>
				<td   valign=bottom >
					ѧ�ţ�${rs.xh }
				</td>
			</tr>
			<tr  height='30px'>
				<td colspan=2 valign=bottom >
					Ժϵ��${rs.xymc }
				</td>
				<td colspan=4 valign=bottom >
					רҵ��${rs.zymc }
				</td>
				<td colspan=2 valign=bottom >
					����绰��${rs.qsdh }
				</td>
			</tr>
			<tr  height='30px'>
				<td colspan=2 valign=bottom >
					ѧ��ѧ����${rs.xz }
				</td>
				<td  colspan=6 valign=bottom >
					&nbsp;
				</td>
			</tr>
			<tr  height='30px'>
				<td  colspan=2 rowspan=3 >
					���������
				</td>
				<td  colspan=4 rowspan=3 >
					�ܽ�${rs.dkzje }
				</td>
				<td  colspan=2 valign=bottom >
						<font color='#3366FF'>���У�ѧ�ӷѴ���</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#3366FF'>Ԫ
				</td>
			</tr>
			<tr  height='30px'>
				<td  colspan=2 valign=bottom >
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#3366FF'>ס�޷�</font>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#3366FF'>Ԫ</font>
				</td>
			</tr>
			<tr  height='30px'>
				<td  colspan=2 valign=bottom >
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#3366FF'>�����</font>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#3366FF'>Ԫ</font>
				</td>
			</tr>
			<tr>
				<td colspan=2 rowspan=3 >
					��������
				</td>
				<td  colspan=6 valign=bottom >
					<span style='color:fuchsia'>ѧ�ӷѴ��</span>
					<span style='color:fuchsia'>&nbsp;��</span> 
					<span style='color:fuchsia'>&nbsp;��</span>
					<span style='color:fuchsia'>&nbsp;&nbsp;</span>
					<span >��</span>
					<span >&nbsp;&nbsp; </span>
					<span >��</span><span >&nbsp;</span>
					<span >��</span>
					<span >&nbsp;&nbsp;</span>
					<span >����</span>
					<span >&nbsp;&nbsp; </span>
					<span >��</span>
					<span >&nbsp;&nbsp; </span>
					<span >��</span>
					<span>&nbsp;</span>
					<span >��</span>
				</td>
			</tr>
			<tr  height='30px'>
				<td  colspan=6 valign=bottom >
					<span style='color:fuchsia'>����Ѵ��</span>
					<span style='color:fuchsia'>&nbsp;��</span> 
					<span style='color:fuchsia'>&nbsp;��</span>
					<span style='color:fuchsia'>&nbsp;&nbsp;</span>
					<span >��</span>
					<span >&nbsp;&nbsp; </span>
					<span >��</span><span >&nbsp;</span>
					<span >��</span>
					<span >&nbsp;&nbsp;</span>
					<span >����</span>
					<span >&nbsp;&nbsp; </span>
					<span >��</span>
					<span >&nbsp;&nbsp; </span>
					<span >��</span>
					<span>&nbsp;</span>
					<span >��</span>
				</td>
			</tr>
			<tr height='30px'>
				<td  colspan=6 valign=bottom >
					<span style='color:fuchsia'>ס�޷Ѵ��</span>
					<span style='color:fuchsia'>&nbsp;��</span> 
					<span style='color:fuchsia'>&nbsp;��</span>
					<span style='color:fuchsia'>&nbsp;&nbsp;</span>
					<span >��</span>
					<span >&nbsp;&nbsp; </span>
					<span >��</span><span >&nbsp;</span>
					<span >��</span>
					<span >&nbsp;&nbsp;</span>
					<span >����</span>
					<span >&nbsp;&nbsp; </span>
					<span >��</span>
					<span >&nbsp;&nbsp; </span>
					<span >��</span>
					<span>&nbsp;</span>
					<span >��</span>
				</td>
			</tr>
			<tr  height='30px'>
				<td width=187 colspan=2 rowspan=2 valign=top >
					<p>
						��ͥ��ϸסַ��${rs.jtdz }
					</p>
					<p>
						�ʱࣺ${rs.jtyb }
					</p>
					<p>
						�绰��${rs.jtdh }
					</p>
					<p>
						����������${rs.fqxm }
					</p>
					<p>
						ĸ��������${rs.mqxm }
					</p>
					<p>
						��ͥ�����룺${rs.jtysr }
					</p>
				</td>
				<td width=373 colspan=6 valign=top >
					<p>
						<span style='font-family:����;
  &quot;Times New Roman&quot;'>���˱�֤������д������ʵ���󣬲������Ͽɣ���������ˣ�ǩ�֣���</span>
					</p>
					<p>
						<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span><span style='font-family:����;
  &quot;Times New Roman&quot;'>��</span><span
							lang=EN-US>&nbsp;&nbsp; </span><span
							style='font-family:
  ����;&quot;Times New Roman&quot;'>��</span><span
							lang=EN-US>&nbsp;&nbsp; </span><span
							style='font-family:����;"Times New Roman"'>��</span>
					</p>
				</td>
			</tr>
			<tr  height='30px'>
				<td width=373 colspan=6 valign=top >
					<p>
						<span style='font-family:����;
  &quot;Times New Roman&quot;'>���������ϵ��У�Ͷ�ѧ������������������ʵ���ش�֤����</span>
					</p>
					<p>
						<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span><span style='font-family:����;
  &quot;Times New Roman&quot;'>���������</span><span
							lang=EN-US>/</span><span style='font-family:����;"Times New Roman"'><bean:message key="lable.xb" /></span>
					</p>
					<p>
						<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span><span style='font-family:����;
  &quot;Times New Roman&quot;'>�����£���</span>
					</p>
					<p>
						<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span
							style='font-family:����;"Times New Roman"'>��</span><span lang=EN-US>&nbsp;&nbsp;
						</span><span style='font-family:
  ����;&quot;Times New Roman&quot;'>��</span><span
							lang=EN-US>&nbsp;&nbsp; </span><span
							style='font-family:����;"Times New Roman"'>��</span>
					</p>
				</td>
			</tr>
			<tr  height='30px'>
				<td  colspan=3 valign=bottom >
					��֤��������${rs.jzrxm }
				</td>
				<td  colspan=2 valign=bottom >
					�Ա�${rs.jzrxb }
				</td>
				<td  colspan=3 valign=bottom >
					�������ڣ�
				</td>
			</tr>
			<tr  height='30px'>
				<td  colspan=6 valign=bottom >
					���֤���룺${rs.jzrzjh}
				</td>
				<td  colspan=2 valign=bottom >
					�������˹�ϵ��${rs.jzrgx}
				</td>
			</tr>
			<tr  height='30px'>
				<td  colspan=3 valign=bottom >
					��ס����ַ��${rs.jzrdz }
				</td>
				<td width=150 colspan=3 valign=bottom >
					�ʱࣺ${rs.jzryb }
				</td>
				<td width=187 colspan=2 valign=bottom >
					�绰��${rs.jzrdh }
				</td>
			</tr>
			<tr  height='90px'>
				<td colspan=8 >
					��֤�������
					${rs.jzryj }
					<p align="right" >&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				</td>
			</tr>
			<tr  height='100px'>
				<td  colspan=8 >
					�Ŵ�Ա�����
					<p align="right" >&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				</td>
			</tr>
			<tr  height='100px'>
				<td  colspan=8  >
					��Ȩ��׼�������
					<p align="right" >&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				</td>
			</tr>
			
		</table>

		</html:form>
	</body>
</html>
