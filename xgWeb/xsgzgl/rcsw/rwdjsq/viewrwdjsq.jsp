<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${data.rwdjid}&tt="+new Date().getTime());
			});
		</script>
	</head>
	<body>
		<html:form method="post" action="/rwdjsq.do" styleId="form" onsubmit="return false;">
			<html:hidden property="xh" />
			<html:hidden property="rwdjid" />
			<div
				style='width: 100%; height: 460px; overflow-x: hidden; overflow-y: auto;'>
				<table width="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
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
								����;��
							</th>
							<td colspan="3">
								${data.rwtjmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								����ʱ��
							</th>
							<td>
								${data.rwsj}
							</td>
							<th width="20%">
								ѧҵ���
							</th>
							<td>
								${data.xyqk}
							</td>
						</tr>
						<tr>
							<th width="20%">
								����ǩ����Э��
							</th>
							<td>
								${data.ywqrwxy}
							</td>
							<th width="20%">
								����������
							</th>
							<td>
								${data.bjgms}
							</td>
						</tr>
						<tr>
							<th width="20%">
								����״��
							</th>
							<td>
								${data.hyzk}
							</td>
							<th width="20%">
								��ҵ���
							</th>
							<td>
								${data.cylb}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�������
							</th>
							<td>
								${data.hjlb}
							</td>
							<th width="20%">
								������ϵ��ʽ
							</th>
							<td>
								${data.bdlxfs}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��������
							</th>
							<td>
								${data.fqxm}
							</td>
							<th width="20%">
								�����ֻ�
							</th>
							<td>
								${data.fqsj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								ĸ������
							</th>
							<td>
								${data.mqxm}
							</td>
							<th width="20%">
								ĸ���ֻ�
							</th>
							<td>
								${data.mqsj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								������ϵ��
							</th>
							<td>
								${data.qtlxr}
							</td>
							<th width="20%">
								������ϵ�˷�ʽ
							</th>
							<td>
								${data.qtlxrfs}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��������
							</th>
							<td>
								${data.zysl}
							</td>
							<th width="20%">
								��������
							</th>
							<td>
								${data.yysl}
							</td>
						</tr>
						<tr>
							<th width="20%">
								���۲���
							</th>
							<td>
								${data.fybd}
							</td>
							<th width="20%">
								���ӵ�ַ
							</th>
							<td>
								${data.bddz}
							</td>
						</tr>
						<tr>
							<th width="20%">
								����ʿ��
							</th>
							<td>
								${data.yxsb}
							</td>
							<th width="20%">
								��ԭʱ��
							</th>
							<td>
								${data.fysj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�ν�
							</th>
							<td colspan="3">	
									${data.jj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								����
							</th>
							<td colspan="3">
								${data.lg}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ҵʱ��
							</th>
							<td>
								${data.bysj}
							</td>
							<th width="20%">
								ר�ӱ�ʱ��
							</th>
							<td>
								${data.zjbsj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								ר�ӱ���Ͷ�ѧԺ
							</th>
							<td>
								${data.zjbhjdxy}
							</td>
							<th width="20%">
								ר�ӱ���רҵ
							</th>
							<td>
								${data.zjbhzy}
							</td>
						</tr>
						<tr>
							<th width="20%">
								ר�ӱ���ѧ��
							</th>
							<td>
								${data.zjbhxh}
							</td>
							<th width="20%">
								ר�ӱ����ҵʱ��
							</th>
							<td>
								${data.zjbhbysj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								���п���
							</th>
							<td>
								${data.bjyhkh}
							</td>
							<th width="20%">
								���п�����
							</th>
							<td>
								${data.yhkmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								���п���ַ
							</th>
							<td>
								${data.yhkdz}
							</td>
							<th width="20%">
								�����ѧ�Ѳ���
							</th>
							<td>
								${data.rwhxfbc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								���ۺ�ѧ������
							</th>
							<td>
								${data.tyhxfzz}
							</td>
							<th width="20%">
								��ҵ��λ
							</th>
							<td>
								${data.jyhdw}
							</td>
						</tr>
						<tr>
							<th width="20%">
								����Ա
							</th>
							<td>
								${data.gwy}
							</td>
							<th width="20%">
								��ҵ��
							</th>
							<td>
								${data.syb}
							</td>
						</tr>
						<tr>
							<th width="20%">
								����
							</th>
							<td>
								${data.gq}
							</td>
							<th width="20%">
								�ǹ�����
							</th>
							<td>
								${data.fgjj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ע
							</th>
							<td colspan="3">
								${data.bz}
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
					<td colspan="4" id="shlccx">
					
					</td>
				</tr>
			</tbody>
					</div>
					<div>
						<table width="100%" border="0" class="formlist"
							style="position: fixed; _position: absolute; bottom: 0;">
							<tfoot>
								<tr>
									<td colspan="4">
										<div class="btn">
											<button type="button" onclick="iFClose();" id="buttonClose">
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
