<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
<title>��ҵ������Ϣϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="������� zfsoft" />
</head>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<base target="_self">

<script language="javascript" src="js/function.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<script language="javascript">
	function add(){

	      	var pkValue ="";
	         var url = "jyjzjkbs.do?act=update&doType=update&pkValue=";
			 pkValue = document.getElementById("pkValue").value;
			 url += pkValue;
	         BatAlert.showTips('�����ύ�����Ժ�...');
			 document.forms[0].action = url;
			 document.forms[0].submit();
	}
	</script>

<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<body>
<html:form action="/yxjzyjs.do" method="post">
	<logic:empty name="rs1">
			δ�ҵ������
		</logic:empty>
	<logic:notEmpty name="rs1">
		<logic:iterate id="rs1" name="rs1">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>��ҵ��չ�������¼��</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" style="width: 20%">
					<input id="pkValue" name="pkValue" type="hidden" value="${rs1.id }"/>
						<font color="red">*</font>ϵ��Ժ����չ��<br>��Ҫ����<br>����ʩ
					</td>
					<td align="right" colspan="3">
					<html:textarea name="rs1" property="gzjcs" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>���ٵ���Ҫ<br>����,����
					</td>
					<td align="right" colspan="3">
					<html:textarea name="rs1" property="zywt" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>��ҵ����<br>����
					</td>
					<td align="right" colspan="3">
					<html:textarea name="rs1" property="jyxsfx" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>��ҵ��չ���<br>������ϵ��
					</td>
					<td>  
						<html:text name="rs1" property="lxr"/>
					</td>
					<td align="right">
						<font color="red">*</font>��ϵ�绰
					</td>
					<td>
						<html:text name="rs1" property="lxdh" />
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<font color="red">*</font>���˵��
					</td>
					<td align="right" colspan="3">
					<html:textarea name="rs1" property="tbsm" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
			</table>
		</logic:iterate>
	</logic:notEmpty>
	<div align="center">
	<button class="button2"
		onclick="add();" style="width: 80px">
	�� ��</button>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button class="button2"
		onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
		style="width: 80px">�� ��</button>
	</div>
</html:form>

<logic:notEmpty name="save">
	<logic:equal name="save" value="ok">
		<script>
                      alert("�ύ�ɹ���");
                    </script>
	</logic:equal>
	<logic:equal name="save" value="no">
		<script>
                      alert("�ظ��ύ������ʧ��!");
                    </script>
	</logic:equal>
</logic:notEmpty>
</body>
</html>

