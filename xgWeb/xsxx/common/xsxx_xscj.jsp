<%@ page language="java" contentType="text/html; charset=GBK"%>
<!--�㽭��ҵְҵ����ѧԺ-->
<logic:present name="showZjszy">				
	<div class="formbox" id="xscj" style="display:none">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="5">
						���ϸ�γ̼�¼
					</td>
				</tr>
			</thead>
			<tr>
				<td>
					<logic:empty name="rs25">
						<br />
						<br />
						<p align="center">
							�޼�¼��
						</p>
					</logic:empty>
					<logic:notEmpty name="rs25">
						<div style="display:none">
							<logic:iterate id="in25" name="rs25" indexId="index">
								<logic:iterate id="xq" name="in25">
									<tr>
										<td bgcolor="#ffccdd" align="center"
											onclick="getBjgcjInfo(${index});document.all.kcxx${index}.style.display=(document.all.kcxx${index}.style.display =='none')?'':'none'"
											id="${index}" name="${index}" colspan="5">
											${xq.xn}ѧ��${xq.xq}ѧ�� &nbsp;&nbsp;&nbsp;&nbsp;��
											<font color="red">${xq.numKc}</font>�Ų�����,���
											<font color="red">${xq.numXf}</font>ѧ��
											<input type="hidden" value="${xq.xn}${xq.xq}"
												id="xq${index}">
										</td>
									</tr>

									<tr>
										<td bgcolor="#ffffff">
											<table width="100%" class="tbstyle" id="kcxx${index}"
												style="display:none">
											</table>
										</td>
									</tr>

								</logic:iterate>
							</logic:iterate>
						</div>
					</logic:notEmpty>
				</td>
			</tr>
			<logic:notEmpty name="rs25">
				<tr>
					<td colspan="5">
						<div align="right">
							��У�ڼ��ֹ�����ڹ���
							<font color="red"><bean:write name="totalKc" /> </font>�Ų��ϸ�
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �ۼƻ��ѧ��
							<font color="red"><bean:write name="totalXf" /> </font>��
						</div>
					</td>
				</tr>
			</logic:notEmpty>
		</table>
	</div>
</logic:present>

<%--�й���Ժ--%>
<logic:equal value="10355" name="xxdm">			
	<div class="formbox" id="zgmyxscj" style="display:none">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="7">
						�γ̳ɼ���¼
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
						�γ�����
					</td>
					<td>
						�γ�����
					</td>
					<td>
						�����ɼ�
					</td>
					<td>
						���޳ɼ�
					</td>
					<td>
						�����ɼ�
					</td>
				</tr>
			</thead>
			<tbody id="xskccj">
			</tbody>
		</table>
	</div>
</logic:equal>
<%--end�й���Ժ--%>

<%--��ɽʦ��ѧԺ--%>
<logic:equal value="10649" name="xxdm">				
	<div class="formbox" id="lssfxscj" style="display:none">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="7">
						�γ̳ɼ���¼
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
						�γ�����
					</td>
					<td>
						�γ�����
					</td>
					<td>
						�����ɼ�
					</td>
					<td>
						���޳ɼ�
					</td>
					<td>
						�����ɼ�
					</td>
				</tr>
			</thead>
			<tbody id="xskccj">
			</tbody>
		</table>
	</div>
</logic:equal>
<%--end��ɽʦ��ѧԺ--%>


<!--���㽭��ҵְҵ����ѧԺ-->
<logic:notPresent name="showZjszy">
<logic:notEqual value="10355" name="xxdm">	
<logic:notEqual value="10649" name="xxdm">		
<div class="formbox" id="xscj" style="display:none">
	<table width="100%" class="dateline">
		<thead>
			<tr>
				<td colspan="9">
					ѧ���ɼ�
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
					�γ�����
				</td>
				<td>
					�γ�����
				</td>
				<td>
					ѧ��
				</td>
				<td>
					�ɼ�
				</td>
				<td>
					�����ɼ�
				</td>
				<td>
					���޳ɼ�
				</td>
			</tr>
		</thead>
		<tbody id="xscjList">
		</tbody>
	</table>
	
	<table width="100%" class="dateline">
		<thead>
			<tr>
				<td colspan="9">
					�ȼ����Գɼ�
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
					�ȼ���������
				</td>
				<td>
					�ɼ�
				</td>
			</tr>
		</thead>
		<tbody id="djksList">
		</tbody>
	</table>
</div>
</logic:notEqual>
</logic:notEqual>
</logic:notPresent>

