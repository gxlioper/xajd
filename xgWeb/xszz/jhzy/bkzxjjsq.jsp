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
		function bkzxSave(){
			var xh = document.getElementById('xh').value;
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			document.forms[0].action = "/xgxt/jhzyjsxy_xszz.do?method=bkzxjjsqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/jhzyjsxy_xszz.do?method=bkzxjjsqb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ������ѧ���������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�
			</p>
		</center>
	</logic:equal>
		<html:form action="jhzyjsxy_xszz" method="post">
			<input type="hidden" id="url" name="url" value="/jhzyjsxy_xszz.do?method=bkzxjjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="fdysh" name="fdysh"
				value="<bean:write name="rs" property="fdysh" />">
			<input type="hidden" id="fdyshyj" name="fdyshyj"
				value="<bean:write name="rs" property="fdyshyj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="msg" name="msg"
				value="${msg}">

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
	         			alert("��ͨ��ѧУ��ˣ��������룡");
	         		</script>
				</logic:match>
			</logic:present>
			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" width="16%">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:notEqual name="type" value="modi">
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do?kns=bkzx',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
							</logic:notEqual>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" width="16%">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="34%">
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
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
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
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<td>
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td>
						<input type="text" id="rxrq" name="rxrq" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxrq"/>">
					</td>
				</tr>
				<tr>
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
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
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
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />����
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
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
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<td>
						<div align="center">
							�༶����
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" readonly="readonly" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
					<td>
						<div align="center">
							���
						</div>
					</td>
					<td>
						<input type="text" id="nd" name="nd" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nd"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ͥסַ���ʱ�
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtzzjyb" maxlength="100" name="jtzzjyb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzzjyb"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ͥ�꾭�ô�����
						</div>
					</td>
					<td>
						<input type="text" id="jtnjjcsr" maxlength="5" name="jtnjjcsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtnjjcsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							��ͥ�˿���
						</div>
					</td>
					<td>
						<input type="text" id="jtrks" name="jtrks" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<input type="text" id="dy" maxlength="10" name="dy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dy"/>">
					</td>
					<td>
						<div align="center">
							ѧ�ѽ������
						</div>
					</td>
					<td>
						<input type="text" id="xfjnqk" maxlength="100" name="xfjnqk" style="width:100%"
						value="<bean:write name="rs" property="xfjnqk"/>"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ѧ������
						</div>
					</td>
					<td>
						<html:textarea name="rs" property="zxdkjje" rows='5' style="width:100%" onblur="chLeng(this,200)"/>
					</td>
					<td>
						<div align="center">
							����ҡ�У��ѧ����
						</div>
					</td>
					<td>
						<html:textarea name="rs" property="gjxjxjje" rows='5' style="width:100%" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ѧ������������־����ѧ�𼰽��
						</div>
					</td>
					<td>
						<html:textarea name="rs" property="gjlzzxjjje" rows='5' style="width:100%" onblur="chLeng(this,200)"/>
					</td>
					<td>
						<div align="center">
							��ѧ������Ѳ��������
						</div>
					</td>
					<td>
						<html:textarea name="rs" property="bxnhknbzjje" rows='5' style="width:100%" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ѧϰ�������
						</div>
					</td>
					<td	colspan="3">
						<html:textarea name="rs" property="xxztqk" rows='5' style="width:100%" onblur="chLeng(this,300)"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							���ܺ��ֽ���
						</div>
					</td>
					<td	colspan="3">
						<html:textarea name="rs" property="cshzjl" rows='5' style="width:100%" onblur="chLeng(this,300)"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							���ܺ���Υ�ʹ���
						</div>
					</td>
					<td	colspan="3">
						<html:textarea name="rs" property="cshzwjcf" rows='5' style="width:100%" onblur="chLeng(this,300)"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ѧ���������ȼ������
						</div>
					</td>
					<td colspan="3">
						<html:select name="rs" property="zxjjdm" styleId="zxjjdm"
										onchange="">
							<html:option value=""></html:option>
							<html:options collection="zzdjList" property="dm"
											labelProperty="dj" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly');chLeng(this,1000)"/>
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
			</table>
			<logic:equal name="sfksq" value="1">
				<logic:notPresent name="msg">
				<div class="buttontool" id="btn" style="position: absolute;width:90%">
					<button type="button" class="button2"
						onClick="bkzxSave();">
						�ύ����
					</button>
					<button type="button" class="button2"
						onClick="toPrintOut();">
						��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
					</button>
				</div>
				</logic:notPresent>
				<logic:present name="msg">
				<div class="buttontool" id="btn" style="position: absolute;width:90%">
					<button type="button" class="button2" disabled="disabled"
						onClick="bkzxSave();">
						�ύ����
					</button>
					<button type="button" class="button2"
						onClick="toPrintOut();">
						��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
					</button>
				</div>
				</logic:present>
			</logic:equal>
		</html:form>
	</body>
	<logic:present name="msg">
		<script>
			alert(''+document.getElementById('msg').value);
		</script>
	</logic:present>
</html:html>
