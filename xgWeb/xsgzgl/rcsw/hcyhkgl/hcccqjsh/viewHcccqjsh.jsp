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
				"comm_spl.do?method=lccx&sqid=${model.ccqjtxid}&tt="
						+ new Date().getTime());
	});
</script>
	</head>
	<body>

		<html:form action="/rcsw_hcyhk_hcccqjshgl" method="post"
			styleId="hcccqjshForm">
			<html:hidden property="ccqjtxid" styleId="ccqjtxid" />

			<div style="height:440px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>�𳵳˳�������Ϣ</span>
							</th>
						</tr>
					</thead>
						<tbody>
						<tr>
							<th align="right" width="20%">
								ѧ��
							</th>
							<td align="left" width="30%">
								${rs.xn}
							</td>
							<th align="right" width="20%">
								ѧ��
							</th>
							<td align="left" width="30%">
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								�˳�����
							</th>
							<td align="left" width="30%" colspan="3">
								${rs.ccqdz}��${rs.cczdz}
							</td>
						</tr>	
						<logic:equal name="xxdm" value="10351">
					    	<th>����ԭ��</th>
							<td colspan="3">
						    	${rs.hcyhklxmc}
							</td>
					    </logic:equal>
						<tr>	
							<th>
								��ע
							</th>
							<td colspan="3" style="word-break:break-all;" width="650px">
								${rs.bz}
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

