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
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	function ysbb(){
		var bbyy = document.getElementById("BBYY").value;
		if(bbyy == "��ʧ"){
			document.getElementById("ys").style.display='inline';
			document.getElementById("gh").style.display='none';
		}else if(bbyy == "����"){
			document.getElementById("ys").style.display='none';
			document.getElementById("gh").style.display='inline';
		}else{
			document.getElementById("ys").style.display='none';
			document.getElementById("gh").style.display='none';
		}
	}
	</script>
	<body onload="ysbb();">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/jyxysbbsq" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã���ҵ���� - ��ҵЭ�鷽�� - ��ҵЭ���鲹��鿴
				</div>
			</div>
				<table width="100%" id="rsT" class="tbstyle">
					<tr>
						<td align="right" nowrap="nowrap">
							ѧ�ţ�
						</td>
						<td>
						<bean:write name="rs" property="XH"/>
						</td>
						<td align="right" nowrap="nowrap">
							������
						</td>
						<td>
							<bean:write name="rs" property="XM" />
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap">
							<div align="right">
								��ҵ��ȣ�
							</div>
						</td>
						<td>
							<bean:write name="rs" property="BYNY" />
						</td>
						<td align="right" nowrap="nowrap">
							�꼶��
						</td>
						<td>
							<bean:write name="rs" property="NJ" />
						</td>
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td>
							<bean:write name="rs" property="XB" />
						</td>
						<td align="right">
							ѧ�ƣ�
						</td>
						<td>
							<bean:write name="rs" property="XZ" />
						</td>							
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td>
							<bean:write name="rs" property="XYMC" />
						</td>
						<td align="right">
							רҵ��
						</td>
						<td>
							<bean:write name="rs" property="ZYMC" />
						</td>				
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td>
							<bean:write name="rs" property="BJMC" />
						</td>
						<td>
							<div align="right">
								<font color="red">*</font>����ԭ��
							</div>
						</td>
						<td>
							<html:select name="rs" property="BBYY" styleId="bbyy" onchange="ysbb();" style="display: none">
								<html:option value=""></html:option>
								<html:option value="��ʧ">��ʧ</html:option>
								<html:option value="����">����</html:option>
							</html:select>
							<bean:write name="rs" property="BBYY"/>
						</td>	
					</tr>
					<tr>
						<td align="right">
							����˵��
						</td>
						<td colspan="3">
							<bean:write  name="rs" property="BBSM"/>
						</td>
					</tr>
					<tr id="ys" style="display: none">
						<td colspan="4" style="width: 800px;">
							<font color="red">
							��ʾ������ʧ����ԭ�򣺸��ݽ���ʡ�������Ĺ涨������ʧ�ĵ��صǱ��������ǹ������еı�ֽ��
							��������ʧ���¡�����ҵ����ѯԭЭ�����ţ����м����Ϲ������еı����Ͽ���ԭЭ����������
							�ϵ���ʧ���£��迯����������ҵԺУ����ҵЭ�����ţ�������������ʧ��ҵ����ҵЭ���飬
							����ѧУ ������������������������ϡ���
							</font>
						</td>
					</tr>
					<tr id="gh" style="display: none">
						<td colspan="4" style="width: 800px;">
							<font color="red">
							��ʾ����ԭ��λ��Ч�˺������֣�һ��ֱ���ڡ�Э���顷��ע������Э���ѽ����������Э��ר���£�
							���ǵ�����������Ч��Լ֤��������Э��ר���£��������ѿ����ı���֤��ע����ͬ����ɡ���
							���ɸõ�λ���»��ɱ�ҵ��Ŀǰ�����йܵ�λ�������¾ֻ��˲��г������¡���λ�ѽ���򵹱յģ�
							��ز��ţ���ԭ��λ���ҿ����˲��г�����֤���ɴ�����Ч�˺�����
							</font>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="Close();return false;">
						�ر�
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
    				alert("�ύʧ�ܣ�");
   				 </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="iszc">
				<logic:equal name="iszc" value="iszc">
					<script>
					    alert("���û��Ѿ������ˣ���Ҫ�ظ��ύͬһ��ѧ��");
					    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
