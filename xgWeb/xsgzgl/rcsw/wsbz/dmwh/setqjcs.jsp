<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/wsbz/dmwh/js/dmwh.js"></script>
	</head>
	<body >
		<html:form action="/wsbz_dmwh" method="post" styleId="WsbzDmwhForm" onsubmit="return false;">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>
										ȫ�ֲ���ά��
									</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>��������
								</th>
								<td>
								<input type="text" id="bmcs" value="${cs.bmcs }" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>��ֹ����
								</th>
								<td>
									<input type="text" id="jzts" value="${cs.jzts}" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>��ֹʱ��
								</th>
								<td>
									<input type="text" id="jzsj" value="${cs.jzsj}" onfocus="return showCalendar('jzsj','HH:mm:ss');" />
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="����" onclick="setQjcs();return false;">
											�� ��
										</button>
										<button type="button" name="�� ��" onclick="iFClose();return false;">
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

