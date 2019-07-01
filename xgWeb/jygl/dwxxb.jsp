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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	function changePage(defaultId){//�л�ҳ�� 
	var title = defaultId.id.substr(0,defaultId.id.length-1);
	var titleName,anotherName,anotherName2;
	if (title == "zks"){
		titleName = "zks";
		document.getElementById("titName").value = "zks";				
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "yjs";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		anotherName2 = "bks";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
	  } else if (title == "yjs") {
		titleName = "yjs";
		document.getElementById("titName").value = "yjs";		
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "zks";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		anotherName2 = "bks";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
	  }	else if (title == "bks") {
		titleName = "bks";
		document.getElementById("titName").value = "bks";		
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "zks";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		anotherName = "yjs";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
	  }	  	  
		document.forms[0].submit();
   }
   
   
	function loadchange(){
	var tab = document.getElementById("titName").value;
	document.getElementById(tab+"l").className = "xxk_on_l";
	document.getElementById(tab+"m").className = "xxk_on_m";
	document.getElementById(tab+"r").className = "xxk_on_r";
    }
    
    
    function add(url){
        var xh = document.getElementById("xh").value;
        var xm = document.getElementById("xm").value;
    	var sfzh = document.getElementById("sfzh").value;
    	var zydm = document.getElementById("zydm").value;
    	var zymc = document.getElementById("zymc").value;
    	var xldm = document.getElementById("xldm").value;
    	var sydqdm = document.getElementById("sydqdm").value;
    	var pyfsdm = document.getElementById("pyfsdm").value;
    	var xz = document.getElementById("xz").value;
    	var titName = document.getElementById("titName").value;
    	
    	if(xh==""){
    	alert("ѧ�ű�����д��");
    	return false;
    	}
    	if(!isNumber(xh)){
    	alert("ѧ���������");
    	return false;
    	}
    	if(xm==""){
    	alert("����������д��");
    	return false;
    	}
    	if(zydm==""){
    	alert("רҵ���������д��");
    	return false;
    	}	
    	if(zymc==""){
    	alert("רҵ���Ʊ�����д��");
    	return false;
    	}
    	if(xldm==""){
    	alert("ѧ�����������д��");
    	return false;
    	}
    	if(xz=="4"&&titName!="bks"){
    	alert("ѧ����ѧ���������ͻ�����飡");
    	return false;
    	}
    	if(xz=="7"&&titName!="yjs"){
    	alert("ѧ����ѧ���������ͻ�����飡");
    	return false;
    	}
    	if(xz=="3"&&titName=="bks"){
    	alert("ѧ����ѧ���������ͻ,���飡");
    	return false;
    	}
    	if(sydqdm==""){
    	alert("��Դ�������������д��");
    	return false;
    	}
    	if(pyfsdm==""){
    	alert("������ʽ���������д��");
    	return false;
    	}	     
    	if(checkSfzh(sfzh)){
		 		document.forms[0].action = "/xgxt/bysjbxxbSave.do?doType=save&act=cancle";
		 		document.forms[0].submit();
        }
    }
    
    function reinputagain(url){
    		
            document.forms[0].action = "/xgxt/bysjbxxbSave.do?act=cancle";
		 	document.forms[0].submit();
    }
    
    function checkSfzh(sfzh) {
    sfzh=sfzh.toUpperCase()
	var OldID;
	if(sfzh.length == 15){
		OldID = sfzh;
		return true;
	}else if(sfzh.length == 18){
		OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
	}else{
		alert("��������ȷ�����֤���룡","",function(){
			obj.select();
			obj.focus();
		});
		return false;
	}
	var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	var A = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
	var i, j, S;
	var NewID, ID, strNF;
	NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
	S = 0;
	for( i = 0; i <= 16; i++){
		j = NewID.substring(i, i+1) * W[i];
		S = S + j;
	}
	S = S % 11;
	if(sfzh != NewID + A[S]){
		alert("��������ȷ�����֤���룡","",function(){
			obj.select();
			obj.focus();
		});
		return false;
	}
	return true;
    }

function isCard(s){ 
	s = trim(s); 
	var p = /^\d{15}(\d{2}[xX0-9])?$/; 
	return p.test(s);
}
		   
function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
}

function queryxh(){
	var xh = document.getElementById("xh").value;
	
	 document.forms[0].action = "/xgxt/bysjbxxbSave.do?act=query&xh="+xh;
     document.forms[0].submit();
}




	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="loadchange()">
		<html:form action="/bysjbxxb_input.do" method="post">
		
			<div class="title">
				<div class="title_img" id="title_m">
					��ҵ���� - ѧ����Ϣ - ѧ����Ϣ�ϱ�
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
				<input type="hidden" id="titName" name="titName"
					value="<bean:write name="titName" scope="request" />" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />
				<input type="hidden" id="url" name="url" value="/bysjbxxb_input.do" />

				<div class="xxk">
					<logic:notEmpty name="pages">
						<logic:iterate id="card" name="pages" scope="request">
							<ul>
								<li id="<bean:write name='card' property='en'/>l"
									class="xxk_off_l"></li>
								<li id="<bean:write name='card' property='en'/>m"
									onclick="changePage(this)" class="xxk_off_m">
									&nbsp;
									<bean:write name='card' property='cn' />
									&nbsp;
								</li>
								<li id="<bean:write name='card' property='en'/>r"
									class="xxk_off_r"></li>
							</ul>
						</logic:iterate>
					</logic:notEmpty>
				</div>



				<table class="tbstyle" id="rsTable" width="100%" style="cursor:hand">
					<thead>
						<tr>
							<td colspan="4" align="center">
								<b>ѧ����Ϣ¼��</b>
						<!-- <button onclick="showTopWin('/xgxt/turndmkqueryjsp.do',427,480);"class="button2"> ������ѯ��</button>   -->		
							</td>
						</tr>
					</thead>
					<tr align="center" style="cursor:hand">
								<td align="right">
									<font color="red">*</font>
									��λ���ƣ�
								</td>
								<td align="left">
									<html:text name='rs' property="xh" styleId="xh"
										onkeypress="if(window.event.keyCode==13) queryxh();" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</td>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>
								ѧ�ţ�
							</td>
							<td align="left">
								<html:text property="xh" name="rs" styleId="xh" readonly="true"></html:text>
							</td>
						</logic:equal>
						<td width="20%" align="right">
							<font color="red">*</font>
							���֤�ţ�
						</td>
						<td align="left">
							<html:text name='rs' property="sfzh" />
						</td>
					</tr>
					<tr align="center" style="cursor:hand">
						<td width="20%" align="right">
							<font color="red">*</font>
							������
						</td>
						<td align="left">
							<html:text name='rs' property="xm" readonly="true" />
						</td>
						<td width="20%" align="right">
							<font color="red">*</font>
							�Ա���룺
						</td>
						<td align="left">
							<html:text name='rs' property="xbm" readonly="true" />
						</td>
					</tr>
					<tr align="center" style="cursor:hand">
						<td width="20%" align="right">
							<font color="red">*</font>
							ѧУ���룺
						</td>
						<td align="left">
							<html:text name='rs' property="xxdm" 
								readonly="true" />
						</td>
						<td width="20%" align="right">
							<font color="red">*</font>
							ѧУ���ƣ�
						</td>
						<td align="left">
							<html:text name='rs' property="xxmc" 
								readonly="true" />

						</td>
					</tr>
					<tr align="center" style="cursor:hand">
						<td width="20%" align="right">
							<font color="red">*</font>
							רҵ���룺
						</td>
						<td align="left">
							<html:text name="rs" property="zydm" readonly="true" />
						</td>
						<td width="20%" align="right" readonly="true">
							<font color="red">*</font>
							רҵ���ƣ�
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" readonly="true" />
						</td>
					</tr>
					<tr align="center" style="cursor:hand">
						<td width="20%" align="right">
							<font color="red">*</font>
							ѧ�����룺
						</td>
						<td align="left">
						<logic:equal name="Listxl" value="zks">
								<html:select name="rs" property="xldm" styleId="xldm"
									style="width:150px">
									<html:option value=""></html:option>
									<html:option value="41">ר������ҵ</html:option>
									<html:option value="43">ר������ҵ</html:option>
									<html:option value="49">ר������ҵ</html:option>
									<html:option value="61">��ְ</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="Listxl" value="bks">
								<html:select name="rs" property="xldm" styleId="xldm"
									style="width:150px">
									<html:option value=""></html:option>
									<html:option value="25">˫ѧλ��ҵ</html:option>
									<html:option value="31">��������ҵ</html:option>
									<html:option value="33">��������ҵ</html:option>
									<html:option value="39">��������ҵ</html:option>
									<html:option value="61">��ְ</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="Listxl" value="yjs">
								<html:select name="rs" property="xldm" styleId="xldm"
									style="width:150px">
									<html:option value=""></html:option>
									<html:option value="11">˶ʿ����ҵ</html:option>
									<html:option value="13">˶ʿ����ҵ</html:option>
									<html:option value="19">˶ʿ����ҵ</html:option>
									<html:option value="21">˶���ҵ</html:option>
								</html:select>
							</logic:equal>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font>
							ѧ�ƣ�
						</td>
						<td align="left">
							<html:text name='rs' property="xz" readonly="true" />
						</td>
					</tr>
					<tr align="center" style="cursor:hand">
						<td width="20%" align="right">
							<font color="red">*</font> 
							��Դ�������룺
						</td>
						<td align="left">
							<html:select name="rs" property="sydqdm" styleId="sydqdm"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="sydqList" property="sydqdm"
									labelProperty="sydq" />
							</html:select>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font>
							��ѧ��ȣ�
						</td>
						<td align="left">
							<html:text name="rs" property="nd" readonly="true" />
						</td>

					</tr>
					<tr align="center" style="cursor:hand">
						<td width="20%" align="right">
							<font color="red">*</font>
							������ʽ���룺
						</td>
						<td align="left">
							<html:select name="rs" property="pyfsdm" styleId="pyfsdm"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="pyfsList" property="pyfsdm"
									labelProperty="pyfs" />
							</html:select>
						</td>
						<td width="20%" align="right">
							��ע��
						</td>
						<td align="left">
							<html:text name="rs" property="memo" />
						</td>
					</tr>
				</table>

				<div class="buttontool" align="center">
					<button class="button2" onclick="add('/xgxt/bysjbxxbSave.do')"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="reinputagain('/xgxt/bysjbxxb_input.do')" type="reset"
						style="width:80px">
						�� ��
					</button>
				</div>

			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
                      alert("�ύ�ɹ���");
                    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
                      alert("�ظ��ύ������ʧ��!");
                    </script>
				</logic:equal>
				<logic:equal name="inserted" value="que">
					<script>
                      alert("����ѡ�������д��");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
</html>
