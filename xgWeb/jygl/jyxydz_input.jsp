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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript"
		src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	function loadShi(){
		var shen = document.getElementById("jgshen").value;	
		getStuDetails.getShiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "jgshi";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
					}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}	
			if (data.xianList !=null){
				var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
					}
			}
		});
	}
	function loadShi2(){
		var shen = document.getElementById("jgshen2").value;	
		getStuDetails.getShiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "jgshi2";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
					}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}	
			if (data.xianList !=null){
				var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
					}
			}
		});
	}
		function loadShi3(){
		var shen = document.getElementById("jgshen3").value;	
		getStuDetails.getShiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "jgshi3";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
					}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}	
			if (data.xianList !=null){
				var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
					}
			}
		});
	}
	
	function savejyxy(){
	var xsxh = document.getElementById("xsxh").value;
	var username = document.getElementById("name").value;
	if(xsxh==""){
	alert("ѧ�Ų���Ϊ�գ�");
	return false;
	}
	if(username==""){
	alert("��������Ϊ�գ�");
	return false;
	}
	
	
	var xysbh = document.getElementById("xysbh").value;
	var xb = document.getElementById("xb").value;
	var id = document.getElementById("id").value;
    var mz = document.getElementById("mz").value;
    var xldm = document.getElementById("xldm").value;
    var zzmm = document.getElementById("zzmm").value;
    var xzdm = document.getElementById("xzdm").value;
    if(xysbh == ""){
    	alert("Э�����Ų���Ϊ��");
    	return false;
    	}
    
    if(mz == ""){
    	alert("���岻��Ϊ��");
    	return false;
    	}
    
    if(xldm == ""){
    	alert("ѧ������Ϊ��");
    	return false;
    	}
    
    if(zzmm == ""){
    	alert("������ò����Ϊ��");
    	return false;
    	}
    
    if(xzdm == ""){
    	alert("ѧ�Ʋ���Ϊ��");
    	return false;
    	}
    if(xb == ""){
    	alert("�Ա���Ϊ��");
    	return false;
    	}
    /*
    var blueno = document.getElementById("blueno").value;
    var dajsd = document.getElementById("dajsd").value;
    var dajsddz = document.getElementById("dajsddz").value;
    var dajsdyb = document.getElementById("dajsdyb").value;
    
    var dwdh = document.getElementById("dwdh").value;
    var dwyb = document.getElementById("dwyb").value;
    var wyj =document.getElementById("wyj").value;
    var dynypjgz =document.getElementById("dynypjgz").value;
    
    var byqxdm =document.getElementById("byqxdm").value;
    var dwmc =document.getElementById("dwmc").value;
    var sydzgbm =document.getElementById("sydzgbm").value;
    
    var dwxzdm2 =document.getElementById("dwxzdm2").value;
    var dwdz = document.getElementById("dwdz").value;
    
    var dqlx = document.getElementById("dqlx").value;
    var hyfl = document.getElementById("hyfl").value;
    var zydk = document.getElementById("zydk").value;
    var dwdq = document.getElementById("dwdq").value;
    var wjyyy = document.getElementById("wjyyy").value;
    var sydqdm = document.getElementById("sydqdm").value;
    var jzzhlbbzwdm = document.getElementById("jzzhlbbzwdm").value;
    
    
    if(xysbh == ""){
    	alert("Э�����Ų���Ϊ��");
    	return false;
    	}

	if(byqxdm=="01"&&dwdq==""||byqxdm=="02"&&dwdq==""||byqxdm=="03"&&dwdq==""||
	   byqxdm=="04"&&dwdq==""||byqxdm=="12"&&dwdq==""||
	   byqxdm=="13"&&dwdq==""||byqxdm=="14"&&dwdq==""||
	   byqxdm=="15"&&dwdq==""||byqxdm=="17"&&dwdq==""){   
	   alert("��λ�������Ʋ���Ϊ�գ�")
	   return false;   
	}
	if(byqxdm!="04"&&dwdq=="����"){
	alert("��ҵȥ���뵥λ�������Ʒ�����ͻ�������º˶ԣ�");
	return false;
	}
	
	
	if(byqxdm!="01"&&byqxdm!="02"&&byqxdm!="03"&&byqxdm!="04"&&byqxdm!="12"&&byqxdm!="13"
	   &&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"&&wjybz=="0"){
	   alert("���ٴ�ȷ���Ƿ��ҵ����δ��ҵ��δ��ҵ��־������")
	   return false;
	}
	if(byqxdm!="01"&&byqxdm!="02"&&byqxdm!="03"&&byqxdm!="04"&&byqxdm!="12"&&byqxdm!="13"
	   &&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"&&dwdq!=""){
	   alert("���ٴ�ȷ���Ƿ��ҵ����δ��ҵ����λ������������")
	   return false;
	}
	
	
	
	
	if(zgbm=="��ѡ��"){
	alert("��ѡ�����ܲ��ţ�");
	return false;
	}
	if(xxdjh.length!=7&&xxdjh!=""){
	alert("��Ϣ�ǼǺų���ӦΪ7λ��");
	return false;
	}
	if(!isNumber(xxdjh)&&xxdjh!=""){
	alert("��Ϣ�ǼǺ�ӦΪ���֣�");
	return false;
	}
	if(zzjgdm.length!=9&&zzjgdm!=""){
	alert("��֯�������볤��ӦΪ9λ��");
	return false;
	}
	if(dwdq!=""&&dwmc==""){
		alert("�����뵥λ���ƣ�");
	return false;
	}
	
	if(wjybz==""){
	alert("δ��ҵ��־����Ϊ�գ�");
	return false;
	}
	
	if(byqxdm=="01"&&wjybz=="2"||byqxdm=="02"&&wjybz=="2"||byqxdm=="03"&&wjybz=="2"||
	   byqxdm=="04"&&wjybz=="2"||byqxdm=="12"&&wjybz=="2"||byqxdm=="13"&&wjybz=="2"||
	   byqxdm=="14"&&wjybz=="2"||byqxdm=="15"&&wjybz=="2"||byqxdm=="17"&&wjybz=="2"){
	   alert("��ҵȥ����δ��ҵ��־������ͻ�������º˶ԣ�");
	   return false;
	}
	
	
	
	if(zzmmdm==""){
	alert("������ò���벻��Ϊ�գ�");
	return false;
	}
	if(wjybz=="1"&&wjyyy==""){
	alert("������δ��ҵԭ��");
	return false;
	}
	if(lxdz==""){
	alert("��ϵ��ַ����Ϊ�գ�");
	return false;
	}
	if(yb==""){
	alert("�ʱ಻��Ϊ�գ�");
	return false;
	}
	if(!isNumber(yb)){
	alert("�ʱ�Ӧ�����֣�");
	return false;
	}
	if(yb.length!=6){
	alert("�ʱ೤��ӦΪ6λ��");
	return false;
	}
	if(dh==""){
	alert("�绰����Ϊ�գ�");
	return false;
	}
	if(!isNumber(dh)){
	alert("�绰����Ӧ�����֣�");
	return false;
	}
	if(dh.length<7){
	alert("�绰���볤�Ȳ���ȷ��");
	return false;
	}
	if(jzzhlbbzwdm==""){
	alert("��ס֤�������־λ����Ϊ�գ�");
	return false;
	}
	if(sydqdm!="310000"&&sydzgbm==""){
	alert("��Դ�����ܲ��Ų���Ϊ�գ�");
	return false;
	}
	if(sydqdm!="310000"&&byqxdm=="04"&&dwmc!=sydzgbm){
	alert("��ѡ���������λ����Ӧ��ͬ����Դ�������ܲ��ţ�")
	return false;
	}
	if(dwxzdm=="��ѡ��"){
	alert("��ѡ��λ���ʴ��룡");
	return false;
	}
    if(!isNumber(blueno)&&blueno!=""){
	alert("������׼�ĺ�ӦΪ���֣�");
	return false;
	}
	if(dajsdyb!=""&&!isNumber(dajsdyb)){
	alert("�������յ��ʱ�ӦΪ���֣�");
	return false;
	}
	
	
	
	
	
	
	if(dwdh!=""&&!isNumber(dwdh)){
	alert("��λ�绰ӦΪ���֣�");
	return false;
	}
	if(dwyb!=""&&!isNumber(dwyb)){
	alert("��λ�ʱ�ӦΪ���֣�");
	return false;
	}
	if(wyj!=""&&!isNumber(wyj)){
	alert("ΥԼ��ӦΪ���֣�");
	return false;
	}
    if(dynypjgz!=""&&!isNumber(dynypjgz)){
	alert("��һ����ƽ������ӦΪ���֣�");
	return false;
	}
	
	
	
	if(dwxzdm2==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("��λ���ʴ���2����Ϊ�գ�");
	return false;
	}
	if(dwxzdm2!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("��λ���ʴ���2������д��");
	return false;
	}
	
	
	
	if(dwdz==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("��λ��ַ����Ϊ�գ�");
	return false;
	}
	if(dwdz!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("��λ��ַ������д��");
	return false;
	}
	
	if(dwdh==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("��λ�绰����Ϊ�գ�");
	return false;
	}
	if(dwdh!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("��λ�绰������д��");
	return false;
	}
	

	
	if(dwyb==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("��λ�ʱ಻��Ϊ�գ�");
	return false;
	}
	if(dwyb!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("��λ�ʱ಻����д��");
	return false;
	}
	
	
	
	if(wyj==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("ΥԼ����Ϊ�գ�");
	return false;
	}
	if(wyj!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("ΥԼ������д��");
	return false;
	}
	
	if(dynypjgz==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("��һ����ƽ�����ʲ���Ϊ�գ�");
	return false;
	}
	if(dynypjgz!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("��һ����ƽ�����ʲ�����д��");
	return false;
	}
	
	
	if(dqlx==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("����������Ϊ�գ�");
	return false;
	}
	if(dqlx!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("������������д��");
	return false;
	}
	
	
	
	if(hyfl==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("��ҵ���಻��Ϊ�գ�");
	return false;
	}
	if(hyfl!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("��ҵ���಻����д��");
	return false;
	}
	
	
	
	if(zydk==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("רҵ�Կڲ���Ϊ�գ�");
	return false;
	}
	if(zydk!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("רҵ�Կڲ�����д��");
	return false;
	}
	*/
		 	document.forms[0].action = "/xgxt/savejyxy.do?doType=save";
		 	document.forms[0].submit();
    }
    
   
    
    function refreshtheweb(){
    var byqxdm = document.getElementById("byqxdm").value;
    var dwdq = document.getElementById("dwdq").value;
    var xsxh = document.getElementById("xsxh").value;
    var zgbm = document.getElementById("zgbm").value;
    
                document.forms[0].action = "/xgxt/savejyxy.do?doType=go&byqxdm="+byqxdm+"&dwdq="+dwdq+"&xsxh="+xsxh+"&zgbm"+zgbm;
                document.forms[0].submit();       
    }
    
    function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    }  
	
	function jyxyreinput(){
	            window.location.href="/xgxt/jyxy_input.do?act=first";  
	}
	
	</script>
	<body>

		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã���ҵ���� - ��ҵЭ�鷽�� - ��ҵЭ��¼��
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<html:hidden name="rs" property="nd" />

				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height: 25px">
							<td colspan="4" align="center">
								<b>��д��ҵЭ��</b>
							</td>
						</tr>
						<tr style="height: 22px">
							<td colspan="4" align="center">

								<b> ѧ��������Ϣ</b>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="4">
								ѧ�����:
								<html:text property="xslb" name="rs" readonly="true"
									style="width:50px" />
								&nbsp;&nbsp;��ҵ���:
								<html:text property="bynd" name="rs" readonly="true"
									style="width:40px" />
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
								<html:text property="xymc" name="rs" readonly="true"
									style="width:130px" />
									
								<html:hidden name="rs" property="xxdm" />
							    <html:hidden name="rs" property="xxmc" />
							    <html:hidden name="rs" property="nd" />
								
								&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="black">ע:</font>
								<font color="red">*</font>Ϊ�������� &nbsp; ������ϵͳ���� &nbsp;&nbsp;
								<button
									onclick="showTopWin('/xgxt/turndmkqueryjsp.do',427,350);"
									class="button2">
									������ѯ��
								</button>
							</td>
						</tr>
					</thead>
					<tr style="height: 22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right" style="">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" style="">
								<html:text name='rs' property="xsxh" styleId="xsxh"
									style="width:210px" readonly="true" />
								<button onclick="showTopWin('/xgxt/jyxyTurnInfo.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right" style="">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" style="">
								<html:text property="xsxh" name="rs" styleId="xsxh"
									readonly="true" style="width:210px" />
							</td>
						</logic:equal>
						<td align="right">
							<font color="red">*</font>Э�����ţ�
						</td>
						<td align="left">
							<html:text name="rs" property="xysbh" readonly="true"
								style="width:210px" />
						<td align="right">
					</tr>
					<tr style="height: 22px">
						<td align="right" style="">
							<font color="red">*</font>������
						</td>
						<td align="left">
							<html:text name="rs" property="name" readonly="true"
								style="width=33%" />
						</td>
						<td align="right" style="">
							<font color="red">*</font>�Ա�
						</td>
						<td align="left">
							<html:text name="rs"  property="xb" readonly="true"
								style="width=33%" />
							<html:hidden name="rs"  property="xbdm" />
					</tr>
					<tr style="height: 22px">
						<td align="right">
							<font color="red">*</font>���֤�ţ�
						</td>
						<td align="left">
							<html:text name="rs" property="id" readonly="true"
								style="width:210px" />
						</td>
						<td align="right">
							QQ�ţ�
						</td>
						<td align="left">
							<html:text name="rs" property="qq" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height: 22px">
						<td align="right">
							<font color="red">*</font>רҵ���ƣ�
						</td>
						<td align="left">
							<html:text name="rs" property="zymc" readonly="true"
								style="width:210px" />
						</td>
						<td align="right">
							<font color="red">*</font>���壺
						</td>
						<td align="left">
							<html:text name="rs" property="mz" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height: 22px">
						<td align="right">
							<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<html:text name="rs" property="xymc" readonly="true"
								style="width:210px" />
						</td>
						<td align="right">
							<font color="red">*</font>ѧ����
						</td>
						<td align="left">
							<html:text name="rs" property="xldm" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height: 22px">
						<td align="right">
							<font color="red">*</font>������ò��
						</td>
						<td align="left">
							<html:select name="rs" property="zzmm" styleId="zzmmdm"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="zzmmdmList" property="zzmm" />
							</html:select>
						</td>
						<td align="right">
							<font color="red">*</font>ѧ�ƣ�
						</td>
						<td align="left">
							<html:text name="rs" property="xzdm" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height: 22px">

						<td align="right">
							��Դ�������룺
						</td>
						<td align="left">
							<html:select name="rs" property="sydqdm" onchange="loadShi()" styleId="jgshen">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							<html:select name="rs" property="jgshi" styleId="jgshi"
								onchange="loadXian()">
								<html:options collection="shiList" property="shidm"
									labelProperty="shimc" />
							</html:select>
						</td>
					</tr>
					<thead>
						<tr style="height: 22px">
							<td colspan="4" align="center">
								<b>��дѧ����ҵ��Ϣ</b>
							</td>
						</tr>
					</thead>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> ������ʽ��
						</td>
						<td align="left">
							<html:text name="rs" property="pyfsdm" readonly="true"></html:text>
						</td>
						<td width="20%" align="right">
							ί�൥λ��
						</td>
						<td align="left">
							<html:text name="rs" property="wpdw"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							�������᣺
						</td>
						<td align="left">
							<html:text name="rs" property="zsmc"></html:text>
						</td>
						<td width="20%" align="right">
							�Զ�����
						</td>
						<td align="left">
							<html:text name="rs" property="zdcl"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							��ҵȥ��
						</td>
						<td align="left">
							<html:select name="rs" property="byqxdm" styleId="byqxdm"
								style="width:210px" >
								<html:option value=""></html:option>
								<html:options collection="byqxdmList" property="byqxdm"
									labelProperty="byqx" />
							</html:select>
						</td>
						<td width="20%" align="right">
							��ҵ״����
						</td>
						<td align="left">
							<html:select name="rs" property="jyzk" styleId="wjybz"
								style="width:210px">
								<html:option value=""></html:option>
								<html:option value="0">�Ѿ�ҵ</html:option>
								<html:option value="1">δ��ҵ</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							δ��ҵ��־��
						</td>
						<td align="left">
							<html:text name="rs" property="wjybz"></html:text>
						</td>
						<td width="20%" align="right">
							Ϊ��ҵԭ��
						</td>
						<td align="left">
							<html:text name="rs" property="wjyyy"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							����֤��ע��
						</td>
						<td align="left">
							<html:text name="rs" property="bdzbz"></html:text>
						</td>
						<td width="20%" align="right">
							ʵ�ʵ�λ��
						</td>
						<td align="left">
							<html:text name="rs" property="sjdw"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							 ��ǲ��λID��
						</td>
						<td align="left">
							<html:text name="rs" property="pqdwid"></html:text>
						</td>
						<td width="20%" align="right">
							��ǲ��λ��
						</td>
						<td align="left">
							<html:text name="rs" property="pqdw"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							��λ���ʣ�
						</td>
						<td align="left">
							<html:text name="rs" property="dwxz1"></html:text>&nbsp<html:text  property="dwxz2"></html:text>
							
						</td>
						<td width="20%" align="right">
							����ѡ��
						</td>
						<td align="left">
							<html:select name="rs" property="dwxzdm2" styleId="dwxzdm2"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="dwxzdm2List" property="dwxzdm"
									labelProperty="dwxz" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							���ܲ��ţ�
						</td>
						<td align="left">
							<html:text name="rs" property="zgbm1"></html:text>
							<html:text name="rs" property="zgbm2"></html:text>
						</td>
						<td width="20%" align="right">
							����ѡ��
						</td>
						<td align="left">
								<html:select name="rs" property="zgbmmc" styleId="sydzgbm"
									style="width:275px">
									<html:option value=""></html:option>
									<html:options collection="sydzgbmList" property="sydzgbm"
										labelProperty="sydzgbm" />
								</html:select>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							��λ���ڵأ�
						</td>
						<td align="left">
							<html:select name="rs" property="dwszd1" onchange="loadShi2()"
								styleId="jgshen2">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							<html:select  property="jgshi2" styleId="jgshi2"
								onchange="loadXian2()">
								<html:options collection="shiList" property="shimc"/>
							</html:select>
						</td>
						<td width="20%" align="right">
							���ڵأ�
						</td>
						<td align="left">
							<html:text name="rs" property="dwszd2"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							ʵ�����ڵأ�
						</td>
						<td align="left">
							<html:select name="rs"  property="dwszd3" onchange="loadShi3()"
								styleId="jgshen3">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							<html:select  property="jgshi3" styleId="jgshi3"
								onchange="loadXian3()">
								<html:options collection="shiList" property="shimc"/>
							</html:select>
						</td>
						<td width="20%" align="right">
							���ڵأ�
						</td>
						<td align="left">
							<html:text name="rs" property="dwdq"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							������ַ��
						</td>
						<td align="left">
							<html:text name="rs" property="dajsd"></html:text>
						</td>
						<td width="20%" align="right">
							�����ʱࣺ
						</td>
						<td align="left">
							<html:text name="rs" property="dajsdyb"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							������ַ��
						</td>
						<td align="left" colspan="10">
							<html:text name="rs" property="hkdz"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							�������ڣ�
						</td>
						<td align="left">
							<html:text name="rs" property="gprq"></html:text>
						</td>
						<td width="20%" align="right">
							���ɴ�����
						</td>
						<td align="left">
							<html:text name="rs" property="gpcs"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							����ԭ��
						</td>
						<td align="left">
							<html:text name="rs" property="gpyy"></html:text>
						</td>
						<td width="20%" align="right">
							ԭ��λ���ƣ�
						</td>
						<td align="left">
							<html:text name="rs" property="ydwmc"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							����֤��ţ�
						</td>
						<td align="left">
							<html:text name="rs" property="bdzbh"></html:text>
						</td>
						</tr>
					<tr>
						<td width="20%" align="right">
							��ע��
						</td>
						<td align="left">
							<html:textarea name="rs" property="memo"></html:textarea>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="savejyxy()">
						�� �� Э ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="jyxyreinput()">
						ȡ �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="expAppTab('rsT', '��ҵЭ��', '')";
>
						�� ӡ Э ��
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
	alert("�ύ�ɹ���");
</script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
	alert("�ύʧ�ܣ������Ƿ��ظ��ύ��");
</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
