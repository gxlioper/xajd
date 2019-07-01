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
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript">
			function yz(){
				var isAllInput = checkAllInput('xh!!jzr1!!sfzh1!!sxnpddd!!bjgbxkms!!dkje');
				if(isAllInput){
					document.forms[0].action = "/xgxt/nbty_xszz.do?method=gjzxdksq&type=add";
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
			��ǰ����λ�ã���ѧ���� - ������ѧ��������
		</div>
	</div>
	<html:form action="/nbty_xszz.do?method=gjzxdksq" method="post">
		<input type="hidden" id="url" name="url"
			value="/nbty_xszz.do?method=gjzxdksq" />
		<input type="hidden" name="save_sqsj" value="${sqsj }" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xh-xm" />
		<input type="hidden" id="delStuInfo" name="getStuInfo"
			value="xh-xm-xz-nj-xb-xymc-zymc-bjmc-csrq-sfzh-ssbh-qsdh-jtdh-jtyb-jtdz-lxdh" />
		<table class="tbstyle" width="90%">
			<tr>
				<logic:notEqual name="userType" value="stu">
					<td align="center" width="16%">
						<font color="red">*</font>ѧ��
					</td>
					<td align="left" width="34%">
						<html:text property="save_xh" styleId="xh" readonly="readonly"
							onchange="checkXhExists($('delStuInfo').value);"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</td>
				</logic:notEqual>
				<logic:equal name="userType" value="stu">
					<td align="center" width="16%">
						<font color="red">*</font>ѧ��
					</td>
					<td align="left" width="34%">
						<html:text styleId="xh" property="save_xh"
							style="width:100%;heigh:100%" value="${rs.xh}" readonly="true" />
					</td>
				</logic:equal>
				<td width="16%">
					<div align="center">
						����
					</div>
				</td>
				<td width="34%">
					<input type="text" readonly="readonly" id="xm" name="xm"
						style="width:100%;heigh:100%" value="${rs.xm }"
						readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�Ա�
					</div>
				</td>
				<td>
					<input type="text" id="xb" readonly="readonly" name="xb"
						style="width:100%;heigh:100%" value="${rs.xb }" />
				</td>
				<td>
					<div align="center">
						��������
					</div>
				</td>
				<td>
					<input type="text" readonly style="cursor:hand;width:100%"
						onclick="return showCalendar('csrq','y-mm-dd');"
						value="${rs.csrq}" name="csrq" id="csrq" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<font color="red">*</font>ѧ��
					</div>
				</td>
				<td>
					<html:text styleId="xn" property="save_xn"
						style="width:100%;heigh:100%" value="${rs.xn}" readonly="true"/>
				</td>
				<td>
					<div align="center">
						ѧ��
					</div>
				</td>
				<td>
					<input type="text" id="xz" readonly="readonly" name="xz"
						style="width:100%;heigh:100%" value="${rs.xz}" />
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
						style="width:100%;heigh:100%" value="${rs.sfzh }" />
				</td>
				<td>
					<div align="center">
						���˵绰
						<br />
					</div>
				</td>
				<td>
					<input type="text" id="lxdh" name="lxdh" maxlength="20"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.lxdh }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�꼶
					</div>
				</td>
				<td>
					<input type="text" id="nj" readonly="readonly" name="nj"
						style="width:100%;heigh:100%" value="${rs.nj }" />
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />����
					</div>
				</td>
				<td>
					<input type="text" id="xymc" name="xymc" readonly="readonly"
						style="width:100%;heigh:100%" value="${rs.xymc }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						רҵ����
					</div>
				</td>
				<td>
					<input type="text" id="zymc" readonly="readonly" name="zymc"
						style="width:100%;heigh:100%" value="${rs.zymc }" />
				</td>
				<td>
					<div align="center">
						�༶
					</div>
				</td>
				<td>
					<input type="text" id="bjmc" name="bjmc" readonly="readonly"
						style="width:100%;heigh:100%" value="${rs.bjmc }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						���ұ��
					</div>
				</td>
				<td>
					<input type="text" id="ssbh" name="ssbh" readonly="readonly"
						style="width:100%;heigh:100%" value="${rs.ssbh }" />
				</td>
				<td>
					<div align="center">
						���ҵ绰
						<br />
					</div>
				</td>
				<td>
					<input type="text" id="qsdh" name="qsdh" maxlength="20"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.qsdh }" />
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
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.jtszd }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��ͥ�ʱ�
					</div>
				</td>
				<td>
					<input type="text" id="jtyb" maxlength="6" name="jtyb"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.jtyb }" />
				</td>
				<td>
					<div align="center">
						��ͥ�绰
					</div>
				</td>
				<td>
					<input type="text" id="jtdh" name="jtdh" maxlength="20"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.lxdh1 }" />
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<font color="red">ע����ͥ�绰�밴������-�绰���롱��ʽ��д����ĸ���ѹʵ�����д���ѹʡ�����ְҵ�ģ��ڸ�ĸְҵ����д���ޡ���</font>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��������
					</div>
				</td>
				<td>
					<input type="text" id="fqxm" maxlength="40" name="fqxm"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.fqxm }">
				</td>
				<td>
					<div align="center">
						ĸ������
					</div>
				</td>
				<td>
					<input type="text" id="mqxm" name="mqxm" maxlength="40"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.mqxm }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						���׵�λ
					</div>
				</td>
				<td>
					<input type="text" id="fqdw" maxlength="40" name="fqdw"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.fqdw }" />
				</td>
				<td>
					<div align="center">
						ĸ�׵�λ
					</div>
				</td>
				<td>
					<input type="text" id="mqdw" name="mqdw" maxlength="40"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.mqdw }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�����ֻ�
					</div>
				</td>
				<td>
					<input type="text" id="fqsj" maxlength="18" name="fqsj"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.fqsj }" />
				</td>
				<td>
					<div align="center">
						ĸ���ֻ�
					</div>
				</td>
				<td>
					<input type="text" id="mqsj" name="mqsj" maxlength="18"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.mqsj }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<font color="red">*</font>��֤��1
					</div>
				</td>
				<td>
					<html:text styleId="jzr1" maxlength="40" property="save_jzr1"
						style="width:100%;heigh:100%" />
				</td>
				<td>
					<div align="center">
						<font color="red">*</font>��֤��1���֤��
					</div>
				</td>
				<td>
					<html:text styleId="sfzh1" property="save_sfzh1" maxlength="40"
						onblur="chkSfzh(this);" style="width:100%;heigh:100%" />
				</td>
			</tr>

			<tr>
				<td>
					<div align="center">
						��֤��2
					</div>
				</td>
				<td>
					<html:text styleId="jzr2" maxlength="40" property="save_jzr2"
						style="width:100%;heigh:100%" />
				</td>
				<td>
					<div align="center">
						��֤��2���֤��
					</div>
				</td>
				<td>
					<html:text styleId="sfzh2" property="save_sfzh2" maxlength="40"
						style="width:100%;heigh:100%" onblur="chkSfzh(this);"
						onkeyup="chkonlynum();" />
				</td>
			</tr>

			<tr>
				<td>
					<div align="center">
						<font color="red">*</font>��ѧ��Ʒ�µȵ�
					</div>
				</td>
				<td>
					<html:text styleId="sxnpddd" maxlength="40" property="save_sxnpddd"
						style="width:100%;heigh:100%" />
				</td>
				<td>
					<div align="center">
						<font color="red">*</font>�Ͷ��ڼ��ۼƲ�������޿�Ŀ��
					</div>
				</td>
				<td>
					<html:text styleId="bjgbxkms" property="save_bjgbxkms"
						onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="40"
						style="width:100%;heigh:100%" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						����Υ�ʹ���
				</td>
				<td>
					<input type="text" id="ywwjcf" maxlength="40" name="ywwjcf"
						style="width:100%;heigh:100%" readonly="readonly"
						value="${rs.ywwjcf }">
				</td>
				<td>
					<div align="center">
						��ҵʱ��
					</div>
				</td>
				<td>
					<input type="text" id="bysj" readonly="readonly" name="bysj"
						style="width:100%;heigh:100%" value="${rs.byny}" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�Ͷ��ڼ�ѧϰ�����������
					</div>
				</td>
				<td>
					�ţ�
					<html:radio property="save_xxqkztpj" value="��" />
					����
					<html:radio property="save_xxqkztpj" value="��" />
					һ�㣺
					<html:radio property="save_xxqkztpj" value="һ��" />
					�
					<html:radio property="save_xxqkztpj" value="��" />
				</td>
				<td>
					<div align="center">
						���޲������ü�¼
					</div>
				</td>
				<td>
					�У�
					<html:radio property="save_ywblxyjl" value="��" />
					�ޣ�
					<html:radio property="save_ywblxyjl" value="��" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<font color="red">*</font>������
					</div>
				</td>
				<td>
					<html:text styleId="dkje" maxlength="40" property="save_dkje"
						onkeyup="value=value.replace(/[^\d]/g,'')"
						style="width:100%;heigh:100%" />
				</td>
				<td>
					<div align="center">
						���ʽ
					</div>
				</td>
				<td>
					�ȶϢ�����
					<html:radio styleId="hkfs" property="save_hkfs" value="�ȶϢ���" />
					�ȶ�𻹿��
					<html:radio styleId="hkfs" property="save_hkfs" value="�ȶ�𻹿" />
				</td>
			</tr>

		</table>
		<logic:equal name="isApply" value="true">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="yz();">
					�ύ����
				</button>
			</div>
		</logic:equal>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
		</script>
	</logic:present>
</body>
</html:html>
