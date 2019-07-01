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


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
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
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var userType = document.getElementById('userType').value;
			if(userType == 'xy'){
				var xyshyj = document.getElementById('xyshyj').value;
				if(xyshyj != null && xyshyj.length > 500){
					alert('<bean:message key="lable.xsgzyxpzxy" />���������ܶ���500�֣�');
					return false;
				}
			}else{
				var xxshyj = document.getElementById('xxshyj').value;
				if(xxshyj != null && xxshyj.length > 500){
					alert('ѧУ���������ܶ���500�֣�');
					return false;
				}
			}
			
			document.forms[0].action = "/xgxt/xcxyXszz.do?method=excuteDgsh&doType=sh";
			document.forms[0].submit();
		}
		
		function toPrintOut(titName){//�����Ӧ�Ĵ�ӡҳ��
			if (titName == "gjjxj"){
				document.forms[0].action = "/xgxt/gjjzxjsqb.do";
				document.forms[0].submit();
			} else if (titName == "gjzxj") {
				document.forms[0].action = "/xgxt/gjjzxjsqb.do";
				document.forms[0].submit();
			} else {
				document.forms[0].action = "/xgxt/xpjjdksqb.do";
				document.forms[0].submit();
			}
		}
		
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - �鿴
			<bean:write name="rs" property="xmmc" />
			����
		</div>
	</div>
	<html:form action="xcxyXszz.do?method=excuteDgsh" method="post">
		<input type="hidden" name="userType" id="userType"
			value="<bean:write name="userType"/>">
		<input type="hidden" name="xmdm" id="xmdm"
			value="<bean:write name="rs" property="xmdm"/>">
		<input type="hidden" name="temp" id="temp"
			value="<bean:write name="pk"/>">
		<logic:present name="result">
			<logic:match value="yes" name="result">
				<script language="javascript">
	         	alert("����ɹ���");
	         	close();
	         	window.dialogArguments.document.getElementById('search_go').click();
	         	</script>
			</logic:match>
			<logic:match value="no" name="result">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" style="width:100%">
			<tr>
				<td align="right" width="16%" colspan="2">
					ѧ�ţ�
				</td>
				<td align="left" width="34%" colspan="2">
					<input type="text" name="xh" id="xh" readonly="true"
						value="<bean:write name='rs' property="xh" />" />
				</td>
				<td width="16%">
					<div align="right">
						������
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="xm" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						�������£�
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="csrq" />
				</td>
				<td>
					<div align="right">
						�Ա�
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xb" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						��ѧʱ�䣺
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="rxrq" />
				</td>
				<td>
					<div align="right">
						���壺
					</div>
				</td>
				<td>
					<bean:write name="rs" property="mzmc" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						��ѧ��
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="xxmc" />
				</td>
				<td>
					<div align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xymc" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						רҵ��
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="zymc" />
				</td>
				<td>
					<div align="right">
						�༶��
					</div>
				</td>
				<td>
					<bean:write name="rs" property="bjmc" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						ѧ�ƣ�
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="xz" />
				</td>
				<td>
					<div align="right">
						������ò��
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zzmmmc" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						�����ţ�
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="ssbh" />
				</td>
				<td>
					<div align="right">
						���֤�ţ�
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfzh" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						���᣺
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="jg" />
				</td>
				<td>
					<div align="right">
						ѧ����
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xl" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						���п��ţ�
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="yhkh" />
				</td>
				<td>
					<div align="right">
						һ��ͨ�ţ�
					</div>
				</td>
				<td>
					<bean:write name="rs" property="ykth" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						�����ţ�
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="fkh" />
				</td>
				<td>
					<div align="right">
						��ϵ�绰��
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxdh" />
				</td>
			</tr>
			<logic:notEqual value="���ҽ�ѧ��" name="rs" property="xmlc">
				<tr height="28">
					<td colspan="2">
						<div align="right">
							��ͥ���ڣ�
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="hkxz" />
					</td>
					<td>
						<div align="right">
							��ͥ�˿�������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtzrs" />
					</td>
				</tr>
				<tr height="28">
					<td colspan="2">
						<div align="right">
							��ͥ�����룺
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtysr" />
					</td>
					<td>
						<div align="right">
							�˾������룺
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rjysr" />
					</td>
				</tr>
				<tr height="28">
					<td colspan="2">
						<div align="right">
							��ͥ������Դ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="srly" />
					</td>
					<td>
						<div align="right">
							�������룺
						</div>
					</td>
					<td>
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
			</logic:notEqual>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						��ͥ��ַ��
					</div>
				</td>
				<td colspan="4">
					<bean:write name="rs" property="jtdz" />
				</td>
			</tr>
			<logic:equal value="������ѧ��" name="rs" property="xmlc">
				<tr height="28">
					<td width="4%" rowspan="5">
						<div align="center">
							��
							<br>
							ͥ
							<br>
							��
							<br>
							Ա
							<br>
							��
							<br>
							��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="9%">
						<div align="center">
							����
						</div>
					</td>
					<td width="25%">
						<div align="center">
							�뱾�˹�ϵ
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							������ѧϰ��λ
						</div>
					</td>
				</tr>
				<tr height="28">
					<td width="12%">
						<bean:write name="rs" property="jtcy1_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_nl" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_gx" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy1_dw" />
					</td>
				</tr>
				<tr height="28">
					<td width="12%">
						<bean:write name="rs" property="jtcy2_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_nl" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_gx" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy2_dw" />
					</td>
				</tr>
				<tr height="28">
					<td width="12%">
						<bean:write name="rs" property="jtcy3_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_nl" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_gx" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy3_dw" />
					</td>
				</tr>
				<tr height="28">
					<td width="12%">
						<bean:write name="rs" property="jtcy4_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_nl" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_gx" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy4_dw" />
					</td>
				</tr>
			</logic:equal>
			<tr>
				<td colspan="2">
					<div align="center">
						�������
					</div>
					<p align="center">
						����
						<br>
						<font color="red">(300��������)</font>
				</td>
				<td colspan="4">
					<html:textarea name="rs" property="chjl" rows='6'
						style="width:100%" readonly="true" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div style="vertical-align: middle;text-align: right;">
						��ѧ������������
					</div>
				</td>
				<td colspan="4" style="vertical-align: text-top;">
					<logic:iterate id="map" name="xnxszzList">
						<bean:write name="map" property="xn" />ѧ��  ���<bean:write
							name="map" property="mc" />��
						</logic:iterate>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div style="vertical-align: middle;text-align: right;">
						����ѧ�����ܴ��֣�
					</div>
				</td>
				<td colspan="4" style="vertical-align: text-top;">
					<logic:iterate id="map" name="xnxswjcfList">
						<bean:write name="map" property="xn" />ѧ��  <bean:write name="map"
							property="cfyymc" />
						<bean:write name="map" property="cflbmc" />��
						</logic:iterate>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<br>
					��������
					<br>
					<br>
					<font color="red">(1000��������)</font>
				</td>
				<td colspan="4">
					<html:textarea name="rs" property="sqly" rows='6'
						style="width:100%" readonly="true" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="vertical-align: middle;text-align: right;" >
					ƶ��֤�����أ�
				</td>
				<td align="left" colspan="4">
					<logic:present name="rs" property="wjsclj">
						<a
							href="/xgxt/xcxyXszz.do?method=DownLoadFile&wjsclj=<bean:write name="rs" property="wjsclj"/>" target="_blank"><bean:write
								name="rs" property="wjmc" />
						</a>
					</logic:present>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="right">
						<font color="red">���״̬�� </font>
					</div>
				</td>
				<td colspan="2">
					<logic:equal value="xy" name="userType">
						<html:select name="rs" property="xysh" style="width:100px"
							styleId="xysh">
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<html:select name="rs" property="xxsh" style="width:100px"
							styleId="xxsh">
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</logic:notEqual>
				</td>
				<td>
					<div align="right">
						<font color="red">������</font>
					</div>
				</td>
				<td>
					<html:select name="rs" property="zzje" style="width:120px"
						styleId="zzje">
						<html:options collection="zzjeList" property="zzje"
							labelProperty="zzje" />
					</html:select>
					&nbsp;Ԫ
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<br>
					<bean:message key="lable.xsgzyxpzxy" />������
					<br>
					<br>
					<font color="red">(500��������)</font>
				</td>
				<td colspan="4">
						<logic:notEqual value="xy" name="userType">
							<textarea name="xyshyj" rows='6' id="xyshyj" style="width:100%"
									readOnly=true type="_moz">
								<bean:write name="rs" property="xyshyj" />
						</textarea>
						</logic:notEqual>
						<logic:equal value="xy" name="userType">
							<textarea name="xyshyj" rows='6' id="xyshyj" style="width:100%"
									 type="_moz">
						<bean:write name="rs" property="xyshyj" />
					</textarea>
						</logic:equal>
				</td>
			</tr>
			<logic:notEqual value="xy" name="userType">
				<tr>
					<td align="center" colspan="2">
						<br>
						ѧУ������
						<br>
						<br>
						<font color="red">(500��������)</font>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="xxshyj" rows='6'
							styleId="xxshyj" style="width:100%" />
					</td>
				</tr>
			</logic:notEqual>
		</table>

		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<logic:equal value="xy" name="userType">
				<logic:equal value="δ���" name="rs" property="xxsh">
					<button type="button" class="button2" onClick="yz();" style="width: 60px">
						�� ��
					</button>
				</logic:equal>
			</logic:equal>
			<logic:notEqual value="xy" name="userType">
				<button type="button" class="button2" onClick="yz();" style="width: 60px">
					�� ��
				</button>
			</logic:notEqual>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2"
				onclick="showTopWin('xcxyXszz.do?method=getXscj&xh='+document.getElementById('xh').value,700,600)">
				ѧ���ɼ���
			</button>
		</div>
	</html:form>
</body>
</html:html>
