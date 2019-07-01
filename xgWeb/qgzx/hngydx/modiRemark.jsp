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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	function commit(){
		var bz = document.getElementById('bz').value;
		if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("��ע���ܴ���60���ַ�");
	          		 return false;
	       		 }
			}
		refreshForm('xsqgzx.do?method=saveXsgwxxRemark');
	}
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<html:form action="/qgzxZgdzdx.do" method="post">
		<input type="hidden" id="url" name="url" value="/qgzx/zgdzdx/addHmd.jsp"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��ڹ���ѧ - ���� - ��������ѯ - �޸ı�ע
				</div>
			</div>			
				<fieldset>
					<legend>
						�޸ı�ע��Ϣ
					</legend>
					
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							<br></td>
							<td align="left">
								<bean:write name="rs" property="xh"/>
							<br></td>
							<td align="right">
								������
							<br></td>
							<td>
							<bean:write name="rs" property="xm"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />���ƣ�
							<br></td>
							<td align="left">
							<bean:write name="rs" property="xymc"/>
							</td>
							<td align="right">
								רҵ���ƣ�
							<br></td>
							<td>
							<bean:write name="rs" property="zymc"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								�༶���ƣ�
							<br></td>
							<td align="left">
							<bean:write name="rs" property="bjmc"/>
							</td>
							<td align="right">
								�꼶��
							<br></td>
							<td>
							<bean:write name="rs" property="nj"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								��λ���ƣ�
							<br></td>
							<td align="left" colspan="3">
							<bean:write name="rs" property="gwsjmc"/>
							</td>							
						</tr>
						<tr>
							<td align="right">
								��ע(������0~250)��
							<br></td>
							<td colspan="3"><br><html:textarea property="bz" name="rs" style="width:100%;height:60px" styleId="bz"/>
							<br><br></td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="commit()"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
				<logic:present name="result">
				<logic:equal value="true" name="result">
				<script>
					alert('�����ɹ�!');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">				
				<script>
					alert('����ʧ�ܣ�');
					Close();
				</script>
				</logic:equal>
				</logic:present> 
		</html:form>
	</body>
</html>
