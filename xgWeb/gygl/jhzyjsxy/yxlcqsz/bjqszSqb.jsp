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
<html:html>
<base target="_self" />
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
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
</head>

<body>

	<div class=Section1 style='layout-grid:15.6pt' align="center">
		<b> ��ְҵ����<bean:message key="lable.xsgzyxpzxy" />���ټ����ҳ�����ѡ�����</b>
		<table border=1 cellspacing=0 cellpadding=0 width=584 align=center>
			<tr>
				<td width=55 class="Normal"align=center>
					
						����
					
				</td>
				<td width=136 class="Normal" align="center">
					<bean:write name="rs" property="xm" />
				</td>
				<td width=104 class="Normal" align=center>
					
						����<bean:message key="lable.xsgzyxpzxy" />
					
				</td>
				<td width=289 colspan=3 class="Normal" align="center">
					<bean:write name="rs" property="xymc" />
				</td>
			</tr>
			<tr>
				<td width=55 class="Normal" align=center>
					¥��
					
				</td>
				<td width=136 class="Normal" align="center">
					<bean:write name="ldmc"/>
				</td>
				<td width=104 class="Normal" align=center>
					����
					
				</td>
				<td width=72 class="Normal">
					<bean:write name="qsh"/>
				</td>
				<td width=85 class="Normal" align=center>
					��ϵ�绰
					
				</td>
				<td width=132 class="Normal" align="center">
					<bean:write name="lxdh" />
				</td>
			</tr>
			<tr>
				<td width=55 class="Normal" align="center">
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
				<td width=529 colspan=5 class="Normal" valign="bottom">
					<p align="left">
						<bean:write name="qsjsqk" />
					</p>

					<p align="right">
						�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp; ��&nbsp;&nbsp; ��
					</p>
				</td>
			</tr>
			<tr>
				<td width=55 class="Normal" align="center">
					<br>
					¥
					<br>
					��
					<br>
					��
					<br>
					��
					<br>
					��
				</td>
				<td width=529 colspan=5  class="Normal" valign="bottom">
					<p align="right">	
						ǩ ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
						��&nbsp;&nbsp; ��
					</p>
				</td>
			</tr>
			<tr>
				<td width=55 class="Normal" align="center">
					<br>
					��
					<br>
					��
					<br>
					Ա
					<br>
					��
					<br>
					��
				</td>
				<td width=529 colspan=5  class="Normal" valign="bottom" >
					<p align="right">
						
						ǩ ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp; ��&nbsp;&nbsp; ��
					</p>
				</td>
			</tr>
			<tr>
				<td width=55 class="Normal" align="center">
					<br>
					ѧ
					<br>
					��
					<br>
					��
					<br>
					��
					<br>
					��
				</td>
				<td width=529 colspan=5  class="Normal" valign="bottom" >
					<p align="right">
					
						�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp; ��&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width=55 class="Normal" align="center">
					<br>
					����
					<br>
					����
					<br>
					����
					<br>
					���
				</td>
				<td width=529 colspan=5  class="Normal" valign="bottom" >
					<p align="right">
						�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp; ��&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>		
	</div>
					<br>
				<div class="buttontool noprint" align="center">
					<input type="button" class="button2" value="ҳ������"
						onclick="WebBrowser.ExecWB(8,1);">
					<input type="button" class="button2" value="��ӡԤ��"
						onclick="WebBrowser.ExecWB(7,1)">
					<input type="button" class="button2" value="ֱ�Ӵ�ӡ"
						onclick="WebBrowser.ExecWB(6,6)">
				</div>
	
</body>

</html:html>
