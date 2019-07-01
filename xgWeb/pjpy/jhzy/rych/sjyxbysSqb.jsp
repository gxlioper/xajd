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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
	<body>
		<center>
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<html:form action="/jhzy_rych" method="post">
				<div class=Section1 style='layout-grid:15.6pt;font-size: 20'>
					�㽭ʡ��ͨ�ߵ�ѧУ�����ҵ���ǼǱ� &nbsp;
				</div>
				<div>
					ѧУ��${xxmc}
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ժ��ϵ����${rs.xymc}&nbsp;&nbsp;&nbsp;&nbsp;
					רҵ��${rs.zymc}&nbsp;&nbsp;&nbsp;&nbsp; �༶��${rs.bjmc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;��
				</div>
				<table width="100%" class="tbstyle" align="center">
					<tr align="center">
						<td width="10%">
							�� ��
						</td>
						<td>
							${rs.xm }
						</td>
						<td width="8%">
							�� ��
						</td>
						<td>
							${rs.xb }
						</td>
						<td colspan=2 width="10%">
							��������
						</td>
						<td>
							${rs.csrq }
						</td>
						<td width="8%">
							�� ��
						</td>
						<td>
							${rs.mzmc }
						</td>
					</tr>
					<tr align="center">
						<td>
							��Դ��
						</td>
						<td colspan=3>
							${rs.syd }
						</td>
						<td colspan=2>
							������ò
						</td>
						<td>
							${rs.zzmmmc }
						</td>
						<td>
							ְ ��
						</td>
						<td>
							${rs.zw }
						</td>
					</tr>
					<tr align="center">
						<td>
							��ͥ��ַ
						</td>
						<td colspan=6>
							${rs.jtdz }
						</td>
						<td>
							��ϵ�绰
						</td>
						<td>
							${rs.sjhm }
						</td>
					</tr>
				</table>
				<table width="100%" class="tbstyle" >
					<tr >
						<td width="10%" align="center">
							��
							<br>
							��
							<br>
							��
							<br>
							��
						</td>
						<td>
							${brjl }
						</td>
					</tr>
					<tr >
						<td align="center">
							��
							<br>
							Ҫ
							<br>
							��
							<br>
							�� 
						</td>
						<td>
							${zysj }
						</td>
					</tr>
					<tr >
						<td align="center">
							��
							<br>
							У
							<br>
							��
							<br>
							��
							<br>
							��
							<br>
							��
							<br>
							��
							<br>
							��
						</td>
						<td valign=top>
							${zxqjhjqk }
						</td>
					</tr>
				</table>
				<table width="100%" class="tbstyle" >
					<tr >
						<td width="10%" align="center">
							Ժ
							<br>
							ϵ
							<br>
							��
							<br>
							��
						</td>
						<td valign=top width="40%">
							${rsShyj.xyshyj }
						</td>
						<td valign=top  width="10%" align="center">
							ѧ
							<br>
							У
							<br>
							��
							<br>
							��
						</td>
						<td valign=top width="40%">
							${rsShyj.xxshyj }
						</td>
					</tr>
					<tr >
						<td align="center">
							ʡ
							<br>
							��
							<br>
							��
							<br>
							��
							<br>
							��
							<br>
							��
						</td>
						<td valign=top>
							
						</td>
						<td valign=top align="center">
							��
							<br>
							ҵ
							<br>
							��
							<br>
							ҵ
							<br>
							ȥ
							<br>
							��
						</td>
						<td valign=top>
							&nbsp;
						</td>
					</tr>
					<tr >
						<td valign=top align="center">
							��<br><br>ע
						</td>
						<td colspan=3 valign=top>
							&nbsp;
						</td>
					</tr>
				</table>
				<div align="right">
					�㽭ʡ�������Ʊ� &nbsp;
				</div>
				<div align="left">
					ע��1���˱�һʽ���ݣ�ѧ�����˵�����ѧУ��һ�ݡ�
					<br>
					&nbsp; &nbsp; &nbsp; 2���������ݿɴ�ӡ���øֱ���д���ּ��������Ժ��ϵ����ѧУ���£��쵼ǩ�ַ���Ч��
				</div>
				<br>
				<br>
				<div class="buttontool noprint" align="center">
					<input  class="button2" value="ҳ������"
						onclick="WebBrowser.ExecWB(8,1);">
					<input  class="button2" value="��ӡԤ��"
						onclick="WebBrowser.ExecWB(7,1)">
					<input  class="button2" value="ֱ�Ӵ�ӡ"
						onclick="WebBrowser.ExecWB(6,6)">
				</div>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>

</html>
