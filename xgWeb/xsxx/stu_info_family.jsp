<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead>
	<tr>
		<th colspan="5" onclick="document.getElementById('hi_jtxx').style.display=(document.getElementById('hi_jtxx').style.display==''?'none':'')"><span>ѧ����ͥ��Ϣ</span>
		</th></tr>
</thead>
<tbody id="hi_jtxx">
	<tr>
		<th width="10%">��ͥ�绰</th>
		<td width="30%">
			<html:text name="rs" property="lxdh1" maxlength="25" styleId="lxdh1"/>
		</td>	
		<th width="10%">��������</th>
		<td colspan="2">
			<html:text property="jtyb" maxlength="10" styleId="jtyb" value="${rs.yb }"
				onkeyup="value=value.replace(/[^\d]/g,'') "/>
		</td>
	</tr>
	<logic:equal name="xxdm" value="11733">
		<tr>
			<th>��ͥ��������</th>
			<td>
				<html:select property="jjzk" value="${rs.jjzk }">
					<html:option value=""></html:option>
						<html:options collection="knlxList" property="dm" labelProperty="mc"/>
				</html:select>
			</td>
			<th></th>
			<td colspan="2"></td>
		</tr>
	</logic:equal>
	<tr>
		<th>��ͥ��ַ</th>
		<td colspan="4">
			<html:text name="rs" property="jtszd" maxlength="25" styleId="jtszd" style="width:90%"/>
		</td>
	</tr>
	<logic:notEqual name="xxdm" value="11733">
	<tr>
		<th>��ͥ����״��</th>
		<td colspan="4">
			<html:textarea name="rs" property="jjzk" style="width:600px;height:45" onblur="chLeng(this,100)" styleId="jjzk"/>
		</td>
	</tr>
	</logic:notEqual>
</tbody>


<thead>
	<tr>
		<th style="cursor:hand" align="center" colspan="5"
			onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
				<span>ѧ����ͥ��Ա��Ϣ1</span>
		</th>
	</tr>
</thead>
<tbody id="jt1" >
	<tr>
		<th>����</th>
		<td>
			<html:text name="rs" property="jtcy1_xm" styleId="xm" maxlength="25"/>
		</td>
		<th>�뱾�˹�ϵ</th>
		<td colspan="2">
			<html:select name="rs" property="jtcy1_gx" styleId="ch">
			<html:option value="" />
			<html:options collection="jtgxList" labelProperty="mc" property="mc"/>
			</html:select>						
		</td>
	</tr>
	<tr>
		<th>��������</th>
		<td>
			<html:text name="rs" property="jtcy1_nl" styleId="nl" readonly="true" onclick="return showCalendar('jtcy1_nl','y-mm-dd');" />
		</td>
		<th>���֤��</th>
		<td colspan="2">
			<html:text name="rs" property="jtcy1_sfzh" styleId="sfzh" maxlength="20"/>
		</td>
	</tr>
	<tr>
		<th>����</th>
		<td>
			<html:select name="rs" property="jtcy1_mz"
				styleId="jtcy1_mz" style="width:150px">
				<html:option value=""></html:option>
				<html:options collection="mzList" property="mzdm"
					labelProperty="mzmc" />
			</html:select>
		</td>
		<th>������ò</th>
		<td colspan="2">
			<html:select name="rs" property="jtcy1_zzmm"
				styleId="jtcy1_zzmm" style="width:150px">
				<html:option value=""></html:option>
				<html:options collection="zzmmList" property="zzmmdm"
					labelProperty="zzmmmc" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th>ְҵ</th>
		<td>
			<html:text name="rs" property="jtcy1_zy"
				styleId="jtcy1_zy" maxlength="10"/>
		</td>
		<th>ְ��</th>
		<td colspan="2">
			<html:text name="rs" property="jtcy1_zw"
				styleId="jtcy1_zw" maxlength="32"/>
		</td>
	</tr>
	<tr>
		<th>������λ�绰</th>
		<td>
			<html:text name="rs" property="jtcy1_lxdh2"
				styleId="lxdh2" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
		</td>
		<th>���˵绰</th>
		<td colspan="2">
			<html:text name="rs" property="jtcy1_lxdh1"
				styleId="lxdh1" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20"/>
		</td>
	</tr>
	<tr>
		<th>������ַ</th>
		<td colspan="4">
			<html:text name="rs" property="jtcy1_gzdz" styleId="gzdz" maxlength="32" style="width:90%"/>
		</td>
	</tr>
</tbody>


<thead>
	<tr>
		<th style="cursor:hand" align="center" colspan="5"
			onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
			<span>ѧ����ͥ��Ա��Ϣ2</span>
		</th>
	</tr>
</thead>
<tbody id="jt2">
	<tr>
		<th>����</th>
		<td>
			<html:text name="rs" property="jtcy2_xm" styleId="jtcy2_xm" maxlength="25"/>
		</td>
		<th>�뱾�˹�ϵ</th>
		<td colspan="2">
			<html:select name="rs" property="jtcy2_gx" styleId="jtcy2_gx">
				<html:option value="" />
				<html:options collection="jtgxList" labelProperty="mc" property="mc"/>
			</html:select>	
		</td>
	</tr>
	<tr>
		<th>��������</th>
		<td>
			<html:text name="rs" property="jtcy2_nl"
				styleId="jtcy2_nl"
				onclick="return showCalendar('jtcy2_nl','y-mm-dd');" readonly="true"/>
		</td>
		<th>���֤��</th>
		<td colspan="2">
			<html:text name="rs" property="jtcy2_sfzh" maxlength="20"
				styleId="jtcy2_sfzh" />
		</td>
	</tr>
	<tr>
		<th>����</th>
		<td>
			<html:select name="rs" property="jtcy2_mz"
				styleId="jtcy2_mz" style="width:150px">
				<html:option value=""></html:option>
				<html:options collection="mzList" property="mzdm"
					labelProperty="mzmc" />
			</html:select>
		</td>
		<th>������ò</th>
		<td colspan="2">
			<html:select name="rs" property="jtcy2_zzmm"
				styleId="jtcy2_zzmm" style="width:150px">
				<html:option value=""></html:option>
				<html:options collection="zzmmList" property="zzmmdm"
					labelProperty="zzmmmc" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th>ְҵ</th>
		<td>
			<html:text name="rs" property="jtcy2_zy"
				styleId="jtcy2_zy" maxlength="10"/>
		</td>
		<th>ְ��</th>
		<td colspan="2">
			<html:text name="rs" property="jtcy2_zw"
				styleId="jtcy2_zw" maxlength="10"/>
		</td>
	</tr>
	<tr>
		<th>������λ�绰</th>
		<td>
			<html:text name="rs" property="jtcy2_lxdh2" onkeyup="value=value.replace(/[^\d]/g,'') "
				styleId="jtcy2_lxdh2" maxlength="25"/>
		</td>
		<th>���˵绰</th>
		<td colspan="2">
			<html:text name="rs" property="jtcy2_lxdh1" onkeyup="value=value.replace(/[^\d]/g,'') "
				styleId="jtcy2_lxdh1" maxlength="25"/>
		</td>
	</tr>
	<tr>
		<th>������ַ</th>
		<td colspan="4">
			<html:text name="rs" property="jtcy2_gzdz"
				styleId="jtcy2_gzdz" maxlength="25"  style="width:90%"/>
		</td>
	</tr>
</tbody>

<thead>
	<tr>
		<th style="cursor:hand" align="center" colspan="5"
			onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
			<span>ѧ����ͥ��Ա��Ϣ3</span>
		</th>
	</tr>
</thead>
<tbody id="jt3">
	<tr>
		<th>����</th>
		<td>
			<html:text name="rs" property="jtcy3_xm" styleId="jtcy3_xm" maxlength="25"/>
		</td>
		<th>�뱾�˹�ϵ</th>
		<td colspan="2">
			<html:select name="rs" property="jtcy3_gx" styleId="jtcy3_gx">
				<html:option value="" />
				<html:options collection="jtgxList" labelProperty="mc" property="mc"/>
			</html:select>			
		</td>
		</tr>
		<tr>
			<th>��������</th>
			<td>
				<html:text name="rs" property="jtcy3_nl"
					styleId="jtcy3_nl"
					onclick="return showCalendar('jtcy3_nl','y-mm-dd');" />
			</td>
			<th>���֤��</th>
			<td colspan="2">
				<html:text name="rs" property="jtcy3_sfzh" maxlength="20"
					styleId="jtcy3_sfzh" />
			</td>
		</tr>
		<tr>
			<th>����</th>
			<td>
				<html:select name="rs" property="jtcy3_mz"
					styleId="jtcy3_mz" style="width:150px">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm"
						labelProperty="mzmc" />
				</html:select>
			</td>
			<th>������ò</th>
			<td colspan="2">
				<html:select name="rs" property="jtcy3_zzmm"
					styleId="jtcy3_zzmm" style="width:150px">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm"
						labelProperty="zzmmmc" />
				</html:select>
			</td>
		</tr>
		<tr>
			<th>ְҵ</th>
			<td>
				<html:text name="rs" property="jtcy3_zy"
					styleId="jtcy3_zy" maxlength="10"/>
			</td>
			<th>ְ��</td>
			<td colspan="2">
				<html:text name="rs" property="jtcy3_zw"
					styleId="jtcy3_zw" maxlength="10"/>
			</td>
		</tr>
		<tr>
			<th>������λ�绰</th>
			<td>
				<html:text name="rs" property="jtcy3_lxdh2" onkeyup="value=value.replace(/[^\d]/g,'') "
					styleId="jtcy3_lxdh2" maxlength="25"/>
			</td>
			<th>���˵绰</th>
			<td colspan="2">
				<html:text name="rs" property="jtcy3_lxdh1" onkeyup="value=value.replace(/[^\d]/g,'') "
					styleId="jtcy3_lxdh1" maxlength="25"/>
			</td>
		</tr>
		<tr>
				<th>������ַ</th>
				<td colspan="4">
					<html:text name="rs" property="jtcy3_gzdz"
						styleId="jtcy3_gzdz" maxlength="25"  style="width:90%"/>
				</td>
		</tr>
</tbody>
