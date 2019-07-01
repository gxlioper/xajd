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
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript">
			function yz(){
				var temp = checkAllInput('xh!!xn!!pddd');
				if(temp){
					document.forms[0].action = "/xgxt/nbty_qhtsjxj.do?method=qhtsjxjupdate";
					document.forms[0].submit();
					return true;
				}
				return false;
			}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã��������� - �庮��ʹ��ѧ�𵥸����
		</div>
	</div>
	<html:form action="/nbty_qhtsjxj.do?method=qhtsjxjone" method="post">
		<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }" />
		<input type="hidden" name="destination" value="qhtsjxjshone"/>
		<table class="tbstyle" width="90%">
			<tr>

				<td align="center" width="20%">
					ѧ��
				</td>
				<td align="left" width="30%">
					<html:text styleId="xh" property="save_xh"
						style="width:100%;heigh:100%" value="${rs.xh}" readonly="true" />
				</td>
				<td width="20%">
					<div align="center">
						����
					</div>
				</td>
				<td width="30%">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�Ա�
					</div>
				</td>
				<td>
					${rs.xb }
				</td>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td>
					${rs.mzmc }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��������
					</div>
				</td>
				<td>
					${rs.csrq }
				</td>
				<td>
					<div align="center">
						�꼶
					</div>
				</td>
				<td>
					${rs.nj }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						ѧ��
					</div>
				</td>
				<td>
					<input type="text" id="xn" readonly="readonly" name="save_xn"
						style="width:100%;heigh:100%" value="${rs.xn}" />
				</td>
				<td>
					<div align="center">
						<font color="red">*</font>Ʒ�µȵ�
					</div>
				</td>
				<td>
					<input type="text" id="pddd" maxlength="40" name="save_pddd"
						style="width:100%;heigh:100%" value="${rs.pddd}" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
					${rs.xymc }
				</td>
				<td>
					<div align="center">
						רҵ
					</div>
				</td>
				<td>
					${rs.zymc }
				</td>
			</tr>
			<tr>

				<td>
					<div align="center">
						�༶
					</div>
				</td>
				<td>
					${rs.bjmc }
				</td>
				<td align="center">
					����Ա��ˣ�
				</td>
				<td>
					${rs.bjsh }
				</td>

			</tr>

			<tr align="left">

				<td>
					<div align="right">
						<bean:message key="lable.xsgzyxpzxy" />���
					</div>
				</td>
				<td>
					${rs.xysh }
				</td>
				<td align="right">
					ѧУ��ˣ�
				</td>
				<td>
					${rs.xxsh }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�Ƿ�μ��ڹ���ѧ
					</div>
				</td>
				<td>
					<html:select styleId="sfcjqgzx" property="save_sfcjqgzx"
						style="width:100%;heigh:100%" value="${rs.sfcjqgzx}">
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
					</html:select>

				</td>
				<td>
					<div align="center">
						�Ƿ�������ѧ����
					</div>
				</td>
				<td>
					<html:select styleId="sfsqzxdk" property="save_sfsqzxdk"
						style="width:100%;heigh:100%" value="${rs.sfsqzxdk}">
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
					</html:select>

				</td>
			</tr>
			<tr align="left">
				<td align="center">
					����Ա��ˣ�
				</td>
				<td>
					<logic:equal value="fdy" name="userType">
						<input type="hidden" name="save_bjshsj" value="${nowtime }" />
						<html:select property="save_bjsh" value="${rs.bjsh}">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</logic:equal>
					<logic:notEqual value="fdy" name="userType">
						<input type="text" id="fdysh" name="fdysh" maxlength="40"
							readonly="readonly" style="width:100%;heigh:100%"
							value="${rs.bjsh }" />
					</logic:notEqual>
				</td>
				<td>
					<div align="right">
						<bean:message key="lable.xsgzyxpzxy" />���
					</div>
				</td>
				<td>
					<logic:equal value="xy" name="userType">
						<input type="hidden" name="save_xyshsj" value="${nowtime }" />
						<html:select property="save_xysh" value="${rs.xysh}">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<input type="text" id="xysh" name="xysh" maxlength="40"
							readonly="readonly" style="width:100%;heigh:100%"
							value="${rs.xysh }" />
					</logic:notEqual>
				</td>
			</tr>
			<tr align="left">
				<td align="right">
					ѧУ��ˣ�
				</td>
				<td>
					<logic:equal value="xx" name="userType">
						<input type="hidden" name="save_xxshsj" value="${nowtime }" />
						<html:select property="save_xxsh" value="${rs.xxsh}">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</logic:equal>
					<logic:notEqual value="xx" name="userType">
						<input type="text" id="fdysh" name="xxsh" maxlength="40"
							readonly="readonly" style="width:100%;heigh:100%"
							value="${rs.xxsh }" />
					</logic:notEqual>
				</td>
			</tr>
			<tr align="left">
				<td align="right">
					���������<br />
					<font color="red">(������200����)</font>
				</td>
				<td colspan="3">
					<html:textarea name='rs' property='save_jcqk' style="width:99%"
						rows='5' value="${rs.jcqk}" onblur="chLeng(this,300)" />
				</td>
			</tr>
			<tr align="left">
				<td align="right">
					��ѧ�����������д����������	<br />
					<font color="red">(������200����)</font>
				</td>
				<td colspan="3">
					<html:textarea name='rs' property='save_hxnhzzz' style="width:99%"
						rows='5' value="${rs.hxnhzzz}" onblur="chLeng(this,300)" />
			</td>
			</tr>
			<tr align="left">
				<td align="right">
					�������ɣ�<br />
					<font color="red">(������800����)</font>
				</td>
				<td colspan="3">
					<html:textarea name='rs' property='save_sqly' style="width:99%"
						rows='5' value="${rs.sqly}" onblur="chLeng(this,300)" />
				</td>
			</tr>
			<tr align="left">
				<td align="right">
					�༶����:<br />
					<font color="red">(������300����)</font>
				</td>
				<td colspan="3">
					<logic:equal value="fdy" name="userType">
						<html:textarea name='rs' property='save_bjpy' style="width:99%"
							rows='5' value="${rs.bjpy}" onblur="chLeng(this,300)" />
					</logic:equal>
					<logic:notEqual value="fdy" name="userType">
						<html:textarea name='rs' property='bjpy' style="width:99%"
							rows='5' value="${rs.bjpy}" readonly="true"
							onblur="chLeng(this,300)" />
					</logic:notEqual>
				</td>
			</tr>
			<tr align="left">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��������<br />
					<font color="red">(������100����)</font>
				</td>
				<td colspan="3">
					<logic:equal value="xy" name="userType">
						<html:textarea name='rs' property='save_xyshyj' style="width:99%"
							rows='5' value="${rs.xyshyj}" onblur="chLeng(this,200)" />
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<html:textarea name='rs' property='xyshyj' style="width:99%"
							rows='5' readonly="true" value="${rs.xyshyj}"
							onblur="chLeng(this,200)" />
					</logic:notEqual>
				</td>
			</tr>
			<tr align="left">
				<td align="right">
					ѧУ��������<br />
					<font color="red">(������100����)</font>
				</td>
				<td colspan="3">
					<logic:equal value="xx" name="userType">
						<html:textarea name='rs' property='save_xxshyj' style="width:99%"
							rows='5' value="${rs.xxshyj}" onblur="chLeng(this,200)" />
					</logic:equal>
					<logic:notEqual value="xx" name="userType">
						<html:textarea name='rs' property='xxshyj' style="width:99%"
							rows='5' readonly="true" value="${rs.xxshyj}"
							onblur="chLeng(this,200)" />
					</logic:notEqual>
				</td>
			</tr>

		</table>
		<div class="buttontool" align="center">
			<button type="button" class="button2" onclick="yz();" style="width:80px"
				id="buttonSave">
				�� ��
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
				id="buttonClose">
				�� ��
			</button>
		</div>
	</html:form>
	<logic:present name="result">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				window.dialogArguments.document.getElementById('search_go').click();	
			}
		</script>
	</logic:present>
</body>
</html:html>
