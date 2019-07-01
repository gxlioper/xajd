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
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			document.forms[0].action = "/xgxt/gnnzzy_gjzxdk.do?method=zxdksq&act=save";
			document.forms[0].submit();
		}
		
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/gnnzzy_gjzxdk.do?method=zxdksqb";
			document.forms[0].submit();
		}
		
		function je(){
			var jtrk = document.getElementById('jtrk').value;
			var jtrjysr = document.getElementById('jtrjysr').value;
			if((jtrk == null) || (jtrk == "")){
				jtrk = "0";
			}
			if((jtrjysr == null) || (jtrjysr == "")){
				jtrjysr = "0";
			}
			var je = Math.round(jtrk)*(Math.round(jtrjysr)*12);
			document.getElementById('jtnzsr').value=je;
		}		
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��ѧ��������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="gnnzzy_gjzxdk.do?method=zxdksq" method="post">
			<input type="hidden" id="url" name="url" value="/gnnzzy_gjzxdk.do?method=zxdksq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh"/>">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh"/>">
			<input type="hidden" id="sqsj_year" name="sqsj_year"
				value="<bean:write name="rs" property="sqsj_year"/>">
			<input type="hidden" id="sqsj_mon" name="sqsj_mon"
				value="<bean:write name="rs" property="sqsj_mon"/>">
			<input type="hidden" id="sqsj_day" name="sqsj_day"
				value="<bean:write name="rs" property="sqsj_day"/>">
			<input type="hidden" id="dklxmc" name="dklxmc"
				value="<bean:write name="rs" property="dklxmc"/>">

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
			<logic:present name="have">
				<logic:match value="have" name="have">
					<script language="javascript">
	         			alert("��ͨ����ˣ��������룡");
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
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
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
					<td width="16%" scope="col">
						<div align="center">
							����
						</div>
					</td>
					<td width="34%" scope="col">
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xm" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td width="16%" scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td width="34%">
						<input type="text" id="xb" name="xb" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xb" />" readonly="true">
					</td>
					<td>
						<div align="center">
							�꼶
						</div>
					</td>
					<td>
						<input type="text" id="nj" name="nj" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="nj" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="sfzh" />" readonly="true">
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xymc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							רҵ
						</div>
					</td>
					<td>
						<input type="text" id="zymc" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zymc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							�༶
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="bjmc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							���
						</div>
					</td>
					<td>
						<input type="text" id="nd" name="nd"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="nd" />" readonly="true">
					</td>
					<td>
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td>
						<input type="text" id="sqsj" name="sqsj"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="sqsj" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<input type="text" id="xz" name="xz"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xz" />" readonly="readonly">
					</td>
					<td>
						<div align="center">
							������ϵ�绰
						</div>
					</td>
					<td align="center">
						<input type="text" id="grlxdh" name="grlxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="grlxdh" />" maxlength="15"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�����ַ
						</div>
					</td>
					<td>
						<input type="text" id="yxdz" name="yxdz"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="yxdz" />" maxlength="50">
					</td>
					<td>
						<div align="center">
							�������ڵ�
						</div>
					</td>
					<td align="center">
						<input type="text" id="hjszd" name="hjszd"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="hjszd" />" maxlength="100">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ͥ��ס��ַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtjzdz" name="jtjzdz"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="jtjzdz" />" maxlength="200">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<input type="text" id="yzbm" name="yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="yzbm" />" maxlength="6"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							��ͥ��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="jtlxdh" name="jtlxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="jtlxdh" />" maxlength="15"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ͥ�˿���
						</div>
					</td>
					<td>
						<input type="text" id="jtrk" name="jtrk"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name='rs' property="jtrk" />" maxlength="5"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							��ͥ�˾�������
						</div>
					</td>
					<td>
						<input type="text" id="jtrjysr" name="jtrjysr"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name='rs' property="jtrjysr" />" maxlength="5"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ͥ��������
						</div>
					</td>
					<td>
						<input type="text" id="jtnzsr" name="jtnzsr"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="jtnzsr" />" maxlength="20">
					</td>
					<td>
						<div align="center">
							��ͥ���ڽֵ�\<br />
							��ί����ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="jtszjwhdh" name="jtszjwhdh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="jtszjwhdh" />" maxlength="15"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<input type="text" id="fqxm" name="fqxm"
							style="width:100%;heigh:100%" 
							value="<bean:write name='rs' property="fqxm" />" maxlength="50">
					</td>
					<td>
						<div align="center">
							ĸ������
						</div>
					</td>
					<td>
						<input type="text" id="mqxm" name="mqxm"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="mqxm" />" maxlength="50">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�������֤��
						</div>
					</td>
					<td>
						<input type="text" id="fqsfzh" name="fqsfzh"
							style="width:100%;heigh:100%" 
							value="<bean:write name='rs' property="fqsfzh" />" maxlength="18">
					</td>
					<td>
						<div align="center">
							ĸ�����֤��
						</div>
					</td>
					<td>
						<input type="text" id="mqsfzh" name="mqsfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="mqsfzh" />" maxlength="18">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							���׹�����λ
						</div>
					</td>
					<td>
						<input type="text" id="fqgzdw" name="fqgzdw"
							style="width:100%;heigh:100%" 
							value="<bean:write name='rs' property="fqgzdw" />" maxlength="100">
					</td>
					<td>
						<div align="center">
							ĸ�׹�����λ
						</div>
					</td>
					<td>
						<input type="text" id="mqgzdw" name="mqgzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="mqgzdw" />" maxlength="100">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							����ְҵ
						</div>
					</td>
					<td>
						<input type="text" id="fqzy" name="fqzy"
							style="width:100%;heigh:100%" 
							value="<bean:write name='rs' property="fqzy" />" maxlength="20">
					</td>
					<td>
						<div align="center">
							ĸ��ְҵ
						</div>
					</td>
					<td>
						<input type="text" id="mqzy" name="mqzy"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="mqzy" />" maxlength="20">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							������ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="fqlxdh" name="fqlxdh"
							style="width:100%;heigh:100%" 
							value="<bean:write name='rs' property="fqlxdh" />" maxlength="20"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							ĸ����ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="mqlxdh" name="mqlxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="mqlxdh" />" maxlength="20"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<html:select name="rs" property="dklxdm" style="width:180px">
							<html:options collection="dklxList" property="dklxdm"
								labelProperty="dklxmc" />
						</html:select>
					</td>
					<td>
						<div align="center">
							������
						</div>
					</td>
					<td>
						<input type="text" id="dkje" name="dkje"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="dkje" />" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<input type="text" id="dkqx" name="dkqx"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="dkqx" />" readonly="readonly">
					</td>
					<td>
						<div align="center">
							����������
						</div>
					</td>
					<td>
						<input type="text" id="nll" name="nll"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="nll" />" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�����ʻ�����
						</div>
					</td>
					<td>
						<input type="text" id="hkzhlx" name="hkzhlx"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="hkzhlx" />" maxlength="40">
					</td>
					<td>
						<div align="center">
							�����ʻ�����
						</div>
					</td>
					<td>
						<input type="text" id="hkzhhm" name="hkzhhm"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="hkzhhm" />" maxlength="30">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ͬ���
						</div>
					</td>
					<td>
						<input type="text" id="htbh" name="htbh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="htbh" />" readonly="readonly">
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<input type="text" id="spbbh" name="spbbh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="spbbh" />" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ע
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bz" name="bz"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="bz" />" maxlength="200">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2"
					onClick="yz();">
					�ύ����
				</button>
				<button class="button2"
					onClick="toPrintOut();">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
			</div>
	</logic:equal>
		</html:form>
</body>
</html:html>
