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
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
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
		function subdata(){
			var cfsj = document.getElementById('cfsj').value;
			var cfwh = document.getElementById('cfwh').value;
			if (cfsj == '' || cfwh == '' || cfsj == null || cfwh == null){
				alert('�뽫��*�ŵ���Ϣ��д������');		
			}
		}
</script>
<script type="text/javascript">
			function getwh(tid) {
				var xxdm='';
				if ($('xxdm')) {
					xxdm = document.getElementById('xxdm').value;
				}
				if (xxdm=='10856') {
					var cfwh = document.getElementById(tid).value;
					if (cfwh==null || cfwh=='') {
						document.getElementById(tid).value='������ѧ[][]��';
					}	
				}
			}
		</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<html:form action="/shgcwjcfwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					${tips }
				</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="xxdm" value="${xxdm }"/>
			<input type="hidden" name="clwh" id="clwh" value="<bean:write name="clwh"/>"/>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							 ${tit }
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 20%">
						ѧ�ţ�
					</td>
					<td align="left" style="width: 30%">
						<bean:write name="rs"  property="xh"/>
					</td>
					<td align="right">
						��ȣ�
					</td>
					<td align="left">
						<bean:write name="rs"  property="nd"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name="rs" property="xm"/>
					</td>
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="rs" property="xn"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name="rs" property="xb" />
					</td>
					<td align="right">
						�������
					</td>
					<td align="left">
						<logic:equal value="11049" name="xxdm">
							<html:select property="cflb" styleId="cflb" style="width:150" styleClass="select">
								<html:options collection="cflbList" property="cflbdm" labelProperty="cflbmc"/>
							</html:select>
						</logic:equal>
						<logic:notEqual value="11049" name="xxdm">
							<bean:write name="rs" property="cflbmc"/>
							<input type="hidden" name="cflb" value="${cflb }"/>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="nj"/>
					</td>
					<td align="right">
						����ԭ��
					</td>
					<td align="left">
						<logic:equal value="11049" name="xxdm">
							<html:select property="cfyy" styleId="cfyy" style="width:150" styleClass="select">
							<html:options collection="cfyyList" property="cfyydm" labelProperty="cfyymc"/>
						</html:select>
						</logic:equal>
						<logic:notEqual value="11049" name="xxdm">
							<bean:write name="rs" property="cfyymc"/>
							<input type="hidden" name="cflb" value="${cfyy }"/>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>
					</td>
					<td align="right">
					<logic:equal value="11049" name="xxdm">ѧ������ˣ�</logic:equal>
					<logic:notEqual value="11049" name="xxdm">��ˣ�</logic:notEqual>						
					</td>
					<td align="left">
					<bean:write name="rs" property="xxsh"/>
						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name="rs"  property="zymc"/>
					</td>
						<td align="right">
						<font color="red">*</font>�������ڣ�
						</td>
						<td align="left">
						<html:text property="cfsj" styleId="cfsj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('cfsj','y-mm-dd');" />
						</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
						<td align="right">
						<font color="red">*</font>�����ĺţ�
						</td>
						<td align="left">
								<html:text property="cfwh" styleId="cfwh" onclick="getwh('cfwh');"/>
						</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="if (document.getElementById('cfsj').value=='' ||document.getElementById('cfwh').value=='') subdata(); else {savexxsh_shgc('savecfrqwh.do?pkVal=');BatAlert.showTips('���ڲ�������ȴ�...');}"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>	
			</div>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("�����ɹ�");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
			<logic:equal value="noview" name="result">
			<script>
				alert("����ʧ��");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
