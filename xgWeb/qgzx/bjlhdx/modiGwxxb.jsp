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
	<base target="_self" />
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/String.js"></script>
	<script language="javascript">	
	function saveGwxxInfo(url,obj){
	 var value = obj.split("-"); 
	 var xyrs = document.getElementById('xyrs').value;
	 var xyknsrs = document.getElementById('xyknsrs').value;
	 var knsbl = document.getElementById('knsbl').vlaue;
	 
	 for(var i=0; i<value.length; i++){
	 	if(document.getElementById(value[i]).value==""){
	 		alert("�뽫��\*�ŵ���Ŀ��д������");
	 		return false;
	 	}
	 }	 
	 if(parseInt(xyrs)<parseInt(xyknsrs)){
	 	alert('�������������ܶ���ʹ��������');
	 	return false;
	 }
	 
	 if (parseFloat(xyrs)*parseFloat(knsbl)/100>parseFloat(xyknsrs)) {
			alert("ʹ�������������ڱ�׼!");
			return false;
	}	 
	refreshForm(url);
	BatAlert.showTips('���ڲ����У����Ե�...'); 
	}
	
</script>
	<body>		
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/qgzxBjlhdx" method="post">
		<input type="hidden" name="sqsj" id="sqsj" value="${sqsj}"/>
		<input type="hidden" name="doType" id="doType" value="${doType}"/>
		<input type="hidden" name="knsbl" id="knsbl" value="${rs.knsbl}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ڹ���ѧ - ���˵�λ - ��λ��Ϣ�޸�
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
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr align="center">
							<td colspan="4">&nbsp;
								��λ��Ϣ
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>��λ���ƣ�
						</td>
						<td align="left">
						<logic:equal value="modi" name="doType">
							<html:text name="rs" property="gwdm" styleId="gwdm" readonly="true"/>
							<html:hidden property="gwdm" name="rs"/>
							<html:hidden property="gwsbsj" name="rs"/>
						</logic:equal>							
						</td>
						<td height="22" align="right">
							<font color="red">*</font>��λ���ƣ�
						</td>
						<td height="22" align="left">
<%--							<html:text name="rs" property="yrdwmc" styleId="yrdwmc" readonly="true"/>--%>
							${rs.yrdwmc}
							<html:hidden property="sqdw" name="rs"/>
						</td>
					</tr>
					<tr>
					<td align="right">
						 ��λ���ʣ�
					</td>
					<td>
						<html:select property="gwxz" name="rs" styleId="gwxz">
							<html:option value=""></html:option>
							<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
						</html:select>
					</td>
					<td height="22" align="right">
							ѧ�꣺
						</td>
						<td height="22" align="left">
<%--							<html:text name="rs" property="xn" style="width: 90px" readonly="true" />--%>
								${rs.xn}	
						</td>
					</tr>
					<tr>
						
						<td height="22" align="right">
							��ȣ�
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="nd" style="width: 90px"
								readonly="true" />							
						</td>
						<td height="22" align="right">
							ѧ�ڣ�
						</td>
						<td height="22">
<%--							<html:text name="rs" property="xueqi" style="width: 90px" readonly="true" />--%>
								${rs.xueqi}
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>����������
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="xyrs" styleId="xyrs" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>
						<td height="22" align="right">
							<font color="red">*</font>ʹ������������
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="xyknsrs" styleId="xyknsrs" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>
					</tr>		
					
					<tr>
					  <td height="22" align="right">����ʱ�䣺</td>
					  <td height="22" colspan="3" align="left">
					  	<html:select property="gzsj" name="rs" styleId="gzsj">
					  		<html:option value=""></html:option>
					  		<html:option value="��ʱ">��ʱ</html:option>
					  		<html:option value="��������ʱ��">����������</html:option>
					  	</html:select>
					  </td>
				  </tr>
					<tr>
					  <td height="22" align="right">�������ݣ�</td>
					  <td height="22" colspan="3" align="left">
					  	<html:textarea name="rs" property="gznr" style="width:100%" styleId="gznr"/>
					  </td>
				  </tr>	
				</table>
				<br />
				<logic:present name="writeAble">
					<logic:match value="yes" name="writeAble">
						<div id="button" align="center" class="buttontool">
							<button type="button" class="button2"
								onclick="saveGwxxInfo('qgzxBjlhdx.do?method=modiGwxx','gwxz-xyrs-xyknsrs')"
								style="width:80px" id="buttonSave">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="Close();return false;" style="width:80px">
								�� ��
							</button>
						</div>
					</logic:match>
				</logic:present>
			</logic:notEmpty>
			<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ���");		
				Close();	
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ�ܣ�");		
					Close();
				</script>
			</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
