<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">

		</script>
	</head>
	<body>
		<!--���ݱ�start-->
		<div class="tab" style="width:100%;height:435px;overflow-x:hidden;overflow-y:auto;">
			<table class="formlist">
				<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>¥����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th align="right" width="16%">
						¥������
					</th>
					<td align="left" width="34%" nowrap="nowrap">
						${rs.ldmc }
					</td>
					<th width="16%">
						¥���Ա�
					</th>
					<td width="34%" id="ldxb">
						${rs.ldxb }
					</td>
				</tr>
				<tr>
					<th>¥������</th>
					<td id="ldcs">
						${rs.ldcs }
					</td>
					<th>¥����ʼ��</th>
					<td id="qsch">
						${rs.qsch }
					</td>
				</tr>
				<tr>
					<th>�Ƿ�0��</th>
					<td>${rs.sfhlc }</td>
					<th></th>
					<td></td>
				</tr>
				</tbody>
				
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							<span>������Ϣ</span>
						</th>
					</tr>
				</thead>
				
				<tbody>
				<tr>
					<th>���</th>
					<td>
						${rs.ch }
					</td>
					<th>
						���Һ�				
					</th>
					<td>
						${rs.qsh }
					</td>
				</tr>
				<tr>
					<th>�����Ա�</th>
					<td>
						${rs.qsxb }
					</td>
					<th>��λ��</th>
					<td>
						${rs.cws }
					</td>		
				</tr>
				<tr>
					<th>�շѱ�׼</th>
					<td>
						${rs.sfbz }
					</td>
					<th>���ҵ绰</th>
					<td>
						${rs.qsdh }
					</td>
				</tr>
				<tr>
					<th>�꼶</th>
					<td>
						${rs.nj }
					</td>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc }
					</td>
				</tr>
				<th>���޿յ�</th>
				<td>
					<div>
						${rs.ywkt }
					</div>
				</td>
				<tr>
					<th>
						��ע
					</th>
					<td colspan="3" style="word-break:break-all;width:100%">
						<html:textarea property='bz' style="width:95%" styleId="bz" rows='4' value="${rs.bz}" readonly="true"/>
					</td>
				</tr>
			</tbody>
			</table>
		<!--���ݱ�end-->
			<h3 class="datetitle_01">
				<span>��λ��Ϣ</span>
			</h3>
			<table class="formlist">
					<thead>
						<tr>
							<th>
								��λ��
							</th>
							<th>
								ѧ��
							</th>
							<th>
								����
							</th>
							<th>
								�꼶
							</th>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<th>
								רҵ
							</th>
							<th>
								�༶
							</th>
							<th>
								��λ�����꼶
							</th>
							<th>
								��λ����<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<th>
								�Ƿ���
							</th>
							
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="s" name="list">
							<tr class="alt">
								<logic:iterate id="v" name="s">
									<td>${v }</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		<table class="formlist" width="97%">
			<tfoot>
					<tr align="right">
						<td colspan="5">
							<div class="btn">
								<button type="button" name="�ر�" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
	</body>
</html>

