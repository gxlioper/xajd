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
	function checkzlb(){
		var zlbs = $('zlbs').value;	
		var array = document.getElementsByName('gdzldm');
		for(var i=0;i<array.length;i++){
			if(zlbs.indexOf('!@!'+array[i].value+'!@!')>-1){
				array[i].checked=true;
			}
		}
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();checkzlb()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">	
			<input type="hidden" name="zlbs" id="zlbs" value="${zlbs}"/>		
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"> ѧ����Ϣ - �鵵���� - �鵵�����ύ</span>
				</div>
			</div>						
			<fieldset>			
				<table width="100%" class="tbstyle" id="rsT">	
					<thead>
						<tr>
							<td colspan="2">
								<center>����ѧ���ύ�鵵����</center>
							</td>
						</tr>
					</thead>					
					<tr>
						<td align="right">
							ѧ�ţ�
						</td>
						<td align="center">
							<bean:write name = "rs" property="xh"/>
							<input type="hidden" name="xh" id="xh" value="<bean:write name = "rs" property="xh"/>"/>
						</td>
					</tr>	
					<tr>
						<td align="right">
							������
						</td>
						<td align="center">
							<bean:write name = "rs" property="xm"/>
						</td>	
					</tr>	
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td align="center">
							<bean:write name = "rs" property="xb"/>
						</td>	
					</tr>		
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />���ƣ�
						</td>
						<td align="center">
							<bean:write name = "rs" property="xymc"/>
						</td>
					</tr>		
					<tr>
						<td align="right">
							רҵ���ƣ�
						</td>
						<td align="center">
							<bean:write name = "rs" property="zymc"/>
						</td>
					</tr>	
					<tr>
						<td align="right">
							�༶���ƣ�
						</td>
						<td align="center">
							<bean:write name = "rs" property="bjmc"/>
						</td>
					</tr>
					<tr>
					<td colspan="2">
					<table class="tbstyle" width="100%">	
					<thead>
						<tr>
							<td colspan="4">
								<center>�鵵�����б�</center>
							</td>
						</tr>
					</thead>		
					<logic:iterate id="s" name="gdzlList">
						<tr>
							<td width="10%">
								${s.lbmc}
							</td>
							<td colspan="3">
								<logic:iterate id="s1" name="s" property="zlblist">
									<input type="checkbox" name="gdzldm" value="${s1.gdzldm}" id="${s1.gdzldm}"/>
									${s1.gdzlmc}&nbsp;&nbsp;&nbsp;
								</logic:iterate>
							</td>
						</tr>
					</logic:iterate>	
					</table>
					</td>			
					</tr>	
				</table>
			</fieldset>
			<div class="buttontool">
				<logic:notEqual value="view" name="doType">
					<logic:notEmpty name="gdzlList">
					<button class="button2" onclick="refreshForm('stu_info_add.do?method=datumCommitSignle&doType=save');BatAlert.showTips('����ִ�в�������ȴ�...');"
					style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEmpty>
				</logic:notEqual>				
								
				<button class="button2" onclick="Close();return false;" style="width:80px" >
					�� ��
				</button>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
				<script>	
					alert("�����ɹ���");
					Close();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>	
						alert("����ʧ�ܣ�");
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>

