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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(titName){
			var xh = document.getElementById('xh').value;
			var bz = document.getElementById('bz').value;
			var xmdm = document.getElementById('xmdm').value;
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ�գ�");
	          	return false;
			}
			if((xmdm == null) || (xmdm == "")){
				alert("У����ѧ����Ϊ�գ�");
	          	return false;
			}
			if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("��ע���ܴ���200���ַ���");
	          		 return false;
	       		 }
			}
			
			document.forms[0].action = "/xgxt/szxx_xnzxjsq.do?doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(titName){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/szxx_xnzxjsqb.do";
			document.forms[0].submit();
		}
		
		function showMon(){
			document.forms[0].action = "/xgxt/szxx_xnzxjsq.do?doType=find";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - У����ѧ������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="szxx_xnzxjsq.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url"
				value="/szxx_xnzxjsq.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">
			<input type="hidden" id="xmmc" name="xmmc"
				value="<bean:write name="rs" property="xmmc"/>">
			<input type="hidden" id="zxclmc" name="zxclmc"
				value="<bean:write name="rs" property="zxclmc"/>">
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
					<table class="tbstyle" width="90%">
						<tr>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<td align="center" width="16%">
									<font color="red">*</font>ѧ��
								</td>
								<td align="left" width="34%">
									<html:text name="rs" property="xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)"
										readonly="true" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<td align="center" width="16%">
									<font color="red">*</font>ѧ��
								</td>
								<td align="left" width="34%">
									<input type="text" id="xh" name="xh"
										style="width:100%;heigh:100%"
										value="<bean:write name='rs' property="xh" />" readonly="true">
								</td>
							</logic:equal>
							<td width="16%">
								<div align="center">
									����
								</div>
							</td>
							<td width="34%">
								<input type="text" readonly="readonly" id="xm" name="xm"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xm"/>"
									readonly="readonly">
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�Ա�
								</div>
							</td>
							<td>
								<input type="text" id="xb" name="xb" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xb"/>">
							</td>
							<td>
								<div align="center">
									�꼶
								</div>
							</td>
							<td>
								<input type="text" id="nj" name="nj" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="nj"/>">
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td>
								<input type="text" id="xymc" readonly="readonly" name="xymc"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xymc"/>">
							</td>
							<td>
								<div align="center">
									רҵ
								</div>
							</td>
							<td>
								<input type="text" id="zymc" name="zymc" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="zymc"/>">
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�༶
								</div>
							</td>
							<td>
								<input type="text" id="bjmc" readonly="readonly" name="bjmc"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="bjmc"/>">
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<input type="text" id="kh" name="kh" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="kh"/>">
							</td>
						</tr>
						<tr>
							<td>
								<div align="center" width="16%">
									<font color="red">*</font>У����ѧ����Ŀ
								</div>
							</td>
							<td width="34%">
								<html:select name="rs" property="xmdm" style="width:80%" onchange="showMon();">
										<html:option value="">
										-------��ѡ��-------
										</html:option>
										<html:options collection="xnzxjList" property="xmdm"
											labelProperty="xmmc" />
									</html:select>
							</td>
							<td width="16%">
								<div align="center">
									У����ѧ����
								</div>
							</td>
							<td width="34%">
								<input type="text" id="zxjje" name="zxjje" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="zxjje"/>">
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									֤������
								</div>
							</td>
							<td>
								<html:select name="rs" property="zmcldm" style="width:80%">
										<html:option value="">
										-------��ѡ��------
										</html:option>
										<html:options collection="zmclList" property="zmcldm"
											labelProperty="zmclmc" />
									</html:select>
							</td>
							<td colspan="2">
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ע
								</div>
							</td>
							<td colspan="3">
								<html:textarea name="rs" property="bz" rows='5' style="width:100%" onblur="yzdx(this,'bz')"/>
								<input type="hidden" id="bz" name="bz" value="">
							</td>
						</tr>
					</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2"
					onClick="yz(document.getElementById('titName').value);">
					�ύ����
				</button>
				<button class="button2"
					onClick="toPrintOut(document.getElementById('titName').value);">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
