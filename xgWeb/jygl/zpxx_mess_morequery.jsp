<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��λ�������</a>
			</p>
		</div>

		<div class="tab" style="margin-top: 0px; padding-top: 0px">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>��λ�������</span>
						</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button onclick="expAppTab('yjfk','��ҵ�������','')"
									>
									�� ӡ
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<th>
							��λ����
						</th>
						<td>
							<html:text name="rs" property="gsmc" readonly="true" />
						</td>
						<th>
							��λ����
						</th>
						<td>
							<html:text name="rs" property="dwxz" style="width:30px"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							��ҵ����
						</th>
						<td>
							<html:text name="rs" property="hyfl" style="width:230px"
								readonly="true" />
						</td>
						<th>
							ʱ��
						</th>
						<td>
							<html:text name="rs" property="fksj" style="width:100px"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th></th>
						<td></td>
						<th></th>
						<td></td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>������Ŀ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="4">
							<table>
								<tbody>
									<tr>
										<td>
											1����λ�Ա�У����֮רҵ�γ̵������
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs1" value="�ǳ�����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs1" value="����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs1" value="һ��"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs1" value="������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs1" value="�ǳ�������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											2����λ��Ϊ��У����֮���ݷ���ҵ��ʵ������
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs2" value="�ǳ�����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs2" value="����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs2" value="һ��"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs2" value="������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs2" value="�ǳ�������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											3����У��ҵ����ѧ���ܺ͹���ʵ���ν�
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx1" value="�ǳ�����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx1" value="����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx1" value="һ��"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx1" value="������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx1" value="�ǳ�������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											4����У��ҵ�����ַ��Ϲ�˾������
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx2" value="�ǳ�����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx2" value="����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx2" value="һ��"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx2" value="������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx2" value="�ǳ�������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											5����У��ҵ���ı���빵ͨ����
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq1" value="�ǳ�����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq1" value="����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq1" value="һ��"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq1" value="������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq1" value="�ǳ�������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											6����У��ҵ���ĵ�����������
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq2" value="�ǳ�����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq2" value="����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq2" value="һ��"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq2" value="������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq2" value="�ǳ�������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											7����У��ҵ���Ĵ��⼰˼������
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq3" value="�ǳ�����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq3" value="����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq3" value="һ��"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq3" value="������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq3" value="�ǳ�������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											8����У��ҵ��������Ự����
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq4" value="�ǳ�����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq4" value="����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq4" value="һ��"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq4" value="������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq4" value="�ǳ�������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											9����У��ҵ���Ķ���˼�����������
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq5" value="�ǳ�����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq5" value="����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq5" value="һ��"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq5" value="������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq5" value="�ǳ�������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											10����λ����У��ҵ�����������
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="myd" value="�ǳ�����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="myd" value="����"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="myd" value="һ��"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="myd" value="������"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="myd" value="�ǳ�������"></html:radio>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<td colspan="4">
							<span>��ϸ��Ϣ����</span>
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							��λ����
						</th>
						<td colspan="3">
							<bean:write name="rs" property="gsmc" />
						</td>
					</tr>
					<tr>
						<th>
							�����������
						</th>
						<td colspan="3">
							<html:text name="rs" property="yjfkbt" style="width=100%"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							�����������
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="yjfknr" style="width:100%"
								rows="26" readonly="true" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>

