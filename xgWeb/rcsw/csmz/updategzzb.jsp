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

<html>
	<head>
			<base target="_self">
		<title>��ɳ����-�����ܱ�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="�����������ӹ������޹�˾ hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript">
			function updategzzb(){

				document.getElementById('content1').value = frames('eWebEditor1').getHTML();
				var bt= $("bt").value;

                if(bt==""){
                 alert("���ⲻ��Ϊ�գ�");
                 return false;
                }


				if(document.getElementById("content1").value == ""){
					alert("���ݲ���Ϊ�գ�");
					return false;
				}
				url = "updateGzzb.do?doType=update";
		 	    document.forms[0].action = url;
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
		<html:form action="/updateGzzb" method="post">
		<input type="hidden" name="pkValue" value="<bean:write name="rs" property="rowid" />" />
			<table width="99%" align="center" class="tbborder">
				<tr>
					<td align="right">
						�����ˣ�
					</td>
					<td>
						<DIV align="left">
							<html:text name="rs" property="fbr" readonly="true"/>
						</DIV>
					</td>
				</tr>
				<tr>
					<td align="right">
					   ��    �⣺
					</td>
					<td>
					   <html:text name="rs" property="bt" style="width:95%" />
					</td>
				</tr>
				<TR>
					<TD align=right width="100">
						�༭���ݣ�
					</TD>
					<TD align=center colspan="3"><INPUT type="hidden" name="content1"  id="content1" value="<bean:write name="nr" scope="request"/>">
						<IFRAME ID="eWebEditor1" src="BatEditor" frameborder="0"
							scrolling="no" width="100%" height="350">
						</IFRAME>
					</TD>
				</TR>
				<TR>
					<TD colspan=4 align=center>
						<button type="button" onclick="updategzzb();" id="buttonSave">
							�ύ
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" type="reset" id="buttonReset">
							�ر�
						</button>
					</TD>
				</TR>
			</table>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("�޸ĳɹ���");
                      Close();
                      window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
			<logic:equal name="save" value="on">
				<script>
                      alert("�޸�ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
