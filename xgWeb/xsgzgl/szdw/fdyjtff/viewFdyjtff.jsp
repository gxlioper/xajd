<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl//szdw/fdyjtff/js/fdyjtff.js"></script>
		<script type="text/javascript">
		</script>
	</head>
		
	<body>
		<html:form action="/szdw_fdyjtff.do" styleId="fdyjtffForm" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="id" styleId="id"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����Ա��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">ְ����</th>
							<td style="width:35%">
								<html:hidden property="zgh" styleId="zgh"/>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="zgh"/>
								</logic:present>
							</td>
							<th style="width:15%">����</th>
							<td style="width:35%">
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xm"/>
								</logic:present>
							</td>
						</tr>
						
						<tr>
							<th>�Ա�</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xbmc"/>
								</logic:present>
							</td>
							<th>��ϵ�绰</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="lxdh"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="bmmc"/>
								</logic:present>
							</td>
							<th></th>
							<td>
								
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>����������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								<bean:write name="fdyjtffForm" property="bzlxmc"/>
							</td>
							<th width="16%">
								�����ȼ�
							</th>
							
							<td width="34%">
								<bean:write name="fdyjtffForm" property="kpdjmc"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<bean:write name="fdyjtffForm" property="xn"/>
							</td>
							<th width="16%">
								ѧ��
							</th>
							
							<td width="34%">
								<bean:write name="fdyjtffForm" property="xqmc"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								�������
							</th>
							<td width="34%">
								<bean:write name="fdyjtffForm" property="bzje"/>
							</td>
							<th width="16%">
								
							</th>
							
							<td width="34%">
								
							</td>
						</tr>
					</tbody>
				</table>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="refreshParent2();">
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