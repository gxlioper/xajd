<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
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
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_xsxx">
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<a href='javascript:void(0);' style="margin-left: 1px;" onclick="showDialog('ѧ����ϸ��Ϣ',750,500,'xsxx_tygl.do?method=ckZxsxx&xh=${rs.xh }')" class='name'>${rs.xh }</a>
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th width="16%">
								�Ա�
							</th>
							<td width="34%">
								${rs.xb}
							</td>
							<th width="16%">
								�꼶
							</th>
							<td width="34%">
								${rs.nj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								<bean:message key="lable.xb" />
							</th>
							<td width="34%">
								${rs.xymc}
							</td>
							<th width="16%">
								רҵ
							</th>
							<td width="34%">
								${rs.zymc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								�༶
							</th>
							<td width="34%">
								${rs.bjmc}
							</td>
							<th width="16%">
								�Ƿ�������
							</th>
							<td width="34%">
								${rs.sfkns}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr>
							<th width="16%">
								��λ����
							</th>
							<td width="34%">
								${rs.gwmc}
							</td>
							<th width="16%">
								���˲���
							</th>
							<td width="34%">
								${rs.bmmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��λ����
							</th>
							<td width="34%">
								${rs.gwxzmc}
							</td>
							<th width="16%">
								�ϸ�����
							</th>
							<td width="34%">
								<html:hidden name="rs" property="sgsj" styleId="sgsj"/>
								${rs.sgsj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								�˸�����
							</th>
							<td width="84%" colspan="3">
								${rs.tgsj}
							</td>
						</tr>
						<tr style="height:60px">
							<th width="16%">
								�˸�ԭ��
							</th>
							<td width="84%" colspan="3" style="word-break:break-all;width:100%">
								${rs.tgyy}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="8">
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

