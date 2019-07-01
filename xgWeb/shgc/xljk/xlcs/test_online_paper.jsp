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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
function loadTestSelectItem(){
  var xxArray= new Array();
  var txt=document.forms[0].xxStr.value;
  var splitTxt=txt.split("!!SplitSignOne!!");
  var stlxtype;
  var tj_flag=document.all['tj_flag'].value;
  if("yes"==tj_flag)
  {
  	
  }
  else
  {
  	for(i=0;i<splitTxt.length;i++)
    	xxArray[i]=splitTxt[i].split("!!SplitSignTwo!!");
  	for(j=0;j<xxArray.length;j++){
    	if(xxArray[j][0]!="")
    	{
    	document.getElementById(xxArray[j][0]).innerHTML=document.getElementById(xxArray[j][0]).innerHTML+"<br/>";
    	if(xxArray[j][1]=="001")
           	stlxtype="radio";     
    	else if(xxArray[j][1]=="002"||xxArray[j][1]=="003")
           	stlxtype="checkbox"; 
    	document.getElementById(xxArray[j][0]).innerHTML=document.getElementById(xxArray[j][0]).innerHTML+"<input type='"+stlxtype+"' name='"+xxArray[j][0]+"' value='"+xxArray[j][2]+"'>&nbsp;"+xxArray[j][2]+"&nbsp;&nbsp;"+xxArray[j][3];
  		jsys();
  		document.all['start_flag'].value="yes";
  		}
  		else
  		{
  			alert("û������");
  			return false;
  		}
  	  }
	}
}

function saveTestSelectItem(){

 	var userChoice = window.confirm("ȷ�Ͻ�����");
	if(true==userChoice)
	{
	    var selectedStr="";
	    var tmp;
	    document.getElementById('buttonSave').disabled=false;
	    document.getElementById('buttonClose').disabled=false;
	    if(document.all['start_flag'].value=="yes")
	    {
	    	for(i=0;i<tp.rows.length;i++){
	       	if(i!=0)
	        selectedStr += "!!SplitSignOne!!";
	       	selectedStr += tp.rows[i].cells[0].id;
	       	selectedStr += "!!SplitSignTwo!!";
	       	tmp=document.getElementsByName(tp.rows[i].cells[0].id);
	      	 for(j=0;j<tmp.length;j++)
	         	selectedStr +=(tmp[j].checked)?tmp[j].value:"";
	    	}
	    	document.forms[0].xxStr.value = selectedStr;
	    	document.all['tj_flag'].value="yes";
	    	refreshForm('/xgxt/xljk_xlcs_zxpc.do?act=zxpc&doType=submit_testPaper');
	    	window.setTimeout("qrtj()",9999);
	    	document.getElementById('buttonSave').disabled=true;
	    }
	    else {
	    	alert("�����ύ");
	    	return false;
	    }
    }
    else if(false==userChoice)
    {
    	return false;
    }
}
	function check_applyed()
	{
		var tj_flag=document.all['tj_flag'].value;
  		if("yes"==tj_flag)
  		{
  			
  		}
  		else
  		{
  			loadTestSelectItem();
  		}
	}
	function qrtj()
	{
		alert("ȷ���ύ��");
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="check_applyed()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<logic:equal name="message" value="true">
						<script>
						alert("��֤ͨ������ʼ���ԣ�");
						enter();
						</script>
					</logic:equal>
		<html:form action="/xljk_xlcs_zxpc" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					������ - ������� - ���߲��� - ����
				</div>
			</div>
			<logic:empty name="sjinfo">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="sjinfo">
				<input type="hidden" id="sjlsh" name="sjlsh" value="<bean:write name="sjinfo" property="sjlsh"/>" />
				<input type="hidden" id="xxStr" name="xxStr" value="<bean:write name="xxStr"/>" />
				<input type="hidden" id="dtkssj" name="dtkssj" value="${dtkssj}" >
				<input type="hidden" id="lssj" name="lssj" value="${dtkssj}" >
				<input type="hidden" id="tj_flag" name="tj_flag" value="<bean:write name="tj_flag" scope="request"/>" >
				<input type="hidden" id="start_flag" name="start_flag" value="no" >
				<input type="hidden" id="xh" name="xh" value="<bean:write name="rs" property="xh"/>" >
				<table width="99%" class="tbstyle">
					<tr>
						<td align="center">
							<B><font size="5"><bean:write name="sjinfo"
										property="sjm" />
							</font></B>
						</td>
					</tr>
					<tr>
						<td align="left">
							������ʱ:<p align="left" id="showdate"></p>
						</td>
					</tr>
					<tr>
						<td align="left">
							<fieldset>
								<legend>
									�Ծ�˵����
								</legend>
								<bean:write name="sjinfo" property="sjsm" />
							</fieldset>
						</td>
					</tr>
					<tr>
						<td align="left">
							<fieldset>
								<legend>
									��
									<bean:write name="total" />
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:iterate id="sjinfoList" name="sjinfoList">
										<bean:write name="sjinfoList" property="stlxmc" />:<bean:write
											name="sjinfoList" property="flTotal" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:iterate>
									ʱ���޶�:
									<bean:write name="sjinfo" property="sjxd" />
									(����)
								</legend>
								<logic:empty name="stList">��������!</logic:empty>
								<logic:notEmpty name="stList">
								
									<table width="100%" class="tbstyle" id="tp">
										<logic:iterate id="stList" name="stList">
											<tr>
												<td align="left"
													id="<bean:write name="stList" property="stlsh"/>"
													width="100%">
													<B><bean:write name="stList" property="stxh" />.</B>&nbsp;
													<bean:write name="stList" property="stnr" />
													&nbsp;&nbsp;[
													<bean:write name="stList" property="stlxmc" />
													&nbsp;&nbsp;
													<bean:write name="stList" property="stndjbmc" />
													]
													<logic:notEmpty name="stList" property="stfz">&nbsp;&nbsp;<bean:write
															name="stList" property="stfz" />��</logic:notEmpty>
												</td>
											</tr>
										</logic:iterate>
									</table>
								</logic:notEmpty>
							</fieldset>
						</td>
					</tr>
				</table>
				<br />
				<div class="buttontool">
					<button class="button2"
						onclick="saveTestSelectItem()"
						style="width:80px" id="buttonSave">
						&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal name="message" value="record_success">
			<script>
				alert("�ύ�ɹ���");
				Close();
		</script>
		</logic:equal>
	</body>
</html>
