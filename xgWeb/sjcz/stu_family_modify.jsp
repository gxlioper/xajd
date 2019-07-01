<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/xsxxZgkd.js'></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
</head>
	<body onload="addJtxxNotNullFiled()">
		<html:form action="/modi_stu_famil" method="post">
			<input type="hidden" value="${oper}" id="oper" />
			<input type="hidden" name="url" id="url" value="/sjcz/stu_family_modify.jsp"/>
			<input type="hidden" name="variable" id="variable" value="ydinfo"/>
			<input type="hidden" name="redirect" id="redirect" value="true"/>
			<input type="hidden" name="page" id="page" value="stuinfo"/>
			<input type="hidden" name="notnull" id="notnull" value="xh"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
			<input type="hidden" name="yhjs" id="yhjs" value="${userType}"/>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ѧ����ͥ��Ϣ - ��Ϣά��</a>
				</p>
			</div>
			<logic:equal name="userOnLine" value="student" scope="session">
				<center>
					<p>
						��ҳ��ѧ�������Է��ʣ�
					</p>
				</center>
			</logic:equal>

			<%--�ϲ���ѧ��ѧ����ѧԺ--%>
			<logic:equal value="13429" name="xxdm">
				<%@ include file="/xsxx/common/jtxx/xsxx_jtxx_ncdxkjxy.jsp"%>
			</logic:equal>
		
			<%--���ϲ��Ƽ�ѧԺ--%>
			<logic:notEqual value="13429" name="xxdm">
			<logic:equal name="userOnLine" value="teacher" scope="session">
			<div class="tab">
			  <table width="100%" border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>ѧ����ͥ��Ϣ</span></th>
			        </tr>
			    </thead>
			    <tbody>
					<tr>
						<th><span class="red">*</span>ѧ��</th>
						<td>
							<logic:equal value="update" name="oper">
								<html:text property="xh" readonly="true" styleId="xh"
									style="cursor:hand" />
							</logic:equal>
							<logic:equal value="add" name="oper">
								<html:text name="rs" property="xh" styleId="xh"
									onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
								<button type="button" class=""
									onclick="showTopWin('/xgxt/stu_info.do?oper=<bean:write name="oper"/>',750,550);"
									id="buttonFindStu">
									ѡ��
								</button>
							</logic:equal>
						</td>
						<th><bean:message key="lable.xsgzyxpzxy"/></th>
						<td >
							${rs.xymc}
						</td>
					</tr>
					<tr>
						<th>�꼶</th>
						<td>
							${rs.nj}
						</td>
						<th>רҵ</th>
						<td>
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<th>����</th>
						<td>
							${rs.xm}
						</td>
						<th>�༶</th>
						<td>
							${rs.bjmc}
						</td>
					</tr>
					<%--�㶫����ѧԺ--%>
					<logic:equal value="10822" name="xxdm">
						<tr>
							<th>�Ƿ�ͱ�</th>
							<td>
								<html:select property="sfdb" name="rs">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
							<th>������Դ</th>
							<td>
								<html:text name="rs" property="srly" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<th>��ͥ��������</th>
							<td>
								<html:text name="rs" property="jtzsr" maxlength="10"/>
							</td>
							<th>��ͥ�˾�����</th>
							<td>
								<html:text name="rs" property="jtrjsr" maxlength="10"/>
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th>��ͥ�绰</th>
						<td>
							<html:text name="rs" property="lxdh1" maxlength="25" styleId="lxdh1"/>
						</td>	
						<th>��������</th>
						<td>
							<html:text name="rs" property="jtyb" maxlength="10" styleId="jtyb"
								onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>
					</tr>
					<tr>
					<th>��ͥ��ַ</th>
					<td  colspan="3">
						<html:text name="rs" property="jtszd" maxlength="25" styleId="jtszd" style="width:90%"/>
					</td>
							
					</tr>

					<%--��������--%>
					<logic:equal value="10690" name="xxdm">
						<tr>
							<th>��ͥ������</th>
							<td colspan="3">
								<html:text name="rs" property="jtzsr"
									onkeyup="value=value.replace(/[^\d]/g,'') " styleId="jtzsr"
									maxlength="10" />
								Ԫ/��
							</td>
							<th>��ͥ�̶��绰</th>
							<td>
								<html:text property="lxdh1" name="rs" maxlength="25"></html:text>
							</td>
						</tr>
					</logic:equal>
					<%--����������--%>
					<logic:notEqual value="10690" name="xxdm">
						<tr>
							<th>��ͥ����״��</th>
							<td colspan="3">
								<html:textarea name="rs" property="jjzk" style="width:600px;height:45" styleId="jjzk"/>
							</td>
						</tr>
					</logic:notEqual>
					<%@ include file="/xsxx/bjqnzzxy/xsjtxx_bjqnzzxy.jsp"%>
					</tbody>
					</table>
					
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<td style="cursor:hand" align="center"
									onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
									<%--���ս�����ҵѧԺ--%>
									<logic:equal value="10878" name="xxdm">
										ѧ��������Ϣ
									</logic:equal>
									<logic:notEqual value="10878" name="xxdm">
										ѧ����ͥ��Ա��Ϣ1
									</logic:notEqual>
								</td>
							</tr>
						</thead>
						<tbody>
						<tr id="jt1">
							<td>
								<table width="100%" class="formlist">
									<tr>
										<th>����</th>
										<td>
											<html:text name="rs" property="jtcy1_xm" styleId="xm" maxlength="25"/>
										</td>
										<th>�뱾�˹�ϵ</th>
										<td>
											<html:text name="rs" property="jtcy1_gx" styleId="ch" maxlength="10"/>
										</td>
									</tr>
									<tr>
										<%--�Ƕ�����ҵ��ѧ--%>
										<logic:notEqual value="10225" name="xxdm">
											<th>��������</th>
											<td>
												<html:text name="rs" property="jtcy1_nl" styleId="nl" readonly="true" onclick="return showCalendar('jtcy1_nl','y-mm-dd');" />
											</td>
										</logic:notEqual>
										<%--end�Ƕ�����ҵ��ѧ--%
										
										<%--������ҵ��ѧ--%>
										<logic:equal value="10225" name="xxdm">
											<th>ְ��</th>
											<td>
												<html:text name="rs" property="jtcy1_zw"
													styleId="jtcy1_zw" maxlength="10"/>
											</td>
											<th>������ַ</th>
											<td>
												<html:text name="rs" property="jtcy1_gzdz" styleId="gzdz" maxlength="32" />
											</td>
										</logic:equal>
										<%--end������ҵ��ѧ--%>

										<%--�Ƕ�����ҵ��ѧ--%>
										<logic:notEqual value="10225" name="xxdm">
											<th>���֤��</th>
											<td>
												<html:text name="rs" property="jtcy1_sfzh" styleId="sfzh" maxlength="20"/>
											</td>
										</logic:notEqual>
										<%--end�Ƕ�����ҵ��ѧ--%>
									</tr>
									<tr>
										<%--������ҵ��ѧ--%>
										<logic:equal value="10225" name="xxdm">
											<th>��ϵ�绰</th>
											<td>
												<html:text name="rs" property="jtcy1_lxdh1"
													styleId="jtcy1_lxdh1" />
											</td>
											<th>�ֻ�����</th>
											<td>
												<html:text name="rs" property="jtcy1_lxdh2"
													styleId="jtcy1_lxdh2" />
											</td>
										</logic:equal>
										<%--end������ҵ��ѧ--%>

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
									</tr>
									<%--�Ƕ�����ҵ��ѧ--%>
									<logic:notEqual value="10225" name="xxdm">
										<tr>
											<%--��������--%>
											<logic:equal value="10690" name="xxdm">
												<th>������λ</th>
											</logic:equal>
											<%--end��������--%>
											<logic:notEqual value="10690" name="xxdm">
												<th>ְҵ</th>
											</logic:notEqual>
											<td>
												<html:text name="rs" property="jtcy1_zy"
													styleId="jtcy1_zy" maxlength="10"/>
											</td>
											<th>ְ��</th>
											<td>
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
											<td>
												<html:text name="rs" property="jtcy1_lxdh1"
													styleId="lxdh1" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20"/>
											</td>
										</tr>
										<tr>
											<%--��������--%>
											<logic:equal value="10690" name="xxdm">
												<th>��λ��ַ</th>
												<td>
													<html:text name="rs" property="jtcy1_gzdz" styleId="gzdz" maxlength="32"/>
												</td>
												<th>������λ�ʱ�</th>
												<td>
													<html:text name="rs" property="jtcy1_yzbm"
														styleId="jtcy1_yzbm" maxlength="10"
														onkeyup="value=value.replace(/[^\d]/g,'') " />
												</td>
											</logic:equal>
											<logic:notEqual value="10690" name="xxdm">
											<logic:notEqual value="10649" name="xxdm">
												<th>������ַ</th>
												<td colspan="3">
													<html:text name="rs" property="jtcy1_gzdz" styleId="gzdz" maxlength="32" style="width:90%"/>
												</td>
											</logic:notEqual>
											<logic:equal value="10649" name="xxdm" >
												<th>������ַ</th>
												<td>
													<html:text name="rs" property="jtcy1_gzdz" styleId="gzdz" maxlength="32"/>
												</td>
												<th>��Ҫ����ϵ��Ϣ</th>
												<td>
													<html:text name="rs" property="zyshgxxx1"
														styleId="zyshgxxx1" maxlength="25"/>
												</td>
											</logic:equal>
											</logic:notEqual>
										</tr>
										</logic:notEqual>
										<!--������ְͨҵ����ѧԺ-->
										<logic:equal value="12752" name="xxdm">
										<tr>
											<th>�ڽ�����</th>
											<td colspan="3">
												<html:text name="rs" property="jtcy1_zjxy"
													styleId="jtcy1_zjxy" maxlength="50" style="width:90%"/>
											</td>
										</tr>
										</logic:equal>
										<!--end������ְͨҵ����ѧԺ-->
									</table>
							</td>
						</tr>
						</tbody>
						<thead>
							<tr>
								<td style="cursor:hand" align="center"
									onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
									<%--���ս�����ҵѧԺ--%>
									<logic:equal value="10878" name="xxdm">
										ѧ��ĸ����Ϣ
									</logic:equal>
									<logic:notEqual value="10878" name="xxdm">
										ѧ����ͥ��Ա��Ϣ2
									</logic:notEqual>
								</td>
							</tr>
						</thead>
						<tbody>
						<tr id="jt2">
							<td>
								<table width="100%" class="formlist">
									<tr>
										<th>����</th>
										<td>
											<html:text name="rs" property="jtcy2_xm" styleId="jtcy2_xm" maxlength="25"/>
										</td>
										<th>�뱾�˹�ϵ</th>
										<td>
											<html:text name="rs" property="jtcy2_gx" styleId="jtcy2_gx" maxlength="10"/>
										</td>
									</tr>
									<tr>
										<logic:notEqual value="10225" name="xxdm">
											<th>��������</th>
											<td>
												<html:text name="rs" property="jtcy2_nl"
													styleId="jtcy2_nl"
													onclick="return showCalendar('jtcy2_nl','y-mm-dd');" readonly="true"/>
											</td>
										</logic:notEqual>
										<logic:equal value="10225" name="xxdm">
											<th>ְ��</th>
											<td>
												<html:text name="rs" property="jtcy2_zw"
													styleId="jtcy2_zw" maxlength="10"/>
											</td>
										</logic:equal>
										<logic:equal value="10225" name="xxdm">
											<th>������ַ</th>
											<td>
												<html:text name="rs" property="jtcy2_gzdz"
													styleId="jtcy2_gzdz" maxlength="25"/>
											</td>
										</logic:equal>
										<logic:notEqual value="10225" name="xxdm">
											<th>���֤��</th>
											<td>
												<html:text name="rs" property="jtcy2_sfzh" maxlength="20"
													styleId="jtcy2_sfzh" />
											</td>
										</logic:notEqual>
									</tr>
									<tr>
										<logic:equal value="10225" name="xxdm">
											<th>��ϵ�绰</th>
											<td>
												<html:text name="rs" property="jtcy2_lxdh1"
													styleId="jtcy2_lxdh1" maxlength="25"/>
											</td>
											<th>�ֻ�����</th>
											<td>
												<html:text name="rs" property="jtcy2_lxdh2"
													styleId="jtcy2_lxdh2" maxlength="25"/>
											</td>
										</logic:equal>
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
									</tr>
									<logic:notEqual value="10225" name="xxdm">
										<tr>
											<%--��������--%>
											<logic:equal value="10690" name="xxdm">
												<th>������λ</th>
											</logic:equal>
											<logic:notEqual value="10690" name="xxdm">
												<th>ְҵ</th>
											</logic:notEqual>
											<td>
												<html:text name="rs" property="jtcy2_zy"
													styleId="jtcy2_zy" maxlength="10"/>
											</td>
											<th>ְ��</th>
											<td>
												<html:text name="rs" property="jtcy2_zw"
													styleId="jtcy2_zw" maxlength="10"/>
											</td>
										</tr>
										<tr>
											<th>������λ�绰</th>
											<td>
												<html:text name="rs" property="jtcy2_lxdh2"
													styleId="jtcy2_lxdh2" maxlength="25"/>
											</td>
											<th>���˵绰</th>
											<td>
												<html:text name="rs" property="jtcy2_lxdh1"
													styleId="jtcy2_lxdh1" maxlength="25"/>
											</td>
										</tr>
										<tr>
											<%--��������--%>
											<logic:equal value="10690" name="xxdm">
												<th>��λ��ַ</th>
												<td>
													<html:text name="rs" property="jtcy2_gzdz"
														styleId="jtcy2_gzdz" maxlength="25"/>
												</td>
												<th>������λ�ʱ�</th>
												<td>
													<html:text name="rs" property="jtcy2_yzbm"
														styleId="jtcy2_yzbm" maxlength="10"
														onkeyup="value=value.replace(/[^\d]/g,'') " />
												</td>
											</logic:equal>
											<logic:notEqual value="10690" name="xxdm">
											<logic:notEqual  value="10649" name="xxdm" >
												<th>������ַ</th>
												<td colspan="3">
													<html:text name="rs" property="jtcy2_gzdz"
														styleId="jtcy2_gzdz" maxlength="25"  style="width:90%"/>
												</td>
												</logic:notEqual>
												<logic:equal value="10649" name="xxdm" >
												<th>������ַ</th>
												<td>
													<html:text name="rs" property="jtcy2_gzdz"
														styleId="jtcy2_gzdz" maxlength="25"/>
												</td>
												<th>��Ҫ����ϵ��Ϣ</td>
												<td>
													<html:text name="rs" property="zyshgxxx2"
														styleId="zyshgxxx2" maxlength="25"/>
												</td>
											</logic:equal>
											</logic:notEqual>
										</tr>
									</logic:notEqual>
									<!--������ְͨҵ����ѧԺ-->
										<logic:equal value="12752" name="xxdm">
										<tr>
											<th>�ڽ�����</th>
											<td colspan="3">
												<html:text name="rs" property="jtcy2_zjxy"
													styleId="jtcy2_zjxy" maxlength="50"  style="width:90%"/>
											</td>
										</tr>
										</logic:equal>
										<!--end������ְͨҵ����ѧԺ-->
								</table>
							</td>
						</tr>
						</tbody>
						<thead>
							<tr>
								<td style="cursor:hand" align="center"
									onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
									<%--���ս�����ҵѧԺ--%>
									<logic:equal value="10878" name="xxdm">
									ѧ��������Ա��Ϣ
								</logic:equal>
									<logic:notEqual value="10878" name="xxdm">
									ѧ����ͥ��Ա��Ϣ3
								</logic:notEqual>
								</td>
							</tr>
						</thead>
						<tbody>
						<tr id="jt3" style="display:none">
							<td>
								<table width="100%" class="formlist">
									<tr>
										<th>����</th>
										<td>
											<html:text name="rs" property="jtcy3_xm" styleId="jtcy3_xm" maxlength="25"/>
										</td>
										<th>�뱾�˹�ϵ</th>
										<td>
											<html:text name="rs" property="jtcy3_gx" styleId="jtcy3_gx" maxlength="10"/>
										</td>
									</tr>
									<tr>
										<logic:notEqual value="10225" name="xxdm">
											<th>��������</th>
											<td>
												<html:text name="rs" property="jtcy3_nl"
													styleId="jtcy3_nl"
													onclick="return showCalendar('jtcy3_nl','y-mm-dd');" />
											</td>
										</logic:notEqual>
										<logic:equal value="10225" name="xxdm">
											<th>ְ��</th>
											<td>
												<html:text name="rs" property="jtcy3_zw"
													styleId="jtcy3_zw" maxlength="10"/>
											</td>
										</logic:equal>
										<logic:equal value="10225" name="xxdm">
											<th>������ַ</th>
											<td>
												<html:text name="rs" property="jtcy3_gzdz"
													styleId="jtcy3_gzdz" maxlength="25"/>
											</td>
										</logic:equal>
										<logic:notEqual value="10225" name="xxdm">
											<th>���֤��</th>
											<td>
												<html:text name="rs" property="jtcy3_sfzh" maxlength="20"
													styleId="jtcy3_sfzh" />
											</td>
										</logic:notEqual>
									</tr>
									<tr>
										<%--������ҵ��ѧ--%>
										<logic:equal value="10225" name="xxdm">
											<th>��ϵ�绰</th>
											<td>
												<html:text name="rs" property="jtcy3_lxdh1"
													styleId="jtcy3_lxdh1" maxlength="25"/>
											</td>
											<th>�ֻ�����</th>
											<td>
												<html:text name="rs" property="jtcy3_lxdh2"
													styleId="jtcy3_lxdh2" maxlength="25"/>
											</td>
										</logic:equal>
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
									</tr>
									<logic:notEqual value="10225" name="xxdm">
										<tr>
											<%--��������--%>
											<logic:equal value="10690" name="xxdm">
												<th>������λ</th>
											</logic:equal>
											<logic:notEqual value="10690" name="xxdm">
												<th>ְҵ</th>
											</logic:notEqual>
											<td>
												<html:text name="rs" property="jtcy3_zy"
													styleId="jtcy3_zy" maxlength="10"/>
											</td>
											<th>ְ��</td>
											<td>
												<html:text name="rs" property="jtcy3_zw"
													styleId="jtcy3_zw" maxlength="10"/>
											</td>
										</tr>
										<tr>
											<th>������λ�绰</th>
											<td>
												<html:text name="rs" property="jtcy3_lxdh2"
													styleId="jtcy3_lxdh2" maxlength="25"/>
											</td>
											<th>���˵绰</th>
											<td>
												<html:text name="rs" property="jtcy3_lxdh1"
													styleId="jtcy3_lxdh1" maxlength="25"/>
											</td>
										</tr>
										<tr>
											<%--��������--%>
											<logic:equal value="10690" name="xxdm">
												<th>��λ��ַ</th>
												<td>
													<html:text name="rs" property="jtcy3_gzdz"
														styleId="jtcy3_gzdz" maxlength="25"/>
												</td>
												<th>������λ�ʱ�</th>
												<td>
													<html:text name="rs" property="jtcy3_yzbm"
														styleId="jtcy3_yzbm" maxlength="10"
														onkeyup="value=value.replace(/[^\d]/g,'') " />
												</td>
											</logic:equal>
											<logic:notEqual value="10690" name="xxdm">
											<logic:notEqual value="10649" name="xxdm" scope="session">
												<th>������ַ</th>
												<td colspan="3">
													<html:text name="rs" property="jtcy3_gzdz"
														styleId="jtcy3_gzdz" maxlength="25"  style="width:90%"/>
												</td>
												</logic:notEqual>
												<logic:equal value="10649" name="xxdm" scope="session">
												<th>������ַ</th>
												<td>
													<html:text name="rs" property="jtcy3_gzdz"
														styleId="jtcy3_gzdz" maxlength="25"/>
												</td>
												<th>��Ҫ����ϵ��Ϣ</th>
												<td>
													<html:text name="rs" property="zyshgxxx3"
														styleId="zyshgxxx3" maxlength="25"/>
												</td>
											</logic:equal>												
											</logic:notEqual>											
										</tr>
									</logic:notEqual>
									<!--������ְͨҵ����ѧԺ-->
									<logic:equal value="12752" name="xxdm">
									<tr>
										<th>�ڽ�����</th>
										<td colspan="3">
											<html:text name="rs" property="jtcy3_zjxy"
												styleId="jtcy3_zjxy" maxlength="50"  style="width:90%"/>
										</td>
									</tr>
									</logic:equal>
									<!--end������ְͨҵ����ѧԺ-->
								</table>
							</td>
						</tr>
						<!--��������-->
						<%@ include file="/xsxx/common/jtxx/xsxx_jtxx_ynys.jsp"%>
					</tbody>
					
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
							<logic:notEqual value="student" name="user">
				            <%--���ϲ���ѧ��ѧ����ѧԺ--%>
							<logic:notEqual value="13429" name="xxdm">
							<logic:notEqual value="view" name="userOper">
								<logic:notPresent name="cWrite">
									<button type="button" style="width:80px" id="buttonSave"
										onclick="stuinfoSave('modi_stu_famil.do?doType=modify&oper=',true)">
										�� ��
									</button>
								</logic:notPresent>
								<logic:present name="cWrite">
									<button type="button" style="width:80px"disabled="disabled">
										�� ��
									</button>
								</logic:present>
							</logic:notEqual>
							</logic:notEqual>
							<%--end���ϲ���ѧ��ѧ����ѧԺ--%>
							
							<%--�ϲ���ѧ��ѧ����ѧԺ--%>
							<logic:equal value="13429" name="xxdm">
								<logic:notPresent name="cWrite">
									<button type="button" style="width:80px"
										onclick="stuinfoSave('modi_stu_famil.do?doType=modify&oper=','family')">
										�� ��
									</button>
								</logic:notPresent>
								<logic:present name="cWrite">
									<button type="button" style="width:80px" disabled="disabled">
										�� ��
									</button>
								</logic:present>
							</logic:equal>
							<%--end�ϲ���ѧ��ѧ����ѧԺ--%>
							</logic:notEqual>
							<button type="button" style="width:80px" onclick="Close();return false;">
								�� ��
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
					</table>
				</div>
				</logic:equal>
				</logic:notEqual>
				
				<logic:notEmpty name="result">
					<logic:equal value="true" name="result">
						<script>
						alert("�����ɹ���");
						Close();
						if(window.dialogArguments){
							window.dialogArguments.document.getElementById('search_go').click();
						}		
						</script>
					</logic:equal>
					<logic:equal value="false" name="result">
						<script>
							alert("����ʧ�ܣ�");
						</script>
					</logic:equal>
				</logic:notEmpty>
		</html:form>
	</body>
</html>
