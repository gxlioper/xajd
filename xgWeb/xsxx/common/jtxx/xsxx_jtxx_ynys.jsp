<%@ page language="java" contentType="text/html; charset=GBK"%>
<%--��������ѧԺ--%>
<logic:equal value="10690" name="xxdm">
	<thead>
		<tr>
			<td style="cursor:hand" align="center"
				onclick="document.getElementById('jt4').style.display=(document.getElementById('jt4').style.display==''?'none':'')">
				ѧ����ͥ��Ա��Ϣ4
			</td>
		</tr>
	</thead>
	<tbody>
	<tr id="jt4" style="display:none">
		<td>
			<table width="100%" class="formlist">
				<tbody>				
				<tr>
					<th>����</th>
					<td>
						<html:text name="rs" property="jtcy4_xm"
							styleId="jtcy4_xm" maxlength="10"/>
					</td>
					<th>�뱾�˹�ϵ</th>
					<td>
						<html:text name="rs" property="jtcy4_gx"
							styleId="jtcy4_gx" maxlength="10" />
					</td>
				</tr>
				<tr>
					<th>��������</th>
					<td>
						<html:text name="rs" property="jtcy4_nl"
							styleId="jtcy4_nl"
							onclick="return showCalendar('jtcy4_nl','y-mm-dd');" />
					</td>
					<th>���֤��</th>
					<td>
						<html:text name="rs" property="jtcy4_sfzh"
							styleId="jtcy4_sfzh" />
					</td>
				</tr>
				<tr>
					<th>����</th>
					<td>
						<html:select name="rs" property="jtcy4_mz"
							styleId="jtcy4_mz" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
					</td>
					<th>������ò</th>
					<td>
						<html:select name="rs" property="jtcy4_zzmm"
							styleId="jtcy4_zzmm" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>������λ</th>
					<td>
						<html:text name="rs" property="jtcy4_zy"
							styleId="jtcy4_zy" maxlength="10"/>
					</td>
					<th>ְ��</th>
					<td>
						<html:text name="rs" property="jtcy4_zw"
							styleId="jtcy4_zw" maxlength="32"/>
					</td>
				</tr>
				<tr>
					<th>������λ�绰</th>
					<td>
						<html:text name="rs" property="jtcy4_lxdh2"
							styleId="jtcy4_lxdh2" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<th>���˵绰</th>
					<td>
						<html:text name="rs" property="jtcy4_lxdh1"
							styleId="jtcy4_lxdh1" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr>
					<th>��λ��ַ</th>
					<td>
						<html:text name="rs" property="jtcy4_gzdz"
							styleId="jtcy4_gzdz" maxlength="32"/>
					</td>
					<th>������λ�ʱ�</th>
					<td>
						<html:text name="rs" property="jtcy4_yzbm"
							styleId="jtcy4_yzbm" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'') " />
					</td>
				</tr>
			</tbody>
		</table>
	</td>
</tr>
</tbody>

<thead>
	<tr>
		<td style="cursor:hand" align="center"
			onclick="document.getElementById('jt5').style.display=(document.getElementById('jt5').style.display==''?'none':'')">
			ѧ����ͥ��Ա��Ϣ5
		</td>
	</tr>
</thead>
<tbody>
<tr id="jt5" style="display:none">
	<td>
	<table width="100%" class="formlist">
		<tbody>
			<tr>
				<th>����</th>
				<td>
					<html:text name="rs" property="jtcy5_xm"
						styleId="jtcy5_xm" maxlength="10"/>
				</td>
				<th>�뱾�˹�ϵ</th>
				<td>
					<html:text name="rs" property="jtcy5_gx"
						styleId="jtcy5_gx" maxlength="10"/>
				</td>
			</tr>
			<tr>
				<th>��������</th>
				<td>
					<html:text name="rs" property="jtcy5_nl"
						styleId="jtcy5_nl"
						onclick="return showCalendar('jtcy5_nl','y-mm-dd');" />
				</td>
				<th>���֤��</th>
				<td>
					<html:text name="rs" property="jtcy5_sfzh"
						styleId="jtcy5_sfzh" />
				</td>
			</tr>
			<tr>
				<th>����</th>
				<td>
					<html:select name="rs" property="jtcy5_mz"
						styleId="jtcy5_mz" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="mzList" property="mzdm"
							labelProperty="mzmc" />
					</html:select>
				</td>
				<th>������ò</th>
				<td>
					<html:select name="rs" property="jtcy5_zzmm"
						styleId="jtcy5_zzmm" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="zzmmList" property="zzmmdm"
							labelProperty="zzmmmc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<th>������λ</th>
				<td>
					<html:text name="rs" property="jtcy5_zy"
						styleId="jtcy5_zy" maxlength="10"/>
				</td>
				<th>ְ��</th>
				<td>
					<html:text name="rs" property="jtcy5_zw"
						styleId="jtcy5_zw" maxlength="32"/>
				</td>
			</tr>
			<tr>
				<th>������λ�绰</th>
				<td>
					<html:text name="rs" property="jtcy5_lxdh2"
						styleId="jtcy5_lxdh2" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20"/>
				</td>
				<th>���˵绰</th>
				<td>
					<html:text name="rs" property="jtcy5_lxdh1"
						styleId="jtcy5_lxdh1" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20"/>
				</td>
			</tr>
			<tr>
				<th>��λ��ַ</th>
				<td>
					<html:text name="rs" property="jtcy5_gzdz"
						styleId="jtcy5_gzdz" maxlength="32"/>
				</td>
				<th>������λ�ʱ�</th>
				<td>
					<html:text name="rs" property="jtcy3_yzbm"
						styleId="jtcy5_yzbm" maxlength="10"
						onkeyup="value=value.replace(/[^\d]/g,'') " />
				</td>
			</tr>
			</tbody>		
		</table>
		</td>
	</tr>
</tbody>
</logic:equal>
<%--end��������ѧԺ--%>