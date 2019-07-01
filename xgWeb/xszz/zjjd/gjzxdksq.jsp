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
		function yz(){
			var xh = document.getElementById('xh').value;
			var xfdk = document.getElementById('xfdk').value;
			var yjxf = document.getElementById('yjxf').value;
			var yxjtzz = document.getElementById('yxjtzz').value;
			var jtjjqk = document.getElementById('jtjjqk').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(yxjtzz != null){
	         	if(yxjtzz.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("��Ч��ͥ��ַ���ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if((xfdk == null) || (xfdk == "")){
				xfdk = "0";
			}
			if((yjxf == null) || (yjxf == "")){
				yjxf = "0";
			}
			if (Math.round(xfdk) > Math.round(yjxf)){
				 alert("������ܳ���Ӧ��ѧ�ѽ��!");
				 return false;
			}
	       	if(jtjjqk != null){
	         	if(jtjjqk.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("��ͥ����������ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=gjzxdksqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=gjzxdksqb";
			document.forms[0].submit();
		}
		function je(){
			var xfdk = document.getElementById('xfdk').value;
			var yjxf = document.getElementById('yjxf').value;
			
			if((xfdk == null) || (xfdk == "")){
				xfdk = "";
			}
			if((yjxf == null) || (yjxf == "")){
				yjxf = "";
			}
			if (Math.round(xfdk) > Math.round(yjxf)){
				xfdk = yjxf;
				alert("������ܳ���Ӧ��ѧ�ѽ��!");
			}
			
			document.getElementById('xfdk').value = xfdk;
			document.getElementById('yjxf').value = yjxf;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ������ѧ����
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="zjjdzyjsxy_xszz.do?method=gjzxdksq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zjjdzyjsxy_xszz.do?method=gjzxdksq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" />">
			<input type="hidden" id="fdysh" name="fdysh"
				value="<bean:write name="rs" property="fdysh" />">
			<input type="hidden" id="fdyshyj" name="fdyshyj"
				value="<bean:write name="rs" property="fdyshyj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">

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
						<td align="center" width="20%">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="30%">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" width="20%">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="30%">
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
							���֤��
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" readonly="readonly" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
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
							��ѧ����
						</div>
					</td>
					<td>
						<input type="text" id="rxny" name="rxny" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxny"/>">
					</td>
					<td>
						<div align="center">
							��ҵ����
						</div>
					</td>
					<td>
						<input type="text" id="byny" name="byny" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="byny"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							���
						</div>
					</td>
					<td>
						<input type="text" id="nd" readonly="readonly" name="nd"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nd"/>">
					</td>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<input type="text" id="xz" name="xz" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="4" style="padding:0;">
						<%@ include file="yhkh.jsp"%>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��Ч��ͥ��ַ
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="yxjtzz" rows='3'
							style="width:100%" onblur="yzdx(this,'yxjtzz')" />
						<input type="hidden" id="yxjtzz" name="yxjtzz" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" name="lxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<input type="text" id="yzbm" name="yzbm" maxlength="6"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�ҳ�����
						</div>
					</td>
					<td>
						<input type="text" id="jz1_xm" maxlength="50" name="jz1_xm"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jz1_xm"/>">
					</td>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="jz1_lxdh" name="jz1_lxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jz1_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�ҳ�����
						</div>
					</td>
					<td>
						<input type="text" id="jz2_xm" maxlength="50" name="jz2_xm"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jz2_xm"/>">
					</td>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="jz2_lxdh" name="jz2_lxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jz2_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							��ͥ�������
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="jtjjqk" rows='3'
							style="width:100%" onblur="yzdx(this,'jtjjqk')" />
						<input type="hidden" id="jtjjqk" name="jtjjqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						&nbsp;&nbsp;<font color="red">ע����ͥ�����������ϸ��д����ע����ͥ�˿������������Դ�����˾����롣</font>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							Ӧ��ѧ��
						</div>
					</td>
					<td>
						<input type="text" id="yjxf" maxlength="6" name="yjxf"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="yjxf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							������
						</div>
					</td>
					<td>
						<input type="text" id="xfdk" name="xfdk" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="xfdk"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ѧ����<br />�������Ŀ
						</div>
					</td>
					<td>
						<input type="text" id="rxlbjgkm" maxlength="3" name="rxlbjgkm"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="rxlbjgkm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							��ѧ������<br />��ͨ����Ŀ
						</div>
					</td>
					<td>
						<input type="text" id="rxlbktgkm" name="rxlbktgkm" maxlength="3"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="rxlbktgkm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="yz();">
					�ύ����
				</button>
				<button type="button" class="button2" onClick="toPrintOut();">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
			</div>
	</logic:equal>
		</html:form>
</body>
</html:html>
