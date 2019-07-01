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
	<title><bean:message key="lable.title" />
	</title>
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
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=knssq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h3>
						<strong>
								�㽭��ѧ������<bean:message key="lable.xsgzyxpzxy" />��ͥ��������ѧ���϶������
						</strong>
							</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="4" width="4%">
								<div align="center">
									<strong>ѧ<br /> ��<br /> ��<br /> ��<br /> ��<br /> ��<br />
										��<br /> ��</strong>
								</div>
							</td>
							<td width="8%">
								<div align="center">
									����
								</div>
							</td>
							<td width="18%">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="6%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name="rs" property="xh" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									����
									<br />
									����
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name="rs" property="csny" />
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
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									����
									<br />
									��ò
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									���ἰ
									<br />
									����绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="ssjssdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��Ժ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									�༶
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="bjmc" />
								</div>
							</td>
							<td>
								<div align="center">
									�ֻ�
									<br />
									����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="sjhm" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ
									<br />
									סַ
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="jtzz" />
							</td>
							<td>
								<div align="center">
									��ͥ
									<br />
									�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtdh" />
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
									<bean:write name="rs" property="jtrjysr" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<strong>ѧ<br />��<br />��<br />��<br />��<br />��<br />��<br />��<br />��<br />��</strong>
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
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly">
									<bean:write name="rs" property="sqly" />
								</logic:notEmpty>
								<br />
								<div align="right">
									ѧ��ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;______��____��____��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="left">
									(ע��������ϸ���˵��)
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<div align="center">
									<strong>��<br />��<br />��<br />��</strong>
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
							<td colspan="2">
								A.��ͥ��������&nbsp;
								<logic:equal name="rs" property="mzpy" value="����">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="mzpy" value="����">
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
							<td colspan="4" rowspan="3">
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
								<div align="right">
									�ർʦǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;______��____��____��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								B.��ͥ�����ر�����&nbsp;
								<logic:equal name="rs" property="mzpy" value="�ر�����">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="mzpy" value="�ر�����">
								��
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								C.��ͥ���ò�����&nbsp;
								<logic:equal name="rs" property="mzpy" value="������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="mzpy" value="������">
								��
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<strong>��<br />��<br />��<br />��</strong>
								</div>
							</td>
							<td>
								<div align="center">
									��
									<br />
									Ժ
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="3">
								���༶�Ƽ�������Ժ������˺�
								<br />
								<logic:equal name="rs" property="xysh" value="δ���">
								��&nbsp;ͬ��༶С�������
								<br />
								��&nbsp;��ͬ��༶С�������
								<br />
								����Ϊ______________��
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="δ���">
									<logic:equal name="rs" property="tj_xy" value="1">
										��&nbsp;ͬ��༶С�������
										<br />
										��&nbsp;��ͬ��༶С�������
										<br />
										����Ϊ_______________��
									</logic:equal>
									<logic:equal name="rs" property="tj_xy" value="0">
										��&nbsp;ͬ��༶С�������
										<br />
										��&nbsp;��ͬ��༶С�������
										<br />
										����Ϊ<u>&nbsp;<bean:write name="rs" property="xysh" />&nbsp;</u>��
									</logic:equal>
								</logic:notEqual>
								<br />
								<br />
								ǩ�֣�
								<br />
								<div align="right">
									______��____��____��
								</div>
							</td>
							<td>
								<div align="center">
									ѧ
									<br />
									Ժ
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="3">
								����ˣ�
								<br />
								<logic:equal name="rs" property="xxsh" value="δ���">
								��&nbsp;ͬ���Ժ�����
								<br />
								��&nbsp;��ͬ���Ժ�����
								<br />
								����Ϊ��_______________��
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="δ���">
									<logic:equal name="rs" property="xy_xx" value="1">
										��&nbsp;ͬ���Ժ�����
										<br />
										��&nbsp;��ͬ���Ժ�����
										<br />
										����Ϊ��_______________��
									</logic:equal>
									<logic:equal name="rs" property="xy_xx" value="0">
										��&nbsp;ͬ���Ժ�����
										<br />
										��&nbsp;��ͬ���Ժ�����
										<br />
										����Ϊ��<u>&nbsp;<bean:write name="rs" property="xxsh" />&nbsp;</u>��
									</logic:equal>
								</logic:notEqual>
								<br />
								<br />
								<br />
								<div align="right">
									______��____��____��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="center">
									(�Ӹǲ��Ź���)
								</div>
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
