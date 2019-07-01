<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/js/jtpjsz.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				autoSetSqkg();
				jQuery("[name=sfkfsq]").bind("click",autoSetSqkg);
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/jtpjsz">
		<html:hidden property="ksqxslx" styleId="ksqxslx"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���影����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>��������
							</th>
							<td colspan="3">
								<html:text property="jxmc" styleId="jxmc"  maxlength="25"></html:text>
							</td>
						</tr>
						<tr>
							<th width="">
								����˵��<br/>
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="jxsm" rows="4" styleId="jxsm" style="width:96%;" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th width="">
								<span class="red">*</span>����������λ<br/>
								<font color="red">(ѡ�������޸�)</font>
							</th>
							<td colspan="3">
								<div style="float: left;">
									<html:radio property="jtpjdw" value="xy" styleId="xy"><label style='cursor:pointer' for="xy">��<font color="blue"><bean:message key="lable.xb" /></font>Ϊ��λ</label></html:radio>
									<html:radio property="jtpjdw" value="bj" styleId="bj"><label style='cursor:pointer' for="bj">��<font color="blue">�༶</font>Ϊ��λ</label></html:radio>
									<logic:equal name="xxdm" value="12309">
									<html:radio property="jtpjdw" value="qs" styleId="qs"><label style='cursor:pointer' for="qs">��<font color="blue">����</font>Ϊ��λ</label></html:radio>
									</logic:equal>
									<logic:equal name="xxdm" value="10466">
									<html:radio property="jtpjdw" value="qs" styleId="qs"><label style='cursor:pointer' for="qs">��<font color="blue">����</font>Ϊ��λ</label></html:radio>
									</logic:equal>
									<html:radio property="jtpjdw" value="qt" styleId="qt"><label style='cursor:pointer' for="qt">����</label></html:radio>
								</div>
							</td>
						</tr>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����������</span>
							</th>
						</tr>
					</thead>
						<tr>
							<th width="">
								��������
							</th>
							<td>
								${dqxn} ${dqxq}
							</td>
							<th width="">
								<span class="red">*</span>������������
								<br /><font color="red">(ѡ�������޸�)</font>
							</th>
							<td>
								<html:select property="pdxn" styleId="pdxn"	style="width:">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
								<html:select property="pdxq" styleId="pdxq"	style="width:">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>								
							</td>
						</tr>
					<logic:equal name="iswzdx" value="1" >
						<tr>
							<th width="">
								ѧ����������Ա�趨
							</th>
							<td colspan="3">
								<logic:iterate name="zwList" id="zw">
									<html:checkbox property="ksqxslx" value="${zw.zwid}"><lable style='cursor:pointer'>${zw.zwmc}</lable></html:checkbox>
								</logic:iterate>
							</td>
						</tr>
					</logic:equal>
						<tr>
							<th width="">
								<span class="red">*</span>���뿪��
							</th>
							<td colspan="3">
								<div>
								<html:radio property="sfkfsq" value="1" styleId="sfkfsq1"><label style='cursor:pointer' for="sfkfsq1">����</label></html:radio></lable>
								<html:radio property="sfkfsq" value="0" styleId="sfkfsq0"><label style='cursor:pointer' for="sfkfsq0">�ر�</label></html:radio></lable>
								</div>
							</td>
						</tr>
						<tbody id="kqxx">
							<tr>
								<th width="">
									���뿪��ʱ��
								</th>
								<td colspan="3">
								<html:text property="sqkfkssj" styleId="sqkfkssj" style="width:120px;" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'sqkfjssj');" />
								��
								<html:text property="sqkfjssj" styleId="sqkfjssj" style="width:120px;" onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'sqkfkssj');" />
								</td>
							</tr>		
							<tr>
								<th>
									<span class="red">*</span>�������
								</th>
								<td colspan="3">
									<html:select property="splcid" styleId="splcid" disabled="false"
										style="width:280px;">
										<html:option value=""></html:option>
										<html:options collection="shlcList" property="splc"
											labelProperty="lcxx" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</tbody>
				</table>
			</div>
			<div style="height: 30px"></div>
			<div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button"
										onclick="save('jtpjsz.do?method=add&type=save','jxmc-pdxn-jtpjdw-sfkfsq');return false;"
										id="buttonSave">
										�� ��
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>
