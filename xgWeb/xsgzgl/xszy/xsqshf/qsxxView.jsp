<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript'>
		function xsxxView(xh) {
			showDialog("������Ϣ�鿴", 700, 450, "xszyXsxxgl.do?method=viewXszyXsxx&xh=" + xh);
		}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xszyXsqshf" method="post" styleId="XszyQshfForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���һ�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								���Һ�
							</th>
							<td width="30%">
								${qsxxMap.qsh}
							</td>
							<th width="20%">
								¥��
							</th>
							<td width="30%">
								${qsxxMap.ldmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�Ա�
							</th>
							<td width="30%">
								${qsxxMap.qsxb}
							</td>
							<th width="20%">
								԰��
							</th>
							<td width="30%">
								${qsxxMap.xymc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�Ƿ�������
							</th>
							<td width="30%">
								${qsxxMap.sfhhqs}
							</td>
							<th width="20%">
								��ϵ�绰
							</th>
							<td width="30%">
								${qsxxMap.qsdh}
							</td>
						</tr>
						<tr>
						<th>��סѧ��</th>
							<td colspan="3">
								<table width="100%">
									<thead>
										<tr>
											<td>ѧ��</td>
											<td>����</td>
											<td>����</td>
											<td>�༶</td>
											<td>��ϵ�绰</td>
										</tr>
									</thead>
									<tbody id="xsxx">
									<logic:present name="rzxsList">
										<logic:iterate id="xsxx" name="rzxsList" indexId="i">
											<tr>
												<td>
												<a href="javascript:void(0);" class="name" onclick="xsxxView('${xsxx.xh }');return false;">${xsxx.xh }</a>
												</td>
												<td>
												${xsxx.xm }
												</td>
												<td>
												${xsxx.dl }
												</td>
												<td>
												${xsxx.bjmc }
												</td>
												<td>
												${xsxx.lxdh }
												</td>
											</tr>
										</logic:iterate>
									</logic:present>
									<logic:empty name="rzxsList">
											<tr>
												<td colspan="5" align="center">��ס��ѧ����</td>
											</tr>
										</logic:empty>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>����֮����Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszy/comm/xszyView.jsp" %>
				</table>
				</div>
			     <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

