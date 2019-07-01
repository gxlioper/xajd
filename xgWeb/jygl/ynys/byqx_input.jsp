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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	
	
	function savebyqx(){
	 var xsxh = document.getElementById("xsxh").value;
	 var byqx = document.getElementById("byqx").value;
	 var lxdz = document.getElementById("lxdz").value;
	 var lxdh = document.getElementById("lxdh").value;
	 var yzbm = document.getElementById("yzbm").value;
	 var yddh = document.getElementById("yddh").value;
	 var email = document.getElementById("email").value;
	 
	 
	 if(xsxh==""){
	 alert("ѧ�ű�����д��")
	 return false;
	 }
	 if(byqx==""){
	 alert("��ҵȥ�������д��");
	 return false;
	 }
	 if(lxdz==""){
	 alert("��ϵ��ַ������д��");
	 return false;
	 }
	 if(yzbm==""){
	 alert("�������������д��");
	 return false;
	 }
	 if(!isNumber(yzbm)){
	 alert("��������ӦΪ���֣�")
	 return false;
	 }
	 if(yzbm!=""&&yzbm.length!=6){
	 alert("�������볤�Ȳ���Ҫ��");
	 return false;
	 }
	 
	 
	 if(lxdh!=""&&!isNumber(lxdh)){
	 alert("��ϵ�绰ӦΪ���֣�")
	 return false;
	 }
	 if(lxdh!=""&&lxdh.length<7){
	 alert("��ϵ�绰���Ȳ���Ҫ��");
	 return false;
	 }
	 if(yddh!=""&&!isNumber(yddh)){
	 alert("�ƶ��绰ӦΪ���֣�")
	 return false;
	 }
	 if(yddh!=""&&yddh.length!=11){
	 alert("�ֻ����볤�Ȳ���Ҫ��");
	 return false;
	 }
	 
	 
	 
	 if(lxdh==""&&yddh==""){
	 alert("��������дһ����ϵ�绰��");
	 return false;
	 }
		 		document.forms[0].action = "/xgxt/savebyqx.do?doType=save";
		 		document.forms[0].submit();
	 }
	
	function returntobegin(){
	            document.forms[0].action = "/xgxt/savebyqx.do";
	            document.forms[0].submit();
	}
	
	
	
	
	function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    }  
	</script>
	<body>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã���ҵ���� - ��ҵЭ�鷽�� - ��ҵȥ��¼��
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>


				<html:hidden name="rs" property="nd" />
				<html:hidden name="rs" property="xslb" />

				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:25px">
							<td colspan="4" align="center">
								<b>��ҵȥ��¼��</b>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="4">
								��ҵ���:
								<html:text property="bynd" name="rs" readonly="true"
									style="width:35px" />
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
								<html:text property="xymc" name="rs" readonly="true"
									style="width:130px" />
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right" width="15%">
								<font color="red">*</font>ѧ��:
							</td>
							<td align="left" width="35%">
								<html:text name='rs' property="xsxh" styleId="xsxh"
									style="width:210px" readonly="true" />
								<button onclick="showTopWin('/xgxt/byqxTurnInfo.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right" width="15%">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" width="35%">
								<html:text property="xsxh" name="rs" styleId="xsxh"
									readonly="true" style="width:210px" />
							</td>
						</logic:equal>
						<td align="right" width="15%">
							������
						</td>
						<td align="left" width="35%">
							<html:text name="rs" property="name" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<html:text name="rs" property="xb" readonly="true"
								style="width:210px" />
						</td>
						<td align="right">
							��Դ������
						</td>
						<td align="left">
							<html:text name="rs" property="sydq" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>��ҵȥ��
						</td>
						<td align="left">
							<html:select name="rs" property="byqx" styleId="byqx"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="byqxList" property="byqx"
									labelProperty="byqx" />
							</html:select>
						</td>
						<td align="right">
							<font color="red">*</font>��ϵ��ַ��
						</td>
						<td align="left">
							<html:text name="rs" property="lxdz" style="width:210px"
								maxlength="40" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ϵ�绰��
						</td>
						<td align="left">
							<html:text name="rs" property="lxdh" style="width:210px"
								maxlength="12" />
						</td>
						<td align="right">
							<font color="red">*</font>�������룺
						</td>
						<td align="left">
							<html:text name="rs" property="yzbm" style="width:210px"
								maxlength="6" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�ƶ��绰��
						</td>
						<td align="left">
							<html:text name="rs" property="yddh" style="width:210px"
								maxlength="11" />
						</td>
						<td align="right">
							�������䣺
						</td>
						<td align="left">
							<html:text name="rs" property="email" style="width:210px"
								maxlength="20" />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="savebyqx()" style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" type="reset" onclick="returntobegin()"
						style="width:80px">
						�� ��
					</button>
				</div>
			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
    alert("�ύ�ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
    alert("�ύʧ�ܣ������Ƿ��ظ��ύ��");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
