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

<html>
	<head>
		<title>ѧ����������ϵͳ</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="�����������ӹ������޹�˾ hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />
        <base target="_self" >
		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		function updatethephoto(){
		     var bt = document.getElementById("bt").value;
		     var nr = document.getElementById("nr").value;
		     var pkValue= $("rid").value;
		
		if(bt==""||nr==""){
		    alert("�뽫��*�ŵĲ�����д������");
		    return false;
		}
			BatAlert.showTips('�����޸ģ���ȴ�...');
		    document.forms[0].action = "jxglupdatephoto.do?doType=update&pkValue="+pkValue;
	        document.forms[0].submit();
		}
		function refreshtheweb()
		{
		    var pkValue = $("rid").value;
			document.forms[0].action = "updatepic.do?method=updatepic&jytype=jyweb&pkValue="+pkValue;
            document.forms[0].submit();
		}
		</script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<html:form action="/updatethephoto" method="post"
			enctype="multipart/form-data">
			<br>
            <html:hidden name="rs" property="rid" />
			<table width="98%" align="center" class="tbborder">
				<tr>
					<td align="center" width="70">
						<font color="red">*</font>����:
					</td>
					<td align="left">
						<html:text style="width:96%"  name="rs" property="bt" 	maxlength="50" />
					</td>
				</tr>
				<tr>
					<td width="70" align="center" valign="top">
						<font color="red">*</font>�������:
						<p>
							<font color="red">˵<br>��<br>��<br>��<br>��<br>100<br>��</font>
						</p>
					</td>
					<td align="left">
						<html:textarea rows="9" name="rs"  property="nr" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td  align="center" colspan="2">
						<button type="button" class="button2" onclick="updatethephoto();" >�޸�</button>&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" >�ر�</button>
					</td>
				</tr>
			</table>

			<button type="button" onclick="refreshtheweb()" id="search_go"
				style="display: none" >
			</button>
		</html:form>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                      alert("��¼ɾ���ɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("��¼ɾ��ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("�ļ��ϴ��ɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("�ļ��ϴ�ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
