<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
		<script language="javascript"  src="js/wjdc/wjdc.js"></script>
		<script language="javascript"  src="js/wjdc/wjdcMk.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	</head>

	<body onload="$('xh').focus()">
		<html:form action="/wjdc">
			<!-- ������ -->
			<%@ include file="/wjdc/hiddenValue.jsp"%>
			<input type="hidden" name="id" id="id" value="${id }"/>
			<input type="hidden" name="oneSs" id="oneSs" value="${oneSs }"/>
			<input type="hidden" name="allSs" id="allSs" value="${allSs }"/>
			<!-- ������ end-->
			
			
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:notEqual name="doType"value="view">
										<!-- �ش��ʾ� -->
										<logic:equal name="bclx" value="hd">
											<button id="buttonSave" onclick="saveHd('wjdc.do?method=hdwjSave&doType=save')">
													ȷ	��
											</button> 
										</logic:equal>
										<!-- �ǻش��ʾ� -->
										<logic:notEqual name="bclx" value="hd">
											<button id="buttonSave" onclick="saveWj()">
													�� ��
											</button> 
										</logic:notEqual>
									</logic:notEqual>
									<button onclick="window.close();return false;" id="buttonClose">
											�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									���Ļ������
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��
							</th>
							<td>
								<html:text property="xh" styleId="xh" readonly="true" value="${stuInfo.xh }"></html:text>
							</td>
							<th>����</th>
							<td>${stuInfo.xm }</td>
						</tr>
						<tr>
							<th>�Ա�</th>
							<td>${stuInfo.xb }</td>
							<th>����ϵ</th>
							<td>${stuInfo.xymc }</td>
						</tr>
						<tr>
							<th>���ڰ༶</th>
							<td colspan="3">${stuInfo.bjmc }</td>
						</tr>
						<tr>
							<th>ʵϰ��λ����
								<br/>
								<font color="red">(��д��λȫ��)</font>
							</th>
							<td colspan="3">
								<html:text property="sxdwmc" styleId="sxdwmc" maxlength="50" style="width:90%" value="${rs.sxdwmc }"></html:text>
							</td>
						</tr>
						<tr>
							<th>�����ص�</th>
							<td colspan="3">
								<html:text property="gzdd" maxlength="100" style="width:90%" value="${rs.gzdd }"></html:text>
							</td>
						</tr>
						<tr>
							<th>������λ</th>
							<td colspan="3">
								<html:text property="gzgw" maxlength="50" style="width:90%" value="${rs.gzgw }"></html:text>
							</td>
						</tr>
						<tr>
							<th>��ϵ��ʽ���绰��e-mail��</th>
							<td colspan="3">
								<html:text property="lxfs" maxlength="100" style="width:90%" value="${rs.lxfs }"></html:text>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									������Ϣ
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%">
								ѧ��
							</th>
							<td width="35%">
								<html:hidden name="rs" property="xn"/>
								${rs.xn }
							</td>
							<th width="15%">
								ѧ��
							</th>
							<td>
								<html:hidden name="rs" property="xq"/>
								${rs.xqmc }
							</td>
						</tr>
						<tr>
							<th>
								���
							</th>
							<td>
								<html:hidden name="rs" property="nd"/>
								${rs.nd }
							</td>
							<th>
								����ʱ��
							</th>
							<td>
								<html:hidden name="rs" property="jlsj"/>
								${rs.jlsjmc }
							</td>
						</tr>
					</tbody>
			<!-- ѧ���û� -->
			<logic:equal name="userType" value="stu">
				<!-- �������� -->
				<input type="hidden" name="kyxg" id="kyxg" value="${rs.kyxg }"/>
				<input type="hidden" name="dawk" id="dawk" value="${rs.dawk }"/>
			</logic:equal>
			<!-- ��ѧ���û� -->
			<logic:notEqual name="userType" value="stu">
				<thead>
						<tr>
							<td colspan="4">
								<span>�������</span>
							</td>
						</tr>
					</thead>
				<tbody>
					<tr>
						<th>
							<font color="red">*</font>�Ƿ���
						</th>
						<td>
							��
							<html:radio name="rs" property="sfkq" styleId="kgkq" value="��"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��
							<html:radio name="rs" property="sfkq" styleId="kggb" value="��"/>
						</td>
						<th>
							ģ������										
						</th>
						<td>
							<html:select property="queryequals_mklx" style="" styleId="mklx" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="mklxList" property="en" labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							��ɺ�ɷ��޸�
						</th>
						<td>
							<html:select name="rs" property="kyxg" style="" styleId="kyxg">
								<html:options collection="sfList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<th>
							�𰸿���Ϊ��					
						</th>
						<td>
							<html:select name="rs" property="dawk" style="" styleId="dawk">
								<html:options collection="sfList" property="en" labelProperty="cn" />
							</html:select>
						</td>
					</tr>
				</tbody>
				</logic:notEqual>
				<thead>
						<tr>
							<td colspan="4">
								<span>�ʾ���Ϣ</span>
							</td>
						</tr>
					</thead>
				<tbody>
					<tr>
						<th>
							<font color="red">*</font>�ʾ�����<br/>
							<font color="red">(��50��)</font>
						</th>
						<td colspan="3">
							<!-- �ش��ʾ� -->
							<logic:equal name="bclx" value="hd">
								<html:text name="rs" property="wjmc" styleId="wjmc" style="width: 90%" readonly="true"/>		
							</logic:equal>
							<!-- �ǻش��ʾ� -->
							<logic:notEqual name="bclx" value="hd">
								<html:text name="rs" property="wjmc" styleId="wjmc" style="width: 90%" maxlength="50" onblur="getWjInfo()"/>	
							</logic:notEqual>		
						</td>
					</tr>
					<tr>
						<th>
							��β��<br/>
							<font color="red">(��50��)</font>
						</th>
						<td colspan="3">
							<html:text name="rs" property="jwy" maxlength="50" style="width: 90%" />	
						</td>
					</tr>
					<tr>
						<th>
							��ע<br/>
							<font color="red">(��500��)</font>
						</th>
						<td colspan="3">
							<!-- �ش��ʾ� -->
							<logic:equal name="bclx" value="hd">
								<html:textarea name="rs" property="bz" rows="5" styleId="bz"
									readonly="true" style="width: 90%"/>
							</logic:equal>
							<!-- �ǻش��ʾ� -->
							<logic:notEqual name="bclx" value="hd">
								<html:textarea name="rs" property="bz" rows="5" styleId="bz"
									onblur="chLeng(this,500)" style="width: 90%"/>
							</logic:notEqual>
						</td>
					</tr>
				</tbody>	
				
			<!-- �����Ϣ -->
			<logic:equal name="isSt" value="true">
					<thead>
						<tr>
							<td colspan="4">
								<span>�����Ϣ</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<logic:iterate name="zjlxList" id="lx">
										<thead>
											<tr>
												<td align="center" colspan="2">
													${lx.lxmc}
												</td>
											</tr>
										</thead>
										<!-- ��ѡ�� -->
										<logic:equal name="lx" property="lxdm" value="oneChoose">
											<%@ include file="/wjdc/stxx/zjOneChoose.jsp"%>
										</logic:equal>
										<!-- ��ѡ�� -->
										<logic:equal name="lx" property="lxdm" value="allChoose">
											<%@ include file="/wjdc/stxx/zjAllChoose.jsp"%>
										</logic:equal>
										<!-- �ʴ��� -->
										<logic:equal name="lx" property="lxdm" value="questions">
											<%@ include file="/wjdc/stxx/zjQueChoose.jsp"%>
										</logic:equal>
									</logic:iterate>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4">	
								<p style="font-size:14"><b>&nbsp;&nbsp;&nbsp;&nbsp;${rs.jwy }</b></p>
							</td>
						</tr>
					</tbody>
			</logic:equal>
		</table>
		</div>
		<!-- �����Ϣ end-->
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/wjdc/other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
		</html:form>
	</body>
</html>
