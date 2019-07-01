<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_shxx')">
	<tr><th colspan="4" style="cursor:hand"><span>审核信息</span></th></tr>
</thead>
<tbody id="mk_shxx">
	<tr>
		<logic:equal value="xx" scope="request" name="userType">
			<th width="20%">用人单位审核</th>
			<td width="30%">
				${rs.xyyj }
			</td>
			<th width="20%">
				学校审核
			</th>
			<td width="30%">
				<html:select name="rs" property="yesNo" styleId="yesNo">
					<html:options collection="chkList" property="en"
						labelProperty="cn" />
				</html:select>
			</td>
		</logic:equal>
		<logic:equal value="xy" scope="request" name="userType">
			<th width="20%">
				用人单位审核
			</th>
			<td width="30%">
				<html:select name="rs" property="yesNo" styleId="yesNo">
					<html:options collection="chkList" property="en"
						labelProperty="cn" />
				</html:select>
			</td>
			<th width="20%">
				学校审核
			</th>
			<td width="30%">
				${rs.xxyj }
			</td>
		</logic:equal>
	</tr>
</tbody>
