<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<title>��ҵ������Ϣϵͳ</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
		<base target="_self">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	function winclo(){
	   	window.Close();
	}
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ҵ���� - ְҵ��ѯ - ������ѯ�鿴
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>������ѯ�鿴</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td width="15%">
						ѧ��<font color="red">*</font>��
					</td>
					<td width="35%">
						<input id="rid" name="rid" type="hidden" value="<bean:write name="rs" property="rid"/>"/>
						<html:text name="rs" property="xh" maxlength="30" style="width=70%" readonly="true"/>
					</td>
					<td width="15%">
						����<font color="red">*</font>��
					</td>
					<td>
						<html:text name="rs" property="xsxm" maxlength="20" style="width=55%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="">
						���䣺
					</td>
					<td>
						<html:text name="rs" property="age" maxlength="2" style="width=70%" readonly="true"/>
					</td>
					<td align="">
						�Ա�
					</td>
					<td>
						<html:text name="rs" property="xb" maxlength="28" style="width=70%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="" >
						רҵ����<font color="red">*</font>��
					</td>
					<td>
						<html:text name="rs" property="zymc" maxlength="28" style="width=70%" readonly="true"/>
					</td>
					<td align="">
						��������<font color="red">*</font>��
					</td>
					<td>
						<html:text name="rs" property="email" style="width=55%" maxlength="38" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="">
						��ϵ�绰<font color="red">*</font>��
					</td>
					<td>
						<html:text name="rs" property="lxdh" style="width=70%" maxlength="28" readonly="true"/>
					</td>
					
				</tr>
				<tr>
					<td align="">
						��ѯ���ݣ�
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="zxnr" rows="12"
							style="width=100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="">
						��ѯ��ʦ�ظ���
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="zxjshf" rows="12"
							style="width=100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="">
						��ʦ�ظ�ʱ�䣺
					</td>
					<td >
						<html:text name="rs" property="hfsj" style="width=90%"  readonly="true"/>
					</td>
					<td align="">
						��ѯ��ʦ������
					</td>
					<td >
						<html:text name="rs" property="hfrxm" style="width=70%"  readonly="true"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				
				<button class="button2" type="button" style="width:80px" onclick="winclo();">
					�ر�
				</button>
			</div>
		</html:form>
	</body>
</html>

