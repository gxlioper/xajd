<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
	</head>
	<body>
		<html:form action="/rwgl_rwtwgl" method="post">
			<div style='height:485px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								${rs.xh }
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%" >
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th width="16%">
								�Ա�
							</th>
							<td width="34%">
								${rs.xb }
							</td>
							<th width="16%">
								�꼶
							</th>
							<td width="34%">
								${rs.nj }
							</td>
						</tr>
						<tr>
							<th width="16%">
								<bean:message key="lable.xb" />
							</th>
							<td width="34%">
								${rs.xymc }
							</td>
							<th width="16%">
								רҵ
							</th>
							<td width="34%">
								${rs.zymc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								�༶
							</th>
							<td width="34%">
								${rs.bjmc }
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
								����
							</th>
							<td width="34%">
								${rs.mzmc }
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								${rs.csrq }
							</td>
						</tr>
						<tr>
							<th width="16%">
								���֤��
							</th>
							<td width="34%">
								${rs.sfzh }
							</td>
							<th width="16%">
								�ֻ�����
							</th>
							<td width="34%">
								${rs.sjhm }
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ͥ��ַ
							</th>
							<td width="34%">
								${rs.jtdz }
							</td>
							<th width="16%">
								��ͥ�绰
							</th>
							<td width="34%">
								${rs.jtdh }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red"></font>����ʱ��
							</th>
							<td width="34%">
								${rs.rwsj }
							</td>
							<th width="16%" >
								<font color="red"></font>�����
							</th>
							<td width="34%" style="word-break:break-all;width:99%">
								<!-- ���ݴ�ѧ -->
								<logic:equal name="xxdm" value="10351">	
									${rs.rwdwdmc }
								</logic:equal>
								<logic:notEqual name="xxdm" value="10351">	
									${rs.rwd }
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th width="16%">
								���鷽ʽ
							</th>
							<td width="34%" colspan="3">
								<font id="rwfs">${rs.rwfsmc}</font>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								����֤��
							</th>
							<td width="34%">
								${rs.rwzh }
							</td>
							<th width="16%">
								����ʱ��
							</th>
							<td width="34%">
								${rs.twsj }
							</td>
						</tr>
						<tr>
							<th width="16%">
								ԭרҵ
							</th>
							<td width="34%">
								${rs.yzy }
							</td>
							<th width="16%">
								ԭ�༶
							</th>
							<td width="34%">
								${rs.ybj }
							</td>
						</tr>
						<tr>
							<th width="16%">
								������ϵ
							</th>
							<td width="34%">
								${rs.hjgx }
							</td>
							<th width="16%">
								�������ڵ�
							</th>
							<td width="34%">
								${rs.hkszd }
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ע
							</th>
							<td width="84%" colspan="3" style="word-break:break-all;width:99%">
								${rs.bz }
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
			<div>
				<table class="formlist">
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

