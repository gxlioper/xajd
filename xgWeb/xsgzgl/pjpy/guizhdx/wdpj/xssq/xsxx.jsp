<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_xsxx')">
	<tr><th colspan="4" style="cursor:hand"><span>ѧ��������Ϣ</span></th></tr>
</thead>
<tbody id="mk_xsxx">
	<tr>
		<th width="20%">ѧ��</th>
		<td width="30%">
			${wdpjXssqInfo.stuInfo.xh }
		</td>
		<th width="20%">����</th>
		<td width="30%">${wdpjXssqInfo.stuInfo.xm }</td>
	</tr>
	<tr>
		<th>�Ա�</th>
		<td>${wdpjXssqInfo.stuInfo.xb }</td>
		<th>������ò</th>
		<td>${wdpjXssqInfo.stuInfo.zzmmmc }</td>
	</tr>
	<tr>
		<th>�꼶</th>
		<td>${wdpjXssqInfo.stuInfo.nj }</td>
		<th><bean:message key="lable.xb" /></th>
		<td>${wdpjXssqInfo.stuInfo.pjxymc }</td>
	</tr>
	<tr>
		<th>רҵ</th>
		<td>${wdpjXssqInfo.stuInfo.pjzymc }</td>
		<th>�༶</th>
		<td>${wdpjXssqInfo.stuInfo.pjbjmc }</td>
	</tr>
	<tr>
		<th>���֤��</th>
		<td>${wdpjXssqInfo.stuInfo.sfzh }</td>
		<th>�ֻ�����</th>
		<td>${wdpjXssqInfo.stuInfo.sjhm }</td>
	</tr>
</tbody>
