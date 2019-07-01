<%@ page language="java" contentType="text/html; charset=GBK"%>
<logic:equal name="userOnLine" value="teacher" scope="session">
	<logic:equal name="userType" value="xy" scope="session">
		<logic:present name="rs1">
			<div class="tab">
		  	<table width="100%" border="0" class="formlist">	
				<thead>
					<tr>
						<th colspan="4">
							<span>ѧ��������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>ѧ��</th>
					<td>
						<html:text name="rs1" property="xh" styleId="xh"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</td>
					<th>����</th>
					<td>
						${rs1.xm}
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs1.xymc}
					</td>
					<th>רҵ</th>
					<td>
						${rs1.zymc}
					</td>
				</tr>
				<tr>
					<th>�꼶</th>
					<td>
						${rs1.nj}
					</td>
					<th>�༶</th>
					<td>
						${rs1.bjmc}
					</td>
				</tr>
				<logic:empty name="gwcxview">
					<%--������ҵ��ѧ--%>
					<logic:equal value="10225" name="xxdm">
						<tr>
							<th>��ϵ�绰</th>
							<td>
								<input type="text" style="width:200px" name="lxdh1" id="lxd1"
									value="<bean:write name="rs" property="lxdh"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
							<th>�ֻ�����</th>
							<td>
								<input type="text" style="width:200px" name="sjhm" id="sjhm"
									value="<bean:write name="rs" property="sjhm"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11" />
							</td>
						</tr>
						<tr>
							<th>E-Mail</th>
							<td>
								<input type="text" style="width:200px" name="email" id="dzyx"
									value="<bean:write name="rs" property="dzyx"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="32" />
							</td>
							<th>��ͥ���ڵ�</th>
							<td>
								<input type="text" style="width:200px" name="jtszd"
									id="jtszd" value="<bean:write name="rs" property="jtszd"/>"
									maxlength="25" />
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td>
								<html:text name="rs" property="csrq"
										onclick="return showCalendar('csrq','y-mm-dd');"
									styleId="csrq" readonly="true" />
							</td>
							<th>���֤��</th>
							<td>
								<html:text name="rs" property="sfzh" styleId="sfzh"
									maxlength="20" />
							</td>
						</tr>
						<tr>
							<th>������ò</th>
							<td>
								<html:select name="rs" property="zzmm" styleId="zzmm"
									style="width:150px">
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
							<th>����</th>
							<td>
								<html:select name="rs" property="mz" styleId="mz"
									style="width:150px">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								<html:text property="jg" name="rs" styleId="jg"
									maxlength="10" />
							</td>
							<th>��Դ����</th>
							<td>
								<html:text property="syd" name="rs" styleId="syd"
									maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								<html:text name="rs" property="ah" styleId="ah"
									maxlength="120" />
							</td>
							<th>�س�</th>
							<td>
								<html:text name="rs" property="tc" styleId="tc"
									maxlength="32" />
							</td>
						</tr>
						<tr>
							<th>�뵳ʱ��</th>
							<td>
								<html:text name="rs" property="jrgcdsj" styleId="jrgcdsj"
									onclick="return showCalendar('jrgcdsj','y-mm-dd');" />
							</td>
							<th>����ʱ��</th>
							<td>
								<html:text name="rs" property="jrgqtsj" styleId="jrgqtsj"
									onclick="return showCalendar('jrgqtsj','y-mm-dd');"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>�����</th>
							<td>
								<html:text property="ssbh" name="rs" styleId="ssbh"
									readonly="true" />
							</td>
							<th>���ҵ绰</th>
							<td>
								<html:text name="rs" property="qsdh" styleId="qsdh"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>�Ƿ����</th>
							<td>
								<html:radio name="rs" property="sfdk" value="0">��</html:radio>
								<html:radio name="rs" property="sfdk" value="1">��</html:radio>
							</td>
							<th>��ͥ�ʱ�</th>
							<td>
								<html:text name="rs" property="jtyb" styleId="jtyb"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>��ͥ��ϸ��ַ</th>
							<td colspan="3">
								<html:text property="jtdz" name="rs"
									style="width:680;height:30" styleId="jtdz" maxlength="25" />
							</td>
						</tr>
						<tr>
							<th>��ͥ�������</th>
							<td colspan="3">
								<html:radio name="rs" property="jtjjqk" value="1">��ԣ</html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jtjjqk" value="2">һ��</html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jtjjqk" value="3">����</html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jtjjqk" value="4">����</html:radio>
							</td>
						</tr>
						</tbody>

						<thead>
							<tr>
								<th onclick="document.getElementById('shgx').style.display=(document.getElementById('shgx').style.display==''?'none':'')">
									<span>����������Ҫ����ϵ</span>
								</th>

							</tr>
						</thead>

						<tbody>
						<tr id="shgx" style="display:none">
							<td colspan="5">
								<table class="formlist">
									<thead>
									<tr>
										<th><span>����</span></th>
										<th><span>�뱾�˹�ϵ</span></th>
										<th><span>������λ</span></th>
										<th><span>ְ��</span></th>
										<th><span>��λ�绰</span></th>
										<th><span>�ֻ�����</span></th>
									</tr>
									</thead>
									<tbody>
									<tr>
										<td>
											<html:text name="rs" property="shgxxm1" styleId="shgxxm1"
												maxlength="10" />
										</td>
										<td>
											<html:text name="rs" property="shgxgx1" styleId="shgxgx1"
												maxlength="32" />
										</td>
										<td>
											<html:text name="rs" property="shgxgzdw1"
												styleId="shgxgzdw1" maxlength="120" />
										</td>
										<td>
											<html:text name="rs" property="shgxzw1" styleId="shgxzw1"
												maxlength="32" />
										</td>
										<td>
											<html:text name="rs" property="shgxdwdh1"
												styleId="shgxdwdh1"
												onkeyup="value=value.replace(/[^\d]/g,'') "
												maxlength="13" />
										</td>
										<td>
											<html:text name="rs" property="shgxsjhm1"
												styleId="shgxsjhm1"
												onkeyup="value=value.replace(/[^\d]/g,'') "
												maxlength="11" />
										</td>
									</tr>
									<tr>
										<td>
											<html:text name="rs" property="shgxxm2" styleId="shgxxm2"
												maxlength="10" />
										</td>
										<td>
											<html:text name="rs" property="shgxgx2" styleId="shgxgx2"
												maxlength="10" />
										</td>
										<td>
											<html:text name="rs" property="shgxgzdw2"
												styleId="shgxgzdw2" maxlength="120" />
										</td>
										<td>
											<html:text name="rs" property="shgxzw2" styleId="shgxzw2"
												maxlength="32" />
										</td>
										<td>
											<html:text name="rs" property="shgxdwdh2"
												styleId="shgxdwdh2"
												onkeyup="value=value.replace(/[^\d]/g,'') "
												maxlength="13" />
										</td>
										<td>
											<html:text name="rs" property="shgxsjhm2"
												styleId="shgxsjhm2"
												onkeyup="value=value.replace(/[^\d]/g,'') "
												maxlength="11" />
										</td>
									</tr>
								</tbody>
								</table>
							</td>
						</tr>
					</tbody>
					</logic:equal>

					<logic:notEqual value="10225" name="xxdm">
						<tr>
							<th>������ϵ�绰</th>
							<td align="left">
								<input type="text" style="width:200px" name="lxdh1"
									id="lxdh1" value="<bean:write name="rs1" property="lxdh1"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
							<th>��ͥ��ϵ�绰</th>
							<td>
								<input type="text" style="width:200px" name="lxdh2"
									id="lxdh2" value="<bean:write name="rs1" property="lxdh2"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
						</tr>
						<tr>
							<th>�ֻ�����</th>
							<td>
								<input type="text" style="width:200px" name="sjhm" id="sjhm"
									value="<bean:write name="rs1" property="sjhm"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11" />
							</td>
							<th>E-Mail</th>
							<td>
								<input type="text" style="width:200px" name="email" id="dzyx"
									value="<bean:write name="rs1" property="dzyx"/>"
									maxlength="32" />
							</td>
						</tr>
						<tr>
							<th>��ͥ���ڵ�</th>
							<td>
								<input type="text" style="width:200px" name="jtszd"
									id="jtszd" value="<bean:write name="rs1" property="jtszd"/>"
									maxlength="25" />
							</td>
							<th>��ͥ�ʱ�</th>
							<td>
								<input type="text" style="width:200px" name="yb" id="yb"
									value="<bean:write name="rs1" property="yb"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>����ͨ�ŵ�ַ</th>
							<td colspan="3">
								<input type="text" style="width:600px" name="brtxdz"
									id="brtxdz"
									value="<bean:write name="rs1" property="brtxdz"/>"
									maxlength="64" />
							</td>
						</tr>
						<%--�㽭����ְҵ����ѧԺ--%>
						<logic:equal value="12861" name="xxdm">
							<tr>
								<th>��������</th>
								<td>
									<html:text name="rs" property="csrq"
										onclick="return showCalendar('csrq','y-mm-dd');"
										styleId="csrq" readonly="true" />
								</td>
								<th>��ͥ�绰</th>
								<td>
									<html:text name="rs" property="jtdh" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>�����</th>
								<td>
									<html:text property="ssbh" name="rs" styleId="ssbh"
										disabled="true" />
								</td>
								<th>���ҵ绰</th>
								<td>
									<html:text name="rs" property="qsdh" styleId="qsdh"
										disabled="true" />
								</td>
							</tr>
							<tr>
								<th>Ѫ��</th>
								<td>
									<html:text name="rs" property="xx" maxlength="10" />
								</td>
								<th>ְ��</th>
								<td>
									<html:text name="rs" property="zw" maxlength="32" />
								</td>
							</tr>
							<tr>
								<th>QQ����</th>
								<td>
									<html:text name="rs" property="qqhm" maxlength="13"
										onkeyup="value=value.replace(/[^\d]/g,'') " />
								</td>
								<th>������ò</th>
								<td>
									<html:select name="rs" property="zzmm" styleId="zzmm"
										style="width:150px">
										<html:options collection="zzmmList" property="zzmmdm"
											labelProperty="zzmmmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>��ͥ�������</th>
								<td colspan="3">
									<html:radio name="rs" property="jtjjqk" value="1">��ԣ</html:radio>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<html:radio name="rs" property="jtjjqk" value="2">һ��</html:radio>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<html:radio name="rs" property="jtjjqk" value="3">����</html:radio>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<html:radio name="rs" property="jtjjqk" value="4">����</html:radio>
								</td>
							</tr>
							<tr>
								<th>��ע</th>
								<td colspan="3">
									<html:text name="rs" property="bz"
										style="width: 95%;height: 25px" maxlength="500" />
								</td>
							</tr>
						</logic:equal>
						<%--���Ϲ�ҵ��ѧ--%>
						<logic:equal value="11535" name="xxdm">
							<%--ѧԺ�û�--%>
							<logic:equal value="xy" name="userType">
								<tr>
									<th>�����Ƿ�����</th>
									<td>
										<html:select property="dasfyl" name="rs1" styleId="dasfyl">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="��">��</html:option>
										</html:select>
									</td>
									<th>��������ԭ��</th>
									<td>
										<input type="text" style="width:200px" name="daylyy"
											id="daylyy"
											value="<bean:write name="rs1" property="daylyy"/>"
											maxlength="150" />
									</td>
								</tr>
							</logic:equal>
						</logic:equal>
					</logic:notEqual>
				</logic:empty>

				<logic:notEmpty name="gwcxview">
					<tr>
						<th>��ϵ�绰1</th>
						<td>
							<input type="text" style="width:200px" name="lxdh1" id="lxdh1"
								value="<bean:write name="rs1" property="lxdh1"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
						</td>
						<th>��ϵ�绰2</th>
						<td>
							<input type="text" style="width:200px" name="lxdh2" id="lxdh2"
								value="<bean:write name="rs1" property="lxdh2"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
						</td>
					</tr>
					<tr>
						<th>�ֻ�����</th>
						<td>
							<input type="text" style="width:200px" name="sjhm" id="sjhm"
								value="<bean:write name="rs1" property="sjhm"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11" />
						</td>
						<th>E-Mail</th>
						<td>
							<input type="text" style="width:200px" name="email" id="email"
								value="<bean:write name="rs1" property="dzyx"/>"
								maxlength="32" />
						</td>
					</tr>
					<tr>
						<th>��ͥ���ڵ�</th>
						<td>
							<input type="text" style="width:200px" name="jtszd" id="jtszd"
								value="<bean:write name="rs1" property="jtszd"/>"
								maxlength="25" />
						</td>
						<th>�������ڵ�</th>
						<td>
							<input type="text" style="width:200px" name="hkszd" id="hkszd"
								value="<bean:write name="rs1" property="hkszd"/>"
								maxlength="10" />
						</td>
					</tr>
					<tr>
						<th>����ͨ�ŵ�ַ</th>
						<td colspan="3">
							<input type="text" style="width:600px" name="brtxdz"
								id="brtxdz"
								value="<bean:write name="rs1" property="brtxdz"/>"
								maxlength="120" />
						</td>
					</tr>
					<tr>
						<th>�ʱ�</th>
						<td>
							<input type="text" style="width:200px" name="yb" id="yb"
								value="<bean:write name="rs1" property="yb"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10" />
						</td>
						<th>����״��</th>
						<td>
							<input type="text" style="width:200px" name="jkzk" id="jkzk"
								value="<bean:write name="rs1" property="jkzk"/>"
								maxlength="10" />
						</td>
					</tr>
					</tbody>
				</logic:notEmpty>

				<thead>
					<tr>
						<th colspan="4" style="cursor:hand" align="center"
							onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
							<span>ѧ����ͥ��Ա��Ϣ1</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr id="jt1"
					<logic:equal value="10225" name="xxdm">
									style="display:none"
								</logic:equal>>
					<td colspan="4">
						<table width="100%" class="formlist">
						<tbody>
							<tr>
								<th>����</th>
								<td>
									<input type="text" style="width:200px" name="jtcy1_xm"
										id="jtcy1_xm"
										value="<bean:write name="rs1" property="jtcy1_xm"/>"
										maxlength="10" />
								</td>
								<th>�뱾�˹�ϵ</th>
								<td>
									<input type="text" style="width:200px" name="jtcy1_gx"
										id="jtcy1_gx"
										value="<bean:write name="rs1" property="jtcy1_gx"/>"
										maxlength="25" />
								</td>
							</tr>
							<tr>
								<th>���֤����</th>
								<td>
									<input type="text" style="width:200px" name="jtcy1_sfzh"
										id="jtcy1_sfzh"
										value="<bean:write name="rs1" property="jtcy1_sfzh"/>"
										maxlength="20" />
								</td>
								<th>ְҵ</th>
								<td>
									<input type="text" style="width:200px" name="jtcy1_zy"
										id="jtcy1_zy"
										value="<bean:write name="rs1" property="jtcy1_zy"/>"
										maxlength="10" />
								</td>
							</tr>
							<tr>
								<th>������ַ</th>
								<td colspan="3">
									<input type="text" style="width:600px" name="jtcy1_gzdz"
										id="jtcy1_gzdz"
										value="<bean:write name="rs1" property="jtcy1_gzdz"/>"
										maxlength="25" />
								</td>
							</tr>
							<tr>
								<th>ְ��</th>
								<td>
									<input type="text" style="width:200px" name="jtcy1_zw"
										id="jtcy1_zw"
										value="<bean:write name="rs1" property="jtcy1_zw"/>"
										maxlength="10" />
								</td>
								<th>���˵绰</th>
								<td>
									<input type="text" style="width:200px" name="jtcy1_lxdh1"
										id="jtcy1_lxdh1"
										value="<bean:write name="rs1" property="jtcy1_lxdh1"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
								</td>
							</tr>
							<tr>
								<th>������λ�绰</th>
								<td>
									<input type="text" style="width:200px" name="jtcy1_lxdh2"
										id="jtcy1_lxdh2"
										value="<bean:write name="rs1" property="jtcy1_lxdh2"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
								</td>
								<th>&nbsp;</th>
								<td>
									&nbsp;
								</td>
							</tr>
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
										value="<bean:write name="rs1" property="jtcy2_xm"/>"
										maxlength="10" />
								</td>
								<th>�뱾�˹�ϵ</th>
								<td>
									<input type="text" style="width:200px" name="jtcy2_gx"
										id="jtcy2_gx"
										value="<bean:write name="rs1" property="jtcy2_gx"/>"
										maxlength="10" />
								</td>
							</tr>
							<tr>
								<th>���֤����</th>
								<td>
									<input type="text" style="width:200px" name="jtcy2_sfzh"
										id="jtcy2_sfzh"
										value="<bean:write name="rs1" property="jtcy2_sfzh"/>"
										maxlength="20" />
								</td>
								<th>ְҵ</th>
								<td>
									<input type="text" style="width:200px" name="jtcy2_zy"
										id="jtcy2_zy"
										value="<bean:write name="rs1" property="jtcy2_zy"/>"
										maxlength="10" />
								</td>
							</tr>
							<tr>
								<th>������ַ</th>
								<td colspan="3">
									<input type="text" style="width:600px" name="jtcy2_gzdz"
										id="jtcy2_gzdz"
										value="<bean:write name="rs1" property="jtcy2_gzdz"/>"
										maxlength="25" />
								</td>
							</tr>
							<tr>
								<th>ְ��</th>
								<td>
									<input type="text" style="width:200px" name="jtcy2_zw"
										id="jtcy2_zw"
										value="<bean:write name="rs1" property="jtcy2_zw"/>"
										maxlength="10" />
								</td>
								<th>���˵绰</th>
								<td>
									<input type="text" style="width:200px" name="jtcy2_lxdh1"
										id="jtcy2_lxdh1"
										value="<bean:write name="rs1" property="jtcy2_lxdh1"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
								</td>
							</tr>
							<tr>
								<th>������λ�绰</th>
								<td>
									<input type="text" style="width:200px" name="jtcy2_lxdh2"
										id="jtcy2_lxdh2"
										value="<bean:write name="rs1" property="jtcy2_lxdh2"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
								</td>
								<th>&nbsp;</th>
								<td>
									&nbsp;
								</td>
							</tr>
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
										value="<bean:write name="rs1" property="jtcy3_xm"/>"
										maxlength="25" />
								</td>
								<th>�뱾�˹�ϵ</th>
								<td>
									<input type="text" style="width:200px" name="jtcy3_gx"
										id="jtcy3_gx"
										value="<bean:write name="rs1" property="jtcy3_gx"/>"
										maxlength="10" />
								</td>
							</tr>
							<tr>
								<th>���֤����</th>
								<td>
									<input type="text" style="width:200px" name="jtcy3_sfzh"
										id="jtcy3_sfzh"
										value="<bean:write name="rs1" property="jtcy3_sfzh"/>"
										maxlength="20" />
								</td>
								<th>ְҵ</th>
								<td>
									<input type="text" style="width:200px" name="jtcy3_zy"
										id="jtcy3_zy"
										value="<bean:write name="rs1" property="jtcy3_zy"/>"
										maxlength="10" />
								</td>
							</tr>
							<tr>
								<th>������ַ</th>
								<td colspan="3">
									<input type="text" style="width:600px" name="jtcy3_gzdz"
										id="jtcy3_gzdz"
										value="<bean:write name="rs1" property="jtcy3_gzdz"/>"
										maxlength="25" />
								</td>
							</tr>
							<tr>
								<th>ְ��</th>
								<td>
									<input type="text" style="width:200px" name="jtcy3_zw"
										id="jtcy3_zw"
										value="<bean:write name="rs1" property="jtcy3_zw"/>"
										maxlength="10" />
								</td>
								<th>���˵绰</th>
								<td>
									<input type="text" style="width:200px" name="jtcy3_lxdh1"
										id="jtcy3_lxdh1"
										value="<bean:write name="rs1" property="jtcy3_lxdh1"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
								</td>
							</tr>
							<tr>
								<th>������λ�绰</th>
								<td>
									<input type="text" style="width:200px" name="jtcy3_lxdh2"
										id="jtcy3_lxdh2"
										value="<bean:write name="rs1" property="jtcy3_lxdh2"/>"
										maxlength="13" onkeyup="value=value.replace(/[^\d]/g,'') " />
								</td>
								<th>&nbsp;</th>
								<td>
									&nbsp;
								</td>
							</tr>
							</tbody>
						</table>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					  <div class="btn">
			          <logic:notPresent name="cWrite">
							<button type="button"
								onclick="refreshForm('stu_info_add.do?method=modiStuInfo&act=save');this.disabled=true">
								�� �� �� Ϣ
							</button>
						</logic:notPresent>
						<logic:present name="cWrite">
							<button type="button" disabled="disabled">
							�� �� �� Ϣ
							</button>
						</logic:present>
					 </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</logic:present>
	</logic:equal>
</logic:equal>