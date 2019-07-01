<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<base target="_self">
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="������� zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/jhzyjsxy_xszz.do?method=bkzxjjsq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="/jhzyjsxy_xszz" method="post">
	<input type="hidden" id="msg" name="msg"
				value="${msg}">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong> ��ְҵ����<bean:message key="lable.xsgzyxpzxy" />������ѧ������������� </strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr align="center">
				<td>
					��<bean:write name="rs" property="nd"/>�꣩
					<br />
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="10%"><div align="center">����</div></td>
						    <td width="15%"><div align="center"><bean:write name="rs" property="xm"/></div></td>
						    <td width="10%"><div align="center">�Ա�</div></td>
						    <td width="15%"><div align="center"><bean:write name="rs" property="xb"/></div></td>
						    <td width="10%"><div align="center">���֤��</div></td>
						    <td width="15%"><div align="center"><bean:write name="rs" property="sfzh"/></div></td>
						    <td width="10%"><div align="center">��ѧ����</div></td>
						    <td width="15%"><div align="center"><bean:write name="rs" property="rxrq"/></div></td>
						</tr>
						<tr>
							<td><div align="center"><bean:message key="lable.xsgzyxpzxy" /></div></td>
						    <td><div align="center"><bean:write name="rs" property="xymc"/></div></td>
						    <td><div align="center">�༶</div></td>
						    <td><div align="center"><bean:write name="rs" property="bjmc"/></div></td>
						    <td><div align="center">ѧ��</div></td>
						    <td><div align="center"><bean:write name="rs" property="xh"/></div></td>
						    <td><div align="center">ѧ��</div></td>
						    <td><div align="center"><bean:write name="rs" property="xz"/></div></td>
						</tr>
  <tr>
    <td colspan="2"><div align="center">��ͥ��ַ���ʱ�</div></td>
    <td colspan="4"><bean:write name="rs" property="jtzzjyb"/></td>
    <td><div align="center">����</div></td>
    <td><div align="center"><bean:write name="rs" property="jg"/></div></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">��ͥ�꾭�ô�����</div></td>
    <td colspan="4"><bean:write name="rs" property="jtnjjcsr"/></td>
    <td><div align="center">��ͥ�˿���</div></td>
    <td><div align="center"><bean:write name="rs" property="jtrks"/></div></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">ѧϰ�������</div></td>
    <td colspan="4"><bean:write name="rs" property="xxztqk"/></td>
    <td><div align="center">����</div></td>
    <td><div align="center"><bean:write name="rs" property="dy"/></div></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">������ֽ���</div></td>
    <td colspan="4"><bean:write name="rs" property="cshzjl"/></td>
    <td><div align="center">ѧ�ѽ������</div></td>
    <td><div align="center"><bean:write name="rs" property="xfjnqk"/></div></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">���ܺ���Υ�ʹ���</div></td>
    <td colspan="6"><bean:write name="rs" property="cshzwjcf"/></td>
  </tr>
  <tr>
    <td><div align="center">��ѧ����<br />�����</div></td>
    <td><bean:write name="rs" property="zxdkjje"/></td>
    <td><div align="center">����ҡ�У��<br />ѧ����</div></td>
    <td><bean:write name="rs" property="gjxjxjje"/></td>
    <td><div align="center">�������־<br />����ѧ��<br />���</div></td>
    <td><bean:write name="rs" property="gjlzzxjjje"/></td>
    <td><div align="center">��ѧ�����<br />�Ѳ��������</div></td>
    <td><bean:write name="rs" property="bxnhknbzjje"/></td>
  </tr>
  <tr>
    <td><div align="center">��<br /><br />��<br /><br />��<br /><br />��</div></td>
    <td colspan="7"><bean:write name="rs" property="sqly"/></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">��ѧ���������ȼ������</div></td>
    <td colspan="6"><bean:write name="rs" property="zxjjxx"/></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;<bean:message key="lable.xsgzyxpzxy" />���<br /><br /><br /><bean:write name="rs" property="xyshyj"/><br /><br /><br />
	<div align="center">��&nbsp;��</div>
	<br />
	<div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;</div>
	</td>
    <td colspan="2">&nbsp;ѧ�������<br /><br /><br /><bean:write name="rs" property="xxshyj"/><br /><br /><br />
	<div align="center">��&nbsp;��</div>
	<br />
	<div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;</div></td>
    <td colspan="3">&nbsp;У�ֹ��쵼(�������»�)���<br /><br /><br /><br /><br /><br />
	<div align="center">ǩ&nbsp;��</div>
	<br />
	<div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;</div></td>
  </tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="����" onclick="back();" />
	</div>
</body>
	<logic:present name="msg">
		<script>
			alert(''+document.getElementById('msg').value);
		</script>
	</logic:present>
</html:html>
