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
			document.forms[0].action = "/xgxt/n05_xszz.do?method=knsrd2sq";
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
							<strong> �ߵ�ѧУ��ͥ��������ѧ���϶������ </strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<br /><br />
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h4><B>ѧУ</B>:<u>&nbsp;&nbsp;<bean:write name='rs' property="xxmc" />&nbsp;&nbsp;</u></h4>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle" >
						<tr>
							<td rowspan="4" width="8%">
								<div align="center">
								<B>
									ѧ
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									</B>
								</div>
							</td>
							<td width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td width="22%" colspan="2">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="8%" colspan="2">
								<div align="center" >
									�Ա�
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="10%" colspan=2>
								<div align="center">
									��������
								</div>
							</td>
							<td width="10%" colspan=2>
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									����
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���֤����
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									����
									<br />
									��ò
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ͥ�˾�
									<br />
									������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtrjnsr" />
									<logic:empty name="rs" property="jtrjnsr">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
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
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="xxmc" />
								</div>
							</td>
							<td>
								<div align="center">
									ϵ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									רҵ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�꼶
								</div>
							</td>
							<td >
								<div align="center">
									<bean:write name='rs' property="nj" />
								</div>
							</td>
							<td>
								<div align="center">
									��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��У��ϵ�绰
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="zxlxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<B>
									ѧ
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									</B>
								</div>
							</td>
							<td colspan="12">
								<br />
								<logic:empty name="rs" property="sqly">
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly">
									<bean:write name='rs' property="sqly" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									ѧ��ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��&nbsp;&nbsp;
								</div>
								<div align="left">
									ע������������д��1.��ͥ�˿����������ڲ��������������2.ƶ��ԭ��
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="4">
								<div align="center">
									<B>
									��
									<br />
									��
									<br />
									��
									<br />
									��
									</B>
								</div>
							</td>
							<td rowspan="4">
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
							<td colspan=3>
								<div align="left">
									<logic:equal name="rs" property="fdysh" value="һ������">
									A.��ͥ����һ������&nbsp;&nbsp;��
									</logic:equal>
									<logic:notEqual name="rs" property="fdysh" value="һ������">
									A.��ͥ����һ������&nbsp;&nbsp;��
									</logic:notEqual>
								</div>
							</td>
							<td rowspan="4">
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
							<td colspan="7" rowspan="4">
								<br />
								<logic:empty name="rs" property="fdyshyj">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="fdyshyj">
									<bean:write name='rs' property="fdyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									����С���鳤ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��&nbsp;&nbsp;
								</div>
								<br><br>
							</td>
						</tr>
						<tr>
							<td colspan=3>
								<div align="left">
									<logic:equal name="rs" property="fdysh" value="����">
									B.��ͥ��������&nbsp;&nbsp;��
									</logic:equal>
									<logic:notEqual name="rs" property="fdysh" value="����">
									B.��ͥ��������&nbsp;&nbsp;��
									</logic:notEqual>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan=3>
								<div align="left">
									<logic:equal name="rs" property="fdysh" value="��������">
									C.��ͥ������������&nbsp;&nbsp;��
									</logic:equal>
									<logic:notEqual name="rs" property="fdysh" value="��������">
									C.��ͥ������������&nbsp;&nbsp;��
									</logic:notEqual>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan=3>
								<div align="left">
									<logic:equal name="rs" property="mzrd" value="������">
									D.��ͥ���ò�����&nbsp;&nbsp;��
									</logic:equal>
									<logic:notEqual name="rs" property="mzrd" value="������">
									D.��ͥ���ò�����&nbsp;&nbsp;��
									</logic:notEqual>
								</div>
							</td>
						</tr>
						<tr>
							<td >
								<div align="center">
								<B>
									��
									<br />
									��
									<br />
									��
									<br />
									��
									</B>
								</div>
							</td>
							<td>
								<div align="center">
									Ժ
									<br />
									(ϵ)
									<br />
									��
									<br />
									��
								</div>
							</td>
							<logic:empty name="rs" property="xh">
								<td colspan="4">
									<div align="left">
										&nbsp;������С���Ƽ�����Ժ(ϵ)������˺�
										<br />
										&nbsp;��&nbsp;&nbsp;ͬ������С�������
										<br />
										&nbsp;��&nbsp;&nbsp;��ͬ������С�����������Ϊ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									</div>
									<br />
									<br />
									<div align="left">
										&nbsp;�������鳤ǩ�֣�
									</div>
									<br />
									<div align="right">
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										ѧ
										<br />
										У
										<br />
										ѧ
										<br />
										��
										<br />
										��
										<br />
										��
										<br />
										��
										<br />
										��
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
								<td colspan="6">
								<BR/><BR/>
									<div align="left">
										&nbsp;��ѧ������Ժ(ϵ)���룬�����������ʵ��
										<br />
										&nbsp;��&nbsp;&nbsp;ͬ�⹤���������С�������
										<br />
										&nbsp;��&nbsp;&nbsp;��ͬ�⹤���������С�����������Ϊ:<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u><br>
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									</div>
									<br />
									<br />
									<div align="left">
										&nbsp;������ǩ�֣�
									</div>
									<br />
									<div align="right">
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��&nbsp;
									</div>
									<div align="left">
										&nbsp;<p align="center">(�Ӹǲ��Ź���)</p>
									</div>
								</td>
							</logic:empty>
							<logic:notEmpty name="rs" property="xh">
								<td colspan="4" >
									<div align="left" >
										&nbsp;������С���Ƽ���Ժ(ϵ)������˺�
										<br />
										<logic:equal name="rs" property="xysh" value="δ���">
									&nbsp;��&nbsp;&nbsp;ͬ������С�������
									<br />
									&nbsp;��&nbsp;&nbsp;��ͬ������С�����������Ϊ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									</logic:equal>
										<logic:notEqual name="rs" property="xysh" value="δ���">
											<logic:equal name="xt1" value="is">
											&nbsp;��&nbsp;&nbsp;ͬ������С�������
											<br />
											&nbsp;��&nbsp;&nbsp;��ͬ������С�����������Ϊ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
											<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
										</logic:equal>
											<logic:equal name="xt1" value="no">
											&nbsp;��&nbsp;&nbsp;ͬ������С�������
											<br />
											&nbsp;��&nbsp;&nbsp;��ͬ������С�����������Ϊ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
											<u>&nbsp;<bean:write name='rs' property="xysh" />&nbsp;</u>��
										</logic:equal>
										</logic:notEqual>
									</div>
									<br />
									<br />
									<div align="left">
										&nbsp;�������鳤ǩ�֣�
									</div>
									<br />
									<div align="right">
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										ѧ
										<br />
										У
										<br />
										ѧ
										<br />
										��
										<br />
										��
										<br />
										��
										<br />
										��
										<br />
										��
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
								<td colspan="6">
								<BR/><BR/>
									<div align="left">
										&nbsp;��ѧ������Ժ(ϵ)���룬�������������ʵ��
										<br />
										<logic:equal name="rs" property="xxsh" value="δ���">
									&nbsp;��&nbsp;&nbsp;ͬ�⹤���������С�������
									<br />
									&nbsp;��&nbsp;&nbsp;��ͬ�⹤���������С�����������Ϊ:<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u><br>
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									</logic:equal>
										<logic:notEqual name="rs" property="xxsh" value="δ���">
											<logic:equal name="xt2" value="is">
											&nbsp;��&nbsp;&nbsp;ͬ�⹤���������С�������
											<br />
											&nbsp;��&nbsp;&nbsp;��ͬ�⹤���������С�����������Ϊ:<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u><br>
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
										</logic:equal>
											<logic:equal name="xt2" value="no">
											&nbsp;��&nbsp;&nbsp;ͬ�⹤���������С�������
											<br />
											&nbsp;��&nbsp;&nbsp;��ͬ�⹤���������С�����������Ϊ:<br/>
											<u>&nbsp;<bean:write name='rs' property="xxsh" />&nbsp;</u>��
										</logic:equal>
										</logic:notEqual>
									</div>
									<br />
									<br />
									<div align="left">
										&nbsp;������ǩ�֣�
									</div>
									<br />
									<div align="right">
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��&nbsp;
									</div>
									<div align="left">
										&nbsp;<p align="center">(�Ӹǲ��Ź���)</p>
									</div>
								</td>
							</logic:notEmpty>
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
