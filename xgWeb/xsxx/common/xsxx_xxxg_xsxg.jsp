<%@ page language="java" contentType="text/html; charset=GBK"%>
<logic:notEmpty name="gwcxview">
	<div class="tab">
		<table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4"><span>ѧ��������Ϣ</span></th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th>ѧ��</th>
				<td>
					${userName}
				</td>
				<th>����</th>
				<td>
					${rs.xm}
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					${rs.xymc}
				</td>
				<th>רҵ</th>
				<td >
					${rs.zymc}
				</td>
			</tr>
			<tr>
				<th>�꼶</th>
				<td>
					${rs.nj}
				</td>
				<th>�༶</th>
				<td>
					${rs.bjmc}
				</td>
			</tr>
			<%--������ְͨҵ����ѧԺ--%>
				<logic:equal value="12752" name="xxdm">
				<tr>
					<th>������</th>
					<td>
						<html:text property="dah" name="rs" styleId="dah" maxlength="30"/>
					</td>
					<th>ҽ�Ʊ��պ�</th>
					<td>
						<html:text property="ylbxh" name="rs" styleId="ylbxh" maxlength="30"/>
					</td>
				</tr>
				<tr>
					<th>QQ����</th>
					<td>
						<html:text property="qqhm" name="rs" styleId="qqhm" maxlength="30" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<th></th>
					<td>
						
					</td>
				</tr>					
				</logic:equal>
				<%--end������ְͨҵ����ѧԺ--%>
			<tr>
				<th>������ϵ�绰</th>
				<td>
					<input type="text" style="width:200px" name="lxdh1" id="lxdh1"
						value="<bean:write name="rs" property="lxdh1"/>"
						maxlength="13" onkeyup="value=value.replace(/[^\d]/g,'') " />
				</td>
				<th>��������</th>
				<td>
					<input type="text" style="width:200px" name="email" id="dzyx"
						value="<bean:write name="rs" property="dzyx"/>" />
				</td>				
			</tr>
			<tr>
				<th>�ֻ�����</th>
				<td>
					<input type="text" style="width:200px" name="sjhm" id="sjhm"
						value="<bean:write name="rs" property="sjhm"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11" />
				</td>
				<th></th>
				<td>
					
				</td>
			</tr>
			<tr>
				<th>��������</th>
				<td>
					<html:select name="rs" property="yhdm" styleId="yhdm" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="yhList" property="yhdm" labelProperty="yhmc" />
					</html:select>
				</td>
				<th>���п���</th>
				<td>
					<input type="text" style="width:200px" name="yhkh" id="yhkh"
						value="<bean:write name="rs" property="yhkh"/>"
						onkeyup="value=value.replace(/[^\d|-]/g,'') " maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>��ͥ��ϵ�绰</th>
				<td>
					<input type="text" style="width:200px" name="lxdh2" id="lxdh2"
						value="<bean:write name="rs" property="lxdh2"/>"
						onkeyup="value=value.replace(/[^\d|-]/g,'') " maxlength="13" />
				</td>
				
				<th>��ͥ�ʱ�</th>
				<td>
					<input type="text" style="width:200px" name="yb" id="yb"
						value="<bean:write name="rs" property="yb"/>" maxlength="10"
						onkeyup="value=value.replace(/[^\d]/g,'') " />
				</td>
			</tr>
			<tr>
				<th>
					<!--��ɽʦ��ѧԺ-->
					<logic:equal value="10649" name="xxdm">
						��ͥͨѶ��ַ
					</logic:equal>
					<!--end��ɽʦ��ѧԺ-->
					<!--����ɽʦ��ѧԺ-->
					<logic:notEqual value="10649" name="xxdm">
						��ͥ���ڵ�
					</logic:notEqual>
					<!--end����ɽʦ��ѧԺ-->
				</th>
				<td colspan="3">
					<input type="text" style="width:400px" name="jtszd" id="jtszd"
						value="<bean:write name="rs" property="jtszd"/>"
						maxlength="120" />
				</td>
			</tr>
<!--			<tr>-->
<!--				<th>����ͨ�ŵ�ַ</th>-->
<!--				<td colspan="3">-->
<!--					<input type="text" style="width:610px" name="brtxdz"-->
<!--						id="brtxdz" value="<bean:write name="rs" property="brtxdz"/>"-->
<!--						maxlength="50" />-->
<!--				</td>-->
<!--			</tr>-->
			</tbody>
			<thead>
				<tr>
					<th colspan="4" style="cursor:hand" align="center"
						onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
						<span>ѧ����ͥ��Ա��Ϣ1</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr id="jt1">
				<td colspan="4">
					<table width="100%" class="formlist">
						<tbody>
						<tr>
							<th>����</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_xm"
									id="jtcy1_xm"
									value="<bean:write name="rs" property="jtcy1_xm"/>"
									maxlength="25" />
							</td>
							<th>�뱾�˹�ϵ</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_gx"
									id="jtcy1_gx"
									value="<bean:write name="rs" property="jtcy1_gx"/>"
									maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_nl"
									id="jtcy1_nl"
									value="${rs.jtcy1_nl}"
									maxlength="20" 
									onclick="return showCalendar('jtcy1_nl','y-mm-dd');" />
							</td>
							<th>���֤����</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_sfzh"
									id="jtcy1_sfzh"
									value="<bean:write name="rs" property="jtcy1_sfzh"/>"
									maxlength="20" />
							</td>							
						</tr>
						<%--�Ƕ�����ҵ��ѧ--%>
						<logic:notEqual value="10225" name="xxdm">
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
							<td>
								<html:select name="rs" property="jtcy1_zzmm"
									styleId="jtcy1_zzmm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
						</logic:notEqual>
						<%--end�Ƕ�����ҵ��ѧ--%>
						<tr>
							<th>ְҵ</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_zy"
									id="jtcy1_zy"
									value="<bean:write name="rs" property="jtcy1_zy"/>"
									maxlength="10" />
							</td>
							<th>ְ��</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_zw"
									id="jtcy1_zw"
									value="<bean:write name="rs" property="jtcy1_zw"/>"
									maxlength="10" />
							</td>							
						</tr>
						<tr>
							<th>������λ�绰</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_lxdh2"
									id="jtcy1_lxdh2"
									value="<bean:write name="rs" property="jtcy1_lxdh2"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
							<th>���˵绰</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_lxdh1"
									id="jtcy1_lxdh1"
									value="<bean:write name="rs" property="jtcy1_lxdh1"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
						</tr>
						<tr>
							<th>������ַ</th>
							<td colspan="3">
								<input type="text" style="width:610px" name="jtcy1_gzdz"
									id="jtcy1_gzdz"
									value="<bean:write name="rs" property="jtcy1_gzdz"/>"
									maxlength="25" />
							</td>
						</tr>
						<!--������ְͨҵ����ѧԺ-->
						<logic:equal value="12752" name="xxdm">
						<tr>
							<th>�ڽ�����</th>
							<td colspan="3">
								<html:text name="rs" property="jtcy1_zjxy"
									styleId="jtcy1_zjxy" maxlength="50"/>
							</td>
						</tr>
						</logic:equal>
						<!--end������ְͨҵ����ѧԺ-->
						</tbody>
					</table>
				</td>
			</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4" style="cursor:hand" align="center"
						onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
						<span>ѧ����ͥ��Ա��Ϣ2</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr id="jt2" style="display:none">
				<td colspan="4">
					<table width="100%" class="formlist">
						<tbody>
						<tr>
							<th>����</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_xm"
									id="jtcy2_xm"
									value="<bean:write name="rs" property="jtcy2_xm"/>"
									maxlength="25" />
							</td>
							<th>�뱾�˹�ϵ</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_gx"
									id="jtcy2_gx"
									value="<bean:write name="rs" property="jtcy2_gx"/>"
									maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_nl"
									id="jtcy2_nl"
									value="${rs.jtcy2_nl}"
									maxlength="20" 
									onclick="return showCalendar('jtcy2_nl','y-mm-dd');" />
							</td>
							<th>���֤����</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_sfzh"
									id="jtcy2_sfzh"
									value="<bean:write name="rs" property="jtcy2_sfzh"/>"
									maxlength="20" />
							</td>							
						</tr>
						<%--�Ƕ�����ҵ��ѧ--%>
						<logic:notEqual value="10225" name="xxdm">
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
							<td>
								<html:select name="rs" property="jtcy2_zzmm"
									styleId="jtcy2_zzmm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
						</logic:notEqual>
						<%--end�Ƕ�����ҵ��ѧ--%>
						<tr>
							<th>ְҵ</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_zy"
									id="jtcy2_zy"
									value="<bean:write name="rs" property="jtcy2_zy"/>"
									maxlength="10" />
							</td>
							<th>ְ��</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_zw"
									id="jtcy2_zw"
									value="<bean:write name="rs" property="jtcy2_zw"/>"
									maxlength="10" />
							</td>
							
						</tr>
						<tr>						
							<th>������λ�绰</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_lxdh2"
									id="jtcy2_lxdh2"
									value="<bean:write name="rs" property="jtcy2_lxdh2"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
							<th>���˵绰</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_lxdh1"
									id="jtcy2_lxdh1"
									value="<bean:write name="rs" property="jtcy2_lxdh1"/>"
									maxlength="13" onkeyup="value=value.replace(/[^\d]/g,'') " />
							</td>
						</tr>
						<tr>
							<th>������ַ</th>
							<td colspan="3">
								<input type="text" style="width:610px" name="jtcy2_gzdz"
									id="jtcy2_gzdz"
									value="<bean:write name="rs" property="jtcy2_gzdz"/>"
									maxlength="25" />
							</td>
						</tr>
						<!--������ְͨҵ����ѧԺ-->
						<logic:equal value="12752" name="xxdm">
						<tr>
							<th>�ڽ�����</th>
							<td colspan="3">
								<html:text name="rs" property="jtcy2_zjxy"
									styleId="jtcy2_zjxy" maxlength="50"/>
							</td>
						</tr>
						</logic:equal>
						<!--end������ְͨҵ����ѧԺ-->
					</tbody>
					</table>
				</td>
			</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4" style="cursor:hand" align="center"
						onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
						<span>ѧ����ͥ��Ա��Ϣ3</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr id="jt3" style="display:none">
				<td colspan="4">
					<table width="100%" class="formlist">
					<tbody>
						<tr>
							<th>����</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_xm"
									id="jtcy3_xm"
									value="<bean:write name="rs" property="jtcy3_xm"/>"
									maxlength="16" />
							</td>
							<th>�뱾�˹�ϵ</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_gx"
									id="jtcy3_gx"
									value="<bean:write name="rs" property="jtcy3_gx"/>"
									maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_nl"
									id="jtcy3_nl"
									value="${rs.jtcy3_nl}"
									maxlength="20" 
									onclick="return showCalendar('jtcy3_nl','y-mm-dd');" />
							</td>
							<th>���֤����</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_sfzh"
									id="jtcy3_sfzh"
									value="<bean:write name="rs" property="jtcy3_sfzh"/>"
									maxlength="20" />
							</td>
							
						</tr>
						<%--�Ƕ�����ҵ��ѧ--%>
						<logic:notEqual value="10225" name="xxdm">
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
							<td>
								<html:select name="rs" property="jtcy3_zzmm"
									styleId="jtcy3_zzmm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
						</logic:notEqual>
						<%--end�Ƕ�����ҵ��ѧ--%>
						<tr>
							<th>ְҵ</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_zy"
									id="jtcy3_zy"
									value="<bean:write name="rs" property="jtcy3_zy"/>"
									maxlength="10" />
							</td>
							<th>ְ��</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_zw"
									id="jtcy3_zw"
									value="<bean:write name="rs" property="jtcy3_zw"/>"
									maxlength="10" />
							</td>							
						</tr>
						<tr>
							<th>������λ�绰</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_lxdh2"
									id="jtcy3_lxdh2"
									value="<bean:write name="rs" property="jtcy3_lxdh2"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
							<th>���˵绰</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_lxdh1"
									id="jtcy3_lxdh1"
									value="<bean:write name="rs" property="jtcy3_lxdh1"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
						</tr>
						<tr>
							<th>������ַ</th>
							<td colspan="3">
								<input type="text" style="width:610px" name="jtcy3_gzdz"
									id="jtcy3_gzdz"
									value="<bean:write name="rs" property="jtcy3_gzdz"/>"
									maxlength="25" />
							</td>
						</tr>
					   <!--������ְͨҵ����ѧԺ-->
						<logic:equal value="12752" name="xxdm">
						<tr>
							<th>�ڽ�����</th>
							<td colspan="3">
								<html:text name="rs" property="jtcy3_zjxy"
									styleId="jtcy3_zjxy" maxlength="50"/>
							</td>
						</tr>
						</logic:equal>
						<!--end������ְͨҵ����ѧԺ-->
					</tbody>
					</table>
				</td>
			</tr>
		</tbody>
		<tfoot>
	      <tr>
	        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
	          <div class="btn">
	            <button type="button" class="button2"
					onclick="saveInfo()">
					�� �� �� Ϣ
				</button>
	          </div>
	        </td>
	      </tr>
	    </tfoot>
		</table>
	</logic:notEmpty>