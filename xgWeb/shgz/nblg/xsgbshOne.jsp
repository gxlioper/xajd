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
		<html:form action="/xsgbForNblg" method="post">
			<input type="hidden" id="method" name="method"
				value="xsgbsqOne" />
			<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/xsgbForNblg.do?method=xsgbsqOne" />
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:empty name="rs">
				<div align="center"><font color="red">ֻ��ѧ�����ܷ��ʱ�ҳ��</font></div>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						ѧ����֯�ɲ��������
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" >
									ѡ��
								</button>
							</td>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" readonly = "true" />
							</td>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj"  readonly = "true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" readonly = "true"/>
							</td>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" readonly = "true"/>
							</td>
							<td align="right">
								��֯��������:
							</td>
							<td align="left">
								<html:select name = "rs" property="bmdm" style="width:180px" styleId="bmdm" onchange="refreshForm('/xgxt/xsgbForNblg.do')"> 
	          					<html:option value=""></html:option> 
	          					<html:options collection="bmList" property="bmdm" labelProperty="bmmc" /> 
	          					</html:select> 
							</td>
						</tr>
						<tr>
							<td align="right">
								��֯����:
							</td>
							<td align="left"> 
								<html:select name = "rs" property="zzdm" style="width:180px" styleId="zzdm" onchange="refreshForm('/xgxt/xsgbForNblg.do')"> 
	          					<html:option value=""></html:option> 
	          					<html:options collection="xszzList" property="zzdm" labelProperty="zzmc" /> 
	          					</html:select> 
							</td>
							<td align="right">
								������ѧ���ɲ�:
							</td>
							<td align="left">
								<html:select name = "rs" property="bgbdm" style="width:180px" styleId="bgbdm"> 
	          					<html:option value=""></html:option> 
	          					<html:options collection="xsgbList" property="bgbdm" labelProperty="bgbmc" /> 
	          					</html:select> 
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								�Ѿ�����ְ��
							</td>
							<td colspan="3">
								<logic:iterate id="v" name="yrxsgbList" offset="0">
											&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="v" /></br>
								</logic:iterate>
							</td>
						</tr>
						<tr>
							<td align="right">
								��ְ��ʼʱ�䣺
							</td>
							<td align="left">
							<html:text name='rs' property="rzkssj" styleId="rzkssj" style="cursor:hand;"
								onclick="return showCalendar('rzkssj','y��mm��dd��');" />
							</td>
							<td align="right">
								��ְ����ʱ��:
							</td>
							<td align="left">
								<html:text name='rs' property="rzjssj" styleId="rzjssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('rzjssj','y��mm��dd��');" />
							</td>
						</tr>
						<tr>
							<td align="right">
								���״̬��
							</td>
							<td align="left">
							<html:select name = "rs" property="shzt" style="width:90px" styleId="shzt"> 
									<html:option value=""></html:option>
	          						<html:option value="δ���">δ���</html:option>
	          						<html:option value="ͨ��">ͨ��</html:option>
	          						<html:option value="��ͨ��">��ͨ��</html:option>
	          				</html:select>
							</td>
							<td align="right">
								���˵ȼ�:
							</td>
							<td align="left">
								<html:select name = "rs" property="khdj" style="width:50px" styleId="shzt"> 
									<html:option value=""></html:option>
	          						<html:option value="��">��</html:option>
	          						<html:option value="��">��</html:option>
	          						<html:option value="��">��</html:option>
	          						<html:option value="�ϸ�">�ϸ�</html:option>
	          						<html:option value="���ϸ�">���ϸ�</html:option>
	          				</html:select>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button class="button2"
						onclick="szsxDataDoSave('xsgbForNblg.do?method=saveXsgbshOne','xh-bgbdm');"
						style="width:80px" id="buttonSave">
						������
					</button>
				</div>
			</logic:notEmpty>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
   					alert("�ύ�ɹ���");
    				dialogArgumentsQueryChick();
						Close();
    				</script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
   					 alert("����ʧ�ܣ�");
   				 </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
