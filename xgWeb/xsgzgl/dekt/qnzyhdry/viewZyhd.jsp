<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">	
	
	</script>
</head>
<body>
	<html:form action="/zyhdry" method="post" styleId="qnzyryForm">
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
				 </table>
		
			<table width="100%" border="0" class="formlist">
			<thead>
					<tr>
						<th colspan="4">
							<span>���Ϣ</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<th width="15%">
						������
					</th>
					<td width="35%">
						${data.fzrxm}
					</td>
					<th width="15%">
						��֯����
					</th>
					<td width="35%">
						${data.zzbm}
					</td>
				</tr>
				<tr>
					<th>
						���ʼʱ��
					</th>
					<td>
						${data.hdkssj}
					</td>
					<th>
						�����ʱ��
					</th>
					<td>
						${data.hdjssj}
					</td>
				</tr>
				<tr>
					<th>
						��ص�
					</th>
					<td colspan="3">
						${data.hddd}
					</td>
				</tr>			
			</tbody>
			<logic:notEqual value="0" name="data" property="gsshzt">
				<thead>
					<tr>
						<th colspan="4">
							<span>�����Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<tr>
							<th>
								����ʱ
							</th>
							<td colspan="3">
								${data.gs}Сʱ 
							</td>
						</tr>
					</tr>
					<tr>
						<tr>
							<th>
								��˽��
							</th>
							<td colspan="3">
								${data.gsshztmc}
							</td>
						</tr>
					</tr>
					<tr>
						<th width="20%">
							 ������
						</th>
						<td colspan="3">
							${data.gsshyj}
						</td>
					</tr>
				</tbody>
			</logic:notEqual>
					<!-- ����ʦ����ѧ���Ի� -->
					<logic:equal value="10346" name="xxdm">					
						<thead>
							<tr>
								<th colspan="4">
									<span>������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>					
							<tr>
								<th>���۽��</th>
								<td colspan="3" >
									${data.pjjg}
								</td>
							</tr>
							<tr>
								<th>
									���۱�ע
								</th>
								<td colspan="3">
									${data.pjbz}
								</td>
							</tr>
						</tbody>
					</logic:equal>				
			</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
