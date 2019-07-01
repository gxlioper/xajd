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
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="������� zfsoft">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<base target="_self">
	<body>
		<html:form action="/stu_archives_history">
			<input type="hidden" name="url" id="url" value="/xsxxgl.do?method=xscjbgdxxAdd">
			<input type="hidden" value="xh-xm-xb-nj-xymc-zymc-bjmc" id="getStuInfo" name="getStuInfo" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ����Ϣ - ѧ����Ϣ - �ɼ����浥��Ϣά�� - �����Ϣ
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								�ɼ����浥��Ϣ
							</td>
						</tr>
					</thead>

					<tr>
						<td align="right">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td>
							<html:text name="rs" property="xh" styleId="xh" readonly="true"/>
						</td>
						<td>
							<div align="right">
								ѧ�꣺
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xn" />
							<input type="hidden" id="xn" name="xn" value="${rs.xn}"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							������
						</td>
						<td>
							<bean:write name="rs" property="xm" />
						</td>
						<td align="right">
							ѧ�ڣ�
						</td>
						<td>
							<bean:write name="rs" property="xqmc" />
							<input type="hidden" id="xq" name="xq" value="${rs.xq}"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td>
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right">
							Ӧ�Ͽ�������
						</td>
						<td>
							<html:text name="rs" property="yskts" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')" ></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
						<td align="right" nowrap="nowrap">
							ʵ�Ͽ�������
						</td>
						<td>
							<html:text name="rs" property="sskts" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')" ></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
						<td align="right">
							���٣�
						</td>
						<td>
							<html:text name="rs" property="bingjia" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')" ></html:text>��
						</td>
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
						<td align="right">
							�¼٣�
						</td>
						<td>
							<html:text name="rs" property="shijia" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')" ></html:text>��
						</td>
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td>
							<bean:write name="rs" property="bjmc" />
						</td>
						<td align="right">
							�ٵ�(����)��
						</td>
						<td>
							<html:text name="rs" property="chidao" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')" ></html:text>��
						</td>
					</tr>
					<tr>
						<td align="right">
							���Σ�
						</td>
						<td>
							<html:text name="rs" property="kuangke" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')" ></html:text>��
						</td>
						<td align="right">
							������
						</td>
						<td>
							<html:text name="rs" property="qita" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')" ></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							�����Σ�
						</td>
						<td>
							<html:text name="rs" property="bzr" maxlength="15"></html:text>
						</td>
						<td align="right">
							��������ϵ�绰��
						</td>
						<td>
							<html:text name="rs" property="bzrlxdh" maxlength="12" onkeyup="value=value.replace(/[^\d|.]/g,'')"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							�ۺ�����������
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="zhszpd" styleId="zhszpd" cols="20" rows="4" onblur="chLeng(this,'300')"></html:textarea>
						</td>
					</tr>
				</table>
				<center>
					<div class="buttontool" id="btn" width="100%">
						<button type="button" class="button2"
							onclick="refreshForm('xsxxgl.do?method=modiXscjbgdxx');"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="window.close();return false;"
							style="width:80px">
							�� ��
						</button>
					</div>
				</center>

			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("�����ɹ���");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();		
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("����ʧ�ܣ�");
					Close();
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
