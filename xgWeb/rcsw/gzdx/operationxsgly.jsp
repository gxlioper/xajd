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
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript'
			src='/xgxt/dwr/interface/dateUtil.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript">
		function save(){
		   var bz = document.getElementById('bz').value;
		   if(bz.length > 300){
		   		alert('��ע���ܴ���300�֣�');
		   	   	return false;
		   }
		   if($('act').value == 'add'){
		      document.forms[0].action = "/xgxt/xsgly.do?method=operateXsglyxx&doType=add";
		   }else{
		   	  document.forms[0].action = "/xgxt/xsgly.do?method=operateXsglyxx&doType=update";
		   }	   
		   document.forms[0].submit();
		}	

	</script>
</head>
<body>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��ճ����� - ֵ����Ա���� - 
				<logic:equal value="add" name="act">
					ѧ������Ա����
				</logic:equal>
				<logic:equal value="update" name="act">
					ѧ������Ա�޸�
				</logic:equal>
			</div>
		</div>
		<html:form action="/xsgly" method="post">
			<input type="hidden" name="pkVStr" id="pkVStr" value="${pkvals}"/>
			<input type="hidden" name="act" id=act value="${act}"/>
			<table class="tbstyle" width="100%">
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" width="35%">
						<html:text name="rs" property="xh" readonly="true"></html:text>
						<logic:equal value="add" name="act">
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">ѡ��
							</button>
						</logic:equal>			
					</td>
					<td align="right" width="15%">
						������
					</td>
					<td align="left" width="35%">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name="rs" property="xb"/>
					</td>
					<td align="right" width="15%">
						<bean:message key="lable.xsgzyxpzxy" />���ƣ�
					</td>
					<td align="left" width="35%">
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>				
				<tr>
					<td align="right" width="15%">
						רҵ���ƣ�
					</td>
					<td align="left" width="35%">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td align="right" width="15%">
						�༶���ƣ�
					</td>
					<td align="left" width="35%">
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>	
				<tr>
					<td align="right" width="15%">
						�Ƿ����ã�
					</td>
					<td align="left" width="35%">
						<html:select name="rs" property="sfqy">
							<html:option value="����"></html:option>
							<html:option value="������"></html:option>
						</html:select>
					</td>
					<td align="right" width="15%">
					</td>
					<td align="left" width="35%">
					</td>
				</tr>			
				<tr>
					<td>
						<div align="center">
							��ע��
							<br>
							<font color="red"><��300����> </font>
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="bz" rows="8" styleId="bz"
							style="width:100%" />
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onClick="save()" id="buttonSave">
					�� ��
				</button>		
				&nbsp;&nbsp;
				<button type="button" class="button2" onClick="window.close()" id="buttonSave">
					�� ��
				</button>
			</div>
		</html:form>
	
	<logic:equal value="true" name="result">
		<script type="text/javascript">
			 alert('�����ɹ���');
			 Close();
			 dialogArgumentsQueryChick();
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script type="text/javascript">
			    alert('����ʧ�ܣ�');
			  </script>
	</logic:equal>
</body>
</html:html>
