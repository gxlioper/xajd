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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
	function jyglByqxSh() {
		 	document.forms[0].action = "/xgxt/jyglByqxSh.do?act=shenhe&doType=shenghe";
		 	document.forms[0].submit();
    }
	</script>
	<body>
		<fieldset>
			<legend>
				��ҵȥ�����
			</legend>
			<html:form action="/data_search" method="post">
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr>
							<td align="left" colspan="4">
								ѧ�����:
								<html:text property="xslb" name="rs" style="width:45px"
									readonly="true" />
								&nbsp;&nbsp;��ҵ���:
								<html:text property="bynd" name="rs" style="width:35px"
									readonly="true" />
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
								<html:text property="xymc" name="rs" style="width:130px"
									readonly="true" />
								&nbsp;&nbsp;�ύʱ�䣺
								<html:text name="rs" property="tjsj" style="width:140px"
									readonly="true" />
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right" width="17%">
							ѧ��:
						</td>
						<td align="left" width="33%">
							<html:text name="rs" property="xsxh" style="width=100%"
								readonly="true" />
						</td>
						<td align="right" width="15%">
							������
						</td>
						<td align="left" width="35%">
							<bean:write name="rs" property="name" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<bean:write name="rs" property="xb" />
						<td align="right">
							���֤�ţ�
						</td>
						<td align="left">
							<bean:write name="rs" property="id" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							רҵ���ƣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="zymc" />
						</td>
						<td align="right">
							�༶���ƣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="bjmc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��Դ������
						</td>
						<td align="left">
							<bean:write name="rs" property="sydq" />
						</td>
						<td align="right">						
						</td>
						<td align="left">
						
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ҵȥ��
						</td>
						<td align="left">
							<bean:write name="rs" property="byqx" />
						</td>
						<td align="right">
							��ϵ��ַ��
						</td>
						<td align="left">
							<bean:write name="rs" property="lxdz" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ϵ�绰��
						</td>
						<td align="left">
							<bean:write name="rs" property="lxdh" />
						</td>
						<td align="right">
							�������룺
						</td>
						<td align="left">
							<bean:write name="rs" property="yzbm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�ƶ��绰��
						</td>
						<td align="left">
							<bean:write name="rs" property="yddh" />
						</td>
						<td align="right">
							�������䣺
						</td>
						<td align="left">
							<bean:write name="rs" property="email" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�����ʼĵ�ַ��
						</td>
						<td align="left">
							<bean:write name="rs" property="dayjdz"/>
						</td>
						<td align="right">
							������Ҫ���ţ�
						</td>
						<td align="left">
							<bean:write name="rs" property="dajydh" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�ʵ���ʴ����ڣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="ydjycrq" />
						</td>
						<td align="right">
							����֤�ţ�
						</td>
						<td align="left">
							<bean:write name="rs" property="bdzh" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							Э�����ţ�
						</td>
						<td align="left">
							<bean:write name="rs" property="xysbh" />
						</td>
						<td align="right">	
						</td>
						<td align="left">			
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							ѧУ��ˣ�
						</td>
						<td align="left">
							<html:select name="rs" property="xxsh" style="width:100xp">
								<html:option value="δ���">δ���</html:option>
								<html:option value="δͨ��X">δͨ��X</html:option>
								<html:option value="��ͨ����">��ͨ����</html:option>
							</html:select>
						</td>
						<td align="right">
							���ʱ�䣺
						</td>
						<td align="left">
							<html:text name="rs" property="shsj" style="width=100%"
								readonly="true" />
						</td>
					</tr>
					<tr style="height:55px">
						<td align="right">
							�޸������
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="xgyj" rows="3"
								style="width=100%" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							����ˣ�
						</td>
						<td align="left">
							<html:text name="rs" property="shperson" style="width=100%"
								readonly="true" />
						</td>
						<td align="right">

						</td>
						<td align="center">

						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="jyglByqxSh()">
						�� �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">
						�� �� �� ��
					</button>
				</div>
			</html:form>
		</fieldset>
		<logic:notEmpty name="shenhe">
			<logic:equal name="shenhe" value="ok">
				<script>
                     alert("�����ɹ���");
               </script>
			</logic:equal>
			<logic:equal name="shenhe" value="no">
				<script>
                    alert("����ʧ�ܣ�");
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

