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
		<html:form action="/csmz_gygl.do?method=fdy_wstb" method="post">	
		<input type="hidden" name="qshV" value=""/>		
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ��Ԣ���� - ����Ա���� - �����
				</div>
			</div>
		<logic:equal value="false" name="isFdy" >			
			   <div align="center"><font color="red">ֻ�и���Ա�û������!</font> </div>
			</logic:equal>
				<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							�����
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>ְ����:
					</td>
					<td align="left">
						<html:text name='rs' property="zgh" styleId="zgh" readonly="true"/>
					</td>
					<td align="right" width="10%">
						ѧ��:
					</td>
					<td align="left">
						<html:text name='rs' property="xn" styleId="xn" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>����Ա����:
					</td>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<td align="right">
						ѧ��:
					</td>
					<td align="left">
						<html:text name='rs' property="xq" styleId="xq" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>¥��:
					</td>
					<td align="left">
						<html:select name="rs" property="lddm" style="width:150px" styleId="lddm" onchange="GetQshList()">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>���Һ�:
					</td>
					<td align="left">
						<html:select name="rs" property="qsh" style="width:110px"
							onchange="GetCwhList()">
							<html:option value=""></html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>����ʱ��:
					</td>
					<td align="left">
						<html:text name="rs" property="sj" readonly="true"
								onblur="dateFormatChg(this)"
								onclick="return showCalendar('sj','y-mm-dd');"
								style="cursor:hand " />
					</td>
					<td align="left">
						&nbsp;
					</td>
					<td align="left">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>�������Ҫ����:<br>��500����
					</td>
					<td align="left" colspan="3">
					<html:textarea name="rs" property="zywt" cols="76" rows="5" ></html:textarea>	
					</td>	
				</tr>
				<tr>
					<td align="right">
						��ע:<br>��200����
					</td>
					<td align="left" colspan="3">
					<html:textarea  name="rs" property="bz" cols="76" rows="5" ></html:textarea>	
					</td>	
				</tr>
			</table>
				<div class="buttontool" align="center">					
					<button class="button2" onclick="tbDataSave()" style="width:80px" id="buttonSave">
					�ᡡ��	
					</button>					
				</div>
		</html:form>		
		<logic:equal value="true" name="done">
			<script type="text/javascript">
	           alert("�ύ�ɹ���");
	        </script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
	          alert("�ύʧ�ܣ�");
	        </script>
		</logic:equal>
  </body>
  <script type="text/javascript">
  function tbDataSave(){
      if(IsNoEmpty('zgh-xm-lddm-qsh-sj-zywt'))
       {
          if($('zywt').value.length>500){
             alert('��Ҫ������������500�֣�');
             return false;
          }else if($('bz').value.length>200){
             alert('��ע��������200�֣�');
             return false;
          }else{
             refreshForm('/xgxt/csmz_gygl.do?method=savefdy_tbxx');
          }
       }    
  }
  </script>
</html>
