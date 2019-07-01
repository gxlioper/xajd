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
								<span>��ҵ��������</span>
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
										<button id="buttonSave"
											onclick="saveUpdate('/xgxt/jygl.do?method=jygzhysb&doType=save',
											'save_bmdm-save_sj-save_dd-save_hylxdm-save_hyzt-save_zcr-save_chrr-save_zyjy');">
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
											<button id="buttonSave"
												onclick="saveUpdate('/xgxt/jygl.do?method=jygzhysb&doType=modify',
												'save_bmdm-save_sj-save_dd-save_hylxdm-save_hyzt-save_zcr-save_chrr-save_zyjy');">
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
								<font color="red">*</font>ʱ��
							</th>
							<td>
								<logic:notPresent name="doType">
									<html:text property="save_sj" readonly="true" style="width:160px"
										   styleId="save_sj"
										   value="${rs.sj }"
										   onblur="dateFormatChg(this)"
										   onclick="return showCalendar(this.id,'y-mm-dd');"></html:text>
								</logic:notPresent>
								<logic:present name="doType">
									<html:text property="save_sj" readonly="true" style="width:160px"
										   styleId="save_sj"
										   value="${rs.sj }"
										   ></html:text>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�ص�
							</th>
							<td>
								<logic:notPresent name="doType">
									<html:text property="save_dd" maxlength="50" style="width:160px" value="${rs.dd }" onkeyup="chgPkValue(this)"></html:text>
								</logic:notPresent>
								<logic:present name="doType">
									<html:text property="save_dd" maxlength="50" style="width:160px" value="${rs.dd }" readonly="true"></html:text>
								</logic:present>
							</td>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<logic:notPresent name="doType">
									<html:select property="save_hylxdm" style="width:160px" value="${rs.hylxdm }">
										<html:options collection="hylxList" property="dm" labelProperty="mc"/>
									</html:select>
								</logic:notPresent>
								<logic:present name="doType">
									<html:select property="save_hylxdm" style="width:160px" value="${rs.hylxdm }" disabled="true">
										<html:options collection="hylxList" property="dm" labelProperty="mc"/>
									</html:select>
									<html:hidden property="save_hylxdm" value="${rs.hylxdm }"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<html:text property="save_hyzt" maxlength="50" style="width:160px" value="${rs.hyzt }" onkeyup="chgPkValue(this)"></html:text>
							</td>
							<th>
								<font color="red">*</font>������
							</th>
							<td>
								<html:text property="save_zcr" maxlength="15" style="width:160px" value="${rs.zcr }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�λ���Ա
							</th>
							<td colspan="3">
								<html:text property="save_chrr" maxlength="50" style="width:80%" value="${rs.chrr }" ></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��Ҫ����
								<br/>
								<font color="red"><��1000��></font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_zyjy" style="width:80%" rows="5"  onblur="checkLen(this,1000)" value="${rs.zyjy }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								��ע
								<br/>
								<font color="red"><��500��></font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_bz" style="width:80%" rows="5"  onblur="checkLen(this,500)" value="${rs.bz }"></html:textarea>
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