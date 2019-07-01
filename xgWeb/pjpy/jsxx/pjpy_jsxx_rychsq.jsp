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
		<html:form action="/rych_jsxx_apply" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��������� - �����ƺ����� - ��д�����
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
				<input type="hidden" id="url" name="url" value="/credit_apply.do" />
				<table width="99%" id="rsT" class="tbstyle">
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
							<td align="right" colspan="2" width="15%">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="5" width="37%"><html:hidden name='rs' property="xh" styleId="xh" />
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<td align="right" colspan="3">
							��ȣ�
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="nd" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							������
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right" colspan="3">
							ѧ�꣺
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="xn" />
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
							<font color="red">*</font>�����ƺţ�
						</td>
						<td align="left" colspan="5">
							<html:select property="xmdm" styleId="rychdm">
								<option value=""></option>
								<html:options collection="rychList" property="rychdm"
									labelProperty="rychmc" />
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
						<td align="right" colspan="3">����ˮƽ��</td>
						<td align="left" colspan="5"><html:text name='rs' property="wysp" styleId="a"  style="width:98%"/></td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right" colspan="3">�ֻ����룺</td>
						<td align="left" colspan="5"><html:text name='rs' property="sjhm" styleId="a" style="width:98%" /></td>
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
							<html:text name='rs' property="drzw" styleId="a" style="width:98%" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							�༶��
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right" colspan="3">������������
						</td>
						<td align="left" colspan="5"><html:text name='rs' property="tydbqk" style="width:98%" styleId="a" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							��Դ�أ�
						</td>
						<td align="left" colspan="5">
							<html:text name='rs' property="syd" style="width:98%" styleId="a" />
						</td>
						<td align="right" colspan="3">��ҵ��ѧ��
						</td>
						<td align="left" colspan="5"><html:text name='rs' property="byzx" style="width:98%" styleId="a" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							��ͥ��ַ��
						</td>
						<td colspan="13" align="left"><html:text name='rs' style="width:98%" property="jtdz" styleId="a" />
						</td>
					</tr>
					<tr>
						<td rowspan="5" align="center">�ɼ�</td>
					    <td width="8%" align="center">����</td>
					    <td width="8%" align="center">����</td>
					    <td width="6%" align="center"><bean:write name="rskc" property="kc1"/></td>
					    <td width="6%" align="center"><bean:write name="rskc" property="kc2"/></td>
					    <td width="6%" align="center"><bean:write name="rskc" property="kc3"/></td>
					    <td width="6%" align="center"><bean:write name="rskc" property="kc4"/></td>
					    <td width="6%" align="center"><bean:write name="rskc" property="kc5"/></td>
					    <td width="6%" align="center"><bean:write name="rskc" property="kc6"/></td>
					    <td width="6%" align="center"><bean:write name="rskc" property="kc7"/></td>
					    <td width="6%" align="center"><bean:write name="rskc" property="kc8"/></td>
					    <td width="6%" align="center"><bean:write name="rskc" property="kc9"/></td>
					    <td width="6%" align="center"><bean:write name="rskc" property="kc10"/></td>
					    <td width="8%" align="center">ƽ���ɼ�</td>
					</tr>
					<tr>
						<td align="center"><bean:write name="rs1" property="cxcj"/></td>
						<td align="center"><bean:write name="rs1" property="tycj"/></td>
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
					    <td align="center"><bean:write name="rs1" property="pjcj"/></td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							���˼�����
						</td>
						<td colspan="13" align="left"><html:textarea rows="5" name='rs' style="width:98%" property="brjl" styleId="a" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							��Ҫ�¼���
						</td>
						<td colspan="13" align="left"><html:textarea rows="5" name='rs' style="width:98%" property="zysj" styleId="a" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							�������
						</td>
						<td colspan="13" align="left"><html:textarea rows="5" name='rs' style="width:98%" property="hjqk" styleId="a" />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="dataDoSaveApply1('/xgxt/applySave.do','rychdm','rych')">
						�� �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"  onclick="window.open('rychqsb.do?xh='+document.getElementById('xh').value+'&rychdm='+document.getElementById('rychdm').value)">
						�� ӡ �� ��
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("����ɹ���");
    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	window.dialogArguments.document.all("search_go").click();
    	Close();
    }
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("����ʧ�ܣ�");
    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	window.dialogArguments.document.all("search_go").click();
    	Close();
    }
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="nocondi">
					<script>
    alert("����������������,����ʧ�ܣ�");
    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	window.dialogArguments.document.all("search_go").click();
    	Close();
    }
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
