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
				"comm_spl.do?method=lccx&sqid=${model.sqid}&tt="
						+ new Date().getTime());
	});

</script>
	</head>
	<body>

		<html:form action="/axcswpsh" method="post"
			styleId="WpshForm">
			<html:hidden property="sqid" styleId="sqid" />
			<div style="overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/axcs/comm/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>
								<bean:message key="lable.axcswpmc" />
							</th>
							<td width="30%">
								${rs.xmmc}
							</td>
							<th width="20%" rowspan="4">
								��ƷͼƬ
							</th>
							<td rowspan="4">
							<div id="zpHidDiv">
								<jsp:include flush="true" page="wpzpck.jsp"></jsp:include>
							</div>
							</td>
						</tr>
						<tr>
							<th width="20%">
								
								<bean:message key="lable.axcswplb" />
							</th>
							<td>
								${rs.xmlbmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								<bean:message key="lable.axcswpxxjs" />
							</th>
							<td>
								${rs.xmxxjs}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��������
							</th>
							<td>
								${rs.sqtj}
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3" style="word-break:break-all;" width="650px">
					            ${rs.sqly }
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
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
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

