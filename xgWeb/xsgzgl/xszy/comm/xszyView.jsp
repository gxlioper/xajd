<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<tbody>
	<%
		HashMap<String,String> qsxxMap = (HashMap<String,String>)request.getAttribute("qsxxMap");
	%>
						<tr>
							<th width="20%">
								ְ����
							</th>
							<td width="30%">
								${qsxxMap.zgh}
							</td>
							<th width="20%">
								����
							</th>
							<td width="30%">
								${qsxxMap.xm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�Ա�
							</th>
							<td width="30%">
								${qsxxMap.xb}
							</td>
							<th width="20%">
								ְ��ְ��
							</th>
							<td width="30%">
								${qsxxMap.zwzc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��λ
							</th>
							<td width="30%">
								${qsxxMap.dwmc}
							</td>
							<th width="20%">
								��ϵ�绰
							</th>
							<td width="30%">
								${qsxxMap.lxdh}
							</td>
						</tr>
						<tr>
							<th width="20%">
								������ò
							</th>
							<td width="30%">
								${qsxxMap.zzmmmc}
							</td>
							<th width="20%">
								����
							</th>
							<td width="30%">
								${qsxxMap.dzyx}
							</td>
						</tr>
						<tr>
							<th width="20%">
								����Ҫ��
							</th>
							<td width="30%" colspan="3">
								${qsxxMap.dlyq}
							</td>							
						</tr>
</tbody>