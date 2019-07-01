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


	<script language="javascript">
	function savejyxy(){
	      document.forms[0].action = "savejyxyupdate.do?doType=update";
          document.forms[0].submit();       
	}
	
	</script>
	<body>
		<html:form action="/savejyxyupdate" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã���ҵ���� - ��ҵЭ�鷽�� - ��ҵЭ���޸�
				</div>
			</div>

			<html:hidden name="rs" property="xh" />

			<table width="100%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:25px">
						<td colspan="9" align="center">
							<b>��д��ҵЭ��</b><font color="red"><br>ע����ʾΪ�������ǿ��޸�����</font>
						</td>
					</tr>
				</thead>

				<tr style="height:22px" align="center">
					<td rowspan="4" width="5%">
						ѧ
						<br>
						��
						<br>
						��
						<br>
						��
					</td>
					<td width="8%">
						����
					</td>
					<td>
						<bean:write name="rs" property="xm" />
					</td>
					<td width="8%">
						�Ա�
					</td>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
					<td width="8%">
						����
					</td>
					<td width="15%">
						<bean:write name="rs" property="nl" />
					</td>
					<td width="5%">
						����
					</td>
					<td>
						<bean:write name="rs" property="mz" />
					</td>
				</tr>
				<tr style="height:22px" align="center">
					<td>
						������ò
					</td>
					<td>
						<bean:write name="rs" property="zzmm" />
					</td>
					<td>
						������ʽ
					</td>
					<td colspan="2">
						<bean:write name="rs" property="pyfs" />
					</td>
					<td>
						ѧ��
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xl" />
					</td>
				</tr>
				<tr align="center">
					<td>
						רҵ
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zymc" />
					</td>
					<td>
						ѧ��
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xz" />
					</td>
				</tr>
				<tr align="center">
					<td>
						��ͥ��ַ
					</td>
					<td colspan="3">
						<html:text name="rs" property="jtdz" style="width:100%" />
					</td>
					<td>
						��ϵ�绰
					</td>
					<td colspan="3">
						<html:text name="rs" property="lxdh" style="width:100%" />
					</td>
				</tr>
				<tr>
					<td colspan="9">
						&nbsp;
					</td>
				</tr>
			</table>
			<table width="100%" id="rsT2" class="tbstyle">
				<tr align="center">
					<td rowspan="5" width="7%">
						��
						<br>
						��
						<br>
						��
						<br>
						λ
						<br>
						��
						<br>
						��
					</td>
					<td width="10%">
						��λ����
					</td>
					<td colspan="2">
						<html:text name="rs" property="dwmc" style="width:100%" />
					</td>
					<td width="10%">
						��λ����
					</td>
					<td colspan="2">
						<html:text name="rs" property="dwls" style="width:100%" />
					</td>
				</tr>
				<tr align="center">
					<td>
						��ϵ��
					</td>
					<td width="17%">
						<html:text name="rs" property="dwlxr" style="width:100%" />
					</td>
					<td width="10%">
						��ϵ�绰
					</td>
					<td>
						<html:text name="rs" property="dwlxdh" />
					</td>
					<td width="13%">
						��������
					</td>
					<td>
						<html:text name="rs" property="dwyb" style="width:100%" />
					</td>
				</tr>
				<tr align="center">
					<td>
						ͨѶ��ַ
					</td>
					<td colspan="3">
						<html:text name="rs" property="dwdz" style="width:100%" />
					</td>
					<td>
						����������
					</td>
					<td align="left">
						<html:select name="rs" property="dwxz" styleId="dwxz"
							style="width:100px">
							<html:option value=""></html:option>
							<html:options collection="dwxzdm2List" property="dwxz"
								labelProperty="dwxz" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						��λ����
					</td>
					<td colspan="5">
						<html:radio name="rs" property="hyfl" value="����" />
						����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="rs" property="hyfl" value="������Ƶ�λ" />
						������Ƶ�λ&nbsp;&nbsp;
					    <html:radio name="rs" property="hyfl" value="�е�ר��ѧУ"/>
						�е�ר��ѧУ&nbsp;
						<html:radio name="rs" property="hyfl" value="��ѧ"/>
						��ѧ
						<br>
						<html:radio name="rs" property="hyfl" value="Сѧ"/>
						Сѧ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="rs" property="hyfl" value="�׶�԰"/>
						�׶�԰&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="rs" property="hyfl" value="˽����Сѧ"/>
						˽����Сѧ&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="rs" property="hyfl" value="����������λ"/>
						����������λ&nbsp;
						<html:radio name="rs" property="hyfl" value="ҽ��������λ"/>
						ҽ��������λ
						<br>
						<html:radio name="rs" property="hyfl" value="����̨"/>
						����̨&nbsp;&nbsp;
						<html:radio name="rs" property="hyfl" value="������ҵ��λ"/>
						������ҵ��λ&nbsp;&nbsp;
						<html:radio name="rs" property="hyfl" value="���ڵ�λ"/>
						���ڵ�λ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="rs" property="hyfl" value="������ҵ"/>
						������ҵ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="rs" property="hyfl" value="������ҵ"/>
						������ҵ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="rs" property="hyfl" value="������ҵ"/>
						������ҵ &nbsp;&nbsp;
						<html:radio name="rs" property="hyfl" value="����"/>
						����
					</td>
				</tr>
				<tr>
					<td align="center">
						����ת��
						<br>
						��ϸ��ַ
					</td>
					<td colspan="5">
						<html:text name="rs" property="dayjdz" style="width:100%" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="savejyxy()">
					�� �� �� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" type="reset">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2"
					onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">
					�� ��
				</button>
			</div>
			<logic:notEmpty name="update">
				<logic:equal name="update" value="ok">
					<script>
                          alert("�޸ĳɹ���");
                    </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
                         alert("�ύʧ�ܣ�");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
