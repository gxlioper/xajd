<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/xljk_xlwjyjgl_ylxlxsglwh" method="post" styleId="ylxlxsglForm">
			<div style='tab;width:100%;height:480px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��̸��¼��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��̸ʱ��</th>
							<td  colspan="3">
									${ylxlxsxx.mtkssj}
									��
									${ylxlxsxx.mtjssj}
							</td>
						</tr>
						<tr>
							<th>��̸�ص�</th>
							
							<td colspan="3">
								${ylxlxsxx.mtdd}
							</td>
						</tr>
						<tr>
							<th>
								��̸����
							</th>
							<td colspan="3" style="word-break: break-all;">
								${ylxlxsxx.ftnr}
							</td>
						</tr>
						<tr>
							<th>
								������������
							</th>
							<td colspan="3">
								${ylxlxsxx.lxmc}
							</td>
						</tr>
						<tr>
							<th>
								�жϵȼ�
							</th>
							<td colspan="3">
								${ylxlxsxx.gzdj}
							</td>
						</tr>
						<tr>
							<th>
								��̸С�ἰ<br />
								���踨����ʩ
							</th>
							<td colspan="3" style="word-break: break-all;">
								${ylxlxsxx.mtxjjjsfdcs}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>����Σ��Ԥ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								������������
							</th>
							<td colspan="3">
								${yjkxx.lxmc}
							</td>
						</tr>
						<tr>
							<th>
								��ע�ȼ�
							</th>
							<td colspan="1">
								${yjkxx.gzdj}
							</td>
							<th>
								���ʱ��
							</th>
							<td colspan="1">
									${yjkxx.rksj}
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3" style="word-break: break-all;">
								${yjkxx.bz}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="�� ��" onclick="iFClose();">
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

