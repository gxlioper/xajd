<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		
		<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	function jygljyxyupdate(){

	    var whichArea = document.getElementById("whichArea").value;
	    var fdysh = "";
	    if($("fdysh")){
	   		fdysh = document.getElementById("fdysh").value;
	    }
	    var xxsh = document.getElementById("xxsh").value;
	    var xsxh = document.getElementById("xsxh").value;
	var name = document.getElementById("name").value;

	if(xsxh==""){
	alert("ѧ�Ų���Ϊ�գ�");
	return false;
	}
	if(name==""){
	alert("��������Ϊ�գ�");
	return false;
	}
	var sydq = document.getElementById("sydq").value;
	var zgbm = document.getElementById("zgbm").value;
	
	
	var xxdjh = document.getElementById("xxdjh").value;
	var zzjgdm = document.getElementById("zzjgdm").value;
    var wjybz = document.getElementById("wjybz").value;
    var zzmmdm = document.getElementById("zzmmdm").value;
    var lxdz = document.getElementById("lxdz").value;
    var yb = document.getElementById("yb").value;
    var dh = document.getElementById("dh").value;
    var dwxzdm = document.getElementById("dwxzdm").value;
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
    var zgbmdm = document.getElementById("zgbmdm").value;
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
	
	if(byqxdm=="01"&&wjybz=="1"||byqxdm=="02"&&wjybz=="1"||byqxdm=="03"&&wjybz=="1"||
	   byqxdm=="04"&&wjybz=="1"||byqxdm=="12"&&wjybz=="1"||byqxdm=="13"&&wjybz=="1"||
	   byqxdm=="14"&&wjybz=="1"||byqxdm=="15"&&wjybz=="1"||byqxdm=="17"&&wjybz=="1"){
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
	
	if(sydqdm!="310000"&&sydzgbm==""){
	alert("��Դ�����ܲ��Ų���Ϊ�գ�")
	return false;
	}
	if(sydqdm!="310000"&&byqxdm=="04"&&dwmc!=sydzgbm){
	alert("��ѡ���������λ����Ӧ��ͬ����Դ�������ܲ��ţ�")
	return false;
	}
	if(sydqdm=="310000"&&sydzgbm!=""){
	alert("�Ϻ���Դ������д��Դ�����ܲ��ţ�");
	return false;
	}
	if(dwxzdm=="��ѡ��"){
	alert("��ѡ��λ���ʴ��룡")
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
	
	if((byqxdm=="01"||byqxdm=="13"||byqxdm=="15")&&
	   dwxzdm!="10"&&dwxzdm!="15"&&dwxzdm!="20"&&dwxzdm!="21"&&dwxzdm!="22"&&
	   dwxzdm!="23"&&dwxzdm!="25"&&dwxzdm!="29"&&dwxzdm!="31"&&dwxzdm!="32"&&
	   dwxzdm!="33"&&dwxzdm!="35"&&dwxzdm!="39"&&dwxzdm!="40"&&dwxzdm!="55"&&dwxzdm!="56"){
	   
	   alert("��ҵȥ������뵥λ���ʴ��뷢����ͻ�����飡");
	   return false;
	}
	if((byqxdm=="02"||byqxdm=="03"||byqxdm=="12")&&dwxzdm!="80"){
	   alert("���Ա�ҵȥ�����Ϊ׼����λ���ʴ���Ӧѡ����ѧ��");
	   return false;
	}
	if(byqxdm=="04"&&dwxzdm!="85"){
	   alert("���Ա�ҵȥ�����Ϊ׼����λ���ʴ���Ӧѡ�������������");
	   return false;
	}
	if(byqxdm=="14"&&dwxzdm!="75"&&dwxzdm!="76"&&dwxzdm!="77"){
	   alert("���Ա�ҵȥ�����Ϊ׼����λ���ʴ���Ӧѡ��������ҵ������ְҵ����������ҵ��");
	   return false;
	}
	if(byqxdm=="17"&&dwxzdm!="50"&&dwxzdm!="51"){
	   alert("���Ա�ҵȥ�����Ϊ׼����λ���ʴ���Ӧѡ����һ�����Ŀ��ط�������Ŀ��");
	   return false;
	}
	if(byqxdm=="06"&&dwxzdm!="70"){
	   alert("���Ա�ҵȥ�����Ϊ׼����λ���ʴ���Ӧѡ�����ҵ��");
	   return false;
	}
	if(byqxdm=="07"&&dwxzdm!="71"&&dwxzdm!="72"){
	   alert("���Ա�ҵȥ�����Ϊ׼����λ���ʴ���Ӧѡ�񲻾�ҵ����ѧ�������ݲ���ҵ��");
	   return false;
	}
	if((byqxdm=="16"||byqxdm=="")&&dwxzdm!="70"&&dwxzdm!="71"&&dwxzdm!="72"){
	   alert("���Ա�ҵȥ�����Ϊ׼����λ���ʴ���Ӧѡ�����ҵ,����ҵ����ѧ�������ݲ���ҵ��");
	   return false;
	}
	if((byqxdm=="05"||byqxdm=="08"||byqxdm=="11")&&dwxzdm!=""){
	   alert("���Ա�ҵȥ�����Ϊ׼����λ���ʴ���Ӧѡ��գ�");
	   return false;
	}
	
	
	if((byqxdm=="02"||byqxdm=="03"||byqxdm=="12")&&zgbmdm!="010"){
	   alert("���Ա�ҵȥ�����Ϊ׼�����ܲ�������Ӧѡ����ѧ��");
	   return false;
	}
	
	if((byqxdm=="04"||byqxdm=="05"||byqxdm=="08"||byqxdm=="11")&&zgbmdm!="011"){
	   alert("���Ա�ҵȥ�����Ϊ׼�����ܲ�������Ӧѡ���������ѧ��������");
	   return false;
	}
	if((byqxdm=="01"||byqxdm=="13"||byqxdm=="14"||byqxdm=="15"||byqxdm=="17")&&(zgbmdm=="010"||zgbmdm=="011"||zgbmdm=="")){
	   alert("��ҵȥ����������ܲ������Ʒ�����ͻ�����飡");
	   return false;
	}
	if((byqxdm=="06"||byqxdm=="07"||byqxdm=="16"||byqxdm=="")&&zgbmdm!=""){
	   alert("���Ա�ҵȥ�����Ϊ׼�����ܲ�������ӦΪ��ֵ��");
	   return false;
	}
	if(byqxdm=="16"&&dwmc!=""){
	   alert("��ʡ����ѧ��������д��λ���ƣ�");
	   return false;
	}
	if(byqxdm=="16"&&sydzgbm==""){
	   alert("��ѡ����Դ�����ܲ��ţ�");
	   return false;
	}
	
	            
	    if(fdysh=="��ͨ����"&&xxsh=="δ���"||xxsh=="��ͨ����"){
	    alert("�����ͨ����������Ȩ�޸ģ�");
	    return false;
	    }        
	            
		 		document.forms[0].action = "/xgxt/savejyxyupdate.do?doType=update&act=update&whichArea="+whichArea;
		 		document.forms[0].submit();
		 		var sumbtn = document.getElementsByTagName("button");
		 		for(var i = 0;i<sumbtn.length;i++){
		 			sumbtn[i].disabled = true;
		 		}
    }
    
 
    function refreshtheweb(){
    var dwdq = document.getElementById("dwdq").value;
    var zgbm = document.getElementById("zgbm").value; 
    var url="/xgxt/refreshjyxyupdate.do?doType2=refresh&act=first&dwdq=";
    url+=dwdq+"&zgbm="+zgbm;
    
		 	document.forms[0].action =url; 
		 	document.forms[0].submit();
    }
    
    function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    }
	</script>
	</head>
	
	<body>
		<html:hidden name="rs" property="whichArea" />
		<html:hidden name="rs" property="nd" />
		<html:form action="/data_search" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ��ҵЭ�鷽�� - ��ҵЭ���޸�</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b> ��һ����</b>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="4">
								ѧ�����
								<html:text property="xslb" name="rs" readonly="true"
									style="width:45px" />
								��ҵ���
								<html:text property="bynd" name="rs" readonly="true"
									style="width:35px" />
								<bean:message key="lable.xsgzyxpzxy" />
								<html:text property="xymc" name="rs" readonly="true"
									style="width:90px" />
								<button
									onclick="showTopWin('/xgxt/turndmkqueryjsp.do',427,350);"
									class="button2">
									������ѯ��
								</button>
								�ύʱ��
						        <html:text name="rs" property="tjsj" readonly="true" style="width:140px" />
							</td>
						</tr>
					</thead>
					<tbody>
					<tr style="height:22px">
						<th align="right" width="14%">
							<font color="red">*</font>ѧ��
						</th>
						<td align="left" width="36%">
							<html:text property="xsxh" name="rs" styleId="xsxh"
								readonly="true" style="width:210px" />
						</td>
						<th align="right" width="15%">
						</th>
						<td align="left" width="35%">
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							Э������
						</th>
						<td align="left">
							<html:text name="rs" property="xysbh" style="width:210px" maxlength="60"/>
						<th align="right" style="width=20%">
							����
						</th>
						<td align="left">
							<html:text name="rs" property="name" readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							�Ա����
						</th>
						<td align="left">
							<html:text name="rs" property="xbdm" readonly="true"
								style="width:210px" />
						</td>
						<th align="right">
							���֤��
						</th>
						<td align="left">
							<html:text name="rs" property="id" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							ѧУ����
						</th>
						<td align="left">
							<html:text name="rs" property="xxdm" readonly="true"
								style="width:210px" />
						</td>
						<th align="right">
							ѧУ����
						</th>
						<td align="left">
							<html:text name="rs" property="xxmc" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							רҵ����
						</th>
						<td align="left">
							<html:text name="rs" property="zydm" readonly="true"
								style="width:210px" />
						</td>
						<th align="right">
							רҵ����
						</th>
						<td align="left">
							<html:text name="rs" property="zymc" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							ѧ�ƴ���
						</th>
						<td align="left">
							<html:text name="rs" property="xzdm" readonly="true"
								style="width:210px" />
						</td>
						<th align="right">
							ѧ������
						</th>
						<td align="left">
							<html:text name="rs" property="xldm" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							������ʽ����
						</th>
						<td align="left">
							<html:text name="rs" property="pyfsdm" readonly="true"
								style="width:210px" />
						</td>
						<th align="right">
							��Դ��������
						</th>
						<td align="left">
							<html:text name="rs" property="sydqdm" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>�ڶ�����</b>
							</td>
						</tr>
					</thead>

					<tr style="height:22px">
						<th align="right">
							��ҵȥ�����
						</th>
						<td align="left">
							<html:select name="rs" property="byqxdm" styleId="byqxdm"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="byqxdmList" property="byqxdm"
									labelProperty="byqx" />
							</html:select>
						</td>
						<th align="right">
							��Դ��������
						</th>
						<td align="left">
							<html:text name="rs" property="sydq" style="width:210px"
								readonly="true" />
						</td>
					</tr>

					<tr style="height:22px">
						<th align="right">
							��λ��������
						</th>
						<td align="left">
							<html:select name="rs" property="dwdq" styleId="dwdq"
								style="width:210px" onchange="refreshtheweb()">
								<html:option value=""></html:option>
								<html:options collection="dwdqList" property="dwdq"
									labelProperty="dwdq" />
							</html:select>
						</td>
						<th align="right">
							���ܲ�������
						</th>
						<td align="left">
							<html:select name="rs" property="zgbm" styleId="zgbm"
								style="width:210px" onchange="refreshtheweb()">
								<html:option value=""></html:option>
								<html:options collection="zgbmList" property="zgbm"
									labelProperty="zgbm" />
							</html:select>
						</td>

					</tr>
					<tr style="height:22px">
						<th align="right">
							��λ��������
						</th>
						<td align="left">
							<html:text name="rs" property="dwdqdm" readonly="true"
								style="width:210px" />
						</td>
						<th align="right">
							���ܲ��Ŵ���
						</th>
						<td align="left">
							<html:text name="rs" property="zgbmdm" readonly="true"
								style="width:210px" />
						</td>

					</tr>
					<tr style="height:22px">
						<th align="right">
							��Ϣ�ǼǺ�
						</th>
						<td align="left">
							<html:text name="rs" property="xxdjh" style="width:210px" />

						</td>
						<th align="right">
							��֯��������
						</th>
						<td align="left">
							<html:text name="rs" property="zzjgdm" style="width:210px" />

						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							��λ����
						</th>
						<td align="left">
							<html:text name="rs" property="dwmc" style="width:210px" />

						</td>
						<th align="right">
							������ò����
						</th>
						<td align="left">
							<html:select name="rs" property="zzmmdm" styleId="zzmmdm"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="zzmmdmList" property="zzmmdm"
									labelProperty="zzmm" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							δ��ҵ��־
						</th>
						<td align="left">
							<html:select name="rs" property="wjybz" styleId="wjybz"
								style="width:210px">
								<html:option value=""></html:option>
								<html:option value="0">�Ѿ�ҵ</html:option>
								<html:option value="1">δ��ҵ</html:option>
							</html:select>
							
						</td>
						<th align="right">
							�Զ���1
						</th>
						<td align="left">
							<html:text name="rs" property="bz1" style="width:210px" />

						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							δ��ҵԭ��
						</th>
						<td align="left">
							<html:text name="rs" property="wjyyy" style="width:210px" />

						</td>
						<th align="right">
							�Զ���2
						</th>
						<td align="left">
							<html:text name="rs" property="bz2" style="width:210px" />

						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							��ϵ��ַ
						</th>
						<td align="left">
							<html:text name="rs" property="lxdz" style="width:210px" />
							
						</td>
						<th align="right">
							�Զ���3
						</th>
						<td align="left">
							<html:text name="rs" property="bz3" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							�ʱ�
						</th>
						<td align="left">
							<html:text name="rs" property="yb" style="width:210px" />
							
						</td>
						<th align="right">
							�Զ���4
						</th>
						<td align="left">
							<html:text name="rs" property="bz4" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							�绰
						</th>
						<td align="left">
							<html:text name="rs" property="dh" style="width:210px" />
							
						</td>
						<th align="right">
							�Զ���5
						</th>
						<td align="left">
							<html:text name="rs" property="bz5" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							
						</th>
						<td align="left">
						
						</td>
						<th align="right">
							��ס֤�������־λ
						</th>
						<td align="left">
							<html:select name="rs" property="jzzhlbbzwdm"
								styleId="jzzhlbbzwdm" style="width=100%">
								<html:option value=""></html:option>
								<html:options collection="jzzhlbbzwdmList"
									property="jzzhlbbzwdm" labelProperty="jzzhlbbzw" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							��Դ�����ܲ���
						</th>
						<td align="left">
							<html:select name="rs" property="sydzgbm" styleId="sydzgbm"
								style="width=100%">
								<html:option value=""></html:option>
								<html:options collection="sydzgbmList" property="sydzgbm"
									labelProperty="sydzgbm" />
							</html:select>
						</td>
						<th align="right">
							��λ���ʴ���
						</th>
						<td align="left">
							<html:select name="rs" property="dwxzdm" styleId="dwxzdm"
								style="width=100%">
								<html:option value=""></html:option>
								<html:options collection="dwxzdmList" property="dwxzdm"
									labelProperty="dwxz" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							������׼�ĺ�
						</th>
						<td align="left">
							<html:text name="rs" property="blueno" style="width:210px" />

						</td>
						<th align="right">
							��ע
						</th>
						<td align="left">
							<html:text name="rs" property="memo" style="width:210px" />

						</td>
					</tr>
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>��������</b>
							</td>
						</tr>
					</thead>

					<tr style="height:22px">
						<th align="right">
							��λ���ʴ���2
						</th>
						<td align="left">
							<html:select name="rs" property="dwxzdm2" styleId="dwxzdm2"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="dwxzdm2List" property="dwxzdm"
									labelProperty="dwxz" />
							</html:select>
						</td>
						<th align="right">
							<logic:equal value="10856" name="xxdm">
								�������յ�λ����
							</logic:equal>
							<logic:notEqual value="10856" name="xxdm">
								�������յ�
							</logic:notEqual>
						</th>
						<td align="left">
							<html:text name="rs" property="dajsd" style="width:210px" />
							
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							��λ��ַ
						</th>
						<td align="left">
							<html:text name="rs" property="dwdz" style="width:210px" />

						</td>
						<th align="right">
							�������յص�ַ
						</th>
						<td align="left">
							<html:text name="rs" property="dajsddz" style="width:210px" />
							
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							��λ��ϵ��
						</th>
						<td align="left">
							<html:text name="rs" property="dwlxr" style="width: 210px;" maxlength="60"></html:text>
						</td>
						<th align="right">
							�������յ�λ����
						</th>
						<td align="left">
							<html:text name="rs" property="dajsddwmc" style="width: 210px;" maxlength="60"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							��λ�绰
						</th>
						<td align="left">
							<html:text name="rs" property="dwdh" style="width:210px" />
						</td>
						<th align="right">
							�������յ��ʱ�
						</th>
						<td align="left">
							<html:text name="rs" property="dajsdyb" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							��λ�ʱ�
						</th>
						<td align="left">
							<html:text name="rs" property="dwyb" style="width:210px" />
						</td>
						<th align="right">
							<logic:equal value="10856" name="xxdm">
								��λ���ڵ���
							</logic:equal>
							<logic:notEqual value="10856" name="xxdm">
								��������
							</logic:notEqual>
						</th>
						<td align="left">
							<html:select name="rs" property="dqlx" styleId="dqlx"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="dqlxdmList" property="dqlx"
									labelProperty="dqlx" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							ΥԼ��
						</th>
						<td align="left">
							<html:text name="rs" property="wyj" style="width:210px" />
						</td>
						<th align="right">
							��ҵ����
						</th>
						<td align="left">
							<html:select name="rs" property="hyfl" styleId="hyfl"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="hyflList" property="hyfl"
									labelProperty="hyfl" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							��һ����ƽ������
						</th>
						<td align="left">
							<html:text name="rs" property="dynypjgz" style="width:210px" />
						</td>
						<th align="right">
							רҵ�Կ�
						</th>
						<td align="left">
							<html:select name="rs" property="zydk" styleId="zydk"
								style="width:210px">
								<html:option value=""></html:option>
								<html:option value="1">��</html:option>
								<html:option value="2">��</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							��λ��ģ
						</th>
						<td align="left">
							<html:select name="rs" property="dwgm" style="width: 210px;">
								<html:option value=""></html:option>
								<html:option value="10������">10������</html:option>
								<html:option value="10-49��">10-49��</html:option>
								<html:option value="50-99��">50-99��</html:option>
								<html:option value="100-499��">100-499��</html:option>
								<html:option value="500����">500����</html:option>
							</html:select>
						</td>
						<th align="right">
							��λע���ʽ�
						</th>
						<td align="left">
							<html:text name="rs" property="dwzczj" style="width: 210px;" maxlength="60"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							ѧУ��˽��
						</th>
						<td align="left">
							<html:text name="rs" property="xxsh" style="width:210px"
								readonly="true" />
						</td>
						<th align="right">
							ѧУ�����
						</th>
						<td align="left">
							<html:text name="rs" property="xxshren" style="width:210px"
								readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						
						<th align="right">
							ѧУ���ʱ��
						</th>
						<td align="left">
							<html:text name="rs" property="xxshsj" style="width:210px"
								readonly="true" />
						</td>
						<th align="right">
							������ʼʱ��
						</th>
						<td align="left">
								<html:text name="rs" property="bdkssj" maxlength="60" style="width: 210px;" onclick="return showCalendar('bdkssj','y-mm-dd');" ></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						
						<th align="right">
							��������ʱ��
						</th>
						<td align="left">
							<html:text name="rs" property="bdjssj" maxlength="60" style="width: 210px;" onclick="return showCalendar('bdjssj','y-mm-dd');"></html:text>
						</td>
						<th align="right">
							����֤��
						</th>
						<td align="left">
							<html:text name="rs" property="bdzh" style="width: 210px;" maxlength="60"></html:text>
						</td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button  onclick="jygljyxyupdate()" >
										�� ��
									</button>
									<logic:equal name="whichArea" value="shenhe">
										<button 
											onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">
											�� ��
										</button>
									</logic:equal>
									<logic:equal name="whichArea" value="result">
										<button " onclick="Close();refreshtheweb()">
											�� ��
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<logic:equal name="update" value="ok">
					<script>
				    	alert("�ύ�ɹ���");
				    	if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('query_go').click();
						}
				    </script>
				</logic:equal>
				<logic:equal name="update" value="no">
					<script>
					    alert("�ύʧ�ܣ�");
					    if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('query_go').click();
						}
					</script>
				</logic:equal>
			</fieldset>
		</html:form>
	</body>
</html>
