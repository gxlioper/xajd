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
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=knssq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="csmz_xszz.do?method=jtjjqkdcb" method="post">
	<input type="hidden" id="nd" name="nd"
			value="<bean:write name="rs" property="nd" />">
	<input type="hidden" id="xh" name="xh"
			value="<bean:write name="rs" property="xh" />">
		<table width="100%" id="theTable" border="0">
			<tr>
				<td>
					<div align="center">
						<h3>
							<strong>��ɳ����ְҵ����<bean:message key="lable.xsgzyxpzxy" />ƶ��ѧ���ǼǱ�</strong>
						</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�걨���
						<logic:equal name="xxsh" value="δ���">
						ƶ���� �� ������ ��
						</logic:equal>
						<logic:equal name="xxsh" value="��ͨ��">
						ƶ���� �� ������ ��
						</logic:equal>
						<logic:equal name="xxsh" value="ƶ����">
						ƶ���� �� ������ ��
						</logic:equal>
						<logic:equal name="xxsh" value="������">
						ƶ���� �� ������ ��
						</logic:equal>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ţ�
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="6">
								<div align="center">
									��������
								</div>
							</td>
						</tr>
						<tr>
							<td width="13%">
								<div align="center">
									����
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="13%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="14%">
								<div align="center">
									���֤��
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td>
								<div align="center">
									�س�
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="tc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									������ò
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ʱ�μӹ��ڹ���ѧ
								</div>
							</td>
							<td colspan="2">
								<bean:write name='rs' property="hscjgqgzx" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									����Ժ��ϵ���༶
								</div>
							</td>
							<td colspan="4">
								<bean:write name='rs' property="xymc" />
								Ժ(ϵ)&nbsp;
								<bean:write name='rs' property="zymc" />
								Ժ(ϵ)&nbsp;
								<bean:write name='rs' property="bjmc" />
								�༶
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="szqs" />
								</div>
							</td>
							<td>
								<div align="center">
									���ҵ绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="qsdh" />
								</div>
							</td>
							<td>
								<div align="center">
									�ͲͿ�����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jckkh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									����ѧ���ɲ����
								</div>
							</td>
							<td colspan="4">
								<bean:write name='rs' property="drxsgbqk" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��У�ڼ�����ʱ������ֽ�ѧ��
								</div>
							</td>
							<td colspan="4">
								<bean:write name='rs' property="zxqjhschghzjxj" />
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="center">
									��ͥ����
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ��ַ
								</div>
							</td>
							<td colspan="5">
								<bean:write name='rs' property="xzjtxxdz" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
							<td>
								<div align="center">
									�绰
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�Ƿ�ƶ����
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<logic:equal name="pkx" value="��">
									�� ��   �� �� �� �� ���Ҽ�ƶ����  �� ʡ��ƶ���� ��
									</logic:equal>
									<logic:equal name="pkx" value="���Ҽ�ƶ����">
									�� ��   �� �� �� �� ���Ҽ�ƶ����  �� ʡ��ƶ���� ��
									</logic:equal>
									<logic:equal name="pkx" value="ʡ��ƶ����">
									�� ��   �� �� �� �� ���Ҽ�ƶ����  �� ʡ��ƶ���� ��
									</logic:equal>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�����ͥ����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="snjtsr" />
								</div>
							</td>
							<td>
								<div align="center">
									��ͥ��Ա��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtrks" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									����ְҵ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="fqzy" />
								</div>
							</td>
							<td>
								<div align="center">
									ĸ��ְҵ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="mqzy" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ������Դ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtjjly" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ͥÿ���ṩ�����(Ԫ)
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtmytgshf" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�����Ƿ���Ƿծ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name="jzsfyqz" value="��">
									�С� / �޿�
									</logic:equal>
									<logic:equal name="jzsfyqz" value="��">
									�п� / �ޡ�
									</logic:equal>
								</div>
							</td>
							<td>
								<div align="center">
									��ĸ�Ƿ���
									<br />
									����м�
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="fmsfycj" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ĸ�Ƿ���
								</div>
							</td>
							<td colspan="5">
								<bean:write name='rs' property="fmsfjz" />
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="center">
									��������
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="left">
									<br />
									<br />
									<br />
									<bean:write name='rs' property="sqly" />
									<br />
									<strong>���˱�֤��������������ʵ��Ч��</strong>
									<br />
								</div>
								<div align="right">
									ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									���ڣ�
									<bean:write name='rs' property="sqsj" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="center">
									��
									��ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����Աǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:empty name="rs" property="fdyshsj">
									&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="fdyshsj">  
									<bean:write name='rs' property="fdyshsj" />
									&nbsp;&nbsp;
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="center">
									����ϵ������
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<br />
								<bean:write name='rs' property="xyshyj" />
								<br />
								<div align="right">
									����֧������ǩ�֣��ǹ��£���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									�� �ڣ�
									<logic:empty name="rs" property="xyshsj">
									&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="xyshsj">  
									<bean:write name='rs' property="xyshsj" />
									&nbsp;&nbsp;
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="center">
									ѧ����������
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<br />
								<bean:write name='rs' property="xxshyj" />
								<br />
								<div align="right">
									ѧ����
									���ǹ��£���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									�� �ڣ�
									<logic:empty name="rs" property="xxshsj">
									&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="xxshsj">  
									<bean:write name='rs' property="xxshsj" />
									&nbsp;&nbsp;
									</logic:notEmpty>
								</div>

							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<p align="left">
						<strong>���˵����1���˱�ϵ���浵��2���˿ձ����˫���ӡ�����ݱ����ױ���д��<br />3��������Ŀ��дӦʵ�����ǡ����渺�𣬲����հס�4���浵�ı��˳����ͻ����ᱣ��һ�¡�</strong>
					</p>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="����"
			onclick="back();" />
	</div>
</body>
</html:html>
