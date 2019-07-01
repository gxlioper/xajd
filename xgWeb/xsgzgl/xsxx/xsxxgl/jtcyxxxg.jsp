<%@ page language="java" contentType="text/html; charset=GBK"%>
<table style="margin-bottom: 2px" width="100%" border="0"
	class="formlist">
	<tbody>
		<tr>
			<th width="13%">
				<div align="center">
					姓名
				</div>
			</th>
			<th>
				<div align="center">
					与本人关系
				</div>
			</th>
			<th>
				<div align="center">
					工作单位及地址
				</div>
			</th>
			<th>
				<div align="center">
					单位电话
				</div>
			</th>
			<th>
				<div align="center">
					个人电话
				</div>
			</th>
		</tr>
		<tr>
			<td align="center">
				<input type="text" name="jtcy1_xm" maxlength="10" value=""
					id="jtcy1_xm" style="width: 70px">
			</td>
			<td align="center">
				<select name="jtcy1_gx" id="jtcy1_gx" style="width: 80px">
					<option value=""></option>
					<option value="父亲">
						父亲
					</option>
					<option value="母亲">
						母亲
					</option>
					<option value="配偶">
						配偶
					</option>
					<option value="爷爷">
						爷爷
					</option>
					<option value="奶奶">
						奶奶
					</option>
					<option value="外公">
						外公
					</option>
					<option value="外婆">
						外婆
					</option>
					<option value="其他">
						其他
					</option>
					<option value="哥哥">
						哥哥
					</option>
				</select>
			</td>
			<td align="center">
				<input type="text" name="jtcy1_gzdz" maxlength="25" value=""
					id="jtcy1_gzdz" style="width: 200px">
			</td>
			<td align="center">
				<input type="text" name="jtcy1_lxdh2" maxlength="20" value=""
					onblur="checkPhone(this)" id="jtcy1_lxdh2" style="width: 110px">
			</td>
			<td align="center">
				<input type="text" name="jtcy1_lxdh1" maxlength="20" value=""
					onblur="checkPhone(this)" id="jtcy1_lxdh1" style="width: 110px">
			</td>
		</tr>
		<tr>
			<td align="center">
				<input type="text" name="jtcy2_xm" maxlength="10" value=""
					id="jtcy2_xm" style="width: 70px">
			</td>
			<td align="center">
				<select name="jtcy2_gx" id="jtcy2_gx" style="width: 80px">
					<option value=""></option>
					<option value="父亲">
						父亲
					</option>
					<option value="母亲">
						母亲
					</option>
					<option value="配偶">
						配偶
					</option>
					<option value="爷爷">
						爷爷
					</option>
					<option value="奶奶">
						奶奶
					</option>
					<option value="外公">
						外公
					</option>
					<option value="外婆">
						外婆
					</option>
					<option value="其他">
						其他
					</option>
					<option value="哥哥">
						哥哥
					</option>
				</select>
			</td>
			<td align="center">
				<input type="text" name="jtcy2_gzdz" maxlength="25" value=""
					id="jtcy2_gzdz" style="width: 200px">
			</td>
			<td align="center">
				<input type="text" name="jtcy2_lxdh2" maxlength="20" value=""
					onblur="checkPhone(this)" id="jtcy2_lxdh2" style="width: 110px">
			</td>
			<td align="center">
				<input type="text" name="jtcy2_lxdh1" maxlength="20" value=""
					onblur="checkPhone(this)" id="jtcy2_lxdh1" style="width: 110px">
			</td>
		</tr>
		<tr>
			<td align="center">
				<input type="text" name="jtcy3_xm" maxlength="10" value=""
					id="jtcy3_xm" style="width: 70px">
			</td>
			<td align="center">
				<select name="jtcy3_gx" id="jtcy3_gx" style="width: 80px">
					<option value=""></option>
					<option value="父亲">
						父亲
					</option>
					<option value="母亲">
						母亲
					</option>
					<option value="配偶">
						配偶
					</option>
					<option value="爷爷">
						爷爷
					</option>
					<option value="奶奶">
						奶奶
					</option>
					<option value="外公">
						外公
					</option>
					<option value="外婆">
						外婆
					</option>
					<option value="其他">
						其他
					</option>
					<option value="哥哥">
						哥哥
					</option>
				</select>
			</td>
			<td align="center">
				<input type="text" name="jtcy3_gzdz" maxlength="25" value=""
					id="jtcy3_gzdz" style="width: 200px">
			</td>
			<td align="center">
				<input type="text" name="jtcy3_lxdh2" maxlength="20" value=""
					onblur="checkPhone(this)" id="jtcy3_lxdh2" style="width: 110px">
			</td>
			<td align="center">
				<input type="text" name="jtcy3_lxdh1" maxlength="20" value=""
					onblur="checkPhone(this)" id="jtcy3_lxdh1" style="width: 110px">
			</td>
		</tr>
	</tbody>

</table>