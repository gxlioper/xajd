<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript">
function plsh(){
	var userType = document.getElementById('userType').value;
	var shType = document.getElementById('shType').value;
	var shjg = document.getElementById('shjg').value;
	
	if (shType == null || shType == ""){
		alert("��ѡ���������!");
		return false;
	}
	if (shType == null || shType == ""){
		alert("��ѡ����˽��!");
		return false;
	}
	
	
	if (userType != "xx" && userType != "admin"){
		if (!confirm("�¼��û������޸���ͨ���ϼ���˵����ݣ�ȷ��Ҫ�޸���ѡ��¼��")){
			return false;
		}
	}
	refreshForm('/xgxt/zgdzdx_xszz.do?method=knsrdplsh&doType=save');
}

</script>
	</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<html:form action="/zgdzdx_xszz.do?method=knsrdplsh" method="post">
			<!-- ���� -->
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>������ - ��� - �������϶��������</a>
				</p>
			</div>
			<!-- ���� end-->
			<input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="cbVal" name="cbVal"
				value="<bean:write name="rs" property="cbVal"/>" />
			<logic:present name="over">
				<logic:match value="over" name="over">
					<script language="javascript">
	         			alert("���������ɣ�");
	         		</script>
				</logic:match>
			</logic:present>
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>����������</span>
						</th>
					</tr>
				</thead>
				<tbody>		
				<tr>
					<th align="right" width="50%">
						�������
					</th>
					<td align="left" width="50%">
						<logic:equal name="userType" value="xy">
							<html:select name="rs" property="shType">
								<html:option value="1">�Ƽ�����</html:option>
								<html:option value="2"><bean:message key="lable.xsgzyxpzxy" />���</html:option>
							</html:select>
						</logic:equal>
						<logic:notEqual name="userType" value="xy">
							<html:select name="rs" property="shType">
								<html:option value="1">�Ƽ�����</html:option>
								<html:option value="2"><bean:message key="lable.xsgzyxpzxy" />���</html:option>
								<html:option value="3">ѧУ���</html:option>
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th align="right" width="50%">
						��˽��
					</th>
					<td align="left" width="50%">
						<html:select name="rs" property="shjg">
							<html:option value="һ������">һ������</html:option>
							<html:option value="����">����</html:option>
							<html:option value="��������">��������</html:option>
							<html:option value="������">������</html:option>
						</html:select>
					</td>
				</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<logic:notEqual name="doType" value="view">
									<button type="button" id="buttonSave" onclick="plsh();">
										�� ��
									</button>
								</logic:notEqual>
								&nbsp;&nbsp;
								<button type="button" id="buttonClose" 
									onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
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
