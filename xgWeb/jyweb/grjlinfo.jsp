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

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		function huifu(){
		  var xh =document.getElementById("xsxh").value;
		  var xm =document.getElementById("name").value;
		  var yhm =document.getElementById("name").value;
		  
		  url = "dwhf.do?method=dwhf&jytype=jyweb&xh="+xh+"&xm="+xm+"&yhm="+yhm+"&r="+Math.random();
		  
		  showTopWin(url, 400, 300);
	
		}
		
		
		</script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<jsp:include flush="true" page="head.jsp"></jsp:include>
		<html:hidden name="rs" property="xsxh" />
		<html:hidden name="rs" property="name" />
		<html:hidden name="rs" property="yhm" />
		<div class="mainframe">
			<div class="jy_midframe">
				<h1>
					���˼���
				</h1>
				<table class="tbborder" width="90%" align="center">

					<tr height="25">
						<td colspan="9">

							<logic:equal name="idsee" value="yes">
							���֤�ţ�
							<bean:write name="rs" property="id" />
							</logic:equal>
							<logic:equal name="idsee" value="no">
							���֤�ţ����أ�
							</logic:equal>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��ѧ���:<bean:write name="rs" property="rxnf" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="red">*</font>ѧ�ţ�
							<bean:write property="xsxh" name="rs" />
						</td>
					</tr>

					<tr height="25">
						<td rowspan="3" align="center" width="30">
							<b>��<br>��<br>��<br>��</b>
						</td>
						<td align="right" width="70">
							������
						</td>
						<td width="150">
							&nbsp;
							<bean:write name="rs" property="name" />
							&nbsp;
						</td>
						<td align="right" width="70">
							�Ա�
						</td>
						<td width="150" colspan="2" >
							&nbsp;
							<bean:write name="rs" property="xb" />
							&nbsp;
						</td>
						<td align="right" width="70">
							�������£�
						</td>
						<td width="150" colspan="2">
							&nbsp;
							<bean:write name="rs" property="csny" />
							&nbsp;
						</td>
					</tr>
					<tr height="25">
						<td align="right">
							���壺
						</td>
						<td>
							&nbsp;
							<bean:write name="rs" property="mz" />
							&nbsp;
						</td>
						<td align="right">
							ѧ����
						</td>
						<td colspan="2">
							&nbsp;
							<bean:write name="rs" property="xl" />
							&nbsp;
						</td>
						<td align="right">
							������ò��
						</td>
						<td colspan="2">
							&nbsp;
							<bean:write name="rs" property="zzmm" />
							&nbsp;
						</td>
					</tr>
					<tr height="25">
						<td align="right">
							רҵ���ƣ�
						</td>
						<td>
							&nbsp;
							<bean:write name="rs" property="zymc" />
							&nbsp;
						</td>
						<td align="right">
							����רҵ��
						</td>
						<td colspan="2">
							&nbsp;
							<bean:write name="rs" property="fxzymc" />
							&nbsp;
						</td>
						<td align="right">
							��Դ������
						</td>
						<td colspan="2">
							&nbsp;
							<bean:write name="rs" property="sydq" />
							&nbsp;
						</td>
					</tr>
					<tr>
						<td rowspan="2" align="center">
							<b>��<br>ϵ<br>��<br>��</b>
						</td>
						<td align="right">
							��ϵ��ַ��
						</td>
						<td colspan="2">
							<html:textarea name="rs" property="lxdz" rows="2"
								style="width=100%" readonly="true" />
						</td>
						<td align="right">
							��ϵ�绰��
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="lxdh" rows="2"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr height="25">
						<td align="right">
							�������룺
						</td>
						<td colspan="2">
							&nbsp;
							<bean:write name="rs" property="yzbm" />
							&nbsp;
						</td>
						<td align="right">
							�������䣺
						</td>
						<td colspan="4">
							&nbsp;
							<bean:write name="rs" property="email" />
							&nbsp;
						</td>
					</tr>
					<tr>
						<td rowspan="5" align="center">
							<b>ѧ<br>��<br>��<br>��<br>��<br>��</b>
						</td>
						<td align="center">
							�����
						</td>
						<td colspan="7">
							<html:textarea name="rs" property="hjqk" rows="6"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							ѧϰ���
						</td>
						<td colspan="7">
							<html:textarea name="rs" property="xxqk" rows="6"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							У����
							<br>
							�Ͻ���
						</td>
						<td colspan="7">
							<html:textarea name="rs" property="xjysjl" rows="6"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							���ʵ
							<br>
							�����
						</td>
						<td colspan="7">
							<html:textarea name="rs" property="shsjqk" rows="8"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��������
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="gzjl" rows="8"
								style="width=100%" readonly="true" />
						</td>
						<td align="center">
							�����س�
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="grtc" rows="8"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<b>��<br>��<br>��<br>��</b>
						</td>
						<td colspan="8">
							<html:textarea name="rs" property="zwtj" rows="20"
								style="width=100%" readonly="true" />
						</td>
					</tr>
				</table>
				<logic:equal name="usertype" value="dw" scope="session">
					<div align="center">
						<button onclick="huifu()">
							��λ�ظ�
						</button>
					</div>
				</logic:equal>
				<div>
					<h3>
					</h3>
				</div>
			</div>
		</div>
		<jsp:include flush="true" page="foot.jsp"></jsp:include>
	</body>
</html>
