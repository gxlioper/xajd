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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	  <script type='text/javascript'src='/xgxt/dwr/interface/getSztzData.js'></script>
    <script type='text/javascript'src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript">
function dataSave(){   
	refreshForm("/xgxt/jhzy_yxlcqsz.do?method=yxlcqszChek&doType=save");	         
}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã���Ԣ���� - ����¥�����ҳ����� - ���� - �ټ����ҳ�
		</div>
	</div>
	<html:form action="/jhzy_yxlcqsz" method="post">
	<input type="hidden" name="userType" value="${userType}">	
	<input type="hidden" name="xmk" value="${xmk}">	
	<input type="hidden" name="pkValue" value="${pkValue}">			
		<table class="tbstyle" width="90%">
			<tr>				
					<td align="center" width="16%">
						<font color="red">*</font>ѧ��
					</td>
					<td align="left" width="34%">
						<bean:write name='rs' property="xh" />
					</td>				
				<td width="16%">
					<div align="center">
						ѧ��
					</div>
				</td>				
					<td width="34%">
						<bean:write name='rsYxlcqsz' property="xn" />
					</td>				
			</tr>
			<tr>
			<td width="16%">
					<div align="center">
						����
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="xm"/>
				</td>
				<td width="16%">
					<div align="center">
						¥��
					</div>
				</td>
				<td width="34%">
					<bean:write name='rsYxlcqsz' property="ldmc" />
				</td>
				
			</tr>
			<tr>
				<td>
					<div align="center">
						�Ա�
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xb" />
				</td>

				<td>
					<div align="center">
						¥��
					</div>
				</td>
				<td>
					<bean:write name='rsYxlcqsz' property="lc" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td>
					<bean:write name="rs" property="mzmc"/>
				</td>
				
				<td width="16%">
					<div align="center">
						����
					</div>
				</td>
				<td width="34%">
					<bean:write name='rsYxlcqsz' property="qsh" />
				</td>
			</tr>
			<tr>
				
				<td>
					<div align="center">
						������ò
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zzmmmc"/>
				</td>
				<td>
					<div align="center">
						�꼶
					</div>
				</td>
				<td>
					<bean:write name="rs" property="nj"/>
				</td>
				
			</tr>
			
			<tr>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xymc"/>
				</td>
				<td>
					<div align="center">
						רҵ
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zymc"/>
				</td>
			</tr>
			<tr>				
				<td>
					<div align="center">
						�༶
					</div>
				</td>
				<td>
                    <bean:write name="rs" property="bjmc"/>
				</td>
				<td>
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td>
					<bean:write name="rsYxlcqsz" property="lxdh"/>
				</td>
			</tr>
					<tr>				
				<td>
					
				</td>
				<td>
                    
				</td>
				<td>
					<div align="center">
						���
					</div>
				</td>
				<td>
					<html:select name="rsYxlcqsz" property="yesNo" styleId="yesNo">						
						<html:options collection="chkList" property="en"
							labelProperty="cn" />
					</html:select>
				</td>
			</tr>					
			<tr>
				<td>
					<div align="center">
						���ҽ������						
					</div>
				</td>
				<td colspan="3">
				<bean:write name="rsYxlcqsz" property="qsjsqk"/>				
				</td>
			</tr>						
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:90%">
		
			<button class="button2" onClick="dataSave();" id="buttonSave">
				��  ��
			</button>
	&nbsp;&nbsp;
			<button class="button2" onClick="Close()">
				��  ��
			</button>
		</div>
	
	</html:form>
					<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('�����ɹ���');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('����ʧ�ܣ�');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>
</body>
<logic:present name="msg">
	<script>
			alert(''+document.getElementById('msg').value);
		</script>
</logic:present>
</html:html>
