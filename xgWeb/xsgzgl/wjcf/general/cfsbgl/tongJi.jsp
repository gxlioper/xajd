<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>	
		
		<script type="text/javascript">

		//�ύ
		function tijiao(){
			confirmInfo("�˲��������ύ���ͨ���ļ�¼���ύ����˲������޸ģ��Ƿ�ȷ����",function(tag){
				if(tag=="ok"){
					refreshForm('general_wjcf_cfsh_ajax.do?method=tjSh');
				}
			});
		}

		
		</script>
	</head>
	<body >
		<html:form action="/general_wjcf_cfsh_ajax" method="post">
					<input type="hidden" name="url" id="url" value="general_wjcf_cfsb_ajax.do?method=zjWjcf">	
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">	
					<input type="hidden" name="message" id="message" value="${message }">	
					<input type="hidden" name="doType" id="doType"  >	
					<input type="hidden" name="sfZgj" id="sfZgj"  value="${sfZgj }">	
					<input type="hidden" name="spgwId" id="spgwId"  value="${spgw }">	
					<input type="hidden" name="cflbdm" id="cflbdm"  value="${cflbdm }">	
					<input type="hidden" name="cfId" id="cfId"  value="${cfId }">	
					<input type="hidden" name="primarykey_checkVal" id="primarykey_checkVal"  value="${primarykey_checkVal }">	
					<table width="100%" border="0" class="formlist">
					<tbody>
					<thead>
							<tr>
								<th colspan="4">
									<span>���ͳ����Ϣ</span>
								</th>
							</tr>
						</thead>

						<tr>
							<td colspan="4">
								<table class="formList" width="100%">
								<thead align="center">
									<tr align="center">
										<td ><b>�������</b></td>
										<td ><b>δ�����</b></td>
										<td ><b>�˻���</b></td>
										<td ><b>��˲�ͨ����</b></td>
										<td ><b>���ͨ����</b></td>
									</tr>
								</thead>
								<tbody align="center">
							<logic:notEmpty name="tongjiList">
								<logic:iterate name="tongjiList" id="s">
										<tr  style="cursor:hand">
										<td >
											${s.cflbmc}
										</td>
										<td >
											${s.wshsl}
										</td>
										<td >
											${s.thsl}
										</td>
										<td >
											${s.btgsl}
										</td>
										<td >
											${s.tgsl}
										</td>
										</tr>
										</logic:iterate>
										</logic:notEmpty>
										</tbody>
								
								</table>
							</td>
						</tr>
							
					</tbody>
						
					<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							
							<div class="btn">
								<button type="button"  id="btn_btg"  onclick="tijiao();return false;" id="buttonSave">
										�ύ
								</button>
								<button type="button"  onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
				</table>
				</input>
			</div>
			
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>
