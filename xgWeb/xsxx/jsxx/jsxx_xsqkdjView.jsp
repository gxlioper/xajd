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


	<title><bean:message key="lable.title" /></title>
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
		function tj(){
			var xh = document.getElementById('xh').value;
			var rxcj = document.getElementById('rxcj').value;
			var bz = document.getElementById('bz').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((rxcj == null) || (rxcj == "")){
				alert("��ѧ�ɼ�����Ϊ��!");
				return false;
			}
			if(bz.length > 250){
				alert("��ע�������ܴ���250��!");
				return false;
			}		
			document.forms[0].action = "/xgxt/stu_info_add.do?method=jsxxXsdjbSave&type=edit";
			document.forms[0].submit();
		}
		function printXs(type){
			var pk = document.getElementById('pk').value;
			if(type == "stu"){
				window.open('stu_info_add.do?method=jsxxXsdjbPrint&pk='+pk);
			}
			if(type == "nostu"){
				window.open('stu_info_add.do?method=jsxxXsdjbPrint&type=nostu&pk='+pk);
			}
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ����Ϣ -  ��������ǼǱ�
		</div>
	</div>
		<html:form action="zgmsxy_xszz.do?method=zgmy_gjzxdksq" method="post">
			<input type="hidden" id="url" name="url"
				value="/stu_info_add.do?method=jsxxXsdjbSave" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pk" name="pk" value="${pk}">
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
			<input type="hidden" id="msg" name="msg" value="${msg}"/>
			<input type="hidden" id="nd" name="nd"
				value="">
			<logic:present name="msg">
				<script>
					alert(''+document.getElementById('msg').value);
					Close();
				</script>
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
			<logic:equal name="userType" value="stu">
			<logic:empty name="rs">
				<center>
					<p>
						��½�û��������������ܷ���!!!
					</p>
				</center>
			</logic:empty>
			</logic:equal>
			<logic:notEmpty name="rs">
			<table class="tbstyle" width="90%" align="center">
				<thead>
					<tr valign="middle" >
						<td align="center" colspan="4">
							<b style="font-size:14">����������Ϣ</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" width="16%">
						ѧ��
					</td>
					<td align="left" width="34%">
						<input type="text" id="xh" name="rs" property="xh" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xh" />" readonly="true">
					</td>
					<td>
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
				</tr>
				<tr>
					<td width="16%">
						<div align="center">
							����
						</div>
					</td>
					<td width="34%">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
					<td width="16%">
						<div align="center">
							������
						</div>
					</td>
					<td width="34%">
						<input type="text" readonly="readonly" id="cym" name="cym"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="cym"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<input type="text" id="csrq" readonly="readonly" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ϵ��
						</div>
					</td>
					<td>
						<input type="text" id="xymc" readonly="readonly" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
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
				</tr>
				<tr>
					<td>
						<div align="center">
							׼��֤��
						</div>
					</td>
					<td>
						<input type="text" id="zkzh" readonly="readonly" name="zkzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zkzh"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��ѧ�ɼ�
						</div>
					</td>
					<td>
						<input type="text" id="rxcj" name="rxcj"
							style="width:100%;heigh:100%" maxlength="3"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							value="<bean:write name="rs" property="rxcj"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%" maxlength="18"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
					<td>
						<div align="center">
							���˵绰
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" name="lxdh"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name="rs" property="lxdh"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ͥ��ϸ��ַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtdz" name="jtdz" maxlength="200"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name="rs" property="jtdz"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ͥ�ʱ�
						</div>
					</td>
					<td>
						<input type="text" id="yzbm" maxlength="6" name="yzbm"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							լ��
						</div>
					</td>
					<td>
						<input type="text" id="jtdh" name="jtdh" readonly="readonly"
							style="width:100%;heigh:100%" maxlength="18"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							value="<bean:write name="rs" property="jtdh"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ʱ�εغ�������(��)
						</div>
					</td>
					<td colspan="3">
						<html:text name='rs' property="rdsj" styleId="rdsj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('rdsj','y-mm-dd');" />��ʱ�䣩
						<input type="text" id="rddd"  name="rddd"
							style="width:20%;heigh:100%" maxlength="10"
							value="<bean:write name="rs" property="rddd"/>">���ص㣩&nbsp;&nbsp;	
						<input type="text" id="rdjsr" name="rdjsr"
							style="width:20%;heigh:100%" maxlength="10"
							value="<bean:write name="rs" property="rdjsr"/>">�������ˣ�
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							���˰����س�
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="grahtc" name="grahtc" 
						value="<bean:write name="rs" property="grahtc"/>" 
						style="width:100%"/>
					</td>
				</tr>
			</table>
			<table class="tbstyle" width="90%" align="center">
				<thead>
					<tr valign="middle" >
						<td align="center" colspan="5">
							<b style="font-size:14">������Ҫ����</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" width="5%">
						���
					</td>
					<td align="center" width="15%">
						��ʱ��
					</td>
					<td align="center" width="15%">
						��ʱֹ
					</td>
					<td align="center">
						�ںεأ���ѧУ��ε�λѧϰ������	
					</td>
					<td align="center" width="20%">
						�κ�ְ
					</td>
				</tr>
				<tr>
					<td align="center">
						1:
					</td>
					<td align="center">
						<html:text name='rs' property="grjlkssj1" styleId="grjlkssj1"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('grjlkssj1','y-mm-dd');" />
					</td>
					<td align="center">
						<html:text name='rs' property="grjljssj1" styleId="grjljssj1"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('grjljssj1','y-mm-dd');" />
					</td>
					<td align="center">
						<input type="text" id="grjlnr1"  name="grjlnr1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="grjlnr1"/>">
					</td>
					<td align="center">
						<input type="text" id="grjlzw1"  name="grjlzw1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="grjlzw1"/>">
					</td>
				</tr>
				<tr>
					<td align="center">
						2:
					</td>
					<td align="center">
						<html:text name='rs' property="grjlkssj2" styleId="grjlkssj2"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('grjlkssj2','y-mm-dd');" />
					</td>
					<td align="center">
						<html:text name='rs' property="grjljssj2" styleId="grjljssj2"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('grjljssj2','y-mm-dd');" />
					</td>
					<td align="center">
						<input type="text" id="grjlnr2"  name="grjlnr2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="grjlnr2"/>">
					</td>
					<td align="center">
						<input type="text" id="grjlzw2"  name="grjlzw2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="grjlzw2"/>">
					</td>
				</tr>
				<tr>
					<td align="center">
						3:
					</td>
					<td align="center">
						<html:text name='rs' property="grjlkssj3" styleId="grjlkssj3"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('grjlkssj3','y-mm-dd');" />
					</td>
					<td align="center">
						<html:text name='rs' property="grjljssj3" styleId="grjljssj3"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('grjljssj3','y-mm-dd');" />
					</td>
					<td align="center">
						<input type="text" id="grjlnr3"  name="grjlnr3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="grjlnr3"/>">
					</td>
					<td align="center">
						<input type="text" id="grjlzw3"  name="grjlzw3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="grjlzw3"/>">
					</td>
				</tr>
			</table>
			<table class="tbstyle" width="90%" align="center">
				<thead>
					<tr valign="middle" >
						<td align="center" colspan="8">
							<b style="font-size:14">��ͥ��Ա���</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" width="5%">
						���
					</td>
					<td align="center" width="10%">
						��ν
					</td>
					<td align="center" width="10%">
						����
					</td>
					<td align="center" width="10%">
						����
					</td>
					<td align="center" width="10%">
						������ò	
					</td>
					<td align="center">
						�ε�λ�����Ź���
					</td>
					<td align="center" width="10%">
						��������
					</td>
					<td align="center" width="10%">
						��ϵ�绰
					</td>
				</tr>
				<tr>
					<td align="center">
						1��
					</td>
					<td align="center">
						<input type="text" id="jtcych1"  name="jtcych1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcych1"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcyxm1"  name="jtcyxm1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcyxm1"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcynl1"  name="jtcynl1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcynl1"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcyzzmm1"  name="jtcyzzmm1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcyzzmm1"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcydw1"  name="jtcydw1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcydw1"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcysr1"  name="jtcysr1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcysr1"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcydh1"  name="jtcydh1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcydh1"/>">
					</td>
				</tr>
				<tr>
					<td align="center">
						2��
					</td>
					<td align="center">
						<input type="text" id="jtcych2"  name="jtcych2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcych2"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcyxm2"  name="jtcyxm2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcyxm2"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcynl2"  name="jtcynl2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcynl2"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcyzzmm2"  name="jtcyzzmm2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcyzzmm2"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcydw2"  name="jtcydw2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcydw2"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcysr2"  name="jtcysr2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcysr2"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcydh2"  name="jtcydh2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcydh2"/>">
					</td>
				</tr>
				<tr>
					<td align="center">
						3��
					</td>
					<td align="center">
						<input type="text" id="jtcych3"  name="jtcych3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcych3"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcyxm3"  name="jtcyxm3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcyxm3"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcynl3"  name="jtcynl3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcynl3"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcyzzmm3"  name="jtcyzzmm3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcyzzmm3"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcydw3"  name="jtcydw3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcydw3"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcysr3"  name="jtcysr3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcysr3"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcydh3"  name="jtcydh3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcydh3"/>">
					</td>
				</tr>
				<tr>
					<td align="center">
						4��
					</td>
					<td align="center">
						<input type="text" id="jtcych4"  name="jtcych4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcych4"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcyxm4"  name="jtcyxm4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcyxm4"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcynl4"  name="jtcynl4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcynl4"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcyzzmm4"  name="jtcyzzmm4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcyzzmm4"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcydw4"  name="jtcydw4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcydw4"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcysr4"  name="jtcysr4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcysr4"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcydh4"  name="jtcydh4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcydh4"/>">
					</td>
				</tr>
			</table>
			<table class="tbstyle" width="90%" align="center">
				<thead>
					<tr valign="middle" >
						<td align="center" colspan="8">
							<b style="font-size:14">��Ҫ����ϵ���</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" width="5%">
						���
					</td>
					<td align="center" width="10%">
						��ν
					</td>
					<td align="center" width="10%">
						����
					</td>
					<td align="center" width="10%">
						����
					</td>
					<td align="center" width="10%">
						������ò	
					</td>
					<td align="center">
						�ε�λ�����Ź���
					</td>
					<td align="center" width="10%">
						��������
					</td>
					<td align="center" width="10%">
						��ϵ�绰
					</td>
				</tr>
				<tr>
					<td align="center">
						1��
					</td>
					<td align="center">
						<input type="text" id="shgxch1"  name="shgxch1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxch1"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxxm1"  name="shgxxm1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxxm1"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxnl1"  name="shgxnl1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxnl1"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxzzmm1"  name="shgxzzmm1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxzzmm1"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxdw1"  name="shgxdw1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxdw1"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxsr1"  name="shgxsr1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxsr1"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxdh1"  name="shgxdh1"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxdh1"/>">
					</td>
				</tr>
				<tr>
					<td align="center">
						2��
					</td>
					<td align="center">
						<input type="text" id="shgxch2"  name="shgxch2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxch2"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxxm2"  name="shgxxm2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxxm2"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxnl2"  name="shgxnl2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxnl2"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxzzmm2"  name="shgxzzmm2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxzzmm2"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxdw2"  name="shgxdw2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxdw2"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxsr2"  name="shgxsr2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxsr2"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxdh2"  name="shgxdh2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxdh2"/>">
					</td>
				</tr>
				<tr>
					<td align="center">
						3��
					</td>
					<td align="center">
						<input type="text" id="shgxch3"  name="shgxch3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxch3"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxxm3"  name="shgxxm3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxxm3"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxnl3"  name="shgxnl3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxnl3"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxzzmm3"  name="shgxzzmm3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxzzmm3"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxdw3"  name="shgxdw3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxdw3"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxsr3"  name="shgxsr3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxsr3"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxdh3"  name="shgxdh3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxdh3"/>">
					</td>
				</tr>
				<tr>
					<td align="center">
						4��
					</td>
					<td align="center">
						<input type="text" id="shgxch4"  name="shgxch4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxch4"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxxm4"  name="shgxxm4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxxm4"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxnl4"  name="shgxnl4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxnl4"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxzzmm4"  name="shgxzzmm4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxzzmm4"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxdw4"  name="shgxdw4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxdw4"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxsr4"  name="shgxsr4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxsr4"/>">
					</td>
					<td align="center">
						<input type="text" id="shgxdh4"  name="shgxdh4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shgxdh4"/>">
					</td>
				</tr>
			</table>
			<table class="tbstyle" width="90%" align="center">
				<thead>
					<tr valign="middle" >
						<td align="center" colspan="8">
							<b style="font-size:14">��ע</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center">
						<html:textarea property="bz" style="width:100%" rows='10' name="rs"  />
					</td>
				</tr>
			</table>
			<table class="tbstyle" width="90%" align="center">
				<thead>
					<tr valign="middle" >
						<td align="center" colspan="8">
							<b style="font-size:14">ѧ���ۺ��������</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td width="3%" align="center">
						�꼶
					</td>
					<td width="10%" align="center">
						ѧ��
					</td>
					<td width="10%" align="center">
						����
					</td>
					<td width="10%" align="center">
						����
					</td>
					<td width="10%" align="center">
						����
					</td>
					<td width="10%" align="center">
						�ۺϲ���
					</td>
					<td width="10%" align="center">
						����
					</td>
					<td width="" align="center">
						ѧ�꽱���������ְ���
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						һ�꼶
					</td>
					<td>
						��ѧ��
					</td>
					<td>
						<html:text name="stuInfo" property="yydcj" styleId="yydcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="yyzcj" styleId="yyzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="yytcj" styleId="yytcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="yykpf" styleId="yykpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="yybjpm" styleId="yybjpm" style="width:100%" readonly="true"/>
					</td>
					<td rowspan="2">
						<html:textarea name="stuInfo" property="yqk" styleId="yqk" rows="3" style="width:100%;" onblur=""/>
					</td>
				</tr>
				<tr>
					<td>
						��ѧ��
					</td>
					<td>
						<html:text name="stuInfo" property="yrdcj" styleId="yrdcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="yrzcj" styleId="yrzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="yrtcj" styleId="yrtcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="yrkpf" styleId="yrkpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="yrbjpm" styleId="yrbjpm" style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						���꼶
					</td>
					<td>
						��ѧ��
					</td>
					<td>
						<html:text name="stuInfo" property="rydcj" styleId="rydcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="ryzcj" styleId="ryzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="rytcj" styleId="rytcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="rykpf" styleId="rykpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="rybjpm" styleId="rybjpm" style="width:100%" readonly="true"/>
					</td>
					<td rowspan="2">
						<html:textarea name="stuInfo" property="rqk" styleId="rqk" rows="3" style="width:100%;" onblur=""/>
					</td>
				</tr>
				<tr>
					<td>
						��ѧ��
					</td>
					<td>
						<html:text name="stuInfo" property="rrdcj" styleId="rrdcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="rrzcj" styleId="rrzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="rrtcj" styleId="rrtcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="rrkpf" styleId="rrkpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="rrbjpm" styleId="rrbjpm" style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						���꼶
					</td>
					<td>
						��ѧ��
					</td>
					<td>
						<html:text name="stuInfo" property="sydcj" styleId="sydcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="syzcj" styleId="syzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="sytcj" styleId="sytcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="sykpf" styleId="sykpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="sybjpm" styleId="sybjpm" style="width:100%" readonly="true"/>
					</td>
					<td rowspan="2">
						<html:textarea name="stuInfo" property="sqk" styleId="sqk" rows="3" style="width:100%;" onblur=""/>
					</td>
				</tr>
				<tr>
					<td>
						��ѧ��
					</td>
					<td>
						<html:text name="stuInfo" property="srdcj" styleId="srdcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="srzcj" styleId="srzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="srtcj" styleId="srtcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="srkpf" styleId="srkpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="srbjpm" styleId="srbjpm" style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						���꼶
					</td>
					<td>
						��ѧ��
					</td>
					<td>
						<html:text name="stuInfo" property="xydcj" styleId="xydcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="xyzcj" styleId="xyzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="xytcj" styleId="xytcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="xykpf" styleId="xykpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="xybjpm" styleId="xybjpm" style="width:100%" readonly="true"/>
					</td>
					<td rowspan="2">
						<html:textarea name="stuInfo" property="xqk" styleId="xqk" rows="3" style="width:100%;" onblur=""/>
					</td>
				</tr>
				<tr>
					<td>
						��ѧ��
					</td>
					<td>
						<html:text name="stuInfo" property="xrdcj" styleId="xrdcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="xrzcj" styleId="xrzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="xrtcj" styleId="xrtcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="xrkpf" styleId="xrkpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="xrbjpm" styleId="xrbjpm" style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						���꼶
					</td>
					<td>
						��ѧ��
					</td>
					<td>
						<html:text name="stuInfo" property="fydcj" styleId="fydcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="fyzcj" styleId="fyzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="fytcj" styleId="fytcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="fykpf" styleId="fykpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="fybjpm" styleId="fybjpm" style="width:100%" readonly="true"/>
					</td>
					<td rowspan="2">
						<html:textarea name="stuInfo" property="fqk" styleId="fqk" rows="3" style="width:100%;" onblur=""/>
					</td>
				</tr>
				<tr>
					<td>
						��ѧ��
					</td>
					<td>
						<html:text name="stuInfo" property="frdcj" styleId="frdcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="frzcj" styleId="frzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="frtcj" styleId="frtcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="frkpf" styleId="frkpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="stuInfo" property="frbjpm" styleId="frbjpm" style="width:100%" readonly="true"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%" align="center">
				<button type="button" class="button2" onClick="printXs('nostu');">
					��ӡ
				</button>
			</div>
			</logic:notEmpty>
		</html:form>
</body>
</html:html>
