<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
	function add() {
		var dwbh = document.getElementById("dwbh").value;
		var dwmc = document.getElementById("dwmc").value;
		var savetype = document.getElementById("savetype").value;
		if (dwbh == "") {
			alert("��λ��Ų���Ϊ�գ�");
			return false;
		}
		if (dwmc == "") {
			alert("��λ�������Ʋ���Ϊ�գ�");
			return false;
		}
		//document.getElementById("buttonClose").click();
		document.forms[0].action = "dwlfxx.do?doType=save";
		document.forms[0].submit(); 
		if(savetype){
		BatAlert.showTips('�����޸ģ����Ժ�...');
		}else{
		BatAlert.showTips('�����ύ�����Ժ�...');
		}
	}

	//exclude left and right space; 
	function trim(s) {
		return rtrim(ltrim(s));
	}
	//exclude left space; 
	function ltrim(s) {
		return s.replace(/^\s*/, "");
	}
	//exclude right space; 
	function rtrim(s) {
		return s.replace(/\s*$/, "");
	}

	function isEmail(s) {
		s = trim(s);
		var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i;
		return p.test(s);
	}
	function reinputagain(){
    		
            document.forms[0].action = "/xgxt/dwlfxx.do";
		 	document.forms[0].submit();
    }
    function showbtn(){
    	var btn = document.getElementById("showtype").value;
		 if("dnset" == btn){
		 	document.getElementById("buttonClose1").style.display='none';
		 	document.getElementById("buttonsave").style.display='none';
		 }
    }
</script>
	</head>
	<body onload="showbtn();">

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��λ������Ϣ - ��λ������Ϣ�Ǽ�</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">
			<input type="hidden" id="showtype" name="showtype" value="${dwtype}" />
			<input type="hidden" id="savetype" name=savetype value="${savetype}" />
			<html:hidden property="dwid" name="rs2" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="add();" id="buttonSave">
										�� ��
									</button>
									<button onclick="reinputagain()" id="buttonClose1">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="15%">
								<font color="red">*</font>���
							</th>
							<td width="35%">
								<html:text property="dwbh" name="rs2"></html:text>
							</td>
							<th width="15%">
								<font color="red">*</font>��λ����
							</th>
							<td width="35%">
								<html:text property="dwmc" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��λ�绰
							</th>
							<td>
								<html:text property="dwdh" name="rs2"></html:text>
							</td>
							<th>
								��λ����
							</th>
							<td>
								<html:text property="dwcz" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��λ����
							</th>
							<td>
								<html:text property="dwyx" name="rs2"></html:text>
							</td>
							<th>
								��λ��ַ
							</th>
							<td>
								<html:text property="dwdz" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��λ�ʱ�
							</th>
							<td>
								<html:text property="dwyb" name="rs2"></html:text>
							</td>
							<th>
								ʱ��
							</th>
							<td>
								<html:text property="sj" name="rs2"
									onclick="return showCalendar('sj','y-mm-dd');"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								<html:text property="xm" name="rs2"></html:text>
							</td>
							<th>
								�Ա�
							</th>
							<td>
								<html:text property="xb" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								<html:text property="bm" name="rs2"></html:text>
							</td>
							<th>
								ְ��
							</th>
							<td>
								<html:text property="zw" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�̶��绰
							</th>
							<td>
								<html:text property="gddh" name="rs2"></html:text>
							</td>
							<th>
								�ƶ��绰
							</th>
							<td>
								<html:text property="yddh" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�����ʼ�
							</th>
							<td>
								<html:text property="dzyx" name="rs2"></html:text>
							</td>
							<th>
								��������
							</th>
							<td>
								<html:text property="lfsy" name="rs2"></html:text>
						</tr>
						<tr>
							<th>
								��̸�ص�
							</th>
							<td>
								<html:text property="ytdd" name="rs2"></html:text>
							</td>
							<th>
								����
							</th>
							<td>
								<html:text property="rs" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�Ӵ���
							</th>
							<td>
								<html:text property="jdr" name="rs2"></html:text>
							</td>
							<th>
								�Ӵ��ص�
							</th>
							<td>
								<html:text property="jddd" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�Ӵ�������1
							</th>
							<td>
								<html:text property="jdcyr1" name="rs2"></html:text>
							</td>
							<th>
								�Ӵ�������2
							</th>
							<td>
								<html:text property="jdcyr2" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�Ӵ�������3
							</th>
							<td>
								<html:text property="jdcyr3" name="rs2"></html:text>
							</td>
							<th>
								�Ӵ�������4
							</th>
							<td>
								<html:text property="jdcyr4" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td>
								<html:text property="bz" name="rs2"></html:text>
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:notEmpty name="insert">
			<logic:equal name="insert" value="ok">
				<script>
		alert("�ύ�ɹ���");
</script>
			</logic:equal>
			<logic:equal name="insert" value="no">
				<script>
	alert("�ظ��ύ������ʧ��!");
</script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
		alert("���³ɹ���");
		window.close();
		dialogArgumentsQueryChick();

</script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
	alert("�ظ��ύ������ʧ��!");
</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

