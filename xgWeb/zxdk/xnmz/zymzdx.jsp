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
		<%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
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
.noPrin {
	display: none;
}
</style>
		<!-- end -->
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript">
		jQuery(function($) {
			
			//�Ա�ѡ
			var selectXb = jQuery("#selectXb").val();
			jQuery("input[name=xb]").each(function() {
				if (jQuery(this).val() == selectXb) {
					jQuery(this).attr("checked", true);
				}
			});

			//������ι�ѡ
			var selectPycc = jQuery("#selectPycc").val();
			jQuery("input[name=pycc]").each(function(){
				if (jQuery(this).val() == selectPycc) {
					jQuery(this).attr("checked", true);
				}
			})
			
			});
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		<html:form action="/zxdk_xnmz" method="post">
			<table width="100%" id="rsT" class="printstyle">
				<tr height='50px'>
					<td colspan=8 valign="middle">
						<p align=center style='font-size: 14.0pt;'>
							<b> �й����й�����ѧ�������������� </b>
						</p>
					</td>
				</tr>
				<tr height='30px'>
					<td valign=bottom>
						�����������${rs.xm }
					</td>
					<td colspan=3 valign=bottom>
						�Ա�
						<input type="hidden" id="selectXb" value="${rs.xb}" />
						<input type='checkbox' value='��' name="xb" disabled="disabled" />
						��
						<input type='checkbox' value='Ů' name="xb" disabled="disabled" />
						Ů
					</td>
					<td colspan=4 valign=bottom>
						�������ڣ�${rs.csrq }
					</td>
				</tr>
				<tr height='30px'>
					<td valign=bottom>
						�Ͷ�ѧУ��${xxmc }
					</td>
					<td colspan=6 valign=bottom>
						���֤���룺${rs.sfzh }
					</td>
					<td valign=bottom>
						�꼶��${rs.nj }
					</td>
				</tr>
				<tr height='30px'>
					<td colspan=1 valign=bottom>
						Ժϵ��${rs.xymc }
					</td>
					<td colspan=3 valign=bottom>
						רҵ��${rs.zymc }
					</td>
					<td colspan=2 valign=bottom>
						����绰��${rs.qsdh }
					</td>
					<td colspan=2 valign=bottom>
						ѧ�ţ�${rs.xh }
					</td>
				</tr>
				<tr height='30px'>
					<td colspan=1 valign=bottom>
						ѧ�ƣ�${rs.xz } ��
					</td>
					<td colspan=7 valign=bottom>
						ѧ����
						<input type="hidden" id="selectPycc" value="${rs.pycc}" />
						<logic:iterate id="pycc" name="pyccList" offset="1">
							<input type="checkbox" value="${pycc.dm}" name="pycc" disabled="disabled" />${pycc.mc }"
						</logic:iterate>
					</td>
				</tr>
				<tr height='30px'>
					<td colspan=1 >
						���������
					</td>
					<td colspan=7 >
						&nbsp;&nbsp;�ܽ�${rs.dkzje } Ԫ
						&nbsp;&nbsp;
						<font color='#3366FF'>���У�ѧ�ӷѴ���______</font>
						<font color='#3366FF'>Ԫ 
						
						<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color='#3366FF'>ס�޷�______</font>
						<font color='#3366FF'>Ԫ</font>
						</p>
						
						<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color='#3366FF'>�����______</font>
						<font color='#3366FF'>Ԫ</font>
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=1 >
						��������
					</td>
					<td colspan=7 valign=bottom>
						<span style='color: fuchsia'>�������ޣ�____����      ��_____��___��___����_____��___��___��</span>
					</td>
				</tr>
				<tr height='30px'>
					<td width=187 colspan=3 rowspan=2 valign=top>
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							ְҵ��
						</p>
						<p>
							�������֤���룺${rs.fqsfzh }
						</p>
						<p>
							ĸ��������${rs.mqxm }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							ְҵ��
						</p>
						<p>
							ĸ�����֤���룺${rs.mqsfzh }
						</p>
						<p>
							��ͥ�����룺${rs.jtysr }
						</p>
					</td>
					<td colspan=5 valign=top>
						<p>
							<span style='font-family: ����;'>���˱�֤������д������ʵ���󣬲������Ͽɣ���������ˣ�ǩ�֣���</span>
						</p>
						<p>
							<span lang=EN-US>
							<p>&nbsp;</p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span style='font-family: ����;'>��</span><span lang=EN-US>&nbsp;&nbsp;
							</span><span style='font-family: ����;'>��</span><span lang=EN-US>&nbsp;&nbsp;
							</span><span style='font-family: ����;'>��</span>
						</p>
					</td>
				</tr>
				<tr height='30px'>
					<td colspan=5 valign=top>
						<p>
							<span style='font-family: ����;'>���������ϵ��У�Ͷ�ѧ������������������ʵ���ش�֤����</span>
						</p>
						<p>
							<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span style='font-family: ����;'>���������</span><span lang=EN-US>/</span><span
								style='font-family: ����;'><bean:message key="lable.xb" /></span>
						</p>
						<p>
							<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span style='font-family: ����;'>�����£���</span>
						</p>
						<p>
							<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span
								style='font-family: ����;'>��</span><span lang=EN-US>&nbsp;&nbsp;
							</span><span style='font-family: ����;'>��</span><span lang=EN-US>&nbsp;&nbsp;
							</span><span style='font-family: ����;'>��</span>
						</p>
					</td>
				</tr>
				<tr height='90px'>
					<td colspan=8>
						��ע��
						<p align="right">
							&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>

				<tr height='100px'>
					<td colspan=8>
						�Ŵ�Ա�����
						<p align="right">
							&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr height='90px'>
					<td colspan=8>
						�Ƴ������
						<p align="right">
							&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr height='100px'>
					<td colspan=8>
						��Ȩ��׼�������
						<p align="right">
							&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>

			</table>

		</html:form>
	</body>
</html>
