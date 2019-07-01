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
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var chhzjl = document.getElementById('chhzjl').value;
			var xxcj = document.getElementById('xxcj').value;
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(chhzjl != null){
	         	if(chhzjl.replace(/[^\u0000-\u00ff]/g, "**").length > 400){	         
	          		 alert("������ֽ������ܴ���400���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxcj != null){
	         	if(xxcj.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("ѧϰ�ɼ����ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("�������ɲ��ܴ���2000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/n05_xszz.do?method=gjlzjxj1sqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/n05_xszz.do?method=gjlzjxj1sqb";
			document.forms[0].submit();
		}
		function changeSr(){
			var jtzrks = document.getElementById('jtzrks').value;
			var rjysr = document.getElementById('rjysr').value;
			
			if((jtzrks == null) || (jtzrks == "")){
				jtzrks = "0";
			}
			if((rjysr == null) || (rjysr == "")){
				rjysr = "0";
			}
			document.getElementById('jtyzsr').value = toInt(jtzrks) * toInt(rjysr);
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a> ѧ������ - ������־��ѧ�������</a>
			</p>
		</div>
	</div>

	<logic:equal name="sfksq" value="-1">
		<p align="center">
			<font color="red">Ŀǰ��������ʱ�䷶Χ�ڣ��ݲ��������룡</font>
		</p>
	</logic:equal>
	<html:form action="n05_xszz.do?method=gjlzjxj1sq" method="post">
		<input type="hidden" id="url" name="url"
			value="/n05_xszz.do?method=gjlzjxj1sq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc" />
		<input type="hidden" id="pkVal" name="pkVal"
			value="<bean:write name="rs" property="pkVal" />">

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
	         			alert("��ͨ��ѧУ��ˣ��������룡");
	         		</script>
			</logic:match>
		</logic:present>
		<div class="tab">
				<table width="99%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="10">
							<span>������־��ѧ�������</span>
						</th>
					</tr>
				</thead>
				<tbody>
			<tr>
				<logic:equal name="userOnLine" value="teacher" scope="session">
					<th width="16%">
						<font color="red">*</font>ѧ��
					</th>
					<td  colspan="4"  width="34%">
						<html:text name='rs' property="xh" styleId="xh" onblur="dctStuXh()" 
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<logic:notEqual name="type" value="modi">
								<logic:equal name="sfKns" value="no">
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:equal>
								<logic:notEqual name="sfKns" value="no">
									<button onclick="showTopWin('/xgxt/stu_info.do?kns=yes',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:notEqual>
							</logic:notEqual>
					</td>
				</logic:equal>
				<logic:equal name="userOnLine" value="student" scope="session">
					<th width="16%">
						<font color="red">*</font>ѧ��
					</th>
					<td  colspan="4"  width="34%">
						<input type="text" id="xh" name="xh" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xh" />" readonly="true">
					</td>
				</logic:equal>
				<th width="16%">
						����
				</th>
				<td  colspan="4"  width="34%">
					<input type="text" readonly="readonly" id="xm" name="xm"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xm"/>" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>
					�Ա�
				</th>
				<td  colspan="4">
					<input type="text" id="xb" readonly="readonly" name="xb"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xb"/>">
				</td>
				<th>
					<div align="center">
						���֤��
					</div>
				</th>
				<td  colspan="4" >
					<input type="text" id="sfzh" name="sfzh" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="sfzh"/>">
				</td>
			</tr>
			<tr>
				<th>
						����
				</th>
				<td  colspan="4" >
					<input type="text" id="mzmc" readonly="readonly" name="mzmc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="mzmc"/>">
				</td>
				<th>
						������ò
				</th>
				<td  colspan="4" >
					<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zzmmmc"/>">
				</td>
			</tr>
			<tr>
				<th>
					��������
				</th>
				<td  colspan="4" >
					<input type="text" id="csrq" readonly="readonly" name="csrq"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="csrq"/>">
				</td>
				<th>
						��ѧʱ��
				</th>
				<td  colspan="4" >
					<input type="text" id="rxrq" name="rxrq" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="rxrq"/>">
				</td>
			</tr>
			<tr>
				<th>
					�꼶
				</th>
				<td  colspan="4" >
					<input type="text" id="nj" readonly="readonly" name="nj"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="nj"/>">
				</td>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />����
				</th>
				<td  colspan="4" >
					<input type="text" id="xymc" name="xymc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xymc"/>">
				</td>
			</tr>
			<tr>
				<th>
					רҵ����
				</th>
				<td  colspan="4" >
					<input type="text" id="zymc" readonly="readonly" name="zymc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zymc"/>">
				</td>
				<th>
						�༶����
				</th>
				<td  colspan="4" >
					<input type="text" id="bjmc" name="bjmc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="bjmc"/>">
				</td>
			</tr>
			<tr>
				<th>
						ѧ��
				</th>
				<td  colspan="4" >
					<input type="text" id="xn" readonly="readonly" name="xn"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xn"/>">
				</td>
				<th>
						��ϵ�绰
				</th>
				<td  colspan="4" >
					<input type="text" id="lxdh" name="lxdh" maxlength="20"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxdh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<th>
					������ֽ���
				</th>
				<td colspan="9">
					<html:textarea name="rs" property="chhzjl" rows='3'
						style="width:100%" onblur="yzdx(this,'chhzjl')" />
					<input type="hidden" id="chhzjl" name="chhzjl" value="">
				</td>
			</tr>
			<tr>
				<th>
					��ͥ����
				</th>
				<td  colspan="4">
					<html:select name="rs" property="jthk">
						<html:option value=""></html:option>
						<html:option value="����">����</html:option>
						<html:option value="ũ��">ũ��</html:option>
					</html:select>
				</td>
				<th>
						��ͥ�˿�����
				</th>
				<td  colspan="4" >
					<input type="text" id="jtzrks" name="jtzrks" maxlength="3"
						style="width:100%;heigh:100%" onblur="changeSr()"
						value="<bean:write name="rs" property="jtzrks"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<th>
					�˾�������
				</th>
				<td  colspan="4">
					<input type="text" id="rjysr" name="rjysr" maxlength="6"
						style="width:100%;heigh:100%" onblur="changeSr()"
						value="<bean:write name="rs" property="rjysr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<th>
					��ͥ��������
				</th>
				<td  colspan="4" >
					<input type="text" id="jtyzsr" name="jtyzsr" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtyzsr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<th>
					������Դ
				</th>
				<td  colspan="4" >
					<input type="text" id="srly" name="srly" maxlength="50"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="srly"/>">
				</td>
				<th>
					��������
				</th>
				<td  colspan="4" >
					<input type="text" id="yzbm" name="yzbm" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="yzbm"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<th>
					��ͥסַ
				</th>
				<td colspan="9">
					<input type="text" id="jtzz" name="jtzz" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtzz"/>">
				</td>
			</tr>
			<tr>
				<th>
					ѧϰ�ɼ�
				</th>
				<td colspan="9">
					<html:textarea name="rs" property="xxcj" rows='5'
						style="width:100%" onblur="yzdx(this,'xxcj')" />
					<input type="hidden" id="xxcj" name="xxcj" value="">
				</td>
			</tr>
			<tr>
				<th>
					��������
				</th>
				<td colspan="9">
					<html:textarea name="rs" property="sqly" rows='10'
						style="width:100%" onblur="yzdx(this,'sqly')" />
					<input type="hidden" id="sqly" name="sqly" value="">
				</td>
			</tr>
			</tbody>
			<tfoot>
					<tr>
						<td colspan="10">
							<div class="bz">
								"
								<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button id="buttonSave" onclick="yz();">
									�ύ����
								</button>
								&nbsp;
								<button onclick="toPrintOut();">
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
	<logic:notEmpty name="rs">
	<logic:empty name="rs" property="xh">
		<script language="javascript">
			alert("�������ѧ�Ų�����,����������!");
			$("xh").focus();
		</script>
	</logic:empty>
	</logic:notEmpty>
	<logic:notEmpty name="rs" property="xh">
	<logic:notEqual name="sfksq" value="-1">
		<logic:equal name="isKns" value="false">
			<script language="javascript">
			  $("buttonSave").disabled=true;
			  alert("����Ŀǰ���������������ܽ�������!");
			</script>
		</logic:equal>
	</logic:notEqual>
	</logic:notEmpty>
</html>
