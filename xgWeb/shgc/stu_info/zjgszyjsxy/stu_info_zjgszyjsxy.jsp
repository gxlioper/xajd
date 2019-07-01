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
			var ssbh = "";
			if($('ssbh'))	{
				ssbh = document.getElementById("ssbh").value;
			}
			var ssch = "";
			if($('ssch')){
				ssch = document.getElementById("ssch").value;
			}
			var zsrq = "";
			if($('zsrq')){
				zsrq = document.getElementById("zsrq").value;
			}
			var zsjzrq = "";
			if($('zsjzrq')){
				zsjzrq = document.getElementById("zsjzrq").value;
			}
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
	<body><br>		
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
						<br></td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>ѧ�ţ�
						<br />
					<br></td>
					<td>
						<logic:equal value="update" name="oper">
							<html:text name="rs" styleId="xh" property="xh" readonly="true"
								style="cursor:hand" />
						</logic:equal>
						<logic:equal value="add" name="oper">
							<html:text name="rs" property="xh" styleId="xh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</logic:equal>
					<br></td>
					<td align="right" width="15%">
						<font color="red">*</font>�꼶��
						<br />
					<br></td>
					<td align="left" width="30%">
						<html:select name="rs" property="nj" styleId="nj"
							style="width:90px"
							onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
						<br />
					<br></td>
						<td align="left" width="15%" rowspan="6">
							<img align="center" border="0" style="height:133px;width:100px;"
								src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg" id="pic">
						<br></td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>������
						<br />
					<br></td>
					<td align="left">
						<html:text name="rs" property="xm" styleId="xm" maxlength="16"/>
						<br />
					<br></td>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
						<br />
					<br></td>
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
					<br></td>
				</tr>
				<tr>
					<td align="right">
						�Ա�
						<br />
					<br></td>
					<td align="left">
						<html:radio name="rs" property="xb" value="1">��</html:radio>
						<html:radio name="rs" property="xb" value="2">Ů</html:radio>
						<br />
					<br></td>
					<td align="right">
						<font color="red">*</font>רҵ��
						<br />
					<br></td>
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
					<br></td>
				</tr>
				<tr>
					<td align="right">
						���壺
						<br />
					<br></td>
					<td align="left">
						<html:select name="rs" property="mz" styleId="mz"
							style="width:150px">
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
						<br />
					<br></td>
					<td align="right">
						<font color="red">*</font>�༶��
						<br />
					<br></td>
					<td align="left">
						<html:select name="rs" property="bjdm" style="width:180px"
							styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
						<input type="hidden" name="bjV" value="<bean:write name="bjdm" scope="request"/>"/>
						<br />
					<br></td>
				</tr>
				<tr>
					<td align="right">
						������ò��
						<br />
					<br></td>
					<td align="left">
						<html:select name="rs" property="zzmm" styleId="mz"
							style="width:150px">
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
						<br />
					<br></td>
					<td align="right">
						ѧ��״̬��
						<br />
					<br></td>
					<td align="left">
						<html:select name="rs" property="xjzt" style="width:150">	
						<html:option value=""></html:option>
						<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
<!--						<html:option value="��ѧ��">��ѧ��</html:option>-->
<!--						<html:option value="��ѧ��">��ѧ��</html:option>-->
						</html:select>
						<br />
					<br></td>
				</tr>
				
				<tr>
					<td align="right">
						ѧ�ƣ�
					<br></td>
					<td>
						<html:text property="xz" name="rs" maxlength="2"></html:text>
					<br></td>
					<td align="right">
						���֤�ţ�
					<br></td>
					<td colspan="5">
						<html:text property="sfzh" name="rs" maxlength="18"/>
					<br></td>
				</tr>				
				
				<tr>
					<td align="right">
						ԺУ��
					<br></td>
					<td>
						<html:select property="yxdm" name="rs">
						<html:option value=""></html:option>
						<html:options collection="yxdmList" property="yxdm" labelProperty="yxmc"/>
						</html:select>
					<br></td>
					<td align="right">
						ѧλ��
					<br></td>
					<td colspan="4">
						<html:select property="xw" name="rs">
						<html:option value=""></html:option>
						<html:options collection="xwdmList" property="xwdm" labelProperty="xwmc"/>
						</html:select>
					<br></td>					
				</tr>
				<tr>
					<td align="right">
						��Դ�أ�
					<br></td>
					<td>
						<html:select property="syd" name="rs">
						<html:option value=""></html:option>
						<html:options collection="sydList" property="syddm" labelProperty="sydmc"/>
						</html:select>
					<br></td>
					<td align="right">
						��ϵ��ַ��
					<br></td>
					<td colspan="4">
						<html:text property="lxdz" name="rs"/>
					<br></td>
				</tr>
				
				<tr>
					<td align="right">
						�Ƿ���ְ��
					<br></td>
					<td>
						<html:select property="sfzz" name="rs" styleId="sfzz">
						<html:option value=""></html:option>
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
						</html:select>
					<br></td>
					<td align="right">
						�Ƿ�ʦ����
					<br></td>
					<td  colspan="4">
						<html:select property="sfsf" name="rs" styleId="sfsf">
						<html:option value=""></html:option>
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
						</html:select>
					<br></td>
				</tr>
				
				<tr>
					<td align="right">
						�Ƿ������
					<br></td>
					<td>
						<html:select property="sfdl" name="rs" styleId="sfdl">
						<html:option value=""></html:option>
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
						</html:select>
					<br></td>
					<td align="right">
						�����ί�ࣺ
					<br></td>
					<td  colspan="4">
						<html:select property="dxhwp" name="rs" styleId="dxhwp">
						<html:option value=""></html:option>
						<html:option value="����">����</html:option>
						<html:option value="ί��">ί��</html:option>
						</html:select>
					<br></td>
				</tr>
				
				<tr>
					<td align="right">
						��ѧʱ�䣺
					<br></td>
					<td>
						<html:text property="rxrq" name="rs"/>
					<br></td>
					<td align="right">
						��ҵʱ�䣺
					<br></td>
					<td  colspan="4">
						<html:text property="bysj" name="rs"/>
					<br></td>					
				</tr>
				
				<tr>
					<td align="right">
						�����������֣�
					<br></td>
					<td>
						<html:select property="zxwyyzdm" name="rs">
						<html:option value=""></html:option>
						<html:options collection="wyyzList" property="wyyzdm" labelProperty="wyyzmc"/>
						</html:select>
					<br></td>
					<td align="right">
						��ҵ֤���ţ�
					<br></td>
					<td  colspan="4">
						<html:text property="zsbh" name="rs" styleId="zsbh" maxlength="20"/>
					<br></td>					
				</tr>
				
				<tr>
					<td align="right">
						����ȼ� ��
					<br></td>
					<td>
						<html:text property="wydj" name="rs" styleId="wydj" maxlength="16"/>
					<br></td>
					<td align="right">
						 ѧ����
					<br></td>
					<td  colspan="4">
						<html:select property="xldm" name="rs" styleId="xldm">
						<html:option value=""></html:option>
						<html:options collection="xlList" property="xldm" labelProperty="xlmc"/>
						</html:select>
					<br></td>					
				</tr>
				
				<tr>
					<td align="right">
						������ȼ� ��
					<br></td>
					<td>
						<html:text property="jsjdj" name="rs" styleId="jsjdj" maxlength="16"/>
					<br></td>
					<td align="right">
						 �������
					<br></td>
					<td  colspan="4">
						<html:select property="zslb" name="rs" styleId="zslb">
						<html:option value=""></html:option>
						<html:options collection="zslbList" property="zslbdm" labelProperty="zslbmc"/>
						</html:select>
					<br></td>					
				</tr>
				
				<tr>
					<td align="right">
						������ʽ ��
					<br></td>
					<td>
						<html:select property="pyfs" name="rs" styleId="pyfs">
						<html:option value=""></html:option>
						<html:options collection="pyfsList" property="pyfsdm" labelProperty="pyfsmc"/>
						</html:select>
					<br></td>
					<td align="right">
						 �ֻ����룺
					<br></td>
					<td  colspan="4">
						<html:text property="sjhm" name="rs" styleId="sjhm" maxlength="20"/>
					<br></td>					
				</tr>
				<tr>
					<td align="right">
						��ҵʱ�䣺
					<br></td>
					<td>
						<html:text property="byny" name="rs" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					<br></td>
					<td align="right">
						�Ƿ��ҵ����
					<br></td>
					<td align="left" colspan="2">
						<html:select property="sfbys" name="rs" style="width:120px">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					<br></td>
				</tr>
				<tr>
					<td align="right">
						QQ���� ��
					<br></td>
					<td>
						<html:text property="qqhm" name="rs" styleId="qqhm" maxlength="10"/>
					<br></td>
					<td align="right">
						 �������䣺
					<br></td>
					<td  colspan="4">
						<html:text property="dzyx" name="rs" styleId="dzyx" maxlength="64"/>
					<br></td>					
				</tr>
				
				<tr>
					<td align="right">
						�������룺
					<br></td>
					<td>
						<html:text property="yzbm" name="rs" styleId="yzbm" maxlength="20"/>
					<br></td>
					<td align="right">
						��ϵ�绰��
					<br></td>
					<td  colspan="4">
						<html:text property="lxdh" name="rs" styleId="lxdh" maxlength="60"/>
					<br></td>					
				</tr>
				
				<tr>
					<td align="right">
						���ţ�
					<br></td>
					<td>
						<html:text property="kh" name="rs" styleId="kh" maxlength="20"/>
					<br></td>
					<td align="right">
						���ְ��
					<br></td>
					<td colspan="4">
						<html:text property="shzw" name="rs" styleId="shzw" maxlength="60" style="width:100%"/>
					<br></td>		
				</tr>
				
				<tr>
					<td align="right">
						������ѵ��
					<br></td>
					<td colspan="6">
						<html:text property="jypx" name="rs" styleId="jypx" maxlength="150" style="width:100%"/>
					<br></td>		
				</tr>
				
				<tr>
					<td align="right">
						��Ŀʵ����
					<br></td>
					<td colspan="6">
						<html:text property="xmsj" name="rs" styleId="xmsj" maxlength="150" style="width:100%"/>
					<br></td>		
				</tr>
				
				<tr>
					<td align="right">
						�ʸ�֤�飺
					<br></td>
					<td colspan="6">
						<html:text property="zgzs" name="rs" styleId="zgzs" maxlength="150" style="width:100%"/>
					<br></td>		
				</tr>
				
				<tr>
					<td align="right">
						�������ܣ�
					<br></td>
					<td colspan="6">
						<html:text property="jljn" name="rs" styleId="jljn" maxlength="150" style="width:100%"/>
					<br></td>		
				</tr>
				
				<tr>
					<td align="right">
						��Դ��ע1��
					<br></td>
					<td colspan="6">
						<html:text property="sybz1" name="rs" styleId="sybz1" maxlength="150" style="width:100%"/>
					<br></td>		
				</tr>
				
				<tr>
					<td align="right">
						��Դ��ע2��
					<br></td>
					<td colspan="6">
						<html:text property="sybz2" name="rs" styleId="sybz2" maxlength="150" style="width:100%"/>
					<br></td>		
				</tr>
				
				<tr>
					<td align="right">
						��Դ��ע3��
					<br></td>
					<td colspan="6"><br><html:text property="sybz3" name="rs" styleId="sybz3" maxlength="150" style="width:100%"/>
					<br><br></td>		
				</tr>				
			</table>
			<div class="buttontool" id="btn" width="100%">
			<logic:notPresent name="details">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" style="height:20px;width:80px"
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
