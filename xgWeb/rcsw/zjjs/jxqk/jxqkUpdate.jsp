<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
	</head>

	<body>
		<html:form action="/zjjsRcswJxqk" method="post">
		<input type="hidden" value="${rs.pkValue }" name="pkValue"/>
		<div class="tab">
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<logic:notEqual value="view" property="doType" name="zjjsJxqkForm">
									<button type="button" id="buttonSave"
										onclick="if(checkSearchTj('rdsj','bysj')){saveUpdate('zjjsRcswJxqk.do?method=jxqkUpdate',
										'bmmc-xm-xb-csrq');}">
										����
									</button>
								</logic:notEqual>
								<button type="button" id="buttonSave"
									onclick="window.close();return false;">
									�ر�
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				<thead>
					<tr>
						<th colspan="4">
							<span>ѧ����Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="16%">
							<font color="red">*</font>ϵ��
						</th>
						<td width="34%">
							<html:text property="save_bmmc" maxlength="25" value="${rs.bmmc }" styleId="bmmc"></html:text>
						</td>
						<th width="16%">
							<font color="red">*</font>����
						</th>
						<td width="34%">
							<html:text property="save_xm" maxlength="10" value="${rs.xm }" styleId="xm"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>�Ա�
						</th>
						<td>
							<html:select property="save_xb" value="${rs.xb }" styleId="xb">
								<html:option value="��">��</html:option>
								<html:option value="Ů">Ů</html:option>
							</html:select>
						</td>
						<th>
							<font color="red">*</font>��������
						</th>
						<td>
							<html:text property="save_csrq" readonly="true"
									   onclick="showCalendar(this.id,'y-mm-dd')" 
									   styleId="csrq" value="${rs.csrq }"
							></html:text>
						</td>
					</tr>
					<tr>
						<th>
							����ѧ��/ѧλ
						</th>
						<td>
							<html:text property="save_xl" maxlength="15" value="${rs.xl }"></html:text>
						</td>
						<th>
							��ҵԺУ
						</th>
						<td>
							<html:text property="save_byyx" maxlength="15" value="${rs.byyx }"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							��ѧרҵ
						</th>
						<td>
							<html:text property="save_zy" maxlength="15" value="${rs.zy }"></html:text>
						</td>
						<th>
							רҵ����ְ��
						</th>
						<td>
							<html:text property="save_zyjszw" maxlength="10" value="${rs.zyjszw }"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							�ִ���רҵ������
						</th>
						<td>
							<html:text property="save_xzy" maxlength="15" value="${rs.xzy }"></html:text>
						</td>
						<th>
							�������רҵ
						</th>
						<td>
							<html:text property="save_sqjxzy" maxlength="15" value="${rs.sqjxzy }"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							����ԺУ
						</th>
						<td>
							<html:text property="save_jxyx" maxlength="15" value="${rs.jxyx }"></html:text>
						</td>
						<th>
							���ʱ��
						</th>
						<td>
							<html:text property="save_rdsj" readonly="true"
									   onclick="showCalendar(this.id,'y-mm-dd')" 
									   styleId="rdsj"  value="${rs.rdsj }"
							></html:text>
						</td>
					</tr>
					<tr>
						<th>
							��ҵʱ��
						</th>
						<td>
							<html:text property="save_bysj" readonly="true"
									   onclick="showCalendar(this.id,'y-mm-dd')" 
									   styleId="bysj" value="${rs.bysj }"
							></html:text>
						</td>
						<th>
							ѧ�ѽ��
						</th>
						<td>
							<html:text property="save_xfje" maxlength="10" value="${rs.xfje }"
									   onkeyup="checkInputNum(this)"
									   onblur="checkInputNum(this)"
							></html:text>
						</td>
					</tr>
					<tr>
						<th>
							�����
						</th>
						<td colspan="3" style="work-break:break-all;">
							<html:text property="save_hjqk" maxlength="40" style="width:80%" value="${rs.hjqk }"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							�������
						</th>
						<td>
							<html:text property="save_bzqk" maxlength="10" value="${rs.bzqk }"></html:text>
						</td>
						<th>
							�Ƿ���ס��Ԣ��ʱ�䣩
						</th>
						<td>
							<html:text property="save_rzgysj" readonly="true"
									   onclick="showCalendar(this.id,'y-mm-dd')" 
									   styleId="rzgysj" value="${rs.rzgysj }"
							></html:text>
						</td>
					</tr>
					<tr>
						<th>
							��ע<font color="red"><��200��></font>
						</th>
						<td colspan="3" style="work-break:break-all;">
							<html:textarea property="save_bz" style="width:80%" rows="5" value="${rs.bz }"
										   onblur="checkLen(this,200)"	
							></html:textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<logic:present name="message">
			<script>
				alertInfo('${message}',function(){
					if (window.dialogArguments) {
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				})
			</script>
		</logic:present>
		</html:form>
	</body>
</html>
