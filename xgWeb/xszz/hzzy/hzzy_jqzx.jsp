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
	          		 alert("��������(��ͥ���)���ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/hzzy_jqzx.do?doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(titName){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/hzzy_jqzxb.do";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - �����������ܻ�"������ѧ"����ѧ���ǼǱ�
		</div>
	</div>
		<html:form action="hzzy_jqzx.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url" value="/hzzy_jqzx.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" scope="request" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" scope="request" />">

			<logic:present name="notSQ">
				<logic:match value="is" name="notSQ">
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
						<td align="center" width="16%">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="34%">
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
							���֤��
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td>
						<input type="text" id="zzmmmc" readonly="readonly" name="zzmmmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
					<td>
						<div align="center">
							��ѧʱ��
						</div>
					</td>
					<td>
						<input type="text" id="rxny" name="rxny" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxny"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							רҵ
						</div>
					</td>
					<td>
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
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
							��ͥ�˿���
						</div>
					</td>
					<td>
						<input type="text" id="jtrks" readonly="readonly" name="jtrks"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrks"/>">
					</td>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" name="lxdh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtzz" readonly="readonly" name="jtzz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzz"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��������(��ͥ���)
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
			</table>
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

		</html:form>
</body>
</html:html>
