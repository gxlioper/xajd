<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
	</head>
	<body style="width:97%">
		<html:form action="/rwgl_mbxxgl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ�޸�</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${rs.xm }
							</td>
							<th width="16%">
								�Ա�
							</th>
							<td width="34%" >
								${rs.xb }
							</td>
						</tr>
						<tr>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								${rs.csrq }
							</td>
							<th width="16%">
								���֤��
							</th>
							<td width="34%">
								${rs.sfzh }
							</td>
						</tr>
						<tr>
							<th width="16%">
								�Ļ��̶�
							</th>
							<td width="34%">
								${rs.whcd }
							</td>
							<th width="16%">
								רҵ
							</th>
							<td width="34%">
								${rs.zy }
							</td>
						</tr>
						<tr>
							<th width="16%">
								ְ��
							</th>
							<td width="34%">
								${rs.zc }
							</td>
							<th width="16%">
								ְ��
							</th>
							<td width="34%">
								${rs.zw }
							</td>
						</tr>
						<tr>
							<th width="16%">
								���ʱ��
							</th>
							<td width="34%">
								${rs.rdsj }
							</td>
							<th width="16%">
								������ò
							</th>
							<td width="34%">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								תҵ�������
							</th>
							<td width="34%">
								${rs.sftwjr }
							</td>
							<th width="16%">
								רҵ��λ
							</th>
							<td width="34%">
								${rs.zygw }
							</td>
						</tr>
						<tr>
							<th width="16%">
								������λ
							</th>
							<td width="34%">
								${rs.gzdw }
							</td>
							<th width="16%">
								��ϵ��ʽ
							</th>
							<td width="34%">
								${rs.lxfs }
							</td>
						</tr>
						<tr>
							<th width="16%">
								�칫�绰
							</th>
							<td width="34%">
								${rs.bgdh }
							</td>
							<th width="16%">
								�̺�
							</th>
							<td width="34%">
								${rs.dh }
							</td>
						</tr>
						<tr>
							<th width="16%">
								���ְ��
							</th>
							<td width="34%">
								${rs.mbzw }
							</td>
							<th width="16%">
								���α���
							</th>
							<td width="34%">
								${rs.zzbx }
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ͥ��ַ
							</th>
							<td width="84%" colspan="3">
								${rs.jtdz }
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ʱ�ܹ����־���ѵ��
							</th>
							<td width="84%" colspan="3" style="height:66px; word-break:break-all;width:99%">
								${rs.jsxl}
							</td>
						</tr>
						
					</tbody>
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
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

