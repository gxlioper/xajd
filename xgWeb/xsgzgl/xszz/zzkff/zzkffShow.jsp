<%@ page language="java" contentType="text/html; charset=GBK" import="java.util.*,xgxt.utils.String.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>���������Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							ѧ��
						</th>
						<td align="left">
							${zzkffForm.xn }
						</td>
						<th align="right">
							ѧ��
						</th>
						<td align="left">
							${zzkffForm.xqmc}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							��Ŀ����
						</th>
						<td align="left">
							${zzkffForm.xmmc }
						</td>
						<th align="right">
							���
						</th>
						<td align="left">
							${zzkffForm.je }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							��������״̬
						</th>
						<td colspan="3">
							${zzkffForm.wsyhzt}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							���з�����Ϣ
						</th>
						<td colspan="3">
							${zzkffForm.yhfkxx}
						</td>
					</tr>
					
				</tbody>
			</table>
		</div>
	</body>
</html>
