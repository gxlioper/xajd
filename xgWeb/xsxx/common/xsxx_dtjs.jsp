<%@ page language="java" contentType="text/html; charset=GBK"%>
<div class="formbox" id="dtjs" style="display:none">
	<table class="dateline" width="100%">
		<thead>
			<tr>
				<td colspan="9">
					��ѵ��¼
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
					�Ա�
				</td>
				<td>
					�꼶
				</td>
				<td>
					<bean:message key="lable.xsgzyxpzxy" />����
				</td>
				<td>
					רҵ����
				</td>
				<td>
					�༶����
				</td>
				<td>
					��ѵ����
				</td>
			</tr>
		</thead>
		<tbody id="xspxxx">
		</tbody>
	</table>
	<br/><br/>
	<table class="dateline" width="100%">
		<thead>
			<tr>
				<td colspan="8">
					�뵳�������Ӽ�¼
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
					�꼶
				</td>
				<td>
					�༶
				</td>
				<td>
					��ʼʱ��
				</td>
			</tr>
		</thead>
		<tbody id="rdjjfz">

		</tbody>
	</table>
	<br/><br/>
	<table class="dateline" width="100%">
		<thead>
			<!--����ѧԺ-->
			<logic:equal value="11122" name="xxdm">
			<tr>
				<td colspan="10">
					Ԥ����Ա��¼
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
					�꼶
				</td>
				<td>
					�༶
				</td>
				<td>
					ת�����
				</td>
				<td>
					��ʼʱ��
				</td>
				<td>
					����ʱ��
				</td>
			</tr>
			</logic:equal>
			<!--������ѧԺ-->
			<logic:notEqual value="11122" name="xxdm">
				<tr>
					<td colspan="9">
						Ԥ����Ա��¼
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
						�꼶
					</td>
					<td>
						�༶
					</td>
					<td>
						��ʼʱ��
					</td>
					<td>
						����ʱ��
					</td>
				</tr>
			</logic:notEqual>
		</thead>
		<tbody id="ybdy">

		</tbody>
	</table>		
	<br/><br/>
	<table class="dateline" width="100%">
		<thead>
			<!--����ѧԺ-->
			<logic:equal value="11122" name="xxdm">
			<tr>
				<td colspan="10">
					��Ա��¼
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
					�꼶
				</td>
				<td>
					�༶
				</td>
				<td>
					�뵳ʱ��
				</td>
				<td>
					ת������
				</td>
				<td>
					ת�����
				</td>
				<td>
					��֯��ϵ���ڵ�λ
				</td>
			</tr>
			</logic:equal>
			<!--����ѧԺ-->

			<!--������ѧԺ-->
			<logic:notEqual value="11122" name="xxdm">
			<tr>
				<td colspan="8">
					��Ա��¼
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
					�꼶
				</td>
				<td>
					�༶
				</td>
				<td>
					�뵳ʱ��
				</td>
			</tr>
			</logic:notEqual>
			<!--end������ѧԺ-->
		</thead>
		<tbody id="dy">

		</tbody>
	</table>
</div>
