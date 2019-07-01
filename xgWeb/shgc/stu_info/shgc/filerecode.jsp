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
	<link id="csss" rel="stylesheet" rev="stylesheet"href="js/calendar.css" type="text/css" media="all" />
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="skin1/style/main.css"
		type="text/css" media="all" />
		
	<script language="javascript">
		
		function check_users()
		{
			var userType=document.all['userType'].value;			
			 if("stu"==userType)
			{
				document.getElementById('bjdm').disabled=true;
			}
		}
		
		function check_user_commUpdate()
		{
			var userType=document.all['userType'].value;
			 if("stu"==userType)
			{
				return false;
			}
			else
			{
				commUpdate('address_book.do?doType=view&bjdm=',400,400);
			}
		}
		
		function modify(url){
			var pk = curr_row.cells[2].innerText;
			url += "&wjh="+pk;
			url += "&doType=modi";
			showTopWin(url,500,350);		
		}
		
		function deleteInfo(url){
			var pk = curr_row.cells[2].innerText;
			url += "&wjh="+pk;
			refreshForm(url);
		}
	</script>
		
		
	<base target="_self">
	<body onLoad="check_users()">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<script type="text/javascript"src="/xgxt/dwr/interface/getXjydInfo.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
			<script language="javascript" src="js/calendar.js"></script>
			<script language="javascript" src="js/calendar-zh.js"></script>
			<script language="javascript" src="js/calendar-setup.js"></script>
			
			<html:form action="/address_book" method="post">
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="tableName" name="tableName"
					value="<bean:write name="tableName" scope="request"/>" />
				<input type="hidden" id="realTable" name="realTable"
					value="<bean:write name="realTable" scope="request"/>" />
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="pk" scope="request"/>" />				
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰλ��:ѧ����Ϣ - �ļ� - �����ļ�ά��						
					</div>
				</div>
				<logic:notEqual value="student" name="user">
					<fieldset>
						<legend>
							�� ѯ
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>		
									<td align="left" nowrap>	
										��ȣ�
										<html:select property="nd" style="width:120px" styleId="nd">
											<html:option value=""></html:option>
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>									
										&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
										<html:select property="bjdm" style="width:120px" styleId="bjdm">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>	
										&nbsp;&nbsp;ѧ�ţ�
										<html:text property="xh" />									
										&nbsp;&nbsp;������
										<html:text property="xm" />
										<br/>
										�ļ��ţ�
										<html:text property="wjh" />
										&nbsp;&nbsp;¼�����ڣ�
										<html:text property="lrrq" onclick="return showCalendar('lrrq','y-mm-dd');" styleId="lrrq"/>
										&nbsp;&nbsp;�ļ����ͣ�										
										<html:select property="wjlx"  styleId="wjlx">
										<html:option value=""></html:option>
										<html:option value="��ѧ�ļ�"> ��ѧ�ļ� </html:option>
										<html:option value="�����ļ�"> �����ļ� </html:option>										
										</html:select>							
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/business.do?method=fileRecode')">
											��ѯ
										</button>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
				</logic:notEqual>
				<logic:empty name="rs">
					<br />
					<br />
					<p align="center">
						δ�ҵ��κμ�¼�� 
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��˫��һ�п���ѡ����������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">

									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onClick="rowOnClick(this)" style="cursor:hand"
									ondblclick="modify('business.do?method=showFileRecode')">
									<logic:iterate id="v" name="s" offset="0">
										<td align="left">
											<input type="hidden" value="<bean:write name="v"/>" />
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
					
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<button class="button2" onClick="showTopWin('business.do?method=showFileRecode',500,350)"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onClick="modify('business.do?method=showFileRecode')" style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onClick="if(confirm('��ȷ��ɾ����Ϣ��'))deleteInfo('business.do?method=deleteFileRecode')" style="width:80px">
							ɾ ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onClick="expTab('rsTable','�ļ�һ����','')">
							��ӡ�б�
					</button>
					</div>
					<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
				</logic:equal>
				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
				<SCRIPT>
					alert("�����ɹ�!");
					window.close();				 	
				 	document.getElementById("search_go").click();
				</SCRIPT>
				</logic:equal>
				<logic:equal value="false" name="result">
				<SCRIPT>
				 	alert("����ʧ��!");
				</SCRIPT>
				</logic:equal>
				</logic:notEmpty>				
			</html:form>
		</center>
	</body>
</html>
