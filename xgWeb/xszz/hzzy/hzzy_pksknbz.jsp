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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(titName){
			var xh = document.getElementById('xh').value;
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("�������Ѳ������ɲ��ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/hzzy_pksknbz.do?doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		
		function toPrintOut(titName){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/hzzy_pksknbzb.do";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function xfhj(){
			var jtrks = document.getElementById('jtrks').value;
			var rjysr = document.getElementById('rjysr').value;
			if((jtrks == null) || (jtrks == "")){
				jtrks = "0";
			}
			if((rjysr == null) || (rjysr == "")){
				rjysr = "0";
			}
			var jtyzsr = Math.round(jtrks) * Math.round(rjysr);
			document.getElementById('jtyzsr').value=jtyzsr;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ƶ�������Ѳ�������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ�䣡
			</p>
		</center>
	</logic:equal>
		<html:form action="hzzy_pksknbz.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url" value="/hzzy_pksknbz.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" scope="request" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" scope="request" />">
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" scope="request" />">

			<logic:present name="notSQ">
				<logic:match value="yes" name="notSQ">
					<script language="javascript">
	         	alert("�������������������룡");
	         	</script>
				</logic:match>
			</logic:present>
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
			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" colspan="3">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" colspan="3">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="mzmc" name="mzmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="csny" readonly="readonly" name="csny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csny"/>">
					</td>
					<td colspan="2">
						<div align="center">
							��ѧʱ��
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="rxny" name="rxny" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxny"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xymc" readonly="readonly" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
					<td colspan="2">
						<div align="center">
							רҵ
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zymc" name="zymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" readonly="readonly" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
					<td colspan="2">
						<div align="center">
							���
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="nd" name="nd" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nd"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ƿ�������ѧ����
						</div>
					</td>
					<td colspan="3" align="center">
						<logic:present name="rs" property="sfcjzxdk">
							<logic:match value="��" name="rs" property="sfcjzxdk">
								<input type="radio" name="sfcjzxdk" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfcjzxdk" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfcjzxdk">
								<input type="radio" name="sfcjzxdk" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfcjzxdk" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfcjzxdk">
							<input type="radio" name="sfcjzxdk" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfcjzxdk" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
					<td colspan="2">
						<div align="center">
							�Ƿ�μ��ڹ���ѧ
						</div>
					</td>
					<td colspan="2" align="center">
						<logic:present name="rs" property="sfcjqgzx">
							<logic:match value="��" name="rs" property="sfcjqgzx">
								<input type="radio" name="sfcjqgzx" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfcjqgzx" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfcjqgzx">
								<input type="radio" name="sfcjqgzx" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfcjqgzx" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfcjqgzx">
							<input type="radio" name="sfcjqgzx" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfcjqgzx" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�ֻ�����
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sjhm" maxlength="15" name="sjhm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sjhm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							����绰
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jldh" name="jldh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jldh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6" scope="row">
						<div align="center">
							��
							<br />
							ͥ
							<br />
							ֱ
							<br />
							ϵ
							<br />
							��
							<br />
							��
							<br />
							��
							<br />
							��
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td width="11%">
						<div align="center">
							����
						</div>
					</td>
					<td width="17%">
						<div align="center">
							�뱾�˹�ϵ
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							����(��ѧϰ)��λ
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy1_xm" name="jtcy1_xm"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_nl" name="jtcy1_nl"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_nl"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_gx" name="jtcy1_gx"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_gx"/>">
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							<input type="text" id="jtcy1_gzdw" name="jtcy1_gzdw"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy2_xm" name="jtcy2_xm"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_nl" name="jtcy2_nl"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_nl"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_gx" name="jtcy2_gx"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_gx"/>">
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							<input type="text" id="jtcy2_gzdw" name="jtcy2_gzdw"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy3_xm" name="jtcy3_xm"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_nl" name="jtcy3_nl"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_nl"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_gx" name="jtcy3_gx"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_gx"/>">
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							<input type="text" id="jtcy3_gzdw" name="jtcy3_gzdw"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy4_xm" name="jtcy4_xm"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_nl" name="jtcy4_nl"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_nl"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_gx" name="jtcy4_gx"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_gx"/>">
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							<input type="text" id="jtcy4_gzdw" name="jtcy4_gzdw"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy5_xm" name="jtcy5_xm"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_nl" name="jtcy5_nl"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_nl"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_gx" name="jtcy5_gx"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_gx"/>">
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							<input type="text" id="jtcy5_gzdw" name="jtcy5_gzdw"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ����
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jthk" readonly="readonly" name="jthk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jthk"/>">
					</td>
					<td colspan="2">
						<div align="center">
							��ͥ�˿�����
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtrks" name="jtrks" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrks"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�˾�������
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="rjysr" maxlength="5" name="rjysr"
							style="width:100%;heigh:100%" onblur="xfhj();"
							value="<bean:write name="rs" property="rjysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							��ͥ��������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtyzsr" name="jtyzsr" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyzsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							������Դ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="srly" maxlength="100" name="srly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="srly"/>">
					</td>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="yzbm" name="yzbm" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yzbm"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="7">
						<input type="text" id="jtzz" readonly="readonly" name="jtzz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzz"/>">
					</td>
				</tr>
				<tr>
					<td rowspan="7" scope="row">
						<div align="center">
							��
							<br />
							ѧ
							<br />
							��
							<br />
							ѧ
							<br />
							ϰ
							<br />
							��
							<br />
							��
							<br />
							��
							<br />
							��
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							�γ�
						</div>
					</td>
					<td>
						<div align="center">
							�ɼ�
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							�γ�
						</div>
					</td>
					<td width="10%">
						<div align="center">
							�ɼ�
						</div>
					</td>
					<td width="20%">
						<div align="center">
							�γ�
						</div>
					</td>
					<td width="11%">
						<div align="center">
							�ɼ�
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<input type="text" id="kc1_mc" maxlength="100" name="kc1_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc1_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc1_cj" maxlength="20" name="kc1_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc1_cj"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="kc2_mc" maxlength="100" name="kc2_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc2_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc2_cj" maxlength="20" name="kc2_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc2_cj"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc3_mc" maxlength="100" name="kc3_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc3_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc3_cj" maxlength="20" name="kc3_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc3_cj"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<input type="text" id="kc4_mc" maxlength="100" name="kc4_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc4_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc4_cj" maxlength="20" name="kc4_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc4_cj"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="kc5_mc" maxlength="100" name="kc5_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc5_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc5_cj" maxlength="20" name="kc5_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc5_cj"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc6_mc" maxlength="100" name="kc6_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc6_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc6_cj" maxlength="20" name="kc6_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc6_cj"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<input type="text" id="kc7_mc" maxlength="100" name="kc7_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc7_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc7_cj" maxlength="20" name="kc7_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc7_cj"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="kc8_mc" maxlength="100" name="kc8_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc8_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc8_cj" maxlength="20" name="kc8_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc8_cj"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc9_mc" maxlength="100" name="kc9_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc9_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc9_cj" maxlength="20" name="kc9_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc9_cj"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<input type="text" id="kc10_mc" maxlength="100" name="kc10_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc10_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc10_cj" maxlength="20" name="kc10_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc10_cj"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="kc11_mc" maxlength="100" name="kc11_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc11_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc11_cj" maxlength="20" name="kc11_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc11_cj"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc12_mc" maxlength="100" name="kc12_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc12_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc12_cj" maxlength="20" name="kc12_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc12_cj"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<input type="text" id="kc13_mc" maxlength="100" name="kc13_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc13_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc13_cj" maxlength="20" name="kc13_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc13_cj"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="kc14_mc" maxlength="100" name="kc14_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc14_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc14_cj" maxlength="20" name="kc14_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc14_cj"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc15_mc" maxlength="100" name="kc15_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc15_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc15_cj" maxlength="20" name="kc15_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc15_cj"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<input type="text" id="kc16_mc" maxlength="100" name="kc16_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc16_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc16_cj" maxlength="20" name="kc16_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc16_cj"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="kc17_mc" maxlength="100" name="kc17_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc17_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc17_cj" maxlength="20" name="kc17_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc17_cj"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc18_mc" maxlength="100" name="kc18_mc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc18_mc"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="kc18_cj" maxlength="20" name="kc18_cj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="kc18_cj"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�������Ѳ�������
						</div>
					</td>
					<td colspan="7">
						<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
						<input type="hidden" id="sqly" name="sqly" value="">
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
