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
		<title><%=session.getAttribute("xxmc")%>��ҵ��</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="�����������ӹ������޹�˾ hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />
        <base target="_self">
		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
        <script type="text/javascript">
        function updatemessage(){
           var pkValue = document.getElementById("newsid").value;
           document.getElementById('content1').value = frames('eWebEditor1').getHTML();
           document.forms[0].action = "updatenewsinfo.do?method=updatenewsinfo&jytype=jyweb&doType=update&pkValue="+pkValue;
	       document.forms[0].submit();
        
        }
        
        
        
        </script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body onunload="window.dialogArguments.document.getElementById('search_go').click();">
		<html:form action="/updatenewsinfo" method="post">
		    <html:hidden name="rs" property="newsid"  />
			<table width="99%" align="center" class="tbborder">
				<tr>
					<td width="30" align="center">
						����
					</td>
					<td>
						<DIV align="left">
							<html:text name="rs" property="newstitle" maxlength="50"
								style="width:60%" />
						</DIV>
					</td>
				</tr>
				<TR>
					<TD align=right>
						��Ŀ
					</TD>
					<TD>
						<input name="wjlx" value="���¶�̬" disabled="disabled" />
					</TD>
				</TR>
				<TR>
					<TD align=right>
						����
					</TD>
					<TD align=center>
						<html:hidden name="rs" property="content1" />
						<IFRAME ID="eWebEditor1" src="BatEditor" frameborder="0"
							scrolling="no" width="100%" height="350"></IFRAME>
					</TD>
				</TR>
				<TR>
					<TD colspan="2" align=center>
						<button onclick="updatemessage();return false;">
							�ύ
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="reset">
							����
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button  onclick="Close();return false;">
							�ر�
						</button>
					</TD>
				</TR>
			</table>
			<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("��¼�޸ĳɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("��¼�޸�ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		</html:form>
	</body>
</html>
