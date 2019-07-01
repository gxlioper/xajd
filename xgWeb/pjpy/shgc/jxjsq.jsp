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
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var sqly = document.getElementById('sqly').value;
			var hjqk = document.getElementById('hjqk').value;
			var xslwfb = document.getElementById('xslwfb').value;
			var zljs = document.getElementById('zljs').value;
			var zdyzdXxxx = document.getElementById('zdyzdXxxx').value;
			var str = new Array();
			var strT = new Array();
			var i = 1;
			var j = 1;
			if(xh == null){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(hjqk != null){
	         	if(hjqk.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("��������ܴ���2000���ַ���");
	          		 return false;
	       		 }
	       	}
	       	if(xslwfb != null){
	         	if(xslwfb.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("ѧ�����ķ����ܴ���2000���ַ���");
	          		 return false;
	       		 }
	       	}
	       	if(zljs != null){
	         	if(zljs.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("ר���������ܴ���2000���ַ���");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("�������ɲ��ܴ���2000���ַ���");
	          		 return false;
	       		 }
	       	}
			str = zdyzdXxxx.split("!!TwoSpile!!");
	       	for(i = 1; i < str.length; i++) {
	       		strT = str[i].split("!!OneSpile!!");
	       		for(j = 1; j < strT.length; j++){
	       			if(strT[4] == "�ı���"){
	       				var temp = document.getElementById('zdy'+strT[2]).value.trim();
	       				if(temp != null){
	         				if(temp.replace(/[^\u0000-\u00ff]/g, "**").length > Math.round(strT[5])){	         
	          					 alert(str[3]+"���ܴ���"+strT[5]+"���ַ���");
	          					 return false;
	       		 			}
	       				}
	       			}
	       		}
	       	}
			document.forms[0].action = "/xgxt/shgc_pjpy.do?method=jxjsq&act=save";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/shgc_pjpy.do?method=jxjsqb";
			document.forms[0].submit();
		}
		
		function chang(){
			document.forms[0].action = "/xgxt/shgc_pjpy.do?method=jxjsq";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã��������� - ��ѧ������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڻ�ý�ѧ����Ŀ����Ϊ�������������룡����
			</p>
		</center>
	</logic:equal>
		<html:form action="shgc_pjpy.do?method=jxjsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/shgc_pjpy.do?method=jxjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" />">
			<input type="hidden" id="xqmc" name="xqmc"
				value="<bean:write name="rs" property="xqmc" />">
			<input type="hidden" id="bbmc" name="bbmc"
				value="<bean:write name="rs" property="bbmc" />">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xyshsj" name="xyshsj"
				value="<bean:write name="rs" property="xyshsj" />">
			<input type="hidden" id="xypzje" name="xypzje"
				value="<bean:write name="rs" property="xypzje" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">
			<input type="hidden" id="xxshsj" name="xxshsj"
				value="<bean:write name="rs" property="xxshsj" />">
			<input type="hidden" id="xxpzje" name="xxpzje"
				value="<bean:write name="rs" property="xxpzje" />">
			<input type="hidden" id="zdyzdXxxx" name="zdyzdXxxx"
				value="<bean:write name="zdyzdXxxx" scope="request"/>">


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
					<td align="center" width="16%">
						<font color="red">*</font>��ѧ����Ŀ
					</td>
					<td colspan="3">
						<html:select name="rs" property="bbdm" style="width:180px"
							onchange="chang();">
							<html:option value=""></html:option>
							<html:options collection="jxjxmList" property="bbdm"
								labelProperty="bbmc" />
						</html:select>
					</td>
				</tr>
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
					<td scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
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
							������
						</div>
					</td>
					<td>
						<input type="text" id="ssbh" name="ssbh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="ssbh" />" readonly="true">
					</td>
					<td>
						<div align="center">
							���ҵ绰
						</div>
					</td>
					<td>
						<input type="text" id="qsdh" name="qsdh"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="qsdh" />">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							������ò
						</div>
					</td>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zzmmmc" />"
							readonly="true">
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" name="mzmc"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="mzmc" />">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td>
						<input type="text" id="rxny" name="rxny"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="rxny" />" readonly="true">
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<input type="text" id="csrq" name="csrq"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="csrq" />">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							����
						</div>
					</td>
					<td>
						<input type="text" id="kh" name="kh" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="kh" />" readonly="true">
					</td>
					<td colspan="2">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div align="center">
							������Ϣ
						</div>
					</td>
				</tr>
				<logic:equal name="isNULL" value="no">
					<%
								String zdyzdXxxx = String.valueOf(request
								.getAttribute("zdyzdXxxx"));
								String zddm = "";
								String zdmc = "";
								String zddx = "";
								String zdlx = "";
								String bxwsz = "";

								String[] temp = zdyzdXxxx.split("!!TwoSpile!!");
								for (int i = 0; i < temp.length; i++) {
									String[] str = temp[i].split("!!OneSpile!!");
									zddm = "zdy" + str[2];
									zdmc = str[3];
									zdlx = str[4];
									zddx = str[5];
									bxwsz = str[6];
					%>
					<tr>
						<td>
							<div align="center">
								<%=zdmc%>
							</div>
						</td>
						<td colspan="3">
							<%
							if ("�ı���".equalsIgnoreCase(zdlx)) {
							%>
							<html:textarea name="rs" property="<%=zddm%>" rows='6' style="width:100%" onblur="yzdx(this,'<%=zddm%>')"/>
							<input type="hidden" id="<%=zddm%>" name="<%=zddm%>" value="">
							<%
							} else {
							%>
							<%if("��".equalsIgnoreCase(bxwsz)){ %>
							<input type="text" id="<%=zddm%>" name="<%=zddm%>"
								style="width:100%;heigh:100%" maxlength="<%=zddx%>"
								value="<bean:write name='rs' property="<%=zddm%>" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
								>
							<%} else {%>
							<input type="text" id="<%=zddm%>" name="<%=zddm%>"
								style="width:100%;heigh:100%" maxlength="<%=zddx%>"
								value="<bean:write name='rs' property="<%=zddm%>" />">
							<%} %>
							<%
							}
							%>
						</td>
					</tr>
					<%
					}
					%>
				</logic:equal>
				<tr>
					<td>
						<div align="center">
							�����
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="hjqk" rows='6' style="width:100%" onblur="yzdx(this,'hjqk')"/>
						<input type="hidden" id="hjqk" name="hjqk" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ѧ�����ķ���
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xslwfb" rows='6' style="width:100%" onblur="yzdx(this,'xslwfb')"/>
						<input type="hidden" id="xslwfb" name="xslwfb" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ר������
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="zljs" rows='6' style="width:100%" onblur="yzdx(this,'zljs')"/>
						<input type="hidden" id="zljs" name="zljs" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sqly" rows='6' style="width:100%" onblur="yzdx(this,'sqly')"/>
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2" onClick="yz();">
					�ύ����
				</button>
				<button class="button2" onClick="toPrintOut();">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
