<%@ page language="java" contentType="text/html; charset=GBK"%>
<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>用户评定</span>
			</th>
		</tr>
	</thead>
	<tbody>	
		<tr style="height: 23px">
			<th align="right" width="15%">
				<font color="red">*</font>服务态度
			</th>
			<td align="left" width="35%">
				<html:select name="rs" property="fwtd" style="" styleId="fwtd" onchange="">
					<html:options collection="pjList" property="dm" labelProperty="mc" />
				</html:select>
			</td>
			<th align="right" width="15%">
				<font color="red">*</font>服务质量
			</th>
			<td align="left" width="35%">
				<html:select name="rs" property="fwzl" style="" styleId="fwzl" onchange="">
					<html:options collection="pjList" property="dm" labelProperty="mc" />
				</html:select>
			</td>
		</tr>
		<tr style="height: 23px">
			<th align="right" width="">
				<font color="red">*</font>是否及时
			</th>
			<td align="left" width="">
				<html:select name="rs" property="sfjs" style="" styleId="sfjs" onchange="">
					<html:options collection="pjList" property="dm" labelProperty="mc" />
				</html:select>
			</td>
			<th align="right" width="">
				<font color="red">*</font>现场清理
			</th>
			<td align="left" width="">
				<html:select name="rs" property="xcql" style="" styleId="xcql" onchange="">
					<html:options collection="pjList" property="dm" labelProperty="mc" />
				</html:select>
			</td>
		</tr>
		<tr style="height: 23px">
			<th align="right" width="">
				用户建议与意见
				<br>
				<font color="red">限录入500字</font>&nbsp;&nbsp;
			</th>
			<td align="left" colspan="3">
				<html:textarea name="rs" property="yhjy" styleId="yhjy" onblur="chLeng(this,500)" rows="5" style="width : 90%"></html:textarea>
			</td>
		</tr>
	</tbody>
</table>