<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<tbody>
	<%
		HashMap<String,String> qsxxMap = (HashMap<String,String>)request.getAttribute("qsxxMap");
	%>
						<tr>
							<th width="20%">
								职工号
							</th>
							<td width="30%">
								${qsxxMap.zgh}
							</td>
							<th width="20%">
								姓名
							</th>
							<td width="30%">
								${qsxxMap.xm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								性别
							</th>
							<td width="30%">
								${qsxxMap.xb}
							</td>
							<th width="20%">
								职务职称
							</th>
							<td width="30%">
								${qsxxMap.zwzc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								单位
							</th>
							<td width="30%">
								${qsxxMap.dwmc}
							</td>
							<th width="20%">
								联系电话
							</th>
							<td width="30%">
								${qsxxMap.lxdh}
							</td>
						</tr>
						<tr>
							<th width="20%">
								政治面貌
							</th>
							<td width="30%">
								${qsxxMap.zzmmmc}
							</td>
							<th width="20%">
								邮箱
							</th>
							<td width="30%">
								${qsxxMap.dzyx}
							</td>
						</tr>
						<tr>
							<th width="20%">
								大类要求
							</th>
							<td width="30%" colspan="3">
								${qsxxMap.dlyq}
							</td>							
						</tr>
</tbody>