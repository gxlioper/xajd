<%@ page language="java" contentType="text/html; charset=gb2312"%> 

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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />				
	<script language="javascript">
       function hiddenField() {
	     i = document.getElementsByTagName("select").length;
	     for (j = 0; j < i; j++) {
		   document.getElementsByTagName("select")[j].style.visibility = "hidden";
	     } 
      }     
    </script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>		
	<script type='text/javascript' src='/xgxt/dwr/interface/GetGyglDataInfo.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/gygl/csmzGyglFunction.js"></script>
	<base target="_self" />
	<%
      response.setHeader("Pragma","No-cache") ;
      response.setHeader("Cache-Control","no-cache");
      response.setDateHeader("Expires", 0);
    %>
	<body onload="xyDisabled('xy');loadStrInit();">
		<html:form action="/csmz_gygl" method="post">
			<html:hidden name="csmz_gyglForm" property="conditionSqlText"
				styleId="conditionSqlText" />
			<html:hidden name="csmz_gyglForm" property="conditionSqlValue"
				styleId="conditionSqlValue" />
			<html:hidden name="csmz_gyglForm" property="count" styleId="count" />
			<html:hidden name="csmz_gyglForm" property="boy" styleId="boy" />
			<html:hidden name="csmz_gyglForm" property="girl" styleId="girl" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
			<input type="hidden" name="oldCondiSqlValue" id="oldCondiSqlValue" value="<bean:write name="oldCondiSqlValue"/>" />
		    <input type="hidden" name="userType" id="userType" value="<bean:write name="userType"/>" />
			<input type="hidden" name="mappingItems" value="" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>			
			<div class="title">
				<div class="title_img" id="title_m"> 
					��ǰ����λ�ã���Ԣ���� - ������� - ��λ���� --������λ����
				</div>
			</div>
            
            <div id="showDiv" style="display:none" align="center">
					<fieldset style="width:90%;height:90%">
						<legend>
							������ʾ��Ϣ
						</legend>
						<table class='buttontool' hight='100px' >
							<thead>
								<tr>
									<td colspan='2'>
										��סʱ������
									</td>
								</tr>
							</thead>
							<tr>
								<td align='right' width='40%'>
									<font color=red>*</font>��סʱ�䣺
								</td>
								<td align='left'>
								<html:text property="zsrq" styleId="zsrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('zsrq','y-mm-dd','aa');" readonly="true"/>
								</td>
							</tr>
							<tfoot>
								<tr>
									<td colspan='2'>
										<button class='button2' id="kfbtnSave" onclick='xsAddCwColum()'>
											�ύ
										</button>
										&nbsp;&nbsp;
										<button id="kfbtnClose" onclick='hiddenMessage(true,true)' class='button2'>
											�ر�
										</button>
									</td>
								</tr>
							</tfoot>
						</table>
					</fieldset>
				</div>										
			<fieldset >
				<legend>
					��λ����
				</legend>				
				<table width="98%" align="center" class="tbstyle"   bgcolor="#D0E0EE" >
					<tr align="center">
						<td width="30%" align="left" rowspan="2">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;У������
							<html:select property="xqdm" styleId="xq" style="width:130px"
								onchange="xsInitLdXbXdList();">			
								<html:options collection="xiaoqquList" property="dm"
									labelProperty="mc" />
							</html:select>
							<br>							
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�Ա��޶���
							<html:select property="xb" styleId="xb"  style="width:100px" onfocus="beforcwFpSubmit()" 
							onchange="wfpXsListInit();xswFpLdXxList();initXsYFpCwList();" >							    								
							<html:options collection="xbxdList" property="dm"
										labelProperty="mc" />
							</html:select>
							<br>	
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>¥������
							<html:select property="lddm" style="width:100px" styleId="ld"
								onchange="xsCwFpSxListInit()">	
														
							<html:options collection="ldList" property="dm"
							labelProperty="mc" />
							</html:select>
						</td>
						<td width="70%" align="left" colspan="3">
						�꼶��
							<html:select property="nj" disabled="true" style="width:60px"
								onchange="ssFpBjListInit();ssFpBjListInit();">
								<html:options collection="njList" property="nd"
									labelProperty="nd" />
							</html:select>
							&nbsp;&nbsp;<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
							<html:select property="xydm"  styleId="xy" onfocus="beforcwFpSubmit()"
								onchange="initXsYFpCwList();wfpXsListInit();getXsCWFPData();cwfpXaioQuInit();ssFpZyListInit();ssFpBjListInit();">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>							
							&nbsp;&nbsp;<font color="red">*</font>רҵ��
							<html:select property="zydm"  styleId="zy" onfocus="beforcwFpSubmit()"
								onchange="getXsCWFPData();ssFpBjListInit();wfpXsListInit();initXsYFpCwList()">				
								<html:options collection="zyList" property="dm"
									labelProperty="mc" />
							</html:select>
							
							<font color="red">*</font>�༶��
							<html:select property="bjdm" styleId="bj"  onfocus="beforcwFpSubmit()" onchange="getXsCWFPData();wfpXsListInit();initXsYFpCwList();">
								<html:options collection="bjList" property="dm" 
									labelProperty="mc" />
							</html:select>	
							&nbsp;&nbsp;�����ţ�<html:text property="ksh" styleId="ksh" onfocus="beforcwFpSubmit();" onkeypress="if(event.keyCode == 13){refreshForm('/xgxt/bed_distribute.do')}"  style="cursor:hand;"  title="�����뿼���ţ������س������в�ѯ!"></html:text>																								
						</td>
					</tr>
					<tr>
						<td colspan="3">
							&nbsp;&nbsp;&nbsp;δ����ѧ������:
							<span id="allbody" style="width: 100px"><bean:write name="allbody" scope="request"/></span>
							(Ů):
							<span id="allgirl" style="width: 100px"><bean:write name="allgirl" scope="request"/></span>
							(��):
							<span id="allboy" style="width: 100px"><bean:write name="allboy" scope="request"/></span>						
							<br>
							&nbsp;&nbsp;&nbsp;�ѷ���ѧ������:
							<span id="totalBed" style="width: 100px"><bean:write name="totalBed" scope="request"/></span>&nbsp;(Ů):
							<span id="girlBed" style="width: 100px"><bean:write name="girlBed" scope="request"/></span>&nbsp;(��):
							<span id="boyBed" style="width: 100px"><bean:write name="boyBed" scope="request"/></span>	

												
						</td>
					</tr>
					<tr align="center" bgcolor="#D0E0EE">
						<td align="center" width="30%">
							δ���䴲λ
						</td>
						<td align="center" width="30%">
							δ����ѧ��
						</td>
						<td align="center" width="6%">
							
						</td>
						<td align="center" width="40%">
							�ѷ������
						</td>
					</tr>
					
					<tr align="center">
						<td rowspan="2" valign="top">
							���ұ��/��λ��/��λ��
							<br>
							<html:select property="oracleItem" style="width:100%;" size="27"
								styleId="oracleList" multiple="multiple">
								<html:options collection="ssxxList" labelProperty="mc"
									property="dm" />
							</html:select>
						</td>
						<td valign="top">
							������/����/�Ա�
							<br>
							<html:select property="xh" style="width:100%;" size="27"
								styleId="xh"  multiple="multiple">
							   <html:options collection="xsList" labelProperty="mc" property="dm" />							
							</html:select>
						</td>
						<td valign="top">
							<br>
							<br>
							<br>
							<br>
							<br>
							<font color="blue">����</font>
							<br>
							<button class="button2" onclick="xsAddDisBedList()"
								style="width:50px;height: 20px" title="��λ����">
								&rarr;
							</button>
							<br>
							<br>
							<br>
							<br>
							<font color="blue">�ͷ�</font>
							<br>
							<button class="button2" onclick="xsDelDisBedList()" 
								style="width:50px;height: 20px" title="��λ�ͷ�">
								&larr;
							</button>
						</td>
						<td valign="top">
							������/����/�Ա�/���ұ��/��λ��/���䴲λ��/��סʱ��
							<br>
							<html:select property="sql" size="27" style="width:100%" styleId="sql"
								multiple="multiple">
							 <html:options collection="fpcwList" labelProperty="mc" property="dm" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="3">
							<input class="button2" type="button" name="button1"
								style="width:100px" value="ȷ ��"
								onclick="xsBedDistSave()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="button2" name="button2"
								style="width:100px" value="�� ��"
								onclick="clearDisList('/xgxt/bed_distribute.do')" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%--							<input type="button" class="button2" name="button2"--%>
<%--								style="width:100px" value="Ĭ �� �� ��"--%>
<%--								onclick="defaultDisBed()" />--%>
						</td>
					</tr>
				</table>
			</fieldset>
			<div id="tmpdiv"></div>
		</html:form>		
		<logic:notEmpty name="doFlag">
			<logic:equal name="doFlag" value="true">
				<script>
				alert("�����ɹ�!");				
				</script>
			</logic:equal>
			<logic:equal name="doFlag" value="false">
				<script>
				alert("����ʧ��!");				
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
