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
			var ssbh = document.getElementById("ssbh").value;
			var ssch = document.getElementById("ssch").value;
			var zsrq = document.getElementById("zsrq").value;
			var zsjzrq = document.getElementById("zsjzrq").value;
			if(ssbh!=""){
				if(ssch==""){
					alert("���ᴲ�Ų���Ϊ�գ�");
					return false;
				}
				if(ssch.length>1){
					alert("���ᴲ��ֻ����һλ�ַ�!");
					return false;
				}
				
			}
			if(zsrq > zsjzrq){
				alert("ס�޽�ֹ����������ס���ڣ����������ã���");
				return false;
			} 	
			//alert('123');
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
					<td align="right" width="15%">
						<font color="red">*</font>ѧ�ţ�
						<br />
					</td>
					<td>
						<logic:equal value="update" name="oper">
							<html:text name="rs" styleId="xh" property="xh" readonly="true"
								style="cursor:hand" />
						</logic:equal>
						<logic:equal value="add" name="oper">
							<html:text name="rs" property="xh" styleId="xh" onkeyup="value=value.replace(/[^\d]/g,'') "
							maxlength="20"/>
						</logic:equal>
					</td>
					<td align="right" width="15%">
						<font color="red">*</font>�꼶��
						<br />
					</td>
					<td align="left" width="30%">
						<html:select name="rs" property="nj" styleId="nj"
							style="width:90px"
							onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
						<br />
					</td>
						<td align="left" width="15%" rowspan="6">
							<img align="center" border="0" style="height:133px;width:100px;"
								src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg" id="pic">
							<br />
						</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>������
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="xm" styleId="xm" maxlength="16"/>
						<br />
					</td>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
						<br />
					</td>
					<td align="left">
						<html:select name="rs" property="xydm" styleId="xy"
							style="width:180px"
							onchange="initZyList();initBjList()">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
						<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						�Ա�
						<br />
					</td>
					<td align="left">
						<html:radio name="rs" property="xb" value="1">��</html:radio>
						<html:radio name="rs" property="xb" value="2">Ů</html:radio>
						<br />
					</td>
					<td align="right">
						<font color="red">*</font>רҵ��
						<br />
					</td>
					<td align="left">
						<html:select name="rs" property="zydm" style="width:180px"
							styleId="zy"
							onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
						<input type="hidden" name="zyV" value="<bean:write name="zydm" scope="request"/>"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						���壺
						<br />
					</td>
					<td align="left">
						<html:select name="rs" property="mz" styleId="mz"
							style="width:150px">
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
						<br />
					</td>
					<td align="right">
						<font color="red">*</font>�༶��
						<br />
					</td>
					<td align="left">
						<html:select name="rs" property="bjdm" style="width:180px"
							styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
						<input type="hidden" name="bjV" value="<bean:write name="bjdm" scope="request"/>"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						������ò��
						<br />
					</td>
					<td align="left">
						<html:select name="rs" property="zzmm" styleId="mz"
							style="width:150px">
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
						<br />
					</td>
					<td align="right">
						ѧ��״̬��
						<br />
					</td>
					<td align="left">
						<html:select name="rs" property="xjzt" style="width:150">	
						<html:option value=""></html:option>
						<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
<!--						<html:option value="��ѧ��">��ѧ��</html:option>-->
<!--						<html:option value="��ѧ��">��ѧ��</html:option>-->
						</html:select>
						<br />
					</td>

				</tr>
				<tr>
					<td align="right">
						����ƴ����
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="xmpy" maxlength="64"/>
						<br />
					</td>
					<td align="right">
						��ߣ�
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="sg" onkeyup="value=value.replace(/[^\d]/g,'') "
						maxlength="3"/>
						(cm)
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						��������
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="cym" maxlength="16"/>
						<br />
					</td>
					<td align="right">
						���أ�
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="tz" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>
						(kg)
						<br />
					</td>

				</tr>
				<tr>
					<td align="right">
						�������ڣ�
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="csrq"
							onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq" readonly="true"/>
						<br />
					</td>
					<td align="right">
						�س���
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="tc" maxlength="32"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						������ʽ��
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="pyfs" maxlength="32"/>
						<br />
					</td>
					<td align="right">
						������Σ�
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="pycc" maxlength="32"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						��ѧ��ʽ��
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="rxfs" maxlength="32"/>
						<br />
					</td>
					<td align="right">
						�������
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="kslb" maxlength="32"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						���֤�ţ�
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="sfzh" styleId="sfzh"/>
						<br />
					</td>
					<td align="right">
						�������䣺
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="dzyx" styleId="dzyx"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						��Դ������
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="syd" maxlength="25"/>
						<br />
					</td>
					<td align="right">
						��ϵ�绰��
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="lxdh" onkeyup="value=value.replace(/[^\d]/g,'') "
						maxlength="13"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						���᣺
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="jg" maxlength="10"/>
						<br />
					</td>
					<td align="right">
						�ֻ����룺
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="sjhm" onkeyup="value=value.replace(/[^\d]/g,'') "
						maxlength="11"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						ѧ�ƣ�
					</td>
					<td >
						<html:text name="rs" property="xz" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="1"/>��						
					</td>
					<td align="right">��ͥ��ַ��</td>
					<td colspan="2"><html:text name="rs" property="jtdz" maxlength="120"/> </td>
					
				</tr>
				<tr>
					<td align="right">
						�Ƿ��ѱ�ҵ��
					</td>
					<td>
						<html:select property="sfyby" styleId="sfyby" name="rs">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
					<td align="right">
						��ҵʱ�䣺
					</td>
					<td colspan="2">
						<html:text property="byny" name="rs" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr>
					<td align="right">
						�Ƿ��ѱ�ҵ��
					</td>
					<td>
						<html:select property="sfyby" styleId="sfyby" name="rs">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
					<td align="right">
						��ҵʱ�䣺
					</td>
					<td colspan="2">
						<html:text property="byny" name="rs" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr>
					<td align="right">
						�Ƿ��ҵ����
					</td>
					<td align="left" colspan="5">
						<html:select property="sfbys" name="rs" style="width:120px">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						�����ţ�
					</td>
					<td>
						<html:text property="kh" name="rs" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<td align="right">
						һ��ͨ�ţ�
					</td>
					<td colspan="2">
						<html:text property="ykth" name="rs" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr>
					<td align="right">
						���п��ţ�
					</td>
					<td colspan="5">
						<html:text property="yhkh" name="rs" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr height="22">
					<td align="right">
						����ţ�
					</td>
					<td align="left">
						<html:text property="ssbh" name="rs" styleId="ssbh" readonly="true"/>
					</td>
					<td align="right">
						���ᴲ�ţ�
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="ssch" readonly="true"/>
					</td>
				</tr>
				<tr height="22">
					<td align="right">
						ס�����ڣ�
					</td>
					<td align="left" valign="middle">
						<html:text name="rs" property="zsrq" styleId="zsrq" readonly="true"/>
					</td>
					<td align="right">
						ס�޽�ֹ���ڣ�
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="zsjzrq" styleId="zsjzrq" readonly="true"/>
					</td>
				</tr>
				<tr height="80">
					<td align="right">
						ס����Ϣ��ע��
					</td>
					<td align="left" colspan="4">
						<html:textarea name="rs" property="ssbz"
							style="width:100%;height:100%" readonly="true"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" width="100%">
			<logic:notPresent name="details">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" style="height:20px;width:80px" id="buttonSave"
					onclick="send();">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notPresent>
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
