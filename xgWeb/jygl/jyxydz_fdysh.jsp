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
		<title><bean:message key="lable.title" /></title>
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
	<script language="javascript">
    function shgo(){
    var fdysh= document.getElementById("fdysh").value;
    var xxsh= document.getElementById("xxsh").value;
    
    if(fdysh=="δ���"&&xxsh!="δ���"||fdysh=="δͨ��X"&&xxsh!="δ���"||fdysh=="��ͨ����"&&xxsh!="δ���"){
    alert("����Ȩ���и��������");
    return false;
    }
		 	document.forms[0].action = "/xgxt/jyglJyxyFdysh.do?act=shenhe&doType=shenghe";
		 	document.forms[0].submit();
    }
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>

		<fieldset>
			<legend>
				��ҵЭ��
			</legend>
			<html:form action="/data_search" method="post">
				<table width="100%" id="rsT" class="tbstyle">
					<tr style="height: 22px">
					<td colspan="4" align="center" bgcolor="DOEOEE">
						<b>��ҵЭ��</b>
					</td>
				</tr>
				<tr bgcolor="DOEOEE">
					<td align="left" colspan="4">
						ѧ�����:
						<html:text property="xslb" name="rs" readonly="true"
							style="width:45px" />
						&nbsp;&nbsp;��ҵ���:
						<html:text property="bynd" name="rs" readonly="true"
							style="width:35px" />
						&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
						<html:text property="xymc" name="rs" readonly="true"
							style="width:120px" />
						&nbsp;&nbsp;�ύʱ��:
						<html:text name="rs" property="tjsj" readonly="true"
							style="width:140px" />
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right" style="">
						ѧ�ţ�
					</td>
					<td align="left" style="">
						<bean:write name="rs" property="xsxh" />
						<html:hidden name="rs" property="xsxh" />
					<td align="right">
						Э�����ţ�
					</td>
					<td align="left">
							<bean:write name="rs" property="xysbh" />
					<td align="right">
				</tr>
				<tr style="height: 22px">
					<td align="right" style="">
						������
					</td>
					<td align="left">
						<bean:write name="rs" property="name" />
					</td>
					<td align="right" style="">
						�Ա�
					</td>
					<td align="left">
						<bean:write name="rs" property="xb" />
				</tr>
				<tr style="height: 22px">
					<td align="right">
						���֤�ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="id" />
					</td>
					<td align="right">
						QQ�ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="qq" />
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right">
						רҵ���ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc" />>
					</td>
					<td align="right">
						���壺
					</td>
					<td align="left">
						<bean:write name="rs" property="mz" />
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right">
						ѧ����
					</td>
					<td align="left">
						<bean:write name="rs" property="xldm" />
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right">
						������ò��
					</td>
					<td align="left">
						<bean:write name="rs" property="zzmm" />
					</td>
					<td align="right">
						ѧ�ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xzdm" />
					</td>
				</tr>
				<tr style="height: 22px">

					<td align="right">
						��Դ�������룺
					</td>
					<td align="left">
						<bean:write name="rs" property="sydqdm" />
						<bean:write name="rs" property="jgshi" />
					</td>
				</tr>
					<tr style="height: 22px">
						<td colspan="4" align="center">
							<b>ѧ����ҵ��Ϣ</b>
						</td>
					</tr>
				<tr>
					<td width="20%" align="right">
						 ������ʽ��
					</td>
					<td align="left">
						<bean:write name="rs" property="pyfsdm" />
					</td>
					<td width="20%" align="right">
						 ί�൥λ��
					</td>
					<td align="left">
						<bean:write name="rs" property="wpdw" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						�������᣺
					</td>
					<td align="left">
						<bean:write name="rs" property="zsmc" />
					</td>
					<td width="20%" align="right">
						�Զ�����
					</td>
					<td align="left">
						<bean:write name="rs" property="zdcl" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						��ҵȥ��
					</td>
					<td align="left">
						<bean:write name="rs" property="byqxdm" />
					</td>
					<td width="20%" align="right">
						��ҵ״����
					</td>
					<td align="left">
						<bean:write name="rs" property="jyzk" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						 δ��ҵ��־��
					</td>
					<td align="left">
						<bean:write name="rs" property="wjybz" />
					</td>
					<td width="20%" align="right">
						Ϊ��ҵԭ��
					</td>
					<td align="left">
						<bean:write name="rs" property="wjyyy" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						����֤��ע��
					</td>
					<td align="left">
						<bean:write name="rs" property="bdzbz" />
					</td>
					<td width="20%" align="right">
						ʵ�ʵ�λ��
					</td>
					<td align="left">
						<bean:write name="rs" property="sjdw" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> ��ǲ��λID��
					</td>
					<td align="left">
						<bean:write name="rs" property="pqdwid" />
					</td>
					<td width="20%" align="right">
						<font color="red">*</font> ��ǲ��λ��
					</td>
					<td align="left">
						<bean:write name="rs" property="pqdw" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> ��λ���ʣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="dwxz1" />
						&nbsp
						<bean:write name="rs" property="dwxz2" />

					</td>
					<td width="20%" align="right">
						<font color="red">*</font> ����ѡ��
					</td>
					<td align="left">
							<bean:write name="rs" property="dwxzdm2" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> ���ܲ��ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zgbm1" />
						<bean:write name="rs" property="zgbm1" />
					</td>
					<td width="20%" align="right">
						<font color="red">*</font> ����ѡ��
					</td>
					<td align="left">
						<bean:write name="rs" property="zgbmmc" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> ��λ���ڵأ�
					</td>
					<td align="left">
						<bean:write name="rs" property="dwszd1" />
						<bean:write name="rs" property="jgshi2" />
					</td>
					<td width="20%" align="right">
						<font color="red">*</font> ���ڵأ�
					</td>
					<td align="left">
						<bean:write name="rs" property="dwszd2" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> ʵ�����ڵأ�
					</td>
					<td align="left">
						<bean:write name="rs" property="dwszd3" />
						<bean:write name="rs" property="jgshi3" />
					</td>
					<td width="20%" align="right">
						<font color="red">*</font> ���ڵأ�
					</td>
					<td align="left">
						<bean:write name="rs" property="dwdq" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> ������ַ��
					</td>
					<td align="left">
						<bean:write name="rs" property="dajsd" />
					</td>
					<td width="20%" align="right">
						<font color="red">*</font> �����ʱࣺ
					</td>
					<td align="left">
						<bean:write name="rs" property="dajsdyb" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> ������ַ��
					</td>
					<td align="left" colspan="10">
						<bean:write name="rs" property="hkdz" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> �������ڣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="gprq" />
					</td>
					<td width="20%" align="right">
						<font color="red">*</font> ���ɴ�����
					</td>
					<td align="left">
						<bean:write name="rs" property="gpcs" />
					</td>
				</tr>
				<tr>
				 	<td width="20%" align="right">
						<font color="red">*</font> ����ԭ��
					</td>
					<td align="left">
						<bean:write name="rs" property="gpyy" />
					</td>
					<td width="20%" align="right">
						<font color="red">*</font> ԭ��λ���ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="ydwmc" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> ����֤��ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="bdzbh" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> ��ע��
					</td>
					<td align="left">
						<bean:write name="rs" property="memo" />
					</td>
				</tr>

					<tr style="height:22px">
						<td align="right">
							����Ա��ˣ�
						</td>
						<td align="left" >
							<html:select name="rs" property="fdysh">
								<html:option value="δ���">δ���</html:option>
								<html:option value="δͨ��X">δͨ��X</html:option>
								<html:option value="��ͨ����">��ͨ����</html:option>
							</html:select>
						</td>
						<td align="right">
							ѧУ��ˣ�
						</td>
						<td align="left">
							<html:text name="rs" property="xxsh" style="width=100%" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							����Ա����ˣ�
						</td>
						<td align="left">
							<html:text name="rs" property="fdyshren" style="width=100%" readonly="true"/>
						</td>
						<td align="right">
							ѧУ����ˣ�
						</td>
						<td align="left">
							<html:text name="rs" property="xxshren" style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							����Ա���ʱ�䣺
						</td>
						<td align="left">
							<html:text name="rs" property="fdyshsj" style="width=100%" readonly="true"/>
						</td>
						<td align="right">
							ѧУ���ʱ�䣺
						</td>
						<td align="left">
							<html:text name="rs" property="xxshsj" style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��ͨ��ԭ��<br>���޸����
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="btgyy" rows="4"
								style="width:99%" />
						</td>
					</tr>
				</table>
				<table width="100%">
					<tr>
						<td>
							<div class="buttontool" align="center">
								<button class="button2" onclick="shgo()" style="width:80px">
									�� ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="button2"
									onclick="Close();window.dialogArguments.document.getElementById('query_go').click();" type="reset"
									style="width:80px">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</table>
			</html:form>
		</fieldset>
		<logic:notEmpty name="shenhe">
			<logic:equal name="shenhe" value="ok">
				<script>
                     alert("�����ɹ���");
                     close();
                     window.dialogArguments.document.getElementById('query_go').click();
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
