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
	         var url = "bysjytjb.do?act=update&doType=update&pkValue=";
			 pkValue = document.getElementById("xsxh").value;
			 url += pkValue;
	         BatAlert.showTips('�����ύ�����Ժ�...');
			 document.forms[0].action = url;
			 document.forms[0].submit();
	}
	function isxxtjyj(){
		var isstu = document.getElementById("isstu").value;
		if(isstu=="stu"){
		document.getElementById("pyfs").disabled="true";
		document.getElementById("jyfw").disabled="true";
		document.getElementById("yxyj").disabled="true";
		document.getElementById("jybmyj").disabled="true";
		document.getElementById("lxbm").disabled="true";
		document.getElementById("bmlxr").disabled="true";
		document.getElementById("bmlxdh").disabled="true";
		}else if(isstu == "xy"){
			document.getElementById("jybmyj").disabled="true";
			document.getElementById("lxbm").disabled="true";
			document.getElementById("bmlxr").disabled="true";
			document.getElementById("bmlxdh").disabled="true";
		}else if(isstu == "xx" || isstu =="admin"){
			document.getElementById("pyfs").disabled="true";
			document.getElementById("jyfw").disabled="true";
			document.getElementById("yxyj").disabled="true";
		}
}
	</script>

<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<body onload="isxxtjyj();">
<html:form action="/yxjzyjs.do" method="post">
	<logic:empty name="rs1">
			δ�ҵ������
		</logic:empty>
	<logic:notEmpty name="rs1">
		<logic:iterate id="rs1" name="rs1">
			<table width="100%" class="tbstyle" id="grjl">
				<thead>
					<tr>
						<td colspan="8" align="center">
							2009�챱��������ͨ��У��ҵ����ҵ�Ƽ���
						</td>
					</tr>
				</thead>
				<tr>
					<td rowspan="7" align="center" width="30">
						<b>��<br>��<br>��<br>Ϣ</b>
					</td>
					<td align="center" style="width: 85px">
						ѧ��
						<input id="isstu" value="<bean:write name="userType" scope="session"/>" type="hidden"/>
					</td>
					<td width="180">
						<html:text name='rs1' property="xsxh" styleId="xsxh" readonly="true" />
					</td>
					<td align="center" width="70">
						����
					</td>
					<td width="150">
						<html:text name="rs1" property="name" style="100%" readonly="true" />
					</td>
					<td align="center" width="70">
						�Ա�
					</td>
					<td width="150">
						<html:text name="rs1" property="xb" style="100%" readonly="true" />
					</td>
					<td rowspan="6" align="center" width="150">
						��Ƭ
					</td>
				</tr>
				<tr>
					<td align="center" width="70">
						���֤��
					</td>
					<td width="150">
						<html:text name="rs1" property="id" style="100%" readonly="true" />
					</td>
					<td align="center">
						����<br>����
					</td>
					<td>
						<html:text name="rs1" property="csrq"  style="100%" readonly="true"/>
					</td>
					<td align="center">
						������Ŀ
					</td>
					<td>
						<html:text name="rs1" property="zzmm" style="100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						��Դ����
					</td>
					<td>
						<html:text name="rs1" property="sydq" style="100%" readonly="true" />
					</td>
					<td align="center">
						�绰
					</td>
					<td>
						<html:text name="rs1" property="lxdh" style="100%" readonly="true"/>
					</td>
					<td align="center">
						�ֻ�
					</td>
					<td>
						<html:text name="rs1" property="sjhm" style="100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="center" >
						ͨѶ��ַ
					</td>
					<td colspan="3">
						<html:text name="rs1" property="txdz" style="width: 100%"  readonly="true"/>
					</td>
					<td align="center">
						�ʱ����
					</td>
					<td >
						<html:text name="rs1" property="yzbm" style="100%" readonly="true"/>
					</td>
					
				</tr>
				<tr>
					<td align="center" >
						��ҵѧУ
					</td>
					<td colspan="3">
						<html:text name="rs1" property="byxx" style="width: 100%" readonly="true" />
					</td>
					<td align="center">
						ѧ��
					</td>
					<td>
						<html:text name="rs1" property="xlmc" style="100%" readonly="true" />
					</td>
					
				</tr>
				<tr>
					<td align="center" >
						רҵ
					</td>
					<td colspan="3">
						<html:text name="rs1" property="zymc" style="width: 100%" readonly="true" />
					</td>
					<td align="center">
						ѧ��
					</td>
					<td >
						<html:text name="rs1" property="xz" style="100%" readonly="true" />
					</td>
					
				</tr>
				<tr>
					<td align="center" >
						��ҵʱ��
					</td>
					<td>
						<html:text name="rs1" property="bysj" style="width: 100%" readonly="true"/>
					</td>
					<td align="center">
						����<br>���
					</td>
					<td colspan="4">
						<html:text name="rs1" property="jlqk" style="width: 100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td rowspan="3" align="center">
						<b>��<br>��<br>ʵ<br>��</b>
					</td>
					<td align="right">
						1��
					</td>
					<td colspan="7">
						<html:text name="rs1" property="shsj1" style="width: 100%" maxlength="55" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						2��
					</td>
					<td colspan="7">
						<html:text name="rs1" property="shsj2" style="width: 100%" maxlength="55" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						3��
					</td>
					<td colspan="7">
						<html:text name="rs1" property="shsj3" style="width: 100%" maxlength="55" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td rowspan="3" align="center">
						<b>��<br>��<br>��<br>��<br>��</b>
					</td>
					<td align="center" colspan="3">
						1��������������
					</td>
					<td >
						<html:text name="rs1" property="wyyz" readonly="true"/>
					</td>
					<td align="center">
						����
					</td>
					<td >
						<html:text name="rs1" property="jb" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
						2:�����ˮƽ
					</td>
					<td >
						<html:text name="rs1" property="jsjsp" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
						3:�س�������(������У�ڼ䵣��ְ��)
					</td>
					<td colspan="4">
						<html:textarea name="rs1" property="tcnl" rows="4" style="width=100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="center" rowspan="5">
						<b>ѧ<br>У<br>��<br>��<br>��<br>��</b>
					</td>
					<td align="center" rowspan="3" colspan="2">
						ϵ��Ժ�������
					</td>
					<td align="center" style="width: 85px">
						��ҵ��<br>������ʽ
					</td>
					<td >
						<html:text name="rs1" property="pyfs" />
					</td>
				</tr>
				<tr>
				<td align="center">
						��ҵ��Χ
					</td>
					<td >
						<html:text name="rs1" property="jyfw" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<html:textarea name="rs1" property="yxyj" rows="4" style="width=100%"/>
					</td>
				</tr>
				<tr>
					
					<td align="center" rowspan="1" colspan="2">
						ѧУ��ҵ<br>����ҵ��<br>�����
					</td>
					<td colspan="5">
						<html:textarea name="rs1" property="jybmyj" rows="4" style="width=100%"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						��ϵ����
					</td>
					<td >
						<html:text name="rs1" property="lxbm" />
					</td>
					<td align="center">
						��ϵ��
					</td>
					<td >
						<html:text name="rs1" property="bmlxr" />
					</td>
					<td align="center">
						��ϵ�绰
					</td>
					<td >
						<html:text name="rs1" property="bmlxdh" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<b>��<br>ע</b>
					</td>
					<td align="center" colspan="7">
					<html:textarea name="rs1" property="bz" rows="4" style="width: 100%"/>
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

