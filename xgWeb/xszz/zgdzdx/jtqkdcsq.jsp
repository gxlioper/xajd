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
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var pkyyxxsm = document.getElementById('pkyyxxsm').value;
			var have = document.getElementById('have').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(pkyyxxsm != null){
	         	if(pkyyxxsm.replace(/[^\u0000-\u00ff]/g, "**").length > 800){	         
	          		 alert("ƶ��ԭ����ϸ˵�����ܳ���800���ַ���");
	          		 return false;
	       		 }
	       	}
	       	if (have == "have"){
	       	if (!confirm("���Ѿ����϶�Ϊ��ͥ��������ѧ�������޸ļ�ͥ���������Ϣ���������������϶���ȷ��Ҫ�޸���Ϣ��")){
				return false;
			}
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jtqkdcsqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jtqkdcsqb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur" id="jd">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>������ - ��ͥ�������</a>
		</p>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="zgdzdx_xszz.do?method=jtqkdcsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgdzdx_xszz.do?method=jtqkdcsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="sh" name="sh"
				value="<bean:write name="rs" property="sh" />">
			<input type="hidden" id="have" name="have"
				value="<bean:write name="have" />">

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
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("��ͨ����ˣ��������룡");
	         		</script>
				</logic:match>
			</logic:present>
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th align="center" colspan="3">
							<font color="red">*</font>ѧ��
						</th>
						<td align="left" colspan="2">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th colspan="3">
							<font color="red">*</font>ѧ��
						</th>
						<td  colspan="2">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<th width="16%">
							����
					</th>
					<td colspan="3">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th colspan="3">
							�Ա�
					</th>
					<td colspan="2">
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<th>
							ѧ��
					</th>
					<td colspan="3">
						<input type="text" id="xn" name="xn" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xn"/>">
					</td>
				</tr>
				<tr>
					<th colspan="3">
							����
					</th>
					<td colspan="2">
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<th>
							������ò
					</th>
					<td colspan="3">
						<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
				</tr>
				<tr>
					<th colspan="3">
							�꼶
					</th>
					<td colspan="2">
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />����
					</th>
					<td colspan="3">
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<th colspan="3">
							רҵ����
					</th>
					<td colspan="2">
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<th>
							�༶����
					</th>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<th colspan="3">
							���֤��
					</th>
					<td colspan="2">
						<input type="text" id="sfzh" readonly="readonly" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
					<th>
							��ѧǰ����
					</th>
					<td colspan="3" align="center">
						<logic:present name="rs" property="rxqhk">
							<logic:match value="����" name="rs" property="rxqhk">
								<input type="radio" name="rxqhk" value="����" checked>&nbsp;&nbsp;����
							    <input type="radio" name="rxqhk" value="ũ��">&nbsp;&nbsp;ũ��
							</logic:match>
							<logic:match value="ũ��" name="rs" property="rxqhk">
								<input type="radio" name="rxqhk" value="����">&nbsp;&nbsp;����
							    <input type="radio" name="rxqhk" value="ũ��" checked>&nbsp;&nbsp;ũ��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="rxqhk">
							<input type="radio" name="rxqhk" value="����" checked>&nbsp;&nbsp;����
							<input type="radio" name="rxqhk" value="ũ��">&nbsp;&nbsp;ũ��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							��Դ
					</th>
					<td colspan="6">
						<input type="text" id="sy" name="sy" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sy"/>">
					</td>
				</tr>
				<tr>
					<th colspan="3">
							��ͥ�˿���
					</th>
					<td colspan="2">
						<input type="text" id="jtrks" name="jtrks" maxlength="5"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
							�Ƿ�²�
					</th>
					<td colspan="3" align="center">
						<logic:present name="rs" property="sfgc">
							<logic:match value="��" name="rs" property="sfgc">
								<input type="radio" name="sfgc" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfgc" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfgc">
								<input type="radio" name="sfgc" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfgc" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfgc">
							<input type="radio" name="sfgc" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfgc" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<th colspan="3">
						�Ƿ���
					</th>
					<td colspan="2">
						<logic:present name="rs" property="sfdq">
							<logic:match value="��" name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfdq" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfdq" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfdq">
							<input type="radio" name="sfdq" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfdq" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
					<th>
						�Ƿ���ʿ��Ů
					</th>
					<td colspan="3" align="center">
						<logic:present name="rs" property="sflszn">
							<logic:match value="��" name="rs" property="sflszn">
								<input type="radio" name="sflszn" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sflszn" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sflszn">
								<input type="radio" name="sflszn" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sflszn" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sflszn">
							<input type="radio" name="sflszn" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sflszn" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							��ͥ��������
					</th>
					<td colspan="2">
						<input type="text" id="jt_yzbm" name="jt_yzbm" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jt_yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						��ͥ��ϵ�绰
					</th>
					<td colspan="3">
						<input type="text" id="jt_lxdh" name="jt_lxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jt_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th colspan="3">
						��ͥ��ϸͨѶ��ַ
					</th>
					<td colspan="6">
						<input type="text" id="jt_xxtxdz" name="jt_xxtxdz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jt_xxtxdz"/>">
					</td>
				</tr>
				<tr>
					<th width="4%" rowspan="6">
							��
							<br>
							ͥ
							<br>
							��
							<br>
							Ա
							<br>
							��
							<br>
							��
					</th>
					<th width="8%">
							����
					</th>
					<th width="8%">
							����
					</th>
					<th width="9%">
							��ѧ����ϵ
					</th>
					<th colspan="2">
							����(ѧϰ)��λ
					</th>
					<th width="12%">
							ְҵ
					</th>
					<th width="11%">
							������(Ԫ)
					</th>
					<th width="11%">
							����״��
					</th>
				</tr>
				<tr>
					<td width="6%">
							<input type="text" id="jtcy1_xm" name="jtcy1_xm" maxlength="50"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy1_xm"/>">
					</td>
					<td width="6%">
							<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="5"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy1_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
							<input type="text" id="jtcy1_gx" name="jtcy1_gx" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy1_gx"/>">
					</td>
					<td colspan="2">
							<input type="text" id="jtcy1_gzdw" name="jtcy1_gzdw"
								maxlength="100" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy1_gzdw"/>">
					</td>
					<td>
							<input type="text" id="jtcy1_zy" name="jtcy1_zy" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy1_zy"/>">
					</td>
					<td>
							<input type="text" id="jtcy1_sr" name="jtcy1_sr" maxlength="10"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy1_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
							<input type="text" id="jtcy1_jkzk" name="jtcy1_jkzk"
								maxlength="50" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy1_jkzk"/>">
					</td>
				</tr>
				<tr>
					<td width="6%">
							<input type="text" id="jtcy2_xm" name="jtcy2_xm" maxlength="50"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy2_xm"/>">
					</td>
					<td width="6%">
							<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="5"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy2_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
							<input type="text" id="jtcy2_gx" name="jtcy2_gx" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy2_gx"/>">
					</td>
					<td colspan="2">
							<input type="text" id="jtcy2_gzdw" name="jtcy2_gzdw"
								maxlength="100" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy2_gzdw"/>">
					</td>
					<td>
							<input type="text" id="jtcy2_zy" name="jtcy2_zy" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy2_zy"/>">
					</td>
					<td>
							<input type="text" id="jtcy2_sr" name="jtcy2_sr" maxlength="10"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy2_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
							<input type="text" id="jtcy2_jkzk" name="jtcy2_jkzk"
								maxlength="50" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy2_jkzk"/>">
					</td>
				</tr>
				<tr>
					<td width="6%">
							<input type="text" id="jtcy3_xm" name="jtcy3_xm" maxlength="50"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy3_xm"/>">
					</td>
					<td width="6%">
							<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="5"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy3_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
							<input type="text" id="jtcy3_gx" name="jtcy3_gx" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy3_gx"/>">
					</td>
					<td colspan="2">
							<input type="text" id="jtcy3_gzdw" name="jtcy3_gzdw"
								maxlength="100" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy3_gzdw"/>">
					</td>
					<td>
							<input type="text" id="jtcy3_zy" name="jtcy3_zy" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy3_zy"/>">
					</td>
					<td>
							<input type="text" id="jtcy3_sr" name="jtcy3_sr" maxlength="10"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy3_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
							<input type="text" id="jtcy3_jkzk" name="jtcy3_jkzk"
								maxlength="50" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy3_jkzk"/>">
					</td>
				</tr>
				<tr>
					<td width="6%">
							<input type="text" id="jtcy4_xm" name="jtcy4_xm" maxlength="50"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy4_xm"/>">
					</td>
					<td width="6%">
							<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="5"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy4_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
							<input type="text" id="jtcy4_gx" name="jtcy4_gx" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy4_gx"/>">
					</td>
					<td colspan="2">
							<input type="text" id="jtcy4_gzdw" name="jtcy4_gzdw"
								maxlength="100" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy4_gzdw"/>">
					</td>
					<td>
							<input type="text" id="jtcy4_zy" name="jtcy4_zy" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy4_zy"/>">
					</td>
					<td>
							<input type="text" id="jtcy4_sr" name="jtcy4_sr" maxlength="10"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy4_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
							<input type="text" id="jtcy4_jkzk" name="jtcy4_jkzk"
								maxlength="50" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy4_jkzk"/>">
					</td>
				</tr>
				<tr>
					<td width="6%">
							<input type="text" id="jtcy5_xm" name="jtcy5_xm" maxlength="50"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy5_xm"/>">
					</td>
					<td width="6%">
							<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="5"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy5_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
							<input type="text" id="jtcy5_gx" name="jtcy5_gx" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy5_gx"/>">
					</td>
					<td colspan="2">
							<input type="text" id="jtcy5_gzdw" name="jtcy5_gzdw"
								maxlength="100" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy5_gzdw"/>">
					</td>
					<td>
							<input type="text" id="jtcy5_zy" name="jtcy5_zy" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy5_zy"/>">
					</td>
					<td>
							<input type="text" id="jtcy5_sr" name="jtcy5_sr" maxlength="10"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy5_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
							<input type="text" id="jtcy5_jkzk" name="jtcy5_jkzk"
								maxlength="50" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy5_jkzk"/>">
					</td>
				</tr>
				<tr>
					<th colspan="3">
							�������֤��
					</th>
					<td colspan="2">
						<input type="text" id="fqsfzh" name="fqsfzh" maxlength="18"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="fqsfzh"/>">
					</td>
					<th>
							ĸ�����֤��
					</th>
					<td colspan="3">
						<input type="text" id="mqsfzh" name="mqsfzh" maxlength="18"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="mqsfzh"/>">
					</td>
				</tr>
				<tr>
					<th colspan="3">
							��ͥȫ������
					</th>
					<td colspan="2">
						<input type="text" id="jtqnsr" name="jtqnsr" maxlength="6"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jtqnsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
							�˾�������
					</th>
					<td colspan="3">
						<input type="text" id="rjnsr" name="rjnsr" maxlength="6"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="rjnsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th colspan="9">
							��ͥƶ��ԭ��
					</th>
				</tr>
				<tr>
					<th colspan="3">
							ũ��ƶ������
					</th>
					<td colspan="2" >
						<logic:present name="rs" property="sfncpkdq">
							<logic:match value="��" name="rs" property="sfncpkdq">
								<input type="radio" name="sfncpkdq" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfncpkdq" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfncpkdq">
								<input type="radio" name="sfncpkdq" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfncpkdq" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfncpkdq">
							<input type="radio" name="sfncpkdq" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfncpkdq" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
					<th>
							�����¸ڼ�ͥ
					</th>
					<td colspan="3">
						<logic:present name="rs" property="sfczxgjt">
							<logic:match value="��" name="rs" property="sfczxgjt">
								<input type="radio" name="sfczxgjt" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfczxgjt" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfczxgjt">
								<input type="radio" name="sfczxgjt" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfczxgjt" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfczxgjt">
							<input type="radio" name="sfczxgjt" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfczxgjt" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							��ĸ�²м�
					</th>
					<td colspan="2">
						<logic:present name="rs" property="sffmxcj">
							<logic:match value="��" name="rs" property="sffmxcj">
								<input type="radio" name="sffmxcj" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sffmxcj" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sffmxcj">
								<input type="radio" name="sffmxcj" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sffmxcj" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sffmxcj">
							<input type="radio" name="sffmxcj" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sffmxcj" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
					<th>
							���ش󼲲�
					</th>
					<td colspan="3">
						<logic:present name="rs" property="sfhzdjb">
							<logic:match value="��" name="rs" property="sfhzdjb">
								<input type="radio" name="sfhzdjb" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfhzdjb" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfhzdjb">
								<input type="radio" name="sfhzdjb" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfhzdjb" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfhzdjb">
							<input type="radio" name="sfhzdjb" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfhzdjb" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							���׼�ͥ
					</th>
					<td colspan="2">
						<logic:present name="rs" property="sfdqjt">
							<logic:match value="��" name="rs" property="sfdqjt">
								<input type="radio" name="sfdqjt" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfdqjt" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfdqjt">
								<input type="radio" name="sfdqjt" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfdqjt" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfdqjt">
							<input type="radio" name="sfdqjt" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfdqjt" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
					<th>
							�¶�
					</th>
					<td colspan="3">
						<logic:present name="rs" property="sfgr">
							<logic:match value="��" name="rs" property="sfgr">
								<input type="radio" name="sfgr" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfgr" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfgr">
								<input type="radio" name="sfgr" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfgr" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfgr">
							<input type="radio" name="sfgr" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfgr" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							��Ȼ�ֺ�
					</th>
					<td colspan="2">
						<logic:present name="rs" property="sfzrzh">
							<logic:match value="��" name="rs" property="sfzrzh">
								<input type="radio" name="sfzrzh" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfdqjt" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfzrzh">
								<input type="radio" name="sfzrzh" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfzrzh" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfzrzh">
							<input type="radio" name="sfzrzh" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfzrzh" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
					<th>
							��ͥ�˿ڶ�
					</th>
					<td colspan="3">
						<logic:present name="rs" property="sfjtrkd">
							<logic:match value="��" name="rs" property="sfjtrkd">
								<input type="radio" name="sfjtrkd" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfjtrkd" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfjtrkd">
								<input type="radio" name="sfjtrkd" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfjtrkd" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfjtrkd">
							<input type="radio" name="sfjtrkd" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfjtrkd" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							����
					</th>
					<td colspan="2">
						<logic:present name="rs" property="sfqt">
							<logic:match value="��" name="rs" property="sfqt">
								<input type="radio" name="sfqt" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfqt" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfqt">
								<input type="radio" name="sfqt" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfqt" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfqt">
							<input type="radio" name="sfqt" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfqt" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
					<td colspan="4">
						<input type="text" id="qtnr" name="qtnr"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qtnr"/>">
					</td>
				</tr>
				<tr>
					<th colspan="3">
							ƶ��ԭ����ϸ˵��
					</th>
					<td colspan="6">
						<html:textarea name="rs" property="pkyyxxsm" rows='3' style='width:100%' onblur="yzdx(this,'pkyyxxsm')"/>
						<input type="hidden" id="pkyyxxsm" name="pkyyxxsm" value="">
					</td>
				</tr>
				<tr>
					<th colspan="3">
							����Ƿծ���
					</th>
					<td colspan="6">
						<input type="text" id="jzqzqk" name="jzqzqk"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzqzqk"/>">
					</td>
				</tr>
				<tr>
					<th colspan="3">
							ѧ����ѧǰ�ѻ��������(���������)
					</th>
					<td colspan="6">
						<input type="text" id="xsrxqyhzzqk" name="xsrxqyhzzqk"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xsrxqyhzzqk"/>">
					</td>
				</tr>
				<tr>
					<th colspan="3">
							�������
					</th>
					<td colspan="6">
						<input type="text" id="qtqk" name="qtqk"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qtqk"/>">
					</td>
				</tr>
				<tr>
					<th colspan="3">
							����������������
					</th>
					<td colspan="2">
						<input type="text" id="mzbm_yzbm" name="mzbm_yzbm" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
							����������ϵ�绰
					</th>
					<td colspan="3">
						<input type="text" id="mzbm_lxdh" name="mzbm_lxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th colspan="3">
							����������ϸͨѶ��ַ${isQuery }
					</th>
					<td colspan="6">
						<input type="text" id="mzbm_xxtxdz" name="mzbm_xxtxdz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_xxtxdz"/>">
					</td>
				</tr>
				</tbody>
				<tfoot>
				 <tr>
				 	<logic:equal name="sfksq" value="1">
						<logic:equal name="have" value="notHave">
							<td colspan="10"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
							<div class="btn">
							<logic:notEqual name="isQuery" value="is">
							<button type="button"   onClick="yz();">
								�ύ����
							</button>
							</logic:notEqual>
							<button type="button"   onClick="toPrintOut();">
								�� ӡ
							</button>
							<button type="button" onclick="window.close();return false;" id="buttonClose">
									�� ��
							</button>
							</div>
							</td>
						</logic:equal>
						<logic:equal name="have" value="have">
							<td colspan="10"><div class="bz"><span class="red">
							���Ѿ����϶�Ϊ��ͥ��������ѧ�������޸ļ�ͥ���������Ϣ���������������϶���</span></div>
								<div class="btn">
								<logic:notEqual name="isQuery" value="is">
								<button type="button"   onClick="yz();">
								ȷ���޸�
								</button>
								</logic:notEqual>
								<button type="button"   onClick="toPrintOut();">
								�� ӡ
								</button>
								<button type="button" onclick="window.close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</logic:equal>
					</logic:equal>
			      </tr>
				</tfoot>
			</table>
		</div>
		</html:form>
</body>
</html>
