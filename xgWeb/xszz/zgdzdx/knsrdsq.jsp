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
			var xscssqly = document.getElementById('xscssqly').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(xscssqly != null){
	         	if(xscssqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("ѧ�������������ɲ��ܳ���1000���ַ���");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=knsrdsqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=knsrdsqb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur" id="jd">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>������ - �������϶�����</a>
		</p>
	</div>
	<logic:present name="sfksq">
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڻ�û����д��ͥ������������
			</p>
		</center>
	</logic:equal>
	</logic:present>
		<html:form action="zgdzdx_xszz.do?method=knsrdsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgdzdx_xszz.do?method=knsrdsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="tjdc" name="tjdc"
				value="<bean:write name="rs" property="tjdc" />">
			<input type="hidden" id="csly" name="csly"
				value="<bean:write name="rs" property="csly" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">

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
				<thead>
					<tr>
	    				<th colspan="6"><span>�������϶�����</span></th>
	    			</tr>
				 </thead>
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
								class="btn_01" id="buttonFindStu">
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
						�Ա�
					</th>
					<td>
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<th>
						ѧ��
					</th>
					<td>
						<input type="text" id="xn" name="xn" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xn"/>">
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
						��ͥ�˾�������
					</th>
					<td>
						<input type="text" id="jtrjnsr" name="jtrjnsr" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrjnsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						��У��ϵ�绰
					</th>
					<td>
						<input type="text" id="zxlxdh" name="zxlxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zxlxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						��ͥ��ַ
					</th>
					<td colspan="3">
						<input type="text" id="jtdz" name="jtdz" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdz"/>">
					</td>
				</tr>
				<tr>
					<th>
						��������
					</th>
					<td>
						<input type="text" id="ssxs" name="ssxs" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ssxs"/>">
					</td>
					<th>
						�Ƿ����������
					</th>
					<td align="center">
						<logic:present name="rs" property="sfdzzzq">
							<logic:match value="��" name="rs" property="sfdzzzq">
								<input type="radio" name="sfdzzzq" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfdzzzq" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfdzzzq">
								<input type="radio" name="sfdzzzq" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfdzzzq" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfdzzzq">
							<input type="radio" name="sfdzzzq" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfdzzzq" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<th>
						ѧ��������������
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="xscssqly" rows='8' style='width:100%' onblur="yzdx(this,'xscssqly')"/>
						<input type="hidden" id="xscssqly" name="xscssqly" value="">
					</td>
				</tr>
				</tbody>
				<logic:equal name="sfksq" value="1">
				<tfoot>
					 <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<logic:notEqual name="isQuery" value="is">
			          	<button type="button"  onClick="yz();">
							�ύ����
						</button>
						<button type="button"  onClick="toPrintOut();">
							�� ӡ
						</button>	
						</logic:notEqual>
						<button type="button" onclick="window.close();return false;" id="buttonClose">
							�� ��
						</button>		           
			          </div>
			          </td>
			      </tr>
				</tfoot>
				</logic:equal>
				<logic:notEqual name="sfksq" value="1">
				<tfoot>
					<tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
						<logic:equal name="isQuery" value="is">
							<button type="button" onclick="window.close();return false;" id="buttonClose">
								�� ��
							</button>
						</logic:equal>			           
			          </div>
			          </td>
			      </tr>
				</tfoot>
				</logic:notEqual>
			</table>
		</html:form>
</body>
</html>
