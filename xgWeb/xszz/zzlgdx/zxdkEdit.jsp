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
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			document.forms[0].action = "/xgxt/zzlgdx_xszz.do?method=zxdkEditSave";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<html:form action="zzlgdx_xszz.do?method=zxdkEdit" method="post">
		<input type="hidden" id="url" name="url"
			value="/zzlgdx_xszz.do?method=zxdkEdit" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-zymc-bjmc-nj-sfzh-mzmc-zzmmmc" />
		<input type="hidden" id="guid" name="guid"
			value="<bean:write name="rs" property="guid" />">
		<input type="hidden" id="act" name="act"
			value="<bean:write name="act" />">

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
		</logic:present><div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ������ - ��Ϣά�� - ��ѧ���� </a>
				</p>
			</div>
		<div class="tab">
			<table width="100%"  border="0" class="formlist">
			 <thead>
   				<tr>
       				<th colspan="6"><span>�ֶ�ά��</span></th>
       			</tr>
  			</thead>
  			<tbody>
			<tr>
				<logic:equal name="userOnLine" value="teacher" scope="session">
					<th width="16%">
						<font color="red">*</font>ѧ��
					</th>
					<td align="left" width="34%">
						<html:text name='rs' property="xh" styleId="xh" readonly="readonly"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</td>
				</logic:equal>
				<logic:equal name="userOnLine" value="student" scope="session">
					<th width="16%">
						<font color="red">*</font>ѧ�ţ�
					</th>
					<td align="left" width="34%">
						<input type="text" id="xh" name="xh" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xh" />" readonly="true">
					</td>
				</logic:equal>
				<th width="16%">
						����
				</th>
				<td width="34%">
					<input type="text" readonly="readonly" id="xm" name="xm"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xm"/>" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>
					�Ա�
				</th>
				<td>
					<input type="text" id="xb" readonly="readonly" name="xb"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xb"/>">
				</td>
				<th>
					���֤��
				</th>
				<td>
					<input type="text" id="sfzh" name="sfzh" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="sfzh"/>">
				</td>
			</tr>
			<tr>
				<th>
					�꼶
				</th>
				<td>
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
				<td>
					<input type="text" id="zymc" readonly="readonly" name="zymc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zymc"/>">
				</td>
				<th>
					�༶����
				</th>
				<td>
					<input type="text" id="bjmc" name="bjmc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="bjmc"/>">
				</td>
			</tr>
			<tr>
				<th>
					����
				</th>
				<td>
					<input type="text" id="mzmc" readonly="readonly" name="mzmc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="mzmc"/>">
				</td>
				<th>
					������ò
				</th>
				<td>
					<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zzmmmc"/>">
				</td>
			</tr>
			<tr>
				<th>
					��ϵ��ʽ
				</th>
				<td>
					<input type="text" id="lxfs" name="lxfs" maxlength="20"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxfs"/>"
						onkeyup="value=value.replace(/[^\d]/g,'')"
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<th>
					��ͥסַ
				</th>
				<td>
					<input type="text" id="jtzz" name="jtzz" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtzz"/>">
				</td>
			</tr>
			<tr>
				<th>
					��������
				</th>
				<td>
					<input type="text" id="yzbm" name="yzbm" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="yzbm"/>"
						onkeyup="value=value.replace(/[^\d]/g,'')"
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<th>
					��ͥ�绰
				</th>
				<td>
					<input type="text" id="jtdh" name="jtdh" maxlength="20"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtdh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'')"
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<th>
					�����ͬ��
				</th>
				<td>
					<input type="text" id="dkhth" name="dkhth" maxlength="50"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dkhth"/>">
				</td>
				<th>
					������
				</th>
				<td>
					<input type="text" id="dkje" name="dkje" maxlength="20"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dkje"/>"
						onkeyup="value=value.replace(/[^\d]/g,'')"
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<th>
					��ͬ�������
				</th>
				<td>
					<input type="text" readonly style="cursor:hand;width:100%"
						onclick="return showCalendar('htdkffr','y-mm-dd');"
						value="<bean:write name='rs' property="htdkffr" />" name="htdkffr"
						id="htdkffr" />
				</td>
				<th>
					��ͬ�������
				</th>
				<td>
					<input type="text" readonly style="cursor:hand;width:100%"
						onclick="return showCalendar('htdkdqr','y-mm-dd');"
						value="<bean:write name='rs' property="htdkdqr" />" name="htdkdqr"
						id="htdkdqr" />
				</td>
			</tr>
			<tr>
				<th>
					���ʽ
				</th>
				<td>
					<input type="text" id="hkfs" name="hkfs" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="hkfs"/>">
				</td>
				<th>
					�����ʺ�
				</th>
				<td>
					<input type="text" id="hkzh" name="hkzh" maxlength="40"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="hkzh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'')"
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			</tbody>
			 <tfoot>
		      <tr>
		        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
					<button type="button" name="����" onclick="yz();" id="buttonSave">
						�� ��
					</button>
					<button type="button"  onclick="Close();return false;"  id="buttonClose"
						 id="buttonModi" >
						�� ��
					</button>					           
		          </div>
		          </td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
</body>
</html>
