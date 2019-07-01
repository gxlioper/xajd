<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/jygl" method="post" >
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="getStuInfo" value="xh" />
			
			<logic:equal value="update" name="doType">
				<input type="hidden" name="save_shzt" value="δ���" />
			</logic:equal>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ҵ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:notPresent name="doType">
										<button id="saveButton"
											onclick="saveUpdate('/xgxt/jygl.do?method=qyxxsb&doType=save',
											'save_bmdm-save_dwmc-save_dwdz-save_yb-save_lxr-save_lxdh-save_cz-save_sjhm-save_email-save_wz-save_xqzy-save_qyfldm');">
											����
										</button>
										<script>
											if (window.dialogArguments){
												document.write("<button id=\"buttonSave\" onclick=\"window.close();\">�ر�</button>");
											} else {
												document.write("<button onclick=\"reset();\">����</button>");
											}
										</script>
									</logic:notPresent>

									<logic:equal value="view" name="doType">
										<button id="buttonSave" onclick="window.close();return false;">
											�ر�
										</button>
									</logic:equal>

									<logic:present name="doType">
										<logic:notEqual value="view" name="doType">
											<button id="saveButton"
												onclick="saveUpdate('/xgxt/jygl.do?method=qyxxsb&doType=modify',
												'save_bmdm-save_dwmc-save_dwdz-save_yb-save_lxr-save_lxdh-save_cz-save_sjhm-save_email-save_wz-save_xqzy-save_qyfldm');">
												����
											</button>
											<button id="buttonSave" onclick="window.close();return false;">
												�ر�
											</button>
										</logic:notEqual>
									</logic:present>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<logic:notPresent name="doType">
									<logic:equal value="xy" name="userType">
									<html:select property="save_bmdm" 
												 style="width:160px" 
												 disabled="true"
												 styleId="xy" 
												 value="${userDep}">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
									</html:select>
									
									<html:hidden property="save_bmdm" value="${userDep}"/>
								</logic:equal>
								<logic:notEqual value="xy" name="userType">
									<html:select property="save_bmdm" style="width:160px" styleId="xy" value="${rs.bmdm }">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
									</html:select>
								</logic:notEqual>
								</logic:notPresent>
								<logic:present name="doType">
									<html:select property="save_bmdm" style="width:160px" styleId="xy" value="${rs.bmdm }" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
									</html:select>
									
									<html:hidden property="save_bmdm" value="${rs.bmdm }"/>
								</logic:present>
							
								
							</td>
							<th>
								<font color="red">*</font>��λ����
							</th>
							<td>
								<logic:notPresent name="doType">
									<html:text property="save_dwmc" 
										   maxlength="25" 
										   onkeyup="chgPkValue(this)" 
										   value="${rs.dwmc }" 
										   style="width:160px"></html:text>
								</logic:notPresent>
								<logic:present name="doType">
									<html:text property="save_dwmc" 
										   maxlength="25" 
										   readonly="true"
										   value="${rs.dwmc }" 
										   style="width:160px"></html:text>
								</logic:present>
								
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��λ��ַ
							</th>
							<td>
								<html:text property="save_dwdz" maxlength="25" style="width:160px" value="${rs.dwdz }" ></html:text>
							</td>
							<th>
								<font color="red">*</font>�ʱ�
							</th>
							<td>
								<html:text property="save_yb" maxlength="6" 
										   style="width:160px" value="${rs.yb }" 
										   onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							
							<th>
								<font color="red">*</font>��ϵ��
							</th>
							<td>
								<html:text property="save_lxr" maxlength="10" style="width:160px" value="${rs.lxr }"></html:text>
							</td>
							<th>
								<font color="red">*</font>�칫�绰
							</th>
							<td>
								<div class="pos" style="z-index:3">
									<html:text property="save_lxdh"
											   maxlength="25" 
											   style="width:160px" 
											   onkeypress="checkPhoneV4(this)"
											   onblur="checkPhoneV4(this)"
											   value="${rs.lxdh }" ></html:text>
									<div id="phoneErrow" class="hide">
											<p>
											�绰��ʽ����ȷ
										</p>
									</div>
								</div>
							</td>
						</tr>
						
						<tr>
							<th>
								<font color="red">*</font>�������
							</th>
							<td>
								<html:text property="save_cz" maxlength="20" style="width:160px" 
										   value="${rs.cz }" ></html:text>
							</td>
							<th>
								<font color="red">*</font>�ֻ�����
							</th>
							<td>
								<html:text property="save_sjhm" maxlength="20" 
										   onkeyup="value=value.replace(/[^\d]/g,'')"
										   style="width:160px" value="${rs.sjhm }" ></html:text>
							</td>
						</tr>
						
						<tr>
							<th>
								<font color="red">*</font>����
							</th>
							<td>
								<div class="pos" style="z-index:2">
									<html:text property="save_email" maxlength="28" 
											   style="width:160px" 
											   onkeypress="checkEmaile(this)"
											   onblur="checkEmaile(this)"
											   value="${rs.email }" ></html:text>
										<div id="emaliErrow" class="hide">
											<p>
											�����ʽ����ȷ
										</p>
									</div>
								</div>
							</td>
							<th>
								<font color="red">*</font>��ַ
							</th>
							<td>
								<div class="pos" style="z-index:1">
									<html:text property="save_wz" maxlength="60" 
											   onkeypress="checkURL(this)"
											   onblur="checkURL(this)"
											   style="width:160px" value="${rs.wz }" ></html:text>
									<div id="urlErrow" class="hide">
										<p>
											��ַ��ʽ����ȷ
										</p>
									</div>
								</div>
							</td>
						</tr>
						
						<tr>
							<th>
								<font color="red">*</font>����רҵ
							</th>
							<td>
								<html:text property="save_xqzy" maxlength="50" style="width:160px" value="${rs.xqzy }" ></html:text>
							</td>
							<th>
								<font color="red">*</font>��ҵ����
							</th>
							<td>
								<html:select property="save_qyfldm" value="${rs.qyfldm }" style="width:160px">
									<html:options collection="qyflList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��λ����<br/><font color="red"><��1500��></font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_dwjj" 
											   style="width:85%" 
											   value="${rs.dwjj }" 
											   rows="10"
											   onblur="checkLen(this,1500)"></html:textarea>
							</td>
						</tr>
						<logic:equal value="sh" name="doType">
							<tr>
								<th>
									<font color="red">*</font>���״̬
								</th>
								<td>
									<html:select property="save_shzt" style="width:160px" value="${rs.shzt }">
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>������<br/><font color="red"><��400��></font></th>
								<td style="word-break:break-all;" colspan="3">
									<html:textarea property="save_shyj" value="${rs.shyj }" style="width:85%" rows="5" onblur="checkLen(this,400)"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="view" name="doType">
							<tr>
								<th>
									���״̬
								</th>
								<td>
									${rs.shzt }
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>
									���״̬���
								</th>
								<td style="word-break:break-all;" colspan="3">
									${rs.shyj }
								</td>
							</tr>
						</logic:equal>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="result">
			<script>
				alert('${message}');
				if (window.dialogArguments) {
					window.close();
					dialogArgumentsQueryChick();
				}
			</script>
		</logic:present>
	</body>