<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript">
		function yz(){
			var xxdm = $("xxdm").value;
			var xh = document.getElementById('xh').value;
			var bxnyhzzqk = document.getElementById('bxnyhzzqk').value;
			var jtcszrzhqk = document.getElementById('jtcszrzhqk').value;
			var jtcstfywqk = document.getElementById('jtcstfywqk').value;
			var jtcyycjnmrndnlrqk = document.getElementById('jtcyycjnmrndnlrqk').value;
			var jtcysyqk = document.getElementById('jtcysyqk').value;
			var jtqzqk = document.getElementById('jtqzqk').value;
			var qtqk = document.getElementById('qtqk').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(bxnyhzzqk != null){
	         	if(bxnyhzzqk.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("ѧ����ѧ���ѻ�����������ܴ���600���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(jtcszrzhqk != null){
	         	if(jtcszrzhqk.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("��ͥ������Ȼ�ֺ�������ܴ���600���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(jtcstfywqk != null){
	         	if(jtcstfywqk.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("��ͥ����ͻ�������¼����ܴ���600���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(jtcyycjnmrndnlrqk != null){
	         	if(jtcyycjnmrndnlrqk.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("��ͥ��Ա��м����������Ͷ�������������ܴ���600���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(jtcysyqk != null){
	         	if(jtcysyqk.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("��ͥ��Աʧҵ������ܴ���600���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(jtqzqk != null){
	         	if(jtqzqk.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("��ͥǷծ������ܴ���600���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(qtqk != null){
	         	if(qtqk.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("����������ܴ���600���ַ�");
	          		 return false;
	       		 }
	       	}
	       	
	       	if( xxdm == "11078"){//���ݴ�ѧ
		       	if(gzdx_yz()){
					document.forms[0].action = "/xgxt/n05_xszz.do?method=jtqkdc1sqSave";
					document.forms[0].submit();
				}
			}else{
				document.forms[0].action = "/xgxt/n05_xszz.do?method=jtqkdc1sqSave";
				document.forms[0].submit();
			}
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/n05_xszz.do?method=jtqkdc1sqb";
			document.forms[0].submit();
		}
		function changeXf(){
			var sfjxf = document.getElementById('sfjxf').value;
			
			if (sfjxf == "��") {
				document.getElementById('yjxf').disabled = false;
			} else {
				document.getElementById('yjxf').disabled = true;
			}
		}
		function changeZsf(){
			var sfjzsf = document.getElementById('sfjzsf').value;
			
			if (sfjzsf == "��") {
				document.getElementById('yjzsf').disabled = false;
			} else {
				document.getElementById('yjzsf').disabled = true;
			}
		}
		
	function gzdx_yz(){
	
		//��ѧǰ����
		var rxqhk = $("rxqhk").value;
		
		if(rxqhk == ""){
			alert("��ѧǰ���ڲ���Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//��ͥ�˿���
		var jtrks = $("jtrks").value;
		
		if(jtrks == ""){
			alert("��ͥ�˿�������Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//��ҵѧУ
		var byxx = $("byxx").value;
		
		if(byxx == ""){
			alert("��ҵѧУ����Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//�����س�
		var grtc = $("grtc").value;
		
		if(grtc == ""){
			alert("�����س�����Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//�²�
		var sfgc = $("sfgc").value;
		
		if(sfgc == ""){
			alert("�Ƿ�²в���Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//����
		var sfdq = $("sfdq").value;
		
		if(sfdq == ""){
			alert("�Ƿ��ײ���Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//��ʿ��Ů
		var sflszn = $("sflszn").value;
		
		if(sflszn == ""){
			alert("�Ƿ���ʿ��Ů����Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//��ͥ��ַ
		var jtdz = $("jtdz").value;
		
		if(jtdz == ""){
			alert("��ͥ��ַ����Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//��������
		var yzbm = $("yzbm").value;
		
		if(yzbm == ""){
			alert("�������벻��Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//ѧ����У��ϵ�绰
		var xszxlxdh = $("xszxlxdh").value;
		
		if(xszxlxdh == ""){
			alert("ѧ����У��ϵ�绰����Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//��ͥ��ϵ�绰
		var jtlxdh = $("jtlxdh").value;
		
		if(jtlxdh == ""){
			alert("��ͥ��ϵ�绰����Ϊ�գ���ȷ�ϣ�");
			return false;
		}
				
		//��ͥ�˾�������
		var jtrjnsr = $("jtrjnsr").value;
		
		if(jtrjnsr == ""){
			alert("��ͥ�˾������벻��Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//��ͥ�˾�������
		var jtrjysr = $("jtrjysr").value;
		
		if(jtrjysr == ""){
			alert("��ͥ�˾������벻��Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//ѧ����ѧ���ѻ��������
		var bxnyhzzqk = $("bxnyhzzqk").value;
		
		if(bxnyhzzqk == ""){
			alert("ѧ����ѧ���ѻ������������Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//��ѧ�����ѧ�����
		var sfjxf = $("sfjxf").value;
		var yjxf = $("yjxf").value;
		if(sfjxf == ""){
			alert("��ѧ�����ѧ���������Ϊ�գ���ȷ�ϣ�");
			return false;
		}else if(sfjxf == "��" && yjxf == ""){
			alert("��ѧ�����ѧ���������Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//����ס�޷����
		var sfjzsf = $("sfjzsf").value;
		var yjzsf = $("yjzsf").value;
		if(sfjzsf == ""){
			alert("����ס�޷��������Ϊ�գ���ȷ�ϣ�");
			return false;
		}else if(sfjzsf == "��" && yjzsf == ""){
			alert("����ס�޷��������Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//��ͥ�ṩ�����
		var jttgshf = $("jttgshf").value;

		if(jttgshf == ""){
			alert("��ͥ�ṩ����Ѳ���Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//��У������
		var zxxxf = $("zxxxf").value;

		if(zxxxf == ""){
			alert("��У�����Ѳ���Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//	��ͥ������Ȼ�ֺ����
		var jtcszrzhqk = $("jtcszrzhqk").value;

		if(jtcszrzhqk == ""){
			alert("	��ͥ������Ȼ�ֺ��������Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//	��ͥ����ͻ�������¼�
		var jtcstfywqk = $("jtcstfywqk").value;

		if(jtcstfywqk == ""){
			alert("	��ͥ������Ȼ�ֺ��������Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//	��ͥ��Ա��м����������Ͷ����������
		var jtcyycjnmrndnlrqk = $("jtcyycjnmrndnlrqk").value;

		if(jtcyycjnmrndnlrqk == ""){
			alert("	��ͥ��Ա��м����������Ͷ��������������Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//	��ͥ��Աʧҵ���
		var jtcysyqk = $("jtcysyqk").value;

		if(jtcysyqk == ""){
			alert("��ͥ��Աʧҵ�������Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		// ��ͥǷծ���
		var jtqzqk = $("jtqzqk").value;

		if(jtqzqk == ""){
			alert("��ͥǷծ�������Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		// �Ƿ�ͱ�����һ�������м���,���ݻ�������ʿ���������������˻򼲹ʾ��˼������������ͥ
		var sfdbh = $("sfdbh").value;

		if(sfdbh == ""){
			alert("�Ƿ�ͱ�����һ�������м���,���ݻ�������ʿ���������������˻򼲹ʾ��˼������������ͥ����Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//�������
		var qtqk = $("qtqk").value;

		if(qtqk == ""){
			alert("�����������Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		
		//��ͥ��Ա״��
		
		//����
		var jtcy1_xm = $("jtcy1_xm").value;
		var jtcy2_xm = $("jtcy2_xm").value;
		var jtcy3_xm = $("jtcy3_xm").value;
		var jtcy4_xm = $("jtcy4_xm").value;
		var jtcy5_xm = $("jtcy5_xm").value;
		//����
		var jtcy1_nl = $("jtcy1_nl").value;
		var jtcy2_nl = $("jtcy2_nl").value;
		var jtcy3_nl = $("jtcy3_nl").value;
		var jtcy4_nl = $("jtcy4_nl").value;
		var jtcy5_nl = $("jtcy5_nl").value;
		//�뱾�˹�ϵ
		var jtcy1_gx = $("jtcy1_gx").value;
		var jtcy2_gx = $("jtcy2_gx").value;
		var jtcy3_gx = $("jtcy3_gx").value;
		var jtcy4_gx = $("jtcy4_gx").value;
		var jtcy5_gx = $("jtcy5_gx").value;
		//������ѧϰ����λ
		var jtcy1_gz = $("jtcy1_gz").value;
		var jtcy2_gz = $("jtcy2_gz").value;
		var jtcy3_gz = $("jtcy3_gz").value;
		var jtcy4_gz = $("jtcy4_gz").value;
		var jtcy5_gz = $("jtcy5_gz").value;
		//������
		var jtcy1_sr = $("jtcy1_sr").value;
		var jtcy2_sr = $("jtcy2_sr").value;
		var jtcy3_sr = $("jtcy3_sr").value;
		var jtcy4_sr = $("jtcy4_sr").value;
		var jtcy5_sr = $("jtcy5_sr").value;
		//����״��
		var jtcy1_zk = $("jtcy1_zk").value;
		var jtcy2_zk = $("jtcy2_zk").value;
		var jtcy3_zk = $("jtcy3_zk").value;
		var jtcy4_zk = $("jtcy4_zk").value;
		var jtcy5_zk = $("jtcy5_zk").value;
		
		if(	(jtcy1_xm != "" && jtcy1_nl != "" && jtcy1_gx != "" && jtcy1_gz != "" && jtcy1_sr != "" && jtcy1_zk != "") ||
			(jtcy1_xm != "" && jtcy1_nl != "" && jtcy1_gx != "" && jtcy1_gz != "" && jtcy1_sr != "" && jtcy1_zk != "") ||
			(jtcy1_xm != "" && jtcy1_nl != "" && jtcy1_gx != "" && jtcy1_gz != "" && jtcy1_sr != "" && jtcy1_zk != "") ||
			(jtcy1_xm != "" && jtcy1_nl != "" && jtcy1_gx != "" && jtcy1_gz != "" && jtcy1_sr != "" && jtcy1_zk != "") ||
			(jtcy1_xm != "" && jtcy1_nl != "" && jtcy1_gx != "" && jtcy1_gz != "" && jtcy1_sr != "" && jtcy1_zk != "")){
			return true;
		}else{
			alert("������������дһλ��ͥ��Ա����Ϣ��");
			return false;
		}
		
		return true;
	}
	</script>
</head>

<body>
	<div class="title">
		<div class="tab_cur" >
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ������ - ��ͥ�������
		</a></p>
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<p align="center">
			<font color="red">Ŀǰ������дʱ�䷶Χ�ڣ��ݲ�������д��</font>
		</p>
	</logic:equal>
		<html:form action="n05_xszz.do?method=jtqkdc1sq" method="post">
			<input type="hidden" id="url" name="url" value="/n05_xszz.do?method=jtqkdc1sq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal" value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="xxdm" name="xxdm"value="${xxdm }" />

			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<div class="tab">
			<table width="99%"  border="0" class="formlist">
				<thead>
	   				<tr>
	       				<th colspan="10"><span>��ͥ�������</span></th>
	       			</tr>
	  			</thead>
					<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td colspan="4">
							<html:text name='rs' property="xh" styleId="xh" onblur="dctStuXh()"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:notEqual name="type" value="modi">
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
							</logic:notEqual>
						</td>
					</logic:equal>
					<logic:equal name="userType" value="stu" scope="session">
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td  colspan="4">
							<input type="text" id="xh" name="xh"
								
								value="${userName }" readonly="true">
						</td>
					</logic:equal>
					<th>
							����
					</th>
					<td  colspan="4">
						<input type="text" readonly="readonly" id="xm" name="xm"
							
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th>
						�Ա�
					</th>
					<td  colspan="4">
						<input type="text" id="xb" readonly="readonly" name="xb"
							
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<th>
						��������
					</th>
					<td  colspan="4">
						<input type="text" id="csrq" name="csrq" readonly="readonly"
							
							value="<bean:write name="rs" property="csrq"/>">
					</td>
				</tr>
				<tr>
					<th>
						����
					</th>
					<td  colspan="4">
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<th>
						������ò
					</th>
					<td  colspan="4">
						<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
							
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
				</tr>
				<tr>
					<th>
							�꼶
					</th>
					<td  colspan="4">
						<input type="text" id="nj" readonly="readonly" name="nj"
							
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />����
					</th>
					<td  colspan="4">
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<th>
						רҵ����
					</th>
					<td  colspan="4">
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<th>
						�༶����
					</th>
					<td  colspan="4">
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<th>
						ѧ��
					</th>
					<td  colspan="4">
						<input type="text" id="xz" name="xz" readonly="readonly"
							
							value="<bean:write name="rs" property="xz"/>">
					</td>
					<th>
						���֤��
					</th>
					<td  colspan="4">
						<input type="text" id="sfzh" readonly="readonly" name="sfzh"
							
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<th>
							ѧ��
					</th>
					<td  colspan="4">
						<input type="text" id="xn" name="xn" readonly="readonly"
							
							value="<bean:write name="rs" property="xn"/>">
					</td>
					<th>
							����ʱ��
					</th>
					<td  colspan="4">
						<input type="text" id="sqsj" readonly="readonly" name="sqsj"
							
							value="<bean:write name="rs" property="sqsj"/>">
					</td>
				</tr>
				<tr>
					<th>
						��ѧǰ����
					</th>
					<td  colspan="4">
						<html:select name="rs" property="rxqhk">
							<html:option value=""></html:option>
							<html:option value="����">����</html:option>
							<html:option value="ũ��">ũ��</html:option>
						</html:select>
					</td>
					<th>
						��ͥ�˿���
					</th>
					<td  colspan="4">
						<input type="text" id="jtrks" name="jtrks" maxlength="4"
							
							value="<bean:write name="rs" property="jtrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						��ҵѧУ
					</th>
					<td  colspan="4">
						<input type="text" id="byxx" name="byxx" maxlength="100"
							
							value="<bean:write name="rs" property="byxx"/>">
					</td>
					<th>
						�����س�
					</th>
					<td  colspan="4">
						<input type="text" id="grtc" name="grtc" maxlength="100"
							
							value="<bean:write name="rs" property="grtc"/>">
					</td>
				</tr>
				<tr>
					<th >
							�²�
					</th>
					<td colspan="2">
						<html:select name="rs" property="sfgc">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
					<th>
						����
					</th>
					<td colspan="2">
						<html:select name="rs" property="sfdq">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
					<th>
						��ʿ��Ů
					</th>
					<td colspan="3">
						<html:select name="rs" property="sflszn">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						��ͥ��ַ
					</th>
					<td  colspan="4">
						<input type="text" id="jtdz" name="jtdz" maxlength="200"
							
							value="<bean:write name="rs" property="jtdz"/>">
					</td>
					<th>
						��������
					</th>
					<td  colspan="4">
						<input type="text" id="yzbm" name="yzbm" maxlength="6"
							
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						ѧ����У��ϵ�绰
					</th>
					<td  colspan="4">
						<input type="text" id="xszxlxdh" name="xszxlxdh" maxlength="20"
							
							value="<bean:write name="rs" property="xszxlxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						��ͥ��ϵ�绰
					</th>
					<td  colspan="4">
						<input type="text" id="jtlxdh" name="jtlxdh" maxlength="20"
							
							value="<bean:write name="rs" property="jtlxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
				<td colspan="10">
				<table width="100%">
				<tr>
					<th rowspan="6" >
						��<br />ͥ<br />��<br />Ա<br />��<br />��
					</th>
					<th>
						����
					</th>
					<th>
						����
					</th>
					<th >
						�뱾�˹�ϵ
					</th>
					<th>
						������ѧϰ����λ
					</th>
					<th>
							�����루Ԫ��
					</th>
					<th>
							����״��
					</th>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy1_xm" name="jtcy1_xm" maxlength="30" size="10"
							value="<bean:write name="rs" property="jtcy1_xm"/>">
					</td>
					<td >
						<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="3" size="10"
							value="<bean:write name="rs" property="jtcy1_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy1_gx" name="jtcy1_gx" maxlength="10" size="10"
							value="<bean:write name="rs" property="jtcy1_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy1_gz" name="jtcy1_gz" maxlength="100" size="10"
							value="<bean:write name="rs" property="jtcy1_gz"/>">
					</td>
					<td>
						<input type="text" id="jtcy1_sr" name="jtcy1_sr" maxlength="10" size="10"
							value="<bean:write name="rs" property="jtcy1_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td >
						<input type="text" id="jtcy1_zk" name="jtcy1_zk" maxlength="50" size="10"
							
							value="<bean:write name="rs" property="jtcy1_zk"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy2_xm" name="jtcy2_xm" maxlength="30" size="10"
							
							value="<bean:write name="rs" property="jtcy2_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="3" size="10"
							
							value="<bean:write name="rs" property="jtcy2_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy2_gx" name="jtcy2_gx" maxlength="10" size="10"
							
							value="<bean:write name="rs" property="jtcy2_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_gz" name="jtcy2_gz" maxlength="100" size="10"
							
							value="<bean:write name="rs" property="jtcy2_gz"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_sr" name="jtcy2_sr" maxlength="10" size="10"
							
							value="<bean:write name="rs" property="jtcy2_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy2_zk" name="jtcy2_zk" maxlength="50" size="10"
							
							value="<bean:write name="rs" property="jtcy2_zk"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy3_xm" name="jtcy3_xm" maxlength="30" size="10"
							
							value="<bean:write name="rs" property="jtcy3_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="3" size="10"
							
							value="<bean:write name="rs" property="jtcy3_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td >
						<input type="text" id="jtcy3_gx" name="jtcy3_gx" maxlength="10" size="10"
							
							value="<bean:write name="rs" property="jtcy3_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_gz" name="jtcy3_gz" maxlength="100" size="10"
							
							value="<bean:write name="rs" property="jtcy3_gz"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_sr" name="jtcy3_sr" maxlength="10" size="10"
							
							value="<bean:write name="rs" property="jtcy3_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy3_zk" name="jtcy3_zk" maxlength="50" size="10"
							
							value="<bean:write name="rs" property="jtcy3_zk"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy4_xm" name="jtcy4_xm" maxlength="30" size="10"
							
							value="<bean:write name="rs" property="jtcy4_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="3" size="10"
							
							value="<bean:write name="rs" property="jtcy4_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy4_gx" name="jtcy4_gx" maxlength="10" size="10"
							
							value="<bean:write name="rs" property="jtcy4_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_gz" name="jtcy4_gz" maxlength="100" size="10"
							
							value="<bean:write name="rs" property="jtcy4_gz"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_sr" name="jtcy4_sr" maxlength="10" size="10"
							
							value="<bean:write name="rs" property="jtcy4_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy4_zk" name="jtcy4_zk" maxlength="50" size="10"
							
							value="<bean:write name="rs" property="jtcy4_zk"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy5_xm" name="jtcy5_xm" maxlength="30" size="10"
							
							value="<bean:write name="rs" property="jtcy5_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="3" size="10"
							
							value="<bean:write name="rs" property="jtcy5_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy5_gx" name="jtcy5_gx" maxlength="10" size="10"
							
							value="<bean:write name="rs" property="jtcy5_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_gz" name="jtcy5_gz" maxlength="100" size="10"
							
							value="<bean:write name="rs" property="jtcy5_gz"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_sr" name="jtcy5_sr" maxlength="10" size="10"
						
							value="<bean:write name="rs" property="jtcy5_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy5_zk" name="jtcy5_zk" maxlength="50" size="10"
				
							value="<bean:write name="rs" property="jtcy5_zk"/>">
					</td>
				</tr>
				</table>
				</td>
				</tr>
				<tr>
					<th>
							��ͥ�˾�������
					</th>
					<td colspan="4">
						<input type="text" id="jtrjnsr" name="jtrjnsr" maxlength="8"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrjnsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						��ͥ�˾�������
					</th>
					<td colspan="4">
						<input type="text" id="jtrjysr" name="jtrjysr" maxlength="8"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrjysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
							ѧ����ѧ���ѻ��������
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="bxnyhzzqk" rows='3' style="width:100%" onblur="yzdx(this,'bxnyhzzqk')"/>
						<input type="hidden" id="bxnyhzzqk" name="bxnyhzzqk" value="">
					</td>
				</tr>
				<tr>
					<th>
							��ѧ�����ѧ�����
					</th>
					<td colspan="4">
						<html:select name="rs" property="sfjxf" onchange="changeXf()">
							<html:option value=""></html:option>
							<html:option value="��">�ѽ�</html:option>
							<html:option value="��">δ��</html:option>
						</html:select>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" id="yjxf" name="yjxf" maxlength="6"
							style="width:60px;heigh:100%"
							value="<bean:write name="rs" property="yjxf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">Ԫ
					</td>
					<th>
							����ס�޷����
					</th>
					<td colspan="4">
						<html:select name="rs" property="sfjzsf" onchange="changeZsf()">
							<html:option value=""></html:option>
							<html:option value="��">�ѽ�</html:option>
							<html:option value="��">δ��</html:option>
						</html:select>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" id="yjzsf" name="yjzsf" maxlength="6"
							style="width:60px;heigh:100%"
							value="<bean:write name="rs" property="yjzsf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">Ԫ
					</td>
				</tr>
				<tr>
					<th colspan="1">
							��ͥ�ṩ�����(Ԫ/��)
					</th>
					<td colspan="4">
						<input type="text" id="jttgshf" name="jttgshf" maxlength="8"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jttgshf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
							��У������(Ԫ/��)
					</th>
					<td colspan="4">
						<input type="text" id="zxxxf" name="zxxxf" maxlength="8"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zxxxf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						��ͥ������Ȼ�ֺ����
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="jtcszrzhqk" rows='3' style="width:100%" onblur="yzdx(this,'jtcszrzhqk')"/>
						<input type="hidden" id="jtcszrzhqk" name="jtcszrzhqk" value="">
					</td>
				</tr>
				<tr>
					<th>
						��ͥ����ͻ�������¼�
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="jtcstfywqk" rows='3' style="width:100%" onblur="yzdx(this,'jtcstfywqk')"/>
						<input type="hidden" id="jtcstfywqk" name="jtcstfywqk" value="">
					</td>
				</tr>
				<tr>
					<th>
						��ͥ��Ա��м����������Ͷ����������
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="jtcyycjnmrndnlrqk" rows='3' style="width:100%" onblur="yzdx(this,'jtcyycjnmrndnlrqk')"/>
						<input type="hidden" id="jtcyycjnmrndnlrqk" name="jtcyycjnmrndnlrqk" value="">
					</td>
				</tr>
				<tr>
					<th>
						��ͥ��Աʧҵ���
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="jtcysyqk" rows='3' style="width:100%" onblur="yzdx(this,'jtcysyqk')"/>
						<input type="hidden" id="jtcysyqk" name="jtcysyqk" value="">
					</td>
				</tr>
				<tr>
					<th>
							��ͥǷծ���
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="jtqzqk" rows='3' style="width:100%" onblur="yzdx(this,'jtqzqk')"/>
						<input type="hidden" id="jtqzqk" name="jtqzqk" value="">
					</td>
				</tr>
				<tr>
					<th colspan="6">
						<div align="center">
							<logic:equal name="xxdm" value="11078">
								�Ƿ�ͱ�����һ�������м���,���ݻ�������ʿ���������������˻򼲹ʾ��˼������������ͥ
							</logic:equal>
							<logic:notEqual name="xxdm" value="11078">
								�Ƿ�ͱ�����һ�������м���,��ʿ���������������˻򼲹ʾ��˼������������ͥ
							</logic:notEqual>
						</div>
					</th>
					<td  colspan="4">
						<html:select name="rs" property="sfdbh">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						�������
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="qtqk" rows='3' style="width:100%" onblur="yzdx(this,'qtqk')"/>
						<input type="hidden" id="qtqk" name="qtqk" value="">
					</td>
				</tr>
<!-- 				<tr>
					<td colspan="2">
						<div align="center">
							����������ϸͨѶ��ַ
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="mzbm_xxtxdz" name="mzbm_xxtxdz" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_xxtxdz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="mzbm_yzbm" name="mzbm_yzbm" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							������������(Ԫ/��)
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="ddzdshbz" name="ddzdshbz" maxlength="8"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ddzdshbz"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jbrxm" name="jbrxm" maxlength="30"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jbrxm"/>">
					</td>
					<td>
						<div align="center">
							�����˰칫��ϵ�绰
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jbrbgdh_qx" name="jbrbgdh_qx" maxlength="4"
							style="width:40px;heigh:100%"
							value="<bean:write name="rs" property="jbrbgdh_qx"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						-
						<input type="text" id="jbrbgdh_dh" name="jbrbgdh_dh" maxlength="16"
							style="width:120px;heigh:100%"
							value="<bean:write name="rs" property="jbrbgdh_dh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>-->
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="10"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button  id="buttonSave" onclick="yz();">
							�ύ����
						</button>
						&nbsp;
						<button 
							onclick="toPrintOut();">
							��&nbsp;ӡ
						</button>						           
			          </div>
			          </td>
			      </tr>
			    </tfoot>
			</table>
		</html:form>
	</body>
	<logic:equal name="sfksq" value="-1">
		<script language="javascript">
		  $("buttonSave").disabled=true;
		</script>
	</logic:equal>
	
	<script language="javascript">
		var sfjxf = document.getElementById('sfjxf').value;
			
		if (sfjxf == "��") {
			document.getElementById('yjxf').disabled = false;
		} else {
			document.getElementById('yjxf').disabled = true;
		}
		var sfjzsf = document.getElementById('sfjzsf').value;
			
		if (sfjzsf == "��") {
			document.getElementById('yjzsf').disabled = false;
		} else {
			document.getElementById('yjzsf').disabled = true;
		}
	</script>
	
</html>
