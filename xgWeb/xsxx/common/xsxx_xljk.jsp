<%@ page language="java" contentType="text/html; charset=GBK"%>
<logic:notEqual value="stu" name="userType" scope="session">				
	<div class="formbox" id="xljk" style="display:none">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="9">
						������Խ����¼
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
						������Ŀ
					</td>
					<td>
						���Խ��
					</td>
					<td>
						����ʱ��
					</td>
					<td>
						���ͱ��
					</td>
				</tr>
			</thead>
			<tbody id="xlcs">
			</tbody>
		</table>
		<br></br>

		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="6">
						����ѧ����¼
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
						��Ҫ�ر�������
					</td>
				</tr>
			</thead>
			<tbody id="tsxs">
			</tbody>
		</table>
	</div>
</logic:notEqual>