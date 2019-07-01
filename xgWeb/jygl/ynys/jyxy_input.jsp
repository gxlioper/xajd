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
	      document.forms[0].action = "jyxy_input.do?doType=save";
          document.forms[0].submit();       
	}
	
	</script>
	<logic:equal value="teacher" name="userOnLine">
		<script language="javascript">
			alert('��ҳ��Ϊѧ����дҳ�棡');
		</script>
	</logic:equal>
	<body>
		<html:form action="/jyxy_input" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã���ҵ���� - ��ҵЭ�鷽�� - ��ҵЭ��¼��
				</div>
			</div>

			<html:hidden name="rs" property="xh" />

			<table width="100%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:25px">
						<td colspan="9" align="center">
							<b>��д��ҵЭ��</b>
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
						<html:text name="rs" property="xm" style="width:100%"
							readonly="true" />
					</td>
					<td width="8%">
						�Ա�
					</td>
					<td>
						<html:text name="rs" property="xb" style="width:100%"
							readonly="true" />
					</td>
					<td width="8%">
						����
					</td>
					<td width="15%">
						<html:text name="rs" property="nl" style="width:100%" />
					</td>
					<td width="5%">
						����
					</td>
					<td>
						<html:text name="rs" property="mz" style="width:100%" />
					</td>
				</tr>
				<tr style="height:22px" align="center">
					<td>
						������ò
					</td>
					<td>
						<html:text name="rs" property="zzmm" style="width:100%" />
					</td>
					<td>
						������ʽ
					</td>
					<td colspan="2">
						<html:text name="rs" property="pyfs" style="width:100%" />
					</td>
					<td>
						ѧ��
					</td>
					<td colspan="2">
						<html:text name="rs" property="xl" style="width:100%" />
					</td>
				</tr>
				<tr align="center">
					<td>
						רҵ
					</td>
					<td colspan="3">
						<html:text name="rs" property="zymc" style="width:100%" />
					</td>
					<td>
						ѧ��
					</td>
					<td colspan="3">
						<html:text name="rs" property="xz" style="width:100%"
							readonly="true" />
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
					<td width="8%">
						��ϵ�绰
					</td>
					<td>
						<html:text name="rs" property="dwlxdh" />
					</td>
					<td width="10%">
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
						<html:select name="rs" property="dwxz" styleId="dwxz" style="width:100px">
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
						<input type="radio" name="hyfl" value="����">
						����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="������Ƶ�λ">
						������Ƶ�λ&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="�ߵ�ѧУ">
						�ߵ�ѧУ&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="�е�ר��ѧУ">
						�е�ר��ѧУ&nbsp;
						<input type="radio" name="hyfl" value="��ѧ">
						��ѧ
						<br>
						<input type="radio" name="hyfl" value="Сѧ">
						Сѧ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="�׶�԰">
						�׶�԰&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="˽����Сѧ">
						˽����Сѧ&nbsp;
						<input type="radio" name="hyfl" value="����������λ">
						����������λ&nbsp;
						<input type="radio" name="hyfl" value="ҽ��������λ">
						ҽ��������λ
						<br>
						<input type="radio" name="hyfl" value="����̨">
						����̨&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="������ҵ��λ">
						������ҵ��λ&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="���ڵ�λ">
						���ڵ�λ&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="������ҵ">
						������ҵ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="������ҵ">
						������ҵ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="hyfl" value="������ҵ">
						������ҵ &nbsp;&nbsp;
						<input type="radio" name="hyfl" value="����">
						����
					</td>
				</tr>
				<tr>
					<td align="center">
						����ת��<br>��ϸ��ַ
					</td>
					<td colspan="5">
						<html:text name="rs" property="dayjdz" style="width:100%" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="savejyxy()">
					�� �� Э ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" type="reset">
					�� ��
				</button>
			</div>
			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
    alert("�ύ�ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
    alert("�ύʧ�ܣ������Ƿ��ظ��ύ��");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
