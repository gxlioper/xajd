<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<html:form action="/xljk_xlwjyjgl_xlfdjlglwh" method="post" styleId="xlfdjlForm">
			<div style='tab;width:100%;height:440px;overflow-x:hidden;overflow-y:auto;'>
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
								<span>��������¼��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ְ����</th>
							<td>
								${xlfxxsxx.zgh}
							</td>
							<th>��ʦ</th>
							<td>${xlfxxsxx.fdyxm}</td>
						</tr>
						<tr>
							<th>ʱ��</th>
							<td  colspan="3">
								${xlfxxsxx.sj }
									
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td colspan="1">
								${xlfxxsxx.xlfdlxmc}
							</td>
							<th class="fdlxtr">��������</th>
							<td colspan="1" class="fdlxtr">
								${xlfxxsxx.fdlxmc}
							</td>
						</tr>
						<tr>
							<th>
								̸������/<br/>
								������ʩ
							</th>
							<td colspan="3" style="word-break: break-all;">
								${xlfxxsxx.thnrfdcs}
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

