<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var userType = document.getElementById('userType').value;
			var xxsh = document.getElementById('xxsh').value;
			var csly = document.getElementById('csly').value;
			if(("δ���" != xxsh ) && (userType != "admin")){
				alert("ѧУ����ˣ��������޸���˽��!");
	          	return false;
			}
			if(csly != null){
	         	if(csly.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("�������ɲ��ܴ���600���ַ�");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/zgdzdx_xszz.do?method=knsrdshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
	</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		
		<html:form action="/zgdzdx_xszz.do?method=knsrdshSave" method="post">
			<div class="tab_cur">
				<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>
				������ - ��� - �������϶���� - �������
				</a>
				</p>
			</div>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	Close();
	         	window.dialogArguments.document.getElementById('search_go').click()
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							�������϶�
						</td>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th width="16%">
						ѧ��
					</th>
					<td  width="34%">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
					</td>
					<th width="16%">
						<div align="center">
							����
						</div>
					</th>
					<td width="34%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<th>
						�Ա�
					</th>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<th>
							ѧ��
					</th>
					<td>
						<bean:write name="rs" property="xn"/>
						<input type="hidden" id="xn" name="xn"
							value="<bean:write name='rs' property="xn" />" />
					</td>
				</tr>
				<tr>
					<th>
							����
					</th>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>
					<th>
							���֤��
					</th>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
				</tr>
				<tr>
					<th>
						�꼶
					</th>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
					<th>
							<bean:message key="lable.xsgzyxpzxy" />����
					</th>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<th>
							רҵ����
					</th>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<th>
							�༶����
					</th>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<th>
							 ��ͥ�˾�������
					</th>
					<td>
						<bean:write name="rs" property="jtrjnsr"/>
					</td>
					<th>
							��У��ϵ�绰
					</th>
					<td>
						<bean:write name="rs" property="zxlxdh"/>
					</td>
				</tr>
				<tr>
					<th>
							 ��ͥ��ַ
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jtdz"/>
					</td>
				</tr>
				<tr>
					<th>
							��������
					</th>
					<td>
						<bean:write name="rs" property="ssxs"/>
					</td>
					<th>
							�Ƿ����������
					</th>
					<td>
						<bean:write name="rs" property="sfdzzzq"/>
					</td>
				</tr>
				<tr>
					<th>
							ѧ��������������
					</th>
					<td colspan="3">
						<bean:write name="rs" property="xscssqly"/>
					</td>
				</tr>
				<tr>
					<th>
							����ʱ��
					</th>
					<td>
						<bean:write name="rs" property="sqsj" />
					</td>
					<logic:equal name="userType" value="xy">
						<th>
								�Ƽ�����
						</th>
						<td>
							<html:select name="rs" property="tjdc">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
						</td>
					</logic:equal>
					<logic:equal name="userType" value="admin">
						<th>
								�Ƽ�����
						</th>
						<td>
							<html:select name="rs" property="tjdc">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</logic:equal>
				</tr>
				<logic:equal name="userType" value="xy">
					<tr>
						<th>
								<bean:message key="lable.xsgzyxpzxy" />���
						</th>
						<td>
							<html:select name="rs" property="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="2">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
					<tr>
						<th>
								<bean:message key="lable.xsgzyxpzxy" />���
						</th>
						<td>
							<html:select name="rs" property="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<th>
								ѧУ���
						</th>
						<td>
							<html:select name="rs" property="xxsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
				</logic:equal>
				<tr>
					<th>
							��������
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="csly" rows='5'
							style="width:100%" onblur="yzdx(this,'csly')" />
						<input type="hidden" id="csly" name="csly" value="">
					</td>
				</tr>
				</tbody>

				 <tfoot>
				      <tr>
				        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
				          	<logic:equal name="shqx" value="1">
							<button type="button" name="����" onclick="yz()" id="buttonSave">
								�� ��
							</button>
							</logic:equal>
							<logic:equal name="shqx" value="-1">
							<button type="button"   onclick="yz()"  disabled="disabled"
								id="buttonSave">
								�� ��
							</button>
							</logic:equal>
							<button type="button"   onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" 
								id="buttonClose">
								�� ��
							</button>					           
				          </div>
				          </td>
				      </tr>
				    </tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>
