<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<table width="100%">
	<logic:notEmpty name="lnzzInfo">
		<thead>
			<tr>
				<td>
					ѧ��
				</td>
				<td>
					����
				</td>
				<td>
					���
				</td>
			</tr>
		</thead>
		<logic:iterate name="lnzzInfo" id="zzInfo">
			<tr>
				<td>
					${zzInfo.xn }&nbsp;&nbsp;
				</td>
				<td>
					${zzInfo.zxjmc }&nbsp;&nbsp;
				</td>
				<td>
					${zzInfo.pzje }&nbsp;&nbsp;
				</td>
			</tr>
		</logic:iterate>
	</logic:notEmpty>
</table>