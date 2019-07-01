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
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(xh,dqgzdwyb,dqgzdwdh){
			var sfzh = 	document.getElementById('sfzh').value;
		
			if(xh == null){
				alert('ѧ�Ų���Ϊ��!');
				return false;
			}
			if(!checkSfzh(sfzh)){
				return false;
			}
			if(!isNumber(dqgzdwyb)){
				alert('��ǰ������λ�ʱ����Ϊ����!');
				return false;
			}
			if(!isNumber(dqgzdwdh)){
				alert('��ǰ������λ�绰����Ϊ����!');
				return false;
			}
			document.forms[0].action = "/xgxt/xslxxx.do?doType=add";
			document.forms[0].submit();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
		function checkSfzh(sfzh) {
   			sfzh=sfzh.toLowerCase()
			var OldID;
			if(sfzh.length == 15){
				OldID = sfzh;
				return true;
			}else if(sfzh.length == 18){
				OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
			}else{
				alert("��������ȷ�����֤���룡","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
			var A = new Array("1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2");
			var i, j, S;
			var NewID, ID, strNF;
			NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
			S = 0;
			for( i = 0; i <= 16; i++){
				j = NewID.substring(i, i+1) * W[i];
				S = S + j;
			}
			S = S % 11;
			if(sfzh != NewID + A[S]){
				alert("��������ȷ�����֤���룡","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			return true;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��У��Ϣ
		</div>
	</div>
		<html:form action="xslxxx.do" method="post">
			<input type="hidden" id="url" name="url"
				value="/xslxxx.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />


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
			
			<logic:notEmpty name="isNULL">
				<logic:equal name="isNULL" value="is">
				<script language="javascript">
	         	alert("û�ҵ���ѧ����Ϣ����ȷ���Ƿ�����ѧ����������ѵ����ѧ����Ϣ��");
	         	</script>
				</logic:equal>
			</logic:notEmpty>

			<logic:notEqual name="haveXY" value="is">
			<table width="100%" border="1" class="tbstyle">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" width="16%" scope="col">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="34%" scope="col">
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
						<td align="center" width="16%" scope="col">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="34%" scope="col">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								readonly="readonly"
								value="<bean:write name='rs' property="xh" />" >
						</td>
					</logic:equal>
					<td width="16%" scope="col">
						<div align="center">
							����
						</div>
					</td>
					<td width="34%" scope="col">
						<div align="left">
							<input type="text" id="xm" name="xm"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xm" />" readonly="true">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>���֤��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="sfzh" name="sfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="sfzh" />" >
						</div>
					</td>
					<td>
						<div align="center">
							��Ч��ϵ��ʽ
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="yxlxfs" name="yxlxfs"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name='rs' property="yxlxfs" />" >
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��Уԭ��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="lxyy" name="lxyy"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="lxyy" />" >
						</div>
					</td>
					<td scope="row">
						<div align="center">
							�״α�ҵȥ��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="scbyqx" name="scbyqx"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name='rs' property="scbyqx" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ǰ������λ
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dqgzdw" name="dqgzdw"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="dqgzdw" />" >
						</div>
					</td>
					<td>
						<div align="center">
							��ǰ������λ��ַ
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dqgzdwdz" name="dqgzdwdz"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="dqgzdwdz" />" >
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ǰ������λ�ʱ�
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dqgzdwyb" name="dqgzdwyb"
								style="width:100%;heigh:100%" maxlength="6"
								value="<bean:write name='rs' property="dqgzdwyb" />" 
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							��ǰ������λ�绰
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dqgzdwdh" name="dqgzdwdh"
								style="width:100%;heigh:100%" maxlength="12"
								value="<bean:write name='rs' property="dqgzdwdh" />" 
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
			</table>
			</logic:notEqual>
			<logic:notEqual name="haveXY" value="no">
			<table width="100%" border="1" class="tbstyle">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" width="16%" scope="col">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="34%" scope="col">
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
						<td align="center" width="16%" scope="col">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="34%" scope="col">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								readonly="readonly"
								value="<bean:write name='rs' property="xh" />" >
						</td>
					</logic:equal>
					<td width="16%" scope="col">
						<div align="center">
							����
						</div>
					</td>
					<td width="34%" scope="col">
						<div align="left">
							<input type="text" id="xm" name="xm"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xm" />" readonly="true">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>���֤��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="sfzh" name="sfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="sfzh" />" >
						</div>
					</td>
					<td>
						<div align="center">
							��Ч��ϵ��ʽ
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="yxlxfs" name="yxlxfs"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="yxlxfs" />" >
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��Уԭ��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="lxyy" name="lxyy"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="lxyy" />" >
						</div>
					</td>
					<td scope="row">
						<div align="center">
							�״α�ҵȥ��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="scbyqx" name="scbyqx"
								style="width:100%;heigh:100%" readonly="true"
								value="<bean:write name='rs' property="scbyqx" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ǰ������λ
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dqgzdw" name="dqgzdw"
								style="width:100%;heigh:100%" readonly="true"
								value="<bean:write name='rs' property="dqgzdw" />" >
						</div>
					</td>
					<td>
						<div align="center">
							��ǰ������λ��ַ
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dqgzdwdz" name="dqgzdwdz"
								style="width:100%;heigh:100%" readonly="true"
								value="<bean:write name='rs' property="dqgzdwdz" />" >
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ǰ������λ�ʱ�
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dqgzdwyb" name="dqgzdwyb" readonly="true"
								style="width:100%;heigh:100%" maxlength="6"
								value="<bean:write name='rs' property="dqgzdwyb" />" >
						</div>
					</td>
					<td>
						<div align="center">
							��ǰ������λ�绰
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dqgzdwdh" name="dqgzdwdh" readonly="true"
								style="width:100%;heigh:100%" maxlength="12"
								value="<bean:write name='rs' property="dqgzdwdh" />" >
						</div>
					</td>
				</tr>
			</table>
			</logic:notEqual>
			
			<div class="buttontool" id="btn" style="position: absolute;width:100%" >
				<button class="button2"
					onClick="yz(document.getElementById('xh').value,document.getElementById('dqgzdwyb').value,document.getElementById('dqgzdwdh').value);">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
				<button class="button2" type="reset" >
					ȡ&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
			</div>

		</html:form>
</body>
</html:html>
