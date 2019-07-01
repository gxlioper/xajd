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
		<html:form action="/csmz_gygl.do?method=stu_fkxxcx" method="post">
		<button id="search_go"onclick="refreshForm('/xgxt/csmz_gygl.do?method=stu_fkxxcx')" style="display: none">
		</button>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ��Ԣ���� - ����Ա���� - ѧ������
				</div>
			</div>
			<logic:notEqual value="student" name="userType">
				<div align="center">
				<br>
				<br>
				<br>
					<font color="red">ֻ��ѧ���û��ɲ鿴������!</font>
				</div>
			</logic:notEqual>
			<logic:equal value="student" name="userType">
				<logic:empty name="rs">
				</logic:empty>
				<logic:notEmpty name="rs">
				<fieldset>
						<legend>
							��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������������ͷ��������</font>
						</legend>
					<table width="100%" class="tbstyle">
					 <thead>
					 <tr style="cursor:hand" align="center">
					 <td onclick="tableSort(this)">
					 �к�
					 </td>
					 <td onclick="tableSort(this)">
					 ¥������
					 </td>
					 <td onclick="tableSort(this)">
					 ���Һ�
					 </td>
					 <td onclick="tableSort(this)">
					 ���ø���Ա
					 </td>
					 <td onclick="tableSort(this)">
					 ����ʱ��
					 </td>
					 <td onclick="tableSort(this)">
					 ��Ҫ����
					 </td>
					 <td onclick="tableSort(this)">
					  ѧ������
					 </td>					 
					 </tr>
					 
					 </thead>
					 <logic:iterate id="s" name="rs">
					   <tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="toRfK()">
						
						<td nowrap>
						<input type="hidden" value="<bean:write name="s" property="pkV"/>" />
						<bean:write name="s" property="r"/>
						</td>
						<td nowrap>
						<bean:write name="s" property="ldmc"/>
						</td>
						<td nowrap>
						<bean:write name="s" property="qsh"/>
						</td>
						<td nowrap>
						<bean:write name="s" property="fdyxm"/>
						</td>
						<td nowrap>
						<bean:write name="s" property="xqsj"/>
						</td>
						<td nowrap>
						<bean:write name="s" property="zywt"/>
						</td>
						<td nowrap>
						<bean:write name="s" property="xsfk"/>
						</td>
						</tr>
					 </logic:iterate>
					</table>
					</fieldset>
				</logic:notEmpty>
			</logic:equal>
		</html:form>				
  </body>
  <script type="text/javascript">
  function toRfK(){
       var pkV=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
       showTopWin("/xgxt/csmz_gygl.do?method=stu_tofkxx&pkValue="+pkV,"700","600");
       }    

  </script>
</html>
