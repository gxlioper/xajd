<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<body>
			
				<tr align="left" style="height:22px">
					<th align="right"  width="16%">
						���˼���
						<br />
						<span class="style1">(������400����)</span>
					</th>
					<td colspan="3">
					<logic:notEmpty name="rs" >
						<html:textarea name="rs" property='save_xxjl' style="width:99%" rows='5' />
					</logic:notEmpty>
					</td>
				</tr>
				<tr align="left" style="height:22px">
					<th align="right"  width="16%">
						�����
						<br />
						<span class="style1">(������400����)</span>
					</th>
					<td colspan="3">
					<logic:notEmpty name="rs" >
						<html:textarea name="rs" property='save_jfqk' style="width:99%" rows='5' />
					</logic:notEmpty>
					</td>
				</tr>
				<tr align="left" style="height:22px">
					<th align="right"  width="16%">
						��Ҫ�¼�
						<br />
						<span class="style1">(������400����)</span>
					</th>
					<td colspan="3">
						<html:textarea name="rs" property='save_zysj' style="width:99%" rows='5' />
				    </td>
				</tr>
</body>