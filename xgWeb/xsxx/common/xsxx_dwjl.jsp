<%@ page language="java" contentType="text/html; charset=GBK"%>
<div class="formbox" id="dwjl" style="display:none">
	<table width="100%" class="dateline">
		<thead>
			<tr>
				<!--����ְҵ����ѧԺ-->
				<logic:equal value="12872" name="xxdm">							
				<td colspan="10">
					���⽻����������ѧ���¼
				</td>
				</logic:equal>
				
				<!--�Ǻ���ְҵ����ѧԺ-->
				<logic:notEqual value="12872" name="xxdm">
				<td colspan="11">
					���⽻����������ѧ���¼
				</td>
				</logic:notEqual>	
				
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
					������Ŀ
				</td>
				<td>
					����ʱ��
				</td>
				<!--����ְҵ����ѧԺ-->
				<logic:equal value="12872" name="xxdm">							
					<td>
						<bean:message key="lable.xsgzyxpzxy" />���
					</td>
				</logic:equal>
				<!--�Ǻ���ְҵ����ѧԺ-->
				<logic:notEqual value="12872" name="xxdm">
					<td>
						<bean:message key="lable.xsgzyxpzxy" />���
					</td>
					<td>
						ѧУ���
					</td>
				</logic:notEqual>						
				<td>
					ѧУ����
				</td>
				<td>
					��ѧ��
				</td>
			</tr>
		</thead>
		<tbody id="dwjljjxj">

		</tbody>
	</table>
</div>
