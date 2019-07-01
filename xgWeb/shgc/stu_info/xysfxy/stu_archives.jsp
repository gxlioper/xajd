<%@ page language="java" contentType="text/html; charset=GBK"%>
<tr>
	<th>毕业时间</th>
	<td>
		<html:text name="rs" styleId="bynf" property="bynf" onclick="return showCalendar('bynf','y-mm-dd');" readonly='true'/>					
	</td>
	<th>生源地</th>
	<td>
		${rs.syd}					
	</td>
<tr>
	<th>档案是否在校</th>
	<td>
		<html:select name="rs" property="dasfzx" styleId="dasfzx"
				style="width:160px">
				<html:option value="不在校">不在校</html:option>
				<html:option value="在校">在校</html:option>
			</html:select>
	</td>
	<th>转移时间</th>
	<td>
		<html:text name="rs"
			onclick="return showCalendar('zysj','y-mm-dd');" property="zysj"
			styleId="zysj" readonly="true" />
	</td>
</tr>

<tr>
	<th>档案材料是否齐全</th>
	<td>
		<html:select name="rs" property="zlsfqq" styleId="zlsfqq"
				style="width:160px">
				<html:option value="是">是</html:option>
				<html:option value="否">否</html:option>
			</html:select>
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
	<th>转档单位名称</th>
	<td>
		<html:text name="rs" styleId="zddwmc" property="zddwmc" maxlength="50" />
	</td>
	<th>有无贷款纪录</th>
	<td>
		<html:select name="rs" property="dkjl" styleId="dkjl"
				style="width:160px">
				<html:option value="有">有</html:option>
				<html:option value="无">无</html:option>
			</html:select>
	</td>
</tr>
<tr>
	<th>学历</th>
	<td >
		<html:text name="rs" styleId="xl" property="xl" maxlength='10'/>
	</td>
	<th>单位性质</th>
	<td >
		<html:text name="rs" styleId="dwxz" property="dwxz" maxlength='10'/>
	</td>
</tr>
<tr>
	<th>备注</th>
	<td colspan="3">
		<html:textarea name="rs" styleId="bz" property="bz" rows="5" style="width:100%" onblur="chLeng(this,10)"/>
	</td>
</tr>
