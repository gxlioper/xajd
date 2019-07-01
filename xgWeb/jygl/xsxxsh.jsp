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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<base target="_self">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
		function grjlshgo(){
			var sfsh = document.getElementById("sfsh").value;
		 	document.forms[0].action = "/xgxt/xsxxsh.do?act=shenhe&doType=shenghe&view=view";
		 	document.forms[0].submit();
    }
	</script>
	<body>
		<fieldset>
			<legend>
				ѧ����ϸ��Ϣ
			</legend>
		<html:form action="/xsxxsh.do" method="post">
			<html:hidden name="rs" property="xsxh"/>
			<table width="480" class="tbstyle">
				<tr>
					<td align="right" width="22%" bgcolor="DOEOEE">
						ѧ�����
					</td>
					<td align="left" width="25%">
						<bean:write name="rs" property="xslb" />
					</td>
					<td align="right" width="22%" bgcolor="DOEOEE">
						����<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left" width="25%">
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td align="right" width="25%" bgcolor="DOEOEE">
						ѧ�ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xsxh" />
					</td>
					<td align="right" width="25%" bgcolor="DOEOEE">
						���֤�ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="id" />
					</td>
				</tr>
				<tr>
					<td align="right" width="25%" bgcolor="DOEOEE">
						������
					</td>
					<td align="left">
						<bean:write name="rs" property="name" />
					</td>
					<td align="right" width="25%" bgcolor="DOEOEE">
						�Ա���룺
					</td>
					<td align="left">
						<bean:write name="rs" property="xbdm" />
					</td>
				</tr>
				<logic:equal value="10491" name="xxdm">
				<tr>
					<td align="right" width="25%" bgcolor="DOEOEE">
						���壺
					</td>
					<td align="left">
						<bean:write name="rs" property="mz" />
					</td>
					<td align="right" width="25%" bgcolor="DOEOEE">
						������ò��
					</td>
					<td align="left">
						<bean:write name="rs" property="zzmm" />
					</td>
				</tr>
				</logic:equal>
				<tr>
					<td align="right" width="25%" bgcolor="DOEOEE">
						ѧУ���ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xxmc" />
					</td>
					<td align="right" width="25%" bgcolor="DOEOEE">
						ѧУ���룺
					</td>
					<td align="left" width="25%">
						<bean:write name="rs" property="xxdm" />
					</td>
				</tr>
				<logic:equal value="10491" name="xxdm">
				<tr>
					<td align="right" width="25%" bgcolor="DOEOEE">
						<bean:message key="lable.xsgzyxpzxy" />���ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right" width="25%" bgcolor="DOEOEE">
						<bean:message key="lable.xsgzyxpzxy" />���룺
					</td>
					<td align="left" width="25%">
						<bean:write name="rs" property="xydm" />
					</td>
				</tr>
				</logic:equal>
				<tr>
					<td align="right" width="25%" bgcolor="DOEOEE">
						רҵ���ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc" />
					</td>
					<td align="right" width="25%" bgcolor="DOEOEE">
						רҵ���룺
					</td>
					<td align="left">
						<bean:write name="rs" property="zydm" />
					</td>
				</tr>
				<logic:equal value="10491" name="xxdm">
				<tr>
					<td align="right" width="25%" bgcolor="DOEOEE">
						�༶���룺
					</td>
					<td align="left">
						<bean:write name="rs" property="bjdm" />
					</td>
					<td align="right" width="25%" bgcolor="DOEOEE">
						��ϵ��ʽ��
					</td>
					<td align="left">
						<bean:write name="rs" property="lxfs" />
					</td>
				</tr>
				</logic:equal>
				<tr>
					<td align="right" width="25%" bgcolor="DOEOEE">
						ѧ�����룺
					</td>
					<td align="left">
						<bean:write name="rs" property="xl" />
					</td>
					<td align="right" width="25%" bgcolor="DOEOEE">
						ѧ�ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xzdm" />
					</td>
				</tr>
				<tr>
					<td align="right" width="25%" bgcolor="DOEOEE">
						��Դ�������룺
					</td>
					<td align="left">
						<bean:write name="rs" property="sydq"/>
					</td>
					<td align="right" width="25%" bgcolor="DOEOEE">
						������ʽ���룺
					</td>
					<td align="left">
						<bean:write name="rs" property="pyfs" />
					</td>
				</tr>
				<tr>
					<td align="right" width="25%" bgcolor="DOEOEE">
						��ѧ��ȣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="nd" />
					</td>
					<td align="right" width="25%" bgcolor="DOEOEE">
						�ϱ�ʱ�䣺
					</td>
					<td align="left">
						<bean:write name="rs" property="sbsj" />
					</td>
				</tr>
				<tr>
					<td align="right" width="25%" bgcolor="DOEOEE">
						��ҵ��ȣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="bynd" />
					</td>
					<logic:equal value="10491" name="xxdm">
					<td align="right" width="25%" bgcolor="DOEOEE">
						Э������:
					</td>
					<td align="left">
						<bean:write name="rs" property="xysbh" />
					</td>
					</logic:equal>
					
					<logic:notEqual value="10491" name="xxdm">
					<td align="right" width="25%" bgcolor="DOEOEE">
						��ע:
					</td>
					<td align="left">
						<bean:write name="rs" property="memo" />
					</td>
					</logic:notEqual>
					
				</tr>
				<logic:equal value="10491" name="xxdm">
				<tr>
					<td align="right" width="25%" bgcolor="DOEOEE">
						���䣺
					</td>
					<td align="left">
						<bean:write name="rs" property="lxdzxx" />
					</td>
					<td align="right" width="25%" bgcolor="DOEOEE">
						QQ:
					</td>
					<td align="left">
						<bean:write name="rs" property="qq" />
					</td>
				</tr>
				</logic:equal>
				<logic:equal value="10491" name="xxdm">
				<tr>
					<td align="right" width="25%" bgcolor="DOEOEE">
						��ע:
					</td>
					<td align="left" colspan="4" width="75%">
					<html:textarea name="rs" property="memo" readonly="true" cols="45"></html:textarea>
					</td>
				</tr>
				</logic:equal>
				<tr>
						<td align="right">
							���:
						</td>
						<td>
							<html:select name="rs" property="sfsh">
								<html:option value="δ���">δ���</html:option>
								<html:option value="δͨ��X">δͨ��X</html:option>
								<html:option value="��ͨ����">��ͨ����</html:option>
							</html:select>
						</td>
				</tr>
				<tr>				
						<td align="right">
							�����:
						</td>
						<td>
							<html:text name="rs" property="shperson" readonly="true"
								style="width=100%" />
						</td>
				</tr>
				<tr>
						<td align="right">
							���ʱ��:
						</td>
						<td colspan="2">
							<html:text name="rs" property="shsj" readonly="true"
								style="width=100%" />
						</td>
				</tr>
				<tr>
						<td align="center">
							��ͨ��ԭ��
							<br>
							���޸����
						</td>
						<td colspan="6" rowspan="2">
							<html:textarea name="rs" property="btgyy" rows="4"
								style="width=100%"/>
						</td>
				</tr>
			</table>
			</html:form>
		</fieldset>
		<div class="buttontool" align="center">
				<button class="button2" onclick="grjlshgo()" style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2"
					onclick="Close();window.dialogArguments.document.getElementById('query_go').click();"
					style="width:80px">
					�� ��
				</button>
			</div>
			<logic:notEmpty name="shenhe">
				<logic:equal name="shenhe" value="ok">
					<script>
                       alert("�ύ�ɹ���");
                       Close();
                       window.dialogArguments.document.getElementById('query_go').click();
                    </script>
				</logic:equal>
				<logic:equal name="shenhe" value="no">
					<script>
                      alert("�ύʧ�ܣ�");
                    </script>
				</logic:equal>
			</logic:notEmpty>
	</body>
</html>
