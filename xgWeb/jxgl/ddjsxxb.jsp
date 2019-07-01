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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<!-- ͷ�ļ� -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%@ include file="/syscommon/pagehead_xg.ini"%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>

	<script language="javascript">
	</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<html:form action="/viewArmyStu" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>��ǰλ�ã�</em>
					<a>��ѵ���� - ��Ϣά�� - ����������ʦ��Ϣά��</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�޼�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />

					<table class="formlist" border="0" align="center" style="width: 100%">
						<thead>
							<tr>
								<th colspan="4">
									<span>������ʦ��Ϣά��</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<th>
								<font color="red">*</font>��ʦ���룺
							</th>
							<td>
							<logic:equal value="modi" name="doType">
								<html:text name="rs" property="jsdm" styleId="jsdm" readonly="true"></html:text>
							</logic:equal>
							<logic:notEqual value="modi" name="doType">
								<html:text name="rs" property="jsdm" styleId="jsdm"></html:text>
							</logic:notEqual>
							</td>
							<th>
								<font color="red">*</font>��ʦ������
							</th>
							<td>
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								<html:select name="rs" property="xb" styleId="xb">
									<html:options collection="xbList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th>
								����<bean:message key="lable.xsgzyxpzxy" />��
							</th>
							<td>
								<html:select name="rs" property="xydm" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								���壺
							</th>
							<td>
								<html:select name="rs" property="mzdm" styleId="mz">
									<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
								</html:select>
							</td>
							<th>
								��ϵ�绰��
							</th>
							<td align="left">
								<html:text name="rs" property="lxdh" styleId="lxdh"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�������ӣ�
							</th>
							<td align="left">
								<html:select name="rs" property="sfzld" styleId="sfzld">
									<html:option value=""></html:option>
									<html:options collection="bzList" property="bz" labelProperty="bz"/>
								</html:select>
							</td>
							<th>
								���Ӵ�����
							</th>
							<td align="left">
								<html:text name='rs' property="ddcs" styleId="ddcs" />
							</td>
						</tr>
						<tr>
						<th>
							��ע��
						</th>
						<td colspan="3" align="left">
							<html:textarea name="rs" property="bz" style="width:90%" rows="3"></html:textarea>
						</td>
					</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="4">
						
							<div class="btn">
								<button type="button" class="button2"
									onclick="if(chkTeacherCode('jsdm')){Savedata('jsdm-xm','TeacherInfoSave.do'),return false;};"
									style="width:80px" id="buttonSave">
									�� ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
									id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("�����ɹ�!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��!");
			</script>
		</logic:equal>
	</body>
</html>
