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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getOtherData.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var userType = document.getElementById('userType').value;
			var xxsh = document.getElementById('xxsh').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			if(("δ���" != xxsh ) && (userType != "admin")){
				alert("<bean:message key="lable.xsgzyxpzxy" />����ˣ��������޸���˽��!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("ѧУ���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
			 refreshForm('/xgxt/bjlh_xszz.do?method=gjzxjshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function zxjdj(){
			var zxjdm = document.getElementById('zxjdm').value;
			
			if((zxjdm == null) || (zxjdm == "")){
				document.getElementById('zxjmc').value = "";
    			document.getElementById('zxjje').value = "";
			}
			var zxjmc = "";
			var zxjje = "";
			
			dwr.engine.setAsync(false);
			getOtherData.getBjlhdxZxjxx(zxjdm,function(data){
       			if(data!=null){
       				zxjmc=data[0];
       				zxjje=data[1];
      			}
   			 });
    		dwr.engine.setAsync(true);
    		document.getElementById('zxjmc').value = zxjmc;
    		document.getElementById('zxjje').value = zxjje;
		}
		</script>
		<html:form action="bjlh_xszz.do?method=gjzxjshOne" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ������ѧ����� - �������
				</div>
			</div>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="6" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td colspan="2">
						<div align="center">
							��ѧ��
						</div>
					</td>
					<td colspan="2">
						<html:select name="rs" property="zxjdm" onchange="zxjdj();">
							<html:options collection="gjzxjList" property="zxjdm"
								labelProperty="zxjmc" />
						</html:select>
						<input type="hidden" id="zxjmc" name="zxjmc"
							value="<bean:write name='rs' property="zxjmc" />">
					</td>
					<td width="16%">
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td width="34%">
						<input type="text" id="zxjje" name="zxjje"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="zxjje" />"
								readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" readonly="true">
						<input type="hidden" id="xn" name="xn"
							value="<bean:write name='rs' property="xn" />" readonly="true">
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb" />
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="mzmc" />
					</td>
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�꼶
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="nj" />
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							רҵ����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zymc" />
					</td>
					<td>
						<div align="center">
							�༶����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="csrq" />
					</td>
					<td>
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rxrq" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xz" />
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="kh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�������<br />����
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="chhjjl" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jthk" />
					</td>
					<td>
						<div align="center">
							��ͥ�˿���
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtrks" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ�˾�������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtyrjsr" />
					</td>
					<td>
						<div align="center">
							��ͥ��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtyzsr" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ������Դ
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtsrly" />
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="jtzz" />
					</td>
				</tr>
				<tr>
					<td rowspan="6" width="4%">
						<div align="center">
							��
							<br />
							ͥ
							<br />
							��
							<br />
							Ա
							<br />
							��
							<br />
							��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="17%">
						<div align="center">
							����
						</div>
					</td>
					<td width="17%">
						<div align="center">
							�뱾�˹�ϵ
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							ѧϰ������λ
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdw" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdw" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdw" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdw" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdw" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="sqly" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sqsj" />
					</td>
					<logic:equal name="userType" value="xy">
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���
							</div>
						</td>
						<td>
							<html:select name="rs" property="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
						</td>
					</logic:equal>
					<logic:equal name="userType" value="admin">
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xysh" />
							<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name='rs' property="xysh" />" />
						</td>
					</logic:equal>
				</tr>
				<logic:equal name="userType" value="admin">
					<tr>
						<td colspan="2">
							<div align="center">
								ѧУ���
							</div>
						</td>
						<td colspan="2">
							<html:select name="rs" property="xxsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="2">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="xy">
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />������
							</div>
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="xyshyj" rows='5'
								style="width:100%" onblur="yzdx(this,'xyshyj')" />
							<input type="hidden" id="xyshyj" name="xyshyj" value="">
							<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name='rs' property="xxshyj" />" />
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />������
							</div>
						</td>
						<td colspan="4">
							<bean:write name='rs' property="xyshyj" />
							<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name='rs' property="xyshyj" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								ѧУ������
							</div>
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="xxshyj" rows='5'
								style="width:100%" onblur="yzdx(this,'xxshyj')" />
							<input type="hidden" id="xxshyj" name="xxshyj" value="">
						</td>
					</tr>
				</logic:equal>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
