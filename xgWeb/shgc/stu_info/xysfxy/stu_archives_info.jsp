<%@ page language="java" contentType="text/html; charset=GBK"%>
<tr>
	<th>档案来源</th>
	<td>
		<html:text name="rs" property="daly" styleId="daly" maxlength="100" />
	</td>
	<th>档案材料是否齐全</th>
	<td>
		<html:select name="rs" property="zlsfqq" styleId="zlsfqq"
				style="width:160px">
				<html:option value="是">是</html:option>
				<html:option value="否">否</html:option>
		</html:select>
	</td>
</tr>
<tr>
	<th>接收人</th>
	<td>
		<html:text name="rs" property="jsr" styleId="jsr" maxlength="10" />
	</td>
	<th>管理人</th>
	<td>
		<html:text name="rs" property="glr" styleId="glr" maxlength="10" />
	</td>
</tr>
<tr>
	<th>档案保存位置</th>
	<td>
		<html:text name="rs" property="dabcwz" styleId="dabcwz" maxlength="112" />
	</td>
	<th>是否有党员材料</th>
	<td>
		<html:select name="rs" property="sfydycl" styleId="sfydycl"
				style="width:160px">
				<html:option value="是">是</html:option>
				<html:option value="否">否</html:option>
			</html:select>
	</td>
</tr>
<tr>
	<th>备注</th>
	<td colspan="3">
		<html:textarea name="rs" property="bz" styleId="bz" rows="5" style="width:100%" onblur="checkLen(this,300)"/>
	</td>
</tr>
							
