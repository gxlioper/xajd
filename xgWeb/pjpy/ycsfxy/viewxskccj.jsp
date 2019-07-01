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
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<style media='print'>
	.noPrin{
	display:none;}
</style>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script>
			function checkData(){
				var userType = document.getElementById('userType').value;
				var yj = '';	
				if("xy"==userType){	
					yj = document.getElementById('xyshyj').value;
				}else{
					yj = document.getElementById('xxshyj').value;
				}
				if(yj.length > 1000){
					alert('ѧУ���������ܴ���1000�֣�');
					return false;
				}
				refreshForm('/xgxt/pjpy_ycsf_viewxskccj.do');
			}
	</script>

</head>
<body>
	<html:form action="pjpyycsfwh.do" method="post">
		<input type="hidden" name="act" value="save" />
		<logic:present name="lb">
			<input type="hidden" name="lb" value="<bean:write name="lb"/>" />
			<input type="hidden" name="pk" value="<bean:write name="pk"/>" />
			<input type="hidden" name="userType"
				value="<bean:write name="userType"/>" />
		</logic:present>
		<input type="hidden" name="act" value="" />
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã��������� - ��Ϣά�� - �ۺ����ʲ���ά�� - �鿴ѧ���ɼ�
			</div>
		</div>
		<fieldset>
			<table width="100%" class="tbstyle" id="theTable">
				<tr>
					<td align="right" width="20%">
						ѧ�ţ�
					</td>
					<td colspan="3" width="30%">
						<bean:write name="xsxxmap" property="xh" />
					</td>
					<td align="right" width="20%">
						������
					</td>
					<td colspan="3" width="30%">
						<bean:write name="xsxxmap" property="xm" />
					</td>
				</tr>
				<tr>
					<td align="right">
						�Ա�
					</td>
					<td colspan="3">
						<bean:write name="xsxxmap" property="xb" />
					</td>
					<td align="right">
						�꼶��
					</td>
					<td colspan="3">
						<bean:write name="xsxxmap" property="nj" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />���ƣ�
					</td>
					<td colspan="3">
						<bean:write name="xsxxmap" property="xymc" />
					</td>
					<td align="right">
						רҵ���ƣ�
					</td>
					<td colspan="3">
						<bean:write name="xsxxmap" property="zymc" />
					</td>
				</tr>
				<logic:notEqual value="yes" name="ahzyjsxy">
					<tr>
						<td align="right">
							�༶���ƣ�
						</td>
						<td colspan="3">
							<bean:write name="xsxxmap" property="bjmc" />
						</td>
						<td align="right">
							ƽʱ���˳ɼ���
						</td>
						<td colspan="3">
							<bean:write name="xsxxmap" property="pjkhcj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							�׶ο��˳ɼ���
						</td>
						<td colspan="3">
							<bean:write name="xsxxmap" property="jdkhcj" />
						</td>
						<td align="right">
							ѧҵ���˳ɼ���
						</td>
						<td colspan="3">
							<bean:write name="xsxxmap" property="xykhcj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							�ۺϲ����ɼ���
						</td>
						<td colspan="3">
							<bean:write name="xsxxmap" property="zhszcpzf" />
						</td>
						<td align="right">
							�ۺϲ���������
						</td>
						<td colspan="3">
							<bean:write name="xsxxmap" property="pm" />
						</td>
					</tr>
				</logic:notEqual>
				<logic:equal value="yes" name="ahzyjsxy">
					<tr>
						<td align="right">
							�༶���ƣ�
						</td>
						<td colspan="3">
							<bean:write name="xsxxmap" property="bjmc" />
						</td>
						<td align="right">
							�ܿ�ƽ���֣�
						</td>
						<td>
							<bean:write name="xsxxmap" property="pjf" />
						</td>
						<td align="right">
							������
						</td>
						<td>
							<bean:write name="xsxxmap" property="pm" />
						</td>
					</tr>
				</logic:equal>
				<logic:present name="shMap">
					<tr>
						<td align="right">
							<font color="red">���</font>
						</td>
						<td colspan="3">
							<logic:equal value="jxj" name="lb">
								<html:select name="shMap" property="dm" styleId="dm">
									<html:options collection="jxjList" property="jxjdm"
										labelProperty="jxjmc" />
								</html:select>
							</logic:equal>
							<logic:equal value="rych" name="lb">
								<html:select name="shMap" property="dm" styleId="dm">
									<html:options collection="rychList" property="rychdm"
										labelProperty="rychmc" />
								</html:select>
							</logic:equal>
						</td>
						<logic:notPresent name="view">
							<logic:equal value="yes" name="ahzyjsxy">
								<logic:equal value="xy" name="userType">
									<td align="right">
										<font color="red"><bean:message key="lable.xsgzyxpzxy" />���״̬��</font>
									</td>
									<td colspan="3">
										<html:select name="shMap" property="xysh" styleId="xysh">
											<html:option value="δ���">δ���</html:option>
											<html:option value="ͨ��">ͨ��</html:option>
											<html:option value="��ͨ��">��ͨ��</html:option>
										</html:select>
									</td>
								</logic:equal>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								<td align="right">
									<font color="red">ѧУ���״̬��</font>
								</td>
								<td colspan="3">
									<html:select name="shMap" property="xxsh" styleId="xxsh">
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
									</html:select>
								</td>
							</logic:notEqual>
						</logic:notPresent>
						<logic:present name="view">
							<td align="right">
								<font color="red">ѧУ���״̬��</font>
							</td>
							<td colspan="3">
								<html:select name="shMap" property="xxsh" styleId="xxsh">
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
							</td>
						</logic:present>
					</tr>
					<logic:equal value="yes" name="ahzyjsxy">
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��������
							</td>
							<td colspan="7">
								<html:textarea name="shMap" property="xyshyj" cols="60" rows="6"></html:textarea>
							</td>
						</tr>
					</logic:equal>
					<logic:present name="view">
						<logic:equal value="xy" name="userType">
							<tr>
								<td align="right">
									ѧУ��������
								</td>
								<td colspan="7">
									<html:textarea name="shMap" property="xxshyj" cols="60"
										rows="6"></html:textarea>
								</td>
							</tr>
						</logic:equal>
					</logic:present>
					<logic:notEqual value="xy" name="userType">
						<tr>
							<td align="right">
								ѧУ��������
							</td>
							<td colspan="7">
								<html:textarea name="shMap" property="xxshyj" cols="60" rows="6"></html:textarea>
							</td>
						</tr>
					</logic:notEqual>
				</logic:present>
				<tr align="center">
					<td colspan="8">
						����γ̳ɼ�����
					</td>
				</tr>
				<logic:notEqual value="yes" name="ahzyjsxy">
					<tr align="center">
						<td>
							ѧ��
						</td>
						<td>
							ѧ��
						</td>
						<td>
							�γ�����
						</td>
						<td>
							�γ�����
						</td>
						<td>
							��������
						</td>
						<td>
							�ɼ�
						</td>
						<td>
							ѧ��
						</td>
						<td>
							�����ɼ�
						</td>
					</tr>
					<logic:present name="rs">
						<logic:iterate id="s" name="rs">
							<tr align="center">
								<logic:iterate id="v" name="s">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:present>
				</logic:notEqual>
				<logic:equal value="yes" name="ahzyjsxy">
					<tr align="center">
						<td>
							ѧ��
						</td>
						<td>
							ѧ��
						</td>
						<td colspan="3">
							�γ�����
						</td>
						<td>
							�γ�����
						</td>
						<td>
							��������
						</td>
						<td>
							�ɼ�
						</td>
					</tr>
					<logic:present name="rs">
						<logic:iterate id="s" name="rs">
							<tr align="center">
								<logic:iterate id="v" name="s" offset="0" length="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="2" length="1">
									<td colspan="3">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="3" length="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:present>
				</logic:equal>
				<logic:present name="shMap">
					<logic:notPresent name="view">
						<logic:notEqual value="stu" name="userType">
							<tr>
								<td align="center" colspan="8">
									<button class="button2" onclick="checkData();"
										style="width: 60px">
										�� ��
									</button>
									&nbsp;&nbsp;
									<button class="button2" onclick="reset();" style="width: 60px">
										�� ��
									</button>
								</td>
							</tr>
						</logic:notEqual>
					</logic:notPresent>
				</logic:present>

			</table>
		</fieldset>
		<br>
		<logic:present name="psjd">
			<div align="center" class="noPrin">
				<input type='button' class='button2' value='ҳ������'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��'
					onclick="WebBrowser.ExecWB(7,1);">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
		</logic:present>
		<logic:equal value="yes" name="result">
			<script>
				alert('�����ɹ�!');
				close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert('����ʧ��!');
			</script>
		</logic:equal>
	</html:form>
</body>
</html:html>
