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
		<script language="javascript" src="pjpy/hbsf/hbsfjs.js"></script>
		<html:form action="/pjpyhbsfwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message key="pjpy_hbsf_priseapply" bundle="pjpyhbsf"/>
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
				<input type="hidden" id="url" name="url" value="/pjpy_hbsf_apply.do" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="13" align="center">
								<b>��д�����</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right" width="10%">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="4">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right" width="6%">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="4">
								<bean:write name='rs' property="xh" /><html:hidden name='rs' property="xh" styleId="xh" />
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
						<td align="right">
							������
						</td>
						<td align="left" colspan="4">
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
						<td align="right">
							�Ա�
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="xb" />
						</td>
						<td align="right" colspan="3">
							<font color="red">*</font>��ѧ��
						</td>
						<td align="left" colspan="5">
							<html:select property="jxjdm" styleId="jxjdm"
								onchange="refreshForm('pjpy_hbsf_apply.do')">
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
						<td align="left" colspan="4">
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
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left" colspan="4">
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
						<td align="right">
							רҵ��
						</td>
						<td align="left" colspan="4">
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
						<td align="right">
							�༶��
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right" colspan="3">
							&nbsp;
						</td>
						<td align="left" colspan="5">
							&nbsp;
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							����ˮƽ��
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="wysp" styleId="a"  style="width:98%"/>
						</td>
						<td align="right" colspan="3">
							�ֻ����룺
						</td>
						<td align="left" colspan="5">
							<html:text name='rs' property="sjhm" styleId="a" style="width:98%" />
						</td>
					</tr>
  <tr>
    <td rowspan="2"><div align="center"><font color="red">*</font>ѧ���</div></td>
    <td rowspan="2" width="12%"> <div align="center">��ѧ�� </div></td>
    <td rowspan="2" width="12%"> <div align="center">���� <br/>
  �� <br/>
  �Ÿ� </div></td>
    <td rowspan="2" width="6%"> <p align="center">˼��<br>����<br>���ʷ� </p>    </td>
    <td rowspan="2" width="6%"> <div align="center">��ѧ<br>
      �Ļ�<br>
    ���ʷ� </div></td>
    <td rowspan="2" width="6%"> <div align="center">����<br>
      ����<br>
    ���ʷ� </div></td>
    <td rowspan="2" width="6%"> <div align="center">�γ�<br>
      ��Ȩ<br>
    ƽ���� </div></td>
    <td rowspan="2" width="6%"> <div align="center">���� </div></td>
    <td colspan="2"> <div align="center">ѧϰ�ɼ����� </div></td>
    <td rowspan="2" width="6%"> <div align="center">������<br>
      ����<br>
    ���� </div></td>
    <td rowspan="2"> <div align="center">����<br>
    ���� </div></td>
    <td rowspan="2"> <div align="center">���� <br>
  �ϸ� <br>
  ��׼ </div></td>
  </tr>
  <tr>
    <td width="6%"> <div align="center">�ۺϲ����ܷ� </div></td>
    <td width="6%"> <div align="center">�ۺ����� </div></td>
  </tr>
  <tr>
    <td><div align="right">
    	<html:select property="xn" styleId="xn" style="width:87px">
    		<html:options collection="xnList" property="xn" labelProperty="xn"/>
    	</html:select>
    </div></td>
    <td><div align="center"><html:text property="jxj1" style="width:98%"></html:text></div></td>
    <td><div align="center"><html:text property="shyg1" style="width:98%"></html:text></div></td>
    <td><div align="center"><html:text property="sxddf" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="kxwhf" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="sxjkf" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="kcjqf" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="pm" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="zhkpzf1" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="zhkppm" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="bjghkms" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="cljz" style="width:98%"></html:text></div></td>
    <td><div align="center"><html:text property="tyhgbz1" style="width:98%"></html:text></div></td>
  </tr>
					<tr align="left" style="height:22px">
						<td align="right">
							ѧϰ������
						</td>
						<td colspan="12">
							<html:textarea name='rs' property='xxjl' style="width:99%"
								rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
							<td align="right">
								������Ŀ��
							</td>
							<td colspan="12">
							<html:textarea name='rs' property='kyxm' style="width:99%"
								rows='5' />
							</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							�������ɣ�
						</td>
						<td colspan="12">
							<html:textarea name='rs' property='sqly' style="width:99%"
								rows='5' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="jxjsqapply('hbsf_jxjsqsave.do','jxjdm-xn-xh')">
						�� �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="window.open('jxjpsdjb.do?xh='+document.getElementById('xh').value+'&jxjdm='+document.getElementById('jxjdm').value)">
						�� ӡ �� ��
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="yes">
					<script>
    alert("����ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("����ʧ�ܣ�");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="exists">
					<script>
    alert("������ѧ��ѧ�������룬�����º˶ԣ�");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
