<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
	jQuery(function() {

		jQuery("#shlccx").load(
				"comm_spl.do?method=lccx&sqid=${model.id}&tt="
						+ new Date().getTime());
	});
</script>
	</head>
	<body>

		<html:form action="/rcsw_rcxwwh_rcxwxxwhgl" method="post"
			styleId="rcxwxxwhForm">
			<html:hidden property="id" styleId="id" />

			<div style="overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>

					<thead>
						<tr>
							<th colspan="4">
								<span>��Ϊ��¼��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								${rs.xn }
							</td>
							<th>
								ѧ��
							</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th>
								��Ϊ����
							</th>
							<td>
								${rs.rcxwlbdlmc}
							</td>
							<th>
								��Ϊ���
							</th>
							</th>
							<td>
								${rs.rcxwlbmc}
							</td>
						</tr>

						<tr>
							<th>
							   	����˵��
							</th>
							<td colspan="3">
								${rs.rcxwlbbz}
							</td>
			      		</tr>
					    <tr>
							<th>������ֵ</th>
							<td>${rs.fz }</td>							
							<th >����ʱ��</th>
							<td >
								${rs.fssj}
							</td>
					    </tr>
					    <tr>
							<th>��¼��</th>
							<td>${rs.jlrxm }</td>							
							<th >��¼ʱ��</th>
							<td >
								${rs.rcxwjlsj}
							</td>
					    </tr>
					    <logic:equal value="13022" name="xxdm">
					    <tr>
					    	<th>����</th>
					    	<td colspan="3" id="fileTd">
					    		<logic:notEmpty name="rs" property="fjlj">
					    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwxxwhgl.do?method=downloadFile&id=${rs.id }');return false;" class="name">����</a>&nbsp;${rs.fjmc}
					    		</logic:notEmpty>
					    	</td>
					    </tr>
					    </logic:equal>
					    <logic:equal value="13871" name="xxdm">
					    <tr>
					    	<th>����</th>
					    	<td colspan="3" id="fileTd">
					    		<logic:notEmpty name="rs" property="fjlj">
					    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwxxwhgl.do?method=downloadFile&id=${rs.id }');return false;" class="name">����</a>&nbsp;${rs.fjmc}
					    		</logic:notEmpty>
					    	</td>
					    </tr>
					    </logic:equal>
					     <logic:equal value="70002" name="xxdm">
					    <tr>
					    	<th>����</th>
					    	<td colspan="3" id="fileTd">
					    		<logic:notEmpty name="rs" property="fjlj">
					    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwxxwhgl.do?method=downloadFile&id=${rs.id }');return false;" class="name">����</a>&nbsp;${rs.fjmc}
					    		</logic:notEmpty>
					    	</td>
					    </tr>
					    </logic:equal>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								${rs.gfly}
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3">
								${rs.bz}
							</td>
						</tr>

						<tr>
							<th>
								���״̬
							</th>
							<td colspan="3">
								${rs.shztmc}
							</td>
						</tr>
					</tbody>
				</table>
				<logic:notEqual value="�������" name="shztmc">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" id="shlccx">

								</td>
							</tr>

						</tbody>

					</table>
				</logic:notEqual>

				</div>
				<table width="100%" border="0" class="formlist">

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
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

