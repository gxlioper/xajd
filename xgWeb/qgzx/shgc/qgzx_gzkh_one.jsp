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
		
   	function sumpd(){
   			var xxdm = document.getElementById("xxdm").value;
			var sum=document.forms[0].ysj.value;			
			var myzgxs=document.forms[0].myzgxs.value;
			var xxdm = document.getElementById("xxdm").value;
			if(xxdm == "10856"){
				sum = document.getElementById("gzsj").value;
			}
			if(parseInt(sum)>parseInt(myzgxs)){
				alert("�ܹ���ʱ�䳬��ÿ�������ʱ��"+myzgxs+"Сʱ!");
				return false;
			}			
			return true;
		}
		
		function initValue(){
			 var kssj = document.getElementById("kssj").value;
			 var jssj = document.getElementById("jssj").value;
			 if(kssj!=null && kssj!=""){
			 	 document.getElementById("kshour").value = kssj.substring(0,kssj.indexOf('��'));
			 	 document.getElementById("ksminute").value = kssj.substring(kssj.indexOf('��')+1,jssj.indexOf('��'));
			 	 document.getElementById("jshour").value = jssj.substring(0,jssj.indexOf('��'));
			 	 document.getElementById("jsminute").value = jssj.substring(jssj.indexOf('��')+1,jssj.indexOf('��'));
			 }
		}
	  function saveInfo(url){
	  	document.forms
	  }
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();document.all('buttonClose').focus();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
			<div class="title_img" id="title_m">
					��ǰ����λ�ã��ڹ���ѧ - ���� - ѧ��������¼ - ����ѧ�����������¼
			</div>
			</div>
						<input type="hidden" name="pkVal" value="<bean:write name="rs" property="xh||gwdm||sqsj"/>" />
						<input type="hidden" name="doType" value="<bean:write name="doType"/>" />
						<input type="hidden" name="xh" value="<bean:write name="rs" property="xh" />" />
						<input type="hidden" id="mxsbc" name="mxsbc" value="<bean:write name="mxsbc" scope="request"/>"/>
						<input type="hidden" id="myzgxs" name="myzgxs" value="<bean:write name="myzgxs" scope="request"/>"/>
						<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm" scope="request"/>"/>
						<input type="hidden" id="ysj" name="ysj" value=""/>
						<input type="hidden" id="yje" name="yje" value=""/>
						<table width="100%" align="center" class="tbstyle" id="tb">
						<tr><td>
						<table width="100%" align="center" class="tbstyle">
							<thead>
								<tr style="height:22px">
									<td colspan="4" align="center">
										����ѧ�����������¼
									</td>
								</tr>
							</thead>
							<tr style="height:22px">
								<td align="right">
									ѧ�ţ�
								</td>
								<td align="left">
									<input type="hidden" id="xh" name="xh" value="<bean:write name="rs" property="xh" />"/>
									<bean:write name="rs" property="xh" />
								</td>
								<td align="right">
									��ȣ�
								</td>
								<td align="left">
									<bean:write name="rs" property="nd" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									������
								</td>
								<td align="left">
									<bean:write name="rs" property="xm" />
								</td>
								<td align="right">
									ѧ�꣺
								</td>
								<td align="left">
									<bean:write name="rs" property="xn" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									�Ա�
								</td>
								<td align="left">
									<bean:write name="rs" property="xb" />
								</td>
								<td align="right">
									��λ���ƣ�
								</td>
								<td align="left">
									<bean:write name="rs" property="gwdm" />
									<input type="hidden"  id="gwmc" name="gwmc" value="${rs.gwdm}"/>
									<input type="hidden"  id="gwdm" name="gwdm" value="${rs.gwdm}"/>
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									�꼶��
								</td>
								<td align="left">
									<bean:write name="rs" property="nj" />
								</td>
								<td align="right">
									����ʱ�䣺
								</td>
								<td align="left">
									<bean:write name="rs" property="sqsj" />
									<input type="hidden" id="sbsj" name="sbsj" value="<bean:write name="rs" property="sqsj" />"/>
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />��
								</td>
								<td align="left">
									<bean:write name="rs" property="xymc" />
								</td>
								<td align="right">
									�Ƿ���������
								</td>
								<td align="left">
									<bean:write name="rs" property="sfpks" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									רҵ��
								</td>
								<td align="left">
									<bean:write name="rs" property="zymc" />
								</td>
								<td align="right">
									��ϵ�绰��
								</td>
								<td align="left">
									<bean:write name="rs" property="lxdh" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									�༶��
								</td>
								<td align="left">
									<bean:write name="rs" property="bjmc" />
								</td>
								<td align="right">
									��ˣ�
								</td>
								<td align="left">
									<bean:write name="rs" property="yesNo" />
								</td>
							</tr>	
							<tr style="height:22px">
								<td align="right">
									�·ݣ�
								</td>
								<td align="left">
									<bean:write name="yf"/>
									<input type="hidden"  id="yf" name="yf" value="<bean:write name="yf"/>"/>
								</td>
								<td align="right">
									�����ܹ���ʱ�䣺
								</td>
								<td align="left">
									<html:text property="gzsj" name="rs" styleId="gzsj">									
									</html:text>Сʱ
								</td>				
							</tr>										
							<logic:present name="xskh">
								<tr>
									<td align="right">
										�������֣�
									</td>
									<td align="left">
										<html:select name="rs" property="gzbx" style="width:120px"
											styleId="gzbx">
										<html:option value="��"> �� </html:option>
										<html:option value="��"> �� </html:option>
										<html:option value="��"> �� </html:option>
										</html:select>
									</td>
									<td>
									</td>
									<td>
									</td>
								</tr>
							</logic:present>
							<logic:present name="gzjl">
								<td align="right">
									���������
								</td>
								<td colspan="3">
									<html:textarea name='rs' property='xsgzqk' rows="4" styleId="xsgzqk" style="width:99%" />
								</td>
							</logic:present>
							<logic:present name="xskh">
								<td align="right">
									���������
								</td>
								<td colspan="3"><html:textarea name='rs' property='xsgzqk' rows="4" styleId="xsgzqk" readonly="true" style="width:99%" />
								</td>
							</logic:present>
								</table>
								</td></tr>
								<tr>
								  <td>&nbsp;
								  <logic:present name="allow">
								 <font color="red"><bean:write name="allow"/></font>
								  </logic:present>
								  </td>
								</tr>
						</table>
						<div class="buttontool" align="center">
							<logic:notPresent name="allow">
							<button type="button" class="button2"
								onclick="if(sumpd()) {refreshForm('/xgxt/stu_work_info.do?act=save&doType=' + document.getElementById('doType').value);Close();window.dialogArguments.document.getElementById('search_go').click();}"
								style="width:80px" id="buttonSave">
								�� ��
							</button>	
							</logic:notPresent>						
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="expTab('tb','','')"
								style="width:80px" id="buttonPrint">
								��ӡ�б�
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
								id="buttonClose">
								�� ��
							</button>
					</div>
		</html:form>
	</body>
</html>
