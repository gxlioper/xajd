<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<div class="formbox" id="pjpy" style="display:none">
	<table width="100%" class="dateline">
		<thead>
			<tr>
				<td>ѧ��</td>
				<td>ѧ��</td>
				<td>5Sģ���</td>
				<td>���Ա���</td>
				<td>������</td>
				<td>IVT��̳��</td>
				<td>������</td>
				<td>��֯������</td>
				<td>���ʵ����</td>
				<td>�ۺ����ʷ�</td>
				<td>�ۺ����ʷ�����</td>
			</tr>
		</thead>
		<tbody id="zhcp">
			<logic:iterate id="zhcp" name="zhcpList">
			<tr>
				<td>${zhcp.xn}</td>
				<td>${zhcp.xqmc}</td>
				<td>${zhcp.wsmkf}</td>
				<td>${zhcp.yybdf}</td>
				<td>${zhcp.dshdf}</td>
				<td>${zhcp.ivtlt}</td>
				<td>${zhcp.wthd}</td>
				<td>${zhcp.zznl}</td>
				<td>${zhcp.shsj}</td>
				<td>${zhcp.zhszf}</td>
				<td>${zhcp.zhszfpm}</td>
			</tr>
			</logic:iterate>
		</tbody>
	</table>
	<br></br>
</div>
