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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">	
	<body onload="">
		<script type="text/javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_whlgdx.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getJxjList.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/whlgPjpy.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		
		<html:form action="/pjpy_whlgdx.do" method="post">
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
				<input type="hidden" id="disableEle" name="disableEle"value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/pjpy/whlgdx_rych_apply.jsp" />
				<input type="hidden" name="tab1" id="tab1" value="qtjxj" />
				<html:hidden property="xn" name="rs"/>
				<html:hidden property="nd" name="rs"/>
				<html:hidden property="xq" name="rs"/>
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="11" align="center">
								<b>��д�����</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td width="23%" align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true"/>	
									<logic:notEqual value="modi" name="doType">
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
								</logic:notEqual>								
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="2">
								<bean:write name='rs' property="xh" />
								<html:hidden name='rs' property="xh" styleId="xh" />
							</td>
						</logic:equal>
						<td align="right">
							��ȣ�
						</td>
						<td colspan="4" align="left">
							<bean:write name='rs' property="nd" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������
						</td>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right">
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
						<td align="left">
							<bean:write name='rs' property="xb" />
						</td>
						<td>
							<div align="right">
								˼��������ʷ�����
							</div>
						</td>
						<td colspan="4">
							<bean:write name='rs' property="dcj" />
						</td>

					</tr>
					<tr style="height:22px">
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<bean:write name='rs' property="nj" />
						</td>
						<td>
							<div align="right">
								ѧϰƽ���ɼ���
							</div>
						</td>
						<td colspan="4">
							<bean:write name='rs' property="xxpjcj" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right">
							ѧϰƽ���ɼ�������
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property='xxpjcjpm' />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
						<td align="right">
							ѧϰƽ���ɼ�����������
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="xxpjcjpmbl" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right">
							�������ʷ�����
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="stszzf" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<bean:write name='rs' property="nj" />
						</td>
						<td align="right">
							��չ���ʷ�����
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="sztzzf" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�۲��ܷ֣�
						</td>
						<td align="left">
							<bean:write name='rs' property="zhszcpzf" />
						</td>
						<td align="right">
							�۲��ܷ�������
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="zhszcpcjpm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�۲��ܷ�����������
						</td>
						<td align="left">
							<bean:write name='rs' property="zhszcpcjpmbl" />
						</td>
						<td align="right">
							������ͷ�����
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="dkzdfs" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������������
						</td>
						<td align="left">
							<bean:write name='rs' property="wygjqk" />
						</td>
						<td align="right">&nbsp;							</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="sfpks" />
						</td>
					</tr>
					<tr style="height:22px">						
						<td align="right">
							�����ƺ����
						</td>
						<td align="left">
							<html:select property="jxjfl" name="rs" onchange="initJxjdmList('rychdmb')">
							<html:option value=""></html:option>
							<html:options collection="jxjflList" property="jxjfldm" labelProperty="jxjflmc"/>
							</html:select>
						</td>
						<td align="right">
							<font color="red">*</font>�����ƺţ�
						</td>
						<td align="left" colspan="4">
						<logic:equal value="modi" name="doType">
						<html:select property="rychdm" styleId="jxjdm"
								onchange="showHidden(this)" name="rs" disabled="true">
								<option value=""></option>
								<html:options collection="rychList" property="jxjdm" labelProperty="jxjmc" />
							</html:select>
							<html:hidden property="jxjdm" name="rs"/>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">
							<html:select property="rychdm" styleId="jxjdm"
								onchange="showHidden(this)" name="rs">
								<option value=""></option>
								<html:options collection="rychList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select>
						</logic:notEqual>
						</td>
					</tr>
					<tr id="dlsq" style="display:none">
						<td align="right">�������룺
						</td>
						<td align="left" colspan="6">
						<html:select property="sfdlsq" name="rs">
							<html:option value=""></html:option>
							<html:options collection="chkList" property="en" labelProperty="cn"/>
						</html:select>
					  	</td>
					</tr>
					<tr align="left" style="height:22px;display: none" id="dlsqly">
					  <td align="right">�����������ɣ�</td>
					  <td colspan="6">
					  <html:textarea name='rs' property='dlsqly' style="width:99%" rows='5' />
					  </td>
				  </tr>
					<tr align="left" style="height:22px">
						<td align="right">
							�������ɣ�
						</td>
						<td colspan="6">
							<html:textarea name='rs' property='sqly' style="width:99%" rows='5' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="rychCommit('xh-rychdm')">
						�� �� �� ��
					</button>
					<button class="button2"
						onclick="expAppTab('rsT','�����ƺ������','')">
						�� ӡ �� ��
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
					<script>
    					alert("����ɹ���");
    					Close();
    				</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
    					alert("����ʧ�ܣ�");    					
    				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
