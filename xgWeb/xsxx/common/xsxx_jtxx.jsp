<%@ page language="java" contentType="text/html; charset=GBK"%>
<div class="formbox" id="jtxx" style="display:none">
	<table width="100%" class="dateline">
		<thead>
			<tr>
				<td colspan="8">
					��ͥ��Ա��Ϣ
				</td>
			</tr>
			<%--���ս�����ҵѧԺ--%>
			<logic:equal value="10878" name="xxdm">
			<tr>
				<td>
					ѧ��
				</td>
				<td>
					����
				</td>
				<td>
					��������
				</td>
				<td>
					�븸�׹�ϵ
				</td>
				<td>
					ĸ������
				</td>
				<td>
					��ĸ�׹�ϵ
				</td>
				<td>
					������Ա����
				</td>
				<td>
					��������Ա��ϵ
				</td>
			</tr>
			</logic:equal>

			<%--�ǰ��ս�����ҵѧԺ--%>
			<logic:notEqual value="10878" name="xxdm">
				<tr>
					<td>
						ѧ��
					</td>
					<td>
						����
					</td>
					<td>
						��ͥ��Ա1����
					</td>
					<td>
						��ͥ��Ա1��ϵ
					</td>
					<td>
						��ͥ��Ա2����
					</td>
					<td>
						��ͥ��Ա2��ϵ
					</td>
					<td>
						��ͥ��Ա3����
					</td>
					<td>
						��ͥ��Ա3��ϵ
					</td>
				</tr>
			</logic:notEqual>
		</thead>
		<tbody  id="xsjtxx">

		</tbody>
	</table>
</div>
