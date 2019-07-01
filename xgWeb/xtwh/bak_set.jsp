<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			function changeType(){
				var sysType = document.getElementById("db_systemType").value;
				if(sysType == "windows"){
					document.getElementById("bak_path").value = "D:/data_bak";
				}else{
					document.getElementById("bak_path").value = "/home/ora_bak";
				}
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ϵͳά�� - ϵͳ���� - ϵͳ����</a>
			</p>
		</div>
	
		<html:form action="/data_backup" method="post">
		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ϵͳ����</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="����" onclick="refreshForm('/xgxt/bak_set_save.do')">
										����
									</button>
									<button type="button" name="�ر�" onclick="Close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								����ϵͳ����
							</th>
							<td width="80%">
								<html:select name="rs" property="db_systemType" styleId="db_systemType" onchange="changeType()">
									<html:option value="windows">Windows</html:option>
									<html:option value="linux">Linux</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								���ݿ��û���
							</th>
							<td>
								<html:text name="rs" property="db_user" styleId="db_user" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								���ݿ�����
							</th>
							<td>
								<html:password name="rs" property="db_pwd" styleId="db_pwd" style="width:150px"></html:password>
							</td>
						</tr>
						<tr>
							<th>
								���������
							</th>
							<td>
								<html:text name="rs" property="db_sid" styleId="db_sid" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								����·��
							</th>
							<td>
								<html:text name="rs" property="bak_path" styleId="bak_path" style="width:150px"></html:text>
								<font color="red">��: d:/data_bak</font>
							</td>
						</tr>
					</tbody>
					</table>
			</div>
		</html:form>
		<logic:equal value="ok" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��!");
				Close();
			</script>
		</logic:equal>
	</body>
</html>
