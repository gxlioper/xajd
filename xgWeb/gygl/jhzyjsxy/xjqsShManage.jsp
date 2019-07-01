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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript">
function checkType(){  
   var userType = $("userType").value;
   if(userType=="xx"||userType=="admin"){
      if($("checkpass")){ $("checkpass").value="ѧУ���ͨ��";}
      if($("checknopass")){ $("checknopass").value="ѧУ��˲�ͨ��";}
   }else if(userType=="xy"){
      if($("checkpass")){ $("checkpass").value="<bean:message key="lable.xsgzyxpzxy" />���ͨ��";}
      if($("checknopass")){ $("checknopass").value="<bean:message key="lable.xsgzyxpzxy" />��˲�ͨ��";}
   }else{
      if($("checkpass")){ $("checkpass").value="����Ա���ͨ��";}
      if($("checknopass")){ $("checknopass").value="����Ա��˲�ͨ��";}
   }
}
function chkView(){
    var url = "/xgxt/jhzy_gygl.do?method=xjqsChek&pkValue=";
    var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	url += pk;
    showTopWin(url,"600","400");              		                  
 }
 function check(str){
 		   var url ='';
 		   var temp = '';
 		   var pkVArray = "";
 		   if(str=="yes"){
 		   		url = "/xgxt/jhzy_gygl.do?method=xjqsShManage&doType=shtg"; 
 		   		temp = '"ͨ��';
 		   }else{
 		   	    url = "/xgxt/jhzy_gygl.do?method=xjqsShManage&doType=shbtg"; 
 		   	    temp = '��ͨ��';
 		   }       
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){	    		 
	    		 pkVArray+=document.getElementsByName("pkV")[i].value+"@";
	    	  }	    	  
		   }		   
		   if (pkVArray==""){
			   alert("��ѡ��Ҫ�����ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
			   return false;
		   }
		   pkVArray=pkVArray.substring(0,pkVArray.length-1); 	
		   document.forms[0].pkVStr.value = pkVArray;	 	   
		   if (confirm("ȷ��Ҫ\""+temp+"\"��ѡ��¼��")){
			     refreshForm(url);
		   }         		                  
 }
</script>
	<body onload="checkType()">
		<center>
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
			<script type="text/javascript"
				src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="js/AjaxFunction.js"></script>
			<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
			
			<html:form action="/jhzy_gygl" method="post">
				<input type="hidden" name="pkVStr" id="pkVStr" value=""/>
				<input type="hidden" id="userType" name="userType"
					value="${userType}" />
				<input type="hidden" id="userName" name=" userName "
					value="${ userName }" />
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã���Ԣ���� - �Ǽ��������ҹ��� - ��� - �Ǽ��������
					</div>
				</div>
				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									ѧ�꣺
									<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp; �·ݣ�
									<html:select property="yf" styleId="yf" style="width:60px">
										<html:option value=""></html:option>
										<html:option value="01">01</html:option>
										<html:option value="02">02</html:option>
										<html:option value="03">03</html:option>
										<html:option value="04">04</html:option>
										<html:option value="05">05</html:option>
										<html:option value="06">06</html:option>
										<html:option value="07">07</html:option>
										<html:option value="08">08</html:option>
										<html:option value="09">09</html:option>
										<html:option value="10">10</html:option>
										<html:option value="11">11</html:option>
										<html:option value="12">12</html:option>
									</html:select>
									&nbsp;&nbsp; <bean:message key="lable.xsgzyxpzxy" />��
									<logic:equal value="xy" name="userType">
										<html:select property="xydm" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:notEqual value="xy" name="userType">
										<html:select property="xydm" styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="refreshForm('/xgxt/jhzy_gygl.do?method=xjqsShManage&doType=query');">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									¥��
									<html:select property="lddm" styleId="lddm"
										onchange="getLcList()">
										<html:options collection="ldList" property="dm"
											labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;¥��
									<html:select property="lc" styleId="lc"
										onchange="getQshList2()">
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;����
									<html:select property="qsh" styleId="qsh">
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;���״̬��
									<html:select property="yesNo" styleId="yesNo">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
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
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ˣ�������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td align="center">
										<input type="checkbox" name="all" value="all" onclick="chec()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="chkView()">
									<td align="center">
										<input type="checkbox" name="pkV"
											value="<bean:write name="s" property="pk"/>">
									</td>
									<td align="center">
										<bean:write name="s" property="xn" />
									</td>
									<td align="center">
										<bean:write name="s" property="qsh" />
									</td>
									<td align="center">
										<bean:write name="s" property="ldmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="cs" />
									</td>
									<td align="center">
										<bean:write name="s" property="lxdh" />
									</td>
									<td align="center">
										<bean:write name="s" property="dj" />
									</td>
									<td align="center">
										<bean:write name="s" property="shzt" />
									</td>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
					<br />
					<br />
				</logic:notEmpty>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="check('yes')" id="checkpass">
							ͨ ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="check('no')" id="checknopass">
							��ͨ��
						</button>
					</div>
				</center>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
	<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "98%";
					window.setInterval("initBTNTool('btn')",1);
	</script>
	<logic:equal value="true" name="result">
		<script language="javascript">
			alert('��˳ɹ���');
			document.getElementById('search_go').click();
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script language="javascript">
			alert('���ʧ�ܣ�');
		</script>
	</logic:equal>
</html>
