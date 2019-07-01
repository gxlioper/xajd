<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">

<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<base target="_self" />
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
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
			var hkczh = document.getElementById('hkczh').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((hkczh == null) || (hkczh == "")){
				alert("�����ʺŲ���Ϊ��!");
				return false;
			}
			
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=zqhhkxysqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=zqhhkxysqb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>${title }</a>
		</p>
	</div>
		<html:form action="zgdzdx_xszz.do?method=zqhhkxysq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgdzdx_xszz.do?method=zqhhkxysq" />
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
			<logic:present name="isNull">
				<logic:match value="is" name="isNull">
					<script language="javascript">
	         			alert("��û������չ����Ϣ��");
	         		</script>
				</logic:match>
			</logic:present>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4">
			        		<span>
			        			<logic:equal name="sfksq" value="-1">
									���ڲ�������ʱ����!
								</logic:equal>
								<logic:notEqual name="sfksq" value="-1">
			        				չ�ں󻹿�Э��
			        			</logic:notEqual>
			        		</span>
			        	</th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4">
			        	<logic:equal name="sfksq" value="1">
						
							<div class="bz" align="left">"<span class="red">*</span>"Ϊ������</div>
							<div class="btn" id="btn" >
							<logic:equal name="rs" property="xyshjg" value="ͨ��">
								<button type="button"  onclick="yz();" disabled="disabled">
									��&nbsp;&nbsp;&nbsp;&nbsp;��
								</button>
							</logic:equal>
							<logic:notEqual name="rs" property="xyshjg" value="ͨ��">
								<button type="button"  onclick="yz();">
									��&nbsp;&nbsp;&nbsp;&nbsp;��
								</button>
							</logic:notEqual>
							<logic:notEqual name="sfkdy" value="0">
								<button type="button"  onclick="toPrintOut();">
									��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
								</button>
							</logic:notEqual>
							<logic:equal name="sfkdy" value="0">
								<button type="button"  onclick="toPrintOut();" disabled="disabled">
									��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
								</button>
							</logic:equal>	
							</div>
						</logic:equal>
			      </tr>
			    </tfoot>
				<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th align="center" width="16%">
							<font color="red">*</font>ѧ��
						</th>
						<td align="left" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th align="center" width="16%">
							<font color="red">*</font>ѧ��
						</th>
						<td align="left" width="34%">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
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
							�������
					</th>
					<td>
						<input type="text" id="bjmc" readonly="readonly" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
					<th>
							���֤��
					</th>
					<td>
						<input type="text" id="sfzh" readonly="readonly" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<th>
							��ͥ��ַ
					</th>
					<td>
						<input type="text" id="jtxxzz" readonly="readonly" name="jtxxzz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtxxzz"/>">
					</td>
					<th>
							��ͥ�ʱ�
					</th>
					<td>
						<input type="text" id="jtyb" name="jtyb" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyb"/>">
					</td>
				</tr>
				<tr>
					<th>
							������λ
					</th>
					<td>
						<input type="text" id="gzdw" name="gzdw" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gzdw"/>">
					</td>
					<th>
							��λ�ʱ�
					</th>
					<td>
						<input type="text" id="dwyb" name="dwyb" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dwyb"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
							��ϵ�绰
					</th>
					<td>
						<input type="text" id="yddh" maxlength="20" name="yddh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yddh"/>">
					</td>
					<th>
							��ͬ��
					</th>
					<td>
						<input type="text" id="htbh" name="htbh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="htbh"/>">
					</td>
				</tr>
				<tr>
					<th>
							ʵ�ʷ��Ž��
					</th>
					<td>
						<input type="text" id="sjffje" readonly="readonly" name="sjffje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sjffje"/>">
						<input type="hidden" id="sjffje_dx" name="sjffje_dx"
								value="<bean:write name="rs" property="sjffje_dx" />">
					</td>
					<th>
							<font color="red">*</font>�����˺�<br />
							<font color="red">(����д���п��·�һ������)</font>
					</th>
					<td>
						<input type="text" id="hkczh" name="hkczh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkczh"/>">
					</td>
				</tr>
				<tr>
					<th>
							չ������
					</th>
					<td>
						<input type="text" id="zqnx" readonly="zqnx" name="sjffje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zqnx"/>">
					</td>
					<th>
							չ�ں󻹿�<br />��ֹ����
					</th>
					<td>
						<input type="text" id="zqrq" name="zqrq" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zqrq"/>">
					</td>
				</tr>
				</tbody>
			</table>
			</div>
	</html:form>
	</body>
</html>
