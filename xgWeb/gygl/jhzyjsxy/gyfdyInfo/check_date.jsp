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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<base target="_self">

	<script language="javascript">
	function subsave(){
		var xn = document.getElementById("xn").options[document.getElementById("xn").options.selectedIndex].text;
		var xq = document.getElementById("xq").options[document.getElementById("xq").options.selectedIndex].text;
		if (document.getElementById("xn").value == "" || document.getElementById("xq").value == "" ||
				document.getElementById("dqxn").value == ""  ||document.getElementById("dqxq").value == "" ) {
				alert("��ѡ��ѧ�ꡢѧ��!");
				return false;
			}
		if(document.getElementById("xn").value == document.getElementById("dqxn").value){
			if(document.getElementById("xq").value == document.getElementById("dqxq").value){
				alert("��ѡ��ͬ��ѧ��!");
				return false;
			}
		}
		if(confirm('ͬ���󽫻�ɾ��'+xn+xq+'��ǰ���������ݣ���ȷ��Ҫͬ����!!')){
				refreshForm('/xgxt/jhzy_gygl.do?method=selectIntoFdy&doType=save');
		}
	}
</script>
	<body onload="">
		<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		%>
		<html:form action="/jhzy_gygl" method="post">
			<table width="99%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="2">
							��ѧ��ͬ����Ԣ��Ϣ
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" style="width:50%">
					ָ��ѧ��ѧ�ڵĸ���Ա��Ϣͬ��
					</td>
				</tr>
				<tr>
					<td align="right" style="width:50%">
						��ǰѧ�꣺
					</td>
					<td align="left">
						<html:select  property="dqxn" style="width:100px" styleId="dqxn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						��ǰѧ�ڣ�
					</td>
					<td align="left">
					<html:select property="dqxq" styleId="dqxq">
							<%--<html:option value=""></html:option>
							--%><html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
					</html:select>
					</td>
				</tr>
					<tr>
					<td align="right" style="width:50%">
						ͬ��ѧ�꣺
					</td>
					<td align="left">
						<html:select  property="xn" style="width:100px" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						ͬ��ѧ�ڣ�
					</td>
					<td align="left">
					<html:select property="xq" styleId="xq">
							<%--<html:option value=""></html:option>
							--%><html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
					</html:select>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<button class="button2"
							onclick="subsave();">
							ͬ��
						</button>
						<button class="button2" onclick="Close();return false;">
							�ر�
						</button>
					</td>
				</tr>
			</table>
			<br />
		</html:form>
		<logic:equal value="ok" name="done">
<script language="javascript">
alert("�����ɹ���");
Close();
//window.dialogArguments.document.getElementById('search_go').click();   
</script>
</logic:equal>
<logic:equal value="no" name="done">
<script language="javascript" >
  alert("����ʧ�ܣ�");
//Close();
//window.dialogArguments.document.getElementById('search_go').click();   
</script>
</logic:equal>
	</body>
</html>
