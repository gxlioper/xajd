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
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body>	
		<html:form action="/csmz_gygl.do?method=stu_tofkxx" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="<bean:write name="pkValue"/>"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ��Ԣ���� - ����Ա���� - ѧ������ - ��д������Ϣ
				</div>
			</div>		
				<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							��д������Ϣ
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="15%">
						����Ա����:
					</td>
					<td align="left">
						<html:text name='rs' property="fdyxm" styleId="fdyxm" disabled="true" />
					</td>
					<td align="right" width="10%">
						ѧ��:
					</td>
					<td align="left">
						<html:text name='rs' property="xn" styleId="xn" disabled="true" />
					</td>
				</tr>
				<tr>
					<td align="right">
						¥������:
					</td>
					<td align="left">
						<html:text name='rs' property="lddm" styleId="lddm" disabled="true" />
					</td>
					<td align="right">
						ѧ��:
					</td>
					<td align="left">
						<html:text name='rs' property="xq" styleId="xq" disabled="true" />
					</td>
				</tr>
				<tr>
					<td align="right">
						���Һ�:					
					</td>
					<td align="left">
					  <html:text name='rs' property="qsh" styleId="qsh" disabled="true" />
					</td>
					<td align="right">
						����ʱ��:
					</td>
					<td align="left">
						<html:text name='rs' property="xqsj" styleId="xqsj" disabled="true" />
					</td>
				</tr>
				<tr>
					<td align="right">
						�������Ҫ����:
					</td>
					<td align="left" colspan="3">
					<html:textarea  name="rs" property="zywt" cols="76" rows="5" disabled="true" ></html:textarea>
					</td>	
				</tr>
				<tr>
					<td align="right">
						��ע:
					</td>
					<td align="left" colspan="3">
					<html:textarea  name="rs" property="bz" cols="76" rows="5" disabled="true"  ></html:textarea>
					</td>	
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>������Ϣ:<br>(��205����)
					</td>
					<td align="left" colspan="3">
					<html:textarea  name="rs" property="xsfk" cols="76" rows="5" ></html:textarea>
					</td>	
				</tr>
			</table>
				<div class="buttontool" align="center">					
					<button class="button2" onclick="fkDataSave()" style="width:80px" id="buttonSave">
					�ᡡ��	
					</button>					
				</div>
		</html:form>		
		<logic:equal value="true" name="done">
			<script type="text/javascript">
	           alert("�ύ�ɹ���");
	           Close();
			   window.dialogArguments.document.getElementById('search_go').click();
	        </script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
	          alert("�ύʧ�ܣ�");
	          Close();
			   window.dialogArguments.document.getElementById('search_go').click();
	        </script>
		</logic:equal>
  </body>
  <script type="text/javascript">
  function fkDataSave(){    
       if($('xsfk').value==""){
          alert('������Ϣ����Ϊ�գ�');
          return false;
       }else if($('xsfk').value.length>250){
          alert('������Ϣ��������250�֣�');
          return false;
       }else{
          refreshForm('/xgxt/csmz_gygl.do?method=saveStu_Fkxx');
       }   
  }
  </script>
</html>
