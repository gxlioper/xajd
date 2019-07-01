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
			var jtjxxqkknzy = document.getElementById('jtjxxqkknzy').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(jtjxxqkknzy != null){
	         	if(jtjxxqkknzy.replace(/[^\u0000-\u00ff]/g, "**").length > 4000){	         
	          		 alert("��ͥ��ѧϰ�������ժҪ���ܴ���4000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/n05_xszz.do?method=wszxj1sqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/n05_xszz.do?method=wszxj1sqb";
			document.forms[0].submit();
		}
		function chengJe(){
			var xmje = document.getElementById('xmje').value;
			
			document.getElementById('sqje').value = xmje;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a> ѧ������ - <bean:write name="xmmc"/></a>
			</p>
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<p align="center">
			<font color="red">Ŀǰ��������ʱ�䷶Χ�ڣ��ݲ��������룡</font>
		</p>
	</logic:equal>
	<html:form action="n05_xszz.do?method=wszxj1sq" method="post">
		<input type="hidden" id="url" name="url"
			value="/n05_xszz.do?method=wszxj1sq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc" />
		<input type="hidden" id="pkVal" name="pkVal"
			value="<bean:write name="rs" property="pkVal" />">
		<input type="hidden" id="zxjdm" name="zxjdm"
			value="<bean:write name="rs" property="zxjdm" />">
		<input type="hidden" id="xmdm" name="xmdm"
			value="<bean:write name="xmdm" />">

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<logic:present name="isPASS">
					<logic:match value="is" name="isPASS">
						<script language="javascript">
			         			alert("��ͨ����ˣ��������룡");
			         		</script>
					</logic:match>
				</logic:present>
				<logic:notPresent name="isPASS">
					<script language="javascript">
		         	alert("����ʧ�ܣ�");
		         	</script>
				</logic:notPresent>
				
			</logic:match>
		</logic:present>
		
		<div class="tab">
				<table width="99%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="10">
							<span><bean:write name="xmmc"/></span>
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
					<th align="center" width="16%">
						<font color="red">*</font>ѧ��
					</th>
					<td colspan="4" width="34%">
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
				<td  colspan="4" >
					<input type="text" id="xb" readonly="readonly" name="xb"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xb"/>">
				</td>
				<th>
					���֤��
				</th>
				<td  colspan="4" >
					<input type="text" id="sfzh" name="sfzh" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="sfzh"/>">
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
				<td>
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
					��������
				</th>
				<td  colspan="4" >
					<input type="text" id="csrq" readonly="readonly" name="csrq"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="csrq"/>">
				</td>
				<th >
					ѧ��
				</th>
				<td  colspan="4" >
					<input type="text" id="xn" readonly="readonly" name="xn"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xn"/>">
				</td>
			</tr>
			<tr>
				<th>
					����
				</th>
				<td  colspan="4" >
					<input type="text" id="jg" name="jg" maxlength="25"
						style="width:100%;heigh:100%" 
						value="<bean:write name="rs" property="jg"/>">
				</td>
				<th>
					Ʒѧ���
				</th>
				<td  colspan="4" >
					<input type="text" id="pxqk" name="pxqk" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="pxqk"/>">
				</td>
			</tr>
			<tr>
				<th>
					��ͥ��ϸ��ַ
				</th>
				<td  colspan="4" >
					<input type="text" id="jtxxdz" name="jtxxdz" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtxxdz"/>">
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
						������
				</th>
				<td  colspan="4" >
					<html:select property="xmje" onchange="chengJe()" value="${rs.sqje}">
						<html:option value=""></html:option>
						<html:options collection="wszxjJeList" property="xmje"
									labelProperty="zxjmc" />
					</html:select>
					&nbsp;&nbsp;
					<input type="hidden" id="sqje" name="sqje" readonly="readonly"
						style="width:80px;heigh:100%"
						value="<bean:write name="rs" property="sqje"/>">
				</td>
				<th>
					&nbsp;
				</th>
				<td  colspan="4" >
					&nbsp;
				</td>
			</tr>
			<tr>
				<th>
					��ͥ��ѧϰ<br />�������ժҪ
				</th>
				<td colspan="9">
					<html:textarea name="rs" property="jtjxxqkknzy" rows='10'
						style="width:100%" onblur="yzdx(this,'jtjxxqkknzy')" />
					<input type="hidden" id="jtjxxqkknzy" name="jtjxxqkknzy" value="">
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
