<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	var stylePath = "<%=stylePath%>";
	function savejyxy(){
	var xsxh = document.getElementById("xsxh").value;
	var name = document.getElementById("name").value;
	var xxdm = document.getElementById("xxdm").value;
	if(xsxh==""){
		alert("ѧ�Ų���Ϊ�գ�");
		return false;
	}
	if(name==""){
		alert("��������Ϊ�գ�");
		return false;
	}
	var byqxdm =document.getElementById("byqxdm").value;
	var dwdq = document.getElementById("dwdq").value;
	if(xxdm=="10333"){
		$("dwdz").value=$("dwshen").value+$("dwshi").value+$("dwxian").value;
		dwdz=$("dwdz").value;
		$("dwdq").value=$("shen").value+$("shi").value+$("xian").value;
		dwdq=$("dwdq").value;
	}
	
	var zgbm = document.getElementById("zgbm").value;
	
	var xxdjh = document.getElementById("xxdjh").value;
	var zzjgdm = document.getElementById("zzjgdm").value;
   
    var zzmmdm = document.getElementById("zzmmdm").value;
    var lxdz = document.getElementById("lxdz").value;
    var yb = document.getElementById("yb").value;
    var dh = document.getElementById("dh").value;
    var dwxzdm = document.getElementById("dwxzdm").value;
    var blueno = document.getElementById("blueno").value;
    
    var sydqdm = document.getElementById("sydqdm").value;
    var jzzhlbbzwdm = document.getElementById("jzzhlbbzwdm").value;
    var dwmc =document.getElementById("dwmc").value;
    var sydzgbm =document.getElementById("sydzgbm").value;
    var wjybz;
    var wjyyy;
    if(xxdm!='10856'){
    	wjybz = document.getElementById("wjybz").value;
    	wjyyy = document.getElementById("wjyyy").value;
    }
    
    var dajsd = document.getElementById("dajsd").value;
    var dajsddz = document.getElementById("dajsddz").value;
    var dajsdyb = document.getElementById("dajsdyb").value;   
    var dwdh = document.getElementById("dwdh").value;
    var dwyb = document.getElementById("dwyb").value;
    var wyj =document.getElementById("wyj").value;
    var dynypjgz =document.getElementById("dynypjgz").value;
    var dqlx = document.getElementById("dqlx").value;
    var hyfl = document.getElementById("hyfl").value;
    var zydk = document.getElementById("zydk").value;
    var dwxzdm2 =document.getElementById("dwxzdm2").value;
    var dwdz = document.getElementById("dwdz").value;
   	
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
	if(xxdm!='10856'){	
		if(byqxdm!="01"&&byqxdm!="02"&&byqxdm!="03"&&byqxdm!="04"&&byqxdm!="12"&&byqxdm!="13"
	   		&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"&&wjybz=="0"){
	   		alert("���ٴ�ȷ���Ƿ��ҵ����δ��ҵ��δ��ҵ��־������")
	   		return false;
		}
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
	
	if(dwmc !=''&&zzjgdm==""){
		alert("��֯�������벻��Ϊ�գ�");
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
	
	if(xxdm!='10856'){
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
		if(wjybz=="1"&&wjyyy==""){
			alert("������δ��ҵԭ��");
			return false;
		}
	}
		
	if(zzmmdm==""){
		alert("������ò���벻��Ϊ�գ�");
		return false;
	}
	
	if(lxdz==""){
		alert("��ͥ��ַ��ַ����Ϊ�գ�");
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
	if(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17"){
		if(dwxzdm2==""){
			alert("��λ���ʴ���2����Ϊ�գ�");
			return false;
		}
		if(dwdh==''){
			alert("��λ�绰����Ϊ�գ�");
			return false;
		}
		if(dwyb==''){
			alert("��λ�ʱ಻��Ϊ�գ�");
			return false;
		}
		if(dwdz==''){
			alert("��λ��ַ����Ϊ�գ�");
			return false;
		}
		if(dqlx==''){
			if(xxdm=='10856'){
				alert("��λ���ڵ�������Ϊ�գ�");
			}else{
				alert("����������Ϊ�գ�");
			}				
			return false;
		}
		if(hyfl==''){
			alert("��ҵ���಻��Ϊ�գ�");
			return false;
		}
		if(zydk==''){
			alert("רҵ�Կڲ���Ϊ�գ�");
			return false;
		}
		if(byqxdm=="01"){
			//2010.11.30 by lr �������յ�λ����Ϊ�Ǳ����ֶΣ���ҳ���ϣ�
			//if(dajsd==''){
			//	alert("�������յ�λ���Ʋ���Ϊ�գ�");
			//	return false;
			//}
			if(dajsddz==''){
				alert("�������յص�ַ����Ϊ�գ�");
				return false;
			}
			if(dajsdyb==''){
				alert("�������յ��ʱ಻��Ϊ�գ�");
				return false;
			}
			
			if(wyj==''){
				alert("ΥԼ����Ϊ�գ�");
				return false;
			}		
			if(dynypjgz==''){
				alert("��һ����ƽ�����ʲ���Ϊ�գ�");
				return false;
			}
			
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
	}else{
		if(dwxzdm2!=""){
			alert("��λ���ʴ���2������д��");
			return false;
		}
		if(dwdz!=""){
			alert("��λ��ַ������д��");
			return false;
		}
		if(dwdh!=""){
			alert("��λ�绰������д��");
			return false;
		}
		if(dwyb!=""){
			alert("��λ�ʱ಻����д��");
			return false;
		}
		if(wyj!=""){
			alert("ΥԼ������д��");
			return false;
		}
		if(dynypjgz!=""){
			alert("��һ����ƽ�����ʲ�����д��");
			return false;
		}
		if(dqlx!=""){
			alert("������������д��");
			return false;
		}
		if(hyfl!=""){
			alert("��ҵ���಻����д��");
			return false;
		}
		if(zydk!=""){
			alert("רҵ�Կڲ�����д��");
			return false;
		}
		if(sydqdm!="310000"&&((byqxdm=="04")||(byqxdm=="16"))){
			if(dajsd==""){
				alert("�������յ�λ���Ʋ���Ϊ�գ�");
				return false;
			}
			if(dajsddz==""){
				alert("�������յص�ַ����Ϊ�գ�");
				return false;
			}
			if(dajsdyb==""){
				alert("�������յ��ʱ಻��Ϊ�գ�");
				return false;
			}
		}else{
			if(dajsd!=""){
				alert("�������յ�λ���Ʋ�����д��");
				return false;
			}
			if(dajsddz!=""){
				alert("�������յص�ַ������д��");
				return false;
			}
			if(dajsdyb!=""){
				alert("�������յ��ʱ಻����д��");
				return false;
			}
		}	
	}	

		document.forms[0].action = "/xgxt/savejyxy.do?doType=save";
		document.forms[0].submit();
    }
    
   
    
    function refreshtheweb(){
    
    	//����ѧԺ
    	
    	
    	if($("xxdm").value=="10333"){
    			var byqxdm = document.getElementById("byqxdm").value;
   				var dwdq = document.getElementById("shen").value;
   				var xsxh = document.getElementById("xsxh").value;
    			var zgbm = document.getElementById("zgbm").value;
    			
    	}else{
    			var byqxdm = document.getElementById("byqxdm").value;
   				var dwdq = document.getElementById("dwdq").value;
   				var xsxh = document.getElementById("xsxh").value;
    			var zgbm = document.getElementById("zgbm").value;
        }      
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
	function getFormatValue(){
		   var getSelectValue = document.getElementById("syd").value;
		   document.getElementById("sydzgbm").value = getSelectValue;         
		}
		
	function loadShiXx(){
		var shen = document.getElementById("shen").value;	
		getStuDetails.shiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "shi";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shimc","shimc");
					}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}	
			
		});
	}
	
	function dwShiXx(){
		var shen = document.getElementById("dwshen").value;	
		getStuDetails.shiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "dwshi";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shimc","shimc");
					}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}	
			
		});
	}
		
	
	</script>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/data_search" method="post" enctype="multipart/form-data">
			<input type="hidden"  name="xxdm" id="xxdm" value="${xxdm}"/>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ��ҵЭ�鷽�� - ��ҵЭ��¼��</a>
				</p>
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
				<div class="tab">
					<table width="100%" id="rsT" border="0" class="formlist">
					<thead>
						<tr style="height:22px">
							<td colspan="6" align="center">
								<b>��һ����</b>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="4">
								ѧ�����
								<html:text property="xslb" name="rs" readonly="true"
									style="width:50px" />
								��ҵ���
								<html:text property="bynd" name="rs" readonly="true"
									style="width:40px" />
								<bean:message key="lable.xsgzyxpzxy" />
								<html:text property="xymc" name="rs" readonly="true"
									style="width:130px" />
								<font color="black">ע:</font>
								<font color="red">*</font>Ϊ�������� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								
								<button
									onclick="showTopWin('/xgxt/turndmkqueryjsp.do',427,350);"
									class="button2">
									������ѯ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<!-- <a href="/xgxt/jygl/jyxyhelp.jsp" target="_blank"><font color="red">��д����˵��</font></a> -->
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<th style="width=18%">
								<font color="red">*</font>ѧ��
							</th>
							<td style="width=32%" colspan="3">
								<html:text name='rs' property="xsxh" styleId="xsxh"
									style="width:210px" readonly="true" />
								<button onclick="showTopWin('/xgxt/jyxyTurnInfo.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<th style="width=18%">
								<font color="red">*</font>ѧ��
							</th>
							<td style="width=32%" colspan="3">
								<html:text property="xsxh" name="rs" styleId="xsxh"
									readonly="true" style="width:210px" />
							</td>
						</logic:equal>
					</tr>
					<tr style="height:22px">
						<th >
							Э������
						</th>
						<td>
							<html:text property="xysbh" style="width:210px" maxlength="60"/>
						</td>
						<th style="width=20%">
							����
						</th>
						<td>
							<html:text name="rs" property="name" readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							�Ա����
						</th>
						<td>
							<html:text name="rs" property="xbdm" readonly="true"
								style="width:210px" />
						</td>
						<th>
							���֤��
						</th>
						<td>
							<html:text name="rs" property="id" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							ѧУ����
						</th>
						<td>
							<html:text name="rs" property="xxdm" readonly="true"
								style="width:210px" />
						</td>
						<th>
							ѧУ����
						</th>
						<td>
							<html:text name="rs" property="xxmc" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							רҵ����
						</th>
						<td>
							<html:text name="rs" property="zydm" readonly="true"
								style="width:210px" />
						</td>
						<th>
							רҵ����
						</th>
						<td>
							<html:text name="rs" property="zymc" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							ѧ�ƴ���
						</th>
						<td>
							<html:text name="rs" property="xzdm" readonly="true"
								style="width:210px" />
						</td>
						<th align="right">
							ѧ������
						</th>
						<td>
							<html:text name="rs" property="xldm" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							������ʽ����
						</th>
						<td>
							<html:text name="rs" property="pyfsdm" readonly="true"
								style="width:210px" />
						</td>
						<th>
							��Դ��������
						</th>
						<td>
							<html:text name="rs" property="sydqdm" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<thead>
						<tr style="height:22px">
							<td colspan="6" align="center">
								<b>�ڶ�����</b>
							</td>
						</tr>
					</thead>

					<tr style="height:22px">
						<th>
							<font color="red">*</font>
							��ҵȥ�����
						</th>
						<td align="left">
							<html:select name="rs" property="byqxdm" styleId="byqxdm"
								style="width:210px" onchange="refreshtheweb()">
								<html:option value=""></html:option>
								<html:options collection="byqxdmList" property="byqxdm"
									labelProperty="byqx" />
							</html:select>
						</td>
						<th>
							��Դ��������
						</th>
						<td align="left">
							<html:text name="rs" property="sydq" style="width:210px"
								readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							��λ��������
						</th>
						<td>
							<!-- ������ -->
							<logic:equal name="xxdm" value="10333">
								<logic:equal name="danweiname" value="X">
							<html:select name="rs" property="sydqdm" onchange="loadShiXx()" styleId="shen"
							style="width:180px">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="ssList" property="ssmc"
									labelProperty="ssmc" />
							</html:select>
							<br/>
							<html:select name="rs" property="jgshi" styleId="shi"
								 style="width:180px">
								<html:options collection="shiList" property="shimc" 
									labelProperty="shimc" />
							</html:select>
							<br/>
							<html:select name="rs" property="jgxian" styleId="xian"
								style="width:180px">
								<html:options collection="xianList" property="xianmc" 
									labelProperty="xianmc" />
							</html:select>
							</logic:equal>
							<logic:equal name="danweiname" value="O">
								<html:text name="rs" property="shen" readonly="true" value=""
									style="width:210px" />
							</logic:equal>
							<logic:equal name="danweiname" value="Y">
								<html:text name="rs" property="shen" readonly="true" value="����" 
									style="width:210px" />
							</logic:equal>
							<html:hidden name="rs" property="dwdq" styleId="dwdq"/>
							</logic:equal>
							<!-- �ǳ����� -->
							<logic:notEqual name="xxdm" value="10333">
							<logic:equal name="danweiname" value="X">
								<html:select name="rs" property="dwdq" styleId="dwdq"
									style="width:210px" onchange="refreshtheweb()">
									<html:option value="�Ϻ�����Ͻ��">�Ϻ�����Ͻ��</html:option>
									<html:options collection="dwdqList" property="dwdq"
										labelProperty="dwdq" />
								</html:select>
								<br/>
								<font color="blue">(Ĭ��Ϊ�Ϻ���������ѡ��)</font>
							</logic:equal>
							<logic:equal name="danweiname" value="O">
								<html:text name="rs" property="dwdq" readonly="true" value=""
									style="width:210px" />
							</logic:equal>
							<logic:equal name="danweiname" value="Y">
								<html:text name="rs" property="dwdq" readonly="true" value="����"
									style="width:210px" />
							</logic:equal>
							</logic:notEqual>
							
						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="zgbm">
								<font color="red">*</font>
							</logic:equal>
							(��λ����)���ܲ�������
						</th>
						<td>
							<logic:equal name="whichzgbmlist" value="X">
								<html:text name="rs" property="zgbm" style="width:210px"
									readonly="true" />
							</logic:equal>

							<logic:equal name="whichzgbmlist" value="J">
								<html:text name="rs" property="zgbm" value="��ѧ"
									style="width:210px" readonly="true" />
							</logic:equal>
							<logic:equal name="whichzgbmlist" value="K">
								<html:text name="rs" property="zgbm" value="��������ѧ���ӳ�"
									style="width:210px" readonly="true" />
							</logic:equal>
							<logic:equal name="whichzgbmlist" value="L">
								<html:select name="rs" property="zgbm" styleId="zgbm"
									style="width:210px" onchange="refreshtheweb()">
									<html:option value="��ѡ��">----------��ѡ��----------</html:option>
									<html:options collection="ListL" property="zgbm"
										labelProperty="zgbm" />
								</html:select>
							</logic:equal>
							<logic:equal name="whichzgbmlist" value="M">
								<html:text name="rs" property="zgbm" style="width:210px"
									value="" readonly="true" />
							</logic:equal>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							��λ��������
						</th>
						<td>
							<logic:equal name="danweiname" value="X">
								<html:text name="rs" property="dwdqdm" readonly="true"
									style="width:210px" />
							</logic:equal>
							<logic:equal name="danweiname" value="Y">
								<html:text name="rs" property="dwdqdm" readonly="true"
									style="width:210px" />
							</logic:equal>
							<logic:equal name="danweiname" value="O">
								<html:text name="rs" property="dwdqdm" readonly="true" value=""
									style="width:210px" />
							</logic:equal>
						</td>


						<th>
							���ܲ��Ŵ���
						</th>
						<td>
							<logic:equal name="whichzgbmlist" value="X">
								<html:text name="rs" property="zgbmdm" style="width:210px"
									readonly="true" />
							</logic:equal>
							<logic:equal name="whichzgbmlist" value="J">
								<html:text name="rs" property="zgbmdm" value="010"
									style="width:210px" readonly="true" />
							</logic:equal>
							<logic:equal name="whichzgbmlist" value="K">
								<html:text name="rs" property="zgbmdm" value="011"
									style="width:210px" readonly="true" />
							</logic:equal>
							<logic:equal name="whichzgbmlist" value="L">
								<html:text name="rs" property="zgbmdm" style="width:210px"
									readonly="true" />
							</logic:equal>
							<logic:equal name="whichzgbmlist" value="M">
								<html:text name="rs" property="zgbmdm" value=""
									style="width:210px" readonly="true" />
							</logic:equal>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							<logic:equal value="yes" name="redflag" property="xxdjh">
								<font color="red">*</font>
							</logic:equal>
							��Ϣ�ǼǺ�
						</th>
						<td align="left">
							<html:text name="rs" property="xxdjh" style="width:210px"
								maxlength="7" />
						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="zzjgdm">
								<font color="red">*</font>
							</logic:equal>
							��֯��������
						</th>
						<td align="left">
							<logic:equal name="danweiname" value="O">
								<html:text name="rs" property="zzjgdm" style="width:210px"
								maxlength="9" readonly="true"/>
							</logic:equal>
							<logic:notEqual name="danweiname" value="O">
								<html:text name="rs" property="zzjgdm" style="width:210px"
								maxlength="9" />
							</logic:notEqual>
							

						</td>
					</tr>
					<tr style="height:22px">
						<th>
							<logic:equal value="yes" name="redflag" property="dwmc">
								<font color="red">*</font>
							</logic:equal>
							��λ����
						</th>
						<td align="left">
							<logic:equal name="danweiname" value="X">
								<html:text name="rs" property="dwmc" style="width:210px"
									maxlength="25" />
							</logic:equal>
							<logic:equal name="danweiname" value="O">
								<html:text name="rs" property="dwmc" style="width:210px"
									value="" readonly="true" />
							</logic:equal>
							<logic:equal name="danweiname" value="Y">
								<html:text name="rs" property="dwmc" style="width:210px"
									maxlength="25" />
							</logic:equal>

						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="zzmmdm">
								<font color="red">*</font>
							</logic:equal>
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

					<logic:notEqual value="10856" name="xxdm">
					<tr style="height:22px">
						<th>
							<font color="red">*</font>δ��ҵ��־��
						</th>
						<td align="left">
							<html:select name="rs" property="wjybz" styleId="wjybz"
								style="width:210px">
								<html:option value=""></html:option>
								<html:option value="0">�Ѿ�ҵ</html:option>
								<html:option value="1">δ��ҵ</html:option>
							</html:select>
						</td>
						<th>
							δ��ҵԭ��
						</th>
						<td align="left">
							<html:text name="rs" property="wjyyy" style="width:210px"
								maxlength="64" />

						</td>					
					</tr>
					</logic:notEqual>
					<tr style="height:22px">
						<th>
							<logic:equal value="yes" name="redflag" property="lxdz">
								<font color="red">*</font>
							</logic:equal>
							��ͥ��ַ
						</th>
						<td align="left">
							<html:text name="rs" property="lxdz" style="width:210px"
								maxlength="64" />

						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="yb">
								<font color="red">*</font>
							</logic:equal>
							�ʱ�
						</th>
						<td align="left">
							<html:text name="rs" property="yb" style="width:210px"
								maxlength="6" />

						</td>
						
					</tr>
					
					<tr style="height:22px">
						<th>
							<logic:equal value="yes" name="redflag" property="dh">
								<font color="red">*</font>
							</logic:equal>
							���˵绰
						</th>
						<td align="left">
							<html:text name="rs" property="dh" style="width:210px"
								maxlength="12" />

						</td>
						<th>
							��ע
						</th>
						<td align="left">
							<html:text name="rs" property="memo" style="width:210px"
								readonly="true" />

						</td>
					</tr>
					<tr style="height:22px">
						<th>
							<logic:equal value="yes" name="redflag" property="jzzhlbbzwdm">
								<font color="red">*</font>
							</logic:equal>
							��ס֤�������־λ
						</th>
						<td align="left">
							<html:select name="rs" property="jzzhlbbzwdm"
								styleId="jzzhlbbzwdm" style="width:260px">
								<html:option value=""></html:option>
								<html:options collection="jzzhlbbzwdmList"
									property="jzzhlbbzwdm" labelProperty="jzzhlbbzw" />
							</html:select>

						</td>
						<th>
							������׼�ĺ�
						</th>
						<td align="left">
							<html:text name="rs" property="blueno" style="width:210px"
								maxlength="5" />

						</td>
					</tr>
					<tr style="height:22px">
						<th>
							<logic:notEqual value="�Ϻ���" name="rs" property="sydq">
								<logic:equal value="yes" name="redflag" property="sydzgbm">
									<font color="red">*</font>
								</logic:equal>
							</logic:notEqual>
							��Դ�����ܲ���
						</th>
<%--						<td align="left">--%>
<%--							<logic:equal name="sydqdmIsSh" value="notkong">--%>
<%--								<html:select name="rs" property="sydzgbm" styleId="sydzgbm"--%>
<%--									style="width:260px">--%>
<%--									<html:option value=""></html:option>--%>
<%--									<html:options collection="sydzgbmList" property="sydzgbm"--%>
<%--										labelProperty="sydzgbm" />--%>
<%--								</html:select>--%>
<%--							</logic:equal>--%>
<%--							<logic:equal name="sydqdmIsSh" value="kong">--%>
<%--								<html:text name="rs" property="sydzgbm" value=""--%>
<%--									style="width:210px" readonly="true" />--%>
<%--							</logic:equal>--%>
<%--						</td>--%>
							<td align="left">
							<html:hidden name="rs" property="sydzgbm" styleId="sydzgbm"
								style="width:200px;height:21px;font-size:10pt;" />
								<html:select name="rs" property="syd" styleId="syd" style="width:210px" onchange="getFormatValue()">
									<html:options collection="sydzgbmList" property="sydzgbm"
										 labelProperty="sydzgbm" />
								</html:select>
<%--						<html:select name="rs" property="sydzgbm" styleId="sydzgbm"--%>
<%--									style="width:260px">--%>
<%--									<html:option value=""></html:option>--%>
<%--									<html:options collection="sydzgbmList" property="sydzgbm"--%>
<%--										labelProperty="sydzgbm" />--%>
<%--								</html:select>--%>
							<logic:equal name="sydqdmIsSh" value="notkong1">
								<html:select name="rs" property="sydzgbm" styleId="sydzgbm"
									style="width:260px">
									<html:option value=""></html:option>
									<html:options collection="sydzgbmList" property="sydzgbm"
										labelProperty="sydzgbm" />
								</html:select>
							</logic:equal>
							<logic:equal name="sydqdmIsSh" value="kong1">
								<html:text name="rs" property="sydzgbm" value=""
									style="width:210px" readonly="true" />
							</logic:equal>
						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="dwxzdm">
								<font color="red">*</font>
							</logic:equal>
							��λ���ʴ���
						</th>
						<td>
							<logic:equal name="whichlist" value="X">
								<html:select name="rs" property="dwxzdm" styleId="dwxzdm"
									style="width:210px">
									<html:option value="��ѡ��">----------��ѡ��----------</html:option>
									<html:options collection="dwxzdmList" property="dwxzdm"
										labelProperty="dwxz" />
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="A">
								<html:select name="rs" property="dwxzdm" styleId="dwxzdm"
									style="width:210px">
									<html:option value="��ѡ��">----------��ѡ��----------</html:option>
									<html:options collection="ListA" property="dwxzdm"
										labelProperty="dwxz" />
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="B">
								<html:select name="rs" property="dwxzdm" style="width:210px">
									<html:option value="80">��ѧ</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="C">
								<html:select name="rs" property="dwxzdm" style="width:210px">
									<html:option value="85">����������</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="D">
								<html:select name="rs" property="dwxzdm" styleId="dwxzdm"
									style="width:210px">
									<html:option value="��ѡ��">----------��ѡ��----------</html:option>
									<html:options collection="ListD" property="dwxzdm"
										labelProperty="dwxz" />
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="E">
								<html:select name="rs" property="dwxzdm" styleId="dwxzdm"
									style="width:210px">
									<html:option value="��ѡ��">----------��ѡ��----------</html:option>
									<html:option value="50">���һ�����Ŀ*</html:option>
									<html:option value="51">�ط�������Ŀ*</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="F">
								<html:select name="rs" property="dwxzdm" style="width:210px">
									<html:option value="70">����ҵ</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="G">
								<html:select name="rs" property="dwxzdm" styleId="dwxzdm"
									style="width:210px">
									<html:option value="��ѡ��">----------��ѡ��----------</html:option>
									<html:option value="71">����ҵ����ѧ</html:option>
									<html:option value="72">�����ݲ���ҵ</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="H">
								<html:select name="rs" property="dwxzdm" styleId="dwxzdm"
									style="width:210px">
									<html:option value="��ѡ��">----------��ѡ��----------</html:option>
									<html:option value="70">����ҵ</html:option>
									<html:option value="71">����ҵ����ѧ</html:option>
									<html:option value="72">�����ݲ���ҵ</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="I">
								<html:text name="rs" property="dwxzdm" value="" readonly="true"
									style="width:210px" />
							</logic:equal>
						</td>
					</tr>
					<tr style="height:22px">
						
						<th>
							�Զ���1
						</th>
						<td align="left">
							<html:text name="rs" property="bz1" style="width:210px"
								maxlength="50" />

						</td>
						<th>
							�Զ���2
						</th>
						<td align="left">
							<html:text name="rs" property="bz2" style="width:210px"
								maxlength="50" />

						</td>
					</tr>
					<tr style="height:22px">
						<th>
							�Զ���3
						</th>
						<td align="left">
							<html:text name="rs" property="bz3" style="width:210px"
								maxlength="50" />

						</td>
						<th>
							�Զ���4
						</th>
						<td align="left">
							<html:text name="rs" property="bz4" style="width:210px"
								maxlength="50" />

						</td>
					</tr>
					<tr style="height:22px">
						<th>
							�Զ���5
						</th>
						<td >
							<html:text name="rs" property="bz5" style="width:210px"
								maxlength="50" />

						</td>
						<th></th>
						<td></td>
					</tr>
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>��������</b>
							</td>
						</tr>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>��ҵȥ��Ϊ����ǲ�����Ծ�ҵ������ҵ������ί����ְ�����ҵط���Ŀ���ı�ҵ����Ҫ��д�˲���</b>
							</td>
						</tr>
					</thead>

					<tr style="height:22px">
						<th align="right">
							<logic:equal value="yes" name="redflag" property="dwxzdm2">
								<font color="red">*</font>
							</logic:equal>
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
								<logic:equal value="yes" name="redflag" property="dajsd">
									<font color="red">*</font>
								</logic:equal>
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
							<logic:equal value="yes" name="redflag" property="dwdz">
								<font color="red">*</font>
							</logic:equal>
							��λ��ַ
						</th>
						<td align="left">
							<!-- ������ -->
							<logic:equal name="xxdm" value="10333">
							<html:select name="rs" property="dwshen" onchange="dwShiXx()" styleId="dwshen"
							style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="ssList" property="ssmc"
									labelProperty="ssmc" />
							</html:select>
							<br/>
							<html:select name="rs" property="dwshi" styleId="dwshi"
								 style="width:180px">
								 <html:option value=""></html:option>
								<html:options collection="shiList" property="shimc" 
									labelProperty="shimc" />
							</html:select>
							<br/>
							<html:select name="rs" property="dwxian" styleId="dwxian"
								style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="xianList" property="xianmc" 
									labelProperty="xianmc" />
							</html:select>
							<html:hidden  property="dwdz" styleId="dwdz"/>
							</logic:equal>
							<logic:notEqual name="xxdm" value="10333">
								<html:text name="rs" property="dwdz" style="width:210px" />
							</logic:notEqual>
						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="dajsddz">
								<font color="red">*</font>
							</logic:equal>
							�������յص�ַ
						</th>
						<td align="left">
							<html:text name="rs" property="dajsddz" style="width:210px" />

						</td>
					</tr>
					<tr style="height:22px">
						<th>
							��λ��ϵ��
						</th>
						<td>
							<html:text name="rs" property="dwlxr" style="width: 210px;" maxlength="60"></html:text>
						</td>
						<th>
							�������յ�λ����
						</th>
						<td align="left">
							<html:text name="rs" property="dajsddwmc" style="width: 210px;" maxlength="60"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							<logic:equal value="yes" name="redflag" property="dwdh">
								<font color="red">*</font>
							</logic:equal>
							��λ�绰
						</th>
						<td align="left">
							<html:text name="rs" property="dwdh" style="width:210px"
								maxlength="12" />

						</td>
						<th align="right">
							<logic:equal value="yes" name="redflag" property="dajsdyb">
								<font color="red">*</font>
							</logic:equal>
							�������յ��ʱ�
						</th>
						<td>
							<html:text name="rs" property="dajsdyb" style="width:210px"
								maxlength="6" />

						</td>
					</tr>
					<tr style="height:22px">
						<th>
							<logic:equal value="yes" name="redflag" property="dwyb">
								<font color="red">*</font>
							</logic:equal>
							��λ�ʱ�
						</th>
						<td align="left">
							<html:text name="rs" property="dwyb" style="width:210px"
								maxlength="6" />

						</td>
						<th>
							<logic:equal value="10856" name="xxdm">
								<logic:equal value="yes" name="redflag" property="dqlx">
									<font color="red">*</font>
								</logic:equal>
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
						<th>
							<logic:equal value="yes" name="redflag" property="wyj">
								<font color="red">*</font>
							</logic:equal>
							ΥԼ��
						</th>
						<td align="left">
							<html:text name="rs" property="wyj" style="width:210px"
								maxlength="5" />

						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="hyfl">
								<font color="red">*</font>
							</logic:equal>
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
						<th>
							<logic:equal value="yes" name="redflag" property="dynypjgz">
								<font color="red">*</font>
							</logic:equal>
							��һ����ƽ������
						</th>
						<td align="left">
							<html:text name="rs" property="dynypjgz" style="width:210px"
								maxlength="5" />

						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="zydk">
								<font color="red">*</font>
							</logic:equal>
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
						<th>
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
						<th>
							��λע���ʽ�
						</th>
						<td align="left">
							<html:text name="rs" property="dwzczj" style="width: 210px;" maxlength="60"></html:text>
						</td>
					</tr>
					<logic:equal value="11122" name="xxdm" scope="session">
					<tr style="height:22px">
						<th>
							������ʼʱ��
						</th>
						<td align="left">
								<html:text property="bdkssj" maxlength="60" style="width: 210px;" onclick="return showCalendar('bdkssj','y-mm-dd');" ></html:text>
						</td>
						<th>
							��������ʱ��
						</th>
						<td align="left">
							<html:text property="bdjssj" maxlength="60" style="width: 210px;" onclick="return showCalendar('bdjssj','y-mm-dd');"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							����֤��
						</th>
						<td align="left">
							<html:text property="bdzh" style="width: 210px;" maxlength="60"></html:text>
						</td>
						<th>
						</th>
						<td align="left">
						</td>
					</tr>
					</logic:equal>
					<logic:equal value="12620" name="xxdm">
							<tr >
						<th align="right">��������顢������ϻ򸽼���</th>
						<td align="left" colspan="3">
							<font color="red">(����Ϲ��࣬�����ϴ�)</font>
							<br/>
							<input type="file" name="uploadFile" id="cfwj" contentEditable="false" style="width:60%" />
						</td>
						</tr>
					</logic:equal>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button  onclick="savejyxy()">
										�� �� Э ��
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button  onclick="jyxyreinput()">
										ȡ �� �� ��
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button  onclick="expAppTab('rsT','��ҵЭ��','')">
										�� ӡ Э ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
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
			<logic:notEmpty name="exists">
				<logic:equal name="exists" value="exists">
					<script>
    alert("ѧУ�����δͨ������ȴ�ѧУ���ͨ���Ժ��������룡");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
