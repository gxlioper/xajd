<%@ page language="java" contentType="text/html; charset=GBK"%>
<div class="formbox" id="pjpy" style="display:none">
	<table width="100%" class="dateline">
		<thead>
			<tr>
				<%--����Ƽ�ѧԺ--%>
				<logic:equal value="11551" name="xxdm">
					<td colspan="11">
						��ѧ���¼
					</td>
				</logic:equal>
				<%--���ս�����ҵѧԺ--%>
				<logic:equal value="10878" name="xxdm">
					<td colspan="11">
						��ѧ���¼
					</td>
				</logic:equal>
				<%--�Ĵ�����ְҵ����ѧԺ--%>
				<logic:equal value="12764" name="xxdm">
					<td colspan="11">
						��ѧ���¼
					</td>
				</logic:equal>
				<%--����ְҵ����ѧԺ--%>
				<logic:equal value="12872" name="xxdm">
					<td colspan="8">
						��ѧ���¼
					</td>
				</logic:equal>

				<%--����--%>
				<logic:notEqual value="11551" name="xxdm">
				<logic:notEqual value="10878" name="xxdm">
				<logic:notEqual value="12764" name="xxdm">
				<logic:notEqual value="12872" name="xxdm">
					<td colspan="9">
						��ѧ���¼
					</td>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
			</tr>
			<tr>
				<!-- ����������Ҫ��ʾ��� -->
				<logic:notEqual value="13022" name="xxdm">							
					<td>
						���
					</td>
				</logic:notEqual>
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
					�༶
				</td>
				<td>
					��ѧ������
				</td>
				<td>
					��ѧ����
				</td>						
				<%--����Ƽ�ѧԺ--%>
				<logic:equal value="11551" name="xxdm">
					<td>
						����Ա���
					</td>
				</logic:equal>
				<%--���ս�����ҵѧԺ--%>
				<logic:equal value="10878" name="xxdm">
					<td>
						����Ա���
					</td>
				</logic:equal>
				<%--�Ĵ�����ְҵ����ѧԺ--%>
				<logic:equal value="12764" name="xxdm">
					<td>
						����Ա���
					</td>
				</logic:equal>
				
				<!--����ְҵ����ѧԺ-->
				<logic:equal value="12872" name="xxdm">
					<td>
						<bean:message key="lable.xsgzyxpzxy" />���
					</td>							
				</logic:equal>
				<!--end����ְҵ����ѧԺ-->

				<!--�Ǻ���ְҵ����ѧԺ-->
				<logic:notEqual value="12872" name="xxdm">							
					<td>
						<bean:message key="lable.xsgzyxpzxy" />���
					</td>
					<td>
						ѧУ���
					</td>
				</logic:notEqual>			
			</tr>
		</thead>
		<tbody id="jxj">
		</tbody>
	</table>
	<br></br>

	<table width="100%" class="dateline">
		<thead>
			<%--����Ƽ�ѧԺ--%>
			<logic:equal value="11551" name="xxdm">
				<tr>
					<td colspan="9">
						�����ƺż�¼
					</td>
				</tr>
			</logic:equal>
			<%--���ս�����ҵѧԺ--%>
			<logic:equal value="10878" name="xxdm">
				<tr>
					<td colspan="9">
						�����ƺż�¼
					</td>
				</tr>
			</logic:equal>
			<%--����ɽ��ѧ--%>
			<logic:equal value="10419" name="xxdm">
				<tr>
					<td colspan="9">
						�����ƺż�¼
					</td>
				</tr>
			</logic:equal>
			<%--��ɳ����ְҵ����ѧԺ--%>
			<logic:equal value="10827" name="xxdm">
				<tr>
					<td colspan="9">
						�����ƺż�¼
					</td>
				</tr>
			</logic:equal>
			<!--����ְҵ����ѧԺ-->
			<logic:equal value="12872" name="xxdm">
				<tr>
					<td colspan="7">
						�����ƺż�¼
					</td>
				</tr>
			</logic:equal>
			<!--����ְҵ����ѧԺ-->

			<%--����--%>
			<logic:notEqual value="11551" name="xxdm">
			<logic:notEqual value="10878" name="xxdm">
			<logic:notEqual value="10419" name="xxdm">
			<logic:notEqual value="10827" name="xxdm">
			<logic:notEqual value="12872" name="xxdm">
				<tr>
					<td colspan="8">
						�����ƺż�¼
					</td>
				</tr>
			</logic:notEqual>
			</logic:notEqual>
			</logic:notEqual>
			</logic:notEqual>
			</logic:notEqual>

			<tr>
				<!-- ����������Ҫ��ʾ��� -->
				<logic:notEqual value="13022" name="xxdm">							
					<td>
						���
					</td>
				</logic:notEqual>
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
					�༶
				</td>
				<td>
					�����ƺ�����
				</td>
				<%--����Ƽ�ѧԺ--%>
				<logic:equal value="11551" name="xxdm">
					<td>
						����Ա���
					</td>
				</logic:equal>
				<%--���ս�����ҵѧԺ--%>
				<logic:equal value="10878" name="xxdm">
					<td>
						����Ա���
					</td>
				</logic:equal>
				<%--����ɽ��ѧ--%>
				<logic:equal value="10419" name="xxdm">
					<td>
						����Ա���
					</td>
				</logic:equal>
				<%--��ɳ����ְҵ����ѧԺ--%>
				<logic:equal value="10827" name="xxdm">
					<td>
						����Ա���
					</td>
				</logic:equal>
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
			</tr>
		</thead>
		<tbody width="100%" id="rych">
		</tbody>
	</table>
	<br/></br>

	<%--�й���Ժ--%>
	<logic:equal value="10355" name="xxdm">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="11">
						������������¼
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
						����������
					</td>
					<td>
						��ע
					</td>
				</tr>
			</thead>
			<tbody id="tydbqk">
			</tbody>
		</table>
		<br/><br/>

		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="11">
						ѧ�����������¼
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
						����ʱ��
					</td>
					<td>
						���εص�
					</td>
					<td>
						���μ�¼
					</td>
				</tr>
			</thead>
			<tbody id="xskqb">

			</tbody>
		</table>
		<br/><br/>
	</logic:equal>
	<%--end�й���Ժ--%>

	<%--���й���Ժ--%>
	<logic:notEqual value="10355" name="xxdm">		
	<%--�Ǻ���ְҵ����ѧԺ--%>		
	<logic:notEqual value="12872" name="xxdm">					
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="11">
						��ʵ�ۺϿ�����¼
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
						˼��Ʒ������
					</td>
					<td>
						֪ʶˮƽ����
					</td>
					<td>
						ѧҵ��������
					</td>
					<td>
						��������
					</td>
					<td>
						��������
					</td>
					<td>
						ѧ��
					</td>
				</tr>
			</thead>
			<tbody id="jszhkp">
			</tbody>
		</table>
		<br/><br/>
		</logic:notEqual>

		<!-- ����������ʾ�ۺϲ�����¼ -->
		<logic:notEqual value="13022" name="xxdm">					
			<table width="100%" class="dateline">
				<thead>
					<tr>
						<td colspan="13">
							�ۺ����ʲ�����¼
						</td>
					</tr>
					<logic:equal value="10822" name="xxdm">
						<%--�㶫����ѧԺ>--%>
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
								�༶
							</td>
							<td>
								���гɼ�
							</td>
						</tr>
					</logic:equal>

					<%--�㽭����--%>
					<logic:equal value="12861" name="xxdm">								
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
								�༶
							</td>
							<td>
								�����ܷ�
							</td>
							<td>
								�����ܷ�
							</td>
							<td>
								�����ܷ�
							</td>
							<td>
								�ۺ����ʲ����ܷ�
							</td>
						</tr>
					</logic:equal>
					<%--����ְҵ����ѧԺ--%>
					<logic:equal value="12872" name="xxdm">								
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
								�༶
							</td>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />����������
							</td>
							<td>
								�����ܷ�
							</td>
							<td>
								�����ܷ�
							</td>
							<td>
								����ѧϰ���·�
							</td>
							<td>
								��Ԣ����������
							</td>
							<td>
								�ۺ����ʲ����ܷ�
							</td>
						</tr>
					</logic:equal>
					<%--����ְҵ����ѧԺ--%>
					<logic:equal value="10863" name="xxdm">
						
						<tr>
							<td>
								ѧ��
							</td>
							<td>
								ְҵ����������
							</td>
							<td>
								ְҵ��������������
							</td>
							<td>
								�ɳ�����չ����������
							</td>
							<td>
								�ۺϲ����ܷ�
							</td>
							<td>
								ְҵ��������
							</td>
							<td>
								�ۺ���������
							</td>
						</tr>
					</logic:equal>
					<%--���ݹ�ҵ԰��ְҵ����ѧԺ--%>
					<logic:equal value="12809" name="xxdm">								
						<tr>
							<td>
								ѧ��
							</td>
							<td>
								ѧ��
							</td>
							<td>
								5Sģ���
							</td>
							<td>
								���ʵ����
							</td>
							<td>
								��֯������
							</td>
							<td>
								������
							</td>
							<td>
								ivt��̳��
							</td>
							<td>
								������
							</td>
							<td>
								���Ա���
							</td>
							<td>
								ѧ�Ż�ܷ�
							</td>
							<td>
								�ۺ������ܷ�
							</td>
							<td>
								�ۺ���������
							</td>
						</tr>
					</logic:equal>
					<logic:notEqual value="12809" name="xxdm">
					<logic:notEqual value="10822" name="xxdm">
					<logic:notEqual value="12861" name="xxdm">
					<logic:notEqual value="12872" name="xxdm">
					<logic:notEqual value="10863" name="xxdm">
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
								�༶
							</td>
							<td>
								˼��������<br/>
								���ܻ���
							</td>
							<td>
								ѧϰ�ܻ���
							</td>
							<td>
								��������<br/>
								�ܻ���
							</td>
							<td>
								��������<br/>
								�ܻ���
							</td>
							<td>
								�ۺ�����<br/>
								�����ܷ�
							</td>
							<td>
								�༶����
							</td>
							<td>
								�꼶����
							</td>
						</tr>
					</logic:notEqual>
					</logic:notEqual>
					</logic:notEqual>
					</logic:notEqual>
					</logic:notEqual>
				</thead>
				<tbody id="zhszcp">
				</tbody>
			</table>					
		</logic:notEqual>
		<!-- end������ -->
	</logic:notEqual>
	<%--end�й���Ժ--%>

	<%--���ս�����ҵѧԺ--%>
	<logic:equal value="10878" name="xxdm">
		<br/><br/>
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="8">
						ѧϰ���������
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
						����
					</td>
					<td>
						��ʱ��
					</td>
					<td>
						ѧϰ��������
					</td>
					<td>
						ѧϰ�����÷�
					</td>
				</tr>
			</thead>
			<tbody id="xxjshj">
			</tbody>
		</table>
	</logic:equal>
</div>
