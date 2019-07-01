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
	function jyglByqxUpdate(){
	var byqx = document.getElementById("byqx").value;
	 var lxdz = document.getElementById("lxdz").value;
	 var lxdh = document.getElementById("lxdh").value;
	 var yzbm = document.getElementById("yzbm").value;
	 var yddh = document.getElementById("yddh").value;
	 var email = document.getElementById("email").value;
	 
	 
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
	   
		 		document.forms[0].action = "/xgxt/jyglByqxUpdate.do?doType=update&act=update";
		 		document.forms[0].submit();
    }
   
	
	function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    }  
    
   
	</script>
	<body>
		<html:form action="/data_search" method="post">
			<fieldset>
				<legend>
					��ҵȥ����Ϣ
				</legend>
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr>
							<td align="left" colspan="4">
								&nbsp;&nbsp;��ҵ���:
								<html:text property="bynd" name="rs" style="width:35px"
									readonly="true" />
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
								<html:text property="xymc" name="rs" style="width:130px"
									readonly="true" />
								&nbsp;&nbsp;�ύʱ�䣺
								<html:text name="rs" property="tjsj" style="width:140px"
									readonly="true" />
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right" width="17%">
							ѧ�ţ�
						</td>
						<td align="left" width="33%">
							<html:text name="rs" property="xsxh" readonly="true"
								style="width=100%" />
						</td>
						<td align="right" width="15%">
							������
						</td>
						<td align="left" width="35%">
							<bean:write name="rs" property="name" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<bean:write name="rs" property="xb" />
						<td align="right">
							���֤�ţ�
						</td>
						<td align="left">
							<bean:write name="rs" property="id" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��Դ������
						</td>
						<td align="left">
							<bean:write name="rs" property="sydq" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>��ҵȥ��
						</td>
						<td align="left">
							<html:select name="rs" property="byqx" styleId="byqx"
								style="width=100%">
								<html:options collection="byqxList" property="byqx"
									labelProperty="byqx" />
							</html:select>
						</td>
						<td align="right">
							<font color="red">*</font>��ϵ��ַ��
						</td>
						<td align="left">
							<html:text name="rs" property="lxdz" style="width=100%" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>��ϵ�绰��
						</td>
						<td align="left">
							<html:text name="rs" property="lxdh" style="width=100%" />
						</td>
						<td align="right">
							<font color="red">*</font>�������룺
						</td>
						<td align="left">
							<html:text name="rs" property="yzbm" style="width=100%" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>�ƶ��绰��
						</td>
						<td align="left">
							<html:text name="rs" property="yddh" style="width=100%" />
						</td>
						<td align="right">
							<font color="red">*</font>�������䣺
						</td>
						<td align="left">
							<html:text name="rs" property="email" style="width=100%" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�����ʼĵ�ַ��
						</td>
						<td align="left">
							<html:text name="rs" property="dayjdz" style="width:100%"
								maxlength="40" />
						</td>
						<td align="right">
							������Ҫ���ţ�
						</td>
						<td align="left">
							<html:text name="rs" property="dajydh" style="width:100%"
								maxlength="20" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�ʵ���ʴ����ڣ�
						</td>
						<td align="left">
							<html:text name="rs" property="ydjycrq" style="width:100%"
								maxlength="11" />
						</td>
						<td align="right">
							����֤�ţ�
						</td>
						<td align="left">
							<html:text name="rs" property="bdzh" style="width:100%"
								maxlength="20" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							Э�����ţ�
						</td>
						<td align="left">
							<html:text name="rs" property="xysbh" style="width:100%"
								maxlength="11" />
						</td>
						<td align="right">	
						</td>
						<td align="left">			
						</td>
					</tr>
					
					
					<tr style="height:22px">
						<td align="right">
							ѧУ��˽����
						</td>
						<td align="left">
							<html:text name="rs" property="xxsh" style="width=100%"
								readonly="true" />
						</td>
						<td align="right">
							���ʱ�䣺
						</td>
						<td align="left">
							<html:text name="rs" property="shsj" style="width=100%"
								readonly="true" />
						</td>
					</tr>
					<tr style="height:55px">
						<td align="right">
							�޸������
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="xgyj" rows="3"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							����ˣ�
						</td>
						<td align="left">
							<html:text name="rs" property="shperson" style="width=100%"
								readonly="true" />
						</td>
						<td align="right">
							
						</td>
						<td align="center">
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="jyglByqxUpdate()">
						�� �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">
						�� �� �� ��
					</button>
				</div>
			</fieldset>
		</html:form>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("�޸ĳɹ�!");
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("�����ͨ����������Ȩ�����޸ģ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
