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
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(titName){
			var xh = document.getElementById('xh').value;
			var lxdh = document.getElementById('lxdh').value;
			var hkrs = document.getElementById('hkrs').value;
			var jtyzsr = document.getElementById('jtyzsr').value;
			var jtrjsr = document.getElementById('jtrjsr').value;
			var yzbm = document.getElementById('yzbm').value;
			var JTCY1_nl = document.getElementById('JTCY1_nl').value;
			var JTCY2_nl = document.getElementById('JTCY2_nl').value;
			var JTCY3_nl = document.getElementById('JTCY3_nl').value;
			var JTCY4_nl = document.getElementById('JTCY4_nl').value;
			var JTCY5_nl = document.getElementById('JTCY5_nl').value;
			var jlxx = document.getElementById('jlxx').value;
			var jtzz = document.getElementById('jtzz').value;
			var sqly = document.getElementById('sqly').value;
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((lxdh != null) && (lxdh != "") && (!isNumber(lxdh))){
				alert("��ϵ�绰����Ϊ����!");
				return false;
			}
			if((hkrs != null) && (hkrs != "") && (!isNumber(hkrs))){
				alert("������������Ϊ����!");
				return false;
			}
			if((jtyzsr != null) && (jtyzsr != "") && (!isNumber(jtyzsr))){
				alert("��ͥ�����������Ϊ����!");
				return false;
			}
			if((jtrjsr != null) && (jtrjsr != "") && (!isNumber(jtrjsr))){
				alert("��ͥ�˾��������Ϊ����!");
				return false;
			}
			if((yzbm != null) && (yzbm != "") && (!isNumber(yzbm))){
				alert("�����������Ϊ����!");
				return false;
			}
			if((JTCY1_nl != null) && (JTCY1_nl != "") && (!isNumber(JTCY1_nl))){
				alert("��ͥ��Ա1�������Ϊ����!");
				return false;
			}
			if((JTCY2_nl != null) && (JTCY2_nl != "") && (!isNumber(JTCY2_nl))){
				alert("��ͥ��Ա2�������Ϊ����!");
				return false;
			}
			if((JTCY3_nl != null) && (JTCY3_nl != "") && (!isNumber(JTCY3_nl))){
				alert("��ͥ��Ա3�������Ϊ����!");
				return false;
			}
			if((JTCY4_nl != null) && (JTCY4_nl != "") && (!isNumber(JTCY4_nl))){
				alert("��ͥ��Ա4�������Ϊ����!");
				return false;
			}
			if((JTCY5_nl != null) && (JTCY5_nl != "") && (!isNumber(JTCY5_nl))){
				alert("��ͥ��Ա5�������Ϊ����!");
				return false;
			}
			if(jlxx != null){
	         	if(jlxx.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("������Ϣ���ܴ���100���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(jtzz != null){
	         	if(jtzz.replace(/[^\u0000-\u00ff]/g, "**").length > 50){	         
	          		 alert("��ͥסַ���ܴ���50���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("�������ɲ��ܴ���1000���ַ�");
	          		 return false;
	       		 }
			}
			
			document.forms[0].action = "/xgxt/lyjszxjsq.do?jxjlbType=lujszxjsq&doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/lyjszxjsqb.do";
			document.forms[0].submit();
		}
	</script>

</head>

<body onload="loadPage()">
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��Ԫ������ѧ������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="gjjxjzxj.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url"
				value="/lyjszxjsq.do?jxjlbType=lyjszxjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">

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
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         		alert("��ͨ����ˣ��������룡");
	         	</script>
				</logic:match>
			</logic:present>
			<div class="xxk">
				<logic:notEmpty name="pages">
					<logic:iterate id="card" name="pages" scope="request">
						<ul>
							<li id="<bean:write name='card' property='en'/>l"
								class="xxk_off_l"></li>
							<li id="<bean:write name='card' property='en'/>m" onclick=""
								class="xxk_off_m">
								&nbsp;
								<bean:write name='card' property='cn' />
								&nbsp;
							</li>
							<li id="<bean:write name='card' property='en'/>r"
								class="xxk_off_r"></li>
						</ul>
					</logic:iterate>
				</logic:notEmpty>
			</div>
			<logic:notEmpty name="isSHGCKNS">
				<logic:equal name="isSHGCKNS" value="no">
					<table class="tbstyle">
						<tr>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<td align="center" colspan="2">
									<font color="red">*</font>ѧ�ţ�
								</td>
								<td align="left" colspan="2">
									<html:text name='rs' property="xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)"
										readonly="true" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<td align="right" colspan="2">
									<font color="red">*</font>ѧ�ţ�
								</td>
								<td align="left" colspan="2">
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
								<input type="text" id="xm" name="xm"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xm"/>"
									readonly="readonly">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="csny" readonly="readonly" name="csny"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="csny"/>">
							</td>
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
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ѧʱ��
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="rxny" readonly="readonly" name="rxny"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="rxny"/>">
							</td>
							<td>
								<div align="center">
									��ҵʱ��
								</div>
							</td>
							<td>
								<input type="text" readonly style="cursor:hand;width:100%"
									onclick="return showCalendar('byny','y-mm-dd');"
									value="<bean:write name='rs' property="byny" />" name="byny"
									id="byny" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="lxdh" name="lxdh" maxlength="12"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="lxdh"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<input type="text" id="mzmc" name="mzmc" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="mzmc"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ѧ
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="xxmc" readonly="readonly" name="xxmc"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xxmc"/>">
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td>
								<input type="text" id="xy" name="xy" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xy"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									ϵ
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="xmc" readonly="readonly" name="xmc"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xmc"/>">
							</td>
							<td>
								<div align="center">
									��
								</div>
							</td>
							<td>
								<input type="text" id="bjmc" name="bjmc" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="bjmc"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									����
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="kh" readonly="readonly" name="kh"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="kh"/>">
							</td>
							<td>
								<div align="center"></div>
							</td>
							<td></td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�������
								</div>
								<p align="center">
									����
									<br>
									(50��������)
							</td>
							<td colspan="4">
								<html:textarea name="rs" property="jlxx" rows='3' style="width:100%" onblur="yzdx(this,'jlxx')"/>
								<input type="hidden" id="jlxx" name="jlxx" value="">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ͥ����
								</div>
							</td>
							<td colspan="2" align="center">
								<logic:present name="rs" property="radJthk">
									<logic:match value="����" name="rs" property="radJthk">
										<input type="radio" name="radJthk" value="����" checked>&nbsp;&nbsp;A  ����
							         	<input type="radio" name="radJthk" value="ũ��">&nbsp;&nbsp;B ũ��
							         </logic:match>
									<logic:match value="ũ��" name="rs" property="radJthk">
										<input type="radio" name="radJthk" value="����">&nbsp;&nbsp;A  ����
							         	<input type="radio" name="radJthk" value="ũ��" checked>&nbsp;&nbsp;B ũ��
							         </logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="radJthk">
									<input type="radio" name="radJthk" value="����" checked>&nbsp;&nbsp;A  ���� 
							         <input type="radio" name="radJthk" value="ũ��">&nbsp;&nbsp;B ũ��
						         </logic:notPresent>
							</td>
							<td>
								<div align="center">
									��ͥ��������
								</div>
							</td>
							<td>
								<input type="text" id="hkrs" name="hkrs" maxlength="10"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="hkrs"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ͥ��������
								</div>
							</td>
							<td colspan="2">
								<input name="jtyzsr" id="jtyzsr" maxlength="10" type="text"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtyzsr"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<div align="center">
									��ͥ�˾�����
								</div>
							</td>
							<td>
								<input type="text" id="jtrjsr" name="jtrjsr" maxlength="10"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtrjsr"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ͥ������Դ
								</div>
							</td>
							<td colspan="2">
								<input type="text" name="jtsrly" maxlength="10" name="jtsrly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtsrly"/>">
							</td>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<input name="yzbm" id="yzbm" type="text" maxlength="6"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="yzbm"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ͥסַ
								</div>
							</td>
							<td colspan="4">
								<html:textarea name="rs" property="jtzz" rows='2' style="width:100%" onblur="yzdx(this,'jtzz')"/>
								<input type="hidden" id="jtzz" name="jtzz" value="">
							</td>
						</tr>
						<tr>
							<td width="4%" rowspan="6">
								<div align="center">
									��
									<br>
									ͥ
									<br>
									��
									<br>
									Ա
									<br>
									��
									<br>
									��
								</div>
							</td>
							<td width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td width="9%">
								<div align="center">
									����
								</div>
							</td>
							<td width="25%">
								<div align="center">
									�뱾�˹�ϵ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									������ѧϰ��λ
								</div>
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY1_XM" maxlength="40" name="JTCY1_XM"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY1_nl" name="JTCY1_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="JTCY1_GX" name="JTCY1_GX" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY1_GZDZ" maxlength="50"
									name="JTCY1_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY2_XM" maxlength="40" name="JTCY2_XM"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY2_nl" name="JTCY2_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="JTCY2_GX" name="JTCY2_GX" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY2_GZDZ" maxlength="50"
									name="JTCY2_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY3_XM" maxlength="40" name="JTCY3_XM"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY3_nl" name="JTCY3_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="JTCY3_GX" name="JTCY3_GX" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY3_GZDZ" maxlength="50"
									name="JTCY3_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY4_XM" maxlength="40" name="JTCY4_XM"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY4_nl" name="JTCY4_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="JTCY4_GX" name="JTCY4_GX" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY4_GZDZ" maxlength="50"
									name="JTCY4_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY5_XM" maxlength="40" name="JTCY5_XM"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY5_nl" name="JTCY5_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="JTCY5_GX" name="JTCY5_GX" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY5_GZDZ" maxlength="50"
									name="JTCY5_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td>
								<br>
								����
								<br>
								����
							</td>
							<td colspan="5">
								<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
								<input type="hidden" id="sqly" name="sqly" value="">
							</td>
						</tr>
					</table>
				</logic:equal>
				<logic:equal name="isSHGCKNS" value="is">
					<table class="tbstyle">
						<tr>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<td align="center" colspan="2">
									<font color="red">*</font>ѧ�ţ�
								</td>
								<td align="left" colspan="2">
									<html:text name='rs' property="xh" styleId="xh" readonly="true"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<td align="right" colspan="2">
									<font color="red">*</font>ѧ�ţ�
								</td>
								<td align="left" colspan="2">
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
								<input type="text" id="xm" name="xm"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xm"/>"
									readonly="readonly">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="2">
								<input type="text" readonly="readonly" id="csny" name="csny"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="csny"/>">
							</td>
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
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ѧʱ��
								</div>
							</td>
							<td colspan="2">
								<input type="text" readonly="readonly" id="rxny" name="rxny"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="rxny"/>">
							</td>
							<td>
								<div align="center">
									��ҵʱ��
								</div>
							</td>
							<td>
								<input type="text" readonly style="cursor:hand;width:100%"
									onclick="return showCalendar('byny','y-mm-dd');"
									value="<bean:write name='rs' property="byny" />" name="byny"
									id="byny" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td colspan="2">
								<input type="text" maxlength="12" id="lxdh" name="lxdh"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="lxdh"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<input type="text" id="mzmc" name="mzmc" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="mzmc"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ѧ
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="xxmc" readonly="readonly" name="xxmc"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xxmc"/>">
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td>
								<input type="text" id="xy" name="xy" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xy"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									ϵ
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="xmc" readonly="readonly" name="xmc"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xmc"/>">
							</td>
							<td>
								<div align="center">
									��
								</div>
							</td>
							<td>
								<input type="text" id="bjmc" name="bjmc" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="bjmc"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									����
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="kh" readonly="readonly" name="kh"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="kh"/>">
							</td>
							<td>
								<div align="center"></div>
							</td>
							<td></td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�������
								</div>
								<p align="center">
									����
									<br>
									(50��������)
							</td>
							<td colspan="4">
								<html:textarea name="rs" property="jlxx" rows='3' style="width:100%" onblur="yzdx(this,'jlxx')"/>
								<input type="hidden" id="jlxx" name="jlxx" value="">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ͥ����
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="radJthk" readonly="readonly"
									name="radJthk" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="radJthk"/>">
							</td>
							<td>
								<div align="center">
									��ͥ��������
								</div>
							</td>
							<td>
								<input type="text" id="hkrs" name="hkrs" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="hkrs"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ͥ��������
								</div>
							</td>
							<td colspan="2">
								<input name="jtyzsr" id="jtyzsr" readonly="readonly" type="text"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtyzsr"/>">
							</td>
							<td>
								<div align="center">
									��ͥ�˾�����
								</div>
							</td>
							<td>
								<input type="text" id="jtrjsr" name="jtrjsr" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtrjsr"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ͥ������Դ
								</div>
							</td>
							<td colspan="2">
								<input type="text" name="jtsrly" readonly="readonly"
									name="jtsrly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtsrly"/>">
							</td>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<input name="yzbm" id="yzbm" maxlength="6" type="text"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="yzbm"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ͥסַ
								</div>
							</td>
							<td colspan="4">
								<input type="text" id="jtzz" name="jtzz" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtzz"/>">
							</td>
						</tr>
						<tr>
							<td width="4%" rowspan="6">
								<div align="center">
									��
									<br>
									ͥ
									<br>
									��
									<br>
									Ա
									<br>
									��
									<br>
									��
								</div>
							</td>
							<td width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td width="9%">
								<div align="center">
									����
								</div>
							</td>
							<td width="25%">
								<div align="center">
									�뱾�˹�ϵ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									������ѧϰ��λ
								</div>
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY1_XM" readonly="readonly"
									name="JTCY1_XM" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY1_nl" name="JTCY1_nl"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_nl" />">
							</td>
							<td>
								<input type="text" id="JTCY1_GX" name="JTCY1_GX"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY1_GZDZ" readonly="readonly"
									name="JTCY1_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY2_XM" readonly="readonly"
									name="JTCY2_XM" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY2_nl" name="JTCY2_nl"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_nl" />">
							</td>
							<td>
								<input type="text" id="JTCY2_GX" name="JTCY2_GX"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY2_GZDZ" readonly="readonly"
									name="JTCY2_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY3_XM" name="JTCY3_XM"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY3_nl" name="JTCY3_nl"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_nl" />">
							</td>
							<td>
								<input type="text" id="JTCY3_GX" name="JTCY3_GX"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY3_GZDZ" readonly="readonly"
									name="JTCY3_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY4_XM" name="JTCY4_XM"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY4_nl" name="JTCY4_nl"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_nl" />">
							</td>
							<td>
								<input type="text" id="JTCY4_GX" name="JTCY4_GX"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY4_GZDZ" name="JTCY4_GZDZ"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY5_XM" name="JTCY5_XM"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY5_nl" name="JTCY5_nl"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_nl" />">
							</td>
							<td>
								<input type="text" id="JTCY5_GX" name="JTCY5_GX"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY5_GZDZ" name="JTCY5_GZDZ"
									readonly="readonly" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td>
								<br>
								����
								<br>
								����
							</td>
							<td colspan="5">
								<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
								<input type="hidden" id="sqly" name="sqly" value="">
							</td>
						</tr>
					</table>
				</logic:equal>
			</logic:notEmpty>

	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2"
					onClick="yz(document.getElementById('titName').value);">
					�ύ����
				</button>
				<button class="button2" onClick="toPrintOut();">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
