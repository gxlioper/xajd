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
           var pkValue = document.getElementById("pk").value;
           document.forms[0].action = "updatewjxzinfo.do?method=updateWjxzInfo&jytype=jyweb&doType=update&pkValue="+pkValue;
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
		<html:form action="/updatewjxzinfo" method="post" enctype="multipart/form-data">
			<input type="hidden" name="pk" value="<bean:write name="rs" property="wjh"/>" id="pk"/>
			<table width="99%" align="center" class="tbborder">
				<tr>
										<td width="90" align="right">
											<font color="red">*</font>�ļ���:
										</td>
										<td>
											<div align="left">
												<html:text name="rs" property="wjh" maxlength="25" size="30" styleId="wjh" style="width:100%" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="90" align="right">
											<font color="red">*</font>�ļ���:
										</td>
										<td>
											<div align="left">
												<html:text name="rs" property="wjm" maxlength="25" size="30" styleId="wjm" style="width:100%" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="100px" align="right">
											<font color="red">*</font>�ϴ�����:
										</td>
										<td>
											<div align="left">
												<input type="text" name="wjffbm" id="wjffbm" value="ѧ����"
													size="20" maxlength="20" readonly="readonly" />
											</div>
										</td>
									</tr>
									<tr>
										<td align="right" valign="top">
											<font color="red">*</font>�ļ�˵��:
											<p>
												<font color="red">˵&nbsp;&nbsp;&nbsp;&nbsp;<br>��&nbsp;&nbsp;&nbsp;&nbsp;<br>
												��&nbsp;&nbsp;&nbsp;&nbsp;<br>��&nbsp;&nbsp;&nbsp;&nbsp;<br>��&nbsp;&nbsp;&nbsp;&nbsp;<br>
												500&nbsp;&nbsp;&nbsp;&nbsp;<br>��&nbsp;&nbsp;&nbsp;&nbsp;</font>
											</p>
										</td>
										<td>
											<html:textarea rows="10" style="width:100%" name="rs" property="wjscsm"
												styleId="wjscsm"></html:textarea>
										</td>
									</tr>
									<tr>
										<td align="right">
											������
										</td>
										<td align="left">
											<div align="left">
												<html:text name="rs" property="wjsclj" style="width:60%" styleId="viewpath"/>
												<input type="file" name="uploadFile" style="width:1px" onchange="changedata(this)" styleId="path">
											</div>
										</td>
									</tr>
				<TR>
					<TD colspan="2" align=center>
						<button onclick="updatemessage();">
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
			<script>
				function changedata(obj){
					document.getElementById('viewpath').value=obj.value;
				}
     
            </script>
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
