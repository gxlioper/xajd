<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%--<%@ include file="/syscommon/pagehead_V4.ini"%>--%>
	<%@ include file="/syscommon/head.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var sfzh = document.getElementById('sfzh').value;
			var sqje = document.getElementById('sqje').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((sfzh == null) || (sfzh == "")){
				alert("���֤����Ϊ�գ��뵽ѧ����Ϣ��ά��!");
				return false;
			}
			if(!chkSfzh(sfzh)){
				return false;
			}
			if ("6000" < Math.round(sqje)){
				alert("����������ѳ�����������6000Ԫ!");
				return false;
			}
			
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=gjzxdksqSave";
			document.forms[0].submit();
		}
		function hm(){
			var hkczh = document.getElementById('hkczh').value;
			if(hkczh.length != 18){
				alert("�����ʺź���λ������!");
				return false;
			}
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		/**���幫�÷���:�������֤����*/
function chkSfzh(obj) {
	var sfzh = obj.toLowerCase();
	var OldID;
	if(sfzh.length == 15){
	    if(sfzh.substring(6,8)<'80'){
	    alert("����������֤�����ǣ�������ǰ�ģ�������ѧ�����˵����֤���룡");
		return false;
	    }else{
		OldID = sfzh;
		return true;
		}
	}else if(sfzh.length == 18){
	    if(sfzh.substring(6,10)<'1980'){
	    alert("����������֤�����ǣ�����������ǰ�ģ�������ѧ�����˵����֤���룡");
		return false;
	    }else{
		OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
		}
		
	}else{
		alert("��������ȷ�����֤���룡");
		return false;
	}
	var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	var A = new Array("1", "0", "x","9", "8", "7", "6", "5", "4", "3", "2");
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
		alert("��������ȷ�����֤���룡");
		return false;
	}
	return true;
}

	function acount(xn){
		
		if("yi"==xn){
			var dyxnzsf=$("dyxnzsf").value;
			var dyxnshf=$("dyxnshf").value;
			var dyxnxf=$("dyxnxf").value;
			if(dyxnzsf==null || dyxnzsf==""){
				dyxnzsf=0;
			}
			if(dyxnshf==null || dyxnshf==""){
				dyxnshf=0;
			}
			if(dyxnxf==null || dyxnxf==""){
				dyxnxf=0;
			}
			$("dyxnhj").innerHTML=eval(dyxnzsf)+eval(dyxnshf)+eval(dyxnxf);
		}else if("er"==xn){
			var dexnzsf=$("dexnzsf").value;
			var dexnshf=$("dexnshf").value;
			var dexnxf=$("dexnxf").value;
			if(dexnzsf==null || dexnzsf==""){
				dexnzsf=0;
			}
			if(dexnshf==null || dexnshf==""){
				dexnshf=0;
			}
			if(dexnxf==null || dexnxf==""){
				dexnxf=0;
			}
			$("dexnhj").innerHTML=eval(dexnzsf)+eval(dexnshf)+eval(dexnxf);
		}else if("san"==xn){
			var dsanxnzsf=$("dsanxnzsf").value;
			var dsanxnshf=$("dsanxnshf").value;
			var dsanxnxf=$("dsanxnxf").value;
			if(dsanxnzsf==null  || dsanxnzsf==""){
				dsanxnzsf=0;
			}
			if(dsanxnshf==null || dsanxnshf==""){
				dsanxnshf=0;
			}
			if(dsanxnxf==null || dsanxnxf==""){
				dsanxnxf=0;
			}
			$("dsanxnhj").innerHTML=eval(dsanxnzsf)+eval(dsanxnshf)+eval(dsanxnxf);
		}else if("si"==xn){
			var dsixnzsf=$("dsixnzsf").value;
			var dsixnshf=$("dsixnshf").value;
			var dsixnxf=$("dsixnxf").value;
			if(dsixnzsf==null || dsixnzsf==""){
				dsixnzsf=0;
			}
			if(dsixnshf==null  || dsixnshf==""){
				dsixnshf=0;
			}
			if(dsixnxf==null  || dsixnxf==""){
				dsixnxf=0;
			}
			$("dsixnhj").innerHTML=eval(dsixnzsf)+eval(dsixnshf)+eval(dsixnxf);
		}else if("wu"==xn){
			var dwuxnzsf=$("dwuxnzsf").value;
			var dwuxnshf=$("dwuxnshf").value;
			var dwuxnxf=$("dwuxnxf").value;
			if(dwuxnzsf==null || dwuxnzsf==""){
				dwuxnzsf=0;
			}
			if(dwuxnshf==null  || dwuxnshf==""){
				dwuxnshf=0;
			}
			if(dwuxnxf==null  || dwuxnxf==""){
				dwuxnxf=0;
			}
			$("dwuxnhj").innerHTML=eval(dwuxnzsf)+eval(dwuxnshf)+eval(dwuxnxf);
		}
	
	}
	
	function acount(){
		
			var dyxnzsf=$("dyxnzsf").value;
			var dyxnshf=$("dyxnshf").value;
			var dyxnxf=$("dyxnxf").value;
			if(dyxnzsf==null || dyxnzsf==""){
				dyxnzsf=0;
			}
			if(dyxnshf==null || dyxnshf==""){
				dyxnshf=0;
			}
			if(dyxnxf==null || dyxnxf==""){
				dyxnxf=0;
			}
			$("dyxnhj").innerHTML=eval(dyxnzsf)+eval(dyxnshf)+eval(dyxnxf);
		
			var dexnzsf=$("dexnzsf").value;
			var dexnshf=$("dexnshf").value;
			var dexnxf=$("dexnxf").value;
			if(dexnzsf==null || dexnzsf==""){
				dexnzsf=0;
			}
			if(dexnshf==null || dexnshf==""){
				dexnshf=0;
			}
			if(dexnxf==null || dexnxf==""){
				dexnxf=0;
			}
			$("dexnhj").innerHTML=eval(dexnzsf)+eval(dexnshf)+eval(dexnxf);
		
			var dsanxnzsf=$("dsanxnzsf").value;
			var dsanxnshf=$("dsanxnshf").value;
			var dsanxnxf=$("dsanxnxf").value;
			if(dsanxnzsf==null  || dsanxnzsf==""){
				dsanxnzsf=0;
			}
			if(dsanxnshf==null || dsanxnshf==""){
				dsanxnshf=0;
			}
			if(dsanxnxf==null || dsanxnxf==""){
				dsanxnxf=0;
			}
			$("dsanxnhj").innerHTML=eval(dsanxnzsf)+eval(dsanxnshf)+eval(dsanxnxf);
		
			var dsixnzsf=$("dsixnzsf").value;
			var dsixnshf=$("dsixnshf").value;
			var dsixnxf=$("dsixnxf").value;
			if(dsixnzsf==null || dsixnzsf==""){
				dsixnzsf=0;
			}
			if(dsixnshf==null  || dsixnshf==""){
				dsixnshf=0;
			}
			if(dsixnxf==null  || dsixnxf==""){
				dsixnxf=0;
			}
			$("dsixnhj").innerHTML=eval(dsixnzsf)+eval(dsixnshf)+eval(dsixnxf);

			var dwuxnzsf=$("dwuxnzsf").value;
			var dwuxnshf=$("dwuxnshf").value;
			var dwuxnxf=$("dwuxnxf").value;
			if(dwuxnzsf==null || dwuxnzsf==""){
				dwuxnzsf=0;
			}
			if(dwuxnshf==null  || dwuxnshf==""){
				dwuxnshf=0;
			}
			if(dwuxnxf==null  || dwuxnxf==""){
				dwuxnxf=0;
			}
			$("dwuxnhj").innerHTML=eval(dwuxnzsf)+eval(dwuxnshf)+eval(dwuxnxf);
	
		}
	
	</script>
</head>

<body <logic:equal value="10488" name="xxdm"> onload="acount()"</logic:equal>>
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>��ѧ���� - ���� - ������ѧ��������</a>
		</p>
	</div>
		<html:form action="zgmsxy_xszz.do?method=zgmy_gjzxdksq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgmsxy_xszz.do?method=gjzxdksq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />"/>
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" />"/>

			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					Close();
					window.dialogArguments.document.getElementById('search_go').click();	
				}
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					Close();
					window.dialogArguments.document.getElementById('search_go').click();	
				}
	         	</script>
				</logic:match>
			</logic:present>
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("��ͨ����ˣ�����������޸ģ�");
	         		</script>
				</logic:match>
			</logic:present>
			<div class="tab" style="overflow-x:hidden;overflow-y:auto;height:520px">
			<table class="formlist" width="90%">
				<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td align="left" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:notEqual value="modi" name="doType">
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</logic:notEqual>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td align="left" width="34%">
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />" readonly="readonly"/>
						</td>
					</logic:equal>
					<th width="16%">
						<div>
							����
						</div>
					</th>
					<td width="34%">
						<input type="text" readonly="readonly" id="xm" name="xm"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							�Ա�
						</div>
					</th>
					<td>
						<input type="text" id="xb" readonly="readonly" name="xb"
							value="<bean:write name="rs" property="xb"/>"/>
					</td>
					<th>
						<div>
							����
						</div>
					</th>
					<td>
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							value="<bean:write name="rs" property="mzmc"/>"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							ϵ��
						</div>
					</th>
					<td>
						<input type="text" id="xymc" readonly="readonly" name="xymc"
							value="<bean:write name="rs" property="xymc"/>"/>
					</td>
					<th>
						<div>
							רҵ
						</div>
					</th>
					<td>
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							value="<bean:write name="rs" property="zymc"/>"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							�꼶
						</div>
					</th>
					<td>
						<input type="text" id="nj" name="nj" readonly="readonly"
							value="<bean:write name="rs" property="nj"/>"/>
					</td>
					<th>
						<div>
							<font color="red">*</font>���֤��
						</div>
					</th>
					<td>
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							 maxlength="18"
							value="<bean:write name="rs" property="sfzh"/>"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							��ͥ��ϸ��ַ
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="jtzz" name="jtzz" maxlength="100" style="width: 84%"
							value="<bean:write name="rs" property="jtzz"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							��ͥ�ʱ�
						</div>
					</th>
					<td>
						<input type="text" id="yzbm" maxlength="6" name="yzbm"
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
					</td>
					<th>
						<div>
							��ϵ�绰
						</div>
					</th>
					<td>
						<input type="text" id="lxdh" name="lxdh" maxlength="14"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" 
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							��������
						</div>
					</th>
					<td>
						<input type="text" id="fqxm" maxlength="20" name="fqxm"
							value="<bean:write name="rs" property="fqxm"/>"/>
					</td>
					<th>
						<div>
							���׹�����λ
						</div>
					</th>
					<td>
						<input type="text" id="fqgzdw" name="fqgzdw" maxlength="100"
							value="<bean:write name="rs" property="fqgzdw"/>"/>
					</td>
				</tr>
				<tr>
					<logic:equal value="10488" name="xxdm">
					<th>
						<div>
							����������
						</div>
					</th>
					</logic:equal>
					<logic:notEqual value="10488" name="xxdm">
					<th>
						<div>
							����������
						</div>
					</th>
					</logic:notEqual>
					<td>
						<input type="text" id="fqysr" maxlength="10" name="fqysr"
							value="<bean:write name="rs" property="fqysr"/>"
							onblur="checkMoney(this);"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
					</td>
					<th>
						<div>
							���׵绰
						</div>
					</th>
					<td>
						<input type="text" id="fqdh" name="fqdh" maxlength="20"
							value="<bean:write name="rs" property="fqdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							ĸ������
						</div>
					</th>
					<td>
						<input type="text" id="mqxm" maxlength="20" name="mqxm"
							value="<bean:write name="rs" property="mqxm"/>"/>
					</td>
					<th>
						<div>
							ĸ�׹�����λ
						</div>
					</th>
					<td>
						<input type="text" id="mqgzdw" name="mqgzdw" maxlength="100"
							value="<bean:write name="rs" property="mqgzdw"/>" />
					</td>
				</tr>
				<tr>
					<logic:equal value="10488" name="xxdm">
					<th>
						<div>
							ĸ��������
						</div>
					</th>
					</logic:equal>
					<logic:notEqual value="10488" name="xxdm">
					<th>
						<div>
							ĸ��������
						</div>
					</th>
					</logic:notEqual>
					<td>
						<input type="text" id="mqysr" maxlength="10" name="mqysr" 
							value="<bean:write name="rs" property="mqysr"/>"
							onblur="checkMoney(this);"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
					</td>
					<th>
						<div>
							ĸ�׵绰
						</div>
					</th>
					<td>
						<input type="text" id="mqdh" name="mqdh" maxlength="20"
							value="<bean:write name="rs" property="mqdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
					</td>
				</tr>
				<logic:equal value="10488" name="xxdm">
				<tr>
					<th>
						<div>
							�������֤��
						</div>
					</th>
					<td>
						<input type="text" id="fqsfzh" maxlength="18" name="fqsfzh"
							value="<bean:write name="rs" property="fqsfzh"/>"
							onblur="if(!checkSfzh(this)){this.focus();}" />
					</td>
					<th>
						<div>
							ĸ�����֤��
						</div>
					</th>
					<td>
						<input type="text" id="mqsfzh" name="mqsfzh" maxlength="18"
							value="<bean:write name="rs" property="mqsfzh"/>"
							onblur="if(!checkSfzh(this)){this.focus();}" />
					</td>
				</tr>
				</logic:equal>
				<tr>
					<th>
							���������
					</th>
					<td>
						<input type="text" id="sqje" name="sqje" maxlength="10"
							value="<bean:write name="rs" property="sqje"/>"
							onblur="checkMoney(this);"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
					</td>
					<th>
						��������
					</th>
					<td>
						<html:text property="dkyh" maxlength="50" value="${rs.dkyh }"></html:text>
					</td>
				</tr>
				<tr>
					<th>��������</th>
					<td>
						<html:text property="dknx" maxlength="2" value="${rs.dknx }" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>��
					</td>
					<th>�������еص�</th>
					<td>
						<html:text property="dkyhdd" maxlength="100" value="${rs.dkyhdd }"></html:text>
					</td>
				</tr>
				<logic:equal value="10407" name="xxdm">
				<tr>
					<th>����ѧ��</th>
					<td>
						<html:select name="dkxn" property="dkxn" style="width:180px" styleId="dkxn" styleClass="select" value="${rs.dkxn }">
							<html:options collection="xnList" property="xn"	labelProperty="xn" />
						</html:select>
					</td>
					<th>ѧ��</th>
					<td>
						<input type="text" id="xf" maxlength="5" name="xf"
							value="<bean:write name="rs" property="xf"/>"
							onblur="checkMoney(this);"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />Ԫ
					</td>
				</tr>
				<tr>
					<th>ס�޷�</th>
					<td>
						<input type="text" id="zsf" maxlength="5" name="zsf"
							value="<bean:write name="rs" property="zsf"/>"
							onblur="checkMoney(this);"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />Ԫ
					</td>
					<th></th>
					<td>
					</td>
				</tr>
				</logic:equal>
				<logic:equal value="10488" name="xxdm">
					<tr>
						<th>
							���������
						</th>
						<td colspan="3">
							<table width="100%" align="center">
								<tr>
									<th width="16%">
										<div align="center">
										ѧ��/��;
										</div>
									</th>
									<th width="16%">
										<div align="center">
										��һѧ��
										</div>
									</th>
									<th width="16%">
										<div align="center">
										�ڶ�ѧ��
										</div>
									</th>
									<th width="16%">
										<div align="center">
										����ѧ��
										</div>
									</th>
									<th width="16%">
										<div align="center">
										����ѧ��
										</div>
									</th><th width="16%">
										<div align="center">
										����ѧ��
										</div>
									</th>
								</tr>
								<tr>
									<th>
										<div align="center">
											ѧ��
										</div>
									</th>
									<td>
										<input type="text" style="width:80px" name="dyxnxf"  maxlength="10" value="${rs.dyxnxf}" 
											onblur="acount('yi')" onkeyup="checkInputNum(this)"
											/>Ԫ
									</td>
									<td>
										<input type="text" style="width:80px" name="dexnxf" maxlength="10" value="${rs.dexnxf}"
											onblur="acount('er')" onkeyup="checkInputNum(this)"/>Ԫ
									</td>
									<td>
										<input type="text" style="width:80px" name="dsanxnxf" maxlength="10" value="${rs.dsanxnxf}"
											onblur="acount('san')" onkeyup="checkInputNum(this)"/>Ԫ
									</td>
									<td>
										<input type="text" style="width:80px" name="dsixnxf" maxlength="10"  value="${rs.dsixnxf}"
											onblur="acount('si')" onkeyup="checkInputNum(this)"/>Ԫ
									</td>
									<td>
										<input type="text" style="width:80px" name="dwuxnxf" maxlength="10"  value="${rs.dwuxnxf}"
											onblur="acount('wu')" onkeyup="checkInputNum(this)"/>Ԫ
									</td>
								</tr>
								<tr>
									<th>
										<div align="center">
											ס�޷�
										</div>
									</th>
									<td>
										<input type="text" style="width:80px" name="dyxnzsf"  maxlength="10" value="${rs.dyxnzsf}" 
											onblur="acount('yi')" onkeyup="checkInputNum(this)"
											/>Ԫ
									</td>
									<td>
										<input type="text" style="width:80px" name="dexnzsf" maxlength="10" value="${rs.dexnzsf}"
											onblur="acount('er')" onkeyup="checkInputNum(this)"/>Ԫ
									</td>
									<td>
										<input type="text" style="width:80px" name="dsanxnzsf" maxlength="10" value="${rs.dsanxnzsf}"
											onblur="acount('san')" onkeyup="checkInputNum(this)"/>Ԫ
									</td>
									<td>
										<input type="text" style="width:80px" name="dsixnzsf" maxlength="10"  value="${rs.dsixnzsf}"
											onblur="acount('si')" onkeyup="checkInputNum(this)"/>Ԫ
									</td>
									<td>
										<input type="text" style="width:80px" name="dwuxnzsf" maxlength="10"  value="${rs.dwuxnzsf}"
											onblur="acount('wu')" onkeyup="checkInputNum(this)"/>Ԫ
									</td>
								</tr>
								<tr>
									<th>
										<div align="center">
											�����
										</div>
									</th>
									<td>
										<input type="text" style="width:80px" name="dyxnshf" maxlength="10" value="${rs.dyxnshf}"
											onblur="checkMoney(this);acount('yi')"
											onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>Ԫ
									</td>
									<td>
										<input type="text" style="width:80px" name="dexnshf" maxlength="10" value="${rs.dexnshf}"
											onblur="checkMoney(this);acount('er')"
											onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>Ԫ
									</td>
									<td>
										<input type="text" style="width:80px" name="dsanxnshf" maxlength="10" value="${rs.dsanxnshf}"
											onblur="checkMoney(this);acount('san')"
											onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>Ԫ
									</td>
									<td>
										<input type="text" style="width:80px" name="dsixnshf" maxlength="10"  value="${rs.dsixnshf}"
											onblur="checkMoney(this);acount('si')"
											onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>Ԫ
									</td>
									<td>
										<input type="text" style="width:80px" name="dwuxnshf" maxlength="10"  value="${rs.dwuxnshf}"
											onblur="checkMoney(this);acount('wu')"
											onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>Ԫ
									</td>
								</tr>
								<tr>
									<th>
										<div align="center">
											�ϼ�
										</div>
									</th>
									<td>
										<span id="dyxnhj">0</span>Ԫ
									</td>
									<td>
										<span id="dexnhj">0</span>Ԫ
									</td>
									<td>
										<span id="dsanxnhj">0</span>Ԫ
									</td>
									<td>
										<span id="dsixnhj">0</span>Ԫ
									</td>
									<td>
										<span id="dwuxnhj">0</span>Ԫ
									</td>
								</tr>
							</table>
						</td>
					
				</tr>
				<tr>
					<th>��֤������</th>
					<td>
						<input type="text" name="jzrxm" value="<bean:write name="rs" property="jzrxm"/>" maxlength="20" />
					</td>
					<th>
						�������˹�ϵ
					</th>
					<td>
						<input type="text" name="yjzrgx" value="<bean:write name="rs" property="yjzrgx"/>" maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>��֤����ϵ�绰</th>
					<td>
						<input type="text" name="jzrlxdh" value="<bean:write name="rs" property="jzrlxdh"/>" maxlength="20" />
					</td>
					<th>
						��֤�˼�ͥסַ
					</th>
					<td>
						<input type="text" name="jzrzz" value="<bean:write name="rs" property="jzrzz"/>" maxlength="100" />
					</td>
				</tr>
				</logic:equal>
				</tbody>
				
			</table>
			</div>
			<table class="formlist" width="90%">
				<tfoot>
			      <tr>
			        <td colspan="4">
			        	<div class="bz">
							 <logic:equal name="sfksq" value="1">
					        	"<span class="red">*</span>"Ϊ������
					        	
					        	<logic:equal value="xy" name="userType">
			          				<logic:equal name="rs" property="xxsh" value="ͨ��"> 
			          					&nbsp;&nbsp;<span class="red"><bean:message key="lable.xb" />�û������޸�ѧУ�û����ͨ������Ϣ��</span>
			          				</logic:equal>
			          			</logic:equal>
					         </logic:equal>
					          
					      	  <logic:equal name="sfksq" value="-1">
					        		<span class="red">���ڲ�������ʱ���ڣ�</span>
					          </logic:equal>
						</div>
			          <div class="btn">
			         	<logic:equal name="sfksq" value="1">
			          		<logic:equal value="xy" name="userType">
			          			<logic:notEqual name="rs" property="xxsh" value="ͨ��"> 
			          				<button type="button" name="�ύ" onclick="yz();">����</button>
			          			</logic:notEqual>
			          		</logic:equal>
			          		<logic:notEqual value="xy" name="userType">
			          			<button type="button" name="�ύ" onclick="yz();">����</button>
			          		</logic:notEqual>
			          	</logic:equal>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
		</html:form>
</body>
</html>
