<%@ page language="java" contentType="text/html; charset=GBK"%>
<div class="formbox" id="qtsj" style="display:none">
	<table width="100%" class="dateline">
		<thead>
			<tr>
				<td colspan="10">
					<logic:equal value="12872" name="xxdm">
						���������¼
					</logic:equal>
						<logic:notEqual value="12872" name="xxdm">
						ѧ�����ռ�¼
					</logic:notEqual>
				</td>
			</tr>
			<tr>
				<td>
					���
				</td>
				<td>
					ѧ��
				</td>
				<td>
					ѧ��
				</td>
				<td>
					ѧ��
				</td>
				<td>
					����
				</td>
				<td>
					���չ�˾
				</td>
				<td>
					Ͷ������
				</td>
				<td>
					Ͷ��ʱ��
				</td>
				<td>
					�˱�ʱ��
				</td>
				<td>
					�˱����
				</td>
			</tr>
		</thead>
		<tbody id="bxxx">
		</tbody>
	</table>
	<br></br>

	<logic:notEqual value="12872" name="xxdm">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="5">
						��ʳ���Ѽ�¼
					</td>
				</tr>
				<tr>
					<td>
						ѧ��
					</td>
					<td>
						����
					</td>
					<td>
						���
					</td>
					<td>
						�·�
					</td>
					<td>
						���ѽ��
					</td>
				</tr>
			</thead>
			<tbody id="hsxf">
			</tbody>
		</table>
	</logic:notEqual>
</div>
