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
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body >
	    <script language="javascript" src="js/function.js"></script>
	    <script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetGyglDataInfo.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/gygl/csmzGyglFunction.js"></script>	
		<html:form action="/csmz_gygl" method="post">
		<input type="hidden" name="nj" id="nj" value="<bean:write name="nj" scope="request"/>">
		<input type="hidden" name="xudm" id="xydm" value="<bean:write name="xydm" scope="request"/>">
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">��ǰ����λ�ã� ��Ԣ���� - ��Ϣά�� - ס����Ϣά��  </span>
				</div>
			</div>
			<logic:equal value="true" name="done">
			     <script language="javascript">
			       alert("��ӳɹ���");
			       close();
			       dialogArgumentsQueryChick();
			     </script>
			</logic:equal>
			<logic:equal value="false" name="done">
			<script language="javascript">
			       alert("���ʧ�ܣ�");
			       close();
			       dialogArgumentsQueryChick();
			     </script>
			</logic:equal>			
				<fieldset>
					<legend>
						ס����Ϣά��
					</legend>
					<div id="items" name="items" style="display:none; position: absolute;color: blue; " >
					<span>�����뿼���ţ������س���</span>
					</div>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>�����ţ�
							</td>
							<td align="left">							
							<html:text property="xh" readonly="false" 
								styleId="xh" onkeypress="autoChkStuInfo(event.keyCode,this)"   
								onfocus="showItems();" onblur="hiddenItems();" />					
							</td>
							<td align="right">
								<font color="red">*</font>¥�����ƣ�
							</td>
							<td align="left">
								<html:select name="rs" property="lddm" style="width:130px"
									styleId="lddm" onchange="ldQshInit()">
									
									<html:options collection="ldList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>													
						</tr>
						<tr>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" readonly="true" />
							</td>
							<td align="right">
								<font color="red">*</font>�����ţ�
							</td>
							<td align="left">
								<html:select name="rs" property="qsh" style="width:130px"
									styleId="qsh" onchange="ldCwhInit()">
																	
									<html:options collection="ssxxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>								
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
							</td>
							<td align="right">
								<font color="red">*</font>��λ�ţ�
							</td>
							<td align="left">
								<html:select name="rs" property="cwh" style="width:130px" styleId="cwh" >
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="cwxxList" property="dm"
										labelProperty="mc" />																		
								</html:select>
							</td>							
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj"  readonly="true"/>
							</td>
							<td align="right">
								��סʱ�䣺
							</td>
							<td align="left">
								<html:text name='rs' property="zsrq" styleId="zsrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('zsrq','y-mm-dd','aa');" readonly="true"/>
							</td>							
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy"  readonly="true"/>
							</td>
							<td align="right">
								
							</td>
							<td align="left">
											</td>							
						</tr>
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" readonly="true" />
							</td>							
							<td align="right" >
							</td>
							<td align="left" colspan="">
							</td>							
						</tr>						
						
					</table>
				</fieldset>
				<div class="buttontool">				 				  
					<button class="button2" onclick="xsZsChkAndSubmit('xh-lddm-qsh-cwh')"
						style="width:80px" id="buttonSave">
						�� ��
					</button>					
					&nbsp;&nbsp;&nbsp;&nbsp;				
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
		</html:form>
	</body>
<script language="javascript">
function showItems(){
	var items = document.getElementById("items");
	if($("xh").value==""){
	items.style.left = (screen.availwidth)/10;
	items.style.top = ((screen.availheight)/25);
	items.style.display = "block";
	}
}
function hiddenItems(){
    var items = document.getElementById("items");
    items.style.display = "none";
}
</script>
</html>