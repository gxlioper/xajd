<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<table width="100%">
	<logic:notEmpty name="lnzzInfo">
		<thead>
			<tr>
				<td>
					学年
				</td>
				<td>
					名称
				</td>
				<td>
					金额
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