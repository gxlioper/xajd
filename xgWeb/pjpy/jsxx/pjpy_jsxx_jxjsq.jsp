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
		<html:form action="/pjpy_jsxx_apply" method="post">
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
							<td colspan="15" align="center">
								<b>��д�����</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right" colspan="2" width="15%">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="5" width="37%">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right" colspan="2"width="15%">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="5" width="37%">
								<bean:write name='rs' property="xh" /><html:hidden name='rs' property="xh" styleId="xh" />
							</td>
						</logic:equal>
						<td align="right" colspan="3">
							ѧ�꣺
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="xn" />
						</td>				
					</tr>
					<tr style="height:22px" width="33%">
						<td align="right" colspan="2">
							������
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right" colspan="3" width="15%">
							ѧ�ڣ�
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="xqmc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							�Ա�
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="xb" />
						</td>
						<td align="right" colspan="3">
							<font color="red">*</font>��ѧ��
						</td>
						<td align="left" colspan="5">
							<html:select property="xmdm" styleId="jxjdm"
								onchange="refreshForm('/xgxt/prise_apply.do')">
								<option value=""></option>
								<html:options collection="jxjList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							�꼶��
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="nj" />
						</td>
						<td align="right" colspan="3">
							��ѧ�����
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="jxjlb" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right" colspan="3">
							������
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property='jlje' />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							רҵ��
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="zymc" />
						</td>
						<td align="right" colspan="3">
							����ְ��
						</td>
						<td align="left" colspan="5">
							<html:text name='rs' property="drzw" styleId="a" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							�༶��
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right" colspan="3">
							�ֻ����룺
						</td>
						<td align="left" colspan="5">
							<html:text name='rs' property="sjhm" styleId="a" style="width:50%" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							����ˮƽ��
						</td>
						<td align="left" colspan="5">
							<html:text name='rs' property="wysp" styleId="a" style="width:50%"/>
						</td>
						<td align="right" colspan="3">
						</td>
						<td align="left" colspan="5">
						</td>
					</tr>
					<tr>
						<td rowspan="2" align="right" colspan="2">�ɼ���</td>
					    <td width="10%" align="center">����</td>
					    <td width="10%" align="center">����</td>
					    <td><bean:write name="rskc" property="kc1"/></td>
					    <td><bean:write name="rskc" property="kc2"/></td>
					    <td><bean:write name="rskc" property="kc3"/></td>
					    <td><bean:write name="rskc" property="kc4"/></td>
					    <td><bean:write name="rskc" property="kc5"/></td>
					    <td><bean:write name="rskc" property="kc6"/></td>
					    <td><bean:write name="rskc" property="kc7"/></td>
					    <td><bean:write name="rskc" property="kc8"/></td>
					    <td><bean:write name="rskc" property="kc9"/></td>
					    <td><bean:write name="rskc" property="kc10"/></td>
					    <td width="10%" align="center">ƽ���ɼ�</td>
					</tr>
					<tr>
						<td align="center"><bean:write name="rszh" property="cxcj"/></td>
						<td align="center"><bean:write name="rszh" property="tycj"/></td>
					    <td align="center"><bean:write name="rscj" property="kccj1"/></td>
					    <td align="center"><bean:write name="rscj" property="kccj2"/></td>
					    <td align="center"><bean:write name="rscj" property="kccj3"/></td>
					    <td align="center"><bean:write name="rscj" property="kccj4"/></td>
					    <td align="center"><bean:write name="rscj" property="kccj5"/></td>
					    <td align="center"><bean:write name="rscj" property="kccj6"/></td>
					    <td align="center"><bean:write name="rscj" property="kccj7"/></td>
					    <td align="center"><bean:write name="rscj" property="kccj8"/></td>
					    <td align="center"><bean:write name="rscj" property="kccj9"/></td>
					    <td align="center"><bean:write name="rscj" property="kccj10"/></td>
					    <td align="center"><bean:write name="rszh" property="pjcj"/></td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right" colspan="2">
							ѧϰ������
						</td>
						<td colspan="13">
							<html:textarea name='rs' property='xxjl' style="width:99%"
								rows='5' onblur="chLeng(this,900)"/>
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<logic:present name="showhzy">
							<td align="right" colspan="2">
								���������
							</td>
							<td colspan="13">
								<html:textarea name='rs' property='jfqk' style="width:99%"
									rows='5'/>
							</td>
						</logic:present>
						<logic:notPresent name="showhzy">
							<td align="right" colspan="2">
								������Ŀ��
							</td>
							<td colspan="13">
							<html:textarea name='rs' property='kycg' style="width:99%"
								rows='5' onblur="chLeng(this,900)"/>
							</td>
						</logic:notPresent>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right" colspan="2">
							�������ɣ�
						</td>
						<td colspan="13">
							<html:textarea name='rs' property='sqly' style="width:99%"
								rows='5' onblur="chLeng(this,900)"/>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="dataDoSaveApply1('/xgxt/applySave.do','jxjdm','jxj','xh-jxjdm')">
						�� �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="window.open('pjpy_jsxx_jxjpsdjb.do?xh='+document.getElementById('xh').value+'&jxjdm='+document.getElementById('jxjdm').value)">
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
