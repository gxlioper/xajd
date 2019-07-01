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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
		
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/szdwfzjy" method="post">
			
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			
			<logic:notEmpty name="rs">
			<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
			<input type="hidden" id="bjdm" name="bjdm"
				value="<bean:write name='rs' property='bjdm'/>" />
			
				<fieldset>
					<legend>
						ѧ�����������걨
					</legend>
					<table width="100%" class="tbstyle" colspan="2">
						<tr>
							<td align="right" colspan="2" >
								<font color="red">*</font>ѧ�꣺
							</td>
							<td align="left" colspan="2">
								<html:select name = "rs" property="xn" style="width:120px" 
									styleId="xn">
								<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<td align="right" colspan="2">
								<font color="red">*</font>��ȣ�
							</td>
							<td align="left" colspan="2">
								<html:select name = "rs" property="nd" style="width:90px"
										styleId="nd">
								<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right"   colspan="2" >
								�༶���ƣ�
							</td>
							<td align="left" colspan="2" >
								<bean:write name='rs' property="bjmc" />
							</td>
							<td align="right" colspan="2" >
								�꼶��
							</td>
							<td align="left" colspan="2" >
								<bean:write  name='rs' property="nj"/>
							</td>
						</tr>
						<tr>
							<td align="right" colspan="2">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left" colspan="2">
								<bean:write name='rs' property="xymc" />
							</td>
							<td align="right" colspan="2">
								רҵ��
							</td>
							<td align="left" colspan="2">
								<bean:write  name='rs' property="zymc"/>
							</td>
						</tr>
						<tr>
							<td align="right" colspan="2">
								�༶����:
							</td>
							<td align="left" colspan="2">
								<html:text name='rs' property="bjrs" readonly = "true"/>
							</td>
							<td align="right" colspan="2">
								 �೤:
							</td>
							<td align="left" colspan="2">
								<html:text name='rs' property="xm" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<td align="right" colspan="2" >
								������ͨ��������
							</td>
							<td align="left" colspan="2" >
								<html:text name='rs' property="sljtgrs" />
							</td>
							<td align="right" colspan="2">
								ѧ����ѧ��:
							</td>
							<td align="left" colspan="2">
								<html:text name='rs' property="xqzxf" />
							</td>
						</tr>
						<tr>
							<td align="right" colspan="2" rowspan="6">
								һѧ�ڸ��γ̳ɼ�ͳ�������
							</td>
							<td align="left"  colspan="2">
								�γ����Ƽ�ѧ��
							</td>
							<td align="left">
								��߷֣�
							</td>
							<td align="left">
								��ͷ�
							</td>
							<td align="left">
								ƽ����
							</td>
							<td align="left">
								������
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2">
								<html:text name='rs' property="kcmc1" />
							</td>
							<td align="left">
								<html:text name='rs' property="zgf1" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="zdf1" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="pjf1" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="yxl1" style="width:80px"/>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2">
								<html:text name='rs' property="kcmc2" />
							</td>
							<td align="left">
								<html:text name='rs' property="zgf2" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="zdf2" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="pjf2" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="yxl2" style="width:80px"/>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2">
								<html:text name='rs' property="kcmc3"/>
							</td>
							<td align="left">
								<html:text name='rs' property="zgf3" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="zdf3" style="width:80px" />
							</td>
							<td align="left">
								<html:text name='rs' property="pjf3" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="yxl3" style="width:80px"/>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2">
								<html:text name='rs' property="kcmc4"/>
							</td>
							<td align="left">
								<html:text name='rs' property="zgf4" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="zdf4" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="pjf4" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="yxl4" style="width:80px"/>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2">
								<html:text name='rs' property="kcmc5" />
							</td>
							<td align="left">
								<html:text name='rs' property="zgf5" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="zdf5" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="pjf5" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="yxl5" style="width:80px"/>
							</td>
						</tr>
						<logic:equal  name="userType" value = "xy" scope = "session">
						<tr>
							<td align="right" colspan="2">
								<bean:message key="lable.xsgzyxpzxy" />��ˣ�
							</td>
							<td align="left" colspan="2">
								<html:select name = "rs" property="xysh" style="width:120px" styleId="xysh" >
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
							</td>
							<td align="right" colspan="2">								
							`	ѧУ��ˣ�
							</td>
							<td align="left" colspan="2"> 
								<html:select name = "rs"  property="xxsh" style="width:120px" styleId="xxsh" disabled = "true">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
								<input type = "hidden" name = "xxsh" value="<bean:write name = "rs"  property="xxsh"/>" />
							</td>
						</tr>
						<tr>
							<td align="right" colspan="2">
								<bean:message key="lable.xsgzyxpzxy" />��������
							</td>
							<td align="left" colspan="6" >
								<html:textarea name = "rs" property="xyyj" style="width:99%" rows="5" />
							</td>
						</tr>
						<tr>
							<td align="right" colspan="2">
								ѧУ��������
							</td>
							<td align="left" colspan="6" >
								<html:textarea name = "rs" property="xxyj" style="width:99%"  rows="5" readonly = "true"/>
							</td>
						</tr>
						</logic:equal>
						<logic:notEqual name="userType" value = "xy" scope = "session">
						<tr>
							<td align="right" colspan="2">
								<bean:message key="lable.xsgzyxpzxy" />��ˣ�
							</td>
							<td align="left" colspan="2" >
								<html:select  name = "rs"  property="xysh" style="width:120px" styleId="xysh" disabled = "true">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
								<input type = "hidden" name = "xysh" value="<bean:write name = "rs"  property="xysh"/>" />
							</td>
							<td align="right" colspan="2">
								ѧУ��ˣ�
							</td>
							<td align="left" colspan="2">
								<html:select  name = "rs"  property="xxsh" style="width:120px" styleId="xxsh">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right" colspan="2">
								<bean:message key="lable.xsgzyxpzxy" />��������
							</td>
							<td align="left" colspan="6" >
								<html:textarea name = "rs" property="xyyj" style="width:99%" rows="5" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<td align="right" colspan="2">
								ѧУ��������
							</td>
							<td align="left" colspan="6" >
								<html:textarea name = "rs" property="xxyj" style="width:99%"  rows="5"/>
							</td>
						</tr>
						</logic:notEqual>
						<tr align="left">
							<td align="right" colspan="2" >
								�༶���������˵��
							</td>
							<td colspan="6" >
								<html:textarea name = "rs" property="bjjjsqsm" style="width:99%" rows="5" />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="szsxDataDoSave('szdwfzjy.do?method=xsjljtSh','bjdm-xn-nd');"
						style="width:80px" id="buttonSave">
						����
					</button>
				</div>
			</logic:notEmpty>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("�޸ĳɹ���");
    dialogArgumentsQueryChick();
	Close();
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("�޸�ʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
