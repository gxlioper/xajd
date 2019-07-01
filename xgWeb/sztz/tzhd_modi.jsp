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
	<base target="_self"/>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/comm_pub" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�������չ - ��չ���Ϣ���� - ��Ϣ����
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
                <input type="hidden" id="writeAble" name="writeAble" value="<bean:write name="writeAble" scope="request"/>" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="url" name="url" value="/sjcz/dwjlxxb.jsp" />						
				<fieldset>
					<legend>
						��Ϣ�����޸�
					</legend>
					<table width="100%" class="tbstyle">
						<tr valign="middle">
							<td width="15%" align="right" nowrap >
								<font color="red">*</font>ѧ�꣺
							</td>
							<td width="35%" align="left">
								<html:select name="rs" styleId="xn" property="xn" style="background-color:#FFFFFF;"  disabled="true">								
									<html:options collection="xnList" property="xn"
										labelProperty="xn"></html:options>
								</html:select>
							</td>
							<td width="15%" align="right" nowrap>
								<font color="red">*</font>ѧ�ڣ�
							</td>
							<td width="35%" align="left">
								<html:select name="rs" styleId="xq" property="xq" style="background-color:#FFFFFF;" disabled="true">
									<option value=""></option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc"></html:options>
								</html:select>
							</td>
						</tr>
						<tr valign="middle">
							<td align="right" nowrap>
								<font color="red">*</font>��ȣ�
							</td>
							<td align="left">
								<html:select name="rs" styleId="nd" property="nd" style="background-color:#FFFFFF;" disabled="true">
									<html:options collection="xnList" property="nd"
										labelProperty="nd"></html:options>
								</html:select>
							</td>
							<td align="right" nowrap>
								<font color="red">*</font>��չ�(��Ŀ)��
							</td>
							<td align="left">
								<html:select name="rs" property="xmdm" styleId="xmdm"
									onchange="refreshForm('/xgxt/tzhd_pub.do')" style="background-color:#FFFFFF;" disabled="true">
									<option value=""></option>
									<html:options collection="xmdmList" property="xmdm"
										labelProperty="xmmc"></html:options>
								</html:select>
							</td>
						</tr>
						<tr valign="middle">
							<td align="right" nowrap>
								������Ŀ��
							</td>
							<td align="left">
								<html:text name="rs" property="kmmc" readonly="true"
									style="cursor:hand" />
							</td>
							<td align="right" nowrap>
								<font color="red">*</font>�����ֹʱ�䣺
							</td>
							<td align="left">
								<html:text name="rs" property="sqjzrq" readonly="true"
									onclick="return showCalendar('sqjzrq','y-mm-dd');"
									onblur="dateFormatChg(this)" style="cursor:hand " property="sqjzrq"/>
							</td>
						</tr>
						<tr valign="middle">
							<td align="right" nowrap>
								 ���֯���ţ�
							</td>
							<td align="left">
								<html:text name="rs" property="zzbmmc" readonly="true"
									style="cursor:hand" />
							</td>
							<td align="right" nowrap>
								<font color="red">*</font>���ʼʱ�䣺
							</td>
							<td align="left">
								<html:text name="rs" property="xmkssj" readonly="true"
									onclick="return showCalendar('xmkssj','y-mm-dd');"
									onblur="dateFormatChg(this)" style="cursor:hand " />
							</td>
						</tr>
						<tr valign="middle">
						<td align="right" nowrap>
						       ִ�е�λ�� 
							</td>
							<td align="left">
								<html:text name="rs" property="xsdw" readonly="true"
									style="cursor:hand"/>
							</td>
							<td align="right" nowrap>
								<font color="red">*</font>�����ʱ�䣺
							</td>
							<td align="left">
								<html:text name="rs" property="xmjssj" readonly="true"
									onclick="return showCalendar('xmjssj','y-mm-dd');"
									onblur="dateFormatChg(this)" style="cursor:hand " />
							</td>
						</tr>
						<tr align="left" valign="top">
							<td colspan="4">
								<font color="red">*</font>����ݣ�
								<br />
								<html:textarea name="rs" property="xmnr" rows="6"
									style="width:95% "></html:textarea>
							</td>
						</tr>
						<tr align="left" valign="top">
							<td colspan="4">
								<font color="red">*</font>����������Ҫ��
								<br />
								<html:textarea name="rs" property="sqztj" rows="6"
									style="width:95% "></html:textarea>
							</td>
						</tr>
						<tr align="left" valign="top">
							<td colspan="4">
								<font color="red">*</font>��ע��
								<br />
								<html:textarea name="rs" property="bz" rows="6"
									style="width:95% "></html:textarea>
							</td>
						</tr>
					</table>
					<div class="buttontool" id="btn" align="center">
						<button class="button2"
							onclick="if(sztzCheckXnNdXq('xn','nd','xq'))sztzDataDoSavePub('/xgxt/sztz_modiData.do?doType=save&realTable=sztz_xm_xxfbb&pkValue=','xn-nd-xq-xmdm');"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
					</div>
				</fieldset>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
