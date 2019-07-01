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
    function chekhid(){
		var fdyval = document.getElementById("fdysh").value;
		var hidval = document.getElementById("btgtr");
		alert(fdyval);
		if(fdyval == "δͨ��X"){
			hidval.style.display = "inline";
		}else{
			hidval.style.display = "none";
		}
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

					<tr style="height:22px">
						<td colspan="4" align="center" bgcolor="DOEOEE">
							<b>��һ����</b>
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
						        <html:text name="rs" property="tjsj" readonly="true" style="width:140px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" width="18%">
							ѧ�ţ�
						</td>
						<td align="left" width="32%">
							<html:text name="rs" property="xsxh" readonly="true" />
						</td>

						<td align="right" width="18%">
							������
						</td>
						<td align="left" width="32%">
							<bean:write name="rs" property="name" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�Ա���룺
						</td>
						<td align="left">
							<bean:write name="rs" property="xbdm" />
						<td align="right">
							���֤�ţ�
						</td>
						<td align="left">
							<bean:write name="rs" property="id" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							ѧУ���룺
						</td>
						<td align="left">
							<bean:write name="rs" property="xxdm" />
						</td>
						<td align="right">
							ѧУ���ƣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="xxmc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							רҵ���룺
						</td>
						<td align="left">
							<bean:write name="rs" property="zydm" />
						</td>
						<td align="right">
							רҵ���ƣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="zymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							ѧ�ƴ��룺
						</td>
						<td align="left">
							<bean:write name="rs" property="xzdm" />
						</td>
						<td align="right">
							ѧ�����룺
						</td>
						<td align="left">
							<bean:write name="rs" property="xldm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������ʽ���룺
						</td>
						<td align="left">
							<bean:write name="rs" property="pyfsdm" />
						</td>
						<td align="right">
							��Դ�������룺
						</td>
						<td align="left">
							<bean:write name="rs" property="sydqdm" />
						</td>
					</tr>

					<tr style="height:22px">
						<td colspan="4" align="center" bgcolor="DOEOEE">
							<b>�ڶ�����</b>
						</td>
					</tr>


					<tr style="height:22px">
						<td align="right">
							��ҵȥ����룺
						</td>
						<td align="left">
							<bean:write name="rs" property="byqxdm" />
						</td>
						<td align="right">
							��Դ�������ƣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="sydq" />
						</td>

					</tr>
					<tr style="height:22px">
						<td align="right">
							��Ϣ�ǼǺţ�
						</td>
						<td align="left">
							<bean:write name="rs" property="xxdjh" />
						</td>
						<td align="right">
							��֯�������룺
						</td>
						<td align="left">
							<bean:write name="rs" property="zzjgdm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��λ���ƣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="dwmc" />
						</td>
						<td align="right">
							������ò���룺
						</td>
						<td align="left">
							<bean:write name="rs" property="zzmmdm" />
						</td>

					</tr>
					<tr style="height:22px">
						<td align="right">
							��λ�������ƣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="dwdq" />
						</td>
						<td align="right">
							���ܲ������ƣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="zgbm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��λ�������룺
						</td>
						<td align="left">
							<bean:write name="rs" property="dwdqdm" />
						</td>
						<td align="right">
							���ܲ��Ŵ��룺
						</td>
						<td align="left">
							<bean:write name="rs" property="zgbmdm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							δ��ҵ��־��
						</td>
						<td align="left">
							<bean:write name="rs" property="wjybz" />
						</td>
						<td align="right">
							�Զ���1��
						</td>
						<td align="left">
							<bean:write name="rs" property="bz1" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							δ��ҵԭ��
						</td>
						<td align="left">
							<bean:write name="rs" property="wjyyy" />
						</td>
						<td align="right">
							�Զ���2��
						</td>
						<td align="left">
							<bean:write name="rs" property="bz2" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ϵ��ַ��
						</td>
						<td align="left">
							<bean:write name="rs" property="lxdz" />
						</td>
						<td align="right">
							�Զ���3��
						</td>
						<td align="left">
							<bean:write name="rs" property="bz3" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�ʱࣺ
						</td>
						<td align="left">
							<bean:write name="rs" property="yb" />
						</td>
						<td align="right">
							�Զ���4��
						</td>
						<td align="left">
							<bean:write name="rs" property="bz4" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�绰��
						</td>
						<td align="left">
							<bean:write name="rs" property="dh" />
						</td>
						<td align="right">
							�Զ���5��
						</td>
						<td align="left">
							<bean:write name="rs" property="bz5" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							
						</td>
						<td align="left">
							
						</td>
						<td align="right">
							��ס֤�������־λ��
						</td>
						<td align="left">
							<bean:write name="rs" property="jzzhlbbzwdm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��Դ�����ܵ�λ��
						</td>
						<td align="left">
							<bean:write name="rs" property="sydzgbm" />
						</td>
						<td align="right">
							��λ���ʴ��룺
						</td>
						<td align="left">
							<bean:write name="rs" property="dwxzdm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������׼�ĺţ�
						</td>
						<td align="left">
							<bean:write name="rs" property="blueno" />
						</td>
						<td align="right">
							��ע��
						</td>
						<td align="left">
							<bean:write name="rs" property="memo" />
						</td>
					</tr>

					<tr style="height:22px">
						<td colspan="4" align="center" bgcolor="DOEOEE">
							<b>��������</b>
						</td>
					</tr>


					<tr style="height:22px">
						<td align="right">
							��λ���ʴ���2��
						</td>
						<td align="left">
							<bean:write name="rs" property="dwxzdm2" />
						</td>
						<td align="right">
							�������յأ�
						</td>
						<td align="left">
							<bean:write name="rs" property="dajsd" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��λ��ַ��
						</td>
						<td align="left">
							<bean:write name="rs" property="dwdz" />
						</td>
						<td align="right">
							�������յص�ַ��
						</td>
						<td align="left">
							<bean:write name="rs" property="dajsddz" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��λ�绰��
						</td>
						<td align="left">
							<bean:write name="rs" property="dwdh" />
						</td>
						<td align="right">
							�������յ��ʱࣺ
						</td>
						<td align="left">
							<bean:write name="rs" property="dajsdyb" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��λ�ʱࣺ
						</td>
						<td align="left">
							<bean:write name="rs" property="dwyb" />
						</td>
						<td align="right">
							��������
						</td>
						<td align="left">
							<bean:write name="rs" property="dqlx" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							ΥԼ��
						</td>
						<td align="left">
							<bean:write name="rs" property="wyj" />
						</td>
						<td align="right">
							��ҵ���ࣺ
						</td>
						<td align="left">
							<bean:write name="rs" property="hyfl" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��һ����ƽ�����ʣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="dynypjgz" />
						</td>
						<td align="right">
							רҵ�Կڣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="zydk" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							
						<logic:equal value="12061" name="xxdm" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />����ˣ�
						</logic:equal>
						<logic:notEqual value="12061" name="xxdm" scope="session">
						<logic:equal value="11122" name="xxdm" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />����ˣ�
						</logic:equal>
							<logic:notEqual value="11122" name="xxdm" scope="session">
								����Ա��ˣ�
							</logic:notEqual>
						</logic:notEqual>
						</td>
						<td align="left" >
							<html:select name="rs" property="fdysh" onchange="">
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
						<logic:equal value="12061" name="xxdm" scope="session">
						<bean:message key="lable.xsgzyxpzxy" />����ˣ�
						</logic:equal>
						<logic:notEqual value="12061" name="xxdm" scope="session">
							����Ա����ˣ�
							</logic:notEqual>
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
						<logic:equal value="12061" name="xxdm" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />���ʱ�䣺
						</logic:equal>
						<logic:notEqual value="12061" name="xxdm" scope="session">
							����Ա���ʱ�䣺
							</logic:notEqual>
							
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
					<tr id="btgtr">
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
