<%@ page language="java" pageEncoding="GB2312"
	contentType="text/html;charset=GBK"%>

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
<html:html locale="true">
<head>
	<base target="_self">
	<title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expires", "0");
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript">
		function yz(titName){
			var xh = document.getElementById('xh').value;
			var sfzh = 	document.getElementById('sfzh').value;
			var qsdh = 	document.getElementById('qsdh').value;
			var sqdkje = document.getElementById('sqdkje').value;
			var dkqx = 	document.getElementById('dkqx').value;
			var yzbm = 	document.getElementById('yzbm').value;
			var jtdh = 	document.getElementById('jtdh').value;
			var jhr1_sfzh = document.getElementById('jhr1_sfzh').value;
			var jhr2_sfzh = document.getElementById('jhr2_sfzh').value;
			
			if(xh == null){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((sfzh != null) && (sfzh != "") && (!checkSfzh(sfzh))){
				return false;
			}
			if((qsdh != null) && (qsdh != "") && (!isNumber(qsdh))){
				alert("����绰����Ϊ����!");
				return false;
			}
			if((sqdkje != null) && (sqdkje != "") && (!isNumber(sqdkje))){
				alert("������������Ϊ����!");
				return false;
			}
			if((dkqx != null) && (dkqx != "") && (!isNumber(dkqx))){
				alert("�������ޱ���Ϊ����!");
				return false;
			}
			if((yzbm != null) && (yzbm != "") && (!isNumber(yzbm))){
				alert("�����������Ϊ����!");
				return false;
			}
			if((jtdh != null) && (jtdh != "") && (!isNumber(jtdh))){
				alert("��ͥ�绰����Ϊ����!");
				return false;
			}
			if((jhr1_sfzh != null) && (jhr1_sfzh != "") && (!checkSfzh(jhr1_sfzh))){
				return false;
			}
			if((jhr2_sfzh != null) && (jhr2_sfzh != "") && (!checkSfzh(jhr2_sfzh))){
				return false;
			}
			
			document.forms[0].action = "/xgxt/xpjjsq.do?doType=save";
			document.forms[0].submit();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
		function checkSfzh(sfzh) {
   			sfzh=sfzh.toLowerCase()
			var OldID;
			if(sfzh.length == 15){
				OldID = sfzh;
				return true;
			}else if(sfzh.length == 18){
				OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
			}else{
				alert("��������ȷ�����֤���룡","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
			var A = new Array("1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2");
			var i, j, S;
			var NewID, ID, strNF;
			NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
			S = 0;
			for( i = 0; i <= 16; i++){
				j = NewID.substring(i, i+1) * W[i];
				S = S + j;
			}
			S = S % 11;
			if(sfzh != NewID + A[S]){
				alert("��������ȷ�����֤���룡","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			return true;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ���� - ��ƽ����������ѧ����
		</div>
	</div>

	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>

			<html:form action="/xpjjsq" method="post">
				<input type="hidden" name="titName" id="titName"
					value="<bean:write name="titName" />" />
				<input type="hidden" id="url" name="url" value="/xpjjsq.do" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-bjmc" />

				<logic:present name="doresult">
					<logic:match value="yes" name="doresult">
						<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
					</logic:match>
					<logic:match value="no" name="doresult">
						<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
					</logic:match>
				</logic:present>
				<logic:present name="isPASS">
					<logic:match value="is" name="isPASS">
						<script language="javascript">
	         				alert("��ͨ����ˣ��������룡");
	         			</script>
					</logic:match>
				</logic:present>

				<table class="tbstyle">
					<tr>
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td width="17%" height="31">
								<div align="center">
									<font color="red">*</font>ѧ�ţ�
								</div>
							</td>
							<td width="33%" height="31">
								<div align="left">
									<html:text name='rs' property="xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true"/>
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</div>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td width="17%" height="31">
								<div align="center">
									<font color="red">*</font>ѧ�ţ�
								</div>
							</td>
							<td width="33%" height="31">
								<div align="left">
									<input type="text" id="xh" name="xh"
										style="width:100%;heigh:100%" 
										value="<bean:write name='rs' property="xh" />" readonly="true">
								</div>
							</td>
						</logic:equal>
						<td width="13%" height="31">
							<div align="center">
								����
							</div>
						</td>
						<td width="37%" height="31">
							<div align="left">
								<input type="text" id="xm" name="xm" readonly="readonly"
									value="<bean:write name="rs" property="xm" />"
									style="width:100%">
							</div>
						</td>
					</tr>
					<tr>
						<td height="31">
							<div align="center">
								�Ա�
							</div>
						</td>
						<td height="31">
							<div align="left">
								<input type="text" id="xb" name="xb" readonly="readonly"
									value="<bean:write name="rs" property="xb" />"
									style="width:100%">
							</div>
						</td>
						<td height="31">
							<div align="center">
								��������
							</div>
						</td>
						<td height="31">
							<div align="left">
								<input type="text" id="csrq" name="csrq" readonly="readonly"
									value="<bean:write name="rs" property="csrq" />"
									style="width:100%">
							</div>
						</td>
					</tr>
					<tr>
						<td height="31">
							<div align="center">
								���֤����
							</div>
						</td>
						<td height="31">
							<div align="left">
								<input type="text" id="sfzh" name="sfzh" maxlength="18"
									value="<bean:write name="rs" property="sfzh" />"
									style="width:100%">								
							</div>
						</td>
						<td height="31">
							<div align="center">
								�꼶
							</div>
						</td>
						<td height="31">
							<div align="left">
								<input type="text" id="nj" name="nj" readonly="readonly"
									value="<bean:write name="rs" property="nj" />"
									style="width:100%">	
							</div>
						</td>
					</tr>
					<tr>
						<td height="31">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />
							</div>
						</td>
						<td height="31">
							<div align="left">
								<input type="text" id="xy" name="xy" readonly="readonly"
									value="<bean:write name="rs" property="xy" />"
									style="width:100%">
							</div>
						</td>
						<td height="31">
							<div align="center">
								רҵ
							</div>
						</td>
						<td height="31">
							<div align="left">
								<input type="text" id="zymc" name="zymc" readonly="readonly"
									value="<bean:write name="rs" property="zymc" />"
									style="width:100%">
							</div>
						</td>
					</tr>
					<tr>
						<td height="31">
							<div align="center">
								����绰
							</div>
						</td>
						<td height="31">
							<div align="left">
								<input type="text" id="qsdh" name="qsdh" maxlength="12"
									value="<bean:write name="rs" property="qsdh" />"
									style="width:100%"
									onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td height="31">
							<div align="center">
								ѧ��
							</div>
						</td>
						<td height="31">
							<div align="left">
								<input type="text" id="xz" name="xz" readonly="readonly"
									value="<bean:write name="rs" property="xz" />"
									style="width:100%">
							</div>
						</td>
					</tr>
					<tr>
						<td height="31">
							<div align="center">
								���������
							</div>
						</td>
						<td height="31">
							<div align="left">
								<input type="text" id="sqdkje" name="sqdkje" maxlength="6"
									value="<bean:write name="rs" property="sqdkje" />"
									style="width:100%"
									onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td height="31">
							<div align="center">
								��������
							</div>
						</td>
						<td height="31">
							<div align="left">
								<input type="text" id="dkqx" name="dkqx" maxlength="4"
									value="<bean:write name="rs" property="dkqx" />"
									style="width:90%">��
							</div>
						</td>
					</tr>
					<tr>
						<td height="31">
							����ʡ����������
						</td>
						<td height="31">
							<input type="text" id="szzq" name="szzq" maxlength="50"
								value="<bean:write name="rs" property="szzq" />"
								style="width:100%">
						</td>
						<td height="31">
							�����У��ء�����
						</td>
						<td height="31">
							<input type="text" id="sxq" name="sxq" maxlength="50"
								value="<bean:write name="rs" property="sxq" />"
								style="width:100%">
						</td>
					</tr>
					<tr>
						<td height="31">
							�ʱ�
						</td>
						<td height="31">
							<input type="text" id="yzbm" name="yzbm" maxlength="6"
								value="<bean:write name="rs" property="yzbm" />"
								style="width:100%"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td height="31">
							��ͥ�绰
						</td>
						<td height="31">
							<input type="text" id="jtdh" name="jtdh" maxlength="12"
								value="<bean:write name="rs" property="jtdh" />"
								style="width:100%"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td height="31">
							�ҳ��򷨶��໤�ˣ�1������
						</td>
						<td height="31">
							<input type="text" id="jhr1_xm" name="jhr1_xm" maxlength="50"
								value="<bean:write name="rs" property="jhr1_xm" />"
								style="width:100%">
						</td>
						<td height="31">
							�ҳ��򷨶��໤�ˣ�1��ְҵ��
						</td>
						<td height="31">
							<input type="text" id="jhr1_zy" name="jhr1_zy" maxlength="50"
								value="<bean:write name="rs" property="jhr1_zy" />"
								style="width:100%">
						</td>
					</tr>
					<tr>
						<td height="31">
							�ҳ��򷨶��໤�ˣ�1�����֤����
						</td>
						<td height="31">
							<input type="text" id="jhr1_sfzh" name="jhr1_sfzh" maxlength="18"
								value="<bean:write name="rs" property="jhr1_sfzh" />"
								style="width:100%">
						</td>
						<td height="31">
							�ҳ��򷨶��໤�ˣ�1��������λ
						</td>
						<td height="31">
							<input type="text" id="jhr1_gzdw" name="jhr1_gzdw" maxlength="50"
								value="<bean:write name="rs" property="jhr1_gzdw" />"
								style="width:100%">
						</td>
					</tr>
					<tr>
						<td height="31">
							�ҳ��򷨶��໤�ˣ�2������
						</td>
						<td height="31">
							<input type="text" id="jhr2_xm" name="jhr2_xm" maxlength="50"
								value="<bean:write name="rs" property="jhr2_xm" />"
								style="width:100%">
						</td>
						<td height="31">
							�ҳ��򷨶��໤�ˣ�2��ְҵ��
						</td>
						<td height="31">
							<input type="text" id="jhr2_zy" name="jhr2_zy" maxlength="50"
								value="<bean:write name="rs" property="jhr2_zy" />"
								style="width:100%">
						</td>
					</tr>
					<tr>
						<td height="31">
							�ҳ��򷨶��໤�ˣ�2�����֤����
						</td>
						<td height="31">
							<input type="text" id="jhr2_sfzh" name="jhr2_sfzh" maxlength="18"
								value="<bean:write name="rs" property="jhr2_sfzh" />"
								style="width:100%">
						</td>
						<td height="31">
							�ҳ��򷨶��໤�ˣ�2��������λ
						</td>
						<td height="31">
							<input type="text" id="jhr2_gzdw" name="jhr2_gzdw" maxlength="50"
								value="<bean:write name="rs" property="jhr2_gzdw" />"
								style="width:100%">
						</td>
					</tr>
				</table>
			<logic:equal name="sfksq" value="1">
				<div class="buttontool" id="btn"
					style="position: absolute;width:100%">
					<button class="button2"
						onClick="yz(document.forms[0].titName.value);">
						�ύ����
					</button>
					<button class="button2"
						onClick="toPrintOut(document.forms[0].titName.value)">
						��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
					</button>
				</div>
			</logic:equal>
			</html:form>
</body>
</html:html>
