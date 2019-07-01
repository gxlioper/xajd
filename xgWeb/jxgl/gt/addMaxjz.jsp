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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src='/xgxt/dwr/interface/jxglNblg.js'></script>
	<script language="javascript">
	function yz(){
			var bzmc = document.getElementById('bzmc').value;
			var jgbh = document.getElementById("jgbh").value;
			var jsdm = document.getElementById("jsdm").value;
			var bz = document.getElementById('bz').value;	
			var xn = document.getElementById('xn').value;	
			if((bzmc == null) || (bzmc == "")){
				alert("�������Ʋ���Ϊ��!");
				return false;
			}
			if(jgbh == "" || jsdm == ""){
				alert("���ӽ̹������ָ��Ա����Ϊ�գ���ȷ�ϣ���");
				return false;
			}
			if((xn == null) || (xn == "")){
				alert("��ѵ����Ϊ��!");
				return false;
			}
			if($("xxdm").value == "13022"){
				var jxfz = document.getElementById('jxfz').value;
				if(jxfz == ""){
					alert("��ѵ��װ����Ϊ�գ���ȷ�ϣ���");
					return false;
				}
			}
			if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("��ע���ܳ���200���ַ���");
	          		 return false;
	       		 }
	       	}
	       	showTips('���������У���ȴ�......');
			document.forms[0].action = "/xgxt/jxglgt.do?method=addMaxjz&act=save";
			document.forms[0].submit();
		}
	function yzdx(obj,str){
		document.getElementById(str).value = obj.value;
	}
</script>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="jxgljz_nblg.do?method=addYjjz" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã�
						<bean:write name="title" />
					</div>
				</div>
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
				<logic:present name="ok">
					<logic:match value="ok" name="ok">
						<script language="javascript">
	         				alert("����ɹ���");
	         				dialogArgumentsQueryChick();
	         				Close();
	         			</script>
					</logic:match>
					<logic:match value="no" name="ok">
						<logic:present name="have">
							<logic:match value="have" name="have">
								<script language="javascript">
	         						alert("���ƴ����Ѵ��ڣ�");
	         					</script>
							</logic:match>
						</logic:present>
						<logic:notPresent name="have">
							<script language="javascript">
	         					alert("����ʧ�ܣ�");
	         				</script>
						</logic:notPresent>
					</logic:match>
				</logic:present>
				<table width="100%" id="rsTable" class="tbstyle">
					<tr>
						<td width="35%">
							<div align="center">
								�꼶
							</div>
						</td>
						<td width="65%">
							<input type="text" readonly="readonly" id="nj" name="nj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="nj"/>"
								readonly="readonly">
						</td>
					</tr>
					<tr>
						<td width="35%">
							<div align="center">
								<font color="red">*</font>��ѵѧ��
							</div>
						</td>
						<td width="65%">
							<html:select name="rs" property="xn" styleId="xn">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
						</td>
					</tr>
					<tr>
						<td width="35%">
							<div align="center">
								<font color="red">*</font>���ƴ���
							</div>
						</td>
						<td width="65%">
							<input type="text" id="bzdm" name="bzdm" readOnly="true"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name="rs" property="bzdm"/>">
						</td>
					</tr>
					<tr>
						<td width="35%">
							<div align="center">
								<font color="red">*</font>��������
							</div>
						</td>
						<td width="65%">
							<input type="text" id="bzmc" name="bzmc"
								style="width:100%;heigh:100%" maxlength="100"
								value="<bean:write name="rs" property="bzmc"/>">
						</td>
					</tr>
					<tr>
						<td width="35%">
							<div align="center">
								���Ƶȼ�
							</div>
						</td>
						<td width="65%">
							<input type="text" id="bzdjmc" name="bzdjmc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name="rs" property="bzdjmc"/>">
							<input type="hidden" id="bzdj" name="bzdj"
								value="<bean:write name="rs" property="bzdj"/>" />
						</td>
					</tr>
					<tr>
						<td width="35%">
							<div align="center">
								�̹�
							</div>
						</td>
						<td width="65%">
							<html:select property="jgbh" styleId="jgbh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="jgList" property="jgbh"
									labelProperty="xm" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="35%">
							<div align="center">
								���ӽ�ʦ
							</div>
						</td>
						<td width="65%">
							<html:select property="jsdm" styleId="jsdm" name="rs">
								<html:option value=""></html:option>
								<html:options collection="jsList" property="jsdm"
									labelProperty="xm" />
							</html:select>
						</td>
					</tr>
					<logic:notEqual name="xxdm" value="13022">
						<tr>
							<td width="35%">
								<div align="center">
									�����Ա�
								</div>
							</td>
							<td width="65%">
								<html:select name="rs" property="xb" styleId="xb">
									<html:option value=""></html:option>
									<html:options collection="xbList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
					</logic:notEqual>
					<logic:equal name="xxdm" value="13022">
						<tr>
							<td width="35%">
								<div align="center">
									��ѵ��װ
								</div>
							</td>
							<td width="65%">
								<html:select name="rs" property="jxfz" styleId="jxfz">
									<html:option value=""></html:option>
									<html:options collection="fzList" property="fzdm"
										labelProperty="fzmc" />
								</html:select>
							</td>
						</tr>
					</logic:equal>
					<tr>
						<td width="35%">
							<div align="center">
								��ע
							</div>
						</td>
						<td width="65%">
							<html:textarea name="rs" property="bz" rows='3'
								style='width:100%' onblur="yzdx(this,'bz')" />
							<input type="hidden" id="bz" name="bz" value="">
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2" onClick="yz();">
						��&nbsp;&nbsp;��
					</button>
					<button type="button" class="button2"
						onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
						��&nbsp;&nbsp;��
					</button>
				</div>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
