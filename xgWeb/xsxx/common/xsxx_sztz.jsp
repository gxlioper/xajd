<%@ page language="java" contentType="text/html; charset=GBK"%>
<logic:equal value="11032" name="xxdm">
	<div  class="formbox"  >
		<table width="100%" class="dateline" id="sztz" style="display:none">
			<thead>
				<tr>
					<td>������Ŀ</td>
					<td>��������</td>
					<td>ѧ��</td>
					<td>ѧ��</td>
					<td>ѧ��</td>
					<td>�Ƿ�����</td>
				</tr>
			</thead>
			<tbody id="sztzData">
				
			</tbody>
		</table>
	</div>
</logic:equal>
<logic:notEqual value="11032" name="xxdm">
<%--������Ϣְҵ����ѧԺ--%>
<logic:equal value="13108" name="xxdm">
	<div class="formbox" id="jsxxsztz" style="display:none">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="9">
						������չ��֤��Ϣ
					</td>
				</tr>
				<tr>
					<td>
						ѧ��
					</td>
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
						����
					</td>
					<td>
						��չ�
					</td>
					<td>
						������Ŀ
					</td>
					<td>
						���ѧ��
					</td>
				</tr>
			</thead>
			<tbody id="tzrzxx">
			</tbody>
		</table>
	</div>
</logic:equal>

<%--���������ѧ--%>
<logic:present name="isXNMZ">
	<div class="formbox" id="xnmzsztz" style="display:none">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="9">
						��ҵ��¼
					</td>
				</tr>
				<tr>
					<td>
						ѧ��
					</td>
					<td>
						ѧ��
					</td>
					<td>
						������չ�༶
					</td>
					<td>
						��ҵʱ��
					</td>
					<td>
						��ҵ����
					</td>
				</tr>
			</thead>
			<tbody id="tzbjjy">
			</tbody>
		</table>
	</div>
</logic:present>

<%--����ְҵ����ѧԺ--%>
<logic:present name="isNbzyjsxy">				
	<div class="formbox">
		<table width="100%" class="dateline" id="sztz" style="display:none">
			<thead>
				<tr>
					<td colspan="10">
						���¼
					</td>
				</tr>
				<tr>
					<td>
						ѧ��
					</td>
					<td>
						ѧ��
					</td>
					<td>
						��Ŀ����
					</td>
					<td>
						��Ŀ����
					</td>
					<td>
						��Ŀ����
					</td>
					<td>
						������
					</td>
					<td>
						����ѧ��
					</td>
					<td>
						��������
					</td>
					<td>
						��֯��λ
					</td>
					<td>
						����ʱ��
					</td>
				</tr>
			</thead>
			<tbody id="tzhdxx">
			</tbody>
		</table>
	</div>
</logic:present>
</logic:notEqual>