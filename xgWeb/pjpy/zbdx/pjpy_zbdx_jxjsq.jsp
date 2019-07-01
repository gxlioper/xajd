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
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">

</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/pjpy_zbdx_apply" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��������� - ��ѧ������ - ��д�����
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/prise_apply.do" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="12" align="center">
								<b>��д�����</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="2">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="2">
								<bean:write name='rs' property="xh" /><html:hidden name='rs' property="xh" styleId="xh" />
							</td>
						</logic:equal>
						<td align="right" >
							��ȣ�
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="nd" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right" >
							ѧ�꣺
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="xn" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�Ա�
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="xb" />
						</td>
						<td align="right" >
							<font color="red">*</font>��ѧ��
						</td>
						<td align="left" colspan="4">
							<html:select property="xmdm" styleId="jxjdm"
								onchange="refreshForm('/xgxt/prise_apply.do')">
								<option value=""></option>
								<html:options collection="jxjList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�꼶��
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="nj" />
						</td>
						<td align="right" >
							��ѧ�����
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="jxjlb" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right" >
							������
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property='jlje' />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							רҵ��
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="zymc" />
						</td>
						<td align="right" >
							����ְ��
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="drzw" styleId="a" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�༶��
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right" >
							�ֻ����룺
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="sjhm" styleId="a" style="width:50%" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							����ˮƽ��
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="wysp" styleId="a"  style="width:98%"/>
						</td>
						<td align="right" >
						</td>
						<td align="left" colspan="4">
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							ѧϰ������
						</td>
						<td colspan="7">
							<html:textarea name='rs' property='xxjl' style="width:99%"
								rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<logic:present name="showhzy">
							<td align="right">
								���������
							</td>
							<td colspan="7">
								<html:textarea name='rs' property='jfqk' style="width:99%"
									rows='5' />
							</td>
						</logic:present>
						<logic:notPresent name="showhzy">
							<td align="right">
								������Ŀ��
							</td>
							<td colspan="7">
							<html:textarea name='rs' property='kyxm' style="width:99%"
								rows='5' />
							</td>
						</logic:notPresent>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							�������ɣ�
						</td>
						<td colspan="7">
							<html:textarea name='rs' property='sqly' style="width:99%"
								rows='5' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="dataDoSaveApply1('/xgxt/applySave.do','jxjdm','jxj','xh-jxjdm')">
						�� �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="window.open('pjpy_zbdx_jxjpsdjb.do?xh='+document.getElementById('xh').value+'&jxjdm='+document.getElementById('jxjdm').value)">
						�� ӡ �� ��
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("����ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("����ʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
