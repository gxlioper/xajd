<%@ page language="java" contentType="text/html; charset=GBK"%>
		<!--��ʾ�μӸ�λ��ѧ���б�-->
		<thead>
		<tr>
			<td colspan="4" onclick="document.getElementById('tbodyXs').style.display=document.getElementById('tbodyXs').style.display=='none' ? '' : 'none'" align="center"><a href="#"><b>�μӸ�λ��ѧ��</b></a></td>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td colspan="4">
			<table id="tbodyXs" width="100%" class="formlist">
				<thead>
					<tr>
						<th>
							ѧ��
						</th>
						<th>
							����
						</th>
						<th>
							�Ա�	
						</th>
						<th>
							�༶
						</th>
					</tr>
				</thead>
				<tbody>
				<logic:iterate id="v" name="xsList">			
					<tr>
						<td>
							<bean:write name="v" property="xh"/>
						</td>
						<td>
							<bean:write name="v" property="xm"/>	
						</td>
						<td>
							<bean:write name="v" property="xb"/>	
						</td>
						<td>
							<bean:write name="v" property="bjmc"/>	
						</td>
					</tr>
				</logic:iterate>	
				</tbody>			
			</table>
		</td>
		</tr>
		</tbody>
		<!--end��ʾ�μӸ�λ��ѧ���б�-->			
