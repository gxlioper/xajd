<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
			<%--<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			--%><div style="height:450px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								${rs.xn}
							</td>
							<th width="16%">
								���˲���
							</th>
							<td width="34%">
								${rs.yrdwmc}
							</td>
						</tr>
						<tr>
							<th>
								��λ����
							</th>
							<td>
								${rs.gwmc}
							</td>
							<th>
								��λ����
							</th>
							<td>
								${rs.gwxzmc}
							</td>
						</tr>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								${rs.xh}
							</td>
							<th>
								����
							</th>
							<td>
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								${rs.nj}
							</td>
							<th>
								ѧԺ
							</th>
							<td>
								${rs.xymc}
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td>
								${rs.zymc}
							</td>
							<th>
								�༶
							</th>
							<td>
								${rs.bjmc}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4"><span>�����Ϣ</span></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								�·�
							</th>
							<td width="34%">
								${rs.ffny}
							</td>
							<th width="16%">
								�ܹ�ʱ
							</th>
							<td width="34%">
								${rs.gs }
							</td>
						</tr>
						<tr>
							<th>
								���
							</th>
							<td colspan="3">
								${rs.je }
							</td>
						</tr>
						<tr style="height:66px">
							<th>
								��ע
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.bz}
							</td>
						</tr>
					</tbody>
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

	</body>
</html>

