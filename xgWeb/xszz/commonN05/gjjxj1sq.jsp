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
			var hjqk = document.getElementById('hjqk').value;
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(hjqk != null){
	         	if(hjqk.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("��������ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("�������ɲ��ܴ���2000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/n05_xszz.do?method=gjjxj1sqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/n05_xszz.do?method=gjjxj1sqb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a> ѧ������ - ���ҽ�ѧ��</a>
				</p>
			</div>
		</div>
	<logic:equal name="sfksq" value="-1">
		<p align="center">
			<font color="red">Ŀǰ��������ʱ�䷶Χ�ڣ��ݲ��������룡</font>
		</p>
	</logic:equal>
		<html:form action="n05_xszz.do?method=gjjxj1sq" method="post">
			<input type="hidden" id="url" name="url" value="/n05_xszz.do?method=gjjxj1sq" />
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
							<span>���ҽ�ѧ��</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th  width="16%" >
							<font color="red">*</font>ѧ��
						</th>
						<td  colspan="4" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								onblur="dctStuXh()" 
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
						<th  width="16%">
							<font color="red">*</font>ѧ��
						</th>
						<td colspan="4"  width="34%">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<th width="16%">
							����
					</th>
					<td  colspan="4" width="34%">
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
						���֤��
					</th>
					<td  colspan="4">
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<th>
						����
					</th>
					<td  colspan="4"  >
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<th>
							������ò
					</th>
					<td  colspan="4"  >
						<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
				</tr>
				<tr>
					<th>
						 ��������
					</th>
					<td  colspan="4"  >
						<input type="text" id="csrq" readonly="readonly" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
					<th>
						��ѧ����
					</th>
					<td   colspan="4">
						<input type="text" id="rxrq" name="rxrq" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxrq"/>">
					</td>
				</tr>
				<tr>
					<th>
						�꼶
					</th>
					<td   colspan="4">
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />����
					</th>
					<td   colspan="4">
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<th>
						רҵ����
					</th>
					<td   colspan="4">
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<th>
						�༶����
					</th>
					<td   colspan="4">
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<th>
						ѧ��
					</th>
					<td   colspan="4"  >
						<input type="text" id="xn" name="xn" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xn"/>">
					</td>
					<th>
							��ϵ�绰
					</th>
					<td  colspan="4">
						<input type="text" id="lxdh" maxlength="20" name="lxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						��ѧ����޿γ���
					</th>
					<td  colspan="4">
						<input type="text" id="gxnbxkcs" maxlength="3" name="gxnbxkcs"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gxnbxkcs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						����γ���
					</th>
					<td  colspan="4">
						<input type="text" id="yxkcs" maxlength="3" name="yxkcs"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yxkcs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						���ÿγ���
					</th>
					<td  colspan="4">
						<input type="text" id="lhkcs" maxlength="3" name="lhkcs"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lhkcs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
							�ɼ�����<br />
							(רҵ���꼶)
					</th>
					<td  colspan="4">
						<input type="text" id="cjpm" name="cjpm" maxlength="15"
							style="width:70%;heigh:100%"
							value="<bean:write name="rs" property="cjpm"/>">������/��������
					</td>
				</tr>
				<tr>
					<th>
						�ۺϿ����ɼ����֣�
					</th>
					<td  colspan="4">
						<input type="text" id="zhkpcj" maxlength="6" name="zhkpcj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zhkpcj"/>">
					</td>
					<th>
						�ۺϿ�������<br />
						(רҵ���꼶)
					</th>
					<td  colspan="4">
						<input type="text" id="zhkppm" name="zhkppm" maxlength="15"
							style="width:70%;heigh:100%"
							value="<bean:write name="rs" property="zhkppm"/>">������/��������
					</td>
				</tr>
				<!-- ������һ -->
				
				<logic:equal name="xxdm" value="13742" >
				<tr>
					<td colspan="10">
					<table>
						<tr>
							<th rowspan="5" width="16%"><center>��ѧ�ڼ���<br>Ҫ�����</center></td>
							<th>
								����
							</th>
							<th>
								��������
							</th>
							<th>
								�佱��λ
							</th>
						</tr>
						<tr>
							<td>
								<html:text name="rs" property="hjsj1" styleId="hjsj1" 
										onclick="return showCalendar('hjsj1','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" style="width:100%;heigh:100%"/>
							</td>
							<td>
								<html:text name="rs" property="hjmc1" style="width:100%;heigh:100%"/>
							</td>
							<td>
								<html:text name="rs" property="bjdw1"	style="width:100%;heigh:100%"/>
							</td>
						</tr>
						<tr>
							<td>
								<html:text name="rs" property="hjsj2" styleId="hjsj2" 
										onclick="return showCalendar('hjsj2','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" style="width:100%;heigh:100%"/>
							</td>
							<td>
								<html:text name="rs" property="hjmc2"	style="width:100%;heigh:100%"/>
							</td>
							<td>
								<html:text name="rs" property="bjdw2" style="width:100%;heigh:100%"/>
							</td>
						</tr>
						<tr>
							<td>
								<html:text name="rs" property="hjsj3" styleId="hjsj3" 
										onclick="return showCalendar('hjsj3','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" style="width:100%;heigh:100%"/>
							</td>
							<td>
								<html:text name="rs" property="hjmc3"	style="width:100%;heigh:100%" />
							</td>
							<td>
								<html:text name="rs" property="bjdw3"	style="width:100%;heigh:100%"/>
							</td>
						</tr>
						<tr>
							<td>
								<html:text name="rs" property="hjsj4" styleId="hjsj4" 
										onclick="return showCalendar('hjsj4','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" style="width:100%;heigh:100%"/>
							</td>
							<td>
								<html:text name="rs" property="hjmc4" style="width:100%;heigh:100%"/>
							</td>
							<td>
								<html:text name="rs" property="bjdw4"	style="width:100%;heigh:100%" />
							</td>
				</tr>
				</table>
				</td>
				</tr>
				</logic:equal>
				<!-- �Ͼ���ʦ -->
				<logic:equal name="xxdm" value="8001">
					<tr>
						<th>
							�Ƿ�ͱ�
						</th>
						<td>
							<html:select name="rs" property="sfdb">
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
						<th>
							
						</th>
						<td>
							
						</td>
					</tr>
				</logic:equal>
				<tr>
					<th>
						�����
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="hjqk" rows='4' style="width:100%" onblur="yzdx(this,'hjqk')"/>
						<input type="hidden" id="hjqk" name="hjqk" value="">
					</td>
				</tr>
				<tr>
					<th>
						Ժ������(��)
					</th>
					<td colspan="4">
						<input type="text" id="yjjl" maxlength="4" name="yjjl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yjjl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						У������(��)
					</th>
					<td colspan="4">
						<input type="text" id="xjjl" name="xjjl" maxlength="4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xjjl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						ʡ�����Ͻ���(��)
					</th>
					<td colspan="4">
						<input type="text" id="sjjl" maxlength="4" name="sjjl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sjjl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						����ʱ��
					</th>
					<td colspan="4">
						<input type="text" id="sqsj" name="sqsj" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqsj"/>">
					</td>
				</tr>
				<tr>
					<th>
							��������<br />
							(ȫ�淴ӳ�����������)
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="sqly" rows='10' style="width:100%" onblur="yzdx(this,'sqly')"/>
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
