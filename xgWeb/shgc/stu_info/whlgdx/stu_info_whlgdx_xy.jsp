<%@ page language="java" pageEncoding="GBK"%>

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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="skin1/style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>	
	<script type="text/javascript" src="js/function.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript">
		function Close() {
			var ua = navigator.userAgent;
			var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false;
			if (ie) {
				var IEversion = parseFloat(ua.substring(ua.indexOf("MSIE ") + 5, ua.indexOf(";", ua.indexOf("MSIE "))));
				if (IEversion < 5.5) {
					var str = "<object id=noTipClose classid=\"clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11\">";
					str += "<param name=\"Command\" value=\"Close\"></object>";
					document.body.insertAdjacentHTML("beforeEnd", str);
					document.all.noTipClose.Click();
				} else {
					window.opener = null;
					window.close();
				}
			} else {
				window.close();
			}
		}
		
		function send(){	
			stuinfoSave("stu_info_add.do?method=stuInfoSave&oper=");	
		}
	</script>
	<body>		
		<html:form action="/stu_info_add" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã�������Ϣ-ѧ����Ϣά��
			</div>
		</div>
			<input type="hidden" value="<bean:write name="oper"/>" id="oper" />			
			<input type="hidden" name="url" id="url" value="/sjcz/stu_info_modify.jsp">
			<input type="hidden" name="variable" id="variable" value="ydinfo">
			<input type="hidden" name="redirect" id="redirect" value="true">
			<input type="hidden" name="notnull" id="notnull" value="xh-xm-bjdm-zydm-xydm-nj">
			
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="5" align="center">
							<center>
								ѧ��������Ϣ
							</center>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td>
						<logic:equal value="update" name="oper">
							<html:text name="rs" styleId="xh" property="xh" readonly="true"
								style="cursor:hand" />
						</logic:equal>
						<logic:equal value="add" name="oper">
							<html:text name="rs" property="xh" styleId="xh" 
							onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20"/>
						</logic:equal>
					</td>
					<td align="right" >
						<font color="red">*</font>�꼶��
					</td>
					<td align="left">
						<html:select name="rs" property="nj" styleId="nj"
							style="width:90px" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
						<html:hidden property="nj" name="rs"/>
					</td>
					<td align="left" rowspan="6">
						<img border="0" style="height:133px;width:100px;"
							src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>������
					</td>
					<td align="left">
						<html:text name="rs" property="xm" styleId="xm" disabled="true"/>
						<html:hidden property="xm" name="rs"/>
					</td>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<html:select name="rs" property="xydm" styleId="xy" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
						<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
						<html:hidden property="xydm" name="rs"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						�������
					</td>
					<td>
						<html:text name="rs" property="zslb" disabled="true"/>	
						<html:hidden property="zslb" name="rs"/>					
					</td>
					<td align="right">
						<font color="red">*</font>רҵ��
					</td>
					<td align="left">
						<html:select name="rs" property="zydm" styleId="zy" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
						<input type="hidden" name="zyV" value="<bean:write name="zydm" scope="request"/>"/>
						<html:hidden property="zydm" name="rs"/>
					</td>
				</tr>
				<tr>
				<td align="right">
						ѧ�ƣ�
					</td>
					<td>
						<html:text name="rs" property="xz" disabled="true"/>��		
						<html:hidden property="xz" name="rs"/>				
					</td>
					<td align="right">
						<font color="red">*</font>�༶��
						<br />
					</td>
					<td align="left">
						<html:select name="rs" property="bjdm" styleId="bj" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
						<input type="hidden" name="bjV" value="<bean:write name="bjdm" scope="request"/>"/>
						<html:hidden property="bjdm" name="rs"/>
					</td>
				</tr>
				<tr>
				<td align="right">
						һ��ͨ�ţ�
					</td>
					<td colspan="3">
						<html:text name="rs" property="kh" disabled="true" style="width:100%"/>	
						<html:hidden property="kh" name="rs"/>					
					</td>
				</tr>
				<tr>
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<html:radio name="rs" property="xb" value="1" disabled="true">��</html:radio>
						<html:radio name="rs" property="xb" value="2" disabled="true">Ů</html:radio>
						<html:hidden name="rs" property="xb"/>
					</td>
					<td align="right">
						ѧ��״̬��
					</td>
					<td align="left">
						<html:select name="rs" property="xjzt" style="width:150" disabled="true">	
						<html:option value=""></html:option>
						<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
<!--							<html:option value="��ѧ��">��ѧ��</html:option>-->
<!--							<html:option value="��ѧ��">��ѧ��</html:option>-->
						</html:select>
						<html:hidden property="xjzt" name="rs"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						���壺
					</td>
					<td align="left">
						<html:select name="rs" property="mz" styleId="mz"
							style="width:150px" disabled="true">
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
						<html:hidden property="mz" name="rs"/>
					</td>
					<td align="right">
						������
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="gj" styleId="gj" disabled="true"/>
						<html:hidden property="gj" name="rs"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						���֤�ţ�
					</td>
					<td align="left">
						<html:text name="rs" property="sfzh" styleId="sfzh" disabled="true"/>
						<html:hidden property="sfzh" name="rs"/>
					</td>
					<td align="right">
						��ߣ�
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="sg" disabled="true"/>
						(cm)
						<html:hidden property="sg" name="rs"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						���᣺
					</td>
					<td align="left">
						<html:text name="rs" property="jg" disabled="true"/>
						<html:hidden property="jg" name="rs"/>
					</td>
					<td align="right">
						���أ�
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="tz" disabled="true"/>
						(kg)
						<html:hidden property="tz" name="rs"/>
					</td>
				</tr>
				<tr>					
					<td align="right">
						�س���
					</td>
					<td align="left">
						<html:text name="rs" property="tc" disabled="true"/>
						<html:hidden property="tc" name="rs"/>
					</td>
					<td align="right">
						������ò��
					</td>
					<td align="left" colspan="2">
						<html:select name="rs" property="zzmm" styleId="mz"
							style="width:150px">
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						�Ƿ��ѻ飺
					</td>
					<td align="left">
						<html:select property="sfjh" name="rs">
						<html:option value=""></html:option>
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
						</html:select>
					</td>
					<td align="right">
						�˳����䣺
					</td>
					<td align="left" colspan="2">
						<html:text property="ccqj" name="rs"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						��ϵ�绰��
					</td>
					<td align="left">
						<html:text name="rs" property="lxdh"/>
					</td>
					<td align="right">
						�ֻ����룺
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="sjhm"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						QQ�ţ�
					</td>
					<td align="left">
						<html:text property="qqhm" name="rs" disabled="true"/>
						<html:hidden name="rs" property="qqhm"/>
					</td>
					<td align="right">
						�������䣺
					</td>
					<td align="left" colspan="2">
						<html:text property="dzyx" name="rs" disabled="true"/>
						<html:hidden name="rs" property="dzyx"/>
					</td>
				</tr>	
			</table>
			<div class="buttontool" id="btn" width="100%">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" style="height:20px;width:80px"
					onclick="send();">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" style="height:20px;width:80px"
					onclick="Close();return false;">
					�� ��
				</button>
			</div>
			<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("�����ɹ���");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();						
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("����ʧ��!");
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
