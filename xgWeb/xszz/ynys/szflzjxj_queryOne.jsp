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
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
	/*	
		function toPrintOut(titName){//�����Ӧ�Ĵ�ӡҳ��
			if (titName == "gjjxj"){
				document.forms[0].action = "/xgxt/gjjzxjsqb.do";
				document.forms[0].submit();
			} else if (titName == "gjzxj") {
				document.forms[0].action = "/xgxt/gjjzxjsqb.do";
				document.forms[0].submit();
			} else {
				document.forms[0].action = "/xgxt/xpjjdksqb.do";
				document.forms[0].submit();
			}
		}
	*/	
		function checkStuInfo(){
			var xh = document.getElementById("xh").value;
			if(xh == "" || xh == null){
				alert("�뽫ѧ�����ϣ�");
				return;
			}
			refreshForm("xszz_ynys.do?method=szflzjxjpre&doType=add");
		}
		
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ʡ������־��ѧ��
			<logic:equal value="true" name="sqms">- ����</logic:equal>
			<logic:notEqual value="true" name="sqms">- ���</logic:notEqual>
		</div>
	</div>
	<%--
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
	--%>
	<%--<logic:equal name="sfksq" value="1">
		--%>
	<html:form action="/xszz_ynys.do?method=szflzjxjpre" method="post">
		<%--<input type="hidden" id="titName" name="titName"
			value="<bean:write name="titName" scope="request" />">
		--%>
		<input type="hidden" id="url" name="url"
			value="/xszz_ynys.do?method=szflzjxjpre" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc-zzmmmc-zymc-xh-sfzh" />
		<%--<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">
		--%>
		<logic:present name="isPASS">
			<logic:match value="is" name="isPASS">
				<script language="javascript">
	         			alert("��ͨ����ˣ��������룡");
	         		</script>
			</logic:match>
		</logic:present>
		<logic:present name="kns">
			<logic:match value="no" name="kns">
				<script language="javascript">
	         			alert("�������������������룡");
	         		</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" style="width:99%">
			<tr>
				<logic:equal name="userOnLine" value="teacher" scope="session">
					<td align="center" colspan="2">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="xh" styleId="xh"
							onkeypress="autoFillStuInfo(event.keyCode,this)"
							readonly="readonly" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</td>
				</logic:equal>
				<logic:equal name="userOnLine" value="student" scope="session">
					<td align="center" colspan="2">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" colspan="2">
						<input type="text" id="xh" name="xh" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xh" />"
							readonly="readonly">
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
						value="<bean:write name="rs" property="xm"/>" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						���֤��
					</div>
				</td>
				<td colspan="2">
					<input type="text" id="sfzh" readonly="readonly" name="sfzh"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="sfzh"/>">
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
						������ò
					</div>
				</td>
				<td colspan="2">
					<input type="text" id="zzmmmc" readonly="readonly" name="zzmmmc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zzmmmc"/>">
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
				<%--�����Formȡ�û��Ǵ����ݿ���ȡ��ѧУ������--%>
				<td colspan="2">
					<logic:notEmpty name="rs" property="xxmc">
						<input type="text" id="xxmc" readonly="readonly" name="xxmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xxmc"/>">
					</logic:notEmpty>
					<logic:empty name="rs" property="xxmc">
						<input type="text" id="xxmc" readonly="readonly" name="xxmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="xxmc" scope="request"/>">
					</logic:empty>
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
					<input type="text" id="xymc" name="xymc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xymc"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						ϵ��
					</div>
				</td>
				<td colspan="2">
					<input type="text" readonly="readonly" id="zymc" name="zymc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zymc"/>">
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
				<td colspan="2">
					<div align="center">
						��ѧ���ۺ�<br />�����ɼ�
					</div>
				</td>
				<td colspan="2">
					<input type="text" id="sxncj" maxlength="20" name="sxncj"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="sxncj"/>">
				</td>
				<td>
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td colspan="2">
					<input type="text" id="lxdh" maxlength="15" name="lxdh"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxdh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					����
				</td>
				<td colspan="2">
					<input name="kh" id="kh" maxlength="30" type="text"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="kh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					������ֽ���
					<br>
					(50��������)
				</td>
				<td colspan="4">
					<html:textarea name="rs" property="chhzjl" rows='5'
						styleId="chhzjl" style="width:100%" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ͥ����
					</div>
				</td>
				<td colspan="2" align="center">
					<logic:present name="rs" property="jthk">
						<logic:match value="����" name="rs" property="jthk">
							<input type="radio" name="jthk" value="����" checked>&nbsp;&nbsp;A  ����
							<input type="radio" name="jthk" value="ũ��">&nbsp;&nbsp;B ũ��
						</logic:match>
						<logic:match value="ũ��" name="rs" property="jthk">
							<input type="radio" name="jthk" value="����">&nbsp;&nbsp;A  ����
							<input type="radio" name="jthk" value="ũ��" checked>&nbsp;&nbsp;B ũ��
						</logic:match>
						<logic:empty name="rs" property="jthk">
							<input type="radio" name="jthk" value="����">&nbsp;&nbsp;A  ����
							<input type="radio" name="jthk" value="ũ��" checked>&nbsp;&nbsp;B ũ��
						</logic:empty>	
					</logic:present>
					<logic:notPresent name="rs" property="jthk">
							<input type="radio" name="jthk" value="����" checked>&nbsp;&nbsp;A  ���� 
							<input type="radio" name="jthk" value="ũ��">&nbsp;&nbsp;B ũ��
					</logic:notPresent>
				</td>
				<td>
					<div align="center">
						��ͥ��������
					</div>
				</td>
				<td>
					<input type="text" id="jtrkzs" name="jtrkzs" maxlength="10"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtrkzs"/>"
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
					<input type="text" id="rjysr" name="rjysr" maxlength="10"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="rjysr"/>"
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
					<input type="text" name="srly" maxlength="50" name="srly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="srly"/>">
				</td>
				<td>
					<div align="center">
						��������
					</div>
				</td>
				<td>
					<input name="yzbm" id="yzbm" maxlength="6" type="text"
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
					<html:textarea name="rs" property="jtzz" rows='3'
						style="width:100%" onblur="yzdx(this,'jtzz')" />
					<input type="hidden" id="jtzz" name="jtzz" value="">
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					��������
				</td>
				<td colspan="4">
					<html:textarea name="rs" property="sqly" rows='5'
						style="width:100%" onblur="yzdx(this,'sqly')" />
					<input type="hidden" id="sqly" name="sqly" value="">
				</td>
			</tr>
		<%-- ���״̬ --%>
		<logic:notPresent name="sqms" scope="request">
		<logic:empty name="rs" property="sqms">	
			<logic:equal value="xy" name="userType" scope="request">
				<tr>
					<td colspan="2" align="center">
						<bean:message key="lable.xsgzyxpzxy" />���
					</td>
					<td colspan="2">
						<html:select property="xysh" name="rs" styleId="xysh"
							style="width:90px;padding-left:130px">
							<html:option value=""></html:option>
							<html:options collection="checkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td style="height:40px" colspan="2"  align="center">
							<bean:message key="lable.xsgzyxpzxy" />������
					</td>
					<td colspan="4">
						<html:textarea property="xyshyj" name="rs" rows="5"
							style="width:100%"></html:textarea>
					</td>
				</tr>
			</logic:equal>
			<logic:equal value="xx" name="userType" scope="request">
				<tr>
					<td colspan="2" align="center">
						<bean:message key="lable.xsgzyxpzxy" />���
					</td>
					<td colspan="2">
						<logic:empty name="rs" property="xysh">
							<input type="text" name="xysh" maxlength="50" name="xysh"
								style="width:100%;heigh:100%" readonly="readonly" value="ͨ��">
						</logic:empty>
						<logic:notEmpty name="rs" property="xysh">
							<input type="text" name="xysh" maxlength="50" name="xysh"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name="rs" property="xysh"/>">
						</logic:notEmpty>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td style="height:40px" colspan="2"  align="center">
							<bean:message key="lable.xsgzyxpzxy" />������
					</td>
					<td colspan="4">
						<html:textarea property="xyshyj" name="rs" rows="5"
							style="width:100%" readonly="true"></html:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						ѧУ���
					</td>
					<td colspan="2">
						<html:select property="xxsh" name="rs" styleId="xxsh"
							style="width:90px;padding-left:130px">
							<html:option value=""></html:option>
							<html:options collection="checkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="5" colspan="2">
						<div align="center">
							ѧУ������
						</div>
					</td>
					<td colspan="4">
						<html:textarea property="xxshyj" name="rs" rows="5"
							style="width:100%"></html:textarea>
					</td>
				</tr>
			</logic:equal>	
			</logic:empty>
		</logic:notPresent>	
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<logic:notEqual value="view" property="doType" name="rs">
				<button type="button" class="button2" onClick="checkStuInfo();" style="width:80px">
					�ύ����
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2"
					onClick="toPrintOut(document.getElementById('titName').value);"
					style="width:80px">
					�� ӡ
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:notEqual>
			<logic:equal value="teacher" name="userOnLine" scope="session">
				<button type="button" class="button2" onClick="Close();" style="width:80px">
					�ر�
				</button>
			</logic:equal>
		</div>
	</html:form>
</body>
<logic:notEmpty name="IsKns">
	<logic:equal name="IsKns" value="no">
		<script>
			alert("������������������!");
		</script>
	</logic:equal>
</logic:notEmpty>
<logic:present name="ok">
	<logic:match value="ok" name="ok">
		<script language="javascript">
	      	alert("����ɹ���");
	      	if(window.dialogArguments){
	      		dialogArgumentsQueryChick();
	      		Close();
	      	}
	    </script>
	</logic:match>
	<logic:match value="no" name="ok">
		<script language="javascript">
	      	alert("����ʧ�ܣ�");
	   	</script>
	</logic:match>
</logic:present>
</html:html>
