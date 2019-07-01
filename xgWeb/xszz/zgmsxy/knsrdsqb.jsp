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
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=knsrdsq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h2>
						<strong>
								�ߵ�ѧУ��ͥ��������ѧ���϶������
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="left">
						<br /><br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ѧ��λ:
						<u> &nbsp;�й�����<bean:message key="lable.xsgzyxpzxy" />&nbsp; </u>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="4%" rowspan="4">
								<div align="center">
									<strong>ѧ<br />��<br />��<br />��<br />��<br />��<br />��<br />��</strong>
								</div>
							</td>
							<td width="10%">
								<div align="center">
									����
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									����
									<br />
									����
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name="rs" property="csny" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									����
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name="rs" property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���֤
									<br />
									����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									������ò
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									��ͥ�˾�
									<br />
									������
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtrjnsr">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtrjnsr">
									<bean:write name="rs" property="jtrjnsr" />&nbsp;
									</logic:notEmpty>
									Ԫ
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									ϵ��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="zymc" />
								</div>
							</td>
							<td>
								<div align="center">
									�꼶
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="nj" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ѧ��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="xh" />
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="qs" />
								</div>
							</td>
							<td>
								<div align="center">
									��ϵ
									<br />
									�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="xslxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<strong>ѧ<br /> ��<br /> ��<br /> ��<br /> ��<br /> ��<br />
										��<br /> ��<br /> ��<br /> ��</strong>
								</div>
							</td>
							<td colspan="8">
								<br />
								<br />
								<logic:empty name="rs" property="sqly">
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly">
								<bean:write name="rs" property="sqly" />
								</logic:notEmpty>
								<br />
								<div align="right">
									ѧ��ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_______��____��____��&nbsp;&nbsp;
								</div>
								<div align="left">
									&nbsp;&nbsp;(ע:������ϸ���˵����)
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<div align="center">
									<strong>��<br /> ��<br /> ��<br /> ��</strong>
								</div>
							</td>
							<td rowspan="3">
								<div align="center">
									��
									<br />
									��
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td>
								A.��ͥ��������&nbsp;
								<logic:equal name="rs" property="mzpyjg" value="����">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="mzpyjg" value="����">
								��
								</logic:notEqual>
							</td>
							<td rowspan="3">
								<div align="center">
									��
									<br />
									��
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="5" rowspan="3">
								<br />
								<logic:empty name="rs" property="csly">
								<br />
								<br />
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="csly">
								<bean:write name="rs" property="csly" />
								</logic:notEmpty>
								<br />
								<div align="left">
									����С���鳤ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_______��____��____��
								</div>
							</td>
						</tr>
						<tr>
							<td>
								B.��ͥ������������&nbsp;
								<logic:equal name="rs" property="mzpyjg" value="��������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="mzpyjg" value="��������">
								��
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td>
								C.��ͥ���ò�����&nbsp;
								<logic:equal name="rs" property="mzpyjg" value="������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="mzpyjg" value="������">
								��
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<strong>��<br /> ��<br /> ��<br /> ��</strong>
								</div>
							</td>
							<td>
								<div align="center">
									Ժ(ϵ)
									<br />
									���
								</div>
							</td>
							<td colspan="2">
								������С���Ƽ�����Ժ(ϵ)������˺�
								<br />
								<logic:equal name="rs" property="xysh" value="δ���">
								��&nbsp;ͬ������С�������
								<br />
								��&nbsp;��ͬ������С�������
								<br />
								����Ϊ_____________��
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="δ���">
									<logic:equal name="rs" property="tj_xy" value="1">
										��&nbsp;ͬ������С�������
										<br />
										��&nbsp;��ͬ������С�������
										<br />
										����Ϊ_____________��
									</logic:equal>
									<logic:equal name="rs" property="tj_xy" value="0">
										��&nbsp;ͬ������С�������
										<br />
										��&nbsp;��ͬ������С�������
										<br />
										����Ϊ<u>&nbsp;<bean:write name="rs" property="xysh" />&nbsp;</u>��
									</logic:equal>
								</logic:notEqual>
								<br />
								<br />
								�϶�С���鳤ǩ�֣�
								<br />
								<br />
								<div align="right">
									______��____��____��
								</div>
								<br />
							</td>
							<td>
								<div align="center">
									ѧУѧ��
									<br />
									��������
									<br />
									�������
								</div>
							</td>
							<td colspan="4">
								��ѧ������Ժ(ϵ)���룬�����������ʵ��
								<br />
								<logic:equal name="rs" property="xxsh" value="δ���">
								��&nbsp;ͬ���϶�С�������С�������
								<br />
								��&nbsp;��ͬ���϶�С�������С�������
								<br />
								����Ϊ��_____________________��
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="δ���">	
									<logic:equal name="rs" property="xy_xx" value="1">
										��&nbsp;ͬ���϶�С�������С�������
										<br />
										��&nbsp;��ͬ���϶�С�������С�������
										<br />
										����Ϊ��_____________________��
									</logic:equal>
									<logic:equal name="rs" property="xy_xx" value="0">
										��&nbsp;ͬ���϶�С�������С�������
										<br />
										��&nbsp;��ͬ���϶�С�������С�������
										<br />
										����Ϊ��<u>&nbsp;<bean:write name="rs" property="xxsh" />&nbsp;</u>��
									</logic:equal>
								</logic:notEqual>
								<br />
								<br />
								������ǩ�֣�
								<br />
								<br />
								<div align="right">
									______��____��____��&nbsp;&nbsp;
								</div>
								<br />
								<div align="center">
									(�Ӹǲ��Ź���)
								</div>
								<br />
							</td>
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
</html:html>
